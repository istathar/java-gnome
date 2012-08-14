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
 * Named icons representing folders.
 * 
 * @author Guillaume Mazoyer
 * @author Andrew Cowie
 * @since 4.0.17
 */
public class PlaceIcon extends Icon
{
    protected PlaceIcon(String name) {
        super(name);
    }

    public static final Icon FOLDER_DOCUMENTS = new PlaceIcon("folder-documents");

    public static final Icon FOLDER_DOWNLOAD = new PlaceIcon("folder-download");

    public static final Icon FOLDER_MUSIC = new PlaceIcon("folder-music");

    public static final Icon FOLDER_PICTURES = new PlaceIcon("folder-pictures");

    public static final Icon FOLDER = new PlaceIcon("folder");

    public static final Icon FOLDER_PUBLICSHARE = new PlaceIcon("folder-publicshare");

    public static final Icon FOLDER_REMOTE = new PlaceIcon("folder-remote");

    public static final Icon FOLDER_SAVED_SEARCH = new PlaceIcon("folder-saved-search");

    public static final Icon FOLDER_TEMPLATES = new PlaceIcon("folder-templates");

    public static final Icon FOLDER_VIDEOS = new PlaceIcon("folder-videos");

    public static final Icon NETWORK_SERVER = new PlaceIcon("network-server");

    public static final Icon NETWORK_WORKGROUP = new PlaceIcon("network-workgroup");

    public static final Icon START_HERE = new PlaceIcon("start-here");

    public static final Icon USER_BOOKMARKS = new PlaceIcon("user-bookmarks");

    public static final Icon USER_DESKTOP = new PlaceIcon("user-desktop");

    public static final Icon USER_HOME = new PlaceIcon("user-home");

    public static final Icon USER_TRASH = new PlaceIcon("user-trash");
}
