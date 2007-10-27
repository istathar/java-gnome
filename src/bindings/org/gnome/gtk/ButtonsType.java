/*
 * ButtonsType.java
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

import org.freedesktop.bindings.Constant;

/**
 * Prebuilt set of Buttons for a {@link MessageDialog MessageDialog}.
 * 
 * <p>
 * MessageDialogs usually make use of one of these predefined sets. If none of
 * these choices are appropriate, simply use {@link #NONE NONE} and then call
 * the {@link MessageDialog#addButton(String, ResponseType) addButton()}
 * methods.
 * 
 * <p>
 * When you use one of these ButtonsType, the Button is automatically
 * associated with the corresponding {@link ResponseType ResponseType}.
 * 
 * @see MessageDialog
 * 
 * @author Vreixo Formoso
 * @version 4.0.5
 */
public final class ButtonsType extends Constant
{
    private ButtonsType(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * No Buttons at all. Useful if none of these choices are suitable for
     * your needs and you want to add your own Buttons.
     */
    public static final ButtonsType NONE = new ButtonsType(GtkButtonsType.NONE, "NONE");

    /**
     * A single OK Button. Use this for MessageDialogs that present a message
     * that the user can just accept, without having any other choice.
     */
    public static final ButtonsType OK = new ButtonsType(GtkButtonsType.OK, "OK");

    /**
     * A single CLOSE Button. Note that in most cases the {@link #OK OK}
     * Button is a better alternative.
     */
    public static final ButtonsType CLOSE = new ButtonsType(GtkButtonsType.CLOSE, "CLOSE");

    /**
     * A single CANCEL Button. If you plan to use a MessageDialog for
     * notifying the user about a task that is being executed, where the
     * CANCEL Button can be used to interrupt that task, don't do that! A
     * Dialog with a {@link ProgressBar ProgressBar} is the correct choice.
     */
    public static final ButtonsType CANCEL = new ButtonsType(GtkButtonsType.CANCEL, "CANCEL");

    /**
     * The MessageDialog will have two Buttons, Yes and No. Use this when the
     * Dialog asks something to the user. Note that the
     * {@link #OK_CANCEL OK_CANCEL} may be a better alternative if you want a
     * confirmation to start some task.
     */
    public static final ButtonsType YES_NO = new ButtonsType(GtkButtonsType.YES_NO, "YES_NO");

    /**
     * The MessageDialog will have two Buttons, OK and CANCEL. Use this for
     * MessageDialogs that ask for confirmation or that alert about warning
     * situations that can be cancelled.
     */
    public static final ButtonsType OK_CANCEL = new ButtonsType(GtkButtonsType.OK_CANCEL, "OK_CANCEL");

}
