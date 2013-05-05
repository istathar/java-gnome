We'd love to have you contribute by hacking on the bindings themselves!

GETTING STARTED
===============

1. Checkout the source code
---------------------------

If you're going to hack seriously with, or on, java-gnome, we recommend the
following sequence to checkout the source code:

    $ cd ~/src/
    $ mkdir java-gnome/
    $ git clone git://github.com/afcowie/java-gnome.git working
    $ cd working/
    $ less README.markdown

If you are using Eclipse, you may instead want to do this such that the
checkout is under `~/workspace`.

    $ cd ~/workspace/
    $ git clone git://github.com/afcowie/java-gnome.git java-gnome
    $ cd ~/workspace/java-gnome/
    $ less README.markdown

Doing it this way allows you to later do 

    $ git checkout some-other-branch

to change between branches you have created with different lines of
development. This works well, but is an advanced layout, so do experiement
with it a bit if you aren't well versed in distributed version control
practises.

### And build!

If you haven't already read them, see the instructions in the top level
[`README`](README.html) for how to install from a source tarball and what
options you can supply to the `./configure` script.


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

_Last modified 22 Feb 13_

<!--
 
  Copyright © 2006-2013 Operational Dynamics Consulting, Pty Ltd and Others

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
