/*
 * RadioButton.java
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2008      Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * A special kind of toggle button used to select from a mutually exclusive
 * set of options.
 * 
 * <p>
 * A RadioButton is somewhat similar to a CheckButton, but it is shown as an
 * empty circle (rather than an empty square) and the selected Button in the
 * group us shown with a dot inside (rather than a checkmark).
 * 
 * <p>
 * However, while a CheckButton can be used alone to choose between two
 * states, a RadioButton is only useful when used together with other
 * RadioButtons. Those related RadioButtons are named as a RadioButton group.
 * Only a single Button in a group can the active at any one time. Note that
 * you don't need to write any line of code to force it, GTK takes care of
 * that for you. That's the reason they are useful to choose between a
 * mutually exclusive set of options.
 * 
 * <p>
 * To create a group of RadioButtons, you first create one of them, and then
 * you pass it to the constructor of the others, as follows:
 * 
 * <pre>
 * RadioButton opt1, opt2, opt3;
 * 
 * opt1 = new RadioButton(&quot;Option _1&quot;);
 * 
 * // and now you submit opt1 to the constructor
 * opt2 = new RadioButton(opt1, &quot;Option _2&quot;);
 * opt3 = new RadioButton(opt1, &quot;Option _3&quot;);
 * </pre>
 * 
 * You can get the active option at any time with the
 * {@link #getActiveButton() getActiveButton()} method. While you can still
 * connect a handler to the ToggleButton's
 * {@link org.gnome.gtk.ToggleButton.TOGGLED TOGGLED} signal, the
 * {@link GROUP_TOGGLED GROUP_TOGGLED} signal is usually easier to use.
 * 
 * <p>
 * You should place related RadioButtons together, better if disposed
 * vertically, as this makes them easier to scan visually. An {@link VBox} is
 * the better alternative to dispose a group of RadioButtons. It is also a
 * good idea to place a descriptive Label at the top of the RadioButtons.
 * Usually a {@link Frame} is a good alternative to fit both requirements, as
 * it places RadioButtons altogether, with a Label at the top. Note, however,
 * that the Frame outline is usually not needed (you can use a blank margin
 * instead):
 * 
 * <pre>
 * Frame option;
 * VBox buttons;
 * 
 * // Create a Frame with a descriptive Label and without outline...
 * option = new Frame(&quot;Option:&quot;);
 * option.setBorderWidth(3);
 * option.setShadowType(ShadowType.NONE);
 * 
 * // ... and with a VBox to dispose the RadioButtons
 * buttons = new VBox(false, 2);
 * option.add(buttons);
 * 
 * buttons.add(opt1);
 * buttons.add(opt2);
 * buttons.add(opt3);
 * </pre>
 * 
 * <p>
 * In your applications you will usually use RadioButtons you have two or more
 * mutually exclusive values for an option. Note that if such values are
 * equivalent to the concept of enable/disable a given option, both
 * {@link CheckButton} or {@link ToggleButton} are a better alternative. In
 * the same way, if you have too many possible values, you should consider
 * using a {@link ComboBox} instead, or even think about the possibility of
 * simplifying your user interface.
 * 
 * @author Vreixo Formoso
 * @since 4.0.7
 */
/*
 * TODO One of the problems with this binding is that in Gtk+ the RadioButton
 * group is represented as a GSList, that we currently map as a java array.
 * However, we cannot change we size of a java array dynamically, so the
 * methods that take the groups GSList lead to an ugly behavior, as after
 * adding a new RadioButton the arrays is not modified. To avoid this, I have
 * chosen to not expose the RadioButton group directly, and only the
 * constructors where another member of the group (i.e. another RadioButton)
 * is used to set group membership. As an exception I have exposed getGroup(),
 * that returns an arrays with the RadioButtons that were in the group at the
 * moment of the call. Alternatively we could choose to map the GSList as a
 * java.util.Collection, or wrap it in a custom RadioButtonGroup, for example.
 * Chosen alternative, however, is easy and good enough.
 */
public class RadioButton extends CheckButton
{
    protected RadioButton(long pointer) {
        super(pointer);
    }

    /**
     * Create a new RadioButton with the given label. It will be placed in its
     * own group, you submit it later to the constructors of other Buttons
     * that should be in the same group.
     * 
     * @param label
     *            The label that will be placed near the RadioButton. If the
     *            text contains an underscore (<code>_<code>) it will be taken 
     *            to be the mnemonic for the Widget.
     * @since 4.0.7
     */
    public RadioButton(String label) {
        super(GtkRadioButton.createRadioButtonWithMnemonic(null, label));
    }

    /**
     * Create a new RadioButton with the given label and that will belong to
     * the given group.
     * 
     * @param group
     *            The newly created RadioButton will be placed in the same
     *            group this RadioButton belong to.
     * @param label
     *            The label that will be placed near the RadioButton. If the
     *            text contains an underscore (<code>_<code>) it will be taken 
     *            to be the mnemonic for the Widget.
     *            @since 4.0.7
     */
    public RadioButton(RadioButton group, String label) {
        super(GtkRadioButton.createRadioButtonWithMnemonicFromWidget(group, label));
    }

    /**
     * Get the RadioButtons that belong to the same group than this. Note that
     * this RadioButton is also included.
     * 
     * @since 4.0.7
     */
    /*
     * This is a bit different of what Gtk+ does, as the original function
     * returns a GSList that can change, but...
     */
    public RadioButton[] getGroup() {
        return GtkRadioButton.getGroup(this);
    }

    /**
     * 
     * @return
     */
    /*
     * This is java-gnome specific, it does not exist in Gtk+, but I think it
     * is really useful.
     */
    public RadioButton getActiveButton() {
        RadioButton[] group = getGroup();
        for (int i = 0; i < group.length; ++i) {
            if (group[i].getActive()) {
                return group[i];
            }
        }
        return null;
    }

    /**
     * Signal that is emitted each time the active RadioButton is a group is
     * changed.
     * 
     * <p>
     * This happens either by clicking in a different option on the group, by
     * pressing the <code>Up</code> or <code>Down</code> key when one of
     * the RadioButtons in the group has the focus, or when the key
     * combination <code>Alt+mnemonic</code> is pressed. It can also be
     * triggered programmaticaly, by calling the
     * {@link ToggleButton#setActive(boolean) setActive()} method.
     * 
     * @author Vreixo Formoso
     */
    /*
     * This is java-gnome specific, it does not exist in Gtk+, but I think it
     * is really useful.
     */
    public interface GROUP_TOGGLED
    {
        /**
         * Called when user changes the active RadioButton in a group
         * 
         * @param source
         *            The RadioButton that is now active.
         */
        public void onGroupToggled(RadioButton source);
    }

    /**
     * Hook up a handler to the GROUP_TOGGLED signal.
     * 
     * @since 4.0.7
     */
    public void connect(GROUP_TOGGLED handler) {
        ToggleHandler toggleHandler = new ToggleHandler(handler);
        RadioButton[] group = getGroup();
        for (int i = 0; i < group.length; ++i) {
            group[i].connect(toggleHandler);
        }
    }

    /*
     * Custom handler needed to implement GROUP_TOGGLED custom signal.
     */
    private static final class ToggleHandler implements ToggleButton.TOGGLED
    {
        private final GROUP_TOGGLED handler;

        public ToggleHandler(GROUP_TOGGLED handler) {
            this.handler = handler;
        }

        public void onToggled(ToggleButton source) {
            if (source.getActive()) {
                handler.onGroupToggled((RadioButton) source);
            }
        }
    }
}
