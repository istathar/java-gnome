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
 * A Widget used to choose from a list of items. <img src="ComboBox.png"
 * class="snapshot" />
 * 
 * <p>
 * Internally, ComboBox uses a TreeModel to store the items, giving you the
 * same MVC power of GTK's TreeView/TreeModel system. There is also an
 * alternative API which can be used to create and manipulate ComboBoxes which
 * are comprised only of text. If that is your requirement, see
 * {@link TextComboBox}.
 * 
 * <p>
 * ComboBox is a CellLayout, that is, it possesses many of the same aspects as
 * a TreeViewColumn, and, along with being backed by a TreeModel, is used in
 * much the same way as a TreeView: create a ListStore or TreeStore then build
 * the GUI side by creating CellRenderers against the ComboBox.
 * 
 * <p>
 * An abbreviated example of using this follows; assuming a ListStore
 * <code>model</code> with at least DataColumnString <code>column</code> in
 * it,
 * 
 * <pre>
 * combo = new ComboBox(model);
 * renderer = new CellRendererText(combo);
 * renderer.setText(column);
 * </pre>
 * 
 * and that's it. While conceptually straight forward, it turns out to be a
 * lot of work if all you're doing is a single column of DataColumnString. But
 * the ability to <i>also</i> have a DataColumnReference in your model means
 * you can get a link back the object that is being represented by the "mere"
 * text label being displayed to the user, and this can be very powerful.
 * Likewise, you can build ComboBoxes with more complicated layouts, for
 * example having with several verticals of text and perhaps an image packed
 * into the ComboBox as well. Finally, don't forget that you can use several
 * different views against one TreeModel, so a TreeModel that is in use
 * somewhere else in your app can also be the source data for your ComboBox.
 * 
 * <p>
 * <i>The underlying <code>GtkComboBox</code> is actually presents two
 * different APIs that are essentially mutually exclusive, which is why we
 * have split this into two public classes.</i>
 * 
 * @author Sebastian Mancke
 * @author Andrew Cowie
 * @since 4.0.3
 */
public class ComboBox extends Bin implements CellEditable, CellLayout
{
    protected ComboBox(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new full-power TreeModel-backed ComboBox with a model to be
     * specified at a future point via the {@link #setModel(TreeModel)
     * setModel()} method. This constructor is explicitly here to permit
     * developers to subclass ComboBox in order to create their own ComboBox
     * based custom Widgets.
     * 
     * @since 4.0.6
     */
    protected ComboBox() {
        super(GtkComboBox.createComboBox());
    }

    /**
     * Construct a new ComboBox with an embedded Entry. This allows the user
     * to add values not already in the backing model.
     * 
     * <p>
     * <i>In java-gnome 4.0 we had a ComboBoxEntry class, corresponding to a
     * GTK 2.x widget. This widget was removed in GTK 3.0, however, and
     * replaced with a construct-only property</i> <var>has-entry</var>. <i>We
     * present this mode here.</i>
     * 
     * @since 4.1.1
     */
    /*
     * The source code in gtk/gtkcombobox.c indicates that the child widget
     * must be a GtkEntry and that when it is added is when the appropriate
     * signals are connected.
     */
    protected ComboBox(Entry entry) {
        super(GtkComboBox.createComboBoxWithEntry());
        GtkContainer.add(this, entry);
    }

    /**
     * Construct a new full-power TreeModel-backed ComboBox.
     * 
     * <p>
     * If subclassing ComboBox, use the <code>protected</code>
     * {@link #ComboBox() <init>()} no-arg constructor and then set the Model
     * later with {@link #setModel(TreeModel) setModel()}.
     * 
     * @since 4.0.3
     */
    public ComboBox(TreeModel model) {
        super(GtkComboBox.createComboBoxWithModel(model));
    }

    /**
     * Construct a new full-power TreeModel-backed ComboBox which also has a
     * supporting Entry.
     * 
     * @since 4.1.1
     */
    public ComboBox(TreeModel model, Entry entry) {
        super(GtkComboBox.createComboBoxWithModelAndEntry(model));
        GtkContainer.add(this, entry);
    }

    /**
     * Set or change the TreeModel from which this ComboBox draws its data.
     * 
     * @since 4.0.6
     */
    public void setModel(TreeModel model) {
        GtkComboBox.setModel(this, model);
    }

    /**
     * Returns the index of the active item in the ComboBox. This counts from
     * a zero origin, so a return value of <code>2</code> means the third item
     * in the list is currently the active item.
     * 
     * <p>
     * This will return <code>-1</code> if there is no active item.
     * 
     * @since 4.0.6
     */
    public int getActive() {
        return GtkComboBox.getActive(this);
    }

    /**
     * Change the active item within this ComboBox to be the one at the
     * supplied index. Items are numbered from <code>0</code>.
     * 
     * @since 4.0.6
     */
    public void setActive(int active) {
        GtkComboBox.setActive(this, active);
    }

    /**
     * This signal emitted whenever a different item is selected by the user.
     * Use {@link ComboBox#getActive() getActive()} or
     * {@link ComboBox#getActiveIter() getActiveIter()} to determine which
     * item was picked.
     * 
     * @since 4.0.3
     */
    public interface Changed extends GtkComboBox.ChangedSignal
    {
        public void onChanged(ComboBox source);
    }

    /**
     * Hook up a <code>ComboBox.Changed</code> handler to the Widget.
     * 
     * since 4.0.3
     */
    public void connect(ComboBox.Changed handler) {
        GtkComboBox.connect(this, handler, false);
    }

    /**
     * Cause the popup part of the ComboBox to raise and present itself. You
     * don't tend to need this (after all it's the user who clicks on the
     * ComboBox to cause the popup to present). The ComboBox must already have
     * been realized to the screen before you will be able to use this.
     * 
     * @since 4.0.6
     */
    public void popup() {
        GtkComboBox.popup(this);
    }

    /**
     * Programmatically tell the ComboBox popup to go away.
     * 
     * @since 4.0.14
     */
    public void popdown() {
        GtkComboBox.popdown(this);
    }

    TreeModel getModel() {
        return GtkComboBox.getModel(this);
    }

    /**
     * Get a TreeIter pointing at the currently selected row. If no row is
     * currently active then <code>null</code> will be returned.
     * 
     * @since 4.0.6
     */
    public TreeIter getActiveIter() {
        final TreeIter active;

        active = new TreeIter(this.getModel());

        if (GtkComboBox.getActiveIter(this, active)) {
            return active;
        } else {
            return null;
        }
    }

    /**
     * Set the ComboBox to be pointing at the row nominated by the TreeIter
     * argument.
     * 
     * @since 4.0.6
     */
    public void setActiveIter(TreeIter row) {
        GtkComboBox.setActiveIter(this, row);
    }
}
