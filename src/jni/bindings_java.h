/*
 * bindings_java.h
 *
 * Copyright (c) 2006-2009 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
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
