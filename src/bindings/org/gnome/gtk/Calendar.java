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

/**
 * Displays a monthly calendar with which users select a date. <img
 * src="Calendar.png" class="snapshot">
 * 
 * @author Vreixo Formoso
 * @author Andrew Cowie
 * @since 4.0.3
 */
/*
 * This was initially bound to expose and test Flags behaviour. It still lacks
 * many necessary methods before it will become usable.
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
     * is to be displayed. See the constants in {@link CalendarDisplayOptions}
     * for details.
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

    /**
     * Get the year of the date currently selected in this Calendar. See
     * companion methods {@link #getDateMonth() getDateMonth()} and
     * {@link #getDateDay() getDateDay()} for the other components of the
     * date.
     * 
     * @since 4.0.6
     */
    /*
     * I hate out parameters
     */
    public int getDateYear() {
        final int[] year;

        year = new int[1];

        GtkCalendar.getDate(this, year, null, null);

        return year[0];
    }

    /**
     * Get the month of the date currently selected in this Calendar. See
     * companion methods {@link #getDateYear() getDateYear()} and
     * {@link #getDateDay() getDateDay()} for the other components of the
     * date.
     * 
     * @return the numerical month, from <code>1</code> to <code>12</code>.
     * 
     * @since 4.0.6
     */
    public int getDateMonth() {
        final int[] month;

        month = new int[1];

        GtkCalendar.getDate(this, null, month, null);

        return month[0] + 1;
    }

    /**
     * Get the day of the date currently selected in this Calendar. See
     * companion methods {@link #getDateYear() getDateYear()} and
     * {@link #getDateMonth() getDateMonth()} for the other components of the
     * date.
     * 
     * @since 4.0.6
     */
    public int getDateDay() {
        final int[] day;
        day = new int[1];

        GtkCalendar.getDate(this, null, null, day);

        return day[0];
    }

    /**
     * Change the month (and year) showing on this Calendar. See
     * {@link #selectDay(int) selectDay()} to change the day that is selected.
     * 
     * @param month
     *            is in the range of <code>1</code> (January) to
     *            <code>12</code> (December).
     * @param year
     *            a four-digit year.
     * @since 4.0.6
     */
    /*
     * As it happens, this is for either historical reasons or reasons of
     * sheer obfuscation, GTK uses the range 0-11 for months but 1-31 for
     * days. That's all a bit silly. Present it in human terms in our API.
     */
    public void selectMonth(int month, int year) {
        if ((month < 1) || (month > 12)) {
            throw new IllegalArgumentException("The month must be in the range 1-12");
        }
        GtkCalendar.selectMonth(this, month - 1, year);
    }

    /**
     * Select the day showing on this Calendar. See
     * {@link #selectMonth(int, int) selectMonth()} to change the month and
     * year.
     * 
     * @param day
     *            should be in the range of <code>1</code> to <code>31</code>.
     *            Passing <code>0</code> will unselect the day.
     * @since 4.0.6
     */
    public void selectDay(int day) {
        if ((day < 0) || (day > 31)) {
            throw new IllegalArgumentException("The day must be in the range 1-31, or 0");
        }
        GtkCalendar.selectDay(this, day);
    }

    /**
     * Signal emitted when the user double clicks on one of the days showing
     * in the Calendar.
     * 
     * <p>
     * See the {@link Calendar.DaySelected} signal for the single click
     * equivalent.
     * 
     * <p>
     * <i>This is used in preference to manipulating individual button press
     * events as the Calendar Widget itself handles those events and
     * translates the positional co-ordinates relative the graphic displayed
     * into a date.</i>
     * 
     * @author Andrew Cowie
     * @since 4.0.6
     */
    public interface DaySelectedDoubleClick extends GtkCalendar.DaySelectedDoubleClickSignal
    {
        void onDaySelectedDoubleClick(Calendar source);
    }

    /**
     * Hook up a handler for <code>Calendar.DaySelectedDoubleClick</code>
     * signals.
     * 
     * @since 4.0.6
     */
    public void connect(Calendar.DaySelectedDoubleClick handler) {
        GtkCalendar.connect(this, handler, false);
    }

    /**
     * Signal emitted when the user selects on one of the days showing in the
     * Calendar.
     * 
     * <p>
     * See {@link Calendar.DaySelectedDoubleClick} for the signal emitted when
     * the user double clicks on a given day.
     * 
     * @author Andrew Cowie
     * @since 4.0.6
     */
    public interface DaySelected extends GtkCalendar.DaySelectedSignal
    {
        void onDaySelected(Calendar source);
    }

    /**
     * Hook up a handler for <code>Calendar.DaySelected</code> signals.
     * 
     * @since 4.0.6
     */
    public void connect(Calendar.DaySelected handler) {
        GtkCalendar.connect(this, handler, false);
    }
}
