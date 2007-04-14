#
# things.py
#
# Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
# Copyright (c) 2007 Srichand Pendyala
# 
# The code in this file, and the library it is a part of, are made available
# to you by the authors under the terms of the "GNU General Public Licence,
# version 2". See the LICENCE file for the terms governing usage and
# redistribution.
#
# @author: Andrew Cowie
# @author: Srichand Pendyala
#

class Thing:

    def __init__(self, g_class, j_package, j_class, java, translation, native, jni):
        # the name of the native (C side) type as used throughout the defs data
        # for lookup purposes
        self.g_class = g_class
        
        # which Java package namespace will this Thing be written to?
        self.j_package = j_package
        
        # the name of the Java class, as [will be] generated. Used for the
        # filename for the generated Java and JNI C files, of course.
        self.j_class = j_class

        # information for generating code at each layer
        self.java = java
        self.translation = translation
        self.native = native
        self.jni = jni
        

class ObjectThing(Thing):
    pass

class EnumThing(Thing):
    pass

class FlagsThing(EnumThing):
    pass

class BoxedThing(Thing):
    pass

class FundamentalThing(Thing):
    pass

#
# The master type information database. The keys are the C types.
#
types = dict()

def addThing(t):
    if not t.valid():
        raise Error, "Attempt to add invalid Thing object to types database"
    types[t.g_class] = t

#
# Get the Things object associated with a given C type.
#
def lookupThing(type):
    return types[type]

    