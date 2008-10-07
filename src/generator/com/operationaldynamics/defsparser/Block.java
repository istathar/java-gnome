/*
 * Block.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package com.operationaldynamics.defsparser;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;

import com.operationaldynamics.codegen.Generator;
import com.operationaldynamics.codegen.Thing;
import com.operationaldynamics.driver.DefsFile;

/**
 * Base class representing a block s-expression .defs data. The system is
 * predicated around the notion that by the time you are done creating a Block
 * object, you know that Thing (type) the Block refers to, and can look it up
 * and set it accordingly.
 * 
 * <p>
 * All members are to be protected, and setters are to be used (via reflection
 * in the DefsParser) to map the characteristics in each block to fields.
 * 
 * @author Andrew Cowie
 */
public abstract class Block
{
    /**
     * The short "python" name for this Object/Function/Method/Constructor/etc
     */
    protected final String blockName;

    protected Block(final String blockName, final List<String[]> characteristics) {
        this.blockName = blockName;

        processCharacteristics(characteristics);
    }

    /**
     * When encountered by the constructor as it calls the reflexive machinery
     * in processCharacteristics(), this will throw UnnecessaryCodeException
     * to signal DefsParser to skip this Block object and move on to the next.
     */
    protected final void setDeprecated(final String deprecated) {
        throw new UnnecessaryCodeException(deprecated);
    }

    protected final void setUnnecessary(final String msg) {
        throw new UnnecessaryCodeException(msg);
    }

    /**
     * Add the '*' pointer character because our indexes are built on the
     * convention that the type that can be looked up is "GObject*", not
     * "GObject". Used by the createThing() and createGenerator()
     * implementations.
     */
    static final String addPointerSymbol(final String bareGObjectName) {
        StringBuffer buf;

        if (bareGObjectName == null) {
            return null;
        }
        buf = new StringBuffer(bareGObjectName);
        buf.append('*');
        return buf.toString().intern();
    }

    /**
     * Reflection engine to populate object members based on key/value pairs
     * in list
     */
    private void processCharacteristics(final List<String[]> list) {
        Iterator<String[]> iter;

        if (list == null) {
            return;
        }

        iter = list.iterator();
        while (iter.hasNext()) {
            final String[] array;
            final String name, value;
            final Class<?>[] signature;
            Method setter;
            Class<?> target;

            array = iter.next();

            name = nameToMethod(array[0]);
            value = array[1];

            /*
             * our setters are always setSomething(String)
             */
            signature = new Class[] {
                String.class
            };

            /*
             * Class's getMethod() searches super classes, but only for public
             * methods. So we have to use getDeclaredMethod() and walk up the
             * hierarchy. What a bore.
             */

            target = this.getClass();
            setter = null;

            while (target != Object.class) {
                try {
                    setter = target.getDeclaredMethod(name, signature);
                    break;
                } catch (NoSuchMethodException nsme) {
                    target = target.getSuperclass();
                }
            }
            if (setter == null) {
                throw new IllegalStateException("Setter " + name + "() not found");
            }

            /*
             * Call setter.
             */

            try {
                setter.invoke(this, new Object[] {
                    value
                });
            } catch (IllegalArgumentException e) {
                // If all screwed up
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                // This shouldn't happen - setters are all protected here or
                // in super classes above us.
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                if (e.getCause() instanceof UnnecessaryCodeException) {
                    throw (UnnecessaryCodeException) e.getCause();
                }
                // the setter itself threw an exception! Crazy.
                e.printStackTrace();
            }
        }
    }

    /**
     * .defs files have characteristic names like "c-name" and "return-value".
     * Convert these to "setCName" and "setReturnValue" for calling setter.
     */
    static final String nameToMethod(final String key) {
        StringBuffer buf;
        int i;
        char ch;

        buf = new StringBuffer(key);
        buf.insert(0, '-');

        i = 0;
        while ((i = buf.indexOf("-", i)) != -1) {
            buf.deleteCharAt(i);
            ch = buf.charAt(i);
            buf.setCharAt(i, Character.toUpperCase(ch));
        }

        buf.insert(0, "set");

        return buf.toString();
    }

    /**
     * Get the code factory appropriate to this Block type, with the
     * additional supporting infromation of what class it fits into.
     */
    public abstract Generator createGenerator(DefsFile file);

    /**
     * Get the type wrapper Thing appropriate to this Block. Actually only
     * relevant for TypeBlock subclasses.
     */
    public abstract Thing createThing();

    public String toString() {
        final StringBuffer buf;
        Class<?> target;

        buf = new StringBuffer();

        buf.append(this.getClass().getName());
        buf.append("\n");

        target = this.getClass();
        while (target != Object.class) {
            Field[] fields;
            int i;

            fields = target.getDeclaredFields();
            for (i = 0; i < fields.length; i++) {
                String name;
                Object value;

                // There's something weird about a few extra Class objects
                // showing up.
                if (fields[i].getType() == Class.class) {
                    continue;
                }

                buf.append("\t");
                name = fields[i].getName();
                buf.append(name);
                buf.append(": ");

                try {
                    value = fields[i].get(this);
                } catch (IllegalArgumentException e) {
                    // huh?
                    e.printStackTrace();
                    continue;
                } catch (IllegalAccessException e) {
                    // go figure.
                    e.printStackTrace();
                    continue;
                }

                if (name.equals("parameters")) {
                    buf.append("\n");
                    String[][] p = (String[][]) value;
                    for (int j = 0; j < p.length; j++) {
                        buf.append("\t\t");
                        buf.append(p[j][0]);
                        buf.append(" ");
                        buf.append(p[j][1]);
                        buf.append("\n");
                    }
                } else {
                    buf.append(value);
                    buf.append("\n");
                }
            }
            target = target.getSuperclass();
        }

        return buf.toString();
    }

    /**
     * In order to put the import statements that goes at the top of the Java
     * class file, we need to extract a list of all the types used in this
     * Block.
     * 
     * @return a List of Things. It would have been an array, but subclasses
     *         often want to build up the list; this is easier if less type
     *         safe (in a pre-generics world).
     */
    public abstract List<Thing> usesTypes();
}
