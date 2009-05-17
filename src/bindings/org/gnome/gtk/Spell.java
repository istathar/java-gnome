/*
 * Spell.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.gnome.glib.Boxed;
import org.gnome.glib.GlibException;

/**
 * @author Andrew Cowie
 * @author Serkan Kaba
 * @since 4.0.12
 */
public final class Spell extends Boxed
{
    protected Spell(long pointer) {
        super(pointer);
    }

    /*
     * Perhaps we should expose this as a utility method on TextView?
     */
    Spell(TextView view, String lang) {
        super(createSpell(view, lang));
    }

    private static long createSpell(TextView view, String lang) {
        try {
            return GtkSpell.createSpellAttach(view, lang);
        } catch (GlibException ge) {
            throw new IllegalArgumentException("The GtkSpell backend reported an error:\n"
                    + ge.getMessage());
        }
    }

    /*
     * The detach() function is how you free a GtkSpell, so it has to be here.
     * We probably should not expose it publicly.
     */
    protected void release() {
        GtkSpell.detach(this);
    }
}
