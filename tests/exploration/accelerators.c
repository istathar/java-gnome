/*
 * accelerators.c
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */

/*
 * Experiment with GTK Accelerator Groups.
 *
 *    gcc `pkg-config --cflags --libs gtk+-2.0` -o accelerators accelerators.c
 *
 * ought to build this for you.
 */

#include <gtk/gtk.h>
#include <gdk/gdkkeysyms.h>

int main(int argc, char **argv) {
	gtk_init(&argc, &argv);

	printf("%s\n", gtk_accelerator_name(GDK_q, GDK_CONTROL_MASK));
	printf("%s\n", gtk_accelerator_get_label(GDK_q, GDK_CONTROL_MASK));

	return 0;
}
