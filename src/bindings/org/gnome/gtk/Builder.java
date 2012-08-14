/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
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

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;

import org.gnome.glib.GlibException;
import org.gnome.glib.Object;

/**
 * A tool to load Glade User Interface description files and instantiate the
 * objects therein as a GTK Widgets and Objects.
 * 
 * <p>
 * Note that this is the replacement for the GNOME 2 "libglade" library, but
 * you still use the <b><code>Glade</code></b> UI designer to create
 * GtkBuilder <code>.ui</code> files.
 * 
 * @author Andrew Cowie
 * @since 4.0.20
 */
public class Builder extends Object
{
    protected Builder(long pointer) {
        super(pointer);
    }

    /**
     * Creates a new Builder tree.
     * 
     * @since 4.0.20
     */
    public Builder() {
        super(GtkBuilder.createBuilder());
    }

    /**
     * Merge the infromation from the given <code>.ui</code> file into the
     * current Builder tree.
     * 
     * @throws ParseException
     *             The underlying GtkBuilder library was unable to process the
     *             User Interface description in the given file.
     * @throws FileNotFoundException
     * 
     * @since 4.0.20
     */
    /*
     * You might regard it as somewhat excessive to test the file here, but
     * path errors (or rather, assumptions about what the current working
     * directory is turning out to wildly wrong) are so common that we help
     * out beginners by guarding.
     */
    public void addFromFile(String filename) throws FileNotFoundException, ParseException {
        final File target;

        if (filename == null) {
            throw new IllegalArgumentException();
        }

        target = new File(filename);

        if (!target.exists()) {
            throw new FileNotFoundException("\nCan't find the specified Glade UI file:\n"
                    + target.getAbsolutePath());
        }
        if (!target.canRead()) {
            throw new FileNotFoundException("\nThe specified Glade UI file,\n"
                    + target.getAbsolutePath() + "\nis not readable");
        }

        try {
            GtkBuilder.addFromFile(this, filename);
        } catch (GlibException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }

    /**
     * Merge the infromation from the given <code>.ui</code> file into the
     * current Builder tree.
     * 
     * @throws ParseException
     *             The underlying GtkBuilder library was unable to process the
     *             User Interface description in the given string.
     * @since 4.0.20
     */
    public void addFromString(String buffer) throws ParseException {
        try {
            GtkBuilder.addFromString(this, buffer, -1);
        } catch (GlibException e) {
            throw new ParseException(e.getMessage(), 0);
        }
    }

    /**
     * Get the Object corresponding to the given name.
     * 
     * @since 4.0.20
     */
    public Object getObject(String name) {
        if (name == null) {
            throw new IllegalArgumentException();
        }
        return GtkBuilder.getObject(this, name);
    }
}
