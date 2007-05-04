/*
 * GtkDialogFlags.c
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

#include <jni.h>
#include <gtk/gtk.h>

#include "org_gnome_gtk_GtkDialogFlags.h"

JNIEXPORT jint JNICALL 
Java_org_gnome_gtk_GtkDialogFlags_get_1flag_1GTK_1DIALOG_1MODAL 
(
	JNIEnv *env,
	jclass cls
)
{
	return GTK_DIALOG_MODAL;
}

JNIEXPORT jint JNICALL 
Java_org_gnome_gtk_GtkDialogFlags_get_1flag_1GTK_1DIALOG_1DESTROY_1WITH_1PARENT 
(
	JNIEnv *env,
	jclass cls
)
{
	return GTK_DIALOG_DESTROY_WITH_PARENT;
}

JNIEXPORT jint JNICALL 
Java_org_gnome_gtk_GtkDialogFlags_get_1flag_1GTK_1DIALOG_1NO_1SEPARATOR 
(
	JNIEnv *env,
	jclass cls
)
{
	return GTK_DIALOG_NO_SEPARATOR;
}
