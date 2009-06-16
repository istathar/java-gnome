/*
 * ExampleEntryCompletion.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package completion;

import org.gnome.gdk.Event;
import org.gnome.gtk.DataColumn;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.Entry;
import org.gnome.gtk.EntryCompletion;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Label;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.TreeIter;
import org.gnome.gtk.TreeModel;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * A simple program that shows the interest of using EntryCompletion.
 * 
 * @author Guillaume Mazoyer
 * @since 4.0.12
 */
public class ExampleEntryCompletion
{
    public static void main(String[] args) {
        final Window window;
        final VBox vbox;
        final Label label;
        final Entry entry;
        final EntryCompletion completion;

        final ListStore model;
        final DataColumnString column;

        /*
         * Initialize GTK.
         */

        Gtk.init(args);

        /*
         * Create a top level Window.
         */

        window = new Window();

        /*
         * Connect the signal to close the window
         */
        window.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });

        /*
         * Create a VBox which will contains a label and an entry completion.
         */

        vbox = new VBox(false, 3);

        /*
         * Create a Label with some text describing the Button that will
         * follow, then add it to the VBox.
         */

        label = new Label("Go ahead: try me!");
        vbox.add(label);

        /*
         * Create the Entry which will display the text.
         */

        entry = new Entry();
        vbox.add(entry);

        /*
         * Create the EntryCompletion.
         */

        completion = new EntryCompletion();

        /*
         * The completion will use a ListStore as model.
         */

        model = new ListStore(new DataColumn[] {
            column = new DataColumnString()
        });
        completion.setModel(model);

        /*
         * Fill the model with words.
         */

        String[] words = {
                "abc", "def", "ghi", "jkl", "mno", "pqr", "stu", "vwx", "yz"
        };
        TreeIter row;
        for (String word : words) {
            /*
             * Append a new row for a new word.
             */

            row = model.appendRow();

            /*
             * Add the word to the model.
             */

            model.setValue(row, column, word);
        }

        /*
         * Indicate the column which contains the text in the model. 0 is for
         * the first.
         */

        completion.setTextColumn(column);

        /*
         * Finally, indicate to the entry which completion it has to use.
         */

        entry.setCompletion(completion);

        /*
         * When the MatchSelected signal is emitted, it means the user has
         * selected a text in the list, so we display it in the Entry.
         */

        completion.connect(new EntryCompletion.MatchSelected() {
            public boolean onMatchSelected(EntryCompletion source, TreeModel model, TreeIter iter) {
                final String text;
                final Entry entry;

                /*
                 * Get the Entry attached to the EntryCompletion.
                 */

                entry = source.getEntry();

                /*
                 * Get the text the user has selected.
                 */

                text = model.getValue(iter, column);

                /*
                 * Display the text in the Entry.
                 */

                entry.setText(text);

                /*
                 * Let the cursor move to the beginning.
                 */

                entry.setPosition(-1);

                return true;
            }
        });

        /*
         * Now we pack the VBox into our Window and set the Window's title.
         */

        window.add(vbox);
        window.setTitle("ExampleEntryCompletion");
        window.showAll();

        /*
         * Run the main loop.
         */

        Gtk.main();
    }
}
