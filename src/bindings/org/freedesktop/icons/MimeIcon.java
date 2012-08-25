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
 * Named icons representing MIME types.
 * 
 * @author Guillaume Mazoyer
 * @author Andrew Cowie
 * @since 4.0.17
 */
public class MimeIcon extends Icon
{
    protected MimeIcon(String name) {
        super(name);
    }

    public static final Icon APPLICATION_CERTIFICATE = new MimeIcon("application-certificate");

    public static final Icon APPLICATION_X_EXECUTABLE = new MimeIcon("application-x-executable");

    public static final Icon AUDIO_X_GENERIC = new MimeIcon("audio-x-generic");

    public static final Icon FONT_X_GENERIC = new MimeIcon("font-x-generic");

    public static final Icon IMAGE_X_GENERIC = new MimeIcon("image-x-generic");

    public static final Icon PACKAGE_X_GENERIC = new MimeIcon("package-x-generic");

    public static final Icon TEXT_HTML = new MimeIcon("text-html");

    public static final Icon TEXT_X_GENERIC = new MimeIcon("text-x-generic");

    public static final Icon TEXT_X_GENERIC_TEMPLATE = new MimeIcon("text-x-generic-template");

    public static final Icon TEXT_X_PREVIEW = new MimeIcon("text-x-preview");

    public static final Icon TEXT_X_SCRIPT = new MimeIcon("text-x-script");

    public static final Icon VIDEO_X_GENERIC = new MimeIcon("video-x-generic");

    public static final Icon X_OFFICE_ADDRESS_BOOK = new MimeIcon("x-office-address-book");

    public static final Icon X_OFFICE_CALENDAR = new MimeIcon("x-office-calendar");

    public static final Icon X_OFFICE_DOCUMENT = new MimeIcon("x-office-document");

    public static final Icon X_OFFICE_DOCUMENT_TEMPLATE = new MimeIcon("x-office-document-template");

    public static final Icon X_OFFICE_DRAWING = new MimeIcon("x-office-drawing");

    public static final Icon X_OFFICE_DRAWING_TEMPLATE = new MimeIcon("x-office-drawing-template");

    public static final Icon X_OFFICE_PRESENTATION = new MimeIcon("x-office-presentation");

    public static final Icon X_OFFICE_PRESENTATION_TEMPLATE = new MimeIcon(
            "x-office-presentation-template");

    public static final Icon X_OFFICE_SPREADSHEET = new MimeIcon("x-office-spreadsheet");

    public static final Icon X_OFFICE_SPREADSHEET_TEMPLATE = new MimeIcon(
            "x-office-spreadsheet-template");
}
