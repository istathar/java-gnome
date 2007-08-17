/*
 * ExampleSimpleMenu.java
 *
 * Copyright (c) 2007 Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package menu;

import org.gnome.gtk.CheckMenuItem;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Label;
import org.gnome.gtk.Menu;
import org.gnome.gtk.MenuBar;
import org.gnome.gtk.MenuItem;
import org.gnome.gtk.SeparatorMenuItem;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Window;
import org.gnome.gtk.CheckMenuItem.TOGGLED;
import org.gnome.gtk.MenuItem.ACTIVATE;

/**
 * Little example of how to use {@link Menu} and related Widgets.
 * 
 * @author Vreixo Formoso
 */
public class ExampleSimpleMenu extends Window
{

    public ExampleSimpleMenu() {
        final VBox x;
        final Label l;

        x = new VBox(false, 3);
        add(x);

        l = new Label("Select an action in a menu");

        /* most applications will use several menus */
        Menu fileMenu = new Menu();
        Menu editMenu = new Menu();
        Menu viewMenu = new Menu();

        /* now you can add MenuItems to the file Menu */
        MenuItem fileNew = new MenuItem("_New");
        fileMenu.append(fileNew);

        /*
         * usually you will want to connect to ACTIVATE signal, that is thrown
         * when the user "activates" the menu, for example by clicking it
         */
        fileNew.connect(new ACTIVATE() {
            public void onActivate(MenuItem sourceObject) {
                l.setLabel("You have selected File->New menu.");
            }
        });

        /*
         * given that in most cases you will connect to the ACTIVATE signal on
         * MenuItem's, a suitable constructor is provided:
         */
        fileMenu.append(new MenuItem("_Save", new ACTIVATE() {
            public void onActivate(MenuItem sourceObject) {
                l.setLabel("You have selected File->Save menu.");
            }
        }));
        
        /*
         * a SeparatorMenuItem can be used to differentiate between unrelated
         * menu options
         */
        fileMenu.append(new SeparatorMenuItem());
        
        fileMenu.append(new MenuItem("_Exit", new ACTIVATE() {
            public void onActivate(MenuItem sourceObject) {
                Gtk.mainQuit();
            }
        }));

        /* and now add the items to the edit Menu */
        editMenu.append(new MenuItem("_Copy", new ACTIVATE() {
            public void onActivate(MenuItem sourceObject) {
                l.setLabel("You have selected Edit->Copy menu.");
            }
        }));
        editMenu.append(new MenuItem("_Paste", new ACTIVATE() {
            public void onActivate(MenuItem sourceObject) {
                l.setLabel("You have selected Edit->Paste menu.");
            }
        }));
        
        /* 
         * a CheckMenuItem holds a boolean state. It can be used, for
         * example, to allow users to hide some parts of the GUI.
         */
        viewMenu.append(new CheckMenuItem("_Hide Label", new TOGGLED() {
            public void onToggled(CheckMenuItem sourceObject) {
                if (sourceObject.getActive()) {
                    l.hide();
                } else {
                    l.show();
                }
            }
        }));
        
        /*
         * A MenuItem can have a sub-menu, that will be expanded when
         * the user puts the mouse pointer over it. This is usually used
         * in top level MenuItems, but you can use it anywhere.
         */
        MenuItem fileMenuItem = new MenuItem("_File...");
        fileMenuItem.setSubmenu(fileMenu);
        MenuItem editMenuItem = new MenuItem("_Edit...");
        editMenuItem.setSubmenu(editMenu);
        MenuItem viewMenuItem = new MenuItem("_View...");
        viewMenuItem.setSubmenu(viewMenu);

        /*
         * Finally, most application make use of a MenuBar, that is located
         * at the top of the application Window.
         */
        MenuBar menuBar = new MenuBar();
        x.packStart(menuBar, false, false, 0);
        
        /* The MenuBar contains the top-level MenuItems */
        menuBar.append(fileMenuItem);
        menuBar.append(editMenuItem);
        menuBar.append(viewMenuItem);
        
        x.add(l);

        showAll();
    }

    public static void main(String[] args) {
        Gtk.init(args);

        new ExampleSimpleMenu();

        Gtk.main();
    }
}
