/*
 * bindings_java.h
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd.
 *
 * This file is free software distributed under the terms of the GNU
 * Library General Public License, version 2.
 */
#ifndef _BINDINGS_JAVA_H_
#define _BINDINGS_JAVA_H_

#include <jni.h>
#include <glib.h>
#include <glib-object.h>

/*
 * bindings_java_util.c
 */

extern JNIEnv* bindings_java_getEnv();

extern void bindings_java_throwByName(JNIEnv*, const char*, const char*);
extern void bindings_java_throw(JNIEnv*, const char*, ...);

extern const gchar* bindings_java_typeToSignature(GType);

/*
 *  bindings_java_signal.c
 */

extern GClosure* bindings_java_closure_new(JNIEnv*, GObject*, jobject, jclass, const gchar*, guint);


#endif 
