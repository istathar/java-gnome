/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd and Others
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
 * A <code>ToggleAction</code> is an equivalent of a {@link Action} but used
 * in general to create {@link CheckMenuItem}. It has an &quot;active&quot;
 * state which indicates whether the action has been checked or not.
 * 
 * @author Guillaume Mazoyer
 * @since 4.0.15
 */
public class ToggleAction extends Action
{
    protected ToggleAction(long pointer) {
        super(pointer);
    }

    /**
     * Create a new ToggleAction, and connect a handler to its
     * <code>ToggleAction.Toggled</code> signal.
     * 
     * @param name
     *            A unique name for the ToggleAction.
     * @param label
     *            The text that will be displayed in the proxy Widgets. You
     *            usually will want to localize it to the user language.
     * @param tooltip
     *            A Tooltip or little help message for the ToggleAction. Also
     *            localized.
     * @param stock
     *            The stock icon to display in proxy Widgets.
     * @param handler
     *            A handler to connect to the
     *            <code>ToggleAction.Toggled</code> signal. Typically this
     *            will be used to actually start the operation related to this
     *            ToggleAction.
     * @since 4.0.15
     */
    /*
     * FIXME describe the implications of different choices for name.
     */
    public ToggleAction(String name, String label, String tooltip, Stock stock,
            ToggleAction.Toggled handler) {
        super(GtkToggleAction.createToggleAction(name, label, tooltip, stock.getStockId()));
        connect(handler);
    }

    /**
     * Create a new ToggleAction.
     * 
     * @param name
     *            A unique name for the ToggleAction.
     * @param label
     *            The text that will be displayed in the proxy Widgets. You
     *            usually will want to localize it to the user language.
     * @param tooltip
     *            A Tooltip or little help message for the ToggleAction. Also
     *            localized.
     * @param stock
     *            The Stock icon to display in proxy Widgets.
     * @since 4.0.15
     */
    public ToggleAction(String name, String label, String tooltip, Stock stock) {
        super(GtkToggleAction.createToggleAction(name, label, tooltip, stock.getStockId()));
    }

    /**
     * Create a new ToggleAction from a Stock item. The message and tooltip
     * will be supplied by GTK automatically.
     * 
     * @since 4.0.15
     */
    public ToggleAction(String name, Stock stock) {
        super(GtkToggleAction.createToggleAction(name, null, null, stock.getStockId()));
    }

    /**
     * Create a new ToggleAction based on a Stock item, and connect a handler
     * to its <code>ToggleAction.Toggled</code> signal. Complements the
     * {@link #ToggleAction(String, Stock) &lt;init&gt;(String, Stock)}
     * constructor.
     * 
     * @since 4.0.15
     */
    public ToggleAction(String name, Stock stock, ToggleAction.Toggled handler) {
        super(GtkToggleAction.createToggleAction(name, null, null, stock.getStockId()));
        connect(handler);
    }

    /**
     * Create a new ToggleAction, and connect a handler to its
     * <code>ToggleAction.Toggled</code> signal.
     * 
     * @param name
     *            A unique name for the ToggleAction.
     * @param label
     *            The label that will be displayed in the proxy Widgets. You
     *            usually will want to localize it to the user language.
     * @param handler
     *            A handler to connect to the
     *            <code>ToggleAction.Toggled</code> signal. Usually will will
     *            start from here the operation related to the Action.
     * @since 4.0.15
     */
    public ToggleAction(String name, String label, ToggleAction.Toggled handler) {
        super(GtkToggleAction.createToggleAction(name, label, null, null));
        connect(handler);
    }

    /**
     * Create a new ToggleAction.
     * 
     * @param name
     *            A unique name for the ToggleAction.
     * @param label
     *            The text that will be displayed in the proxy Widgets. You
     *            usually will want to localize it to the user language.
     * @since 4.0.15
     */
    public ToggleAction(String name, String label) {
        super(GtkToggleAction.createToggleAction(name, label, null, null));
    }

    /**
     * Emit the <code>ToggleAction.Toggled</code> signal.
     * 
     * @since 4.0.19
     */
    public void emitToggled() {
        GtkToggleAction.toggled(this);
    }

    /**
     * Set the checked state of the <code>ToggleAction</code>.
     * 
     * @since 4.0.15
     */
    public void setActive(boolean setting) {
        GtkToggleAction.setActive(this, setting);
    }

    /**
     * Return the checked state of the <code>ToggleAction</code>.
     * 
     * @since 4.0.15
     */
    public boolean getActive() {
        return GtkToggleAction.getActive(this);
    }

    /**
     * Set whether the action may have proxies like a {@link RadioAction}.
     * 
     * @since 4.0.15
     */
    public void setDrawAsRadio(boolean setting) {
        GtkToggleAction.setDrawAsRadio(this, setting);
    }

    /**
     * Return whether the action may have proxies like a {@link RadioAction}.
     * 
     * @since 4.0.15
     */
    public boolean getDrawAsRadio() {
        return GtkToggleAction.getDrawAsRadio(this);
    }

    /**
     * Signal emitted when the <code>ToggleAction</code> is toggled
     * 
     * <p>
     * A <code>ToggleAction</code> is activated when the user clicks a
     * ToolButton proxy, when (s)he activates an associated MenuItem or when
     * {@link ToggleAction#toggled() toggled()} is called.
     * 
     * @author Guillaume Mazoyer
     * @since 4.0.15
     */
    public interface Toggled extends GtkToggleAction.ToggledSignal
    {
        public void onToggled(ToggleAction source);
    }

    /**
     * Connect a handler to the <code>ToggleAction.Toggled</code> signal.
     * 
     * @since 4.0.15
     */
    public void connect(ToggleAction.Toggled handler) {
        GtkToggleAction.connect(this, handler, false);
    }
}
