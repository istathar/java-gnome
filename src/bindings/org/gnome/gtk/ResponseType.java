/*
 * ResponseType.java
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

import java.util.HashMap;

/**
 * Predefined responses for a {@link Dialog Dialog}. Responses are usually
 * associated with a Button in the Dialog, but can also come from other
 * Widgets or user Actions, such as closing the Dialog window.
 * 
 * <p>
 * If one of such responses fits your needs, it is recommended that you make
 * use of it, mainly for code clarity. You can also define your own responses
 * by extending this class, for example:
 * 
 * <pre>
 * public class FileResponse extend ResponseType
 * {
 *     protected FileResponse(String nickname) {
 *         super(nickname);
 *     }
 *     
 *     public static final FileResponse OPEN = new FileResponse("OPEN");
 *     
 *     public static final FileResponse SAVE = new FileResponse("SAVE");
 *     
 *     public static final FileResponse NEW = new FileResponse("NEW");
 * }
 * </pre>
 * 
 * 
 * @author Vreixo Formoso
 * @since 4.0.5
 */
/*
 * This is an exception to the usual usage of enumerated Constants. First,
 * although it is an enum, its values in Gtk+ doesn't grow incrementally from
 * 0. They're all negative values, because positive values are reserved for
 * user usage. That's another difference with usual enums: the user can define
 * his own codes. Finally, Gtk+ never uses this type in function declarations.
 * Instead, it uses int. However, in java we want to provide type-safety.
 */
public class ResponseType
{

    /*
     * All values defined by Gtk+ are negative numbers. Positive values from 0
     * are reserved for user usage. To ensure a user doesn't give the same
     * value, we don't let him to provide one, and just use this field as a
     * counter for user defined response codes.
     */
    private static int response;

    private static final HashMap knownResponses;

    static {
        response = 0;
        knownResponses = new HashMap();
    }

    private final int value;

    private final String nickname;

    private ResponseType(int value, String nickname) {
        this.value = value;
        this.nickname = nickname;
        registerResponse(this);
    }

    /**
     * Constructor to let user define its own ResponseTypes.
     * 
     * @param nickname
     *            An String used mainly for debugging purposes.
     */
    /*
     * An unique value is assigned automatically
     */
    protected ResponseType(String nickname) {
        this.value = response++;
        this.nickname = nickname;
        registerResponse(this);
    }

    int getValue() {
        return value;
    }

    private static void registerResponse(ResponseType response) {
        knownResponses.put(new Integer(response.value), response);
    }

    static ResponseType instanceFor(int value) {
        return (ResponseType) knownResponses.get(new Integer(value));
    }

    public String toString() {
        return nickname;
    }

    /**
     * This corresponds to the programmatic hidding of the Dialog using the
     * {@link Dialog#hide() hide()} method.
     */
    public static final ResponseType NONE = new ResponseType(-1, "NONE");

    /**
     * Returned when the user close the Dialog window, for example, by
     * clicking the [x] Button or press Alt+F4 key.
     */
    public static final ResponseType DELETE_EVENT = new ResponseType(-4, "DELETE_EVENT");

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
    public static final ResponseType OK = new ResponseType(-5, "OK");

    /**
     * Usually associated with a Button whose action is closing the Dialog,
     * discarding any changes made on it by the user, or cancelling an action,
     * either being executed or just before executing it.
     * 
     * <p>
     * This Button is usually disposed at the left of the Ok Button.
     */
    public static final ResponseType CANCEL = new ResponseType(-6, "CANCEL");

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
    public static final ResponseType CLOSE = new ResponseType(-7, "CLOSE");

    /**
     * Associated with a Button whose action is to accept the changes made by
     * the user, but without closing the Dialog.
     */
    public static final ResponseType APPLY = new ResponseType(-10, "APPLY");

    /**
     * Associated with "Yes" Buttons, used in Dialogs that ask some question
     * to the user.
     */
    public static final ResponseType YES = new ResponseType(-8, "YES");

    /**
     * Associated with "No" Buttons, used in Dialogs that ask some question to
     * the user.
     */
    public static final ResponseType NO = new ResponseType(-9, "NO");

    /**
     * This response is associated with a help Button, whose Action is to open
     * a contextual help about the Dialog and the settings it allow to change.
     */
    public static final ResponseType HELP = new ResponseType(-11, "HELP");

}
