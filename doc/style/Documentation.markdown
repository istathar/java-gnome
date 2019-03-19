Style guide: Documentation
==========================

Text files
==========

Documentation such as design documents and files like this one are almost
always viewed in terminals using pagers like `less`. It does no one any good
if the paragraphs run off into the netherworld off the right of the screen
somewhere.

Therefore, all textual documentation should be word wrapped at 78 characters,
the same as for JavaDoc comments. If you look at the bottom of this file,
you'll see a vim modeline which will takes care of it for this file.

The exception, of course, is source code fragments. If they run on past the 78
character mark, then so be it it.

JavaDoc
=======

Don't bother putting class names in `<code>` spans. You'd never get anything
done.

There's no reason to `@link` ever single class name either. JavaDoc is
already full of cross references. Only put a `@link` tag in when you really
think that someone should follow the link to the doc snippet you're pointing
at.

Getters and setters are a bit obvious. If the single sentence you write for
`getLabel()` is "Get the text of the Label on the Button" and the method
returns String, then there's not much need to put a `@returns` clause in. If
unusual things happen in parameters or return values (for example `null` is an
acceptable argument, or a return of `-1` indicates success) then definitely
explain it in a `@param` or `@return` clause!

Please take the trouble to write out a bit of supplementary text for a link so
that you positively control what text appears. Otherwise you can end up with
fully qualified class names and all sorts of other ugliness which distract
from the flow of the paragraph. For example, if you just do:

    /**
     * For more details, see {@link org.gnome.gtk.Button#setLabel(java.lang.String)}.
     */

You end up with "For more details, see
<u>org.gnome.gtk.Button.setLabel(java.gnome.String)</u>." Bleh! So try adding
a bit of extra text to make it more natural:

    /**
     * For more details, see Button's {@link org.gnome.gtk.Button#setLabel(java.lang.String) setLabel()}.
     */

This will render as "For more details, see Button's <u>setLabel()</u>." Much
better.

As a matter of consistency, if you are referring to a static method, say:

    Gtk.init()
    
but to talk about an instance method, use the possessive, eg:

    Button's setLabel()

Obviously the whole point of java-gnome is to wrap GTK and the other GNOME
libraries. Unfortunately, however the doxygen documentation for GLib, GTK and
friends, while extensive, is _quite_ inappropriate for someone brand-new to
GTK or {Linux,Solaris}. Therefore, in order to achieve the criterion of
**Approachability** that we defined as our primary goal, considerable
massaging and indeed complete rewriting is necessary. Use a friendly, casual,
and colloquial style... and don't forget an example snippet! Methods like
Widget's [`show()`][show], the entire [Button][button] class, and the signal
[`Window.DeleteEvent`][delete] are all good examples.

[show]: /4.0/doc/api/org/gnome/gtk/Widget.html#show()
[button]: /4.0/doc/api/org/gnome/gtk/Button.html
[delete]: /4.0/doc/api/org/gnome/gtk/Window.DeleteEvent.html

Finally, where it is appropriate to discuss how our implementation varies from
conventional GTK practise, use a paragraph all in _italics_ at the end of the
class's JavaDoc to explain the divergence or how java-gnome is implemented.
The comment at the top of org.gnome.glib.Object is a pretty good example of
this. But other than these rare observations, don't refer to the translation,
native, JNI layers or the native library [call] itself at all! Whenever
possible, we want the implementation details of java-gnome to be invisible.
They're not `public` after all!

Spelling
========

And for super bonus points, run a spellchecker over any documentation you
contribute. Yes, on code too!

We use `LANG=en_CA` around here, but **don't worry if your written English
isn't very strong** or if you speak American. What matters is that you're
contributing and a native English speaker like myself will be more than happy
to fix it up.

An aspell word list is included at the top of a checkout in the file
`.aspell.en.pws` containing all the custom usages from both documentation
_and_ Java source code. To use it to spell check `Button.java`, for example,
try:

    $ cd src/java/org/gnome/gtk
    $ aspell -x -p `bzr root`/.aspell.en.pws -c Button.java

_Originally written by Andrew Cowie 2 Dec 06. Last modified 2 Jan 09._

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
