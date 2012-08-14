/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
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
     * 
     * @since 4.0.3
     */
    public ToggleButton() {
        super(GtkToggleButton.createToggleButton());
    }

    /**
     * Constructs a new ToggleButton with a label.
     * 
     * <p>
     * The label may contain underscores (<code>_<code>) to indicate
     * the mnemonic for the Button.
     * 
     * @since 4.0.3
     */
    public ToggleButton(String label) {
        super(GtkToggleButton.createToggleButtonWithMnemonic(label));
    }

    /**
     * Set the state of the ToggleButon.
     * 
     * @param active
     *            The new state of the ToggleButton
     * @since 4.0.3
     */
    public void setActive(boolean active) {
        GtkToggleButton.setActive(this, active);
    }

    /**
     * Returns the current state of the ToggleButton
     * 
     * @return returns <code>true</code> if the ToggleButton is pressed,
     *         <code>false</code> otherwise.
     * @since 4.0.3
     */
    public boolean getActive() {
        return GtkToggleButton.getActive(this);
    }

    /**
     * Signal indicating the Button has changed state.
     * 
     * @since 4.0.3
     */
    public interface Toggled extends GtkToggleButton.ToggledSignal
    {
        public void onToggled(ToggleButton source);
    }

    /**
     * Hook up a <code>ToggleButton.Toggled</code> signal handler.
     * 
     * @since 4.0.3
     */
    public void connect(ToggleButton.Toggled handler) {
        GtkToggleButton.connect(this, handler, false);
    }
}
