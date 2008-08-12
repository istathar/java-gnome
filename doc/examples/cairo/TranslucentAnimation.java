/*
 * TranslucentAnimation.java
 *
 * Copyright (c) 2008 Zak Fenton
 * 
 * The code in this file, and the program it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package cairo;


import org.gnome.gtk.*;
import org.gnome.gdk.EventExpose;
import org.gnome.gdk.Event;
import org.freedesktop.cairo.*;

/** 
 * Example using Cairo and Gtk to create a translucent top-level window that
 * contains animated graphics.
 *
 * @author Zak Fenton
 */
public class TranslucentAnimation {
	// Animation frame counter
	static long count; 
	
	public static void main(String[] args) {
		Gtk.init(args);
		
		final Window w = new Window();
		w.setDefaultSize(100, 100);
		
		w.setTooltipMarkup("<i>Hint:</i> use setDecorated to remove the window frame");
		//w.setDecorated(false);
		
		// Enable per-pixel alpha channel
		w.setColormap(w.getScreen().getRgbaColormap());
		
		// Thread that updates count 20 times per second and redraws the window
		final Thread anim = new Thread() {
			public void run () {
				while (true) {
					try {
						sleep(50);
					} catch (InterruptedException e) {
					}
					
					count++;
					w.queueDraw(); 
				}
			}
		};
		
		// Expose event handler - repaints the window contents
		Widget.ExposeEvent painter = new Widget.ExposeEvent() {
			public boolean onExposeEvent (Widget w, EventExpose e) {
				Context cr = new Context(w.getWindow());
				
				// Clear the background
				cr.setOperator(Operator.CLEAR);
				cr.paint();
				cr.setOperator(Operator.DEFAULT);
				
				// Rotate around the centre of the semi-circle
				cr.translate(50,50);
				cr.rotate(count*Math.PI/15);
				
				// The semi-circle (remember, rotations are in radians - so
				// a full circle is 2*PI, half a circle is PI)
				cr.arc(0, 0, 40, 0, Math.PI);
				
				// Fill the semi-circle see-thru red
				cr.setSourceRGBA(1, 0, 0, 0.8);
				cr.fillPreserve();
				
				// And outline it blue
				cr.setSourceRGB(0, 0, 1);
				cr.stroke();
				
				// Little green circle in the centre
				cr.arc(0,0,10,0,2*Math.PI);
				cr.setSourceRGBA(0, 1, 0, 0.8);
				cr.fill();
				
				return true;
			}
		};
		w.connect(painter);
		
		// Stop Gtk and exit on window close
		Window.DeleteEvent del = new Window.DeleteEvent() {
			public boolean onDeleteEvent(Widget source, Event event) {
				Gtk.mainQuit();
				System.exit(0);
				return false;
			}
		};
		w.connect(del);
		
		w.show();
		
		anim.start();
		
		Gtk.main();
	}

}
