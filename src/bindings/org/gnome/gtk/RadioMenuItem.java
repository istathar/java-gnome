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
 * A special kind of CheckMenuItem used to select from a mutually exclusive
 * set of options.
 * 
 * <p>
 * A RadioMenuItem is somewhat similar to a CheckMenuItem, but it is shown as
 * an empty circle (rather than an empty square) and the selected MenuItem in
 * the group us shown with a dot inside (rather than a check mark).
 * 
 * <p>
 * However, while a CheckMenuItem can be used alone to choose between two
 * states, a RadioMenuItem is used together in a group of other (related)
 * RadioMenuItems to offer a choice of one of those MenuItems. Only a single
 * MenuItem in a group can the active at any one time.
 * 
 * @author Guillaume Mazoyer
 * @since 4.0.15
 */
public class RadioMenuItem extends CheckMenuItem
{
    /*
     * Reference keeps our group mechanism in scope, and powers getGroup()
     */
    private RadioGroup enclosingGroup;

    protected RadioMenuItem(long pointer) {
        super(pointer);
    }

    /**
     * Create a new RadioMenuItem with the given label. It will be placed in
     * its own group, you submit it later to the constructors of other
     * MenuItems that should be in the same group.
     * 
     * @param label
     *            The label that will be placed near the RadioMenuItem. If the
     *            text contains an underscore (<code>_</code>) it will be
     *            taken to be the mnemonic for the Widget.
     * @since 4.0.15
     */
    public RadioMenuItem(RadioGroup group, String label) {
        super(createFirstOrNext(group, label));
        group.setMember(this);
        enclosingGroup = group;
    }

    private static long createFirstOrNext(RadioGroup group, String label) {
        final RadioMenuItem first;

        first = (RadioMenuItem) group.getMember();

        if (first == null) {
            return GtkRadioMenuItem.createRadioMenuItemWithMnemonic(null, label);
        } else {
            return GtkRadioMenuItem.createRadioMenuItemWithMnemonicFromWidget(first, label);
        }
    }

    /**
     * Get the RadioMenuItemGroup that encloses this RadioMenuItem and the
     * others that belonging to the same mutual exclusion group.
     * 
     * @since 4.0.15
     */
    public RadioGroup getGroup() {
        return enclosingGroup;
    }
}
