/*
 * ToggleButton.java
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
 * A TottleButton is a Button which retains its state.
 * 
 * 
 * @author Sebastian Mancke
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
     * @param active The new state of the ToggleButton
     */
    public void setActive(boolean active) {
        GtkToggleButton.setActive(this, active);
    }

    /**
     * Returns the current state of the ToggleButton
     * @return returns <code>true</code> if the ToggleButton is pressed, <code>false</code> otherwise
     */
    public boolean getActive() {
        return GtkToggleButton.getActive(this);
    }

    /** 
     * The handler interface for an toggle event.
     */
    public interface TOGGLED extends GtkToggleButton.TOGGLED {
        // TODO: should we repeat the interface here?
        // public void onToggled(ToggleButton sourceObject);
    }
    
    /** 
     * Connect an {@see TOGGLED} handler to the widget.
     */
    public void connect(TOGGLED handler) {
        GtkToggleButton.connect(this, handler);
    }
}
