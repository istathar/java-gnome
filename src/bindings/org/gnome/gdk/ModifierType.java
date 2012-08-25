/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2007-2011 Operational Dynamics Consulting, Pty Ltd
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
package org.gnome.gdk;

import org.freedesktop.bindings.Flag;

/**
 * Constants representing what modifier keys are being held down on a
 * keystroke, if any. You get an object containing flags that are set via the
 * {@link EventKey#getState() getState()} method on the EventKey you receive
 * when hooking up a <code>Widget.KeyPressEvent</code> or
 * <code>Widget.KeyReleaseEvent</code>.
 * 
 * <p>
 * Try running this fragment if you're confused about the relationship between
 * Keyvals, ModifierTypes, and the keyboard events:
 * 
 * <pre>
 * foo.connect(new Widget.KeyPressEvent() {
 *     public boolean onKeyPressEvent(Widget source, EventKey event) {
 *         final Keyval key;
 *         final ModifierType mod;
 * 
 *         key = event.getKeyval();
 *         mod = event.getState();
 * 
 *         System.out.print(&quot;Pressed: &quot; + key.toString() + &quot;, &quot;);
 *         System.out.print(&quot;Modifier: &quot; + mod.toString() + &quot; &quot;);
 * 
 *         if (mod == ModifierType.SHIFT_MASK) {
 *             System.out.print(&quot;That's Shifty!&quot;);
 *         }
 *         if (mod.contains(ModifierType.ALT_MASK)) {
 *             System.out.print(&quot;Hooray for Alt!&quot;);
 *         }
 * 
 *         System.out.println();
 *         return false;
 *     }
 * });
 * </pre>
 * 
 * For each of the following keystrokes, you'll get a sequence of output
 * something like the following:
 * 
 * <dl>
 * <dt><b><code>A</code></b>
 * <dd>
 * 
 * <pre>
 * Pressed: Keyval.a, Modifier: ModifierType.NONE
 * </pre>
 * 
 * <dt><b><code>Shift+A</code></b>
 * <dd>
 * 
 * <pre>
 * Pressed: Keyval.ShiftRight, Modifier: ModifierType.NONE 
 * Pressed: Keyval.A, Modifier: ModifierType.SHIFT_MASK That's Shifty!
 * </pre>
 * 
 * <dt><b><code>Ctrl+A</code></b>
 * <dd>
 * 
 * <pre>
 * Pressed: Keyval.ControlRight, Modifier: ModifierType.NONE 
 * Pressed: Keyval.a, Modifier: ModifierType.CONTROL_MASK
 * </pre>
 * 
 * <dt><b><code>Ctrl+Shift+A</code></b>
 * <dd>
 * 
 * <pre>
 * Pressed: Keyval.ControlRight, Modifier: ModifierType.NONE  
 * Pressed: Keyval.ShiftRight, Modifier: ModifierType.CONTROL_MASK 
 * Pressed: Keyval.A, Modifier: ModifierType.SHIFT_MASK|CONTROL_MASK
 * </pre>
 * 
 * <dt><b><code>Alt+A</code></b>
 * <dd>
 * 
 * <pre>
 * Pressed: Keyval.AltRight, Modifier: ModifierType.NONE 
 * Pressed: Keyval.a, Modifier: ModifierType.ALT_MASK Hooray for Alt!
 * </pre>
 * 
 * <dt><b><code>Ctrl+Alt+A</code></b>
 * <dd>
 * 
 * <pre>
 * Pressed: Keyval.ControlLeft, Modifier: ModifierType.NONE 
 * Pressed: Keyval.AltLeft, Modifier: ModifierType.CONTROL_MASK 
 * Pressed: Keyval.a, Modifier: ModifierType.CONTROL_MASK|ALT_MASK Hooray for Alt!
 * </pre>
 * 
 * </dl>
 * 
 * <p>
 * The sequence of keystrokes for the modifying keys will depend on the order
 * the user strikes them, but you won't get them showing up as ModifierType
 * constants until a "normal" key is hit. Incidentally, this is where the
 * usefulness of Keyval's {@link Keyval#toUnicode() toUnicode()} come in: you
 * can filter key events until you get one with a non-<code>0</code>
 * translation.
 * 
 * <p>
 * <i>As with Keyval there are many other modifier constants that we haven't
 * bothered to expose. If you need one, feel free to subclass this and add
 * it.</i>
 * 
 * @author Andrew Cowie
 * @since 4.0.6
 */
public class ModifierType extends Flag
{
    protected ModifierType(int ordinal, String nickname) {
        super(ordinal, nickname);
    }

    /**
     * No modifiers were pressed.
     * 
     * @since 4.0.6
     */
    /*
     * For some reason this isn't explicitly specified in GDK. Well, it's just
     * 0 so you don't need much of a constant for that.
     */
    public static final ModifierType NONE = new ModifierType(0, "NONE");

    /**
     * The ModifierType associated with the <b><code>CapsLock</code></b> key.
     * A bit strange that this is also treated as a modifier.
     * 
     * @since 4.0.13
     */
    public static final ModifierType LOCK_MASK = new ModifierType(GdkModifierType.LOCK_MASK, "LOCK_MASK");

    /**
     * The ModifierType associated with the <b><code>NumLock</code></b> key. A
     * bit strange that this is also treated as a modifier.
     * 
     * <p>
     * <i>Even worse is that this appears to actually be state
     * <code>0x10</code>, which is <code>GDK_MOD2_MASK</code>!</i>
     * 
     * @since 4.0.18
     */
    public static final ModifierType NUM_MASK = new ModifierType(GdkModifierType.MOD2_MASK, "NUM_MASK");

    /**
     * The <b><code>Shift</code></b> key modifier.
     * 
     * @since 4.0.6
     */
    public static final ModifierType SHIFT_MASK = new ModifierType(GdkModifierType.SHIFT_MASK,
            "SHIFT_MASK");

    /**
     * The <b><code>Control</code></b> key modifier.
     * 
     * @since 4.0.6
     */
    public static final ModifierType CONTROL_MASK = new ModifierType(GdkModifierType.CONTROL_MASK,
            "CONTROL_MASK");

    /**
     * The <b><code>Alt</code></b> key modifier.
     * 
     * <p>
     * <i>The legacy code in the X server as wrapped by GDK has this as
     * <code>MOD1_MASK</code>; you should probably be aware that it is
     * possible that</i> <b><code>Alt</code></b> <i>could be mapped to a
     * different modifier, There are, however, a number of hard coded
     * references tying Mod1 to the Alt key in various places, notably
     * gnome-control-center's <code>gnome-keybinding-properties</code>, so
     * calling this <code>ALT_MASK</code> seems safe enough.</i>
     * 
     * @since 4.0.6
     */
    public static final ModifierType ALT_MASK = new ModifierType(GdkModifierType.MOD1_MASK, "ALT_MASK");

    public static final ModifierType SUPER_MASK = new ModifierType(GdkModifierType.SUPER_MASK,
            "SUPER_MASK");

    public static final ModifierType HYPER_MASK = new ModifierType(GdkModifierType.HYPER_MASK,
            "HYPER_MASK");

    /**
     * The <b><code>Window</code></b> modifier key. You will probably also get
     * <code>SUPER_MASK</code> with this one.
     * 
     * <p>
     * <i>Unless your user has changed things of their X server is doing
     * something weird, it is likely that <code>MOD4_MASK</code> is mapped to
     * the "key with the Microsoft Windows logo" that is present on modern
     * PC104 keyboards. Damn monopolists. Anyway, that's what people call it,
     * so that's that's what we've named our constant.</i>
     * 
     * @since 4.0.6
     */
    public static final ModifierType WINDOW_MASK = new ModifierType(GdkModifierType.MOD4_MASK,
            "WINDOW_MASK");

    /*
     * These names correspond to the ones we exposed in MouseButton.
     */

    /**
     * The left mouse button was held while the key was pressed. GNOME user
     * interface conventions don't have us using mouse buttons as modifiers,
     * so you won't need this in normal usage.
     * 
     * <p>
     * If hooking up a handler for <code>Widget.ButtonPressEvent</code> you
     * will instead be using the {@link MouseButton#LEFT LEFT} MouseButton
     * constant.
     * 
     * 
     * @since 4.0.14
     */
    public static final ModifierType BUTTON_LEFT_MASK = new ModifierType(GdkModifierType.BUTTON1_MASK,
            "BUTTON_LEFT_MASK");

    /**
     * The middle mouse button was held while the key was pressed.
     * 
     * @since 4.0.14
     */
    public static final ModifierType BUTTON_MIDDLE_MASK = new ModifierType(GdkModifierType.BUTTON2_MASK,
            "BUTTON_MIDDLE_MASK");

    /**
     * The right mouse button was held while the key was pressed.
     * 
     * @since 4.0.14
     */
    public static final ModifierType BUTTON_RIGHT_MASK = new ModifierType(GdkModifierType.BUTTON3_MASK,
            "BUTTON_RIGHT_MASK");

    /**
     * Combine two ModifierType instances.
     */
    public static ModifierType or(ModifierType one, ModifierType two) {
        return (ModifierType) Flag.orTwoFlags(one, two);
    }

    /**
     * Find the union of two ModifierType instances.
     * 
     * @since 4.0.18
     */
    public static ModifierType and(ModifierType one, ModifierType two) {
        return (ModifierType) Flag.andTwoFlags(one, two);
    }

    /**
     * Remove the second Flag's fields from the first. This is especially
     * useful for screening out <b><code>NumLock</code></b>
     * 
     * @since 4.0.18
     */
    public static ModifierType mask(ModifierType one, ModifierType two) {
        return (ModifierType) Flag.maskTwoFlags(one, two);
    }
}
