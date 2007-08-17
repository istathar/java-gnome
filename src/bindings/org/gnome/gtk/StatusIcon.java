/*
 * StatusIcon.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd, and Others
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
 * An icon that is displayed in the "system tray".
 * <p>
 * The "system tray" or notification area is normally used for transient icons
 * that indicate some special state. For example, a system tray icon might
 * appear to tell the user that they have new mail, or have an incoming
 * instant message, or something along those lines. The basic idea is that
 * creating an icon in the notification area is less annoying than popping up
 * a dialog.
 * <p>
 * The icon can have a tooltip, and the user can interact with it by
 * activating it or popping up a context menu. Critical information should not
 * solely be displayed in a StatusIcon, since it may not be visible (e.g. when
 * the user doesn't have a notification area on his panel). This can be
 * checked with the StatusIcon.is_embedded() method.
 * <p>
 * Note that a StatusIcon is not a widget, but just a GLib
 * {@link org.gnome.glib.Object}. Making it a widget would be impractical,
 * since the system tray on Win32 doesn't allow the embedding of arbitrary
 * widgets.
 * 
 * @author Nat Pryce
 * @since 4.0.4
 */
public class StatusIcon extends org.gnome.glib.Object
{
    /**
     * Creates an empty status icon object.
     */
    public StatusIcon() {
        this(GtkStatusIcon.createStatusIcon());
    }

    protected StatusIcon(long pointer) {
        super(pointer);
    }

    /**
     * Makes this StatusIcon display <var>pixbuf</var>.
     * 
     * @param pixbuf
     *            the {@link Pixbuf} to display.
     */
    public void setFromPixbuf(Pixbuf pixbuf) {
        GtkStatusIcon.setFromPixbuf(this, pixbuf);
    }

    /**
     * Makes this StatusIcon display the icon in file <var>filename</var>.
     * 
     * @param filename
     *            the name of an icon file.
     */
    public void setFromFile(String filename) {
        GtkStatusIcon.setFromFile(this, filename);
    }

    /**
     * Makes this StatusIcon display a stock icon.
     * 
     * @param stockId
     *            a stock icon id
     */
    public void setFromStock(String stockId) {
        GtkStatusIcon.setFromStock(this, stockId);
    }

    /**
     * Makes this StatusIcon display a named icon from the current icon theme.
     * 
     * @param iconName
     *            an icon name
     */
    public void setFromIconName(String iconName) {
        GtkStatusIcon.setFromIconName(this, iconName);
    }

    /**
     * Gets the type of representation being used by the StatusIcon to store
     * image data. If the StatusIcon has no image data, the return value will
     * be {@link ImageType#EMPTY}.
     * 
     * @return the image representation being used.
     */
    public ImageType getStorageType() {
        return GtkStatusIcon.getStorageType(this);
    }

    /**
     * Returns the {@link Pixbuf} being displayed by the gtk.StatusIcon. The
     * storage type of the status icon must be {@link ImageType#EMPTY} or
     * {@link ImageType#PIXBUF}.
     * 
     * @return the {@link Pixbuf} being displayed by the StatusIcon or
     *         <code>null</code> if the StatusIcon is empty.
     * @see #getStorageType()
     */
    public Pixbuf getPixbuf() {
        return GtkStatusIcon.getPixbuf(this);
    }

    /**
     * Returns the id of the stock icon being displayed by the gtk.StatusIcon.
     * The storage type of the status icon must be {link
     * {@link ImageType#EMPTY} or {@link ImageType#STOCK}.
     * 
     * @return the stock id of the stock icon being displayed by the
     *         StatusIcon or <code>null</code> if the StatusIcon is empty.
     * @see #getStorageType()
     */
    public String getStock() {
        return GtkStatusIcon.getStock(this);
    }

    /**
     * Sets the tooltip of the status icon.
     * 
     * @param tooltipText
     *            the tooltip text, or <code>null</code> to remove the
     *            tooltip.
     */
    public void setTooltip(String tooltipText) {
        GtkStatusIcon.setTooltip(this, tooltipText);
    }

    /**
     * Reports if the status icon is visible. Note that being visible does not
     * guarantee that the user can actually see the icon, it must also be
     * embedded in the system tray.
     * 
     * @see #isEmbedded()
     * 
     * @return <code>true</code> if the StatusIcon is visible,
     *         <code>false</code> otherwise.
     */
    public boolean isVisible() {
        return GtkStatusIcon.getVisible(this);
    }

    /**
     * Shows or hides a status icon.
     * 
     * @param visible
     *            <code>true</code> to show the StatusIcon,
     *            <code>false</code> to hide it.
     */
    public void setVisible(boolean visible) {
        GtkStatusIcon.setVisible(this, visible);
    }

    /**
     * Reports if the the status icon is blinking.
     * 
     * @return <code>true</code> to start the StatusIcon blinking,
     *         <code>false</code> to stop it blinking.
     * 
     * @see #setBlinking
     */
    public boolean isBlinking() {
        return GtkStatusIcon.getBlinking(this);
    }

    /**
     * Makes the status icon start or stop blinking. Note that blinking user
     * interface elements may be problematic for some users, and thus may be
     * turned off, in which case this setting has no effect.
     * 
     * @param blinking
     *            <code>true</code> to start the StatusIcon blinking,
     *            <code>false</code> to stop it blinking.
     */
    public void setBlinking(boolean blinking) {
        GtkStatusIcon.setBlinking(this, blinking);
    }

    /**
     * Gets the size in pixels that is available for the image. Stock icons
     * adapt their size automatically if the size of the notification area
     * changes. For other storage types, the {@link StatusIcon.SIZE_CHANGED}
     * signal can be used to react to size changes.
     * 
     * @return the size that is available for the image
     */
    public int getSize() {
        return GtkStatusIcon.getSize(this);
    }

    /**
     * Reports if the StatusIcon is embedded in a notification area.
     * 
     * @return <code>true</code> if the status icon is embedded in a
     *         notification area, <code>false</code> otherwise.
     */
    public boolean isEmbedded() {
        return GtkStatusIcon.isEmbedded(this);
    }

    /**
     * The signal emitted when the user activates the StatusIcon. If and how
     * StatusIcons can be activated is platform-dependent.
     */
    public interface ACTIVATE extends GtkStatusIcon.ACTIVATE
    {
        /**
         * The signal emitted when the user activates the StatusIcon.
         * 
         * @param sourceObject
         *            the StatusIcon that was activated.
         */
        void onActivate(StatusIcon sourceObject);
    }

    public void connect(ACTIVATE handler) {
        GtkStatusIcon.connect(this, handler);
    }

    /**
     * The signal emitted when the user brings up the context menu of the
     * status icon. Whether status icons can have context menus and how these
     * are activated is platform-dependent.
     */
    public interface POPUP_MENU extends GtkStatusIcon.POPUP_MENU
    {
        /**
         * The signal emitted when the user brings up the context menu of the
         * status icon.
         * 
         * @param sourceObject
         *            the object which received the signal
         * @param button
         *            the button that was pressed, or 0 if the signal is not
         *            emitted in response to a button press event
         * @param activateTime
         *            the timestamp of the event that triggered the signal
         *            emission
         */
        void onPopupMenu(StatusIcon sourceObject, int button, int activateTime);
    }

    public void connect(POPUP_MENU handler) {
        GtkStatusIcon.connect(this, handler);
    }

    /**
     * Signal emitted when the size available for the image changes, e.g.
     * because the notification area got resized.
     */
    interface SIZE_CHANGED extends GtkStatusIcon.SIZE_CHANGED
    {
        /**
         * Signal emitted when the size available for the image changes.
         * 
         * @param sourceObject
         *            the object which received the signal
         * @param size
         *            the new size
         */
        boolean onSizeChanged(StatusIcon sourceObject, int size);
    }

    public void connect(SIZE_CHANGED handler) {
        GtkStatusIcon.connect(this, handler);
    }
}
