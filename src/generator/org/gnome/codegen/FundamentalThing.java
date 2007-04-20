/*
 * FundamentalThing.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.codegen;

class FundamentalThing extends Thing
{
    FundamentalThing(String gType, String javaType, String nativeType, String jniType) {
        super(gType, null, null, javaType, null, nativeType, jniType);

        /*
         * Account for the encoded "const-gchar*" that represents the C type
         * "const gchar*". Are there others? Dunno, but this will handle them.
         */
        if (gType.indexOf("-") != -1) {
            StringBuffer buf;
            int i;

            buf = new StringBuffer(gType);
            while ((i = buf.indexOf("-")) != -1) {
                buf.setCharAt(i, ' ');
            }
            
            cType = buf.toString();
        }
    }
}
