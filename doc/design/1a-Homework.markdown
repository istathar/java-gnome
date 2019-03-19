Re-engineering (1): Homework
============================

BACKGROUND
==========

Discussions of auto-generating the Java bindings around GTK, GNOME and friends
are NOT new. So its time to do your homework:

1. learn what algorithmic mapping means,
2. learn what denaturation is,
3. see why `onEvent()` will be a better method prototype than `event()`
   in a redesigned signal handling API,
4. know what a `.defs` files looks like are and where you might get them
   from.

The Great Owen Thread
---------------------

18 months ago there was an outstanding discussion on the subject of
java-gnome's API design and internals. Since it was started by Owen
Taylor, I've named it _The Great Owen Thread_. :)

You could read it online at SourceForge
<http://sourceforge.net/mailarchive/message.php?msg_id=11579420>
but don't do that: you can't make out half of it because it's so deeply
nested. Instead, I cleaned up a copy of the thread so it would be suitable for
printing and included it here:

[`TheGreatOwenThread.html`](TheGreatOwenThread.html)

Follow the link and _print it out_ with your _printer_ so you can _read it_
carefully and thoroughly. **THIS IS REQUIRED READING**. I'm not kidding. If
you want to comment on what we might do to redesign the signal handling APIs,
then read this thread. If you're interested in what the overall structure and
architecture and "feel" of the new java-gnome might be like, then read this
thread. If you want to know why its necessary to break API, read this thread.

The proposals there are not gospel, and, in fact, we've learnt a lot since
then as you will be seeing in the subsequent emails about architecture. But
it's good background: it contains a lot of wisdom from the very bright people
who preceded us as maintainers of java-gnome, and it behoves us to learn from
them. 


Aside: Memory Management
------------------------

Do note that much of the memory stuff was successfully incorporated into
java-gnome in when libgtk-java 2.8 was released containing the "memory
management patches". See the release notes for

[java-gnome 2.12.0](http://java-gnome.sourceforge.net/cgi-bin/bin/view/Main/NewsTwoTwelveZero)

It was a shame that Nick Rahn and Jeffery Morgan had to move on from the
project right when they did as they contributed so much to the the fact
that the code no longer leaks **everything**.

While you're at it, see all the release announcements over the last year
or so:

[java-gnome 2.12.1](http://java-gnome.sourceforge.net/cgi-bin/bin/view/Main/NewsTwoTwelveOne)  
[java-gnome 2.12.2](http://java-gnome.sourceforge.net/cgi-bin/bin/view/Main/NewsTwoTwelveTwo)  
[java-gnome 2.12.3](http://java-gnome.sourceforge.net/cgi-bin/bin/view/Main/NewsTwoTwelveThree)  
[java-gnome 2.14.0](http://java-gnome.sourceforge.net/cgi-bin/bin/view/Main/NewsTwoFourteenZero)  
[java-gnome 2.14.1](http://java-gnome.sourceforge.net/cgi-bin/bin/view/Main/NewsTwoFourteenOne)  
[java-gnome 2.14.2](http://java-gnome.sourceforge.net/cgi-bin/bin/view/Main/NewsTwoFourteenTwo)  
[java-gnome 2.14.3](http://java-gnome.sourceforge.net/cgi-bin/bin/view/Main/NewsTwoFourteenThree)  
[java-gnome 2.16.0](http://java-gnome.sourceforge.net/cgi-bin/bin/view/Main/NewsTwoSixteenZero)  
[java-gnome 2.16.1](http://java-gnome.sourceforge.net/cgi-bin/bin/view/Main/NewsTwoSixteenOne)  


Considering using .defs format
------------------------------

It turns out that there _is_ a meta data representation of GTK. Well sort
of.

For a while now, several other language bindings have used something
called defs files to help them know what code they need to generate, and
how.

There is _not_, however, one canonical version of this information.  Rather,
the individual projects run a defs file generator (`h2defs.py`, etc) over
their own copy of the header files from each C library, then tinker with the
results a little (or a lot) and then run their own code generator over THAT.

As you all know, we've had some good chats with the python crowd, and and
having looked over their `.defs` files (and compared to gtkmm's, etc) I get
the sense that we will probably be best off to import their `.defs` files and
use those [on the premise that someday we might be able to abstract the .defs
information OUT of pyGTK and into some other module on their own.
Unfortunately, it's not a 100% drop in - there are PyThis and PyThat here and
there in their `.defs` files. {sigh}.

Anyway, discussions about defs files goes 'way back: this message from
2000 (midway through the discussion thread where the design was
originally done) more or less documents the file format:  
<http://mail.gnome.org/archives/gtk-devel-list/2000-January/msg00086.html>  
(reading the whole thread gives a sense of the complexity of the
problem, but you can skip it)

There was also discussion of cross project use of .defs files. See this
thread from 2004,  
<http://mail.gnome.org/archives/language-bindings/2004-March/msg00089.html>  
but its unclear whether anything actually came of it.

Finally, I recommend you check out a copy of pyGTK and nose around the
`gtk/*.defs` files and the `codegen/*.py` files. Failing a checkout, at least
look at one of these files
[online](http://cvs.gnome.org/viewcvs/pygtk/gtk/gtk-base.defs?view=markup).


Optional reading: Software version control
------------------------------------------

GNOME is *still* using CVS, which is rather surprising. They are about to do a
migration to Subversion (tried once, and aborted, which is a surprise seeing
as how most other projects managed to get off of CVS quite a while ago).

None of this impresses me terribly much; while Subversion is indeed a
better CVS, it's still of the same mold and still reliant on a
centralized server. Its branching and merging capability is rather
simplistic.And more to the point, distributed version control has been
with us for quite a while now.

Of course, it's difficult to know which Distributed Revision Control
System might be best to choose. Third generation systems of current note
are

* Bazaar[-NG] (`bzr`) an evolution from Bazaar (`baz`) evolving from
  (`tla`) which evolved from (`arch`). Current work being done by the likes of
  Martin Pool, Robert Collins, Aaron Bentley and other really smart people I
  respect. Written in Python

* Git (`git`), the version control system written by Linux Torvalds
  after their licence to use the proprietary bitkeeper software the Linux
  kernel was using was revoked out from underneath them. Written in C.

* Mercurial (`hg`), also written in Python, started about the same week as
  Git was, and for the same reason.

I'm going to blog my views on the subject one of these days, but for now
I encourage you to read up on them.

In particular, I recommend the following two threads, both from Carl Worth,
the leader of the Cairo project:  
<http://lists.freedesktop.org/archives/cairo/2006-February/006255.html>  
(just read his message unless you want to hear arguments about why Subversion
isn't up to it)

<http://lists.freedesktop.org/archives/cairo/2006-February/006335.html>  
(read the thread -- interesting insights into things)

FreeDesktop as a whole is rapidly moving to git; in particular X.org has now
migrated as well.

Why do I bring this up?

1. There won't likely be very much direct code reuse from the existing
java-gnome bindings so whether and when GNOME CVS migrates to Subversion is
less of a concern at this point;

2. I'm not super concerned about code history in any event;

3. the way that distributed systems dramatically drop the barrier to
contribution is **amazing**, and can (depending on how you use them) better
reflect the way that open source projects actually work.

Counterpoint: Jeff Waugh noted to me the other day that Bazaar-NG has a
read-from-subversion feature, something like

        bzr checkout http://svn.blah.org/path/to/repo

which I thought rather a good idea. (Jeff contends that this is why switching
to Subversion is "ok"). Of course, it didn't take me long to realize that git
has the same feature, care of `git-svn`. Oh, and the Bazaar feature relies on
an unreleased version of Subversion.

All this to say: we will not be using a 1st generation revision control tool
for the new java-gnome bindings. Which to use? Good question. We'll decide
over the next couple months. Despite my evident enthusiasm for Git, I suspect
we will settle on Bazaar. It is more likely to work on more platforms, the
developers actually write unit tests around their tool (imagine), but most
importantly, it's easier to use -- and given that we are serious about
inviting new audiences to the GNOME world, I think that's pretty important.

_Andrew Overholt and others commented that IDE integration with the VCS tool
is important. This is true, but the benefits of going with a modern 3rd
generation DVCS system outweigh the team tools as currently in Eclipse and the
others. They will catch up soon enough._


Optional reading: GNOME politics
--------------------------------

I'm hesitant to introduce this topic, but as members of the GNOME community,
it's important to understand the context of things. Anyone wanting to someday
be a future maintainer of the GNOME Java bindings had probably better be
current on all this.

In essence, there was great concern expressed recently over the proposed
inclusion of a GTK# based app in the GNOME Desktop. That in turn led to the
question of whether or not Mono should be a language allowed for desktop apps.

I do not take this as an overly significant issue, since distros will ship
whatever they're going to ship and it matters to me somewhat less what is
"official GNOME release" and what isn't. It is nevertheless a massively
contentious issue within GNOME and so here you go with the latest instalment
of the flame fest.

Jeff's attempt to restart the discussion [a good summary email which
leads a second monster debate]:
<http://mail.gnome.org/archives/desktop-devel-list/2006-July/msg00516.html>

The mail from the release manager which quickly mutated out of control
into an enormous discussion:
<http://mail.gnome.org/archives/desktop-devel-list/2006-July/msg00076.html>

To a lesser extent, this discussion about the role of GNOME is
interesting:
<http://mail.gnome.org/archives/desktop-devel-list/2006-July/msg00240.html>

Observations:

1. It's quite obvious that people have many preconceived notions that they are
not in a rush to let facts or objective benchmarking get in the way of.  The
fact there is little or no concrete objective benchmarking available doesn't
help.

2. Several of the corporate players in the the GNOME community have expressed
that they will not be shipping Mono apps (and are shipping Java ones).

3. This entire discussion has absolutely nothing to do with you being
"allowed" to write java-gnome apps. It would be relevant if you ever want your
app will be a part of *core* GNOME. Of course, the definition of *THAT* is a
bit vague, as you will see if you delve into this monster.

4. Java is already a blessed part of the language binding suite, for whatever
that's worth.

AfC  

_Originally written as an email to java-gnome-hackers by Andrew Cowie on 12
Aug 06; last modified 4 Dec 06._

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
