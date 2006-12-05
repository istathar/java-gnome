/*
 * Signal.java
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.glib;

/**
 * Marker interface which is the parent of all signals as expressed in the
 * bindings. Calling it Signal is actually a slight misnomer; when someone
 * implements the concrete Signal subclass that is written into the public API
 * files a SignalHandler is more what they've created.
 * 
 * @author Andrew Cowie
 */
/*
 * This is only here so that the callback design is more straight forward.
 * Otherwise we'd have to lookup the jclass for each signal on the JNI side, and
 * that's silly. The downcast in the generated handleName() method will take
 * care of it.
 */
public abstract interface Signal
{
}
