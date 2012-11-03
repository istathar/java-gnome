LICENCE

This is the java-gnome user interface library allowing you to write GTK and
GNOME programs from Java!

    Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd and Others

See [`AUTHORS`](AUTHORS.html) file for contributors.

1) java-gnome source code and documentation
-------------------------------------------

java-gnome is Logiciel Libre and is Open Source; you can redistribute it
and/or modify it under the terms of the GNU General Public License, version 2
("GPL").

Linking this library statically or dynamically with other modules is making a
combined work based on this library. Thus, the terms and conditions of the GPL
cover the whole combination.

As a special exception, the copyright holders of this library give you
permission to link this library with independent modules to produce an
executable, regardless of the license terms of these independent modules, and
to copy and distribute the resulting executable under terms of your choice,
provided that you also meet, for each linked independent module, the terms and
conditions of the license of that module. An independent module is a module
which is not derived from or based on this library. If you modify this
library, you may extend this exception to your version of the library, but you
are not obligated to do so. If you do not wish to do so, delete this exception
statement from your version.

_In other words, if you change java-gnome itself, you can still link against
it, but you must publish the changes you made to this library._

--

java-gnome is distributed in the hope that it will be useful, but WITHOUT ANY
WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
A PARTICULAR PURPOSE. See the GPL for more details. In particular, note that
if you use this and lose your data, you're on your own.

Of course, if you find a bug, please let us know. And, if you fix anything or
improve the code please contribute your changes back -- that's the whole idea
of Open Source. See [`BUGS`](/4.0/lists/#bugs) and [`HACKING`](HACKING.html)
files for details.

You should have a copy of the GPL somewhere on your system. If you don't, see
[`http://www.gnu.org/licenses/`][GPL] or write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA

2) prerequisites and dependencies
---------------------------------

java-gnome depends on a number of external libraries. They are either provided
by your operating system distribution or, in some cases, may be shipped
alongside the java-gnome sources in the `lib/` subdirectory. That code is open
source (or we couldn't have redistributed it), but note that third party
sources are made available under the terms specified by their authors and as
documented in the individual licences within those files, as noted below and
in `lib/README`.

The most notable dependency, of course, is GNOME itself. You will need to have
a recent version of GLib, Cairo, GTK, and many of the other GNOME libraries
installed in order to build java-gnome and use it in your programs. Just in
case you are wondering, this is a feature, not a bug.

3) use of Java
--------------

The public API of the java-gnome library is expressed in the Java programming
language with the glue code to reach the native libraries it wraps written
jointly in Java and C.

You have the option of building and running java-gnome either using the
original Proprietary compilers and runtime environments are available from Sun
and others, or using the Libre and Open Java implementation available through
one of the Free runtimes that use the GNU CLASSPATH (aka glibj) project. We
welcome you to deploy java-gnome using either approach. Software Freedom,
after all, is about choice.

Our build framework, Equivalence, allows you to easily switch between
proprietary and Free compilers and runtimes, allowing you to evaluate
different options.

Of course, with Sun having recently released their Java VM under the GPL, and
having announced that the full JDK and class libraries will likewise be
released as Free Software, you are most certainly encouraged to make use of
that code as it becomes available. Java Libre!

4) trademarks and acknowledgements
----------------------------------

Java is a trademark of Sun Microsystems, Inc.
GNOME is a trademark of the GNOME Foundation.

[GPL]: http://www.gnu.org/licenses/

<!-- vim: set textwidth=78 nowrap: -->
