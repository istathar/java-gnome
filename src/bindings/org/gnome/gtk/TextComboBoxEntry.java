/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
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
 * A ComboBoxEntry with a simplified API for displaying and extracting
 * Strings. <img src="TextComboBoxEntry.png" class="snapshot" /> When dealing
 * with ComboBoxEntries you usually get the Entry child Widget and hook up to
 * its methods and signals. Our text-only variant, however, has methods
 * allowing you to quickly manipulate the list of options and to get the
 * currently selected one.
 * 
 * <p>
 * As with the parent ComboBoxEntry, if you want what is actually <i>in</i>
 * the Entry, you will have to pull the Entry out to ask it.
 * 
 * <p>
 * <i> These are indeed the same convenience methods as found in the text-only
 * API of <code>GtkComboBox</code>, which we have split into
 * <code>ComboBox</code> and <code>TextComboBox</code>.
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public class TextComboBoxEntry extends ComboBoxEntry
{
    /**
     * Construct a new text-only ComboBoxEntry.
     * 
     * @since 4.0.6
     */
    public TextComboBoxEntry() {
        super(GtkComboBoxEntry.createComboBoxEntryText());
    }

    /*
     * These are cut and paste from TextComboBox. No common superinterface,
     * unfortunately. Not that one would do THAT much good.
     */

    /**
     * Append an item to the list.
     * 
     * @since 4.0.6
     */
    public void appendText(String text) {
        GtkComboBox.appendText(this, text);
    }

    /**
     * Append an item at the supplied position. Positions are zero origin.
     * 
     * @since 4.0.6
     */
    public void insertText(int position, String text) {
        GtkComboBox.insertText(this, position, text);
    }

    /**
     * Prepend an item to the beginning of the list.
     * 
     * @since 4.0.6
     */
    public void prependText(String text) {
        GtkComboBox.prependText(this, text);
    }

    /**
     * Returns the text of the active item.
     * 
     * <p>
     * If the TextComboBoxEntry is showing the result of the user having
     * selected an item from the list, or the developer has called
     * <code>setActive()</code>, then this will be equal to the result of
     * calling:
     * 
     * <pre>
     * e = (Entry) combo.getChild();
     * e.getText();
     * </pre>
     * 
     * but beware that if the user <i>hasn't</i> caused one of the existing
     * options to be picked and has instead entered their own data, then this
     * won't match what is in the Entry.
     * 
     * @since 4.0.6
     */
    public String getActiveText() {
        return GtkComboBox.getActiveText(this);
    }
}
