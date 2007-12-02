#!/usr/bin/perl -w
#
# normalize.pl - sort the aspell dictionary so diffs are clean
#
# Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd 
# 
# The code in this file, and the library it is a part of, are made available
# to you by the authors under the terms of the "GNU General Public Licence,
# version 2". See the LICENCE file for the terms governing usage and
# redistribution.
#

#
# For some reason, each time aspell runs it resorts the .pws personal word list
# that we use as the local dictionary for java-gnome. This makes for really
# stupid diffs each and every bloody time we run aspell on a source file. So,
# instead, normalize the .pws file by simply sorting it.
#
# Run this from the project top level directory.
#
# TODO Perhaps we could figure out what aspell's actual ordering is? Better
# yet, figure out what option to give to aspell to stop it shuffling the word
# list each time aspell runs, then this wouldn't be necessary!
#

use strict;

my $dict_file;
my @words;
my @sorted;
my $count;
my $root;


$root = `bzr root`;
chomp $root;
$dict_file = $root . "/.aspell.en.pws";

open DICT, $dict_file;

@words = <DICT>;

close DICT;

#
# Loose the first line, we can recreate it per the aspell documentation at
# http://aspell.net/man-html/Format-of-the-Personal-and-Replacement-Dictionaries.html
#
shift @words;

# 
# Normalize the list.
#

@sorted = sort @words;

#
# And write it back to the dictionary file
#

open DICT, ">$dict_file";

#
# We used to go to the trouble of accurately listing the word count but this
# proved a conflict *every single time* we merged branches with spell checking.
# So screw it. `aspell` seems to figure it out fine anyway, and the docs say
# its just a hint.
#
#$count = $#words + 1;
#

print DICT "personal_ws-1.1 en 0\n";

foreach my $word ( @sorted ) {
	# didn't chomp
	print DICT "$word";
}

close DICT;

