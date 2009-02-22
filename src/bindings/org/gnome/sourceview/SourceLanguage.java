/*
 * SourceLanguage.java
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
 * A SourceLanguage is used for syntax highlighting in a {@link SourceBuffer}.
 * Instances can be obtained from a {@link SourceLanguageManager} .
 * 
 * @author Stefan Schweizer
 */
public class SourceLanguage extends Object
{
    protected SourceLanguage(long pointer) {
        super(pointer);
    }

    /**
     * Get the ID of the language. For example, the ID for Java is
     * <code>java</code>. The ID of a language is defined in the
     * corresponding language definition file.
     */
    public String getID() {
        return GtkSourceLanguage.getId(this);
    }

    /**
     * Get the localized name of the language.
     */
    public String getName() {
        return GtkSourceLanguage.getName(this);
    }
}
