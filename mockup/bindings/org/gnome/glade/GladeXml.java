/*
 * GladeXml.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 *
 *                      THIS FILE WILL BE GENERATED CODE!
 *
 * To modify its contents or behaviour, either update the generation program,
 * change the information in the source defs file, or implement an override
 * for this class.
 */
package org.gnome.glade;

import org.gnome.glib.Plumbing;
import org.gnome.gtk.Widget;

final class GladeXml extends Plumbing
{
    private GladeXml() {}

    static final long createGladeXml(String filename, String root) {
        synchronized (lock) {
            return glade_xml_new(filename, root);
        }
    }

    private static native final long glade_xml_new(String filename, String root);

    static final Widget getWidget(Xml self, String name) {
        synchronized (lock) {
            return (Widget) objectFor(glade_xml_get_widget(pointerOf(self), name));
        }
    }

    private static native final long glade_xml_get_widget(long xml, String name);
}
