/*
 * org_gnome_glib_GObject.c
 *
 * Copyright (c) 2006 Operational Dynamics 
 * See LICENCE file for usage and redistribution terms
 */
 
#include <glib.h>
#include <glib-object.h>
#include <jni.h>
#include "org_gnome_glib_GBoxed.h"

/*
 * Implements
 *   org.gnome.glib.GBoxed.g_boxed_free(long boxed)
 * called from
 *   org.gnome.glib.GBoxed.free(Boxed reference)
 * called from
 *   org.gnome.glib.Boxed.release()
 *
 * This is where we free a GBoxed if we're the owner of it.
 */
// nothing here
