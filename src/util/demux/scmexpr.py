#
# scmexpr.py
#
# Copyright (c) 1998-2006 James Henstridge, John Finlay, and Others
# Copyright (c) 2007-     Operational Dynamics Consulting Pty Ltd
# 
# The code in this file, and the library it is a part of, are made available
# to you by the authors under the terms of the "GNU General Public Licence,
# version 2". See the LICENCE file for the terms governing usage and
# redistribution.
#
# This code imported from pygtk, the Python language bindings for GTK and
# GNOME, then stripped down to the task of round-tripping .defs data from
# a single stream of data into individual files.
#

from __future__ import generators

import string
from cStringIO import StringIO

class error(Exception):
    def __init__(self, filename, lineno, msg):
        Exception.__init__(self, msg)
        self.filename = filename
        self.lineno = lineno
        self.msg = msg
    def __str__(self):
        return '%s:%d: error: %s' % (self.filename, self.lineno, self.msg)

trans = [' '] * 256
for i in range(256):
    if chr(i) in string.letters + string.digits + '_':
        trans[i] = chr(i)
    else:
        trans[i] = '_'
trans = string.join(trans, '')

def parse(filename):
    if isinstance(filename, str):
        fp = open(filename, 'r')
    else: # if not string, assume it is some kind of iterator
        fp = filename
        filename = getattr(fp, 'name', '<unknown>')
    whitespace = ' \t\n\r\x0b\x0c'
    nonsymbol = whitespace + '();\'"'
    stack = []
    openlines = []
    lineno = 0
    for line in fp:
        pos = 0
        lineno += 1
        while pos < len(line):
            if line[pos] in whitespace: # ignore whitespace
                pass
            elif line[pos] == ';': # comment
                break
            elif line[pos:pos+2] == "'(":
                pass # the open parenthesis will be handled next iteration
            elif line[pos] == '(':
                stack.append(())
                openlines.append(lineno)
            elif line[pos] == ')':
                if len(stack) == 0:
                    raise error(filename, lineno, 'close parenthesis found when none open')
                closed = stack[-1]
                del stack[-1]
                del openlines[-1]
                if stack:
                    stack[-1] += (closed,)
                else:
                    yield closed
            elif line[pos] == '"': # quoted string
                if not stack:
                    raise error(filename, lineno,
                                'string found outside of s-expression')
                endpos = pos + 1
                chars = []
                while endpos < len(line):
                    if endpos+1 < len(line) and line[endpos] == '\\':
                        endpos += 1
                        if line[endpos] == 'n':
                            chars.append('\n')
                        elif line[endpos] == 'r':
                            chars.append('\r')
                        elif line[endpos] == 't':
                            chars.append('\t')
                        else:
                            chars.append('\\')
                            chars.append(line[endpos])
                    elif line[endpos] == '"':
                        break
                    else:
                        chars.append(line[endpos])
                    endpos += 1
                if endpos >= len(line):
                    raise error(filename, lineno, "unclosed quoted string")
                pos = endpos
                stack[-1] += (''.join(chars),)
            else: # symbol/number
                if not stack:
                    raise error(filename, lineno,
                                'identifier found outside of s-expression')
                endpos = pos
                while endpos < len(line) and line[endpos] not in nonsymbol:
                    endpos += 1
                symbol = line[pos:endpos]
                pos = max(pos, endpos-1)
                try: symbol = int(symbol)
                except ValueError:
                    try: symbol = float(symbol)
                    except ValueError: pass
                stack[-1] += (symbol,)
            pos += 1
    if len(stack) != 0:
        msg = '%d unclosed parentheses found at end of ' \
              'file (opened on line(s) %s)' % (len(stack),
                                               ', '.join(map(str, openlines)))
        raise error(filename, lineno, msg)

class Parser:
    def __init__(self, filename):
        """Argument is either a string, a parse tree, or file object"""
        self.filename = filename
    def startParsing(self, filename=None):
        statements = parse(filename or self.filename)
        for statement in statements:
            self.handle(statement)
    def handle(self, tup):
        cmd = string.translate(tup[0], trans)
        if hasattr(self, cmd):
            getattr(self, cmd)(*tup[1:])
        else:
            self.unknown(tup)
    def unknown(self, tup):
        pass


