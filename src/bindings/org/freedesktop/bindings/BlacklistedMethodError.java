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
 * Thrown by a generated method which is actually (dangerously) incomplete due
 * to the fact that information about one or more native types was lacking
 * when the bindings were generated. This Error is made visible as a last
 * ditch programatic measure to draw attention to the fact that this method
 * cannot be called; hopefully the {@link FIXME} in the method signature would
 * catch your attention first. This represents a wonderful opportunity for you
 * to contribtue to the java-gnome bindings.
 * 
 * <p>
 * hackers: don't even <b>think</b> about exposing a method that throws this
 * in java-gnome's public API.
 * 
 * @author Andrew Cowie
 * @since 4.0.3
 */
public final class BlacklistedMethodError extends Error
{
    private static final long serialVersionUID = 1L;

    public BlacklistedMethodError(String gType) {
        super("\nNo information about " + gType);
    }

}
