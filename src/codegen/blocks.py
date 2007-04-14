#
# blocks.py
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

from generators import ObjectGenerator

class Block:
    def __init__(self):
        self.g_class = None
     
    def generator(self):
        raise Error, "Code generation not implemented for this block type"


# ---------------------------------------------------------
# Type defnitions: Enums, Flags, GObjects
# ---------------------------------------------------------

class TypeBlock:
    def __init__(self, thing):
        self.thing = thing
    


class EnumBlock(TypeBlock):
    pass

class FlagsBlock(TypeBlock):
    pass

class BoxedBlock(TypeBlock):
    pass

class ObjectBlock(TypeBlock):
    def generator(self):
        return ObjectGenerator(self)


# ---------------------------------------------------------
# Functions, constructors, methods, and virtuals
# ---------------------------------------------------------

class FunctionBlock(Block):
    pass

class ConstructorBlock(FunctionBlock):
    pass

class MethodBlock(FunctionBlock):
    pass

class VirtualBlock(FunctionBlock):
    pass
    


    