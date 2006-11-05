// generated

#include <jni.h>
#include <gtk/gtk.h>

#include "org_gnome_gtk_GtkWidget.h"

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkWidget_gtk_1widget_1show
  (JNIEnv *env, jclass cls, jlong _self)
{
	GtkWidget* self;

	// translate arg self
	self = (GtkWidget*) _self;

	// call function
	gtk_widget_show(self);

	// cleanup arg self
}

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkWidget_gtk_1widget_1show_1all
  (JNIEnv *env, jclass cls, jlong _self)
{
	GtkWidget* self;

	// translate arg self
	self = (GtkWidget*) _self;

	// call function
	gtk_widget_show_all(self);

	// cleanup arg self
}




