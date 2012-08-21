/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2009-2010 Operational Dynamics Consulting, Pty Ltd
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
 * Test Compose key usage
 *
 *    gcc `pkg-config --cflags --libs gtk+-2.0` -o compose compose.c
 *
 * ought to build this for you.
 */

#include <gtk/gtk.h>
#include <gdk/gdkkeysyms.h>

static gboolean
keystroke_cb
(
	GtkWidget* widget,
	GdkEventKey* event,
	gpointer user_data
)
{
	GtkIMContext* im;

	im = (GtkIMContext*) user_data;

	if (gtk_im_context_filter_keypress(im, event)) {
		return TRUE;
	}
	return FALSE;
}

static void
commit_cb
(
	GtkIMContext* im,
	gchar* str,
	gpointer user_data
)
{
	g_print("%s\n", str);
	gtk_main_quit();
}

int
main
(
	int argc,
	char **argv
)
{
	GtkWindow* window;
	GtkDrawingArea* area;
	GtkIMContext* im;

	gtk_init(&argc, &argv);

	window = (GtkWindow*) gtk_window_new(GTK_WINDOW_TOPLEVEL);
	area = (GtkDrawingArea*) gtk_drawing_area_new();
        GTK_WIDGET_SET_FLAGS(area, GTK_CAN_FOCUS);

	gtk_container_add((GtkContainer*) window, (GtkWidget*) area);

	im = gtk_im_context_simple_new();

	g_signal_connect(area, "key-press-event", G_CALLBACK(keystroke_cb), im);
	g_signal_connect(area, "key-release-event", G_CALLBACK(keystroke_cb), im);
	g_signal_connect(im, "commit", G_CALLBACK(commit_cb), NULL);

	gtk_widget_show_all((GtkWidget*) window);

	/*
	 * Get the Window on screen and let GTK settle.
	 */

	while (gtk_events_pending()) {
		gtk_main_iteration();
	}

	/*
	 * Generate the compose sequence.
	 */

	gtk_test_widget_send_key((GtkWidget*) area, GDK_Multi_key, 0);
	gtk_test_widget_send_key((GtkWidget*) area, GDK_e, 0);
	gtk_test_widget_send_key((GtkWidget*) area, GDK_equal, 0);

	/*
	 * Will return if and only if commit is hit.
	 */

	gtk_main();

	return 0;
}
