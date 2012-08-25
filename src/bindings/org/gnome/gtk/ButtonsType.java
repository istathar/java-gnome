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

import org.freedesktop.bindings.Constant;

/**
 * Specify that a predefined set of Buttons should be used for a
 * {@link MessageDialog MessageDialog}.
 * 
 * When you use one of these ButtonsType, each automatically constructed
 * Button is automatically associated with its corresponding ResponseType.
 * 
 * <p>
 * If none of these choices represented by the constants in this class are
 * appropriate, use {@link #NONE NONE} and then call the
 * {@link MessageDialog#addButton(String, ResponseType) addButton()} methods
 * manually. You can also specify your own response codes by subclassing
 * {@link ResponseType}.
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
     * No Buttons at all. Used when none of these choices are suitable for
     * your needs and you want to add your own Buttons instead.
     */
    public static final ButtonsType NONE = new ButtonsType(GtkButtonsType.NONE, "NONE");

    /**
     * A single "Ok" Button. Use this for MessageDialogs that present an
     * information message to which that the user can acknowledge but to which
     * they can take no other action.
     */
    public static final ButtonsType OK = new ButtonsType(GtkButtonsType.OK, "OK");

    /**
     * A single "Close" Button. In most cases (ie, information providing
     * Dialogs) the {@link #OK OK} Button is a better alternative, but
     * <code>CLOSE</code> is the appropriate choice for preferences Dialogs
     * where the settings are automatically applied.
     */
    public static final ButtonsType CLOSE = new ButtonsType(GtkButtonsType.CLOSE, "CLOSE");

    /**
     * A single "Cancel" Button.
     * 
     * <p>
     * If you intend to use a MessageDialog with only a <code>CANCEL</code>
     * Button in order to give the user the ability to interrupt a long
     * running task, you should also include a {@link ProgressBar ProgressBar}
     * in the Dialog showing the status of the task so far.
     */
    public static final ButtonsType CANCEL = new ButtonsType(GtkButtonsType.CANCEL, "CANCEL");

    /**
     * The MessageDialog will have two Buttons, "Yes" and "No". Use this when
     * the Dialog asks something to the user. Note that the {@link #OK_CANCEL
     * OK_CANCEL} may be a better alternative if you want a confirmation to
     * start some task.
     */
    public static final ButtonsType YES_NO = new ButtonsType(GtkButtonsType.YES_NO, "YES_NO");

    /**
     * The MessageDialog will have two Buttons, "Ok" and "Cancel". Use this
     * for Dialogs that ask for confirmation or that alert about warning
     * situations that can be avoided.
     */
    public static final ButtonsType OK_CANCEL = new ButtonsType(GtkButtonsType.OK_CANCEL, "OK_CANCEL");
}
