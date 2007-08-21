/*
 * Action.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2007 Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

import org.gnome.glib.Object;

/**
 * Actions represent an operation that the user can perform from one or
 * several GUI places.
 * 
 * <p>
 * Usually, an application provide several ways to let users execute an
 * operation, such as Menus and Toolbars. If an operation can be executed from
 * several GUI places, it seems logical that such different places appear
 * similar to the user. Namely, they should have the same operation name, or
 * the same icon. This way, if the user uses to execute an operation from the
 * Toolbar, when it sees the equivalent MenuItem, it can know that both will
 * do the same thing.
 * 
 * <p>
 * Thus, it seems reasonable that both MenuItem and ToolButton share some
 * Object where the common information is stored. This is the role of the
 * Action class.
 * 
 * <p>
 * An Action holds information about an operation that could be executed from
 * different places. This information include the label that is shown to the
 * user, the icon, and a tooltip or help message. It also stores state
 * information, such as whether the action is "visible" or "sensitive". When
 * this information changes, all the Action <i>proxies</i>, i.e. the Widgets
 * such as MenuItems or ToolButtons related to it, are also changed properly.
 * 
 * <p>
 * Additionally, an Action has an {@link ACTIVATE} signal, emitted when any of
 * its proxies is activated. Thus, you can just connect to the Action ACTIVATE
 * signal to start the needed operation, instead of having to connect to the
 * ACTIVATE signal of each of the proxies.
 * 
 * <p>
 * Once you have created an Action, you can get proxies for it with the
 * {@link #createMenuItem()} and {@link #createToolItem()} methods.
 * 
 * <p>
 * Finally, note that you can also use Actions even if you only plan to let
 * user execute an operation from a single GUI place.
 * 
 * @see ActionGroup
 * 
 * @author Vreixo Formoso
 * @since 4.0.4
 */
public class Action extends Object
{
    protected Action(long pointer) {
        super(pointer);
    }

    /**
     * Create a new Action, and connect a handler to its ACTIVATE signal.
     * 
     * @param name
     *            A unique name for the Action.
     * @param label
     *            The label that will be displayed in the proxy Widgets. You
     *            usually will want to localize it to the user language.
     * @param tooltip
     *            A tooltip or little help message for the Action. Also
     *            localized.
     * @param stockId
     *            The Stock icon to display in proxy widgets.
     * @param handler
     *            A handler to connect to the ACTIVATE signal. Usually will
     *            will start from here the operation related to the Action.
     */
    public Action(String name, String label, String tooltip, Stock stockId, ACTIVATE handler) {
        super(GtkAction.createAction(name, label, tooltip, stockId.getStockId()));
        connect(handler);
    }

    /**
     * Create a new Action.
     * 
     * @param name
     *            A unique name for the Action.
     * @param label
     *            The label that will be displayed in the proxy Widgets. You
     *            usually will want to localize it to the user language.
     * @param tooltip
     *            A tooltip or little help message for the Action. Also
     *            localized.
     * @param stockId
     *            The Stock icon to display in proxy widgets.
     */
    public Action(String name, String label, String tooltip, Stock stockId) {
        super(GtkAction.createAction(name, label, tooltip, stockId.getStockId()));
    }

    /**
     * Create a new Action.
     * 
     * @param name
     *            A unique name for the Action.
     * @param label
     *            The label that will be displayed in the proxy Widgets. You
     *            usually will want to localize it to the user language.
     * @param tooltip
     *            A tooltip or little help message for the Action. Also
     *            localized.
     */
    public Action(String name, String label, String tooltip) {
        super(GtkAction.createAction(name, label, tooltip, null));
    }

    /**
     * Create a new Action, and connect a handler to its ACTIVATE signal.
     * 
     * @param name
     *            A unique name for the Action.
     * @param label
     *            The label that will be displayed in the proxy Widgets. You
     *            usually will want to localize it to the user language.
     * @param handler
     *            A handler to connect to the ACTIVATE signal. Usually will
     *            will start from here the operation related to the Action.
     */
    public Action(String name, String label, ACTIVATE handler) {
        super(GtkAction.createAction(name, label, null, null));
        connect(handler);
    }

    /**
     * Create a new Action.
     * 
     * @param name
     *            A unique name for the Action.
     * @param label
     *            The label that will be displayed in the proxy Widgets. You
     *            usually will want to localize it to the user language.
     */
    public Action(String name, String label) {
        super(GtkAction.createAction(name, label, null, null));
    }

    /**
     * Create a MenuItem from this Action.
     * 
     * <p>
     * You can add the returned MenuItem to a {@link Menu}. The MenuItem will
     * have the same Label, icon or accelerator of this Action, and when the
     * user ACTIVATE's it, this Action will be ACTIVATED too.
     * 
     * @see Menu
     */
    public MenuItem createMenuItem() {
        return (MenuItem) GtkAction.createMenuItem(this);
    }

    /**
     * Creates a ToolItem from this Action.
     * 
     * <p>
     * You can add the returned ToolItem to a {@link Toolbar}. The ToolItem
     * will have the same Label, icon, tooltips or accelerator of this Action,
     * and when the user clicks it, this Action will be ACTIVATED.
     */
    public ToolItem createToolItem() {
        return (ToolItem) GtkAction.createToolItem(this);
    }

    /**
     * Set if the Action is enabled to be activated by the user. When an
     * Action is not sensitive, all its ToolItem or MenuItem proxies are
     * disabled, meaning the user can't activate them.
     * 
     * <p>
     * You usually will want to deactivate an Action when its operation has no
     * sense in current application status. For example, in a text editor you
     * should disable the "Save" Action when no document is opened, or maybe
     * when current document hasn't changed.
     * 
     * <p>
     * When not sensitive, Action proxies are displayed differently than
     * enabled proxies (usually in grey color). That way, user knows the
     * Action has no meaning in the current application status, and (s)he
     * needs to do some action before the disabled Action becomes sensitive.
     * 
     * <p>
     * However, when a full Menu on a MenuBar needs to be disable, generally a
     * better idea is to {@link #setVisible(boolean) setVisible(false)} it.
     * 
     * <p>
     * Finally, note that setting an Action sensitive doesn't always mean it
     * will be actually sensitive, as you also need to make sensitive the
     * ActionGroup the Action belongs to (see {@link #isSensitive()}).
     */
    public void setSensitive(boolean sensitive) {
        GtkAction.setSensitive(this, sensitive);
    }

    /**
     * Return whether the Action itself is enabled to be activated by the
     * user. Note that this doesn't necessarily mean effective sensitivity.
     * 
     * @see #isSensitive()
     * @see #setSensitive(boolean)
     */
    public boolean getSensitive() {
        return GtkAction.getSensitive(this);
    }

    /**
     * Returns whether the action is effectively sensitive, i.e., if both the
     * Action and the ActionGroup it belongs to are enabled.
     * 
     * @see #setSensitive(boolean)
     * @see #getSensitive()
     */
    public boolean isSensitive() {
        return GtkAction.isSensitive(this);
    }

    /**
     * Set whether the Action is visible to users.
     * 
     * <p>
     * When an Action is not visible, any associated proxies, such as
     * MenuItems or ToolItems are hidden to the used.
     * 
     * <p>
     * In most cases, it's a better idea to
     * {@link #setSensitive(boolean) setSensitive(false)} an Action instead of
     * make it not visible. That way, users can see that such operation exists
     * in the application, but they need to do some operations before it
     * becomes available.
     * 
     * <p>
     * However, when a full Menu is disabled, this could be a good option. For
     * example, on a text edit application, you could hide the "Edit" menu
     * when there's no opened document.
     * 
     * <p>
     * Finally, note that setting an Action visible doesn't always mean it
     * will be actually displayed, as you also need to make visible the
     * ActionGroup the Action belongs to (see {@link #isVisible()}).
     */
    public void setVisible(boolean visible) {
        GtkAction.setVisible(this, visible);
    }

    /**
     * Return whether the Action itself is visible. Note that this doesn't
     * necessarily mean effective visibility.
     * 
     * @see #isVisible()
     * @see #setVisible(boolean)
     */
    public boolean getVisible() {
        return GtkAction.getVisible(this);
    }

    /**
     * Is the Action visible on the screen?
     * 
     * @return <code>true</code> when both the Action and the ActionGroup it
     *         belongs to are visible, <code>false</code> otherwise.
     * @see #setVisible(boolean)
     */
    public boolean isVisible() {
        return GtkAction.isVisible(this);
    }

    /**
     * Activates the Action if it's sensitive.
     * 
     * <p>
     * Note that Action is automatically activate when user activates one of
     * its proxies, maybe selecting the specific MenuItem or clicking the
     * ToolButton, so in most cases <b>you don't need this.</b> However, in
     * some cases you want to activate the Action in your application code.
     * Use this there.
     */
    public void activate() {
        GtkAction.activate(this);
    }

    /**
     * Signal emitted when the Action is activated.
     * 
     * <p>
     * An Action is activated when the user clicks a ToolButton proxy, when
     * (s)he activates an associated MenuItem or when
     * {@link Action#activate() activate()} is called.
     */
    public interface ACTIVATE extends GtkAction.ACTIVATE
    {
        public void onActivate(Action sourceObject);
    }

    /**
     * Connect a handler to the ACTIVATE signal.
     */
    public void connect(ACTIVATE handler) {
        GtkAction.connect(this, handler);
    }

}
