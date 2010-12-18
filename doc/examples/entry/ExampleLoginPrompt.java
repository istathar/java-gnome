/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
package entry;

import org.gnome.gdk.Event;
import org.gnome.gtk.Button;
import org.gnome.gtk.DataColumn;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.Dialog;
import org.gnome.gtk.Entry;
import org.gnome.gtk.EntryCompletion;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.IconSize;
import org.gnome.gtk.Image;
import org.gnome.gtk.InfoMessageDialog;
import org.gnome.gtk.Label;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.ResponseType;
import org.gnome.gtk.Stock;
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
public class ExampleLoginPrompt
{
    public static void main(String[] args) {
        final Window window;
        final VBox vbox;
        final Button button;
        final Image image;
        final Label loginLabel;
        final Label passwordLabel;
        final Entry loginEntry;
        final Entry passwordEntry;
        final EntryCompletion completion;
        final ListStore model;
        final DataColumnString column;
        final String[] words;
        TreeIter row;

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
         * Create Labels with some text describing what the entries are here.
         */

        image = new Image(Stock.NETWORK, IconSize.DIALOG);
        loginLabel = new Label("Login");
        passwordLabel = new Label("Password");

        /*
         * Create the Entries which will display the text.
         */

        loginEntry = new Entry();
        passwordEntry = new Entry();

        /*
         * The password Entry should hide its text.
         */

        passwordEntry.setVisibility(false);

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

        words = new String[] {
            "respawneral@gmail.com",
            "joe@example.org"
        };

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

        loginEntry.setCompletion(completion);

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

                /*
                 * Use a fake password
                 */

                passwordEntry.setText("abcdefgh");

                return true;
            }
        });

        /*
         * Pack everything in the box.
         */

        vbox.add(image);
        vbox.add(loginLabel);
        vbox.add(loginEntry);
        vbox.add(passwordLabel);
        vbox.add(passwordEntry);

        /*
         * Now we create the "connection" button.
         */

        button = new Button(Stock.CONNECT);
        vbox.add(button);

        /*
         * Connect the button to a signal.
         */

        button.connect(new Button.Clicked() {
            public void onClicked(Button source) {
                /*
                 * Get the address which was entered in the login Entry.
                 */

                final String address = loginEntry.getText();

                /*
                 * Don't do anything if there's no address.
                 */

                if (address.isEmpty()) {
                    return;
                }

                /*
                 * Display a little message in the dialog.
                 */

                InfoMessageDialog dialog = new InfoMessageDialog(window, "Login successful",
                        "You tryed to get login with '" + address
                                + "' address. Try it again with the same address.");

                /*
                 * Just close the dialog if we click on its button.
                 */

                dialog.connect(new Dialog.Response() {
                    public void onResponse(Dialog source, ResponseType response) {
                        source.hide();
                    }
                });

                dialog.run();

                /*
                 * Search if the address has already been use.
                 */

                boolean add = true;
                TreeIter row = model.getIterFirst();
                do {
                    final String text = model.getValue(row, column);

                    if (text.equals(address)) {
                        add = false;
                    }
                } while (row.iterNext() && add);

                /*
                 * If not, we add it to the completion list.
                 */

                if (add) {
                    row = model.appendRow();
                    model.setValue(row, column, address);
                }

                /*
                 * And we reset Entries.
                 */

                loginEntry.setText("");
                passwordEntry.setText("");
            }
        });

        /*
         * Now we pack the VBox into our Window and set the Window's title.
         */

        window.add(vbox);
        window.setTitle("Login");
        window.showAll();

        /*
         * Run the main loop.
         */

        Gtk.main();
    }
}
