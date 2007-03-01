#!/usr/bin/perl -w
#
# Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd
# Proprietary - not for release without written authorization
#

package DefsParser;

use strict;
use Exporter;
our @ISA = qw(Exporter);
our @EXPORT = qw(&process &finalize);

use Data::Dumper;		# For debug use only
$Data::Dumper::Indent = 1;


#
# information about G types and how they map to types in our public API, as
# mapped to primitives for the native call, and as defined in JNI. Also present
# is metadata about what sort of type this is.
#

my %types;

#
# First is the {'java'} subhash, which contains the mapping of native types to
# "public API" aka Java types. For example, "GtkButton" maps to "Button" and
# "gint" maps to "int". The fundamental types are hard coded here
#
# Next is {'native'} mapping public API types to native primatives.
#
# {'jni'} subhash maps native types to JNI primatives. Actually, this is the
# type that will correspond to the Java native declaration, which we encode to
# primatives.
#


$types{'none'}{'phylum'} = "fundamental";
$types{'none'}{'java'} = "void";
$types{'none'}{'translation'} = "";
$types{'none'}{'native'} = "void";
$types{'none'}{'jni'} = "void";

$types{'gfloat'}{'phylum'} = "fundamental";
$types{'gfloat'}{'java'} = "float";
$types{'gfloat'}{'translation'} = "";
$types{'gfloat'}{'native'} = "float";
$types{'gfloat'}{'jni'} = "jfloat";

$types{'gfloat*'}{'phylum'} = "fundamental-outparameter";
$types{'gfloat*'}{'java'} = "float[]";
$types{'gfloat*'}{'translation'} = "";
$types{'gfloat*'}{'native'} = "float[]";
$types{'gfloat*'}{'jni'} = "jfloatArray";

$types{'gboolean'}{'phylum'} = "fundamental";
$types{'gboolean'}{'java'} = "boolean";
$types{'gboolean'}{'translation'} = "";
$types{'gboolean'}{'native'} = "boolean";
$types{'gboolean'}{'jni'} = "jboolean";

$types{'guint'}{'phylum'} = "fundamental";
$types{'guint'}{'java'} = "int";
$types{'guint'}{'translation'} = "";
$types{'guint'}{'native'} = "int";
$types{'guint'}{'jni'} = "jint";

$types{'gint'}{'phylum'} = "fundamental";
$types{'gint'}{'java'} = "int";
$types{'gint'}{'translation'} = "";
$types{'gint'}{'native'} = "int";
$types{'gint'}{'jni'} = "jint";

$types{'gulong'}{'phylum'} = "fundamental";
$types{'gulong'}{'java'} = "long";
$types{'gulong'}{'translation'} = "";
$types{'gulong'}{'native'} = "long";
$types{'gulong'}{'jni'} = "jlong";

$types{'glong'}{'phylum'} = "fundamental";
$types{'glong'}{'java'} = "long";
$types{'glong'}{'translation'} = "";
$types{'glong'}{'native'} = "long";
$types{'glong'}{'jni'} = "jlong";

$types{'gchar*'}{'phylum'} = "fundamental";
$types{'gchar*'}{'java'} = "String";
$types{'gchar*'}{'translation'} = "";
$types{'gchar*'}{'native'} = "String";
$types{'gchar*'}{'jni'} = "jstring";

$types{'const-gchar*'}{'phylum'} = "fundamental";
$types{'const-gchar*'}{'java'} = "String";
$types{'const-gchar*'}{'translation'} = "";
$types{'const-gchar*'}{'native'} = "String";
$types{'const-gchar*'}{'jni'} = "jstring";


#
# Where do things go?
#

#my %packages;

#
# Iterate over the input text; the hierarchy is max three deep:
#
# (define-DEFINE THING
#   (CHARACTERISTIC DATA)
#   (parameters
#     '(PARAMETER VALUE)
#     '(PARAMETER VALUE)
#   )
# )
#


#
# Current parser state
#

# the public API name of a given class we expose to developers and that will be passed
# through to us as self parameters on the Java side, eg "Button"
my $public_class;

# the generated class name we use internally to contain the wrapper code.
# Corresponds to what GTK natively calls it, eg "GtkButton"
my $g_class;


# the type of block we are in
my $define;

# whatever it is we are defining.
my $thing;

# cache the block we are parsing for debug purposes
my @lines;

# temporary holding of characteristic data we build up while parsing while
# we're trying to figure out where it goes. Once we have is-constructor-of or
# of-object we can file things away.
my %characteristics;

# same for parameters, but we store them backwards, name -> type (otherwise two
# gfloats would clobber one another)
my %parameters;
my @parameters_ordered;

# .java source files we have created (and that we will need to finalize with a } character.
my @java_files = ( );

#
# complain about something
#

my $RED="\e[31;01m";
my $BROWN="\e[31;02m";
my $LIGHTGREEN="\e[32;01m";
my $GREEN="\e[32;02m";
my $LIGHTYELLOW="\e[33;01m";
my $YELLOW="\e[33;02m";
my $BLUE="\e[34;01m";
my $NAVY="\e[34;02m";
my $MAGENTA="\e[35;01m"; #BOLD?
my $PURPLE="\e[35;02m";
my $CYAN="\e[36;01m";
my $TURQUOISE="\e[36;02m";
my $WHITE="\e[37;01m";
my $GRAY="\e[37;02m";
my $BLACKUNDERLINE="\e[38;01m";
my $GRAYUNDERLINE="\e[38;02m";


my $NORMAL="\e[0m";

sub warning {
	print "$YELLOW";
	print @_;
	print "$NORMAL\n";

}


#
# convert a trimmed C name to javaCamelCase
#

sub toCamel($) {
	my @words = split /_/, $_[0];

	my $camel = shift @words;

	while ( @words ) {
		my $word = shift @words;
		$camel .= ucfirst($word);
	}

	return "$camel";
}

#
# algorithmically map signal names
#

sub javaEventName($) {
	my $signal = uc $_[0];
	$signal =~ s/-/_/g;
	return $signal;
}

#
# get the approriate translation of return-type
#

sub returnType($) {
	my $layer = $_[0];
	my $type =  $characteristics{'return-type'};

	if (defined $types{$type}{$layer}) {
		return $types{$type}{$layer}
	} else {
		warning "Don't know what to do for ".$characteristics{'return-type'}." in $layer.\n";
	}
}


#
# generate a block of code that will encode for translation any necessary
# out-parameters. One line per argument. Configured for the two layers where
# this takes place: 'translation' and 'jni'
#

sub codeForArgs($) {
	my $layer = $_[0];
	my $declarations = "";
	my $code = "";

	foreach my $name ( @parameters_ordered ) {
		my $type = $parameters{$name};

		if ($layer eq "jni") {
			if ($types{$type}{'phylum'} eq "fundamental") {
				if (($type eq "gchar*") or ($type eq "const-gchar*")) {
					# filter out the hyphen
					$type =~ s/-/ /g;
					$code .=
<<HERE ;
	$name = ($type) (*env)->GetStringUTFChars(env, _$name, NULL);
	if (label == NULL) {
		return; /* OutOfMemoryError already thrown */
	}
HERE
				} else {
					# an ordinary guint or something.
					# Perhaps we'll need some type
					# conversion for unsigned.
					$code .=
<<HERE ;
	$name = ($type) _$name;
HERE
				}
			} elsif ($types{$type}{'phylum'} eq "fundamental-outparameter") {
				$code .=
<<HERE ;
	$name = ($type) (*env)->GetDoubleArrayElements(env, "_$name", NULL);
HERE
			} elsif (($types{$type}{'phylum'} eq "object") || 
				($types{$type}{'phylum'} eq "enum") ||
				($types{$type}{'phylum'} eq "boxed")) {
				$code .=
<<HERE ;
	$name = ($type) _$name;
HERE
			}

			# and add a declaration for the variable. $type has
			# been stripped of hyphens by now if necessary. Should
			# we switch to %types having a {'g'} factor, then we
			# can se that instead of munging $types.

			$declarations .=
<<HERE ;
	$type $name;
HERE
		} elsif ($layer eq "translation") {
			# no-op
		}
	}

	if ($declarations) {
		return "$declarations\n$code";
	} else {
		return "$code";
	}
}

sub codeForReturn($) {
	my $layer = $_[0];
	my $code = "";
	my $type = $characteristics{'return-type'};

	if ($type eq "none") {
		return "";
	}

	$code .= "\n";
	
	if ($layer eq "translation") {
		my $java_return_type = $types{$type}{'java'};
		if (($types{$type}{'phylum'} eq "object") ||
		    ($types{$type}{'phylum'} eq "boxed")) {
			$code .= 
<<HERE ;
		return ($java_return_type) instanceFor(result);
HERE
		} elsif ($types{$type}{'phylum'} eq "enum") {
			$code .= 
<<HERE ;
		return ($java_return_type) constantFor($java_return_type.class, result);
HERE
		} else {
			$code .= 
<<HERE ;
		return result;
HERE
		}

	} elsif ($layer eq "jni") {
		if (($type eq "gchar*") || ($type eq "const-gchar*")) {
			$code .= 
<<HERE ;
	return (*env)->NewStringUTF(env, result);
HERE
			return $code;
		}
		

		if ($types{$type}{'phylum'} eq "fundamental-outparameter") {
			$code .=
<<HERE ;
	// FIXME necesary to set args? Perhaps not. Need & on the call though?
HERE
		} elsif (($types{$type}{'phylum'} eq "object") || 
			($types{$type}{'phylum'} eq "enum") ||
			($types{$type}{'phylum'} eq "boxed") ||
			($types{$type}{'phylum'} eq "fundamental")) {
			$code .=
<<HERE ;
	return ($types{$type}{'jni'}) result;
HERE
		}
	}
	return $code;
}

#
# generate the argument signature for a given layer
#

sub genArgs($) {
	my $layer = $_[0];
	my $text = "";

	if ($layer eq 'jni') {
		$text .= "JNIEnv *env, jclass cls";
	}

	foreach my $name ( @parameters_ordered ) {

		# make a copy using lookup
		my $type = $parameters{$name};

		# if a second or subsequent argument add the comma which is the
		# argument separator at all three layers
		if ($text) {
			$text .= ", ";
		}

		# lookup the primative-only argument type
		if (defined $types{$type}{$layer}) {
			$text .= $types{$type}{$layer};
		} else {
			warning "Don't know how to convert \'$type\' to \'$layer\'";
			$text .= "$RED$type$NORMAL";
		}

		# and add the parameter name. In the 'jni' layer, as a local
		# convention, we add a leading _ to our encoded names to
		# differentiate the JNI parameter from the converted G version.
		# In the 'java' and 'native' add nicely converting the name to
		# camel case

		if ($layer eq 'translation') {
			if ($types{$type}{$layer}) {
				$text .= "(";
			}
		} else {
			$text .= " ";
		}

		if ($layer eq 'jni') {
			$text .= "_$name";
		} else {
			$text .= toCamel($name);
		}

		if ($layer eq 'translation') {
			if ($types{$type}{$layer}) {
				$text .= ")";
			}
		}

	}
	return $text;
}




sub process_block() {

#
# handle define-object. GObjects are always passed by reference, so appear in
# defs files as (for example) "GtkWidget*" when used in parameters. In the
# define-object block, however, they are defined bare. So add a '*' for the
# type key.
#

	if ($define eq "object") {
		if ($g_class = $characteristics{'c-name'}.'*') {
			$types{$g_class}{'phylum'} = $define;
			$types{$g_class}{'java'} = $thing;
			$types{$g_class}{'translation'} = "pointerOf";
			$types{$g_class}{'native'} = "long";
			$types{$g_class}{'jni'} = "jlong";
		} else {
			warning "What kind of object is $thing, really?\n";
			return;
		}

		if (my $g_module = $characteristics{'in-module'}) {
			#FIXME other spaces
			$types{$g_class}{'package'} = "org.gnome." . lc($g_module);
		} else {
			warning "What package is $thing in? No module statement.\n";
			return;
		} 

		print "write .java and .c stubs - GObject\n";
		return;
	}

#
# handle define-enum. Unlike real GObjects, enums in C are, of course, constant integer values
# passed by value, so they appear in defs files as (for example) "GtkThing" when used in parameters,
# which is the way they appear in the define-enum block.
#

	if ($define eq "enum") {
		if ($g_class = $characteristics{'c-name'}) {
			$types{$g_class}{'phylum'} = $define;
			$types{$g_class}{'java'} = $thing;
			$types{$g_class}{'translation'} = "numOf";
			$types{$g_class}{'native'} = "int";
			$types{$g_class}{'jni'} = "jint";
		} else {
			warning "What kind of enum is $thing, really?\n";
			return;
		}

		print "write .java and .c stubs - enum\n";
		return;
	}

#
# handle define-function. There are two cases: constructors, and miscallaneous functions.
#

	if ($define eq "function") {
		if ($g_class = $characteristics{'is-constructor-of'}) {
			print "will write constructor\n";
		} else {
			warning "unknown function - what to do with $thing?\n";
		}
		return;
	}

	if ($define eq "method") {
		if ($g_class = $characteristics{'of-object'}) {
			$g_class .= "*";

			#
			# As a convention, we call the necessary pointer to "this"
			# (which all G methods have as their first argument "self
			#

			$parameters{"self"} = $g_class;
			unshift @parameters_ordered, "self";
			print "write method:\n";

			generate_method();
		}
		return;
	}

	if ($define eq "virtual") {
		if ($g_class = $characteristics{'of-object'}) {
			print "will write signal handler\n";
			print "\tJava:\t".javaEventName($thing)."\n";
		}
		return;
	}

	warning "What native class is this?\n";

#		} elsif ($characteristic eq "parent") {
#			# unusually, this ie ignore. Inheritence relationships
#			# between classes are tracked in the public API, not
#			# in the generated code which is all static.
#		} else {
#			print "unknown characteristic: $characteristic\n";
#		}
}



#
# The main entry point for the parser. Can be called multiple times; object
# definitions accumulate.
#

sub process($) {
	my $defsfile = $_[0];

	open DEFS, $defsfile;

	while (<DEFS>) {
		chomp;

		#
		# Skip comments and blank lines
		#

		if (/^;;/) {
			next;
		}

		if (/^\s*$/) {
			next;
		}

		push @lines, $_;


		#
		# Ends a declaration
		#

		if (/^\)/) {
			print "\n$NAVY" . join "\n", @lines;
			print "$NORMAL\n";

			process_block();

			@lines = ( );

			undef $define;
			undef %parameters;
			undef @parameters_ordered;
			undef %characteristics;

			next;
		}

		#
		# Process top level declaration. define-PHYLUM THING gives us information
		# which we can store immediately.
		#

		if (/^\(define-(\w+)\s+(\w+)/) {
			$define = $1;
			$thing = $2;

			if (($define eq "object") || 
			    ($define eq "boxed") ||
			    ($define eq "enum")) {
				# $phylum{$thing} = $define;
			} elsif (($define eq "function") ||
			    ($define eq "method") ||
			    ($define eq "virtual")) {
				# do nothing
			} else {
				print "unknown define: $define\n";
			}

			%characteristics = ( );
			next;
		}
		

		#
		# Process characteristics
		#

		if (/^\s+\((\S+)\s+"?([\w\-\*]+)"?\)/) {
			if (!defined $define) {
				warning "Parsing error: found a characteristics line but not in a define block\n";
				next;
			}

			$characteristics{$1} = $2;
			next;
		}

		#
		# Process parameters subblock declaration
		#

		if (/^\s+\(parameters/) {
			if (!defined $define) {
				warning "Parsing error: found a parameters line but not in a parameters block\n";
				next;
			}
			if (%parameters) {
				warning "Parsing error: found a parameters line but already in parameters block\n";
				next;
			}

			%parameters = ( );
			next;
		}


		#
		# Process individual parameters lines. Discard quotes. The parameters are
		# stored backwards, name to type.
		#

		if (/^\s+'?\("?([\w\-\*]+)"?\s+"?([\w]+)"?\)/) {
			if (!defined $define) {
				warning "Parsing error: found a characteristics line but not in a define block\n";
				next;
			}

			$parameters{$2} = $1;
			push @parameters_ordered, $2;
			next;
		}

		#
		# Note end parameters
		#

		if (/^\s+\)/) {
			next;
		}

		print "unknown line: $_\n"; 
		#DEBUG, and die
		exit;
	}

	close DEFS;
}


#
# ---------------------------------------------------------
# Output routines
# ---------------------------------------------------------
#

#
# Output the standard header and warning that the file is generated so that
# people don't try to edit these files.
#

sub spew_file_header(*$) {
	use Symbol 'qualify_to_ref';
	my $fd = qualify_to_ref($_[0]);

	my $filename = $_[1];

	print $fd <<HERE ;
/*
 * ${filename}
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd
 * Proprietary - not for release without written authorization
 *
 * 			THIS FILE IS GENERATED CODE!
 *
 * To modify its contents or behaviour, either update the generation program,
 * change the information in the source defs file, or implement an override for
 * this class.
 */
HERE
}

sub spew_java_class($$) {
	my $package = $_[0];
	my $class = $_[1];

	# cosmetic
	my $target = $class . ".java";
	
	spew_file_header(JAVA, $target);

	print JAVA
<<HERE ;
package $package;

import org.gnome.glib.Plumbing;

final class $class extends Plumbing
{
	private $class() { }

HERE

}

#
# A bit silly to have a function for this, but nevertheless, write the final
# closing brace for java source files.
#

sub finalize_java_files() {
	foreach my $file ( @java_files ) {
		open JAVA, ">>$file" or die "can't open $file for finalizing";
		print JAVA
<<HERE ;
}
HERE
		close JAVA;
	}
}


#
# Output the java side code for a method
#
# This could have beend done as a succession of print statements (and indeed an
# intermediate version had that) but its easier to grok the resultant generated
# code as a whole with appropriately named variables substituting.
#

sub spew_java_method($$) {
	my $native_class = $_[0];
	my $native_name = $_[1];

	#
	# 'java' layer
	#
	my $java_return = returnType('java');

	my $java_method = toCamel($thing);

	my $java_args = genArgs('java');

	#
	# code within the 'java' layer that translates to 'native' layer form. This will 
	# need additional concatination to deal with further encoding if required.
	#

	my $translation_code = codeForArgs('translation');
	my $translation_args = genArgs('translation');

	#
	# 'native' layer
	#
	my $native_return = returnType('native');

	my $native_method = $characteristics{'c-name'};

	my $native_args = genArgs('native');

	my $translation_return = "";
	if ($characteristics{'return-type'} ne "none") {
		my $translation_return_type = $types{$characteristics{'return-type'}}{'native'};
		$translation_code .=
<<HERE;
		$translation_return_type result;

HERE
		$translation_return .= "result = ";
	}

	#
	# and output the code! First the method signature
	#

	print JAVA
<<HERE ;
	static final $java_return $java_method($java_args) {
HERE

	#
	# then the translation code and native call
	#

	print JAVA $translation_code;

	print JAVA
<<HERE ;
		$translation_return$native_method($translation_args);
HERE

	$translation_code = codeForReturn('translation');
	print JAVA $translation_code;


	print JAVA
<<HERE ;
	}
HERE


	#
	# and the native signature itself.
	#

	print JAVA
<<HERE ;

	private static native final $native_return $native_method($native_args);

HERE

}


#
# Given a fully qualified class name (ie a Type name with Package prefix), encode the name
# per the JNI spec. This part is trivial.
#
sub encode_jni_class($$) {
	my $package = $_[0];
	my $class = $_[1];

	my $fqcn = $package . "." . $class;

	$fqcn =~ s/\./_/g;
	return $fqcn;
}


#
# Given a method, and a signature, encode them into the concatonate native
# function name per the JNI sepc. This assumes that there is no native method
# overloading
#

sub encode_jni_method($) {
	my $method = $_[0];

	$method =~ s/_/_1/g;

	return $method;
}







sub spew_jni_class($$) {
	my $package = $_[0];
	my $class = $_[1];

	# The name of the header file to be included
	my $header = encode_jni_class($package,$class) . ".h";

	# output the stock file opener. Giving the name.c is just cosmetic.
	spew_file_header(JNI, $class . ".c");

	print JNI <<HERE ;

#include <jni.h>
#include <gtk/gtk.h>
#include "bindings_java.h"
#include "$header"

HERE

}




#
# Output the JNI method.
#
# At the cost of some redundency, we spit out the signature of the public API
# call and the boundary crossing call to make it easier to locate methods in
# the generated code (since munged JNI names are a pain in the ass to read).
#

sub spew_jni_method($$) {
	my $package = $_[0];
	my $class = $_[1];

	# recreate the java and native layer signatures so we can print them in
	# the comment preceeding the function. This obviously duplicates the
	# work done in spew_java_method(), so perhaps we should stash this
	# somewhere?

	#
	# java layer
	#
	my $java_method = toCamel($thing);

	my $java_args = genArgs('java');

	#
	# native layer
	#
	my $native_method = $characteristics{'c-name'};

	my $native_args = genArgs('native');

	#
	# jni layer
	#
	my $jni_return = returnType('jni');

	# work out the signature of the JNI function
	my $jni_signature = "";
	$jni_signature .= "Java_";
	$jni_signature .= encode_jni_class($package, $class);
	$jni_signature .= "_";
	$jni_signature .= encode_jni_method($native_method);

	my $jni_args = genArgs('jni');

	my $c_encode = codeForArgs('jni');
	my $c_return = "";
	if ($characteristics{'return-type'} ne "none") {
		my $c_return_type = $characteristics{'return-type'};
		$c_return_type =~ s/-/ /g;
		my $c_return_declaration =
<<HERE;
	$c_return_type result;
HERE
		if ($c_encode) {
			$c_encode =~ s/\n\n/\n$c_return_declaration\n/;
		} else {
			$c_encode = $c_return_declaration;
		}
		$c_return = "result = ";
	}

	my $c_function = $native_method;
	my $c_args = join ", ", @parameters_ordered;

	#
	# Output the function signature
	#

	print JNI <<HERE ;
/*
 * Implements
 *   $package.$class.$native_method($native_args)
 * called from
 *   $package.$class.$java_method($java_args)
 */
JNIEXPORT $jni_return JNICALL
$jni_signature($jni_args)
{
HERE

	#
	# Output the code to translate from JNI to G
	#
	
	print JNI $c_encode;

	#
	# Call the underlying C function
	#

	print JNI <<HERE ;

	$c_return$c_function($c_args);
HERE

	#
	# Cleanup and return
	#

	my $result = codeForReturn('jni');
	print JNI $result;

	print JNI <<HERE ;
}

HERE

}

#
# Do what mkdir -p does.
#

sub recursive_mkdir($) {
	my $target = $_[0];

	my @components = split "/", $target;

	# assumed to exist
	my $path = ".";

	foreach my $component ( @components ) {

		$path .= "/";
		$path .= $component;

		if ( -d $path ) {
			next;
		}

		mkdir $path or die "Can't make directory $path: $!";
	}
}


#
# Entry point into the code generator section from the defs parser section. Here we
# Output the Java and JNI code corresponding to a method, calling the routines to create the files
# if necessary. The key to the whole thing is $g_class, one of the globals that gets set by the parser.
#

sub generate_method() {
	my $j_class;
	my $j_package;

	$j_class = $g_class;
	$j_class =~ s/\*$//;

	unless ($j_package = $types{$g_class}{'package'}) {
		warning "No package for $g_class";
		return;
	};

	my $j_package_path = "tmp/" . $j_package; 
	$j_package_path =~ s/\./\//g;

	my $java_file = $j_package_path . "/" . $j_class . ".java";
	my $jni_file = $java_file;
	$jni_file =~ s/\.java$/.c/;

	if ( -f $java_file ) {
		open JAVA, ">>$java_file" or die "can't open $java_file for appending";
		open JNI, ">>$jni_file" or die "can't open $java_file for appending";
	} else {
		if ( ! -d $j_package_path) {
			recursive_mkdir($j_package_path);
		}

		open JAVA, ">$java_file" or die "can't open $java_file for writing";
		spew_java_class($j_package, $j_class);
		push @java_files, $java_file;

		open JNI, ">$jni_file" or die "can't open $jni_file for writing";
		spew_jni_class($j_package, $j_class);
	}

	print "\tWill write to Java file:\t". $java_file . "\n";
	print "\tWill write to JNI file:\t\t". $jni_file . "\n";

	spew_java_method($j_package, $j_class);
	spew_jni_method($j_package, $j_class);

	close JNI;
	close JAVA;
}

sub generate_signal_handler() {

}


sub finalize() {
	finalize_java_files();
}

sub dump() {
	print "\n$TURQUOISE";
	print Dumper(\%types);
	print "$NORMAL";
}
