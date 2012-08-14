/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2007 Vreixo Formoso
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
package org.gnome.glib;

/**
 * An exception thrown by the underlying library.
 * 
 * <p>
 * <b>It is inappropriate for a public API wrapper method to throw this
 * Exception. It is to be caught and re-thrown as a new Exception of an
 * appropriate Java type.</b> For example, if a function uses this mechanism
 * to report being unable to locate a file on disk, then the wrapper method
 * should do the following:
 * 
 * <pre>
 * public String getModificationDate(String filename) {
 *     try {
 *         NativeLibrary.getModificationDate(this, filename);
 *     } catch (GlibException ge) {
 *         throw new FileNotFoundException(ge.getMessage());
 *     }
 * }
 * </pre>
 * 
 * <p>
 * <i> We map native functions that take a <code>GError**</code> argument to
 * throwing this Exception if the function actually returns an error via that
 * parameter; the error parameter is masked from the binding hacker's view by
 * being handled in the C side JNI code.</i>
 * 
 * <p>
 * <i>Note that <code>GError</code>s are meant as Exceptions in the Java sense
 * of the term; they do not represent crashes nor RuntimeExceptions; they are
 * conditions that the programmer will need to create appropriate user
 * interface code for to allow the <i>user</i> to deal with.</i>
 * 
 * @author Vreixo Formoso
 * @author Andrew Cowie
 * @since 4.0.4
 */
public class GlibException extends Exception
{
    private static final long serialVersionUID = 1;

    protected GlibException() {
        super();
    }

    protected GlibException(String msg) {
        super(msg);
    }
}
