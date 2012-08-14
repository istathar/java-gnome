/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006,2008 Operational Dynamics Consulting, Pty Ltd
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

/**
 * Marker interface which is the parent of all signals as expressed in the
 * bindings. Calling it Signal is actually a slight misnomer; when someone
 * implements the concrete Signal subclass that is written into the public API
 * files a "SignalHandler" is more what they've created.
 * 
 * <p>
 * <b>Developers using the bindings will never need to use or subclass this
 * directly.</b> People wishing to write signal handler callbacks implement
 * the subclasses of this interface provided by individual Widgets. See the
 * signal called Button {@link org.gnome.gtk.Button.Clicked Button.Clicked}
 * for a thorough example of how signals are used in practise.
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 */
/*
 * This is only here so that the callback design is more straight forward.
 * Otherwise we'd have to lookup the jclass for each signal on the JNI side,
 * and that's silly. The downcast in the generated handleName() method will
 * take care of it.
 */
public abstract interface Signal
{
}
