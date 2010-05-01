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
	printf("%d\n", 1 << 4);
	printf("%d\n", 1 << 8);
	printf("%d\n", 1 << 14);
	printf("%d\n", 1 << 30);

	return 0;
}
