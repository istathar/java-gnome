/*
 * FileChooserWidget.java
 *
 * Copyright (c) 2009 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2009 Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import java.net.URI;

/**
 * A Widget used to select files.
 * 
 * <p>
 * Note that in most cases you don't need this at all! If you only want to
 * provide a way to let user select a file, {@link FileChooserDialog} or
 * {@link FileChooserButton} are much better alternatives.
 * 
 * @author Vreixo Formoso
 * @since 4.0.12
 */
public class FileChooserWidget extends VBox implements FileChooser
{
    protected FileChooserWidget(long pointer) {
        super(pointer);
    }

    /**
     * Create a new FileChooserWidget.
     */
    public FileChooserWidget(FileChooserAction action) {
        super(GtkFileChooserWidget.createFileChooserWidget(action));
    }

    public String getCurrentFolder() {
        return GtkFileChooser.getCurrentFolder(this);
    }

    public boolean setCurrentFolder(String directory) {
        return GtkFileChooser.setCurrentFolder(this, directory);
    }

    public String getFilename() {
        return GtkFileChooser.getFilename(this);
    }

    public void setAction(FileChooserAction action) {
        GtkFileChooser.setAction(this, action);
    }

    public FileChooserAction getAction() {
        return GtkFileChooser.getAction(this);
    }

    public URI getURI() {
        String uri = GtkFileChooser.getUri(this);
        if (uri != null) {
            return URI.create(uri);
        } else {
            return null;
        }
    }

    public void addFilter(FileFilter filter) {
        GtkFileChooser.addFilter(this, filter);
    }

    public void setFilter(FileFilter filter) {
        GtkFileChooser.setFilter(this, filter);
    }

    public FileFilter getFilter() {
        return GtkFileChooser.getFilter(this);
    }

    public boolean setFilename(String filename) {
        if (filename.charAt(0) == '/') {
            return GtkFileChooser.setFilename(this, filename);
        } else {
            throw new IllegalArgumentException("The filename argument must be an absolute path");
        }
    }
}