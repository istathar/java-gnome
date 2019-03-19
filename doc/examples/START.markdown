EXAMPLES
========

These files are examples of specific parts of the java-gnome API worked up as
complete Java programs.

Source code, especially Java source code, doesn't always lend itself to being
_read_, certainly not from top to bottom. We have attempted, however, to create
some examples which are non-trivial but nonetheless are still readable.

----
<img src="button/ExamplePressMe.png" class="right"/>
### Button in a Window

* [`ExamplePressMe.java`](button/ExamplePressMe.html)  
It doesn't get any simpler than this. A [Label][] and a [Button][] in a
[Window][], showing how to connect a listener to handle a
[`Button.Clicked`][Button.Clicked] signal from the Button and how to ensure
your application terminates when the Window is [deleted][Window.DeleteEvent].
Incidentally, this is what runs if you type `make demo` in a fresh java-gnome
build.

[Button]: /doc/api/4.1/org/gnome/gtk/Button.html
[Label]: /doc/api/4.1/org/gnome/gtk/Label.html
[Window]: /doc/api/4.1/org/gnome/gtk/Window.html
[Button.Clicked]: /doc/api/4.1/org/gnome/gtk/Button.Clicked.html
[Window.DeleteEvent]: /doc/api/4.1/org/gnome/gtk/Window.DeleteEvent.html

----
<img src="keyboard/ExampleSnooping.png" class="right"/>
### Handling keystrokes

* [`ExampleSnooping.java`](keyboard/ExampleSnooping.html)  
Handling keyboard input is more complicated than it reasonably ought to be. In
most cases the existing GTK Widgets already do everything that needs doing
(when you type text into an Entry, the text does indeed go into that Entry),
focus handling, etc. However, if you need to intercept a keystroke so you can
do something special, then you need to work with the [Keyval][] and
[ModifierType][] classes in response to the
[`Widget.KeyPressEvent`][Widget.KeyPressEvent] signal. This is how.

[Keyval]: /doc/api/4.1/org/gnome/gdk/Keyval.html
[ModifierType]: /doc/api/4.1/org/gnome/gdk/ModifierType.html
[Widget.KeyPressEvent]: /doc/api/4.1/org/gnome/gtk/Widget.KeyPressEvent.html

<pre style="max-width:600px;">
Pressed: Keyval.ControlLeft, Modifier: ModifierType.NONE 
Pressed: Keyval.c, Modifier: ModifierType.CONTROL_MASK 
</pre>

----
<a name="ExampleTrailHeads"></a>
<img src="treeview/ExampleTrailHeads.png" class="right"/>
### TreeView and TreeModel

* [`ExampleTrailHeads.java`](treeview/ExampleTrailHeads.html)  
While not a complete tutorial, we have an example of a small application which
uses a TreeView backed by a ListStore called `ExampleTrailHeads`.  It is
heavily commented and meant to serve as an introduction of the incredibly
powerful yet very complex [TreeView][] / [TreeModel][] system. It should be
read in conjunction with the API documentation for these types. You'll also
want to read about how to setup a [ListStore][] and the explanation of
available column types in [DataColumn][].

[TreeView]: /doc/api/4.1/org/gnome/gtk/TreeView.html
[TreeModel]: /doc/api/4.1/org/gnome/gtk/TreeModel.html
[ListStore]: /doc/api/4.1/org/gnome/gtk/ListStore.html
[DataColumn]: /doc/api/4.1/org/gnome/gtk/DataColumn.html

----
<a name="ExampleInstantMessenger"></a>
<img src="textview/ExampleInstantMessenger.png" class="right"/>
### TextView and TextBuffer 

* [`ExampleInstantMessenger.java`](textview/ExampleInstantMessenger.html)  
This is a more involved sample illustrating use of the [TextView][] /
[TextBuffer][] multi-line text display and editing Widget. We set up a
conversation window like you'd see in an instant messaging program, and then
simulate a conversation with someone -- all as a means of demonstrating
setting up the view, doing text insertion into the buffer with [TextIter][] as
well as more advanced features like managing text formatting via [TextTag][]s.
The example is more or less readable from the top, but the program is quite
dynamic and the most fun will be had if you _run_ the thing.

[TextView]: /doc/api/4.1/org/gnome/gtk/TextView.html
[TextBuffer]: /doc/api/4.1/org/gnome/gtk/TextBuffer.html
[TextIter]: /doc/api/4.1/org/gnome/gtk/TextIter.html
[TextTag]: /doc/api/4.1/org/gnome/gtk/TextTag.html

----
<a name="ExampleDrawingInExposeEvent"></a>
<img src="cairo/ExampleCairoDrawingBlends.png" class="right"/>
### Drawing with Cairo 

* [`ExampleDrawingInExposeEvent.java`](cairo/ExampleDrawingInExposeEvent.html)  
FIXME _this needs a better name. For that matter, it needs something more
interesting._  
Using the Cairo Graphics library to draw within a GTK program means following
the idiom shown in this example. This program demonstrates drawing lines,
rectangles, and gradients using a drawing [Context][] as obtained within a
Widget's [Widget.ExposeEvent][] signal handler.

[Context]: /doc/api/4.1/org/freedesktop/cairo/Context.html
[Widget.ExposeEvent]: /doc/api/4.1/org/gnome/gtk/Widget.ExposeEvent.html

----
<a name="ExampleLinedPaper"></a>
<img src="cairo/ExampleLinedPaper.png" class="right"/>
### Creating PDFs with Cairo and drawing text with Pango

* [`ExampleLinedPaper.java`](cairo/ExampleLinedPaper.html)  
All text drawing in java-gnome is done with GNOME's powerful text rendering
library, Pango. This example uses the [Layout][] class to lay out the standard
_lorem ipsum_ typesetting text. To make it more interesting we have drawn
horizontal rules at the font baseline and a red line at the left margin to
create the effect of lined three-hole punch paper, all with Cairo of course.
<br><br>
In this case we have not used Cairo to draw a Widget -- we have created a
program that writes out a PDF file using Cairo's [PdfSurface][] and drawn our
text there instead!
<br><br>
Of course, no one has ever sat through Latin class without doodling on their
notepad...  to create the effect we use a transparent image as the source
pattern and paint it onto the surface.
<br><br>
_The resultant_ `.pdf` _is shown here as rendered by Evince, GNOME's excellent
PDF viewing program._
<br>
<span class="bottompanel" style="float:none;">
The tank sketch is used with the permission of the artist, Nathan Strum.
</span>

[PdfSurface]: /doc/api/4.1/org/freedesktop/cairo/PdfSurface.html
[Layout]: /doc/api/4.1/org/gnome/pango/Layout.html


<style>
img.right {
	float: right;
	padding-bottom: 10px;
}
hr {
	clear: right;
}
</style>

<!--
 
  Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd and Others

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
