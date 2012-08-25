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
 * A <code>RadioAction</code> is an equivalent of a {@link Action} but used in
 * general to create {@link RadioMenuItem}. It has an &quot;active&quot; state
 * which indicates whether the action has been checked or not.
 * 
 * @author Guillaume Mazoyer
 * @since 4.0.15
 */
public class RadioAction extends ToggleAction
{
    /*
     * Reference keeps our group mechanism in scope, and powers getGroup()
     */
    private RadioGroup enclosingGroup;

    protected RadioAction(long pointer) {
        super(pointer);
    }

    /**
     * Create a new RadioAction, and connect a handler to its
     * <code>RadioAction.Toggled</code> signal.
     * 
     * @param name
     *            A unique name for the RadioAction.
     * @param label
     *            The text that will be displayed in the proxy Widgets. You
     *            usually will want to localize it to the user language.
     * @param tooltip
     *            A Tooltip or little help message for the RadioAction. Also
     *            localized.
     * @param stock
     *            The stock icon to display in proxy Widgets.
     * @since 4.0.15
     */
    /*
     * FIXME describe the implications of different choices for name.
     */
    public RadioAction(RadioGroup group, String name, String label, String tooltip, Stock stock) {
        this(GtkRadioAction.createRadioAction(name, label, tooltip, stock.getStockId(), 0));
        setGroup(group);
    }

    /**
     * Create a new RadioAction from a Stock item. The message and tooltip
     * will be supplied by GTK automatically.
     * 
     * @since 4.0.15
     */
    public RadioAction(RadioGroup group, String name, Stock stock) {
        this(GtkRadioAction.createRadioAction(name, null, null, stock.getStockId(), 0));
        setGroup(group);
    }

    /**
     * Create a new RadioAction based on a Stock item, and connect a handler
     * to its <code>RadioAction.Toggled</code> signal. Complements the
     * {@link #RadioAction(RadioGroup, String, Stock) &lt;init&gt;(RadioGroup,
     * String, Stock)} constructor.
     * 
     * @since 4.0.15
     */
    public RadioAction(RadioGroup group, String name, Stock stock, RadioAction.Toggled handler) {
        this(GtkRadioAction.createRadioAction(name, null, null, stock.getStockId(), 0));
        setGroup(group);
        connect(handler);
    }

    /**
     * Create a new RadioAction, and connect a handler to its
     * <code>RadioAction.Toggled</code> signal.
     * 
     * @param name
     *            A unique name for the RadioAction.
     * @param label
     *            The label that will be displayed in the proxy Widgets. You
     *            usually will want to localize it to the user language.
     * @param handler
     *            A handler to connect to the <code>RadioAction.Toggled</code>
     *            signal. Usually will will start from here the operation
     *            related to the Action.
     * @since 4.0.15
     */
    public RadioAction(RadioGroup group, String name, String label, RadioAction.Toggled handler) {
        this(GtkRadioAction.createRadioAction(name, label, null, null, 0));
        setGroup(group);
        connect(handler);
    }

    /**
     * Create a new RadioAction.
     * 
     * @param name
     *            A unique name for the RadioAction.
     * @param label
     *            The text that will be displayed in the proxy Widgets. You
     *            usually will want to localize it to the user language.
     * @since 4.0.15
     */
    public RadioAction(RadioGroup group, String name, String label) {
        this(GtkRadioAction.createRadioAction(name, label, null, null, 0));
        setGroup(group);
    }

    /*
     * This private method is used into the constructor. Like all other
     * RadioThings, the current way to manage a group sucks. So, again we made
     * our own magic.
     */
    private void setGroup(RadioGroup group) {
        final RadioAction first;

        first = (RadioAction) group.getMember();

        /*
         * Add the action to our group
         */
        group.setMember(this);
        enclosingGroup = group;

        /*
         * This is the first the first action
         */
        if (first == null) {
            GtkRadioAction.setGroup(this, null);
        } else {
            /*
             * We use the last action of our group
             */
            setPropertyObject("group", first);
        }
    }

    /**
     * Get the RadioActionGroup that encloses this RadioAction and the others
     * that belonging to the same mutual exclusion group.
     * 
     * @since 4.0.15
     */
    public RadioGroup getGroup() {
        return enclosingGroup;
    }
}
