/*
 * ValidateSnapUtilities.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the suite it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

public class ValidateSnapshotUtilities extends TestCaseGtk
{
    public void testClassToTargetFilename() {
        assertEquals("doc/api/org/gnome/gtk/QuestionMessageDialog.png",
                Snapshot.targetFilenameFromClass(QuestionMessageDialog.class, null));
    }
}
