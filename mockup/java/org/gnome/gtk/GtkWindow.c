// generated

#include <jni.h>
#include <gtk/gtk.h>

#include "org_gnome_gtk_GtkWindow.h"

JNIEXPORT jlong JNICALL
Java_org_gnome_gtk_GtkWindow_gtk_1window_1new
  (JNIEnv *env, jclass cls, jint _type)
{
	GtkWindowType type;
	GtkWidget* window;

	// translate arg type
	type = (GtkWindowType) _type;

	// call constructor
	window = gtk_window_new(type);

	// cleanup arg type

	// return address
	return (jlong) window;
}

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkWindow_gtk_1window_1set_1title
  (JNIEnv *env, jclass cls, jlong _self, jstring _title)
{
	GtkWindow* self;
	gchar* title;

	// translate arg self
	self = (GtkWindow*) _self;

	// translate arg title
	title = (gchar*) (*env)->GetStringUTFChars(env, _title, NULL);
	if (title == NULL) {
		return; /* OutOfMemoryError already thrown */
	}

	// call function
	gtk_window_set_title(self, title);

	// cleanup arg self

	// cleanup arg title
	(*env)->ReleaseStringUTFChars(env, _title, title);
}
