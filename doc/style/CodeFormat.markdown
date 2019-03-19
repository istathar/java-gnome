Style guide: Source Code
========================

Code Formatting
===============

The code formatting tools available in modern IDEs are nothing short of
miraculous.

Eclipse in particular has an outstanding code formatter that is highly
configurable. This encourages people to tinker and have their code be just the
way they like it. That's a Good Thing (tm), but it plays havoc when it comes
time to create diffs as patches to commit to the version control system. So we
need to share a code format convention.

Java conventions, mostly
------------------------

We ended up adopting what Eclipse termed the "Java conventions [builtin]",
with four modifications. Notably, this default Java style is set to convert
tabs to 4 spaces. I personally prefer `\t` characters, but it makes a mess
when looking at diffs or raw files because the terminal expands tabs to 8. So
I'll buy this one -- better that your code looks correct at all times.

The modifications are:

 * The opening brace on a Class statement is on its own line, not on the same
   line as the declaration. This is largely to provide some visual
   distinctness to the nested interfaces that contain our signal handler
   callbacks. Otherwise, they look too much like methods and it's very
   confusing to read.

 * The code width is 105 characters. The default of 80 causes so much wrapping
   to render it unreadable - especially in things like the Constant Object
   declarations in Enums and Flags.

 * Comment with is 78 characters. The default of 80 would be fine, except that
   when you view the changes to a file with the version control tool's diff
   command, the `+` and `-` character push the line out past the to the 80
   character mark, and that's no good in the standard 80 character wide
   terminal where you ran the command in the first place.

 * empty methods have both braces on the same line. We've got quite a number
   of private constructors which are empty and just serve to prevent the class
   showing up as an option when doing `new G`**<COMPLETE>**. An empty method
   with a blank line in it appears to the eye to be unfinished, and so we want
   to avoid that distraction.


Your patches have a much better chance of being accepted if they produce clean
diffs, and that's more likely to happen if you stick to these rules. If you're
using Eclipse, you will find "**The java-gnome Style**" pre-configured in the
.settings/ directory. Just hitting Format should do the trick.

(Come on Sun boys and girls! Make me believe that NetBeans is better...)

Coding standards
================

TODO: coding practises. Not really too much to talk about here.

AfC

_Originally written by Andrew Cowie 27 Nov 06. Last modified 9 Dec 06._

<!--
 
  Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd and Others

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
