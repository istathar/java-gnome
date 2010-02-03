#! /usr/bin/perl -w
#
# coverage.pl, a quick first order approximation of the extend of the coverage
# provided by java-gnome. This works releative to the project top level
# directory, so you need to invoke it as [./]src/util/coverage.pl
#
# Copyright © 2008-2010 Operational Dynamics Consulting, Pty Ltd
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

use strict;

print "Coverage\n";
print "========\n";
print "\n";
print "Functions, methods, and virtuals\n";
print "--------------------------------\n";
print "\n";

my $num;
my ( @defs, @sources );

$num = 0;

print "Size of imported pygtk data:\t";
printf "%5d\n", 5468; 


@defs = glob "src/defs/*.defs src/util/missing.defs";

foreach my $file ( @defs ) {
	open DEFS, $file;

	while (<DEFS>) {
		if ((/^\(define-method/) ||
		    (/^\(define-function/) ||
		    (/^\(define-virtual/)) {
			$num++;
		}
	}

	close DEFS;
}

print "Current size of .defs data:\t";
printf "%5d\n", $num;

@sources = split(/\n/, `find generated/bindings/ -name '*.java'`);
$num = 0;

foreach my $file ( @sources ) {
	open JAVA, $file;

	while (<JAVA>) {
		if (/^    private static native /) {
			if (/get_ordinal_/) {
				# skip flags
				next;
			}
			$num++;
		} elsif (/^    protected static final void /) {
			# receiver
			$num++;
		}
	}

	close JAVA;
}

print "Number generated:\t\t";
printf "%5d\n", $num;

print "Number actually relevant:\t 1800?\n";

@sources = split(/\n/, `find src/bindings/ -name '*.java'`);
$num = 0;

foreach my $file ( @sources ) {
	open JAVA, $file;

	while (<JAVA>) {
		if (/^    public /) {
			if (/^    public interface/) {
				next;
			}
			$num++;
		}
	}

	close JAVA;
}

print "java-gnome public API:\t\t";
printf "%5d\n", $num;


