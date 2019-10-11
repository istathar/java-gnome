Re-engineering (3): Package names
=================================

This is a minor matter, but while we're breaking everything anyway, several
people have commented that the old java-gnome 2.x package names were _really_
messed up.

Library names, prefixes, and suffixes
-------------------------------------

Specifically, we have `glib-java`, `cairo-java`. All good. Then we have
`libgtk-java`. Huh? Shouldn't that be just `gtk-java`? Or, alternately, the
first should have been `libglib-java`. Tell me that doesn't look stupid.

Personally, I'm inclined to ditch the lib prefix:

* `glib-java`  
* `cairo-java`  
* `gtk-java`  

but then it gets tricky:

* `gnome-java`  ? 

We already get no end of confusion with people wondering where to get
"`java-gnome`". More to the point, `libgnome{,ui}` is the thing we're
wrapping, so should it be

* `libgnomeui-java`  ? 

for instance? Same applies to Glade - `libglade` is the thing we're wrapping,
which would imply we keep

* `libglade-java`  X 

even though that seems stupid. On the other hand, just getting on with it,
regardless of the underlying library name, seems smart:

* `glade-java`  

which, incidentally, matches what the GTK# folks did, calling it
`glade-sharp`.

That raises another point. Is the `-java` suffix what people want? We could as
easily switch to:

* `java-glib`  
* `java-cairo`  
* `java-gtk`  
* `java-gnome`  ? 
* `java-glade`  
* `java-vte`  

etc. I'm really undecided about it. Using a suffix matches current practise
and the GTK# '-sharp' naming pattern to boot, whereas the prefix keeps all the
libs nicely together in a directory listing (and one in the middle,
`java-gnome`, matches our current branding).

Open to suggestions.

Of course, changing package names is hell for downstream distros doing the
actual packaging, but given that there is a major API break happening
concurrently, I don't view renaming as a completely offencive thing to
contemplate :)

Number of release blobs
-----------------------

An even bigger question is whether or not we need this many individual
libraries. I for one am already **sick and tired** of doing releases on 8+
libraries each time a macro in the single base library changes. (Of course,
that's just the gross negligence of the GNU autotools for you. We won't be
using those in the future).

So we could well reduce it to

* `java-glib`  [to allow independent bindings to be written]
* `java-gtk`  [to wrap the basic GTK + Glade and/or GtkBuilder]
* `java-gnome`  [everything else]

And be done with it. A variation on this theme is to do as pyGTK has done:

* `pygtk` 
* `gnome-python-desktop` 
* `gnome-python-extras` 

etc which is a naming split based around the constraints of what is allowed to
go into the bindings set, desktop set, etc of GNOME. Looks a bit inconsistent,
though.


Decision? How about just one!
-----------------------------

Another variation is what the GTK# folks did, which is one source leads to
however many packages a given distro wishes. That actually seems rather
appealing, but they ran all afoul of the GNOME release team over this for some
reason.

Personally, anything that lets us not have to zillions of tarballs would
make me much happier, so:

Right now, **the new Java bindings are just a single `java-gnome` and it
contains everything**. We'll see how long we can keep that up, but someone is
going to have to pay me a lot of money to change it.

AfC

_Originally written as an email to java-gnome-hackers by Andrew Cowie on 24
Aug 06; last modified 24 Aug 07._

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
