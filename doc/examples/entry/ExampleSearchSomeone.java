/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2011 Operational Dynamics Consulting, Pty Ltd and Others
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
import org.gnome.gtk.CellRendererText;
import org.gnome.gtk.DataColumn;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.Entry;
import org.gnome.gtk.EntryIconPosition;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.SelectionMode;
import org.gnome.gtk.Stock;
import org.gnome.gtk.TreeIter;
import org.gnome.gtk.TreeModel;
import org.gnome.gtk.TreeModelFilter;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.TreeViewColumn;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * A simple program that shows the interest of using icon in Entry.
 * 
 * @author Guillaume Mazoyer
 * @since 4.0.13
 */
public class ExampleSearchSomeone
{
    public static void main(String[] args) {
        final Window window;
        final VBox vbox;
        final Entry entry;
        final DataColumnString textColumn;
        final ListStore model;
        final TreeView view;
        final TreeModelFilter filter;
        TreeViewColumn vertical;
        CellRendererText renderer;
        TreeIter row;

        final String[] contacts = {
            "Andrew Cowie\nandrew@operationaldynamics.com",
            "Vreixo Formoso Lopes\nmetalpain2002@yahoo.es",
            "Serkan Kaba\nserkan@gentoo.org",
            "Stefan Schweizer\nsteve.schweizer@gmail.com",
            "Guillaume Mazoyer\nrespawneral@gmail.com"
        };

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
         * Create a VBox which will contains everything.
         */

        vbox = new VBox(false, 3);

        /*
         * Create the Entry which will display the text.
         */

        entry = new Entry();
        vbox.add(entry);

        /*
         * Add a clear icon to the right and a find icon to the left The find
         * icon will be just decorative, no action will be done with it.
         */

        entry.setIconFromStock(EntryIconPosition.PRIMARY, Stock.FIND);
        entry.setIconActivatable(EntryIconPosition.PRIMARY, false);
        entry.setIconFromStock(EntryIconPosition.SECONDARY, Stock.CLEAR);

        /*
         * Build the TreeView model as a ListStore.
         */

        model = new ListStore(new DataColumn[] {
            textColumn = new DataColumnString()
        });

        /*
         * Add each "contact" to the underlying ListStore
         */

        for (String contact : contacts) {
            row = model.appendRow();
            model.setValue(row, textColumn, contact);
        }

        /*
         * Use a filter to display only the good results
         */

        filter = new TreeModelFilter(model, null);
        filter.setVisibleCallback(new TreeModelFilter.Visible() {
            public boolean onVisible(TreeModelFilter source, TreeModel base, TreeIter row) {
                final String contact;
                final String search;

                contact = base.getValue(row, textColumn);
                search = entry.getText();

                if (contact.contains(search)) {
                    return true;
                } else {
                    return false;
                }
            }
        });

        /*
         * Then, we build the TreeView.
         */

        view = new TreeView(filter);
        view.setHeadersVisible(false);
        view.setEnableSearch(false);
        view.getSelection().setMode(SelectionMode.NONE);
        view.setCanFocus(false);
        view.setSizeRequest(300, 200);
        vbox.add(view);

        /*
         * Setup visual display of the contact data
         */

        vertical = view.appendColumn();
        renderer = new CellRendererText(vertical);
        renderer.setText(textColumn);

        /*
         * Connect the signal to enable search when we type.
         */

        entry.connect(new Entry.Changed() {
            public void onChanged(Entry source) {
                /*
                 * Refilter the view
                 */

                filter.refilter();
            }
        });

        /*
         * Then connect the signal to the icon to clear the entry.
         */

        entry.connect(new Entry.IconPress() {
            public void onIconPress(Entry source, EntryIconPosition position, Event event) {
                source.setText("");
            }
        });

        /*
         * Finally pack the VBox into our Window and set a title.
         */

        window.add(vbox);
        window.setTitle("Search for a contact");
        window.showAll();

        /*
         * Run the main loop.
         */

        Gtk.main();
    }
}
