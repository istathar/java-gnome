Re-engineering (3): Version numbers
===================================

I've stated my [views about
versioning](http://sourceforge.net/mailarchive/message.php?msg_id=3D15113906)
before; summarizing quickly:

Our version numbers NOT coupled to underlying libraries
-------------------------------------------------------

I'd like us to break the tight coupling of our library version number to the
versions of the underlying libraries (note, plural) that we are wrapping, thus
freeing us to use the traditional major.minor.patchlevel (`z.y.x`) numbering
to properly signal API changes without having much of anything to do with
whether or not GTK or GNOME finally chooses to go to "`3.0`" next week or next
century.

[The bump to `4.y` and `3.y` is to avoid being re-entangled the moment that
the myriad GNOME libraries arbitrarily do the jump to `3.0`. As has been
pointed out elsewhere, that event will probably be marketing-driven and 
will _not_ likely imply an API or ABI break! Good optics, but bad programming
juju]

Therefore, our next release set will be (plus or minus whatever naming scheme
we use):

    glib-java   4.0.x
    cairo-java  4.0.x
    gtk-java    4.0.x
etc.

Which as a whole will be called "`java-gnome 4.0`". The pieces that are part
of the GNOME bindings set will happily go into "`GNOME 2.18`" or whatever with
these version numbers.

Release plan
------------

One final note here -- the first golden release set of the new bindings will
be **`java-gnome 4.2`**:

* `4.0` will be us hacking and bashing it into shape. I'm hoping it will be
  ready by GNOME 2.18 [March '07], assuming the work is funded;

* `4.1` will be the first unstable branch when the point eventually comes along
  after we have ported apps to `4.0` that we need to advance the bindings
  without wrecking those apps, whenever that comes. It's going to happen --
  we're sure to have forgotten something {shrug}; and

* that `4.1` will become `4.2` when it goes out with the next formal GNOME
  release after `4.1` is ready. Crystal ball time? Perhaps "`GNOME 2.20`"
  [Sept '07] or "`GNOME 2.22`" [March '08]). Here's hoping!

AfC

_Originally written as an email to java-gnome-hackers by Andrew Cowie on 7 Sep
06; last modified 7 Dec 06. This document is fairly transient and will likely
be removed once a more rigorous release plan is written_

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
