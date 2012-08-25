/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
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
 * The space at the bottom of an application Window where status messages are
 * displayed. <img src="Statusbar.png" class="snapshot">
 * 
 * <p>
 * A Statusbar should generally be present at the bottom of a GNOME
 * application's main Window by being the first Widget to be packed with
 * respect to the end of the VBox used to vertically layout such a Window:
 * 
 * <pre>
 * status = new Statusbar();
 * vbox.packEnd(status, false, false, 0);
 * window.add(vbox);
 * </pre>
 * 
 * in fact, this is so much a convention that a Statusbar should be present
 * regardless of whether or not you plan to have messages to display. Make
 * sure you pack it into the top level VBox with <var>expand</var> and
 * <var>fill</var> set to <code>false</code> as shown. Statusbars somewhat by
 * definition should stay narrow; they shouldn't grow thicker if the user
 * resizes vertically.
 * 
 * <p>
 * The text in the Statusbar is set with {@link #setMessage(String)
 * setMessage()}. Most applications leave the Statusbar empty as a default
 * state, but if you wish to inform the user of things being in a normal
 * state, you can certainly do so:
 * 
 * <pre>
 * status.setMessage(&quot;Ready&quot;);
 * </pre>
 * 
 * or similar words appropriate to your program.
 * 
 * <p>
 * Statusbars are excellent for providing hints to the user about what the
 * user can do next (see <b><code>Inkscape</code></b> as a terrific example),
 * or to update the user with what the application is up to when processing.
 * Keep in mind, however, that this is considered only an assistance; people
 * don't necessarily look to the Statusbar when wondering what is going on
 * (and further, many applications allow the user to turn the Statusbar off
 * entirely). If you need to provide urgent information to the user then use a
 * Dialog.
 * 
 * <p>
 * As a Box subclass, you can pack other Widgets into the Statusbar. This is a
 * great place for a ProgressBar if you have a long running worker thread that
 * needs to report its percentage completion. Widgets so added will be packed
 * after the Label that is (obviously) internally present to display the
 * status messages. If it is a small Window you may want to constrain the size
 * of the ProgressBar lest it blot out the message:
 * 
 * <pre>
 * status = new Statusbar();
 * bar = new ProgressBar();
 * bar.setSizeRequest(30, -1);
 * status.packEnd(bar, false, false, 0);
 * </pre>
 * 
 * then later:
 * 
 * <pre>
 * status.setMessage(&quot;Sending all your files to the CIA...&quot;);
 * bar.setFraction(0.35);
 * </pre>
 * 
 * <p>
 * A visible Statusbar usually drawn with a grip in the bottom right corner
 * which most users will recognized as a stylized visual indication that the
 * Window can be resized. If necessary, it can be suppressed by calling
 * {@link #setHasResizeGrip(boolean) setHasResizeGrip(false)}.
 * 
 * <p>
 * <i>The underlying API in <code>GtkStatusbar</code> is ridiculously
 * complicated for absolutely no good reason. We have therefore compressed its
 * hideous stack-based mechanism into the simple single-message interface
 * presented here.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
/*
 * Not only is the whole context thing excessive, it turns out it is
 * unnecessary. Per gtkstatusbar.h, there is a default context, id 0, which
 * can be used as a global default.
 */
public class Statusbar extends HBox
{
    protected Statusbar(long pointer) {
        super(pointer);
        GtkStatusbar.push(this, 0, "");
    }

    /**
     * Construct a new Statusbar. The initial message is empty (you don't need
     * to explicitly set it to be so).
     * 
     * @since 4.0.6
     */
    /*
     * Strictly speaking, adding an empty status message isn't necessary, but
     * it makes the logic for our setMessage() method a bit sounder.
     */
    public Statusbar() {
        super(GtkStatusbar.createStatusbar());
        GtkStatusbar.push(this, 0, "");
    }

    /**
     * Set the message showing in the Statusbar. You can call this frequently
     * with whatever indication you wish to display to the user; the last
     * message will be discarded.
     * 
     * <p>
     * If you want to clear the message, simply pass the empty string,
     * <code>""</code>.
     * 
     * @since 4.0.6
     */
    public void setMessage(String text) {
        GtkStatusbar.pop(this, 0);
        GtkStatusbar.push(this, 0, text);
    }
}
