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
package org.gnome.glib;

import org.freedesktop.bindings.Pointer;

/**
 * Parent class of proxied structures. Java side, these behave like normal
 * {@link org.gnome.glib.Object Object}s, but they are not as rich - they may
 * be missing getters or setters (or both!), for example.
 * 
 * <p>
 * In general, Boxed are short lived. They are commonly used to convey
 * parameters or represent state such as in an Iterator. As such, good
 * programming practise is to <b>not</b> hold on to these long term. Use them
 * and let them go out of scope so they can be reclaimed.
 * 
 * <p>
 * <i>In the underlying GLib library, a <code>GBoxed</code> is an opaque
 * wrapper around a C structure allowing it to be used within GLib's Type
 * system. <code>GBoxed</code> generally have custom <code>copy()</code> and
 * <code>free()</code> functions. Unlike <code>GObjects</code> they are not
 * memory managed via the reference counting mechanism, and so the owner of a
 * <code>GBoxed</code> is responsible to free it. The trick is to figure out
 * whether we are owner of the <code>GBoxed</code> or not...</i>
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 * @since 4.0.0
 */
/*
 * WARNING This is not fully implemented.
 */
public abstract class Boxed extends Pointer
{
    protected Boxed(long pointer) {
        super(pointer);
    }
}
