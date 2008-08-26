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
import org.gnome.pango.Style;

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

    private TextTag gray, italics, blue;

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
        createFormatTags();
        setupIncomingDisplay();
        setupOutgoingEntry();
        initialPresentation();
    }

    /**
     * The usual establishment of a Window to contain the example.
     */
    private void setupInitial() {
        window = new Window();
        window.setTitle("Instant Messaging");
        window.setSizeRequest(300, 200);

        top = new VBox(false, 3);
        window.add(top);

        window.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });
    }

    /**
     * Create a TextView which will display incoming text messages (and also
     * echo messages as they are sent). It is set up to be read only and to
     * not have a cursor, thereby conveying the impression that it is just a
     * display (a cursor especially would suggest that the text there can be
     * changed).
     */
    private void setupIncomingDisplay() {
        TextBuffer buffer;

        buffer = new TextBuffer();

        incoming = new TextView(buffer);
        incoming.setEditable(false);
        incoming.setCursorVisible(false);
        incoming.setPaddingBelowParagraph(10);

        top.packStart(incoming, true, true, 0);
    }

    /**
     * TextTags are how you apply formatting to content. We'll create a few
     * for later use in the display.
     */
    private void createFormatTags() {
        gray = new TextTag();
        gray.setForeground("gray");

        italics = new TextTag();
        italics.setStyle(Style.ITALIC);
        italics.setForeground("green");

        blue = new TextTag();
        blue.setForeground("blue");
    }

    /**
     * Create the place for the user to enter messages they want to send.
     * 
     * The interesting part here is not that there is an Entry (a real Instant
     * Messager would have a TextView supporting rich content area for the
     * user to write messages too) but that when the user presses Enter in the
     * Entry it "sends" a message and appends it to the log in the incoming
     * TextView.
     */
    private void setupOutgoingEntry() {
        outgoing = new Entry();
        top.packStart(outgoing, false, false, 0);

        outgoing.connect(new Entry.Activate() {
            public void onActivate(Entry source) {
                final String str;

                str = outgoing.getText();

                /*
                 * Append the text in the Entry to the TextBuffer backing the
                 * incoming display.
                 */

                appendMessage(str, true);

                /*
                 * And now clear the Entry so that we can enter another
                 * message.
                 */

                outgoing.setText("");
            }
        });
    }

    private void loadImages() {
        try {
            smiley = new Pixbuf("doc/examples/textview/face-smile.png");
        } catch (FileNotFoundException fnfe) {
            System.err.println("Sorry " + fnfe.getMessage());
            System.exit(1);
        }
    }

    /**
     * Append a received (or sent) message to the incoming display.
     */
    /*
     * For fun, we translate the smile emoticon into an image, giving us an
     * opportunity to demonstrate adding non-text elements to a TextBuffer.
     */
    private void appendMessage(String msg, boolean outgoing) {
        final TextBuffer text;
        final TextIter end;
        final long now;
        final String timestamp;
        final TextTag colour;
        int i;
        int prev;

        /*
         * Get a TextIter pointing at the end of the existing text.
         */

        text = incoming.getBuffer();
        end = text.getIterEnd();

        /*
         * Start with a paragraph separator and a timestamp. We colour the
         * timestamp a lighter colour so as to not distract from the text.
         */

        text.insert(end, "\n");

        now = System.currentTimeMillis() / 1000;
        timestamp = formatTime("%H:%M:%S\t", now);
        text.insert(end, timestamp, gray);

        /*
         * Loop over what we're going to add, replacing text smileys with
         * graphical ones. As for the text we write, if the user sent it we'll
         * make it blue but if incoming we'll leave it black.
         */

        if (outgoing) {
            colour = blue;
        } else {
            colour = null;
        }

        prev = 0;

        while ((i = msg.indexOf(":)", prev)) != -1) {
            text.insert(end, msg.substring(prev, i), colour);
            text.insert(end, smiley);

            i += 2;
            prev = i;
        }
        text.insert(end, msg.substring(prev), colour);
    }

    /**
     * When a conversation starts we want to indicate who it is with.
     */
    private void startConversationWith(String who) {
        final TextBuffer text;
        final TextIter pointer;

        /*
         * You've probably realized by now that it would be a lot easier to
         * just keep a field with a reference to the TextBuffer model.
         */
        text = incoming.getBuffer();

        /*
         * This hard codes the assumption that this is only being called once,
         * before any calls to appendMessage().
         */

        pointer = text.getIterStart();
        text.insert(pointer, "Starting conversation with " + who, italics);
    }

    private void initialPresentation() {
        final Thread other;

        /*
         * Put the Window and all its children on screen.
         */

        window.showAll();

        /*
         * Make sure the user's text Entry has the keyboard focus. For a
         * number of reasons, this won't work until late in the game, else
         * something else will end up with focus despite this call.
         */

        outgoing.grabFocus();

        /*
         * And start the "converstaion" :)
         */

        other = new Conversation();
        other.start();
    }

    class Conversation extends Thread
    {
        private final String[] messages;

        Conversation() {
            /*
             * Mark this thread as a daemon thread, else the main thread
             * terminating after Gtk.main() returns will not end the program.
             */

            this.setDaemon(true);

            messages = new String[] {
                    "Hello there!",
                    "Will you be my friend? :)",
                    "What's wrong?",
                    "Why won't you talk to me?"
            };

            startConversationWith("Jane");
        }

        public void run() {
            for (int i = 0; i < messages.length; i++) {
                try {
                    sleep((int) (1000 + i * 1000 * Math.random()));

                } catch (InterruptedException ie) {
                }

                appendMessage(messages[i], false);
            }
        }
    }
}
