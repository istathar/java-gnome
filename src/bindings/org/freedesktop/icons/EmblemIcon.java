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
 * Named icons with "emblems". These are small images that can be used to
 * annotate an icon (you might have used these for files in Nautilus).
 * 
 * @author Guillaume Mazoyer
 * @author Andrew Cowie
 * @since 4.0.17
 */
public class EmblemIcon extends Icon
{
    protected EmblemIcon(String name) {
        super(name);
    }

    public static final Icon EMBLEM_DEFAULT = new EmblemIcon("emblem-default");

    public static final Icon EMBLEM_DOCUMENTS = new EmblemIcon("emblem-documents");

    public static final Icon EMBLEM_DOWNLOADS = new EmblemIcon("emblem-downloads");

    public static final Icon EMBLEM_FAVORITE = new EmblemIcon("emblem-favorite");

    public static final Icon EMBLEM_GENERIC = new EmblemIcon("emblem-generic");

    public static final Icon EMBLEM_IMPORTANT = new EmblemIcon("emblem-important");

    public static final Icon EMBLEM_MAIL = new EmblemIcon("emblem-mail");

    public static final Icon EMBLEM_NEW = new EmblemIcon("emblem-new");

    public static final Icon EMBLEM_PACKAGE = new EmblemIcon("emblem-package");

    public static final Icon EMBLEM_PHOTOS = new EmblemIcon("emblem-photos");

    public static final Icon EMBLEM_READONLY = new EmblemIcon("emblem-readonly");

    public static final Icon EMBLEM_SHARED = new EmblemIcon("emblem-shared");

    public static final Icon EMBLEM_SYMBOLIC_LINK = new EmblemIcon("emblem-symbolic-link");

    public static final Icon EMBLEM_SYSTEM = new EmblemIcon("emblem-system");

    public static final Icon EMBLEM_UNREADABLE = new EmblemIcon("emblem-unreadable");

    public static final Icon EMBLEM_URGENT = new EmblemIcon("emblem-urgent");

    public static final Icon EMBLEM_WEB = new EmblemIcon("emblem-web");
}
