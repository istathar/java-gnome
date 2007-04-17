#
# things.py
#
# Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd, and Others
# 
# The code in this file, and the library it is a part of, are made available
# to you by the authors under the terms of the "GNU General Public Licence,
# version 2". See the LICENCE file for the terms governing usage and
# redistribution.
#
# @author: Andrew Cowie
# @author: Srichand Pendyala
#
# There are already existing "Type" objects in Python's internals, resulting
# in horribly ugly conflict errors. So we use a hierarchy of Things instead.
# When you read "thing", think "type"
#

class Thing:

    def __init__(self, g_type, j_package, j_class, java, translation, native, jni):
        # the name of the native (C side) type as used throughout the defs data
        # for lookup purposes
        self.g_type = g_type
        
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
    
    def valid(self):
        return True;


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

_things = dict()

def addThing(t):
    if not t.valid():
        raise RuntimeError, "Attempt to add invalid Thing object to types database"
    _things[t.g_type] = t

#
# Get the Things object associated with a given C type.
#
def lookupThing(g_type):
    try: 
        return _things[g_type]
    except KeyError:
        raise KeyError, "Type " + g_type + " not registered"
        

# ---------------------------------------------------------
# Register basic Things corresponding to primative types
# ---------------------------------------------------------

addThing(FundamentalThing('void',
                          '',
                          '',
                          'void',
                          '',
                          'void',
                          'void'))

addThing(FundamentalThing('const-gchar*',
                          '',
                          '',
                          'String',
                          '',
                          'String',
                          'jstring'))

# TODO and all the rest to follow here...

