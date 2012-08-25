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
 * A Dialog preconfigured to present a cautionary message to the user and to
 * request confirmation of an action.
 * 
 * <p>
 * Be careful not to overdo it - a Dialog popping up to confirm every last
 * action is a) really annoying and worse b) will desensitize the user to the
 * message - they will just start ignoring the Dialog and thus probably miss
 * the chance to avoid something <i>really</i> important.
 * 
 * <p>
 * This is a modal MessageDialog of type {@link MessageType#WARNING WARNING}
 * with an "Ok" Button for the user to confirm they want to proceed, and a
 * "Cancel" Button for them to abort the operation.
 * 
 * <p>
 * 
 * <pre>
 * final Window w;
 * final Dialog d;
 * final ResponseType result;
 * 
 * d = new WarningMessageDialog(w, &quot;You are about set off the fire alarm&quot;,
 *         &quot;Is that what you really want to do?&quot;);
 * result = d.run();
 * 
 * d.hide();
 * if (result == ResponseType.OK) {
 *     // do dangerous action
 * }
 * </pre>
 * 
 * @author Andrew Cowie
 * @author Vreixo Formoso
 */
public class WarningMessageDialog extends MessageDialog
{
    public WarningMessageDialog(Window parent, String primary, String secondary) {
        super(parent, true, MessageType.WARNING, ButtonsType.OK_CANCEL, primary);
        if (secondary != null) {
            this.setSecondaryText(secondary);
        }
    }
}
