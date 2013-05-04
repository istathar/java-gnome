/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2013 Operational Dynamics Consulting, Pty Ltd and Others
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
package tooltip;

import org.gnome.gdk.Event;
import org.gnome.gtk.CellRendererText;
import org.gnome.gtk.DataColumn;
import org.gnome.gtk.DataColumnStock;
import org.gnome.gtk.DataColumnString;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.IconSize;
import org.gnome.gtk.ListStore;
import org.gnome.gtk.Stock;
import org.gnome.gtk.Tooltip;
import org.gnome.gtk.TreeIter;
import org.gnome.gtk.TreePath;
import org.gnome.gtk.TreeView;
import org.gnome.gtk.TreeViewColumn;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * A tutorial example of using a TreeView with a tooltip per row.
 * 
 * @author Sarah Leibbrand
 */
public class ExampleTreeViewTooltips
{

    private TreeView view;

    private ListStore model;

    private DataColumnString name;

    private TreeViewColumn vertical;

    private DataColumnStock icon;

    public ExampleTreeViewTooltips() {
        final Window window;

        window = new Window();

        /*
         * First we create the TreeView
         */
        createTreeView();

        /*
         * Then we have to connect the QueryTooltip handler to the view so it
         * can display tooltips.
         */
        view.connect(new Widget.QueryTooltip() {
            public boolean onQueryTooltip(Widget source, int x, int y, boolean keyboardMode,
                    Tooltip tooltip) {
                TreeIter iter;
                TreePath path;

                /*
                 * first we check whether there is anything to show a tooltip
                 * for, if not we return false so that there won't be any
                 * tooltip shown.
                 */
                if (!view.hasTooltipContext(x, y, keyboardMode)) {
                    return false;
                }

                /*
                 * Now that we know that there can be a tooltip we retrieve
                 * the TreeIter so we can work with that to get the data:
                 */
                iter = view.getTreeIterFromTooltipContext(x, y, keyboardMode, model);

                /*
                 * Now we can work with the tooltip and place any content we
                 * would want in it.
                 * 
                 * In this example a simple icon and text.
                 */
                tooltip.setMarkup(model.getValue(iter, name));
                tooltip.setStockIcon(model.getValue(iter, icon), IconSize.MENU);

                /*
                 * Now that we have setup the tooltip we need to set it the
                 * row or the cell. so it knows it is for that area. This will
                 * prevent it from continously trying to repaint the tooltip.
                 * 
                 * In this example we will bind it the row.
                 * 
                 * But for this we need the TreePath which we can also
                 * retrieve.
                 */
                path = view.getTreePathFromTooltipContext(x, y, keyboardMode);
                view.setTooltipRow(tooltip, path);

                /*
                 * Now it is time to show the tooltip by returning true;
                 */
                return true;
            }
        });

        /*
         * The rest of this file is the usual minimum wrapper to make this
         * presentable as a Window on the display.
         */

        window.add(view);

        window.setTitle("TreeView tooltips");
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

        new ExampleTreeViewTooltips();

        Gtk.main();
    }

    /*
     * Create a standard TreeView as shown in another example.
     */
    private void createTreeView() {
        DataColumnString index;
        CellRendererText renderer;
        TreeIter row;

        model = new ListStore(new DataColumn[] {
            name = new DataColumnString(),
            index = new DataColumnString(),
            icon = new DataColumnStock()
        });

        view = new TreeView(model);

        vertical = view.appendColumn();
        vertical.setTitle("Index");
        renderer = new CellRendererText(vertical);
        renderer.setText(index);

        vertical = view.appendColumn();
        vertical.setTitle("Name");
        renderer = new CellRendererText(vertical);
        renderer.setMarkup(name);

        vertical.setSortColumn(index);

        row = model.appendRow();
        model.setValue(row, name, "The first row of the table\n<small>(with an edit icon)</small>");
        model.setValue(row, index, "1.");
        model.setValue(row, icon, Stock.EDIT);

        row = model.appendRow();
        model.setValue(row, name, "And now the second row\n<small>(with the apply icon)</small>");
        model.setValue(row, index, "2.");
        model.setValue(row, icon, Stock.APPLY);

        row = model.appendRow();
        model.setValue(row, name,
                "Last but not least, the final row\n<small>(And the last one to convert)</small>");
        model.setValue(row, index, "3.");
        model.setValue(row, icon, Stock.CONVERT);
    }
}
