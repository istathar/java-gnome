/*
 * ToggleToolButton.java
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
 * A ToggleToolButton is a ToolButton that retain its state.
 * 
 * <p>
 * The usual usage of such kind of ToolButtons is to let users enable or
 * disable an application feature.
 * 
 * <p>
 * You can check the current state of the ToggleToolButton using the
 * {@link #getActive()} method. Additionally you can connect to the
 * {@link TOGGLED} signal to respond to changes in the <i>active</i> state.
 * 
 * @author Vreixo Formoso
 * @since 4.0.4
 */
public class ToggleToolButton extends ToolButton
{
    protected ToggleToolButton(long pointer) {
        super(pointer);
    }

    /**
     * Create a new ToggleToolButton.
     */
    public ToggleToolButton() {
        super(GtkToggleToolButton.createToggleToolButton());
    }

    /**
     * Create a new ToggleToolButton from a stock item.
     */
    public ToggleToolButton(StockId stockId) {
        super(GtkToggleToolButton.createToggleToolButtonFromStock(stockId.getStockId()));
    }

    /**
     * Sets the <i>active</i> state of the ToggleToolButton.
     */
    public void setActive(boolean isActive) {
        GtkToggleToolButton.setActive(this, isActive);
    }

    /**
     * Get the <i>active</i> state of the ToggleToolButton.
     */
    public boolean getActive() {
        return GtkToggleToolButton.getActive(this);
    }

    /**
     * Signal indicating the ToggleToolButton state has changed.
     */
    public interface TOGGLED extends GtkToggleToolButton.TOGGLED
    {
        public void onToggled(ToggleToolButton source);
    }

    /**
     * Connect a handler to the <code>TOGGLED</code> signal.
     */
    public void connect(TOGGLED handler) {
        GtkToggleToolButton.connect(this, handler);
    }
}
