/*
 * AttributeList.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.pango;

import org.gnome.glib.Boxed;

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
/**
 * @since 4.0.10
 */
/*
 * AttrList was the original mapping, but since we aren't exposing
 * pango_attr_... anything, and DO have Attribute as the public class for
 * PangoAttribute pointers, renaming to this to AttributeList makes it much
 * clearer.
 */
public final class AttributeList extends Boxed
{
    protected AttributeList(long pointer) {
        super(pointer);
    }

    public AttributeList() {
        super(PangoAttrList.createAttributeList());
    }

    protected void release() {
        PangoAttrList.unref(this);
    }

    /**
     * Insert an Attribute into this list. It will be inserted after any other
     * Attributes already in the list possessing the same <var>start
     * index</var>.
     * 
     * @since 4.0.10
     */
    public void insert(Attribute attr) {
        PangoAttrList.insert(this, attr);
    }

    /**
     * Insert an Attribute into this list. Same as {@link #insert(Attribute)
     * insert()} except that the Attribute will come before other Attributes
     * already in the list possessing the same <var>start index</var>.
     * 
     * @since 4.0.10
     */
    public void insertBefore(Attribute attr) {
        PangoAttrList.insertBefore(this, attr);
    }
}
