/*
 * bindings_java_memory.c
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */

/*
 * We would be remiss if we did not record that the GObject ToggleRef
 * capability was worked out by Owen Taylor especially for language bindings
 * and that the original implementation in the old java-gnome 2.x was done by
 * Nick Rahn, Ismael Juma, and Jeffrey Morgan, ultimately coming to rest in
 * glib-java/src/jni/glib_java.c. Although our overall Proxy strategy, memory
 * management approach, and use of ToggleRef is somewhat different, the idea
 * of switching to a weak Java reference (rather than dropping it entirely)
 * is inspired from that code.
 */

#include <jni.h>
#include <glib.h>
#include <glib-object.h>
#include "bindings_java.h"

#define REFERENCE "RefToJavaProxy"

/**
 * This function is called whenever the GObject reference count drops to one,
 * and that one reference is the ToggleRef. When that happens, we drop the
 * strong Java reference which means that if that was the last such strong
 * reference, the Java object can be finalized (with the result that the
 * ToggleRef will in turn be dropped, allowing the GObject to destroy).
 * 
 * We switch to a weak Java reference instead of just removing it so that we
 * can find our way back to the Java object should the ToggleRef be activated
 * with a "you're not the last ref anymore".
 */
/*
 * Signature the prototype of a (*GToggleNotify) callback, meeting the
 * requirements of the second argument to g_object_add_toggle_ref()
 */
static void
bindings_java_toggle
(
	gpointer data,
        GObject *object,
        gboolean is_last_ref
)
{
	JNIEnv* env;
	jobject ref;
	jobject strong;
	jobject weak;
	
	ref = g_object_get_data(object, REFERENCE);
	env = bindings_java_getEnv();
	
	if (is_last_ref) {
		/*
		 * Create weak Java reference, replace association stored in
		 * GObject, and remove strong Java reference
		 */
		g_print("mem: toggle Java ref to WEAK\t%ld\n", (long) object);
		weak = (*env)->NewWeakGlobalRef(env, ref);
		g_object_set_data(object, REFERENCE, weak);
 		(*env)->DeleteGlobalRef(env, ref);
	} else {
		/*
		 * Create strong Java reference, overwrite association in
		 * GObject, and remove weak Java reference now that we've
		 * replaced it with a strong one. 
		 */
		g_print("mem: toggle Java ref to STRONG\t%ld\n", (long) object);
		strong = (*env)->NewGlobalRef(env, ref);
		g_object_set_data(object, REFERENCE, strong);
		(*env)->DeleteWeakGlobalRef(env, ref);
	}	
}


/*
 * Called from
 *   Java_org_gnome_glib_GObject_g_1object_1add_1toggle_1ref()
 * called from
 *   org.gnome.glib.GObject.g_object_add_toggle_ref(long reference, Object target)
 * called from
 *   org.gnome.glib.GObject.addToggleRef(Object reference)
 * called from
 *   org.gnome.glib.Object.<init>(long pointer)
 * 
 * Here we go through the various motions necessary to appropriately
 * increment the ref count on the GObject for which we are creating a Proxy
 * to.
 */
void
bindings_java_memory_ref
(
	JNIEnv* env,
	GObject* object,
	jobject target 
)
{
	jobject strong;

	/*
	 * Take out a strong Java reference "from the GObject to the Proxy"
	 * and stash it in the GObject. 
	 */
 
 	g_print("mem: add STRONG Java ref\t%ld\n", (long) object);
	strong = (*env)->NewGlobalRef(env, target);
	g_object_set_data(object, REFERENCE, strong);

	/*
	 * Add a ToggleRef to the GObject "from the Proxy to the GObject",
	 * incrementing its ref count
	 */

	g_object_add_toggle_ref(object, bindings_java_toggle, NULL);

	/*
	 * Many GtkObjects are created with a floating ref, for better API
	 * convenience when working in C. Sink that reference. Per the docs
	 * for g_object_ref_sink(), if it _wasn't_a floating reference, then
	 * this just acts a g_object_ref() - making this a no op,
	 * collectively.
	 */
	 
	g_object_ref_sink(object);
	g_object_unref(object);
}
	
/*
 * Called from
 *   Java_org_gnome_glib_GObject_g_1object_1remove_1toggle_1ref()
 * called from
 *   org.gnome.glib.GObject.g_object_remove_toggle_ref(long reference)
 * called from
 *   org.gnome.glib.GObject.removeToggleRef(Object reference)
 * called when
 *   org.gnome.glib.Object.release()
 * is invoked by a finalizer.
 * 
 * Remove the ToggleRef. Since this is called by the Java finalizer, and
 * since the Java object should only become only-weakly-reachable when there
 * are no more GObject refs, this should drop the last ref count to the
 * GObject, resulting it in it being eligible for destruction.  
 */
void
bindings_java_memory_unref
(
	GObject* object
)
{
	g_print("mem: remove toggle ref for\t%ld\n", (long) object);
	g_object_remove_toggle_ref(object, bindings_java_toggle, NULL);
}
