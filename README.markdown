[![No Maintenance Intended](https://img.shields.io/maintenance/no/2013)](http://unmaintained.tech/)
[![License](https://img.shields.io/badge/license-GPL--2.0%20%2B%20Linking%20Exception-blue)](/LICENCE.markdown)

This is the java-gnome language bindings project. We endeavour to provide a
high quality library you can use to write GTK and GNOME programs. The
underlying APIs are elegantly transformed into Java and carefully documented so
that anyone new to Linux or Open Source can rapidly be on their way to creating
fabulous applications.

<!--
If you didn't know, the project website is:  

	http://java-gnome.sourceforge.net

where we host the API documentation, web versions of all the design files, and
tutorials to help get people started. Contact information, release notes and
and project news are also to be found there.
-->

This README file is devoted to helping you get started building the bindings
themselves. 

Building java-gnome
===================

For the impatient:

    $ tar xJf java-gnome-4.1.3.tar.xz
    $ cd java-gnome-4.1.3
    $ ./configure
    $ make

But there's a bunch of stuff you probably want to know, so read on!


1. Get the source code
----------------------

### From a release tarball ###

You can download the latest java-gnome release from the GNOME FTP server at:  
[`http://ftp.gnome.org/pub/gnome/sources/java-gnome/4.1/`][mirror]

Once you've downloaded the latest source tarball:

    $ tar xjf java-gnome-4.1.3.tzr.xz
    $ cd java-gnome-4.1.3

And go on to step 2 for details about options you can pass to the
configuration command.


### Or checkout the source ###

If you want a newer version of the code than the tarball you might have, you
can always check it out over the net. We use Git, an advanced
third-generation Distributed Version [or Revision] Control System, to manage
our source code. 

Getting a checkout is easy:

    $ cd ~/src/
    $ git clone git://github.com/afcowie/java-gnome.git
    $ cd java-gnome/
    $ less README.markdown

2. Run `./configure`
--------------------

The top level directory contains a custom `./configure` which detects your
Operating System variant, sets defaults accordingly, verifies the location of
prerequisites (the various `.jar` files), and finally chooses a Java bytecode
compiler and Java Virtual Machine runtime environment. The configuration
output is a Makefile fragment which is written to `.config` and subsequently
included by the top level `Makefile`.

So run it already:

    $ ./configure

### Background ###

The steps necessary to configure and build a Java project are quite different
than those needed to construct a program written in a more traditional
language. Unlike C, for example, there is no need to do substitution across
the codebase nor to worry about conditional compilation; `#ifdef` is not
something we do in Java. This is in no small part because the Java class
libraries and the language itself have been remarkably stable. To build and
run a Java program, however, three things are necessary:

 * locate pre-requisite libraries (`.jar`s), and form a `CLASSPATH`;

 * locate, validate, and select a Java compiler; and

 * locate, validate, and select a Java runtime.

That's it! From there, often a single compiler invocation will take care of
building an entire program, but these preconditions must be satisfied before
compiling is possible. _(Incidentally, tools like Ant are no help with any of
this -- it just takes care of the build part; and don't even think about
suggesting the GNU autotools -- they are a complex, arcane, and bloated
nightmare that don't address with the Java specific challenges at all)_.

At the moment, we use Andrew Cowie's "Equivalence" build system, which is
composed of a straight-forward (if somewhat overweight) Perl program along
with a simple Makefile which together carry out the task of configuring and
building the library. Right now, Gentoo Linux, Debian Linux, Fedora Core
Linux, and Solaris Unix should be detected properly and result in working
configurations. If you are running a different operating system or
distribution, please contact us and we'll add it -- it's just a matter of
identifying the location of a few things. Better yet, look in the `configure`
Perl script -- the places where OS is switched are obvious, and just add what
you need to add, and send us a patch.

Meanwhile...

### Customizing build options ###

You can override the choices `configure` makes by listing parameters on the
command line, like this:

    $ ./configure compiler=ecj runtime=jamvm

This facilitates easily switching between runtimes and compilers for testing.
At the moment, the available selections are:

* `compiler` `-->` javac, ecj

* `runtime`  `-->` java, cacao, jamvm, cacao, gij, and kaffe

The whole point of the Equivalence's `configure` script is to figure things
out for you, but if it can't quite figure out where Java is, you can override
it by specifying an alternate location to find a JDK using either
of the following:

* `jdk` -- where to find a traditional Java Development Kit, ie `JAVA_HOME`

* `jamvm` -- path to the JamVM executable

* `cacao` -- path to the CACAO executable

Examples:

    $ ./configure
    $ ./configure jdk=/opt/sun-jdk
    $ ./configure jamvm=/home/joe/custom/bin/jamvm runtime=jamvm

Your configuration is persistent across builds in that checkout, ie, `make
clean` won't force you to reconfigure (though `make distclean` will). The
`configure` script runs very quickly, so it's no big deal to switch settings
by re-running it.

### Dependencies ###

The java-gnome library depends on the GNOME desktop and is intended for people
wishing to do tight integration with it. In particular, this version of
java-gnome depends on:

* GLib `>= 2.28.0`

* GTK `>= 3.0.4`

* Cairo `>= 1.10.0`

* Pango `>= 1.28.0`

* gtksourceview `>= 2.91.9`

* libnotify `>= 0.7.0`

* gtkspell `>= 3.0`  
_This isn't available yet, and GtkSpell support is currently disabled._

* libunique `>= 3.0`

* Enchant `>= 1.4.2`

* librsvg `>= 2.32.0`

3. Build
--------

Once you've configured, compiling java-gnome is as simple as running Make:

    $ make

If you're having trouble with something as Make runs and need to debug it, you
can try:

    $ V=1 make

This will show you the actual commands being executed (ie, Make's normal
behaviour, which we override for appearances sake and because otherwise the
signal to noise ratio is terrible and you never see warnings). If you're still
stumped, you might try having a look at `.config`, which is where all the Make
variables come from.

The build products end up in `tmp/`:

`tmp/gtk-4.1.jar`  
`tmp/libgtkjni-4.1.3.so`

That's actually enough to go on -- if you're using an IDE like Eclipse you can
just tell it about the `.jar` and then jump right to "Using the Bindings". Or
you can install java-gnome somewhere. Doesn't matter, really.

<a name="install"></a>

4. Install
----------

java-gnome 4.1 has the standard `make install` behaviour, and the equally
standard `prefix` option to `./configure`.

### Installing locally

Someone installing it locally (to your home directory, say) might do:

	$ ./configure prefix=/home/bloggins
	$ make install

and you would end up with:

`~/share/java/gtk-4.1.jar`  
`~/share/java/gtk.jar`  
`~/lib/libgtkjni-4.1.3.so`

The default is to send it off to `/usr/local` as you'd expect. 

Using `make install` is **compulsory** if you intend to use java-gnome from
anywhere other than "in-place" from the temporary location where it was built.

### Installing to system (for people packaging the library for their distro)

The `install` target understands the `DESTDIR` variable used by packagers to
install to a specified prefix _within_ a temporary directory. Someone writing
an `.ebuild` to create a package for java-gnome on a Gentoo system would
probably end up seeing the following commands being run by Portage, for
example:

	...
	
	./configure prefix=/usr
	make
	
	...
	
	make DESTDIR=/var/tmp/portage/java-gnome-4.1.3-r2/image install
	
	...

With a prefix of `/usr` you will end up with:

`/usr/share/java/gtk-4.1.jar`  
`/usr/share/java/gtk.jar`  
`/usr/lib/libgtkjni-4.1.3.so`

If you have distro policy issues to deal with, then pass `jardir` and/or
`libdir` overrides to `configure`.

<a name="using"></a>

Using the bindings
==================

### Running the "demo"

There are a few _tiny_ and _trivial_ example programs in the `doc/examples/`
directory of the bindings. If you would like to see one, you could compile and
run it by hand, doing something like:

    $ javac -classpath tmp/gtk-4.1.jar -d tmp/tests doc/examples/button/ExamplePressMe.java
    $ java -classpath tmp/gtk-4.1.jar:tmp/tests button.ExamplePressMe

This shows you how you can reference and use the library after it is built by
`make` into `tmp/`.

Of course, that was _way_ too much typing. Instead, just do this:

    $ make demo

`:)`. As usual, use `V=1` to see what it is actually doing.

### Running your own programs

java-gnome has a native component that links tightly against various GNOME
libraries (after all, the whole point is to use the real GTK, not some pseudo
look alike pathetic attempt of a widget toolkit), but we take care of locating
it and loading it for you. So all you need to do to run an application is:

    $ java -client -ea                                   \
        -classpath /home/bloggins/share/java/gtk-4.1.jar \
        com.example.ComeOnBabyLightMyFire

Oh, the joys of running Java programs by hand. 


Status
======

java-gnome is now a solid foundation that has been used to develop non-trivial
applications. The architecture and internal design has been well proved, and
the coverage level (relative to the full breadth of the underlying libraries)
is reaching maturity.

The library has been unmaintained for some years, however. If anyone in the
GNOME community wishes to adopt the project we will happily transfer the
repository to you.

Happy coding!

AfC

`--`  
Andrew Frederick Cowie  

_Last modified 22 Feb 13_

[mirror]: http://ftp.gnome.org/pub/gnome/sources/java-gnome/4.1/

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
