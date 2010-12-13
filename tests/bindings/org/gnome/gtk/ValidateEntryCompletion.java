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

/**
 * Test coverage of EntryCompletion object.
 * 
 * @author Guillaume Mazoyer
 * @author Andrew Cowie
 */
public class ValidateEntryCompletion extends GraphicalTestCase
{
    public final void testConstructor() {
        final Entry entry;
        final String[] words;
        final EntryCompletion completion;
        final DataColumnString column;
        final ListStore model;
        TreeIter row = null;

        entry = new Entry();
        completion = new EntryCompletion();

        model = new ListStore(new DataColumn[] {
            column = new DataColumnString()
        });

        completion.setModel(model);
        completion.setTextColumn(column);
        entry.setCompletion(completion);

        words = new String[] {
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqr",
            "stu",
            "vwx",
            "yz"
        };

        for (String word : words) {
            row = model.appendRow();
            model.setValue(row, column, word);
        }

        assertSame(String.class, column.getType());
        assertSame(0, column.getOrdinal());
        assertEquals("yz", model.getValue(row, column));
    }

    public final void testExtractModel() {
        final ListStore model;
        final EntryCompletion completion;
        final TreeModel extracted;

        completion = new EntryCompletion();
        assertNull(completion.getModel());

        model = new ListStore(new DataColumn[] {
            new DataColumnString()
        });

        completion.setModel(model);

        extracted = completion.getModel();
        assertNotNull(extracted);
        assertTrue(extracted instanceof ListStore);
        assertSame(model, extracted);
    }

    public final void testExtractEntry() {
        final Entry entry;
        final EntryCompletion completion;
        final Entry extracted;

        entry = new Entry();
        completion = new EntryCompletion();

        entry.setCompletion(completion);

        extracted = completion.getEntry();
        assertSame(entry, extracted);
    }

    public final void testCompleteEntry() {
        final Entry entry;
        final EntryCompletion completion;
        final DataColumnString column;
        final ListStore model;
        final String[] words;
        TreeIter row;

        entry = new Entry();
        completion = new EntryCompletion();

        model = new ListStore(new DataColumn[] {
            column = new DataColumnString()
        });

        completion.setModel(model);
        completion.setTextColumn(column);
        entry.setCompletion(completion);

        words = new String[] {
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqr",
            "stu",
            "vwx",
            "yz"
        };

        for (String word : words) {
            row = model.appendRow();
            model.setValue(row, column, word);
        }

        assertTrue(completion.getPopupCompletion());
        completion.setPopupCompletion(false);
        assertFalse(completion.getPopupCompletion());

        assertTrue(completion.getPopupSingleMatch());
        completion.setPopupSingleMatch(false);
        assertFalse(completion.getPopupSingleMatch());

        assertFalse(completion.getInlineCompletion());
        completion.setInlineCompletion(true);
        assertTrue(completion.getInlineCompletion());

        assertFalse(completion.getInlineSelection());
        completion.setInlineSelection(true);
        assertTrue(completion.getInlineSelection());
        completion.setInlineSelection(false);
        assertFalse(completion.getInlineSelection());

        entry.setText("a");
        completion.insertPrefix();
        assertEquals("a", entry.getText());

        completion.emitMatchSelected(model.getIterFirst());
        assertEquals("abc", entry.getText());
    }

    private TreeIter completionIter;

    public final void testMatching() {
        final Entry entry;
        final EntryCompletion completion;
        final DataColumnString column;
        final ListStore model;
        final String[] words;
        TreeIter row;

        entry = new Entry();
        completion = new EntryCompletion();

        model = new ListStore(new DataColumn[] {
            column = new DataColumnString()
        });

        completion.setModel(model);
        completion.setTextColumn(column);
        entry.setCompletion(completion);

        words = new String[] {
            "Sydney, Australia",
            "Toronto, Canada",
            "New York, USA",
            "London, UK"
        };

        for (String word : words) {
            row = model.appendRow();
            model.setValue(row, column, word);
        }

        completion.setMatchCallback(new EntryCompletion.Match() {
            public boolean onMatch(EntryCompletion source, String key, TreeIter iter) {
                final String text, lower;
                boolean result;

                text = model.getValue(iter, column);
                lower = text.toLowerCase();
                result = lower.contains(key);

                if (result) {
                    completionIter = iter;
                }

                return result;
            }
        });

        entry.setText("C");
        completion.complete();
        completion.insertPrefix();

        /*
         * Did the item we expected get selected?
         */

        assertNotNull(completionIter);
        completion.emitMatchSelected(completionIter);

        assertEquals("Toronto, Canada", entry.getText());
    }
}
