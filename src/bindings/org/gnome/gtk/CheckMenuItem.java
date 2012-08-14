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
 * A MenuItem that maintains a binary state.
 * 
 * <p>
 * A CheckMenuItem is just like a MenuItem, but additionally it displays a
 * "check box" alongside the normal Label, indicating the state of the boolean
 * value it holds. When that value is set to <code>true</code>, the item is
 * <i>active</i> and the box shows the check mark.
 * 
 * <p>
 * You can use a CheckMenuItem as a way to allow users of your application
 * enable or disable a feature in an application. This is often used within to
 * toggle options, for example to let the user hide an optional Widget of your
 * user interface.
 * 
 * <p>
 * The active state is switched automatically when the user activates the
 * MenuItem. You can access the current state with the {@link #getActive()
 * getActive()} method. And while you can still connect to the
 * <code>MenuItem.Active</code> signal, CheckMenuItem provides the
 * {@link CheckMenuItem.Toggled} signal, emitted when the active state
 * changes.
 * 
 * <p>
 * See the {@link MenuItem parent} class for further details general to all
 * MenuItems.
 * 
 * @author Vreixo Formoso
 * @since 4.0.4
 */
/*
 * TODO need screenshot.
 */
public class CheckMenuItem extends MenuItem
{
    protected CheckMenuItem(long pointer) {
        super(pointer);
    }

    /**
     * Construct a CheckMenuItem
     * 
     * @since 4.0.4
     */
    public CheckMenuItem() {
        super(GtkCheckMenuItem.createCheckMenuItem());
    }

    /**
     * Construct a CheckMenuItem with a given text Label. The text may contain
     * underscores (<code>_<code>) which, if present, will indicate the
     * mnemonic which will activate that CheckMenuItem directly if that key is
     * pressed while viewing the Menu.
     * 
     * @since 4.0.4
     */
    public CheckMenuItem(String mnemonicLabel) {
        super(GtkCheckMenuItem.createCheckMenuItemWithMnemonic(mnemonicLabel));
    }

    /**
     * Construct a CheckMenuItem with a given text label, and additionally
     * connect a handler to its <code>CheckMenuItem.Toggled</code> signal.
     * This affords you the convenience of being able to add a MenuItem fairly
     * compactly:
     * 
     * <pre>
     * editMenu.append(new MenuItem(&quot;_Paste&quot;, new CheckMenuItem.Toggled() {
     *     public void onToggled(MenuItem source) {
     *         ...
     *     }
     * }));
     * </pre>
     * 
     * @since 4.0.4
     */
    public CheckMenuItem(String mnemonicLabel, CheckMenuItem.Toggled handler) {
        super(GtkCheckMenuItem.createCheckMenuItemWithMnemonic(mnemonicLabel));
        connect(handler);
    }

    /**
     * Set the active state of the Item. This is switched automatically when
     * the user activates (clicks) the menu item, but in some situations you
     * will want to change it manually.
     * 
     * @since 4.0.4
     */
    public void setActive(boolean isActive) {
        GtkCheckMenuItem.setActive(this, isActive);
    }

    /**
     * Retrieve the active state of the item.
     * 
     * @since 4.0.4
     */
    public boolean getActive() {
        return GtkCheckMenuItem.getActive(this);
    }

    /**
     * Set the <var>inconsistent</var> state. This refers to an additional
     * third state meaning that currently it cannot be decided what is the
     * active state of the item.
     * 
     * <p>
     * Think, for example, in a text editor application, in which a
     * CheckMenuItem is used to choose between a bold or a normal font. If the
     * user selects a range of text where both normal and bold fonts are being
     * used, the state is inconsistent, and we want to mark it in a different
     * way.
     * 
     * <p>
     * However, note that, while such property can be really useful in a
     * {@link ToggleButton}, its utility in a CheckMenuItem is really unclear.
     * 
     * <p>
     * Notice also that this property only affects visual appearance, it
     * doesn't affect the semantics of the Widget.
     * 
     * @since 4.0.4
     */
    public void setInconsistent(boolean setting) {
        GtkCheckMenuItem.setInconsistent(this, setting);
    }

    /**
     * Get the <var>inconsistent</var> state.
     * 
     * @see #setInconsistent(boolean)
     * @since 4.0.4
     */
    public boolean getInconsistent() {
        return GtkCheckMenuItem.getInconsistent(this);
    }

    /**
     * The handler interface for a change in the active state. This is
     * triggered when the active state changes, either when the user activates
     * the MenuItem, or when it is changed with
     * {@link CheckMenuItem#setActive(boolean) setActive()}.
     * 
     * @see MenuItem.Activate
     * @since 4.0.4
     */
    public interface Toggled extends GtkCheckMenuItem.ToggledSignal
    {
        void onToggled(CheckMenuItem source);
    }

    /**
     * Connect a <code>CheckMenuItem.Toggled</code> handler to the Widget.
     * 
     * @since 4.0.4
     */
    public void connect(CheckMenuItem.Toggled handler) {
        GtkCheckMenuItem.connect(this, handler, false);
    }
}
