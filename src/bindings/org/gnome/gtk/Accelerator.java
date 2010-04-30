/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright ?? 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
 * "Claspath Exception"), the copyright holders of this library give you
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

import org.gnome.gdk.Keyval;
import org.gnome.gdk.ModifierType;
import org.gnome.glib.Object;

/**
 * <p>
 * Accelerator are for keybindings for windows. For example Control + O would
 * be for opening a file. Accelerator in this case refers to holding these
 * keybindings.
 * </p>
 * 
 * <p>
 * When a keybinding is pressed the action related to the widget is being
 * activated. Therefor keybindings are directly bound the widgets. There can
 * be however only 1 Accelerator object per window. This object is therefor
 * retrieved by calling {@link Window#getAccelerator()}.
 * </p>
 * 
 * <p>
 * Keybindings are only bound to menuitems. Below is an example:
 * </p>
 * 
 * <pre>
 * Window window = new Window();
 * Menu menu = new Menu();
 * MenuItem item = new MenuItem();
 * ImageMenuItem imageitem = new ImageMenuItem(Stock.NEW);
 * 
 * ....
 * 
 * menu.setAccelerator(window.getAccelerator());
 * item.setAccelerator(window.getAccelerator(), Keyval.O, ModifierType.CONTROL_MASK);
 * </pre>
 * 
 * <p>
 * For ImageMenuItem you can either use the keybinding that is associated with
 * it trough the stock and only invoke:
 * 
 * <pre>
 * imageitem.setAccelerator(window.getAccelerator());
 * </pre>
 * 
 * Or set a custom keybinding trough the method from the parent MenuItem:
 * 
 * <pre>
 * imageitem.setAccelerator(window.getAccelerator(), Keyval.N, ModifierType.CONTROL_MASK);
 * </pre>
 * 
 * </p>
 * 
 * <p>
 * In order to set multiple Modifier Types you can use the static method
 * {@link ModifierType#or(ModifierType, ModifierType)}
 * </p>
 * 
 * @author azania
 * @since 4.0.16
 */
public class Accelerator extends Object
{

    private String root;

    protected Accelerator(long pointer) {
        super(pointer);
    }

    protected Accelerator() {
        super(GtkAccelGroup.createAccelerator());
        root = stringGenerator();
    }

    protected boolean addMenuItemKeyBinding(MenuItem item, Keyval key, ModifierType modifier) {
        // check whether it has already has a path and whether it is known, if
        // so then change it.
        if (item.getPath() != null)
            if (!GtkAccelMap.lookupEntry(item.getPath(), null))
                return GtkAccelMap.changeEntry(item.getPath(), key, modifier, true);

        // generate the path, set it and add it the map to be registered.
        String path = "<" + root + ">/" + stringGenerator();
        item.setPath(path);
        GtkAccelMap.addEntry(path, key, modifier);
        return true;
    }

    /**
     * Internal method to used to create a random 8 character long string.
     * This string is used for path's of menu items and the root for the
     * Accelerator.
     * 
     * @return String A random generated string of 8 characters.
     */
    private String stringGenerator() {
        char[] generated = new char[8];
        for (int i = 0; i < 8; i++) {
            generated[i] = (char) ((int) (Math.random() * 26) + 97);
        }
        return String.valueOf(generated);
    }
}
