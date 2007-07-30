/*
 * BoxedBlock.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2007 Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.defsparser;

import java.util.List;

import com.operationaldynamics.codegen.BoxedGenerator;
import com.operationaldynamics.codegen.BoxedThing;
import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.codegen.Thing;
import com.operationaldynamics.driver.DefsFile;

/**
 * Block object representing the .defs data defining a GBoxed. Source .defs
 * data for a boxed is of the following form:
 * 
 * <pre>
 *  (define-boxed Cursor
 *    (in-module &quot;Gdk&quot;)
 *    (c-name &quot;GdkCursor&quot;)
 *    (gtype-id &quot;GDK_TYPE_CURSOR&quot;)
 *    (copy-func &quot;gdk_cursor_ref&quot;)
 *    (release-func &quot;gdk_cursor_unref&quot;)
 *    (fields
 *      '(&quot;GdkCursorType&quot; &quot;type&quot;)
 *    )
 *  )
 * </pre>
 * 
 * The "(fields ...)" subcharacteristic lines present in some GBoxed
 * definitions define the fields of the underlying C struct wrapped by the
 * boxed. These are represented by GetterBlock and SetterBlock instances that
 * are manually created when DefsParser encounters a (define-boxed ...)
 * 
 * @author Vreixo Formoso
 * @author Andrew Cowie
 */
public class BoxedBlock extends TypeBlock
{
    protected String copyFunc;

    protected String releaseFunc;

    public BoxedBlock(final String blockName, final List characteristics) {
        super(blockName, characteristics);
    }

    protected final void setCopyFunc(final String copyFunc) {
        this.copyFunc = copyFunc;
    }

    protected final void setReleaseFunc(final String releaseFunc) {
        this.releaseFunc = releaseFunc;
    }

    public Thing createThing() {

        /*
         * GdkEventAny is an exception to the usual GBoxeds wrapping, as we
         * don't need to expose it in our public API. Thus, the java type that
         * wraps this boxed is a org.gnome.gdk.Event.
         */
        String javaType = "EventAny".equals(blockName) ? "Event" : blockName;
        return new BoxedThing(addPointerSymbol(cName), moduleToJavaPackage(inModule), cName, javaType);
    }

    public Generator createGenerator(final DefsFile data) {
        return new BoxedGenerator(data);
    }
}
