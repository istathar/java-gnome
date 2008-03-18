/*
 * Expander.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008 Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * A Container that can hide its child. It can be in two states: one, called
 * expanded, where the child Widget is shown; and another where the child
 * Widget is hidden. In both states, it shows a control, usually a little
 * triangle, that the user can click to change between both states, i.e. to
 * alternatively show or hide the child.
 * 
 * <p>
 * This Container is useful for hiding advanced options from a Dialog, but at
 * the same time provide a way to let users access those options. You usually
 * specify a text label that is show near the little triangle, and that
 * contains a brief description of the contents of the hidden Widget.
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
     * Create a new Expander with the given label.
     */
    public Expander(String label) {
        // TODO what about GtkExpander.createExpanderWithMnemonic(label)?
        super(GtkExpander.createExpander(label));
    }

    /**
     * Set the state of this Expander.
     * 
     * @param expanded
     *            <code>true</code> to show the child Widget,
     *            <code>false</code> to hide it.
     */
    public void setExpanded(boolean expanded) {
        GtkExpander.setExpanded(this, expanded);
    }

    /**
     * Get the expanded state of this Expander, i.e., whether its child is
     * shown or hidden.
     */
    public boolean getExpanded() {
        return GtkExpander.getExpanded(this);
    }
}
