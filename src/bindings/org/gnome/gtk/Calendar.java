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
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 * 
 * TODO The methods implemented here are only to test flags management. Docs
 * need to be improved.
 */
public class Calendar extends Widget
{
    protected Calendar(long pointer) {
        super(pointer);
    }

    public Calendar() {
        super(GtkCalendar.createCalendar());
    }

    /**
     * Sets display options (whether to display the heading and the month
     * headings).
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
