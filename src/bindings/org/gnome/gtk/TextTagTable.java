/*
 * TextTagTable.java
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

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
public class TextTagTable extends Object
{
    protected TextTagTable(long pointer) {
        super(pointer);
    }

    /**
     * Instantiate a new table for collecting TextTags.
     * 
     * @since 4.0.8
     */
    public TextTagTable() {
        super(GtkTextTagTable.createTextTagTable());
    }

    /**
     * Add a TextTag to this collection.
     * 
     * <p>
     * You cannot add the same tag to multiple tables, and (as should be
     * obvious) you can't add the same tag to a given TextTagTable twice.
     * Also, you cannot add a tag with a name that is already in use by
     * another tag in this table.
     * 
     * @since 4.0.8
     */
    public void add(TextTag tag) {
        if (tag.table != null) {
            if (tag.table == this) {
                throw new IllegalStateException("You've already added tag to this table");
            } else {
                throw new IllegalStateException("You can't add tag to second table");
            }
        }

        GtkTextTagTable.add(this, tag);
        tag.table = this;
    }

    /**
     * Remove a TextTag from this table.
     * 
     * @since 4.0.8
     */
    public void remove(TextTag tag) {
        tag.table = null;
        GtkTextTagTable.remove(this, tag);
    }
}
