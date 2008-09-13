#! /usr/bin/perl -w
#
# coverage.pl, a quick first order approximation of the extend of the coverage
# provided by java-gnome. This works releative to the project top level
# directory, so you need to invoke it as [./]src/util/coverage.pl
#
# Copyright (c) 2008 Operational Dynamics Consulting Pty Ltd 
# 
# The code in this file, and the library it is a part of, are made available
# to you by the authors under the terms of the "GNU General Public Licence,
# version 2". See the LICENCE file for the terms governing usage and
# redistribution.
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
				next;
			}
			$num++;
		}
	}

	close JAVA;
}

print "Number methods generated:\t";
printf "%5d\n", $num;

print "Number that are relevant:\t 1600?\n";

@sources = split(/\n/, `find src/bindings/ -name '*.java'`);
$num = 0;

foreach my $file ( @sources ) {
	open JAVA, $file;

	
	while (<JAVA>) {
		if (/^    public /) {
			if (/^    public void connect/) {
				next;
			}
			$num++;
		}
	}

	close JAVA;
}

print "java-gnome public API:\t\t";
printf "%5d\n", $num;


