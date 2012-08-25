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
package org.gnome.gtk;

/**
 * A slider control which allows the user to manipulate a numeric value. <img
 * src="HScale.png" class="snapshot"> As with many other Widget hierarchies in
 * GTK, there is a horizontal ({@link HScale}) and vertical ({@link VScale})
 * implementation for you to choose from.
 * 
 * 
 * <p>
 * <img src="VScale.png" class="snapshot" style="clear: right;"> The default
 * position for the value to be displayed is {@link PositionType#TOP TOP}
 * which may not be quite what you want. Use
 * {@link Scale#setValuePosition(PositionType) setValuePosition()} to change
 * it.
 * 
 * <p>
 * Otherwise, most of the useful methods (notably those relating to the value)
 * are inherited from the parent class, {@link Range}.
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public class Scale extends Range
{
    protected Scale(long pointer) {
        super(pointer);
    }

    /**
     * Construct a Scale of the given orientation and supplying an Adjustment.
     * See also {@link HScale} and {@link VScale}.
     * 
     * @since 4.1.1
     */
    public Scale(Orientation orientation, Adjustment adjustment) {
        super(GtkScale.createScale(orientation, adjustment));
    }

    /**
     * Specify the number of decimal places that will be shown in the value.
     * This also rounds the value so that when it is retrieved it will match
     * what is displayed.
     * 
     * @since 4.0.6
     */
    public void setDigits(int places) {
        GtkScale.setDigits(this, places);
    }

    /**
     * Specify where the value will be drawn. {@link PositionType#TOP TOP} is
     * the default.
     * 
     * @since 4.0.6
     */
    public void setValuePosition(PositionType position) {
        GtkScale.setValuePos(this, position);
    }

    /**
     * Specify if the text displaying the value will be shown. The default is
     * <code>true</code>.
     * 
     * @since 4.0.17
     */
    public void setDrawValue(boolean draw) {
        GtkScale.setDrawValue(this, false);
    }

    /**
     * Is the Scale presently displaying text indicating its value?
     * 
     * @since 4.0.17
     */
    public boolean getDrawValue() {
        return GtkScale.getDrawValue(this);
    }
}
