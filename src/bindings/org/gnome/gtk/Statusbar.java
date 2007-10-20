/*
 * Statusbar.java
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

/**
 * A bar, usually placed at the bottom of the application Window, used to
 * display little information messages about the status of the application.
 * 
 * <p>
 * A Statusbar should only be used to display non-critical information, such
 * as general information (for example, the size of the document openned),
 * status information about the task currently executed, progress of a
 * background operation... You must be aware that information shown in the
 * status bar may be never seen by the user.
 * 
 * <p>
 * If you want to use an Statusbar in your application, it should be placed at
 * the bottom of the main Window. A good idea is to add a {@link VBox VBox} to
 * your application Window, and then place the Statusbar in the last slot of
 * the VBox, with the <code>expand</code> property set to <code>false</code>.
 * Don't use Statusbars in Dialogs or other secondary Windows.
 * 
 * <p>
 * The Statusbar mantains a stack of messages. You can
 * {@link #push(int, String) push()} and {@link #pop(int) pop()} the messages
 * on the stack. Only the message at the top of the stack is actually shown to
 * the user. If you have several sources for status messages, you can use a
 * different {@link #getContextId(String) context} id for each source. Note
 * that even if you use different contexts, the Statusbar has a single stack
 * and only one message will be shown at the same time. If you have no
 * information to shown, it is better to keep the stack empty than adding a
 * "Ready" or similar message.
 * 
 * <p>
 * As the Statusbar is a {@link Box Box}, you can also use the methods
 * inherit from it to add other Widgets. Note that in most cases a Statusbar
 * is not used as a place for interactive controls. If you want to add this
 * kind of Widgets, a good idea is to provide inlaid appearance for Widgets
 * that respond to user events, and keep a flat appearance to informative
 * Widgets.
 * 
 * @author Vreixo Formoso
 * @since 4.0.5
 */
public class Statusbar extends HBox
{
    protected Statusbar(long pointer) {
        super(pointer);
    }

    /**
     * Create a new Statusbar.
     */
    public Statusbar() {
        super(GtkStatusbar.createStatusbar());
    }

    /**
     * Get a context id that identifies a source for status messages.
     * 
     * @param contextDescription
     *            A description of the context for the messages. It is not
     *            shown to the user.
     */
    public int getContextId(String contextDescription) {
        return GtkStatusbar.getContextId(this, contextDescription);
    }

    /**
     * Add a new message to the top of the stack. This message will be shown
     * in the Statusbar until you push another message, or you remove it.
     * 
     * @param contextId
     *            The context the message belongs to. You must call
     *            {@link #getContextId(String) getContextId()} to get a valid
     *            id.
     * @param text
     *            The text of the message.
     * @return An identifier for the message, that you must store if you
     *         pretend to use the {@link #remove(int, int) remove()} method.
     */
    public int push(int contextId, String text) {
        return GtkStatusbar.push(this, contextId, text);
    }

    /**
     * Remove the first message in the stack with the given context id.
     * 
     * <p>
     * Note that this may not change the displayed message, if the message at
     * the top of the stack has a different context id.
     */
    public void pop(int contextId) {
        GtkStatusbar.pop(this, contextId);
    }

    /**
     * Remove a message from the stack.
     * 
     * @param contextId
     *            The context identifier.
     * @param messageId
     *            The identifier of the message, returned by
     *            {@link #push(int, String) push()}.
     */
    public void remove(int contextId, int messageId) {
        GtkStatusbar.remove(this, contextId, messageId);
    }
}
