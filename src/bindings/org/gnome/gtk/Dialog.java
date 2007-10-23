/*
 * Dialog.java
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

/**
 * A Dialog is a special Window which is used to display information for the
 * user or to get response from him. While a normal Window usually is used to
 * represent the whole application and to offer sometimes many interactions
 * for the user a Dialog should only be opened if a special situation has
 * occured. This situations may be triggered by the application itself or by
 * the user.
 * 
 * <p>
 * A Dialog is build-up by a Window which contains a {@link VBox}. This box
 * is separated by an {@link HSeparator} in two areas. The bottom "action
 * area" consists of a {@link HBox} and is used to pack buttons to the Dialog
 * which may perform actions such as ok or cancel, for example. The default
 * way to add a Button to the Dialog is using the
 * {@link Dialog#addButton(String, int) addButton} method and give it a unique
 * response ID. Now if you click on this Button the Dialog emits a response
 * event containing the response ID you have given the Button.
 * 
 * <p>
 * The top "client area" consists of the VBox itself and is used to pack
 * Widgets such as Labels or Icons, for example. The default way to add a
 * Widget to the top area is using the {@link Dialog#add(Widget) add} method.
 * It is a good way to add first a new Box such as {@link VBox} or
 * {@link HBox} to the top area and pack all other Widgets to it as a Box
 * provide better layout handling possibilities.
 * 
 * <p>
 * To open the Dialog window you can use the {@link Widget#show() show} method
 * after you have packed all Widgets to it. But sometimes you may use a Dialog
 * box to get information from the user which is required for the further
 * progress of the main application. For this situations it is intended to use
 * the {@link Dialog#run()} method to open the Dialog as it blocks the
 * application and waits for response of the user.
 * 
 * <p>
 * GTK hold some standard Dialogs in store which can be used for typical
 * situations such as a {@link MessageDialog} for example. Another standard
 * Dialog is the {@link FileChooserDialog} which provide the full
 * functionality to select a file.
 * 
 * @author Thomas Schmitz
 * @since 4.0.5
 */
public class Dialog extends Window
{
    protected Dialog(long pointer) {
        super(pointer);
    }

    /**
     * Creates a new Dialog box containing an empty action area and an empty
     * client area, separated by an HSeperator.
     */
    public Dialog() {
        super(GtkDialog.createDialog());
    }

    /*
     * Because a Dialog contains a Widget already we have to overwrite the add
     * method. Now it adds the given Widget to the Dialog's client area.
     */
    public void add(Widget widget) {
        ((VBox) this.getChild()).add(widget);
    }

    /**
     * Adds an action {@link Button} with the given text as its label to the
     * end of the Dialog's action area. The given response id will be
     * contained in the Dialog's response event if the added button is
     * clicked.
     * 
     * @return the added button
     */
    public Widget addButton(String buttonText, int responseId) {
        return GtkDialog.addButton(this, buttonText, responseId);
    }

    /**
     * Blocks the rest of the application by running in a recursive main loop
     * until a {@link RESPONSE} signal arises on the Dialog. This is also
     * known as 'modal'. While this loop is running the user can't interact
     * with the rest of the application.
     * 
     * <p>
     * Before you call this method on your dialog think if it is wise to block
     * the others operations within the Gtk main loop.
     */
    public int run() {
        return GtkDialog.run(this);
    }

    /**
     * This signal arises when a user activates an action widget, means one of
     * the widgets laying out in the action area.
     * 
     * @author Thomas Schmitz
     * @since 4.0.5
     */
    public interface RESPONSE extends GtkDialog.RESPONSE
    {
        public void onResponse(Dialog source, int responseId);
    }

    public void connect(GtkDialog.RESPONSE handlerInstance) {
        GtkDialog.connect(this, handlerInstance);
    }
}
