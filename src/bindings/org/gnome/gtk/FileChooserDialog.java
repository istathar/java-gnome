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

import java.net.URI;

/**
 * A Dialog suitable for operations that need to select a file, such as "File
 * -> Open" or "File -> Save" commands.
 * 
 * <p>
 * A FileChooserDialog is just a Dialog with a FileChooserWidget plus
 * appropriate Buttons that corresponding to the specified
 * {@link FileChooserAction FileChooserAction}. Otherwise, all the methods
 * provided by the FileChooser interface are available which gives you the
 * necessary power to manipulate the selection received from the Dialog.
 * 
 * <p>
 * Using a FileChooserDialog to open a file could go like this:
 * 
 * <pre>
 * FileChooserDialog dialog;
 * ResponseType response;
 * 
 * // instantiate
 * dialog = new FileChooserDialog(&quot;Open File&quot;, window, FileChooserAction.OPEN);
 * 
 * // run the Dialog
 * response = dialog.run();
 * dialog.hide();
 * 
 * // deal with the result
 * if (response == ResponseType.OK) {
 *     open(dialog.getFilename());
 * }
 * </pre>
 * 
 * @author Vreixo Formoso
 * @author Andrew Cowie
 * @since 4.0.5
 */
public class FileChooserDialog extends Dialog implements FileChooser
{
    protected FileChooserDialog(long pointer) {
        super(pointer);
    }

    /**
     * Create a new FileChooserDialog.
     * 
     * <p>
     * Buttons appropriate to each of the different FileChooserActions have
     * been preconfigured in the <var>action area</var> of the Dialog. In all
     * cases, the executive to go ahead with the action will be the return of
     * ResponseType <code>OK</code>.
     * 
     * @param title
     *            the text to use in the title bar of the Dialog Window as
     *            drawn by the window manager, or <code>null</code> if you
     *            want a blank title.
     * @param parent
     *            the transient parent of the Dialog. While <code>null</code>
     *            is allowed, things are designed to work properly on the
     *            assumption that a parent is specified so it is recommended
     *            that you do so.
     * @param action
     *            which style of FileChooser you want.
     */
    public FileChooserDialog(String title, Window parent, FileChooserAction action) {
        super(GtkFileChooserDialog.createFileChooserDialog(title, parent, action, null));

        this.addButton(Stock.CANCEL, ResponseType.CANCEL);
        if (action == FileChooserAction.SAVE) {
            this.addButton(Stock.SAVE, ResponseType.OK);
        } else if (action == FileChooserAction.OPEN) {
            this.addButton(Stock.OPEN, ResponseType.OK);
        } else if (action == FileChooserAction.SELECT_FOLDER) {
            this.addButton(Stock.OK, ResponseType.OK);
        } else if (action == FileChooserAction.CREATE_FOLDER) {
            this.addButton(Stock.NEW, ResponseType.OK);
        }
    }

    public String getCurrentFolder() {
        return GtkFileChooser.getCurrentFolder(this);
    }

    public boolean setCurrentFolder(String directory) {
        return GtkFileChooser.setCurrentFolder(this, directory);
    }

    public String getFilename() {
        return GtkFileChooser.getFilename(this);
    }

    public void setAction(FileChooserAction action) {
        GtkFileChooser.setAction(this, action);
    }

    public FileChooserAction getAction() {
        return GtkFileChooser.getAction(this);
    }

    public URI getURI() {
        String uri = GtkFileChooser.getUri(this);
        if (uri != null) {
            return URI.create(uri);
        } else {
            return null;
        }
    }

    public void addFilter(FileFilter filter) {
        GtkFileChooser.addFilter(this, filter);
    }

    public void setFilter(FileFilter filter) {
        GtkFileChooser.setFilter(this, filter);
    }

    public FileFilter getFilter() {
        return GtkFileChooser.getFilter(this);
    }

    public boolean setFilename(String filename) {
        if (filename.charAt(0) == '/') {
            return GtkFileChooser.setFilename(this, filename);
        } else {
            throw new IllegalArgumentException("The filename argument must be an absolute path");
        }
    }
}
