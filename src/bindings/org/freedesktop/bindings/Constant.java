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
package org.freedesktop.bindings;

/**
 * Representation of enumerated constants. Enums are very fundamental in most
 * libraries but are ordinal integers handled by the compiler as constants;
 * they are <i>not</i> passed around by pointer. For that, we have the
 * {@link Proxy} class.
 * 
 * <p>
 * Note that this has nothing to do with the <code>enum</code> mechanism
 * introduced in Java 1.5.
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 */
/*
 * Note for GLib based libraries: these are our representation of integer
 * constants and are not org.gnome.glib.Values instances. So for the case of a
 * GValue containing an G_TYPE_ENUM, we have extract it from the Value; see
 * getEnum() in org.gnome.glib.GValue
 */
public abstract class Constant
{

    /*
     * This is package visible so that Plumbing can see it, and final so that
     * only the constructor here can set it.
     */
    final int ordinal;

    /**
     * A text version of the name of the constant representing this enum. This
     * is mostly registered so that if the developer has need to output
     * <i>which</i> particular constant they are using while debugging, they
     * can do so.
     * 
     * <p>
     * The pattern of passing a name up when constructing constants as an
     * explicit debugging measure is inspired by "Constant Objects" in Robert
     * Simmons, Jr's <i>Hardcore Java<i> (O'Reilly, 2004), page 170.
     */
    /*
     * In GTK, enums are frequently registered as <code>GEnumValues</code>
     * with <code>g_enum_register_static()</code>. This, however, is not done
     * consistently, and cannot be relied upon.
     */
    final String nickname;

    /**
     * The order is ordinal, nickname so that the code completion comes first
     * and copying the name of the constant to the nickname becomes a trivial
     * exercise, as opposed to having to figure out what the nickname should
     * be before you've completed from the generated code.
     */
    protected Constant(int ordinal, String nickname) {
        this.ordinal = ordinal;
        this.nickname = nickname;

        Plumbing.registerConstant(this);
    }

    /**
     * Return the name of the Constant class and the name of the constant
     * itself. For example,
     * 
     * <pre>
     * WindowType.POPUP
     * </pre>
     * 
     * @since 4.0.1
     */
    public String toString() {
        return this.getClass().getSimpleName() + "." + nickname;
    }
}
