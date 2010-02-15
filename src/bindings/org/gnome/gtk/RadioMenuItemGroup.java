/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
 * A group of RadioMenuItems sharing the mutually exclusive relationship that
 * only one of the group member can be selected at a time.
 * 
 * <p>
 * This is the mechanism by which you indicate a series of RadioMenuItems will
 * be associated:
 * 
 * <p>
 * This class in an adapted copy of {@link RadioButtonGroup}.
 * 
 * <pre>
 * RadioMenuItemGroup group;
 * RadioMenuItem one, two, three, ...;
 * 
 * group = new RadioMenuItemGroup();
 * one = new RadioMenuItem(group, &quot;One&quot;);
 * two = new RadioMenuItem(group, &quot;Two&quot;);
 * three = new RadioMenuItem(group, &quot;Three&quot;);
 * </pre>
 * 
 * @author Guillaume Mazoyer
 * @since 4.0.15
 */
/*
 * This acts as a wrapper to allow us to avoid the complexity of the GSList
 * used as GtkRadioMenuItem's group property and the fact that its
 * constructors are polluted with C API sugar.
 */
public class RadioMenuItemGroup
{
    RadioMenuItem member;

    /**
     * Create a new group of related RadioMenuItems.
     * 
     * @since 4.0.15
     */
    public RadioMenuItemGroup() {
        member = null;
    }

    RadioMenuItem getMember() {
        return member;
    }

    void setMember(RadioMenuItem item) {
        member = item;
    }

    private void checkState() {
        if (member == null) {
            throw new IllegalStateException(
                    "Can't use a RadioMenuItemGroup until you've added one or more RadioMenuItems to it");
        }
    }

    /**
     * Signal that is emitted each time the active RadioMenuItem is a group is
     * changed.
     * 
     * <p>
     * This happens either by clicking in a different option on the group, by
     * using the key combination <b><code>Alt+</code><i>mnemonic</i></b>. It
     * can also be triggered programmatically by calling the
     * {@link CheckMenuItem#setActive(boolean) setActive()} method.
     * 
     * @author Guillaume Mazoyer
     * @since <span style="color: red">Unstable</span>
     */
    public interface GroupToggled
    {
        /**
         * Called when user changes the active RadioMenuItem in a group.
         * 
         * @param source
         *            The RadioMenuItem that is now active.
         */
        public void onGroupToggled(RadioMenuItem source);
    }

    /**
     * Hook up a handler to the <code>RadioMenuItemGroup.GroupToggled</code>
     * signal.
     * 
     * @since <span style="color: red">Unstable</span>
     */
    public void connect(RadioMenuItemGroup.GroupToggled handler) {
        ToggleHandler toggleHandler = new ToggleHandler(handler);
        RadioMenuItem[] group = GtkRadioMenuItem.getGroup(member);
        for (int i = 0; i < group.length; ++i) {
            group[i].connect(toggleHandler);
        }
    }

    /*
     * Custom handler needed to implement RadioMenuItemGroup.GroupToggled
     * custom signal.
     */
    private static final class ToggleHandler implements RadioMenuItem.Toggled
    {
        private final RadioMenuItemGroup.GroupToggled handler;

        private ToggleHandler(RadioMenuItemGroup.GroupToggled handler) {
            this.handler = handler;
        }

        public void onToggled(CheckMenuItem source) {
            if (source.getActive()) {
                handler.onGroupToggled((RadioMenuItem) source);
            }
        }
    }

    /**
     * Indicate which RadioMenuItem in this RadioMenuItemGroup is currently
     * selected active.
     * 
     * <p>
     * This will return <code>null</code> if no RadioMenuItem is selected, but
     * note that this is generally indicative of something wrong with the
     * programming; generally a RadioMenuItemGroup should always have at least
     * one RadioMenuItem selected.
     * 
     * @since 4.0.15
     */
    public RadioMenuItem getActive() {
        final RadioMenuItem[] group;
        checkState();

        group = GtkRadioMenuItem.getGroup(member);

        for (int i = 0; i < group.length; ++i) {
            if (group[i].getActive()) {
                return group[i];
            }
        }
        return null;
    }
}
