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
 * <p>
 * Note that like most things in GTK, most of the code that actually updates
 * the ProgressBar will not run until control is returned to the main loop.
 * Keep that in mind if you're wondering why the bar hasn't "updated".
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
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
     * @throws IllegalArgumentException
     *             If fraction is greater than 1.0 or less than 0.0
     * @since 4.0.3
     */
    public void setFraction(double fraction) {
        if (fraction < 0.0 || fraction > 1.0) {
            throw new IllegalArgumentException("fraction must be between 0.0 and 1.0, inclusive.");
        }
        GtkProgressBar.setFraction(this, fraction);
    }

    /**
     * Causes the ProgressBar to enter &quot;activity mode&quot;, a mode used
     * to indicate that the application is done some progress, but that can't
     * be quantized.
     * 
     * This is used when the application is executing a long running task
     * whose progress can't be quantized, i.e., we don't know how much percent
     * of the task has been done.
     * 
     * <p>
     * Each time this method is invoqued, the a little block inside the bar is
     * moved a bit. You should call this method at little time intervals to
     * cause the effect of the block moving back and foreward along the
     * ProgressBar.
     * 
     * @since 4.0.7
     */
    public void pulse() {
        GtkProgressBar.pulse(this);
    }
}
