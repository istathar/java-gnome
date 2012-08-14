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

import org.freedesktop.icons.Helper;
import org.freedesktop.icons.Icon;
import org.gnome.gdk.Event;
import org.gnome.gdk.Pixbuf;

/**
 * A data entry field allowing the user to input a single line of text. <img
 * src="Entry.png" class="snapshot" />
 * 
 * @author Sebastian Mancke
 * @author Andrew Cowie
 * @author Guillaume Mazoyer
 * @since 4.0.3
 */
public class Entry extends Widget implements Editable, CellEditable
{
    protected Entry(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new Entry
     * 
     * @since 4.0.3
     */
    public Entry() {
        this(GtkEntry.createEntry());
    }

    /**
     * Construct a new Entry, initialized with the specified text
     * 
     * @since 4.0.3
     */
    public Entry(String text) {
        this(GtkEntry.createEntry());
        setText(text);
    }

    /**
     * Replace the current contents of the Entry with the supplied text.
     * 
     * @since 4.0.3
     */
    public void setText(String text) {
        GtkEntry.setText(this, text);
    }

    /**
     * Get the text currently showing in the Entry. This is typically the most
     * significant method as it is the one you use to get the result of the
     * user's activity upon receiving a {@link Entry.Activate} signal.
     * 
     * @since 4.0.3
     */
    public String getText() {
        return GtkEntry.getText(this);
    }

    /**
     * Get the length of the text currently showing in the Entry.
     * 
     * @since 4.0.13
     */
    public char getTextLength() {
        return GtkEntry.getTextLength(this);
    }

    /**
     * Specify the maximum number of characters the user is allowed to enter.
     * Note that if the current text in the Entry is longer than the specified
     * length, the contents will be truncated!
     * 
     * @param max
     *            A value of <code>0</code> indicates no maximum length.
     * @since 4.0.3
     */
    public void setMaxLength(int max) {
        GtkEntry.setMaxLength(this, max);
    }

    /**
     * Returns the current maximum width, in characters, the text in the Entry
     * is allowed to be.
     * 
     * @since 4.0.3
     */
    public int getMaxLength() {
        return GtkEntry.getMaxLength(this);
    }

    /**
     * Set whether the text in the entry is visible or obscured. This is
     * typically used for password fields. Use <code>true</code> for showing
     * and <code>false</code> for hiding input characters.
     * 
     * <p>
     * When set to be not visible, characters entered are shown with a
     * <code>'*'</code> instead. This default can be changed with
     * {@link #setInvisibleChar(char) setInvisibleChar()}.
     * 
     * @since 4.0.12
     */
    public void setVisibility(boolean setting) {
        GtkEntry.setVisibility(this, setting);
    }

    /**
     * Is text in the Entry are visible, or hidden by an obscuring character?
     * Returns <code>true</code> if characters entered are visible,
     * <code>false</code> if obscured.
     * 
     * @since 4.0.12
     */
    public boolean getVisibility() {
        return GtkEntry.getVisibility(this);
    }

    /**
     * Change the character used to obscure text when
     * {@link #setVisibility(boolean) visibility} is <code>false</code>.
     * 
     * @param replacement
     *            The new character to be used to obscure text. A value of
     *            <code>0</code> will cause no feedback to displayed at all
     *            when the user is typing in the Entry.
     * @since 4.0.3
     */
    public void setInvisibleChar(char replacement) {
        GtkEntry.setInvisibleChar(this, replacement);
    }

    /**
     * Set whether the text in the Entry can be change by the user.
     * 
     * @since 4.0.3
     */
    public void setEditable(boolean editable) {
        GtkEditable.setEditable(this, editable);
    }

    /**
     * Set whether the text should be overwritten when typing in the Entry.
     * 
     * @since 4.0.13
     */
    public void setOverwriteMode(boolean setting) {
        GtkEntry.setOverwriteMode(this, setting);
    }

    /**
     * Get the value previously set with {@link #setOverwriteMode(boolean)
     * setOverwriteMode()}.
     * 
     * @since 4.0.13
     */
    public boolean getOverwriteMode() {
        return GtkEntry.getOverwriteMode(this);
    }

    /**
     * The <code>Entry.Activate</code> signal occurs when the user presses <b>
     * <code>Enter</code></b> or <b><code>Return</code></b> in an Entry.
     * 
     * <p>
     * Note that the other significant signal on an Entry is
     * <code>Editable.Changed</code>, inherited from Editable. There is a
     * {@link Entry#connect(org.gnome.gtk.Editable.Changed) connect()} method.
     * 
     * @since 4.0.3
     */
    public interface Activate extends GtkEntry.ActivateSignal
    {
        public void onActivate(Entry source);
    }

    /**
     * Connects an <code>Entry.Activate</code> handler to the Widget.
     * 
     * @since 4.0.3
     */
    public void connect(Entry.Activate handler) {
        GtkEntry.connect(this, handler, false);
    }

    public void setPosition(int position) {
        if (position < -1) {
            throw new IllegalArgumentException(
                    "Position must be -1 to indicate you want it after the last character.");
        }
        GtkEditable.setPosition(this, position);
    }

    public int getPosition() {
        return GtkEditable.getPosition(this);
    }

    /**
     * Request that the width of this Entry be wide enough for a given number
     * of characters.
     * 
     * <p>
     * As with all font related operations, there are a number of competing
     * approximations involved. In particular, this method operates by
     * influencing the size <i>requested</i> by this Widget; the box packing
     * model will still have the final say in the size-allocation phase.
     * 
     * <p>
     * See also Label's {@link Label#setWidthChars(int) setWidthChars()}; the
     * challenges and constraints involved are similar.
     * 
     * @param width
     *            A setting of <code>-1</code> will return the Entry to normal
     *            sizing behaviour.
     * @since 4.0.6
     */
    public void setWidthChars(int width) {
        GtkEntry.setWidthChars(this, width);
    }

    /**
     * The signal emitted when the text in the Entry has changed.
     * 
     * @author Andrew Cowie
     * @since 4.1.1
     */
    /*
     * This signal is a mashup so that we can have Entry in the signature.
     */
    public interface Changed
    {
        public void onChanged(Entry source);
    }

    private static class EntryChangedHandler implements Editable.Changed
    {
        private final Entry.Changed handler;

        private EntryChangedHandler(Entry.Changed handler) {
            this.handler = handler;
        }

        public void onChanged(Editable source) {
            final Entry entry;

            entry = (Entry) source;
            handler.onChanged(entry);
        }
    }

    /**
     * Connect a <code>Editable.Changed</code> handler. Use
     * {@link #connect(org.gnome.gtk.Entry.Changed) connect(Entry.Changed)}
     * instead, as it saves you having to cast the <code>source</code>
     * parameter.
     * 
     * @since 4.0.6
     */
    public void connect(Editable.Changed handler) {
        GtkEditable.connect(this, handler, false);
    }

    /**
     * Connect a <code>Entry.Changed</code> handler.
     * 
     * @since 4.1.1
     */
    public void connect(Entry.Changed handler) {
        GtkEditable.connect(this, new EntryChangedHandler(handler), false);
    }

    public void selectRegion(int start, int end) {
        GtkEditable.selectRegion(this, start, end);
    }

    public int getSelectionBoundsStart() {
        int[] start = new int[1];
        int[] end = new int[1];

        GtkEditable.getSelectionBounds(this, start, end);

        return start[0];
    }

    public int getSelectionBoundsEnd() {
        int[] start = new int[1];
        int[] end = new int[1];

        GtkEditable.getSelectionBounds(this, start, end);

        return end[0];
    }

    /**
     * Set the alignment of the the text being displayed in the Entry.
     * 
     * @param xalign
     *            A value from <code>0.0f</code> for fully left-aligned
     *            through <code>1.0f</code> for fully right-aligned. You can
     *            use the constants {@link Alignment#LEFT LEFT},
     *            {@link Alignment#CENTER CENTER} and {@link Alignment#RIGHT
     *            RIGHT} in Alignment for convenience if you like. No, this
     *            has nothing to do with politics.
     * @since 4.0.6
     */
    /*
     * Supposedly this reverses for RTL text layout. It'd be nice of someone
     * could test that and document it to that effect if true.
     */
    public void setAlignment(float xalign) {
        if ((xalign < 0.0) || (xalign > 1.0)) {
            throw new IllegalArgumentException("xalign must be between 0.0 and 1.0");
        }
        GtkEntry.setAlignment(this, xalign);
    }

    /**
     * Set the completion object to use with this Entry.
     * 
     * <p>
     * Completion is a mechanism whereby pre-populated suggestions can be
     * offered to the user, allowing for faster data entry in some
     * circumstances.
     * 
     * <p>
     * All configuration of the completion mechanism is done using
     * {@link EntryCompletion}'s methods, so see there for details.
     * 
     * 
     * @since 4.0.12
     */
    public void setCompletion(EntryCompletion completion) {
        GtkEntry.setCompletion(this, completion);
    }

    /**
     * Returns the current {@link EntryCompletion} object which is currently
     * used by the <code>Entry</code>.
     * 
     * @since 4.0.12
     */
    public EntryCompletion getCompletion() {
        return GtkEntry.getCompletion(this);
    }

    /**
     * Set whether the Entry has a bevelled frame around it or not. The
     * default (as you will be well accustomed to seeing) is <code>true</code>
     * .
     * 
     * <p>
     * As this decoration is a strong visual cue for users to realize that
     * they are able to enter text into a given control, your are discouraged
     * from turning it off unless you really need to minutely control
     * placement; in that case, see also
     * {@link #setInnerBorder(int, int, int, int) setInnerBorder()}.
     * 
     * @since 4.0.8
     */
    public void setHasFrame(boolean setting) {
        GtkEntry.setHasFrame(this, setting);
    }

    /**
     * Set the padding between the text entry control itself, and the
     * surrounding decoration. This overrides the <var>inner-border</var>
     * style property set by the theme.
     * 
     * <p>
     * Ordinarily you shouldn't need this; in general you should leave things
     * alone so that Entries appear uniform across the user's desktop. This is
     * provided for the rare cases that minute positioning control is required
     * (think of what goes on when you edit text in a CellRendererText) it is
     * possible to do so.
     * 
     * @since 4.0.8
     */
    public void setInnerBorder(int left, int right, int top, int bottom) {
        GtkEntryOverride.setInnerBorder(this, left, right, top, bottom);
    }

    /**
     * Set the fraction of the progress that shows the <code>Entry</code> as
     * &quot;filled-in&quot;.
     * 
     * @since 4.0.13
     */
    public void setProgressFraction(double fraction) {
        if ((fraction < 0.0) || (fraction > 1.0)) {
            throw new IllegalArgumentException("fraction must be between 0.0 and 1.0, inclusive.");
        }
        GtkEntry.setProgressFraction(this, fraction);
    }

    /**
     * Get the current progress fraction of the <code>Entry</code>.
     * 
     * @since 4.0.13
     */
    public double getProgressFraction() {
        return GtkEntry.getProgressFraction(this);
    }

    /**
     * Set the progress pulse step of the <code>Entry</code>.
     * 
     * @since 4.0.13
     */
    public void setProgressPulseStep(double fraction) {
        if ((fraction < 0.0) || (fraction > 1.0)) {
            throw new IllegalArgumentException("fraction must be between 0.0 and 1.0, inclusive.");
        }
        GtkEntry.setProgressPulseStep(this, fraction);
    }

    /**
     * Get the current progress pulse step of the <code>Entry</code>.
     * 
     * @since 4.0.13
     */
    public double getProgressPulseStep() {
        return GtkEntry.getProgressPulseStep(this);
    }

    /**
     * Cause the <code>Entry</code>'s progress indicator to enter
     * &quot;activity mode&quot;, used to indicate that the application is
     * making progress but in a way that can't be strictly quantized.
     * 
     * @since 4.0.13
     */
    public void progressPulse() {
        GtkEntry.progressPulse(this);
    }

    /**
     * Set the icon to a given <code>position</code> using a {@link Pixbuf}.
     * If <code>pixbuf</code> is <code>null</code>, no icon will be shown.
     * 
     * @since 4.0.13
     */
    public void setIconFromPixbuf(EntryIconPosition position, Pixbuf pixbuf) {
        GtkEntry.setIconFromPixbuf(this, position, pixbuf);
    }

    /**
     * Set the icon to a given <code>position</code> using a {@link Stock}
     * item. If <code>stock</code> is <code>null</code>, no icon will be
     * shown.
     * 
     * @since 4.0.13
     */
    public void setIconFromStock(EntryIconPosition position, Stock stock) {
        GtkEntry.setIconFromStock(this, position, stock == null ? null : stock.getStockId());
    }

    /**
     * Cause the Entry to have an icon at the given <code>position</code>
     * using an Icon from the current icon theme. If <code>name</code> is
     * <code>null</code>, no icon will be shown. If for whatever reason the
     * icon lookup fails, a &quot;broken image&quot; icon will be used
     * instead.
     * 
     * @since 4.0.17
     */
    public void setIconFromIcon(EntryIconPosition position, Icon icon) {
        GtkEntry.setIconFromIconName(this, position, Helper.getName(icon));
    }

    /**
     * Get the type used by the icon to store image data.
     * 
     * @since 4.0.13
     */
    public ImageType getIconStorageType(EntryIconPosition position) {
        return GtkEntry.getIconStorageType(this, position);
    }

    /**
     * Retrieves the image used for the icon as a {@link Pixbuf}.
     * 
     * <p>
     * A <code>null</code> value will be returned if no icon is set for this
     * <code>position<code>.
     * 
     * @since 4.0.13
     */
    public Pixbuf getIconPixbuf(EntryIconPosition position) {
        return GtkEntry.getIconPixbuf(this, position);
    }

    /**
     * Retrieves the image used for the icon as a {@link Stock} item.
     * 
     * <p>
     * A <code>null</code> value will be returned if the icon was not set from
     * a {@link Stock} item.
     * 
     * @since 4.0.13
     */
    public Stock getIconStock(EntryIconPosition position) {
        return Stock.instanceFor(GtkEntry.getIconStock(this, position));
    }

    /**
     * Retrieves the named Icon used for illustrating icon at
     * <code>position</code> on this Entry.
     * 
     * <p>
     * A <code>null</code> value will be returned if the icon was not set from
     * an named Icon.
     * 
     * @since 4.0.17
     */
    public Icon getIconIcon(EntryIconPosition position) {
        final String name;
        final Icon result;

        name = GtkEntry.getIconName(this, position);
        result = Helper.instanceFor(name);

        return result;
    }

    /**
     * Set whether the icon at the given <code>position</code> is activatable
     * or not.
     * 
     * @since 4.0.13
     */
    public void setIconActivatable(EntryIconPosition position, boolean setting) {
        GtkEntry.setIconActivatable(this, position, setting);
    }

    /**
     * Return <code>true</code> if the icon at the given <code>position</code>
     * is activatable.
     * 
     * @since 4.0.13
     */
    public boolean getIconActivatable(EntryIconPosition position) {
        return GtkEntry.getIconActivatable(this, position);
    }

    /**
     * Set whether the icon at the given <code>position</code> should be
     * sensitive or insensitive.
     * 
     * @since 4.0.13
     */
    public void setIconSensitive(EntryIconPosition position, boolean setting) {
        GtkEntry.setIconSensitive(this, position, setting);
    }

    /**
     * Return <code>true</code> if the icon at the given <code>position</code>
     * is sensitive.
     * 
     * @since 4.0.13
     */
    public boolean getIconSensitive(EntryIconPosition position) {
        return GtkEntry.getIconSensitive(this, position);
    }

    /**
     * Find the icon at the given position and return its index.
     * 
     * <p>
     * If <code>x</code> or <code>y</code> are not inside an icon,
     * <code>-1</code> will be returned.
     * 
     * @since 4.0.13
     */
    public int getIconAtPos(int x, int y) {
        return GtkEntry.getIconAtPos(this, x, y);
    }

    /**
     * Set the note (without Pango markup) that will be displayed when the
     * mouse pointer will be over the icon. The <code>text</code> string
     * should be in plain text (without any Pango markup).
     * 
     * @since 4.0.13
     */
    public void setIconTooltipText(EntryIconPosition position, String text) {
        GtkEntry.setIconTooltipText(this, position, text);
    }

    /**
     * Return the note that is displayed when the mouse pointer is over the
     * icon.
     * 
     * @since 4.0.13
     */
    public String getIconTooltipText(EntryIconPosition position) {
        return GtkEntry.getIconTooltipText(this, position);
    }

    /**
     * Set the note (with Pango markup) that will be displayed when the mouse
     * pointer will be over the icon.
     * 
     * @since 4.0.13
     */
    public void setIconTooltipMarkup(EntryIconPosition position, String markup) {
        GtkEntry.setIconTooltipMarkup(this, position, markup);
    }

    /**
     * Return the note that is displayed when the mouse pointer is over the
     * icon.
     * 
     * @since 4.0.13
     */
    public String getIconTooltipMarkup(EntryIconPosition position) {
        return GtkEntry.getIconTooltipMarkup(this, position);
    }

    /**
     * Emitted when an activatable icon is clicked.
     * 
     * @since 4.0.13
     */
    public interface IconPress extends GtkEntry.IconPressSignal
    {
        public void onIconPress(Entry source, EntryIconPosition position, Event event);
    }

    /**
     * Hook up the <code>Entry.IconPress</code> handler.
     * 
     * @since 4.0.13
     */
    public void connect(Entry.IconPress handler) {
        GtkEntry.connect(this, handler, false);
    }

    /**
     * Emitted on the button release from a mouse click over an activatable
     * icon.
     * 
     * @since 4.0.13
     */
    public interface IconRelease extends GtkEntry.IconReleaseSignal
    {
        public void onIconRelease(Entry source, EntryIconPosition position, Event event);
    }

    /**
     * Hook up the <code>Entry.IconRelease</code> handler.
     * 
     * @since 4.0.13
     */
    public void connect(Entry.IconRelease handler) {
        GtkEntry.connect(this, handler, false);
    }
}
