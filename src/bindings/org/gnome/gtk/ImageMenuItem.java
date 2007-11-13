/*
 * ImageMenuItem.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
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
 * discussion of the relationship between Images and Label text, and
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
     */
    public ImageMenuItem(Stock stock) {
        super(GtkImageMenuItem.createImageMenuItemFromStock(stock.getStockId(), null));
    }

    /**
     * Convenience constructor, allowing you to create a MenuItem displaying a
     * stock icon while simultaneously hooking up the handler which will take
     * its <code>ACTIVATE</code> signals.
     */
    public ImageMenuItem(Stock stock, MenuItem.ACTIVATE handler) {
        this(stock);
        connect(handler);
    }
}
