/*
 * GtkLabel.java
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 *
 *                      THIS FILE WILL BE GENERATED CODE!
 *
 * To modify its contents or behaviour, either update the generation program,
 * change the information in the source defs file, or implement an override
 * for this class.
 */
package org.gnome.gtk;

import org.gnome.glib.Plumbing;
import org.gnome.glib.Signal;

final class GtkLabel extends Plumbing
{
    private GtkLabel() {}

    /**
     * gtk_label_new()
     * 
     * @param str
     */
    /*
     * These JavaDoc are almost completely unnecessary, but provide some
     * visual distinction when viewing these files and do provide the
     * slightest nudge reminder about what you're wrapping. I'm not sure
     * whether or not I'll have the code generator actually turn them out.
     */
    static final long createLabel(String str) {
        return gtk_label_new(str);
    }

    private static native final long gtk_label_new(String str);

    /**
     * gtk_label_set_text()
     * 
     * @param label
     * @param str
     */
    static final void setText(Label self, String str) {
        gtk_label_set_text(pointerOf(self), str);
    }

    private static final native void gtk_label_set_text(long label, String str);

    /**
     * gtk_label_get_text()
     * 
     * @param label
     */
    static final String getText(Label self) {
        return gtk_label_get_text(pointerOf(self));
    }

    private static final native String gtk_label_get_text(long label);

    /**
     * gtk_label_set_label()
     * 
     * @param label
     * @param str
     */
    static final void setLabel(Label self, String str) {
        gtk_label_set_label(pointerOf(self), str);
    }

    private static final native void gtk_label_set_label(long label, String str);

    /**
     * gtk_label_get_label()
     * 
     * @param label
     */
    static final String getLabel(Label self) {
        return gtk_label_get_label(pointerOf(self));
    }

    private static final native String gtk_label_get_label(long label);

    /**
     * gtk_label_set_use_markup()
     * 
     * @param label
     * @param setting
     */
    static final void setUseMarkup(Label self, boolean setting) {
        gtk_label_set_use_markup(pointerOf(self),setting);
    }

    private static native void gtk_label_set_use_markup(long label, boolean setting);
    
    /**
     * gtk_label_set_angle()
     * 
     * @param angle
     */
    static final void setAngle(Label self, double angle){
        gtk_label_set_angle(pointerOf(self), angle);
    }
    private static final native void gtk_label_set_angle(long label, double angle);
    
    /**
     * gtk_label_get_angle()
     * 
     * @param label
     */
    static final double getAngle(Label self){
        return gtk_label_get_angle(pointerOf(self));
    }
    private static final native double gtk_label_get_angle(long label);
    
    
    interface COPY_CLIPBOARD extends Signal
    {
        void onCopyClipboard(Label source);
    }

    static final void connect(Label self, GtkLabel.COPY_CLIPBOARD handler) {
        connectSignal(self, handler, GtkLabel.class, "copy-clipboard");
    }

    /*
     * There are two more signals, MOVE_CURSOR and POPULATE_POPUP which may or
     * may not need wrapping and in any case are too complicated for the
     * mockup at present.
     */

    /*
     * called by native code!
     */
    protected static final void handleCopyClipboard(Signal handler, long source) {
        ((GtkLabel.COPY_CLIPBOARD) handler).onCopyClipboard((Label) instanceFor(source));
    }
}
