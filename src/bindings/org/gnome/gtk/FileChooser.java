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
 * Widgets which allow you to select a file or directory. <img
 * src="FileChooserDialog.png" class="snapshot"> GNOME has a unified
 * FileChooser which is used by all applications to select files. It comes in
 * several pre-baked forms, notably {@link FileChooserDialog} (a Dialog which
 * can be used as a modal popup), and {@link FileChooserButton} (which is a
 * Button which displays the selected filename and pops up a FileChooserDialog
 * when activated).
 * 
 * <p>
 * Be aware that much of FileChooser's internal behaviour depends on the main
 * loop cycling; calls to methods like {@link #setCurrentFolder(String)
 * setCurrentFolder()} and {@link #setFilename(String) setFilename()} will not
 * actually take effect until you start the main loop or return from the
 * current signal handler (as the case may be).
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 * @since 4.0.2
 */
public interface FileChooser
{
    /**
     * Get the filename currently selected by this FileChooser.
     * 
     * @return The filename, or if no file is selected then this will return
     *         <code>null</code>. If multiple files are selected, one of the
     *         filenames will be returned at random. If the FileChooser is in
     *         one of the folder modes, this returns the selected folder's
     *         name.
     * @since 4.0.2
     */
    public String getFilename();

    /**
     * Get the current folder being displayed in this FileChooser. Note that
     * this is not the same as the currently-selected folder if the
     * FileChooser is in {@link FileChooserAction#SELECT_FOLDER SELECT_FOLDER}
     * mode. To get the currently-selected folder in that mode, you can use
     * {@link #getURI()} instead.
     * 
     * @return The current folder, or <code>null</code> if if the FileChooser
     *         was unable to load the last folder that was requested of it (as
     *         would happen if calling {@link #setCurrentFolder(String)} on a
     *         nonexistent directory).
     * @see #getFilename()
     * @since 4.0.2
     */
    public String getCurrentFolder();

    /**
     * Set the current directory for this FileChooser. The user will be shown
     * the full contents of that folder, plus user interface elements for
     * navigating to other folders.
     * 
     * @param directory
     *            the full path of the new current folder
     * @return <code>true</code> if the folder could be changed successfully,
     *         <code>false</code> otherwise.
     * @since 4.0.2
     */
    public boolean setCurrentFolder(String directory);

    /**
     * Sets the type of operation that the chooser is performing; the user
     * interface is adapted to suit the selected action. For example, an
     * option to create a new folder might be shown if the action is
     * {@link FileChooserAction#SAVE SAVE} but not if the action is
     * {@link FileChooserAction#OPEN OPEN}.
     * 
     * @since 4.0.3
     */
    public void setAction(FileChooserAction action);

    /**
     * Gets the type of operation that the file chooser is performing.
     * 
     * @see #setAction(FileChooserAction)
     * @since 4.0.3
     */
    public FileChooserAction getAction();

    /**
     * Get the URI representing the file or directory currently selected by
     * this FileChooser.
     * 
     * @return The selected file's URI, or if no file is selected then this
     *         will return <code>null</code>. If multiple files are selected,
     *         one of the filenames will be returned at random. If the
     *         FileChooser is in one of the folder modes, this returns the
     *         selected folder's URI.
     * @since 4.0.2
     */
    /*
     * A direct mapping would be getUri(), but that looks stupid, and
     * java.io.File uses URI in its method names. But is actually returning a
     * Java URI object a good idea? I think so - it helps bridge the gap a bit
     * better, I think.
     */
    public URI getURI();

    /*
     * We do not expose SelectionChanged or FileActivated, as they are
     * internal signals (and besides, testing it didn't seem to result in the
     * signal being fired in a visible way).
     */

    /**
     * Set the file you want selected in the FileChooser.
     * 
     * <p>
     * If the folder currently showing in the FileChooser isn't the directory
     * containing the filename you specify, then the FileChooser will be
     * changed to that directory.
     * 
     * @param filename
     *            Must be an absolute path.
     * @return <code>true</code> if the the directory was changed (if
     *         necessary) and a file was successfully selected.
     * @since 4.0.5
     */
    /*
     * Calling this method is the equivalent of calling unselectAll() then
     * selectFilename(filename).
     * 
     * I was thus very tempted not to expose this, but it makes a better
     * complement to getFilename() than selectFilename() and so decided not to
     * expose the latter after all. If anyone ever implements multiple
     * selection they can [re]add it, but this is nice and clean at the
     * moment.
     */
    public boolean setFilename(String filename);

    /**
     * Add a FileFilter to the list of filters that the user can select
     * between. When a filter is selected, only files that are passed by that
     * filter are displayed.
     * 
     * @see FileFilter
     * @since 4.0.12
     */
    public void addFilter(FileFilter filter);

    /**
     * Sets the current filter; only the files that pass the filter will be
     * displayed. If the user-selectable list of filters is non-empty, then
     * the filter should be one of the filters in that list.
     * 
     * <p>
     * Setting the current filter when the list of filters is empty is useful
     * if you want to restrict the displayed set of files without letting the
     * user change it.
     * 
     * @see FileFilter
     * @since 4.0.12
     */
    public void setFilter(FileFilter filter);

    /**
     * Gets the current filter.
     * 
     * <p>
     * This function is specially useful on "Save" FileChoosers, to know the
     * file type chosen by the user.
     * 
     * @see FileFilter
     * @since 4.0.12
     */
    public FileFilter getFilter();
}
