/*
 * TreeModel.java
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

/*
 * FIXME this is a placeholder stub for what will become the public API for
 * this type. Replace this comment with appropriate javadoc including author
 * and since tags. Note that the class may need to be made abstract, implement
 * interfaces, or even have its parent changed. No API stability guarantees
 * are made about this class until it has been reviewed by a hacker and this
 * comment has been replaced.
 */
public interface TreeModel
{
    public String getValue(TreeIter row, DataColumnString column);
    
    /**
     * 
     * @param iter
     * @param column
     * @return
     */
    public java.lang.Object getValue(TreeIter row, DataColumnReference column);
    
    

    /**
     * Initialize a new iterator at the beginning of the model. Since you
     * presumably want to iterate through the remaining rows, use
     * {@link TreeIter#iterNext() iterNext()} as in the following:
     * 
     * <pre>
     * TreeIter row;
     * 
     * row = model.getIterFirst();
     * while (row != null) {
     *     // do something with row
     *     row = model.iterNext();
     * }
     * </pre>
     * 
     * FIXME This is not very Java friendly.
     * 
     * @return <code>null</code> if the model is empty.
     */
    public TreeIter getIterFirst();
}
