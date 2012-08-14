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
 * A <code>RadioToolButton</code> is a {@link ToolItem} that contains a
 * {@link RadioButton}. It is a part of a group of {@link ToggleButton
 * ToggleButtons} where only one button can be active at a time.
 * 
 * @author Guillaume Mazoyer
 * @since 4.0.15
 */
public class RadioToolButton extends ToggleToolButton
{
    /*
     * Reference keeps our group mechanism in scope, and powers getGroup()
     */
    private RadioGroup enclosingGroup;

    protected RadioToolButton(long pointer) {
        super(pointer);
    }

    /**
     * Create a new RadioToolButton and add it to a RadioGroup.
     * 
     * @since 4.0.15
     */
    public RadioToolButton(RadioGroup group) {
        super(createFirstOrNext(group));
        group.setMember(this);
        enclosingGroup = group;
    }

    /**
     * Create a new RadioToolButton using a {@link Stock Stock item} and add
     * it to a RadioGroup.
     * 
     * @since 4.0.15
     */
    public RadioToolButton(RadioGroup group, Stock stock) {
        super(createFirstOrNext(group, stock));
        group.setMember(this);
        enclosingGroup = group;
    }

    private static long createFirstOrNext(RadioGroup group) {
        final RadioToolButton first;

        first = (RadioToolButton) group.getMember();

        if (first == null) {
            return GtkRadioToolButton.createRadioToolButton(null);
        } else {
            return GtkRadioToolButton.createRadioToolButtonFromWidget(first);
        }
    }

    private static long createFirstOrNext(RadioGroup group, Stock stock) {
        final RadioToolButton first;

        first = (RadioToolButton) group.getMember();

        if (first == null) {
            return GtkRadioToolButton.createRadioToolButtonFromStock(null, stock.getStockId());
        } else {
            return GtkRadioToolButton.createRadioToolButtonWithStockFromWidget(first, stock.getStockId());
        }
    }

    /**
     * Get the RadioGroup that encloses this RadioToolButton and the others
     * that belonging to the same mutual exclusion group.
     * 
     * @since 4.0.15
     */
    public RadioGroup getGroup() {
        return enclosingGroup;
    }
}
