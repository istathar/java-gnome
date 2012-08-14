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

import org.freedesktop.bindings.Constant;

/**
 * Set which kind of FileChooser Dialog will appear.
 * 
 * @author Andrew Cowie
 * @since 4.0.2
 */
public final class FileChooserAction extends Constant
{
    private FileChooserAction(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Open mode: the FileChooser will only let the user pick an existing
     * file.
     */
    public static final FileChooserAction OPEN = new FileChooserAction(GtkFileChooserAction.OPEN, "OPEN");

    /**
     * Save mode: the FileChooser will come up in a form suited to saving
     * files. It will let the user pick an existing file [TODO: which will
     * lead to an overwrite yes/no question, right?], or type in a new
     * filename.
     */
    public static final FileChooserAction SAVE = new FileChooserAction(GtkFileChooserAction.SAVE, "SAVE");

    /**
     * Open folder mode: set the FileChooser to enable the user pick a
     * specific directory.
     */

    public static final FileChooserAction SELECT_FOLDER = new FileChooserAction(
            GtkFileChooserAction.SELECT_FOLDER, "SELECT_FOLDER");

    /**
     * Create folder mode: put the FileChooser into a mode whereby it is
     * creating a new directory. It will, however, let the user name an
     * existing directory, which might be thought of as a bit odd, but it's a
     * usability feature making the interface consistent across GNOME.
     */
    public static final FileChooserAction CREATE_FOLDER = new FileChooserAction(
            GtkFileChooserAction.CREATE_FOLDER, "CREATE_FOLDER");
}
