#
# generator.py
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

from things import lookupThing
import re

class Generator(object):
    def __init__(self, block):
        self.block = block
    
    def writeJava(self):
        raise Error, "Java code generation not implemented for this block type"

    def writeC(self):
        raise Error, "C code generation not implemented for this block type"


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
    private %(j_class)s() { }
""" % vars(self.block.thing)


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
    pass

class VirtualGenerator(FunctionGenerator):
    pass
    



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


def _encodeJniClassName(thing):
    fqcn = thing.j_package + "." + thing.j_class
    return re.sub("\.", "_", fqcn)


def _encodeJniMethodName(thing):
    # TODO
    pass

if __name__ == '__main__':
    pass