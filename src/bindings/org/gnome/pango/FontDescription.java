/*
 * FontDescription.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008 Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.pango;

import org.gnome.glib.Boxed;
import org.gnome.gtk.FontSelection;
import org.gnome.gtk.FontSelectionDialog;

/**
 * Description of a Font. This is an abstract description of the ideal Font we
 * want to load. It is used both to list what fonts are available on the
 * system and also for specifying the characteristics of a real Font to load.
 * 
 * @author Vreixo Formoso
 * @since 4.0.8
 */
public final class FontDescription extends Boxed
{
    protected FontDescription(long pointer) {
        super(pointer);
    }

    /**
     * Creates a new FontDescription with all fields unset.
     * 
     * <p>
     * {@link FontDescription#FontDescription(String) FontDescription(String)}
     * is usually a better way to initialize the FontDescription, specially if
     * you get the description from a {@link FontSelection} or
     * {@link FontSelectionDialog} Widgets.
     */
    public FontDescription() {
        super(PangoFontDescription.createFontDescription());
    }

    /**
     * Creates a new FontDescription from a String representation in the form
     * "[FAMILY-LIST] [STYLE-OPTIONS] [SIZE]", where FAMILY-LIST is a comma
     * separated list of families optionally terminated by a comma,
     * STYLE_OPTIONS is a whitespace separated list of words where each WORD
     * describes one of style, variant, weight, stretch, or gravity, and SIZE
     * is a decimal number (size in points) or optionally followed by the unit
     * modifier "px" for absolute size. Any one of the options may be absent.
     * If FAMILY-LIST is absent, then the <code>familyName</code> field of
     * the resulting font description will be initialized to <code>null</code>.
     * If STYLE-OPTIONS is missing, then all style options will be set to the
     * default values. If SIZE is missing, the size in the resulting font
     * description will be set to 0.
     * 
     * <p>
     * Examples are "Sans Bold 27"
     * 
     * @param str
     */
    public FontDescription(String str) {
        super(PangoFontDescription.createFontDescriptionFromString(str));
    }

    protected void release() {
        PangoFontDescription.free(this);
    }

    public int hashCode() {
        return PangoFontDescription.hash(this);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FontDescription)) {
            return false;
        }
        return PangoFontDescription.equal(this, (FontDescription) obj);
    }

    /**
     * Sets the family name field of a FontDescription. The family name
     * represents a family of related font styles. In some uses of
     * PangoFontDescription, it is also possible to use a comma separated list
     * of family names for this field.
     * 
     * TODO examples of family name.
     */
    public void setFamily(String family) {
        PangoFontDescription.setFamily(this, family);
    }

    /**
     * Gets the family name field of the FontDescription.
     * 
     * @return The family name, or <code>null</code> if not previously set.
     */
    public String getFamily() {
        return PangoFontDescription.getFamily(this);
    }

    /**
     * Set the slant style of the Font.
     */
    public void setStyle(Style style) {
        PangoFontDescription.setStyle(this, style);
    }

    /**
     * Get the slant style of the Font.
     */
    public Style getStyle() {
        return PangoFontDescription.getStyle(this);
    }
}
