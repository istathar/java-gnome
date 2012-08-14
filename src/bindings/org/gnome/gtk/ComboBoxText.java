/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2011 Operational Dynamics Consulting, Pty Ltd and Others
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
 * A ComboBox for displaying simple Strings. <img src="ComboBoxText.png"
 * class="snapshot" /> A common use case for ComboBoxes is to just choose from
 * a list of textual options. This subclass of ComboBox takes care of the
 * mechanics of setting up a the ListStore and the appropriate CellRenderers
 * so as to allow a straight forward interface with a limited number of
 * convenience methods for adding Strings.
 * 
 * <p>
 * Usage is very straight forward:
 * 
 * <pre>
 * combo = new TextComboBox();
 * combo.appendText(&quot;SYD&quot;);
 * combo.appendText(&quot;YYZ&quot;);
 * combo.appendText(&quot;JFK&quot;);
 * combo.appendText(&quot;LHR&quot;);
 * ...
 * </pre>
 * 
 * You can still use the {@link ComboBox#getActive() getActive()} from
 * ComboBox, but for simple lists of Strings being displayed be this Widget,
 * the String specific {@link #getActiveText() getActiveText()} that is
 * introduced here is probably what you want.
 * 
 * @author Andrew Cowie
 * @author Serkan Kaba
 * @since 4.0.20
 */
public class ComboBoxText extends ComboBox
{
    /**
     * Construct a new text-only ComboBox.
     * 
     * @since 4.0.20
     */
    public ComboBoxText() {
        super(GtkComboBoxText.createComboBoxText());
    }

    /**
     * Unsupported.
     * 
     * <p>
     * <i>If you're using a text mode ComboBox, GTK internally allocates a
     * ListStore of a certain structure and the methods are designed to work
     * against it. Things will blow up if you swap it for something different,
     * unless it had exactly the correct signature. As this signature is not
     * something that is part of the GTK API, it is not something we're going
     * to expose; this override is here to prevent you from shooting yourself
     * in the foot.</i>
     * 
     * @throws UnsupportedOperationException
     *             if called.
     */
    /*
     * Is there a better way of managing the completion visibility and it
     * appearing in JavaDoc? Using the deprecated tag works for the latter,
     * but not the former. Hm.
     */
    public void setModel(TreeModel model) {
        throw new UnsupportedOperationException(
                "Sorry, but you can't change the model being used to back a TextComboBox.");
    }

    /**
     * Append an item to the list.
     * 
     * @since 4.0.20
     */
    public void appendText(String text) {
        GtkComboBoxText.appendText(this, text);
    }

    /**
     * Append an item at the supplied position. Positions are zero origin.
     * 
     * @since 4.0.20
     */
    public void insertText(int position, String text) {
        GtkComboBoxText.insertText(this, position, text);
    }

    /**
     * Prepend an item to the beginning of the list.
     * 
     * @since 4.0.20
     */
    public void prependText(String text) {
        GtkComboBoxText.prependText(this, text);
    }

    /**
     * Returns the text of the active item.
     * 
     * @since 4.0.20
     */
    public String getActiveText() {
        return GtkComboBoxText.getActiveText(this);
    }

    /**
     * Removes the item at given position from the list.
     * 
     * @since 4.0.20
     */
    public void removeText(int position) {
        GtkComboBoxText.remove(this, position);
    }
}
