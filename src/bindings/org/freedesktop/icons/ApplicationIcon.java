/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2010 Operational Dynamics Consulting, Pty Ltd and Others
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
package org.freedesktop.icons;

/**
 * Named icons representing individual applications.
 * 
 * @author Guillaume Mazoyer
 * @author Andrew Cowie
 * @since 4.0.17
 */
public class ApplicationIcon extends Icon
{
    protected ApplicationIcon(String name) {
        super(name);
    }

    public static final Icon ACCESSORIES_CALCULATOR = new ApplicationIcon("accessories-calculator");

    public static final Icon ACCESSORIES_CHARACTER_MAP = new ApplicationIcon("accessories-character-map");

    public static final Icon ACCESSORIES_DICTIONARY = new ApplicationIcon("accessories-dictionary");

    public static final Icon ACCESSORIES_TEXT_EDITOR = new ApplicationIcon("accessories-text-editor");

    public static final Icon APPLETS_SCREENSHOOTER = new ApplicationIcon("applets-screenshooter");

    public static final Icon HELP_BROWSER = new ApplicationIcon("help-browser");

    public static final Icon LOGVIEWER = new ApplicationIcon("logviewer");

    public static final Icon MULTIMEDIA_VOLUME_CONTROL = new ApplicationIcon("multimedia-volume-control");

    public static final Icon PREFERENCES_DESKTOP_ACCESSIBILITY = new ApplicationIcon(
            "preferences-desktop-accessibility");

    public static final Icon PREFERENCES_DESKTOP_DISPLAY = new ApplicationIcon(
            "preferences-desktop-display");

    public static final Icon PREFERENCES_DESKTOP_FONT = new ApplicationIcon("preferences-desktop-font");

    public static final Icon PREFERENCES_DESKTOP_KEYBOARD = new ApplicationIcon(
            "preferences-desktop-keyboard");

    public static final Icon PREFERENCES_DESKTOP_KEYBOARD_SHORTCUTS = new ApplicationIcon(
            "preferences-desktop-keyboard-shortcuts");

    public static final Icon PREFERENCES_DESKTOP_LOCALE = new ApplicationIcon(
            "preferences-desktop-locale");

    public static final Icon PREFERENCES_DESKTOP_REMOTE_DESKTOP = new ApplicationIcon(
            "preferences-desktop-remote-desktop");

    public static final Icon PREFERENCES_DESKTOP_SCREENSAVER = new ApplicationIcon(
            "preferences-desktop-screensaver");

    public static final Icon PREFERENCES_DESKTOP_THEME = new ApplicationIcon("preferences-desktop-theme");

    public static final Icon PREFERENCES_DESKTOP_WALLPAPER = new ApplicationIcon(
            "preferences-desktop-wallpaper");

    public static final Icon PREFERENCES_SYSTEM_WINDOWS = new ApplicationIcon(
            "preferences-system-windows");

    public static final Icon SYSTEM_FILE_MANAGER = new ApplicationIcon("system-file-manager");

    public static final Icon SYSTEM_SOFTWARE_INSTALL = new ApplicationIcon("system-software-install");

    public static final Icon SYSTEM_SOFTWARE_UPDATE = new ApplicationIcon("system-software-update");

    public static final Icon SYSTEM_USERS = new ApplicationIcon("system-users");

    public static final Icon USER_INFO = new ApplicationIcon("user-info");

    public static final Icon UTILITIES_SYSTEM_MONITOR = new ApplicationIcon("utilities-system-monitor");

    public static final Icon UTILITIES_TERMINAL = new ApplicationIcon("utilities-terminal");

    public static final Icon WEB_BROWSER = new ApplicationIcon("web-browser");
}
