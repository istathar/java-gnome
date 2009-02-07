/*
 * TextBuilder.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by its authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.pango;

/**
 * Build up a string of text along with any desired markup. You can use this
 * if you are iterating over an internal representation of some textual data,
 * and need to render it as formatted text on a Cairo Surface.
 * 
 * <pre>
 * buf = new TextBuilder();
 * 
 * buf.append(&quot;H€llo &quot;, null);
 * 
 * attr = new Attribute(Style.ITALICS);
 * buf.append(&quot;world&quot;, attr);
 * 
 * layout.setText(buf);
 * </pre>
 * 
 * would result in you getting text looking more or less like:<br>
 * 
 * <blockquote> <code>H€llo <i>world</i></code> </blockquote>
 * 
 * <p>
 * Although the underlying mechanism supports arbitrary overlapping of
 * Attributes, in practise there's little need. Just create a new Attribute
 * for each span of text which has contiguous formatting.
 * 
 * <p>
 * <i>This class is an optimization to deal with the fact that PangoAttributes
 * work in the variable byte offsets into UTF-8 strings, whereas the Java
 * language works in strings of fixed 2 byte Unicode characters. The EUR
 * currency symbol in the above snippet is an example of the kind of string
 * that causes their to be a non-linear relationship between character offset
 * and byte offset; the start and end points of the Attribute around "world"
 * turn out to be (7,12) and not (5,10) as you would have expected from the
 * length of the Java String objects.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.10
 */
public class TextBuilder
{
    private StringBuilder buf;

    private AttributeList list;

    private int index;

    public TextBuilder() {
        buf = new StringBuilder();
        list = new AttributeList();
        index = 0;
    }

    /**
     * Add a piece of text with formatting as described by an Attribute. The
     * works on the assumption that <i>all</i> the formatting you want on this
     * bit of text is represented by the passed Attribute.
     * 
     * <p>
     * If you don't need to add any markup then pass <code>null</code> for
     * <code>attr</code>.
     * 
     * @since 4.0.10
     */
    public void append(String str, Attribute attr) {
        final int len, width;
        int i;

        len = str.length();
        for (i = 0; i < len; i++) {
            buf.append(str.charAt(i));
        }

        /*
         * Make a native call to ask the JVM how wide the UTF8 version of this
         * String would be.
         */
        width = PangoAttributeOverride.getLengthUTF8(str);

        if (attr != null) {
            PangoAttribute.setStartIndex(attr, index);
            PangoAttribute.setEndIndex(attr, index + width);
            PangoAttrList.insert(list, attr);
        }

        index += width;
    }

    /**
     * Apply an Attribute over an arbitrary range. The <code>offset</code> and
     * <code>width</code> parameters are expressed in terms of character
     * offset into the Java String.
     * 
     * @since <span style="color: red;">Not implemented</span>
     */
    /*
     * If the assumption stated in the class documentation above about there
     * being little need for applying additional Attributes over spans, then
     * we'll need to expose this and/or Attribute's setIndexes().
     */
    void apply(Attribute attr, int offset, int width) {
        if ((offset + width) > buf.length()) {
            throw new IllegalArgumentException(
                    "The text in this TextBuilder is shorter than offset + width");
        }

        throw new UnsupportedOperationException();
    }

    /*
     * This does a copy, but there's probably no way around that. Damn.
     */
    String getText() {
        return buf.toString();
    }

    AttributeList getAttributes() {
        return list;
    }
}
