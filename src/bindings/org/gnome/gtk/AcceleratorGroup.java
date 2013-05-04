/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
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

import org.gnome.gdk.ModifierType;
import org.gnome.glib.Object;

/**
 * Accelerator are for key bindings for windows. For example, <b>
 * <code>Ctrl+O</code></b> is the typical accelerator for opening a file.
 * Accelerator in this case refers to pressing these keys together and the
 * action that results.
 * 
 * <p>
 * When a key binding is pressed the action related to the Widget is being
 * activated. Therefore each key binding is directly tied to the Widget in
 * question. You generally only need one AcceleratorGroup object per Window.
 * Create one, then tell the toplevel about it with Window's
 * {@link Window#addAcceleratorGroup(AcceleratorGroup) addAcceleratorGroup()}.
 * 
 * <p>
 * Key bindings are only bound to MenuItems or Actions. For example, given a
 * Window, a Menu, and a MenuItem, we setup an AcceleratorGroup for the
 * toplevel and then assign a key binding to the MenuItem:
 * 
 * <pre>
 * group = new AcceleratorGroup();
 * window.addAcceleratorGroup(group);
 * ..
 * menu.setAcceleratorGroup(group);
 * item.setAccelerator(group, Keyval.O, ModifierType.CONTROL_MASK);
 * </pre>
 * 
 * <p>
 * For ImageMenuItem you can either use the key binding that is associated
 * with it through the Stock item and only invoke:
 * 
 * <pre>
 * imageitem.setAccelerator(group);
 * </pre>
 * 
 * or you can set a custom key binding through the method from parent class
 * MenuItem:
 * 
 * <pre>
 * imageitem.setAccelerator(group, Keyval.N, ModifierType.CONTROL_MASK);
 * </pre>
 * 
 * <p>
 * In order to set multiple Modifier Types you can use the static method
 * {@link ModifierType#or(ModifierType, ModifierType) or()}
 * 
 * @author Sarah Leibbrand
 * @since 4.0.16
 */
public class AcceleratorGroup extends Object
{
    private String root;

    protected AcceleratorGroup(long pointer) {
        super(pointer);
    }

    public AcceleratorGroup() {
        super(GtkAccelGroup.createAcceleratorGroup());

        root = generateRandomString();
    }

    protected String generateRandomPath() {
        return "<" + root + ">/" + generateRandomString();
    }

    /**
     * Internal method to used to create a random 8 character long string.
     * This string is used for path's of menu items and the root for the
     * Accelerator.
     */
    private static String generateRandomString() {
        final char[] generated;
        int i;

        generated = new char[8];

        for (i = 0; i < 8; i++) {
            generated[i] = (char) ((int) (Math.random() * 26) + 97);
        }

        return String.valueOf(generated);
    }
}
