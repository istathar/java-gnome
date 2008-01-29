/*
 * AboutDialog.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.gnome.gdk.Pixbuf;

/**
 * A Dialog which presents information about a program. <img
 * src="AboutDialog.png" class="snapshot"> The AboutDialog presents simple
 * meta information including program {@link #setProgramName(String) name}, a
 * short {@link #setComments(String) description},
 * {@link #setCopyright(String) copyright} info, and then lists of
 * {@link #setAuthors(String[]) authors}, documenters, and translators of
 * responsible for the program.
 * 
 * <p>
 * As a convention, every GNOME application has a MenuItem labelled "About" in
 * the main "Help" menu.
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public class AboutDialog extends Dialog
{
    protected AboutDialog(long pointer) {
        super(pointer);
    }

    /**
     * Instantiate a new AboutDialog. You'll need to call the various setters
     * in this class to populate it.
     * 
     * @since 4.0.6
     */
    public AboutDialog() {
        super(GtkAboutDialog.createAboutDialog());
    }

    /**
     * Specify the name of the application. Some people choose to use a human
     * readable name like "<code>The Epiphany Web Browser</code>", whereas
     * others will use the project name, for example "<code>epiphany</code>".
     * This will be shown prominently in a large font as the title of the
     * Dialog.
     * 
     * @since 4.0.6
     */
    public void setProgramName(String name) {
        GtkAboutDialog.setProgramName(this, name);
    }

    /**
     * Set the version number of this release. It will be shown beside the
     * program name.
     * 
     * @since 4.0.6
     */
    public void setVersion(String version) {
        GtkAboutDialog.setVersion(this, version);
    }

    /**
     * Specify the copyright holder of the application. This should be short
     * and to the point, for example:
     * 
     * <pre>
     * setCopyright(&quot;Copyright (c) 2008 ACME, Inc&quot;);
     * </pre>
     * 
     * @since 4.0.6
     */
    public void setCopyright(String text) {
        GtkAboutDialog.setCopyright(this, text);
    }

    /**
     * Set a simple description of the program. This will essentially be shown
     * as a subtitle to your application's name.
     * 
     * @since 4.0.6
     */
    public void setComments(String text) {
        GtkAboutDialog.setComments(this, text);
    }

    /**
     * Set the image to be displayed in the AboutDialog. Most apps just want
     * to use the default icon which was set with
     * {@link Gtk#setDefaultIcon(Pixbuf) Gtk.setDefaultIcon()}.
     * 
     * @since 4.0.6
     */
    public void setLogo(Pixbuf icon) {
        GtkAboutDialog.setLogo(this, icon);
    }

    /**
     * Add a list of authors to the AboutDialog. You pass in an array of
     * Strings, with one author listed per String, for example:
     * 
     * <pre>
     * about.setAuthors(new String[] {
     *         &quot;Andrew Cowie &lt;andrew@operationaldynamics.com&gt;&quot;,
     *         &quot;Srichand Pendyala &lt;srichand.pendyala@gmail.com&gt;&quot;,
     *         &quot;Vreixo Formoso Lopes &lt;metalpain2002@yahoo.es&gt;&quot;,
     *         &quot;Sebastian Mancke &lt;s.mancke@tarent.de&gt;&quot;
     * });
     * </pre>
     * 
     * It is indeed tradition for authors of Open Source Software to include
     * their email addresses when identifying their work.
     * 
     * @since 4.0.6
     */
    public void setAuthors(String[] authors) {
        GtkAboutDialog.setAuthors(this, authors);
    }
}
