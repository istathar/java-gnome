/*
 * FontHeights.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
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
 * behave in this regard, and buggy distros like Ubuntu can fail to get things
 * right.
 * 
 * @author Andrew Cowie
 */
public final class ExampleFontHeights
{
    private final int pt = 12;

    private ExampleFontHeights() {
        final Window w;
        final TextView view;
        final FontDescription desc;
        final TextBuffer buffer;
        final TextTag filename, function, classname;
        TextIter pointer;

        w = new Window();

        view = new TextView();

        desc = new FontDescription("DejaVu Serif, " + pt);
        view.modifyFont(desc);

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
        Gtk.init(args);

        new ExampleFontHeights();

        Gtk.main();
    }
}
