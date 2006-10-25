/*
 * Gtk.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2006 Operational Dynamics
 */
package org.gnome.gtk;

/**
 * The GTK widget toolkit initialization and main loop entry point.
 * 
 * @author Andrew Cowie
 */
public final class Gtk {
    
    private static final String APIVERSION = "4.0";
    
    static {
        System.loadLibrary("gtkjni-"+APIVERSION);
    }
    

    /**
     * No instantiation. Static methods only!
     */
    private Gtk() {
    }

    /**
     * A guard against someone calling init() twice
     */
    private static boolean initialized = false;

    /**
     * 
     * @param args
     *            The command line arguments array. This is passed to the
     *            underlying library to allowing user (or window manager) to
     *            alter GTK's behaviour.
     */
    public static void init(String[] args) {
        if (initialized) {
            return;
        }
//
//        Glib.init(args);

        GtkMain.init(args);
        
        initialized = true;
    }

    /**
     * This method blocks, ie, it does not return until the GTK main loop is
     * terminated. If you wish to
     * <p>
     * You can nest calls to Gtk.main()! If you do, then calling
     * {@link #mainQuit() mainQuit()} will make the innermost invocation of the
     * main loop return. (This is how modal Dialog boxes run and block the rest
     * of the application while still accepting events themselves)
     */
    public static void main() {
        GtkMain.main();
    }

    public static void mainQuit() {
    }

}
