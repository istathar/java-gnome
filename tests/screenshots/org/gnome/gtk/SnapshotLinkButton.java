/*
 * SnapshotLinkButton.java
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
        final LinkButton link1, link2;
        final Label label1, label2;

        window = new Window();
        window.setDecorated(false);
        window.setBorderWidth(6);

        x = new VBox(true, 6);
        
        label1 = new Label("LinkButton with URI as label");
        x.packStart(label1, false, false, 0);

        link1 = new LinkButton(new URI("http://java-gnome.sourceforge.net"));
        x.packStart(link1, false, false, 0);
        
        label2 = new Label("A visited LinkButton with a custom label");
        x.packStart(label2, false, false, 0);
        
        link2 = new LinkButton(new URI("http://java-gnome.sourceforge.net"),"Java-Gnome");
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
