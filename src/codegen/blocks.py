#
# blocks.py
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

from generators import *

class Block:
    def __init__(self):
        self.g_class = None
     
    def generator(self):
        raise RuntimeError, "Code generation not implemented for this block type"


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
    def __init__(self, thing, py_function_name, g_return_type, c_function_name, g_parameters):
        self.thing = thing
        self.py_function_name = py_function_name
        self.g_return_type = g_return_type
        self.c_function_name = c_function_name
        self.g_parameters = g_parameters


class ConstructorBlock(FunctionBlock):
    def __init__(self, thing, py_function_name, g_return_type, c_function_name, g_parameters):
        
        py_constructor_name = re.sub("gtk_button_", "", py_function_name)
        py_constructor_name = re.sub("new", "create_button", py_constructor_name)
        FunctionBlock.__init__(self, thing, py_constructor_name, thing.g_type, c_function_name, g_parameters)        
    
    def generator(self):
        return ConstructorGenerator(self)

class MethodBlock(FunctionBlock):
    def generator(self):
        return MethodGenerator(self)

class VirtualBlock(FunctionBlock):
    pass

