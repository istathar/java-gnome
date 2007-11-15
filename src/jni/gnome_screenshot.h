/*
 * gnome_screenshot.h
 *
 * Copyright (C) 2001-2006 Jonathan Blandford
 * Copyright (c) 2007-     Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */

#ifndef __GNOME_SCREENSHOT_H__
#define __GNOME_SCREENSHOT_H__

#include <gtk/gtk.h>
#include <gdk/gdkx.h>

extern gboolean screenshot_grab_lock(void);
extern void screenshot_release_lock(void);
extern gchar* screenshot_get_window_title(Window);
extern Window screenshot_find_current_window (gboolean);
extern GdkPixbuf* screenshot_get_pixbuf(Window);

extern void screenshot_add_shadow(GdkPixbuf**);
extern void screenshot_add_border(GdkPixbuf**);

#endif
