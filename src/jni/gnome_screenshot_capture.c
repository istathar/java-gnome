/*
 * gnome_screenshot_capture.c
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 *
 * The code to capture an X11 Window as a GdkPixbuf is taken from gnome-utils's
 * gnome-screenshot/gnome-screenshot.c,
 *
 * Copyright (C) 2001-2005 Jonathan Blandford
 * Copyright (C) 2006-2008 Emmanuele Bassi
 * Copyright (C) 2008      Cosimo Cecchi
 *
 * and licenced under the terms of the "GNU General Public Licence, version
 * 2" only. This code is presented in java-gnome as wrapped in the class
 * org.gnome.screeshot.Screenshot; making use of this code path require you
 * to make your entire application available under a GPL compatible licence.
 */

#include <jni.h>
#include <gtk/gtk.h>
#include "bindings_java.h"
#include "gnome_screenshot.h"

/*
 * This basically is a slimmed down and java-gnome adapted version of the
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
	GdkWindow* win;
	GdkPixbuf* result;
	JNIEnv* env;

 	if (!screenshot_grab_lock()) {
 		env = bindings_java_getEnv();
 		bindings_java_throw(env, "Unable to take grab screenshot lock");
		return 0L;
	}
 		
    	if (take_window_shot) {
		win = screenshot_find_current_window();
		if (!win) {
			take_window_shot = FALSE;
			win = gdk_get_default_root_window();
		}
	} else {
		win = gdk_get_default_root_window();

	}

	if (take_window_shot) {
		result = screenshot_get_pixbuf(win, FALSE, TRUE);

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
  	} else {
		result = screenshot_get_pixbuf(win, FALSE, FALSE);
	}



	screenshot_release_lock();

	if (result == NULL) {
		env = bindings_java_getEnv();
		bindings_java_throw(env, "Unable to take a screenshot of the current window");
		return 0L;
	}

	// and finally
	return result;
}
