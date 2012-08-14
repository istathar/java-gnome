/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2010 Operational Dynamics Consulting, Pty Ltd
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
package org.gnome.gtk;

/**
 * A <code>InfoBar</code> is a widget that can be used to show messages to a
 * user without showing a dialog. You can add buttons and widgets like in a
 * {@link Dialog}. <img src="InfoBar.png" class="snapshot">
 * 
 * <p>
 * The style of the <code>InfoBar</code> can be changed using the
 * {@link MessageType type} of the message to show to the user. It is possible
 * to control the sensitivity of the action widgets by using the
 * {@link #setResponseSensitive(ResponseType, boolean) setResponseSensitive()}
 * method.
 * 
 * @author Guillaume Mazoyer
 * @since 4.0.16
 */
public class InfoBar extends HBox
{
    protected InfoBar(long pointer) {
        super(pointer);
    }

    /**
     * Create a new <code>InfoBar</code>.
     * 
     * @since 4.0.16
     */
    public InfoBar() {
        this(GtkInfoBar.createInfoBar());
    }

    /**
     * Add a Widget to the <var>content area</var> of the InfoBar.
     * 
     * @since 4.0.16
     */
    public void add(Widget widget) {
        final Container container;

        container = (Container) GtkInfoBar.getContentArea(this);
        container.add(widget);
    }

    /**
     * Add an activatable widget to the action area of this InfoBar.
     * 
     * @since 4.0.16
     */
    public void addActionWidget(Widget child, ResponseType response) {
        GtkInfoBar.addActionWidget(this, child, response.getResponseId());
    }

    /**
     * Add a Button whose icon and label are taken from a given Stock. It is,
     * as ever, recommended to use a Stock Button for common actions. See
     * {@link #addButton(String, ResponseType) addButton()}.
     * 
     * @since 4.0.16
     */
    public Button addButton(Stock stock, ResponseType response) {
        return (Button) GtkInfoBar.addButton(this, stock.getStockId(), response.getResponseId());
    }

    /**
     * Adds an action {@link Button} with the given text as its label to the
     * end of the InfoBar's <var>action area</var>. The given ResponseType
     * will be returned back in the InfoBar's {@link InfoBar.Response
     * Response} signal when the Button added as a result of this call is
     * clicked.
     * 
     * @since 4.0.16
     */
    public Button addButton(String text, ResponseType response) {
        return (Button) GtkInfoBar.addButton(this, text, response.getResponseId());
    }

    /**
     * Set the sensitivity of the widget associated with the given
     * <code>response</code>.
     * 
     * @since 4.0.16
     */
    public void setResponseSensitive(ResponseType response, boolean setting) {
        GtkInfoBar.setResponseSensitive(this, response.getResponseId(), setting);
    }

    /**
     * Set the widget with the given <var>response</var> as the default widget
     * of this InfoBar. Pressing &quot;Enter&quot; activates the default
     * widget.
     * 
     * <p>
     * Be careful! The InfoBar must be in a widget hierarchy to use this
     * method.
     * 
     * @since 4.0.16
     */
    public void setDefaultResponse(ResponseType response) {
        GtkInfoBar.setDefaultResponse(this, response.getResponseId());
    }

    /**
     * Cause a <var>InfoBar.Response</var> signal with the specified
     * ResponseType to be emitted by this InfoBar.
     * 
     * @since 4.0.16
     */
    public void response(ResponseType response) {
        GtkInfoBar.response(this, response.getResponseId());
    }

    /**
     * Set the type of the message. It is used to define what color to use
     * when drawing the message area.
     * 
     * @since 4.0.16
     */
    public void setMessageType(MessageType type) {
        GtkInfoBar.setMessageType(this, type);
    }

    /**
     * This signal arises when a user uses a keybinding to dismiss the
     * InfoBar.
     * 
     * <p>
     * The default binding for this signal is the &quot;Escape&quot; key.
     * 
     * @author Guillaume Mazoyer
     * @since 4.0.16
     */
    public interface Close extends GtkInfoBar.CloseSignal
    {
        public void onClose(InfoBar source);
    }

    /**
     * Hook up a <code>InfoBar.Close</code> handler.
     * 
     * @since 4.0.16
     */
    public void connect(InfoBar.Close handler) {
        GtkInfoBar.connect(this, handler, false);
    }

    /**
     * This signal arises when a user activates one of the Widgets laid out in
     * the <var>action area</var> of the InfoBar.
     * 
     * @author Guillaume Mazoyer
     * @since 4.0.16
     */
    public interface Response
    {
        void onResponse(InfoBar source, ResponseType response);
    }

    /**
     * Hook up a <code>InfoBar.Response</code> handler.
     * 
     * @since 4.0.16
     */
    public void connect(InfoBar.Response handler) {
        GtkInfoBar.connect(this, new ResponseHandler(handler), false);
    }

    /*
     * Needed to emit the InfoBar.Response signal with a type-safe
     * ResponseType instead of the underlying int ordinal.
     */
    private static class ResponseHandler implements GtkInfoBar.ResponseSignal
    {
        private InfoBar.Response handler;

        private ResponseHandler(InfoBar.Response handler) {
            this.handler = handler;
        }

        public void onResponse(InfoBar source, int responseId) {
            final ResponseType response;

            response = ResponseType.constantFor(responseId);

            handler.onResponse(source, response);
        }
    }
}
