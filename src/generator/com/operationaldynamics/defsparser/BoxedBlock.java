/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
 * Copyright © 2007      Vreixo Formoso
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

    public BoxedBlock(final String blockName, final List<String[]> characteristics) {
        super(blockName, characteristics);
    }

    protected final void setCopyFunc(final String copyFunc) {
        this.copyFunc = copyFunc;
    }

    protected final void setReleaseFunc(final String releaseFunc) {
        this.releaseFunc = releaseFunc;
    }

    public Thing createThing() {
        final String javaType;
        final BoxedThing t;

        /*
         * GdkEventAny is an exception to the usual GBoxeds wrapping, as we
         * don't need to expose it in our public API. Thus, the java type that
         * wraps this boxed is a org.gnome.gdk.Event.
         */
        javaType = "EventAny".equals(blockName) ? "Event" : blockName;

        t = new BoxedThing(addPointerSymbol(cName), moduleToJavaPackage(inModule), cName, javaType);
        t.setImportHeader(importHeader);

        return t;
    }

    public Generator createGenerator(final DefsFile data) {
        return new BoxedGenerator(data);
    }
}
