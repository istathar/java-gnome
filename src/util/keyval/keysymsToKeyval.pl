#!/usr/bin/perl -s
#
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
