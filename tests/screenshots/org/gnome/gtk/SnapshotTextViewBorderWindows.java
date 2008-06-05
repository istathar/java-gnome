/*
 * SnapshotTreeStore.java
 *
 * Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.gtk;

import static org.gnome.gtk.PolicyType.ALWAYS;
import static org.gnome.gtk.TextWindowType.BOTTOM;
import static org.gnome.gtk.TextWindowType.LEFT;
import static org.gnome.gtk.TextWindowType.RIGHT;
import static org.gnome.gtk.TextWindowType.TOP;
import static org.gnome.gtk.WrapMode.WORD;

/**
 * @author Andrew Cowie
 */
public class SnapshotTextViewBorderWindows extends Snapshot
{
    /*
     * This text created using the generator at http://www.lipsum.com/
     */
    private static String lorem = "Lorem ipsum dolor sit amet, consectetuer "
            + "adipiscing elit. Proin adipiscing locus est. Curabitur vel "
            + "odio. Morbi ipsum sem, viverra eget, tristique tincidunt, "
            + "mollis vestibulum, nulla. Suspendisse blandit. Fuse bibendum "
            + "neque at est. Mauris eget turpis. Sed erat. Class aptent "
            + "taciti sociosqu ad litora torquent per conubia nostra, per "
            + "inceptos himenaeos. Phasellus in diam. Donec felis. "
            + "Pellentesque libero libero, interdum et, accumsan quis, "
            + "interdum quis, odio. Vestibulum nec lacus. Etiam id lacus. "
            + "Curabitur ornare, felis sit amet auctor iaculis, metus eros "
            + "feugiat nisl, sed adipiscing nisl urna nec odio. Nam magna. "
            + "Pellentesque sed ante non massa pulvinar dapibus. Suspendisse "
            + "at sapien. Sed lobortis, nisi non tempor consequat, nisl ante "
            + "lobortis dolor, ut hendrerit tortor quam id augue.\nPhasellus "
            + "mollis, ante quis tincidunt rutrum, augue nisl malesuada "
            + "ipsum, sit amet condimentum dolor felis nec lorem. Nulla "
            + "ante. Etiam vulputate lorem quis orci. Duis mattis odio sit "
            + "amet erat. Maecenas eu sem et nunc mollis dapibus. Quisque a "
            + "dui. Pellentesque a arcu in eros pellentesque iaculis. "
            + "Vestibulum ante ipsum primis in faucibus orci luctus et "
            + "ultrices posuere cubilia Curae; Cras nulla pede, consequat "
            + "quis, tincidunt eget, fermentum ac, sapien. Integer nec mi. "
            + "Sed elementum iaculis justo. Suspendisse arcu arcu, facilisis "
            + "quis, viverra aliquam, dapibus quis, magna. Nulla facilisi. "
            + "Quisque dui ipsum, eleifend ut, ultrices vel, aliquet ac, "
            + "turpis. Pellentesque volutpat lorem eget pede. Proin dapibus, "
            + "ornare suscipit, magna nunc pharetra lorem, eget ornare velit "
            + "sem vel tortor. Ut dolor. Fusce tincidunt.\nCurabitur "
            + "viverra. Proin lectus nunc, elementum a, consequat vel, "
            + "interdum non, mauris. Cras diam. Maecenas magna augue, "
            + "tristique sed, mollis quis, tincidunt at, ante. Cras in metus "
            + "eget tortor adipiscing egestas. In aliquet massa sollicitudin "
            + "quam. Cras arcu. Vestibulum lorem felis, vestibulum laoreet, "
            + "convallis in, suscipit eu, felis. Integer tincidunt. Nullam  "
            + "tempor. Phasellus vel lorem. Suspendisse congue odio sit amet "
            + "ligula. Donec rhoncus eleifend nisl. Fusce congue nunc quis "
            + "lectus. Quisque vel nisl ornare magna interdum convallis.\nIn "
            + "tincidunt nunc a leo. In id sem vitae lectus feugiat veh. "
            + "Donec nisl justo, aliquet lobortis, dignissim non, sagittis "
            + "eget, orci. Integer iaculis sapien vel diam sollicitudin "
            + "facilisis. Morbi varius eros quis justo. Vivamus id leo eget "
            + "metus accumsan dignissim. Nullam a est vitae erat elementum "
            + "bibendum. Suspendisse a ante at nulla aliquet egestas. Donec "
            + "ante lectus, ullamcorper id, egestas et, ultricies lacinia, "
            + "nibh, non tincidunt nibh purus sit amet dui. Congue dictum "
            + "augue. Donec sit amet velit quis augue aliquam pulvinar.\n"
            + "Etiam rutrum velit in nunc porta auctor. Nullam fringilla "
            + "velit volutpat felis. Etiam vestibulum condimentum augue. "
            + "In ante quis neque laoreet pulvinar. Integer pharetra ligula "
            + "eget magna. Morbi condimentum lectus commodo nunc. Sed "
            + "suscipit, elit quis condimentum malesuada, erat arcu pulvinar "
            + "tortor, vitae rhoncus justo est ut ipsum. Sed sagittis mi sit amet mauris.\n";

    public SnapshotTextViewBorderWindows() {
        super(TextView.class, "BorderWindows");
        final TextTagTable table;
        final TextView view;
        final TextBuffer buffer;
        final Label left2, right, top, bottom;
        final Box left;
        final ScrolledWindow scroll;

        table = new TextTagTable();
        buffer = new TextBuffer(table);
        view = new TextView(buffer);

        left2 = new Label("LEFT");
        left = new HBox(true, 0);
        left.packStart(left2);

        right = new Label("RIGHT");
        top = new Label("TOP");
        bottom = new Label("BOTTOM");

        view.setBorderWindowSize(LEFT, 50);
        view.addChildInWindow(left, TextWindowType.LEFT, 0, 0);

        view.setBorderWindowSize(RIGHT, 50);
        view.addChildInWindow(right, TextWindowType.RIGHT, 0, 0);

        view.setBorderWindowSize(TOP, 50);
        view.addChildInWindow(top, TextWindowType.TOP, 0, 0);

        view.setBorderWindowSize(BOTTOM, 50);
        view.addChildInWindow(bottom, TextWindowType.BOTTOM, 0, 0);

        view.setWrapMode(WORD);
        scroll = new ScrolledWindow();
        scroll.setPolicy(ALWAYS, ALWAYS);
        scroll.add(view);

        buffer.insertAtCursor(lorem);

        window = new Window();
        window.add(scroll);
        window.setDecorated(false);
        window.setBorderWidth(2);
        window.setDefaultSize(400, 300);
    }

    public static void main(String[] args) {
        Gtk.init(args);
        runExample(new SnapshotTextViewBorderWindows());
        Gtk.main();
    }
}
