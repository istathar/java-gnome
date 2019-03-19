Re-engineering (4): TreeView and TreeModel API design
=====================================================

A few weeks ago I wrote:

> TreeView is a classic example. Right now we have an implementation that is
> serviceable, but oh my is it hard to use. This in no small part reflects the
> very thick complexity of the underlying GTK TreeView and TreeModel APIs.
> Now, I rather expect that when we crank out generated bindings that, if
> anything, the situation will be worse because the constructs in GTK really
> **are** low level. We potentially have an opportunity to do better this time
> around. As a supplement to the low level APIs, it may well make sense to
> develop a wrapper layer on top of the generated low level function mappings
> which would provide a much simpler API and which would integrate with basic
> Java language idioms more cleanly.

We've discussed this a bit in the past, but in general the scope was limited
by what we already have in place. Removing that constraint, lets see what we
can come up with?

Some thoughts to seed the discussion: I had a conversation with some of the
pyGTK hackers the other day, and they made some interesting comments in
respect of how they have done things. Interpreting these observations in terms
of what we might consider doing:

Use the language's iterator?
----------------------------

Java already has a "perfectly good" (well sort of) idiom for iterators.  It's
called `Iterator` :) [Yes, the one in `java.util`].

To all intents and purposes it has the same limitations as TreeIter (only
valid for one cycle through the underlying Collection, invalidated [illegal?]
if the Collection changes.  TreeIter behaves the same way). So our higher
level TreeModel API could be an Iterator instead.

Or we could not bother :)

Implement a custom model that uses language data types?
-------------------------------------------------------

We could write a custom model that implements the GtkTreeModel interface but
does so in Java and on the Java side so that you can just store Java objects
in it and carry on. You'd use this instead of [Gtk]ListStore or [Gtk]TreeStore

I'm not convinced this is a good idea, especially given boundary crossing
costs (there would have to be a C -> JNI query every time a CellRenderer
needed a bit of information), but maybe.

I imagine it would call for *considerable* work on the C side to create the
TreeModel subtype that would do all the right hooks which we would then bind
to. Be pretty cool, though.

Personally, I consider the Object to User Interface mapping to be as tough a
problem as the Object to Relational mapping problem. What you present and how
you present it doesn't have a terribly great deal to do with how you represent
that information in your object model in memory.

As such, the effort of copying data into a ListStore is to me no different
than that of copying text into a Label. The fact that the data is duplicated?
{shrug}, whatever.

Davyd Madeley's use case for a custom model was one whereby it did lazy
loading of a huge data set from disk and as such only had a partial amount in
memory and thus only that or a subset of it presented to user in the TreeView.
That does sound rather smart of them to have worked out, but in most of the
java-gnome code I've seen, that sort of thing is done elsewhere in the code --
the model in TreeView's MVC is *not* used for primary data storage. After all,
we've got a perfectly good object-oriented language for that.

More natural idioms?
--------------------

We could, for example, present Integer and Long and String as the TreeModel
column types, rather than DataColumnInt and so on.

This one is a touch more appealing to me. The `DataColumn[]` that we pass to
our {Tree,List}Store constructor has always struck me as a bit cumbersome; or,
at least, it is because of the requirements to pass the individual DataColumn
objects as identifiers to later `{set,get}Value()` calls. Ugh.

Just touching up around the margins isn't really going to change anything, but
a wholesale model API improvement would be something to see.

Just for consideration. Comments welcome.

AfC

_Originally written as an email to java-gnome-hackers by Andrew Cowie on 11
Sep 06; last modified 7 Dec 06. This document most certainly will evolve if
we actually redesign the TreeModel and TreeView APIs._

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
