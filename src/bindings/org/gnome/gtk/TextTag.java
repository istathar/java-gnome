/*
 * TextTag.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import static org.gnome.gtk.TextTagTable.getDefaultTable;

import org.gnome.glib.Object;
import org.gnome.pango.Scale;
import org.gnome.pango.Underline;
import org.gnome.pango.Weight;

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
/**
 * ... markup and formatting for regions of text in a TextBuffer ...
 * 
 * <p>
 * All TextTags belong to a TextTagTable, and likewise all TextBuffers are
 * constructed by specifying the TextTagTable that it will draw tags from.
 * 
 * <p>
 * <i>All TextTags created in java-gnome are "anonymous"; the underlying
 * library has a notion of named tags but we have no need of this and have not
 * exposed it. In order to use a TextTag later just keep a reference to it.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.8
 */
/*
 * This class is interesting and unusual in that there are no direct setter
 * functions; all the various properties are only exposed through the GObject
 * GValue property setting mechanism.
 */
public class TextTag extends Object
{
    protected TextTag(long pointer) {
        super(pointer);
    }

    /**
     * Create a new TextTag and place it in the default TextTagTable. This
     * will be usable by a TextBuffer created with the
     * {@link TextBuffer#TextBuffer() no-arg} constructor.
     * 
     * @since 4.0.8
     */
    public TextTag() {
        super(GtkTextTag.createTextTag(null));
        GtkTextTagTable.add(getDefaultTable(), this);
    }

    /**
     * Create a new TextTag. You pass the TextTagTable that will enclose this
     * TextTag as the argument to the constructor.
     * 
     * <p>
     * <i>In GTK you have to add TextTags to TextTagTables; we do this for you
     * automatically.</i>
     * 
     * @since 4.0.8
     */
    public TextTag(TextTagTable table) {
        super(GtkTextTag.createTextTag(null));
        GtkTextTagTable.add(table, this);
    }

    /**
     * Specify that you want the text scaled up or scaled down from the
     * otherwise extant font size. The default is
     * {@link org.gnome.pango.Scale#NORMAL NORMAL} (that is, a scaling factor
     * of <code>1.0</code> which thereby has no effect).
     * 
     * @since 4.0.8
     */
    /*
     * Be aware in passing that this obscures the Scale in org.gnome.gtk
     */
    public void setScale(Scale scale) {
        setPropertyDouble("scale", GtkTextTagOverride.valueOf(scale));
    }

    /**
     * Specify the amount by which this paragraph is to be indented, in
     * pixels. Interestingly, this <i>can</i> be negative. The default is
     * <code>0</code>.
     * 
     * @since 4.0.8
     */
    public void setIndent(int pixels) {
        setPropertyInteger("indent", pixels);
    }

    /**
     * Specify the left margin, in pixels. The default is <code>0</code>.
     * 
     * @since 4.0.8
     */
    public void setLeftMargin(int pixels) {
        if (pixels < 0) {
            throw new IllegalArgumentException("Margin property must be positive");
        }
        setPropertyInteger("left-margin", pixels);
    }

    /**
     * Specify the right margin, in pixels. The default is <code>0</code>.
     * 
     * @since 4.0.8
     */
    public void setRightMargin(int pixels) {
        if (pixels < 0) {
            throw new IllegalArgumentException("Margin property must be positive");
        }
        setPropertyInteger("right-margin", pixels);
    }

    /**
     * Specify the font weight. The useful one is {@link Weight#BOLD BOLD};
     * the default is {@link Weight#NORMAL NORMAL}.
     * 
     * @since 4.0.8
     */
    public void setWeight(Weight weight) {
        setPropertyInteger("weight", GtkTextTagOverride.valueOf(weight));
    }

    /**
     * Specify the underling mode to be used for this text. Single underlining
     * is {@link Underline#SINGLE SINGLE}. {@link Underline#NONE NONE} is the
     * default, obviously.
     * 
     * @since 4.0.8
     */
    public void setUnderline(Underline underline) {
        setPropertyEnum("underline", underline);
    }

    /**
     * Specify that this text be rendered struck through.
     * 
     * @since 4.0.8
     */
    public void setStrikethrough(boolean setting) {
        setPropertyBoolean("strikethrough", setting);
    }

    /**
     * Specify the background colour via a String description.
     * 
     * <p>
     * See {@link #setForeground(String) setForeground()} for discussion of
     * allowable colour names.
     * 
     * <p>
     * The default is no explicit setting.
     * 
     * @since 4.0.8
     */
    public void setBackground(String colour) {
        setPropertyString("background", colour);
    }

    /**
     * Specify the foreground colour by name.
     * 
     * <p>
     * Colours can be specified either:
     * 
     * <ul>
     * <li>as hex, in the form <code>"#<i>rrggbb</i>"</code> where
     * <code><i>rr</i></code>, <code><i>gg</i></code>, and
     * <code><i>bb</i></code> are two hexadecimal characters expressing a
     * values between <code>0x00</code> and <code>0xFF</code> for red,
     * green, and blue respectively; or
     * <li>as a symbolic name, such as <code>"blue"</code> and
     * <code>"medium sea green"</code>, depending on what constants have
     * been built into your X server. You can likely see a list at
     * <code>/usr/share/X11/rgb.txt</code>. Obviously these are unreliable
     * between different systems, but they are undeniably easy to use.
     * </ul>
     * 
     * <p>
     * The default is no explicit setting.
     * 
     * @since 4.0.8
     */
    public void setForeground(String colour) {
        setPropertyString("foreground", colour);
    }

    /**
     * Set whether the region of text covered by this TextTag is editable by
     * the user.
     * 
     * @since 4.0.8
     */
    /*
     * FIXME The default is true what happens if a TextView is
     * setEditable(false)? Needs a test case.
     */
    public void setEditable(boolean setting) {
        setPropertyBoolean("editable", setting);
    }
}
