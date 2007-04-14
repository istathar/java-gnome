
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



#
# ---------------------------------------------------------
# Output routines
# ---------------------------------------------------------
#

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
