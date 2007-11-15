/*
 * Screenshot.c
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code to capture an X11 Window as a GdkPixbuf is taken from gnome-utils's
 * gnome-screenshot/gnome-screenshot.c,
 *
 * Copyright (C) 2001 Jonathan Blandford <jrb@alum.mit.edu>
 * Copyright (C) 2006 Emmanuele Bassi <ebassi@gnome.org>
 *
 * and relicenced with their permission for inclusion in java-gnome.
 * Therefore, 
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */

#include <jni.h>
#include <gtk/gtk.h>

#include <gdk/gdkx.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <X11/Xutil.h>

#include "bindings_java.h"
#include "gnome_screenshot.h"

/*
 * This basically is a slimmed down and java-gnome adapted verison of the
 * code from gnome-utils/gnome-screenshot/gnome-screenshot.c's
 * prepare_screenshot() function.
 */
GdkPixbuf*
gnome_screenshot_capture
(
	gboolean take_window_shot,
 	gboolean include_border,
    	const gchar* border_effect
)
{
    	Window win;
	GdkPixbuf* result;
	JNIEnv* env;

 	if (!screenshot_grab_lock()) {
 		env = bindings_java_getEnv();
 		bindings_java_throw(env, "Unable to take grab screenshot lock");
		return 0L;
	}
 		
    	if (take_window_shot) {
		win = screenshot_find_current_window(include_border);
		if (win == None) {
			take_window_shot = FALSE;
			win = GDK_ROOT_WINDOW();
		}
	} else {
		win = GDK_ROOT_WINDOW ();
	}

	result = screenshot_get_pixbuf(win);

	if (take_window_shot) {
		switch (border_effect[0]) {
		case 's': /* shadow */
			screenshot_add_shadow(&result);
        		break;
		case 'b': /* border */
			screenshot_add_border(&result);
			break;
		case 'n': /* none */
		default:
			break;
		}
  	}

	screenshot_release_lock();

	if (result == NULL) {
		env = bindings_java_getEnv();
		bindings_java_throw(env, "Unable to take a screenshot of the current window");
		return 0L;
	}

	// and finally
	return (jlong) result;
}
