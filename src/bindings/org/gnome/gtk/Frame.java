/*
 * Frame.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/** 
 * A bin with a decorative frame and optional label.
 * This widget may be used to surround a group of widgets with a border.
 * 
 * @author Sebastian Mancke
 * @since 4.0.3
 */
public class Frame extends Bin
{
    protected Frame(long pointer) {
        super(pointer);
    }
    
    /**
     * Constructs a new Frame with a label
     */
    public Frame(String label) {
        super(GtkFrame.createFrame(label));
    }

    /**
     * Sets a String label for the frame
     */
    public void setLabel(String label) {
        GtkFrame.setLabel(this, label);
    }

    /**
     * Returns the label of the frame
     */
    public String getLabel() {
        return GtkFrame.getLabel(this);
    }

    /**
     * Sets a widget as label for the frame
     */
    public void setLabel(Widget label) {
        GtkFrame.setLabelWidget(this, label);
    }

    /**
     * Returns the label widget of the frame
     */
    public Widget getLabelWidget() {
        return GtkFrame.getLabelWidget(this);
    }
}
