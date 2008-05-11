/*
 * TextMark.java
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
 * A stable reference to a specific position within a TextBuffer.
 * 
 * <p>
 * TextIters are transient pointers to positions within TextBuffers which are
 * invalidated when that TextBuffer is modified, so you can't "save" them. If
 * you need to preserve a position for later reuse, then get a TextMark by
 * calling TextBuffer's
 * {@link TextBuffer#createMark(TextIter, boolean) createMark()} on it.
 * 
 * <p>
 * Instances of this class fill the same role for TextBuffers that
 * {@link TreeRowReference} does in providing stability for rows pointed to
 * transiently by TreeIters that come from TreeModels.
 * 
 * @author Andrew Cowie
 * @since 4.0.8
 */
public class TextMark extends Object
{
    protected TextMark(long pointer) {
        super(pointer);
    }
}
