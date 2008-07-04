/*
 * TypeBlock.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.defsparser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.operationaldynamics.codegen.Thing;
import com.operationaldynamics.driver.DefsFile;

/**
 * Base class for blocks that declare type information. <b>Note that TypeBlock
 * does not imply total information for what will become an entire Java
 * compilation unit; it's just the information that allows us to know that the
 * type exists in the first place.</b> The context that this block lives in
 * is represented in this program by the {@link DefsFile} class.
 * 
 * @author Andrew Cowie
 */
public abstract class TypeBlock extends Block
{
    protected String inModule;

    protected String cName;

    protected List<String> importHeader;

    protected TypeBlock(final String blockName, final List<String[]> characteristics) {
        super(blockName, characteristics);
    }

    final void setCName(final String name) {
        this.cName = name;
    }

    /**
     * We ignore gtype-id completely as it is unnecessary in the java-gnome
     * context, but this stub allows the reflection to work when it hits a
     * characteristic so named.
     */
    protected final void setGtypeId(final String gtypeId) {}

    protected final void setInModule(final String inModule) {
        this.inModule = inModule;
    }

    /**
     * Some types need additional import header, other than <gtk/gtk.h>. This
     * characteristic, introduced by java-gnome, solves that problem.
     */
    protected final void setImportHeader(final String importHeader) {
        if (this.importHeader == null) {
            this.importHeader = new ArrayList<String>(1);
        }
        this.importHeader.add(importHeader);
    }

    /**
     * Convert the module name to a package suiting the Java namespace and our
     * mapping into it. At the moment, this simply means "Gtk" ->
     * "org.gnome.gtk". FUTURE when we wrap things outside of GNOME this will
     * have to change somewhat radically.
     */
    protected static String moduleToJavaPackage(String module) {
        StringBuffer buf;
        char ch;

        if (module.equals("Cairo")) {
            return "org.freedesktop.cairo";
        }

        buf = new StringBuffer(module);

        ch = buf.charAt(0);
        ch = Character.toLowerCase(ch);
        buf.setCharAt(0, ch);

        buf.insert(0, "org.gnome.");

        return buf.toString();
    }

    /*
     * Default bahavior, as most TypeBlock's don't import anything. This will
     * have to change if we start using the values subcharacteristics in
     * ObjectBlocks.
     */
    public List<Thing> usesTypes() {
        return Collections.emptyList();
    }
}
