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
 * To add a tooltip to a Widget use the
 * {@link Widget#setTooltipText(String) setTooltipText()} method available on
 * Widget.
 * 
 * <p>
 * Note that the class Tooltips represents not only one tooltip; in fact it
 * represents a group of tooltips.
 * 
 * <p>
 * <b>WARNING: This class will not have public methods in java-gnome 4.0.4,
 * and will be removed soon thereafter</b>.
 * 
 * <p>
 * <i>The underlying <code>GtkTooltips</code> class is deprecated in favour
 * of a new API in <code>GtkTooltip</code>, forthcoming in GTK 2.12.</i>
 * 
 * @author Thomas Schmitz
 */
public final class Tooltips extends Object
{
    protected Tooltips(long pointer) {
        super(pointer);
    }

    /**
     * Create a new Tooltips group.
     * 
     * @deprecated
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
     * 
     * @deprecated
     */
    public void enable() {
        GtkTooltips.enable(this);
    }

    /**
     * Cause all tooltips to become inactive. Any Widgets that have tips
     * associated with this Tooltips group will no longer display their tips
     * until they are enabled again with {@link Tooltips#enable() enable()}
     * method.
     * 
     * @deprecated
     */
    public void disable() {
        GtkTooltips.disable(this);
    }

    /**
     * Set the time between the user moving the mouse over a Widget and the
     * Widget's tooltip appearing. In practise you should use a value between
     * <code>500</code> (the default) and <code>1000</code> as delay. Too
     * low a value will display the tooltip every time the mouse moves over
     * the widget which rapidly gets annoying. If you use a value higher than
     * one second the user will probably think that there is no tooltip
     * enabled for this Widget and move on.
     * 
     * @param delay
     *            an integer value representing milliseconds.
     * @deprecated
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
     * @deprecated
     */
    /*
     * The GtkTooltips.setTip() method contains a second String parameter,
     * "private", but it seems not to do anything.
     */
    public void setTip(Widget widget, String tipText) {
        GtkTooltips.setTip(this, widget, tipText, "");
    }
}
