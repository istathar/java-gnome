// generated

#include <jni.h>
#include <gtk/gtk.h>

#include "org_gnome_gtk_GtkContainer.h"

JNIEXPORT void JNICALL
Java_org_gnome_gtk_GtkContainer_gtk_1container_1add
  (JNIEnv *env, jclass cls, jlong _self, jlong _child)
{
	GtkContainer* self;
	GtkWidget* child;


	// translate arg self
	self = (GtkContainer*) _self;

	// translate arg child
	child = (GtkWidget*) _child;

	// call function
	gtk_container_add(self, child);

	// cleanup arg child

	// cleanup arg self
}
