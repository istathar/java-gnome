/*
 * ToggleButton.java
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
 * A ToggleButton is a Button which retains its state.
 * 
 * @author Sebastian Mancke
 * @author Andrew Cowie
 * @since 4.0.3
 */
public class ToggleButton extends Button
{
    protected ToggleButton(long pointer) {
        super(pointer);
    }

    /**
     * Constructs a new ToggleButton
     */
    public ToggleButton() {
        super(GtkToggleButton.createToggleButton());
    }

    /**
     * Constructs a new ToggleButton with a label.
     * 
     * The label may contain underscores (<code>_<code>) to indicate
     * the mnemonic for the Button.
     */
    public ToggleButton(String label) {
        super(GtkToggleButton.createToggleButtonWithMnemonic(label));
    }

    /**
     * Set the state of the ToggleButon.
     * 
     * @param active
     *            The new state of the ToggleButton
     */
    public void setActive(boolean active) {
        GtkToggleButton.setActive(this, active);
    }

    /**
     * Returns the current state of the ToggleButton
     * 
     * @return returns <code>true</code> if the ToggleButton is pressed,
     *         <code>false</code> otherwise
     */
    public boolean getActive() {
        return GtkToggleButton.getActive(this);
    }

    /**
     * Signal indicating the Button has changed state.
     */
    public interface TOGGLED extends GtkToggleButton.TOGGLED
    {
        public void onToggled(ToggleButton source);
    }

    public void connect(TOGGLED handler) {
        GtkToggleButton.connect(this, handler, false);
    }
}
