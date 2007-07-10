/*
 * Container.java
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * A Widget that contains at least one, and maybe more, other Widgets.
 * Container is the base of the GTK box packing model; all layouts are
 * composed of Widgets that are packed into other Widgets.
 * 
 * <p>
 * Containers fall into two major categories. They are either:
 * <dl>
 * <dt><b>decorators</b>
 * <dd>Containers which add something around a Widget. Examples include
 * {@link Button Button} (adding the push button aspect to a Widget) and
 * {@link Window Window} (the top level Widget which adds window decorations
 * via the window manager); <i>or</i>
 * <dt><b>layout</b>
 * <dd>Containers which control the layout of other Widgets within a user
 * interface. Examples include the simplistic {@link Fixed Fixed} layout tool,
 * the ubiquitous {@link Box Box} subclasses like VBox and HBox, and the more
 * advanced packing mechanisms like {@link Table}.
 * </dl>
 * 
 * <p>
 * TODO Add a human understandable discussion about the basics of <i>size
 * requests</i> and <i>size allocations</i>.
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 */
public abstract class Container extends Widget
{
    protected Container(long pointer) {
        super(pointer);
    }

    /**
     * Add a child Widget to this Container. This works for all the various
     * Container types, of course, but most offer more specialized packing
     * methods that allow you to control the positioning of the Widget being
     * added with greater finesse.
     * 
     * @since 4.0.0
     */
    public void add(Widget child) {
        GtkContainer.add(this, child);
    }

    /**
     * Remove a Widget from this Container.
     * 
     * <p>
     * <i>In native GTK, this often results in the destruction of the Widget.
     * In java-gnome, that will occur once the last Java reference goes out of
     * scope and a garbage collection run occurs. So you can, quite safely,
     * do:</i>
     * 
     * <pre>
     * box1.remove(button);
     * box2.add(button);
     * </pre>
     * 
     * <i>without worrying that that Button is going to evaporate out from
     * under you.</i>
     * 
     * @param child
     *            A child Widget that is already <i>in</i> the Container,
     *            right? If you try to remove a Widget that isn't, don't
     *            complain when you get all sorts of errors!
     * @since 4.0.2
     */
    /*
     * TODO, yes, we should probably check that the child is actually in the
     * Container first.
     */
    public void remove(Widget child) {
        GtkContainer.remove(this, child);
    }

    /**
     * Get the Widgets that are children of this Container, i.e., it retrieves
     * the Widgets previously added to this Container.
     * 
     * @return An array with the Container children, or <code>null</code> if
     *         the Container hasn't any child. Of course, you can cast the
     *         returned Widget objects to the appropriate Widget subtype. For
     *         example:
     * 
     * <pre>
     * box.add(button);
     * Widget[] children = box.getChildren();
     * //you know the unique child is a Button
     * Button b2 = (Button) children[0];
     * </pre>
     * 
     * @since 4.0.3
     */
    /*
     * TODO mmm, maybe an empty array is a better option that returning null
     */
    public Widget[] getChildren() {
        return GtkContainer.getChildren(this);
    }
}
