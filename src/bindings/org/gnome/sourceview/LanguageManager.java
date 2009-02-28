/*
 * LanguageManager.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.sourceview;

import org.gnome.gtk.Object;

/**
 * A LanguageManager is used to obtain {@link Language} objects that are used
 * for syntax highlighting in a {@link SourceBuffer}.
 * 
 * @author Stefan Schweizer
 */
public class LanguageManager extends Object
{
    protected LanguageManager(long pointer) {
        super(pointer);
    }

    /**
     * Return the default LanguageManager.
     */
    public static LanguageManager getDefault() {
        return GtkSourceLanguageManager.getDefault();
    }

    /**
     * Return a Language specified by its language ID or null if the ID is not
     * known.
     */
    public Language getLanguage(String id) {
        return GtkSourceLanguageManager.getLanguage(this, id);
    }
}
