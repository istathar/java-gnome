package org.gnome.gtk;

public class ValidateNotebook extends TestCaseGtk
{
    public void testSwitchPage() {
        final Notebook notebook = new Notebook();

        notebook.connect(new Notebook.SwitchPage() {
            public void onSwitchPage(Notebook source, int pageNum) {}
        });
    }
}
