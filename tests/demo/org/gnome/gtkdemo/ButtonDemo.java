/*
 * ButtonDemo.java
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
import org.gnome.gtk.Button;
import org.gnome.gtk.Label;
import org.gnome.gtk.ToggleButton;
import org.gnome.gtk.CheckButton;

/**
 * Demonstration of the Button widget
 */
public class ButtonDemo implements DemoPanel {
        
    
    public Widget getWidget() {
        VBox v = new VBox(false, 50);

        Button button = new Button("Click me");
        button.connect(new Button.CLICKED() {
                public void onClicked(Button source) {
                    source.setLabel("clicked!");
                }
            });
        v.packStart(button);
        
        Button labelButton = new Button();
        Label markupLabel = new Label("<markup>Button with <span foreground=\"blue\" size=\"large\"><b>blue</b></span> Label</markup>");
        markupLabel.setUseMarkup(true);
        labelButton.add(markupLabel);
        v.packStart(labelButton);

        ToggleButton toggleButton = new ToggleButton("_toggle Button");
        toggleButton.connect(new ToggleButton.TOGGLED() {
                public void onToggled(ToggleButton source) {
                    source.setLabel("_toggle Button activated: "+source.getActive());
                }
            });
        v.packStart(toggleButton);


        CheckButton checkButton = new CheckButton("_check Button");
        checkButton.connect(new CheckButton.TOGGLED() {
                public void onToggled(ToggleButton source) {
                    source.setLabel("_check Button activated: "+source.getActive());
                }
            });
        v.packStart(checkButton);
        
        return v;
    }
}