/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2007      Vreixo Formoso
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
 * Representation of an enum that is used to bit-pack option flags. A flag is
 * a Constant that can be bitwise OR'd with another flag of the same type
 * 
 * @author Vreixo Formoso
 * @author Andrew Cowie
 * @since 4.0.3
 */
/*
 * The presence of bitwise operations here is of some concern; given the
 * contract we have established to treat binary data from the native side as
 * opaque, I am not convinced that these instructions are safe. Vreixo has
 * contributed unit tests establishing some confidence, so we will keep it for
 * now. Move to JNI calls if any byte order problems are encountered.
 */
public abstract class Flag extends Constant
{

    protected Flag(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * Helper method to enable the implementation of or() in subclasses.
     */
    protected final static Flag orTwoFlags(Flag a, Flag b) {
        if (a.getClass() != b.getClass()) {
            throw new IllegalArgumentException(
                    "Both arguments need to be an instances of the same concrete Flags class");
        }

        return Plumbing.flagFor(a.getClass(), a.ordinal | b.ordinal);
    }

    /**
     * Utility function to determine whether a Flags instance has the bit
     * embodied by <code>setting</code> set. An example of this in action is:
     * 
     * <pre>
     * WindowState s;
     * ...
     *     
     * if (s.contains(WindowState.STICKY)) {
     *     // get a cloth to clean up the mess
     * }
     * </pre>
     * 
     * You can only use this on instances of the same class!
     */
    public final boolean contains(Flag setting) {
        if (this.getClass() != setting.getClass()) {
            throw new IllegalArgumentException("Argument needs to be an instance of " + this.getClass());
        }

        return (this.ordinal & setting.ordinal) != 0;
    }

    protected final static Flag andTwoFlags(Flag a, Flag b) {
        if (a.getClass() != b.getClass()) {
            throw new IllegalArgumentException(
                    "Both arguments need to be an instances of the same concrete Flags class");
        }

        return Plumbing.flagFor(a.getClass(), a.ordinal & b.ordinal);
    }

    /**
     * Remove b from a
     */
    protected final static Flag maskTwoFlags(Flag a, Flag b) {
        return Plumbing.flagFor(a.getClass(), a.ordinal & (a.ordinal ^ b.ordinal));
    }
}
