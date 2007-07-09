/*
 * MainFrame.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtkdemo;

import org.gnome.gtk.Window;
import org.gnome.gtk.Notebook;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Label;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.WindowPosition;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Menu;
import org.gnome.gtk.MenuItem;
import org.gnome.gtk.MenuBar;

import org.gnome.gdk.Event;

/**
 * Start class for the GTK demo application
 */
public class MainFrame {

    boolean fullScreen = false;

    Window window;
    Notebook notebook;

    public MainFrame() {        
        window = new Window();
        window.setTitle("GTK Demo Application");

        notebook = new Notebook();
// TODO: This raises an error
//         notebook.connect(new Notebook.CHANGE_CURRENT_PAGE() {
//                              public void onChangeCurrentPage(Notebook sourceObject, int offset) {
//                                  System.out.println("tab activated: "+ offset);
//                              }
//                          });
        
        
        window.setPosition(WindowPosition.CENTER);
        window.setDefaultSize(600, 400);
        window.connect(new Window.DELETE_EVENT() {
                public boolean onDeleteEvent(Widget source, Event event) {                    
                    System.out.println("event.getType(): "+ event.getType());
                    exit();
                    return false;
                }
            });

        VBox v = new VBox(false, 0);
        v.packStart(createMenuBar());
        v.packStart(notebook);

        window.add(v);            
    }
    
    /**
     * adds one widget demo to the demo example
     */
    public void add(DemoPanel applet) {
        String label = applet.getClass().getName();
        label = label.substring(label.lastIndexOf(".")+1);
        notebook.appendPage(applet.getWidget(), new Label(label));
    }

    /**
     * Shows all the widgets
     */
    public void showAll() {
		window.showAll();
    }

    MenuBar createMenuBar() {
      
        Menu subMenu = new Menu();
        subMenu.append(new MenuItem("Sub Item _1"));
        subMenu.append(new MenuItem("Sub Item _2"));
        MenuItem subMenuItem = new MenuItem("Sub menu ..");
        subMenuItem.setSubmenu(subMenu);

        Menu otherMenu = new Menu();
        otherMenu.append(new MenuItem("Item _1"));
        otherMenu.append(new MenuItem("Item _2"));
        otherMenu.append(subMenuItem);
        MenuItem otherMenuItem = new MenuItem("_Other menu ..");
        otherMenuItem.setSubmenu(otherMenu);

        Menu fileMenu = new Menu();
        fileMenu.append(new MenuItem("This is one item"));
        fileMenu.append(new MenuItem("This is another item"));
        MenuItem full = new MenuItem("Toggle _Fullscreen");
        full.connect(new MenuItem.ACTIVATE() {
                public void onActivate(MenuItem source) {
                    toggleFullScreen();
                }
            });
        fileMenu.append(full);        
        
        MenuItem exit = new MenuItem("E_xit");
        exit.connect(new MenuItem.ACTIVATE() {
                public void onActivate(MenuItem source) {
                    System.out.println("exit clicked");
                    exit();
                }
            });
        fileMenu.append(exit);        

        MenuItem fileMenuItem = new MenuItem("_File");
        fileMenuItem.setSubmenu(fileMenu);

        MenuBar menuBar = new MenuBar();
        menuBar.append(fileMenuItem);
        menuBar.append(otherMenuItem);

        return menuBar;
     }

    public void toggleFullScreen() {
        if (fullScreen) {
            window.setUnFullScreen();
        } else {
            window.setFullScreen();
        }
        fullScreen = ! fullScreen;
    }
    
    public void exit() {
         Gtk.mainQuit();
    }
}