/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
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
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.gnome.gtk;

/**
 * A MenuItem which displays an icon Image to the left of the Label text. This
 * is used almost exclusively to add the MenuItems with common behaviour from
 * the family of Stock icons, for example "File->Quit" and "Edit->Copy", so
 * that the icons used are consistent with the rest of the GNOME Desktop.
 * Indeed, it is somewhat discouraged to create ImageMenuItems with your own
 * icons as too many icons can distract visually from the fact that the stock
 * ones allow the eye to quickly recognize expected UI elements.
 * 
 * <p>
 * See Action's {@link Action#createMenuItem() createMenuItem()}; you
 * frequently need to do a UI activity from more than one place and Action
 * will (among other things) generate the ImageMenuItem for a given Stock
 * item. See also Button's {@link Button#setImage(Image) setImage()} for a
 * discussion of the relationship between Images and Label text.
 * 
 * @author Andrew Cowie
 * @since 4.0.5
 */
public class ImageMenuItem extends MenuItem
{
    protected ImageMenuItem(long pointer) {
        super(pointer);
    }

    /**
     * Create a MenuItem displaying a stock icon image.
     */
    /*
     * TODO Passing null as the second argument probably isn't quite what we
     * want. You can change the AccelGroup separately, but that's available to
     * support overriding the default accelerators. All in all this will need
     * updating.
     * 
     * From Azania: this should no longer need any updating unless you want to
     * force the user to use the accelerator keybindings from the stock icon.
     */
    public ImageMenuItem(Stock stock) {
        super(GtkImageMenuItem.createImageMenuItemFromStock(stock.getStockId(), null));
    }

    /**
     * Convenience constructor, allowing you to create a MenuItem displaying a
     * stock icon while simultaneously hooking up the handler which will take
     * its <code>MenuItem.Activate</code> signals.
     */
    public ImageMenuItem(Stock stock, MenuItem.Activate handler) {
        this(stock);
        connect(handler);
    }

    /**
     * Create a MenuItem displaying an image next to text. When you have a
     * custom visual that is appropriate to show beside the label of a given
     * MenuItem, you can use this to construct it.
     * 
     * <p>
     * Don't use an empty string as a label! Users can turn off display of
     * icons in menus, and if they do you'll end up with a MenuItem with
     * "nothing" in it.
     * 
     * <p>
     * There is also a constructor which allows you to connect an
     * <code>MenuItem.Activate</code> handler in-line, see
     * {@link ImageMenuItem#ImageMenuItem(Image, String, org.gnome.gtk.MenuItem.Activate)
     * here}.
     * 
     * @since 4.0.6
     */
    public ImageMenuItem(Image image, String label) {
        super(GtkImageMenuItem.createImageMenuItemWithMnemonic(label));
        GtkImageMenuItem.setImage(this, image);
    }

    /**
     * Create a MenuItem displaying an image next to text, and hook up an
     * <code>MenuItem.Activate</code> handler at the same time.
     * 
     * @since 4.0.6
     */
    public ImageMenuItem(Image image, String label, MenuItem.Activate handler) {
        super(GtkImageMenuItem.createImageMenuItemWithMnemonic(label));
        GtkImageMenuItem.setImage(this, image);
        connect(handler);
    }

    /**
     * Set the Image that will be used as an icon beside the text in the
     * ImageMenuItem.
     * 
     * <p>
     * Be aware that there are system wide settings which allow a user to turn
     * off icons appearing in menus. Since there is always a chance that the
     * "image" Widget being added will be hidden, don't create an
     * ImageMenuItem with an empty label.
     * 
     * @since 4.0.6
     */
    public void setImage(Image image) {
        GtkImageMenuItem.setImage(this, image);
    }

    /**
     * Force the icon accompanying this ImageMenuItem to always be shown,
     * overriding the setting inherited from GtkSettings and GNOME.
     * 
     * <p>
     * <i>As at GNOME 2.28, the default was changed by the `control-center`
     * hackers to not show menu and button icons. This is very inconvenient,
     * so you'll find yourself needing to call this frequently.</i>
     * 
     * @since 4.0.14
     */
    public void setAlwaysShowImage(boolean setting) {
        GtkImageMenuItem.setAlwaysShowImage(this, setting);
    }

    /**
     * Activate the key binding that comes with the stock configuration, for
     * the case that this ImageMenuItem was constructed using a Stock item.
     * 
     * @since 4.0.16
     */
    public void setAccelerator(AcceleratorGroup group) {
        GtkImageMenuItem.setAccelGroup(this, group);
    }
}
