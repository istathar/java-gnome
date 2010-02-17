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
 * A group of RadioActions sharing the mutually exclusive relationship that
 * only one of the group member can be selected at a time.
 * 
 * <p>
 * This is the mechanism by which you indicate a series of RadioActions will
 * be associated:
 * 
 * <p>
 * This class in an adapted copy of {@link RadioButtonGroup}.
 * 
 * @author Guillaume Mazoyer
 * @since 4.0.15
 */
/*
 * This acts as a wrapper to allow us to avoid the complexity of the GSList
 * used as GtkRadioAction's group property and the fact that its constructors
 * are polluted with C API sugar.
 */
public class RadioActionGroup
{
    RadioAction member;

    int value;

    /**
     * Create a new group of related RadioActions.
     * 
     * @since 4.0.15
     */
    public RadioActionGroup() {
        member = null;
        value = -1;
    }

    RadioAction getMember() {
        return member;
    }

    void setMember(RadioAction item) {
        member = item;
    }

    int getNextValue() {
        return ++value;
    }

    private void checkState() {
        if (member == null) {
            throw new IllegalStateException(
                    "Can't use a RadioActionGroup until you've added one or more RadioActions to it");
        }
    }

    /**
     * Signal that is emitted each time the active RadioAction is a group is
     * changed.
     * 
     * @author Guillaume Mazoyer
     * @since <span style="color: red">Unstable</span>
     */
    public interface GroupToggled
    {
        /**
         * Called when user changes the active RadioAction in a group.
         * 
         * @param source
         *            The RadioAction that is now active.
         */
        public void onGroupToggled(RadioAction source);
    }

    /**
     * Hook up a handler to the <code>RadioActionGroup.GroupToggled</code>
     * signal.
     * 
     * @since <span style="color: red">Unstable</span>
     */
    public void connect(RadioActionGroup.GroupToggled handler) {
        ToggleHandler toggleHandler = new ToggleHandler(handler);
        RadioAction[] group = GtkRadioAction.getGroup(member);
        for (int i = 0; i < group.length; ++i) {
            group[i].connect(toggleHandler);
        }
    }

    /*
     * Custom handler needed to implement RadioActionGroup.GroupToggled custom
     * signal.
     */
    private static final class ToggleHandler implements RadioAction.Toggled
    {
        private final RadioActionGroup.GroupToggled handler;

        private ToggleHandler(RadioActionGroup.GroupToggled handler) {
            this.handler = handler;
        }

        public void onToggled(ToggleAction source) {
            if (source.getActive()) {
                handler.onGroupToggled((RadioAction) source);
            }
        }
    }

    /**
     * Indicate which RadioAction in this RadioActionGroup is currently
     * selected active.
     * 
     * <p>
     * This will return <code>null</code> if no RadioAction is selected, but
     * note that this is generally indicative of something wrong with the
     * programming; generally a RadioActionGroup should always have at least
     * one RadioAction selected.
     * 
     * @since 4.0.15
     */
    public RadioAction getActive() {
        final RadioAction[] group;
        checkState();

        group = GtkRadioAction.getGroup(member);

        for (int i = 0; i < group.length; ++i) {
            if (group[i].getActive()) {
                return group[i];
            }
        }
        return null;
    }
}
