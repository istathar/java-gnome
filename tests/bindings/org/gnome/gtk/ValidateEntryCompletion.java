/*
 * ValidateEntryCompletion.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

/**
 * Test coverage of EntryCompletion object.
 * 
 * @author Guillaume Mazoyer
 */
public class ValidateEntryCompletion extends TestCaseGtk
{
    public final void testConstructor() {
        final Entry entry;
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

        String[] words = {
                "abc", "def", "ghi", "jkl", "mno", "pqr", "stu", "vwx", "yz"
        };

        for (String word : words) {
            row = model.appendRow();
            model.setValue(row, column, word);
        }

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
                "abc", "def", "ghi", "jkl", "mno", "pqr", "stu", "vwx", "yz"
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

        completion.complete();
        completion.insertPrefix();
        assertEquals("abc", entry.getText());
    }
}
