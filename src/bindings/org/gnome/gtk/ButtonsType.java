/*
 * ButtonsType.java
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

import org.freedesktop.bindings.Constant;

/**
 * Specify that a predefined set of Buttons should be used for a
 * {@link MessageDialog MessageDialog}.
 * 
 * <p>
 * If none of these choices represented by the constants in this class are
 * appropriate, simply use {@link #NONE NONE} and then call the
 * {@link MessageDialog#addButton(String, ResponseType) addButton()} methods.
 * 
 * <p>
 * When you use one of these ButtonsType, the Button is automatically
 * associated with the corresponding {@link ResponseType ResponseType}.
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
     * your needs and you want to add your own Buttons instead.
     */
    public static final ButtonsType NONE = new ButtonsType(GtkButtonsType.NONE, "NONE");

    /**
     * A single OK Button. Use this for MessageDialogs that present an
     * information message to which that the user can acknowledge but take no
     * other action.
     */
    public static final ButtonsType OK = new ButtonsType(GtkButtonsType.OK, "OK");

    /**
     * A single CLOSE Button. In most cases (ie, information providing
     * Dialogs) the {@link #OK OK} Button is a better alternative, but CLOSE
     * is the appropriate choice for preferences Dialogs where the settings
     * are automatically applied.
     */
    public static final ButtonsType CLOSE = new ButtonsType(GtkButtonsType.CLOSE, "CLOSE");

    /**
     * A single CANCEL Button.
     * 
     * <p>
     * Don't use a MessageDialog with only a CANCEL button for giving the user
     * the ability to interrupt a long running task. Be sure to construct a
     * Dialog with a {@link ProgressBar ProgressBar} showing the status.
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
