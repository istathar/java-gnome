/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
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
 *
 * Linking this library statically or dynamically with other modules is making
 * a combined work based on this library. Thus, the terms and conditions of
 * the GPL cover the whole combination. As a special exception (the
 * "Classpath Exception"), the copyright holders of this library give you
 * permission to link this library with independent modules to produce an
 * executable, regardless of the license terms of these independent modules,
 * and to copy and distribute the resulting executable under terms of your
 * choice, provided that you also meet, for each linked independent module,
 * the terms and conditions of the license of that module. An independent
 * module is a module which is not derived from or based on this library. If
 * you modify this library, you may extend the Classpath Exception to your
 * version of the library, but you are not obligated to do so. If you do not
 * wish to do so, delete this exception statement from your version.
 */

#include <jni.h>
#include <gtk/gtk.h>
#include "bindings_java.h"
#include "org_gnome_gtk_GtkMenuOverride.h"

/**
 * This is hand written to deal with the fact that in conventional use all
 * the arguments to gtk_menu_popup() are completely useless.
 */
JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkMenuOverride_gtk_1menu_1popup
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	GtkMenu* self;

	// convert parameter self
	self = (GtkMenu*) _self;

	// call function
	gtk_menu_popup(self, NULL, NULL, NULL, NULL, 0, gtk_get_current_event_time());
	
	// cleanup parameter self
}

/*
 * Call gtk_menu_popup(), but hardwired to use
 * gtk_status_icon_position_menu() as the (*GtkMenuPositionFunc).
 */
JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkMenuOverride_gtk_1menu_1popup_1status_1icon
(
	JNIEnv* env,
	jclass cls,
	jlong _self,
	jlong _status
)
{
	GtkMenu* self;
	GtkStatusIcon* status;

	// convert parameter self
	self = (GtkMenu*) _self;

	// convert parameter status
	status = (GtkStatusIcon*) _status;

	// call function
	gtk_menu_popup(self, NULL, NULL, gtk_status_icon_position_menu, status, 0, gtk_get_current_event_time());
	
	// cleanup parameter self
}

typedef struct {
	gint x;
	gint y;
} Pair;

/**
 * Return the x and y variables passed when this was constructed. This is
 * just some hoop jumping to get around a) not really wanting to create a
 * custom menu position function callback signal, and b) there not being a
 * straight forward "position at location" call in GtkMenu.
 */
/*
 * Matches the signature of (*GtkMenuPositionFunc) as required by the fourth
 * parameter of gtk_menu_popup().
 */
static void
fixed_position
(
	GtkMenu *menu,
	gint *x,
	gint *y,
	gboolean *push_in,
	gpointer user_data
)
{
	Pair* pair;

	pair = (Pair*) user_data;
	
	*x = pair->x;
	*y = pair->y;
}

/**
 * A special case of popup() where we specifiy x,y [ie, in response to a Menu
 * key press.
 */
/*
 * DANGER is it acceptable to use a stack allocated Pair struct? I think so;
 * the call to popup appears to be straight ahead (and this doesn't crash).
 * Otherwise we need to heap allocate and somehow clean up afterwards.
 */
JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkMenuOverride_gtk_1menu_1popup_1at_1position
(
	JNIEnv* env,
	jclass cls,
	jlong _self,
	jint _x,
	jint _y
)
{
	GtkMenu* self;
	gint x;
	gint y;
	Pair pair = { 0, };

	// convert parameter self
	self = (GtkMenu*) _self;

	// convert parameter x
	x = (gint) _x;

	// convert parameter y
	y = (gint) _y;

	/*
	 * Setup a struct to carry the two variables
	 */

	pair.x = x;
	pair.y = y;

	// call function
	gtk_menu_popup(self, NULL, NULL, fixed_position, &pair, 0, gtk_get_current_event_time());
	
	// cleanup parameter self
}

