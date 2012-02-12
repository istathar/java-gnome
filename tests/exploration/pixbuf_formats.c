/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd and Others
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
