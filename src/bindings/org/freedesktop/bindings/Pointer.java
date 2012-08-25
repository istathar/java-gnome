/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
package org.freedesktop.bindings;

/**
 * A proxy object representing a native resource. Specifically, this is a
 * wrapper around a pointer.
 * 
 * <p>
 * <i><b>This is implementation, and you will never need to use it
 * directly</b></i>
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 * @since 4.0.9
 */
/*
 * And so the magic happens. This is where the pointer that this class proxies
 * is held. The JNI layer reaches up to this class and reads the pointer field
 * to get the address of the proxied GValue, etc. This is in its own library
 * as a means to keep the pointer handling isolated and cleanly installable.
 * Also useful given that Cairo doesn't actually depend on Glib.
 */
public abstract class Pointer
{
    /*
     * This is an opaque representation of a memory address. It's a Java long,
     * which means it's 64 bits wide which in turn means it can hold an
     * address on a 64 bit system, but any interpretation that the Java
     * language might assign to a long (ie, that it's signed) is meaningless
     * and incorrect! This is package public so that Plumbing can see it, and
     * final so that once constructed it is immutable.
     */
    final long pointer;

    /**
     * Create a new proxy object with the specified address as its pointer.
     * This it the top of the constructor chain.
     */
    protected Pointer(long pointer) {
        if (pointer == 0L) {
            throw new RuntimeException("Cannot make a Java proxy for the NULL pointer!");
        }
        this.pointer = pointer;
    }

    /**
     * Parent release function. Will be called by the Java garbage collector
     * when it invokes the finalizer, so this is the time to release
     * references and free memory on the C side.
     */
    protected abstract void release();

    /*
     * This is a placeholder to remind us of the cleanup actions that will be
     * necessary, irrespective of the finalizer technique used.
     */
    protected void finalize() {
        release();
    }

    public String toString() {
        if (Debug.MEMORY_MANAGEMENT) {
            StringBuilder result;
            result = new StringBuilder();
            result.append(Plumbing.toHexString(pointer));
            result.append("*");
            result.append(this.getClass().getName());

            return result.toString();
        } else {
            return this.getClass().getName();
        }
    }
}
