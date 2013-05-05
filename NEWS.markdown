<a name="4.1.3" id="1367686931" title="Tooltips"></a>

java-gnome 4.1.3  (4 May 2013)
==============================

_Adventures in Tooltips ._

A third release in the 4.1 series, with improved coverage of GTK's tooltips
and styling functionality. Also includes numerous build fixes and maintenance
of the build infrastructure.

Thanks to Guillaume Mazoyer and Sarah Leibbrand for their contributions!

AfC

<a name="4.1.2" id="1346294738" title="GtkApplication"></a>

java-gnome 4.1.2  (30 Aug 2012)
===============================

_Applications don't stand idly by._

After a bit of a break, we're back with a second release in the 4.1 series
covering GNOME 3 and its libraries. 

Application for Unique
----------------------

The significant change in this release is the introduction of GtkApplication,
the new mechanism providing for unique instances of applications. This
replaces the use of libunique for this purpose, which GNOME has deprecated and
asked us to remove.

* **`org.gnome.gtk.Application`**
* **`org.gnome.glib.Application`**
* **`org.gnome.glib.ApplicationFlags`**
* **`org.gnome.glib.ApplicationCommandLine`**
* <strike>`org.gnome.unique.Application`</strike>
* <strike>`org.gnome.unique.Message`</strike>
* <strike>`org.gnome.unique.Response`</strike>

Thanks to Guillaume Mazoyer for having done the grunt work figuring out how
the underlying GApplication mechanism worked.

Idle time
---------

The new Application coverage doesn't work with java-gnome's multi-thread
safety because GTK itself is not going to be thread safe anymore. This is a
huge step backward, but has been coming for a while, and despite our intense
disappointment about it all, java-gnome will now be like every other GUI
toolkit out there: not thread safe.

If you're working from another thread and need to update your GTK widgets, you
must do so from within the main loop. To get there, you add an idle handler
which will get a callback from the main thread at some future point. We've
exposed that as `Glib.idleAdd()`; you put your call back in an instance of the
Handler interface.

* **`org.gnome.glib.Glib`**
* **`org.gnome.glib.Handler`**

As with signal handlers, you have to be careful to get back from your callback
as soon as possible; you're blocking the main loop while that code is running.

Miscellaneous improvements
--------------------------

Other than this, we've accumulated a number of fixes and improvements over the
past months. Improvements to radio buttons, coverage of GtkSwitch, fixes to
Assistant, preliminary treatment of StyleContext, and improvements to
SourceView, FileChooser, and more. Compliments to Guillaume Mazoyer, Georgios
Migdos, and Alexander Boström for their contributions.

* **`org.gnome.gtk.Switch`**
* **`org.gnome.gtk.FileChooserButton`**
* **`org.gnome.gtk.ProgressBar`**
* **`org.gnome.gtk.Style`**
* **`org.gnome.gtk.StyleContext`**
* **`org.gnome.gtk.StyleProperty`**
* **`org.gnome.gtk.StyleRegion`**
* **`org.gnome.gtk.JunctionSides`**
* **`org.gnome.gtk.RegionFlags`**

java-gnome builds correctly when using Java 7. The minimum supported version
of the runtime is Java 6. This release depends on GTK 3.4.

AfC

<a name="4.1.1" id="1310346568" title="GNOME 3"></a>

java-gnome 4.1.1  (11 Jul 2011)
===============================

_To bump or not to bump; that is the question_

This is the first release in the 4.1 series. This introduces coverage of the
GNOME 3 series of libraries, notably GTK 3. There was a fairly significant API
change from GTK 2.x to 3.x, and we've done our best to accommodate it.

Drawing with Cairo, which you were already doing
------------------------------------------------

The biggest change has to do with drawing; if you have a custom widget (ie, a
DrawingArea) then you have to put your Cairo drawing code in a handler for the
[`Widget.Draw`][Widget.Draw] signal rather than what used to be
`Widget.ExposeEvent`. Since java-gnome has ever only exposed drawing via
Cairo, this change will be transparent to most developers using the library.

Other significant changes include colours: instead of the former `Color` class
there's now RGBA; you use this in calls in the `override...()` family instead
of `modify...()` family; for example see Widget's
[`overrideColor()`][Widget.overrideColor()].

* **`org.gnome.gdk.RGBA`**
* **`org.gnome.gtk.StateFlags`**
* **`org.gnome.gtk.Widget`**
* **`org.gnome.gtk.Widget.Draw`**

Orientation is allowed now
--------------------------

Widgets that had abstract base classes and then concrete horizontal and
vertical subclasses can now all be instantiated directly with an Orientable
parameter. The most notable example is Box's [`<init>()`][Box.<init>()] (the
idea is to replace [VBox][] and [HBox][], which upstream is going to do away
with). Others are [Paned][], various `Range` subclasses such as
[Scrollbar][]. [Separator][], [Toolbar][], and [ProgressBar][] now implement
Orientable as well.

* **`org.gnome.gtk.Scrollbar`**
* **`org.gnome.gtk.Toolbar`**
* **`org.gnome.gtk.Box`**
* **`org.gnome.gtk.Paned`**
* **`org.gnome.gtk.ProgressBar`**
* **`org.gnome.gtk.Separator`**
* **`org.gnome.gtk.Toolbar`**

There's actually a new layout Container, however. Replacing Box and Table
is [Grid][]. Grid is optimized for GTK's new height-for-width geometry
management and should be used in preference to other Containers.

* **`org.gnome.gtk.Grid`**

The [ComboBox][] API was rearranged somewhat. The text-only type is now
[ComboBoxText][]; the former ComboBoxEntry is gone and replaced by a ComboBox
property. This is somewhat counter-intuitive since the behaviour of the Widget
is so dramatically different when in this mode (ie, it looks like a
ComboBoxEntry; funny, that).

* **`org.gnome.gtk.ComboBox`**
* **`org.gnome.gtk.ComboBoxText`**

Other improvements
------------------

It's been some months since our last release, and although most of the work
has focused on refactoring to present GTK 3, there have been numerous other
improvements. Cairo in particular has seen some refinement in the area of
Pattern and [Filter][] handling thanks to Will Temperley, and coverage of
additional [TextView][] and [TextTag][] properties, notably relating to
paragraph spacing and padding.

* **`org.freedesktop.cairo.Filter`**
* **`org.freedesktop.cairo.Pattern`**
* **`org.gnome.gtk.TextTag`**
* **`org.gnome.gtk.TextView`**

Thanks to Kenneth Prugh, Serkan Kaba, and Guillaume Mazoyer for their help
porting java-gnome to GNOME 3.

AfC

[HBox]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/gtk/HBox.html>
[VBox]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/gtk/VBox.html>
[Paned]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/gtk/Paned.html>
[Separator]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/gtk/Separator.html>
[Scrollbar]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/gtk/Scrollbar.html>
[ProgressBar]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/gtk/ProgressBar.html>
[ToolBar]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/gtk/ToolBar.html>
[Box.<init>()]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/gtk/Box.html#Box(org.gnome.gtk.Orientation,%20int)>
[Grid]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/gtk/Grid.html>
[ComboBox]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/gtk/ComboBox.html>
[ComboBoxText]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/gtk/ComboBoxText.html>
[Widget.Draw]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/gtk/Widget.Draw.html>
[Widget.overrideColor()]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/gtk/Widget.html#overrideColor(org.gnome.gtk.StateFlags,%20org.gnome.gdk.RGBA)>
[TextTag]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/gtk/TextTag.html>
[Filter]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/freedesktop/cairo/Filter.html>

<a name="4.0.20" id="1310346568" title="Port Me"></a>

java-gnome 4.0.20 (11 Jul 2011)
===============================

This will be the last release in the 4.0 series. It is meant only as an aide
to porting over the API bump between 4.0 and 4.1; if your code builds against
4.0.20 without reference to any deprecated classes or methods then you can be
fairly certain it will build against 4.1.1; if we've missed something please
let us know.

* <strike>**`org.gnome.gdk.Color`**</strike>
* <strike>**`org.gnome.gdk.Colormap`**</strike>
* <strike>**`org.gnome.gdk.EventExpose`**</strike>
* <strike>**`org.gnome.gdk.Drawable`**</strike>
* <strike>**`org.gnome.gdk.Pixmap`**</strike>
* <strike>**`org.gnome.gdk.Bitmap`**</strike>
* <strike>**`org.gnome.gdk.Region`**</strike>
* <strike>**`org.gnome.gtk.Widget.ExposeEvent`**</strike>
* <strike>**`org.gnome.gtk.StateType`**</strike>
* <strike>**`org.gnome.gtk.Ruler`**</strike>
* <strike>**`org.gnome.gtk.Object`**</strike>
* <strike>**`org.gnome.gtk.Item`**</strike>
* <strike>**`org.gnome.gtk.ComboBoxEntry`**</strike>
* <strike>**`org.gnome.gtk.TextComboBox`**</strike>
* <strike>**`org.gnome.gtk.TextComboBoxEntry`**</strike>
* <strike>**`org.gnome.glade.Glade`**</strike>
* <strike>**`org.gnome.glade.XML`**</strike>

AfC

Website update
==============

We've reorganized the java-gnome website. API Documentation is now found at
[`doc/api/4.1/`](/doc/api/4.1/overview-summary.html). This prepares us for the
upcoming API bump.

AfC

<a name="4.0.19" id="1297659945" title="Which Font?"></a>

java-gnome 4.0.19 (14 Feb 2011)
===============================

_What do you mean that's not the font I asked for?_

This release includes some minor feature enhancements.

Preliminary coverage of Pango's Font object. Font is Pango's abstraction
describing a typeface, and is what is actually loaded. We've exposed the
methods that allow you to find out what was _actually_ loaded for a given
FontDescription request. You do this with Context's
[`loadFont()`][Context.loadFont()] and then Font's
[`describe()`][Font.describe()]. Thanks to Behdad Esfahbod for explaining how
all this works.

* **`org.gnome.pango.Font`**
* **`org.gnome.pango.Context`**

Exposed a few utility functions, including one to find out if your program is
running in a terminal or from the Desktop directly.

* **`org.freedesktop.bindings.Environment`**

GTK improvements
----------------

Further improved some corner cases involved in using Actions, and now you can
make them with named Icons.

* **`org.gnome.gtk.Action`**
* **`org.gnome.gtk.ToolItem`**

There are some odd corner cases, especially with TextView, where idle handlers
need to run before you have the calculations you need ready to query. One
workaround appears to be letting the main loop cycle, so we've exposed
[`Gtk.mainIterationDo()`][Gtk.mainIterationDo()] and the `Gtk.eventsPending()`
which wraps it.

* **`org.gnome.gtk.Gtk`**

Build improvements
------------------

Building java-gnome on Mandriva now works! Thanks to Liam Quin for helping QA
the top level `configure` script.

AfC

[Gtk.mainIterationDo()]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/gtk/Gtk.html#mainIterationDo(boolean)>
[Context.loadFont()]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/pango/Context.html#loadFont(org.gnome.pango.FontDescription)>
[Font.describe()]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/pango/Font.html#describe()>

<a name="4.0.18" id="1293156906" title="Fix GDK_IS_WINDOW crash"></a>

java-gnome 4.0.18 (23 Dec 2010)
===============================

_My compressed original is better than your uncompressed copy_

This is a bug fix release. A serious crasher was occurring when you requested
a the underlying [org.gnome.gdk] Window backing a Widget, as is often
necessary before popping up context menus. Thanks to Kenneth Prugh and
Guillaume Mazoyer for their help in duplicating and isolating the problem.

Better image rendering
----------------------

While we're at it, we've merged work in progress offering coverage of the
librsvg Scalable Vector Graphics loader. This allows you to draw an SVG image
as a vector graphic to Cairo (which itself works in vector form, of course),
and is a substantial improvement over just loading the `.svg` with gdk-pixbuf
(which rasterizes the graphic to a bitmap first, of course). Load the image
with [Handle][], then draw it with Context's
[`showHandle()`][Context.showHandle()].

* **`org.freedesktop.cairo.Context`**
* **`org.gnome.rsvg.Handle`**
* **`org.gnome.rsvg.DimensionData`**
* `org.gnome.rsvg.Plumbing`
* `org.gnome.rsvg.RsvgDimensionDataOverride`
* `org.gnome.rsvg.ValidateVectorIllustrations`
* `cairo.ExampleDrawingPenguins`

We've also added coverage of Cairo Surface's new
[`setMimeType()`][Surface.setMimeType()], which allows you to embed the the
original [ie JPEG, or to a lesser extent PNG] image in PDF output rather than
just the decoded, rasterized, and very huge bitmap image that Cairo uses on
screen and would otherwise have used in PDF and SVG output. So 100 kB JPEGs
stay JPEGs instead of turning into 12 MB bitmaps. Yeay.

* **`org.freedesktop.cairo.Surface`**
* **`org.freedesktop.cairo.RecordingSurface`**
* **`org.freedesktop.cairo.MimeType`**
* `org.freedesktop.cairo.CairoSurfaceOverride`
* `org.freedesktop.cairo.ValidateCairoInternals`

java-gnome now depends on Cairo 1.10 and librsvg 2.32.

AfC

[Handle]: http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/rsvg/Handle.html
[Context.showHandle()]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/freedesktop/cairo/Context.html#showHandle(org.gnome.rsvg.Handle)>
[Surface.setMimeType()]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/freedesktop/cairo/Surface.html#setMimeData(org.freedesktop.cairo.MimeType,%20byte[])>

<a name="4.0.17" id="1290043843" title="Dictionaries exist"></a>

java-gnome 4.0.17 (18 Nov 2010)
===============================

_All dictionaries are equal. But some dictionaries are more equal than
others._

After some 6 months of development, this release includes substantial
improvements across the library. Thanks to Guillaume Mazoyer, Michael
Culbertson, Douglas Goulart, Vreixo Formoso, Mauro Galli, Thijs Leibbrand, and
Andrew Cowie for their contributions to the library, and also to Yaakov
Selkowitz, and Alexander Boström for their updates to the build system.

Enchant Dictionaries
--------------------

Improve the utility of the Enchant library by exposing functionality to test
wither a dictionary [exists][Enchant.existsDictionary()] for a given "language
tag", and to [list][Enchant.listDictionaries()] all available dictionaries.
Add speciality functions to the Internationalization class facilitating the
translation of [language][Internationalization.translateLangageName()] and
[country][Internationalization.translateCountryName()] names so you can
present the list of available languages properly translated in the user's
language.

* **`org.freedesktop.enchant.Enchant`**
* **`org.freedesktop.bindings.Internationalization`**
* `org.freedesktop.enchant.EnchantBrokerOverride`
* `org.freedesktop.enchant.ValidateEnchantInternals`
* `org.freedesktop.bindings.ValidateInternationalization`

GTK improvements
----------------

Introduce Icon as a strongly typed class to wrap "named icons" available in an
icon theme, complementing the previous coverage of "stock icons" provided by
the Stock class. Add methods to DataColumn, TreeModel, Image, and Entry making
these available.

* **`org.freedesktop.icons.Icon`**
* `org.freedesktop.icons.Helper`
* **`org.freedesktop.icons.ActionIcon`**
* **`org.freedesktop.icons.ApplicationIcon`**
* **`org.freedesktop.icons.CategoryIcon`**
* **`org.freedesktop.icons.DeviceIcon`**
* **`org.freedesktop.icons.EmblemIcon`**
* **`org.freedesktop.icons.FaceIcon`**
* **`org.freedesktop.icons.MimeIcon`**
* **`org.freedesktop.icons.PlaceIcon`**
* **`org.freedesktop.icons.StateIcon`**
* **`org.gnome.gtk.DataColumnIcon`**
* **`org.gnome.gtk.TreeModel`**
* **`org.gnome.gtk.Image`**
* **`org.gnome.gtk.Entry`**
* `org.freedesktop.icons.ValidateIconItems`

Also in TreeView land, Vreixo Foromso contributed a change to make
DataColumnReference generic, noting that this was his "one great irritation"
with java-gnome. Itch scratched, apparently. `:)`

* **`org.gnome.gtk.TreeModel`**
* **`org.gnome.gtk.DataColumnReference`**
* `org.gnome.gtk.ValidateTreeModel`

A fair bit of work went into polishing coverage in various classes. We now
have coverage for Adjustment's various properties (necessary if you want to
drive a scroll bar around yourself without using one built into a
ScrolledWindow).

* **`org.gnome.gtk.Adjustment`**
* **`org.gnome.gtk.HScrollbar`**
* **`org.gnome.gtk.VScrollbar`**
* `org.gnome.gtk.ValidateAssistant`
* `org.gnome.gtk.ValidateProperties`
* `org.gnome.gtk.ValidateSignalEmission`

We've also introduced a new signal in the [Assistant][]. You can now define
the behaviour of an Assistant using the `ForwardPage` signal with the
`setForwardPageCallback()` method. It can help you to skip pages when you need
to. When going back, the Assistant will also skip the previously skipped page.

* **`org.gnome.gtk.Assistant`**
* `org.gnome.gtk.GtkAssistantOverride`
* `org.gnome.gtk.ValidateAssistant`

java-gnome now supports GTK+ 2.20 and introduces the new [Spinner][] widget
that can be used to display an unknown progress.

* **`org.gnome.gtk.Spinner`**
* **`org.gnome.gtk.CellRendererSpinner`**

Added coverage for another utility function, this time the one that
[escapes][Glib.markupEscapeText()] text in strings so that it can be safely
included when Pango markup is being used.

* **`org.gnome.glib.Glib`**

If you need to ensure whatever has been copied to the clipboard is available
after your application terminates, you can call Clipboard's `store()`.

* **`org.gnome.gtk.Clipboard`**

Thread safety
-------------

Fixed a fairly serious bug in the interaction between the memory management
code and the thread safety mechanism. Amazing we got away with this one so
long, really. Thanks to Vreixo Formoso for helping with analysis of the crash
dumps, confirming the diagnosis, and double checking the proposed solution.
The problem only showed up if you were making extensive use of something like
TextViews which (internal to GTK) did its drawing in a background idle
handler.

* `org.gnome.glib.GObject`

Also fixed a crasher that turned up if your cursor theme didn't have a certain
named cursor. `ENOTGNOME`, but anyway.

* **`org.gnome.gdk.Cursor`**

More drawing
------------

The Cairo graphics library continues to be a joy to use and we continue to
make minor improvements to our coverage as people use it more. In particular,
based on help from Benjamin Otte and others we've refined the way you create a
[Context][] in a `Widget.ExposeEvent`, improving efficiency and taking
advantage of some of the underlying support functions more effectively.

* **`org.freedesktop.cairo.Extend`**
* **`org.freedesktop.cairo.FillRule`**
* **`org.freedesktop.cairo.HintStyle`**
* **`org.freedesktop.cairo.Context`**
* **`org.freedesktop.cairo.FontOptions`**
* **`org.freedesktop.cairo.Pattern`**
* **`org.gnome.gdk.Color`**
* **`org.gnome.pango.Layout`**
* **`org.gnome.pango.Underline`**
* `org.freedesktop.cairo.GdkCairoSupport`
* `org.freedesktop.cairo.ValidateCairoContext`

Looking ahead
-------------

With GTK 3.0 coming closer to reality, we're keeping close track of the
activity there. GTK 3.0 is a pretty vast API and ABI break from 2.x with some
fairly major changes to the way Widget sizing works, along with an overhaul of
the drawing system. We'll be updating java-gnome to meet these changes in the
months to come.

AfC, GM

[Enchant.existsDictionary()]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/freedesktop/enchant/Enchant.html#existsDictionary(java.lang.String)>
[Enchant.listDictionaries()]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/freedesktop/enchant/Enchant.html#listDictionaries()>
[Internationalization.translateLangageName()]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/freedesktop/bindings/Internationalization.html#translateLanguageName(java.lang.String)>
[Internationalization.translateCountryName()]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/freedesktop/bindings/Internationalization.html#translateCountryName(java.lang.String)>
[Assistant]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/gtk/Assistant.html>
[Spinner]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/gtk/Spinner.html>
[Glib.markupEscapeText()]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/gnome/glib/Glib.html#markupEscapeText(java.lang.String)>
[Context]: <http://java-gnome.sourceforge.net/doc/api/4.1/org/freedesktop/cairo/Context.html#Context(org.gnome.gdk.EventExpose)>


<a name="4.0.16" id="1276732981" title="Accelerating operations"></a>

java-gnome 4.0.16 (17 Jun 2010)
===============================

_Accelerating is good for you_

Accelerators
------------

java-gnome now has full support for accelerators, the key bindings (such as
**`Ctrl+Q`** for "quit") typically used to activate MenuItems and Actions. The
heart of the API is in the [AcceleratorGroup][] class, although you actually
use it care of MenuItem's `setAccelerator()` and Action's `setAccelerator()`.
Huge thanks are due to Thijs Leibbrand for having navigating the almost
incomprehensible native API and figured out how we could best add coverage for
Java programs.

* **`org.gnome.gtk.AcceleratorGroup`**
* **`org.gnome.gtk.Menu`**
* **`org.gnome.gtk.MenuItem`**
* **`org.gnome.gtk.ImageMenuItem`**
* **`org.gnome.gtk.Window`**
* **`org.gnome.gtk.Action`**
* `menu.ExampleSimpleMenu`

Cairo Operations
----------------

Though we've had support for Cairo's various "operators" (different modes for
combining what's being drawn with what's already on the surface) for some
time, we didn't really know what we were doing. Thanks to the careful work of
Kenneth Prugh, we've now got full coverage in the [Operator][] class along
with a magnificent series of illustrations. These are the same pictures as are
in the underlying Cairo documentation, but like our screenshots, ours are
generated automatically by java-gnome programs whenever you build the
documentation.

* **`org.freedesktop.cairo.Operator`**
* `Harness`
* `org.freedesktop.cairo.IllustrationOperatorOver`
* `org.freedesktop.cairo.IllustrationOperatorDestOver`
* `org.freedesktop.cairo.IllustrationOperatorSaturate`
* `org.freedesktop.cairo.IllustrationOperatorDestIn`
* `org.freedesktop.cairo.IllustrationOperatorDestAtop`
* `org.freedesktop.cairo.Illustration`
* `org.freedesktop.cairo.IllustrationOperatorAdd`
* `org.freedesktop.cairo.IllustrationOperatorDest`
* `org.freedesktop.cairo.IllustrationOperatorAtop`
* `org.freedesktop.cairo.IllustrationOperatorSource`
* `org.freedesktop.cairo.IllustrationOperatorClear`
* `org.freedesktop.cairo.IllustrationOperatorDestOut`
* `org.freedesktop.cairo.IllustrationOperatorOut`
* `org.freedesktop.cairo.IllustrationOperator`
* `org.freedesktop.cairo.IllustrationOperatorXOR`
* `org.freedesktop.cairo.IllustrationOperatorIn`

Miscellaneous improvements
--------------------------

The style `CENTER` has been added in ButtonBoxStyle.

* **`org.gnome.gtk.ButtonBoxStyle`**

Coverage of GTK's new [InfoBar][] Widget was added by Guillaume Mazoyer, who
also made numerous touch ups to various core classes. The Activatable and
Editable interfaces got some love. And methods to get "human readable" byte
sizes have been added to the [Glib][] utility class.

* **`org.gnome.gtk.Activatable`**
* **`org.gnome.gtk.Editable`**
* **`org.gnome.gtk.Entry`**
* **`org.gnome.gtk.EntryBuffer`**
* **`org.gnome.gtk.InfoBar`**
* **`org.gnome.glib.Glib`**
* **`org.gnome.gtk.Widget`**
* **`org.gnome.gtk.StatusIcon`**
* `org.gnome.glib.ValidateUtilityFunctions`
* `org.gnome.gtk.ValidateEntry`

Finally, we exposed the code needed to force GDK to revert to the pre GTK 2.18
behaviour of using native X Windows for every Widget. This shouldn't be
necessary -- the whole point of major changes like the "client-side windows"
branch are is that they are supposed to Just Work (and more to the point Just
Work better, over time) -- but it does give a workaround for unusual corner
cases where either GTK, java-gnome, or the developer is constrained and needs
some help.

* **`org.gnome.gdk.Window`**

AfC

[InfoBar]: doc/api/4.1/org/gnome/gtk/InfoBar.html
[AcceleratorGroup]: doc/api/4.1/org/gnome/gtk/AcceleratorGroup.html
[Operator]: doc/api/4.1/org/freedesktop/cairo/Operator.html
[Glib]: doc/api/4.1/org/gnome/glib/Glib.html

<a name="4.0.15" id="1268692532" title="Radio thang"></a>

java-gnome 4.0.15 (16 Mar 2010)
===============================

_Radio things_

This has mostly been a bug fix cycle with numerous internal quality
improvements being made. A few developer visible API additions have been made,
summarized below.

Unified radio handling
----------------------

There are a number of controls in GTK that exhibit the "radio" behaviour of
being in a group of which only one can be selected: RadioButton,
RadioMenuItem, RadioToolButton and RadioAction. We originally had a class
called RadioButtonGroup which was used when constructing RadioButtons to
indicate which group they were a member of. In introducing overage of the
other radio types, Guillaume Mazoyer implemented a generic grouping class
called RadioGroup which is now used for all the radio types.

* **`org.gnome.gtk.RadioGroup`**
* **`org.gnome.gtk.RadioButton`**
* **`org.gnome.gtk.RadioMenuItem`**
* **`org.gnome.gtk.RadioToolButton`**
* **`org.gnome.gtk.RadioAction`**

XDG utility functions
---------------------

A number of utility functions (aka static methods) were added to Glib allowing
you to access the various user and systems directories as specified by the XDG
specification. These are `Glib.getUserConfigDir()`, `Glib.getUserDataDir()`
and friends.

* **`org.gnome.glib.UserDirectory`**
* **`org.gnome.glib.Glib`**

Miscellaneous improvements
--------------------------

Better control of positioning when popping up context menus; you can specify
co-ordinates when calling Menu's `popup()`.

* **`org.gnome.gtk.Menu`**
* `org.gnome.gtk.GtkMenuOverride`

The no-arg "convenience" packing methods we invented for HBox and VBox were
causing more trouble than they were worth because people we not understanding
the implications of the "default" packing values. So these are deprecated; the
full four-argument `packStart()` and `packEnd()` have been present for a long
time and are to be used in preference.

* **`org.gnome.gtk.Box`**

Serkan Kaba spent a bit of time working with the text version of the ComboBox
API. Apparently no one had needed `removeText()` so he added that.

* **`org.gnome.gtk.TextComboBox`**

A number of improvements to the coverage of GDK's event masks were merged.
This is work originally by Vreixo Formoso necessary to support the
`Widget.MotionNotifyEvent` signal, though it has use in other applications.
Widget now has `addEvents()` and `setEvents()` to this end.

* **`org.gnome.gtk.Widget`**
* `org.gnome.gtk.GtkWidgetOverride`
* **`org.gnome.gdk.EventMotion`**
* **`org.gnome.gdk.EventMask`**

Finally, Guillaume needed to handle selections in IconViews. The API is
similiar to that in TreeView, but doesn't use the same TreeSelection helper
class; the methods are directly on IconView. Boo for asymmetry. Anyway, we've
exposed isSelected() on both TreeSelection and IconView so you can find out if
a given TreePath is currently selected.

* **`org.gnome.gtk.IconView`**
* **`org.gnome.gtk.TreeSelection`**
* `org.gnome.gtk.ValidateIconView`

Better initialization checking
------------------------------

Logic checking that the library has been properly initialized has been
refactored, making it harder to accidentally misuse java-gnome when getting
started \[or when starting a new program after you've been using the library
for years and forgotten the basics `:)`\].

Last but not least, a number of bug fixes; one was developer-visible;
`Enchant.requestDictionary()` now returns `null` like it claimed to if you
request an unknown dictionary. Nice catch, Serkan.

* **`org.freedesktop.enchant.Enchant`**
* `org.freedesktop.enchant.Plumbing`
* `org.freedesktop.enchant.ValidateEnchantInternals`

All the source files have the full (ie traditional) GPL v2 header text now;
the files comprising the library as used at run-time have GPL headers text +
the Classpath Exception text. Needed to be done. Makes for a large diff this
release, but makes the
[Ohloh](http://www.ohloh.net/p/java-gnome/analyses/latest)'s page for
java-gnome happy too `:)`.

AfC

<a name="4.0.14" id="1260921997" title="Enchanting input methods"></a>

java-gnome 4.0.14 (16 Dec 2009)
===============================

_You have to compose in order to enchant_

Access to Enchant spell checking API
------------------------------------

Coverage of the Enchant spell checking facade (which was already an implicit
dependency arising from our GtkSpell coverage) is now included in java-gnome.
It's a lovely library with a simple to use API which in turn fronts for
various back end spelling providers.

* **`org.freedesktop.enchant.Enchant`**
* **`org.freedesktop.enchant.Dictionary`**
* `org.freedesktop.enchant.Broker`
* `org.freedesktop.enchant.ValidateEnchantInternals`

More detailed input handling
----------------------------

GTK's handling of complex input methods is extraordinarily powerful, and of
course present by default in the Entry and TextView text entry Widgets. If
you're doing your own text based work, however, you might need to capture the
results of an input method being used to compose characters or words.
`InputMethod.Commit` is where the result of a compose sequence is captured and
delivered to the application.

* **`org.gnome.gtk.InputMethod`**
* **`org.gnome.gtk.SimpleInputMethod`**
* **`org.gnome.gtk.MulticontextInputMethod`**
* `org.gnome.gtk.ValidateInputMethods`

We've also made numerous improvements down in GDK where events are processed;
as a Java library we represent many naked low-level native entities with
strongly-typed classes, and have improved our coverage here, notably with new
Cursor constants representing the common use cases of changing the pointer.

* **`org.gnome.gdk.Keyval`**
* **`org.gnome.gdk.ModifierType`**
* **`org.gnome.gdk.Cursor`**
* **`org.gnome.gdk.CursorType`**

Improved text rendering
-----------------------

Other minor improvements are present across the text rendering stack, notably
with the ability to introspect where a Pango Layout has made its line breaks
when wrapping via LayoutLine's `getStartIndex()` and `getLength()` methods.

* **`org.gnome.gtk.TextIter`**
* **`org.gnome.gtk.TextTag`**
* `org.gnome.pango.ValidateTextBuffer`
* **`org.gnome.pango.LayoutLine`**
* `org.gnome.pango.ValidatePangoWrapBehaviour`

Guillaume Mazoyer finished up a work by Serkan Kaba resulting in us having
coverage of the special LinkButton subclass of Button which can be used to
present clickable URLs.

* **`org.gnome.gtk.LinkButton`**
* `org.gnome.gtk.ValidateLinkBehaviour`
* `button.ExampleDocumentationLink`

You can now create custom PaperSize objects, which is handy if you need to use
Cairo to output PDF documents with a non-standard paper format.

* **`org.gnome.gtk.CustomPaperSize`**
* `org.gnome.gtk.ValidatePrinting`

Other changes
-------------

You can now use gdk-pixbuf to query an image on disk for its dimensions via
Pixbuf `getFileInfo()` function calls.

* **`org.gnome.gdk.Pixbuf`**
* `org.gnome.gdk.ValidateImageHandling`

There were of course miscellaneous improvements to various long established
core classes, mostly fixing typos.

* **`org.gnome.gtk.Dialog`**
* **`org.gnome.gtk.AboutDialog`**
* **`org.gnome.gtk.TreeIter`**
* **`org.gnome.gtk.TreeModelFilter`**
* `org.gnome.gtk.ValidateTreeStore`
* **`org.gnome.gtk.Window`**
* **`org.gnome.pango.Attribute`**

We now have the methods necessary to have ImageMenuItems actually show images
(there's a GNOME bug whereby suddenly icons are not showing in menus. So you
need to either explicitly tell an ImageMenuItem that it should always show its
image, or use the global Settings to say that Menus and Buttons should always
have their icons showing).

* **`org.gnome.gtk.ImageMenuItem`**
* **`org.gnome.gtk.Settings`**
* `org.gnome.gtk.ValidateGlobalSettings`

The internal initialization sequence has been tweaked to ensure that GLib's
threads are initialized before anything else. This means java-gnome apps will
work if `glib 2.22.3` is installed; this is a workaround for bug
[603774](https://bugzilla.gnome.org/show_bug.cgi?id=603774).

Headless testing
----------------

For a long time we've had to be careful in our test suite not to do anything
that would cause a Window to appear or otherwise popup while the tests were
running. But some for some test cases this is unavoidable, especially if the
main loop needs to cycle. We now run the java-gnome test suite within a
virtual X server (ie `Xvfb`), and as a result distros packaging the library
can run the test suite on their headless build servers if they wish.

There's a new base class for java-gnome TestCases needing to run in this
environment.

* `org.gnome.gtk.GraphicalTestCase`

Looking ahead
-------------

What's ahead for java-gnome? That's always a good question. At this point
java-gnome provides a comprehensive API for development of user interfaces
suitable for the GNOME desktop, and it's incredibly rewarding to see people
using the library for their own programs.

Development of java-gnome has continued pretty steadily, driven by people
finding they need additional features from some of the underlying GNOME and
FreeDesktop libraries we already expose. As a community we also work to
fine-tune the performance and quality of the library through continuous
improvement of the code base and its algorithms.

There are also people quietly working on experimental coverage of more unusual
libraries such as GStreamer and Clutter which is pretty exciting to see.

People using java-gnome are always welcome to join us in `#java-gnome` to ask
questions or just hang out! So happy hacking, and see you soon.

AfC

<a name="4.0.13" id="1251341992" title="Fix unicode handling"></a>

java-gnome 4.0.13 (27 Aug 2009)
===============================

_Unicode. It's bigger than you think._

This is a bug fix release to address a serious weakness in Java's handling of
Unicode characters.

Unicode handling
----------------

It turns out that Java's chars are **not** pure Unicode codepoints. Most
people know that Java String objects are arrays of Java chars, but in
aggregate they are encoded in UTF-16 in order to deal with the fact that there
are Unicode characters whose index is higher than `0xFFFF` and which need more
than two bytes to identify them. It's a problem that an application developer
has to deal with if they're using high-range "supplementary" Unicode
characters, but wasn't something that would break java-gnome...

Except it turns out that the Java VM does not do UTF-8 translation properly.
It has a hard wired limitation preventing it from writing out UTF-8 sequences
longer than 3 bytes. Who knows what crack they were smoking when they decided
that one. But things like TextView / TextBuffer work in characters, so we need
characters. (actually, they work in UTF-8 bytes, but the offsets in our public
API are the characters variants).

Luckily, we can get at the raw UTF-16 arrays backing Strings, and so in
combination with GLib's character set conversion functions, we've been able to
redo our string handling internally so as to have correct treatment of Unicode
codepoints. Lots of testing.

* `org.gnome.gtk.ValidateUnicode`
* `org.gnome.gtk.ValidateTextBuffer`
* **`org.gnome.gtk.TextIter`**

This surgery was almost entirely internal; Strings returned by java-gnome
methods are of course still Java String objects. There was only one signature
change in the public API: TextIter's `getChar()` now returns Java int, not
Java char.

New coverage
------------

<img src="doc/api/4.1/org/gnome/gtk/Entry.png" alt="" style="float: right;
text-align: right; padding-left: 15px;" /> This release also features the work
of Guillaume Mazoyer exposing some of the new features available in Entry
Widgets, including displaying icons and showing progress bars in the
background.

* **`org.gnome.gtk.Entry`**
* **`org.gnome.gtk.EntryIconPosition`**
* `org.gnome.gtk.ValidateEntry`
* `org.gnome.gtk.SnapshotEntryIcon`
* `entry.ExampleSearchSomeone`

Along with minor enhancements to various miscellaneous classes.

* **`org.gnome.gdk.ModifierType`**
* **`org.gnome.gtk.Label`**
* **`org.gnome.pango.EllipsizeMode`**
* **`org.gnome.pango.RiseAttribute`**

With this release, java-gnome now requires GTK `2.16` or newer.

AfC

<a name="4.0.12" id="1248418784" title="Unique, Notify, Sourceview, Spelling"></a>

java-gnome 4.0.12 (24 Jul 2009)
===============================

_Being Uniquely Notified while Spelling the Sources you are Viewing is good
for the soul._

In addition to ongoing improvement in our coverage of the GTK widget toolkit,
the next release of java-gnome begins to realize our vision to offer
coverage of the broad suite of libraries making up the GNOME desktop.

New Coverage
------------

The TextView text editing Widget has received two significant capability
boosts. With the work of Stefan Schweizer, we now have coverage of the
powerful GtkSourceView library with its impressive built-in multi
\[programming\] language source highlighting features.

* **`org.gnome.sourceview.Language`**
* **`org.gnome.sourceview.LanguageManager`**
* `org.gnome.sourceview.Plumbing`
* **`org.gnome.sourceview.SourceBuffer`**
* **`org.gnome.sourceview.SourceView`**
* `org.gnome.sourceview.ValidateSourceView`
* `sourceview.ExampleEditor`

And with the contribution of GtkSpell coverage by Serkan Kaba, we can now
offer spell checking in TextViews as well.

* **`org.gnome.gtk.Spell`**
* **`org.gnome.gtk.TextView`**
* `org.gnome.gtk.ValidateTextViewSpelling`
* `org.gnome.gtk.SnapshotTextViewSpelling`
* `textview.ExampleInstantMessenger`

Two other GNOME libraries feature in this release. Serkan also contributed
excellent coverage of LibNotify, enabling an application to create and send
popups to be displayed by the desktop notification mechanism.

* **`org.gnome.notify.Notification`**
* **`org.gnome.notify.Notify`**
* **`org.gnome.notify.Urgency`**
* `org.gnome.notify.Plumbing`
* `notify.ExampleLowBattery`

And, we expose LibUnique, which offers DBus-powered machinery enabling a
developer to ensure only one instance of their application is running.

* **`org.gnome.unique.Application`**
* **`org.gnome.unique.Command`**
* **`org.gnome.unique.MessageData`**
* `org.gnome.unique.Plumbing`
* **`org.gnome.unique.Response`**
* `org.gnome.unique.ValidateUniqueApplications`
* `unique.ExampleThereCanBeOnlyOne`

Continuing improvement
----------------------

Lots of minor changes and enhancements throughout the core GTK libraries.
Highlights include improved mouse button handling, filtering when choosing
files, and further refinement to Pixbuf. Thanks to Peter Mossveld, Kenneth
Prugh, Vreixo Formoso, Serkan Kaba.

* **`org.freedesktop.cairo.Context`**
* `org.freedesktop.cairo.Status`
* `org.freedesktop.cairo.ValidateCairoContext`
* **`org.gnome.gdk.CursorType`**
* **`org.gnome.gdk.EventScroll`**
* **`org.gnome.gdk.MouseButton`**
* **`org.gnome.gdk.Pixbuf`**
* **`org.gnome.gdk.ScrollDirection`**
* **`org.gnome.gtk.AboutDialog`**
* **`org.gnome.gtk.CellRendererProgress`**
* **`org.gnome.gtk.ColorButton`**
* **`org.gnome.gtk.Entry`**
* **`org.gnome.gtk.FileChooser`**
* **`org.gnome.gtk.FileChooserButton`**
* **`org.gnome.gtk.FileChooserDialog`**
* **`org.gnome.gtk.FileChooserWidget`**
* **`org.gnome.gtk.FileFilter`**
* **`org.gnome.gtk.Notebook`**
* **`org.gnome.gtk.Range`**
* **`org.gnome.gtk.Toolbar`**
* **`org.gnome.gtk.ToolbarStyle`**
* **`org.gnome.gtk.Widget`**
* `org.freedesktop.bindings.ValidateInternationalization`
* `org.gnome.gtk.ValidateProperties`
* `org.gnome.gtk.ValidateTextBuffer`

Finally, thanks to Guillaume Mazoyer we now have coverage of EntryCompletion,
the feature of Entry Widgets whereby available possible completions are
offered to the based on characters the user has typed so far.

* **`org.gnome.gtk.EntryCompletion`**
* `org.gnome.gtk.ValidateEntryCompletion`
* `org.gnome.gtk.SnapshotEntryCompletion`
* `completion.ExampleLoginPrompt`

Looking ahead
-------------

There are a number of people working on various branches -- some small feature
extensions, and some major coverage additions that add signifant capabilies --
but which haven't quite made it to the point where they can be merged into
java-gnome. We'll see how these pieces of work fare in the coming months, but
nevertheless the Java bindings for GNOME have reached a significant level of
maturity and we are pleased to see people starting to use them in serious
applications.

AfC

<a name="4.0.11" id="1241147383" title="Bug fixes"></a>

java-gnome 4.0.11 (1 May 2009)
==============================

_This is a bug fix release._

We made a few mistakes in our handling of the PangoAttribute structures'
memory which resulted in VM crashes [unfortunately, the normal GNOME way of
debugging things is to `SIGSEGV`. That's fine for a C program but Bad™ for
your average Java Virtual Machine as it takes out the entire process. We
therefore work rather hard to avoid -- or at least trap -- this sort of
thing]. Releasing corrections for these bugs was a priority.

Concurrently a significant internal improvement in our handling of
accumulating Attributes into AttributeLists was made. While this is not user
visible _per se_, we were able to drop the requirement that the text you were
formatting already be in the Pango Layout before assigning the range that the
Attribute would cover. You also no longer need to pass that Layout to
`setIndices()`. This makes things a great deal easier if you are
simultaneously aggregating the text and assigning markup.

* **`org.gnome.pango.Attribute`**
* **`org.gnome.pango.AttributeList`**
* `org.gnome.pango.PangoAttributeOverride`
* **`org.gnome.pango.Layout`**
* **`org.gnome.pango.FontDescription`**
* **`org.gnome.pango.FallbackAttribute`**
* `org.gnome.pango.ValidatePangoTextRendering`
* **`org.gnome.pango.WrapMode`**

Martin Garton contributed some documentation quality improvements and new
coverage. Serkan Kaba made some minor fixes to ensure the unit tests run in a
Turkish locale. And as ever there are small incremental improvements to
various classes, including a number of additional properties exposed care of
the work of new contributor Thijs Leibbrand. 

* **`org.gnome.gtk.TreeViewColumn`**
* **`org.gnome.gtk.ComboBox`**
* **`org.gnome.gtk.Widget`**
* `org.freedesktop.bindings.ValidateInternationalization`
* **`org.gnome.gtk.Label`**
* **`org.gnome.gtk.ScrolledWindow`**
* **`org.gnome.gtk.Box`**
* **`org.gnome.gtk.AboutDialog`**

Looking ahead
-------------

This was a brief cycle; as noted we're mostly pushing some bug fixes.
Meanwhile there are a number of significant branches underway by various
hackers; which, hopefully, will feature prominently in the next release.

AfC

<a name="4.0.10" id="1236252051" title="Beautiful sculpted text"></a>

java-gnome 4.0.10 (5 Mar 2009)
==============================

_Sculptures made of letters_

This release is a landmark, because it features coverage of the Pango text
rendering library and so, along with our coverage of Cairo, brings us to a
complete solution for drawing graphics with text -- be they custom Widgets,
PDF documents, or beautiful vector illustrations.

In addition to merging Pango work from numerous contributors, we have made
massive improvements to our Cairo coverage, support for writing PDFs, and
better access to the system Clipboard.

Drawing API improvements
------------------------

We've done a huge amount of refinement to our APIs for the ever fabulous Cairo
Graphics drawing library, including full coverage of matrix translations,
rotations, and scaling and more integrated ways of setting the source pattern
to be used in stroke, paint, and fill operations. <img
src="doc/api/4.1/org/freedesktop/cairo/Matrix-translate.png" alt="" style="float:
right; text-align: right; padding-left: 15px;" /> Thanks to Kenneth Prugh for
having seen this through and having done the lion's share of the testing. <img
src="doc/api/4.1/org/freedesktop/cairo/Context-arcNegative.png" alt=""
style="float: right; text-align: right; padding-left: 15px; clear: right;" />

* **`org.freedesktop.cairo.Content`**
* **`org.freedesktop.cairo.Context`**
* **`org.freedesktop.cairo.Matrix`**
* `org.freedesktop.cairo.Plumbing`
* `org.freedesktop.cairo.Status`
* **`org.freedesktop.cairo.Surface`**
* **`org.freedesktop.cairo.ImageSurface`**
* **`org.freedesktop.cairo.SvgSurface`**
* **`org.freedesktop.cairo.XlibSurface`**
* **`org.freedesktop.cairo.SurfacePattern`**
* `cairo.ExampleDrawingText`

The various use cases of setting a source pattern for a Context including RGB,
RGBA, other Surfaces, already existing Pixbufs, etc have been combined into a
series of `setSource()` method overloads.

We've also had a number of improvements in the lower levels of GTK relating to
image rendering. The gdk-pixbuf library contains a capable image parser and we
can now feed it directly from a Pixbuf constructor. Thanks to new contributor
Martin Garton for his hard work getting that tested and accepted.

* **`org.gnome.gdk.InterpType`**
* **`org.gnome.gdk.Pixbuf`**

Drawing graphics often also requires drawing text. This release features
coverage of Pango, GNOME's powerful text rendering library. Pango is not just
about drawing mere glyphs; it also includes a sophisticated paragraph layout
engine which gives us a capable solution for drawing text onto Cairo Surfaces.

Thanks are due to Serkan Kaba and Kenneth Prugh have both been really helpful
testing; it was Vreixo Formoso who did the original leg work in April that
identified Layout as the key class that we needed to concentrate on and
cleaned up much of the underlying infrastructure.

* **`org.gnome.pango.Alignment`**
* **`org.gnome.pango.FontDescription`**
* **`org.gnome.pango.Layout`**
* **`org.gnome.pango.LayoutLine`**
* **`org.gnome.pango.Rectangle`**
* **`org.gnome.pango.Style`**
* **`org.gnome.pango.Variant`**
* **`org.gnome.pango.Weight`**
* `org.gnome.pango.Plumbing`
* **`org.gnome.pango.Context`**
* **`org.freedesktop.cairo.FontOptions`**
* **`org.freedesktop.cairo.HintMetrics`**
* **`org.gnome.pango.Attribute`**
* **`org.gnome.pango.AttributeList`**
* **`org.gnome.pango.FontDescriptionAttribute`**
* **`org.gnome.pango.SizeAttribute`**
* **`org.gnome.pango.StyleAttribute`**
* **`org.gnome.pango.VariantAttribute`**
* **`org.gnome.pango.WeightAttribute`**
* **`org.gnome.pango.UnderlineAttribute`**
* **`org.gnome.pango.BackgroundColorAttribute`**
* **`org.gnome.pango.ForegroundColorAttribute`**
* **`org.gnome.pango.BackgroundColorAttribute`**
* **`org.gnome.pango.UnderlineColorAttribute`**

As we were working on this we had occasion to continue the polish in and
around the TextView / TextBuffer APIs, including a number of convenience
methods for inserting text while simultaneously applying multiple TextTags,
and supporting changing default fonts.

* **`org.gnome.gtk.TextBuffer`**
* **`org.gnome.gtk.TextIter`**
* **`org.gnome.gtk.TextTag`**
* **`org.gnome.gtk.TextView`**
* **`org.gnome.gtk.TreeViewColumn`**
* **`org.gnome.gtk.Widget`**

We've also modelled "notify signals" for when a property changes; see the
`TextBuffer.NotifyCursorPosition` signal for this in action.

Finally, thanks to contributions from Zak Fenton and Kamil Szymala we have
support for applications running in composited window managers to have
transparent backgrounds -- which is an exceptionally cool effect, even if it
doesn't have much to do with creating HIG compliant usable applications `:)`.

* **`org.freedesktop.cairo.Operator`**
* **`org.gnome.gdk.Colormap`**
* **`org.gnome.gdk.Screen`**
* **`org.gnome.gdk.Window`**
* **`org.gnome.gtk.Widget`**

Handling cut & paste
--------------------

The GTK clipboard APIs are fairly complex, in no small part because the
underlying implementations in X are really rather complex. Our coverage here
in java-gnome is still fairly basic relative to that, but it's been enough for
people working on applications like text editors to write text to the system
clipboard, get notification when the clipboard changes, and read text back out
again.

* **`org.gnome.gtk.Clipboard`**

Printed document support
------------------------

<img src="doc/examples/cairo/ExampleLinedPaper.png" alt="" width="200"
style="float: right; text-align: right; padding-left: 15px; clear: right;" />
The work on Pango noted above represented the essential machinery necessary to
make effective use of Cairo's PDF backend, and this has started us down the
road of covering the GNOME printing APIs.  You can now generate `.pdf`
documents with java-gnome, and we've included an fun
[example](doc/examples/START.html#ExampleLinedPaper) showing this in action.

* **`org.freedesktop.cairo.PdfSurface`**
* **`org.gnome.gtk.PaperSize`**
* **`org.gnome.gtk.InternationalPaperSize`**
* **`org.gnome.gtk.NorthAmericanPaperSize`**
* **`org.gnome.gtk.Unit`**
* `cairo.ExampleLinedPaper`

Thanks to Nathan Strum for permission to use one of his sketches in the
example (you might find reading the [blog][nathan] where we found this
interesting -- it's a fascinating expos&eacute; of a graphic artist in action).

[nathan]: http://www.atariage.com/forums/index.php?automodule=blog&blogid=118&showentry=5460

Continuing improvement
----------------------

Thanks to new contributor Stefan Schweizer we now have better coverage of
signals relating to Notebook style Containers, and to new contributors Miloud
Bel and Bruno Dusausoy for numerous small improvements in and around
ProgressBar.

* **`org.gnome.gtk.Notebook`**
* **`org.gnome.gtk.ProgressBar`**

Miscellaneous documentation improvements and minor feature improvements always
feature in our releases, with minor changes having accrued in many classes.

* **`org.gnome.gtk.ShadowType`**
* **`org.gnome.gdk.CrossingMode`**
* **`org.gnome.gdk.NotifyType`**
* **`org.gnome.gtk.AboutDialog`**
* **`org.gnome.gtk.Arrow`**
* **`org.gnome.gtk.ArrowType`**
* **`org.gnome.gtk.Assistant`**
* **`org.gnome.gtk.Menu`**

Just as important as new coverage is getting rid of cruft that we don't need.
Libraries like GTK and GDK are, like any other mature project, full of
deprecated, obsolete, and unnecessary code -- not to mention things that we
just plain don't need in a Java binding of these underlying native libraries.
We're already pretty good about not generating translation Java or JNI C code
for such things; this release continues our refinements in this area with a
considerable refinement of the `.jar` and `.so` which is ultimately what we
ship.

Finally, it's worth noting that java-gnome's test suite is now 40 unit test
classes with 186 individual test fixtures. Combined with over 30 screenshot
generating programs and 17 example programs, we actually have surprisingly
good test coverage of our library. It's high time they -- and more to the
point the people who work hard to create such tests -- got as much credit as
the visible public APIs do.

* `UnitTests`
* `com.operationaldynamics.codegen.ValidateUtilityMethods`
* `com.operationaldynamics.defsparser.ValidateBlockUsage`
* `com.operationaldynamics.defsparser.ValidateDefsParsing`
* `com.operationaldynamics.codegen.ValidateThingUsage`
* `org.freedesktop.bindings.ValidateEnvironment`
* `org.freedesktop.bindings.ValidateInternationalization`
* `org.gnome.glib.ValidateReferenceCounting`
* `org.gnome.glib.ValidateMemoryManagement`
* `org.gnome.glib.ValidateGListMethods`
* `org.gnome.glib.ValidateConstants`
* `org.gnome.gtk.ValidateProperties`
* `org.gnome.gtk.ValidateSignalEmission`
* `org.gnome.gdk.ValidateScreensAndDisplays`
* `org.gnome.gdk.ValidateKeyboardHandling`
* `org.gnome.gdk.ValidateImageHandling`
* `org.freedesktop.cairo.ValidateCairoInternals`
* `org.freedesktop.cairo.ValidateDrawingToFile`
* `org.gnome.gtk.ValidateOutParameters`
* `org.gnome.gtk.ValidatePacking`
* `org.gnome.gtk.ValidateNotebookBehaviour`
* `org.gnome.gtk.ValidateFileChoosing`
* `org.gnome.gtk.ValidateStockItems`
* `org.gnome.gtk.ValidateResponseType`
* `org.gnome.gtk.ValidateTreeModel`
* `org.gnome.gtk.ValidateTreeStore`
* `org.gnome.gtk.ValidateTreeModelFilter`
* `org.gnome.gtk.ValidateTreeView`
* `org.gnome.gtk.ValidateIconView`
* `org.gnome.gtk.ValidateComboBox`
* `org.gnome.gtk.ValidateSnapshotUtilities`
* `org.gnome.gtk.ValidateAssistant`
* `org.gnome.gtk.ValidateTextBuffer`
* `org.gnome.gtk.ValidateTextViewProperties`
* `org.gnome.gtk.ValidateTextViewBorderWindows`
* `org.gnome.gtk.ValidateArrow`
* `org.gnome.pango.ValidatePangoTextRendering`
* `org.gnome.gtk.ValidatePrinting`
* `Harness`
* `org.freedesktop.cairo.SnapshotMatrixRotate`
* `org.freedesktop.cairo.SnapshotCairoAxis`
* `org.freedesktop.cairo.SnapshotCairo`
* `org.freedesktop.cairo.SnapshotContextLine`
* `org.freedesktop.cairo.SnapshotMatrixScale`
* `org.freedesktop.cairo.SnapshotContextArcNegative`
* `org.freedesktop.cairo.SnapshotMatrixTranslate`
* `org.freedesktop.cairo.SnapshotContextArc`
* `org.freedesktop.cairo.SnapshotMatrix`
* `org.freedesktop.cairo.SnapshotContextRectangle`
* `org.gnome.gtk.SnapshotCalendar`
* `org.gnome.gtk.SnapshotTreeView`
* `org.gnome.gtk.SnapshotButton`
* `org.gnome.gtk.SnapshotAboutDialog`
* `org.gnome.gtk.SnapshotArrow`
* `org.gnome.gtk.SnapshotTextView`
* `org.gnome.gtk.SnapshotRadioButton`
* `org.gnome.gtk.SnapshotTextComboBox`
* `org.gnome.gtk.SnapshotTreeStore`
* `org.gnome.gtk.SnapshotTextComboBoxEntry`
* `org.gnome.gtk.SnapshotFileChooserDialog`
* `org.gnome.gtk.SnapshotTextViewBorderWindows`
* `org.gnome.gtk.SnapshotVScale`
* `org.gnome.gtk.SnapshotHScale`
* `org.gnome.gtk.SnapshotDialog`
* `org.gnome.gtk.SnapshotEntryRed`
* `org.gnome.gtk.SnapshotComboBox`
* `org.gnome.gtk.SnapshotStatusbar`
* `org.gnome.gtk.SnapshotInfoMessageDialog`
* `org.gnome.gtk.Snapshot`
* `org.gnome.gtk.SnapshotAssistant`
* `org.gnome.gtk.SnapshotWindow`
* `org.gnome.gtk.SnapshotNotebook`
* `org.gnome.gtk.SnapshotQuestionMessageDialog`

Anyone can run the tests (and anyone wanting to submit a patch had _better_
run the tests!); they've been accumulating since java-gnome 4.0.0; it's just:

    $ make test

and

    $ make doc

If you're working in Eclipse launch class `UnitTests` as a JUnit test suite.

Build improvements
------------------

We have some fixes from new contributor Przemysław Grzegorczyk ensuring we
include the correct headers in a few corner cases. This came to us via work on
a GNOME Love bug, which is a first for us. Thanks!

Meanwhile, the ever industrious Serkan Kaba has added some changes to ensure
that our magically locating the native shared library component of java-gnome
works properly even under strange and unusual conditions.

Configuration and prerequisite detection on Open Solaris is improved thanks to
reports from new contributor Kamil Szymala.

Instructions for how to optimally lay out your branches when working with
java-gnome have been moved to the [`HACKING`](HACKING.html) file. People
intending to hack on the bindings from Eclipse are _really_ urged to set
things up this way as it will make their lives much easier. Also, using the
latest release of Bazaar (ie `bzr >= 1.12`) is definitely recommended -- it's
getting really fast. We've upgraded our public branches, so you will need
`1.9` at a minimum.

Note to those creating packages for distributions: java-gnome now depends on
the current stable GTK (ie `gtk+ >= 2.14`).

Looking ahead
-------------

Coverage of Poppler, GNOME's PDF rendering library is well along, but didn't
quite make it in time for this release. It could very likely feature in the
next version of java-gnome, as might coverage of GConf, GNOME's simple store
for application configuration options. People are also known to be working on
coverage of GtkSourceView, libwnck, and librsvg. We'll see what gets
contributed!

The most exciting part about java-gnome at the moment, though, is the number
of people working hard on applications using it. It takes a **long** time to
write a mature, well rounded, robust end-user program, but there are some
pretty cool projects ticking away out there, and it has been terrific to see
the authors of these projects joining our community.

Happy developing,

AfC

<a name="pango" id="1229699838" title="Text drawing"></a>

Rendering text
==============

One thing that was previously missing from our Cairo support was the ability
to draw text. To that end we have been working on exposing the excellent Pango
text rendering library. The '`pango`' branch where the effort has been
underway to polish coverage allowing developers add text when drawing has now
been merged to '`mainline`'.

Pango is not just about drawing mere words. It also includes a sophisticated
paragraph layout engine. This in turn represents the essential machinery
necessary to make effective use of Cairo's PDF backend; you can now generate
`.pdf` documents with java-gnome, and we've included an fun example showing
this in action.

Thanks are due to Serkan Kaba and Kenneth Prugh have both been really helpful
testing; it was Vreixo Formoso who did the original leg work in April that
identified Layout as the key class that we needed to concentrate on and
cleaned up much of the underlying infrastructure.

The API documentation for [Layout][] is pretty much the center piece of all
this work. Improvements to various Cairo backends have also been underway; the
descriptions at [XlibSurface][] and [PdfSurface][] may also be of interest.

AfC

[Layout]: doc/api/4.1/org/gnome/pango/Layout.html
[XlibSurface]: doc/api/4.1/org/freedesktop/cairo/XlibSurface.html
[PdfSurface]: doc/api/4.1/org/freedesktop/cairo/PdfSurface.html

<a name="4.0.9" id="1223889576" title="Text APIs and better running!"></a>

java-gnome 4.0.9 (13 Oct 2008)
==============================

_The pen is mightier than the sword_

New coverage
------------

<img src="doc/examples/textview/ExampleInstantMessenger.png" alt=""
style="float: right; text-align: right; padding-left: 15px;" /> 

This is the first release with coverage of GTK's powerful TextView/TextBuffer
multi-line text display and editing Widget. This has been the result of
several months of careful effort to present a clean and self-consistent API
while remaining faithful to the underlying implementation. This bulk of this
work was done by Stefan Prelle and Andrew Cowie, with contributions from
Kenneth Prugh and testing by many people in the `#java-gnome` community.

* **`org.gnome.gtk.TextBuffer`**
* **`org.gnome.gtk.TextIter`**
* **`org.gnome.gtk.TextTag`**
* **`org.gnome.gtk.TextTagTable`**
* **`org.gnome.gtk.TextView`**
* **`org.gnome.gtk.TextWindowType`**

The snapshot at right is from
[`ExampleInstantMessenger`](doc/examples/textview/ExampleInstantMessenger.html),
included with the sources. It is a somewhat detailed example showing the use
of TextView, TextBuffer, and related classes. Try running it!

Other improvements
------------------

Continuous improvement to various classes, especially in our documentation.
Incremental changes have occurred in a number of places. In the
TreeView/TreeModel APIs, some useful methods for translating TreeIters from
one model to another have been added.

* **`org.gnome.gtk.TreeModelSort`**	
* **`org.gnome.gtk.TreeModelFilter`**

Also thanks to the persistent work of Stefan Prelle, we have nice coverage of
GTK's Assistant (aka druid, wizard, etc): 

* **`org.gnome.gtk.Assistant`**

Better support for doing popup context menus, including some bug fixes. Thanks
Srichand Pendyala for taking care of this and to Owen Taylor for having
explained out some of the underlying implementation details.

* **`org.gnome.gdk.EventButton`**
* **`org.gnome.gtk.Action`**
* **`org.gnome.gtk.TreeView`**
* **`org.gnome.gtk.Widget`**

And, as usual, incremental improvements to core classes, notably a few new
signals here and there. Virtually every class has been touched in one way or
another; most changes are cosmetic but they add up to significant
contribution.

Reducing memory pressure
------------------------

Internally, java-gnome maintains a lookup table so that pointers coming from
the C side can be converted into proxy objects for the case where a proxy has
already been created. In any library there a great number of transient and
temporary objects and structures allocated, and we are no different. It turned
out that registering these temporary objects was putting pressure on the
lookup table. While these objects _were_ properly weak referenced and being
garbage collected (and thence freed), there were nevertheless an enormous
number of temporary objects being inserted and removed from the lookup table
-- and that sort of thing causes hash tables to grow overly large.

To do something about this we have split the former hierarchy root into two
classes. Only structures which have a persistent identity (which, in practise,
means only GObjects and certain Cairo entities) are registered so they can be
looked up by address later as necessary. These remain subclasses of the former
root `Proxy`. The rest of the Java side proxies for are now subclasses of
`Pointer` which is now the new root for our class hierarchy. These _aren't_
registered, essentially eliminating the transient pressure on the lookup
table.

* **`org.freedesktop.bindings.Pointer`**
* **`org.freedesktop.bindings.Proxy`**
* `org.freedesktop.bindings.Plumbing`
* `org.freedesktop.cairo.Entity`
* **`org.gnome.glib.Object`**
* `org.gnome.glib.Plumbing`
* **`org.gnome.glib.Boxed`**

This is entirely an internal change. Users of released & packaged versions of
the library will not notice any difference. Developers and hackers who have a
checkout of the project source code may need to `make clean` if they haven't
since `'mainline'` revno 567.

Thanks to Vreixo Formoso for doing the bulk of the leg-work on this one.

Making it easier to run java-gnome programs
-------------------------------------------

Because java-gnome is directly binds to underlying system libraries, it has a
native shared library component. This led to the usual development hassle of
having to specify where this library is to be found if it were anywhere other
than `/usr` and of course the nightmare of ensuring a VM used the right
library in the event you were developing against or hacking on a newer version
of java-gnome; in Java this meant:

    $ java -classpath /opt/local/share/java/gtk-4.0.jar:. -Djava.library.path=/opt/local/lib com.example.Program

No longer!

The native shared library part of java-gnome is now located deterministically
and loaded automatically. You don't need to faff about with
`java.library.path` on the command line or in your IDE any more!

    $ java -classpath /opt/local/share/java/gtk-4.1.jar:. com.example.Program

Our native component is completely coupled to the specific release you are
using, so sufficient version information is embedded in the `.so` name to
ensure that the right library (and only the right library) is loaded.

There are _no_ changes if you are simply working against an "in-place"
development build of java-gnome, be it from command line, or in an IDE like
Eclipse, things will Just Work&trade;; again, no `-Djava.library.path`:

    $ java -classpath ~/workspace/java-gnome/tmp/gtk-4.1.jar:. com.example.Program

Note to downstream packagers: running `make install` is now **compulsory**.
This signals a build that it is no longer being used "in-place" but instead is
to be prepared for installation to a system prefix (Gentoo got bit by this;
hooray for downstream packagers testing release candidates!). If you need to
change the install locations, you can specify overrides when you run
`./configure`, perhaps:

    $ ./configure prefix=/usr jardir=/opt/share/java libdir=/usr/lib/jni

The defaults are all sensible, of course, and are as described in
[README](README.html).

Build system improvements
-------------------------

Serkan Kaba has contributed a number of internal improvements allowing the top
level `./configure` script to be precise about the versions of various GNOME
dependencies we require.

With the release of GTK 2.14, various bits of the underlying libraries have
been deprecated. Thanks to the hard work from Serkan Kaba and new contributor
George McLachlan, java-gnome correctly builds against GTK 2.14 without any
problems.

Note that java-gnome releases do _not_ set `GTK_DISABLE_DEPRECATED` (this is a
change from 4.0.8); thanks to Mart Raudsepp of the Gentoo Linux desktop team
for pointing out why this would be better. These macros _are_ still enabled
for builds checked out from version control so hackers working on the bindings
so will be able to keep up with ensuring we react to future deprecations (it's
always awesome when downstream is a part of the upstream community; Serkan and
Kenneth are also Gentoo packagers, and take care of the java-gnome `.ebuild`
for us).

Incidentally, the version constants identifying the library are now alongside
the rest of the infrastructure. Public methods are now available if you want
to use the API or release version number in a snapshot or example.

* **`org.freedesktop.bindings.Version`**

Looking ahead
-------------

We're pretty happy with the state of the java-gnome right now. Coverage of the
most important parts of GTK are in place. Our treatment of the underlying
drawing library, Cairo, still has a bit to go, but the basics are there and a
firm foundation to build from. More interesting are the remaining areas; the
more general GNOME utility libraries and other parts of the Free Desktop stack
that might be needed by an end-user application. It'll be interesting to see
how these areas evolve in the coming months.

AfC

<a name="textview" id="1219892996" title="Text rendering"></a>

Lorem ipsum
===========

A major concentration of effort over the last few months has been to write
appropriate public coverage to present GTK's powerful but complex
TextView/TextBuffer API, a Widget for presenting multi-line text and the
backing store for manipulating it.

The '`textview`' branch where this work has been taking place has finally been
merged to '`mainline`'.

As this development cycle continues we will continue to review and refine the
API we're presenting, but this is nonetheless a significant milestone and one
we're justly proud of. Thanks to Stefan Prelle for having done much of the leg
work and also to Kenneth Prugh for his support during testing.

You can read the API documentation for [TextBuffer][] and [TextView][] if
you're interested in how things are shaping up.

AfC

[TextBuffer]: doc/api/4.1/org/gnome/gtk/TextBuffer.html
[TextView]: doc/api/4.1/org/gnome/gtk/TextView.html

<a name="4.0.8" id="1218770492" title="Cleanups and signals naming revised"></a>

java-gnome 4.0.8 (15 Aug 2008)
==============================

_Cleanups and fixups._

This release is mostly to push out bug fixes and internal improvements,
setting the stage for some major new feature development. We've also taken the
opportunity to introduce a major change to the way we connect handlers for
signals.

New coverage and continuing improvement
---------------------------------------

With thanks to new contributors Stefan Prelle and Andreas Kuehntopf we have a
number of small improvements to the TreeView/TreeModel APIs.

* **`org.gnome.gtk.TreeView`**
* **`org.gnome.gtk.TreeViewColumn`**
* **`org.gnome.gtk.TreeViewColumnSizing`**
* **`org.gnome.gtk.CellRendererText`**
* **`org.gnome.gtk.CellRendererToggle`**

As always, Widget and Window saw a bunch of work, with `Window.ConfigureEvent`
now being available and a number of additional property setters and methods
relating to window type.

* **`org.gnome.gdk.WindowTypeHint`**
* **`org.gnome.gtk.Widget`**
* **`org.gnome.gtk.Window`**

Widgets that scroll around a view of a broader underlying canvas have seen a
fair bit of activity related to controlling that scrolling.

* **`org.gnome.gtk.Adjustment`**
* **`org.gnome.gtk.Layout`**
* **`org.gnome.gtk.ScrolledWindow`**
* **`org.gnome.gtk.Viewport`**

New features include refinements and new coverage of methods in a variety of
lower level classes including that further support drawing operations. Bug
fixes, debugging improvements, and defencive enhancements to our thread safety
measures have also featured largely.

* `org.freedesktop.bindings.Plumbing`
* `org.gnome.glib.GObject`
* **`org.gnome.glib.Object`**
* `org.gnome.glib.Plumbing`
* `org.gnome.gdk.Plumbing`
* **`org.gnome.gdk.Rectangle`**
* **`org.gnome.gdk.Pixbuf`**
* **`org.gnome.gdk.Window`**
* **`org.gnome.gtk.Allocation`**
* **`org.gnome.gtk.Box`**
* **`org.gnome.gtk.Button`**
* **`org.gnome.gtk.Entry`**
* **`org.gnome.gtk.Image`**
* **`org.gnome.gtk.IconView`**
* **`org.gnome.gtk.Gtk`**
* **`org.gnome.gtk.Requisition`**
* **`org.gnome.gtk.Table`**

Signal naming change
--------------------

We have changed the naming scheme used to name the interfaces that are used
when hooking up signal handlers.

The names of the inner interfaces used to specify the prototypes of the
methods which receive signal callbacks have changed to the pattern
`Button.Clicked`, this being more appropriate to Java type naming conventions
and providing better consistency between the signal name, the method to be
implemented to receive the callback, and the method that can be used to emit
this signal.

API compatibility to previous releases in the 4.0 series has been preserved.
The old signal interfaces and `connect()` methods are `@deprecated`.
Developers are encouraged to migrate quickly; new coverage will of course all
follow the new pattern. Making the transition is is easy, especially in an
IDE. Most of the people we're aware of using the library have already ported
their code. Just turn assertions on to double check.

Build changes
-------------

java-gnome now defines C compiler flags like `GTK_DISABLE_DEPRECATED` to
ensure we are not linking against code that will be unavailable in GTK 3.0.
Many thanks are due to new contributor Kenneth Prugh for having done some
terrific grunt work to prune deprecated classes and methods from our `.defs`
data so that java-gnome compiles without using these APIs.

The build system internally now ensures that multiple runs don't occur
simultaneously, fixing a number of annoyances that cropped up when using IDEs
which tend to like trying to frequently re-run the build even if a previous
one hasn't finished.

Documentation, examples, and testing
------------------------------------

Our [API documentation](doc/api/4.1/overview-summary.html) and the growing set of
[example code](doc/examples/START.html) have all been updated to reflect the
new signal interface names. Doing so forced us to review a wide swath of the
documentation, and so along the way a huge number of minor improvements were
made. Given how detailed our JavaDoc is, this sort of painstaking work really
makes a genuine contribution to overall quality.

There has been steady growth in our test suite, which is great. When combined
with the snapshots used to illustrate our documentation, the coverage level
is substantial.

Error handling continues to improve. In the (hand written) public API wrapper
layer we do our best to catch  misuses of the library before they get sent to
the native code. But that's not always possible, and in 4.0.7 we introduced a
mechanism whereby GLib error messages get translated into Java Exceptions and
thrown. As of 4.0.8, in addition to `ERROR` and `CRITICAL`, we also throw
`WARNING`s as Exceptions. Getting a stack trace this way has proved very
useful in helping developers track down where they are making mistakes in
using the library.

Conclusion
----------

You can see the full changes accompanying a release by grabbing a copy of the
sources and running:

    $ bzr diff -r tag:v4.0.7..tag:v4.0.8

Because of the API changes to signal handling this release touches just about
every public class in the library and so isn't quite as clean (as a summary)
as in previous releases -- but it does show you everything that changed. `:)`

Looking ahead
-------------

Most of the contributors to java-gnome are working on branches that didn't
reach sufficient maturity to be merged in time for 4.0.8; that's the way it
goes sometimes. Major effort continues on implementing coverage of GTK's
powerful TextView/TextBuffer APIs, along with further drawing capabilities in
Cairo and Pango. There have also been a surprising level of interest on other
areas of the GNOME stack, with new contributors working on adding support to
java-gnome for Nautilus, GStreamer, and even WebKit. Exciting stuff!

AfC

<a name="signal" id="1217552625" title="Signal API change"></a>

Signal API change
=================

We have changed the naming scheme used to name the interfaces that are used
when hooking up signal handlers.

Connecting a handler to a Button now looks as follows:

<pre style="background:white; color:black;">
b.connect(new Button.Clicked() {
    public void onClicked(Button source) {
        // do stuff
    }
});
</pre>

Those developing with java-gnome will recognize that the inner interface's
name has changed to `Button.Clicked`, being more appropriate to Java type
naming conventions and providing better consistency between the signal name,
the method to be implemented, `onClicked()`, and the method that can be used
to emit this signal, `emitClicked()`.

The process which led to this change was discussed on the `java-gnome-hackers`
mailing list; see first [message][] if interested.

[message]: http://article.gmane.org/gmane.comp.gnome.bindings.java.devel/1147

Interfaces with the old names and corresponding `connect()` methods are still
present in the library (marked `@deprecated`, of course, and with assertions
to encourage developers to migrate their code) so the upcoming release will
preserve ABI compatibility.

The [documentation](doc/api/4.1/overview-summary.html) posted here has been
updated and a release candidate has been uploaded.

AfC

<a name="4.0.7" id="1209449091" title="Drawing"></a>

java-gnome 4.0.7 (30 Apr 2008)
==============================

_Draw some._

In addition to improvements to our coverage of the GNOME libraries, this
release introduces preliminary coverage of the Cairo Graphics drawing library,
along with the infrastructure to make it work within a GTK program.

Drawing with Cairo
------------------

![Example](doc/examples/cairo/ExampleCairoDrawingBlends.png)

The trusty Cairo context, traditionally declared as a variable named `cr` in
code, is mapped as class
[`Context`](doc/api/4.1/org/freedesktop/cairo/Context.html). Various Cairo types
such as different surfaces and patterns are mapped as an abstract base class
(`Surface`, `Pattern`) along with various concrete subclasses (`ImageSurface`,
`XlibSurface`, and `SolidPattern`, `RadialPattern`, etc). Error checking is
implicit: the library status is checked internally after each operation and an
Exception thrown if there is a failure.

* `org.freedesktop.cairo.Plumbing`
* **`org.freedesktop.cairo.Context`**
* **`org.freedesktop.cairo.ImageSurface`**
* **`org.freedesktop.cairo.XlibSurface`**
* **`org.freedesktop.cairo.SVGSurface`**
* **`org.freedesktop.cairo.SolidPattern`**
* **`org.freedesktop.cairo.LinearPattern`**
* **`org.freedesktop.cairo.RadialPattern`**
* **`org.freedesktop.cairo.SurfacePattern`**
* **`org.freedesktop.cairo.Operator`**
* **`org.freedesktop.cairo.Format`**
* `org.freedesktop.cairo.Status`
* `org.freedesktop.bindings.FatalError`
* **`org.freedesktop.cairo.FatalError`**

The gateway to custom Widgets is the
[`EXPOSE_EVENT`](doc/api/4.1/org/gnome/gtk/Widget.EXPOSE_EVENT.html) signal; this
is where you can transition from the GDK `Window` to a Cairo `Context` and
then begin drawing.

* **`org.gnome.gtk.Widget.EXPOSE_EVENT`**
* **`org.gnome.gdk.EventExpose`**
* **`org.gnome.gdk.Rectangle`**
* **`org.gnome.gdk.EventCrossing`**
* **`org.gnome.gdk.CrossingMode`**
* **`org.gnome.gdk.NotifyType`**
* **`org.gnome.gtk.Image`**

Thanks in particular to Carl Worth for having reviewed our API and having
helped test our implementation.

New coverage and continuing improvement
---------------------------------------

The single option choice buttons in GTK are called `RadioButton`s and have now
been exposed. When using them you need to indicate the other buttons they are
sharing a mutually exclusive relationship with, and this is expressed by
adding them to a `RadioButtonGroup`.

![RadioButton](doc/api/4.1/org/gnome/gtk/RadioButton.png)


* **`org.gnome.gtk.RadioButton`**
* **`org.gnome.gtk.RadioButtonGroup`**

The usual steady refinements to our coverage of the GtkTreeView API continue.
There's a new `DataColumn` type for Stock icons, and TreeModelSort is now
implemented.

* **`org.gnome.gtk.TreeModelSort`**
* **`org.gnome.gtk.DataColumnPixbuf`**
* **`org.gnome.gtk.DataColumnStock`**
* **`org.gnome.gtk.CellRendererPixbuf`**
* **`org.gnome.gtk.TreeView`**
* **`org.gnome.gtk.TreeModel`**
* **`org.gnome.gtk.ListStore`**
* **`org.gnome.gtk.TreeStore`**

and minor changes to various other miscellaneous classes:

* **`org.freedesktop.bindings.Environment`**
* **`org.gnome.gtk.Expander`**
* **`org.gnome.gtk.Frame`**
* **`org.gnome.gtk.Paned`**
* **`org.gnome.gtk.HPaned`**
* **`org.gnome.gtk.VPaned`**
* **`org.gnome.gtk.Widget`**
* **`org.gnome.gtk.ProgressBar`**
* **`org.gnome.gtk.Action`**

Considerable internal optimizations have been done, especially relating to
ensuring proper memory management, with notable refinements to make use  of
"caller owns return" information available in the `.defs` data. This fixes a
number of bugs. Thanks to Vreixo Formoso for having driven these improvements.

Error handling has been improved for GLib based libraries as well. If an
`ERROR` or `CRITICAL` is emitted, our internals will trap this and throw an
exception instead, allowing the developer to see a Java stack trace leading
them to the point in their code where they caused the problem.

* **`org.gnome.glib.FatalError`**

Internationalization support
----------------------------

java-gnome now has full support for the GNOME translation and localization
infrastructure, including the standard `_("Hello")` idiom for marking strings
for extraction and translation. There's a fairly detailed explanation in the
[`Internationalization`](doc/api/4.1/org/freedesktop/bindings/Internationalization.html)
utility class.

* **`org.freedesktop.bindings.Internationalization`**

Build changes
-------------

Note that as was advertised as forthcoming some time ago, Java 1.5 is now the
minimum language level required of your tool chain and Java virtual machine in
order to build and use the java-gnome library.

Thanks to Colin Walters, Manu Mahajan, Thomas Girard, Rob Taylor, and Serkan
Kaba for contributing improvements allowing the library to build in more
environments and for their work on packages for their distributions.

The [download](get/) page has updated instructions for getting either binary
packages or checking out the source code.

Documentation, examples, and testing
------------------------------------

Refinements to the API documentation continue across the board, notably
improving consistency. A large number of javadoc warnings have also been
cleaned up.

While not a full blown tutorial, the number of fully explained examples is
growing. There are examples for box packing and signal connection, presenting
tabular data, and basic drawing, among others. See the description page in the
[`doc/examples/`](doc/examples/START.html) section.

This code, together with the not inconsiderable number of unit tests and the
code for generating snapshots of Widgets and Windows means that a large
portion of the public API is tested within the library itself. The number of
non-trivial applications making use of java-gnome is starting to grow, which
are likewise providing for ongoing validation of the codebase.

Summary
-------

You can see the full changes accompanying a release by grabbing a copy of the
sources and running:

    $ bzr diff -r tag:v4.0.6..tag:v4.0.7

Looking ahead
-------------

It's probably unwise to predict what will be in future releases. The challenge
for anyone contributing is that they need to understand _what_ something does,
_when_ to use it (and more to the point, when not to!), and be able to
_explain_ it to others. This needs neither prior experience developing with
GNOME or guru level Java knowledge, but a certain willingness to dig into
details _is_ necessary.

That said, I imagine we'll likely see further Cairo improvements as people
start to use it in anger. It shouldn't take too long until the bulk of the
functionality needed for most uses is present in java-gnome. In particular,
forthcoming coverage of the Pango text drawing library will round things out
nicely.

There are a number of other major feature improvements we'd like to see in
java-gnome. Conceptual and design work is ongoing on for bindings of GConf,
GStreamer, and even support for applets. Within GTK, there have been a number
of requests made for various things to be exposed, for example, the powerful
GtkTextView / GtkTextBuffer text display and editing capability. Some of these
have preliminary implementations; whether or not any given piece of work is
acceptable in time for any particular future release will remain to be seen
and depends on the willingness of clients to fund us to review and test such
work.

In the mean time, people are happily _using_ the library to develop rich user
interfaces, which is, of course, the whole point. We're always pleased to
welcome new faces to the community around the project. If you want to learn
more, stop by `#java-gnome` and say hello!

AfC

<a name="debian" id="1208324712" title="Packaged in Debian Linux"></a>

Debian packages
===============

The java-gnome bindings suite is now available in [Debian
Linux](http://www.debian.org/) as

 * `libjava-gnome-java`

If you need help installing it, see the the Debian page in the
[download](/get/debian.php) section here.

Compliments to Manu Mahajan for having done the research to develop the Debian
package, and thanks to Thomas Girard for having refined Manu's start and for
having seen through the process of getting the package uploaded.

AfC

<a name="cairo" id="1207377494" title="Cairo"></a>

Cairo support
=============

Adding coverage of the Cairo Graphics library is a feature we've been working
on for about 6 months now, and during the 4.0.7 development cycle we've been
able to land it in java-gnome!

Cairo is a huge library, of course, but we've put enough coverage in place to
ensure that things are working. Cairo has lots of convenience functions and
tons of obscure uses; no surprise (and no apology) that there's still lots
that will need doing. If you want to help make sure it has what you need, then
grab '`mainline`' and see `org.freedesktop.cairo.`[`Context`][Context].

Huge thanks go out to Behdad Esfahbod and Carl Worth; Behdad was really
critical in explaining some basic Cairo concepts to me started when we were
working together at the GNOME Summit back in October in Boston, and during March
at the GTK hackfest in Berlin, Carl Worth was awesome for having checked our 
preliminary APIs and for having helped sorted us out as we were working our
way through create some examples.

[Context]: doc/api/4.1/org/freedesktop/cairo/Context.html

AfC

<a name="4.0.6" id="1202791317" title="Missing"></a>

java-gnome 4.0.6 (12 Feb 2008)
==============================

_Finding the missing methods._

Most of our effort recently has simply been fleshing out areas of the
public API. The focus for this work as been getting the coverage needed to
allow us to port some of our in-house applications to java-gnome 4.0. It's not
especially glamorous -- if anything it has been tedious as hell -- but the
result has been a large body of improvements to java-gnome as a whole which
we're pleased to release as java-gnome 4.0.6

The bulk of this development took place on the '`missing`' branch, so named
because that's where I was working on what was missing `:)`.

Continuing Improvement
----------------------

Notable public changes include coverage additions to enable key stroke and
mouse button handling:

* **`org.gnome.gdk.Keyval`**
* **`org.gnome.gdk.KeypadKeyval`**
* **`org.gnome.gdk.EventKey`**
* **`org.gnome.gdk.ModifierType`**
* **`org.gnome.gdk.MouseButton`**
* **`org.gnome.gdk.EventButton`**

Rather than exposing the `int` keyvals that bubble up out of the X server, we
have wrapped these as  constants of type
[Keyval](doc/api/4.1/org/gnome/gdk/Keyval.html) (thereby being consistent with the
rest of java-gnome in our working to the strengths of Java as a strongly-typed
language; MouseButton was created for the same reason, helping developers
understand just what on earth mouse button
"[1](doc/api/4.1/org/gnome/gdk/MouseButton#LEFT)" is, anyway). Along with
[ModifierType](doc/api/4.1/org/gnome/gdk/ModifierType.html), this gives enough to
deal with the `KEY_PRESS_EVENT` and `KEY_RELEASE_EVENT` signals when the
developer wishes to deal with key strokes.

We've finally gotten around to providing proper coverage of the box packing
model which underlies every aspect of how GTK presents user interfaces. To
understand the size-request/size-allocation process, you might start with
Widget's
[`setSizeRequest()`](doc/api/4.1/org/gnome/gtk/Widget.html#setSizeRequest\(int,%20int\)).

* **`org.gnome.gtk.Requisition`**
* **`org.gnome.gtk.Allocation`**
* **`org.gnome.gtk.Widget`**
* **`org.gnome.gtk.Container`**
* **`org.gnome.gtk.SizeGroup`**
* **`org.gnome.gtk.SizeGroupMode`**

We've also added coverage for SizeGroup, which, when used in concert with
nested VBoxes and HBoxes, can work wonderful magic and is often far better
than messing around with Table when doing complex layouts.

After dithering for several releases, we've settled on how we're going to deal
with ComboBox and family. The underlying GtkComboBox presents something of a
nightmare as it is really two classes in one with more-or-less incompatible
APIs. So, not surprisingly, we've presented it as two sets of classes, with the
text-only convenience API spliced out of ComboBox and ComboBoxEntry into
TextComboBox and TextComboBoxEntry respectively.

* **`org.gnome.gtk.ComboBox`**
* **`org.gnome.gtk.ComboBoxEntry`**
* **`org.gnome.gtk.TextComboBox`**
* **`org.gnome.gtk.TextComboBoxEntry`**

We've added a few new features in our coverage of GTK's TreeView API, and many
other classes involved have also seen improvements. The persistent reference to
a row provided by TreeRowReference is now available as is model type
TreeModelFilter.

* **`org.gnome.gtk.TreeModel`**
* **`org.gnome.gtk.ListStore`**
* **`org.gnome.gtk.TreeIter`**
* **`org.gnome.gtk.TreePath`**
* **`org.gnome.gtk.TreeView`**
* **`org.gnome.gtk.TreeViewColumn`**
* **`org.gnome.gtk.TreeViewColumnSizing`**
* **`org.gnome.gtk.TreeModelFilter`**
* **`org.gnome.gtk.TreeRowReference`**
* **`org.gnome.gtk.CellLayout`**
* **`org.gnome.gtk.CellRenderer`**
* **`org.gnome.gtk.CellRendererPixbuf`**
* **`org.gnome.gtk.CellRendererText`**

Support for the actual filtering in TreeModelFilter is notable for having been
quite tricky. The underlying C library use a function pointers rather than a
GObject signal emission, and we don't have any mechanism to handle that. We
_do_, however, have a fantastic capability to marshal signals, so we dealt with
the problem by creating a custom signal and then passing a function which emits
it when the TreeModelFilter wants to ask the developer whether to include a row
or not.

The new classes include support for TreeModel columns storing `long` data as
well as setting properties of that type:

* `org.gnome.glib.Value`
* `org.gnome.gtk.Value`
* `org.gnome.gtk.Object`
* **`org.gnome.gtk.DataColumnLong`**

It should also be noted that most of the methods taking a TreeViewColumn have
been converted to taking an argument of type CellLayout (an interface
implemented by TreeViewColumn). This has no change to how you use our TreeView
API, but was necessary to support ComboBox properly.

Finally lots and lots of minor additions to both public APIs and internals
deeper down in the GDK part of the toolkit:

* **`org.gnome.gdk.Color`**
* **`org.gnome.gdk.Cursor`**
* **`org.gnome.gdk.CursorType`**
* **`org.gnome.gdk.Pixbuf`**
* **`org.gnome.gdk.Window`**
* **`org.gnome.gtk.AboutDialog`**
* **`org.gnome.gtk.Action`**
* **`org.gnome.gtk.Box`**
* **`org.gnome.gtk.Button`**
* **`org.gnome.gtk.Calendar`**
* **`org.gnome.gtk.Editable`**
* **`org.gnome.gtk.Entry`**
* **`org.gnome.gtk.EventBox`**
* **`org.gnome.gtk.Image`**
* **`org.gnome.gtk.IconSize`**
* **`org.gnome.gtk.ImageMenuItem`**
* **`org.gnome.gtk.Label`**
* **`org.gnome.gtk.ScrolledWindow`**
* **`org.gnome.gtk.Statusbar`**
* **`org.gnome.gtk.Table`**
* **`org.gnome.gtk.Window`**

As ever, you can see the full changes accompanying a release by
grabbing a copy of the sources and running:

    $ bzr diff -r tag:v4.0.5..tag:v4.0.6

Documentation
-------------

We've always had HTML JavaDoc for the current stable release at
[`doc/api/`](doc/api/4.1/overview-summary.html) on the java-gnome website.
We're going to change that a bit, though. As fixes to the explanatory
documentation happen quite frequently to classes and methods all over the
place, we've decided to generate the JavaDoc from '`mainline`' periodically and
upload that instead. This means that there will, of course, be descriptions of
some methods which aren't yet available in a released version of the library,
but they will clearly identifiable by virtue of having a `@since` tag showing
a version number greater than the most recent release.

The idea of having up-to-date illustrations of the various Widgets has proved
popular, and we've continued to update the suite of snapshots. Doing that 
is _also_ tedious, but it does provide a good opportunity to test APIs we are
exposing especially where unit tests are less suitable.

![Statusbar](doc/api/4.1/org/gnome/gtk/Statusbar.png)

Looking ahead
-------------

Almost as complex as the TreeView/TreeModel API are GTK's powerful
TextView/TextModel classes, collectively a Widget used to display and edit
large text documents. Working out the java-gnome coverage for TextView will
take a fair bit of consideration, but TreeView provides a road map, and, as
with the coverage in 4.0.5 and 4.0.6 (which was driven largely by existing
applications we were porting), we have some significant uses of GtkTextView
which will guide us on our way.

The next release will also feature significant work outside of GTK; we should
be in a position to merge our coverage of the excellent Cairo drawing library
soon, and likewise we have tentative work in place letting people store
configuration and settings data in GConf. Both the '`cairo`' and '`gconf`'
branches need more QA and documentation work, but they're looking good and will
definitely be featured in java-gnome 4.0.7.

AfC

<a name="4.0.5" id="1196060444" title="TreeView is here"></a>

java-gnome 4.0.5 (26 Nov 2007)
==============================

_TreeView is here!_

It's always a great feeling when you bag a milestone, and with this release we
have reached a major goal on our way to having outstanding Java bindings for
the GNOME platform: coverage of GTK's powerful yet complex TreeView &
TreeModel API.

TreeView
--------

TreeViews are a central part of almost every application. GUIs use lists for
all sorts of things, and so a significant goal was to make coding TreeViews
and their backing TreeModels as straight forward as possible.

The most challenging and complex part was to design the Java side API, which
was no small matter. As a native library, the GtkTreeView API is complex and
very much written with programming in the C language in mind, and as such our
algorithmic mapping of the underlying libraries into Java doesn't entirely
fit. Long experience with the TreeViews in the previous bindings had made it
clear just how nasty to use the API could be, and so the hardest part of the
work was to come up with a mapping and a usage pattern that would be both
faithful to GTK _and_ be sensible to use.

The other significant challenge was to document the work effectively. Our Java
side API documentation is a major feature of java-gnome, and merely exposing
classes and methods is not sufficient; they need to be clearly explained in
our JavaDoc as well. Introduced in this release, then, are:

* **`org.gnome.gtk.TreeView`**
* **`org.gnome.gtk.TreeViewColumn`**
* **`org.gnome.gtk.CellRenderer`**
* **`org.gnome.gtk.CellRendererText`**
* **`org.gnome.gtk.CellRendererPixbuf`**
* **`org.gnome.gtk.TreeSelection`**
* **`org.gnome.gtk.SelectionMode`**
* **`org.gnome.gtk.TreeModel`**
* **`org.gnome.gtk.ListStore`**
* **`org.gnome.gtk.TreeIter`**
* **`org.gnome.gtk.TreePath`**
* **`org.gnome.gtk.Alignment`**

along with numerous test cases in our unit test suite, and several
comprehensively worked [examples][example-treeview].

This was a monster patch, and the culmination of not just three months direct
effort, but also where we've been heading since we first started the
re-engineering of Java bindings for GNOME. Although largely written by Andrew
Cowie, a _significant_ contribution was made by Srichand Pendyala who not only
exhaustively evaluated the design but also threw in some serious chunks of
code. The work benefited from comprehensive input from Peter Miller on the
modelling and design, and the comments of Bryan Clark, Owen Taylor, and Hanna
Wallach were all really positive and helped us know that we'd gone in the
right direction. Finally, thanks to Behdad Esfahbod and the GNOME Foundation
who made it possible for us to meet in Boston at the GNOME Summit and so
accomplish much of the final pulling together of this branch.

Continuing Improvement
----------------------

Meanwhile, steady work continues on to the fundamental base classes, with a
whack of additional signals and methods on Widget and especially Window, along
with expansion of coverage in numerous other classes:

* **`org.gnome.gtk.Widget`**
* **`org.gnome.gtk.Window`**
* **`org.gnome.gtk.StateType`**
* **`org.gnome.gdk.Color`**
* **`org.gnome.gdk.VisibilityState`**
* **`org.gnome.gdk.EventVisibility`**

What else? We've begun to get the basics of image handling in place,

* **`org.gnome.gdk.Pixbuf`**
* **`org.gnome.gtk.Image`**
* **`org.gnome.gtk.ImageMenuItem`**

One nice piece of contributed work came from Vreixo Formoso and Thomas Schmitz
with coverage of the Dialog Window functionality in GTK. It took a bit of
doing to map the `int` response codes used by GTK into something suitably
strongly-typed, but all good:

* **`org.gnome.gtk.Dialog`**
* **`org.gnome.gtk.ResponseType`**
* **`org.gnome.gtk.MessageDialog`**
* **`org.gnome.gtk.MessageType`**
* **`org.gnome.gtk.ButtonsType`**
* **`org.gnome.gtk.FileChooserDialog`**

And finally, minor improvements to all sorts of stuff:

* **`org.gnome.gtk.Container`**
* **`org.gnome.gtk.Button`**
* **`org.gnome.gtk.Box`**
* **`org.gnome.gtk.Bin`**
* **`org.gnome.gtk.Menu`**

notably from new contributor Mario Torre. Awesome!

For further details you can always grab a copy of the sources and run

    $ bzr diff -r tag:v4.0.4..tag:v4.0.5

to see the complete code delta.

Screenshots
-----------

<img src="doc/api/4.1/org/gnome/gtk/Window.png" alt="" style="float: right;
text-align: right; padding-left: 15px;" /> For fun we built in a capability to
create demonstrations to be captured as screenshots to illustrate various
things. It doesn't get more basic than the example on the
[`Window`][api-Window] documentation page, but it's a nice touch. `:)` We've
screenshots for a number of Dialog classes and one for the TreeView page. I
imagine we'll build up a nice library of images in the next few months (yes,
dear contributors, you can add snapshots to the list of things we'll be
expecting along with well written documentation and unit tests when submitting
additions to the public API).

Building and requirements
-------------------------

The library now depends on GTK >= 2.12. Those packaging java-gnome for their
distributions please take note.

Looking ahead
-------------

Continuing to expand the coverage levels in the classes already exposed will
continue to dominate our attention; there's still a long way to go but we're
pleased with the progress we've made so far; you can definitely build real
applications with java-gnome now.

The next release also ought to include preliminary coverage of GConf and
Cairo. Doing each justice will again take a serious amount of work, but will
continue to grow the fun things you can do with java-gnome.

AfC

[example-treeview]: http://research.operationaldynamics.com/bzr/java-gnome/mainline/doc/examples/treeview/ExampleTrailHeads.java
[api-window]: doc/api/4.1/org/gnome/gtk/Window.html

<a name="arch" id="1195662600" title="Packaged in Arch Linux"></a>

Arch packages
=============

java-gnome now builds on [Arch Linux](http://www.archlinux.org/) and is
packaged there. Thanks to Timm Preetz for having done the legwork for this!

AfC

<a name="treeview" id="1194509646" title="Trees and Branches"></a>

Trees and Branches
==================

Lots of ongoing work.

The major focus over the last three months has been on the '`treeview`' branch.
Andrew Cowie, backed by Srichand Pendyala, has made awesome progress in working
out the engineering necessary to support GTK's powerful but complex
TreeView/TreeModel system and designing an appropriate public API by which
java-gnome can present it. This has taken most of September and October but is
working really well at this point. This branch should be ready for merging to
'`mainline`' in the next week or two; just need to bring the documentation up
to release quality and we're set.

Vreixo Formoso and Thomas Schmitz have done some great work to expose the
Dialog family of classes. This work has exposed a few bugs in our internals,
but thanks to some expert help from Owen Taylor we should have that sorted out
soon. Work to fix that is taking place on the '`delete`' branch.

Andrew Cowie, helped by Behdad Esfahbod, has begun work on a binding of the
Cairo library. This work is still experimental at this stage, but we'll merge
this '`cairo`' branch in so we can to at least set the tone for what will be an
exciting addition to java-gnome over the coming months.

Once we land these branches we should be able to polish things up for the
release of 4.0.5, hopefully by the end of November.

AfC

<a name="4.0.4" id="1190798858" title="Coverage increasing"></a>

java-gnome 4.0.4 (26 Sep 2007)
==============================

_Coverage increasing!_

Most of our work continues to be on infrastructure and architecture, improving
the code generator that outputs the translation Java layer and JNI C layer
which allow bindings hackers to reach the underlying native libraries.
Nevertheless, there have been a number of publicly visible improvements across
the board, so we wanted to push out a release highlighting these contributions!

Documentation improvements
--------------------------

Continuing our effort to have extensive developer friendly tutorial style
documentation, there have been major additions to a number of existing classes.
Of particular note is the Window class, containing the various utility methods
used to ask the window manager to do things for you (we've also started
exposing some of the deeper parts of the GTK toolkit, though only a few things
that were immediately related to window management).

* **`org.gnome.gtk.Window`**
* **`org.gnome.gtk.Widget`**
* **`org.gnome.gdk.Screen`**
* **`org.gnome.gdk.Window`**

While the topic of thread safety was discussed at considerable length in the
last release, we have added some of the more relevant information to the code
documentation to reinforce its importance.

* **`org.gnome.gdk.Gdk`**'s `lock`

New coverage
------------

Numerous people have been hard at work developing new coverage. The standards
for accepting patches which expose public API are high, so it's awesome to see
bundles accepted for being merged to `mainline` from new contributors Thomas
Schmitz, Wouter Bolsterlee, and Nat Pryce.

The infrastructure for a number of areas important to supporting applications
including Menus, Toolbars, and Actions has been put in place:

* **`org.gnome.gtk.Toolbar`**
* **`org.gnome.gtk.ToolItem`**
* **`org.gnome.gtk.ToolButton`**
* **`org.gnome.gtk.ToggleToolButton`**
* **`org.gnome.gtk.MenuToolButton`**
* **`org.gnome.gtk.SeparatorToolItem`**
* **`org.gnome.gtk.MenuItem`**
* **`org.gnome.gtk.CheckMenuItem`**
* **`org.gnome.gtk.SeparatorMenuItem`**
* **`org.gnome.gtk.Action`**
* **`org.gnome.gtk.ActionGroup`**

A number of Container related Widgets have been added, though coverage is
preliminary. There have, of course, also been a number of minor improvements in
other existing classes, including:

* **`org.gnome.gtk.Notebook`**
* **`org.gnome.gtk.ScrolledWindow`**
* **`org.gnome.gtk.Misc`**
* **`org.gnome.gtk.ButtonBox`**
* **`org.gnome.gtk.HButtonBox`**
* **`org.gnome.gtk.VButtonBox`**
* **`org.gnome.gtk.Label`**

and even:

* **`org.gnome.gtk.StatusIcon`**

Along with these goes a variety of miscellaneous constants and wrappers around
the stock item identifiers:

* **`org.gnome.gtk.Alignment`**
* **`org.gnome.gtk.ImageType`**
* **`org.gnome.gtk.ButtonBoxStyle`**
* **`org.gnome.gtk.Justification`**
* **`org.gnome.gtk.Orientation`**
* **`org.gnome.gtk.Stock`**
* **`org.gnome.gdk.Gravity`**

Internals
---------

Vreixo Formoso carried out an important refactoring to the type database and
Generator family of classes in the code generator, with the result that more of
the array passing and out-parameter cases are now being handled correctly. This
kind of work is usually thankless and taken for granted, but it's hugely
appreciated!

The real gains are in internal quality. A number of serious bugs and
limitations have been overcome (`Glade` is working again, for example). The
generated code now guards against improper use (you can't pass a `null` pointer
unless it's allowed by the underlying library). Related to this is handling of
"`GError`" -- Java side, bindings hackers will get `GlibException` which they
can then re-throw as an appropriate Java Exception, say `FileNotFoundException`
in the case of not being able to open a file.

This all goes along with numerous build system fixes by Srichand Pendyala to
make for an increasingly robust project. Thanks guys!

Looking ahead
-------------

As mentioned above, we have mostly been focused on areas other than public API,
but it is expanding steadily. The hard work on infrastructure, however, is
starting to pay off, and the next release should include coverage of TreeView,
GTK's powerful but complex list Widget.

AfC

<a name="gentoo" id="1187677072" title="Packaged in Gentoo Linux"></a>

Gentoo packages
===============

The java-gnome bindings suite is now available in [Gentoo
Linux](http://www.gentoo.org/). An `.ebuild` for 4.0.3 has been merged to
Portage in

 * `dev-java/java-gnome`

If you need help installing it, see the the Gentoo page in the 
[download](/get/gentoo.php) section here.

Thanks to the people who pushed this through: Xerces MC for having submitted an
initial `.ebuild`, Petteri R&#228;ty (Gentoo dev and Java team lead) for having
fixed it up, and Christoph Brill for testing.

AfC

<a name="4.0.3" id="1185883828" title="Code generator landed"></a>

java-gnome 4.0.3 (31 Jul 2007)
==============================

_The code generator has landed!_


Work has been underway for several months to develop the next stage of the new
java-gnome: the code generator that will output the tedious translation and
native layers that allow us to glue our public API to the native GNOME
libraries. With this release we're pleased to announce that the code generator
is a reality!

Generated translation and JNI layers
------------------------------------

The primary goal of the java-gnome 4.0 re-engineering effort has been to switch
to an architecture whereby we could _generate_ the bulk of the machinery
necessary to take make native calls into the GNOME libraries from Java.

Extensive prototyping was done to establish the detailed design and to validate
the architecture we had developed. Releases 4.0.0 through 4.0.2 contained this
work along with mockups of the "translation" layer (the Java code that
downshifts from our Proxy objects to primitives suitable to pass over the JNI
boundary, along with the `native` declarations necessary in order to call
methods actually written in C) and of the "JNI layer" (the C code that
implements the methods declared in the translation layer which in turn coverts
parameters into GLib terms and then makes the actual function call into the
appropriate GNOME library).

With a solid foundation proving that our design was sound, we subsequently
began the long effort to implement a code generator which would output these
Java and C layers, allowing us to replace the `mockup/` directory and at last
leave behind the shackles of entirely hand written bindings. Over the past five
months, the java-gnome hackers have been steadily working on the 'codegen'
branch. The nature of the challenge meant that we had to have most of the code
in place before any of it would be useful -- never an enviable task to be
working on. Thanks to the hard work of Andrew Cowie, Vreixo Formoso Lopes, and
Srichand Pendyala, we reached the point  where the output Java code compiled in
May, and the output C code successfully compiled in by the end of June.
Tremendous.

We've been bug hunting and refining since then, pushing towards the point where
we could merge back to 'mainline', at last replacing the hand written mockup
code. We are today pleased to announce the culmination of that work with the
release of java-gnome 4.0.3.

_This post on the [development of the java-gnome code generator][codegen]
contains further details should you be interested; the file
[5a-Architecture.txt][architecture] in the `doc/design/` directory of the
source code explains the rationale and origin of the engineering design._

New coverage
------------

Although our focus has evidently been on getting the generator into working
order, there have nevertheless been a few minor coverage additions along the
way:

* **`org.gnome.gtk.Entry`**
* **`org.gnome.gtk.Separator`**
* **`org.gnome.gtk.HSeparator`**
* **`org.gnome.gtk.VSeparator`**
* **`org.gnome.gtk.Frame`**
* **`org.gnome.gtk.Calendar`**
* **`org.gnome.gtk.Notebook`**
* **`org.gnome.gtk.Image`**
* **`org.gnome.gtk.Menu`**
* **`org.gnome.gtk.MenuBar`**
* **`org.gnome.gtk.MenuItem`**
* **`org.gnome.gtk.ToggleButton`**
* **`org.gnome.gtk.CheckButton`**

which are largely to the credit of Sebastian Mancke for having submitted them
and Srichand Pendyala for having fixed up their JavaDoc. There have also been
steady improvements to a number of other classes; notably further signals and
utility methods exposed in:

* **`org.gnome.gtk.Widget`**
* **`org.gnome.gtk.Container`**

along with some preliminary coverage of the lower level GDK event machinery:

* **`org.gnome.gdk.Event`**
* **`org.gnome.gdk.EventType`**

As is reasonable given our focus on writing the actual translation and JNI
layer generators, most of these present only one or two methods from the
underlying native class. Coverage will steadily improve as people contribute
their knowledge and experience in documentation form.

_People upgrading from 4.0.2 will actually notice that there are stubs for
**all** of the public API classes; this was necessary to make the generated
code compile. Most of these are empty as yet._

GList and friends
-----------------

`GList` and `GSList` are the native GLib data structures used to represent
lists and are the return type from quite a few methods across GTK. Vreixo
Formoso Lopes worked out how to handle and working our wrap/unwrap functions
for us to use in the JNI layer made a significant contribution to reducing our
blacklisted method count.

Flags
-----

We've also worked out handling of native types that, while type defined as
enums, are actually bit fields. These are used occasionally in GTK to express
options; two examples now exposed are:

* **`org.gnome.gtk.CalendarDisplayOptions`**
* **`org.gnome.gdk.WindowState`**

Flags subclasses present an `or()` function allowing you to combine individual
Flags constants into a new composite Flags object. Yes it's a lot of machinery
to do a logical `|`, but being strongly typed is a hallmark of java-gnome.

Build improvements
------------------

No release would be complete without mentioning that the code builds on more
systems than it did before! Thanks to Maciej Piechotka and Srichand Pendyala
for fixing problems resulting from Debian and Ubuntu strangeness.

The build internally now uses an optimized script that takes into account that
even though the code generator may have run the translation and jni files may
not actually be different. This was causing problems as Make only looks at file
modification time. Instead, `build/faster` (great name, huh?) will only rebuild
a target when source contents have changed. This was necessary for bindings
hackers working in Eclipse; every time a file was saved Eclipse would merrily
spawn off an auto-build which sooner or later would block the IDE UI. Yuk.
Addressing this has also resulted in a faster build for everyone; all good.

The internal build script _should_ be transparent; you still run `./configure`
and `make` as before. If you experience problems let us know.

Thread safety
-------------

In addition to the code generator, java-gnome 4.0.3 incorporates a
comprehensive thread safety strategy. 

None of the major Java graphical toolkits out there let you make GUI calls from
threads other than the "main" one; they're all single threaded. Even if all you
want to do is a quick worker thread to carry out some input validation in the
background after the user presses "OK", you have to jump through horrific
contortions to do so safely, resulting in cumbersome, clunky code.

By contrast, the new Java bindings of GTK presented in java-gnome are
**transparently thread safe**, the first and only graphical user interface
widget toolkit for Java to be so! We integrate properly with the underlying GDK
thread lock and as a result you can safely make calls to various GTK methods
from worker threads! This has been a long sought after goal and we hope a
significant contribution to helping developers write elegant code.

Every call made to the native libraries is protected by entering the "GDK lock"
[that's `gdk_threads_enter/leave()` for those familiar with the C side of
things]. The lock used is actually a Java side `synchronized` monitor and
therefore reentrant; nested calls all behave properly. When in a signal handler
callback the GDK lock is already held (you're "in" the main loop when a
callback happens), but since it just works transparently you don't need to
worry about it. If you do find a need to take the lock into account explicitly
in your own code, see `Gdk.lock` in `org.gnome.gdk`.

_It is worth noting that we have been warned that there are certain to be
places in the underlying libraries that do not yet live up to the requirements
of the GDK threads model -- thus we will likely end up tripping over such
things as we slowly add API coverage. We regard such inevitable instances as an
opportunity to help contribute to improving the stability of the underlying
libraries and will actively work with their maintainers to identify and resolve
such issues. Nevertheless, in testing thus far our multi-threaded use of GTK
has been rock solid. See these posts on [GTK thread
"awareness"][gtk-thread-awareness] and [java-gnome's thread
strategy][java-gnome-thread] if you wish further details on our approach to the
thread safety question._

Thanks in particular to Owen Taylor for having helped us navigate these waters!

Looking ahead
-------------

Taken together, these innovations represent the culmination of an immense
amount of work towards realizing java-gnome as a viable platform for GTK and
GNOME development.

Obviously with the generated translation layer in place the opportunity at last
exists to start dramatically improving our coverage level, and we welcome
contributions to this end. Prospective hackers are cautioned, however, that
simply wrapping generated methods is insufficient -- public API will only be
added when it is clearly documented and meets the [approachability][objectives]
criterion.

There are still areas where the code generator needs to be improved; we need to
improve our handling for arrays, lists, and out-parameters -- there are
numerous permutations with all sorts of ugly corner cases.

Now that Free Java with support for generics is becoming widely available,
4.0.3 will be the last release holding the language level to Java 1.4; starting
the next cycle 1.5 will be the minimum language requirement and we will be
leveraging generics and other 1.5 features from here on.

Most importantly, the primary focus of the next few months will be developing a
quality binding for the backbone of many applications: the `TreeView` Widget
and the underlying `TreeModel` which powers it. The APIs in the native library
is hideously complicated and has long been the source of confusion and pain for
developers in C; it has long been a major goal amongst the java-gnome hackers
to present a public API with as friendly and usable an interface as possible.
It'll be a good challenge.

AfC

[architecture]: /doc/design/5a-Architecture.html
[codegen]: http://research.operationaldynamics.com/blogs/andrew/software/java-gnome/code-generator-cometh.html
[objectives]: /objectives.php
[gtk-thread-awareness]: http://research.operationaldynamics.com/blogs/andrew/software/gnome-desktop/gtk-thread-awareness.html
[java-gnome-thread]: http://research.operationaldynamics.com/blogs/andrew/software/java-gnome/thread-safety-for-java.html


<a name="4.0.2" id="1171283501" title="End of the beginning"></a>

java-gnome 4.0.2 (12 Feb 2007)
==============================

_The End of the Beginning!_

Major bugfixes and refactorings
-------------------------------

Setting and getting properties on GObjects requires some tricky manoeuvring.
We implemented the code to do this early on, and it looked like our general
mechanism for getting Proxy instances for arbitrary pointers was working  fine
for properties. It turns out, however, that when you call `g_type_name()` on a
GValue _containing_ a GObject, it returns the name of the type that was listed
when the property specification was registered, rather than saying it is a
GValue (as you might expect) or what the object actually is (that you might
_also_ reasonably expect).

This led to all kinds of nastiness since the type name was what we were using
in our `instanceFor()` mechanism to discriminate (on the Java side) what kind
of Proxy subclass to create. The example we tripped over was asking for the
parent property of a Button packed into a VBox. What `g_type_name()` told us
was "GtkContainer", not "GtkVBox"! And that was a big problem, because
Container is abstract, and besides, we want to instantiate a concrete VBox
Proxy, not a Container one!

Solving the problem involved major changes to:

 * **`org.gnome.glib.Value`**
 * **`org.gnome.glib.Object`**
 * `org.gnome.glib.Plumbing`
 * `org.gnome.glib.GValue`
 * `org.gnome.glib.GObject`

The solution basically boiled down to having two separate code paths: one
named `objectFor()` [a greatly simplified version of the previous
`instanceFor()`] which returns normal Proxy objects for GObject subclasses
(Buttons and Labels and whatnot), and a new code path available via
`valueFor()` to specifically return our GValue Proxy for the cases where we
know we're getting a GValue back. Since that occurs in limited and known
circumstances only (ie, when we're getting properties) it's no problem to know
which to use when.

Thanks to Davyd Madeley for extensive debugging assistance, and credit to
Manish Singh, James Henstridge, and Malcolm Tredinnick for having analyzed the
root cause issue and having clarified that two code paths would indeed be
necessary.

As often happens when you kick a stone loose, we were able to do a number of
refactorings to clean things up. This eventually led to the realization (ok,
epiphany) that our treatment of the GValue mechanism was needlessly complex.
Toss. We no longer have individual Value subclasses for each different
fundamental type, but rather just leave them as opaque references:

 * <span style="text-decoration: line-through;">
   `org.gnome.glib.Fundamental`
 * <span style="text-decoration: line-through;">
   `org.gnome.glib.StringValue`  
 * <span style="text-decoration: line-through;">
   `org.gnome.glib.BooleanValue`
 * <span style="text-decoration: line-through;">
   `org.gnome.glib.IntegerValue`
 * <span style="text-decoration: line-through;">
   `org.gnome.glib.EnumValue`
 * <span style="text-decoration: line-through;">
   `org.gnome.glib.ObjectValue`
 * **`org.gnome.glib.Value`**
 * `org.gnome.glib.Plumbing`

This allowed a further simplification of the `valueFor()` mechanism and even
more smashing about in Plumbing with a chainsaw. Net result was a _reduction_
by several hundred lines of code. Yeay!

All of these changes were confined to the internals of the binding machinery
and are not user visible.

Loading `.glade` files
----------------------

User interface designers are nothing new, but one of the really cool things
about GTK has long been the existence of `libglade`. It's a library which
takes the output of a one of the GNOME user interface designers (such as such
as **Glade 3** or **Gazpacho**) and dynamically, at runtime, generates live
Windows full of Widgets!

With the arbitrary Proxy retrieval sorted out, the beginnings of a binding of
`libglade` was possible. None of the fancy stuff is there yet, but a `.glade`
file can be loaded, and Widgets retrieved from the instantiated tree.

* **`org.gnome.glade.Glade`**
* **`org.gnome.glade.Xml`**
* _`org.gnome.glade.GladeXml`_

The JavaDoc for these classes clearly indicates that this is preliminary and
subject to change. It may well all be blown away when GtkBuilder lands. We'll
see.

Testing framework
-----------------

We've introduced the beginnings of a unit test framework. At the moment, this
just evaluates various getters and setters without doing anything that
requires the main loop. Despite this, the unit tests end up exercising the
entire Proxy system discussed above; validating that the properties set and
get and that the correct Proxy object is returned through a round trip is no
mean feat.

You can run the suite from Eclipse, by specifying a JUnit 3 launcher on class
UnitTests in the default package in `tests/java`, or by running 

	$ make test

from the command line.

Further coverage
----------------

This release also sees the addition of:

* **`org.gnome.gtk.FileChooser`**
* **`org.gnome.gtk.FileChooserAction`**
* **`org.gnome.gtk.FileChooserButton`**

Along with mocked up code for:

* _`org.gnome.gtk.GtkFileChooser`_
* _`org.gnome.gtk.GtkFileChooserAction`_
* _`org.gnome.gtk.GtkFileChooserButton`_

This is significant because GtkFileChooser is an _interface_ in GTK, and
GtkFileChooserButton implements it. We'd been putting off the question of
dealing with GInterface (would it work or be a major problem?) for a while
now. We were delighted to find that the design implied by the re-engineered
bindings handled it cleanly, elegantly, and without any fuss. Another nice
validation of our new architecture.

Finally, a number of new signals were exposed on: 

* **`org.gnome.gtk.Widget`**

though these were mostly the result of doing live demonstrations at
conferences of how easy extending the coverage of the new bindings is.

Memory management
-----------------

We have successfully implemented full GObject memory management in java-gnome
4.0 using GLib's ToggleRef mechanism.

A strongly referenced Java Proxy will not allow its GObject to be destroyed
out from underneath it; meanwhile, as long as the GObject is still referenced
by something other than java-gnome, an otherwise only weakly reachable Java
object that Proxies it will not be finalized. When the situation _does_ occur
whereby the GObject is only referenced from java-gnome, and the Java object is
no longer strongly referenced by any other Java objects, then the Java object
can be garbage collected and the GObject will be unref()'d and destroyed.

You can watch the reference system in action if you set
`Debug.MEMORY_MANAGEMENT` to `true`.

Huge thanks go to Vreixo Formoso Lopes who collaborated on the design,
reviewed the implementation, and contributed test case code.

Build system improvements
-------------------------

A better detection of jni.h is done on Ubuntu, thanks to Michael Kedzierski.
This makes java-gnome more likely to build out of the box on Debian-derived
systems.

On the eve of release, Srichand Pendyala noticed that if you are running such
a system, a package named `libglade-dev` needs to be installed. Of course, on
more modern systems all the necessary dependencies are present merely by
having GNOME installed in the first place. We'll add a check for this Debian
specific behaviour in 4.0.3.

The `VERSION` and `APIVERSION` constants were moved to

* `org.gnome.gtk.Version`

so that anyone working on the Gtk main class isn't forced to do a
re-configuration every time they save.

Installation and Packaging
--------------------------

java-gnome 4.0 now has the standard `make install` command, and the equally
standard `--prefix` option to `./configure`.

	$ ./configure --prefix=/usr
	$ make
	$ sudo make install

The `install` target understands the `DESTDIR` variable used by packagers to
install to a specified prefix _within_ a temporary directory.

See the [`README`](README.html) file for details.

Looking ahead
-------------

The feature additions described above were done to bring java-gnome up to
speed for the GTK & GNOME tutorial given at [linux.conf.au][LCA]. With that
past, we're not going to do any more manual mockups of code in what will be
the generated layers. Focus now turns to designing and implementing the tool
that will parse `.defs` files and output the translation code.

Once we secure funding for the project, the code generator will be our top
priority and shouldn't take more than a couple months to complete.

AfC

[LCA]: http://lca2007.linux.org.au/talk/258


<a name="4.0.1" id="1167969613" title="Prototype becomes foundation"></a>

java-gnome 4.0.1 (05 Jan 2007)
==============================

_It's not really a prototype anymore! the design works, and so the code that
is here is forming the foundation of the new Java bindings for GTK and GNOME._

While there are some significant pieces of engineering that are yet to be
done, and of course a universe of coverage yet to write, we're pleased to mark
the milestone of the prototype having proved itself to be stable and the
strong foundation that we need. In this release:

Project documentation
---------------------

Import project documentation, initially consisting of the re-engineering
emails written by Andrew Cowie to the java-gnome-hackers mailing list, and
expanded to include top level [`README`](README.html) and
[`HACKING`](HACKING.html) files, and a style guide for contributors to follow.
All documentation [re]formatted in Markdown syntax so as to be renderable to
web pages. See [`doc/design/`](doc/design/START.html) and
[`doc/style/`](doc/style/).

Project website
---------------

Create an entirely new website for <http://java-gnome.sourceforge.net/>,
introducing sections "[About](/about/)", "[Documentation](/doc/)",
"[Download](/get/)" and "[Interact](/lists/)" to discuss the the
project as a whole, to be a home for the documentation, to provide
instructions on how to get java-gnome, and information about the mailing lists
and IRC channel, respectively.

The website is no longer a wiki but is entirely within the source code of
java-gnome itself. See the `web/public/` directory; improvements welcome.

Major engineering
-----------------

Quite significantly, the infrastructure to get a Proxy or Constant instance
for any arbitrary C side pointer or enum is complete, involving _significant_
work to:

 * `org.freedestkop.bindings.Plumbing`
 * **`org.freedestkop.bindings.Proxy`**
 * **`org.freedestkop.bindings.Constant`**
 * `org.gnome.glib.Plumbing`
 * **`org.gnome.glib.Value`**
 * `org.gnome.glib.GValue`
 * `org.gnome.glib.Fundamental`
 * **`org.gnome.glib.Object`**

Along with the corresponding C side code, especially in `GValue.c`

This was a necessary building block in order to complete the generalized
`getProperty()` mechanism that, while hidden from public view, is nevertheless
a major aspect of the GObject tool chest and is usable by bindings hackers
when necessary. The generalized instance mechanism was the last major
engineering hurdle that needed to be achieved in order to prove the new
bindings design.

New coverage
------------

* **`org.gnome.gtk.Label`**
* **`org.gnome.gtk.Fixed`**
* **`org.gnome.gtk.Box`**
* **`org.gnome.gtk.VBox`**
* **`org.gnome.gtk.HBox`**

Along with

* _`org.gnome.gtk.GtkLabel`_
* _`org.gnome.gtk.GtkFixed`_
* _`org.gnome.gtk.GtkBox`_
* _`org.gnome.gtk.GtkVBox`_
* _`org.gnome.gtk.GtkHBox`_

And corresponding [working] mockup native code.

Compliments to Srichand Pendyala from Bangalore, India for being the first
external hacker to have a patch accepted to mainline! He contributed methods
to Label and initiated the implementation of the Fixed class. In so doing, he
also helped work the bugs out of the `bzr bundle` submission process. Awesome.

Build improvements
------------------

java-gnome now builds on Ubuntu and OpenSolaris in addition to its home turf
of Gentoo. Thanks to John Rice of Sun Microsystems who provided the guidance
allowing us to port Equivalence to Solaris some months ago, and Laszlo Peter,
also of Sun Ireland, for several fixes to allow configure to recognize a wider
range of Solaris environments.

The tiny example program that we have been using to validate the code,
`Experiment` is now compiled by the build system if you so request. Try `make
demo`.

API documentation
-----------------

Extensive attention has been paid to the JavaDoc for the few methods that are
presented so as to clearly set the standard required. The canonical JavaDoc
for the project is available at the website with a stable URL and can be
linked to.

All source code [comments, ie JavaDoc] have been spell checked! Initial top
level `overview.html` and `package.html` files have also been written to help
round out the JavaDoc.

AfC


<a name="4.0.0" id="1164533400" title="First public demonstration"></a>

java-gnome 4.0.0 (26 Nov 2006)
==============================
 
_Initial release of the java-gnome 4.0 prototype, corresponding to the first
public demonstration of the new bindings done at
[foss.in/2006](http://foss.in/2006/) at Bangalore, India._

The prototype is fully functional and is intended to prove the design and
architecture we have arrived at as a result of the re-engineering process. It
includes both real wrapper layer classes that are the seed from which our
public API will grow, along with the infrastructure that the wrapper layer
depends on.

Initial coverage
----------------

Wrapper layer presenting the public API to developers (publicly visible
classes in bold):

* **`org.freedesktop.bindings.Proxy`**
* **`org.gnome.glib.Value`**
* **`org.gnome.glib.Object`**
* **`org.gnome.gtk.Object`**
* **`org.gnome.gtk.Widget`**
* **`org.gnome.gtk.Container`**
* **`org.gnome.gtk.Bin`**
* **`org.gnome.gtk.Button`**
* **`org.gnome.gtk.Window`**

Along with complete translation layer implementations for each:

* `org.freedesktop.bindings.Plumbing`
* `org.gnome.glib.Plumbing`
* `org.gnome.glib.GValue`
* `org.gnome.glib.GObject`
* `org.gnome.gtk.GtkObject`
* _`org.gnome.gtk.GtkWidget`_
* _`org.gnome.gtk.GtkContainer`_
* _`org.gnome.gtk.GtkBin`_
* _`org.gnome.gtk.GtkButton`_
* _`org.gnome.gtk.GtkWindow`_

At present the bindings mock up the code that will be generated with
temporarily hand written substitutes (ie, those in italics above) for both
translation (Java) and native (C) layers. These will do until we receive the
funding to make the code generator a reality; we certainly don't want to be
writing much more translation layer Java and C code by hand. Yuk.


Signal API
----------

The defining aspect of GUI programming is, of course, that it is event driven.
In addition to the "forward" direction of making calls to the native library
and having return values bubble back up, there is the "reverse" direction of
connecting callback handlers to the various signals that different Widgets
offer, and having those signal events result in those handlers being invoked.

java-gnome 4.0 has an entirely new and redesigned signal connection and
callback API. This functionality was demonstrated, and coverage of
**`Button.CLICKED`** and **`Window.DELETE`** is now present and functional!
The APIs used by GNOME language bindings to achieve this are some of the most
voodoo I have ever seen. But it's hooked up, and it works. It's like black
magic :)

Build
-----

java-gnome is configured and built using Andrew Cowie's Equivalence build
scripts. It builds on Gentoo and should build on a Debian or Fedora derived
system as well. See [`README`](README.html) for further details.

At the moment, java-gnome is a single source package.

The source code is available via `bzr`. Again, see `README`.

AfC

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

  vim: set textwidth=78:

-->
