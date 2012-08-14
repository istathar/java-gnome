/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd and Others
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
 * A Dialog is a special Window which is used to display information for the
 * user or to get response from her. While a normal Window usually is used to
 * represent the whole application and to offer sometimes many interactions
 * for the user, a Dialog should only be opened if a special situation has
 * occurred.
 * 
 * <p>
 * A Dialog is a composite Window which contains a {@link VBox}. This box is
 * split by an HSeparator into two areas:
 * 
 * <ul>
 * <li>
 * <p>
 * The <var>main area</var> consists of the VBox itself above the separator.
 * It is used to pack Widgets such as Labels or Icons which will display the
 * descriptive part of your Dialog. The easiest way to add Widget(s) to the
 * top area is to simply call the {@link Dialog#add(Widget) add()} method. It
 * is a good technique to first add your own Container such as an {@link HBox}
 * to the top area and pack all other Widgets to it if you need more refined
 * layout control, although this is not compulsory.
 * 
 * <li>
 * <p>
 * The bottom <var>action area</var> (actually an HButtonBox) is used to pack
 * buttons to the Dialog which may perform actions such as "Ok" or "Cancel".
 * You should only add Buttons to the <var>action area</var> using one of the
 * {@link Dialog#addButton(String, ResponseType) addButton()} methods allowing
 * you to specify a ResponseType constant to be emitted if the Button is
 * clicked.
 * </ul>
 * 
 * <p>
 * To open the Dialog as a normal non-blocking Window you use the
 * {@link Window#present() present()} method after you have packed the various
 * child elements into it. On the other hand, for occasions where you are
 * using a Dialog to get information required for the further progress of the
 * main application, the {@link Dialog#run() run()} method can be used to open
 * the Dialog. This method blocks the application and waits for response from
 * the user.
 * 
 * <p>
 * Like any Window that you are finished with, you have to
 * {@link Window#hide() hide()} when you receive a
 * <code>Dialog.Response</code> callback, or after {@link Dialog#run() run()}
 * returns.
 * 
 * <p>
 * GTK comes with a number of standard Dialogs which can be used for typical
 * situations such as {@link MessageDialog MessageDialog} to present urgent
 * information to the user, and {@link FileChooserDialog FileChooserDialog}
 * which provides the familiar behaviour of popping up a Dialog to select a
 * file.
 * 
 * @author Thomas Schmitz
 * @author Vreixo Formoso
 * @author Andrew Cowie
 * @since 4.0.5
 */
public class Dialog extends Window
{
    protected Dialog(long pointer) {
        super(pointer);
    }

    /**
     * Creates a new Dialog box with both <var>main area</var> and an empty
     * <var>action area</var>, separated by an HSeparator. You'll need to do
     * the rest of the Dialog setup manually yourself, including calling
     * {@link Window#setTransientFor(Window) setTransientFor()} and
     * {@link Window#setTitle(String) setTitle()}.
     * 
     * @since 4.0.5
     */
    public Dialog() {
        super(GtkDialog.createDialog());
    }

    /**
     * Create a new Dialog. This is a convenience constructor with the same
     * effect as:
     * 
     * <pre>
     * d = new Dialog();
     * d.setTitle(title);
     * d.setModal(modal);
     * d.setTransientFor(parent);
     * </pre>
     * 
     * @param title
     *            Title of the new Dialog.
     * @param parent
     *            Transient parent of the dialog, or <code>null</code>. If a
     *            parent is provided, the window manager will keep the Dialog
     *            on top of it. If the Dialog is modal, it is highly
     *            recommended that you provide a parent Window, otherwise the
     *            user could be trying to interact with the main Window while
     *            the Dialog is blocking it, but hidden under other Window. In
     *            that case, the user experience is really bad, as it is not
     *            easy to figure out the real case of the blocking.
     * @param modal
     *            Whether the dialog is to be modal or not. A modal Dialog
     *            blocks interaction with the other parts of the application.
     *            Note that you can also get a similar behaviour using the
     *            {@link #run() run()} method.
     * @since 4.0.5
     */
    public Dialog(String title, Window parent, boolean modal) {
        super(GtkDialog.createDialogWithButtons(title, parent, modal ? DialogFlags.MODAL
                : DialogFlags.NONE, null));
    }

    /**
     * Add a Widget to the <var>main area</var> of the Dialog.
     * 
     * @since 4.0.5
     */
    public void add(Widget widget) {
        final Box box;

        box = (Box) this.getChild();
        box.add(widget);
    }

    /**
     * Adds an action {@link Button} with the given text as its label to the
     * end of the Dialog's <var>action area</var>. The given ResponseType will
     * be returned back in the Dialog's {@link Dialog.Response} signal when
     * the Button added as a result of this call is clicked.
     * 
     * @return the added Button. This is a convenience allowing you to hook up
     *         further handlers to the Button if necessary.
     * @since 4.0.5
     */
    public Button addButton(String text, ResponseType response) {
        return (Button) GtkDialog.addButton(this, text, response.getResponseId());
    }

    /**
     * Add a Button whose icon and label are taken from a given Stock. It is,
     * as ever, recommended to use a Stock Button for common actions. See
     * {@link #addButton(String, ResponseType) addButton()}.
     * 
     * @since 4.0.5
     */
    public Button addButton(Stock stock, ResponseType response) {
        return (Button) GtkDialog.addButton(this, stock.getStockId(), response.getResponseId());
    }

    /**
     * Add a Button Widget you've already constructed to the action area of
     * this Dialog.
     * 
     * <p>
     * It is, as ever, recommended to use a Stock Button for common actions.
     * See {@link #addButton(String, ResponseType) addButton()}.
     * 
     * <p>
     * <i>You can pass any Activatable as the <code>widget</code>
     * parameter.</i>
     * 
     * @since 4.0.14
     */
    public Button addButton(Widget widget, ResponseType response) {
        GtkDialog.addActionWidget(this, widget, response.getResponseId());

        /*
         * The other addButton() signature returns Button, though the native
         * code here actually takes a GtkActivatable. So workaround in this
         * overload by returning Button if possible.
         */

        if (widget instanceof Button) {
            return (Button) widget;
        } else {
            return null;
        }
    }

    /**
     * Block the rest of the application by running in a recursive main loop
     * until a {@link Dialog.Response} signal arises on the Dialog as a result
     * of the user activating one of the <var>action area</var> Buttons. This
     * is known as a 'modal' Dialog. While this loop is running the user is
     * prevented from interacting with the rest of the application.
     * 
     * <pre>
     * response = dialog.run();
     * 
     * dialog.hide();
     * if (response == ResponseType.CANCEL) {
     *     return;
     * }
     * // take action!
     * </pre>
     * 
     * If you don't care about the response, just go ahead and
     * <code>hide()</code> right away as soon as <code>run()</code> returns.
     * 
     * <pre>
     * dialog = new AboutDialog();
     * dialog.run();
     * dialog.hide();
     * </pre>
     * 
     * <p>
     * While there are legitimate uses of modal Dialogs, it is a feature that
     * tends to be badly abused. Therefore, before you call this method,
     * please consider carefully if it is wise to prevent the rest of the user
     * interface from being used.
     * 
     * <p>
     * A common bug is for people to neglect to {@link Widget#hide() hide()}
     * the Dialog after this method returns. If you don't, then the Dialog
     * will remain on screen despite repeated clicking of (for example) the
     * "Close" Button [the Window is not hidden automatically because of cases
     * like "Apply" in preferences Dialogs and "Open" in FileChoosers when a
     * folder is selected and activation will change directory rather than
     * finishing the Dialog].
     * 
     * <p>
     * While <code>run()</code> can be very useful in callbacks when popping
     * up a quick question, you may find hooking up to the
     * {@link Dialog.Response} signal more flexible.
     * 
     * <p>
     * <b>Warning</b><br>
     * <i>Using this method will prevent other threads that use GTK from
     * running. That's a bug as far as we're concerned, but if the goal is to
     * display a dialog without blocking other threads that use GTK, it is
     * better (for now) to call Window's</i> {@link Window#present()
     * present()} <i>and the</i> {@link #connect(Response) connect()} <i>to
     * the </i> {@link Dialog.Response} <i>signal.</i>
     * 
     * <pre>
     * dialog.connect(new Dialog.Response() {
     *     public void onResponse(Dialog source, ResponseType response) {
     *         dialog.hide();
     *         if (response == ResponseType.CLOSE) {
     *             return;
     *         }
     *         // take action!
     *     }
     * });
     * 
     * dialog.present();
     * </pre>
     * 
     * @return the emitted response constant. If asking a question, you should
     *         check this against the various constants in the ResponseType
     *         class. Don't forget to <code>hide()</code> afterwards.
     * @since 4.0.5
     * @see <a href="https://bugzilla.gnome.org/show_bug.cgi?id=606796">the
     *      bug report</a>
     */
    public ResponseType run() {
        final int value;
        final ResponseType response;

        value = GtkDialog.run(this);
        response = ResponseType.constantFor(value);

        return response;
    }

    /**
     * This signal arises when a user activates one of the Widgets laid out in
     * the <var>action area</var> of the Dialog.
     * 
     * @author Thomas Schmitz
     * @author Vreixo Formoso
     * @since 4.0.5
     */
    public interface Response
    {
        void onResponse(Dialog source, ResponseType response);
    }

    /**
     * Hook up a <code>Dialog.Response</code> handler.
     */
    public void connect(Dialog.Response handler) {
        GtkDialog.connect(this, new ResponseHandler(handler), false);
    }

    /*
     * Needed for emit the Dialog.Response signal with a type-safe
     * ResponseType instead of the underlying int ordinal.
     */
    private static class ResponseHandler implements GtkDialog.ResponseSignal
    {
        private Dialog.Response handler;

        private ResponseHandler(Dialog.Response handler) {
            this.handler = handler;
        }

        public void onResponse(Dialog source, int responseId) {
            final ResponseType response;

            response = ResponseType.constantFor(responseId);

            handler.onResponse(source, response);
        }
    }

    /**
     * Cause a <code>Dialog.Response</code> signal with the specified
     * ResponseType to be emitted by this Dialog.
     * 
     * @since 4.0.9
     */
    public void emitResponse(ResponseType response) {
        GtkDialog.response(this, response.getResponseId());
    }

    /**
     * Tell the Dialog which Button (which ResponseType) is to be activated
     * when the user presses <b><code>Enter</code></b>.
     * 
     * <p>
     * If you're calling this because you've created a custom Button with
     * {@link #addButton(Widget, ResponseType) addButton()}, you'll probably
     * need to call Widget's {@link Widget#setCanDefault(boolean)
     * setCanDefault()} on that Button first, before using this.
     * 
     * <p>
     * Note that calling Widget's {@link Widget#grabDefault() grabDefault()}
     * is insufficient; Dialogs have some fairly tricky internal wiring, and
     * you need to use this method instead.
     * 
     * @since 4.0.17
     */
    public void setDefaultResponse(ResponseType response) {
        GtkDialog.setDefaultResponse(this, response.getResponseId());
    }
}
