/*
 * FileChooserDialog.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import java.net.URI;

/**
 * A Dialog suitable for operations that need to select a file, such as "File ->
 * Open" or "File -> Save" commands.
 * 
 * <p>
 * it implements the FileChooser interface, which has most of the methods
 * necessary to manipulate the selection in the Dialog.
 * 
 * <p>
 * A FileChooserDialog is just a Dialog with a FileChooserWidget. It has no
 * Buttons, so you should add suitable Buttons that correspond to the desired
 * {@link FileChooserAction FileChooserAction}. Try to use standard Stock
 * icons, such as {@link Stock#OPEN OPEN} or {@link Stock#SAVE SAVE}. You can
 * add the Buttons with the Dialog's
 * {@link #addButton(Stock, ResponseType) addButton()} method. Although you
 * can use a custom ResponseType, it is highly recommended that you use one of
 * the predefined responses to make the FileChooserDialog work propertly. For
 * example, when you click the "Open" Button in an "Open File" Dialog, if a
 * folder is selected it should be openned, instead of terminate the Dialog.
 * The usage of the predefined {@link ResponseType ResponseTypes} ensures this
 * correct behavior.
 * 
 * <p>
 * For example, a FileChooserDialog to open a file could be like this:
 * 
 * <pre>
 * FileChooserDialog dialog;
 * dialog = new FileChooserDialog(&quot;Open File&quot;, window, FileChooserAction.OPEN);
 * 
 * // add the Buttons with suitable predefined responses
 * dialog.addButton(Stock.CANCEL, ResponseType.CANCEL);
 * dialog.addButton(Stock.OPEN, ResponseType.OK);
 * 
 * // run the Dialog
 * ResponseType response = dialog.run();
 * dialog.hide();
 * 
 * if (response == ResponseType.OK) {
 *     // open the file
 *     String filename = dialog.getFilename();
 *     ....
 * }
 * </pre>
 * 
 * 
 * @see FileChooserWidget
 * @see FileChooserAction
 * 
 * @author Vreixo Formoso
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
     * @param title
     *            the title of the Dialog, or <code>null</code>.
     * @param parent
     *            the transient parent of the Dialog, or <code>null</code>.
     *            It is recommended to specify a parent Window.
     * @param action
     *            which style of FileChooser you want. You should add to the
     *            Dialog Buttons that match the selected action. For example,
     *            if you select the {@link FileChooserAction#OPEN OPEN}
     *            action, you should add an "Open" Button to the Dialog.
     */
    public FileChooserDialog(String title, Window parent, FileChooserAction action) {
        super(GtkFileChooserDialog.createFileChooserDialog(title, parent, action, null));
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

    public void connect(FileChooser.SELECTION_CHANGED handler) {
        GtkFileChooser.connect(this, handler);
    }

    /**
     * Show an "Open File" Dialog. This is a convenience method to show a
     * Dialog for open a file. It wait until the user selects a file or
     * close/cancel the Dialog, and return the selected filename.
     * 
     * @param title
     *            Title for the Dialog
     * @param parent
     *            Transient parent
     * @return The selected file path, or <code>null</code> if the user has
     *         closed or cancelled the user.
     */
    /*
     * TODO maybe it is better to return the URI, or just add a boolean
     * parameter to let the user select what it prefers.
     */
    public static String openFile(String title, Window parent) {
        FileChooserDialog dialog;
        ResponseType response;

        dialog = new FileChooserDialog(title, parent, FileChooserAction.OPEN);
        dialog.addButton(Stock.CANCEL, ResponseType.CANCEL);
        dialog.addButton(Stock.OPEN, ResponseType.OK);

        response = dialog.run();
        dialog.hide();
        if (response == ResponseType.OK) {
            return dialog.getFilename();
        } else {
            return null;
        }
    }

    /**
     * Show an "Save File" Dialog. This is a convenience method to show a
     * Dialog for saving a file. It wait until the user select the location
     * for the file or close/cancel the Dialog, and return the selected
     * filename.
     * 
     * @param title
     *            Title for the Dialog
     * @param parent
     *            Transient parent
     * @return The selected file path, or <code>null</code> if the user has
     *         closed or cancelled the user.
     */
    /*
     * TODO maybe it is better to return the URI, or just add a boolean
     * parameter to let the user select what it prefers.
     */
    public static String saveFile(String title, Window parent) {
        FileChooserDialog dialog;
        ResponseType response;

        dialog = new FileChooserDialog(title, parent, FileChooserAction.SAVE);
        dialog.addButton(Stock.CANCEL, ResponseType.CANCEL);
        dialog.addButton(Stock.SAVE, ResponseType.OK);

        response = dialog.run();
        dialog.hide();
        if (response == ResponseType.OK) {
            return dialog.getFilename();
        } else {
            return null;
        }
    }

}
