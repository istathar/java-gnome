/*
 * FileFilter.java
 *
 * Copyright (c) 2007-2009 Operational Dynamics Consulting Pty Ltd
 * Copyright (c)      2009 Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * FileFilters objects are used to restrict the files being shown on a
 * {@link FileChooser FileChooser}.
 * 
 * <p>
 * The most typical usage is to only show the file types the application can
 * deal with. On "Open" Dialogs, a FileFilter can be used to only shown the
 * files the application can open. On the other side, on "Save as" Dialogs
 * they can be used as a way to let users choose the format in which to save
 * the document.
 * 
 * <p>
 * You will usually {@link FileChooser#addFilter(FileFilter) add} several
 * FileFilters to your FileChooser Widget. The FileChooser will show a
 * ComboBox with the different Filters, that the user can use to choose a
 * specific file type. You should use the {@link #setName(String) setName()}
 * method to set the name used as the label for the FileFilter on the
 * ComboBox.
 * 
 * <p>
 * In a FileFilter, you specify the files to shown either as with its MIME
 * type, with the {@link #addMimeType(String) addMimeType()} method, or as a
 * shell style glob pattern, with the {@link #addPattern(String) addPattern()}
 * method. You can add several MIME types or patterns to a single FileFilter.
 * This is useful, for example, to provide a "All Supported Files" filter.
 * 
 * <p>
 * As an example, consider the following code:
 * 
 * <pre>
 * FileChooserDialog openDialog;
 * FileFilter pngImages, jpegImages, allImages;
 * 
 * openDialog = new FileChooserDialog(&quot;Open...&quot;, w, FileChooserAction.OPEN);
 * 
 * // you can specify a MIME type...
 * pngImages = new FileFilter(&quot;Portable Network Graphics (PNG)&quot;);
 * pngImages.addMimeType(&quot;image/png&quot;);
 * 
 * // ...or a pattern
 * jpegImages = new FileFilter(&quot;JPEG Image&quot;);
 * jpegImages.addPattern(&quot;*.jpg&quot;);
 * 
 * // of course you can use several patterns or MIME types per filter
 * jpegImages.addPattern(&quot;*.jpeg&quot;);
 * 
 * // and you can use both MIME types and patterns in a single filter
 * allImages = new FileFilter(&quot;All supported images&quot;);
 * allImages.addMimeType(&quot;image/png&quot;);
 * allImages.addPattern(&quot;*.jpg&quot;);
 * allImages.addPattern(&quot;*.jpeg&quot;);
 * 
 * // Finally, you should add each filter to the FileChooser
 * openDialog.addFilter(allImages);
 * openDialog.addFilter(pngImages);
 * openDialog.addFilter(jpegImages);
 * </pre>
 * 
 * @author Vreixo Formoso
 * @since 4.0.12
 */
public class FileFilter extends Object
{
    protected FileFilter(long pointer) {
        super(pointer);
    }

    /**
     * Create a new FileFilter
     * 
     * @since 4.0.12
     */
    public FileFilter() {
        super(GtkFileFilter.createFileFilter());
    }

    /**
     * Create a new FileFilter with the given name. This is equivalent to
     * create a FileFilter and the call {@link #setName(String) setName()}
     * method.
     * 
     * @since 4.0.12
     */
    public FileFilter(String name) {
        super(GtkFileFilter.createFileFilter());
        setName(name);
    }

    /**
     * Sets the human-readable name of the filter; this is the String that
     * will be displayed in the FileChooser user interface if there is a
     * selectable list of filters.
     * 
     * @since 4.0.12
     */
    public void setName(String name) {
        GtkFileFilter.setName(this, name);
    }

    /**
     * Add a rule allowing a given MIME type to the filter.
     * 
     * <p>
     * Filtering by MIME types handles aliasing and subclassing of mime types;
     * e.g. a filter for <code>"text/plain"</code> also matches a file with
     * MIME type <code>"application/rtf"</code>, since
     * <code>"application/rtf"</code> is a subclass of
     * <code>"text/plain"</code>. Note that FileFilter allows wildcards for
     * the subtype of a MIME type, so you can e.g. filter for
     * <code>"image/*"</code>.
     * 
     * @since 4.0.12
     */
    public void addMimeType(String mimeType) {
        GtkFileFilter.addMimeType(this, mimeType);
    }

    /**
     * Adds a rule allowing a shell style glob to the filter.
     * 
     * @since 4.0.12
     */
    public void addPattern(String pattern) {
        GtkFileFilter.addPattern(this, pattern);
    }
}
