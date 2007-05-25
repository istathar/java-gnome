/*
 * enums.c
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */

/*
 * One of the main hypothesis in the design of the new java-gnome 4.0 bindings
 * is that enums in C are sequential from 0, and that the #defined constants
 * in the various GLib and GTK header files are enums and hence sequential.
 *
 * This little file tests that assumption. If you compile and run this, you
 * will hopefully see a nice sequence, starting at 0!
 *
 *    gcc `pkg-config --cflags --libs gtk+-2.0` -o enums enums.c
 *
 * ought to build this for you.
 */

#include <gtk/gtk.h>

int main(int argc, char **argv) {
	gtk_init(&argc, &argv);

	printf("%d\n", GTK_SCROLL_NONE);
	printf("%d\n", GTK_SCROLL_JUMP);
	printf("%d\n", GTK_SCROLL_STEP_BACKWARD);
	printf("%d\n", GTK_SCROLL_STEP_FORWARD);
	printf("%d\n", GTK_SCROLL_PAGE_BACKWARD);
	printf("%d\n", GTK_SCROLL_PAGE_FORWARD);
	printf("%d\n", GTK_SCROLL_STEP_UP);
	printf("%d\n", GTK_SCROLL_STEP_DOWN);
	printf("%d\n", GTK_SCROLL_PAGE_UP);
	printf("%d\n", GTK_SCROLL_PAGE_DOWN);
	printf("%d\n", GTK_SCROLL_STEP_LEFT);
	printf("%d\n", GTK_SCROLL_STEP_RIGHT);
	printf("%d\n", GTK_SCROLL_PAGE_LEFT);
	printf("%d\n", GTK_SCROLL_PAGE_RIGHT);
	printf("%d\n", GTK_SCROLL_START);
	printf("%d\n", GTK_SCROLL_END);


	printf("\n");
	printf("%d\n", 1 << 0);
	printf("%d\n", 1 << 1);
	printf("%d\n", 1 << 2);
	printf("%d\n", 1 << 3);

	return 0;
}
