/*
 * ToolButton.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * A ToolButton is a ToolItem that displays a little graphical Button.
 * 
 * <p>
 * This is the item you will want to add in most cases to your application
 * Toolbar(s). It is a acts a like a normal Button, but its appearance is
 * designed to look better on a Toolbar.
 * 
 * <p>
 * A ToolButton has an image or icon and a Label. The user Toolbar preferences
 * determine whether the Label is actually shown on screen. Even if you uses
 * to disable the Label in your desktop preferences, you should provide a
 * valid Label to your application ToolButtons, as other users may want to
 * show them.
 * 
 * @author Vreixo Formoso
 * @since 4.0.4
 */
public class ToolButton extends ToolItem
{
    protected ToolButton(long pointer) {
        super(pointer);
    }

    /**
     * Creates a new ToolButton using the given icon and Label.
     * 
     * @param iconWidget
     *            The Widget to be used as the icon for the ToolButton.
     *            Usually you will want to use a Widget display an image, such
     *            as {@link Image}.
     * @param label
     *            The Label for the ToolButton.
     */
    public ToolButton(Widget iconWidget, String label) {
        super(GtkToolButton.createToolButton(iconWidget, label));
    }

    /**
     * Creates a new ToolButton from the specific stock item. Both the Label
     * and icon will be set properly from the stock item. By using a system
     * stock item, the newly created ToolButton with use the same Label and
     * Image as other GNOME applications. To ensure consistent look-n-feel
     * between applications, it is highly recommend that you use provided
     * stock item whenever possible.
     * 
     * @param stockId
     *            The StockId that will determine the Label and icon of the
     *            ToolButton.
     */
    public ToolButton(StockId stockId) {
        super(GtkToolButton.createToolButtonFromStock(stockId.getStockId()));
    }

    /**
     * Set the Label of the Button.
     * 
     * <p>
     * Note that any value of the Label set by this method won't be
     * actually displayed if you have set a Widget Label using
     * {@link #setLabelWidget(Widget) setLabelWidget()}
     * 
     * @param label
     *            The String to be used as Label, or <code>null</code>.
     */
    /*
     * FIXME null labels not supported
     */
    public void setLabel(String label) {
        GtkToolButton.setLabel(this, label);
    }

    /**
     * Get the text Label previously set with
     * {@link #setLabel(String) setLabel()}.
     * 
     * @return The Label or <code>null</code> if not Label has been set.
     *         Note that a <code>null</code> return value doesn't mean that
     *         the ToolButton doesn't have a Label, as it can have a Widget
     *         Label or a Label taken from a stock item.
     */
    public String getLabel() {
        return GtkToolButton.getLabel(this);
    }

    /**
     * Set the Widget to be used as a Label.
     * 
     * <p>
     * Please check {@link #setLabel(String) setLabel()} before using this,
     * because in most cases that is what you want.
     * 
     * <p>
     * Usually ToolButtons have a text Label, set with
     * <code>setLabel()</code>, or maybe a Label automatically determined
     * by a stock item. However, in some cases you will want to provide
     * another kind of Widget as a Label.
     * 
     * @param labelWidget
     *            A Widget to be used as a Label, or <code>null</code> to
     *            not use the Widget Label (in this case, the text Label will
     *            be used. If the text Label is also <code>null</code>, the
     *            default stock item label is used. In this later case, if
     *            this ToolButton has no stock item, then no Label will be
     *            using). Note that if you pass a not-null here, this will be
     *            used as a Label despite of the normal text Label or the
     *            stock item label.
     */
    public void setLabelWidget(Widget labelWidget) {
        GtkToolButton.setLabelWidget(this, labelWidget);
    }

    /**
     * Get the Widget used as Label, if any.
     * 
     * @return The Widget used as Label, or <code>null</code> if this
     *         ToolButton doesn't have a Widget as Label.
     * 
     * @see #setLabelWidget(Widget)
     */
    public Widget getLabelWidget() {
        return GtkToolButton.getLabelWidget(this);
    }

    /**
     * Event generated when a user presses and releases a button, causing it
     * to activate.
     */
    public interface CLICKED extends GtkToolButton.CLICKED
    {
        public void onClicked(ToolButton sourceObject);
    }

    /**
     * Connect a handler to the <code>CLICKED</code> signal.
     */
    public void connect(CLICKED handler) {
        GtkToolButton.connect(this, handler);
    }
}
