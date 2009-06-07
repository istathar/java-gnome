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
 * Helper class which manages spell checking in TextViews. Convert a TextView
 * to one that is doing spelling by calling TextView's
 * {@link TextView#attachSpell() attachSpell()}.
 * 
 * <p>
 * <i>Augmenting a TextView to provide spell checking is provided by the
 * GtkSpell library, which in turn leverages Enchant to connect to a spell
 * checking backend.</i>
 * 
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
            throw new RuntimeException("The GtkSpell backend reported an error:\n" + ge.getMessage());
        }
    }

    /*
     * The detach() function is how you free a GtkSpell, so it has to be here.
     * We probably should not expose it publicly.
     */
    protected void release() {
        GtkSpell.detach(this);
    }

    /**
     * Change the language that spellings are being checked against.
     * 
     * <p>
     * The default language selected when you call TextView's
     * {@link TextView#attachSpell() attachSpell()} is based on the
     * <code>LANG</code> environment variable, so normally you don't need to
     * call this.
     * 
     * <p>
     * Otherwise, you can attach with the other
     * {@link TextView#attachSpell(String) attachSpell()} method, specifying a
     * language there.
     * 
     * <p>
     * <i>Interestingly, if for some strange reason that environment variable
     * is not set, the language selection will fall back to English. But as it
     * is essentially impossible to log into a GNOME system without</i>
     * <code>LANG</code> <i>being set, you shouldn't ever encounter this.</i>
     * 
     * @since 4.0.12
     */
    public void setLanguage(String lang) {
        try {
            GtkSpell.setLanguage(this, lang);
        } catch (GlibException ge) {
            throw new IllegalArgumentException("The GtkSpell backend reported an error:\n"
                    + ge.getMessage());
        }
    }

    /**
     * Re-run the spell checker over the entire text.
     * 
     * @since 4.0.12
     */
    public void recheckAll() {
        GtkSpell.recheckAll(this);
    }
}
