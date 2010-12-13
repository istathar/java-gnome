/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd
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
 * "Claspath Exception"), the copyright holders of this library give you
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
package org.gnome.glade;

import java.io.File;
import java.io.FileNotFoundException;

import org.gnome.glib.Glib;

/**
 * This class is a hyper-thin wrapping of <code>libglade</code>, a library
 * that allows you to transform the XML interface definition files generated
 * by the GNOME User Interface Designers (of which <code>Glade</code> is the
 * original and best known) into live Widgets in your application.
 * 
 * <p>
 * Using <code>libglade</code> is pretty easy. You prepare your user interface
 * in <code>Glade</code>, write some code to load it in, and then extract the
 * particular Widgets that you care about further manipulating.
 * 
 * <p>
 * Since we're stuck with the somewhat stupid name that the underlying library
 * used for the tree of instantiated widgets, we recommend you use a variable
 * name like <code>glade</code> or <code>processedTree</code> for the
 * {@link XML XML} object you get back, perhaps as follows:
 * 
 * <pre>
 * final XML glade;
 * final Window top;
 * final Button confirm;
 * 
 * glade = Glade.parse(&quot;HelpWindow.glade&quot;, &quot;window1&quot;);
 * 
 * top = (Window) glade.getWidget(&quot;window1&quot;);
 * confirm = (Button) glade.getWidget(&quot;button4&quot;);
 * </pre>
 * 
 * If you're wonder where <code>"window1"</code> and <code>"button4"</code>
 * came from, they are the style of names that the <code>Glade</code> designer
 * generates for its Widgets by default. <b>To improve maintainability, you
 * are highly encouraged to change these automatically generated names to ones
 * which corresponds to the variable names being used in your Java code!</b>
 * In other words, something like:
 * 
 * <pre>
 * confirm = (Button) glade.getWidget(&quot;confirmButton&quot;);
 * </pre>
 * 
 * These examples do, however, expose the two major structural weaknesses of
 * using Glade:
 * <ul>
 * <li>it is not type safe. We do our best to check you aren't asking for
 * something that you shouldn't, but you are still at the mercy of
 * <code>ClassCastException</code> (or worse a segmentation fault) if you get
 * it wrong.
 * <li>the names embedded in your <code>parse()</code> and
 * <code>getWidget()</code> calls are tightly coupled to the textual names
 * created in the <code>Glade</code> program. If you refactor your Java code,
 * sooner or later you will forget to change the corresponding names in the
 * <code>.glade</code> files, which will inevitably lead to confusion down the
 * road.
 * </ul>
 * All that said, <code>libglade</code> provides a rapid application
 * development capability par excellence, and is a significant part of almost
 * every GNOME application.
 * 
 * <p>
 * <b>Warning:</b> <i>There has been considerable discussion within GNOME
 * about the state of <code>libglade</code> and it is widely expected that it
 * will be replaced by <code>GtkBuilder</code> within GTK in the very near
 * future. If that occurs before java-gnome 4.2 we will not release Glade to
 * stable. Indeed, that event may well be the trigger to bump to 4.1</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.2
 */
/*
 * By extending Glib we force library initialization, though really Gtk.init()
 * should have been called already.
 */
public final class Glade extends Glib
{
    /**
     * No instantiation. Static methods only!
     */
    private Glade() {}

    /**
     * Parse a Glade interface definition file and create a tree of live
     * instantiated Widgets ready for use.
     * 
     * @param filename
     *            the path to the file containing the XML data generated by a
     *            <code>Glade</code> user interface designer. The path is
     *            relative to FIXME. A particularly effective Java solution is
     *            to put the <code>.glade</code> file on your
     *            <code>CLASSPATH</code> and then load the data as an
     *            InputStream with ClassLoader's
     *            {@link java.lang.ClassLoader#getResourceAsStream(String)
     *            getResourceAsStream()}, passing the InputStream to the other
     *            form of parse().
     * @param root
     *            the name of the Widget whose hierarchy you wish to load. It
     *            turns out you can have several Windows and Dialogs in a
     *            single <code>.glade</code> file; because of this you
     *            normally specify which Window you want to load. Hopefully
     *            you renamed it from <code>window1</code> to something more
     *            sensible. You can also specify something lower down such as
     *            a ToolBar or VBox if that is all you need to instantiate. If
     *            you specify <code>null</code> all Widget hierarchies within
     *            the file will be loaded.
     * @throws FileNotFoundException
     *             if the specified <code>.glade</code> file cannot be found
     *             or read. Unfortunately, this actually happens quite a lot
     *             due to people assuming that the XML data will be loaded
     *             from the location of the the source file which calls this
     *             method instead of relative to the FIXME place the program
     *             is executed from. The problem can certainly be solved using
     *             an absolute path for filename, but that isn't going to be
     *             portable at all.
     * @since 4.0.2
     */
    public static XML parse(String filename, String root) throws FileNotFoundException {
        final File target;
        final XML glade;

        if (filename == null) {
            throw new IllegalArgumentException();
        }

        target = new File(filename);

        if (!target.exists()) {
            throw new FileNotFoundException("\nCan't find the specified Glade XML file:\n"
                    + target.getAbsolutePath());
        }
        if (!target.canRead()) {
            throw new FileNotFoundException("\nThe specified Glade XML file,\n"
                    + target.getAbsolutePath() + "\nis not readable");
        }

        glade = new XML(filename, root);

        return glade;
    }
}
