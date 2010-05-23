/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007      Vreixo Formoso
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
package menu;

import org.gnome.gdk.Event;
import org.gnome.gdk.Keyval;
import org.gnome.gdk.ModifierType;
import org.gnome.gtk.Accelerator;
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
        final MenuItem fileMenuItem, editMenuItem, viewMenuItem;
        final MenuItem fileSave, editCopy, editPaste;
        final ImageMenuItem fileNew, fileClose;
        final MenuBar menuBar;
        final Accelerator a;

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
         * Create all of MenuItems that will be used:
         */
        fileNew = new ImageMenuItem(Stock.NEW);
        fileSave = new MenuItem("_Save");
        fileClose = new ImageMenuItem(Stock.CLOSE);
        editCopy = new MenuItem("_Edit");
        editPaste = new MenuItem("_Paste");

        /*
         * Now we add the keybindings for the menu items.
         * 
         * This has to be done before you append them to their Menus.
         */
        a = w.getAccelerator();
        fileSave.setAccelerator(a, Keyval.S, ModifierType.CONTROL_MASK);
        editCopy.setAccelerator(a, Keyval.C, ModifierType.CONTROL_MASK);
        editPaste.setAccelerator(a, Keyval.V, ModifierType.CONTROL_MASK);

        /*
         * For ImageMenuItem you can set the keybinding that comes with the
         * Stock item instead.
         */
        fileNew.setAccelerator(a);

        /*
         * Despite fileClose also being an ImageMenuItem we could use the
         * keybinding that is set for Stock.CLOSE. But since we have already
         * set Control + C for editCopy we set this one manually to another
         * keybinding:
         */
        fileClose.setAccelerator(a, Keyval.W, ModifierType.CONTROL_MASK);

        /*
         * To ensure keybindings will work we also have set the Accelerator to
         * the menus containing the MenuItems with the keybindings.
         * 
         * Since we have not set a keybinding for hide text MenuItem we also
         * do need to set the Accelerator of the Menu containing this menu
         * item. Also if a Menu only contains MenuItems with keybindings set
         * from Stock Items (and not set a keybinding manually) it is also not
         * needed.
         */
        fileMenu.setAccelerator(a);
        editMenu.setAccelerator(a);

        /*
         * Now you can add MenuItems to the "file" Menu.
         */
        fileMenu.append(fileNew);
        fileMenu.append(fileSave);

        /*
         * A SeparatorMenuItem can be used to differentiate between unrelated
         * menu options; in practise, though, only use sparingly.
         */
        fileMenu.append(new SeparatorMenuItem());

        /*
         * And add the rest of the menu items.
         */
        fileMenu.append(fileClose);
        editMenu.append(editCopy);
        editMenu.append(editPaste);

        /*
         * Usually you will want to connect to the MenuItem.Activate signal,
         * that is emitted when the user "activates" the menu by either
         * clicking it with the mouse or navigating to it with the keyboard
         * and pressing <ENTER>.
         */
        fileNew.connect(new MenuItem.Activate() {
            public void onActivate(MenuItem source) {
                l.setLabel("You have selected File->New menu.");
            }
        });
        fileSave.connect(new MenuItem.Activate() {
            public void onActivate(MenuItem source) {
                l.setLabel("You have selected File->Save.");
            }
        });

        fileClose.connect(new MenuItem.Activate() {
            public void onActivate(MenuItem source) {
                l.setLabel("You have selected File->Close.");
            }
        });

        /*
         * Given that in most cases you will connect to the MenuItem.Activate
         * signal on MenuItems, a convenience constructor is provided:
         */
        fileMenu.append(new MenuItem("_Quit", new MenuItem.Activate() {
            public void onActivate(MenuItem source) {
                Gtk.mainQuit();
            }
        }));

        /*
         * And now add the actions for the items making up the "edit" Menu.
         */
        editCopy.connect(new MenuItem.Activate() {
            public void onActivate(MenuItem source) {
                l.setLabel("You have selected Edit->Copy.");
            }
        });
        editPaste.connect(new MenuItem.Activate() {
            public void onActivate(MenuItem source) {
                l.setLabel("You have selected Edit->Paste.");
            }
        });

        /*
         * CheckMenuItems hold a boolean state. One use is to allow users to
         * hide some parts of the GUI, as in this example which we put into
         * the "view" Menu:
         */
        viewMenu.append(new CheckMenuItem("Hide _text", new CheckMenuItem.Toggled() {
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
        x.packStart(menuBar, false, false, 0);
        x.packStart(l, false, false, 0);

        w.showAll();

        /*
         * And that's it! One last piece of house keeping, though: it is
         * always necessary to deal with the user closing (what is in this
         * case) the last Window in the application; otherwise the Java VM
         * will keep running even after the (sole) Window is closed - because
         * the main loop never returned.
         */
        w.connect(new Window.DeleteEvent() {
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
