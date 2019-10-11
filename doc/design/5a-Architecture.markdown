Re-engineering (5): Internal Architecture
=========================================

I have alluded several times to having worked out an architecture that I think
will do nicely for us given the constraints that I have articulated.

I spent about a month trailing different alignments of things. When I later
found Jeff Morgan's "peanut" and "cashew" suggestions, I smiled, but sadly
those variations had already been considered *and discarded* because they
weren't sufficient.

Actually, I've done two architectures. I got all the way to a working
prototype with the first approach, only to discover at the very end that I'd
missed something. Damn. It was, nevertheless, a rather elegant solution, and
so I'll describe it:

Interfaces
==========

The defining constraint is approachability, defined as a) being able to hand
write documentation and b) ensuring and algorithmic mapping. [Refer to the
documents in the (2) series]. I also mentioned that I consider it abhorrent to
expose any implementation machinery to ordinary developers using the bindings.

One common technique in Java land to achieve this is to only hand instances of
Interfaces to public users, with the actual concrete implementation being
otherwise hidden.

On the pro side:

If we hand write these interface files, and do our JavaDoc there. This is cool
because at the end of the day we have to map API documentation to methods, and
what better meta mapping that to do so *in* Java.

We would be held to ensuring we obey the algorithmic mapping because if we
borked a method name the generated classes wouldn't map.

It hides the implementation, so all the machinery (signal handlers, object
Handles, etc) can be hidden from view in the concrete class and not be on the
interface.

But there's a con:

The biggest objection was to come up with a way to factory create these
instances. In java-gnome 2.x, you do:

    Button b = new Button("Press Me");

Since Button would now be an Interface, I pondered long and hard about what we
might replace that with. Putting a static "`create`" factory method on Button
doesn't work when Interfaces are involved because of course there's no code in
an Interface file. Creating a separate factory class in parallel naming seemed
silly:

    Button b = ButtonFactory.createWithLabel("Press Me");

and NOT exposing the underlying generated code is the whole point, so:

    Button b = InternalButtonImplementation.createWithLabel("Press Me");

sucks because it's got nothing to do with the Button we're trying to create.

(Actually, the name GtkButton is the obvious choice for the generated code,
but the whole point is to not have that class to be visible to developers)

Then one night I struck upon this idea instead: why not put the constructors
on the static classes we have, roughly one per module, that are named with
that module name?

    Button b = Gtk.createButton("PressMe");

Which I then shortened about 5 minutes later to:

    Button b = Gtk.Button("PressMe");

Which I was really pleased with. [So pleased in fact that I picked up the
phone, called Ismael in England, and babbled on for 30 minutes about how cool
I thought it was. Poor ijuma. :)]

It has a lot to recommend it: it quietly groups things by which GNOME module
things are from without being offencive about it. The underlying instance that
is returned is some generated class which implements our Button interface. All
good. And other than constructors being separated from the declaring
interfaces, not so bad.

I coded up a fully working prototype, all the way to it popping a window up on
the screen with a button in it. It worked quite nicely. One thing I paid
enormous attention to was making sure that whenever you hit `<COMPLETE>` in
your IDE it offers sensible choices, not mush.

Things were going swimmingly until I ran the JavaDoc generator over my
prototype. When I loaded them up in my browser I was surprised that the class
hierarchy was missing.

Oh shit.

The part I'd missed was not only can Classes can implement more than one
Interface, but Interfaces can implement more than one Interface as well. So
there's no singular parent, so,

Con: JavaDoc doesn't show that nice inheritance tree.

[actually, I'd often wondered why Eclipse wouldn't show a clean super
hierarchy of a bunch of interfaces like it would for classes. Duh. Here's why
-- it can't draw a simple parent relationship because there could be more than
one parent at each step up the hierarchy]

So is this a show stopper?

I think so: given the criteria of approachability, seeing that GtkButton
descends from GtkBin descends from GtkContainer descends from GtkWidget is
really important for quickly coming to understand the whole stack.

We could play games like generating an HTML snippet that would have the
inheritance diagram we want, but I'm not hugely impressed with that idea, and
it still looks crummy in an IDE.

I won't go into all the design that I did for how to do custom overrides of
behaviours and code we'd have to add for various things, or the design I had
for the generated side. Suffice it to say that those parts all worked quite
nicely.

Concrete wrappers
=================

So if that nice class inheritance diagram is important, then the public API
needs to be in concrete classes. {shrug} ok.

It actually only took me about a day or so of mad hacking to take the
Interfaces prototype and morph it into something that worked this way. There
were a couple of surprising changes along the way, but things are looking
pretty good.

The trick remains this: we need to separate the hand written Java code (where
the JavaDoc goes and [now] where custom overrides of behaviour [can] go) from
the generated Java code. The tough parts are issues like:

1. name spaces (ie, effectively leveraging the combination of package space
and class name space).

2. inheritance hierarchies -- who is extending what? Given that any native G
entity has two parts on the Java side (the hand written part with the API that
we expose, and the machinery that we generate and put ... somewhere) ... Is
there an inheritance hierarchy in both public classes and generated classes?
Yuk, but if necessary, so be it.

3. where does the information about the underlying native {GObject | Boxed |
Flag | Enum | whatever} live?

4. Somewhat orthogonal to all this is consideration of what pattern to use
across the JNI boundary. At first it was tempting to use the fact that a
native instance method automatically conveys a reference to the Java side
object, which seemed to offer great possibilities in terms of skipping the
whole `getHandle()` / `getPointerFromHandle()` nightmare.

I'm not going to iterate through the universe of possibilities, though a lot
may come out if anyone cares to dispute the choices I'm suggesting. Anyway,
this is what I ended up with:

Usage wise, we're back to a familiar idiom:

    Button b = new Button("Press Me");

Button is a hand written concrete class. That is not as ideal as the
"Interfaces" design because instead of typing:

    /**
     * Some documentation
     */
    public String getLabel();

we of course have to have to type:

    /**
     * Some documentation
     */
    public String getLabel() {
        // call to the generated code, however that's to be done...
    }

which means that we have to hand write the signature of the generated method
we're calling, and loose the rigour of matching our method names to the
underlying native API we're wrapping as a check of algorithmic mapping.

That's the theoretical risk; in practise it evaporates quite nicely. To
explain why it's not a problem, consider the situation in the 2.x bindings:

At the moment, we have a hand written method signature and the native
declaration embedded in the same class. Someone typed:

    /**
     * Some documentation
     */
    public void setLabel(String text) {
        gtk_button_set_label(getHandle(), text);
    }

    protected native void gtk_button_set_label(Handle handle, String label);

This is what allowed us to get all wonky with our names as Owen pointed out to
us... there was nothing obviously reminding us to name our method `getLabel()`
so stuff like `getSticker()` crept in.

By ensuring the generated code has the algorithmic name that under 98% of
circumstances matches what we are supposed to present as public API, there's
less guess work involved:

    /**
     * Some documentation
     */
    public String getLabel() {
        return getLabel();       // FIXME!
    }

So, duh, if you're wrapping something that the generator spewed out as
getLabel() you just call your method "`getLabel`" .

[For an example of places where we will want to diverge from slavish
naming and be a bit more sensible, consider `getAlignment()` which has two
floats out parameters. We shall again wrap that as `getAlignmentX()` and
`getAlignmentY()`, but even so, the naming is still consistent and
predictable - and because we have hand written wrapper classes
presenting the public API, its easy to add the few lines of custom code
that will be necessary to present these methods. In fact, I've already
trialed it]

Of course, there's the obvious question of where the hell does the second
`getLabel()` come from? I said it was generated, and I said that an
anti-pattern we're absolutely going to avoid is trying to inject generated
code and hand maintained code in the same file.

I mentioned wanting to use native instance methods (rather than class
methods), and I spent a while mixing and matching trying to figure that out.
The reason to use that mechanism seemed to be a nice match between improving
the elegance of our code along with being a way to reduce the JNI boundary
crossing pressure that Andrew Cagney has commented on.

In the end, I landed on something quite different, and the proxy (aka, Handle)
management is for another email. Relevant for our purposes here is just this:
rather than having a delegate Proxy object (which we call Handle), I've
abstracted that down to a single member field on a class that is at the very
top of our object hierarchy. So instead of passing the Handle object to the
generated code, we just pass a reference to self which Java provides with
keyword "`this`". Too easy.

Anyway, what's more important is _"how much work does the person writing the
wrapper class have to do?"_

How's this:

    /**
     * Some documentation
     */
    public void setLabel(String label) {
        GtkButton.setLabel(this, label);
    }

Details:

* The class GtkButton is generated code.

* It is in the same package as our Button class, but unlike button is
  default visibility (aka "package" visibility aka "package private"). So
  someone hacking on the bindings can see it with `GtkB<COMPLETE>` but
  `Gtk<COMPLETE>` is **not** polluted for someone _developing_ with our
  bindings since they can't see GtkButton. (ie, `Gtk<COMPLETE>` will lead to a
  single import, Gtk, and show only `Gtk.init()`, `Gtk.main()`, etc -- but see
  note [1])

* `setLabel()` is a generated class method in GtkButton.

* It in turn calls a static native method [declaration] which is also a
  generated class method in GtkButton; it was tempting to make `setLabel()`
  the static native method, but it turns out that we need to be a bit of
  translating before we can make the JNI call. As we've discussed, better to
  do that work on the Java side and keep the JNI code as trivial as possible.

(Actually, I ended up on using the existing convention of using the name of
the C function as the name of the native method; more than anything, it looks
really good in stack traces when the top most line is evidently the name of
the underlying C function that we're wrapping and says "native method")

So, `gtk_button_set_label()` is also generated class method (but this time
`native`, of course) also in generated file GtkButton.java . And moreover,
there's not even any reason for it to be more than private -- so even
java-gnome hackers are kept away from this implementation goo.

Here's a skeleton of what generated code for setLabel looks like:

    final class GtkButton extends Plumbing {

        static final void setLabel(Button self, String label) {
            gtk_button_set_label(pointerOf(self), label);
        }

        private static native final void gtk_button_set_label(long self, String label);
    }


Of course, running `javah` over that results in a header file which allows
us to double check that the generated C code matches up.

The JNI function on the C side implementing this is:

    JNIEXPORT void JNICALL
    Java_org_gnome_gtk_GtkButton_gtk_1button_1set_1label
            (JNIEnv *env, jclass cls, jlong _self, jstring _label)
    {
            ...
    }


This generated C code is very straight forward as well: take the JNI
parameters and cast them to G ones (trivial in almost every case), calling the
JNI functions to extract things like Strings, then calling the native
function, then re-encoding the return value if any.

Discussion
----------

So, at the end of all this, everything at each layer is nice and clean. It's
easy to write the API that we present to the public. Since there's a layer
between the generated code name and the API we present, we have 100% control
of the API we present and can guarantee our API stability even if people mess
with the signatures of the underlying functions.

You'll recognize that this code seems to look similar to our existing code
now. That's not surprising given the nature of the problem space but it's only
superficial. The handling of parameters, the signatures, and the visibility at
each layer are deliberately controlled to hide every single implementation
detail from users. I'm rather pleased with the outcome.

Throughout the design work I've been prototyping with code and testing with an
experimental application. Pops up a Window with a Button quite nicely, thank
you very much. I've also put some work into roughing out a code generator, and
the approach from that side seems quite promising.

And for the occasions that inevitably will arise where we have to do something
by hand at the layers that are otherwise all generated, the necessary pattern
is simple to follow. After all, I hand wrote the entire prototype and along
the way paid close attention to being able to write is simply, easily, with
brain on but without aggravation.

Conclusion
==========

I opened this message with "The defining constraint is approachability,
defined as a) being able to hand write documentation and b) ensuring and
algorithmic mapping. I also mentioned that I consider it abhorrent to
expose any implementation machinery to ordinary developers using the
bindings." These sort of thing is important for -hackers, too: if it's
too murky, then no one will be able to understand or maintain it.

I'm quite pleased to report that it looks like we've found an
architecture that will meet these goals.

AfC

_Originally written as an email to java-gnome-hackers by Andrew Cowie on 19
Sep 06; last modified 25 Sep 07._

<!--
 
  Copyright © 2006-2007 Operational Dynamics Consulting, Pty Ltd

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
