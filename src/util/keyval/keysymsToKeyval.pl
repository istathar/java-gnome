#!/usr/bin/perl -s
#
# Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
# 
# The code in this file, and the library it is a part of, are made available
# to you by the authors under the terms of the "GNU General Public Licence,
# version 2". See the LICENCE file for the terms governing usage and
# redistribution.
#
# Output constant object lines corresonsing to the keyvals as found in
# gtk+'s gdk/gdkkeysyms.h
#
# It would probably be better if this output (defs-flags ...) data
#

use strict;

while (<>) {
	my $name;
	my $val;

	if (!(s/^#define GDK_//)) {
		next;
	}
	chomp;
	if (!(/(.*) (0x[\d\w]+)/)) {
		next;
	}

	$name = $1;
	$val = $2;

	$name =~ s/_//g;
	
	print "     public static final Keyval $name = new Keyval($val, \"$name\");\n";
}
	
#define GDK_VoidSymbol 0xffffff
