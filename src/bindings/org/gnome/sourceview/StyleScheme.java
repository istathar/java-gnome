/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2012 Operational Dynamics Consulting, Pty Ltd and Others
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
package org.gnome.sourceview;

import org.gnome.glib.Object;

/**
 * StyleScheme contains all the text styles to be used in {@link SourceView}
 * and {@link SourceBuffer}. For instance, it contains text styles for syntax
 * highlighting, it may contain foreground and background color for
 * non-highlighted text, color for the line numbers, etc. used.
 * 
 * @author Georgios Migdos
 * @since 4.1.2
 */
public class StyleScheme extends Object
{
    private StyleScheme(long pointer) {
        super(pointer);
    }

    /**
     * Return this StyleScheme's unique identifier.
     * 
     * @since 4.1.2
     */
    public String getID() {
        return GtkSourceStyleScheme.getId(this);
    }

    /**
     * Return this StyleScheme's name (e.g. what would be presented in a
     * preferences dialog).
     * 
     * @since 4.1.2
     */
    public String getName() {
        return GtkSourceStyleScheme.getName(this);
    }

    /**
     * Return this StyleScheme's description.
     * 
     * @since 4.1.2
     */
    public String getDescription() {
        return GtkSourceStyleScheme.getDescription(this);
    }

    /**
     * Return a String array containing information about this scheme's
     * authors or <code>null</code> if no author is specified by the style
     * scheme.
     * 
     * @since 4.1.2
     */
    public String[] getAuthors() {
        return GtkSourceStyleScheme.getAuthors(this);
    }

    /**
     * Return a file name if the scheme was created parsing a style scheme
     * file or <code>null</code>.
     * 
     * @since 4.1.2
     */
    public String getFilename() {
        return GtkSourceStyleScheme.getFilename(this);
    }

    /**
     * Return the {@link Style} which corresponds to styleID in the scheme, or
     * <code>null</code> when no style with this name exists.
     * 
     * @since 4.1.2
     */
    public Style getStyle(String styleID) {
        return GtkSourceStyleScheme.getStyle(this, styleID);
    }
}
