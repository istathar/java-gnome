#!/usr/bin/perl -w
#
# codegen.pl
#
# Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd and Others
# See LICENCE file for terms governing usage and redistribution
#
# Generate java-gnome bindings from defs files!
#
# This file is a small execution wrapper around DefsParser. Given a .defs file
# it reads it and as complete define blocks are encountered it carries out the
# necessary actions to generate the .java and .c files.
#

use strict;
use DefsParser;

# main

#*OUTPUT = *STDOUT;

process("GtkButton.defs");

#process("gtk-base-types.defs");
#process("gtk-base.defs");

finalize();

DefsParser::dump();

