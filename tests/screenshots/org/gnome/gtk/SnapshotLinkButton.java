/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
package org.gnome.gtk;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author Serkan Kaba
 */
public class SnapshotLinkButton extends Snapshot
{
    public SnapshotLinkButton() throws URISyntaxException {
        super(LinkButton.class);

        final VBox x;
        final LinkButton link1, link2;
        final Label label1, label2;

        window = new Window();
        window.setDecorated(false);
        window.setBorderWidth(6);

        x = new VBox(true, 6);

        label1 = new Label("LinkButton with only a URI as its label");
        x.packStart(label1, false, false, 0);

        link1 = new LinkButton(new URI("http://java-gnome.sourceforge.net/"));
        x.packStart(link1, false, false, 0);

        label2 = new Label("A visited LinkButton with a text label");
        x.packStart(label2, false, false, 0);

        link2 = new LinkButton(new URI("http://java-gnome.sourceforge.net/"), "The java-gnome website");
        link2.setVisited(true);
        x.packStart(link2, false, false, 0);

        window.add(x);
    }

    public static void main(String[] args) throws URISyntaxException {
        Gtk.init(args);
        runExample(new SnapshotLinkButton());
        Gtk.main();
    }
}
