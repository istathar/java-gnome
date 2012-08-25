/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
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
 * List the available font descriptions, by string name
 *
 *    gcc `pkg-config --cflags --libs gtk+-2.0` -o fonts fonts.c
 *
 * ought to build this for you.
 */

#include <gtk/gtk.h>

int main(int argc, char **argv) {
	PangoFontMap* map;
	PangoFontFamily **families;
	int n_families;
	int i;
	PangoFontFace **faces;
	int n_faces;
	int j;

	gtk_init(&argc, &argv);

	map = pango_cairo_font_map_get_default();

	pango_font_map_list_families(map, &families, &n_families);

	for (i = 0; i < n_families; i++) {
		g_print("%s\n", pango_font_family_get_name(families[i]));

		pango_font_family_list_faces(families[i], &faces, &n_faces);
		for (j = 0; j < n_faces; j++) {
			g_print("  %s\n", pango_font_face_get_face_name(faces[j]));
		}
	}

	return 0;
}
