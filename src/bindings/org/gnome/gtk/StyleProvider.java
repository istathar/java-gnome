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

/**
 * Interface to provide style information to {@link StyleContext}
 * 
 * @see StyleContext
 * @see CssProvider
 * 
 * @author Patrick Plenefisch
 * @since 4.1.4
 */
public interface StyleProvider
{
    /**
     * The priority used for default style information that is used in the absence of themes.
     * <br/><br/>
     * Note that this is not very useful for providing default styling for custom style classes 
     * - themes are likely to override styling provided at this priority with 
     * catch-all <code>* {...}</code> rules.
     */
    public final static int PRIORITY_FALLBACK = 1;

    /**
     * The priority used for style information provided by themes.
     */
    public final static int  PRIORITY_THEME = 200;

    /**
     * The priority used for style information provided via {@link Settings}.
     * <br/><br/>
     * This priority is higher than {@link #PRIORITY_THEME} to let settings override themes.
     */
    public final static int  PRIORITY_SETTINGS = 400;

    /**
     * A priority that can be used when adding a StyleProvider for application-specific style information.
     */
    public final static int  PRIORITY_APPLICATION = 600;

    /**
     * The priority used for the style information from <code>XDG_CONFIG_HOME/gtk-3.0/gtk.css</code>.
     * <br/><br/>
     * You should not use priorities higher than this, to give the user the last word.
     */
    public final static int  PRIORITY_USER = 800;

}
