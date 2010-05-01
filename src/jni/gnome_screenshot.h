/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2001-2006 Jonathan Blandford
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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

/*
 * This code is presented in java-gnome as wrapped in the class
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
