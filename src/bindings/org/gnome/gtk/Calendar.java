/*
 * Calendar.java
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
 * Displays a monthly calendar which users can use to select a date.
 * 
 * @author Vreixo Formoso
 * @since 4.0.3
 */
/*
 * This was initially bound to expose and test Flags behaviour. It still lacks
 * many necessary methods before it will become useable.
 */
public class Calendar extends Widget
{
    protected Calendar(long pointer) {
        super(pointer);
    }

    /**
     * Creates a new Calendar instance.
     */
    public Calendar() {
        super(GtkCalendar.createCalendar());
    }

    /**
     * Sets the various options which control the details of how this Calendar
     * is to be displayed. See the various constants in
     * {@link CalendarDisplayOptions} for details.
     */
    public void setDisplayOptions(CalendarDisplayOptions flags) {
        GtkCalendar.setDisplayOptions(this, flags);
    }

    /**
     * Returns the Flags object describing the current options enabled in this
     * Calendar.
     */
    public CalendarDisplayOptions getDisplayOptions() {
        return GtkCalendar.getDisplayOptions(this);
    }
}
