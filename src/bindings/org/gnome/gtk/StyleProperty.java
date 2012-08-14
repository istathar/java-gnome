/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2011 Operational Dynamics Consulting, Pty Ltd and Others
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
package org.gnome.gtk;

/**
 * Styles that help to manipulate the property value of the
 * {@link StyleContext} of a widget inside GTK.
 * 
 * @author Guillaume Mazoyer
 * @since 4.1.2
 */
public final class StyleProperty extends Style
{
    protected StyleProperty(String name) {
        super(name);
    }

    /**
     * A property holding the background color of rendered elements.
     */
    public static final StyleProperty BACKGROUND_COLOR = new StyleProperty("background-color");

    /**
     * A property holding the foreground color of rendered elements.
     */
    public static final StyleProperty COLOR = new StyleProperty("color");

    /**
     * A property holding the font properties used when rendering text as a
     * PangoFontDescription.
     */
    public static final StyleProperty FONT = new StyleProperty("font");

    /**
     * A property holding the rendered element's margin as a GtkBorder. The
     * margin is defined as the spacing between the border of the element and
     * its surrounding elements.
     */
    public static final StyleProperty MARGIN = new StyleProperty("margin");

    /**
     * A property holding the rendered element's padding as a GtkBorder. The
     * padding is defined as the spacing between the inner part of the element
     * border and its child.
     */
    public static final StyleProperty PADDING = new StyleProperty("padding");

    /**
     * A property holding the rendered element's border width in pixels as a
     * GtkBorder.
     */
    public static final StyleProperty BORDER_WIDTH = new StyleProperty("border-width");

    /**
     * A property holding the rendered element's border radius in pixels.
     */
    public static final StyleProperty BORDER_RADIUS = new StyleProperty("border-radius");

    /**
     * A property holding the element's border style..
     */
    public static final StyleProperty BORDER_STYLE = new StyleProperty("border-style");

    /**
     * A property holding the element's border color.
     */
    public static final StyleProperty BORDER_COLOR = new StyleProperty("border-color");

    /**
     * A property holding the element's background.
     */
    public static final StyleProperty BACKGROUND_IMAGE = new StyleProperty("background-image");
}
