/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd
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
 * "Claspath Exception"), the copyright holders of this library give you
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
package org.gnome.gtk;

/**
 * Base class for Widgets and various other elements in GTK.
 * 
 * <p>
 * <i><b>This is the wrapper around <code>GtkObject</code>!</b></i>
 * 
 * <p>
 * <i>Since the GObject type system was abstracted out from GTK some time
 * after GTK was first written, <code>GtkObject</code> predates
 * <code>GObject</code>; almost all of the functionality originally resident
 * in <code>GtkObject</code> was moved to <code>GObject</code> long ago. Its
 * presence in the type hierarchy is largely for backwards compatibility. Only
 * people hacking on java-gnome itself will have any need to interact with
 * this class, and then only rarely.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.0
 * @deprecated
 */
/*
 * Once upon a time we had a few additional property accessors that were
 * specific to GTK here, but given that we now have setPropertyObject(), they
 * are no longer necessary.
 */
public abstract class Object extends org.gnome.glib.Object
{
    protected Object(long pointer) {
        super(pointer);
    }

    /**
     * Ask everything connected to this object to release its references. Use
     * this in preference to Container's {@link Container#remove(Widget)
     * remove()} if you don't need the Widget any more.
     * 
     * <p>
     * <i>We didn't expose this for a long time in java-gnome; after all,
     * memory management of both Java references and GObject Ref counts is
     * handled automatically by the diabolical cunning of this most excellent
     * library. It turns out, however, that this does not <code>free()</code>
     * the GtkObject's memory; it really only does what it says: ask other
     * GtkObjects to drop whatever Refs they may be holding to this object.
     * Thus if this GtkWidget is in a GtkContainer and you call</i>
     * <code>destroy()</code> <i>the GtkContainer will drop its Refs to this
     * GtkWidget thereby breaking its parent-child relationship.</i>
     * 
     * <p>
     * <i>Note that Java's references remain, so the object will <b>not</b>,
     * in fact, be eligable for finalizing until you drop all your references;
     * ie, the Java side Proxy Object goes out of scope. Nevertheless calling
     * this will speed up release of resources associated with a Widget, so
     * it's a good idea. Once you've done so,</i> <code>finalize()</code>
     * <i>will be traversed a short time later and the GObject will be
     * released.</i>
     * 
     * @since 4.0.18
     * @deprecated This has moved to Widget's {@link Widget#destroy()
     *             destroy()} in 4.1
     */
    public void destroy() {
        GtkObject.destroy(this);
    }

    /**
     * Signal handler for when an Object requests that all other code holding
     * references to it release those references. In Java-speak, that means if
     * you have this object in a variable or structure of some kind,
     * <code>null</code> it out to to release the strong reference to the
     * object.
     * 
     * @author Andrew Cowie
     * @since 4.0.18
     * @deprecated Moved to <code>Widget.Destroy</code> in 4.1
     */
    public interface Destroy extends GtkObject.DestroySignal
    {
        /**
         * Release any references you hold to the given <code>source</code>
         * Object.
         */
        public void onDestroy(Object source);
    }

    /**
     * Hook up a <code>Object.Destroy</code> handler.
     * 
     * @since 4.0.18
     * @deprecated This has moved to Widget's
     *             {@link Widget#connect(Widget.Destroy) connect()} in 4.1
     */
    public void connect(Object.Destroy handler) {
        GtkObject.connect(this, handler, false);
    }
}
