/*
 * FileChooser.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import java.net.URI;

public interface FileChooser
{
    /**
     * Get the filename currently selected by this FileChooser.
     * 
     * @return The filename, or if no file is selected then this will return
     *         <code>null</code>. If multiple files are selected, one of
     *         the filenames will be returned at random. If the FileChooser is
     *         in one of the folder modes, this returns the selected folder's
     *         name.
     */
    public String getFilename();

    /**
     * Get the current folder being displayed in this FileChooser. Note that
     * this is not the same as the currently-selected folder if the
     * FileChooser is in {@link FileChooserAction#SELECT_FOLDER SELECT_FOLDER}
     * mode. To get the currently-selected folder in that mode, you can use
     * {@link #getURI()} instead.
     * 
     * @return The current folder, or <code>null</code> if if the
     *         FileChooser was unable to load the last folder that was
     *         requested of it (as would happen if calling
     *         {@link #setCurrentFolder(String)} on a nonexistent directory).
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
     * @return <code>true</code> if the folder could be changed
     *         successfully, <code>false</code> otherwise.
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
     *         will return <code>null</code>. If multiple files are
     *         selected, one of the filenames will be returned at random. If
     *         the FileChooser is in one of the folder modes, this returns the
     *         selected folder's URI.
     */
    /*
     * A direct mapping would be getUri(), but that looks stupid, and
     * java.io.File uses URI in its method names. But is actually returning a
     * Java URI object a good idea? I think so - it helps bridge the gap a bit
     * better, I think.
     */
    public URI getURI();

    /*
     * We do not expose FILE_ACTIVATED, as it is an internal signal (and
     * besides, testing it didn't seem to result in the signal being fired in
     * a visible way).
     */

    /**
     * Event generated when the selection in this FileChooser is changed. The
     * usual cause of this would be the user pressing "OK" in a
     * FileChooserDialog, of course, but it can also happen as a result of
     * {@link FileChooser#selectFilename(java.lang.String) selectFilename()}
     * or {@link FileChooser#selectURI(java.lang.String) selectURI()} being
     * called.
     * 
     * <p>
     * <b>WARNING</b><br>
     * This signal will be replaced by <code>FILE_SET</code> in java-gnome
     * 4.0.5. <i>This signal being the only way to get at the selection in a
     * <code>GtkFileChooserButton</code> is a bug in GTK that has been fixed
     * in 2.12.</i>
     */
    public interface SELECTION_CHANGED extends GtkFileChooser.SELECTION_CHANGED
    {
        /**
         * @deprecated
         */
        public void onSelectionChanged(FileChooser source);
    }

    /**
     * Hook up a callback to handle the "selection-changed" signal generated
     * when the file or directory has been set.
     */
    public void connect(SELECTION_CHANGED handler);
}
