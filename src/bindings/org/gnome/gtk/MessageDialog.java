/*
 * MessageDialog.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * A convenient Dialog to show a message to the user.
 * 
 * <p>
 * MessageDialogs are used to show some information message to the user, warn
 * him about application errors or problems, and let him choose what action to
 * take.
 * 
 * <p>
 * A MessageDialog is a simple Dialog with an image representing the type of
 * the message (error, warning, etc... take a look at
 * {@link MessageType MessageType}), the message text and some Buttons to let
 * the user decide how to respond to the message. The
 * {@link ButtonsType ButtonsType} enumeration can be used to choose among
 * some common combination of Buttons, but you can also add other Buttons via
 * the usual Dialog's {@link #addButton(String, ResponseType) addButton()}
 * method.
 * 
 * <p>
 * Optionally you can provide a secondary text that displays more information
 * about the message. Both the main message and the secondary text can be
 * formatted with Pango markup.
 * 
 * <p>
 * In most cases, MessageDialog is a modal Dialog, that you will show with the
 * {@link #run() run()} method, and hide once the user has responded o it,
 * much like in the following example:
 * 
 * <pre>
 * // create the Dialog
 * MessageDialog dialog = new MessageDialog(window, MessageType.WARNING, ButtonsType.OK_CANCEL,
 *         &quot;Do you want to delete file.txt?&quot;);
 * dialog.setSecondaryText(&quot;If you delete that file, you will loose all the information there&quot;);
 * 
 * // Show the Dialog
 * ResponseType choice = dialog.run();
 * 
 * // Hide it once the user has responded
 * dialog.hide();
 * 
 * // process
 * if (choice == ResponseType.OK) {
 *     // delete the file...
 * } else {
 *     // cancel...
 * }
 * </pre>
 * 
 * @see MessageType
 * @see ButtonsType
 * 
 * @author Vreixo Formoso
 * @since 4.0.5
 */
public class MessageDialog extends Dialog
{
    protected MessageDialog(long pointer) {
        super(pointer);
    }

    /**
     * Create a new MessageDialog.
     * 
     * @param parent
     *            Transient parent for the MessageDialog. It can be
     *            <code>null</code>, but if you have a parent Window you
     *            will want to pass it here to force the Dialog to be on top
     *            of the parent Window and to be presented to the user when
     *            (s)he selects the parent Window.
     * @param modal
     *            Whether the Dialog will be modal.
     * @param type
     *            Type of the Dialog
     * @param buttons
     *            Set of Buttons to be presented.
     * @param message
     *            The main message of the Dialog. If you want to use a String
     *            formatted with the Pango markup, you will need to call
     *            {@link #setUseMarkup(boolean) setUseMarkup()} later.
     */
    public MessageDialog(Window parent, boolean modal, MessageType type, ButtonsType buttons,
            String message) {

        super(GtkMessageDialog.createMessageDialog(parent, modal ? DialogFlags.MODAL : DialogFlags.NONE,
                type, buttons, null));

        /*
         * We set the property instead of passing it in the constructor to
         * avoid possible problems with the printf()-style.
         */
        setPropertyString("text", message);
    }

    /**
     * Create a new MessageDialog. This is a convenience constructor that
     * makes the Dialog modal. Use this
     * {@link #MessageDialog(Window, boolean, MessageType, ButtonsType, String) constructor}
     * if you plan to build a non modal Dialog.
     * 
     * @see #MessageDialog(Window, boolean, MessageType, ButtonsType, String)
     */
    public MessageDialog(Window parent, MessageType type, ButtonsType buttons, String message) {

        super(GtkMessageDialog.createMessageDialog(parent, DialogFlags.MODAL, type, buttons, null));

        setPropertyString("text", message);
    }

    /**
     * Set whether the message text is to be parsed as containing markup in
     * Pango's text markup language. Using this allows MessageDialog to be
     * created with expressive formatting considerably more advanced than a
     * simple line of text.
     * 
     * @param setting
     *            If setting is true, then any markup included in the text is
     *            interpreted as such. If its set to false, markup is ignored
     *            and included as-is.
     */
    public void setUseMarkup(boolean setting) {
        setPropertyBoolean("use-markup", setting);
    }

    /**
     * Whether the message text is to be interpreted as marked up with Pango's
     * text markup language. When enabled, the MessageDialog can show
     * formatted text instead of just a simple line of text.
     */
    public boolean getUseMarkup() {
        return getPropertyBoolean("use-markup");
    }

    /**
     * Sets the secondary text for the MessageDialog.
     * 
     * <p>
     * Note that setting a secondary text makes the primary text become bold,
     * unless you are using markup.
     * 
     * @param text
     *            The text to be used as secondary text.
     * @param markup
     *            Whether to interpret this secondary text as marked up with
     *            Pango's text markup language.
     */
    public void setSecondaryText(String text, boolean markup) {
        setPropertyString("secondary-text", text);
        setPropertyBoolean("secondary-use-markup", markup);
    }

    /**
     * Sets the secondary text for the MessageDialog. This text is interpreted
     * as plain text, if you want to use Pango markup format you should call
     * {@link #setSecondaryUseMarkup(boolean) setSecondaryUseMarkup()} or call
     * {@link #setSecondaryText(String, boolean) setSecondaryText(String, true)}
     * instead.
     */
    public void setSecondaryText(String text) {
        setPropertyString("secondary-text", text);
    }

    /**
     * Whether the secondary text is to be interpreted as marked up with
     * Pango's text markup language.
     */
    public boolean getSecondaryUseMarkup() {
        return getPropertyBoolean("secondary-use-markup");
    }

    /**
     * Set whether the secondary text is to be parsed as containing markup in
     * Pango's text markup language.
     */
    public void setSecondaryUseMarkup(boolean setting) {
        setPropertyBoolean("secondary-use-markup", setting);
    }

    /**
     * Set the Widget to be used as the image for the MessageDialog. For
     * common {@link MessageType types}, the image is set to an appropriate
     * icon from the Stock. That is what you must use in most cases. However,
     * there can be some cases where you want to provide your own Widget. In
     * that cases, you should set the message type to
     * {@link MessageType#OTHER OTHER} when creating the Dialog.
     */
    public void setImage(Widget image) {
        GtkMessageDialog.setImage(this, image);
    }

    /**
     * Shows an information message to the user. This is a convenience method
     * that creates an {@link MessageType#INFO INFO} MessageDialog with a
     * single OK Button and given message. It shows the Dialog and blocks
     * until the user clicks the OK Button or closes the Dialog.
     * 
     * @param parent
     *            Parent Window.
     * @param message
     *            The text of the message.
     * @param secondary
     *            The secondary text, or <code>null</code> to not use any
     *            secondary text.
     */
    public static void info(Window parent, String message, String secondary) {
        MessageDialog dialog;

        dialog = new MessageDialog(parent, MessageType.INFO, ButtonsType.OK, message);
        if (secondary != null) {
            dialog.setSecondaryText(secondary);
        }
        dialog.run();
        dialog.hide();
    }

    /**
     * Shows an error message to the user. This is a convenience method that
     * creates an {@link MessageType#ERROR ERROR} MessageDialog with a single
     * OK Button and given message. It shows the Dialog and blocks until the
     * user clicks the OK Button or closes the Dialog.
     * 
     * @param parent
     *            Parent Window.
     * @param message
     *            The text of the message.
     * @param secondary
     *            The secondary text, or <code>null</code> to not use any
     *            secondary text.
     */
    public static void error(Window parent, String message, String secondary) {
        MessageDialog dialog;

        dialog = new MessageDialog(parent, MessageType.ERROR, ButtonsType.OK, message);
        if (secondary != null) {
            dialog.setSecondaryText(secondary);
        }
        dialog.run();
        dialog.hide();
    }

    /**
     * Ask a question to the user. This is a convenience method that creates
     * an {@link MessageType#QUESTION QUESTION} MessageDialog with Yes and No
     * Buttons and given question. It shows the Dialog and blocks until the
     * user clicks one of the Buttons or closes the Dialog.
     * 
     * @param parent
     *            Parent Window.
     * @param question
     *            The question.
     * @param secondary
     *            The secondary text, or <code>null</code> to not use any
     *            secondary text.
     * @return <code>true</code> if the user has selected "yes",
     *         <code>false</code> otherwise, i.e., if the user has chosen
     *         "no" or has closed the Dialog.
     */
    public static boolean question(Window parent, String question, String secondary) {
        MessageDialog dialog;
        ResponseType choice;

        dialog = new MessageDialog(parent, MessageType.QUESTION, ButtonsType.YES_NO, question);
        if (secondary != null) {
            dialog.setSecondaryText(secondary);
        }
        choice = dialog.run();
        dialog.hide();

        return (choice == ResponseType.YES);
    }

    /**
     * Shows a warning message to the user. This is a convenience method that
     * creates an {@link MessageType#WARNING WARNING} MessageDialog with Ok
     * and Cancel Buttons and given alert. It shows the Dialog and blocks
     * until the user clicks one of the Buttons or closes the Dialog.
     * 
     * @param parent
     *            Parent Window.
     * @param alert
     *            The alert.
     * @param secondary
     *            The secondary text, or <code>null</code> to not use any
     *            secondary text.
     * @return <code>true</code> if the user has selected "ok",
     *         <code>false</code> otherwise, i.e., if the user has chosen
     *         "cancel" or has closed the Dialog.
     */
    public static boolean warning(Window parent, String alert, String secondary) {
        MessageDialog dialog;
        ResponseType choice;

        dialog = new MessageDialog(parent, MessageType.WARNING, ButtonsType.OK_CANCEL, alert);
        if (secondary != null) {
            dialog.setSecondaryText(secondary);
        }
        choice = dialog.run();
        dialog.hide();

        return (choice == ResponseType.OK);
    }
}
