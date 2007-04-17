#
# generator.py
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

import re
from things import lookupThing

class Generator(object):
    def __init__(self, block):
        self.block = block
    
    def writeJava(self):
        raise RuntimeError, "Java code generation not implemented for this block type"

    def writeC(self):
        raise RuntimeError, "C code generation not implemented for this block type"


# ---------------------------------------------------------
# Type defnitions: Enums, Flags, GObjects
# ---------------------------------------------------------

class TypeGenerator(Generator):
    def writeJava(self):
        _output(_fileHeader(self.block.thing.j_class + ".java"))

    def writeC(self):
        _output(_fileHeader(self.block.thing.j_class + ".c"))


class EnumGenerator(TypeGenerator):
    def writeJava(self):
        TypeGenerator.writeJava(self)

    def writeC(self):
        pass

class FlagsGenerator(TypeGenerator):
    pass

class BoxedGenerator(TypeGenerator):
    pass

class ObjectGenerator(TypeGenerator):
    def writeJava(self):
        TypeGenerator.writeJava(self)

        _output(\
"""
package %(j_package)s;

import org.gnome.glib.Plumbing;

final class %(j_class)s extends Plumbing
{
    private %(j_class)s() { }
""" % vars(self.block.thing))


    def writeC(self):
        TypeGenerator.writeC(self)
        
        _output(\
"""
#include <jni.h>
#include <gtk/gtk.h>
#include "bindings_java.h"
#include "%(header)s.h"

""" % { 'header': _encodeJniClassName(self.block.thing) })




# ---------------------------------------------------------
# Functions, constructors, methods, and virtuals
# ---------------------------------------------------------

class FunctionGenerator(Generator):
    pass

class ConstructorGenerator(FunctionGenerator):
    pass

class MethodGenerator(FunctionGenerator):
    def writeJava(self):
        
        #
        # First the method declaration
        #
        
        java_return = lookupThing(self.block.g_return_type).java
        java_method = _toCamel(self.block.py_function_name)
        java_args = ""
        
        translation_return = ""
        translation_args = ""
        
        native_return = lookupThing(self.block.g_return_type).native
        native_method = self.block.c_function_name
        native_args = ""
        
        # if a second or subsequent argument add the comma which is the
        # argument separator at all layers
        subsequent = False
        
        for arg_pair in [ ('GtkButton*', 'self' )] + self.block.g_parameters:
            (g_type, local_name) = arg_pair
            t = lookupThing(g_type)

            if subsequent:
                java_args += ", "
                translation_args += ", "
                native_args += ", "
            
            java_args += t.java + " " + local_name
            
            if (t.translation == ''):
                translation_args += local_name
            else:
                translation_args += t.translation + "(" + local_name + ")"

            native_args += t.native + " " + local_name
            
            subsequent = True

        _output(\
"""

    static final %(java_return)s %(java_method)s(%(java_args)s) {
""" % vars())

        #
        # Declare translation variables as necessary
        #

        if self.block.g_return_type != 'void':
            return_type = lookupThing(self.block.g_return_type).native
            translation_return = "result = "
            _output(\
"""
        %(return_type)s result;

""" % vars())


        #
        # Now the call to the native method
        #

        native_method = self.block.c_function_name

        _output(\
"""
        %(translation_return)s%(native_method)s(%(translation_args)s);
""" % vars())

        return_arg = ""
        if self.block.g_return_type != 'void':
            t = lookupThing(self.block.g_return_type)
            if (t.translation == ''):
                return_arg += 'result'
            else:
                return_arg += t.translation + "(result)"

            _output(\
"""

        return %(return_arg)s;
""" % vars())
            

        _output(\
"""
    }

    private static native final %(native_return)s %(native_method)s(%(native_args)s);
""" % vars())



class VirtualGenerator(FunctionGenerator):
    pass
    

#
# Trim a leading or trailing newline (which come from the way we're using
# multiline strings as if they were here docs), and output the result. TODO,
# change to using target File pointer.
#
def _output(str):
    if str[0] == '\n':
        str = str[1:]
    if str[-1] == '\n':
        str = str[:-1]
    print str
    

#
# Output the standard header and warning that the file is generated so that
# people don't try to edit these files.
#
def _fileHeader(filename):
    return \
"""
/*
 * %(filename)s
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd
 *
 * The code in this file, and the library it is a part of, are made available
 * to you by the authors under the terms of the "GNU General Public Licence,
 * version 2" plus the "Classpath Exception" (you may link to this code as a
 * library into other programs provided you don't make a derivation of it).
 * See the LICENCE file for the terms governing usage and redistribution.
 *
 *                      THIS FILE IS GENERATED CODE!
 *
 * To modify its contents or behaviour, either update the generation program,
 * change the information in the source defs file, or implement an override for
 * this class.
 */
""" % vars()

#
# The defs data has the names of methods as 'set_label'; this function
# converts them to Java camelCase.
#

def _toCamel(var):
    words = var.split("_")
    camel = words.pop(0)
    while ( words ):
        word = words.pop(0)
        camel += word.capitalize()
    return camel


def _encodeJniClassName(thing):
    fqcn = thing.j_package + "." + thing.j_class
    return re.sub("\.", "_", fqcn)


def _encodeJniMethodName(thing):
    # TODO
    pass

if __name__ == '__main__':
    pass
