/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd and Others
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
import org.gnome.glib.Object;

/**
 * Object that contains the styling information affecting a widget. The
 * StyleContext associated to a widget can be retrieved by using the Widget
 * {@link Widget#getStyleContext() getStyleContext()} method.
 * 
 * @author Guillaume Mazoyer
 * @since 4.1.2
 */
public class StyleContext extends Object
{
    protected StyleContext(long pointer) {
        super(pointer);
    }

    /**
     * Returns the {@link Screen} to which this StyleContext is attached.
     * 
     * @since 4.1.2
     */
    public Screen getScreen() {
        return GtkStyleContext.getScreen(this);
    }

    /**
     * Sets the {@link Screen} to which this StyleContext has to be attached.
     * 
     * @since 4.1.2
     */
    public void setScreen(Screen screen) {
        GtkStyleContext.setScreen(this, screen);
    }

    /**
     * Returns the {@link StateFlags} used when rendering.
     * 
     * @since 4.1.2
     */
    public StateFlags getState() {
        return GtkStyleContext.getState(this);
    }

    /**
     * Sets the {@link StateFlags} to be used when rendering.
     * 
     * @since 4.1.2
     */
    public void setState(StateFlags flags) {
        GtkStyleContext.setState(this, flags);
    }

    /**
     * Restores StyleContext state to a previous saved stage. The saved state
     * is created when calling {@link #save()}.
     * 
     * @since 4.1.2
     */
    public void restore() {
        GtkStyleContext.restore(this);
    }

    /**
     * Saves the StyleContext state, so all modifications done through
     * {@link #addClass(StyleClass)}, {@link #removeClass(StyleClass)},
     * {@link #addRegion(StyleRegion, RegionFlags)}, or
     * {@link #removeRegion(StyleRegion)} can be reverted in one go through
     * {@link #restore()}.
     * 
     * @since 4.1.2
     */
    public void save() {
        GtkStyleContext.save(this);
    }

    /**
     * Adds a {@link StyleClass} to StyleContext so render functions will make
     * use of this new class for styling.
     * 
     * @since 4.1.2
     */
    public void addClass(StyleClass value) {
        GtkStyleContext.addClass(this, value.getStyleName());
    }

    /**
     * Removes a {@link StyleClass} from the StyleContext.
     * 
     * @since 4.1.2
     */
    public void removeClass(StyleClass value) {
        GtkStyleContext.removeClass(this, value.getStyleName());
    }

    /**
     * Returns <code>true</code> if StyleContext currently has defined the
     * given {@link StyleClass}.
     * 
     * @since 4.1.2
     */
    public boolean hasClass(StyleClass value) {
        return GtkStyleContext.hasClass(this, value.getStyleName());
    }

    /**
     * Returns a string array that list the classes used by this context.
     * 
     * @since 4.1.2
     */
    public String[] listClasses() {
        return GtkStyleContextOverride.getClasses(this);
    }

    /**
     * Adds a {@link StyleRegion} to StyleContext, so render functions will
     * make use of this new region for styling.
     * 
     * @since 4.1.2
     */
    public void addRegion(StyleRegion region, RegionFlags flags) {
        GtkStyleContext.addRegion(this, region.getStyleName(), flags);
    }

    /**
     * Adds a global style provider to <code>screen</code>, which will be used in style
     * construction for all StyleContexts under screen.
     * <br/><br/>
     * GTK+ uses this to make styling information from {@link Settings} available.
     * <br/><br/>
     * Note: If both priorities are the same, A {@link StyleProvider} added through 
     * {@link #addProvider(StyleProvider, int)} takes precedence over another added 
     * through this function.
     * 
     * @param priority the priority of the style provider. The lower it is, the earlier it 
     * will be used in the style construction. Typically this will be in the range between 
     * {@link StyleProvider#PRIORITY_FALLBACK} and {@link StyleProvider#PRIORITY_USER}
     * 
     * @since 4.1.4
     */
    public static void addProviderForScreen(Screen screen, StyleProvider provider, int priority) {
        GtkStyleContext.addProviderForScreen(screen, provider, priority);
    }
    
    /**
     * Adds a style provider to StyleContext, to be used in style construction.
     * Note that a style provider added by this function only affects the style of 
     * the widget to which context belongs. If you want to affect the style of 
     * all widgets, use {@link #addProviderForScreen(Screen, StyleProvider, int)}
     * <br/><br/>
     * Note: If both priorities are the same, a {@link StyleProvider} added through
     * this function takes precedence over another added through 
     * {@link #addProviderForScreen(Screen, StyleProvider, int)}
     * 
     * @param priority the priority of the style provider. The lower it is, the earlier it 
     * will be used in the style construction. Typically this will be in the range between 
     * {@link StyleProvider#PRIORITY_FALLBACK} and {@link StyleProvider#PRIORITY_USER}
     * 
     * @since 4.1.4
     */
    public void addProvider(StyleProvider provider, int priority) {
        GtkStyleContext.addProvider(this, provider, priority);
    }

    /**
     * Removes a {@link StyleRegion} from the StyleContext.
     * 
     * @since 4.1.2
     */
    public void removeRegion(StyleRegion region) {
        GtkStyleContext.removeRegion(this, region.getStyleName());
    }

    /**
     * Returns a non-<code>null</code> value if StyleContext currently has
     * defined the given {@link StyleRegion}. The returned value corresponds
     * to the {@link RegionFlags}.
     * 
     * @since 4.1.2
     */
    public RegionFlags hasRegion(StyleRegion region) {
        return GtkStyleContextOverride.hasRegion(this, region.getStyleName());
    }

    /**
     * Returns a string array that list the regions used by this context.
     * 
     * @since 4.1.2
     */
    public String[] listRegions() {
        return GtkStyleContextOverride.getRegions(this);
    }

    /**
     * Return the sides where rendered elements connect visually with others.
     * 
     * @since 4.1.2
     */
    public JunctionSides getJunctionSides() {
        return GtkStyleContext.getJunctionSides(this);
    }

    /**
     * Sets the sides where rendered elements will visually connect with other
     * visual elements. Container widgets are expected to do all this work by
     * themselves so calling this method manually shouldn't be necessary.
     * 
     * @since 4.1.2
     */
    public void setJunctionSides(JunctionSides sides) {
        GtkStyleContext.setJunctionSides(this, sides);
    }

    /**
     * Returns the widget direction used for rendering.
     * 
     * @since 4.1.2
     */
    public TextDirection getDirection() {
        return GtkStyleContext.getDirection(this);
    }

    /**
     * Sets the reading direction of a rendered widget. You generally do not
     * have to call this method but it can be useful for test purposes.
     * 
     * @since 4.1.2
     */
    public void setDirection(TextDirection direction) {
        GtkStyleContext.setDirection(this, direction);
    }
}
