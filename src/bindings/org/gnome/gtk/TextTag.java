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

import org.gnome.glib.Object;
import org.gnome.pango.Scale;
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
    /*
     * Simple Java side reference to facilitate avoiding double-tap additions.
     */
    TextTagTable table;

    /*
     * The tags name, again to facilitate Java side checking
     */
    final String name;

    protected TextTag(long pointer) {
        super(pointer);
        name = getPropertyString("name");
    }

    /**
     * Create a new TextTag. The given <code>name</code> will be used as the
     * TextTag's nickname. You can pass <code>null</code> to make this
     * anonymous.
     * 
     * @since 4.0.8
     */
    public TextTag(String name) {
        super(GtkTextTag.createTextTag(name));
        this.name = name;
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
        setPropertyDouble("scale", GtkTextTagOverride.scaleOf(scale));
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
     * Specfict the font weight. The useful one is {@link Weight#BOLD BOLD};
     * the default is {@link Weight#NORMAL NORMAL}.
     * 
     * @since 4.0.8
     */
    public void setWeight(Weight weight) {
        setPropertyInteger("weight", GtkTextTagOverride.weightOf(weight));
    }
}
