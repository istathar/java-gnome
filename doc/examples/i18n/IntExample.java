/*
 * I18n.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008 Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package i18n;

import static org.gnome.glib.I18n._;
import static org.gnome.glib.I18n.N_;

import java.util.Calendar;

import org.gnome.glib.I18n;
import org.gnome.gtk.Gtk;

/**
 * Little example of i18n features, still to be improved.
 * 
 * @author Vreixo Formoso
 */
public class IntExample
{
    /*
     * With code that will be executed before calling I18n.init(), you need
     * to use N_() function instead of _(). This just marks the string as
     * translatable, it does not really translate (it can't!), so when actually
     * print, you will need to call _()! See below.
     */
    private static final String GOODBYE = N_("Goodbye");
    
    public static void main(String[] args) {
        
        /* this is called just to link against JNI library */
        Gtk.init(args);
        
        I18n.init("example", "doc/examples/i18n/po");
        
        /* we can translate simple messages */
        System.out.println(_("Hello"));
        
        /* as GOODBYE is marked with N_(), we need to call _() */
        System.out.println(_(GOODBYE));
        
        /* or also use messages with parameters */
        System.out.println(_("User name is {0} and home dir is {1}", System.getProperty("user.name"), System.getProperty("user.home")));
        
        /* but also parameters that should be formatted in a locale-dependent way */
        System.out.println(_("This costs {0,number,currency}", 1555.45));
        System.out.println(_("Today is {0,date,long}", Calendar.getInstance().getTime()));
        
        /* we even have support for advanced usage */
        System.out.println(_("There {0,choice,0#are no files|1#is one file|1<are {0,number,integer} files}.", 1));
        
        /* and of course, we can use not internationalized messages. This should 
         * be done, for example, for debug messages.
         */
        System.out.println("Not internationalized");
    }
}
