/*
 * GnomeEntry.java
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 *
 *                      THIS FILE WILL BE GENERATED CODE!
 *
 * To modify its contents or behaviour, either update the generation program,
 * change the information in the source defs file, or implement an override
 * for this class.
 */
package org.gnome.gnome;

import org.gnome.glib.Plumbing;

final class GnomeEntry extends Plumbing
{
    private GnomeEntry() {}

    static final String getText(Entry self) {
        return gnome_entry_get_text(pointerOf(self));
    }

    private static native final String gnome_entry_get_text(long self);
}
