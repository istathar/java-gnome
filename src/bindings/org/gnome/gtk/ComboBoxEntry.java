/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
 * An Entry which also offers a range of pre-configured options to choose
 * from. <img src="TextComboBoxEntry.png" class="snapshot" /> It is a
 * composite Widget with an Entry wrapped in a mechanism to activate a popup
 * dialog allowing the user to pick from a list of options.
 * 
 * <p>
 * ComboBoxEntry is powered by the same mechanism as the parent class
 * ComboBox; the option list is driven by a TreeModel. When constructing a
 * ComboBoxEntry, you specify the column from the model that will supply the
 * data to be used as the list of options.
 * 
 * <p>
 * Almost everyone dealing with ComboBoxEntries wonders how to get the text
 * showing in the Widget. You do this by very simply getting by getting it
 * from the Entry. Call {@link Bin#getChild() getChild()} and call that
 * Entry's {@link Entry#getText() getText()}; given:
 * 
 * <pre>
 * ComboBoxEntry combo;
 * Entry e;
 * String str;
 * ...
 * </pre>
 * 
 * you simply write:
 * 
 * <pre>
 * e = (Entry) combo.getChild();
 * str = e.getText();
 * </pre>
 * 
 * and then do something with the returned String.
 * 
 * <p>
 * <i>Like <code>GtkComboBox</code>, <code>GtkComboBoxEntry</code> has a
 * second text-only API that is lumped into the same classes while not being
 * able to be used together. We have similarly spliced this functionality out
 * of this class into our subclass</i> {@link TextComboBoxEntry}.
 * 
 * @since 4.0.6
 * @author Andrew Cowie
 */
public class ComboBoxEntry extends ComboBox implements CellEditable, CellLayout
{
    protected ComboBoxEntry(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new ComboBoxEntry, indicating which DataColumn in the
     * TreeModel is to provide the list of options for the Widget to display.
     * 
     * @since 4.0.6
     */
    public ComboBoxEntry(TreeModel model, DataColumnString column) {
        super(GtkComboBoxEntry.createComboBoxEntryWithModel(model, column.getOrdinal()));
    }
}
