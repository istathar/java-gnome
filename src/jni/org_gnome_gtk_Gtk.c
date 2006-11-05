/*
 * org_gnome_gtk_Gtk.c
 *
 * Copyright (c) 2006 Operational Dynamics 
 * See LICENCE file for usage and redistribution terms
 */

#include <jni.h>
#include <gtk/gtk.h>

#include "org_gnome_gtk_Gtk.h"

/*
 * Implements
 *   org.gnome.gtk.Gtk.gtk_init()
 * called from
 *   org.gnome.gtk.Gtk.init()
 *
 * FIXME we still have to handle passing the args array through.
 */ 
JNIEXPORT void JNICALL
Java_org_gnome_gtk_Gtk_gtk_1init
  (JNIEnv *env, jclass cls)
{
	// call function
	gtk_init(0, NULL);
}


/*
 * Implements
 *   org.gnome.gtk.Gtk.gtk_main()
 * called from
 *   org.gnome.gtk.Gtk.main()
 */
JNIEXPORT void JNICALL
Java_org_gnome_gtk_Gtk_gtk_1main
  (JNIEnv *env, jclass cls)
{
	// call function
	gtk_main();
}

/*
 * Implements
 *   org.gnome.gtk.Gtk.gtk_main_quit()
 * called from
 *   org.gnome.gtk.Gtk.mainQuit()
 */
JNIEXPORT void JNICALL
Java_org_gnome_gtk_Gtk_gtk_1main_1quit
  (JNIEnv *env, jclass cls)
{
	// call function
	gtk_main_quit();
}
