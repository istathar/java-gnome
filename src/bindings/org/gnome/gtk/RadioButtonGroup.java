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
 * A group of RadioButtons sharing the mutually exclusive relationship that
 * only one of the group member can be selected at a time. <img
 * src="RadioButton.png" class="snapshot">
 * 
 * <p>
 * This is the mechanism by which you indicate a series of RadioButtons will
 * be associated:
 * 
 * <pre>
 * RadioButtonGroup group;
 * RadioButton one, two, three, ...;
 * 
 * group = RadioButtonGroup();
 * one = RadioButton(group, &quot;One&quot;);
 * two = RadioButton(group, &quot;Two&quot;);
 * three = RadioButton(group, &quot;Three&quot;);
 * </pre>
 * 
 * @author Andrew Cowie
 * @author Mario Torre
 * @since 4.0.7
 */
/*
 * This acts as a wrapper to allow us to avoid the complexity of the GSList
 * used as GtkRadioButton's group property and the fact that its constructors
 * are polluted with C API sugar.
 */
public class RadioButtonGroup
{
    RadioButton member;

    /**
     * Create a new group of related RadioButtons
     * 
     * @since 4.0.7
     */
    public RadioButtonGroup() {
        member = null;
    }

    /*
     * The idea is that we keep a reference to one of the members in a group
     * of RadioButtons and then use that for subsequent constructors. The
     * usual temptation would be to keep reusing the same one, but not
     * actually required; just keep setting this each time a RadioButton
     * constructor is called.
     */
    RadioButton getMember() {
        return member;
    }

    void setMember(RadioButton button) {
        this.member = button;
    }

    private void checkState() {
        if (member == null) {
            throw new IllegalStateException(
                    "Can't use a RadioButtonGroup until you've added one or more RadioButtons to it");
        }
    }

    /**
     * Signal that is emitted each time the active RadioButton is a group is
     * changed.
     * 
     * <p>
     * This happens either by clicking in a different option on the group, by
     * pressing the <b><code>Up</code></b> or <b><code>Down</code></b> key
     * when one of the RadioButtons in the group has the focus, or when the
     * key combination <b><code>Alt+</code><i>mnemonic</i></b> is pressed. It
     * can also be triggered programmatically by calling the
     * {@link ToggleButton#setActive(boolean) setActive()} method.
     * 
     * @author Vreixo Formoso
     * @since <span style="color: red">Unstable</span>
     */
    /*
     * In case you're wondering, yes, this is java-gnome specific.
     */
    public interface GroupToggled
    {
        /**
         * Called when user changes the active RadioButton in a group
         * 
         * @param source
         *            The RadioButton that is now active.
         */
        public void onGroupToggled(RadioButton source);
    }

    /**
     * Hook up a handler to the <code>RadioButtonGroup.GroupToggled</code>
     * signal.
     * 
     * @since <span style="color: red">Unstable</span>
     */
    public void connect(RadioButtonGroup.GroupToggled handler) {
        ToggleHandler toggleHandler = new ToggleHandler(handler);
        RadioButton[] group = GtkRadioButton.getGroup(member);
        for (int i = 0; i < group.length; ++i) {
            group[i].connect(toggleHandler);
        }
    }

    /*
     * Custom handler needed to implement RadioButtonGroup.GroupToggled custom
     * signal.
     */
    private static final class ToggleHandler implements ToggleButton.Toggled
    {
        private final RadioButtonGroup.GroupToggled handler;

        private ToggleHandler(RadioButtonGroup.GroupToggled handler) {
            this.handler = handler;
        }

        public void onToggled(ToggleButton source) {
            if (source.getActive()) {
                handler.onGroupToggled((RadioButton) source);
            }
        }
    }

    /**
     * Indicate which RadioButton in this RadioButtonGroup is currently
     * selected active.
     * 
     * <p>
     * This Will return <code>null</code> if no RadioButton is selected, but
     * note that this is generally indicative of something wrong with the
     * programming; generally a RadioButtonGroup should always have at least
     * one RadioButton selected.
     * 
     * @since 4.0.7
     */
    /*
     * A bit of a shame that GTK doesn't just provide this by itself, but
     * then, it doesn't provide a very strong grouping mechanism either.
     */
    public RadioButton getActive() {
        final RadioButton[] group;
        checkState();

        group = GtkRadioButton.getGroup(member);

        for (int i = 0; i < group.length; ++i) {
            if (group[i].getActive()) {
                return group[i];
            }
        }
        return null;
    }
}
