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
 * TODO there are several methods still not implemented
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
     * Sets display options (whether to display the heading and the month
     * headings, for example).
     * 
     * @see CalendarDisplayOptions
     */
    public void setDisplayOptions(CalendarDisplayOptions flags) {
        GtkCalendar.setDisplayOptions(this, flags);
    }

    /**
     * Returns the current display options.
     */
    public CalendarDisplayOptions getDisplayOptions() {
        return GtkCalendar.getDisplayOptions(this);
    }
}
