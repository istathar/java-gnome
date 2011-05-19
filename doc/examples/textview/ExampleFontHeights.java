/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2011 Operational Dynamics Consulting, Pty Ltd
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
package textview;

import org.gnome.gdk.Event;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.TextBuffer;
import org.gnome.gtk.TextIter;
import org.gnome.gtk.TextTag;
import org.gnome.gtk.TextView;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.gtk.WrapMode;
import org.gnome.pango.FontDescription;
import org.gnome.pango.Style;

/**
 * Are all the characters in the rendered text the same height? Not all fonts
 * behave in this regard.
 * 
 * <p>
 * Run this as:
 * 
 * <pre>
 * $ java -client -ea -classpath tmp/gtk-4.0.jar:tmp/tests textview.ExampleFontHeight 10
 * </pre>
 * 
 * to see the 10pt problem on buggy distros like Ubuntu.
 * 
 * @author Andrew Cowie
 */
public final class ExampleFontHeights
{
    private ExampleFontHeights(int pt) {
        final Window w;
        final TextView view;
        final FontDescription desc;
        final TextBuffer buffer;
        final TextTag filename, function, classname;
        TextIter pointer;

        w = new Window();

        view = new TextView();

        desc = new FontDescription("DejaVu Serif, " + pt);
        view.overrideFont(desc);

        buffer = new TextBuffer();

        filename = new TextTag();
        filename.setFamily("DejaVu Sans Mono, " + pt);
        filename.setStyle(Style.ITALIC);
        filename.setForeground("darkgreen");

        function = new TextTag();
        function.setFamily("DejaVu Sans Mono, " + pt);

        classname = new TextTag();
        classname.setFamily("DejaVu Sans, " + pt);
        classname.setForeground("darkblue");

        pointer = buffer.getIterStart();
        buffer.insert(pointer, "Accessing the ");
        buffer.insert(pointer, "/tmp", filename);
        buffer.insert(pointer, " directory directly is fine, but the ");
        buffer.insert(pointer, "File", classname);
        buffer.insert(pointer, " class has a ");
        buffer.insert(pointer, "createTempFile()", function);
        buffer.insert(pointer, " function that you are often better off using.");

        view.setBuffer(buffer);
        view.setWrapMode(WrapMode.WORD);
        view.setSizeRequest(250, -1);
        w.add(view);

        w.setTitle("DejaVu " + pt + "pt");
        w.showAll();

        w.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });
    }

    public static void main(String[] args) {
        final int pt;

        Gtk.init(args);

        if (args.length == 1) {
            pt = Integer.parseInt(args[0]);
        } else {
            pt = 11;
        }

        new ExampleFontHeights(pt);

        Gtk.main();
    }
}
