/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
 * Copyright © 1998-2004 The java-gnome Team
 * Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd and Others
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

/*
 * The concept for this mechanism to represent the combination of ordinal
 * column number and column type was inspired from several sources.
 * 
 * Ironically, we actually tried quite hard not to use this design because in
 * the old java-gnome 2.x bindings it led to very cumbersome code for
 * developers to have to write. However, as we carried out our own analysis of
 * a possible design that would solve the problem on not exposing raw ints as
 * column identifiers while simultaneously establishing type safety, we ended
 * up with what turns out to be effectively same solution internally but with
 * slightly more manageable public API.
 * 
 * The actual name "DataColumn" is taken directly from the TreeModel binding
 * used in java-gnome 2.x as originally written by Mark Howard. Reviewing
 * their work confirmed that we had hit on almost exactly the same approach
 * internally. Our code is simpler, of course.
 * 
 * Thanks to Murray Cummings for having discussed the original use of this
 * pattern in gtkmm and for having encouraged presenting column identifiers in
 * a type safe manner.
 */

/**
 * Specify the data type of a column in a TreeModel tabular store and identify
 * its position for later reference. When instantiating a {@link ListStore} or
 * {@link TreeStore} you use an array of DataColumns to indicate the data type
 * that each column of the model is to hold.
 * 
 * <p>
 * The types you can store in a TreeModel is somewhat deliberately constrained
 * to those that make sense when it comes time to present them. In almost
 * every case you will be presenting textual data and so will want a
 * DataColumnString to store it in. And while there are DataColumnInteger and
 * DataColumnLong, if will often find yourself doing formatting on that data
 * (currency, dates, etc) on the Java side before pushing it into the model
 * and so will end up using a DataColumnString anyway.
 * 
 * <p>
 * Keep in mind that you do <i>not</i> need to store all your application data
 * in the TreeModel. After all, you already have a perfectly sound (and
 * infinitely more powerful) means to model and represent your data: the Java
 * language you're working from in the first place. You only need to push data
 * into a TreeModel that will be directly displayed by a TreeView or that will
 * be used to help manage that display (such as using a numeric type as an
 * auxiliary sorting index for textual columns that otherwise wouldn't order
 * properly). Quite frequently, however, you will want to take a selection or
 * activation event from the TreeView and get back to the Java object that
 * where the displayed data came from in the first place. This is the role of
 * the other significant column type, {@link DataColumnReference}.
 * 
 * <h2>Initializing TreeModels</h2>
 * 
 * <p>
 * The optimal way to use this class is as follows. First declare variables to
 * represent each column (they can be local variables but often you end up
 * needing to access them from code outside the declaring method, so they
 * usually end up as private instance variables).
 * 
 * <pre>
 * ...
 * private final DataColumnString name;
 * private final DataColumnString dob; // date of birth
 * private final DataColumnLong dobSort;
 * private final DataColumnBoolean useful; // is this person valued, or a waste of oxygen?
 * </pre>
 * 
 * and then instantiate them;
 * 
 * <pre>
 *     ...
 *     name = new DataColumnString();
 *     dob = new DataColumnString();
 *     dobSort = new DataColumnLong();
 *     useful = new DataColumnBoolean();
 * </pre>
 * 
 * It is important to choose useful names for these variables; you'll be using
 * them to conveniently identify the columns in later code (some people even
 * go so far as to use all capitals to make them stand out, or use a suffix
 * like <code>Column</code>, though neither are really necessary). You then
 * make an array of them and use that for the ListStore constructor:
 * 
 * <pre>
 *     final DataColumn[] types;
 *     final ListStore model;
 * 
 *     types = new DataColumn[] {
 *         name,
 *         dob,
 *         dobSort,
 *         useful
 *     }
 *     model = new ListStore(types);
 * </pre>
 * 
 * You can rather nicely do this all at once:
 * 
 * <pre>
 *     ... 
 *     model = new ListStore(new DataColumn[] {
 *         name = new DataColumnString(),
 *         dob = new DataColumnString(),
 *         dobSort = new DataColumnInteger(),
 *         useful = new DataColumnBoolean()
 *     });
 * </pre>
 * 
 * which is ultimately the API we had in mind for you to use! (Important: do
 * <b>not</b> do the following:
 * 
 * <pre>
 *     ...
 *     types = new DataColumn[] {
 *         new DataColumnString(),
 *         new DataColumnString(),
 *         new DataColumnInteger(),
 *         new DataColumnBoolean()
 *     };
 *     new ListStore(types);
 * </pre>
 * 
 * as this means you can only refer to each column as <code>types[2]</code>
 * thus defeating the whole idea of having a meaningfully named handle to the
 * column that each instance of this class represents. You will <i>need</i>
 * those comprehensible names later).
 * 
 * <p>
 * See the list of subclasses of DataColumn to see the current set of
 * available column types.
 * 
 * @author Andrew Cowie
 * @author Mark Howard
 * @author Murray Cummings
 * @author Hanna Wallach
 * @since 4.0.5
 */
public abstract class DataColumn
{
    private final Class<?> type;

    private int ordinal = -1;

    protected DataColumn(Class<?> type) {
        assert (type != null);

        this.type = type;
    }

    Class<?> getType() {
        return type;
    }

    void setOrdinal(int column) {
        assert (column >= 0);

        if (ordinal != -1) {
            throw new IllegalArgumentException(
                    "You've tried to construct a TreeModel with a DataColumn array containing the same DataColumn twice. Each DataColumn must uniquely identify a column in the TreeModel you're constructing.");
        }

        this.ordinal = column;
    }

    int getOrdinal() {
        if (ordinal == -1) {
            throw new IllegalStateException(
                    "You have to add the DataColumn to target TreeModel before using it");
        }
        return ordinal;
    }
}
