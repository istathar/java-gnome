/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
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

import org.gnome.glib.Object;

/**
 * An abstract rendering-system-independent representation of a font, used by
 * Pango to manage font backends. While internal to Pango, it has various uses
 * when trying to manage font selections.
 * 
 * <p>
 * You get a Font object by calling Context's
 * {@link Context#loadFont(FontDescription) loadFont()}.
 * 
 * @author Andrew Cowie
 * @since 4.0.19
 */
public abstract class Font extends Object
{
    protected Font(long pointer) {
        super(pointer);
    }

    /**
     * Given a calculated Pango Font object, get a description of it. Size
     * will be in points.
     * 
     * @since 4.0.19
     */
    public FontDescription describe() {
        return PangoFont.describe(this);
    }

    /**
     * Given a calculated Pango Font object, get a description of it. Size
     * will be in device units.
     * 
     * @since 4.0.19
     */
    public FontDescription describeWithAbsoluteSize() {
        return PangoFont.describeWithAbsoluteSize(this);
    }
}

abstract class FcFont extends Font
{
    protected FcFont(long pointer) {
        super(pointer);
    }
}

/*
 * PangoCairoFcFont is what is actually getting passed back by loadFont(). Is
 * that always the case?
 */
final class CairoFcFont extends FcFont
{
    protected CairoFcFont(long pointer) {
        super(pointer);
    }
}
