/*
 * ComboBox.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd, and Others
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
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
 * <i>The underlying <code>GtkComboBox</code> is actually presents two
 * different APIs that are essentially mutually exclusive, which is why we
 * have split this into two public classes.</i>
 * 
 * @author Sebastian Mancke
 * @author Andrew Cowie
 * @since 4.0.6
 */
public class ComboBox extends Bin implements CellEditable, CellLayout
{
    protected ComboBox(long pointer) {
        super(pointer);
    }

    /**
     * Construct a new full-power TreeModel-backed ComboBox with a model to be
     * specified at a future point via {@link #setModel(TreeModel) setModel()}.
     * This constructor is explicitly here to permit developers to subclass
     * ComboBox in order to create their own ComboBox based custom Widgets.
     * 
     * @since 4.0.6
     */
    protected ComboBox() {
        super(GtkComboBox.createComboBox());
    }

    /**
     * Construct a new full-power TreeModel-backed ComboBox.
     * 
     * <p>
     * If subclassing ComboBox, use the <code>protected</code>
     * {@link #ComboBox() <init>()} no-arg constructor and then set the Model
     * later with {@link #setModel(TreeModel) setModel()}.
     */
    public ComboBox(TreeModel model) {
        super(GtkComboBox.createComboBoxWithModel(model));
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
     * a zero origin, so a return value of <code>2</code> means the third
     * item in the list is currently the active item.
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
     * Handler interface for the <code>changed</code> signal. This event
     * occurs whenever a different item gets selected by the user.
     */
    public interface CHANGED extends GtkComboBox.CHANGED
    {
        public void onChanged(ComboBox source);
    }

    /**
     * Hook up a <code>CHANGED</code> handler to the Widget.
     */
    public void connect(CHANGED handler) {
        GtkComboBox.connect(this, handler);
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
