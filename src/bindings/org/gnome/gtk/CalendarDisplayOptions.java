/*
 * CalendarDisplayOptions.java
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

import org.freedesktop.bindings.Flag;

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
public final class CalendarDisplayOptions extends Flag
{
    private CalendarDisplayOptions(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    public final static CalendarDisplayOptions SHOW_HEADING = new CalendarDisplayOptions(
            GtkCalendarDisplayOptions.SHOW_HEADING, "SHOW_HEADING");

    public final static CalendarDisplayOptions SHOW_DAY_NAMES = new CalendarDisplayOptions(
            GtkCalendarDisplayOptions.SHOW_DAY_NAMES, "SHOW_DAY_NAMES");

    public final static CalendarDisplayOptions NO_MONTH_CHANGE = new CalendarDisplayOptions(
            GtkCalendarDisplayOptions.NO_MONTH_CHANGE, "NO_MONTH_CHANGE");

    public final static CalendarDisplayOptions SHOW_WEEK_NUMBERS = new CalendarDisplayOptions(
            GtkCalendarDisplayOptions.SHOW_WEEK_NUMBERS, "SHOW_WEEK_NUMBERS");

    public final static CalendarDisplayOptions WEEK_START_MONDAY = new CalendarDisplayOptions(
            GtkCalendarDisplayOptions.WEEK_START_MONDAY, "WEEK_START_MONDAY");

    public static CalendarDisplayOptions or(CalendarDisplayOptions ws1, CalendarDisplayOptions ws2) {
        return (CalendarDisplayOptions) Flag.orTwoFlags(ws1, ws2);
    }

    public boolean contains(CalendarDisplayOptions ws) {
        return andTwoFlags(this, ws);
    }

}
