/*
 * ExampleInstantMessager.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 * 
 * The smiley image used in this program is from the Tango Icon Theme, whose
 * authors make available under the Creative Commons Attribution Share-Alike
 * licence version 2.5. See http://tango-project.org/ for more details.  
 */
package textview;

import java.io.FileNotFoundException;

import org.gnome.gdk.Event;
import org.gnome.gdk.Pixbuf;
import org.gnome.gtk.Entry;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.TextBuffer;
import org.gnome.gtk.TextIter;
import org.gnome.gtk.TextTag;
import org.gnome.gtk.TextView;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

import static org.freedesktop.bindings.Time.formatTime;

/**
 * TODO describe
 * 
 * There comes a point when it is difficult to demostrate complex
 * functionality in a trivial example, so we have opted to show... FIXME
 * 
 * @author Andrew Cowie
 * @author Stefan Prelle
 */
public class ExampleInstantMessager
{
    private TextView incoming;

    private Entry outgoing;

    private Window window;

    private VBox top;

    private Pixbuf smiley;

    private TextTag gray;

    public static void main(String[] args) {
        Gtk.init(args);

        new ExampleInstantMessager();

        Gtk.main();
    }

    /*
     * This class also demonstrates an alternative way to manage building the
     * user interface: a series of sequential methods. This is mostly
     * cosmetic, but as you can see it does allow you to follow the logic of
     * what is going on rather than the constructor being hundreds of lines
     * long. More to the point, it makes navigation in an IDE fare more
     * effective.
     */
    private ExampleInstantMessager() {
        loadImages();
        setupInitial();
        createTextTags();
        setupIncomingDisplay();
        setupOutgoingEntry();

        hookupWindowManagement();

        initialPresentation();
    }

    private void setupInitial() {
        window = new Window();
        window.setTitle("Instant Messaging");
        window.setSizeRequest(300, 200);

        top = new VBox(false, 3);
        window.add(top);
    }

    private void setupIncomingDisplay() {
        TextBuffer buffer;

        buffer = new TextBuffer();

        incoming = new TextView(buffer);
        incoming.setEditable(false);
        incoming.setCursorVisible(false);
        incoming.setPaddingBelowParagraph(10);

        top.packStart(incoming, true, true, 0);
    }

    private void createTextTags() {
        gray = new TextTag();
        gray.setForeground("gray");
    }

    private void setupOutgoingEntry() {
        outgoing = new Entry();

        outgoing.connect(new Entry.Activate() {
            public void onActivate(Entry source) {
                final String str;

                str = outgoing.getText();

                /*
                 * Append the text in the Entry to the TextBuffer backing the
                 * incoming display.
                 */

                appendMessage(str);

                /*
                 * And now clear the Entry so that we can enter another
                 * message.
                 */

                outgoing.setText("");
            }
        });

        top.packStart(outgoing, false, false, 0);
    }

    private void loadImages() {
        try {
            smiley = new Pixbuf("doc/examples/textview/face-smile.png");
        } catch (FileNotFoundException fnfe) {
            System.err.println("Can't find the smiley " + fnfe.getMessage());
        }
    }

    private void hookupWindowManagement() {
        window.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });
    }

    /**
     * Append a received (or sent) message to the incoming display.
     */
    /*
     * For fun, we translate the smile emoticon into an image, giving us an
     * opportunity to demonstrate adding non-text elements to a TextBuffer.
     */
    private void appendMessage(String msg) {
        final TextBuffer model;
        final TextIter end;
        final long now;
        final String timestamp;
        int i;
        int prev;

        /*
         * Get a TextIter pointing at the end of the existing text.
         */

        model = incoming.getBuffer();
        end = model.getIterEnd();

        /*
         * Start with a paragraph separator and a timestamp. We colour the
         * timestamp a lighter colour so as to not distract from the text.
         */

        model.insert(end, "\n");

        now = System.currentTimeMillis() / 1000;
        timestamp = formatTime("%H:%M:%S\t", now);
        model.insert(end, timestamp, gray);

        /*
         * Loop over what we're going to add, replacing text smileys with
         * graphical ones.
         */

        prev = 0;

        while ((i = msg.indexOf(":)", prev)) != -1) {
            model.insert(end, msg.substring(prev, i));
            model.insert(end, smiley);

            i += 2;
            prev = i;
        }
        model.insert(end, msg.substring(prev));
    }

    private void initialPresentation() {
        outgoing.grabFocus();
        window.showAll();
    }
}
