/*
 * java-gnome, a UI library for writing GTK and GNOME programs from Java!
 *
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

/**
 * A tabbed notebook container. These are common sights in web browsers and
 * text editors and are the recommended way to create user interfaces that
 * must manage multiple documents if creating a completely independent window
 * per document (the preferred GNOME approach) is inappropriate. <img
 * src="Notebook.png" class="snapshot">
 * 
 * <p>
 * Note that Notebooks are a poor way to organize pages of application
 * preferences; if your program has that many options there is probably
 * something very wrong with your design in the first place. Do you
 * <i>really</i> need to present that many different configuration settings to
 * the user?
 * 
 * @author Sebastian Mancke
 * @author Andrew Cowie
 * @author Stefan Prelle
 * @author Stefan Schweizer
 * @since 4.0.3
 */
/*
 * The various add page functions return -1 on failure. This should be
 * intercepted and a RuntimeException thrown.
 */
public class Notebook extends Container
{
    protected Notebook(long pointer) {
        super(pointer);
    }

    /**
     * Constructs a new Notebook.
     * 
     * @since 4.0.3
     */
    public Notebook() {
        super(GtkNotebook.createNotebook());
    }

    /**
     * Append a new tab to the Notebook after the current tab.
     * 
     * @param child
     *            The Widget to be shown on the new Notebook page.
     * @param tabLabel
     *            The Label Widget you want to use for the tab itself.
     * @since 4.0.3
     */
    public int appendPage(Widget child, Widget tabLabel) {
        return GtkNotebook.appendPage(this, child, tabLabel);
    }

    /**
     * Insert a tab to the Notebook before the current tab.
     * 
     * @param child
     *            The Widget shown on the new Notebook page.
     * @param tabLabel
     *            The Label Widget for the tab
     * @return the position of the prepended tab.
     * @since 4.0.3
     */
    public int prependPage(Widget child, Widget tabLabel) {
        return GtkNotebook.prependPage(this, child, tabLabel);
    }

    /**
     * Insert a tab at the supplied position in the Notebook.
     * 
     * @param child
     *            The Widget shown on the new Notebook page.
     * @param tabLabel
     *            The Label Widget to use on the tab itself.
     * @param position
     *            The position at which to insert the page.
     * @return the position in the Notebook of the inserted tab.
     * @since 4.0.3
     */
    public int insertPage(Widget child, Widget tabLabel, int position) {
        return GtkNotebook.insertPage(this, child, tabLabel, position);
    }

    /**
     * Remove a tab.
     * 
     * @param pageNum
     *            The position number (from 0) of the page to remove.
     * @since 4.0.3
     */
    public void removePage(int pageNum) {
        GtkNotebook.removePage(this, pageNum);
    }

    /**
     * Activate/show the page at the supplied position.
     * 
     * @param pageNum
     *            Position of the page to activate
     * @since 4.0.3
     */
    public void setCurrentPage(int pageNum) {
        GtkNotebook.setCurrentPage(this, pageNum);
    }

    /**
     * Returns the number of the active page
     * 
     * @since 4.0.9
     */
    public int getCurrentPage() {
        return GtkNotebook.getCurrentPage(this);
    }

    /**
     * The handler interface for notification of changes in the current page.
     * 
     * <p>
     * Note that this handler is only used when the page is changed with the
     * keyboard using Ctrl+Page Up/Down. If you want to get informed every
     * time the page is changed, you should use the
     * <code>Notebook.SwitchPage</code> signal instead.
     * 
     * @since 4.0.3
     */
    public interface ChangeCurrentPage extends GtkNotebook.ChangeCurrentPageSignal
    {
        /**
         * @param offset
         *            The number of pages to move forward/backward (negative
         *            value for backward)
         */
        public boolean onChangeCurrentPage(Notebook source, int offset);
    }

    /**
     * Connects a <code>Notebook.ChangeCurrentPage</code> handler to the
     * Notebook.
     * 
     * @since 4.0.3
     */
    public void connect(Notebook.ChangeCurrentPage handler) {
        GtkNotebook.connect(this, handler, false);
    }

    /**
     * The signal emitted when the user or the program switches to a new page.
     * 
     * <p>
     * Use this in preference to <code>Notebook.ChangeCurrentPage</code>.
     * 
     * @author Stefan Schweizer
     * @since 4.0.10
     */
    public interface SwitchPage
    {
        /**
         * Callback received when the page showing in the Notebook changes.
         * Values of the page number parameter start from 0.
         */
        public void onSwitchPage(Notebook source, int pageNum);
    }

    /**
     * Connects a <code>Notebook.SwitchPage</code> handler to this Notebook.
     * 
     * @since 4.0.10
     */
    public void connect(Notebook.SwitchPage handler) {
        GtkNotebook.connect(this, new SwitchPageHandler(handler), false);
    }

    /*
     * Eliminate page parameter from handler interface.
     */
    private static class SwitchPageHandler implements GtkNotebook.SwitchPageSignal
    {
        private final SwitchPage handler;

        private SwitchPageHandler(SwitchPage handler) {
            this.handler = handler;
        }

        public void onSwitchPage(Notebook source, long page, int pageNum) {
            handler.onSwitchPage(source, pageNum);
        }
    }

    /**
     * Specify where the Notebook tabs will be located.
     * {@link PositionType#TOP TOP} is the default.
     * 
     * @since 4.0.6
     */
    public void setTabPosition(PositionType position) {
        GtkNotebook.setTabPos(this, position);
    }

    /**
     * Returns the number of pages in the Notebook.
     * 
     * @since 4.0.9
     */
    public int getPageCount() {
        return GtkNotebook.getNPages(this);
    }

    /**
     * Returns the page number of the given Widget.
     * 
     * @return The page number or <code>-1</code> if <code>child</code> is not
     *         in the Notebook.
     * 
     * @since 4.0.9
     */
    public int getPageNumber(Widget child) {
        return GtkNotebook.pageNum(this, child);
    }

    /**
     * Get the Widget which is the page at a given index. Page numbers start
     * at <code>0</code>.
     * 
     * @since 4.0.9
     */
    public Widget getPage(int pageNum) {
        return GtkNotebook.getNthPage(this, pageNum);
    }

    /**
     * Should the tabs of the Notebook actually be there?
     * 
     * <p>
     * The default is <code>true</code>, as you'd expect.
     * 
     * <p>
     * Turning this off might be useful if you're trying to create the effect
     * of toggling user interface from one set of controls to another in the
     * same space on screen. That might seem nifty, but keep in mind that
     * you're going to have to come up with a convincingly clear UI for your
     * users to be able to change modes, else they won't have any clue that
     * there <i>are</i> any other states to change to.
     * 
     * @since 4.0.10
     */
    public void setShowTabs(boolean setting) {
        GtkNotebook.setShowTabs(this, setting);
    }

    /**
     * Do you want a border drawn around the Notebook?
     * 
     * <p>
     * The border decoration drawn around the Notebook pages only optional if
     * you've turned tabs off with {@link #setShowTabs(boolean) setShowTabs()}.
     * 
     * @since 4.0.10
     */
    public void setShowBorder(boolean setting) {
        GtkNotebook.setShowBorder(this, setting);
    }

    /**
     * Sets whether all the tabs will be visible, thus increasing the size the
     * Notebook takes, or instead only show as many tabs as the current size
     * of the Notebook allows. In this later case, an pair of arrows for
     * scrolling among all tabs will be show in the tab label area.
     * 
     * <p>
     * It is desirable to call this with a <code>true</code> value if the
     * Notebook has many pages. Otherwise, the tabs will artificially increase
     * the size of the Notebook, sometimes even leading to a Window that
     * cannot fit in the screen space.
     * 
     * @since 4.0.12
     */
    public void setScrollable(boolean setting) {
        GtkNotebook.setScrollable(this, setting);
    }
}
