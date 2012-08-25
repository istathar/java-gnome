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

import org.freedesktop.cairo.FontOptions;
import org.gnome.glib.Object;

/**
 * The internal state used by Pango when rendering.
 * 
 * <p>
 * This is one of those cases where we are unfortunate that the class name
 * collides with the same name from another package and you could find
 * yourself using both in the same file. If you're working from a Layout and
 * need to use this, and given theses:
 * 
 * <pre>
 * import org.freedesktop.cairo.Context;
 * import org.freedesktop.cairo.FontOptions;
 * import org.gnome.pango.Layout;
 * import org.gnome.gtk.Widget;
 * </pre>
 * 
 * etc, you might be well off to do something like this in a
 * <code>Widget.Draw</code> signal handler:
 * 
 * <pre>
 * final Layout layout;
 * final FontOptions config;
 * 
 * layout = new Layout(cr);
 * config = new FontOptions();
 * ...
 * 
 * layout.getContext().setFontOptions(config);
 * </pre>
 * 
 * which avoids you having to use the fully qualified
 * <code>org.gnome.pango.Context</code> name when you call Layout's
 * <code>getContext()</code>.
 * 
 * <p>
 * <i>You ordinarily do not need to use this, although you might want to know
 * that every time a Pango Layout is created a fresh Pango Context is done
 * up.</i>
 * 
 * @since 4.0.10
 */
public class Context extends Object
{
    protected Context(long pointer) {
        super(pointer);
    }

    /**
     * Set the configuration options that will be used by Cairo when doing the
     * actual rendering of font glyphs. This is how you control things like
     * which hinting methods being used.
     * 
     * @since 4.0.10
     */
    public void setFontOptions(FontOptions options) {
        PangoContext.setFontOptions(this, options);
    }

    /**
     * Given a description, ask the back end to load the font which is the
     * closest match to the request.
     * 
     * <p>
     * This is the code path used by {@link Layout Layout}, so you can use
     * this find out what font has actually been selected for rendering.
     * 
     * @since 4.0.19
     */
    public Font loadFont(FontDescription desc) {
        return PangoContext.loadFont(this, desc);
    }
}
