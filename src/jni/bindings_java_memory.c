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
		if (DEBUG_MEMORY_MANAGEMENT) {
			g_printerr("mem: toggle Java ref to WEAK\t%s\n", bindings_java_memory_pointerToString(object));
		}
		weak = (*env)->NewWeakGlobalRef(env, ref);
		g_object_set_data(object, REFERENCE, weak);
 		(*env)->DeleteGlobalRef(env, ref);
	} else {
		/*
		 * Create strong Java reference, overwrite association in
		 * GObject, and remove weak Java reference now that we've
		 * replaced it with a strong one.
		 */
		if (DEBUG_MEMORY_MANAGEMENT) {
			g_printerr("mem: toggle Java ref to STRONG\t%s\n", bindings_java_memory_pointerToString(object));
		}

		strong = (*env)->NewGlobalRef(env, ref);
		g_object_set_data(object, REFERENCE, strong);
		(*env)->DeleteWeakGlobalRef(env, ref);
	}	
}

/**
 * Drop our initial default GObject reference. This is called via a GIdle
 * call to avoid the ping pong effect which results if the ToggleRef is the
 * only ref count to a GtkWidget while setters are called prior to the
 * GtkWidget being added to a GtkContainer. By delaying the ref count
 * reduction, the initial strong Java reference is left alone.
 */
/*
 * Signature the prototype of a (*GSourceFunc) callback, meeting the
 * requirements of the first argument to g_idle_add()
 */
static gboolean
bindings_java_memory_deref
(
        gpointer data
)
{
        GObject* object;

        object = (GObject*) data;

	if (DEBUG_MEMORY_MANAGEMENT) {
		g_printerr("mem: drop GObject ref\t\t%s\n", bindings_java_memory_pointerToString(object));
	}
        g_object_unref(object);
        return FALSE;
}

/**
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

 	if (DEBUG_MEMORY_MANAGEMENT) {
 		g_printerr("mem: add STRONG Java ref\t%s\n", bindings_java_memory_pointerToString(object));
 	}
	strong = (*env)->NewGlobalRef(env, target);
	g_object_set_data(object, REFERENCE, strong);

	/*
	 * Add a ToggleRef to the GObject "from the Proxy to the GObject",
	 * incrementing its ref count
	 */

	g_object_add_toggle_ref(object, bindings_java_toggle, NULL);

	/*
	 * And drop the reference we originally own. Note that our automatically
	 * generated code ensure we always own a ref, adding it if we don't have
	 * such reference originally.
	 */
	g_idle_add(bindings_java_memory_deref, object);
}


/**
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
	if (DEBUG_MEMORY_MANAGEMENT) {
		g_printerr("mem: remove toggle ref for\t%s\n", bindings_java_memory_pointerToString(object));
	}

	g_object_remove_toggle_ref(object, bindings_java_toggle, NULL);
}

/**
 * Ensure we properly own a GObject.
 *
 * This is really important. The aggregate result ensures that we own one Ref
 * count to the object - no more, no less - which we can then turn into a
 * ToggleRef. It needs to be called anywhere we are preparing to create a
 * Proxy.
 */
/*
 * TODO This needs a better name
 */
void
bindings_java_memory_cleanup
(
    GObject* object,
    gboolean owner
)
{
    if (g_object_get_data(object, REFERENCE) == NULL) {

        /*
         * We don't have a proxy for the given object. If we own the object,
         * we don't need to do anything, as Object constructor will take care
         * of adding the toggle ref and removing the ref we own. There is an
         * exception, however, as for GInitiallyUnowned we need to sink the
         * reference if we have one.
         *
         * If we don't own the object, we need to add a new ref, that will be
         * later removed by the Proxy constructor.
         */
        if (owner) {
            if (G_IS_INITIALLY_UNOWNED(object) && g_object_is_floating(object)) {
                if (DEBUG_MEMORY_MANAGEMENT) {
                    g_printerr("mem: sink GObject ref\t\t%s\n", bindings_java_memory_pointerToString(object));
                }
                g_object_ref_sink(object);
            }
        } else {
            /*
             * We do not own the object, so we need to add an extra ref, as
             * Object constructor assumes we actually own the object.
             */
            if (DEBUG_MEMORY_MANAGEMENT) {
                g_printerr("mem: added extra ref for\t%s\n", bindings_java_memory_pointerToString(object));
            }
            g_object_ref(object);
        }
    } else {
        /*
         * We already have a proxy for the object. So we already have our
         * toggle ref. Thus, if a methods adds an extra ref, we need to drop
         * it.
         * TODO does such behavior exist in Gtk+?
         */
        if (owner) {
            if (DEBUG_MEMORY_MANAGEMENT) {
                g_printerr("mem: remove ref for\t%s\n", bindings_java_memory_pointerToString(object));
            }
            g_object_unref(object);
        }
    }
}

#if GLIB_SIZEOF_VOID_P == 8
#define WIDTH "16"
#define SIZE GLIB_SIZEOF_VOID_P * 2 + 3
#else
#define WIDTH "8"
#define SIZE GLIB_SIZEOF_VOID_P * 2 + 3
#endif

/*
 * A utility function to format a pointer address as an appropriate width
 * string. This is necessary because of the inadequecies of printf's %p.
 * The return value is statically allocated and must not be free'd.
 *
 * This is called when debugging, both from C and from Java via
 * Plumbing.toHexString()
 */
const gchar*
bindings_java_memory_pointerToString
(
	gpointer pointer
)
{
	static gchar result[SIZE];
	
	g_snprintf(result, SIZE, "0x%." WIDTH "lX", (unsigned long) pointer);
	
	return result;
}
