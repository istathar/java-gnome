/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2011 Operational Dynamics Consulting, Pty Ltd and Others
 *
 * The code in this file, and the program it is a part of, is made available
 * to you by its authors as open source software: you can redistribute it
 * and/or modify it under the terms of the GNU General Public License version
 * 2 ("GPL") as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
 *
 * You should have received a copy of the GPL along with this program. If not,
 * see http://www.gnu.org/licenses/. The authors of this program may be
 * contacted through http://java-gnome.sourceforge.net/.
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */
package org.gnome.gtk;

import org.gnome.glib.Boxed;

/**
 * Helper class which manages spell checking in TextViews. Convert a TextView
 * to one that is doing spelling by calling TextView's
 * {@link TextView#attachSpell() attachSpell()}. <img class="snapshot"
 * src="TextView-Spelling.png">
 * 
 * <p>
 * <i>Augmenting a TextView to provide spell checking is provided by the
 * GtkSpell library, which in turn leverages Enchant to connect to a spell
 * checking backend.</i>
 * 
 * <p>
 * <i>If GtkSpell doesn't meet your needs, then you may try using the
 * underlying checking API directly. We have coverage of it starting at</i>
 * {@link org.freedesktop.enchant.Enchant Enchant}.
 * 
 * 
 * 
 * @author Andrew Cowie
 * @author Serkan Kaba
 * @since 4.0.12
 * @deprecated This is temporarily disabled pending the release of a GTK 3
 *             compatible version of GtkSpell
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
        throw new UnsupportedOperationException("GtkSpell support not yet available");
    }

    /*
     * DANGER WARNING DANGER The detach() function is how you free a GtkSpell,
     * so normally it would be here. It seems, however, that the current
     * implementation in the GtkSpell library's gtkspell.c has a signal
     * handler connected to the GtkTextView 'destroy' signal calling its
     * internal gtkspell_free() function. If we call gtkspell_detach() it
     * results in a segmentation fault.
     */
    protected void release() {
        // GtkSpell.detach(this);
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
        throw new UnsupportedOperationException("GtkSpell support not yet available");
    }

    /**
     * Re-run the spell checker over the entire text.
     * 
     * @since 4.0.12
     */
    public void recheckAll() {
        throw new UnsupportedOperationException("GtkSpell support not yet available");
    }
}
