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
import org.gnome.pango.Weight;

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
public class ExampleInstantMessenger
{
    private final TextView incoming;

    private final TextBuffer buffer;

    private final Pixbuf smiley;

    private final TextTag gray, italics, blue;

    private ExampleInstantMessenger() throws FileNotFoundException {
        final Window window;
        final VBox top;
        final Thread other;
        final Entry outgoing;

        /*
         * Start with the usual establishment of a Window to contain the
         * example.
         */

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

        /*
         * Create a TextView which will display incoming text messages (and
         * also echo messages as they are sent). It is set up to be read only
         * and to not have a cursor, thereby conveying the impression that it
         * is just a display (a cursor especially would suggest that the text
         * there can be changed).
         */

        buffer = new TextBuffer();

        incoming = new TextView(buffer);
        incoming.setEditable(false);
        incoming.setCursorVisible(false);
        incoming.setPaddingBelowParagraph(10);

        top.packStart(incoming, true, true, 0);

        /*
         * Create the place for the user to enter messages they want to send.
         * 
         * The interesting part here is not that there is an Entry (a real
         * Instant Messager would have a TextView supporting rich content area
         * for the user to write messages too) but that when the user presses
         * Enter in the Entry it "sends" a message and appends it to the log
         * in the incoming TextView.
         */

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

        /*
         * TextTags are how you apply formatting to content. We'll create a
         * few for later use in the display.
         */

        gray = new TextTag();
        gray.setForeground("#777777");

        italics = new TextTag();
        italics.setStyle(Style.ITALIC);
        italics.setForeground("darkgreen");

        blue = new TextTag();
        blue.setWeight(Weight.BOLD);
        blue.setForeground("blue");

        /*
         * And finally load an image that we'll use later to replace text
         * smilies with.
         */

        smiley = new Pixbuf("doc/examples/textview/face-smile.png");

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

        other = new IncomingConversation();
        other.start();
    }

    /**
     * Append a received (or sent) message to the incoming display.
     */
    /*
     * For fun, we translate the smile emoticon into an image, giving us an
     * opportunity to demonstrate adding non-text elements to a TextBuffer.
     */
    private void appendMessage(String msg, boolean outgoing) {
        final TextIter end;
        final long now;
        final String timestamp;
        final TextTag colour;
        int i;
        int prev;

        /*
         * Get a TextIter pointing at the end of the existing text.
         */

        end = buffer.getIterEnd();

        /*
         * Start with a paragraph separator and a timestamp. We colour the
         * timestamp a lighter colour so as to not distract from the text.
         */

        buffer.insert(end, "\n");

        now = System.currentTimeMillis() / 1000;
        timestamp = formatTime("%H:%M:%S\t", now);
        buffer.insert(end, timestamp, gray);

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
            buffer.insert(end, msg.substring(prev, i), colour);
            buffer.insert(end, smiley);

            i += 2;
            prev = i;
        }
        buffer.insert(end, msg.substring(prev), colour);
    }

    /**
     * When a conversation starts we want to indicate who it is with.
     */
    private void startConversationWith(String who) {
        final TextIter pointer;

        /*
         * Place a message at the top of the display indicating who is writing
         * in.
         */

        pointer = buffer.getIterStart();
        buffer.insert(pointer, "Starting conversation with " + who, italics);
    }

    class IncomingConversation extends Thread
    {
        private final String[] messages;

        private IncomingConversation() {

            /*
             * Mark this thread as a daemon thread, else the main thread
             * terminating after Gtk.main() returns will not end the program.
             */

            this.setDaemon(true);

            /*
             * Some silly messages to use to fake incoming conversation.
             */

            messages = new String[] {
                    "Hello there!",
                    "Will you be my friend? :)",
                    "What's wrong?",
                    "Why won't you talk to me?"
            };

            startConversationWith("Jane Doe");
        }

        public void run() {
            for (int i = 0; i < messages.length; i++) {
                try {
                    sleep((int) (1000 + i * 2000 * Math.random()));

                } catch (InterruptedException ie) {
                }

                appendMessage(messages[i], false);
            }
        }
    }

    public static void main(String[] args) {
        Gtk.init(args);

        try {
            new ExampleInstantMessenger();
        } catch (FileNotFoundException fnfe) {
            System.err.println("Sorry " + fnfe.getMessage());
            System.exit(1);
        }

        Gtk.main();
    }
}
