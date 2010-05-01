/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd
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

/**
 * Base class for Widgets and various other elements in GTK.
 * 
 * <p>
 * <i><b>This is the wrapper around <code>GtkObject</code>!</b></i>
 * 
 * <p>
 * <i>Since the GObject type system was abstracted out from GTK some time
 * after GTK was first written, <code>GtkObject</code> predates
 * <code>GObject</code>; almost all of the functionality originally resident
 * in <code>GtkObject</code> was moved to <code>GObject</code> long ago. Its
 * presence in the type hierarchy is largely for backwards compatibility. Only
 * people hacking on java-gnome itself will have any need to interact with
 * this class, and then only rarely.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 */
/*
 * Once upon a time we had a few additional property accessors that were
 * specific to GTK here, but given that we now have setPropertyObject(), they
 * are no longer necessary.
 */
public abstract class Object extends org.gnome.glib.Object
{
    protected Object(long pointer) {
        super(pointer);
    }
}
