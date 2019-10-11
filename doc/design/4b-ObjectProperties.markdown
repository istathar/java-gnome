Re-engineering (4): GObject property handling API
=================================================

I was chatting with Davyd last night about how I'd achieved a generalized
setProperty() capability for GObjects in the new bindings. I wrote him an
email about one of the details, included below, but along the way I've arrived
at a design decision that I'd like to make sure everyone is ok with.


Type safety
-----------

Java is strongly and statically typed. That's a burden in some cases, but of
course the benefit of that is type *safety* if something takes an Image, you
can't shot yourself in the foot by passing it a Font.

That all goes out the window if you have a generic mechanism that takes a
String as the property name, and some arbitrary Object to use as the its value
and then tries to do something with it:

    setProperty("label", someFont)

this will compile but blow at runtime in a really ugly fashion.


Method Signatures
-----------------

For a while I've considered properties as special, or at least as needing
special treatment compared to the more usual functions that are provided. I
had a good chat with Sandor about this, and we agreed that we would probably
end up with:

    setPropertyFoo()
    setPropertyBar()

etc as a good way to  present such properties to developers using our bindings
in a way that would allow us type safety.

But consider that things like GtkButton have a property called "label".
GtkButton of course provides a "convenience" method to set the label. Yup,
it's `gtk_button_set_label()` which java-gnome mapped years ago as
`setLabel()`. If we consider our wrapper around the standard method, and the
property, we would end up with

    setLabel()
    setPropertyLabel()

in the same Button class, and that's just silly.


Completion
----------

One of the characteristics we discussed that we're all in love with is
code-completion of appropriate methods turning out to be a really cool way of
finding out what things you can manipulate in (say) a Button, like,

    setLabel()
    setRelief()
    setFocusOnClick()
    setImage()

etc.

These all exist as explicit methods because in a lot of cases the methods
pre-date the property system in GObject (since GtkObject predates GObject
which was abstracted out of GTK+).

However, just because there is a label property does not mean that we should
just set the property directly and bypass the explicit method supplied by GTK.
That's just asking for trouble, as there is always the risk that the real C
function does more than just twiddling the public property.

[ok, a String label is a bad example, but there are many more properties which
are decidedly internal mechanisms, especially in the more complex widgets]

So here comes a design decision:


Explicit methods for everything
-------------------------------

We will have an explicit, strongly typed method for any characteristic that
can be written to or read from.

Where a characteristic has an explicit function provided by GTK already, and
we have chosen to expose it, we will wrap and bind that function as normal

    setImage(Image image) -> gtk_button_set_image(button, image)

and ignore a property by the same name if such exists.

When there is no pre-existing explicit function [convenience or otherwise] on
the C side for a characteristic, and a property exists and is public [and in
this case, writable], and if we wish to expose it in our public API, we will
do so with an appropriately named, strongly typed setter method for that
property doing so by using the GObject property setting mechanism.

    setAlignmentX(float align) {
        setPropertyFloat(this, "xalign", align) -> g_object_set_property(button, "xalign", align);
    }

Thus our developers will never have to know that "xalign" is a property, and
nor do they need to risk runtime type smashups. They can simply learn that
they can set the x alignment with a predictably named method call.


Details
-------

The hacker visible (ie protected)

    setPropertyFloat()

is a type safe wrapper around the actual `setProperty()`; although we could
have done a pile of overloading, we can't overload a method's return type in
Java, so `getProperty()` wouldn't work, whereas

    getPropertyFloat()
    getPropertyString(), etc

work fine... and along the way allows us to create a GValue which we can a)
memory manage properly and b) pass to `setProperty(String name, Value value)`
without having to expose the mechanics of wrapping Fundamental types. More
below.

AfC


-------- Forwarded Message --------  
From: Andrew Cowie  
To: Davyd Madeley  
Subject: Method overloading  
Date: Mon, 06 Nov 2006 14:26:15 +1100

After you mentioned method overloading the other day and wondered why I was
bothering with different explicitly named convenience functions (protected so
only bindings hackers see 'em, but anyway), I woke up screaming worried that I
may have briefly given you the idea that I didn't know what overloading was.

Just so that you don't think I'm a complete basket case, it occurred to me to
show you what the constructor of those (likely rarely used) various
fundamental Value types all call. Each one gets its pointer [as stored Java
side in a long] by calling one of the various

    GValue.createValue()

which looks something like the code snippet that follows. Actually, not only
is the hacker visible static factory (-esque) method overloaded, but the
native methods are also overloaded (for the value of the fact that crossing
the JNI boundary with primitives is far more efficient than passing objects
across then having to reach back to get field values).

So,

    class GValue extends Plumbing {
         
        ...
    
        static final long createValue(int i) {
            return g_value_init(i);
        }
    
        static final long createValue(boolean b) {
            return g_value_init(b);
        }
    
        static final long createValue(String str) {
            return g_value_init(str);
        }
    
        ...
    
        /*
         * These ones does not match the exact prototype of g_value_init()
         * [which is (GValue*, GType)]; we do the type system magic on the
         * other side (where its all mostly macros in any case) and carry
         * out allocation using GSlice.  A rare occasion when we overload
         * the native call.
         */
    
        private static native final long g_value_init(int i);
    
        private static native final long g_value_init(boolean b);
    
        private static native final long g_value_init(String str);
    
        ...
    
        static final void free(Fundamental reference) {
            g_slice_free(pointerOf(reference));
        }
    
        /*
         * This could live in GSlice.java, I suppose, but no real reason.
         * We're below the level of clean abstraction here, and it just
         * needs to go somewhere.
         */
        private static native final long g_slice_free(long value);
    
        ...
    }

Given that I pass primitives across the JNI boundary in the end anyway, it's
tempting to wonder why not just have the `setProperty()` methods do that --
and indeed that is what I once had. But the underlying
`g_object_set_property()` takes a GValue as an argument, so going to the
trouble of wrapping Java primitives into a GValue means that my single hacker
visible `setProperty()` is org.gnome.glib.Object's

    setProperty(String name, Value value)

can be used for any of the various things that can be passed to
`g_object_set_property()`, be they Glib Fundamentals, GBoxeds, GObjects, etc,
rather than needing an overload of setProperty() for every single type at each
of developer API, generated java layer, and native layer levels.

Emailing my friends code commentary in an email. I must be on crack.

AfC  
Sydney

_Originally written as an email to java-gnome-hackers by Andrew Cowie on 6 Nov
06; last modified 4 Dec 06._

<!--
 
  Copyright © 2006 Operational Dynamics Consulting, Pty Ltd and Others

  As project documentation, this file forms an integral part of the source
  code of the library it accompanies, and thus is made available to you by its
  authors as open source software: you can redistribute it and/or modify it
  under the terms of the GNU General Public License version 2 ("GPL") as
  published by the Free Software Foundation.

  This program is distributed in the hope that it will be useful, but WITHOUT
  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
  FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.

  You should have received a copy of the GPL along with this program. If not,
  see http://www.gnu.org/licenses/. The authors of this program may be
  contacted through http://java-gnome.sourceforge.net/.

  vim: set textwidth=78 nowrap:

-->
