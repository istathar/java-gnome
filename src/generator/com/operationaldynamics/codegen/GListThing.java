/*
 * GListThing.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd, and Others
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

/**
 * A Thing that represents an array of Java objects. This is the Thing used to
 * deal with GList and GSList types.
 * 
 * @author Vreixo Formoso
 */
public class GListThing extends ProxiedArrayThing
{

    public GListThing(String gType, Thing type) {
        super(gType.split("-")[0], type);
        this.cType = this.gType + "*";
    }

    protected GListThing() {}

    String jniConversionDecode(String name) {
        if (gType.equals("GList")) {
            return "bindings_java_convert_jarray_to_glist(env, _" + name + ")";
        } else if (gType.equals("GSList")) {
            return "bindings_java_convert_jarray_to_gslist(env, _" + name + ")";
        } else {
            throw new Error();
        }
    }

    String jniConversionCleanup(String name) {

        // FIXME are there GList's as outparams? if so, a g_list_free is
        // not enought. We need to copy back the results.

        if (gType.equals("GList")) {
            return "g_list_free(" + name + ")";
        } else if (gType.equals("GSList")) {
            return "g_slist_free(" + name + ")";
        } else {
            throw new Error();
        }
    }

    String jniReturnEncode(String name) {
        if (gType.equals("GList")) {
            return "bindings_java_convert_glist_to_jarray(env, " + name + ")";
        } else if (gType.equals("GSList")) {
            return "bindings_java_convert_gslist_to_jarray(env, " + name + ")";
        } else {
            throw new Error("Unexpected gtype " + gType);
        }
    }

    @Override
    String jniReturnCleanup(String name, char callerOwnsReturn) {
        // FIXME we need to manage t in a different way, with deep clean-up
        if (callerOwnsReturn != 'f') {
            if (gType.equals("GList")) {
                return "g_list_free(" + name + ")";
            } else if (gType.equals("GSList")) {
                return "g_slist_free(" + name + ")";
            } else {
                throw new Error("Unexpected gtype " + gType);
            }
        } else {
            return null;
        }
    }
}
