/*
 * SnapshotArrow.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
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
        final LinkButton l1, l2;

        window = new Window();
        window.setDecorated(false);
        window.setBorderWidth(6);

        x = new VBox(true, 6);

        l1 = new LinkButton(new URI("http://java-gnome.sourceforge.net"));
        x.packStart(l1, false, false, 0);
        
        l2 = new LinkButton(new URI("http://java-gnome.sourceforge.net"),"Java-Gnome");
        l2.setVisited(true);
        x.packStart(l2, false, false, 0);

        window.add(x);
    }

    public static void main(String[] args) throws URISyntaxException {
        Gtk.init(args);
        runExample(new SnapshotLinkButton());
        Gtk.main();
    }
}
