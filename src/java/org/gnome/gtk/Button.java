/*
 * Button.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2006 Operational Dynamics
 */
package org.gnome.gtk;

/**
 * A Widget that creates a signal when clicked on.
 * 
 * Button can hold any just about any other Widget as its child (TODO which
 * means what? What limitations?). The most commonly used child is a Label, and
 * there are convenience methods to help you do just create a button with a
 * given label automatically.
 * 
 * @author Andrew Cowie
 */
public class Button extends Bin {

    protected Button(long pointer) {
        super(pointer);
    }

    /**
     * Create an "empty" button to use as a Container. You'll need to
     * {@link org.gnome.gtk.Container#add(Widget) add()} the Widget which will
     * be the Button's child.
     */
    public Button() {
        super(GtkButton.createButton());
    }

    /**
     * Create a button with a Label as its child. Simply specify the text you
     * want for the Label and a Button will be created accordingly. This is
     * quite a common case - in fact, we're generally more used to thinking of
     * Buttons as being Labels that you can press than as arbitrary Widget
     * Containers.
     * 
     * @param text
     *            the text you wish on the Label that will be created in the
     *            Button.
     */
    public Button(String text) {
        super(GtkButton.createButtonWithLabel(text));
    }

    public void setLabel(String label) {
        GtkButton.setLabel(this, label);
    }

    /**
     * 
     * @return the text on the Label in the Button, or
     *         <code>null<code> if the no arg constructor was used.
     */
    public String getLabel() {
        return GtkButton.getLabel(this);
    }

    public float getAlignmentX() {
        float[] x = new float[1];
        float[] y = new float[1];

        // GtkButton.getAlignment(this, x, y);

        return x[0];
    }

    /**
     * Event generated when a user presses and releases a button, causing it to
     * activate.
     */
    /*
     * We can't inherit this from anywhere, nor is there directly a prototype in
     * the generated code to "call", but you can look at the signature for
     * handleClicked to find out what the arguments need to be.
     */
    public interface CLICKED extends GtkButton.CLICKED {
        public void onClicked(Button source);
    }
    
    public interface DEPRESSED extends GtkButton.DEPRESSED {
        
    }
    
    /**
     * Hook up a handler to receive "clicked" events on this Button.
     * 
     * @param handler
     *            the Inteface implenting the callback to be executed when this
     *            signal is received.
     */
    public void connect(CLICKED handler) {
        GtkButton.connect(this, handler);
    }
    
    public void connect(DEPRESSED handler) {
        GtkButton.connect(this, handler);
    }
}
