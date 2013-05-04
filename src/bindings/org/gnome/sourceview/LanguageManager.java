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
package org.gnome.sourceview;

import org.gnome.glib.Object;

/**
 * A LanguageManager is used to obtain Language objects that are used for
 * syntax highlighting in a SourceBuffer.
 * 
 * <p>
 * You need to use {@link #getDefault() LangagueManager.getDefault()} to get
 * the singleton and then use it to request a Language by "id" via it's
 * {@link #getLanguage(String) getLanguage()} method.
 * 
 * @author Stefan Schweizer
 * @since 4.0.12
 */
public class LanguageManager extends Object
{
    protected LanguageManager(long pointer) {
        super(pointer);
    }

    /**
     * Return the default LanguageManager.
     * 
     * @since 4.0.12
     */
    public static LanguageManager getDefault() {
        return GtkSourceLanguageManager.getDefault();
    }

    /**
     * Return a Language specified by its language ID or <code>null</code> if
     * the ID is not known.
     * 
     * @since 4.0.12
     */
    public Language getLanguage(String id) {
        return GtkSourceLanguageManager.getLanguage(this, id);
    }

    /**
     * Get a language based on the filename and content type.
     * 
     * This is only a guess and could be wrong. When it is unable to guess the
     * language it will return null.
     * 
     * @since 4.1.3
     */
    public Language getLanguageGuess(String fileName, String contentType) {
        return GtkSourceLanguageManager.guessLanguage(this, fileName, contentType);
    }
}
