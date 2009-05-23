/*
 * ExampleEditor.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package sourceview;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.gnome.gdk.Event;
import org.gnome.gtk.Gtk;
import org.gnome.gtk.PolicyType;
import org.gnome.gtk.ScrolledWindow;
import org.gnome.gtk.Stock;
import org.gnome.gtk.TextBuffer;
import org.gnome.gtk.TextTagTable;
import org.gnome.gtk.ToolButton;
import org.gnome.gtk.Toolbar;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;
import org.gnome.sourceview.LanguageManager;
import org.gnome.sourceview.SourceBuffer;
import org.gnome.sourceview.SourceView;

/**
 * A simple text editor demonstrating the GtkSourceView API.
 * 
 * @author Stefan Schweizer
 */
public class ExampleEditor
{
    private final SourceView view;

    private final SourceBuffer buffer;

    private final ToolButton buttonUndo;

    private final ToolButton buttonRedo;

    private ExampleEditor() {
        final Window w;
        final VBox x;
        final Toolbar toolbar;
        final ScrolledWindow scroll;
        final TextTagTable table;

        w = new Window();
        w.setTitle("java-gnome editor");
        w.setDefaultSize(550, 600);

        w.connect(new Window.DeleteEvent() {
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return false;
            }
        });

        x = new VBox(false, 3);
        w.add(x);

        /*
         * Create a toolbar with buttons for undo and redo.
         */

        toolbar = new Toolbar();
        buttonUndo = new ToolButton(Stock.UNDO);
        buttonRedo = new ToolButton(Stock.REDO);
        toolbar.add(buttonUndo);
        toolbar.add(buttonRedo);
        x.packStart(toolbar, false, false, 0);

        /*
         * Create the SourceBuffer and SourceView. Setup syntax highlighting
         * and configure the view to meet our coding standards.
         */

        table = new TextTagTable();
        buffer = new SourceBuffer(table);
        buffer.setLanguage(LanguageManager.getDefault().getLanguage("java"));

        view = new SourceView(buffer);
        view.setShowLineNumbers(true);
        view.setHighlightCurrentLine(true);
        view.setShowRightMargin(true);
        view.setRightMarginPosition(105);
        view.setTabWidth(4);
        view.setInsertSpacesInsteadOfTabs(true);
        view.setAutoIndent(true);

        scroll = new ScrolledWindow();
        scroll.setPolicy(PolicyType.AUTOMATIC, PolicyType.ALWAYS);
        scroll.add(view);
        x.add(scroll);

        /*
         * Whenever the buffer is changed, recheck if there are actions that
         * can be undone or redone. Update the sensivity of the buttons
         * accordingly.
         */

        buffer.connect(new TextBuffer.Changed() {
            public void onChanged(TextBuffer source) {
                updateButtons();
            }
        });

        /*
         * Connect handlers for undo and redo buttons. It is important to
         * check if there is something to undo first to prevent a Gtk
         * exception.
         */

        buttonUndo.connect(new ToolButton.Clicked() {
            public void onClicked(ToolButton source) {
                if (buffer.canUndo()) {
                    buffer.undo();
                }
                updateButtons();
            }
        });

        buttonRedo.connect(new ToolButton.Clicked() {
            public void onClicked(ToolButton source) {
                if (buffer.canRedo()) {
                    buffer.redo();
                }
                updateButtons();
            }
        });

        w.showAll();

        /*
         * Load the source file into the editor. The user should not be able
         * to undo that, that is why the call to setText() is marked as an
         * undoable action.
         */

        buffer.beginNotUndoableAction();
        buffer.setText(readFile("doc/examples/sourceview/ExampleEditor.java"));
        buffer.endNotUndoableAction();

        /*
         * Move the cursor to the start of the file.
         */

        buffer.placeCursor(buffer.getIter(0));
        view.grabFocus();
    }

    private void updateButtons() {
        buttonUndo.setSensitive(buffer.canUndo());
        buttonRedo.setSensitive(buffer.canRedo());
    }

    private String readFile(String filename) {
        final StringBuilder content;
        BufferedReader reader = null;

        content = new StringBuilder();

        try {
            reader = new BufferedReader(new FileReader(filename));
            for (int c = reader.read(); c != -1; c = reader.read()) {
                content.append((char) c);
            }
        } catch (IOException ioe) {
            content.append(ioe.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
            }
        }

        return content.toString();
    }

    public static void main(String[] args) {
        Gtk.init(args);
        new ExampleEditor();
        Gtk.main();
    }
}
