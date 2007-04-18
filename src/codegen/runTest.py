#! /usr/bin/env python

#
# Test harness: evaluate the intermediate data forms, and feed to
# the code generator.
#

from things import *
from blocks import *

if __name__ == '__main__':
    button = ObjectThing("GtkButton*",
                         "org.gnome.gtk",
                         "GtkButton",
                         "Button",
                         "pointerOf",
                         "long",
                         "jlong")
    addThing(button)
    
    
    block1 = ObjectBlock(button)
    
    gen1 = block1.generator()
    gen1.writeJava()

    block2 = MethodBlock(button,
                        "set_label",
                        "void",
                        "gtk_button_set_label",
                        [ ('GtkButton*', 'self' ), ( "const-gchar*", "label" ) ])

    block3 = MethodBlock(button,
                        "get_label",
                        "GtkButton*",
                        "gtk_button_get_label",
                        [ ('GtkButton*', 'self' ) ])

    gen2 = block2.generator()
    gen2.writeJava()
    
    gen3 = block3.generator()
    gen3.writeJava()

    # FIXME
    print "}\n"

    gen1.writeC()
    gen2.writeC()
    gen3.writeC()
