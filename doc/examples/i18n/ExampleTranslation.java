/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008      Vreixo Formoso
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
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
 */
package i18n;

import java.util.Calendar;

import org.freedesktop.bindings.Internationalization;
import org.gnome.gtk.Gtk;

import static org.freedesktop.bindings.Internationalization.N_;
import static org.freedesktop.bindings.Internationalization._;

/**
 * Little example of i18n features, still to be improved.
 * 
 * @author Vreixo Formoso
 */
public class ExampleTranslation
{
    /*
     * With code that will be executed before calling I18n.init(), you need to
     * use N_() function instead of _(). This just marks the string as
     * translatable, it does not really translate (it can't!), so when
     * actually print, you will need to call _()! See below.
     */
    private static final String GOODBYE = N_("Goodbye");

    public static void main(String[] args) {

        /* this is called just to link against JNI library */
        Gtk.init(args);

        Internationalization.init("example", "tmp/locale/");

        /* we can translate simple messages */
        System.out.println(_("Hello"));

        /* as GOODBYE is marked with N_(), we need to call _() */
        System.out.println(_(GOODBYE));

        /* or also use messages with parameters */
        System.out.println(_("User name is {0} and home dir is {1}", System.getProperty("user.name"),
                System.getProperty("user.home")));

        /*
         * but also parameters that should be formatted in a locale-dependent
         * way
         */
        System.out.println(_("This costs {0,number,currency}", 1555.45));
        System.out.println(_("Today is {0,date,long}", Calendar.getInstance().getTime()));

        /* we even have support for advanced usage */
        System.out.println(_(
                "There {0,choice,0#are no files|1#is one file|1<are {0,number,integer} files}.", 1));

        /*
         * and of course, we can use not internationalized messages. This
         * should be done, for example, for debug messages.
         */
        System.out.println("Not internationalized");
    }
}
