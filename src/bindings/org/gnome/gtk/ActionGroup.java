/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
 * Copyright © 2007      Vreixo Formoso
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

import org.gnome.glib.Object;

/**
 * A group of Actions.
 * 
 * <p>
 * Actions are generally organized into groups. All Actions that would make
 * sense to use in a particular context should be in a single group. Multiple
 * ActionGroups may be used for a particular user interface. In fact, it is
 * expected that most nontrivial applications will make use of multiple
 * ActionGroups. For example, in an application that can edit multiple
 * documents, you would likely have one ActionGroup holding global Actions
 * (e.g. quit, about, new), and one ActionGroup per document holding Actions
 * that act on that document (eg. save, cut/copy/paste, etc). Each Window's
 * Menus would be constructed from a combination of two ActionGroups.
 * 
 * @author Vreixo Formoso
 * @since 4.0.4
 */
public class ActionGroup extends Object
{
    protected ActionGroup(long pointer) {
        super(pointer);
    }

    /**
     * Create a new ActionGroup.
     * 
     * @param name
     *            TODO what the name is for? it seems only useful for
     *            accelerator questions that we will expose in another way!
     */
    public ActionGroup(String name) {
        super(GtkActionGroup.createActionGroup(name));
    }

    /**
     * Sets whether Actions in this ActionGroup can respond to user events.
     * 
     * <p>
     * Notice that both the Action itself and the ActionGroup need to be
     * sensitive to actually allow the user to activate the Action. See
     * {@link Action#setSensitive(boolean) Action.setSensitive()} for the
     * other half of this equation.
     */
    public void setSensitive(boolean sensitive) {
        GtkActionGroup.setSensitive(this, sensitive);
    }

    /**
     * Whether this ActionGroup is sensitive, i.e., if its Actions can be
     * activated by users. Take care that even if the ActionGroup is
     * sensitive, any or all of its Actions may not be.
     */
    public boolean getSensitive() {
        return GtkActionGroup.getSensitive(this);
    }

    /**
     * Sets whether Actions in this ActionGroup are visible to the user.
     * 
     * <p>
     * Notice that both the Action itself and the ActionGroup need to be
     * visible in order the users can see them displayed on screen. See
     * {@link Action#setVisible(boolean) Action.setVisible()} for the
     * corresponding per-Action method.
     */
    public void setVisible(boolean visible) {
        GtkActionGroup.setVisible(this, visible);
    }

    /**
     * Get if this ActionGroup is visible for the user. Take care that even if
     * the ActionGroup is visible, its Actions might not be.
     */
    public boolean getVisible() {
        return GtkActionGroup.getVisible(this);
    }

    /**
     * Adds an Action to this ActionGroup.
     */
    /*
     * FIXME Gtk+ docs claim that this function can lead to problems if the
     * user tries to modify the accelerator of a MenuItem associated with the
     * Action. Thus, I'm tempted to map it to
     * GtkActionGroup.addActionWithAccel, with a "" accelerator. Anyway, given
     * that accelerators are not exposed yet, I will delay this discussion a
     * bit.
     */
    public void addAction(Action action) {
        GtkActionGroup.addAction(this, action);
    }
}
