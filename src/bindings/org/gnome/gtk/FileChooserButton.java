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
 * Displays a filename and a Button which, if pressed, opens a
 * FileChooserDialog allowing the user to select the file.
 * 
 * <p>
 * This Widget implements the FileChooser interface, which has most of the
 * methods necessary to manipulate the selection in the Widget.
 * 
 * <p>
 * Note that FileChooserButton only supports selecting files (mode
 * {@link FileChooserAction#OPEN OPEN}) or directories (mode
 * {@link FileChooserAction#SELECT_FOLDER SELECT_FOLDER}). If you need
 * something more complicated, then you'll need to use wrap a
 * FileChooserWidget in a custom Widget or launch a FileChooserDialog.
 * 
 * @author Andrew Cowie
 * @since 4.0.2
 * @see FileChooserWidget
 * @see FileChooserDialog
 */
public class FileChooserButton extends HBox implements FileChooser
{
    protected FileChooserButton(long pointer) {
        super(pointer);
    }

    /**
     * Creates a new FileChooserButton. The selected file is unset, and will
     * appear as "(none)" in the display.
     * 
     * @param title
     *            a title for the FileChooserDialog when it is popped.
     * @param action
     *            which style of FileChooser you want. Only
     *            {@link FileChooserAction#OPEN OPEN} (selecting a single
     *            file), and {@link FileChooserAction#SELECT_FOLDER
     *            SELECT_FOLDER}, (selecting a single directory) are enabled
     *            for FileChooserButton.
     * @since 4.0.2
     */
    public FileChooserButton(String title, FileChooserAction action) {
        super(GtkFileChooserButton.createFileChooserButton(title, action));
    }

    public String getFilename() {
        return GtkFileChooser.getFilename(this);
    }

    public String getCurrentFolder() {
        return GtkFileChooser.getCurrentFolder(this);
    }

    public boolean setCurrentFolder(String directory) {
        return GtkFileChooser.setCurrentFolder(this, directory);
    }

    public void setAction(FileChooserAction action) {
        GtkFileChooser.setAction(this, action);
    }

    public FileChooserAction getAction() {
        return GtkFileChooser.getAction(this);
    }

    /*
     * Changes to this implementation need to be dittoed in FileChooserDialog
     * and FileChooserWidget!
     */
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

    /**
     * Signal emitted when the file indicated by this FileChooserButton has
     * been set by the user.
     * 
     * @author Andrew Cowie
     * @since 4.0.5
     */
    public interface FileSet extends GtkFileChooserButton.FileSetSignal
    {
        public void onFileSet(FileChooserButton source);
    }

    /**
     * Hook up a callback to handle the <code>FileChooserButton.FileSet</code>
     * signal generated when the file or directory has been selected by the
     * user using this FileChooserButton.
     * 
     * @since 4.0.5
     */
    public void connect(FileChooserButton.FileSet handler) {
        GtkFileChooserButton.connect(this, handler, false);
    }

    public boolean setFilename(String filename) {
        if (filename.charAt(0) == '/') {
            return GtkFileChooser.setFilename(this, filename);
        } else {
            throw new IllegalArgumentException("The filename argument must be an absolute path");
        }
    }
}
