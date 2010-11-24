/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
package treeview;

import org.gnome.gdk.Event;
import org.gnome.gtk.CellRendererText;
import org.gnome.gtk.DataColumn;
import org.gnome.gtk.DataColumnBoolean;
import org.gnome.gtk.DataColumnInteger;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.TreeIter;
import org.gnome.gtk.TreePath;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.TreeViewColumn;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * A tutorial example of using a TreeView backed by a ListStore. In this
 * little demonstration, we list a number of famous hiking trails and how to
 * get to them.
 * 
 * @author Andrew Cowie
 */
public class ExampleTrailHeads
{
    public ExampleTrailHeads() {
        final Window window;
        final TreeView view;
        final ListStore model;
        TreeIter row;
        CellRendererText renderer;
        TreeViewColumn vertical;

        window = new Window();

        // ------------------------------------------------

        final DataColumnString placeName;
        final DataColumnString trailHead;
        final DataColumnString elevationFormatted;
        final DataColumnInteger elevationSort;
        final DataColumnBoolean accessibleByTrain;

        /*
         * So we begin our explorations of the TreeView API here. First thing
         * is to create a model. You'll notice we declared the variables
         * above. You don't have to do that, of course, but it makes calling
         * the ListStore constructor more palatable. More generally, you will
         * frequently have the DataColumns as instance field variables (so
         * they can be accessed throughout the file), rather than just local
         * variables. So you'll end up pre-declaring them regardless, and that
         * makes the constructor call nice and compact:
         */

        model = new ListStore(new DataColumn[] {
            placeName = new DataColumnString(),
            trailHead = new DataColumnString(),
            elevationFormatted = new DataColumnString(),
            elevationSort = new DataColumnInteger(),
            accessibleByTrain = new DataColumnBoolean()
        });

        /*
         * You almost never populate your TreeModels with data statically from
         * your source code. We have done so here to demonstrate the use of
         * appendRow() to get a new horizontal data row and then the various
         * forms of setValue() to store data in that row.
         * 
         * In practise, however, you will find the setting of data to be
         * fairly involved; after all, it takes some trouble to decide what
         * you want to display, extract if from your domain object layer and
         * format it prior to insertion into the TreeModel for display by a
         * TreeView.
         * 
         * Notice the use of Pango markup in the placeName column. This can be
         * a powerful way of improving your information density, but be aware
         * that if some rows have multiple lines and some don't, the row
         * height will be inconsistent and will look really ugly. So make sure
         * they all have the same font information and number of newlines. You
         * would use utility methods to help keep things organized, of course,
         * but we've kept it straight forward here.
         */

        row = model.appendRow();
        model.setValue(row, placeName, "Blue Mountains national park\n<small>(Six Foot Track)</small>");
        model.setValue(row, trailHead, "Katoomba, NSW, Australia");
        model.setValue(row, elevationFormatted, "1015 m");
        model.setValue(row, elevationSort, 1005);
        model.setValue(row, accessibleByTrain, true);

        row = model.appendRow();
        model.setValue(row, placeName, "Kilimanjaro\n<small>(Machame route)</small>");
        model.setValue(row, trailHead, "Nairobi, Kenya");
        model.setValue(row, elevationFormatted, "5894 m");
        model.setValue(row, elevationSort, 5894);
        model.setValue(row, accessibleByTrain, false);

        row = model.appendRow();
        model.setValue(row, placeName, "Appalachian Trail\n<small>(roller coaster section)</small>");
        model.setValue(row, trailHead, "Harpers Ferry, West Virginia, USA");
        model.setValue(row, elevationFormatted, "147 m");
        model.setValue(row, elevationSort, 147);
        model.setValue(row, accessibleByTrain, true);

        /*
         * Now onto the view side. First we need the top level TreeView which
         * is the master Widget into which everything else is mixed.
         */

        view = new TreeView(model);

        /*
         * Now the vertical display columns. The sequence is to get a
         * TreeViewColumn, then create the CellRenderer to be put into it, and
         * then set whatever data mappings are appropriate along with any
         * settings for the vertical TreeViewColumn. This is the heart of the
         * TreeView/TreeModel API
         * 
         * Note that we reuse the renderer and vertical variables; unlike the
         * DataColumns, there's no need to keep coming up with unique column
         * names as you rarely need to reference them later.
         */

        vertical = view.appendColumn();
        vertical.setTitle("Place");
        renderer = new CellRendererText(vertical);
        renderer.setMarkup(placeName);

        vertical = view.appendColumn();
        vertical.setTitle("Nearest town");
        renderer = new CellRendererText(vertical);
        renderer.setText(trailHead);
        renderer.setAlignment(0.0f, 0.0f);
        vertical.setExpand(true);

        vertical = view.appendColumn();
        vertical.setTitle("Elevation");
        renderer = new CellRendererText(vertical);
        renderer.setText(elevationFormatted);
        renderer.setAlignment(0.0f, 0.0f);

        /*
         * Because the elevation is a formatted String, it won't sort
         * properly. So we use a DataColumnInteger populated with the raw
         * altitude number to indicate the sort order. (If you don't believe
         * me, try changing the sort column to elevationFormatted instead of
         * elevationSort, and you'll see it order as 1015, 147, 5894). As
         * you'll see from the documentation, setSortColumn() also makes the
         * headers clickable, which saves you having to mess with TreeView's
         * setHeadersClickable() or TreeViewColumn's setClickable(), although
         * there are occasional use cases for them.
         * 
         * Then we call emitClicked() to force the header we want things to be
         * sorted on to actually be active. This is especially necessary if
         * you've defined sorting for more than one vertical column, but if
         * you want sorting on from the start, you need to call it.
         */

        vertical.setSortColumn(elevationSort);
        vertical.emitClicked();

        /*
         * And that's it! You've now done everything you need to have a
         * working TreeView.
         * 
         * ... except that if it's for more than information, you probably
         * want something to happen when someone clicks on a row. So, we hook
         * up a handler to the TreeView.RowActivated signal. The TreePath it
         * gives you is the useful bit.
         */

        view.connect(new TreeView.RowActivated() {
            public void onRowActivated(TreeView source, TreePath path, TreeViewColumn vertical) {
                final TreeIter row;
                final String place, height;

                row = model.getIter(path);

                place = model.getValue(row, trailHead);
                height = model.getValue(row, elevationFormatted);

                System.out.println("You want to go to " + place + " in order to climb to " + height);
            }
        });

        /*
         * The rest of this file is the usual minimum wrapper to make this
         * presentable as a Window on the display.
         */

        // ------------------------------------------------
        window.add(view);

        window.setTitle("Trail Heads");
        window.showAll();

        window.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });
    }

    public static void main(String[] args) {
        Gtk.init(args);

        new ExampleTrailHeads();

        Gtk.main();
    }
}
