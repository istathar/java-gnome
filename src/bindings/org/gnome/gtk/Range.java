/*
 * Range.java
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

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
public class Range extends Widget
{
    protected Range(long pointer) {
        super(pointer);
    }

    /**
     * Retreive the value currently indicated by this Range instance.
     * 
     * @since 4.0.6
     */
    public double getValue() {
        return GtkRange.getValue(this);
    }

    /**
     * Change the value showingin the Range. As you would expect, the
     * <code>VALUE_CHANGED</code> signal will be emitted if the new value is
     * different from the present setting.
     * 
     * @since 4.0.6
     */
    public void setValue(double value) {
        GtkRange.setValue(this, value);
    }

    /**
     * The value showing in the Range instance has changed.
     * 
     * @author Andrew Cowie
     * @since 4.0.6
     */
    public interface VALUE_CHANGED extends GtkRange.VALUE_CHANGED
    {
        public void onValueChanged(Range source);
    }

    /**
     * Connect a <code>VALUE_CHANGED</code> handler to this Range instance.
     * 
     * @since 4.0.6
     */
    public void connect(Range.VALUE_CHANGED handler) {
        GtkRange.connect(this, handler);
    }
}
