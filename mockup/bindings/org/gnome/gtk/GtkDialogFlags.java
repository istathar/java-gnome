/*
 * GtkDialogFlags.java
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
package org.gnome.gtk;

import org.gnome.glib.Plumbing;

final class GtkDialogFlags extends Plumbing
{
    private GtkDialogFlags() {}

    static final int MODAL = get_flag_GTK_DIALOG_MODAL();

    private static native final int get_flag_GTK_DIALOG_MODAL();

    static final int DESTROY_WITH_PARENT = get_flag_GTK_DIALOG_DESTROY_WITH_PARENT();

    private static native final int get_flag_GTK_DIALOG_DESTROY_WITH_PARENT();

    static final int NO_SEPARATOR = get_flag_GTK_DIALOG_NO_SEPARATOR();

    private static native final int get_flag_GTK_DIALOG_NO_SEPARATOR();
}
