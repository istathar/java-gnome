#! /usr/bin/env python
# vim: set fileencoding=UTF-8
#
# demux.py
#
# Copyright © 1998-2006 James Henstridge, John Finlay and Others
# Copyright © 2007-2010 Operational Dynamics Consulting, Pty Ltd
# 
# The code in this file, and the program it is a part of, is made available
# to you by its authors as open source software: you can redistribute it
# and/or modify it under the terms of the GNU General Public License version
# 2 ("GPL") as published by the Free Software Foundation.
#
# This program is distributed in the hope that it will be useful, but WITHOUT
# ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
# FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.
#
# You should have received a copy of the GPL along with this program. If not,
# see http://www.gnu.org/licenses/. The authors of this program may be
# contacted through http://java-gnome.sourceforge.net/.
#

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
