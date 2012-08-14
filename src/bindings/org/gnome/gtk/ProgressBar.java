/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd and Others
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
 * @author Bruno Dusausoy
 * @since 4.0.3
 */
public class ProgressBar extends Widget implements Orientable
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
     * Getting the text superimposed on the ProgressBar itself.
     * 
     * @since 4.0.10
     */
    public String getText() {
        return GtkProgressBar.getText(this);
    }

    /**
     * Sets whether the progressbar will show text superimposed over the bar.
     * To make a progress bar that is styled and sized suitably for containing
     * text you should call the {@link setText(String)} method with an empty
     * String.
     * 
     * @since 4.1.2
     */
    public void setShowText(boolean value) {
        GtkProgressBar.setShowText(this, value);
    }

    /**
     * Tells whether or not the progress bar is able to show text.
     * 
     * @since 4.1.2
     */
    public boolean showsText() {
        return GtkProgressBar.getShowText(this);
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
        if ((fraction < 0.0) || (fraction > 1.0)) {
            throw new IllegalArgumentException("fraction must be between 0.0 and 1.0, inclusive.");
        }
        GtkProgressBar.setFraction(this, fraction);
    }

    /**
     * Causes the ProgressBar to enter &quot;activity mode&quot;, used to
     * indicate that the application is making progress but in a way that
     * can't be strictly quantized.
     * 
     * <p>
     * An example of this is web: most URLs addressable via
     * <code>http://</code> are files of known size, and so when downloading
     * them, a web browser can report exactly what percentage has been
     * downloaded relative to the file size that was psssed in the initial
     * HTTP header. Dynamic web pages, on the other hand (ie, your average PHP
     * or JSP script), do not have a known size at the time the HTTP headers
     * are sent, and so the web browser doesn't know ahead of time how many
     * bytes are on the way. In this scenario, all the application can do is
     * "pulse" the ProgressBar back and forth to indicate that traffic is
     * continuing to arrive but that the percentage complete is not known, and
     * that is what this method is for.
     * 
     * <p>
     * Each time this method is invoked, the a little block inside the
     * ProgressBar is moved a small amount. You should therefore call this
     * method fairly frequently (ie with reasonably small time intervals) to
     * cause the effect of the block moving back and forward along the
     * ProgressBar.
     * 
     * @since 4.0.7
     */
    public void pulse() {
        GtkProgressBar.pulse(this);
    }

    public void setOrientation(Orientation orientation) {
        GtkOrientable.setOrientation(this, orientation);
    }

    public Orientation getOrientation() {
        return GtkOrientable.getOrientation(this);
    }

    /**
     * Set the progress bar pulse step. That means the amount of progress to
     * perform each time {@link ProgressBar#pulse() pulse()}; is called.
     * 
     * @param fraction
     *            a value between 0.0 and 1.0
     * @throws IllegalArgumentException
     *             If fraction is greater than 1.0 or less than 0.0
     * @since 4.0.10
     */
    public void setPulseStep(double fraction) {
        if ((fraction < 0.0) || (fraction > 1.0)) {
            throw new IllegalArgumentException("fraction must be between 0.0 and 1.0, inclusive.");
        }
        GtkProgressBar.setPulseStep(this, fraction);
    }

    /**
     * Get the current progress bar pulse step
     * 
     * @since 4.0.10
     */
    public double getPulseStep() {
        return GtkProgressBar.getPulseStep(this);
    }

    /**
     * Get the current progress bar fraction
     * 
     * @since 4.0.10
     */
    public double getFraction() {
        return GtkProgressBar.getFraction(this);
    }
}
