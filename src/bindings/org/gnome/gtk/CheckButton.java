/*
 * CheckButton.java
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
 * CheckButton, a toggle button with a check box.
 * 
 * 
 * @author Sebastian Mancke
 * @since 4.0.3
 */
public class CheckButton extends ToggleButton
{
    protected CheckButton(long pointer) {
        super(pointer);
    }

    /**
     * Constructs a new CheckButton
     */
    public CheckButton() {
        super(GtkCheckButton.createCheckButton());
    }

    /**
     * Constructs a new CheckButton with a label.
     * 
     * The label may contain underscores (<code>_<code>) to indicate
     * the mnemonic for the Button.
     */
    public CheckButton(String label) {
        super(GtkCheckButton.createCheckButtonWithMnemonic(label));
    }    
}
