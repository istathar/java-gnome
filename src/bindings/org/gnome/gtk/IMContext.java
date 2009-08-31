/*
 * IMContext.java
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
 * Complex input handling.
 * 
 * @author Andrew Cowie
 * @since 4.0.14
 */
/*
 * We are not exposing this as an application programming interface for you to
 * implement your own input methods; if you need to create a custom input
 * method it needs to be done in C and via GTK's dynamic module loading
 * machinery.
 */
public abstract class IMContext extends Object
{
    protected IMContext(long pointer) {
        super(pointer);
    }
}
