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

import org.freedesktop.cairo.Context;
import org.gnome.gdk.Event;
import org.gnome.gdk.EventKey;
import org.gnome.gtk.DrawingArea;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.InputMethod;
import org.gnome.gtk.SimpleInputMethod;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.pango.FontDescription;
import org.gnome.pango.Layout;

/**
 * Experiment with GTK's input method system.
 * 
 * @author AndrewCowie
 */
public class ManualInput
{
    public static void main(String[] args) {
        final Window w;
        final DrawingArea d;
        final InputMethod im;
        final StringBuilder buf;

        Gtk.init(args);

        w = new Window();
        w.setTitle("Text editor");
        w.setDefaultSize(200, 100);

        d = new DrawingArea();
        d.setCanFocus(true);

        w.add(d);

        im = new SimpleInputMethod();
        im.setUsePreedit(true);

        buf = new StringBuilder("Hello");

        d.connect(new Widget.Draw() {
            public boolean onDraw(Widget source, Context cr) {
                final Layout layout;
                final FontDescription desc;

                layout = new Layout(cr);

                desc = new FontDescription("DejaVu Serif, Book 12");
                layout.setFontDescription(desc);

                layout.setText(buf.toString());

                source.setSizeRequest(layout.getPixelWidth(), layout.getPixelHeight());

                cr.showLayout(layout);
                return false;
            }
        });

        d.connect(new Widget.KeyPressEvent() {
            public boolean onKeyPressEvent(Widget source, EventKey event) {
                if (im.filterKeypress(event)) {
                    return true;
                }
                return false;
            }
        });

        d.connect(new Widget.KeyReleaseEvent() {
            public boolean onKeyReleaseEvent(Widget source, EventKey event) {
                if (im.filterKeypress(event)) {
                    return true;
                }
                return false;
            }
        });

        im.connect(new InputMethod.Commit() {
            public void onCommit(InputMethod source, String str) {
                buf.append(str);
                d.queueDraw();
            }
        });

        w.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });

        w.showAll();

        Gtk.main();
    }
}
