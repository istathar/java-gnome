/*
 * Entry.java
 * 
 * See LICENCE file for usage and redistribution terms
 * Copyright (c) 2006 Operational Dynamics
 */
package org.gnome.gnome;

import org.gnome.gtk.Widget;

public class Entry extends Widget {
    private Entry() {
        // FIXME WRONG!!!
        super(0);
    }
    public String getText() {
        return GnomeEntry.get_text(this);
    }
}
