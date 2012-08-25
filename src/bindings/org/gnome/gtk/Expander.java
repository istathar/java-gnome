/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2008      Vreixo Formoso
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
 * A Container that can hide its child. It can be in two states: one, called
 * "expanded", where the child Widget is shown; and another where the child
 * Widget is "hidden". In both states, it shows a control, usually a little
 * triangle, that the user can click to change between both states, i.e. to
 * alternatively show or hide the child.
 * 
 * <p>
 * This Container is useful for hiding advanced options from a Dialog, while
 * still providing a way to let users access those options. You usually
 * specify a text label that is show near the expander triangle that contains
 * a brief description of the hidden elements.
 * 
 * <p>
 * To add the child Widget, you should use the Container
 * {@link Container#add(Widget) add()} method, as follows:
 * 
 * <pre>
 * Widget advancedOptionsWidget;
 * Expander advancedOptions;
 * 
 * // create a Widget with some options we want to hide 
 * advancedOptionsWidget = ... 
 * advancedOptions = new Expander(&quot;Advanced Options&quot;);
 * advancedOptions.add(advancedOptionsWidget);
 * </pre>
 * 
 * Note that the child Widget is hidden by default, you can use the
 * {@link #setExpanded(boolean) setExpanded()} method to show it from the
 * beginning.
 * 
 * @author Vreixo Formoso
 * @since 4.0.7
 */
public class Expander extends Bin
{
    protected Expander(long pointer) {
        super(pointer);
    }

    /**
     * Create a new Expander with the given label. Underscore characters will
     * be interpreted as marking mnemonic keys.
     * 
     * @since 4.0.7
     */
    public Expander(String label) {
        super(GtkExpander.createExpanderWithMnemonic(label));
    }

    /**
     * Set the state of this Expander.
     * 
     * @param expanded
     *            <code>true</code> to show the child Widget,
     *            <code>false</code> to hide it.
     * @since 4.0.7
     */
    public void setExpanded(boolean expanded) {
        GtkExpander.setExpanded(this, expanded);
    }

    /**
     * Get the expanded state of this Expander, i.e., whether its child is
     * shown or hidden.
     * 
     * @since 4.0.7
     */
    public boolean getExpanded() {
        return GtkExpander.getExpanded(this);
    }
}
