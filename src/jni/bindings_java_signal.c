/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 1998-2005 The java-gnome Team
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
 * The code for signal marshaling was completely rewritten by Andrew Cowie
 * during the 4.0 re-engineering effort. Working with JNI and with GLib is
 * very idiomatic, however, and the sequence of calls employed here was drawn
 * directly from the example set by the java-gnome 2.x project in their
 * glib-java/src/jni/org_gnu_glib_GObject.c. We are certainly indebted to the
 * previous hackers for having worked out these techniques.
 */

#include <jni.h>
#include <string.h>
#include <glib.h>
#include <glib-object.h>
#include <gtk/gtk.h>
#include "bindings_java.h"

typedef struct  {
	/**
	 * In typical G fashion, we extend something by build this structure
	 * on top of an existing one.
	 */
	GClosure	closure;

	/**
	 * We use Java's single letter codes to choose which kind of
	 * invocation we will use
	 */
	jchar		returnType;

 	/*
 	 * The Class upon which we invoke the [static] "receiveBlah" method.
 	 */
 	jclass		receiver;

 	/*
 	 * The actual Signal subinterface that is being connected. This will
 	 * be passed to the handler on the Java side so it can call the
 	 * appropriate method which is actually (from the developer's
 	 * standpoint) the callback being invoked.
 	 */
 	jobject 	handler;

 	/*
 	 * ID of the method on handlerInstance that will be invoked upon
 	 * callback
 	 */
 	jmethodID	method;

} BindingsJavaClosure;


/**
 * This method actually receives all callbacks in the system. It receives a
 * GClosure struct as its data, which turns out to be our BindingsJavaClosure.
 * It then determines what Java method to call and invokes it passing the
 * correct parameters
 */
/*
 * Signature the prototype of (*GClosureMarshall), meeting the requirements
 * to be the second argument to g_closure_set_marshal()
 */
static void
bindings_java_marshaller
(
	GClosure* closure,
	GValue* return_value,
	guint n_param_values,
	const GValue* param_values,
	gpointer invocation_hint,
	gpointer marshal_data
)
{
 	BindingsJavaClosure* bjc;
 	JNIEnv* env;
 	jvalue* jargs;
	guint i;
	GType type;

	// Return values, as necessary
	jboolean _b;
	gboolean b;
	
	jint _e;
	
	jstring _str;
	gchar* str;
	GObject* obj;
	
	/*
	 * Begin marshaller by downcasting the GClosure we got.
	 */

	bjc = (BindingsJavaClosure*) closure;

	/*
	 * Get the JNIEnv interface pointer
	 */

	env = bindings_java_getEnv();
	if (env == NULL) {
		g_critical("Couldn't get JNIEnv interface, aborting marshal");
		return;
	}
		
	/*
	 * Build the parameters for the callback. The signature of the
	 * handlers on the Java side for a signal "name" would be:
	 *
	 * 	receiveName(Signal handler, type arg0, type arg1, ...)
	 *
	 * Note that arg0 is universally the source object (in otherwords, a
	 * method function where the first argument is always a reference to
	 * self).
	 *
	 * In case you didn't know, JNI's jvalue us a rather complex union
	 * which holds any of the possible things you can send across the
	 * boundary. So we allocate an array of them, then for each parameter
	 * passed to the marshaller, whack them in.
	 */

	jargs = g_newa(jvalue, n_param_values + 1);

	jargs[0].l = bjc->handler;

	for(i = 0; i < n_param_values; i++) {
		type = G_VALUE_TYPE(&param_values[i]);
		switch(G_TYPE_FUNDAMENTAL(type)) {
		case G_TYPE_CHAR:
			jargs[i+1].c = g_value_get_char(&param_values[i]);
      			break;

		case G_TYPE_UCHAR:
			jargs[i+1].c = g_value_get_uchar(&param_values[i]);
      			break;

		case G_TYPE_BOOLEAN:
			b = g_value_get_boolean(&param_values[i]);
			jargs[i+1].z = (b == TRUE) ? JNI_TRUE : JNI_FALSE;
			break;

		case G_TYPE_INT:
			jargs[i+1].i = g_value_get_int(&param_values[i]);
			break;

		case G_TYPE_UINT:
			jargs[i+1].i = g_value_get_uint(&param_values[i]);
			break;

		case G_TYPE_ENUM:
			jargs[i+1].i = g_value_get_enum(&param_values[i]);
			break;

		case G_TYPE_FLAGS:
			jargs[i+1].i = g_value_get_flags(&param_values[i]);
			break;

		case G_TYPE_LONG:
			jargs[i+1].j = g_value_get_long(&param_values[i]);
			break;

		case G_TYPE_ULONG:
			jargs[i+1].j = g_value_get_ulong(&param_values[i]);
			break;

		case G_TYPE_FLOAT:
			jargs[i+1].f = g_value_get_float(&param_values[i]);
			break;

		case G_TYPE_DOUBLE:
			jargs[i+1].d = g_value_get_double(&param_values[i]);
			break;

		case G_TYPE_STRING:
			jargs[i+1].l = bindings_java_newString(env, g_value_get_string(&param_values[i]));
      			break;

		case G_TYPE_OBJECT:
		case G_TYPE_INTERFACE:
			/*
			 * GObjects are just pointers, and so we pass up the
			 * address across the boundary to be looked up and
			 * either an existing Proxy returned or a new Proxy
			 * created.
			 */
			obj = g_value_get_object(&param_values[i]);
			bindings_java_memory_cleanup(obj, FALSE);
			jargs[i+1].j = (jlong) obj;
			break;

		case G_TYPE_BOXED:
			/*
			 * We make a copy of the GBoxed so that we own it and
			 * thus it can (will) survive the duration of the
			 * signal in the event that the developer using this
			 * code keeps a reference to the returned Boxed.
			 */
			jargs[i+1].j = (jlong) g_boxed_copy(type, g_value_get_boxed(&param_values[i]));
			break;

		case G_TYPE_PARAM:
			/*
			 * We're ignoring GParamSpec at the moment. They
			 * normally only show up in 'notify' signals, and we
			 * don't need them.
			 */
		case G_TYPE_POINTER:
			/*
			 * and, we're ignoring something that gets registered
			 * as a gpointer, by definition it has no type
			 * information and there's nothing we can do.
			 */
			jargs[i+1].j = (jlong) NULL;
			break;

		default:
			/*
			 * Unrecognized. Probably means we need to add a clause above.
			 */
			g_printerr("Don't know how to marshal a %s", g_type_name(type));
			jargs[i+1].l = 0;
			break;
		}
	}
	
	/*
	 * And now we invoke the callback on the Java side Signal handler; we have to
	 * select the correct function based on what return type is necessary.
	 */

	switch(bjc->returnType) {
	case 'V':
		/*
		 * void return signals
		 */
		(*env)->CallStaticVoidMethodA(env, bjc->receiver, bjc->method, jargs);
		break;

	case 'Z':
		/*
		 * boolean return signals
		 */
		_b = (*env)->CallStaticBooleanMethodA(env, bjc->receiver, bjc->method, jargs);		
		if (_b == JNI_TRUE) {
			b = TRUE;
		} else if (_b == JNI_FALSE) {
			b = FALSE;
		} else {
			g_critical("How did you manage to return a boolean that's neither TRUE nor FALSE?");
			return;
		}
		
		g_value_set_boolean(return_value, b);
		break;

	case 'I':
		/*
		 * integer return signals
		 */
		_e = (*env)->CallStaticIntMethodA(env, bjc->receiver, bjc->method, jargs);
		
		g_value_set_int(return_value, _e);
		break;

	case 'E':
		/*
		 * enum return signals
		 */
		_e = (*env)->CallStaticIntMethodA(env, bjc->receiver, bjc->method, jargs);		
		
		g_value_set_enum(return_value, _e);
		break;

	case 'L':
		/*
		 * String return signals
		 *
		 * L is actually Object, of course, but the only type we need to
		 * worry about is java.lang.String encode it for now, and so make the
		 * enormous assumption that a string is what we get back.
		 */
		_str = (*env)->CallStaticObjectMethodA(env, bjc->receiver, bjc->method, jargs);
		if (_str == NULL) {
			g_warning("Invoking string handler returned null. That's probably bad");
			break;
		}
		
		str = (gchar*) bindings_java_getString(env, _str);
		if (str == NULL) {
			/* OutOfMemoryError already thrown */
			return;
		}
		
		// according to the API docs, this copies the input...
		g_value_set_string(return_value, str);
		
		// ... so we can release str
		bindings_java_releaseString(str);
		break;

	default:
		/*
		 * If it's not void, boolean or gchar*, then what kind of signal is it?
		 */
		g_critical("Invocation for return type %c not implemented", bjc->returnType);
		break;
	}

	/*
	 * Cleanup
	 */

	for(i = 0; i < n_param_values; i++) {
  		type = G_VALUE_TYPE(&param_values[i]);
		switch(G_TYPE_FUNDAMENTAL(type)) {
		case G_TYPE_STRING:
			(*env)->DeleteLocalRef(env, jargs[i+1].l);
			break;

		default:
			break;
		}
	}
	
	/*
	 * Don't need to free jargs - we alloca()'d it
	 */
	
	/*
	 * Now, check if an exception occurred in the callback. There's a
	 * catch: because we're in native code right now [care of the call to
	 * gtk_main()] the exception gets swallowed until we return from that
	 * native call. So we call the function which causes the main loop to
	 * terminate, with the result that the exception will propegate out
	 * and, yes, probably halt the program.
	 *
	 * This is abrupt, but it is deliberate: we need to force developers
	 * to deal with criticals emitted by the underlying libraries.
	 * Otherwise, the next thing that is likely to happen is a
	 * segmentation fault, and not only does that crash the "program" but
	 * it takes out the Java Virtual Machine running it. People don't
	 * like VM crashes.
	 *
	 * Uncaught exceptions of any kind leaving a signal handler are to be
	 * considered programmer error and will be fatal.
	 */

	if ((*env)->ExceptionOccurred(env)) {
		gtk_main_quit();
	}
}

/**
 * Called to cleanup a BindingsJavaClosure, and critically, to remove the
 * reference to the Java object which is the signal handler.
 */
/*
 * Signature the prototype of (*GClosureNotify), meeting the requirements
 * to be the third argument to g_closure_add_finalize_notifier()
 */
static void
bindings_java_closure_destroy
(
	gpointer data,
	GClosure *closure
)
{
	BindingsJavaClosure* bjc;
	JNIEnv* env;	

 	bjc = (BindingsJavaClosure*) closure;

	if (bjc->handler) {
		env = bindings_java_getEnv();
		(*env)->DeleteWeakGlobalRef(env, bjc->handler);
	}
}

/**
 * Create one of our custom GClosure subclasses. To save us having to export
 * it, however, we just return the GClosure* that it extends.
 */
GClosure*
bindings_java_closure_new
(
	JNIEnv* env,
	jobject handler,
	jclass receiver,
	const gchar* name,
	guint id
)
{
	GClosure* closure;
	BindingsJavaClosure* bjc;
	
	GSignalQuery info;

	GString* buf;
	guint i;

	gchar* methodName;
	gchar* methodSignature;
	
	/*
	 * First we allocate the closure and do the footwork to tell it what
	 * its marshaller is
	 */

	closure = g_closure_new_simple(sizeof(BindingsJavaClosure), NULL);
	g_closure_add_finalize_notifier(closure, NULL, bindings_java_closure_destroy);
	g_closure_set_marshal(closure, bindings_java_marshaller);

	bjc = (BindingsJavaClosure*) closure;

	/*
	 * And now we begin the legwork of figuring out what the methodID of
	 * the callback to be invoked is and caching that in the closure. We
	 * get the GSignalQuery data for the specified signal and then use that
	 * to formulate a string that can be use to lookup the method.
	 */

	g_signal_query(id, &info);

	switch(G_TYPE_FUNDAMENTAL(info.return_type)) {
	case G_TYPE_BOOLEAN:
		bjc->returnType = 'Z';
      		break;

	case G_TYPE_INT:
		bjc->returnType = 'I';
		break;

	case G_TYPE_ENUM:
		bjc->returnType = 'E';
      		break;

	case G_TYPE_STRING:
		/*
		 * Strings are encoded as java.lang.String objects in signatures,
		 * so we use the object type marker for gchar* (only).
		 */
		bjc->returnType = 'L';
      		break;

	case G_TYPE_NONE:
		bjc->returnType = 'V';
		break;

	default:
		g_critical("Don't know what to do with signal return type %s", g_type_name(info.return_type));
		return NULL;
	}
	

	/*
	 * the name of the methods we invoke is algorithmic: "receiveName",
	 * where Name is a PascalCase version of the signal name we were
	 * passed in.
	 */

	buf = g_string_new("receive");

	gchar** tokens = g_strsplit_set(name, "_-:", -1);

	for (i = 0; i < g_strv_length(tokens); i++) {
		gchar* token = tokens[i];

		if (token[0] == '\0') {
			// skip past :: which splits signal name from "detail"
			continue;
		}

		gchar first = g_unichar_toupper(token[0]);
		g_string_append_c(buf, first);

		token++;
		g_string_append(buf, token);
	}

	methodName = buf->str;

	g_string_free(buf, FALSE);
	g_strfreev(tokens);

	/*
	 * And here is the tricky bit: formulate the method signature that goes
	 * along with this signal. A method of the signature
	 *
	 * 	boolean method(int, long, String)
	 *
	 * has a JNI encoding of
	 *
	 * 	(IJLjava/util/String;)Z
	 */

	buf = g_string_new("(Lorg/gnome/glib/Signal;J");

	// add the signature for each parameter type
	for(i = 0; i < info.n_params; i++) {
		g_string_append(buf, bindings_java_typeToSignature(info.param_types[i]));
	}

	// and the return type
	g_string_append(buf, ")");
	g_string_append(buf, bindings_java_typeToSignature(info.return_type));
	
	methodSignature = buf->str;
	g_string_free(buf, FALSE);
	
	/*
	 * Now at last we can lookup the method ID
	 */
	
//	jclass CANDIDATE = (*env)->FindClass(env, "org/gnome/gtk/GtkWidget");
//	if ((*env)->IsSameObject(env, CANDIDATE, receiver)) {
//		g_debug("Received a GtkWidget");
//	}

	bjc->receiver = receiver;
	bjc->method = (*env)->GetStaticMethodID(env, bjc->receiver, methodName, methodSignature);
	
//	g_debug("Looking for\nJava method %s\nwith signature %s\nin class %s\nto handle signal %s\n",
//			methodName, methodSignature, "FIXME", g_signal_name(id));

	// clean up	
	g_free(methodName);
	g_free(methodSignature);

	// and check for error
	if (bjc->method == NULL) {
		// Exception already thrown by GetMethodID
		return NULL;
	}

	/*
	 * Set the reference so that the marshaller can find the Signal instance.
	 */

	bjc->handler = (*env)->NewWeakGlobalRef(env, handler);

	/*
	 * And we're done!
	 */
	
	return closure;
}
