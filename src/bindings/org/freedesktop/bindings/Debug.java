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

package org.freedesktop.bindings;

/**
 * Conditionally control internal aspects of the java-gnome libraries. Much as
 * conditional compilation is frowned upon, sometimes you have to switch
 * things on or off. <b>This is not for ./configure time selection of
 * features!</b>
 * 
 * <p>
 * You use these as follows:
 * 
 * <pre>
 * if (Debug.WALK) {
 *     // on the wild side
 * }
 * </pre>
 * 
 * Remember that you <b>must</b> <code>make clean</code> and rebuild the
 * <i>entire</i> project if you change any value in here, otherwise classes
 * that are already compiled with these constants will not be affected.
 * 
 * @author Andrew Cowie
 * @since 4.0.2
 */
public final class Debug
{
    private Debug() {}

    /**
     * Do you want debug output every time a Proxy object is finalized?
     */
    /*
     * FIXME for the moment, this has no effect on the JNI side. It would be
     * much better if we could turn this into a compile time constant in a
     * header file. For now, the twin of this is in bindings_java.h
     */
    public static final boolean MEMORY_MANAGEMENT = false;
}
