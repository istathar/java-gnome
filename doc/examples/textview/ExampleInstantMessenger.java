/*
 * ExampleInstantMessenger.java
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
import org.gnome.gdk.EventKey;
import org.gnome.gdk.Keyval;
import org.gnome.gdk.Pixbuf;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.IconSize;
import org.gnome.gtk.ScrolledWindow;
import org.gnome.gtk.Stock;
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
import static org.gnome.gtk.PolicyType.ALWAYS;
import static org.gnome.gtk.PolicyType.NEVER;
import static org.gnome.gtk.WrapMode.WORD;

/**
 * An example of rendering multi-line text in an application.
 * 
 * There comes a point when it is difficult to demonstrate complex
 * functionality in a trivial example, so so illustrate usage of the powerful
 * TextView/TextBuffer APIs, we so we have opted to do something a bit more
 * involved.
 * 
 * This program creates the conversation window you might see in a typical
 * graphical instant messenger. It has a TextView displaying the conversation
 * above, and uses various formatting to differentiate between incoming
 * messages and outgoing ones. There is a small Entry at the bottom is where
 * the user can write the messages and "send" them. Finally, a tiny worker
 * thread is kicked off to simulate incoming conversation.
 * 
 * Enjoy!
 * 
 * @author Andrew Cowie
 * @author Stefan Prelle
 */
public class ExampleInstantMessenger
{
    private final TextBuffer buffer;

    private final TextView incoming;

    private final Pixbuf smiley;

    private final TextTag grey, italics, blue;

    private ExampleInstantMessenger() {
        final Window window;
        final VBox top;
        final TextView outgoing;
        final ScrolledWindow scroll;
        final Thread other;
        Pixbuf tmp;

        /*
         * Start with the usual establishment of a Window to contain the
         * example.
         */

        window = new Window();
        window.setTitle("Instant Messaging");
        window.setDefaultSize(300, 200);

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

        /*
         * We want word wrapping, otherwise messages wider that the screen
         * width will be truncated. We also need to set up vertical scrolling
         * so that as the conversation continues it won't be inaccessible off
         * the bottom of the screen.
         */

        incoming.setWrapMode(WORD);

        scroll = new ScrolledWindow();
        scroll.setPolicy(NEVER, ALWAYS);
        scroll.add(incoming);

        top.packStart(scroll, true, true, 0);

        /*
         * Create the place for the user to enter messages they want to send.
         * 
         * The interesting part here is not that there is an Entry (a real
         * Instant Messenger would have a TextView supporting rich content
         * area for the user to write messages to) but that when the user
         * presses Enter in the Entry it "sends" a message and appends it to
         * the log in the incoming TextView.
         */

        outgoing = new TextView();
        outgoing.setAcceptsTab(false);

        top.packStart(outgoing, false, false, 0);

        outgoing.connect(new Widget.KeyPressEvent() {

            public boolean onKeyPressEvent(Widget source, EventKey event) {
                if (event.getKeyval() == Keyval.Return) {
                    final String str;

                    str = outgoing.getBuffer().getText();

                    /*
                     * Append the text in the TextView to the TextBuffer
                     * backing the incoming display.
                     */

                    appendMessage(str, true);

                    /*
                     * And now clear the TextView so that we can enter another
                     * message.
                     */

                    outgoing.getBuffer().setText("");

                    /*
                     * Don't process the return chracter further.
                     */
                    return true;
                }

                /*
                 * Process any other chracters.
                 */
                return false;
            }

        });

        /*
         * Add English spellchecking to input TextView.
         */
        outgoing.attachSpell("en");

        /*
         * TextTags are how you apply formatting to content. We'll create a
         * few for later use in the display.
         */

        grey = new TextTag();
        grey.setForeground("#777777");

        italics = new TextTag();
        italics.setStyle(Style.ITALIC);
        italics.setForeground("darkgreen");

        blue = new TextTag();
        blue.setWeight(Weight.BOLD);
        blue.setForeground("blue");

        /*
         * Almost there. Quickly load an image that we'll use later to replace
         * text smileys with. Since people frequently screw up relative paths
         * when running things, we'll go to some trouble to load the broken
         * image icon instead if we can't find our smiley.
         */

        try {
            tmp = new Pixbuf("doc/examples/textview/face-smile.png");
        } catch (FileNotFoundException fnfe) {
            System.err.println("Warning: smiley image " + fnfe.getMessage());
            tmp = Gtk.renderIcon(window, Stock.MISSING_IMAGE, IconSize.BUTTON);
        }
        smiley = tmp;

        /*
         * Put the Window and all its children on screen.
         */

        window.showAll();

        /*
         * Make sure the user's text Entry has the keyboard focus. For a
         * number of reasons, this won't work until late in the game after
         * everything else is packed. If you try it earlier something else
         * will end up with focus despite this call having been made.
         */

        outgoing.grabFocus();

        /*
         * And start the "conversation" :)
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
        buffer.insert(end, timestamp, grey);

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

        /*
         * We would be done, except that we need to scroll the TextView to
         * show the message just appended. Otherwise the display will stay
         * scrolled to top despite the fact that more message traffic is
         * coming in. An instant messenger shows the recently arrived traffic
         * on screen, letting older messages go off the top.
         */

        incoming.scrollTo(end);
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

    /*
     * We end with the program's main() method where we initialize GTK, call
     * the constructor to build the GUI and then start the main event loop.
     */

    public static void main(String[] args) {
        Gtk.init(args);

        new ExampleInstantMessenger();

        Gtk.main();
    }

    /*
     * And that's it! The remainder of this file is just some contrived
     * infrastructure to simulate a conversation. Run this example and you'll
     * see it all in action!
     */

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
                    "I live in Kenya. " + "It's nice here because it is so warm.",
                    "Someday, though, I want to see snow. " + "Perhaps I will go climb Kilimanjaro.",
                    "Did you see the marathon on the last day of the Olympics? "
                            + "What a run by Samuel Wanjiru! " + "We are all so proud."
            };

            startConversationWith("Catherine Ojiambo");
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
}
