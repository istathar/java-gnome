/*
 * Start.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtkdemo;

import org.gnome.gtk.Gtk;

/**
 * Start class for the GTK demo application
 */
public class Start {

    public static void main(String args[]) {
		Gtk.init(args);
        MainFrame mainFrame = new MainFrame();

        mainFrame.add(new LabelDemo());
        mainFrame.add(new ButtonDemo());
        mainFrame.add(new SeparatorDemo());
        mainFrame.add(new EntryDemo());
        mainFrame.add(new TextViewDemo());
        mainFrame.add(new ComboBoxDemo());
        mainFrame.add(new EventDemo());

        mainFrame.showAll();
		Gtk.main();
    } 
}