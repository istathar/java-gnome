/*
 * Signal.java
 * 
 * Copyright (c) 2006 Operational Dynamics
 * See LICENCE file for usage and redistribution terms
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
