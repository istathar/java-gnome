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
 * Named icons representing status.
 * 
 * @author Guillaume Mazoyer
 * @author Andrew Cowie
 * @since 4.0.17
 */
public class StateIcon extends Icon
{
    protected StateIcon(String name) {
        super(name);
    }

    public static final Icon APPOINTMENT_MISSED = new StateIcon("appointment-missed");

    public static final Icon APPOINTMENT_SOON = new StateIcon("appointment-soon");

    public static final Icon AUDIO_VOLUME_HIGH = new StateIcon("audio-volume-high");

    public static final Icon AUDIO_VOLUME_LOW = new StateIcon("audio-volume-low");

    public static final Icon AUDIO_VOLUME_MEDIUM = new StateIcon("audio-volume-medium");

    public static final Icon AUDIO_VOLUME_MUTED = new StateIcon("audio-volume-muted");

    public static final Icon AVATAR_DEFAULT = new StateIcon("avatar-default");

    public static final Icon BATTERY_CAUTION_CHARGING = new StateIcon("battery-caution-charging");

    public static final Icon BATTERY_CAUTION = new StateIcon("battery-caution");

    public static final Icon BATTERY_CHARGING = new StateIcon("battery-charging");

    public static final Icon BATTERY_EMPTY = new StateIcon("battery-empty");

    public static final Icon BATTERY_FULL_CHARGING = new StateIcon("battery-full-charging");

    public static final Icon BATTERY_FULL = new StateIcon("battery-full");

    public static final Icon BATTERY_GOOD_CHARGING = new StateIcon("battery-good-charging");

    public static final Icon BATTERY_GOOD = new StateIcon("battery-good");

    public static final Icon BATTERY_LOW_CHARGING = new StateIcon("battery-low-charging");

    public static final Icon BATTERY_LOW = new StateIcon("battery-low");

    public static final Icon BATTERY_MISSING = new StateIcon("battery-missing");

    public static final Icon CHANGES_ALLOW = new StateIcon("changes-allow");

    public static final Icon CHANGES_PREVENT = new StateIcon("changes-prevent");

    public static final Icon DIALOG_ERROR = new StateIcon("dialog-error");

    public static final Icon DIALOG_INFORMATION = new StateIcon("dialog-information");

    public static final Icon DIALOG_PASSWORD = new StateIcon("dialog-password");

    public static final Icon DIALOG_QUESTION = new StateIcon("dialog-question");

    public static final Icon DIALOG_WARNING = new StateIcon("dialog-warning");

    public static final Icon FOLDER_DRAG_ACCEPT = new StateIcon("folder-drag-accept");

    public static final Icon FOLDER_OPEN = new StateIcon("folder-open");

    public static final Icon FOLDER_VISITING = new StateIcon("folder-visiting");

    public static final Icon IMAGE_LOADING = new StateIcon("image-loading");

    public static final Icon IMAGE_MISSING = new StateIcon("image-missing");

    public static final Icon MAIL_ATTACHMENT = new StateIcon("mail-attachment");

    public static final Icon MAIL_READ = new StateIcon("mail-read");

    public static final Icon MAIL_REPLIED = new StateIcon("mail-replied");

    public static final Icon MAIL_SIGNED = new StateIcon("mail-signed");

    public static final Icon MAIL_SIGNED_VERIFIED = new StateIcon("mail-signed-verified");

    public static final Icon MAIL_UNREAD = new StateIcon("mail-unread");

    public static final Icon MEDIA_PLAYLIST_REPEAT = new StateIcon("media-playlist-repeat");

    public static final Icon MEDIA_PLAYLIST_SHUFFLE = new StateIcon("media-playlist-shuffle");

    public static final Icon NETWORK_ERROR = new StateIcon("network-error");

    public static final Icon NETWORK_IDLE = new StateIcon("network-idle");

    public static final Icon NETWORK_OFFLINE = new StateIcon("network-offline");

    public static final Icon NETWORK_RECEIVE = new StateIcon("network-receive");

    public static final Icon NETWORK_TRANSMIT = new StateIcon("network-transmit");

    public static final Icon NETWORK_TRANSMIT_RECEIVE = new StateIcon("network-transmit-receive");

    public static final Icon NETWORK_WIRELESS_ENCRYPTED = new StateIcon("network-wireless-encrypted");

    public static final Icon PRINTER_ERROR = new StateIcon("printer-error");

    public static final Icon PRINTER_PRINTING = new StateIcon("printer-printing");

    public static final Icon SECURITY_HIGH = new StateIcon("security-high");

    public static final Icon SECURITY_LOW = new StateIcon("security-low");

    public static final Icon SECURITY_MEDIUM = new StateIcon("security-medium");

    public static final Icon SOFTWARE_UPDATE_AVAILABLE = new StateIcon("software-update-available");

    public static final Icon SOFTWARE_UPDATE_URGENT = new StateIcon("software-update-urgent");

    public static final Icon TASK_DUE = new StateIcon("task-due");

    public static final Icon TASK_PAST_DUE = new StateIcon("task-past-due");

    public static final Icon USER_AVAILABLE = new StateIcon("user-available");

    public static final Icon USER_AWAY = new StateIcon("user-away");

    public static final Icon USER_BUSY = new StateIcon("user-busy");

    public static final Icon USER_IDLE = new StateIcon("user-idle");

    public static final Icon USER_INVISIBLE = new StateIcon("user-invisible");

    public static final Icon USER_OFFLINE = new StateIcon("user-offline");

    public static final Icon USER_TRASH_FULL = new StateIcon("user-trash-full");

    public static final Icon WEATHER_CLEAR_NIGHT = new StateIcon("weather-clear-night");

    public static final Icon WEATHER_CLEAR = new StateIcon("weather-clear");

    public static final Icon WEATHER_FEW_CLOUDS_NIGHT = new StateIcon("weather-few-clouds-night");

    public static final Icon WEATHER_FEW_CLOUDS = new StateIcon("weather-few-clouds");

    public static final Icon WEATHER_FOG = new StateIcon("weather-fog");

    public static final Icon WEATHER_OVERCAST = new StateIcon("weather-overcast");

    public static final Icon WEATHER_SEVERE_ALERT = new StateIcon("weather-severe-alert");

    public static final Icon WEATHER_SHOWERS = new StateIcon("weather-showers");

    public static final Icon WEATHER_SHOWERS_SCATTERED = new StateIcon("weather-showers-scattered");

    public static final Icon WEATHER_SNOW = new StateIcon("weather-snow");

    public static final Icon WEATHER_STORM = new StateIcon("weather-storm");
}
