/*
 * Window.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2006 Operational Dynamics
 */
package org.gnome.gtk;

public class Window extends Bin {

    /**
     * Create a new Window
     */
    public Window() {
        super(GtkWindow.createWindow(WindowType.TOPLEVEL));
    }

    /**
     * Create a new Window of the specified type. In general you don't need to
     * use this; see the comments in {@link org.gnome.gtk.WindowType WindowType};
     * in particular, {@link org.gnome.gtk.WindowType#POPUP POPUP} is <b>not</b>
     * for dialog windows!
     */
    public Window(WindowType type) {
        super(GtkWindow.createWindow(type));
    }

    public void setTitle(String title) {
        GtkWindow.setTitle(this, title);
    }
    
    public void setDecorated(boolean decorated) {
        // FIXME implement
    }
    
//    public interface clicked {
//        public void onClicked(Window target);
//    }
//    
//    public void connect(clicked handler) {
//        // FIXME
//    }

}
