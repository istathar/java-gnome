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

/*
 * This class was originally RadioButtonGroup and specifically for instances
 * of RadioButton only, it is now extended to everything that displays
 * "radio" behaviour.
 */

import org.gnome.glib.Object;

/**
 * A group of RadioActions or Radio widgets sharing the mutually exclusive
 * relationship that only one of the group member can be selected at a time.
 * 
 * <p>
 * A group can contain <b>only one</b> type of object. It must be
 * {@link RadioAction}, {@link RadioButton}, {@link RadioMenuItem} or
 * {@link RadioToolButton}.
 * 
 * @author Andrew Cowie
 * @author Mario Torre
 * @author Guillaume Mazoyer
 * @since 4.0.15
 */
/*
 * This acts as a wrapper to allow us to avoid the complexity of the GSList
 * used as GtkRadioThing's group property and the fact that its constructors
 * are polluted with C API sugar.
 */
public class RadioGroup
{
    Object member;

    public RadioGroup() {
        member = null;
    }

    void setMember(Object object) {
        if ((object instanceof RadioAction) || (object instanceof RadioButton)
                || (object instanceof RadioMenuItem) || (object instanceof RadioToolButton)) {
            member = object;
        } else {
            throw new IllegalArgumentException("object not a RadioThing");
        }

    }

    Object getMember() {
        return member;
    }

    private void checkState() {
        if (member == null) {
            throw new IllegalStateException(
                    "Can't use a RadioGroup until you've added one or more Radio widgets or RadioActions to it");
        }
    }

    /**
     * Signal that is emitted each time the active action or widget of the
     * group is changed.
     * 
     * @author Guillaume Mazoyer
     * @since 4.0.15
     */
    public interface GroupToggled
    {
        /**
         * Called when user changes the active action or widget in a group.
         * 
         * @param source
         *            The action or widget that is now active.
         */
        public void onGroupToggled(Object source);
    }

    /**
     * Hook up a handler to the <code>RadioGroup.GroupToggled</code> signal.
     * 
     * @since 4.0.15
     */
    public void connect(RadioGroup.GroupToggled handler) {
        if (member instanceof RadioAction) {
            ToggleActionHandler toggleHandler = new ToggleActionHandler(handler);
            RadioAction[] group = GtkRadioAction.getGroup((RadioAction) member);

            for (RadioAction action : group) {
                action.connect(toggleHandler);
            }
        } else if (member instanceof RadioButton) {
            ToggleButtonHandler toggleHandler = new ToggleButtonHandler(handler);
            RadioButton[] group = GtkRadioButton.getGroup((RadioButton) member);

            for (RadioButton button : group) {
                button.connect(toggleHandler);
            }
        } else if (member instanceof RadioMenuItem) {
            ToggleMenuItemHandler toggleHandler = new ToggleMenuItemHandler(handler);
            RadioMenuItem[] group = GtkRadioMenuItem.getGroup((RadioMenuItem) member);

            for (RadioMenuItem item : group) {
                item.connect(toggleHandler);
            }
        }
    }

    /*
     * Custom handler needed to implement RadioGroup.GroupToggled custom
     * signal for RadioAction.
     */
    private static final class ToggleActionHandler implements ToggleAction.Toggled
    {
        private final RadioGroup.GroupToggled handler;

        private ToggleActionHandler(RadioGroup.GroupToggled handler) {
            this.handler = handler;
        }

        public void onToggled(ToggleAction source) {
            if (source.getActive()) {
                handler.onGroupToggled(source);
            }
        }
    }

    /*
     * Custom handler needed to implement RadioGroup.GroupToggled custom
     * signal for RadioButton.
     */
    private static final class ToggleButtonHandler implements ToggleButton.Toggled
    {
        private final RadioGroup.GroupToggled handler;

        private ToggleButtonHandler(RadioGroup.GroupToggled handler) {
            this.handler = handler;
        }

        public void onToggled(ToggleButton source) {
            if (source.getActive()) {
                handler.onGroupToggled(source);
            }
        }
    }

    /*
     * Custom handler needed to implement RadioGroup.GroupToggled custom
     * signal for CheckMenuItem.
     */
    private static final class ToggleMenuItemHandler implements CheckMenuItem.Toggled
    {
        private final RadioGroup.GroupToggled handler;

        private ToggleMenuItemHandler(RadioGroup.GroupToggled handler) {
            this.handler = handler;
        }

        public void onToggled(CheckMenuItem source) {
            if (source.getActive()) {
                handler.onGroupToggled(source);
            }
        }
    }

    /**
     * Indicate which RadioAction or Radio widget in this RadioGroup is
     * currently selected active.
     * 
     * <p>
     * This will return <code>null</code> if no RadioAction or Radio widget is
     * selected, but note that this is generally indicative of something wrong
     * with the programming; generally a RadioGroup should always have at
     * least one RadioAction or Radio widget selected.
     * 
     * @since 4.0.15
     */
    public Object getActive() {
        Object[] group;
        RadioAction action;
        RadioButton button;
        RadioMenuItem item;

        checkState();

        group = null;

        if (member instanceof RadioAction) {
            action = (RadioAction) member;
            group = GtkRadioAction.getGroup(action);
        } else if (member instanceof RadioButton) {
            button = (RadioButton) member;
            group = GtkRadioButton.getGroup(button);
        } else if (member instanceof RadioMenuItem) {
            item = (RadioMenuItem) member;
            group = GtkRadioMenuItem.getGroup(item);
        }

        for (Object object : group) {
            if (object instanceof RadioAction) {
                action = (RadioAction) object;

                if (action.getActive()) {
                    return action;
                }
            } else if (object instanceof RadioButton) {
                button = (RadioButton) object;

                if (button.getActive()) {
                    return button;
                }
            } else if (object instanceof RadioMenuItem) {
                item = (RadioMenuItem) object;

                if (item.getActive()) {
                    return item;
                }
            }
        }

        return null;
    }
}
