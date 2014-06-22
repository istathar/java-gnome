/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2014 Operational Dynamics Consulting, Pty Ltd and Others
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.gnome.gtk;

import org.freedesktop.cairo.Context;
import org.gnome.gdk.Event;
import org.gnome.gdk.EventButton;
import org.gnome.gdk.EventCrossing;
import org.gnome.gdk.EventFocus;
import org.gnome.gdk.EventKey;
import org.gnome.gdk.EventMask;
import org.gnome.gdk.EventMotion;
import org.gnome.gdk.EventScroll;
import org.gnome.gdk.EventVisibility;
import org.gnome.gdk.RGBA;
import org.gnome.gdk.Rectangle;
import org.gnome.gdk.VisibilityState;
import org.gnome.pango.FontDescription;

/**
 * The base class of all GTK Widgets. Graphical user interface toolkits have
 * long been built up from individual controls and presentation mechanisms
 * that are nested together. These elements are collectively called Widgets.
 * Quite a lot of Widgets contain other Widgets; those are called
 * {@link Container Container}s.
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 * @author Guillaume Mazoyer
 * @since 4.0.0
 */
public abstract class Widget extends org.gnome.glib.Object
{
    protected Widget(long pointer) {
        super(pointer);
    }

    /**
     * Get the name of the Widget, if it has one.
     * 
     * <p>
     * Widgets can be identified by a name. You will most likely use this
     * method a lot if you are using Glade. Every widget there is identified
     * by a name. It is suggested to use sensible names; see
     * {@link org.gnome.glade.Glade Glade} for a discussion of the
     * implications of different nomenaclatures if that's what you are using,
     * but in any case once you've retrieved the Widget with
     * {@link org.gnome.glade.XML#getWidget(String) getWidget()} you won't
     * really need to know its name anymore.
     * 
     * @since 4.0.11
     */
    public String getName() {
        return GtkWidget.getName(this);
    }

    /**
     * Every Widget has a name as an optional identifier.
     * 
     * <p>
     * If using Glade then these names are used to retrieve widgets from the
     * XML file. You can use this method you set the name of a Widget after it
     * is retrieved.
     * 
     * <p>
     * If you are using Glade then you will most likely never need this
     * method, as you will have them defined in Glade. And if you're not using
     * Glade, then you probably won't need this method either, since you can
     * refer to Widgets you create by reference.
     * 
     * @since 4.0.11
     */
    public void setName(String name) {
        GtkWidget.setName(this, name);
    }

    /**
     * Cause this Widget to be activated. This has the same effect as when the
     * user presses the <code>Return</code> key while the Widget is in focus.
     * Calling this method on a {@link ToggleButton} will toggle its state,
     * for example. Whatever signals would normally result from the user
     * activating this Widget by hand will be emitted.
     * 
     * <p>
     * Note that this method only works if this Widget is
     * <var>activatable</var>; not all are, making this more an optional
     * characteristic that some, but not all Widgets share.
     * 
     * <p>
     * The Widget must already be showing on the screen for this method to
     * work (ie, you must invoke {@link #show()} before you can
     * <code>activate()</code> it). Parent Containers must also have been
     * shown.
     * 
     * @throws UnsupportedOperationException
     *             if the Widget is not activatable.
     * @since 4.0.5
     */
    public void activate() {
        if (!GtkWidget.activate(this)) {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * Cause this Widget to be mapped to the screen. Flags a widget to be
     * displayed. Any widget that isn't shown will not appear on the screen.
     * 
     * <p>
     * There are a bunch of quirks you need to be aware of:
     * 
     * <ul>
     * <li>You have to show the Containers containing a Widget, in addition to
     * the Widget itself, before it will appear on the display.
     * <li>When a toplevel Container is shown (ie, your {@link Window Window}
     * ), it is immediately realized and mapped, and any Widgets within it
     * that are shown are then realized and mapped.
     * <li>You can't get information about the actual size that has been
     * allocated to a Widget until it gets mapped to the screen.
     * </ul>
     * 
     * <p>
     * If you want to show all the widgets in a container, it's actually much
     * easier to just call {@link #showAll()} on the container, rather than
     * calling show manually one each individual Widget you've added to it.
     * 
     * @since 4.0.0
     */
    public void show() {
        GtkWidget.show(this);
    }

    /**
     * Cause this Widget, and any Widgets it contains, to be mapped to the
     * screen. You typically call this on a {@link Window Window} after you've
     * finished all the work necessary to set it up.
     * <p>
     * Quite frequently you also want to cause a Window to appear on the
     * screen as well (ie, not be buried under a whole bunch of other
     * applications' Windows), so calling Window's {@link Window#present()
     * present()} is usually next.
     * 
     * <p>
     * <i>Don't be surprised if this takes a few hundred milliseconds.
     * Realizing and mapping all the zillion elements that ultimately make up
     * a Window is one of the most resource intensive operations that GTK,
     * GDK, Pango, your X server, and your kernel have to churn through.
     * Sometimes, you just gotta wait.</i>
     * 
     * @since 4.0.0
     */
    public void showAll() {
        GtkWidget.showAll(this);
    }

    /**
     * Hide the Widget, making it invisible to the user. This can be used to
     * "deactivate" sections of your UI, pending some activity or action that
     * will cause it to be returned to the Window. Note that hiding does not
     * remove it from its parent Container - it just makes the Widget
     * invisible for the time being.
     * 
     * <p>
     * You can call {@link #show()} to make a hidden Widget visible [again].
     * 
     * @since 4.0.4
     */
    public void hide() {
        GtkWidget.hide(this);
    }

    /**
     * Request that an area of the Widget to be redrawn. This will eventually
     * result in an <code>Widget.Draw</code> signal being sent to the Widget
     * asking it to [re]render the area given by the passed in co-ordinates.
     * 
     * <p>
     * You only ever need this if doing your own custom Widget drawing, and
     * then only sometimes.
     * 
     * <p>
     * The redraw will not happen immediately, but rather during the next
     * iteration of the main loop. Also, note that several such requests may
     * be combined into a single <code>Widget.Draw</code> signal by the X
     * server and GDK.
     * 
     * <p>
     * Be aware that this will invalidate both this Widget <i>and</i> all its
     * children. If you wish to limit your redrawing, you will be better off
     * with one of the
     * {@link org.gnome.gdk.Window#invalidate(org.gnome.gdk.Rectangle, boolean)
     * invalidate()} calls in the underlying native resource.
     * 
     * @since 4.0.10
     */
    public void queueDrawArea(int x, int y, int width, int height) {
        GtkWidget.queueDrawArea(this, x, y, width, height);
    }

    /**
     * Request that the entire Widget be redrawn. See
     * {@link #queueDrawArea(int, int, int, int) queueDrawArea()} for full
     * details, but you only ever need this if doing your own drawing.
     * 
     * @since 4.0.10
     */
    public void queueDraw() {
        GtkWidget.queueDraw(this);
    }

    /**
     * Signal emitted when the mouse enters the Widget.
     * 
     * @author Andrew Cowie
     * @author Davyd Madeley
     * @since 4.0.2
     */
    public interface EnterNotifyEvent extends GtkWidget.EnterNotifyEventSignal
    {
        /**
         * @since 4.0.7
         */
        public boolean onEnterNotifyEvent(Widget source, EventCrossing event);
    }

    /**
     * Hook up a handler to receive <code>Widget.EnterNotifyEvent</code>
     * signals on this Widget.
     * 
     * @since 4.0.2
     */
    public void connect(Widget.EnterNotifyEvent handler) {
        GtkWidget.connect(this, handler, false);
    }

    /**
     * Signal emitted when the mouse pointer leaves the Widget.
     * 
     * @author Andrew Cowie
     * @since 4.0.7
     */
    public interface LeaveNotifyEvent extends GtkWidget.LeaveNotifyEventSignal
    {
        boolean onLeaveNotifyEvent(Widget source, EventCrossing event);
    }

    /**
     * Hook up a handler to receive <code>Widget.LeaveNotifyEvent</code>
     * signals.
     * 
     * @since 4.0.7
     */
    public void connect(Widget.LeaveNotifyEvent handler) {
        GtkWidget.connect(this, handler, false);
    }

    /**
     * Signal emitted when the <i>keyboard</i> focus leaves this Widget. Focus
     * is a concept that is shared evenly between the widget toolkit and the
     * window manager - which often becomes apparent if you're wondering
     * <i>why</i> you have lost focus or regained it.
     * 
     * @author Andrew Cowie
     * @since 4.0.2
     */
    public interface FocusOutEvent extends GtkWidget.FocusOutEventSignal
    {
        public boolean onFocusOutEvent(Widget source, EventFocus event);
    }

    /**
     * Hook up a handler to receive <code>Widget.FocusOutEvent</code> events
     * on this Widget
     * 
     * @since 4.0.2
     */
    public void connect(Widget.FocusOutEvent handler) {
        GtkWidget.connect(this, handler, false);
    }

    /**
     * Signal emitted when focus enters this Widget. See
     * {@link Widget.FocusOutEvent}.
     * 
     * @author Andrew Cowie
     * @since 4.0.6
     */
    public interface FocusInEvent extends GtkWidget.FocusInEventSignal
    {
        public boolean onFocusInEvent(Widget source, EventFocus event);
    }

    /**
     * Hook up a handler to receive <code>Widget.FocusInEvent</code> signals.
     * 
     * @since 4.0.6
     */
    public void connect(Widget.FocusInEvent handler) {
        GtkWidget.connect(this, handler, false);
    }

    /**
     * The signal emitted when a portion or all of the Widget has been exposed
     * and needs [re]drawing. This can happen when a Widget is first realized
     * to the screen or when part of it has come back from being obscured (by
     * another Window or because it was offscreen).
     * 
     * <p>
     * GNOME uses the excellent <a href="http://www.cairographics.org/">Cairo
     * 2D graphics library</a> to draw its user interfaces, which we make
     * available in java-gnome in package <code>org.freedesktop.cairo</code>.
     * 
     * <p>
     * Code that does drawing needs to be written a little differently than
     * code which just builds Widgets up into user interfaces. While we are
     * accustomed to doing setting up various Widgets and packing them into
     * Windows in our constructors, the one thing you cannot easily do there
     * is graphics drawing. In order to be able to construct the Context used
     * for drawing operations, Cairo needs the details of the [org.gnome.gdk]
     * Display, Screen and Window it will be drawing to, and these are not
     * available until the Widget has been realized and mapped. The
     * <code>Widget.Draw</code> signal is emitted exactly at this point, and
     * so that's when we have the environment we need to do our drawing.
     * 
     * <p>
     * To do drawing with Cairo you need a Context, and GTK 3 conveniently
     * provides you with one all ready to go:
     * 
     * <pre>
     * foo.connect(new Widget.Draw() {
     *     public boolean onDraw(Widget source, Context cr) {
     *        // start drawing
     *     }
     * }
     * </pre>
     * 
     * GTK makes calls to Context's <code>save()</code> and
     * <code>restore()</code> around the <code>Widget.Draw</code> signal so
     * you don't worry about having to reset the Context to its initial state.
     * 
     * @author Andrew Cowie
     * @since 4.0.20
     */
    public interface Draw extends GtkWidget.DrawSignal
    {
        public boolean onDraw(Widget source, Context cr);
    }

    /**
     * Hook up a handler to receive <code>Widget.Draw</code> signals on this
     * Widget.
     * 
     * @since 4.0.20
     */
    public void connect(Widget.Draw handler) {
        GtkWidget.connect(this, handler, false);
    }

    /**
     * Handler interface for key press events. While ordinarily the user
     * <i>pressing</i> a key is generally more interesting (in terms of "what
     * key stroke did we get"), it should be noted that by the conventions of
     * modern graphical user interfaces, them <i>releasing</i> a key is when a
     * program should take action if action is called for. For example, if
     * they press and hold the <b><code>Enter</code></b> key while the pointer
     * is over a Button, but then move the mouse off of that Button,
     * subsequently releasing won't cause that Button to activate).
     * 
     * <p>
     * When hooking this up, you'll probably be wanting the key that was hit.
     * That's accomplished with Call <code>event.getKeyval()</code> as in:
     * 
     * <pre>
     * foo.connect(new Widget.KeyPressEvent() {
     *     public boolean onKeyPressEvent(Widget source, EventKey event) {
     *         final Keyval key;
     *         final ModifierType mod;
     * 
     *         key = event.getKeyval();
     *         mod = event.getState();
     * 
     *         if (key == Keyval.Up) {
     *             // go up!
     *         }
     *         return false;
     *     }
     * });
     * </pre>
     * 
     * but see {@link org.gnome.gdk.Keyval Keyval} for a long discussion of
     * the interpretation and use of keystrokes. Also note that reacting to a
     * key stroke does not imply intercepting it; returning <code>false</code>
     * and letting the default handlers in GTK carry on with things is usually
     * what you want to do.
     * 
     * <p>
     * The release half of this is {@link Widget.KeyReleaseEvent} as you might
     * expect.
     * 
     * @author Andrew Cowie
     * @since 4.0.3
     */
    public interface KeyPressEvent extends GtkWidget.KeyPressEventSignal
    {
        /**
         * As with other event signals, returning <code>false</code> means "I
         * didn't [fully] handle this signal, so proceed with the next (ie,
         * usually the default) handler" whereas if you return
         * <code>true</code> you mean "I have handled this event, and wish to
         * stop further emission of the signal".
         */
        public boolean onKeyPressEvent(Widget source, EventKey event);
    }

    /**
     * Hook up a handler to receive <code>Widget.KeyPressEvent</code> signals
     * on this Widget. For general typing this is the one you want, but for
     * critical events (like pressing <b><code>Enter</code></b> to activate a
     * Button that is going to delete things, you might want to postpone
     * action until <code>Widget.KeyReleaseEvent</code>.
     * 
     * @since 4.0.3
     */
    public void connect(Widget.KeyPressEvent handler) {
        GtkWidget.connect(this, handler, false);
    }

    /**
     * Handler interface for key release events. Calling
     * {@link EventKey#getKeyval() getKeyval()} on the <code>event</code>
     * parameter gets you to the constant representing the key that was
     * actually typed.
     * 
     * @since 4.0.3
     */
    public interface KeyReleaseEvent extends GtkWidget.KeyReleaseEventSignal
    {
        /**
         * (See the comment at
         * {@link Widget.KeyPressEvent#onKeyPressEvent(Widget, EventKey)
         * Widget.KeyReleaseEvent} to understand the return value)
         */
        public boolean onKeyReleaseEvent(Widget source, EventKey event);
    }

    /**
     * Hook up a handler to receive <code>Widget.KeyReleaseEvent</code>
     * signals on this Widget
     * 
     * @since 4.0.3
     */
    public void connect(Widget.KeyReleaseEvent handler) {
        GtkWidget.connect(this, handler, false);
    }

    /**
     * Signal emitted when the user turns the mouse wheel.
     * 
     * <p>
     * In most cases you are not interested in this Signal. If you only want
     * to provide a Scrolling surface for a big Widget, {@link ScrolledWindow}
     * is what you are looking for.
     * 
     * <p>
     * This event can be useful, however, when you want to have more control
     * of the scroll operations, specially when implemented custom Widgets.
     * 
     * <p>
     * For example, to implement a zoom operation when the user turns the
     * mouse wheel while pressing the <code>Ctrl</code> key, as many drawing
     * applications do:
     * 
     * <pre>
     * MyCustomWidget w;
     * 
     * w.connect(new Widget.ScrollEvent() {
     *     public boolean onScrollEvent(Widget source, EventScroll event) {
     *         // check if Ctrl key is pressed
     *         if (event.getState().contains(ModifierType.CONTROL_MASK)) {
     *             ScrollDirection dir = event.getDirection();
     *             if (dir == ScrollDirection.UP) {
     *                 // increment zoom
     *             } else if (dir == ScrollDirection.DOWN) {
     *                 // decrement zoom
     *             }
     *             // prevent the default scrolling if the Widget is added
     *             // to a ScrolledWindow
     *             return true;
     *         }
     *         return false;
     *     }
     * });
     * </pre>
     * 
     * <p>
     * It is also useful when your custom Widget can use the scroll events to
     * perform some related operation. For example, when you scroll a ComboBox
     * Widget it changes its active item. Of course, this is only useful when
     * the operation can be seen as a scroll in the particular Widget context;
     * it is not a good idea, for example, to close a Window or click a Button
     * in response to a <code>Widget.SrollEvent</code>.
     * 
     * @author Vreixo Formoso
     * @since 4.0.12
     */
    public interface ScrollEvent extends GtkWidget.ScrollEventSignal
    {
        public boolean onScrollEvent(Widget source, EventScroll event);
    }

    /**
     * Hook up a <code>Widget.ScrollEvent</code> handler.
     * 
     * @since 4.0.12
     */
    public void connect(Widget.ScrollEvent handler) {
        GtkWidget.connect(this, handler, false);
    }

    /**
     * Handler interface for mouse motion events. This event is emitted when
     * the user moves the mouse over the Widget.
     * 
     * <p>
     * Note that by default this event is disabled, even if you connect to it.
     * You will need to {@link Widget#addEvents(EventMask) enable} it. If you
     * want to receive all mouse motion events, you will need to supply the
     * POINTER_MOTION mask. Note that it generates a big amount of events,
     * typically tens of events per second, when the user moves the mouse over
     * this Widget. If you only care about this event when a mouse button is
     * pressed, any of LEFT_BUTTON_MOTION, MIDDLE_BUTTON_MOTION,
     * RIGHT_BUTTON_MOTION or BUTTON_MOTION masks can be used instead.
     * 
     * <p>
     * Many times, however, you are only interested on this events in some
     * specific circunstances. For example, a drawing application may be
     * interested on this when the user has selected a drawing tool (e.g. a
     * pencil) and is actually drawing (e.g. by clicking the mouse). In such
     * cases, enabling the event when needed, and disabling it when no more
     * needed, is the best alternative. For example:
     * 
     * <pre>
     * // hook up a handler to mouse button event.
     * drawarea.connect(new Widget.MotionNotifyEvent() {
     *     public boolean onMotionNotifyEvent(Widget source, EventMotion event) {
     *         double x, y;
     * 
     *         x = event.getX();
     *         y = event.getY();
     * 
     *         // draw something, x and y hold the mouse position
     *     }
     * });
     * </pre>
     * 
     * @author Vreixo Formoso
     * @since 4.0.15
     */
    /*
     * FUTURE Hint motion events seem interesting, but more research is
     * needed. FIXME Removed the enableEvents(),disableEvent() discussion
     * pending more research, but the signature here is correct so this is now
     * exposed.
     */
    public interface MotionNotifyEvent extends GtkWidget.MotionNotifyEventSignal
    {
        public boolean onMotionNotifyEvent(Widget source, EventMotion event);
    }

    /**
     * Hook up a <code>Widget.MotionNotifyEvent</code> handler.
     * 
     * @since 4.0.15
     */
    public void connect(MotionNotifyEvent handler) {
        GtkWidget.connect(this, handler, false);
    }

    /**
     * Return the Container that this Widget is packed into. If the Widget
     * doesn't have a parent, or you're already at a top level Widget (ie, a
     * Window) then expect <code>null</code>.
     * 
     * @since 4.0.2
     */
    public Container getParent() {
        return (Container) getPropertyObject("parent");
    }

    /**
     * Set whether the Widget is greyed out or not. Being insensitive is the
     * term GTK uses for a Widget that is still in its parent layout and still
     * visible on the screen, but no longer responding to user input and
     * de-emphasized on the screen.
     * 
     * <p>
     * This is frequently used on Buttons and MenuItems to show that a given
     * course of action is not currently available, but <i>would</i> be if the
     * user did something different to the application. This can be a terrific
     * hint to assist with discovery, but can be overdone; having everything
     * insensitive and leaving the user no idea what to do next doesn't really
     * help much.
     * 
     * <p>
     * Beware that setting the sensitive property cascades down through the
     * hierarchy of any children packed into this Widget in the same way that
     * {@link #showAll() showAll()} is recursive. While this is usually great
     * for <i>de</i>sensitizing an entire Window, the catch is that if you
     * <i>re</i>sensitize that same Window <b>every</b> Widget within it will
     * return to being sensitive; there's no "memory" of which might have been
     * insensitive before. Thus if you heavily use a mix of sensitive and
     * insensitive states in a Window and desensitize the whole thing while
     * carrying out input validation in a worker thread, you will be left with
     * the task of manually restoring the sensitive state of the various sub
     * components of your UI should you need to return back to that Window to
     * have the user re-enter something.
     * 
     * @param sensitive
     *            <code>true</code> to have the Widget respond as normal, and
     *            <code>false</code> to de-activate the Widget and have it
     *            "grey out".
     * @since 4.0.4
     */
    public void setSensitive(boolean sensitive) {
        GtkWidget.setSensitive(this, sensitive);
    }

    /**
     * Is this Widget set to be sensitive?
     * 
     * <p>
     * The default is <code>true</code> of course.
     * 
     * <p>
     * The utility of this is somewhat limited, since it only returns the
     * boolean value of this Widget's <var>sensitive</var> property, whereas
     * whether a Widget is displayed sensitive (normal) or insensitive (grayed
     * out) depends on both this property and the settings in the parent
     * Widgets.
     * 
     * @since 4.0.17
     */
    public boolean getSensitive() {
        return GtkWidget.getSensitive(this);
    }

    /**
     * Will this Widget be shown as sensitive or insensitive? This is based on
     * both its <var>sensitive</var> property, and that of all its parents.
     * 
     * <p>
     * You don't need to use this ordinarily (it's GTK that needs to know!)
     * but if you're curious, well, here you go.
     * 
     * @since 4.0.17
     */
    public boolean isSensitive() {
        return GtkWidget.isSensitive(this);
    }

    /**
     * Tooltips are notes that will be displayed if a user hovers the mouse
     * pointer over a Widget. They are usually used with controls such as
     * Buttons and Entries to brief the user about that Widget's function.
     * 
     * @param text
     *            The string of plain text (i.e. without any Pango markup) you
     *            wish to be displayed when if the tooltip is popped up.
     * @since 4.0.4
     */
    public void setTooltipText(String text) {
        GtkWidget.setTooltipText(this, text);
    }

    /**
     * Tooltips are notes that will be displayed if a user hovers the mouse
     * pointer over a Widget. They are usually used with controls such as
     * Buttons and Entries to brief the user about that Widget's function.
     * 
     * @param markup
     *            The string with Pango markup you wish to be displayed when
     *            if the tooltip is popped up.
     * @since 4.0.7
     */
    public void setTooltipMarkup(String markup) {
        GtkWidget.setTooltipMarkup(this, markup);
    }

    /**
     * Get the underlying resource being used to power display of, and
     * interaction with, this Widget.
     * 
     * <p>
     * <b>If you're looking for the top Window in a Widget hierarchy, see</b>
     * {@link #getToplevel() getToplevel()}. This method is to get a reference
     * to the lower level GDK mechanisms used by this Widget, not to navigate
     * up a hierarchy of Widgets to find the top-level Window they are packed
     * into.
     * 
     * <p>
     * If what you need are the event handling facilities that go with Widgets
     * that have their own native resources, consider creating an
     * {@link EventBox EventBox} and putting this Widget into it.
     * 
     * <p>
     * While it is best to wait until the Widget is mapped to screen and user
     * visible before manipulating underlying properties, there are rare cases
     * when you need the [org.gnome.gdk] Window to be not-<code>null</code>
     * before then; if so, you can call {@link #realize() realize()}.
     * 
     * <p>
     * <i>If you call this in a class where you're building Windows, then you
     * will probably end up having to use the fully qualified name</i>
     * <code>org.gnome.gdk.Window</code> <i>when declaring variables. That's
     * an unavoidable consequence of the class mapping algorithm we used:
     * <code>GdkWindow</code> is the name of the underlying type being
     * returned, and so Window it is.</i>
     * 
     * @return the <code>org.gnome.gdk.Window</code> associated with this
     *         Widget, or <code>null</code> if this Widget is (as yet) without
     *         one.
     * @since 4.0.4
     */
    public org.gnome.gdk.Window getWindow() {
        return GtkWidget.getWindow(this);
    }

    /**
     * Adjust the background colour being used when drawing this Widget. This
     * leaves all other style properties unchanged.
     * 
     * @since 4.0.20
     */
    public void overrideBackground(StateFlags state, RGBA color) {
        GtkWidget.overrideBackgroundColor(this, state, color);
    }

    /**
     * Set the colour used for text rendered by this Widget. This is the
     * foreground colour; to change the background colour behind text use
     * {@link #overrideBackground(StateFlags, RGBA) overrideBackground()}.
     * 
     * @since 4.0.20
     */
    public void overrideColor(StateFlags state, RGBA color) {
        GtkWidget.overrideColor(this, state, color);
    }

    /**
     * Set the font used for text rendered by this Widget.
     * 
     * @since 4.0.20
     */
    public void overrideFont(FontDescription desc) {
        GtkWidget.overrideFont(this, desc);
    }

    /**
     * A signal providing notification that the Widget has been obscured or
     * unobscured. To use this, go through the supplied <code>event</code>
     * parameter to get to the VisibilityState as follows:
     * 
     * <pre>
     * foo.connect(new Widget.VisibilityNotifyEvent() {
     *     public boolean onVisibilityNotifyEvent(Widget source, EventVisibility event) {
     *         VisibilityState state = event.getState();
     *         if (state == VisibilityState.FULLY_OBSCURED) {
     *            ...
     *         }
     *     }
     *     return false;
     * });
     * </pre>
     * 
     * See {@link VisibilityState VisibilityState} for the constants
     * describing the possible three possible changes to an underlying
     * element's visibility. See also {@link Widget.UnmapEvent} for a
     * discussion of how this can be used to actively toggle the presentation
     * of a Window to the user.
     * 
     * @author Andrew Cowie
     * @since 4.0.5
     */
    public interface VisibilityNotifyEvent extends GtkWidget.VisibilityNotifyEventSignal
    {
        /**
         * Although this is an <var>event-signal</var>, this merely reports
         * information coming from the underlying X11 windowing system. Since
         * a window being obscured by another application's window is not
         * something you can control (short of requesting the Window holding
         * this Widget always be on-top), it's not entirely clear what good it
         * would do to block further emission of this signal. Return
         * <code>false</code>!
         */
        public boolean onVisibilityNotifyEvent(Widget source, EventVisibility event);
    }

    /**
     * Connect a <code>Widget.VisibilityNotifyEvent</code> handler.
     * 
     * @since 4.0.5
     */
    /*
     * VISIBILITY_NOTIFY is automatically set to receive this event.
     */
    public void connect(Widget.VisibilityNotifyEvent handler) {
        GtkWidget.addEvents(this, EventMask.VISIBILITY_NOTIFY);
        GtkWidget.connect(this, handler, false);
    }

    /**
     * The signal emitted when a Window becomes invisible. This happens in a
     * variety of scenarios, notably when the Window is minimized, when you
     * change workspaces, and as a Window is being destroyed.
     * 
     * <p>
     * In combination with {@link Widget.VisibilityNotifyEvent}, this can be
     * used to detect whether a Window is actually currently presented to the
     * top of the stack and visible to the user:
     * 
     * <pre>
     * private boolean up = false;
     * ...
     * final Window w;
     * final Button b;
     * ...
     * w.connect(new Widget.VisibilityNotifyEvent() {
     *     public boolean onVisibilityNotifyEvent(Widget source, EventVisibility event) {
     *         if (event.getState() == VisibilityState.UNOBSCURED) {
     *             up = true;
     *         } else {
     *             up = false;
     *         }
     *         return false;
     *     }
     * });
     * 
     * w.connect(new Widget.UnmapEvent() {
     *     public boolean onUnmapEvent(Widget source, Event event) {
     *         up = false;
     *         return false;
     *     }
     * });
     * </pre>
     * 
     * thus allowing you to do something like:
     * 
     * <pre>
     * b.connect(new Button.Clicked() {
     *     public void onClicked(Button source) {
     *         if (up) {
     *             w.hide();
     *             up = false;
     *         } else {
     *             w.present();
     *             up = true;
     *         }
     *     }
     * });
     * </pre>
     * 
     * to intelligently toggle the visibility of the Window.
     * 
     * <p>
     * Note that you don't need <code>Widget.MapEvent</code> here because the
     * the <code>Widget.VisibilityNotifyEvent</code> will be tripped if you
     * come back to the workspace the Window is already on.
     * 
     * @author Andrew Cowie
     * @author Ryan Lortie
     * @since 4.0.5
     */
    public interface UnmapEvent extends GtkWidget.UnmapEventSignal
    {
        /**
         * Although this is an <var>event-signal</var>, this merely reports
         * information coming from the underlying X11 windowing system. It's
         * information you can monitor, but don't try to block this signal.
         * Return <code>false</code>!
         */
        boolean onUnmapEvent(Widget source, Event event);
    }

    /**
     * Connect a <code>Widget.UnmapEvent</code> handler.
     * 
     * @since 4.0.5
     */
    public void connect(Widget.UnmapEvent handler) {
        GtkWidget.connect(this, handler, false);
    }

    /**
     * Does this Widget currently have the keyboard focus?
     * 
     * <p>
     * This can be quite useful when one Widget takes action in a signal
     * handler which changes the state of another Widget. Take for example two
     * related Entry Widgets. The second Entry's <code>Entry.Changed</code>
     * signal will fire when the first Entry's <code>Entry.Changed</code>
     * handler calls <code>second.setText()</code>; if it changes the first
     * Entry then you have an infinite loop on your hands. By checking for
     * <var>has-focus</var> at the beginning of both handlers, then only the
     * Widget that the user changed will carry out it's logic; the other will
     * realize it doesn't have focus and can quickly pass.
     * 
     * @since 4.0.6
     */
    public boolean getHasFocus() {
        return getPropertyBoolean("has-focus");
    }

    /**
     * Get the Widget at the top of the container hierarchy to which this
     * Widget belongs.
     * 
     * <p>
     * It's is somewhat common to want to find the ultimately enclosing top
     * level Window that this Widget belongs to. Assuming that the Widget has
     * actually been packed into a Container hierarchy that tops out at a
     * Window (or Dialog, etc) then that is what you'll get. So yes, you can
     * do:
     * 
     * <pre>
     * w = (Window) obj.getToplevel();
     * </pre>
     * 
     * as you'll get a ClassCastException or NullPointerException if you're
     * wrong about <code>obj</code> being in a Window yet.
     * 
     * <p>
     * To manually walk up the hierarchy one level at a time, use
     * {@link #getParent() getParent()}.
     * 
     * @return Will return <code>this</code> if the Widget isn't (yet) in a
     *         hierarchy.
     * @since 4.0.6
     */
    public Widget getToplevel() {
        return GtkWidget.getToplevel(this);
    }

    /**
     * The signal emitted when a Widget is hidden.
     * 
     * @author Andrew Cowie
     * @since 4.0.6
     */
    public interface Hide extends GtkWidget.HideSignal
    {
        public void onHide(Widget source);
    }

    /**
     * Connect a <code>Widget.Hide</code> handler.
     * 
     * @since 4.0.6
     */
    public void connect(Widget.Hide handler) {
        GtkWidget.connect(this, handler, false);
    }

    /**
     * Make this Widget have the keyboard focus for the Window it is within.
     * 
     * <p>
     * Obviously, if this is going to actually have affect, this Widget needs
     * to be <i>in</i> a Window. Furthermore, the Widget needs to be
     * <i>able</i> to take input focus, that is, it must have the
     * <var>can-focus</var> property set (which is inherent to the particular
     * Widget subclass, not something you can change).
     * 
     * @since 4.0.6
     */
    public void grabFocus() {
        GtkWidget.grabFocus(this);
    }

    /**
     * Set the minimum size that will be requested by this Widget of its
     * parent Container.
     * 
     * <p>
     * A major feature of GTK is its adaptability in the face of different
     * languages, fonts, and theme engines, with all of these factors
     * impacting the number of pixels that will be necessary for drawing. The
     * box packing model has each Widget request the size it calculates it
     * needs at runtime of its parent. These requests flow up the Containers
     * the Widget is packed into, with each Container collating the requests
     * from its children. When it reaches the toplevel, GTK negotiates with
     * the X server, and the result is the size allocation for the Window as a
     * whole. The Window proceeds to inform each Container packed into it how
     * much space it has been allocated, leaving it to the Containers to in
     * turn allocate space to each of its children.
     * 
     * <p>
     * The whole point of all this is that in general you are <b>not</b>
     * supposed to interfere with this process. It is virtually impossible to
     * calculate the correct size for a Widget on a given user's desktop ahead
     * of time, so don't try. This method is here for the unusual cases where
     * you need to force a Widget to be a size other than what the default
     * request-allocation process results in.
     * 
     * <p>
     * See {@link Requisition} and {@link Allocation} for further discussion
     * of how the size-request/size-allocation process works and how you can
     * get insight into it.
     * 
     * <p>
     * A value of <code>-1</code> for either <code>width</code> or
     * <code>height</code> will cause that dimension to revert to the
     * "natural" size, that is, the size that would have been requested if
     * you'd left things alone.
     * 
     * <p>
     * Passing <code>0,0</code> is a special case, meaning "as small as
     * possible". This will have varying results and may not actually have
     * much effect.
     * 
     * <p>
     * Incidentally, use {@link Window#setDefaultSize(int, int)
     * setDefaultSize()} for top level Windows, as that method still allows a
     * user to make the Window smaller than the specified default.
     * 
     * @since 4.0.6
     */
    public void setSizeRequest(int width, int height) {
        if ((width < -1) || (height < -1)) {
            throw new IllegalArgumentException("width and height need to be >= -1");
        }
        GtkWidget.setSizeRequest(this, width, height);
    }

    /*
     * We cache our wrappers for the GtkRequisition and GtkAllocation structs
     * so that we get the same Pointer object back if multiple calls are made
     * to getRequisition() and getAllocation(), thus creating a Proxy like
     * behaviour even though these are Boxeds. This avoids allocating
     * duplicates.
     */

    /*
     * This requisition will need to be removed when GTK3 arrives
     */
    private Requisition requisition;

    private Allocation allocation;

    /**
     * Get the details of the rectangle that represents the size that the
     * windowing system down to GTK on down to the parent containers of this
     * Widget have allocated to it. Note that the Widget must already have
     * been realized for the request-allocation cycle to have taken place (ie,
     * the top level Window and all its children must have been
     * <code>show()</code>n. In some circumstances the main loop may need to
     * have iterated).
     * 
     * @since 4.0.6
     */
    public Allocation getAllocation() {
        if (allocation == null) {
            allocation = new Allocation();
        }

        GtkWidgetOverride.getAllocation(this, allocation);

        return allocation;
    }

    /**
     * Get the size that will be (is being) requested by this Widget.
     * 
     * <p>
     * In addition to getting the Requisition object for this Widget, this
     * method will also ask the Widget to actually calculate its requirements.
     * This can be a relatively expensive operation as font metrics need to be
     * worked out relative to the Display's physical characteristics (this
     * implies that the request calculation won't have any effect if the
     * Widget is not yet been FIXME to a Screen).
     * 
     * @since 4.0.6
     */
    public Requisition getRequisition() {
        if (requisition == null) {
            requisition = new Requisition();
        }

        GtkWidgetOverride.getRequisition(this, requisition);

        return requisition;
    }

    /**
     * Retreive the width that has been allocated to this Widget, in pixels.
     * This is meant to be used by <code>Widget.Draw</code> handlers when they
     * need to know their canvas size.
     * 
     * @since 4.1.1
     */
    public int getAllocatedWidth() {
        return GtkWidget.getAllocatedWidth(this);
    }

    /**
     * Retreive the height that has been allocated to this Widget. See also
     * {@link #getAllocatedWidth() getAllocatedWidth()}.
     * 
     * @since 4.1.1
     */
    public int getAllocatedHeight() {
        return GtkWidget.getAllocatedHeight(this);
    }

    /**
     * @since 4.1.1
     */
    public int getPreferredWidthNatural() {
        final int[] natural;

        natural = new int[1];

        GtkWidget.getPreferredWidth(this, null, natural);

        return natural[0];
    }

    /**
     * @since 4.1.1
     */
    public int getPreferredWidthMinimum() {
        final int[] minimum;

        minimum = new int[1];

        GtkWidget.getPreferredWidth(this, minimum, null);

        return minimum[0];
    }

    /**
     * @since 4.1.1
     */
    public int getPreferredHeightMinimum() {
        final int[] minimum;

        minimum = new int[1];

        GtkWidget.getPreferredHeight(this, minimum, null);

        return minimum[0];
    }

    /**
     * @since 4.1.1
     */
    public int getPreferredHeightNatural() {
        final int[] natural;

        natural = new int[1];

        GtkWidget.getPreferredHeight(this, null, natural);

        return natural[0];
    }

    /**
     * @since 4.1.1
     */
    public int getPreferredHeightForWidthMinimum(int width) {
        final int[] minimum;

        minimum = new int[1];

        GtkWidget.getPreferredHeightForWidth(this, width, minimum, null);

        return minimum[0];
    }

    /**
     * @since 4.1.1
     */
    public int getPreferredHeightForWidthNatural(int width) {
        final int[] natural;

        natural = new int[1];

        GtkWidget.getPreferredHeightForWidth(this, width, null, natural);

        return natural[0];
    }

    /**
     * @since 4.1.1
     */
    public int getPreferredWidthForHeightMinimum(int height) {
        final int[] minimum;

        minimum = new int[1];

        GtkWidget.getPreferredWidthForHeight(this, height, minimum, null);

        return minimum[0];
    }

    /**
     * @since 4.1.1
     */
    public int getPreferredWidthForHeightNatural(int height) {
        final int[] natural;

        natural = new int[1];

        GtkWidget.getPreferredWidthForHeight(this, height, null, natural);

        return natural[0];
    }

    /**
     * Signal fired when the user clicks one of their mouse buttons.
     * 
     * <p>
     * Typically, you will use this to do something specific on a mouse click,
     * for example popping up a context menu in response to a "right-click"
     * anywhere in Window <code>w</code>,
     * 
     * <pre>
     * w.connect(new Widget.ButtonPressEvent() {
     *     boolean onButtonPressEvent(Widget source, EventButton event) {
     *         if (event.getButton() == MouseButton.RIGHT) {
     *             // popup menu
     *         }
     *         return false;
     *     }
     * });
     * </pre>
     * 
     * <p>
     * Like all event signals, you only return <code>true</code> if you are
     * intercepting this event and want to prevent the default handlers in GTK
     * from running.
     * 
     * <p>
     * The signal emitted when the user lets the button go is
     * {@link Widget.ButtonReleaseEvent}.
     * 
     * <p>
     * Note that this signal doesn't apply just to the user clicking on a
     * Button Widget. Indeed, "left-click" on a Button will cause
     * {@link Button.Clicked} to be emitted, and you should use that in
     * preference for normal purposes.
     * 
     * @author Andrew Cowie
     * @since 4.0.6
     */
    public interface ButtonPressEvent extends GtkWidget.ButtonPressEventSignal
    {
        public boolean onButtonPressEvent(Widget source, EventButton event);
    }

    /**
     * Hook up a <code>Widget.ButtonPressEvent</code> handler.
     * 
     * @since 4.0.6
     */
    /*
     * Do we need to force the GDK event mask like we did with
     * Widget.VisibilityNotifyEvent?
     */
    public void connect(Widget.ButtonPressEvent handler) {
        GtkWidget.connect(this, handler, false);
    }

    /**
     * The signal emitted when the user releases a pressed mouse button. See
     * {@link Widget.ButtonPressEvent} for discussion of this set of event
     * signals.
     * 
     * @author Andrew Cowie
     * @since 4.0.6
     */
    public interface ButtonReleaseEvent extends GtkWidget.ButtonReleaseEventSignal
    {
        public boolean onButtonReleaseEvent(Widget source, EventButton event);
    }

    /**
     * Hook up a <code>Widget.ButtonReleaseEvent</code> handler.
     * 
     * @since 4.0.6
     */
    public void connect(Widget.ButtonReleaseEvent handler) {
        GtkWidget.connect(this, handler, false);
    }

    /**
     * Whether the Widget can (is willing to) accept the keyboard input focus.
     * There's no default, as such; Widgets able to take keyboard focus will
     * (already) have it <code>true</code>, and others not. The
     * <var>can-focus</var> property is mostly internal, but it is
     * occasionally useful to use a widget that can focus in an environment
     * where you don't want it to take input. Calling
     * <code>setCanFocus(false)</code> will do the trick.
     * 
     * @since 4.0.6
     */
    public void setCanFocus(boolean setting) {
        setPropertyBoolean("can-focus", setting);
    }

    /**
     * Get the value of the <var>can-focus</var> property. See
     * {@link #setCanFocus(boolean) setCanFocus()} and {@link #grabFocus()
     * grabFocus()} for details.
     * 
     * @since 4.0.8
     */
    public boolean getCanFocus() {
        return getPropertyBoolean("can-focus");
    }

    /**
     * Whether this Widget is willing to be the default Widget. The
     * <var>can-default</var> property, like <var>can-focus</var>, is mostly
     * internal; while telling GTK that this Widget isn't the one that should
     * activated on <b><code>Enter</code></b> isn't that useful, setting this
     * <i>sometimes</i> has an effect on whether or not the theme will draw a
     * dotted line (or other markup) around a Button to indicate that the
     * Widget is the current default Widget.
     * 
     * <p>
     * Normally, this setting is <code>false</code>, though
     * <var>activatable</var> Widgets will already have it <code>true</code>.
     * Disabling <var>can-default</var> will mean that a
     * {@link #grabDefault() grabDefault()} on this Widget will be ignored.
     * 
     * @since 4.0.8
     */
    public void setCanDefault(boolean setting) {
        setPropertyBoolean("can-default", setting);
    }

    /**
     * Get the value of the <var>can-default</var> property. See
     * {@link #setCanDefault(boolean) setCanDefault()} and
     * {@link #grabDefault() grabDefault()} for details.
     * 
     * @since 4.0.8
     */
    public boolean getCanDefault() {
        return getPropertyBoolean("can-default");
    }

    /**
     * Make this Widget attempt to become the default. The default Widget is
     * the one which is activated when the user presses <b><code>Enter</code>
     * </b>.
     * 
     * <p>
     * This will only work if the Widget is <var>activatable</var> (see
     * {@link #activate() activate()}) and if the Widget has its
     * <var>can-default</var> property enabled with
     * {@link #setCanDefault(boolean) setCanDefault()}. The Widget also needs
     * to have already been packed into a hierarchy which tops out at a
     * Window.
     * 
     * <p>
     * If you're reading this you may in fact be looking for
     * {@link #grabFocus() grabFocus()} which affects where keyboard input is
     * going, as opposed to this method which affects activation.
     * 
     * @since 4.0.8
     */
    public void grabDefault() {
        GtkWidget.grabDefault(this);
    }

    /**
     * Make this the current grabbed Widget. Interaction with other Widgets
     * will be prevented. If this Widget is not sensitive, this call will do
     * nothing.
     * 
     * <p>
     * Note that being the current grabbed widget means mouse and keyboard
     * events will not be delivered to other widgets, so use this with care.
     * 
     * @since 4.0.11
     */
    public void grabAdd() {
        GtkWidget.grabAdd(this);
    }

    /**
     * Removes the "grab" status from this Widget if it is currently grabbed,
     * otherwise this does nothing. See {@link #grabAdd() grabAdd()}.
     * 
     * @since 4.0.11
     */
    public void grabRemove() {
        GtkWidget.grabRemove(this);
    }

    /**
     * The signal emitted when a Widget becomes visible on the screen.
     * 
     * <p>
     * This can be used as an indication that your Window is no longer
     * minimized. Connecting to {@link Widget.Draw} would probably tell you
     * what you need to know, but if all you want to do is find out your app
     * is [back] onscreen then <code>Widget.Draw</code> would be a bit heavy
     * handed. Of course, if you are drawing anyway, then it's fine. See
     * {@link Widget.UnmapEvent} for examples of other variations on the theme
     * of tracking the state of your application.
     * 
     * <p>
     * <i>The interaction between the GTK library we use, its underlying GDK
     * resource management layer, and the the X windowing system which GDK
     * wraps, is complex. Sometimes there is more than one way to do
     * things.</i>
     * 
     * @author Andrew Cowie
     * @since 4.0.8
     */
    public interface MapEvent extends GtkWidget.MapEventSignal
    {
        /**
         * Although this is an <var>event-signal</var>, this merely reports
         * information coming from the underlying X11 windowing system. It's
         * information you can monitor, but don't try to block this signal.
         * Return <code>false</code>!
         */
        public boolean onMapEvent(Widget source, Event event);
    }

    /**
     * Hook up a <code>Widget.MapEvent</code> signal handler.
     * 
     * @since 4.0.8
     */
    public void connect(Widget.MapEvent handler) {
        GtkWidget.connect(this, handler, false);
    }

    /**
     * The signal emitted when the user presses the <b><code>Menu</code></b>
     * key.
     * 
     * <p>
     * This is usually used in conjunction with a
     * <code>Widget.ButtonPressEvent</code> handler set up to intercept a
     * right-click, with both handlers being written to call common code to
     * populate and present a context menu.
     * 
     * @author Andrew Cowie
     * @since 4.0.9
     */
    public interface PopupMenu extends GtkWidget.PopupMenuSignal
    {
        public boolean onPopupMenu(Widget source);
    }

    /**
     * Hookup a <code>Widget.PopupMenu</code> handler.
     * 
     * @since 4.0.9
     */
    public void connect(Widget.PopupMenu handler) {
        GtkWidget.connect(this, handler, false);
    }

    /**
     * Enable the given events. While most events are enabled by default, some
     * need to be activated. Corresponding signal will document this
     * procedure, if needed.
     * 
     * <p>
     * Take care that events are actually received by the underlying GDK
     * Window resource being used. Such resource is in many cases shared by
     * several Widgets, so enabling or disabling an event on one of these can
     * affect all Widgets. If that is a problem for you, {@link EventBox
     * EventBox} Widget can be used to ensure only it is affected by this
     * method.
     * 
     * @since 4.0.15
     */
    public void addEvents(EventMask events) {
        GtkWidget.addEvents(this, events);
    }

    /**
     * Reset the list of events for this Widget. You probably want
     * {@link #addEvents(EventMask) addEvents()}. If you use this, you'll need
     * to <code>or()</code> together all the events that this Widget needs to
     * function.
     * 
     * @since 4.0.15
     */
    public void setEvents(EventMask events) {
        GtkWidget.setEvents(this, events);
    }

    /**
     * Cause the resources underlying the Widget to be assigned. Among other
     * things, this will populate the [org.gnome.gdk] Window that backs this
     * Widget.
     * 
     * <p>
     * In general you don't want to be calling this. This is largely an
     * internal method; while you can trigger realization manually you rarely
     * need to. You <i>do</i> need to show the Widget with {@link #show()
     * show()} (or better yet {@link #showAll() showAll()} on one of its
     * parents) once you've built it.
     * 
     * <p>
     * Almost anything that you would do that would invole you needing an
     * [org.gnome.gdk] Window is best done in a <code>Widget.Draw</code>
     * handler, at which point the Widget is already realized, mapped, and
     * showing.
     * 
     * @since 4.0.16
     */
    public void realize() {
        GtkWidget.realize(this);
    }

    /*
     * Accessors for style properties. The primitive types are fairly straight
     * forward, but the more complex derived types will take a bit of work.
     * The underlying function takes a Value as an out-parameter, but you have
     * to have initialized it to the right "type" first. Tricky.
     */

    /**
     * Access a "style property" with an integral value.
     * 
     * @since 4.0.17
     */
    protected int getStylePropertyInteger(String name) {
        final Value value;

        value = new Value(0);

        GtkWidget.styleGetProperty(this, name, value);

        return value.getInteger();
    }

    /**
     * Access a "style property" with a String value.
     * 
     * @since 4.0.17
     */
    protected String getStylePropertyString(String name) {
        final Value value;

        value = new Value("");

        GtkWidget.styleGetProperty(this, name, value);

        return value.getString();
    }

    /**
     * Access a "style property" with a boolean value.
     * 
     * @since 4.0.17
     */
    protected boolean getStylePropertyBoolean(String name) {
        final Value value;

        value = new Value(true);

        GtkWidget.styleGetProperty(this, name, value);

        return value.getBoolean();
    }

    /**
     * Access a "style property" with a float value.
     * 
     * @since 4.0.17
     */
    protected float getStylePropertyFloat(String name) {
        final Value value;

        value = new Value(0.0f);

        GtkWidget.styleGetProperty(this, name, value);

        return value.getFloat();
    }

    /**
     * Access a "style property" with a double value.
     * 
     * @since 4.0.17
     */
    protected double getStylePropertyDouble(String name) {
        final Value value;

        value = new Value(0.0);

        GtkWidget.styleGetProperty(this, name, value);

        return value.getDouble();
    }

    /**
     * Access a "style property" with a long value.
     * 
     * @since 4.0.17
     */
    protected long getStylePropertyLong(String name) {
        final Value value;

        value = new Value(0L);

        GtkWidget.styleGetProperty(this, name, value);

        return value.getLong();
    }

    /**
     * Ask everything connected to this object to release its references. Use
     * this in preference to Container's {@link Container#remove(Widget)
     * remove()} if you don't need the Widget any more.
     * 
     * <p>
     * <i>We didn't expose this for a long time in java-gnome; after all,
     * memory management of both Java references and GObject Ref counts is
     * handled automatically by the diabolical cunning of this most excellent
     * library. It turns out, however, that this does not <code>free()</code>
     * the GtkWidget's memory; it really only does what it says: ask other
     * GtkWidget to drop whatever Refs they may be holding to this object.
     * Thus if this GtkWidget is in a GtkContainer and you call</i>
     * <code>destroy()</code> <i>the GtkContainer will drop its Refs to this
     * GtkWidget thereby breaking its parent-child relationship.</i>
     * 
     * <p>
     * <i>Note that Java's references remain, so the object will <b>not</b>,
     * in fact, be eligable for finalizing until you drop all your references;
     * ie, the Java side Proxy Object goes out of scope. Nevertheless calling
     * this will speed up release of resources associated with a Widget, so
     * it's a good idea. Once you've done so,</i> <code>finalize()</code>
     * <i>will be traversed a short time later and the GObject will be
     * released.</i>
     * 
     * @since 4.0.20
     */
    public void destroy() {
        GtkWidget.destroy(this);
    }

    /**
     * Signal handler for when a Widget requests that all other code holding
     * references to it release those references. In Java-speak, that means if
     * you have this object in a variable or structure of some kind,
     * <code>null</code> it out to to release the strong reference to the
     * object.
     * 
     * @author Andrew Cowie
     * @since 4.0.20
     */
    public interface Destroy extends GtkWidget.DestroySignal
    {
        /**
         * Release any references you hold to the given <code>source</code>
         * Widget.
         */
        public void onDestroy(Widget source);
    }

    /**
     * Hook up a <code>Widget.Destroy</code> handler.
     * 
     * @since 4.0.20
     */
    public void connect(Widget.Destroy handler) {
        GtkWidget.connect(this, handler, false);
    }

    /**
     * Signal fired when a Widget is given its size allocation.
     * 
     * @author Andrew Cowie
     * @since 4.1.1
     */
    public interface SizeAllocate extends GtkWidget.SizeAllocateSignal
    {
        void onSizeAllocate(Widget source, Rectangle allocation);
    }

    /**
     * Hook up a <code>Widget.SizeAllocate</code> handler.
     * 
     * @since 4.1.1
     */
    public void connect(Widget.SizeAllocate handler) {
        GtkWidget.connect(this, handler, false);
    }

    /**
     * Does the Widget prefer that it be given height for a given width, or
     * vice versa?
     * 
     * <p>
     * Part of GTK 3's height-for-width geometry management system.
     * 
     * @return
     */
    public SizeRequestMode getRequestMode() {
        return GtkWidget.getRequestMode(this);
    }

    /**
     * Will this Widget be given any additional space that it's parent
     * Container has? The default is <code>false</code> which means that as a
     * child this Widget will be allocated its natural size reqeust, but not
     * more.
     * 
     * <p>
     * This is the <var>hexpand</var> property.
     * 
     * @since 4.1.1
     */
    public void setExpandHorizontal(boolean setting) {
        GtkWidget.setHexpand(this, setting);
    }

    /**
     * <p>
     * This is the <var>vexpand</var> property.
     * 
     * @since 4.1.1
     */
    public void setExpandVertical(boolean setting) {
        GtkWidget.setVexpand(this, setting);
    }

    /**
     * Set the horizontal positioning of a Widget in its parent Container.
     * 
     * <p>
     * This is the <var>halign</var> property.
     * 
     * <p>
     * <i>This is part of how the GTK 2.x Alignment Widget has been
     * replaced.</i>
     * 
     * @since 4.1.1
     */
    public void setAlignHorizontal(Align align) {
        GtkWidget.setHalign(this, align);
    }

    /**
     * Set the vertical positioning of a Widget in its parent Container.
     * 
     * <p>
     * This is the <var>valign</var> property.
     * 
     * @since 4.1.1
     */
    public void setAlignVertical(Align align) {
        GtkWidget.setValign(this, align);
    }

    /**
     * Get the {@link StyleContext} of a Widget. You will be able to change
     * the values of the style after getting this object.
     * 
     * @since 4.1.2
     */
    public StyleContext getStyleContext() {
        return GtkWidget.getStyleContext(this);
    }

    /**
     * Get the primary {@link Clipboard} of a Widget.
     *
     * @since 4.1.3
     */
    public Clipboard getPrimaryClipboard() {
        return GtkWidgetOverride.getPrimaryClipboard(this);
    }

    /**
     * Signal fired when requesting a tooltip.
     * 
     * When using the method you should return true to either display the
     * tooltip or false when not to display the tooltip. You can decide
     * whether to show it or not based on the x and y values that you receive
     * from the method.
     * 
     * Note however that if keyboardMode is true x and y will be undefined.
     * 
     * You will also receive the {@link Tooltip} object where you can place
     * the contents to show in the tooltip. This is the only way to receive a
     * tooltip object to work with.
     * 
     * See {@link Tooltip} for more informtation.
     * 
     * @since 4.1.3
     */
    /*
     * As stated in the Tooltip documentation, this is _only_ way to receive
     * the tooltip object.
     */
    public interface QueryTooltip extends GtkWidget.QueryTooltipSignal
    {
        boolean onQueryTooltip(Widget source, int x, int y, boolean keyboardMode, Tooltip tooltip);
    }

    /**
     * Connect a tooltip query to the widget.
     * 
     * This will cause the widget to be be monitored for motion and leave
     * notifications. Also note that this cannot be turned off.
     * 
     * @since 4.1.3
     */
    /*
     * Note that notion in the javadoc entry that mentions that the leave and
     * motion notifications cannot be turned off. This is true even if you
     * change the hasTooltip property to false. It changes something in the
     * GdkWindow masks and simply cannot be undone at all.
     */
    public void connect(Widget.QueryTooltip handler) {
        // needs to be set to true or tooltips won't be shown. Note that this
        // cannot be turned off.
        GtkWidget.setHasTooltip(this, true);
        // and now connect the handler.
        GtkWidget.connect(this, handler, false);
    }
}
