/*
 * StockId.java
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
 * Copyright (c) 2007 Vreixo Formoso
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 */
package org.gnome.gtk;

/**
 * Identifier for a stock item.
 * 
 * <p>
 * Stock items represent commonly-used Menu or Toolbar items such as "Open" or
 * "Exit". An stock item defines properties like a localized user-visible
 * Label, an icon or a keyboard accelerator, that will be used when creating
 * new Buttons, MenuItems, ... from the stock item.
 * 
 * <p>
 * Gtk+ comes with a pre-built set of stock items. You should use them in your
 * applications whenever possible. That way, your application will have a
 * look-n-feel consistent with the other GNOME apps and the user preferences.
 * This will help the user, for example, to identify what a Button does, or to
 * find how to execute a common operation in your application.
 * 
 * <p>
 * Each stock item is identified by a StockId, that you can pass to
 * constructors of different widgets, such as Buttons. When your application
 * does a common task represented by one of the StockIds in this class, you
 * should use it to identify that task in your Menus and Buttons. On the other
 * side, if the stock item seems to fit what you application does (maybe just
 * because the icon is suitable for your operation), but actually that action
 * is not logically equivalent with the usual usage of the stock item in other
 * applications, you shouldn't use it.
 * 
 * @author Vreixo Formoso
 * @since 4.0.4
 */
/*
 * In Gtk+ stock-ids are just plain char* strings. We implement this here for
 * commodity and type-safety.
 * 
 * TODO the javadoc comment will need to be reviewed when we implement
 * StockItem.
 */
public class StockId
{
    private final String stockId;

    /* TODO should this be public when adding register capabilities */
    private StockId(String stockId) {
        this.stockId = stockId;
    }

    /* TODO make this public? */
    String getStockId() {
        return stockId;
    }

    /** 
     * The "About" item. This is usually used to identify an action that
     * shows some version, copyright or authoring information about the
     * application.
     */
    public static final StockId ABOUT = new StockId("gtk-about");

    public static final StockId ADD = new StockId("gtk-add");

    public static final StockId APPLY = new StockId("gtk-apply");

    public static final StockId BOLD = new StockId("gtk-bold");

    public static final StockId CANCEL = new StockId("gtk-cancel");

    public static final StockId CDROM = new StockId("gtk-cdrom");

    public static final StockId CLEAR = new StockId("gtk-clear");

    public static final StockId CLOSE = new StockId("gtk-close");

    public static final StockId COLOR_PICKER = new StockId("gtk-color-picker");

    public static final StockId CONVERT = new StockId("gtk-convert");

    public static final StockId CONNECT = new StockId("gtk-connect");

    public static final StockId COPY = new StockId("gtk-copy");

    public static final StockId CUT = new StockId("gtk-cut");

    public static final StockId DELETE = new StockId("gtk-delete");

    public static final StockId DIALOG_AUTHENTICATION = new StockId("gtk-dialog-authentication");

    public static final StockId DIALOG_ERROR = new StockId("gtk-dialog-error");

    public static final StockId DIALOG_INFO = new StockId("gtk-dialog-info");

    public static final StockId DIALOG_QUESTION = new StockId("gtk-dialog-question");

    public static final StockId DIALOG_WARNING = new StockId("gtk-dialog-warning");

    public static final StockId DIRECTORY = new StockId("gtk-directory");

    public static final StockId DISCONNECT = new StockId("gtk-disconnect");

    public static final StockId DND = new StockId("gtk-dnd");

    public static final StockId DND_MULTIPLE = new StockId("gtk-dnd-multiple");

    public static final StockId EDIT = new StockId("gtk-edit");

    public static final StockId EXECUTE = new StockId("gtk-execute");

    public static final StockId FILE = new StockId("gtk-file");

    static final public StockId FIND = new StockId("gtk-find");

    static final public StockId FIND_AND_REPLACE = new StockId("gtk-find-and-replace");

    static final public StockId FLOPPY = new StockId("gtk-floppy");

    static final public StockId FULLSCREEN = new StockId("gtk-fullscreen");

    static final public StockId GOTO_BOTTOM = new StockId("gtk-goto-bottom");

    static final public StockId GOTO_FIRST = new StockId("gtk-goto-first");

    static final public StockId GOTO_LAST = new StockId("gtk-goto-last");

    static final public StockId GOTO_TOP = new StockId("gtk-goto-top");

    static final public StockId GO_BACK = new StockId("gtk-go-back");

    static final public StockId GO_DOWN = new StockId("gtk-go-down");

    static final public StockId GO_FORWARD = new StockId("gtk-go-forward");

    static final public StockId GO_UP = new StockId("gtk-go-up");

    static final public StockId HARDDISK = new StockId("gtk-harddisk");

    static final public StockId HELP = new StockId("gtk-help");

    static final public StockId HOME = new StockId("gtk-home");

    static final public StockId INDENT = new StockId("gtk-indent");

    static final public StockId INDEX = new StockId("gtk-index");

    static final public StockId INFO = new StockId("gtk-info");

    static final public StockId ITALIC = new StockId("gtk-italic");

    static final public StockId JUMP_TO = new StockId("gtk-jump-to");

    static final public StockId JUSTIFY_CENTER = new StockId("gtk-justify-center");

    static final public StockId JUSTIFY_FILL = new StockId("gtk-justify-fill");

    static final public StockId JUSTIFY_LEFT = new StockId("gtk-justify-left");

    static final public StockId JUSTIFY_RIGHT = new StockId("gtk-justify-right");

    static final public StockId LEAVE_FULLSCREEN = new StockId("gtk-leave-fullscreen");

    static final public StockId MEDIA_FORWARD = new StockId("gtk-media-forward");

    static final public StockId MEDIA_NEXT = new StockId("gtk-media-next");

    static final public StockId MEDIA_PAUSE = new StockId("gtk-media-pause");

    static final public StockId MEDIA_PLAY = new StockId("gtk-media-play");

    static final public StockId MEDIA_PREVIOUS = new StockId("gtk-media-previous");

    static final public StockId MEDIA_RECORD = new StockId("gtk-media-record");

    static final public StockId MEDIA_REWIND = new StockId("gtk-media-rewind");

    static final public StockId MEDIA_STOP = new StockId("gtk-media-stop");

    static final public StockId MISSING_IMAGE = new StockId("gtk-missing-image");

    static final public StockId NETWORK = new StockId("gtk-network");

    static final public StockId NEW = new StockId("gtk-new");

    static final public StockId NO = new StockId("gtk-no");

    static final public StockId OK = new StockId("gtk-ok");

    static final public StockId OPEN = new StockId("gtk-open");

    public static final StockId ORIENTATION_LANDSCAPE = new StockId("gtk-orientation-landscape");

    public static final StockId ORIENTATION_PORTRAIT = new StockId("gtk-orientation-portrait");

    public static final StockId ORIENTATION_REVERSE_LANDSCAPE = new StockId(
            "gtk-orientation-reverse-landscape");

    public static final StockId ORIENTATION_REVERSE_PORTRAIT = new StockId(
            "gtk-orientation-reverse-portrait");

    static final public StockId PASTE = new StockId("gtk-paste");

    static final public StockId PREFERENCES = new StockId("gtk-preferences");

    static final public StockId PRINT = new StockId("gtk-print");

    static final public StockId PRINT_PREVIEW = new StockId("gtk-print-preview");

    static final public StockId PROPERTIES = new StockId("gtk-properties");

    static final public StockId QUIT = new StockId("gtk-quit");

    static final public StockId REDO = new StockId("gtk-redo");

    static final public StockId REFRESH = new StockId("gtk-refresh");

    static final public StockId REMOVE = new StockId("gtk-remove");

    static final public StockId REVERT_TO_SAVED = new StockId("gtk-revert-to-saved");

    static final public StockId SAVE = new StockId("gtk-save");

    static final public StockId SAVE_AS = new StockId("gtk-save-as");

    static final public StockId SELECT_ALL = new StockId("gtk-select-all");

    static final public StockId SELECT_COLOR = new StockId("gtk-select-color");

    public static final StockId SELECT_FONT = new StockId("gtk-select-font");

    public static final StockId SORT_ASCENDING = new StockId("gtk-sort-ascending");

    public static final StockId SORT_DESCENDING = new StockId("gtk-sort-descending");

    public static final StockId SPELL_CHECK = new StockId("gtk-spell-check");

    public static final StockId STOP = new StockId("gtk-stop");

    public static final StockId STRIKETHROUGH = new StockId("gtk-strikethrough");

    public static final StockId UNDELETE = new StockId("gtk-undelete");

    public static final StockId UNDERLINE = new StockId("gtk-underline");

    public static final StockId UNDO = new StockId("gtk-undo");

    public static final StockId UNINDENT = new StockId("gtk-unindent");

    public static final StockId YES = new StockId("gtk-yes");

    public static final StockId ZOOM_100 = new StockId("gtk-zoom-100");

    public static final StockId ZOOM_FIT = new StockId("gtk-zoom-fit");

    public static final StockId ZOOM_IN = new StockId("gtk-zoom-in");

    public static final StockId ZOOM_OUT = new StockId("gtk-zoom-out");

}
