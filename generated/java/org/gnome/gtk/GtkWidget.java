package org.gnome.gtk;

import org.gnome.glib.Plumbing;

final class GtkWidget extends Plumbing {
    
    private GtkWidget() { }

    static final void show(Widget self) {
        gtk_widget_show(pointerOf(self));
    }
    
    private static native final void gtk_widget_show(long self);
    
    static final void showAll(Widget self) {
        gtk_widget_show_all(pointerOf(self));
    }
    
    private static native final void gtk_widget_show_all(long self);
}
