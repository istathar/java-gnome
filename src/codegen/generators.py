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
        
        for arg_pair in self.block.g_parameters:
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

        #
        # And return the value, translating if necessary. Obviously
        # This is just a pass through unless it is a
        # org.bindings.freedesktop.Proxy or Constant, in which case
        # the appropriate instanceFor() needs to be called, as stored
        # in Thing.translation for that type.
        #

        return_arg = ""
        if self.block.g_return_type != 'void':
            t = lookupThing(self.block.g_return_type)
            if (t.translation == ''):
                return_arg += 'result'
            else:
                # FIXME
                return_arg += "instanceFor" + "(result)"

            _output(\
"""

        return %(return_arg)s;
""" % vars())
            
        #
        # finally, close the translation method and declare the native
        # method stub.
        #

        _output(\
"""
    }

    private static native final %(native_return)s %(native_method)s(%(native_args)s);
""" % vars())

        #
        # done!
        #
    
    
    
#
# Output the JNI function implementing native method.
#
# At the cost of some redundency, we spit out the signature of the public API
# call and the boundary crossing call to make it easier to locate methods in
# the generated code (since munged JNI names are a pain in the ass to read).
#
    
    def writeC(self):        
        #
        # First the function declaration
        #
        
        jni_return = lookupThing(self.block.g_return_type).jni
        jni_class = _encodeJniClassName(self.block.thing)
        jni_method = _encodeJniMethodName(self.block)
        jni_args = "JNIEnv *env, jclass cls"

        return_error = ""

        conversion_return = ""
        conversion_declaration = ""
        conversion_code = ""

        c_return = self.block.g_return_type
        c_function = self.block.c_function_name
        c_args = ""

        subsequent = False

        for arg_pair in self.block.g_parameters:
            (g_type, local_name) = arg_pair
            t = lookupThing(g_type)

            jni_args += ", "
            if subsequent:
                c_args += ", "

            jni_args += t.jni + " _" + local_name
            c_args += local_name

            subsequent = True

        jni_args = re.sub(", ", ",\n        ", jni_args)
        _output(\
"""
JNIEXPORT %(jni_return)s JNICALL
Java_%(jni_class)s_%(jni_method)s
(
        %(jni_args)s
)
{
""" % vars())

        #
        # Declare translation variables as necessary
        #
        for arg_pair in self.block.g_parameters:
            (g_type, local_name) = arg_pair
            c_type = _typeCast(lookupThing(g_type))
            _output(\
"""
        %(c_type)s %(local_name)s;
""" % vars())

        if self.block.g_return_type != 'void':
            c_type = _typeCast(lookupThing(self.block.g_return_type))
            _output(\
"""
        %(c_type)s result;
""" % vars())
            conversion_return = "result = "

        if jni_return == 'void':
            pass
        elif jni_return == 'jboolean':
            return_error = " JNI_FALSE"        
        elif jni_return == 'jint':
            return_error = " 0"
        elif jni_return == 'jlong':
            return_error = " 0L"
        else:
            return_error = " NULL"

        #
        # convert (cast, decode, etc) types as necessary
        #

        for arg_pair in self.block.g_parameters:
            (g_type, local_name) = arg_pair
            _output(\
"""

        // convert argument %(local_name)s
""" % vars())
            if g_type == 'gchar*' or g_type == 'const-gchar*':
                c_type = _typeCast(t)

                _output(\
"""
        %(local_name)s = (%(c_type)s) (env*)->GetStringUTFChars(env, _%(local_name)s, NULL);
        if (%(local_name)s == NULL) {
                return%(return_error)s; // OutOfMemoryError already thrown
        }
""" % vars())

            else:
                _output(\
"""
        %(local_name)s = (%(g_type)s) _%(local_name)s;
""" % vars())

        _output("\n\n");


        #
        # Now the call to the native method
        #

        c_method = self.block.c_function_name

        _output(\
"""
        // call function
        %(conversion_return)s%(c_method)s(%(c_args)s);
""" % vars())

        #
        # cleanup
        #

        for arg_pair in self.block.g_parameters:
            (g_type, local_name) = arg_pair
            _output(\
"""

        // cleanup argument %(local_name)s
""" % vars())
            if g_type == 'gchar*' or g_type == 'const-gchar*':

                _output(\
"""
        (env*)->ReleaseStringUTFChars(env, _%(local_name)s, %(local_name)s);
""" % vars())



        #
        # And return the value, translating as necessary for strings
        #

        if self.block.g_return_type != 'void':
            t = lookupThing(self.block.g_return_type)
            jni_type = t.jni
            
            _output(\
"""

        // return result
""")
            c_type = _typeCast(t)

            if t.g_type == 'gchar*' or t.g_type == 'const-gchar*':
                _output(\
"""
        return (jstring) (*env)->NewStringUTF(env, result);
""" % vars())
            else:
                _output(\
"""
        return (%(jni_return)s) result;
""" % vars())


        #
        # finally, close the JNI function
        #

        _output(\
"""
}

""")

        #
        # done!
        #
        

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


def _encodeJniMethodName(block):
    return re.sub("_", "_1", block.c_function_name)
    

def _typeCast(thing):
    if thing.g_type == 'const-gchar*':
        return'const gchar*'
    else:
        return thing.g_type

