/*
 * TypeGenerator.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.codegen;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.operationaldynamics.defsparser.Block;
import com.operationaldynamics.defsparser.FunctionBlock;

/**
 * Base class for the Generators which create the files for types we are
 * rendering into Java classes: GObjects, boxeds/structs, enums, etc
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 */
abstract class TypeGenerator extends Generator
{
    private Thing objectType;

    private List functions;
    
    TypeGenerator(Thing objectType, List functions) {
        super();
        this.objectType = objectType;
        this.functions = functions;
    }

    public boolean writeJavaCode(PrintWriter out) {
        
        writeJavaHeader(out);
        writeImportStatements(functions, out);
        writeJavaBody(functions, out);
        
        return true;
    }
    
    public boolean writeCCode(PrintWriter out) {
        
        writeCHeader(out);
        writeCBody(functions, out);
        
        return true;
    }
    
    protected void writeJavaHeader(final PrintWriter out) {
        commonFileHeader(out, objectType.bindingsClass + ".java");
        packageStatementAndImports(out);
    }

    protected void writeJavaBody(final List blocks, final PrintWriter out) {
        
        Iterator iter;
        
        translationClassDeclaration(out);  

        if ( blocks == null ) {
            return;
        }
        
        iter = blocks.iterator();
        while ( iter.hasNext() ) {
            Generator gen;
            Block block;
            
            block = (Block) iter.next();
            assert ( block instanceof FunctionBlock ) :
                "Ups!! No FunctionBlock as Type functions";

            gen = block.createGenerator();
            gen.writeJavaCode(out);
        }

        out.println("}");
    }

    protected void writeCHeader(final PrintWriter out) {
        commonFileHeader(out, objectType.bindingsClass + ".c");
        hashIncludeStatements(out);
    }

    protected void writeCBody(final List blocks, final PrintWriter out) {
        Iterator iter;

        if ( blocks == null ) {
            return;
        }
        
        iter = blocks.iterator();
        while ( iter.hasNext() ) {
            Generator gen;
            Block block;
            
            block = (Block) iter.next();
            assert ( block instanceof FunctionBlock ) :
                "Ups!! No FunctionBlock as Type functions";

            gen = block.createGenerator();
            gen.writeCCode(out);
        }
    }
    
    /**
     * Compose the copyright header common to all generated sources files.
     */
    /*
     * I truely cannot believe that I just converted a here doc into a series
     * of single line strings with \n at the end of each. Oh well, it's done
     * now, and this was really the only long block of text.
     */
    protected static void commonFileHeader(PrintWriter out, String fileName) {
        out.print("/*\n");
        out.print(" * ");
        out.print(fileName);
        out.print("\n *\n");
        out.print(" * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd\n");
        out.print(" *\n");
        out.print(" * The code in this file, and the library it is a part of, are made available\n");
        out.print(" * to you by the authors under the terms of the \"GNU General Public Licence,\n");
        out.print(" * version 2\" plus the \"Classpath Exception\" (you may link to this code as a\n");
        out.print(" * library into other programs provided you don't make a derivation of it).\n");
        out.print(" * See the LICENCE file for the terms governing usage and redistribution.\n");
        out.print(" *\n");
        out.print(" *                      THIS FILE IS GENERATED CODE!\n");
        out.print(" *\n");
        out.print(" * To modify its contents or behaviour, either update the generation program,\n");
        out.print(" * change the information in the source defs file, or implement an override for\n");
        out.print(" * this class.\n");
        out.print(" */\n");
    }

    protected void packageStatementAndImports(final PrintWriter out) {
        out.print("package ");
        out.print(objectType.bindingsPackage);
        out.print(";\n\n");
    
        out.print("import org.gnome.glib.Plumbing;\n");
    }

    protected void translationClassDeclaration(final PrintWriter out) {
        out.print("\n");
        out.print("final class ");
        out.print(objectType.bindingsClass);
        out.print(" extends Plumbing\n{\n");
    
        out.print("    ");
        out.print("private ");
        out.print(objectType.bindingsClass);
        out.print("() {}\n");
    }

    protected void hashIncludeStatements(final PrintWriter out) {
        out.print("\n");
        out.print("#include <jni.h>\n");
        out.print("#include <gtk/gtk.h>\n");
        out.print("#include \"bindings_java.h\"\n");
    
        out.print("#include \"");
        out.print(encodeJavaClassName(objectType.bindingsPackage, objectType.bindingsClass));
        out.print(".h\";\n");
    }

    private static void writeImportStatements(final List blocks, 
            final PrintWriter out) {
        final Set types;
        Iterator blockiter, iter;

        types = new HashSet();

        if ( blocks == null ) {
            return;
        }
        
        /*
         * FIXME: I don't like this loop here. It's much better to have it
         * in #usesTypes() method of TypeBlock!!
         */
        blockiter = blocks.iterator();
        while ( blockiter.hasNext() ) {
            List things;

            things = ( (Block) blockiter.next() ).usesTypes();

            iter = things.iterator();
            while (iter.hasNext()) {
                Thing t = (Thing) iter.next();
                if (t instanceof FundamentalThing) {
                    continue;
                }
                // As a Set it won't do duplicates. Ta-da.
                types.add(t);
            }
        }

        /*
         * And now output the actual code for the include statements. TODO
         * More than anything, this is what shouldn't be here. FUTURE sort the
         * includes, perhaps with TreeSet, but that will need compareTo() in
         * Thing.
         */
        iter = types.iterator();

        while (iter.hasNext()) {
            Thing t = (Thing) iter.next();

            out.print("import ");

            out.print(t.fullyQualifiedJavaClassName());
            out.print(";\n");
        }
    }

}
