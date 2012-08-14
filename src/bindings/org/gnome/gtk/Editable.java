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
 * Methods common to Widgets which allow the line of text they display to be
 * edited.
 * 
 * @author Andrew Cowie
 * @author Douglas Goulart
 * @since 4.0.6
 */
public interface Editable
{
    /**
     * Set the position of the cursor in this Editable. The cursor will be put
     * before the character at the position indicated.
     * 
     * @param position
     *            The value given must be less than or equal to the number of
     *            characters currently in the Editable field. Supplying a
     *            value of <code>-1</code> will cause the cursor to move to a
     *            position after the last character in the text.
     * 
     * @since 4.0.6
     */
    public void setPosition(int position);

    /**
     * Retrieves the current cursor position.
     * 
     * Returns the position of the cursor. The cursor is displayed before the
     * character with the given (base 0) index in the widget. The value will
     * be less than or equal to the number of characters in the widget.
     * 
     * @since 4.0.16
     */
    public int getPosition();

    /**
     * The signal emitted when the text in the Editable has changed.
     * 
     * @author Andrew Cowie
     * @since 4.0.6
     */
    public interface Changed extends GtkEditable.ChangedSignal
    {
        void onChanged(Editable source);
    }

    /**
     * Hook up a handler for <code>Editable.Changed</code> signals.
     * 
     * @since 4.0.6
     */
    public void connect(Editable.Changed handler);

    /**
     * Select a region of the text in this Editable. The characters between
     * <code>start</code> up to <i>but not including</i> <code>end</code> will
     * be selected.
     * 
     * <p>
     * Calling <code>selectRegion(0, 0)</code> will remove the selection
     * (although that will only happen if some other Widget has the focus; in
     * Windows where there is only one control the user can manipulate an
     * Entry will end up selected no matter what).
     * 
     * @param end
     *            If negative, then the selection will be from
     *            <code>start</code> to the end of the text in the Editable.
     * @since 4.0.6
     */
    public void selectRegion(int start, int end);

    /**
     * Retrieves the selection start position of the Editable. If no text was
     * selected both <code>getSelectionBoundsStart()</code> and
     * <code>getSelectionBoundsEnd()</code> will be identical.
     * 
     * @since 4.0.16
     */
    public int getSelectionBoundsStart();

    /**
     * Retrieves the selection end position of the Editable. If no text was
     * selected both <code>getSelectionBoundsStart()</code> and
     * <code>getSelectionBoundsEnd()</code> will be identical.
     * 
     * @since 4.0.16
     */
    public int getSelectionBoundsEnd();
}
