/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2011 Operational Dynamics Consulting, Pty Ltd and Others
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
 * Styles that help to manipulate the class value of the {@link StyleContext}
 * of a widget inside GTK.
 * 
 * @author Guillaume Mazoyer
 * @since 4.1.2
 */
public final class StyleClass extends Style
{
    protected StyleClass(String name) {
        super(name);
    }

    /**
     * A CSS class to match the window background.
     */
    public static final StyleClass BACKGROUND = new StyleClass("background");

    /**
     * A CSS class to match buttons.
     */
    public static final StyleClass BUTTON = new StyleClass("button");

    /**
     * A CSS class to match calendars.
     */
    public static final StyleClass CALENDAR = new StyleClass("calendar");

    /**
     * A CSS class to match content rendered in cell views.
     */
    public static final StyleClass CELL = new StyleClass("cell");

    /**
     * A CSS class to match check boxes.
     */
    public static final StyleClass CHECK = new StyleClass("check");

    /**
     * A CSS class to match the default widget.
     */
    public static final StyleClass DEFAULT = new StyleClass("default");

    /**
     * A CSS class to match text entries.
     */
    public static final StyleClass ENTRY = new StyleClass("entry");

    /**
     * A CSS class to match a header element.
     */
    public static final StyleClass HEADER = new StyleClass("header");

    /**
     * A CSS class to match popup menus.
     */
    public static final StyleClass MENU = new StyleClass("menu");

    /**
     * A CSS class to match radio buttons.
     */
    public static final StyleClass RADIO = new StyleClass("radio");

    /**
     * A CSS class to match the rubberband selection rectangle.
     */
    public static final StyleClass RUBBERBAND = new StyleClass("rubberband");

    /**
     * A CSS class to match scrollbars.
     */
    public static final StyleClass SCROLLBAR = new StyleClass("scrollbar");

    /**
     * A CSS class to match sliders.
     */
    public static final StyleClass SLIDER = new StyleClass("slider");

    /**
     * A CSS class to match tooltip windows.
     */
    public static final StyleClass TOOLTIP = new StyleClass("tooltip");

    /**
     * A CSS class to match troughs, as in scrollbars and progressbars.
     */
    public static final StyleClass TROUGH = new StyleClass("trough");

    /**
     * A CSS class to match an accelerator.
     */
    public static final StyleClass ACCELERATOR = new StyleClass("accelerator");

    /**
     * A widget class defining a dock area.
     */
    public static final StyleClass DOCK = new StyleClass("dock");

    /**
     * A widget class defining a resize grip.
     */
    public static final StyleClass GRIP = new StyleClass("grip");

    /**
     * A CSS class to menubars.
     */
    public static final StyleClass MENUBAR = new StyleClass("menubar");

    /**
     * A CSS class to match menu items.
     */
    public static final StyleClass MENUITEM = new StyleClass("menuitem");

    /**
     * A widget class defining a resize grip.
     */
    public static final StyleClass PROGRESSBAR = new StyleClass("progressbar");

    /**
     * A widget class defining a spinner.
     */
    public static final StyleClass SPINNER = new StyleClass("spinner");

    /**
     * A CSS class to match toolbars.
     */
    public static final StyleClass TOOLBAR = new StyleClass("toolbar");

    /**
     * A CSS class to match primary toolbars.
     */
    public static final StyleClass PRIMARY_TOOLBAR = new StyleClass("primary-toolbar");

    /**
     * A CSS class for a pane separator.
     */
    public static final StyleClass PANE_SEPARATOR = new StyleClass("pane-separator");

    /**
     * A CSS class for a separator.
     */
    public static final StyleClass SEPARATOR = new StyleClass("separator");

    /**
     * A CSS class for a drag-and-drop indicator.
     */
    public static final StyleClass DND = new StyleClass("dnd");

    /**
     * A widget class for an area displaying an error message, such as those
     * in infobars.
     */
    public static final StyleClass ERROR = new StyleClass("error");

    /**
     * A widget class defining an expander, such as those in treeviews.
     */
    public static final StyleClass EXPANDER = new StyleClass("expander");

    /**
     * A CSS class defining a frame delimiting content.
     */
    public static final StyleClass FRAME = new StyleClass("frame");

    /**
     * A CSS class defining a highlighted area, such as headings in
     * assistants.
     */
    public static final StyleClass HIGHLIGHT = new StyleClass("highlight");

    /**
     * A widget class for an area displaying an informational message, such as
     * those in infobars.
     */
    public static final StyleClass INFO = new StyleClass("info");

    /**
     * A widget class defining marks in a widget, such as in scales.
     */
    public static final StyleClass MARK = new StyleClass("mark");

    /**
     * A widget class defining a notebook.
     */
    public static final StyleClass NOTEBOOK = new StyleClass("notebook");

    /**
     * A widget class for an area displaying a question to the user, such as
     * those in infobars.
     */
    public static final StyleClass QUESTION = new StyleClass("question");

    /**
     * A CSS class to match scale widgets.
     */
    public static final StyleClass SCALE = new StyleClass("scale");

    /**
     * A widget class defining an spinbutton.
     */
    public static final StyleClass SPINBUTTON = new StyleClass("spinbutton");

    /**
     * A widget class defining a view, such as iconviews or treeviews.
     */
    public static final StyleClass VIEW = new StyleClass("view");

    /**
     * A widget class for an area displaying a warning message, such as those
     * in infobars.
     */
    public static final StyleClass WARNING = new StyleClass("warning");

    /**
     * A widget class for horizontally layered widgets.
     */
    public static final StyleClass HORIZONTAL = new StyleClass("horizontal");

    /**
     * A widget class for vertically layered widgets.
     */
    public static final StyleClass VERTICAL = new StyleClass("vertical");
}
