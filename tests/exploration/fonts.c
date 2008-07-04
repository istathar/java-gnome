/*
 * fonts.c
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd 
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
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
