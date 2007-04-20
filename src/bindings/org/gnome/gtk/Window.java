/*
 * Window.java
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * The top level Widget that contains other Widgets. Typical examples are
 * application windows, dialog boxes, and popup menus.
 * 
 * @author Andrew Cowie
 * @author Srichand Pendyala
 * @since 4.0.0
 */
public class Window extends Bin
{
    protected Window(long pointer) {
        super(pointer);
    }

    /**
     * Create a new Window.
     */
    public Window() {
        super(GtkWindow.createWindow(WindowType.TOPLEVEL));
    }

    /**
     * Create a new Window of the specified type. In general you don't need to
     * use this; see the comments in
     * {@link org.gnome.gtk.WindowType WindowType}; in particular,
     * {@link org.gnome.gtk.WindowType#POPUP POPUP} is <b>not</b> for dialog
     * windows!
     * 
     * @since 4.0.0
     */
    public Window(WindowType type) {
        super(GtkWindow.createWindow(type));
    }

    /**
     * Sets the title that will be displayed in the Window's title bar.
     * <p>
     * The title of a Window is an important usability factor. It should help
     * the user distinguish this Window from others they may have open - and
     * that gets tough when many, many applications are running. The key is to
     * get the most relevant information is first. Examples of good titles
     * are:
     * <ul>
     * <li><b>Invoice.odt</b>
     * <li><b>Invoice.odt - OpenOffice</b>
     * <li><b>andrew@procyon:~/src</b>
     * <li><b>Audio Configuration</b>
     * </ul>
     * 
     * <p>
     * This is important because the list of Windows titles the user is
     * looking at may have been truncated with the result that you can't tell
     * the difference between different Windows of the same application. For
     * example, these are no good if you can only see the first 20 characters
     * of the title:
     * 
     * <ul>
     * <li><b>OpenOffice 2.0.4 brought to yo</b>u by the letter B! -
     * Invoice.odt
     * <li><b>OpenOffice 2.0.4 brought to yo</b>u by the letter B! -
     * LoveLetter.odt
     * </ul>
     * 
     * <p>
     * Don't forget that Windows also have an icon, and that icon will show in
     * the list too, so you don't even really need the application name -
     * leaving more room for the details that help identify this Window
     * uniquely.
     * 
     * @see <a
     *      href="http://developer.gnome.org/projects/gup/hig/2.0/windows-primary.html#primary-window-titles">GNOME
     *      Human Interface Guidelines</a>
     * @since 4.0.0
     */
    public void setTitle(String title) {
        GtkWindow.setTitle(this, title);
    }

    /**
     * By default, Windows are decorated with a title bar,
     * minimize/maximize/close buttons, a border, resize handles, etc. This
     * isn't done by your program, though - it's automatically by the window
     * manager which is a part of your desktop. Some window managers allow GTK
     * to disable these decorations, creating a borderless window. If you set
     * the decorated property to <code>false</code> using this method, GTK
     * will do its best to convince the window manager not to decorate the
     * Window.
     * 
     * <p>
     * <ul>
     * <li>You will have no problem creating undecorated Windows on a GNOME
     * desktop.
     * <li>Apparently, turning off decorations will not work if the Window is
     * already visible on some systems. So if you're going to use
     * <code>setDecorated(false)</code>, call it before invoking
     * {@link Widget#show() show()} on the Window.
     * </ul>
     * 
     * @since 4.0.0
     */
    public void setDecorated(boolean decorated) {
        GtkWindow.setDecorated(this, decorated);
    }

    /**
     * Sets the default size of a Window. If the Window's "natural" size (the
     * size request resulting from the aggregate requests of all the Widgets
     * contained in this Window) is larger than the default, the default will
     * be ignored. The default size of a Window only affects the first time a
     * Window is shown; if a Window is hidden and re-shown, it will remember
     * the size it had prior to hiding, rather than using the default size.
     * 
     * <p>
     * Depending on your needs, {@link #resize() resize()} could be more
     * appropriate, especially if the Window is already realized . resize()
     * changes the current size of the Window, rather than the size to be used
     * on initial display which is what this method is for.
     * 
     * <p>
     * Incidentally, Windows can't be 0x0; the minimum size is 1x1.
     * 
     * @param width
     *            The default minimum width you'd like to set. A value of 0
     *            will be silently bumped to 1. A value of -1 will unset any
     *            previous default width setting.
     * @param height
     *            Same.
     * @since 4.0.1
     */
    public void setDefaultSize(int width, int height) {
        GtkWindow.setDefaultSize(this, width, height);
    }

    /**
     * This signal arises when a user tries to close a top level window. As
     * you would expect, the default handler for this signal destroys the
     * Window.
     * 
     * <p>
     * If you want to prevent a Window from being closed, connect this signal,
     * and return <code>true</code>. Often the reason to do this is to pop
     * up a notification Dialog, for example asking you if you want to save an
     * unsaved document. Another technique is reusing a Window: rather than
     * going to all the trouble to create this Window again, you can just
     * temporarily hide it by calling {@link Widget#hide() Widget's hide()}.
     * 
     * <p>
     * <i> This signal is actually "delete-event" which lives on GtkWidget.
     * That, however, is for implementation reasons in GTK because all the
     * GdkEvents go to GtkWidget even though this particular signal only has
     * to do with Windows. So, we expose it here. </i>
     * 
     * @author Andrew Cowie
     * @author Devdas Bhagat
     * @since 4.0.0
     */
    public interface DELETE extends GtkWidget.DELETE_EVENT
    {
        public boolean onDeleteEvent(Widget source, Object event);
    }

    public void connect(DELETE handler) {
        GtkWidget.connect(this, handler);
    }
}
