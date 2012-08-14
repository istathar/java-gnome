/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
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
 * Misuse of the underlying library. This is thrown as a result of a
 * programmer carrying out an operation which the native library considers
 * illegal. In an ideal world we would inhibit this before we even get to the
 * native side, but in this case we didn't, and their defence mechanisms have
 * caught the problem.
 * 
 * <p>
 * By definition, a <code>CRITICAL</code> has to be fatal; the application is
 * known to be in an undefined state after one has been emitted. While some
 * programs allow the user to carry on in blissful ignorance, these warnings
 * indicate a programmer doing something wrong, and that needs fixing.
 * 
 * <p>
 * The message has, therefore, been thrown as a Java Error. This gets you a
 * stack trace at the place where the problem occurred, and that's how we
 * identify problems in the Java world.
 * 
 * <p>
 * <i><b>This is not the wrapper around <code>GError</code>!</b></i>
 * 
 * <p>
 * <i>This class is our way of exposing fatal error conditions in a
 * Java-appropriate fashion. <code>GError</code>, on the other hand, is GLib's
 * mechanism for returning conditions that the developer can ask the user for
 * a decision about. Incidentally, we do not expose those directly in the
 * java-gnome public API; where they occur we propagate an appropriate Java
 * checked exception instead. See {@link GlibException}</i>.
 * 
 * @author Andrew Cowie
 * @since 4.0.7
 */
/*
 * It was very tempting to have this class extend CoderMalfunctionError. What
 * a great name. "Problem exists between Keyboard and Chair". I love it! :)
 */
public class FatalError extends org.freedesktop.bindings.FatalError
{
    private static final long serialVersionUID = 1;

    protected FatalError() {
        super();
    }

    protected FatalError(String msg) {
        super(msg);
    }
}
