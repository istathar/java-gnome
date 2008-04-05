/*
 * SnapshotContext.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.freedesktop.cairo;

import org.gnome.gdk.EventExpose;
import org.gnome.gtk.Image;
import org.gnome.gtk.Snapshot;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * @author Andrew Cowie
 */
public abstract class SnapshotContext extends Snapshot
{
    public SnapshotContext(String suffix) {
        super(Context.class, suffix);
        final Image image;

        window = new Window();
        window.setDefaultSize(100, 100);
        window.setDecorated(false);

        image = new Image();
        window.add(image);
        window.showAll();

        image.connect(new Widget.EXPOSE_EVENT() {
            public boolean onExposeEvent(Widget source, EventExpose event) {
                final Context cr;

                cr = new Context(source.getWindow());
                draw(cr);

                return false;
            }
        });

    }

    protected abstract void draw(Context cr);
}
