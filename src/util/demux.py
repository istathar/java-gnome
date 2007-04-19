#! /usr/bin/env python
#
# demux.py
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
# @author: Andrew Cowie
#

import sys
import definitions
import defsparser

def main(argv):
    defines = {}

    p = defsparser.DefsParser(argv[1], defines)

    p.startParsing()

    p.writeDefs()

if __name__ == '__main__':
    sys.exit(main(sys.argv))
