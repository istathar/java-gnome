/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 2010 Operational Dynamics Consulting, Pty Ltd and Others
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
package org.freedesktop.icons;

/**
 * Named icons with "smiley faces". Not all of them are smiling
 * <code>:)</code>
 * 
 * @author Guillaume Mazoyer
 * @author Andrew Cowie
 * @since 4.0.17
 */
public class FaceIcon extends Icon
{
    protected FaceIcon(String name) {
        super(name);
    }

    public static final Icon FACE_ANGEL = new FaceIcon("face-angel");

    public static final Icon FACE_ANGRY = new FaceIcon("face-angry");

    public static final Icon FACE_COOL = new FaceIcon("face-cool");

    public static final Icon FACE_CRYING = new FaceIcon("face-crying");

    public static final Icon FACE_DEVILISH = new FaceIcon("face-devilish");

    public static final Icon FACE_EMBARRASSED = new FaceIcon("face-embarrassed");

    public static final Icon FACE_GLASSES = new FaceIcon("face-glasses");

    public static final Icon FACE_KISS = new FaceIcon("face-kiss");

    public static final Icon FACE_LAUGH = new FaceIcon("face-laugh");

    public static final Icon FACE_MONKEY = new FaceIcon("face-monkey");

    public static final Icon FACE_PLAIN = new FaceIcon("face-plain");

    public static final Icon FACE_RASPBERRY = new FaceIcon("face-raspberry");

    public static final Icon FACE_SAD = new FaceIcon("face-sad");

    public static final Icon FACE_SICK = new FaceIcon("face-sick");

    public static final Icon FACE_SMILE_BIG = new FaceIcon("face-smile-big");

    public static final Icon FACE_SMILE = new FaceIcon("face-smile");

    public static final Icon FACE_SMIRK = new FaceIcon("face-smirk");

    public static final Icon FACE_SURPRISE = new FaceIcon("face-surprise");

    public static final Icon FACE_TIRED = new FaceIcon("face-tired");

    public static final Icon FACE_UNCERTAIN = new FaceIcon("face-uncertain");

    public static final Icon FACE_WINK = new FaceIcon("face-wink");

    public static final Icon FACE_WORRIED = new FaceIcon("face-worried");
}
