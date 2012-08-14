/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2010 Operational Dynamics Consulting, Pty Ltd and Others
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
package org.freedesktop.icons;

/**
 * Named icons. These constants represent icons that should be available in
 * the current icon theme.
 * 
 * <p>
 * The named icon standard groups icons in different categories. There is a
 * subclass of Icon for each of these divisions: common {@link ActionIcon
 * action}s that can be taken by users, {@link ApplicationIcon applications},
 * main menu {@link CategoryIcon categoies}, {@link DeviceIcon devices} and
 * more importantly {@link MimeIcon MIME types}, {@link EmblemIcon emblems}
 * used to annotate other icons, the all important smiley {@link FaceIcon
 * faces}, XDG defined {@link PlaceIcon places}, and a list of common
 * {@link StateIcon states} for various application features.
 * 
 * <h2>Usage</h2>
 * 
 * <p>
 * These constants can be used to create an Image using the constructor
 * {@link org.gnome.gtk.Image#Image(Icon, org.gnome.gtk.IconSize) Image()}
 * taking an Icon:
 * 
 * <pre>
 * image = new Image(FaceIcon.FACE_SMILE);
 * </pre>
 * 
 * You can also use named icons in TreeViews; DataColumnIcon and a
 * {@link org.gnome.gtk.TreeModel#setValue(org.gnome.gtk.TreeIter, org.gnome.gtk.DataColumnIcon, Icon)
 * setValue()} taking an Icon are for specifying a named icon in a TreeModel
 * and CellRendererPixbuf has a
 * {@link org.gnome.gtk.CellRendererPixbuf#setIcon(org.gnome.gtk.DataColumnIcon)
 * setIcon()} where you can specify the column to pull the image from.
 * 
 * <p>
 * Ideally you should always get an icon back when requesting an Image with
 * one of these constants. There is a fallback mechanism if an image isn't
 * found, but that is somewhat vague and certainly up to the overall desktop
 * theme you're using. Worst case you'll get the "broken missing image" icon.
 * 
 * @author Guillaume Mazoyer
 * @author Andrew Cowie
 * @since 4.0.17
 * @see <a
 *      href="http://standards.freedesktop.org/icon-naming-spec/icon-naming-spec-latest.html">Icon
 *      Naming Specification</a>
 */
public class Icon
{
    /**
     * The name used to identify the icon in the theme.
     */
    final String name;

    /**
     * Construct a new Icon constant from a given string.
     * 
     * <p>
     * This is provided so that if we missed a name that you desperately need
     * a named icon for which isn't in the standardized named icon set, you
     * can subclass and create a constant of your own for it.
     * 
     * <p>
     * <i>If you find yourself doing this, we'd appreciate it if you'd point
     * out what it is about that name that you needed, and if appropriate
     * submit a patch adding it to one of the Icon subclasses instead.</i>
     * 
     * @since 4.0.17
     */
    protected Icon(String name) {
        this.name = name;
        Helper.registerIcon(this);
    }
}
