/*
 * ExampleToolbar.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2007 Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package menu;

import org.gnome.gdk.Event;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Label;
import org.gnome.gtk.Menu;
import org.gnome.gtk.MenuItem;
import org.gnome.gtk.MenuToolButton;
import org.gnome.gtk.SeparatorToolItem;
import org.gnome.gtk.StockId;
import org.gnome.gtk.ToolButton;
import org.gnome.gtk.ToolItem;
import org.gnome.gtk.Toolbar;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.gtk.ToolButton.CLICKED;
import org.gnome.gtk.Window.DELETE_EVENT;

/**
 * How to use {@link Toolbar} and related Widgets.
 * 
 * @author Vreixo Formoso
 */
public class ExampleToolbar 
{

    public ExampleToolbar() {
        final Window w;
        final VBox x;
        final Label l;
        final Toolbar toolbar;
        final ToolButton buttonNew;
        final MenuToolButton mtb;
        final Menu openMenu;
        final ToolItem item;
        
        w = new Window();

        x = new VBox(false, 3);
        w.add(x);

        /*
         * Create a Toolbar, and add it at the beginning of the Window.
         * Note that usually you also want a MenuBar, that is located at the
         * top of the Window, with the Toolbar just under it. 
         */
        toolbar = new Toolbar();
        x.packStart(toolbar, false, false, 0);

        l = new Label("Select an action in a menu");
        x.add(l);
        
        /*
         * Usually you will want to add several ToolButtons to your Toolbar.
         * You could create ToolButtons from a Stock id. That way, both 
         * Icon and Label are automatically set.
         */
        buttonNew = new ToolButton(StockId.NEW);
        toolbar.add(buttonNew);
        
        /*
         * You can respond to user clicks in the ToolButton connecting
         * to the CLICKED signal.
         */
        buttonNew.connect(new CLICKED() {
            public void onClicked(ToolButton sourceObject) {
                l.setLabel("You have click NEW ToolButton");
            }
        });

        /*
         * Sometimes you need a ToolButton that also has a dropdown Menu,
         * to allow the user select alternative actions. You can do that
         * with a MenuToolButton. 
         */
        mtb = new MenuToolButton(StockId.OPEN);
        toolbar.add(mtb);
        
        /*
         * You can add your Menu to this kind of ToolButtons
         */
        openMenu = new Menu();
        openMenu.append(new MenuItem("File _A", new MenuItem.ACTIVATE() {
            public void onActivate(MenuItem sourceObject) {
                l.setLabel("You have select File A in the Menu");
            }
        }));
        openMenu.append(new MenuItem("File _B", new MenuItem.ACTIVATE() {
            public void onActivate(MenuItem sourceObject) {
                l.setLabel("You have select File B in the Menu");
            }
        }));
        openMenu.showAll();
        mtb.setMenu(openMenu);
        
        /*
         * You also can respond to user clicks in the MenuToolButton itself
         */
        mtb.connect(new CLICKED() {
            public void onClicked(ToolButton sourceObject) {
                l.setLabel("You have click the Open MenuToolButton");
            }
        });
        
        /*
         * To group together related items, you put a SeparatorToolItem
         * between then
         */
        toolbar.add( new SeparatorToolItem() );
        
        //TODO ToggleToolButtons when implemented
        
        /*
         * Finally, you can add another kind of Widgets to your Toolbar,
         * after adding them to a ToolItem
         */
        item = new ToolItem();
        
        //TODO replace the Label with a more useful Widget
        item.add(new Label("This is a label"));
        toolbar.add(item);        

        w.connect(new DELETE_EVENT() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });
        w.showAll();
    }

    public static void main(String[] args) {
        Gtk.init(args);

        new ExampleToolbar();

        Gtk.main();
    }

}
