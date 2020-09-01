/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2020 java-gnome contributors
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

import org.gnome.gdk.Screen;
import org.gnome.glib.GlibException;
import org.gnome.glib.Object;

/**
 * CSS-like styling for widgets
 * <br/><br/>
 * CssProvider is an object implementing the {@link StyleProvider} interface. 
 * It is able to parse CSS-like input in order to style widgets.
 * <br/><br/>
 * An application can make GTK+ parse a specific CSS style sheet by calling 
 * {@link #loadFromPath(String)} or {@link #loadFromResource(String)} and 
 * adding the provider with {@link StyleContext#addProvider(StyleProvider, int)} 
 * or {@link StyleContext#addProviderForScreen(Screen, StyleProvider, int)}
 * 
 * @author Patrick Plenefisch
 * @since 4.1.4
 */
public class CssProvider extends Object implements StyleProvider
{
    protected CssProvider(long pointer) {
        super(pointer);
    }

    /**
     * Creates a new CssProvider
     * 
     * @since 4.1.4
     */
    public CssProvider() {
        super(GtkCssProvider.createCssProvider());
    }

    /**
     * Loads a theme from the usual theme paths.
     * 
     * @since 4.1.4
     */
    public static CssProvider getNamed(String name, String variant) {
        return GtkCssProvider.getNamed(name, variant);
    }
    /**
     * Loads the data contained in the resource at resource_path into the 
     * CssProvider, clearing any previously loaded information.
     * 
     * @since 4.1.4
     */
    public void loadFromResource(String resource_path) {
        GtkCssProvider.loadFromResource(this, resource_path);
    }
    
    /**
     * Loads data into css_provider , and by doing so clears any previously loaded information.
     * 
     * @since 4.1.4
     */
    public boolean loadFromData(String data) {
        try {
	    return GtkCssProvider.loadFromData(this, data, data.length());
        } catch (GlibException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    /**
     * Loads the data contained in path into css_provider,
     * making it clear any previously loaded information.
     * 
     * @since 4.1.4
     */
    public boolean loadFromPath(String path) {
    	try {
	    return GtkCssProvider.loadFromPath(this, path);
        } catch (GlibException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Converts the provider into a string representation in CSS format.
     * 
     * @since 4.1.4
     */
    public String toString() {
        return GtkCssProvider.toString(this);
    }
}
