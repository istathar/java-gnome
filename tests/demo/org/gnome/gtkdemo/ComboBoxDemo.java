/*
 * ComboBoxDemo.java
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

import org.gnome.gtk.Widget;
import org.gnome.gtk.VBox;
import org.gnome.gtk.ComboBox;
import org.gnome.gtk.Label;


/**
 * Demonstration of the ComboBox widget
 */
public class ComboBoxDemo implements DemoPanel {
        
    
    public Widget getWidget() {
        VBox v = new VBox(false, 50);
        
        final Label selectedText = new Label("");
        v.packStart(selectedText);

        final ComboBox combo = new ComboBox(true);
        combo.appendText("item 3");
        combo.appendText("item 4");
        combo.prependText("item 1");
        combo.insertText(1, "item 2");
        combo.setActive(3);
        
        combo.connect(new ComboBox.CHANGED() {
                public void onChanged(ComboBox sourceObject) {
                    selectedText.setLabel("selected item ("+sourceObject.getActive()+"): "+sourceObject.getActiveText());
                }
            });
        v.packStart(combo);

        return v;
    }
}