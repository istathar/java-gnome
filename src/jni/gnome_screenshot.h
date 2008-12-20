/*
 * gnome_screenshot.h
 *
 * Copyright (C) 2001-2006 Jonathan Blandford
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * and licenced under the terms of the "GNU General Public Licence, version
 * 2" only. This code is presented in java-gnome as wrapped in the class
 * org.gnome.screeshot.Screenshot; making use of this code path require you
 * to make your entire application available under a GPL compatible licence.
 */

#ifndef __GNOME_SCREENSHOT_H__
#define __GNOME_SCREENSHOT_H__

#include <gtk/gtk.h>
#include <gdk/gdkx.h>

/*
 * This is somewhat of a force, but my system configured this way. Since we
 * don't have the infrastructure to probe this sort of thing at present, we 
 * just assume we're on a modern and capable GNOME Desktop. If not, too bad.
 */
#define HAVE_X11_EXTENSIONS_SHAPE_H 1

extern gboolean screenshot_grab_lock(void);
extern void screenshot_release_lock(void);
extern GdkWindow* screenshot_find_current_window(void);
extern GdkPixbuf* screenshot_get_pixbuf(GdkWindow*, gboolean, gboolean);

extern void screenshot_add_shadow(GdkPixbuf**);
extern void screenshot_add_border(GdkPixbuf**);

#endif
