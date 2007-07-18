/*
 * CalendarDisplayOptions.java
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

import org.freedesktop.bindings.Flag;

/**
 * These options are used to modify the display and behavior of a
 * {@link Calendar}.
 * 
 * <p>
 * You can specify one or several of these using the method
 * {@link Calendar#setDisplayOptions(CalendarDisplayOptions) setDisplayOptions}
 * in Calendar.
 * 
 * @author Vreixo Formoso
 * @since 4.0.3
 */
public final class CalendarDisplayOptions extends Flag
{
    private CalendarDisplayOptions(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * When setted, the Calendar will display a little heading with the year
     * and month currently shown.
     * 
     * <p>
     * If {@link #NO_MONTH_CHANGE} is not specified, the user can change both
     * the month and the year shown in the Calendar, using little arrows that
     * also appear in the header.
     */
    public final static CalendarDisplayOptions SHOW_HEADING = new CalendarDisplayOptions(
            GtkCalendarDisplayOptions.SHOW_HEADING, "SHOW_HEADING");

    /**
     * When setted, the name of the days is shown in the Calendar, otherwise
     * they don't appear.
     */
    public final static CalendarDisplayOptions SHOW_DAY_NAMES = new CalendarDisplayOptions(
            GtkCalendarDisplayOptions.SHOW_DAY_NAMES, "SHOW_DAY_NAMES");

    /**
     * Prevents the user from switching months (and years) with the Calendar.
     */
    public final static CalendarDisplayOptions NO_MONTH_CHANGE = new CalendarDisplayOptions(
            GtkCalendarDisplayOptions.NO_MONTH_CHANGE, "NO_MONTH_CHANGE");

    /**
     * If setted, displays each week numbers of the current year, down the
     * left side of the calendar.
     */
    public final static CalendarDisplayOptions SHOW_WEEK_NUMBERS = new CalendarDisplayOptions(
            GtkCalendarDisplayOptions.SHOW_WEEK_NUMBERS, "SHOW_WEEK_NUMBERS");

    /**
     * Creates a new flag object that contains all options especified at least
     * in one of the two given flags. It can be thought as the logical OR
     * between the two flags.
     */
    public static CalendarDisplayOptions or(CalendarDisplayOptions ws1, CalendarDisplayOptions ws2) {
        return (CalendarDisplayOptions) Flag.orTwoFlags(ws1, ws2);
    }

    /**
     * Check if this flag contains at least one of the options specified in
     * the given flag.
     */
    public boolean contains(CalendarDisplayOptions ws) {
        return andTwoFlags(this, ws);
    }

    /**
     * Creates a new flag object that contains all options especified in this
     * flag or in the given flag. This method can be used to or together
     * several flags in a elegant way:
     * 
     * <pre>
     * cal.setDisplayOptions(CalendarDisplayOptions.SHOW_HEADING
     *         .or(CalendarDisplayOptions.SHOW_WEEK_NUMBERS)
     *         .or(CalendarDisplayOptions.SHOW_DAY_NAMES));
     * 
     */
    public CalendarDisplayOptions or(CalendarDisplayOptions ws) {
        return or(this, ws);
    }
}
