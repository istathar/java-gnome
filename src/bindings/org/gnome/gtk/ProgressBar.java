/*
 * ProgressBar.java
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
 * A Widget that displays the progress of a task as a visual bar. This is
 * principally used to give feedback to a user that things are actually
 * happening when some computationally intensive or long running task is
 * taking place. You can use a ProgressBar in two ways. Usually you know what
 * fraction of a task is complete and that is what you want to display as a
 * steadily increasing bar. On the other hand, if the total duration of the
 * task is unknown or unpredictable, you can still indicate progress is being
 * made by having the bar "pulse" back and forth.
 * 
 * @author Andrew Cowie
 * @since 4.0.3
 */
public class ProgressBar extends Widget
{

    protected ProgressBar(long pointer) {
        super(pointer);
    }

    /**
     * Instantiate a new ProgressBar.
     * 
     * @since 4.0.3
     */
    public ProgressBar() {
        super(GtkProgressBar.createProgressBar());
    }

    /**
     * Cause text to appear superimposed on the ProgressBar itself.
     * 
     * @param text
     *            Use <code>null</code> if you want to clear any text there.
     * 
     * @since 4.0.3
     */
    public void setText(String text) {
        GtkProgressBar.setText(this, text);
    }

    /**
     * Set the fraction of the ProgressBar that shows as completed or
     * "filled-in".
     * 
     * @param fraction
     *            a value between 0.0 (not started) and 1.0 (fully complete)
     * 
     * @since 4.0.3
     */
    public void setFraction(double fraction) {
        GtkProgressBar.setFraction(this, fraction);
    }
}
