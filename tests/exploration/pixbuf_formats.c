/*
 * pixbuf_formats.c
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */

/*
 * Code cut from the GdkPixbuf documentation to determine the file types
 * available 
 *
 *    gcc `pkg-config --cflags --libs gtk+-2.0` -o enums enums.c
 *
 * ought to build this for you.
 */

#include <gtk/gtk.h>

void add_if_writable(gpointer data, gpointer user_data) {
	GdkPixbufFormat* format;

	format = (GdkPixbufFormat*) data;
	
	g_print("%s\t", gdk_pixbuf_format_get_name(format));
	if (gdk_pixbuf_format_is_writable(format)) {
		g_print("rw\t");
	} else {
		g_print("r\t");
	}
	g_print("%s\n", gdk_pixbuf_format_get_description(format));
}

int main(int argc, char **argv) {
	gtk_init(&argc, &argv);

	GSList *formats = gdk_pixbuf_get_formats();
	g_slist_foreach(formats, add_if_writable, NULL);
	g_slist_free(formats);

	return 0;
}
