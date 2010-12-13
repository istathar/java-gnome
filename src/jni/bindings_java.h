/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd
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
 * "Claspath Exception"), the copyright holders of this library give you
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

#ifndef _BINDINGS_JAVA_H_
#define _BINDINGS_JAVA_H_

#include <jni.h>
#include <glib.h>
#include <glib-object.h>
#include <gdk-pixbuf/gdk-pixbuf.h>

/*
 * bindings_java_util.c
 */

extern JNIEnv* bindings_java_getEnv();

extern void bindings_java_throwByName(JNIEnv*, const char*, const char*);
extern void bindings_java_throw(JNIEnv*, const char*, ...);
extern void bindings_java_throwGlibException(JNIEnv*, GError*);

extern const gchar* bindings_java_typeToSignature(GType);

extern void bindings_java_logging_init();

extern const gchar* bindings_java_getString(JNIEnv*, jstring);
extern jstring bindings_java_newString(JNIEnv*, const gchar*);
extern void bindings_java_releaseString(const gchar*);

/*
 *  bindings_java_signal.c
 */

extern GClosure* bindings_java_closure_new(JNIEnv*, jobject, jclass, const gchar*, guint);

/*
 * bindings_java_memory.c
 */

extern void bindings_java_memory_ref(JNIEnv*, GObject*, jobject);
extern void bindings_java_memory_unref(GObject*);
extern void bindings_java_memory_cleanup(GObject*, gboolean);
extern const gchar* bindings_java_memory_pointerToString(void*);

// TODO derive this at compile time from org.freedesktop.bindings.Debug
#define DEBUG_MEMORY_MANAGEMENT FALSE
/*
 * bindings_java_threads.c
 */

extern void bindings_java_threads_init(JNIEnv*, jobject);
extern void bindings_java_threads_lock();
extern void bindings_java_threads_unlock();

/*
 * bindings_java_convert.c
 */
 
extern GList* bindings_java_convert_jarray_to_glist(JNIEnv*, jlongArray);
extern GSList* bindings_java_convert_jarray_to_gslist(JNIEnv*, jlongArray);
extern jlongArray bindings_java_convert_glist_to_jarray(JNIEnv*, GList*);
extern jlongArray bindings_java_convert_gslist_to_jarray(JNIEnv*, GSList*);
extern gpointer* bindings_java_convert_jarray_to_gpointer(JNIEnv*, jlongArray);

// the gpointer array is freed at the end
extern void bindings_java_convert_gpointer_to_jarray(JNIEnv*, gpointer*, jlongArray);

// the gchar* arrays is not freed
extern jobjectArray bindings_java_convert_gchararray_to_jarray(JNIEnv*, const gchar**);
extern gchar** bindings_java_convert_strarray_to_gchararray(JNIEnv*, jobjectArray);

// the gchar* array is freed
extern void bindings_java_convert_gchararray_to_strarray(JNIEnv*, gchar**, jobjectArray);

/*
 * bindings_java_type.c
 */

extern GType BINDINGS_JAVA_TYPE_REFERENCE;
extern GType bindings_java_type_lookup(const gchar*);

/*
 * gnome_screenshot_capture.c
 */

extern GdkPixbuf* gnome_screenshot_capture(gboolean, gboolean, const gchar*);

#endif 
