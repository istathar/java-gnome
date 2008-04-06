/*
 * ExampleSimpleMenu.java
 *
 * Copyright (c) 2007 Vreixo Formoso
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package menu;

import org.gnome.gdk.Event;
import org.gnome.gtk.CheckMenuItem;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.ImageMenuItem;
import org.gnome.gtk.Label;
import org.gnome.gtk.Menu;
import org.gnome.gtk.MenuBar;
import org.gnome.gtk.MenuItem;
import org.gnome.gtk.SeparatorMenuItem;
import org.gnome.gtk.Stock;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.gtk.CheckMenuItem.TOGGLED;
import org.gnome.gtk.MenuItem.ACTIVATE;

/**
 * How to use {@link Menu} and related Widgets.
 * 
 * @author Vreixo Formoso
 * @author Andrew Cowie
 */
public class ExampleSimpleMenu
{
    public ExampleSimpleMenu() {
        final Window w;
        final VBox x;
        final Label l;
        final Menu fileMenu, editMenu, viewMenu;
        final MenuItem fileNew, fileMenuItem, editMenuItem, viewMenuItem;
        final MenuBar menuBar;

        /*
         * Begin with the standard VBox in a Window setup:
         */

        w = new Window();
        x = new VBox(false, 3);
        w.add(x);

        l = new Label("Select an action in a menu");
        l.setWidthChars(30);
        l.setAlignment(0.0f, 0.5f);

        /*
         * Most applications will use several Menus in a MenuBar:
         */
        fileMenu = new Menu();
        editMenu = new Menu();
        viewMenu = new Menu();

        /*
         * Now you can add MenuItems to the "file" Menu.
         */
        fileNew = new MenuItem("_New");
        fileMenu.append(fileNew);

        /*
         * Usually you will want to connect to ACTIVATE signal, that is
         * emitted when the user "activates" the menu by either clicking it
         * with the mouse or navigating to it with the keyboard and pressing
         * <ENTER>.
         */
        fileNew.connect(new ACTIVATE() {
            public void onActivate(MenuItem source) {
                l.setLabel("You have selected File->New menu.");
            }
        });

        /*
         * Given that in most cases you will connect to the ACTIVATE signal on
         * MenuItem's, a convenience constructor is provided:
         */
        fileMenu.append(new MenuItem("_Save", new ACTIVATE() {
            public void onActivate(MenuItem source) {
                l.setLabel("You have selected File->Save.");
            }
        }));

        /*
         * A SeparatorMenuItem can be used to differentiate between unrelated
         * menu options; in practise, though, only use sparingly.
         */
        fileMenu.append(new SeparatorMenuItem());

        fileMenu.append(new ImageMenuItem(Stock.CLOSE, new ACTIVATE() {
            public void onActivate(MenuItem source) {
                l.setLabel("You have selected File->Close.");
            }
        }));
        fileMenu.append(new MenuItem("_Quit", new ACTIVATE() {
            public void onActivate(MenuItem source) {
                Gtk.mainQuit();
            }
        }));

        /*
         * And now add the items making up the "edit" Menu.
         */
        editMenu.append(new MenuItem("_Copy", new ACTIVATE() {
            public void onActivate(MenuItem source) {
                l.setLabel("You have selected Edit->Copy.");
            }
        }));
        editMenu.append(new MenuItem("_Paste", new ACTIVATE() {
            public void onActivate(MenuItem source) {
                l.setLabel("You have selected Edit->Paste.");
            }
        }));

        /*
         * CheckMenuItems hold a boolean state. One use is to allow users to
         * hide some parts of the GUI, as in this example which we put into
         * the "view" Menu:
         */
        viewMenu.append(new CheckMenuItem("Hide _text", new TOGGLED() {
            public void onToggled(CheckMenuItem source) {
                if (source.getActive()) {
                    l.hide();
                } else {
                    l.show();
                }
            }
        }));

        /*
         * A MenuItem can have a "sub-menu", that will be expanded when the
         * user puts the mouse pointer over it. This is also used in creating
         * the elements for the top level MenuBar, but you can use it within
         * normal Menus as well. That said, submenus of Menus are considered
         * less "discoverable" because the user has to navigate through the
         * hierarchy to find out what options are available to them, rather
         * than seeing them at first glance.
         */
        fileMenuItem = new MenuItem("_File");
        fileMenuItem.setSubmenu(fileMenu);
        editMenuItem = new MenuItem("_Edit");
        editMenuItem.setSubmenu(editMenu);
        viewMenuItem = new MenuItem("_View");
        viewMenuItem.setSubmenu(viewMenu);

        /*
         * Finally, most applications make use of a MenuBar that is by
         * convention located at the top of the application Window. It
         * contains the top-level MenuItems.
         */
        menuBar = new MenuBar();
        menuBar.append(fileMenuItem);
        menuBar.append(editMenuItem);
        menuBar.append(viewMenuItem);

        /*
         * Finally, pack the Widgets into the VBox, and present:
         */
        x.packStart(menuBar);
        x.packStart(l);

        w.showAll();

        /*
         * And that's it! One last piece of house keeping, though: it is
         * always necessary to deal with the user closing (what is in this
         * case) the last Window in the application; otherwise the Java VM
         * will keep running even after the (sole) Window is closed - because
         * the main loop never returned.
         */
        w.connect(new Window.DELETE_EVENT() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });
    }

    public static void main(String[] args) {
        Gtk.init(args);

        new ExampleSimpleMenu();

        /*
         * Yes, you could have written all the Window creation code here in
         * main() but it is generally good practise to put that setup into a
         * constructor, as we have here.
         */

        Gtk.main();
    }
}
