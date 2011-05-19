Contributing to java-gnome

We'd love to have you contribute by hacking on the bindings themselves!

GETTING STARTED
===============

1. Checkout the source code
---------------------------

We are using Bazaar to manage our source code. More commonly known as `bzr`,
it is an advanced third-generation Distributed Version [or Revision] Control
System. It's a good idea to have a recent version (ie `bzr >= 2.3`).

If you're going to hack seriously with, or on, java-gnome, we recommend the
following sequence to checkout the source code:

<a class="nav-white"
href="bzr://research.operationaldynamics.com/bzr/java-gnome/mainline/">

    $ cd ~/src/
    $ bzr init-repository java-gnome/
    $ cd java-gnome/
    $ bzr checkout bzr://research.operationaldynamics.com/bzr/java-gnome/mainline/ mainline/
    $ bzr branch mainline/ working/
    $ cd working/
    $ less README

</a> 

This will result in a local copy of '`mainline`' which you can use to track
upstream and to diff against, and '`working`' as new branch for you to play
with. `:)` The branch will be at (in this example)
`~/src/java-gnome/working/`. All branches under `~/src/java-gnome/` will share
storage of revisions, so you won't pay any penalty for creating as many
branches as you like.

If you are using Eclipse, we recommend creating a branch in your Workspace and
using that to work on. This will allow you to relatively easily change
branches while not screwing up all your launchers.

    $ cd ~/workspace/
    $ bzr checkout ~/src/java-gnome/working java-gnome
    $ cd ~/workspace/java-gnome/
    $ less README

Doing it this way allows you to later do 

    $ bzr switch some-other-branch

to change between branches you have created with different lines of
development. This works well, but is an advanced layout, so do experiement
with it a bit if you aren't well versed in distributed version control
practises.

### And build!

If you haven't already read them, see the instructions in the top level
[`README`](README.html) for how to install from a source tarball and what
options you can supply to the `./configure` script.

### Why `bzr`?

We chose Bazaar (Bazaar-NG, as it was briefly known) as the VCS for the Java
bindings for GNOME because of its relative straight-forwardness and because of
our faith in the ethos and extreme competence of its developers.  Anyone
experienced with the old world 1st generation centralized VCS tools like CVS
or Subversion will be able to make sense of it and you can learn from there.
Bazaar is constantly improving in performance terms, has a vibrant developer
community, is widely portable. Most of all, the fact that the people hacking
on `bzr` itself actually follow test-driven-development practises (their unit
test suite has over 15,000 tests!) keeps them honest and conclusively biases
in their favour.

In production use for the last three years, we have found Bazaar to be reliable,
amazingly easy for newcomers to the Open Source world to learn, and a
significant element in our effort to reduce barriers to entry.

We reiterate that you must use a recent version of Bazaar. There is nothing
intrinsically better about older software; bug fixes and performance
improvements are not back-ported by the people who write the code; they
release a new version instead. If your operating system does not provide `>=
2.0` then you would do well to install `bzr` manually.

2. Do your own thing!
---------------------

The amazing thing about the decentralized VCS tools is that you do not need to
be online to make commits. But it's more than that -- you don't need to be me
to make commits! You can work away on your own branch(es) and then send your
patches _in already committed form_ to me for consideration and merging.

Make sure to identify yourself first:

    $ bzr whoami 'George Jones <grjones@example.com>'

_before_ committing the first time -- see [setting your user ID](doc/style/CommitMessages.html#id).


### A day in the life of a java-gnome hacker

As you are working, you can see what you have done with:

    $ bzr status
    $ bzr diff

then, when you are happy with a piece of work, commit:

    $ bzr commit

You can do this one or more times.

There is a vitally important point, however: no one can tell you you can't
work on something. And of you want to share it, go right ahead. That said, if you'd like to see your work merged into the mainline and available publicly in the official releases, then we'd encourage you to follow the stylistic guidelines discussed out below.

### Staying up to date

Meanwhile, you'll want to catch-up with what's been going on upstream. So
update the checkout you have of mainline with the following:

    $ cd ../mainline
    $ bzr update

Then you need to merge in the upstream changes into your branch:

    $ cd ../working
    $ bzr merge

Merging only brings the changes to your working directory; it doesn't _commit_
them to your working branch. So, assuming you want to accept them, you need to
do a commit of those changes. So, Look at the changes that were pulled in
with:

    $ bzr status
    $ bzr diff

then commit:

    $ bzr commit

but make sure you only commit the stuff you've merged in, not unfinished
changes you've might have locally! In other words, best to do the merge after
you've finished anything you might happen to be doing in that branch. 

### To send a patch

If you've done some work on your local branch and you'd like to contribute it,
go ahead and commit it locally in your working branch, then try the following:

    $ bzr bundle ../mainline > /tmp/name-that-tune.patch

This will output a human readable diff along with the actual revisions in a
safely encoded form into the file you specify. Just attach that file to an
email and send it along. `.patch` is a good suffix because most mail clients
know to set the MIME type of such an attachment properly.


TECHNICAL DETAILS
=================

The java-gnome bindings are pretty straight forward, so in terms of adding
new coverage you can probably pick things up by example fairly easily. But you
really should take the time to understand *why* things work, or you won't be
able to understand the mechanisms involved behind the scene.

API design and overall system architecture 
------------------------------------------

In the `doc/design/` subdirectory you will find extensive documentation of the
current re-engineering process that lead to the design and architecture of the
java-gnome bindings. Try starting with
[`doc/design/START`](doc/design/START.html).

You really are encouraged to peruse these files; the one on Architecture in
particular is pretty much required reading if you want to understand what's
going on.

STYLE GUIDE
===========

Things like how you format your code, what editor you use, etc are all very
personal issues, but for a project to function with anything even remotely
resembling sanity, there need to be some standards and conventions. Thus we
have several documents outlining the style guidelines you'll need to know if
hacking on the java-gnome 4.x bindings. They're in the `doc/style/` directory.

Code formatting
---------------

I've carefully documented the 4 divergences from the otherwise default "Java
conventions" for Java source code formatting. Please take a moment to read
[`doc/style/CodeFormat`](doc/style/CodeFormat.html); your patches have a much
better chance of being accepted if they produce clean diffs, and that's more
likely to happen if you stick to these rules.

Commit Messages
---------------

There are just a few minor conventions you should be aware of when committing
patches. See [`doc/style/CommitMessages`](doc/style/CommitMessages.html).

Documentation
-------------

JavaDoc is the heart and soul of our API documentation and good JavaDoc is
going to be *the* key defining criteria for our bindings being **approachable**
to new developers. As such we've written out considerable guidance about how
to best go about documenting our public APIs. 

Of course, JavaDoc alone isn't sufficient, and we have a number of supporting
documents describing the design and architecture of java-gnome.  See
[`doc/style/Documentation`](doc/style/Documentation.html) for discussion of
appropriate style for both in-code JavaDoc and supporting textual Design
documentation.

Markup
------

We have gone to considerable trouble to ensure our text documentation files are
readable from a terminal window, but by using a simple yet powerful markup
syntax called Markdown we can also easily render these files to useful web
pages. See [`doc/style/MARKUP`](doc/style/MARKUP.html).


CONCLUSION
==========

The opportunity to work on something you love is the greatest experience you
can have. If you or your company chooses to offer code you write, be it
towards helping us on this project or on any other endeavour whatsoever, I
encourage you to choose to let others use your work as Software Libre and in
your own turn help people find their own passion and excitement. By your
actions you lift us all up. I hope you'll join us!

Apotheosis Rising.

AfC

`--`  
Andrew Frederick Cowie  
Managing Director,  
[Operational Dynamics](http://www.operationaldynamics.com/),  
a Change Management consultancy...

Maintainer,  
[java-gnome](http://java-gnome.sourceforge.net/),  
opening GTK and GNOME to Java programmers!

_Last modified 22 Mar 11_

<!--
 
  Copyright © 2006-2011 Operational Dynamics Consulting, Pty Ltd and Others

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

  vim: set textwidth=78:

-->
