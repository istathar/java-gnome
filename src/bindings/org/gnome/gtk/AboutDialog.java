/*
 * AboutDialog.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import java.net.URI;
import java.net.URISyntaxException;

import org.gnome.gdk.Pixbuf;

/**
 * A Dialog which presents information about a program. <img
 * src="AboutDialog.png" class="snapshot"> The AboutDialog presents simple
 * meta information including program {@link #setProgramName(String) name}, a
 * short {@link #setComments(String) description},
 * {@link #setCopyright(String) copyright} info, and then lists of
 * {@link #setAuthors(String[]) authors}, {@link #setDocumenters(String[])
 * documenters}, {@link #setArtists(String[]) artists} responsible for the
 * program. Those who contributed by {@link #setTranslatorCredits(String)
 * translating} the application can also be listed.
 * 
 * <p>
 * As a convention, every GNOME application has a MenuItem labelled "About" in
 * the main "Help" menu.
 * 
 * @author Andrew Cowie
 * @author Guillaume Mazoyer
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
     * Add a "licence" Button to the AboutDialog. The String set by this
     * method will be displayed if the user clicks on this Button.
     * 
     * <p>
     * If <code>null</code> the licence Button is hidden.
     * 
     * @since 4.0.12
     */
    public void setLicense(String text) {
        GtkAboutDialog.setLicense(this, text);
    }

    /**
     * Wrap the licence text. If <code>true</code>, the licence text will be
     * automatically wrapped and the initial dialog size will be preserved.
     * 
     * @since 4.0.12
     */
    public void setWrapLicense(boolean setting) {
        GtkAboutDialog.setWrapLicense(this, setting);
    }

    /**
     * Set a link to your website. It is recommended to do in the standard
     * <code>http://www.example.com/</code> format.
     * 
     * <p>
     * <i>At the moment this "link" is just plain text and not live.</i>
     * 
     * @since 4.0.11
     */
    public void setWebsite(String website) {
        GtkAboutDialog.setWebsite(this, website);
    }

    /**
     * Set a label to modify the website link previously set by something more
     * friendly.
     * 
     * @since 4.0.13
     */
    public void setWebsiteLabel(String label) {
        GtkAboutDialog.setWebsiteLabel(this, label);
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
    public void setAuthors(String[] people) {
        GtkAboutDialog.setAuthors(this, people);
    }

    /**
     * Add a list of documenters to the AboutDialog. You pass in an array of
     * Strings, with one person per String, for example:
     * 
     * <pre>
     * about.setDocumenters(new String[] {
     *         &quot;Guillaume Mazoyer &lt;respawneral@gmail.com&gt;&quot;,
     *         &quot;Stefan Prelle &lt;stefan@prelle.org&gt;&quot;,
     *         &quot;Serkan Kaba &lt;serkan@gentoo.org&gt;&quot;
     * });
     * </pre>
     * 
     * @since 4.0.12
     */
    public void setDocumenters(String[] people) {
        GtkAboutDialog.setDocumenters(this, people);
    }

    /**
     * Add a list of artists to the AboutDialog. You pass in an array of
     * Strings, with one artist per String, for example:
     * 
     * <pre>
     * about.setArtists(new String[] {
     *     &quot;Joao Victor &lt;jvital@gmail.com&gt;&quot;
     * });
     * </pre>
     * 
     * @since 4.0.12
     */
    public void setArtists(String[] people) {
        GtkAboutDialog.setArtists(this, people);
    }

    /**
     * Add a tab to the AboutDialog's Credit popup listing the people who have
     * contributed to translating the application into the active language.
     * 
     * <p>
     * This one is a bit different than <var>authors</var>,
     * <var>artists</var>, and <var>documenters</var> in that it is expected
     * that you'll pass the result of looking up the translation of the string
     * <code>"translator-credits"</code> as follows:
     * 
     * <pre>
     * about.setTranslatorCredits(_(&quot;translator-credits&quot;));
     * </pre>
     * 
     * This way, the user of your program will be shown the list of people who
     * worked on the translation for that particular locale. The translation
     * for the <code>"translator-credits"</code> string in the PO file should
     * still be names and email addresses as shown in
     * {@link #setAuthors(String[]) setAuthors()}.
     * 
     * <p>
     * See {@link org.freedesktop.bindings.Internationalization
     * Internationalization} for more details about the use of the
     * <code>_()</code> function.
     * 
     * <p>
     * <i>You could of course use any string as the index for lookup, but
     * "translator-credits" is the accepted convention, and people doing
     * translation work will recognize it.</i>
     * 
     * @since 4.0.10
     */
    public void setTranslatorCredits(String credits) {
        GtkAboutDialog.setTranslatorCredits(this, credits);
    }

    private static class EmailClickHandler implements GtkAboutDialog.EmailClickedSignal
    {
        private final EmailHook emailHook;

        public EmailClickHandler(EmailHook emailHook) {
            this.emailHook = emailHook;
        }

        public void onEmailClicked(AboutDialog source, String email) {
            emailHook.onEmailClicked(source, email);
        }
    }

    /**
     * Callback invoked when a email address is clicked.
     * 
     * @author Guillaume Mazoyer
     * @since 4.0.13
     */
    public interface EmailHook
    {
        public void onEmailClicked(AboutDialog source, String email);
    }

    /**
     * Hookup the <code>AboutDialog.EmailHook</code> callback that will be
     * used to handle email clicked actions.
     * 
     * @since 4.0.13
     */
    public void setEmailCallback(EmailHook handler) {
        GtkAboutDialogOverride.setEmailHook(this);
        GtkAboutDialog.connect(this, new EmailClickHandler(handler), false);
    }

    private static class UrlClickHandler implements GtkAboutDialog.UrlClickedSignal
    {
        private final UrlHook urlHook;

        public UrlClickHandler(UrlHook urlHook) {
            this.urlHook = urlHook;
        }

        public void onUrlClicked(AboutDialog source, String link) {
            try {
                urlHook.onUrlClicked(source, new URI(link));
            } catch (URISyntaxException e) {
                throw new RuntimeException("We shouldn't be throwing this exception", e);
            }
        }
    }

    /**
     * Callback invoked when the website is clicked.
     * 
     * @author Guillaume Mazoyer
     * @since 4.0.13
     */
    public interface UrlHook
    {
        public void onUrlClicked(AboutDialog source, URI link);
    }

    /**
     * Hookup the <code>AboutDialog.UrlHook</code> callback that will be used
     * to handle website button click actions.
     * 
     * @since 4.0.13
     */
    public void setUrlCallback(UrlHook handler) {
        GtkAboutDialogOverride.setUrlHook(this);
        GtkAboutDialog.connect(this, new UrlClickHandler(handler), false);
    }
}
