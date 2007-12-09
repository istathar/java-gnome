/*
 * Invisible.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/*
 * TODO: shall we get rid of this? From the GTK docs: "The GtkInvisible widget
 * is used internally in GTK+, and is probably not very useful for application
 * developers. It is used for reliable pointer grabs and selection handling in
 * the code for drag-and-drop."
 */
class Invisible extends Widget
{
    protected Invisible(long pointer) {
        super(pointer);
    }
}
