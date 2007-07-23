/*
 * Frame.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * A decorative border frame with an optional label. This is typically used to
 * surround a group of widgets with a a visual hint grouping them together.
 * 
 * <p>
 * These are frequently over-used and so using Frames is actually highly
 * discouraged: in general Widgets already have sufficient decoration and
 * adding extra horizontal and vertical lines distracts the user and actually
 * makes it <b>harder</b> to distinguish the Widgets from one another. If you
 * need to group widgets, do so with white space or use a technique like
 * applying {@link SizeGroup}s to give a set of Widgets a consistent
 * appearance.
 * 
 * <p>
 * Note that the "label" can be a full Widget in its own right; if you use the
 * methods which take a text string they will transparently create a Label for
 * you containing that text and using it on the edge of the Frame.
 * 
 * @author Sebastian Mancke
 * @author Andrew Cowie
 * @since 4.0.3
 */
public class Frame extends Bin
{
    protected Frame(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new Frame with a simple text label.
     */
    public Frame(String label) {
        super(GtkFrame.createFrame(label));
    }

    /**
     * Set the text label for the Frame (assuming you created it with a Label
     * in the first place).
     */
    public void setLabel(String label) {
        GtkFrame.setLabel(this, label);
    }

    /**
     * Returns the text label from the frame's edge.
     */
    public String getLabel() {
        return GtkFrame.getLabel(this);
    }

    /**
     * Set a Widget to be the "label" for the Frame.
     */
    public void setLabel(Widget label) {
        GtkFrame.setLabelWidget(this, label);
    }

    /**
     * Returns the Widget being used as the "label" of the Frame.
     */
    public Widget getLabelWidget() {
        return GtkFrame.getLabelWidget(this);
    }
}
