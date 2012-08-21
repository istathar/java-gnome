/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2008-2011 Operational Dynamics Consulting, Pty Ltd
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

#include <jni.h>
#include <gtk/gtk.h>
#include "bindings_java.h"
#include "org_gnome_gtk_GtkTreeModelFilterOverride.h"

static guint signalID = 0;

/**
 * Find out whether a given row is to be visible by emitting our custom
 * "visible" signal, to which the we have connected a Java side signal handler
 * returning boolean.
 *
 * Note that the reference to self is prepended by the g_signal_emit() code
 * automatically. You need it in the callback signature, but not in the .defs
 * or in the parameters here.
 */
/*
 * Meets the signature requirement of (*GtkTreeModelFilterVisibleFunc) in
 * order to be the second parameter to the call to
 * gtk_tree_model_filter_set_visible_func() below.
 */
static gboolean
emit_visible
(
	GtkTreeModel *child,
	GtkTreeIter *iter,
	gpointer instance
)
{
	gboolean result;
	
	g_signal_emit_by_name(GTK_TREE_MODEL_FILTER(instance), "visible", child, iter, &result);
	
	return result;
}

/**
 * called from
 *   org.gnome.gtk.GtkTreeModelFilterOverride.setVisibleFunc()
 * called from
 *   org.gnome.gtk.TreeModelFilter.setVisibleCallback()
 *
 */
JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkTreeModelFilterOverride_gtk_1tree_1model_1filter_1set_1visible_1func
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	GtkTreeModelFilter* self;

	// convert parameter self
	self = (GtkTreeModelFilter*) _self;

	if (signalID == 0) {
		signalID = g_signal_new("visible",
					GTK_TYPE_TREE_MODEL_FILTER,
					G_SIGNAL_ACTION,
					0,
					NULL,
					NULL,
					NULL, // note 1
					G_TYPE_BOOLEAN,
					2,    // note 2
					GTK_TYPE_TREE_MODEL,
					GTK_TYPE_TREE_ITER);
		/*
		 * Notes:
		 *
		 * 1. Don't need to register a marshall function; the
		 * subsequent g_signal_connect() as invoked by our
		 * TreeModelFilter's setVisibleHandler() will register a
		 * dynamic bindings_java_marshaller().
		 *
		 * 2. As ever, there is an implicit reference to self
		 * automatically added as the first parameter for the signal.
		 * Good of them, but confusing, because in this case, the
		 * model parameter is the _child_, not the reference to self.
		 */
	}

	// call function
	gtk_tree_model_filter_set_visible_func(self, emit_visible, self, NULL);

	// clean up
}
