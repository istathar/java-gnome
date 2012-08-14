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
 * A Dialog properly preconfigured to ask a yes or no question of the user.
 * 
 * <p>
 * This is a modal MessageDialog of type {@link MessageType#QUESTION QUESTION}
 * with an "Yes" and a "No" Button.
 * 
 * <p>
 * You would typically use this in conjunction with <code>run()</code> as
 * follows:
 * 
 * <pre>
 * final Window main;
 * final Dialog question;
 * final ResponseType response;
 * 
 * main = new Window();
 * ...
 * question = new QuestionMessageDialog(main, &quot;File exists!&quot;, &quot;Do you really want to overwrite it?&quot;);
 * response = question.run();
 * 
 * question.hide();
 * if (response == ResponseType.YES) {
 *     // save file
 * } else {
 *     // cancel and return to application
 * }
 * </pre>
 * 
 * which will result in something like:
 * 
 * <p>
 * <img src="QuestionMessageDialog.png">
 * 
 * <p>
 * (depending on your theme, icon set, window manager preferences, of course).
 * Notable here are the "question mark" icon and that the "No" Button has
 * focus by default.
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 */
public class QuestionMessageDialog extends MessageDialog
{
    public QuestionMessageDialog(Window parent, String primary, String secondary) {
        super(parent, true, MessageType.QUESTION, ButtonsType.YES_NO, primary);
        if (secondary != null) {
            this.setSecondaryText(secondary);
        }
    }
}
