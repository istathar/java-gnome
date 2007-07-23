/*
 * CheckButton.java
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
 * A small box with a checkmark that can be toggled on or off.
 * 
 * <p>
 * See in particular the {@link ToggleButton.TOGGLED TOGGLED} Signal inherited
 * from ToggleButton to detect changes in state to the checkbox.
 * 
 * @author Sebastian Mancke
 * @author Andrew Cowie
 * @since 4.0.3
 */
public class CheckButton extends ToggleButton
{
    protected CheckButton(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new CheckButton.
     */
    public CheckButton() {
        super(GtkCheckButton.createCheckButton());
    }

    /**
     * Construct a new CheckButton containing a label with the given text. If
     * the text contains an underscore (<code>_<code>) it will be taken to be the
     * mnemonic for the Widget.
     */
    public CheckButton(String label) {
        super(GtkCheckButton.createCheckButtonWithMnemonic(label));
    }
}
