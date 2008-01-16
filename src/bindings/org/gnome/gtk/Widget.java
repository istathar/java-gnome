/*
 * Widget.java
 *
 * Copyright (c) 2006-2008 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.gnome.gdk.Color;
import org.gnome.gdk.Event;
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
     * Cause this Widget to be activated. This has the same effect as when the
     * user presses the <code>Return</code> key while the Widget is in
     * focus. Calling this method on a {@link ToggleButton} will toggle its
     * state, for example. Whatever signals would normally result from the
     * user activating this Widget by hand will be emitted.
     * 
     * <p>
     * Note that this method only works if this Widget is <var>activatable</var>;
     * not all are, making this more an optional characteristic that some, but
     * not all Widgets share.
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
     * Hook up a handler to receive <code>FOCUS_OUT_EVENT</code> events on
     * this Widget
     * 
     * @since 4.0.2
     */
    public void connect(FOCUS_OUT_EVENT handler) {
        GtkWidget.connect(this, handler);
    }

    /**
     * Signal emitted when focus enters this Widget. See
     * {@link Widget.FOCUS_OUT_EVENT FOCUS_OUT_EVENT}.
     * 
     * @author Andrew Cowie
     * @since 4.0.6
     */
    public interface FOCUS_IN_EVENT extends GtkWidget.FOCUS_IN_EVENT
    {
        public boolean onFocusInEvent(Widget source, EventFocus event);
    }

    /**
     * Hook up a handler to receive <code>FOCUS_IN_EVENT</code> signals.
     * 
     * @since 4.0.6
     */
    public void connect(FOCUS_IN_EVENT handler) {
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
        /**
         * As with other event signals, returning <code>false</code> means
         * "I didn't [fully] handle this signal, so proceed with the next (ie,
         * usually the default) handler" whereas if you return
         * <code>true</code> you mean "I have handled this event, and wish
         * to stop further emission of the signal".
         */
        public boolean onKeyPressEvent(Widget source, EventKey event);
    }

    /**
     * Hook up a handler to receive <code>KEY_PRESS_EVENT</code> signals on
     * this Widget. For general typing this is the one you want, but for
     * critical events (like pressing <b>Enter</b> to activate a Button that
     * is going to delete things, you might want to postpone action until
     * <code>KEY_RELEASE_EVENT</code>.
     * 
     * @since 4.0.3
     */
    public void connect(KEY_PRESS_EVENT handler) {
        GtkWidget.connect(this, handler);
    }

    /**
     * Handler interface for key release events. Calling
     * {@link EventKey#getKeyval() getKeyval()} on the <code>event</code>
     * parameter gets you to the constant representing the key that was
     * actually typed.
     * 
     * @since 4.0.3
     */
    public interface KEY_RELEASE_EVENT extends GtkWidget.KEY_RELEASE_EVENT
    {
        /**
         * (See the comment at
         * {@link Widget.KEY_PRESS_EVENT#onKeyPressEvent(Widget, EventKey) KEY_PRESS_EVENT}
         * to understand the return value)
         */
        public boolean onKeyReleaseEvent(Widget source, EventKey event);
    }

    /**
     * Hook up a handler to receive <code>KEY_RELEASE_EVENT</code> signals
     * on this Widget
     * 
     * @since 4.0.3
     */
    public void connect(KEY_RELEASE_EVENT handler) {
        GtkWidget.connect(this, handler);
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
     * {@link #getToplevel() getToplevel()}. This method is to get a
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
     * an unavoidable consequence of the class mapping algorithm we used:
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
     * Set the colour used for text rendered by this Widget. This is the
     * foreground colour; to change the background colour behind text use
     * {@link #modifyBase(StateType, Color) modifyBase()}.
     * 
     * <p>
     * This is one of a family of "<code>modify</code>" methods; see
     * {@link #modifyStyle(Widget, RcStyle) modifyStyle()} for further details
     * about the interaction of the various theming and style mechanisms.
     * 
     * @since 4.0.6
     */
    public void modifyText(StateType state, Color color) {
        GtkWidget.modifyText(this, state, color);
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
     * element's visibility. See also {@link UNMAP_EVENT UNMAP_EVENT} for a
     * discussion of how this can be used to actively toggle the presentation
     * of a Window to the user.
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
         * <code>false</code>!
         */
        public boolean onVisibilityNotifyEvent(Widget source, EventVisibility event);
    }

    /**
     * Connect a <code>VISIBILITY_NOTIFY_EVENT</code> handler.
     * 
     * @since 4.0.5
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

    /**
     * The signal emitted when a Window becomes invisible. This happens in a
     * variety of scenarios, notably when the Window is minimized, when you
     * change workspaces, and as a Window is being destroyed.
     * 
     * <p>
     * In combination with
     * {@link VISIBILITY_NOTIFY_EVENT VISIBILITY_NOTIFY_EVENT}, this can be
     * used to detect whether a Window is actually currently presented to the
     * top of the stack and visible to the user:
     * 
     * <pre>
     * private boolean up = false;
     * ...
     * final Window w;
     * final Button b;
     * ...
     * w.connect(new Widget.VISIBILITY_NOTIFY_EVENT() {
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
     * w.connect(new Widget.UNMAP_EVENT() {
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
     * b.connect(new Button.CLICKED() {
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
     * Note that you don't need <code>MAP</code> because the the
     * <code>VISIBILITY_NOTIFY_EVENT</code> will be tripped if you come back
     * to the workspace the Window is already on.
     * 
     * @author Andrew Cowie
     * @author Ryan Lortie
     * @since 4.0.5
     */
    public interface UNMAP_EVENT extends GtkWidget.UNMAP_EVENT
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
     * Connect a <code>UNMAP_EVENT</code> handler.
     * 
     * @since 4.0.5
     */
    public void connect(UNMAP_EVENT handler) {
        GtkWidget.connect(this, handler);
    }

    /**
     * Does this Widget currently have the keyboard focus?
     * 
     * <p>
     * This can be quite useful when one Widget takes action in a signal
     * handler which changes the state of another Widget. Take for example two
     * related Entry Widgets. The second Entry's <code>CHANGED</code> signal
     * will fire when the first Entry's <code>CHANGED</code> handler calls
     * <code>second.setText()</code>; if it changes the first Entry then
     * you have an infinite loop on your hands. By checking for <var>has-focus</var>
     * at the beginning of both handlers, then only the Widget that the user
     * changed will carry out it's logic; the other will realize it doesn't
     * have focus and can quickly pass.
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
    public interface HIDE extends GtkWidget.HIDE
    {
        void onHide(Widget source);
    }

    /**
     * Connect a <code>HIDE</code> handler.
     * 
     * @since 4.0.6
     */
    public void connect(Widget.HIDE handler) {
        GtkWidget.connect(this, handler);
    }

    /**
     * Make this Widget have the keyboard focus for the Window it is within.
     * 
     * <p>
     * Obviously, if this is going to actually have affect, this Widget needs
     * to be <i>in</i> a Window. Furthermore, the Widget needs to be <i>able</i>
     * to take input focus, that is, it must have the <var>can-focus</var>
     * property set (which is inherent to the particular Widget subclass, not
     * something you can change).
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
     * the X server, and the result ifs the size allocation for the Window as
     * a whole. The Window proceeds to inform each Container packed into it
     * how much space it has been allocated, leaving it to the Containers to
     * in turn allocate space to each of its children.
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
     * Incidentally, use
     * {@link Window#setDefaultSize(int, int) setDefaultSize()} for top level
     * Windows, as that method still allows a user to make the Window smaller
     * than the specified default.
     * 
     * @since 4.0.6
     */
    public void setSizeRequest(int width, int height) {
        if ((width < -1) || (height < -1)) {
            throw new IllegalArgumentException("width and height need to be >= -1");
        }
        GtkWidget.setSizeRequest(this, width, height);
    }

    /**
     * Get the details of the rectangle that represents the size that the
     * windowing system down to GTK on down to the parent containers of this
     * Widget have allocated to it. Note that the Widget must already have
     * been realized for the request-allocation cycle to have taken place (ie,
     * the top level Window and all its children must have been
     * <code>show()</code>n. In some circumstances the main loop may need
     * to have iterated).
     * 
     * @author Andrew Cowie
     * @since 4.0.6
     */
    public Allocation getAllocation() {
        final Allocation result;

        result = GtkWidgetOverride.getAllocation(this);
        /*
         * We are making a live reference to the GtkAllocation struct member
         * in the GtkWidget class, so we need to make sure that our Allocation
         * Proxy does not survive longer than the Widget. We use this back
         * reference for this purpose.
         */
        result.widget = this;

        return result;
    }
}
