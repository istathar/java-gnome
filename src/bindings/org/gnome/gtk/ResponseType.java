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
 * Predefined responses for a {@link Dialog Dialog}. Each ResponseType is
 * usually associated with a preconfigured action Button in the Dialog, but
 * can also come from other Widgets or user actions, such as closing the
 * Window.
 * 
 * <p>
 * If one of these responses constants fits your needs, it is recommended that
 * you make use of it. This is partly for code clarity but mostly because
 * there are a number of special behaviours within GTK which presume you're
 * using the correct predefined constants. This is especially the case in
 * FileChooserDialog.
 * 
 * <p>
 * If your needs require it, however, you can define your own responses codes
 * by extending this class. For example:
 * 
 * <pre>
 * public class PowerResponseType extend ResponseType
 * {
 *     protected PowerResponseType(String nickname) {
 *         super(nickname);
 *     }
 *     
 *     public static final PowerResponseType SQUARED = new PowerResponseType(&quot;SQUARED&quot;);
 *     
 *     public static final PowerResponseType CUBED = new PowerResponseType(&quot;CUBED&quot;);
 *     
 *     public static final PowerResponseType FOURTH = new PowerResponseType(&quot;FOURTH&quot;);
 * }
 * </pre>
 * 
 * 
 * @author Vreixo Formoso
 * @author Andrew Cowie
 * @since 4.0.5
 */
/*
 * This is an exception to the usual usage of enumerated Constants. First,
 * although it is an enum, its values in Gtk+ doesn't grow incrementally from
 * 0. They're all negative values, because positive values are reserved for
 * user usage. Therefore we have hacked the .defs data to be (define-flags
 * ...) to force the generated translation layer to reach to native for the
 * ordinals. The other difference is that the developer can define their own
 * codes to be associated with specific actions, and we provide the mechanism
 * to support this.
 */
public class ResponseType extends Constant
{
    /*
     * All the response codes predefined in GTK have negative ordinals.
     * Positive values are reserved for custom usage. To ensure a developer
     * doesn't give an already-used value, we use this field as a counter to
     * supply response codes.
     */
    private static int response;

    static {
        response = 1;
    }

    private ResponseType(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Constructor to let developer define their own ResponseTypes.
     * 
     * @param nickname
     *            Used mainly for debugging purposes. The String supplied
     *            should match the name of the constant as declared in your
     *            ResponseType subclass.
     */
    /*
     * An unique ordinal is assigned automatically
     */
    protected ResponseType(String nickname) {
        super(response++, nickname);
    }

    /*
     * It's not entirely necessary to have these here; we could just have
     * Dialog call the static methods in GtkResponseTypeOverride directly, but
     * the code seems a bit to have this as instance method here...
     */
    int getResponseId() {
        return GtkResponseTypeOverride.numOf(this);
    }

    /*
     * ... on the other hand, this is just clumsy, but it is at least
     * consistent with getResponseId() and likewise a bit cleaner.
     */
    static ResponseType constantFor(int ordinal) {
        return GtkResponseTypeOverride.enumFor(ordinal);
    }

    /**
     * This corresponds to the programmatic hiding of the Dialog using the
     * {@link Dialog#hide() hide()} method.
     */
    public static final ResponseType NONE = new ResponseType(GtkResponseType.NONE, "NONE");

    /**
     * Returned when the user close the Dialog window, for example, by
     * clicking the [x] Button or press Alt+F4 key.
     */
    public static final ResponseType DELETE_EVENT = new ResponseType(GtkResponseType.DELETE_EVENT,
            "DELETE_EVENT");

    /**
     * This response is usually associated with a Button whose action involves
     * closing the Dialog and accept the action proposed.
     * 
     * <p>
     * Typical usages of this response are: close an informative Dialog,
     * accept changes made by user on a preferences Dialog, or start an action
     * with the options shown in the Dialog.
     * 
     * <p>
     * In your Dialogs you should put this kind of Button in the Dialog right
     * corner.
     */
    public static final ResponseType OK = new ResponseType(GtkResponseType.OK, "OK");

    /**
     * Usually associated with a Button whose action is closing the Dialog,
     * discarding any changes made on it by the user, or cancelling an action,
     * either being executed or just before executing it.
     * 
     * <p>
     * This Button is usually disposed at the left of the Ok Button.
     */
    public static final ResponseType CANCEL = new ResponseType(GtkResponseType.CANCEL, "CANCEL");

    /**
     * Used in a Button whose action is to close the Dialog.
     * 
     * <p>
     * Provide this kind of Buttons only on informative Dialogs, where the
     * user cannot do any change, or in Dialogs where the changes can be
     * easily reverted later (such as some preferences Dialogs). If the
     * changes the Dialog allow have a great impact of the application, or it
     * starts some action, it is better to provide {@link #OK} and
     * {@link #CANCEL} Buttons.
     */
    public static final ResponseType CLOSE = new ResponseType(GtkResponseType.CLOSE, "CLOSE");

    /**
     * Associated with a Button whose action is to accept the changes made by
     * the user, but without closing the Dialog.
     */
    public static final ResponseType APPLY = new ResponseType(GtkResponseType.APPLY, "APPLY");

    /**
     * Associated with "Yes" Buttons, used in Dialogs that ask some question
     * to the user.
     */
    public static final ResponseType YES = new ResponseType(GtkResponseType.YES, "YES");

    /**
     * Associated with "No" Buttons, used in Dialogs that ask some question to
     * the user.
     */
    public static final ResponseType NO = new ResponseType(GtkResponseType.NO, "NO");

    /**
     * This response is associated with a help Button, whose Action is to open
     * a contextual help about the Dialog and the settings it allow to change.
     */
    public static final ResponseType HELP = new ResponseType(GtkResponseType.HELP, "HELP");
}
