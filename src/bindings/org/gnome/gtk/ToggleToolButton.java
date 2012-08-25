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
 * A ToggleToolButton is a ToolButton that retains its state.
 * 
 * <p>
 * The usual usage of such kind of ToolButtons is to let users enable or
 * disable an application feature.
 * 
 * <p>
 * You can check the current state of the ToggleToolButton using the
 * {@link #getActive()} method. Additionally you can connect to the
 * {@link ToggleToolButton.TOGGLED TOGGLED} signal to respond to changes in
 * the <var>active</var> state.
 * 
 * <p>
 * Beware that ToogleToolButtons have sometimes fared poorly in usability
 * tests. The problem is that they are an exception: most ToolItems are
 * ToolButtons, and most of them you just press and something immediately
 * happens. Since there is no visual distinction between a ToolButton and an
 * inactive ToggleToolButton, users don't expect that this particular ToolItem
 * is going to behave differently and maintain its state. It's not a huge
 * problem (ie, if the user is paying attention they'll soon find out that the
 * button is a toggle), but it's something that usually takes a few iterations
 * before the user learns. Something to keep in mind.
 * 
 * @author Vreixo Formoso
 * @author Andrew Cowie
 * @since 4.0.4
 */
public class ToggleToolButton extends ToolButton
{
    protected ToggleToolButton(long pointer) {
        super(pointer);
    }

    /**
     * Create a new ToggleToolButton.
     * 
     * @since 4.0.4
     */
    public ToggleToolButton() {
        super(GtkToggleToolButton.createToggleToolButton());
    }

    /**
     * Create a new ToggleToolButton based on one of the stock items.
     * 
     * @since 4.0.4
     */
    public ToggleToolButton(Stock stock) {
        super(GtkToggleToolButton.createToggleToolButtonFromStock(stock.getStockId()));
    }

    /**
     * Set whether this ToggleToolButton is active, that is, whether it
     * appears pressed or not. Calling this will emit a <code>TOGGLED</code>
     * signal.
     * 
     * @since 4.0.4
     */
    public void setActive(boolean isActive) {
        GtkToggleToolButton.setActive(this, isActive);
    }

    /**
     * Is the ToggleToolButton currently activated on?
     * 
     * @since 4.0.4
     */
    public boolean getActive() {
        return GtkToggleToolButton.getActive(this);
    }

    /**
     * Signal indicating the ToggleToolButton state has changed.
     * 
     * @since 4.0.4
     */
    public interface Toggled extends GtkToggleToolButton.ToggledSignal
    {
        public void onToggled(ToggleToolButton source);
    }

    /**
     * Connect a handler to the <code>ToggleToolButton.Toggled</code> signal.
     * 
     * @since 4.0.4
     */
    public void connect(ToggleToolButton.Toggled handler) {
        GtkToggleToolButton.connect(this, handler, false);
    }
}
