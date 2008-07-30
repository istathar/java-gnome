/*
 * ExampleTextBuffer.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package textview;

import java.io.FileNotFoundException;

import org.gnome.gdk.Event;
import org.gnome.gdk.Pixbuf;
import org.gnome.gtk.Button;
import org.gnome.gtk.FileChooserAction;
import org.gnome.gtk.FileChooserDialog;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.HButtonBox;
import org.gnome.gtk.ResponseType;
import org.gnome.gtk.TextBuffer;
import org.gnome.gtk.TextIter;
import org.gnome.gtk.TextMark;
import org.gnome.gtk.TextTag;
import org.gnome.gtk.TextTagTable;
import org.gnome.gtk.TextView;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * FIXME
 * 
 * @author Stefan Prelle
 */
public class ExampleTextBuffer
{

    private TextBuffer buffer;

    private TextTagTable tags;

    private TextTag blueback;

    private TextView view;

    private Window win;

    private Button blue, image;

    public final static void main(String[] args) {
        Gtk.init(args);
        new ExampleTextBuffer();
        Gtk.main();
    }

    public ExampleTextBuffer() {
        win = new Window();
        init();
        doLayout();

        fillBuffer();
    }

    private void init() {
        blue = new Button("blue");
        blue.connect(new Button.Clicked() {
            public void onClicked(Button source) {
                TextMark startMark, endMark;
                TextIter start, end;

                startMark = buffer.getSelectionBound();
                endMark = buffer.getInsert();

                start = buffer.getIter(startMark);
                end = buffer.getIter(endMark);

                System.out.println("Selected from: " + start + " to " + end);

                buffer.applyTag(blueback, start, end);
            }
        });

        image = new Button("image");
        image.connect(new Button.Clicked() {
            public void onClicked(Button source) {
                FileChooserDialog chooser = new FileChooserDialog("Select image", win,
                        FileChooserAction.OPEN);
                if (chooser.run() != ResponseType.OK) {
                    chooser.hide();
                    return;
                }

                try {
                    Pixbuf image = new Pixbuf(chooser.getFilename());
                    TextIter pos = buffer.getIter(buffer.getInsert());
                    buffer.insert(pos, image);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                chooser.hide();
            }
        });

        // Define possible tags
        tags = new TextTagTable();

        blueback = new TextTag(tags);
        blueback.setBackground("blue");

        // Create a buffer with the tag collection
        buffer = new TextBuffer(tags);
        // Create a view
        view = new TextView(buffer);
    }

    private void doLayout() {
        VBox layout = new VBox(false, 3);
        HButtonBox buttons = new HButtonBox();
        buttons.add(blue);
        buttons.add(image);
        layout.add(buttons);
        layout.add(view);

        win.setTitle("ExampleTextBuffer");
        win.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return true;
            }
        });
        win.add(layout);
        win.setSizeRequest(300, 200);
        win.showAll();
    }

    private void fillBuffer() {
        buffer.setText("Hello\nWorld!");

        TextIter pos1 = buffer.getIterStart();
        pos1.setLineOffset(6);

        buffer.applyTag(blueback, pos1, buffer.getIterEnd());
    }
}
