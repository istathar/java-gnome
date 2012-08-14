/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
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

import org.freedesktop.bindings.Flag;

/**
 * These options are used to modify the display and behaviour of a
 * {@link Calendar} Widget.
 * 
 * <p>
 * You can specify one or several of these using the method
 * {@link Calendar#setDisplayOptions(CalendarDisplayOptions)
 * setDisplayOptions()} in Calendar.
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
     * When set the Calendar will display a heading showing with the current
     * year and month.
     * 
     * <p>
     * So long as {@link #NO_MONTH_CHANGE} is not specified, the user can
     * change both the month and the year shown in the Calendar using little
     * arrows that will appear in the header.
     */
    public final static CalendarDisplayOptions SHOW_HEADING = new CalendarDisplayOptions(
            GtkCalendarDisplayOptions.SHOW_HEADING, "SHOW_HEADING");

    /**
     * Whether to show the names of the days in the Calendar. The default is
     * for them not to appear.
     */
    public final static CalendarDisplayOptions SHOW_DAY_NAMES = new CalendarDisplayOptions(
            GtkCalendarDisplayOptions.SHOW_DAY_NAMES, "SHOW_DAY_NAMES");

    /**
     * Prevents the user from switching months (and years) with the Calendar
     * widget, forcing it to display only whichever month has been set by the
     * programmer.
     */
    public final static CalendarDisplayOptions NO_MONTH_CHANGE = new CalendarDisplayOptions(
            GtkCalendarDisplayOptions.NO_MONTH_CHANGE, "NO_MONTH_CHANGE");

    /**
     * Displays the week number (relative to the start of the current year)
     * down the left side of the Calendar.
     */
    public final static CalendarDisplayOptions SHOW_WEEK_NUMBERS = new CalendarDisplayOptions(
            GtkCalendarDisplayOptions.SHOW_WEEK_NUMBERS, "SHOW_WEEK_NUMBERS");

    /**
     * Returns a CalendarDisplayOptions object that contains all options
     * specified by the two given arguments. It can be thought as the logical
     * OR between the two Flags.
     */
    public static CalendarDisplayOptions or(CalendarDisplayOptions one, CalendarDisplayOptions two) {
        return (CalendarDisplayOptions) Flag.orTwoFlags(one, two);
    }
}
