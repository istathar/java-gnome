/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd
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

import org.freedesktop.bindings.Constant;
import org.gnome.gdk.Pixbuf;

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
class Value extends org.gnome.glib.Value
{
    protected Value(long pointer, boolean proxy) {
        super(pointer, proxy);
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

    Value(float value) {
        super(value);
    }

    Value(double value) {
        super(value);
    }

    Value(org.gnome.glib.Object obj) {
        super(obj);
    }

    Value(Pixbuf pixbuf) {
        super(pixbuf);
    }

    /*
     * Used by GObject, not GtkObject!
     */
    protected org.gnome.glib.Object getObject() {
        return super.getObject();
    }

    protected Pixbuf getPixbuf() {
        return super.getPixbuf();
    }

    protected float getFloat() {
        return super.getFloat();
    }

    protected double getDouble() {
        return super.getDouble();
    }

    Value(long value) {
        super(value);
    }

    protected long getLong() {
        return super.getLong();
    }

    Value(Constant value) {
        super(value);
    }

    protected Constant getEnum() {
        return super.getEnum();
    }
}
