Re-engineering (2): Design constraints
======================================

The issue of audience described in my last message has a significant impact on
our design. You've probably already guessed:

* useful JavaDoc is paramount
* full coverage is important
* predictable API is important

As much as possible, this message describes conceptual goals and constraints,
rather than elaborating on actual implementation. You might therefore be
tempted to dismiss this discussion, but note that as of this writing I have a
working prototype that so far achieves these objectives, so realizing them is
at least possible.

In no particular order, then:

Overall feel
============

The basic pattern is good...
----------------------------

There is lots of java-gnome code out there, even if most of it is tucked away
quietly. So people (certainly myself among them) have been reasonably happy
with the basic idioms that working in java-gnome has presented.

This is not, however, a governing constraint.

...but API breakage is going to be necessary
--------------------------------------------

This can't be helped. Certainly, if you recall the areas of inconsistency that
Owen pointed out in his messages to this list last year, you will recognize
that it will be impossible to switch to a (presumably generated) algorithmic
mapping of the underlying libraries while somehow maintaining the chaotic
variety of method naming schemes that are present today. The two simply aren't
compatible.

There are also vast areas of GTK that aren't covered properly -- mostly
because of missing accessors and mutators for properties, but also a fair
number of fundamental features are missing as well. I won't mention the stuff
that notionally has coverage but that in reality, well, just doesn't work.

So there's going to be API breakage. It may well be considerable (ie major
version number increment API change, along the lines of what we went through
from GTK+ 1.2 to GTK+ 2.0). 

At which point we have a golden opportunity.

Improvement
-----------

Frankly, there really aren't too many sacred cows. Some areas of the coverage
are cumbersome or are implemented poorly. And these are definitely targets for
repair.

TreeView is a classic example. Right now we have an implementation that is
serviceable, but oh my is it hard to use. This in no small part reflects the
very thick complexity of the underlying GTK TreeView and TreeModel APIs. Now,
I rather expect that when we crank out generated bindings, that, if anything,
the situation will be worse because the constructs in GTK really _are_ low
level. This is where we potentially have an opportunity to do better this time
around. As a supplement to the low level APIs, it may well make sense to
develop a wrapper layer on top of the generated low level function mappings
which would provide a much simpler API and which would integrate with basic
Java language idioms more cleanly.

This particular topic will be continued in another thread; lots of room for
creativity and ideas here. See FIXME

<a name="comfort"></a>

The bindings will be comfortable to Java developers
===================================================

Java is not the "best" language. Nor is it the "worst". Frankly, it's just
another programming language and that's more or less that. But it does have a
few characteristics that are worth taking advantage of.

More than anything, the existence of outstanding IDEs has made java-gnome a
pleasure to work in. And it's a specific feature of the IDE that matters: code
completion. Getting a list of property setters or listeners that you can
connect along with popup JavaDoc describing what each one does makes for a
vastly superior development experience.

And that means:

<a name="hand-written"></a>

Extensive JavaDoc that is hand written, not generated
-----------------------------------------------------

One of the reasons that GTK is hard to use is because the documentation -- for
all that it is extensive -- doesn't really tell you how to use it if you don't
already know what you're doing. Certainly, if you are not already a wizard C
programmer you can forget about it being told how to use it _right_. Trying to
do a one to one translation from C descriptions to Java ones would be foolish
at best (doesn't mean we can't cut and paste many of the property
descriptions).

A related problem in API documentation in general is that it is usually
written for a given entry point and defined (generally linear) reading path.
Which is no good whatsoever when you're neck deep in the middle of something,
and you're either mouse hovering over a method name or you've jumped directly
to some arbitrary page in the HTML docs.

This, more than anything else, is where approachability comes in.

We will have **extensive** explanation to the point of considerable redundancy
so that people new to GTK and using our bindings to try and write a GTK
program can quickly learn how the heck to do what it is they want to do. YES
that violates the programming dictat to abstract out functionality rather than
duplicating code, but YES we're going to do it anyway.

They key to understand why is to realize that whenever someone looks at a
given snippet of documentation, they have a specific question in mind.
Perhaps its "how do I get a button click". If you look at GtkButton, you
discover there's clicked and activate. What's the difference? The doc should
explain, and refer to both, rather than just "get a clicked event" -- thanks
for nothing (it's even worse, there's activate() and an activate event signal.
What's the difference?)

And not just

    "Set the rabbit property." (gee, thanks for nothing)

but

    "Set the rabbit property to one of fast, lean, fat, or dead. You probably
    want to choose a fast rabbit if you're betting on the races, whereas
    selecting dead is appropriate if you're hungry.  On the other hand, fat
    rabbits are cute and cuddly and make great companions."

Finally, we will include code snippets in our JavaDoc. Yes, code snippets.
Phil and I were talking the other night and recalling how amazing the original
Turbo C|Pascal documentation was -- every single function call in the standard
library had a small bit of code to show how you might use the thing. Now
that's not necessary for every last method, but certainly at class level it's
a great idea. See the JavaDoc for Button's [connect()][example]
for an idea of what I'm getting at.

[example]: http://java-gnome.sourceforge.net/4.0/doc/api/org/gnome/gtk/Button.html#connect(org.gnome.gtk.Button.CLICKED)

Implementation details hidden
-----------------------------

One of my personal grievances about the present java-gnome bindings is the
amount of internal machinery that is exposed. With the possible exception of
`getType()` [and I'm not even sure that will be necessary], I'd like to see a
strategy that completely hides internal implementation details.

This means, for instance, that if (say) GtkButton were a generated class
{underlying, implementing, or being the delegate of} Button, then GtkButton
simply **should not** visible to anyone developing an application with the
bindings. Java has a rich scoping mechanism and we should make the most of it.


Development features
====================

Must be able to use an IDE to do any of the Java coding required
----------------------------------------------------------------

This came up last week, and I reiterate it here as a design constraint. A
major consideration is that whatever part of the bindings that have to be hand
crafted can be maintained from an IDE.

Even if every last drop of the bindings were to be generated, there would
still be the requirement to hand write documentation as described above. If
someone is writing JavaDoc comments, they should be able to do so from within
an IDE's editor. Eclipse for one provides warnings (tunable to error!) if
JavaDoc is malformed. [Example: we've got un-escaped `<` and `>` characters in
the documentation of Accelerator; so, surprise, they API documentation there
is confusing].

This is one reason why the pattern of maintaining documentation blobs in
individual files and then somehow trying to copy those blurbs into the right
inclusion points at code generation time is such a terrible approach to doing
things. You have to develop a terribly complex naming scheme of how and where
to include such snippets, and then you've got no support for writing the
snippets in the first place.

[SWIG does this by embedding `""` strings in a *config* file. For 10,000
methods? Yeah, right]

A bit more broadly, the overall anti-pattern is injection. Trying to embed
generated code into programmer maintained code or vice versa is a nightmare,
in no small part because of the collision between what you're editing and that
file being edited, amended or replaced right out from beneath you. No, if
you're going to mix and match, they need to be in separate files.

This isn't SWING, or AWT, or SWT, or...
---------------------------------------

This is an important point. 

"Java-like" and "doing-it-like-SWING-does-it" are not the same thing.

We have a free hand at this point how the more complex APIs are presented. I
referred you to the long discussions about how to handle events. Our
opportunity for improvement is to come up with something in these cases that
makes sense, that is consistent, and that is easily learnt. In other words,
approachable.


...It's GTK!...
---------------

GTK as a toolkit has quite a number of distinctly GTKish notions.  There's no
getting around that, and more importantly, presenting those knobs and levers
is *the whole point*.

...but that doesn't mean every last thing needs to be exposed
-------------------------------------------------------------

Providing full access to the richness of the GTK API is our primary technical
goal. But this doesn't mean that every last little thing needs to be exposed.

What I do want to see, though, more than anything, is consistency. Once we
pick a way to do something, let's stick to it.

A few cases come to mind:

* StockItems: some places the current bindings pass a strongly
defined Type such as GtkStockItem, and some places we pass a String.

* Properties: many GTK widgets have convenience methods around the
more popular properties. So those we have those bound; meanwhile, we've
occasionally bound some of the more interesting properties. We could choose to
only expose things via `setProperty()`. Or we can choose to expose a getter
and/or setter for each and every property (the existing bindings lean in this
direction). I'm inclined to think that this is the best approach as it allows
us to be really strongly typed. But whichever we do, we should do it
consistently across the board.


...and it doesn't mean we need to completely bind GLib
-------------------------------------------------------

This may well be contentious. I'm not even certain that it is correct. But
one thing I have observed is that we may not need to strongly bind the GLib
and GObject machinery. My investigations so far have tended towards the
conclusion that _so long as we use GLib and GObject correctly on the C side,
the fact that Java is already a perfectly well object oriented language means
we may be able to take [considerable] shortcuts_.

No mapping of deprecated features
---------------------------------

Deprecated methods and classes are hugely confusing to newcomers for whom GTK
is already complex enough to grok.

There's tons of cruft in GTK, and tons of cruft in our existing libraries. We
will, I'm sure, build up new cruft in the future, but it would be silly to
start a new major version with any of it. So none of the deprecated code in
GTK and friends will be exposed by our new bindings.

Conclusion
==========

Those are the list of features and qualities that I think must constrain us.
The only one that *really* drives design is the JavaDoc one. For what I hope
are obvious reasons we want to write JavaDoc in `.java` files, and that more
than anything helps us find our way as we go down the road of considering what
architectures might work.

AfC

_Originally written as an email to java-gnome-hackers by Andrew Cowie on 24
Aug 06; last modified 4 Dec 06._

<!--
 
  Copyright © 2006 Operational Dynamics Consulting, Pty Ltd

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
