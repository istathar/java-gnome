/*
 * Value.java
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * Wrap real Value class so that we can keep the visibility of its methods
 * restricted. See {@link org.gnome.glib.Value Value} for all the details.
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 * @since 4.0.4
 */
/*
 * This is cut and paste hell, so be careful. Make sure unit tests in
 * org.gnome.gtk do not import org.gnome.glib.Value.
 */
final class Value extends org.gnome.glib.Value
{
    protected Value(long pointer) {
        super(pointer);
    }

    Value() {
        super();
    }

    Value(String value) {
        super(value);
    }

    protected String getString() {
        return super.getString();
    }

    Value(int value) {
        super(value);
    }

    protected int getInteger() {
        return super.getInteger();
    }

    Value(boolean value) {
        super(value);
    }

    protected boolean getBoolean() {
        return super.getBoolean();
    }

    Value(float f) {
        super(f);
    }

    protected float getFloat() {
        return super.getFloat();
    }
}
