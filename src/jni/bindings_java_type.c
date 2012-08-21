/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
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

/*
 * Thanks to the guidance from Mathias Hasselmann to protect g_str_equal(),
 * and to Manish Singh for suggesting the technique of using a GBoxed to
 * manage the reference count to a Java side object being stored in a
 * TreeModel, and for locating the code to implement this in pygobject's
 * gobject/gobjectmodule.c and gobject/pygtype.c
 */

#include <jni.h>
#include <glib.h>
#include "bindings_java.h"

/*
 * Create a GBoxed type to hold Java references. The actual "structure" being
 * boxed is just a gpointer. When the GBoxed is free'd, the reference will be
 * dropped, all neat and tidy.
 *
 * Using a pointer as a GBoxed is somewhat of a sharp shortcut, but it is
 * viable: GBoxeds are treated as pointers by the g_value_*() functions, and
 * since we control the meaning of the _copy() and _free() functions it's all
 * under control. Beware, however, the problem that arose in using the pointer
 * value in the getter case; see the native implementation of
 * GtkTreeModelOverride.gtk_tree_model_get_reference() for further details.
 */

GType BINDINGS_JAVA_TYPE_REFERENCE = 0;

/**
 * Called as a result of GTK attempting to store a GValue into a GtkListStore
 * column of type BINDNGS_JAVA_REFERENCE.
 *
 * Increment the global reference count on the actual Java object that we are
 * storing in a TreeModel. We go to the trouble of doing this because JNI
 * global references are pinned and not arbitrarily moved by the garbage
 * collector (else the stored pointer might become invalid if moved between
 * generations).
 */
/*
 * Meets the signature of (*GBoxedCopyFunc).
 */
static gpointer
bindings_java_reference_copy
(
	gpointer _boxed
)
{
	JNIEnv* env;
	jobject result, reference;

	env = bindings_java_getEnv();
	reference = (jobject) _boxed;

	result = (*env)->NewGlobalRef(env, reference);

	return (gpointer) result;
}

/*
 * Meets the signature requirement of (*GBoxedFreeFunc)
 */
static void
bindings_java_reference_free
(
	gpointer _boxed
)
{
	JNIEnv* env;
	jobject reference;
	
	env = bindings_java_getEnv();
	reference = (jobject) _boxed;

	(*env)->DeleteGlobalRef(env, reference);
}


/**
 * Called from
 *   Java_org_gnome_gtk_GtkTreeModelOverride_gtk_1list_1store_1new
 * called from
 *   org.gnome.gtk.GtkTreeModeOverride.gtk_list_store_new(String[])
 *
 * Map from a fully qualified class name string to a GType. Also initialize
 * our custom GBoxed if called upon to store a DataColumnReference
 *
 * So long as this is a short list, do it in an if/else ladder. Switch to a
 * GHashTable if it
 * becomes a performance bottleneck.
 */
GType
bindings_java_type_lookup
(
	const gchar* fqcn
)
{
	g_assert(fqcn != NULL);

	if (g_str_equal(fqcn, "java.lang.String")) {
		return G_TYPE_STRING;
	} else if (g_str_equal(fqcn, "java.lang.Integer")) {
		return G_TYPE_INT;
	} else if (g_str_equal(fqcn, "java.lang.Long")) {
		return G_TYPE_INT64;
	} else if (g_str_equal(fqcn, "java.lang.Boolean")) {
		return G_TYPE_BOOLEAN;
	} else if (g_str_equal(fqcn, "org.gnome.glib.Object")) {
		return G_TYPE_OBJECT;
	} else if (g_str_equal(fqcn, "org.gnome.gdk.Pixbuf")) {
		return GDK_TYPE_PIXBUF;
	} else if (g_str_equal(fqcn, "java.lang.Object")) {
		/*
		 * This is what we use to stash references to Java objects.
		 * They're never displayed in a TreeView, but used to find
		 * your way back to your Java side domain object graph.
		 */
		if (!BINDINGS_JAVA_TYPE_REFERENCE) {
        		BINDINGS_JAVA_TYPE_REFERENCE = g_boxed_type_register_static(
        			"BindingsJavaReference",
                                bindings_java_reference_copy,
                                bindings_java_reference_free
			);
		}

		return BINDINGS_JAVA_TYPE_REFERENCE;
	} else {
		return G_TYPE_INVALID;
	}
}
