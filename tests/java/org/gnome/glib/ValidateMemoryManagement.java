/*
 * ValidateMemoryManagement.java
 *
 * Copyright (c) 2007 Vreixo Formoso Lopes and Others
 * 
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" See the LICENCE file for the terms governing usage and
 * redistribution.
 */
package org.gnome.glib;

import java.util.HashSet;

import org.gnome.gtk.Button;
import org.gnome.gtk.TestCaseGtk;
import org.gnome.gtk.VBox;
import org.gnome.gtk.Widget;
import org.gnome.gtk.Window;

/**
 * Validates memory management by checking many possibly conflicting
 * situations. Note that some situations may exist that are not verified
 * because they cannot be automatically checked from the Java side.
 *  
 * FIXME we need a way to check what is being occurred on C side!!
 *  
 * @author Vreixo Formoso
 */
public class ValidateMemoryManagement extends TestCaseGtk
{

    /**
     * Subclass Button to simplify automatic testing
     */
    private static class MyButtonForTesting extends Button implements Button.CLICKED
    {

        /*
         * we need a static field to check object finalization Of course, this
         * not works if many instances are created!!
         */
        static HashSet finalized = new HashSet();

        private int code;
        
        public MyButtonForTesting(String text, int code) {
            super(text);
            this.code = code;
        }
        
        public int getCode() {
            return code;
        }
        
        protected void finalize() {
            System.err.println("finalize() called\t\t" + this.toString());
            System.err.flush();

            /*
             * I prefer having this commented, because it helps ensure
             * that we have a robust design against bad user bad practices
             */
            super.finalize();
            finalized.add(new Integer(code));
        }

        // ignore
        public void onClicked(Button source) {}

    }

    /**
     * Ensures that java object is removed when only created and put out of
     * scope
     */
    public final void testObjectNeverUsedRemoving() {

        MyButtonForTesting b;

        b = new MyButtonForTesting("Live free, die young!! Like me", 1);

        cycleMainLoop();

        /* object gets out of scope */
        b = null;

        cycleMainLoop();

        System.gc();
        try {
            /* this is needed because GC runs asynchronously, I think */
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // just ignore
        }

        /* check if button has been deleted */
        assertTrue("An object created but never used is not deleted", 
                MyButtonForTesting.finalized.contains( new Integer(1) ));
    }

    /**
     * Ensures that java object is not removed when it is still being used on
     * C side
     */
    public final void testObjectNotRemovedWhenStillGtkAlive() {

        MyButtonForTesting b;
        VBox x;

        x = new VBox(false, 3);
        b = new MyButtonForTesting("Old rockers never die!!", 2);

        /* add button to VBox */
        x.add(b);

        cycleMainLoop();

        /* object gets out of scope */
        b = null;

        cycleMainLoop();

        System.gc();
        try {
            /* this is needed because GC runs asynchronously, I think */
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // just ignore
        }

        /* check that button hasn't been deleted */
        assertFalse("Object deleted in Java but still alives in Gtk+", 
                MyButtonForTesting.finalized.contains( new Integer(2) ));
    }

    /**
     * Ensures that java object is removed when it goes out of scope in both
     * java and C side
     */
    public final void testRemovingAfterNoMoreReachableInBothSides() {

        MyButtonForTesting b;
        VBox x;

        x = new VBox(false, 3);
        b = new MyButtonForTesting("Ready to rock", 3);

        /* add button to VBox */
        x.add(b);

        cycleMainLoop();

        /* out of scope in Gtk+ */
        x.remove(b);

        cycleMainLoop();

        /* out of scope in Java */
        b = null;

        cycleMainLoop();

        System.gc();
        try {
            /* this is needed because GC runs asynchronously, I think */
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // just ignore
        }

        /* check that button has been deleted */
        assertTrue("Object no more needed is not deleted", 
                MyButtonForTesting.finalized.contains( new Integer(3) ));
    }

    /**
     * Ensures cyclic references are managed properly
     */
    public final void testTakeCareAboutCyclicReferences() {

        MyButtonForTesting b;
        VBox x;

        x = new VBox(false, 3);
        b = new MyButtonForTesting("I will reference myself", 4);

        /* connect button to itself. cyclic ref! */
        b.connect(b);

        cycleMainLoop();

        /* add button to VBox */
        x.add(b);

        cycleMainLoop();

        /* out of scope in Gtk+ */
        x.remove(b);

        cycleMainLoop();

        /* out of scope in Java */
        b = null;

        cycleMainLoop();

        System.gc();
        try {
            /* this is needed because GC runs asynchronously, I think */
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // just ignore
        }

        /* check that button has been deleted */
        assertTrue("Implementation don't take care about cyclic refences",
                MyButtonForTesting.finalized.contains( new Integer(4) ));
    }

    /**
     * Ensures no denaturation occurs, i.e., object getted from Gtk+
     * if the same class that original object.
     */
    /* 
     * This test is redundant, because we need not only prevent
     * denaturation, but indeed get de _same_ object, which is tested
     * below. Just keep this here to known better what is the problem
     * if any
     */
    public final void testNoDenaturation() { 

        MyButtonForTesting b;
        Window w;

        w = new Window();
        b = new MyButtonForTesting("To be or not to be...", 5);

        /* add button to Window */
        w.add(b);

        cycleMainLoop();

        /* get button from window */
        Widget wd = w.getChild();

        cycleMainLoop();

        assertNotNull("Retrieved a null object", wd);
        assertTrue("No a MyButtonForTesting instance. Denaturation occurs", 
                wd instanceof MyButtonForTesting);

        /* and finally we check object retrieved is the _same_ */
        assertSame("A different object was created", b, wd);
        /* now we delete our refs */
        b = null;
        wd = null;

        cycleMainLoop();

        System.gc();
        try {
            /* this is needed because GC runs asynchronously, I think */
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // just ignore
        }
        
        /* get button again and check */
        wd = w.getChild();
    }

    /**
     * Ensures no new Java object is created when user retrieves from Gtk a
     * previously referenced Widget.
     */
    public final void testNoMultipleCreation() { 

        MyButtonForTesting b;
        Window w;

        w = new Window();
        b = new MyButtonForTesting("I don't wanna be Dolly", 6);

        /* add button to Window */
        w.add(b);

        cycleMainLoop();

        /* get button from window */
        Widget wd = w.getChild();

        cycleMainLoop();

        assertNotNull("Retrieved a null object", wd);
        assertTrue("No a MyButtonForTesting instance", 
                wd instanceof MyButtonForTesting);

        /* and finally we check object retrieved is the _same_ */
        assertSame("A different object was created", b, wd);

        /* now we delete our refs */
        b = null;
        wd = null;

        cycleMainLoop();

        System.gc();
        try {
            /* this is needed because GC runs asynchronously, I think */
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // just ignore
        }
        
        /* get button again and check */
        wd = w.getChild();

        assertNotNull("Retrieved a null object", wd);
        assertTrue("No a MyButtonForTesting instance", 
                wd instanceof MyButtonForTesting);

        assertEquals("A different object was created", 6, 
                ( (MyButtonForTesting)wd ).getCode() );
    }

}
