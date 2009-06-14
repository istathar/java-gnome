/*
 * Language.java
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
 * A Language is used for syntax highlighting in a SourceBuffer. Instances can
 * be obtained from the {@link LanguageManager}.
 * 
 * @author Stefan Schweizer
 * @since 4.0.12
 */
public class Language extends Object
{
    protected Language(long pointer) {
        super(pointer);
    }

    /**
     * Get the ID of the language. For example, the ID for Java is
     * <code>java</code>. The ID of a language is defined in the corresponding
     * language definition file.
     * 
     * @since 4.0.12
     */
    public String getID() {
        return GtkSourceLanguage.getId(this);
    }

    /**
     * Get the localized name of the language.
     * 
     * @since 4.0.12
     */
    public String getName() {
        return GtkSourceLanguage.getName(this);
    }
}
