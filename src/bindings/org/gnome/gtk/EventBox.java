/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
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
package org.gnome.gtk;

/**
 * Add the ability for a Widget to accept events. A fair number of Widgets do
 * <i>not</i> have the ability to process events coming from the user because,
 * under ordinary circumstances, they don't need it. If you want a Label or
 * Image to take keystrokes or mouse clicks, then you instantiate an EventBox
 * and pack your Widget into it.
 * 
 * <p>
 * Quite a number of Widgets share underlying resources. That's not something
 * you normally need to worry about as a developer, but if you have called
 * {@link Widget#getWindow() getWindow()} and find yourself needing an
 * underlying [<code>org.gnome.gdk</code>] Window specifically for your
 * Widget, then use an EventBox.
 * 
 * <p>
 * EventBoxes can also be used for things like painting a different background
 * colour behind an otherwise transparent Widget.
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public class EventBox extends Bin
{
    protected EventBox(long pointer) {
        super(pointer);
    }

    /**
     * Create a new EventBox. Be sure to call {@link #setAboveChild(boolean)
     * setAboveChild()} and {@link #setVisibleWindow(boolean)
     * setVisibleWindow()} if necessary.
     * 
     * @since 4.0.6
     */
    public EventBox() {
        super(GtkEventBox.createEventBox());
    }

    /**
     * Whether the EventBox should be "above" its child or not. If above, the
     * EventBox will receive <i>all</i> the events targeted at the child. If
     * below, then the events will first go to the child and then to the
     * EventBox. The default is <code>false</code> which is usually what you
     * want (ie, you don't want to mess with existing functionality in a
     * Widget, just add to it).
     * 
     * @since 4.0.6
     */
    public void setAboveChild(boolean setting) {
        GtkEventBox.setAboveChild(this, setting);
    }

    /**
     * Whether the EventBox will have a "visible" child. The default is
     * <code>true</code>; ordinarily you want the child Widget to be displayed
     * normally.
     * 
     * @since 4.0.6
     */
    /*
     * TODO there is a lot more to this than described here. Someone please
     * read the GTK docs, experiment with this, and improve our API
     * documentation accordingly.
     */
    public void setVisibleWindow(boolean setting) {
        GtkEventBox.setVisibleWindow(this, setting);
    }
}
