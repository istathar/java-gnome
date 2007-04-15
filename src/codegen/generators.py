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
        print _fileHeader(self.block.thing.j_class + ".java")
    
    def writeC(self):
        print _fileHeader(self.block.thing.j_class + ".c")


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

        print """
package %(j_package)s;

import org.gnome.glib.Plumbing;

final class %(j_class)s extends Plumbing
{
    private %(j_class)s() { }""" % vars(self.block.thing)


    def writeC(self):
        TypeGenerator.writeC(self)
        
        print """
#include <jni.h>
#include <gtk/gtk.h>
#include "bindings_java.h"
#include "%(header)s.h"

""" % { 'header': _encodeJniClassName(self.block.thing) }




# ---------------------------------------------------------
# Functions, constructors, methods, and virtuals
# ---------------------------------------------------------

class FunctionGenerator(Generator):
    pass

class ConstructorGenerator(FunctionGenerator):
    pass

class MethodGenerator(FunctionGenerator):
    def writeJava(self):
        java_return = lookupThing(self.block.g_return_type).java
        java_method = _toCamel(self.block.py_function_name)
        java_args = ""
        
        for arg_pair in self.block.g_parameters:
            (g_type, name) = arg_pair
            java_args += lookupThing(g_type).java
            java_args += " " + name

        print """
    static final %(java_return)s %(java_method)s(%(java_args)s) {""" % vars()

class VirtualGenerator(FunctionGenerator):
    pass
    


def output(args, str):
    print str % args
    

#
# Output the standard header and warning that the file is generated so that
# people don't try to edit these files.
#
def _fileHeader(filename):
    return """/*
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
