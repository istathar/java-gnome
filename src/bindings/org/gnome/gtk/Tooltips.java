/*
 * Tooltips.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * Tooltips are notes that will be displayed if a user hovers the mouse
 * pointer over a Widget. They are usually used with controls such as Buttons
 * and Entries to brief the user about that Widget's function.
 * 
 * <p>
 * To add a tooltip to a Widget use the Tooltips
 * {@link Tooltips#setTip(Widget, String) setTip} method.
 * 
 * <p>
 * Note that the class Tooltips represents not only one tooltip; in fact it
 * represents a group of tooltips.
 * 
 * @author Thomas Schmitz
 * @since 4.0.4
 */
public class Tooltips extends Object
{
    protected Tooltips(long pointer) {
        super(pointer);
    }

    /**
     * Create a new Tooltips group.
     */
    public Tooltips() {
        super(GtkTooltips.createTooltips());
    }

    /**
     * Cause all tooltips to become active. Any widgets that have tooltips
     * associated with that Tooltips group will display their tips until they
     * are disabled with the {@link Tooltips#disable() disable()} method.
     * 
     * <p>
     * By default the tooltips of a new Tooltips group are enabled.
     */
    public void enable() {
        GtkTooltips.enable(this);
    }

    /**
     * Cause all tooltips to become inactive. Any Widgets that have tips
     * associated with this Tooltips group will no longer display their tips
     * until they are enabled again with {@link Tooltips#enable() enable()}
     * method.
     */
    public void disable() {
        GtkTooltips.disable(this);
    }

    /**
     * Set the time between the user moving the mouse over a Widget and the
     * Widget's tooltip appearing. In practice you should use a value between
     * <code>500</code> (the default) and <code>1000</code> as delay. Too
     * low a value will display the tooltip everytime the mouse moves over the
     * widget which rapidly gets annoying. If you use a value higher than one
     * second the user will propably think that there is no tooltip enabled
     * for this Widget and move on.
     * 
     * @param delay
     *            an integer value representing milliseconds.
     */
    /*
     * FIXME if there is a Desktop wide default setting, we should add a
     * mention of it the same way we did for Toolbar.
     */
    public void setDelay(int delay) {
        GtkTooltips.setDelay(this, delay);
    }

    /**
     * Adds a tooltip containing the given message to the given Widget.
     * 
     * @param widget
     *            the widget you wish to associate the tip with.
     * @param tipText
     *            a string containing the note.
     */
    /*
     * The GtkTooltips.setTip() method contains a second String parameter,
     * "private", but it seems not to do anything.
     */
    public void setTip(Widget widget, String tipText) {
        GtkTooltips.setTip(this, widget, tipText, "");
    }
}
