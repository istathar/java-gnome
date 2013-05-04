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
import org.gnome.gtk.AcceleratorGroup;
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
 * @author Sarah Leibbrand
 */
public class ExampleSimpleMenu
{
    public ExampleSimpleMenu() {
        final Window window;
        final VBox box;
        final Label label;
        final Menu fileMenu, editMenu, viewMenu;
        final MenuItem file, edit, view;
        final MenuItem save, copy, paste;
        final ImageMenuItem nouveau, close;
        final AcceleratorGroup group;
        final MenuBar bar;

        /*
         * Begin with the standard VBox in a Window setup:
         */

        window = new Window();
        box = new VBox(false, 3);
        window.add(box);

        label = new Label("Select an action in a menu");
        label.setWidthChars(30);
        label.setAlignment(0.0f, 0.5f);

        /*
         * Most applications will use several Menus in a MenuBar:
         */
        fileMenu = new Menu();
        editMenu = new Menu();
        viewMenu = new Menu();

        /*
         * Create all of MenuItems that will be used:
         */
        nouveau = new ImageMenuItem(Stock.NEW);
        save = new MenuItem("_Save");
        close = new ImageMenuItem(Stock.CLOSE);
        copy = new MenuItem("_Edit");
        paste = new MenuItem("_Paste");

        /*
         * Create the AcceleratorGroup object and add it to the window.
         */

        group = new AcceleratorGroup();
        window.addAcceleratorGroup(group);

        /*
         * Now we add the keybindings for the menu items. This has to be done
         * before you append them to their Menus.
         */
        save.setAccelerator(group, Keyval.s, ModifierType.CONTROL_MASK);
        copy.setAccelerator(group, Keyval.c, ModifierType.CONTROL_MASK);
        paste.setAccelerator(group, Keyval.v, ModifierType.CONTROL_MASK);

        /*
         * For ImageMenuItems you can activate the keybinding that comes with
         * the Stock item instead.
         */
        nouveau.setAccelerator(group);

        /*
         * Despite 'close' also being an ImageMenuItem we could use the
         * keybinding that is set for Stock.CLOSE. But since we have already
         * set Control + C for editCopy we set this one manually to another
         * keybinding:
         */
        close.setAccelerator(group, Keyval.w, ModifierType.CONTROL_MASK);

        /*
         * To ensure keybindings will work we also have set the
         * AcceleratorGroup to the menus containing the MenuItems with the
         * keybindings.
         * 
         * Since we have not set a keybinding for hide text MenuItem we also
         * do not need to set the AcceleratorGroup of the Menu containing this
         * menu item. Also if a Menu only contains MenuItems with keybindings
         * set from Stock Items (and not set a keybinding manually) it is also
         * not needed.
         */
        fileMenu.setAcceleratorGroup(group);
        editMenu.setAcceleratorGroup(group);

        /*
         * Now you can add MenuItems to the "file" Menu.
         */
        fileMenu.append(nouveau);
        fileMenu.append(save);

        /*
         * A SeparatorMenuItem can be used to differentiate between unrelated
         * menu options; in practise, though, only use sparingly.
         */
        fileMenu.append(new SeparatorMenuItem());

        /*
         * And add the rest of the menu items.
         */
        fileMenu.append(close);
        editMenu.append(copy);
        editMenu.append(paste);

        /*
         * Usually you will want to connect to the MenuItem.Activate signal,
         * that is emitted when the user "activates" the menu by either
         * clicking it with the mouse or navigating to it with the keyboard
         * and pressing <ENTER>.
         */
        nouveau.connect(new MenuItem.Activate() {
            public void onActivate(MenuItem source) {
                label.setLabel("You have selected File->New menu.");
            }
        });
        save.connect(new MenuItem.Activate() {
            public void onActivate(MenuItem source) {
                label.setLabel("You have selected File->Save.");
            }
        });

        close.connect(new MenuItem.Activate() {
            public void onActivate(MenuItem source) {
                label.setLabel("You have selected File->Close.");
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
        copy.connect(new MenuItem.Activate() {
            public void onActivate(MenuItem source) {
                label.setLabel("You have selected Edit->Copy.");
            }
        });
        paste.connect(new MenuItem.Activate() {
            public void onActivate(MenuItem source) {
                label.setLabel("You have selected Edit->Paste.");
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
                    label.hide();
                } else {
                    label.show();
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
        file = new MenuItem("_File");
        file.setSubmenu(fileMenu);
        edit = new MenuItem("_Edit");
        edit.setSubmenu(editMenu);
        view = new MenuItem("_View");
        view.setSubmenu(viewMenu);

        /*
         * Finally, most applications make use of a MenuBar that is by
         * convention located at the top of the application Window. It
         * contains the top-level MenuItems.
         */
        bar = new MenuBar();
        bar.append(file);
        bar.append(edit);
        bar.append(view);

        /*
         * Finally, pack the Widgets into the VBox, and present.
         */
        box.packStart(bar, false, false, 0);
        box.packStart(label, false, false, 0);

        window.showAll();

        /*
         * And that's it! One last piece of house keeping, though: it is
         * always necessary to deal with the user closing (what is in this
         * case) the last Window in the application; otherwise the Java VM
         * will keep running even after the (sole) Window is closed - because
         * the main loop never returned.
         */
        window.connect(new Window.DeleteEvent() {
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
