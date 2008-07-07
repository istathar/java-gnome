/*
 * Assistant.java
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

import org.gnome.gdk.Pixbuf;

/**
 * Guide a user through a multi-step operation one page at a time. You may
 * recognize the name "wizard" or "druid"; GTK calls these Assistants.
 * 
 * <p>
 * An Assistant contains of multiple pages. While each page is just a Widget
 * of your choice, in most cases you will use a layout container as you would
 * in creating a regular top level Window. Each page in the Assistant is
 * marked as a specific type, set with
 * {@link #setPageType(Widget, AssistantPageType) setPageType()}. Be aware
 * that the last page of the Assistant must be of type
 * {@link AssistantPageType#CONFIRM CONFIRM} or
 * {@link AssistantPageType#SUMMARY SUMMARY}.
 * 
 * <p>
 * You can hook to a number of signals which indicate user actions specific to
 * the Assistant:
 * 
 * <ul>
 * <li>APPLY - The user activated the 'Apply' button on the page.</li>
 * <li>CANCEL - The user cancelled the Assistant.</li>
 * <li>CLOSE - The Assistant closes normally.</li>
 * <li>PREPARE - Another page is going to be displayed</li>
 * </ul>
 * 
 * <p>
 * Listening to the <code>PREPARE</code> signal allows you to modify the
 * page contents in time, perhaps depending on input made on previous pages.
 * 
 * <p>
 * You create the Assistant with a number of pages, the most of it possibly
 * being of type CONTENT. To allow a user to go from one page to the user, the
 * page must be flagged as <var>complete</var>. If not complete, the 'Next'
 * button is disabled. There is no suggested way where to apply such a check
 * for completeness. A possible way would listening to events emitted from the
 * Widgets inside the page when there contents change.
 * 
 * FIXME then lets suggest one.
 * 
 * @author Stefan Prelle
 * @author Andrew Cowie
 * @since 4.0.8
 */
public class Assistant extends Window
{
    protected Assistant(long pointer) {
        super(pointer);
    }

    /**
     * Creates a new instance of the Assistant.
     * 
     * @since 4.0.8
     */
    public Assistant() {
        super(GtkAssistant.createAssistant());
    }

    /**
     * Get the page number of the currently displayed page. Page numbers count
     * from <code>0</code>. If the assistant has no pages, <code>-1</code>
     * will be returned.
     * 
     * @since 4.0.8
     */
    public int getCurrentPage() {
        return GtkAssistant.getCurrentPage(this);
    }

    /**
	  * FIXME
	  *
     * @since 4.0.8
     */
    public void setCurrentPage(int pageNum) {
        GtkAssistant.setCurrentPage(this, pageNum);
    }

    /**
     * Get the number of pages in the Assistant.
     * 
     * @since 4.0.8
     */
    public int getNumPages() {
        return GtkAssistant.getNPages(this);
    }

    /**
     * Returns the child Widget contained in the given page.
     * 
     * @param pageNum
     *            The index of a page in the assistant. Use <code>-1</code>
     *            to get the last page.
     * @since 4.0.8
     */
    public Widget getPage(int pageNum) {
        final Widget result;

        result = GtkAssistant.getNthPage(this, pageNum);

        if (result == null) {
            throw new IndexOutOfBoundsException("Illegal page number requested");
        }
        return result;
    }

    /**
     * Prepend <code>page</code> to the existing pages in the Assistant.
     * 
     * @return the index (starting at 0) of the inserted page.
     * @since 4.0.8
     */
    public int prependPage(Widget page) {
        return GtkAssistant.prependPage(this, page);
    }

    /**
     * Appends a page to the pages in the Assistant.
     * 
     * @param page
     *            Page to add.
     * @return the index (starting at 0) of the inserted page
     * @since 4.0.8
     */
    public int appendPage(Widget page) {
        return GtkAssistant.appendPage(this, page);
    }

    /**
     * Places a page at a specific position between the pages already
     * existing in the Assistant.
     * 
     * @param page
     *            Page to add.
     * @param position
     *          The index (starting at 0) at which to insert the page, or -1 
     *          to append the page to the Assistant.
     * @return The index (starting at 0) of the inserted page
     * @since 4.0.8
     */
    public int insertPage(Widget page, int position) {
        return GtkAssistant.insertPage(this, page, position);
    }

    /**
     * Sets the {@link AssistantPageType page type} for page. The page type 
     * determines the page behavior in the Assistant.
     * 
     * @since 4.0.8
     */
    public void setPageType(Widget page, AssistantPageType type) {
        GtkAssistant.setPageType(this, page, type);
    }

    /**
     * Get the type of the given Assistant page.
     * 
     * @since 4.0.8
     */
    public AssistantPageType getPageType(Widget page) {
        return GtkAssistant.getPageType(this, page);
    }

    /**
     * Sets the title for page. The title is displayed in the header area of
     * the Assistant when page is the current page.
     * 
     * @param page
     *            A page of Assistant
     * @param type
     *            The new title of the page
     * @since 4.0.8
     */
    public void setPageTitle(Widget page, String title) {
        GtkAssistant.setPageTitle(this, page, title);
    }

    /**
     * FIXME
     * 
     * @since 4.0.8
     */
    public String getPageTitle(Widget page) {
        return GtkAssistant.getPageTitle(this, page);
    }

    /**
     * Sets a header image for page. This image is displayed in the header 
     * area of the Assistant when <code>page</code> is the current page.
     * 
     * @param page
     *            A page of assistant
     * @param image
     *            the new header image
     * @since 4.0.8
     */
    public void setPageHeaderImage(Widget page, Pixbuf image) {
        GtkAssistant.setPageHeaderImage(this, page, image);
    }

    /**
     * Gets the header image for page.
     *
     * @since 4.0.8
     */
    public Pixbuf getPageHeaderImage(Widget page) {
        return GtkAssistant.getPageHeaderImage(this, page);
    }

    /**
     * Sets a side image for page. This image is displayed in the side area of
     * the assistant when <code>page</code> is the current page.
     * 
     * @param page
     *            A page of assistant
     * @param image
     *            the new side image
     * @since 4.0.8
     */
    public void setPageSideImage(Widget page, Pixbuf image) {
        GtkAssistant.setPageSideImage(this, page, image);
    }

    /**
     * Gets the side image for page.
     * 
     * @since 4.0.8
     */
    public Pixbuf getPageSideImage(Widget page) {
        return GtkAssistant.getPageSideImage(this, page);
    }

    /**
     * Sets whether page contents are complete. This will make assistant
     * update the buttons state to be able to continue the task.
     * 
     * @since 4.0.8
     */
    public void setPageComplete(Widget page, boolean complete) {
        GtkAssistant.setPageComplete(this, page, complete);
    }

    /**
     * Gets whether page is complete.
     * 
     * @since 4.0.8
     */
    public boolean getPageComplete(Widget page) {
        return GtkAssistant.getPageComplete(this, page);
    }

    /**
     * Adds a widget to the action area of a Assistant.
     * 
     * @since 4.0.8
     */
    public void addActionWidget(Widget child) {
        GtkAssistant.addActionWidget(this, child);
    }

    /**
     * Removes a Widget that has been added with {@link #addActionWidget(Widget)}.
     * 
     * @since 4.0.8
     */
    public void removeActionWidget(Widget child) {
        GtkAssistant.removeActionWidget(this, child);
    }

    /**
     * Force the ... FIXME
     * 
     * @since 4.0.8
     */
    public void updateButtonsState() {
        GtkAssistant.updateButtonsState(this);
    }

    /**
     * This handler is called every time a page inside the assistant is
     * displayed. This includes the first page, as well as every page when
     * flipping forward and backward through the Assistants pages.
     * 
     * @since 4.0.8
     */
    public interface PREPARE extends GtkAssistant.PREPARE
    {
        public void onPrepare(Assistant source, Widget page);
    }

    /**
     * Attach the handler that is called when a new page is displayed.
     * 
     * @since 4.0.8
     */
    public void connect(PREPARE handler) {
        GtkAssistant.connect(this, handler);
    }

    /**
     * This handler is called when the user choices to confirm the input of
     * the Assistant at the confirmation page (AssistantPageType = CONFIRM)
     * 
     * @since 4.0.8
     */
    public interface APPLY extends GtkAssistant.APPLY
    {
        public void onApply(Assistant source);
    }

    /**
     * Attach the handler that is called when the user chooses to apply the
     * selections.
     * 
     * @since 4.0.8
     */
    public void connect(APPLY handler) {
        GtkAssistant.connect(this, handler);
    }

    /**
     * This handler is called at the end of the assistant after the APPLY
     * event when the handler ends normally.
     * 
     * @since 4.0.8
     */
    public interface CLOSE extends GtkAssistant.CLOSE
    {
        public void onClose(Assistant source);
    }

    /**
     * Attach the handler that is called when the Assistant is closed normally
     * at a CONFIRM or SUMMARY page.
     * 
     * @since 4.0.8
     */
    public void connect(CLOSE handler) {
        GtkAssistant.connect(this, handler);
    }

    /**
     * This handler is called when the user cancels the Assistant, no matter
     * at which page this happens.
     * 
     * @since 4.0.8
     */
    public interface CANCEL extends GtkAssistant.CANCEL
    {
        public void onCancel(Assistant source);
    }

    /**
     * Attach the handler that is called when the Assistant is cancelled.
     * 
     * @since 4.0.8
     */
    public void connect(CANCEL handler) {
        GtkAssistant.connect(this, handler);
    }

    /*
     * This is not part of the normal GTK+ bindings. It only performs a sanity
     * check whether you thought about having a CONFIRM or SUMMARY page at
     * your last page in the assistant.
     */
    void prepareForDisplay() {
        final Widget lastPage;
        final AssistantPageType type;

        lastPage = getPage(getNumPages() - 1);
        type = getPageType(lastPage);

        if ((type != AssistantPageType.CONFIRM) && (type != AssistantPageType.SUMMARY)) {
            throw new IllegalArgumentException("Last page must be of type CONFIRM or SUMMARY");
        }
    }
}
