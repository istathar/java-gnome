/*
 * compose.c
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd 
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
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
