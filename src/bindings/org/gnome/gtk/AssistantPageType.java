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

import org.freedesktop.bindings.Constant;

/**
 * Used for determining the page role inside the {@link Assistant}. It's used
 * to handle buttons sensitivity and visibility.
 * 
 * <p>
 * Note that an assistant needs to end its page flow with a page of type
 * {@link #CONFIRM CONFIRM} or {@link #SUMMARY SUMMARY} to be correct.
 * 
 * @author Stefan Prelle
 * @since 4.0.8
 */
public final class AssistantPageType extends Constant
{
    private AssistantPageType(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * The page has regular contents. It provides a 'Cancel','Back'
     */
    public static final AssistantPageType CONTENT = new AssistantPageType(GtkAssistantPageType.CONTENT,
            "CONTENT");

    /**
     * The page contains an introduction to the assistant task.
     */
    public static final AssistantPageType INTRO = new AssistantPageType(GtkAssistantPageType.INTRO,
            "INTRO");

    /**
     * The page lets the user confirm or deny the changes. It provides a
     * 'Cancel', 'Back' and 'Apply' button.
     */
    public static final AssistantPageType CONFIRM = new AssistantPageType(GtkAssistantPageType.CONFIRM,
            "CONFIRM");

    /**
     * The page informs the user of the changes done. It provides a 'Close'
     * button.
     */
    public static final AssistantPageType SUMMARY = new AssistantPageType(GtkAssistantPageType.SUMMARY,
            "SUMMARY");

    /**
     * Used for tasks that take a long time to complete, blocks the assistant
     * until the page is marked as complete with
     * {@link Assistant#setPageComplete(Widget, boolean) setPageComplete()}
     */
    public static final AssistantPageType PROGRESS = new AssistantPageType(
            GtkAssistantPageType.PROGRESS, "PROGRESS");

}
