/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd
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
package org.gnome.pango;

/**
 * An Attribute that modifies the style of the text it is applied to. See
 * {@link Style}.
 * 
 * @author Andrew Cowie
 * @since 4.0.10
 */
public final class StyleAttribute extends Attribute
{
    /**
     * Create a StyleAttribute. {@link Style#ITALIC ITALIC} is usually the
     * Style people want since <code>NORMAL</code> is the default.
     * 
     * <p>
     * This is equivalent to using FontDescription's
     * {@link FontDescription#setStyle(Style) setStyle()} when requesting
     * fonts and TextTag's {@link org.gnome.gtk.TextTag#setStyle(Style)
     * setStyle()} when formatting text in a TextBuffer.
     * 
     * <p>
     * <i>The StyleAttribute is actually the mechanism that underlies both
     * properties.</i>
     * 
     * 
     * @since 4.0.10
     */
    public StyleAttribute(Style style) {
        super(PangoAttribute.createAttributeStyle(style));
    }
}
