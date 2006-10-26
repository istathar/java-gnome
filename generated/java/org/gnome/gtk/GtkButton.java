/*
 * GtkButton.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2006 Operational Dynamics
 */
package org.gnome.gtk;

import org.freedesktop.bindings.Proxy;
import org.gnome.glib.Plumbing;
import org.gnome.glib.Signal;

// generated
final class GtkButton extends Plumbing {
    
	private GtkButton() {
    }

    static final Button instanceFor(long pointer) {
        Proxy obj = proxyFor(pointer);

        if (obj == null) {
            return new Button(pointer);
        } else {
            return (Button) obj;
        }
    }

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

    private static final native void gtk_button_set_label(long button,
            String label);

    static final String getLabel(Button self) {
        return gtk_button_get_label(pointerOf(self));
    }

    private static final native String gtk_button_get_label(long button);
    
    static final void connect(Button self, GtkButton.CLICKED handler) {
        connectSignal(self, handler);
    }

    static final void connect(Button self, GtkButton.DEPRESSED handler) {
        connectSignal(self, handler);
    }
    
    interface CLICKED extends Signal {
        void onClicked(Button source);
    }
    
    interface DEPRESSED extends Signal {
        boolean onDepressed(Button source, Widget whoIsDepressed);
    }
    
    interface ENTERED extends Signal {
        void onEntered(Button source);
    }
    
    /*
     * called by native code! Also, with this visibility we can unit test it?!?
     */
    protected static final void handleClicked(Signal handler, Button source) {
        ((Button.CLICKED) handler).onClicked(source);
    }

    protected static final boolean handleDepressed(Signal handler, Button source, long whoIsDepressed) {
        return ((GtkButton.DEPRESSED) handler).onDepressed(source, (Widget) proxyFor(whoIsDepressed));
    }
    
    protected static final void handleEntered(Signal handler, long source) {
        ((GtkButton.ENTERED) handler).onEntered((Button) proxyFor(source)); 
    }
}
