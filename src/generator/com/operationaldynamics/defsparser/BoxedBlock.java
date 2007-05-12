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

import java.util.ArrayList;
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
 * (define-boxed Cursor
 *   (in-module &quot;Gdk&quot;)
 *   (c-name &quot;GdkCursor&quot;)
 *   (gtype-id &quot;GDK_TYPE_CURSOR&quot;)
 *   (copy-func &quot;gdk_cursor_ref&quot;)
 *   (release-func &quot;gdk_cursor_unref&quot;)
 *   (fields
 *     '(&quot;GdkCursorType&quot; &quot;type&quot;)
 *   )
 * )
 * </pre>
 * 
 * @author Vreixo Formoso
 */
public class BoxedBlock extends TypeBlock
{
    /*
     * TODO any reason to make these protected?
     */
    protected String copyFunc;

    protected String releaseFunc;

    protected String[][] fields;

    public BoxedBlock(final String blockName, final List characteristics, final List fields) {
        super(blockName, characteristics);

        processFields(fields);
    }

    /**
     * The "(fields ...)" subcharacteristic lines present in some GBoxed
     * definitions define the fields of the underlying C struct wrapped by the
     * boxed. The idea is that we need setXXX and getXXX for each of them to
     * allow java developers access them.
     */
    protected final void processFields(List fields) {
        this.fields = (String[][]) fields.toArray(new String[fields.size()][]);
    }

    protected final void setCopyFunc(final String copyFunc) {
        this.copyFunc = copyFunc;
    }

    protected final void setReleaseFunc(final String releaseFunc) {
        this.releaseFunc = releaseFunc;
    }

    public Thing createThing() {
        return new BoxedThing(addPointerSymbol(cName), moduleToJavaPackage(inModule), cName, blockName);
    }

    public Generator createGenerator(final DefsFile data) {
        return new BoxedGenerator(data, fields);
    }

    public List usesTypes() {
        List types;

        types = new ArrayList(fields.length);

        for (int i = 0; i < fields.length; i++) {
            types.add(Thing.lookup(fields[i][0]));
        }

        return types;
    }
}
