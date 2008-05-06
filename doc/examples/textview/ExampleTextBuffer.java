package textview;

import org.gnome.gdk.Event;
import org.gnome.gtk.*;

public class ExampleTextBuffer implements Button.CLICKED
{

    private TextBuffer buffer;
    private TextTagTable tags;
    private TextTag blueback;
    private TextView view;
    
    private Button blue;
    
    public final static void main(String[] args) {
        Gtk.init(args);
        new ExampleTextBuffer();
        Gtk.main();
    }
    
    public ExampleTextBuffer() {       
        init();
        doLayout();
        
        fillBuffer();
        
    }
    
    private void init() {
        blue = new Button("blue");
        blue.connect((Button.CLICKED)this);
        
        // Define possible tags
        blueback = new TextTag("blueback");
        blueback.setBackground("blue");
        // Build a tag collection
        tags = new TextTagTable();
        tags.add(blueback);
        // Create a buffer with the tag collection
        buffer = new TextBuffer(tags);
        // Create a view
        view = new TextView(buffer);
    }
    
    private void doLayout() {
        VBox layout = new VBox(false, 3);
        HButtonBox buttons = new HButtonBox();
        buttons.add(blue);
        layout.add(buttons);
        layout.add(view);
        
        Window win = new Window();
        win.setTitle("ExampleTextBuffer");
        win.connect(new Window.DELETE_EVENT(){
            public boolean onDeleteEvent(Widget source, Event event) {
                Gtk.mainQuit();
                return true;
            }});
       win.add(layout);
       win.setSizeRequest(300, 200);
       win.showAll();
   }
    
    private void fillBuffer() {
        buffer.setText("Hello\nWorld!");
        
//        TextIter pos1 = buffer.getStartIter();
//        pos1.setLineOffset(6);
//        
//        buffer.applyTag(blueback, pos1, buffer.getEndIter());
    }

    /* (non-Javadoc)
     * @see org.gnome.gtk.Button.CLICKED#onClicked(org.gnome.gtk.Button)
     */
    public void onClicked(Button source) {
        /*
         * Not used since get getSelectionBound() seems broken
        TextMark startMark = buffer.getInsert();
        TextMark endMark   = buffer.getSelectionBound();
        System.out.println("endMark = "+endMark);
        
        TextIter start = buffer.getIterAtMark(startMark);
        TextIter end   = buffer.getIterAtMark(endMark);
         */
        
        TextIter[] selection = buffer.getSelectionBounds();
        TextIter start = selection[0];
        TextIter end   = selection[1];
        System.out.println("Selected: "+start+" to "+end);
       
        if (source==blue) {
            buffer.applyTag(blueback, start, end);
        }
    }
    
    
}
