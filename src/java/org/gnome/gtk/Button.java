/*
 * Button.java
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
 * A Widget that creates a signal when clicked on. Button can hold any just
 * about any other Widget as its child (TODO which means what? What
 * limitations?). The most commonly used child is a Label, and there are
 * convenience methods to help you just create a button with a given label
 * automatically.
 * 
 * @author Andrew Cowie
 */
public class Button extends Bin
{

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

    /**
     * Set the text showing in the Button. (This assumes you've got a Label in
     * the Button in the first place, ie, you created this with
     * {@link #Button(String)}).
     */
    public void setLabel(String text) {
        GtkButton.setLabel(this, text);
    }

    /**
     * Get the text showing on the Button.
     * 
     * @return the text of the Label, or <code>null</code> if the no-arg
     *         constructor was used and you've just got an arbitrary
     *         Widget-containing-Button, not the more usual Button-with-Label.
     */
    public String getLabel() {
        // return GtkButton.getLabel(this);
        return super.getPropertyString("label");
    }

    public float getAlignmentX() {
        float[] x = new float[1];
        float[] y = new float[1];

        // GtkButton.getAlignment(this, x, y);

        return x[0];
    }

    /**
     * Set the "relief" style used to determine how the edges of this Button
     * will be decorated. The default is {@link ReliefStyle#NORMAL NORMAL}
     * which results in a Button just as you would expect, screaming out to be
     * pressed! There are two other variations, see {@link ReliefStyle} for
     * details.
     */
    public void setRelief(ReliefStyle style) {
        GtkButton.setRelief(this, style);
    }

    /**
     * Get the relief style in used around this Button.
     */
    public ReliefStyle getRelief() {
        // GtkButton.getRelief(this);
        return (ReliefStyle) getPropertyEnum("relief");
    }

    /**
     * Event generated when a user presses and releases a button, causing it
     * to activate.
     * 
     * <p>
     * <i>When the mouse is used to click on a Button this signal will be
     * emitted, but only if the cursor is still in the Button when the mouse
     * button is released. You're probably used to this behaviour without
     * realizing it.</i>
     */
    public interface CLICKED extends GtkButton.CLICKED
    {
        public void onClicked(Button source);
    }

    /**
     * Hook up a handler to receive "clicked" events on this Button. A typical
     * example of how this is used is as follows:
     * 
     * <pre>
     * Button b;
     * 
     * b.connect(new Button.CLICKED() {
     *     public void onClicked(Button source) {
     *         // do something!
     *     }
     * }
     * </pre>
     * 
     * <p>
     * You can of course create a subclass of Button.CLICKED and then use
     * instances of it if you highly complicated algorithms to implement.
     * <p>
     * If you implement Button.CLICKED in the class you're currently working
     * on, then you use a technique called "self-delegation" which can
     * sometimes work well;
     * 
     * <pre>
     * b.connect(this);
     * </pre>
     */
    public void connect(CLICKED handler) {
        GtkButton.connect(this, handler);
    }

    /*
     * ACTIVATE: "Applications should never connect to this signal, but use
     * the 'clicked' signal."
     */
    /*
     * ENTERED, PRESSED, etc: "deprecated"
     */
}
