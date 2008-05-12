/*
 * Assistant.java
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

import org.gnome.gdk.Pixbuf;

/**
 * A widget used to guide users through multi-step operations.
 * <p>
 * An Assistant contains multiple pages, where a page is a Widget of your
 * choice, but most likely a layout container. Each page in the Assistant
 * is marked as a specific type (see {@link AssistantPageType AssistantPageType}).
 * _ATTENTION_: GTK requires that the last page is of the type CONFIRM or
 * SUMMARY.
 * </p>
 * <p>
 * You can hook to events in the Assistant, which are:
 * </p><ul>
 * <li>APPLY - The user activated the 'Apply' button on the page.</li>
 * <li>CANCEL - The user cancelled the Assistant.</li>
 * <li>CLOSE - The Assistant closes normally.</li>
 * <li>PREPARE - Another page is going to be displayed</li>
 * </ul>
 * <p>
 * Listening to the PREPARE event allows you to modify the page contents in time,
 * perhaps depending on input made on previous pages. 
 * </p>
 * <p>
 * You create the Assistant with a number of pages, the most of it possibly
 * being of type CONTENT. To allow a user to go from one page to the user, the
 * page must be flagged as *complete*. If not complete, the 'next' button is
 * disabled. There is no suggested way where to apply such a check for 
 * completeness. A possible way would listening to events emitted from the
 * Widgets inside the page when there contents change.
 * </p>
 * @since 4.0.8
 * @author Stefan Prelle
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
     * Returns the index of the page currently being displayed.
     * 
     * @return The page index starting by 0, or -1 if there are no pages
     *         added to the Assistant yet.
     *         
     * @since 4.0.8
     */
    public int getCurrentPage() {
        return GtkAssistant.getCurrentPage(this);
    }

    /**
     * Select the new page to display. Usually you do not need to take care
     * of the page selection yourself, because it is done internally by the
     * Assistant. But in case you are working with custom buttons or other
     * Widgets added via {@link #addActionWidget(Widget) addActionWidget},
     * you may want to control the behaviour.
     * 
     * @param pageNum 
     *          Index of the page to switch to, starting from 0. If 
     *          negative, the last page will be used. If greater than the 
     *          number of pages in the Assistant, nothing will be done.
     * @since 4.0.8
     */
    public void setCurrentPage(int pageNum) {
        GtkAssistant.setCurrentPage(this, pageNum);
    }

    /**
     * Returns the number of pages in the Assistant
     * 
     * @since 4.0.8
     */
    public int getNPages() {
        return GtkAssistant.getNPages(this);
    }

    /**
     * Returns the child widget contained in the given page. The page
     * is referenced by the index number.
     * 
     * @param pageNum 
     *              The page index or -1 for the last page.
     * @return The child widget, or a NullPointer if <code>pageNum</code> 
     *          is out of bounds.
     * @since 4.0.8
     */
    public Widget getPage(int pageNum) {
        return GtkAssistant.getNthPage(this, pageNum);
    }

    /**
     * Adds a page at the first position in the Assistant.
     * 
     * @param page
     *          Widget for the page to add
     * @return The index (starting at 0) of the inserted page
     * @since 4.0.8
     */
    public int prependPage(Widget page) {
        return GtkAssistant.prependPage(this, page);
    }

    /**
     * Appends a page after the last page in the Assistant.
     * 
     * @param page
     *          Widget for the page to add
     * @return The index (starting at 0) of the inserted page
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
     *          Widget for the page to add
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
     * @param page  A page of assistant
     * @param type  The new type of the page
     * @since 4.0.8
     */
    public void setPageType(Widget page, AssistantPageType type) {
        GtkAssistant.setPageType(this, page, type);
    }

    /**
     * Returns the {@link AssistantPageType page type} of page.
     * 
     * @param page A page of assistant
     * @since 4.0.8
     */
    public AssistantPageType getPageType(Widget page) {
        return GtkAssistant.getPageType(this, page);
    }

    /**
     * Sets the title for page. The title is displayed in the header area 
     * of the Assistant when the page is selected..
     * 
     * @param page  A page of Assistant
     * @param type  The new title of the page
     * @since 4.0.8
     */
    public void setPageTitle(Widget page, String title) {
        GtkAssistant.setPageTitle(this, page, title);
    }

    /**
     * Returns the page type of page.
     * 
     * @param page A page of assistant
     * @since 4.0.8
     */
    public String getPageTitle(Widget page) {
        return GtkAssistant.getPageTitle(this, page);
    }

    /**
     * Sets a header image for page. This image is displayed in the header 
     * area of the Assistant when <code>page</code> is the current page.
     * 
     * @param page  A page of assistant
     * @param image the new header image
     * @since 4.0.8
     */
    public void setPageHeaderImage(Widget page, Pixbuf image) {
        GtkAssistant.setPageHeaderImage(this, page, image);
    }

    /**
     * Returns the header image for page.
     * 
     * @param page A page of assistant
     * @since 4.0.8
     */
    public Pixbuf getPageHeaderImage(Widget page) {
        return GtkAssistant.getPageHeaderImage(this, page);
    }

    /**
     * Sets a side image for page. This image is displayed in the side area 
     * of the assistant when <code>page</code> is the current page.
     * 
     * @param page  A page of assistant
     * @param image the new side image
     * @since 4.0.8
     */
    public void setPageSideImage(Widget page, Pixbuf image) {
        GtkAssistant.setPageSideImage(this, page, image);
    }

    /**
     * Gets the side image for page.
     * 
     * @param page A page of assistant
     * @since 4.0.8
     */
    public Pixbuf getPageSideImage(Widget page) {
        return GtkAssistant.getPageSideImage(this, page);
    }

    /**
     * Sets the completion status of a page. Only on pages which are
     * marked 'complete' the 'next' button is enabled. This allows you to
     * let the user commence to the next page only after he entered all
     * required input.
     * 
     * @param page 
     *          A page of assistant
     * @param complete 
     *          The completeness status of the page
     * @since 4.0.8
     */
    public void setPageComplete(Widget page, boolean complete) {
        GtkAssistant.setPageComplete(this, page, complete);
    }

    /**
     * Returns whether the <code>page</code> is complete.
     * 
     * @param page
     *          A page of assistant
     * @since 4.0.8
     */
    public boolean getPageComplete(Widget page) {
        return GtkAssistant.getPageComplete(this, page);
    }

    /**
     * Adds a widget to the action area of a Assistant. Using this method
     * you can put additional buttons in your Assistant which are visible
     * on all pages. You need to take care of enabling or disabling them
     * yourself.
     * 
     * @param child Widget to add
     * @since 4.0.8
     */
    public void addActionWidget(Widget child) {
        GtkAssistant.addActionWidget(this, child);
    }

    /**
     * Removes a Widget that has been added with {@link #addActionWidget(Widget)}.
     * 
     * @param child
     *          Widget to remove
     * @since 4.0.8
     */
    public void removeActionWidget(Widget child) {
        GtkAssistant.removeActionWidget(this, child);
    }

    /**
     * Forces assistant to recompute the buttons state.
     * <p>
     * GTK+ automatically takes care of this in most situations, e.g. when 
     * the user goes to a different page, or when the visibility or 
     * completeness of a page changes.
     * </p><p>
     * One situation where it can be necessary to call this function is when 
     * changing a value on the current page affects the future page flow of 
     * the Assistant.
     * </p>
     * @since 4.0.8
     */
    public void updateButtonsState() {
        GtkAssistant.updateButtonsState(this);
    }

    /**
     * This handler is called every time a page inside the assistant
     * is displayed. This includes the first page, as well as every
     * page when flipping forward and backward through the Assistants
     * pages.
     * 
     * @since 4.0.8
     */
    public interface PREPARE extends GtkAssistant.PREPARE {
        public void onPrepare(Assistant source, Widget page);
    }

    /**
     * Attach the handler that is called when a new page is displayed.
     * @since 4.0.8
     */
    public void connect(PREPARE handler) {
        GtkAssistant.connect(this, handler);
    }

    /**
     * This handler is called when the user choices to confirm the input of
     * the Assistant at the confirmation page (AssistantPageType = CONFIRM)
     * @since 4.0.8
     */
    public interface APPLY extends GtkAssistant.APPLY {
        public void onApply(Assistant source);
    }

    /**
     * Attach the handler that is called when the user chooses to apply
     * the selections.
     * @since 4.0.8
     */
    public void connect(APPLY handler) {
        GtkAssistant.connect(this, handler);
    }

    /**
     * This handler is called at the end of the assistant after the APPLY
     * event when the handler ends normally.
     * @since 4.0.8
     */
    public interface CLOSE extends GtkAssistant.CLOSE {
        public void onClose(Assistant source);
    }

    /**
     * Attach the handler that is called when the Assistant is closed normally
     * at a CONFIRM or SUMMARY page.
     * @since 4.0.8
     */
    public void connect(CLOSE handler) {
        GtkAssistant.connect(this, handler);
    }

    /**
     * This handler is called when the user cancels the Assistant, no matter
     * at which page this happens.
     * @since 4.0.8
     */
    public interface CANCEL extends GtkAssistant.CANCEL {
        public void onCancel(Assistant source);
    }

    /**
     * Attach the handler that is called when the Assistant is cancelled.
     * @since 4.0.8
     */
    public void connect(CANCEL handler) {
        GtkAssistant.connect(this, handler);
    }

    /**
     * This is not part of the normal GTK+ bindings. It only performs a 
     * sanity check whether you thought about having a CONFIRM or SUMMARY
     * page at your last page in the assistant.
     */
    void prepareForDisplay() {
        Widget lastPage = getPage(getNPages()-1);
        AssistantPageType type = getPageType(lastPage);
        if (type!=AssistantPageType.CONFIRM &&
                type!=AssistantPageType.SUMMARY)
            throw new IllegalArgumentException("Last page must be of type CONFIRM or SUMMARY");           
    }
    
    /**
     * Overloaded method for {@link Widget#show()} that performs a
     * sanity check on the pages before displaying the Assistant.
     */
    public void show() {
        prepareForDisplay();
        super.show();
    }
    
    /**
     * Overloaded method for {@link Widget#showAll()} that performs a
     * sanity check on the pages before displaying the Assistant.
     */
    public void showAll() {
        prepareForDisplay();
        super.showAll();
    }
}
