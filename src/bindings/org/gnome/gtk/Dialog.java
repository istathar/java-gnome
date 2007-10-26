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
 * @author Vreixo Formoso
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

    /**
     * Create a new Dialog.
     * 
     * @param title
     *            Title of the new Dialog.
     * @param parent
     *            Transient parent of the dialog, or <code>null</code>. If
     *            a parent is provided, the window manager will keep the
     *            Dialog on top of it. If the Dialog is modal, it is higly
     *            recommended that you provide a parent Window, otherwise the
     *            user could be trying to interact with the main Window while
     *            the Dialog is blocking it, but hidden under other Window. In
     *            that case, the user experience is really bad, as it is not
     *            easy to figure out the real case of the blocking.
     * @param modal
     *            Whether the dialog is modal. A modal Dialog blocks the
     *            interaction with other parts of the application. Note that
     *            you can also get a similar behavior using the
     *            {@link #run() run()} method.
     */
    public Dialog(String title, Window parent, boolean modal) {
        super(GtkDialog.createDialogWithButtons(title, parent, modal ? DialogFlags.MODAL
                : DialogFlags.NONE, null));
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
     * end of the Dialog's action area. The given response will be contained
     * in the Dialog's {@link RESPONSE RESPONSE} event if the added button is
     * clicked.
     * 
     * @return the added button
     */
    public Button addButton(String buttonText, ResponseType response) {
        return (Button) GtkDialog.addButton(this, buttonText, response.getValue());
    }

    /**
     * Add a Button whose icon and label are taken from a given Stock. It is a
     * good idea to use an Stock Button for common actions.
     * 
     * <p>
     * The given response will be contained in the Dialog's
     * {@link RESPONSE RESPONSE} signal if the added button is clicked.
     */
    public Button addButton(Stock stock, ResponseType response) {
        return (Button) GtkDialog.addButton(this, stock.getStockId(), response.getValue());
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
     * 
     * @return the emitted response.
     */
    public ResponseType run() {
        int value;
        ResponseType response;

        value = GtkDialog.run(this);
        response = ResponseType.instanceFor(value);

        if (response == null) {
            throw new RuntimeException("Unexpected return response " + value);
        }

        return response;
    }

    /**
     * This signal arises when a user activates an action widget, means one of
     * the widgets laying out in the action area.
     * 
     * @author Thomas Schmitz
     * @author Vreixo Formoso
     * @since 4.0.5
     */
    public interface RESPONSE
    {
        void onResponse(Dialog source, ResponseType response);
    }

    public void connect(RESPONSE handler) {
        GtkDialog.connect(this, new ResponseHandler(handler));
    }

    /*
     * Needed for emit the RESPONSE signal with a type-safe ResponseType
     * instead of an int.
     */
    private static class ResponseHandler implements GtkDialog.RESPONSE
    {
        private RESPONSE handler;

        public ResponseHandler(RESPONSE handler) {
            super();
            this.handler = handler;
        }

        public void onResponse(Dialog source, int responseId) {
            ResponseType response;
            response = ResponseType.instanceFor(responseId);

            if (response == null) {
                throw new RuntimeException("Unexpected return response " + responseId);
            } else {
                handler.onResponse(source, response);
            }
        }
    }
}
