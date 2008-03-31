/*
 * Internationalization.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008 Vreixo Formoso
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.glib;

import java.text.MessageFormat;

/**
 * Internationalization support for java-gnome.
 * 
 * <p>
 * <i>Internationalization</i>, also know as i18n, is the process of
 * preparing our applications to support multiple languages. The objective is
 * that messages shown to the user are not presented in english, but in
 * his/her native language. Note that i18n does not actually mean that you, as
 * an application developer, need to translate your software to all <b>locales</b>
 * it is plan to be used. This is done later, by translators, in a process
 * called <i>localization</i>. Applications developers just need to be aware
 * of that, and prepare the software to be localized later. This preparation
 * is what we call internationalization.
 * 
 * <p>
 * First of all, i18n means we need to allow message translation. So, we
 * shouldn't output english messages directly. Instead, we would call a
 * function that takes care of message translation. In the same way, there are
 * other aspects that affect internationalization. The way dates, numbers,
 * currency, etc are formatted is locale-dependent. Fortunately, the methods
 * in this class will simplify that task for you.
 * 
 * <p>
 * Let's take the following Java code as an example:
 * 
 * <pre>
 * System.out.println(&quot;The file &quot; + filename + &quot; was modified on &quot; + date);
 * </pre>
 * 
 * Obviously the code above is not internationalized at all. For that, you
 * need to translate the message, but also formatting the date according, or
 * take care that the positional parameters (filename, date) may have a
 * different order in another language. Obviously, you don't need to know how
 * this message is written in all languages of the world!! You just need to
 * let translators do that. With the above sentence, translators would need to
 * look for messages in your sources, modify them and compile your app again.
 * Not a good idea. It is better if you just change the way you output
 * messages. For example, if you write:
 * 
 * <pre>
 * System.out.println(_(&quot;The file {0} was modified on {1,date,long}&quot;, filename, date));
 * </pre>
 * 
 * The function _() can take care of that for you, returning the same message,
 * but localized to the user native language! This is internationalization.
 * 
 * <p>
 * In java-gnome, internationalize your apps is as easy as the code above. You
 * should use the {@link #_(String, Object...) _()} method with any message
 * you want to show to the user (not that messages intended for developers,
 * such as debug messages, don't need to be localized). As this is a static
 * method, you can use an static import:
 * 
 * <pre>
 * import static org.gnome.glib.Internationalization._;
 * </pre>
 * 
 * To format the parameters, you have to use the {@link MessageFormat} syntax.
 * To put it briefly, you need:
 * 
 * <ul>
 * <li> Refer to any parameter with a <code>{n}</code> in your message,
 * where <code>n</code> is the 0-based index of the parameter, as you submit
 * it to the _() function. </li>
 * <li> If the parameter needs to be formatted (numbers, dates, currency), etc
 * you should pass a format type parameter (number, date, time) and optionally
 * a format style qualifier. Take a look at {@link MessageFormat}
 * documentation for further details. </li>
 * </ul>
 * 
 * <p>
 * Of course, computers don't make magic (still), so you still need a
 * translation process to actually localize your messages. However, with this
 * approach this is done outside the code, in files named "message catalogs"
 * where the translated messages are stored. In fact, the _() function will
 * look up the given message in that catalogs, to show the translated version
 * to the user. As a developer, you don't need to create them, it is the task
 * of translator. However, so knowledge of that process is still needed.
 * 
 * <p>
 * java-gnome uses the same translation infrastructure GNOME and most GNOME
 * apps use: GNU gettext tools [TODO link to gettext page]. To put it briefly,
 * this is the process used by gettext to generate the message catalogs:
 * 
 * <p>
 * First of all, the messages used in your code need to be extracted. This is
 * done by the <code>xgettext</code> tool. It is able to distinguish between
 * translatable messages and other Strings because the formers are marked with
 * the calls to _(). So, the following call:
 * 
 * <pre>
 * xgettext -o myapp.pot --omit-header --keyword=_ --keyword=N_ path/to/TYPE.java
 * </pre>
 * 
 * will extract the messages used in the TYPE.java class to a file called
 * myapp.pot. It is a POT file, a template with the list of translatable
 * messages, that translators will use to know the message he/she must
 * translate. With the command:
 * 
 * <pre>
 * msginit -i myapp.pot -o {LANG}.po
 * </pre>
 * 
 * the translator generate a PO file, where the messages will be translated.
 * PO files contain the translated messages. There is one PO file per locale,
 * named with the language_COUNTRY scheme, for example: en_US.po, es_ES.po,
 * fr.po, pt.po, pt_BR.po... Usually, the PO files of your project are stored
 * in a po directory.
 * 
 * <p>
 * To be used by gettext, those files need to be "compiled" to a binary form,
 * the MO files. That is done with the <code>msgfmt</code> command:
 * 
 * <pre>
 * msgfmt -o myapp.mo es.po
 * </pre>
 * 
 * MO files are installed together with other application artifacts, usually
 * under /usr/share/locale/${locale}/LC_MESSAGES/${packageName}.mo, where
 * ${locale} is the locale the MO is translated to, and ${packageName} an
 * unique identifier for you application, usually your application name. This
 * is needed because under that directory are stored MO files for all
 * installed apps. For example, localized messages for "pt_BR" locale of a
 * "myapp" application would be stored in the
 * /usr/share/locale/pt_BR/LC_MESSAGES/myapp.mo.
 * 
 * <p>
 * As you could figure out, you will need to tell gettext where those files
 * are actually localed. This is done with the
 * {@link #init(String, String) init()} method, that must be called before any
 * usage of the i18n infrastructure.
 * 
 * <p>
 * In some cases, this might be a problem. Let's suppose you have some
 * messages stored in a static array:
 * 
 * <pre>
 * static final String msgs = new String[] {
 *    "one message",
 *    "another message",
 *    ...
 * };
 * </pre>
 * 
 * You cannot call _() there, as probably it will be executed before
 * library initialization. You can easily postpone the _() call to the
 * momment it is actually shown:
 * 
 * <pre>
 * System.out.println(_(msg[0]));
 * </pre>
 * 
 * But we still have a problem: the messages are not marked as translatable,
 * and thus they're not extracted by xgettext. For this case, we have the
 * {@link #N_(String) N_()} function. It does just this: mark the String
 * as translatable, without actually translate it. You use that as follows:
 * 
 * <pre>
 * // mark the Strings as translatable
 * static final String msgs = new String[] {
 *    N_("one message"),
 *    N_("another message"),
 *    ...
 * };
 * 
 * // and actually translate the String later
 * System.out.println(_(msg[0]));
 * </pre>
 * 
 * TODO documentation needs review!!
 * 
 * @author Vreixo Formoso
 * @since 4.0.7
 */
public final class Internationalization
{

    private Internationalization() {}

    /**
     * Translate and format a given message according to user locale.
     * 
     * <p>
     * This attempts to translate a text string into the user's native
     * language. You just need to call it with the message in english, as
     * follows:
     * 
     * <pre>
     * // a static import is really useful in this case
     * import static org.gnome.glib.Internationalization._;
     * 
     * ....
     * 
     * // you must remember to initialize i18n features somewhere in your code
     * Internationalization.init(....);
     * 
     * // and then call the function
     * String translated = _(&quot;Hello&quot;);
     * </pre>
     * 
     * <p>
     * This also supports message formatting and concatenation in a
     * language-neutral way. For example, let's suppose we want to print the
     * following message: "The file 'data.log' has been modified at March 21,
     * 2008 at 5:27:22 PM". This message usually would have two parameters,
     * the file name, and the date of modification. Of course, the data is
     * locale-dependent, as the dates are represented differently depending on
     * language or country. We could get the internationalized message with:
     * 
     * <pre>
     * String filename;
     * Date date;
     * _(&quot;&quot;The file '{0}' has been modified on {1,date,long} at {1,time}&quot;, filename, date);
     * </pre>
     * 
     * As you can see, it is easy to construct a given message from several
     * parameters, even if they have a format that is locale-dependent. The
     * actual formatting is done by {@link MessageFormat}, so take a look at
     * its documentation for all available format options.
     * 
     * @param msg
     *            The message to print, in english.
     * @param parameters
     *            Parameters of the message
     * @return The message translated and formatted properly. If not
     *         translation has found, it is returned in english.
     */
    public static final String _(String msg, java.lang.Object... parameters) {
        if (msg == null) {
            return null;
        }
        if (parameters.length == 0) {
            return gettext(msg);
        } else {
            return MessageFormat.format(gettext(msg), parameters);
        }
    }

    private static native final String gettext(String msg);

    /**
     * Mark the given message as translatable, without actually translate it.
     * This is useful, for example, for static fields that are initialized
     * before library initialization:
     * 
     * <pre>
     * private static final String HELLO_MESSAGE = N_("Hello!");
     * </pre>
     * 
     * Remember that you still need to call _() later, to actually translate
     * the message.
     * 
     * <pre>
     * System.out.println(_(HELLO_MESSAGE));
     * </pre>
     * 
     * @param msg
     *      The message to mark as translatable
     * @return
     *      The submitted msg! This is only useful to mark a String as
     *      translatable and let xgettext to extract it.
     */
    public static final String N_(String msg) {
        return msg;
    }

    /**
     * Initialize the i18n support. You must call this function before any
     * other usage of the i18n functions.
     * 
     * @param packageName
     *            Application name
     * @param localeDir
     *            Directory where to find the message catalogs (usually
     *            /usr/share/locale) The actually message catalog is found at
     *            ${localeDir}/${locale}/LC_MESSAGES/${packageName}.mo For
     *            example: /usr/share/locale/pt_BR/LC_MESSAGES/myapp.mo
     */
    /*
     * FIXME this init() message is a bit ugly. It would a better idea to
     * have both args in a properties files, maybe i18n.properties, that
     * is read from the CLASSPATH in the Internationalization static initilizer, for example.
     */
    public static void init(String packageName, String localeDir) {
        bindtextdomain(packageName, localeDir);
    }

    private static native final void bindtextdomain(String packageName, String localeDir);
}
