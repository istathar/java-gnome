#
# jni.make, part of the Equivalence setup used by java-gnome
#
# Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd 
# 
# The code in this file, and the library it is a part of, are made available
# to you by the authors under the terms of the "GNU General Public Licence,
# version 2". See the LICENCE file for the terms governing usage and
# redistribution.
#

#
# This Makefile fragment takes care of building sources; it is separate from 
# the top level Makefile so that it occurs *after* code generation, thus
# allowing the variable setting to find the generated files.
#

-include .config

all: tmp/libgtkjni-$(APIVERSION).so

# These are just the headers which are crafted, not generated
HEADERS_C=$(shell find src/jni -name '*.h' | sed -e 's/src\/jni/tmp\/include/g' -e 's/\.c/\.h/g')

SOURCES_JNI=$(shell find src/bindings -name '*.c') $(shell find generated/bindings -name '*.c')
SOURCES_C=$(SOURCES_JNI) $(shell find src/jni -name '*.c' )

OBJECTS_C=$(shell echo $(SOURCES_C) | sed -e's/\.c/\.o/g' -e's/src\/bindings/tmp\/objects/g' -e's/generated\/bindings/tmp\/objects/g' -e's/src\/jni/tmp\/objects/g' )


GTK_MODULES="gthread-2.0 glib-2.0 gtk+-2.0 gtk+-unix-print-2.0 libglade-2.0"

GTK_CFLAGS=$(shell pkg-config --cflags $(GTK_MODULES))
GTK_LIBS=$(shell pkg-config --libs $(GTK_MODULES))


ifdef V
JAVAH:=$(JAVAH) -verbose
endif

build/headers: build/headers-static build/headers-generate
	touch $@

build/headers-static: $(HEADERS_C)
	touch $@

tmp/include/%.h: src/jni/%.h
	@echo "CP        $< -> $(@D)"
	cp -p $< $@

# We don't use an implict rule for this for the simple reason that we only
# want to do one invocation, which means using $? (newer than target). It gets
# more complicated because of the need to give classnames to javah.

build/headers-generate: $(SOURCES_JNI)
	@echo "$(JAVAH_CMD) tmp/headers/*.h"
	$(JAVAH) -d tmp/include -classpath tmp/bindings \
		$(shell echo $? | sed -e 's/src\/bindings\///g' -e 's/generated\/bindings\///g' -e 's/\.c//g' -e 's/\//\./g' )
	touch $@

tmp/objects/%.o: src/jni/%.c src/jni/bindings_java.h
	@if [ ! -d $(@D) ] ; then echo "MKDIR     $(@D)" ; mkdir -p $(@D) ; fi
	echo "$(CC_CMD) $@"
	$(CCACHE) $(CC) $(GTK_CFLAGS) -Itmp/include -o $@ -c $<


tmp/objects/%.o: src/bindings/%.c src/jni/bindings_java.h
	@if [ ! -d $(@D) ] ; then echo "MKDIR     $(@D)" ; mkdir -p $(@D) ; fi
	@echo "$(CC_CMD) $@"
	$(CCACHE) $(CC) $(GTK_CFLAGS) -Itmp/include -o $@ -c $<

tmp/objects/%.o: generated/bindings/%.c
	@if [ ! -d $(@D) ] ; then echo "MKDIR     $(@D)" ; mkdir -p $(@D) ; fi
	@echo "$(CC_CMD) $@"
	$(CCACHE) $(CC) $(GTK_CFLAGS) -Itmp/include -o $@ -c $<

tmp/libgtkjni-$(APIVERSION).so: build/headers $(OBJECTS_C)
	@echo "$(LINK_CMD) $@"
	$(LINK) \
		 $(GTK_LIBS) \
		-o $@ $(OBJECTS_C)
#	@echo "STRIP     $@"
#	strip --only-keep-debug $@


# vim: set filetype=make textwidth=78 nowrap:
