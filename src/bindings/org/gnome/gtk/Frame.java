/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
 * @author Vreixo Formoso
 * @since 4.0.3
 */
public class Frame extends Bin
{
    protected Frame(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new Frame with a simple text label.
     * 
     * @param label
     *            The desired label, or <code>null</code> if you don't want to
     *            use any label.
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
     * Returns the text from the frame's edge (assuming it's a Label Widget as
     * is usual practise).
     */
    public String getLabel() {
        return GtkFrame.getLabel(this);
    }

    /**
     * Set a Widget to be the "label" for the Frame.
     */
    public void setLabelWidget(Widget label) {
        GtkFrame.setLabelWidget(this, label);
    }

    /**
     * Returns the Widget being used as the "label" of the Frame.
     */
    public Widget getLabelWidget() {
        return GtkFrame.getLabelWidget(this);
    }

    /**
     * Set the ShadowType of the Frame, that will determine the appearance of
     * the outline. The default is {@link ShadowType#ETCHED_IN ETCHED_IN}.
     * 
     * @since 4.0.7
     */
    public void setShadowType(ShadowType type) {
        GtkFrame.setShadowType(this, type);
    }

    /**
     * Sets the alignment of the Frame's label.
     * 
     * @param xalign
     *            The position of the label along the top edge of the widget.
     *            A value of <code>0.0f</code> represents left alignment;
     *            <code>1.0f</code> represents right alignment. The default
     *            value is <code>0.0f</code>
     * @param yalign
     *            The vertical alignment of the label. A value of
     *            <code>0.0f</code> aligns under the frame; <code>1.0f</code>
     *            aligns above the frame. The default value is
     *            <code>0.5f</code>.
     * @since 4.0.7
     */
    public void setLabelAlign(float xalign, float yalign) {
        if ((xalign < 0.0) || (xalign > 1.0)) {
            throw new IllegalArgumentException("xalign must be between 0.0 and 1.0, inclusive.");
        }
        if ((yalign < 0.0) || (yalign > 1.0)) {
            throw new IllegalArgumentException("yalign must be between 0.0 and 1.0, inclusive.");
        }
        GtkFrame.setLabelAlign(this, xalign, yalign);
    }
}
