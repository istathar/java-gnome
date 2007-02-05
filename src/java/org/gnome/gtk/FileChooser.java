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
     * Get the current folder, i.e. the folder being displayed in this
     * FileChooser. Note that this is not the same as the currently-selected 
     * folder if the FileChooser is in {@link FileChooserAction#SELECT_FOLDER}
     * mode. To get the currently-selected folder in that mode, you can use
     * {@link #getURI()} instead.
     * 
     * @return The current folder, or <code>null</code> if if the FileChooser 
     *         was unable to load the last folder that was requested from it; 
     *         for example, as would be for calling 
     *         {@link #setCurrentFolder(String)} on a nonexistent folder.
     * @see #setCurrentFolder(String)
     * @see #getURI()
     * @see #getFilename()
     * @since 4.0.2
     */
    public String getCurrentFolder();
    
    /**
     * Sets the current folder for FileChooser from a local filename. 
     * The user will be shown the full contents of the current folder, plus 
     * user interface elements for navigating to other folders.
     * 
     * @param filename
     *               the full path of the new current folder
     * @return <code>true</code> if the folder could be changed successfully, 
     *         <code>false</code> otherwise.
     * @see #getCurrentFolder()
     * @since 4.0.2
     */
    public boolean setCurrentFolder(String filename);
    
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
     * We do not expose FILE_ACTIVATED, as there it is an internal signal (and
     * besides, testing it didn't seem to result in the signal being fired in
     * a visible way).
     */

    /**
     * Event generated when the selection in this FileChooser is changed. The
     * usual cause of this would be the user pressing "OK" in a
     * FileChooserDialog, of course, but it can also happen as a result of
     * {@link #selectFilename(java.lang.String) selectFilename()} or
     * {@link #selectURI(java.lang.String) selectURI()} being called.
     * 
     */
    public interface SELECTION_CHANGED extends GtkFileChooser.SELECTION_CHANGED
    {
        public void onSelectionChanged(FileChooser source);
    }

    /**
     * Hook up a callback to handle the "selection-changed" signal generated
     * when the file or directory has been set.
     */
    public void connect(SELECTION_CHANGED handler);
}
