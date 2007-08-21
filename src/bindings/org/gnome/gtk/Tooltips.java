/*
 * Tooltips.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * Tooltips are notes that will be displayed if a user moves the mouse pointer
 * over a widget. They are usually used to brief the user over the function of
 * the relevant widget.
 * 
 * <p>
 * To add a tooltip to a widget use the Tooltips
 * {@link Tooltips#setTip(Widget, String) setTip} methode.
 * 
 * <p>
 * Note that the class Tooltips represents not only one tooltip, in fact it
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
     * Creates a new Tooltips instance.
     */
    public Tooltips() {
        super(GtkTooltips.createTooltips());
    }

    /**
     * Causes all tooltips to become active. Any widgets that have tooltips
     * associated with that Tooltips group will display their tips until they
     * are disabled with the {@link Tooltips#disable() disable} methode. By
     * default the tooltips of the Tooltips group are enabled.
     */
    public void enable() {
        GtkTooltips.enable(this);
    }

    /**
     * Causes all tooltips to become inactive. Any widgets that have tips
     * associated with that Tooltips group will no longer display their tips
     * until they are enabled again with {@link Tooltips#enable() enable}
     * methode.
     */
    public void disable() {
        GtkTooltips.disable(this);
    }

    /**
     * Sets the time between the user moving the mouse over a widget and the
     * widget's tooltip appearing. Usually you should use a value between 500
     * and 1000 as delay, because a shorter value will display the tooltip
     * everytime the mouse moves over the widget. If you use a higher value as
     * 1000 the user propably think that there is no tooltip enabled at this
     * widget.
     * 
     * @param delay
     *            an integer value representing milliseconds.
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
     * The GtkTooltips.setTip() methode contains a second String parameter,
     * but it seams not to have a function.
     */
    public void setTip(Widget widget, String tipText) {
        GtkTooltips.setTip(this, widget, tipText, new String());
    }
}
