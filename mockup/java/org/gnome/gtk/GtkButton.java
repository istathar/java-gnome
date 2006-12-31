/*
 * GtkButton.java
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd
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

final class GtkButton extends Plumbing
{
    static {
        registerType("GtkButton", "org.gnome.gtk.Button");
    }

    private GtkButton() {}

    static final long createButton() {
        return gtk_button_new();
    }

    private static native final long gtk_button_new();

    static final long createButtonWithLabel(String label) {
        return gtk_button_new_with_label(label);
    }

    private static native final long gtk_button_new_with_label(String label);

    static final void setLabel(Button self, String label) {
        gtk_button_set_label(pointerOf(self), label);
    }

    private static final native void gtk_button_set_label(long button, String label);

    static final String getLabel(Button self) {
        return gtk_button_get_label(pointerOf(self));
    }

    private static final native String gtk_button_get_label(long button);
    
    static final void setRelief(Button self, ReliefStyle newstyle) {
        gtk_button_set_relief(pointerOf(self), numOf(newstyle));
    }
    
    private static native final void gtk_button_set_relief(long button, int newstyle);

    interface CLICKED extends Signal
    {
        void onClicked(Button source);
    }

    static final void connect(Button self, GtkButton.CLICKED handler) {
        connectSignal(self, handler, GtkButton.class, "clicked");
    }

    /**
     * This is a joke and just to experiement with more complex signal
     * signatures anid is not actually a signal in GtkButton.
     */
    interface DEPRESSED extends Signal
    {
        boolean onDepressed(Button source, Widget whoIsDepressed);
    }

    static final void connect(Button self, GtkButton.DEPRESSED handler) {
        connectSignal(self, handler, GtkButton.class, "depressed");
    }

    interface ENTERED extends Signal
    {
        void onEntered(Button source);
    }

    static final void connect(Button self, GtkButton.ENTERED handler) {
        connectSignal(self, handler, GtkButton.class, "entered");
    }

    /*
     * called by native code! Also, with this visibility we can unit test it?!?
     */
    protected static final void handleClicked(Signal handler, long source) {
        ((GtkButton.CLICKED) handler).onClicked((Button) instanceFor(source));
    }

    protected static final boolean handleDepressed(Signal handler, long source, long whoIsDepressed) {
        return ((GtkButton.DEPRESSED) handler).onDepressed((Button) instanceFor(source), (Widget)
                instanceFor(whoIsDepressed));
    }

    protected static final void handleEntered(Signal handler, long source) {
        ((GtkButton.ENTERED) handler).onEntered((Button) instanceFor(source));
    }
}
