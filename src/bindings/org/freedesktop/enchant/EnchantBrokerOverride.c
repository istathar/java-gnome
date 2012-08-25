/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2010 Operational Dynamics Consulting, Pty Ltd
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
#include <enchant.h>
#include <libintl.h>
#include "bindings_java.h"
#include "org_freedesktop_enchant_EnchantBrokerOverride.h"

static GSList* list;

static void
enumerate_dictionary
(
	const char* const lang_tag,
	const char* const provider_name,
	const char* const provider_desc,
	const char* const provider_file,
        void* user_data
)
{
	gchar* copy;

	copy = g_strdup(lang_tag);
	list = g_slist_prepend(list, copy);
}

/*
 */
/*
 * Signature the prototype of (*EnchantDictDescribeFn), meeting the
 * requirements to be the second argument to enchant_broker_list_dicts().
 */

/*
 * Implements
 *   org.freedesktop.enchant.EnchantBrokerOverride.enchant_broker_list_dicts()
 * called from
 *   org.freedesktop.enchant.EnchantBrokerOverride.listDicts()
 * called from
 *   org.freedesktop.enchant.Enchant.listDictionaries()
 *
 * This one uses a function pointer to enumerate through the available
 * dictionaries, so we need to do this here in native code.
 */
JNIEXPORT jobjectArray JNICALL
Java_org_freedesktop_enchant_EnchantBrokerOverride_enchant_1broker_1list_1dicts
(
	JNIEnv* env,
	jclass cls,
	jlong _self
)
{
	EnchantBroker* self;
	GSList* iter;
	gchar** result;
	jobjectArray _result;
	guint size;
	int i;

	// convert self
	self = (EnchantBroker*) _self;


	/*
	 * Ideally we would allocate a GList we can use to accumulate results.
	 * Except that you don't attually allocate a GList :) and we'll use one
	 * stack allocated here. FIXME Except that I couldn't make that work,
	 * so we used a global instead.
	 */

	list = NULL;

	// call function
	enchant_broker_list_dicts(self, enumerate_dictionary, NULL);


	// cleanup parameter self

	/*
	 * To get this back to Java as a String[] we need a gchar** so we can
	 * then use our existing utilty function. So we need to transfer the
	 * strings from the GSList to a temporary gchar** now.
	 */

	size = g_slist_length(list);

	result = g_malloc((size + 1) * sizeof(gchar*));
	result[size] = NULL;

	iter = list;
	for (i = 0; i < size; i++) {
		result[i] = (gchar*) iter->data;
		iter = g_slist_next(iter);
	}


	// translate return value to JNI type
	_result = (jobjectArray) bindings_java_convert_gchararray_to_jarray(env, (const gchar**)result);

	// cleanup return value
	if (result != NULL) {
		g_strfreev(result);
	}

	// and finally
	return _result;
}
