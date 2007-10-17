/*
 * Widget.java
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.gnome.gdk.Color;
import org.gnome.gdk.EventExpose;
import org.gnome.gdk.EventFocus;
import org.gnome.gdk.EventKey;
import org.gnome.gdk.EventVisibility;
import org.gnome.gdk.VisibilityState;

/**
 * The base class of all GTK Widgets. Graphical user interface toolkits have
 * long been built up from individual controls and presentation mechanisms
 * that are nested together. These elements are collectively called Widgets.
 * Quite a lot of Widgets contain other Widgets; those are called
 * {@link Container Container}s.
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 * @since 4.0.0
 */
public abstract class Widget extends org.gnome.gtk.Object
{
    protected Widget(long pointer) {
        super(pointer);
    }

    /**
     * Cause this Widget to be mapped to the screen. Flags a widget to be
     * displayed. Any widget that isn't shown will not appear on the screen.
     * 
     * <p>
     * There are a bunch of quirks you need to be aware of:
     * 
     * <ul>
     * <li>You have to show the Containers containing a Widget, in addition
     * to the Widget itself, before it will appear on the display.
     * <li>When a toplevel Container is shown (ie, your {@link Window Window}),
     * it is immediately realized and mapped, and any Widgets within it that
     * are shown are then realized and mapped.
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
     * applications' Windows), so calling Window's
     * {@link Window#present() present()} is usually next.
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
     * Signal emitted when the mouse enters the Widget
     * 
     * @author Andrew Cowie
     * @author Davyd Madeley
     * @since 4.0.2
     */
    public interface ENTER_NOTIFY_EVENT extends GtkWidget.ENTER_NOTIFY_EVENT
    {
        public boolean onEnterNotifyEvent(Widget source, Object event);
    }

    /**
     * Hook up a handler to receive "enter-notify-event" events on this
     * Widget.
     * 
     * @since 4.0.2
     */
    public void connect(ENTER_NOTIFY_EVENT handler) {
        GtkWidget.connect(this, handler);
    }

    /**
     * Signal emitted when the focus leaves this Widget. Focus is a concept
     * that is shared evenly between the widget toolkit and the window manager -
     * which often becomes apparent if you're wondering <i>why</i> you have
     * lost focus or regained it.
     * 
     * @author Andrew Cowie
     * @since 4.0.2
     */
    public interface FOCUS_OUT_EVENT extends GtkWidget.FOCUS_OUT_EVENT
    {
        public boolean onFocusOutEvent(Widget source, EventFocus event);
    }

    /**
     * Hook up a handler to receive "focus-out-event" events on this Widget
     * 
     * @since 4.0.2
     */
    public void connect(FOCUS_OUT_EVENT handler) {
        GtkWidget.connect(this, handler);
    }

    public interface EXPOSE_EVENT extends GtkWidget.EXPOSE_EVENT
    {
        public boolean onExposeEvent(Widget source, EventExpose event);
    }

    /**
     * Hook up a handler to receive "expose-event" events on this Widget
     * 
     * @since 4.0.2
     */
    public void connect(EXPOSE_EVENT handler) {
        GtkWidget.connect(this, handler);
    }

    /**
     * Handler interface for key press events. The user <i>pressing</i> a key
     * is generally less interesting than them <i>releasing</i> a key as
     * that, by the conventions of modern graphical user interfaces, is when a
     * program should take action; note for example that if they press a key
     * but then move the mouse out the release won't cause that Button to
     * activate.
     * 
     * @since 4.0.3
     * @see Widget.KEY_RELEASE_EVENT
     */
    public interface KEY_PRESS_EVENT extends GtkWidget.KEY_PRESS_EVENT
    {
        public boolean onKeyPressEvent(Widget source, EventKey event);
    }

    /**
     * Hook up a handler to receive <code>key-press-event</code> signals on
     * this Widget. In general you <b>don't</b> want this.
     * 
     * @since 4.0.3
     */
    public void connect(KEY_PRESS_EVENT handler) {
        GtkWidget.connect(this, handler);
    }

    /**
     * Handler interface for key release events.
     * 
     * @since 4.0.3
     */
    public interface KEY_RELEASE_EVENT extends GtkWidget.KEY_RELEASE_EVENT
    {
        public boolean onKeyReleaseEvent(Widget source, EventKey event);
    }

    /**
     * Hook up a handler to receive <code>key-release-event</code> signals
     * on this Widget
     * 
     * @since 4.0.3
     */
    public void connect(KEY_RELEASE_EVENT handler) {
        GtkWidget.connect(this, handler);
    }

    /*
     * Temporary: testing full downcasting.
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
     * course of action is not currently available, but <i>would</i> be if
     * the user did something different to the application. This can be a
     * terrific hint to assist with discovery, but can be overdone; having
     * everything insensitive and leaving the user no idea what to do next
     * doesn't really help much.
     * 
     * <p>
     * Beware that setting the sensitive property cascades down through the
     * hierarchy of any children packed into this Widget in the same way that
     * {@link #showAll() showAll()} is recursive. While this is usually great
     * for <i>de</i>sensitizing an entire Window, the catch is that if you
     * <i>re</i>sensitize that same Window <b>every</b> Widget within it
     * will return to being sensitive; there's no "memory" of which might have
     * been insensitive before. Thus if you heavily use a mix of sensitive and
     * insensitive states in a Window and desensitize the whole thing while
     * carrying out input validation in a worker thread, you will be left with
     * the task of manually restoring the sensitive state of the various sub
     * components of your UI should you need to return back to that Window to
     * have the user re-enter something.
     * 
     * @param sensitive
     *            <code>true</code> to have the Widget respond as normal,
     *            and <code>false</code> to de-activate the Widget and have
     *            it "grey out".
     * @since 4.0.4
     */
    public void setSensitive(boolean sensitive) {
        GtkWidget.setSensitive(this, sensitive);
    }

    private static Tooltips globalTooltipsGroup;

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
    /*
     * This method anticipates one that is in the forthcoming GTK 2.12; at the
     * moment we are using this to wrap the deprecated GtkTooltips
     * functionality.</i>
     */
    public void setTooltipText(String text) {
        if (globalTooltipsGroup == null) {
            globalTooltipsGroup = new Tooltips();
        }
        GtkTooltips.setTip(globalTooltipsGroup, this, text, "");
    }

    /**
     * Get the underlying resource being used to power display of, and
     * interaction with, this Widget.
     * 
     * <p>
     * <b>If you're looking for the top Window in a Widget hierarchy, see</b>
     * {@link #getTopLevel() getTopLevel()}. This method is to get a
     * reference to the lower level GDK mechanisms used by this Widget, not to
     * navigate up a hierarchy of Widgets to find the top-level Window they
     * are packed into.
     * 
     * <p>
     * If what you need are the event handling facilities that go with Widgets
     * that have their own native resources, consider creating an
     * {@link EventBox EventBox} and putting this Widget into it.
     * 
     * <p>
     * <i>If you call this in a class where you're building Windows, then you
     * will probably end up having to use the fully qualified name</i>
     * <code>org.gnome.gdk.Window</code> <i>when declaring variables. That's
     * an unavoidable consequence of the class mapping algorithm we used, but
     * <code>GdkWindow</code> is the name of the underlying type being
     * returned, and so Window it is.</i>
     * 
     * @return the <code>org.gnome.gdk.Window</code> associated with this
     *         Widget, or <code>null</code> if this Widget is (as yet)
     *         without one.
     * @since 4.0.4
     */
    public org.gnome.gdk.Window getWindow() {
        return GtkWidgetOverride.getWindow(this);
    }

    /**
     * Adjust the background colour being used when drawing this Widget. This
     * leaves all other style properties unchanged.
     * 
     * <p>
     * If you need to change the background colour behind the text in an Entry
     * or TextView, see
     * {@link #modifyBase(Widget, StateType Color) modifyBase()}.
     * 
     * <p>
     * This is one of a family of "<code>modify</code>" methods; see
     * {@link #modifyStyle(Widget, RcStyle) modifyStyle()} for further details
     * about the interaction of the various theming and style mechanisms.
     * 
     * @since 4.0.5
     */
    public void modifyBackground(StateType state, Color color) {
        GtkWidget.modifyBg(this, state, color);
    }

    /**
     * A signal providing notification that the Widget has been obscured or
     * unobscured. To use this, go through the supplied <code>event</code>
     * parameter to get to the VisibilityState as follows:
     * 
     * <pre>
     * w.connect(new Widget.VISIBILITY_NOTIFY_EVENT() {
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
     * element's visibility.
     * 
     * @author Andrew Cowie
     * @since 4.0.5
     */
    public interface VISIBILITY_NOTIFY_EVENT extends GtkWidget.VISIBILITY_NOTIFY_EVENT
    {
        /**
         * Although this is an <var>event-signal</var>, this merely reports
         * information coming from the underlying X11 windowing system. Since
         * a window being obscured by another application's window is not
         * something you can control (short of requesting the Window holding
         * this Widget always be on-top), it's not entirely clear what good it
         * would do to block further emission of this signal. Return
         * <code>false</code>.
         */
        public boolean onVisibilityNotifyEvent(Widget source, EventVisibility event);
    }

    /**
     * Connect a <code>VISIBILITY_NOTIFY_EVENT</code> handler.
     */
    /*
     * It turns out that two things are necessary for this signal to work: 1)
     * GDK_VISIBILITY_NOTIFY_MASK must be set, and to do that 2) the GDK
     * window must have been assigned. by realize. We do these two steps in
     * the override.
     */
    public void connect(VISIBILITY_NOTIFY_EVENT handler) {
        GtkWidgetOverride.setEventsVisibility(this);
        GtkWidget.connect(this, handler);
    }
}
