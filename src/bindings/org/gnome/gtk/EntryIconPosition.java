/*
 * EntryIconPosition.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.freedesktop.bindings.Constant;

/**
 * Constants used to specify where an icon will be placed in an {@link Entry}.
 * <img src="Entry.png" class="snapshot" />
 * 
 * @author Guillaume Mazoyer
 * @since 4.0.13
 */
public final class EntryIconPosition extends Constant
{
    private EntryIconPosition(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * The icon will be placed at the beginning of the {@link Entry}
     * (depending on the text direction).
     */
    public static final EntryIconPosition PRIMARY = new EntryIconPosition(GtkEntryIconPosition.PRIMARY,
            "PRIMARY");

    /**
     * The icon will be placed at the end of the {@link Entry} (depending on
     * the text direction).
     */
    public static final EntryIconPosition SECONDARY = new EntryIconPosition(
            GtkEntryIconPosition.SECONDARY, "SECONDARY");
}
