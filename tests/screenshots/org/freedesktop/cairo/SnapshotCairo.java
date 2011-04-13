/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2011 Operational Dynamics Consulting, Pty Ltd
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
 */
package org.freedesktop.cairo;

import org.gnome.gtk.Image;
import org.gnome.gtk.Snapshot;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * @author Andrew Cowie
 */
public abstract class SnapshotCairo extends Snapshot
{
    public SnapshotCairo(Class<?> cls, String suffix) {
        super(cls, suffix);
        final Image image;

        window = new Window();
        window.setDefaultSize(100, 100);
        window.setDecorated(false);

        image = new Image();
        window.add(image);
        window.showAll();

        image.connect(new Widget.Draw() {
            public boolean onDraw(Widget source, Context cr) {
                draw(cr);
                return false;
            }
        });
    }

    protected abstract void draw(Context cr);
}
