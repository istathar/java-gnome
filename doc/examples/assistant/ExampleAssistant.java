/*
 * ExampleAssistant.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package assistant;

import java.io.FileNotFoundException;

import org.gnome.gdk.Event;
import org.gnome.gdk.Pixbuf;
import org.gnome.gtk.Assistant;
import org.gnome.gtk.AssistantPageType;
import org.gnome.gtk.Entry;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.Label;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

public class ExampleAssistant implements Assistant.Cancel, Assistant.Apply, Assistant.Close,
        Assistant.Prepare
{

    Label page1, page3;

    Entry id_tf;

    String input;

    public static void main(String[] args) {
        Gtk.init(args);
        new ExampleAssistant();
        Gtk.main();
    }

    public ExampleAssistant() {
        Window win = new Window();
        win.setTitle("ExampleAssistant");
        win.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return true;
            }
        });

        final Assistant assi = new Assistant();
        assi.connect((Assistant.Cancel) this);
        assi.connect((Assistant.Prepare) this);
        assi.connect((Assistant.Apply) this);
        assi.connect((Assistant.Close) this);

        // Page 1
        page1 = new Label("This is a introductionary page.");
        assi.appendPage(page1);
        assi.setPageType(page1, AssistantPageType.INTRO);
        assi.setPageTitle(page1, "This is the page title");
        try {
            assi.setPageSideImage(page1, new Pixbuf("doc/examples/assistant/gnome-about-logo.png"));
            assi.setPageHeaderImage(page1, new Pixbuf("doc/examples/assistant/adobe.pdf.png"));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assi.setPageComplete(page1, true);

        // Page 2
        final VBox page2 = new VBox(false, 3);
        id_tf = new Entry();
        id_tf.connect(new Entry.Activate() {
            public void onActivate(Entry source) {
                input = id_tf.getText();
                if (input.length() > 0) {
                    assi.setPageComplete(page2, true);
                }
            }
        });
        page2.packStart(new Label("Enter any data"));
        page2.packStart(new Label("The 'next' button will be enabled after\nyou hit return."));
        page2.packEnd(id_tf);
        assi.appendPage(page2);
        assi.setPageType(page2, AssistantPageType.CONTENT);
        assi.setPageTitle(page2, "Enter data");

        // Page 3
        page3 = new Label("");
        assi.appendPage(page3);
        assi.setPageType(page3, AssistantPageType.CONFIRM);
        assi.setPageTitle(page3, "Done");
        assi.setPageComplete(page3, true);

        assi.showAll();
    }

    public void onCancel(Assistant source) {
        System.out.println("onCancel");
        Gtk.mainQuit();
    }

    public void onApply(Assistant source) {
        System.out.println("onApply");
        Gtk.mainQuit();
    }

    public void onClose(Assistant source) {
        System.out.println("onClose");
    }

    public void onPrepare(Assistant source, Widget page) {
        System.out.println("onPrepare");
        if (page == page3) {
            page3.setLabel("This is the confirmation page.\nYou entered: " + input);
        }
    }
}
