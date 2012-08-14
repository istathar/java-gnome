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
 * Named icons representing actions.
 * 
 * @author Guillaume Mazoyer
 * @author Andrew Cowie
 * @since 4.0.17
 */
public class ActionIcon extends Icon
{
    protected ActionIcon(String name) {
        super(name);
    }

    public static final Icon ADDRESS_BOOK_NEW = new ActionIcon("address-book-new");

    public static final Icon APPLICATION_EXIT = new ActionIcon("application-exit");

    public static final Icon APPOINTMENT_NEW = new ActionIcon("appointment-new");

    public static final Icon BOOKMARK_NEW = new ActionIcon("bookmark-new");

    public static final Icon CALL_START = new ActionIcon("call-start");

    public static final Icon CALL_STOP = new ActionIcon("call-stop");

    public static final Icon CONTACT_NEW = new ActionIcon("contact-new");

    public static final Icon DOCUMENT_NEW = new ActionIcon("document-new");

    public static final Icon DOCUMENT_OPEN = new ActionIcon("document-open");

    public static final Icon DOCUMENT_OPEN_RECENT = new ActionIcon("document-open-recent");

    public static final Icon DOCUMENT_PAGE_SETUP = new ActionIcon("document-page-setup");

    public static final Icon DOCUMENT_PRINT = new ActionIcon("document-print");

    public static final Icon DOCUMENT_PRINT_PREVIEW = new ActionIcon("document-print-preview");

    public static final Icon DOCUMENT_PROPERTIES = new ActionIcon("document-properties");

    public static final Icon DOCUMENT_REVERT = new ActionIcon("document-revert");

    public static final Icon DOCUMENT_SAVE_AS = new ActionIcon("document-save-as");

    public static final Icon DOCUMENT_SAVE = new ActionIcon("document-save");

    public static final Icon DOCUMENT_SEND = new ActionIcon("document-send");

    public static final Icon EDIT_CLEAR = new ActionIcon("edit-clear");

    public static final Icon EDIT_COPY = new ActionIcon("edit-copy");

    public static final Icon EDIT_CUT = new ActionIcon("edit-cut");

    public static final Icon EDIT_DELETE = new ActionIcon("edit-delete");

    public static final Icon EDIT_FIND = new ActionIcon("edit-find");

    public static final Icon EDIT_FIND_REPLACE = new ActionIcon("edit-find-replace");

    public static final Icon EDIT_PASTE = new ActionIcon("edit-paste");

    public static final Icon EDIT_REDO = new ActionIcon("edit-redo");

    public static final Icon EDIT_SELECT_ALL = new ActionIcon("edit-select-all");

    public static final Icon EDIT_UNDO = new ActionIcon("edit-undo");

    public static final Icon FOLDER_NEW = new ActionIcon("folder-new");

    public static final Icon FORMAT_INDENT_LESS = new ActionIcon("format-indent-less");

    public static final Icon FORMAT_INDENT_MORE = new ActionIcon("format-indent-more");

    public static final Icon FORMAT_JUSTIFY_CENTER = new ActionIcon("format-justify-center");

    public static final Icon FORMAT_JUSTIFY_FILL = new ActionIcon("format-justify-fill");

    public static final Icon FORMAT_JUSTIFY_LEFT = new ActionIcon("format-justify-left");

    public static final Icon FORMAT_JUSTIFY_RIGHT = new ActionIcon("format-justify-right");

    public static final Icon FORMAT_TEXT_BOLD = new ActionIcon("format-text-bold");

    public static final Icon FORMAT_TEXT_DIRECTION_LTR = new ActionIcon("format-text-direction-ltr");

    public static final Icon FORMAT_TEXT_DIRECTION_RTL = new ActionIcon("format-text-direction-rtl");

    public static final Icon FORMAT_TEXT_ITALIC = new ActionIcon("format-text-italic");

    public static final Icon FORMAT_TEXT_STRIKETHROUGH = new ActionIcon("format-text-strikethrough");

    public static final Icon FORMAT_TEXT_UNDERLINE = new ActionIcon("format-text-underline");

    public static final Icon GO_BOTTOM = new ActionIcon("go-bottom");

    public static final Icon GO_DOWN = new ActionIcon("go-down");

    public static final Icon GO_FIRST = new ActionIcon("go-first");

    public static final Icon GO_HOME = new ActionIcon("go-home");

    public static final Icon GO_JUMP = new ActionIcon("go-jump");

    public static final Icon GO_LAST = new ActionIcon("go-last");

    public static final Icon GO_NEXT = new ActionIcon("go-next");

    public static final Icon GO_PREVIOUS = new ActionIcon("go-previous");

    public static final Icon GO_TOP = new ActionIcon("go-top");

    public static final Icon GO_UP = new ActionIcon("go-up");

    public static final Icon HELP_ABOUT = new ActionIcon("help-about");

    public static final Icon HELP_CONTENTS = new ActionIcon("help-contents");

    public static final Icon HELP_FAQ = new ActionIcon("help-faq");

    public static final Icon INSERT_IMAGE = new ActionIcon("insert-image");

    public static final Icon INSERT_LINK = new ActionIcon("insert-link");

    public static final Icon INSERT_OBJECT = new ActionIcon("insert-object");

    public static final Icon INSERT_TEXT = new ActionIcon("insert-text");

    public static final Icon LIST_ADD = new ActionIcon("list-add");

    public static final Icon LIST_REMOVE = new ActionIcon("list-remove");

    public static final Icon MAIL_FORWARD = new ActionIcon("mail-forward");

    public static final Icon MAIL_MARK_IMPORTANT = new ActionIcon("mail-mark-important");

    public static final Icon MAIL_MARK_JUNK = new ActionIcon("mail-mark-junk");

    public static final Icon MAIL_MARK_NOTJUNK = new ActionIcon("mail-mark-notjunk");

    public static final Icon MAIL_MARK_READ = new ActionIcon("mail-mark-read");

    public static final Icon MAIL_MARK_UNREAD = new ActionIcon("mail-mark-unread");

    public static final Icon MAIL_MESSAGE_NEW = new ActionIcon("mail-message-new");

    public static final Icon MAIL_REPLY_ALL = new ActionIcon("mail-reply-all");

    public static final Icon MAIL_REPLY_SENDER = new ActionIcon("mail-reply-sender");

    public static final Icon MAIL_SEND = new ActionIcon("mail-send");

    public static final Icon MAIL_SEND_RECEIVE = new ActionIcon("mail-send-receive");

    public static final Icon MEDIA_EJECT = new ActionIcon("media-eject");

    public static final Icon MEDIA_PLAYBACK_PAUSE = new ActionIcon("media-playback-pause");

    public static final Icon MEDIA_PLAYBACK_START = new ActionIcon("media-playback-start");

    public static final Icon MEDIA_PLAYBACK_STOP = new ActionIcon("media-playback-stop");

    public static final Icon MEDIA_RECORD = new ActionIcon("media-record");

    public static final Icon MEDIA_SEEK_BACKWARD = new ActionIcon("media-seek-backward");

    public static final Icon MEDIA_SEEK_FORWARD = new ActionIcon("media-seek-forward");

    public static final Icon MEDIA_SKIP_BACKWARD = new ActionIcon("media-skip-backward");

    public static final Icon MEDIA_SKIP_FORWARD = new ActionIcon("media-skip-forward");

    public static final Icon OBJECT_FLIP_HORIZONTAL = new ActionIcon("object-flip-horizontal");

    public static final Icon OBJECT_FLIP_VERTICAL = new ActionIcon("object-flip-vertical");

    public static final Icon OBJECT_ROTATE_LEFT = new ActionIcon("object-rotate-left");

    public static final Icon OBJECT_ROTATE_RIGHT = new ActionIcon("object-rotate-right");

    public static final Icon PROCESS_STOP = new ActionIcon("process-stop");

    public static final Icon SYSTEM_LOCK_SCREEN = new ActionIcon("system-lock-screen");

    public static final Icon SYSTEM_LOG_OUT = new ActionIcon("system-log-out");

    public static final Icon SYSTEM_RUN = new ActionIcon("system-run");

    public static final Icon SYSTEM_SEARCH = new ActionIcon("system-search");

    public static final Icon SYSTEM_SHUTDOWN = new ActionIcon("system-shutdown");

    public static final Icon TAB_NEW = new ActionIcon("tab-new");

    public static final Icon TOOLS_CHECK_SPELLING = new ActionIcon("tools-check-spelling");

    public static final Icon VIEW_FULLSCREEN = new ActionIcon("view-fullscreen");

    public static final Icon VIEW_REFRESH = new ActionIcon("view-refresh");

    public static final Icon VIEW_RESTORE = new ActionIcon("view-restore");

    public static final Icon VIEW_SORT_ASCENDING = new ActionIcon("view-sort-ascending");

    public static final Icon VIEW_SORT_DESCENDING = new ActionIcon("view-sort-descending");

    public static final Icon WINDOW_CLOSE = new ActionIcon("window-close");

    public static final Icon WINDOW_NEW = new ActionIcon("window-new");

    public static final Icon ZOOM_FIT_BEST = new ActionIcon("zoom-fit-best");

    public static final Icon ZOOM_IN = new ActionIcon("zoom-in");

    public static final Icon ZOOM_ORIGINAL = new ActionIcon("zoom-original");

    public static final Icon ZOOM_OUT = new ActionIcon("zoom-out");
}
