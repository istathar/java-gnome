/*
 * IMContextSimple.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
/**
 * The basis of the default input method in GTK.
 * 
 * <p>
 * By all accounts you do <b>not</b> want to use this. Instead, call the
 * constructor of Multicontext . If ther is no input method set, then GTK's
 * default fallback is to use a simple context.
 * 
 * @author Andrew Cowie
 * @since 4.0.14
 */
public class IMContextSimple extends IMContext
{
    protected IMContextSimple(long pointer) {
        super(pointer);
    }

    /**
     * Construct an IMContext using the GTK's default "simple" backend.
     * 
     * @since 4.0.14
     */
    public IMContextSimple() {
        super(GtkIMContextSimple.createIMContextSimple());
    }
}
