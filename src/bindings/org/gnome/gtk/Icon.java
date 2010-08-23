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
 * "Claspath Exception"), the copyright holders of this library give you
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
 * Identify the icons that should be in the icons theme. These constants can
 * be used to create an {@link Image image} using the constructor
 * {@link Image#Image(String, IconSize) Image(Icon, IconSize)}.
 * 
 * @author Guillaume Mazoyer
 * @since 4.0.17
 */
public class Icon
{
    /**
     * The name used to identify the icon in the theme.
     */
    private final String name;

    /**
     * Construct a new Icon constant from a given string. This is provided so
     * that if we missed a name that you desperately need, you can subclass
     * and create it. Ideally, though, we'd appreciate it if you'd point out
     * what it is about that name that you needed, and if appropriate submit a
     * patch adding it to this class instead.
     * 
     * @since 4.0.17
     */
    protected Icon(String name) {
        this.name = name;
    }

    /**
     * Get the name of the icon expected by GTK.
     * 
     * @since 4.0.17
     */
    /*
     * For the moment, keep this with restricted visibility because it's
     * internal.
     */
    protected String getName() {
        return name;
    }

    public static final Icon EMBLEM_DOWNLOADS = new Icon("emblem-downloads");

    public static final Icon EMBLEM_PACKAGE = new Icon("emblem-package");

    public static final Icon EMBLEM_GENERIC = new Icon("emblem-generic");

    public static final Icon EMBLEM_MAIL = new Icon("emblem-mail");

    public static final Icon EMBLEM_IMPORTANT = new Icon("emblem-important");

    public static final Icon EMBLEM_SHARED = new Icon("emblem-shared");

    public static final Icon EMBLEM_DEFAULT = new Icon("emblem-default");

    public static final Icon EMBLEM_FAVORITE = new Icon("emblem-favorite");

    public static final Icon EMBLEM_WEB = new Icon("emblem-web");

    public static final Icon EMBLEM_UNREADABLE = new Icon("emblem-unreadable");

    public static final Icon EMBLEM_SYMBOLIC_LINK = new Icon("emblem-symbolic-link");

    public static final Icon EMBLEM_SYSTEM = new Icon("emblem-system");

    public static final Icon EMBLEM_DESKTOP = new Icon("emblem-desktop");

    public static final Icon EMBLEM_DOCUMENTS = new Icon("emblem-documents");

    public static final Icon EMBLEM_NEW = new Icon("emblem-new");

    public static final Icon EMBLEM_READONLY = new Icon("emblem-readonly");

    public static final Icon EMBLEM_URGENT = new Icon("emblem-urgent");

    public static final Icon EMBLEM_PHOTOS = new Icon("emblem-photos");

    public static final Icon STOCK_PERSON = new Icon("stock_person");

    public static final Icon TEXT_X_SCRIPT = new Icon("text-x-script");

    public static final Icon X_OFFICE_SPREADSHEET = new Icon("x-office-spreadsheet");

    public static final Icon X_OFFICE_DOCUMENT = new Icon("x-office-document");

    public static final Icon APPLICATION_X_EXECUTABLE = new Icon("application-x-executable");

    public static final Icon VIDEO_X_GENERIC = new Icon("video-x-generic");

    public static final Icon X_OFFICE_PRESENTATION = new Icon("x-office-presentation");

    public static final Icon IMAGE_X_GENERIC = new Icon("image-x-generic");

    public static final Icon X_OFFICE_DRAWING = new Icon("x-office-drawing");

    public static final Icon X_OFFICE_CALENDAR = new Icon("x-office-calendar");

    public static final Icon TEXT_X_GENERIC_TEMPLATE = new Icon("text-x-generic-template");

    public static final Icon APPLICATION_CERTIFICATE = new Icon("application-certificate");

    public static final Icon AUDIO_X_GENERIC = new Icon("audio-x-generic");

    public static final Icon X_OFFICE_ADDRESS_BOOK = new Icon("x-office-address-book");

    public static final Icon TEXT_X_PREVIEW = new Icon("text-x-preview");

    public static final Icon PACKAGE_X_GENERIC = new Icon("package-x-generic");

    public static final Icon TEXT_X_GENERIC = new Icon("text-x-generic");

    public static final Icon FONT_X_GENERIC = new Icon("font-x-generic");

    public static final Icon TEXT_HTML = new Icon("text-html");

    public static final Icon FACE_SMILE_BIG = new Icon("face-smile-big");

    public static final Icon FACE_ANGRY = new Icon("face-angry");

    public static final Icon FACE_WORRIED = new Icon("face-worried");

    public static final Icon FACE_ANGEL = new Icon("face-angel");

    public static final Icon FACE_SMILE = new Icon("face-smile");

    public static final Icon FACE_TIRED = new Icon("face-tired");

    public static final Icon FACE_CRYING = new Icon("face-crying");

    public static final Icon FACE_SAD = new Icon("face-sad");

    public static final Icon FACE_SURPRISE = new Icon("face-surprise");

    public static final Icon FACE_SICK = new Icon("face-sick");

    public static final Icon FACE_EMBARRASSED = new Icon("face-embarrassed");

    public static final Icon FACE_SMIRK = new Icon("face-smirk");

    public static final Icon FACE_MONKEY = new Icon("face-monkey");

    public static final Icon FACE_COOL = new Icon("face-cool");

    public static final Icon FACE_KISS = new Icon("face-kiss");

    public static final Icon FACE_PLAIN = new Icon("face-plain");

    public static final Icon FACE_WINK = new Icon("face-wink");

    public static final Icon FACE_RASPBERRY = new Icon("face-raspberry");

    public static final Icon FACE_DEVILISH = new Icon("face-devilish");

    public static final Icon FACE_LAUGH = new Icon("face-laugh");

    public static final Icon FACE_UNCERTAIN = new Icon("face-uncertain");

    public static final Icon UTILITIES_SYSTEM_MONITOR = new Icon("utilities-system-monitor");

    public static final Icon PREFERENCES_DESKTOP_ACCESSIBILITY = new Icon(
            "preferences-desktop-accessibility");

    public static final Icon PREFERENCES_DESKTOP_KEYBOARD_SHORTCUTS = new Icon(
            "preferences-desktop-keyboard-shortcuts");

    public static final Icon ACCESSORIES_DICTIONARY = new Icon("accessories-dictionary");

    public static final Icon PREFERENCES_DESKTOP_LOCALE = new Icon("preferences-desktop-locale");

    public static final Icon IM_YAHOO = new Icon("im-yahoo");

    public static final Icon SYSTEM_SOFTWARE_INSTALL = new Icon("system-software-install");

    public static final Icon ACCESSORIES_TEXT_EDITOR = new Icon("accessories-text-editor");

    public static final Icon LOGVIEWER = new Icon("logviewer");

    public static final Icon PREFERENCES_SYSTEM_WINDOWS = new Icon("preferences-system-windows");

    public static final Icon SYSTEM_USERS = new Icon("system-users");

    public static final Icon PREFERENCES_DESKTOP_REMOTE_DESKTOP = new Icon(
            "preferences-desktop-remote-desktop");

    public static final Icon SYSTEM_FILE_MANAGER = new Icon("system-file-manager");

    public static final Icon HELP_BROWSER = new Icon("help-browser");

    public static final Icon IM_ICQ = new Icon("im-icq");

    public static final Icon IM_AIM = new Icon("im-aim");

    public static final Icon WEB_BROWSER = new Icon("web-browser");

    public static final Icon SYSTEM_SOFTWARE_UPDATE = new Icon("system-software-update");

    public static final Icon PREFERENCES_DESKTOP_FONT = new Icon("preferences-desktop-font");

    public static final Icon PREFERENCES_DESKTOP_KEYBOARD = new Icon("preferences-desktop-keyboard");

    public static final Icon MULTIMEDIA_VOLUME_CONTROL = new Icon("multimedia-volume-control");

    public static final Icon IM_NOV = new Icon("im-nov");

    public static final Icon ACCESSORIES_CALCULATOR = new Icon("accessories-calculator");

    public static final Icon ACCESSORIES_CHARACTER_MAP = new Icon("accessories-character-map");

    public static final Icon PREFERENCES_DESKTOP_WALLPAPER = new Icon("preferences-desktop-wallpaper");

    public static final Icon IM_JABBER = new Icon("im-jabber");

    public static final Icon USER_INFO = new Icon("user-info");

    public static final Icon PREFERENCES_DESKTOP_SCREENSAVER = new Icon(
            "preferences-desktop-screensaver");

    public static final Icon IM_MSN = new Icon("im-msn");

    public static final Icon UTILITIES_TERMINAL = new Icon("utilities-terminal");

    public static final Icon PREFERENCES_DESKTOP_THEME = new Icon("preferences-desktop-theme");

    public static final Icon APPOINTMENT_SOON = new Icon("appointment-soon");

    public static final Icon MEDIA_PLAYLIST_REPEAT = new Icon("media-playlist-repeat");

    public static final Icon PRINTER_PRINTING = new Icon("printer-printing");

    public static final Icon FOLDER_DRAG_ACCEPT = new Icon("folder-drag-accept");

    public static final Icon SOFTWARE_UPDATE_AVAILABLE = new Icon("software-update-available");

    public static final Icon NETWORK_OFFLINE = new Icon("network-offline");

    public static final Icon IMAGE_MISSING = new Icon("image-missing");

    public static final Icon WEATHER_STORM = new Icon("weather-storm");

    public static final Icon MEDIA_PLAYLIST_SHUFFLE = new Icon("media-playlist-shuffle");

    public static final Icon NETWORK_RECEIVE = new Icon("network-receive");

    public static final Icon WEATHER_CLEAR = new Icon("weather-clear");

    public static final Icon WEATHER_SHOWERS = new Icon("weather-showers");

    public static final Icon IMAGE_LOADING = new Icon("image-loading");

    public static final Icon AUDIO_VOLUME_MUTED = new Icon("audio-volume-muted");

    public static final Icon WEATHER_SNOW = new Icon("weather-snow");

    public static final Icon DIALOG_QUESTION = new Icon("dialog-question");

    public static final Icon NETWORK_ERROR = new Icon("network-error");

    public static final Icon DIALOG_INFORMATION = new Icon("dialog-information");

    public static final Icon DIALOG_ERROR = new Icon("dialog-error");

    public static final Icon NETWORK_TRANSMIT_RECEIVE = new Icon("network-transmit-receive");

    public static final Icon MAIL_UNREAD = new Icon("mail-unread");

    public static final Icon NETWORK_IDLE = new Icon("network-idle");

    public static final Icon DIALOG_PASSWORD = new Icon("dialog-password");

    public static final Icon USER_TRASH_FULL = new Icon("user-trash-full");

    public static final Icon MAIL_ATTACHMENT = new Icon("mail-attachment");

    public static final Icon WEATHER_SEVERE_ALERT = new Icon("weather-severe-alert");

    public static final Icon WEATHER_FOG = new Icon("weather-fog");

    public static final Icon DIALOG_WARNING = new Icon("dialog-warning");

    public static final Icon SECURITY_LOW = new Icon("security-low");

    public static final Icon TASK_PAST_DUE = new Icon("task-past-due");

    public static final Icon SECURITY_MEDIUM = new Icon("security-medium");

    public static final Icon APPOINTMENT_MISSED = new Icon("appointment-missed");

    public static final Icon AUDIO_VOLUME_LOW = new Icon("audio-volume-low");

    public static final Icon FOLDER_VISITING = new Icon("folder-visiting");

    public static final Icon MAIL_READ = new Icon("mail-read");

    public static final Icon MAIL_REPLIED = new Icon("mail-replied");

    public static final Icon SECURITY_HIGH = new Icon("security-high");

    public static final Icon BATTERY_LOW = new Icon("battery-low");

    public static final Icon WEATHER_CLEAR_NIGHT = new Icon("weather-clear-night");

    public static final Icon NETWORK_TRANSMIT = new Icon("network-transmit");

    public static final Icon PRINTER_ERROR = new Icon("printer-error");

    public static final Icon WEATHER_SHOWERS_SCATTERED = new Icon("weather-showers-scattered");

    public static final Icon WEATHER_FEW_CLOUDS = new Icon("weather-few-clouds");

    public static final Icon AUDIO_VOLUME_HIGH = new Icon("audio-volume-high");

    public static final Icon AUDIO_VOLUME_MEDIUM = new Icon("audio-volume-medium");

    public static final Icon BATTERY_CAUTION = new Icon("battery-caution");

    public static final Icon FOLDER_OPEN = new Icon("folder-open");

    public static final Icon WEATHER_OVERCAST = new Icon("weather-overcast");

    public static final Icon SOFTWARE_UPDATE_URGENT = new Icon("software-update-urgent");

    public static final Icon WEATHER_FEW_CLOUDS_NIGHT = new Icon("weather-few-clouds-night");

    public static final Icon TASK_DUE = new Icon("task-due");

    public static final Icon APPLICATIONS_MULTIMEDIA = new Icon("applications-multimedia");

    public static final Icon APPLICATIONS_OTHER = new Icon("applications-other");

    public static final Icon APPLICATIONS_ACCESSORIES = new Icon("applications-accessories");

    public static final Icon APPLICATIONS_OFFICE = new Icon("applications-office");

    public static final Icon PREFERENCES_DESKTOP_PERIPHERALS = new Icon(
            "preferences-desktop-peripherals");

    public static final Icon SYSTEM_HELP = new Icon("system-help");

    public static final Icon APPLICATIONS_UTILITIES = new Icon("applications-utilities");

    public static final Icon APPLICATIONS_GRAPHICS = new Icon("applications-graphics");

    public static final Icon APPLICATIONS_SYSTEM = new Icon("applications-system");

    public static final Icon APPLICATIONS_GAMES = new Icon("applications-games");

    public static final Icon APPLICATIONS_INTERNET = new Icon("applications-internet");

    public static final Icon APPLICATIONS_DEVELOPMENT = new Icon("applications-development");

    public static final Icon PREFERENCES_OTHER = new Icon("preferences-other");

    public static final Icon PREFERENCES_DESKTOP = new Icon("preferences-desktop");

    public static final Icon PREFERENCES_SYSTEM = new Icon("preferences-system");

    public static final Icon APPLICATIONS_SCIENCE = new Icon("applications-science");

    public static final Icon PREFERENCES_SYSTEM_NETWORK = new Icon("preferences-system-network");

    public static final Icon APPLICATIONS_ENGINEERING = new Icon("applications-engineering");

    public static final Icon PREFERENCES_DESKTOP_PERSONAL = new Icon("preferences-desktop-personal");

    public static final Icon PHONE = new Icon("phone");

    public static final Icon AUDIO_INPUT_MICROPHONE = new Icon("audio-input-microphone");

    public static final Icon NETWORK_WIRELESS = new Icon("network-wireless");

    public static final Icon MEDIA_ZIP = new Icon("media-zip");

    public static final Icon DRIVE_REMOVABLE_MEDIA = new Icon("drive-removable-media");

    public static final Icon CAMERA_VIDEO = new Icon("camera-video");

    public static final Icon MEDIA_TAPE = new Icon("media-tape");

    public static final Icon INPUT_GAMING = new Icon("input-gaming");

    public static final Icon BATTERY = new Icon("battery");

    public static final Icon MEDIA_FLOPPY = new Icon("media-floppy");

    public static final Icon MEDIA_FLASH = new Icon("media-flash");

    public static final Icon CAMERA_WEB = new Icon("camera-web");

    public static final Icon CAMERA_PHOTO = new Icon("camera-photo");

    public static final Icon COMPUTER = new Icon("computer");

    public static final Icon MULTIMEDIA_PLAYER = new Icon("multimedia-player");

    public static final Icon NETWORK_WIRED = new Icon("network-wired");

    public static final Icon AUDIO_CARD = new Icon("audio-card");

    public static final Icon DRIVE_HARDDISK = new Icon("drive-harddisk");

    public static final Icon MODEM = new Icon("modem");

    public static final Icon DRIVE_OPTICAL = new Icon("drive-optical");

    public static final Icon VIDEO_DISPLAY = new Icon("video-display");

    public static final Icon INPUT_TABLET = new Icon("input-tablet");

    public static final Icon SCANNER = new Icon("scanner");

    public static final Icon PRINTER = new Icon("printer");

    public static final Icon INPUT_MOUSE = new Icon("input-mouse");

    public static final Icon MEDIA_OPTICAL = new Icon("media-optical");

    public static final Icon PDA = new Icon("pda");

    public static final Icon INPUT_KEYBOARD = new Icon("input-keyboard");

    public static final Icon INSERT_OBJECT = new Icon("insert-object");

    public static final Icon EDIT_DELETE = new Icon("edit-delete");

    public static final Icon WINDOW_CLOSE = new Icon("window-close");

    public static final Icon ADDRESS_BOOK_NEW = new Icon("address-book-new");

    public static final Icon SYSTEM_SEARCH = new Icon("system-search");

    public static final Icon MEDIA_SEEK_FORWARD = new Icon("media-seek-forward");

    public static final Icon MEDIA_SKIP_BACKWARD = new Icon("media-skip-backward");

    public static final Icon DOCUMENT_REVERT = new Icon("document-revert");

    public static final Icon APPOINTMENT_NEW = new Icon("appointment-new");

    public static final Icon INSERT_TEXT = new Icon("insert-text");

    public static final Icon VIEW_REFRESH = new Icon("view-refresh");

    public static final Icon EDIT_UNDO = new Icon("edit-undo");

    public static final Icon FORMAT_INDENT_MORE = new Icon("format-indent-more");

    public static final Icon SYSTEM_LOG_OUT = new Icon("system-log-out");

    public static final Icon OBJECT_ROTATE_RIGHT = new Icon("object-rotate-right");

    public static final Icon EDIT_FIND = new Icon("edit-find");

    public static final Icon HELP_ABOUT = new Icon("help-about");

    public static final Icon ZOOM_ORIGINAL = new Icon("zoom-original");

    public static final Icon EDIT_FIND_REPLACE = new Icon("edit-find-replace");

    public static final Icon EDIT_REDO = new Icon("edit-redo");

    public static final Icon INSERT_IMAGE = new Icon("insert-image");

    public static final Icon FORMAT_TEXT_UNDERLINE = new Icon("format-text-underline");

    public static final Icon GO_NEXT = new Icon("go-next");

    public static final Icon VIEW_FULLSCREEN = new Icon("view-fullscreen");

    public static final Icon MAIL_MARK_IMPORTANT = new Icon("mail-mark-important");

    public static final Icon BOOKMARK_NEW = new Icon("bookmark-new");

    public static final Icon LIST_REMOVE = new Icon("list-remove");

    public static final Icon WINDOW_NEW = new Icon("window-new");

    public static final Icon DOCUMENT_NEW = new Icon("document-new");

    public static final Icon FORMAT_JUSTIFY_RIGHT = new Icon("format-justify-right");

    public static final Icon MEDIA_EJECT = new Icon("media-eject");

    public static final Icon MAIL_SEND = new Icon("mail-send");

    public static final Icon GO_LAST = new Icon("go-last");

    public static final Icon FORMAT_TEXT_BOLD = new Icon("format-text-bold");

    public static final Icon MEDIA_PLAYBACK_START = new Icon("media-playback-start");

    public static final Icon TOOLS_CHECK_SPELLING = new Icon("tools-check-spelling");

    public static final Icon MEDIA_SKIP_FORWARD = new Icon("media-skip-forward");

    public static final Icon PROCESS_STOP = new Icon("process-stop");

    public static final Icon EDIT_PASTE = new Icon("edit-paste");

    public static final Icon ZOOM_FIT_BEST = new Icon("zoom-fit-best");

    public static final Icon FORMAT_INDENT_LESS = new Icon("format-indent-less");

    public static final Icon MAIL_MARK_READ = new Icon("mail-mark-read");

    public static final Icon FORMAT_TEXT_DIRECTION_LTR = new Icon("format-text-direction-ltr");

    public static final Icon MAIL_MARK_UNREAD = new Icon("mail-mark-unread");

    public static final Icon DOCUMENT_OPEN = new Icon("document-open");

    public static final Icon MAIL_REPLY_SENDER = new Icon("mail-reply-sender");

    public static final Icon GO_BOTTOM = new Icon("go-bottom");

    public static final Icon EDIT_CLEAR = new Icon("edit-clear");

    public static final Icon ZOOM_IN = new Icon("zoom-in");

    public static final Icon DOCUMENT_PROPERTIES = new Icon("document-properties");

    public static final Icon MEDIA_PLAYBACK_PAUSE = new Icon("media-playback-pause");

    public static final Icon VIEW_RESTORE = new Icon("view-restore");

    public static final Icon VIEW_SORT_DESCENDING = new Icon("view-sort-descending");

    public static final Icon DOCUMENT_SAVE = new Icon("document-save");

    public static final Icon OBJECT_ROTATE_LEFT = new Icon("object-rotate-left");

    public static final Icon SYSTEM_RUN = new Icon("system-run");

    public static final Icon DOCUMENT_PRINT_PREVIEW = new Icon("document-print-preview");

    public static final Icon FORMAT_TEXT_STRIKETHROUGH = new Icon("format-text-strikethrough");

    public static final Icon EDIT_SELECT_ALL = new Icon("edit-select-all");

    public static final Icon DOCUMENT_PRINT = new Icon("document-print");

    public static final Icon OBJECT_FLIP_HORIZONTAL = new Icon("object-flip-horizontal");

    public static final Icon CONTACT_NEW = new Icon("contact-new");

    public static final Icon CALL_STOP = new Icon("call-stop");

    public static final Icon MEDIA_SEEK_BACKWARD = new Icon("media-seek-backward");

    public static final Icon MEDIA_PLAYBACK_STOP = new Icon("media-playback-stop");

    public static final Icon HELP_FAQ = new Icon("help-faq");

    public static final Icon CALL_START = new Icon("call-start");

    public static final Icon HELP_CONTENTS = new Icon("help-contents");

    public static final Icon GO_FIRST = new Icon("go-first");

    public static final Icon MAIL_FORWARD = new Icon("mail-forward");

    public static final Icon FORMAT_JUSTIFY_LEFT = new Icon("format-justify-left");

    public static final Icon APPLICATION_EXIT = new Icon("application-exit");

    public static final Icon SYSTEM_SHUTDOWN = new Icon("system-shutdown");

    public static final Icon FORMAT_TEXT_ITALIC = new Icon("format-text-italic");

    public static final Icon GO_UP = new Icon("go-up");

    public static final Icon GO_DOWN = new Icon("go-down");

    public static final Icon MEDIA_RECORD = new Icon("media-record");

    public static final Icon VIEW_SORT_ASCENDING = new Icon("view-sort-ascending");

    public static final Icon FORMAT_TEXT_DIRECTION_RTL = new Icon("format-text-direction-rtl");

    public static final Icon DOCUMENT_PAGE_SETUP = new Icon("document-page-setup");

    public static final Icon OBJECT_FLIP_VERTICAL = new Icon("object-flip-vertical");

    public static final Icon EDIT_COPY = new Icon("edit-copy");

    public static final Icon DOCUMENT_SAVE_AS = new Icon("document-save-as");

    public static final Icon GO_PREVIOUS = new Icon("go-previous");

    public static final Icon LIST_ADD = new Icon("list-add");

    public static final Icon SYSTEM_LOCK_SCREEN = new Icon("system-lock-screen");

    public static final Icon DOCUMENT_SEND = new Icon("document-send");

    public static final Icon MAIL_REPLY_ALL = new Icon("mail-reply-all");

    public static final Icon GO_HOME = new Icon("go-home");

    public static final Icon FORMAT_JUSTIFY_CENTER = new Icon("format-justify-center");

    public static final Icon FOLDER_NEW = new Icon("folder-new");

    public static final Icon ZOOM_OUT = new Icon("zoom-out");

    public static final Icon FORMAT_JUSTIFY_FILL = new Icon("format-justify-fill");

    public static final Icon GO_JUMP = new Icon("go-jump");

    public static final Icon MAIL_SEND_RECEIVE = new Icon("mail-send-receive");

    public static final Icon DOCUMENT_OPEN_RECENT = new Icon("document-open-recent");

    public static final Icon INSERT_LINK = new Icon("insert-link");

    public static final Icon EDIT_CUT = new Icon("edit-cut");

    public static final Icon MAIL_MESSAGE_NEW = new Icon("mail-message-new");

    public static final Icon GO_TOP = new Icon("go-top");

    public static final Icon USER_BOOKMARKS = new Icon("user-bookmarks");

    public static final Icon NETWORK_SERVER = new Icon("network-server");

    public static final Icon NETWORK_WORKGROUP = new Icon("network-workgroup");

    public static final Icon USER_HOME = new Icon("user-home");

    public static final Icon START_HERE = new Icon("start-here");

    public static final Icon FOLDER_REMOTE = new Icon("folder-remote");

    public static final Icon USER_DESKTOP = new Icon("user-desktop");

    public static final Icon FOLDER_SAVED_SEARCH = new Icon("folder-saved-search");

    public static final Icon FOLDER = new Icon("folder");

    public static final Icon USER_TRASH = new Icon("user-trash");
}
