/*
 * ExampleEditor.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package sourceview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.gnome.gdk.Event;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.PolicyType;
import org.gnome.gtk.ScrolledWindow;
import org.gnome.gtk.TextTagTable;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.sourceview.LanguageManager;
import org.gnome.sourceview.SourceBuffer;
import org.gnome.sourceview.SourceView;

/**
 * @author Stefan Schweizer
 */
// TODO Showcase more GtkSourceView features, documentation
public class ExampleEditor
{
    private ExampleEditor() {
        final Window w;
        final ScrolledWindow scroll;
        final SourceView view;
        final SourceBuffer buffer;
        final TextTagTable tagTable;

        w = new Window();
        w.setTitle("java-gnome editor");
        w.setDefaultSize(550, 600);

        w.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });

        tagTable = new TextTagTable();
        buffer = new SourceBuffer(tagTable);
        buffer.setLanguage(LanguageManager.getDefault().getLanguage("java"));
        view = new SourceView(buffer);

        buffer.setText(readFile("ExampleEditor.java"));

        scroll = new ScrolledWindow();
        scroll.setPolicy(PolicyType.AUTOMATIC, PolicyType.ALWAYS);
        scroll.add(view);

        w.add(scroll);
        w.showAll();

    }

    private String readFile(String filename) {
        final StringBuilder content;
        final InputStream in;
        final BufferedReader reader;

        content = new StringBuilder();
        in = ExampleEditor.class.getResourceAsStream(filename);
        reader = new BufferedReader(new InputStreamReader(in));

        try {
            for (int c = reader.read(); c != -1; c = reader.read()) {
                content.append((char) c);
            }
        } catch (IOException ioe) {
            content.append(ioe.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
            }
        }

        return content.toString();
    }

    public static void main(String[] args) {
        Gtk.init(args);
        new ExampleEditor();
        Gtk.main();
    }
}
