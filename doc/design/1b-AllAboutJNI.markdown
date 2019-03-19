Re-engineering (1): The Java Native Interface 
=============================================

There's one other vitally important area of background information necessary
to grok; I should have included this in the Homework email. It's hard to
contemplate a Java binding of a C library without understanding the API used
to cross the boundary between Java code and C code: the Java Native Interface,
or JNI.

The JNI Book
------------

There's a [good book][jnibook] on the topic which at some point became
available in an HTML version. You can download it from the link below and I
recommend that you do. You'll need to make a couple symlinks to make the
navigation work, though.  
<http://java.sun.com/docs/books/jni/>  

[jnibook]: http://java.sun.com/docs/books/jni/html/jniTOC.html

The book does get a touch off the beaten track at some points. Without the
experience we already have I'm sure it would be easy to get lost down blind
alleys. You don't need to worry about the C code creating and running a VM,
for example. And their example of leveraging existing native libraries
(binding, to us) is chaotic.

Despite these points (which you can ignore), the rest of the book is
excellent. Certainly if you've done any work already with the existing
java-gnome libraries it'll be a welcome reference to help understand some of
the things we've been doing by rote. :)

In any case, there's one message that comes through loud and clear: while you
_can_ do manipulations equivalent to Java code on the C side, it takes MANY
more lines of code to do so and is WAY more error-prone; therefore, don't!

Obviously there are corner cases where we won't be able to avoid using the
power of JNI to cause things to happen on the Java side [raising Exceptions is
the thin edge of that particular wedge] but nevertheless it is increasingly
obvious to me that if we're careful about the choices we make we can end up
avoiding a huge amount of hassle. As Ismael said on #java-gnome last night: "I
prefer that approach more, where you do as most as you can in the Java side.
It's faster and there are better tools to debug with". Agreed.

The JNI Specification
---------------------

The spec itself is somewhat obscurely written. As far as I can tell,
everything you need is in the above book. I'd still recommend downloading a
copy of the spec for reference. Doing:

    $ wget -x -nc -r -I/j2se/1.4.2/docs/guide/jni/spec/ http://java.sun.com/j2se/1.4.2/docs/guide/jni/spec/jniTOC.html

will get you a copy which you can then move somewhere convenient.

Performance
-----------

People (myself included) always express concerns about the performance of code
which uses JNI. 

Someone in `#classpath` pointed out the following link:  
<http://java.sun.com/docs/books/performance/1st\_edition/html/JPNativeCode.fm.html#18062>

Reading any selected paragraph of the above reference or the JNI book would
quickly give you the idea that most of JNI is the kiss of death for
performance. Well, it turns out that java-gnome in its present 2.x form
already *does* things the worst case way:

* multiple crossings of the boundary per call to pass data
* passing objects for Handles
* using finalizers
* not caching class and method lookups (!)

And you know what? It's not slow! Java GTK apps are perfectly snappy. My
inference is that the underlying libraries we are calling are costly enough
(or, fast enough) that a few cycles for the overhead of JNI are not a
significant factor. So restricting ourselves based on some paranoia about this
or that way of doing things turns out to premature. (Take finalizers for
example -- so what if "It is typically slower to create and reclaim instances
of classes with finalizers than to create and reclaim those without
finalizers." -- in the present design we use them to good effect, and things
work just fine. If we need them in the re-engineered bindings, so be it)

Doesn't mean that we won't choose better algorithms if we can, but we can
first and foremost concentrate on getting the job done properly, and accept
small JNI overheads should a particular approach that we choose imply those
overheads.

AfC

_Originally written as an email to java-gnome-hackers by Andrew Cowie on 18
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
