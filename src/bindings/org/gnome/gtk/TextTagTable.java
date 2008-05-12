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
/**
 * A set of TextTags that can be used by one or more TextBuffers.
 * 
 * <p>
 * Creating a TextTag automatically adds it to the TextTagTable passed when
 * the TextTag is constructed.
 * 
 * <p>
 * You certainly only need one of these per TextBuffer, and as most people
 * want to reuse the same set of TextTags across all the TextBuffers in a
 * given program, they typically end up only having one TextTagTable across
 * the entire application.
 * 
 * @author Andrew Cowie
 * @since 4.0.8
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
}
