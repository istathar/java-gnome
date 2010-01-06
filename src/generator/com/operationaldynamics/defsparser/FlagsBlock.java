/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
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

import com.operationaldynamics.codegen.FlagsGenerator;
import com.operationaldynamics.codegen.FlagsThing;
import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.codegen.Thing;
import com.operationaldynamics.driver.DefsFile;

/**
 * Block object representing the .defs data defining a Flag. A flag is like an
 * enum, but their ordinal values can't be obtained in an automatic way.
 * 
 * Source .defs data for an emum is of the following form:
 * 
 * <pre>
 *  (define-flags WindowState
 *    (in-module &quot;Gdk&quot;)
 *    (c-name &quot;GdkWindowState&quot;)
 *    (gtype-id &quot;GDK_TYPE_WINDOW_STATE&quot;)
 *    (values
 *      '(&quot;withdrawn&quot; &quot;GDK_WINDOW_STATE_WITHDRAWN&quot;)
 *      '(&quot;iconified&quot; &quot;GDK_WINDOW_STATE_ICONIFIED&quot;)
 *    )
 *  )
 * </pre>
 * 
 * Like Enums, all the information needed to define the Java code that will
 * result for a Flag type is in a single (define...) stanza.
 * 
 * @author Vreixo Formoso
 */
public class FlagsBlock extends EnumBlock
{
    FlagsBlock(String blockName, List<String[]> characteristics, List<String[]> values) {
        super(blockName, characteristics, values);
    }

    public Thing createThing() {
        FlagsThing t = new FlagsThing(cName, moduleToJavaPackage(inModule), inModule + blockName,
                blockName);
        t.setImportHeader(importHeader);
        return t;
    }

    public Generator createGenerator(final DefsFile data) {
        return new FlagsGenerator(data, values);
    }
}
