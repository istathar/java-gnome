/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2011 Operational Dynamics Consulting, Pty Ltd and Others
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
 * "Claspath Exception"), the copyright holders of this library give you
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
 * Switch is a widget that has two states: on or off. The user can control
 * which state should be active by clicking the empty area, or by dragging the
 * handle. <img src="Switch.png" class="snapshot" />
 * 
 * @author Guillaume Mazoyer
 * @since 4.1.2
 */
public class Switch extends Widget
{
    protected Switch(long pointer) {
        super(pointer);
    }

    /**
     * Create a new Switch widget.
     * 
     * @since 4.1.2
     */
    public Switch() {
        this(GtkSwitch.createSwitch());
    }

    /**
     * Returns the current state of the Switch.
     * 
     * @since 4.1.2
     */
    public boolean isActive() {
        return GtkSwitch.getActive(this);
    }

    /**
     * Sets the state of the Switch.
     * 
     * @since 4.1.2
     */
    public void setActive(boolean value) {
        GtkSwitch.setActive(this, value);
    }

    /**
     * The callback invoked when the Switch is activated.
     * 
     * Generally, when you will receive the callback, you will probably want
     * to check the <code>active</code> property with the Switch
     * {@link Switch#isActive() isActive()} method:
     * 
     * <pre>
     * final Switch switcher;
     * 
     * ...
     * 
     * switcher.connect(new Switch.NotifyActivated() {
     *     public void onNotifyActivated(Switch source) {
     *         if (source.isActive()) {
     *             System.out.printl("WiFi is on.");
     *         } else {
     *             System.out.printl("WiFi is off.");
     *         }
     *     }
     * });
     * </pre>
     * 
     * @since 4.1.2
     */
    public interface NotifyActivated extends GtkSwitch.NotifyActivatedSignal
    {
        public void onNotifyActivated(Switch source);
    }

    /**
     * Hookup the <code>Switch.NotifyActivated</code> signal that will be
     * emitted when the Switch will be activated.
     * 
     * @since 4.1.2
     */
    public void connect(Switch.NotifyActivated handler) {
        GtkSwitchOverride.setActivatedSignal(this);
        GtkSwitch.connect(this, handler, false);
    }
}