#
# Makefile, part of Equivalence
#
# Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd 
# 
# The code in this file, and the library it is a part of, are made available
# to you by the authors under the terms of the "GNU General Public Licence,
# version 2". See the LICENCE file for the terms governing usage and
# redistribution.
#

ifdef V
else
MAKEFLAGS=-s
endif

-include .config

ifdef GCJ
all: build-java build-native
else
all: build-java
endif

build-java: tmp/gtk-$(APIVERSION).jar tmp/libgtkjni-$(APIVERSION).so

build-native: tmp/libgtkjava-$(APIVERSION).so

.PHONY: test demo doc clean distlcean sleep install

# [this  will be called by the above include if .config is missing.
# We don't call ./configure automatically to allow scope for
# manual configuration and overrides]
.config: src/bindings/org/gnome/gtk/Version.java
	echo
	echo "You need to run ./configure to check prerequisites"
	echo "and setup preferences before you can build java-gnome."
	( if [ ! -x configure ] ; then chmod +x configure ; echo "I just made it executable for you." ; fi )
	echo
	exit 1

build/config: .config build/dirs
	@echo "CHECK     build system configuration"
	( if [ ! "$(JAVA_CMD)" ] ; then echo "Sanity check failed. Run ./configure" ; exit 1 ; fi )
	touch $@

SOURCES_CODEGEN=$(shell find src/generator -name '*.java')

SOURCES_DIST=$(shell find src/bindings -name '*.java') $(shell find mockup/bindings -name '*.java' )

SOURCES_TEST=$(shell find tests/prototype -name '*.java' ) $(shell find tests/bindings -name '*.java' )  $(shell find tests/generator -name '*.java' )

# These are just the headers which are crafted, not generated
HEADERS_C=$(shell find src/jni -name '*.h' | sed -e 's/src\/jni/tmp\/include/g' -e 's/\.c/\.h/g')

SOURCES_C=$(shell find src/bindings -name '*.c' ) $(shell find mockup/bindings -name '*.c' ) $(shell find src/jni -name '*.c' )
OBJECTS_C=$(shell echo $(SOURCES_C) | sed -e's/\.c/\.o/g' -e's/src\/bindings/tmp\/objects/g' -e's/mockup\/bindings/tmp\/objects/g' -e's/src\/jni/tmp\/objects/g' )


#
# convenience target: setup pre-reqs
#
build/dirs: .config
	@echo "MKDIR     temporary build directories"
	-test -d build || mkdir build
	-test -d tmp/bindings || mkdir -p tmp/bindings
	-test -d tmp/generator || mkdir -p tmp/generator
	-test -d tmp/native || mkdir -p tmp/native
	-test -d tmp/objects || mkdir -p tmp/objects
	-test -d tmp/include || mkdir -p tmp/include
	-test -d tmp/tests || mkdir -p tmp/tests
	touch $@


# --------------------------------------------------------------------
# Source compilation
# --------------------------------------------------------------------

tmp/gtk-$(APIVERSION).jar: build/config build/classes-codegen build/classes-dist build/properties-dist
	@echo "$(JAR_CMD) $@"
	cd tmp/bindings ; find . -name '*.class' -o -name '*.properties' | xargs $(JAR) cf ../../$@ 

build/classes-codegen: $(SOURCES_CODEGEN)
	@echo "$(JAVAC_CMD) tmp/generator/*.class"
	$(JAVAC) -d tmp/generator -classpath tmp/generator -sourcepath src/generator $?
	@echo "NOOP      CodeGenerator"
	touch $@

build/classes-dist: $(SOURCES_DIST)
	@echo "$(JAVAC_CMD) tmp/bindings/*.class"
	$(JAVAC) -d tmp/bindings -classpath tmp/bindings -sourcepath src/bindings:mockup/bindings $?
	touch $@


build/properties-dist: tmp/bindings/typeMapping.properties
	touch $@

tmp/bindings/%.properties: mockup/bindings/%.properties
	@echo "CP        $< -> $(@D)"
	cp -p $< $@


GTK_CFLAGS=$(shell pkg-config --cflags gthread-2.0) \
		$(shell pkg-config --cflags glib-2.0) \
		$(shell pkg-config --cflags gtk+-2.0) \
		$(shell pkg-config --cflags libglade-2.0)

GTK_LIBS=$(shell pkg-config --libs gthread-2.0) \
		$(shell pkg-config --libs glib-2.0) \
		$(shell pkg-config --libs gtk+-2.0) \
		$(shell pkg-config --libs libglade-2.0)


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

SOURCES_JNI=$(shell find src/bindings -name '*.c') $(shell find mockup/bindings -name '*.c')
build/headers-generate: $(SOURCES_JNI)
	@echo "$(JAVAH_CMD) tmp/headers/*.h"
	$(JAVAH) -jni -d tmp/include -classpath $(JAVAGNOME_JARS):tmp/bindings \
		$(shell echo $? | sed -e 's/src\/bindings\///g' -e 's/mockup\/bindings\///g' -e 's/\.c//g' -e 's/\//\./g' )
	touch $@

tmp/objects/%.o: src/jni/%.c
	@if [ ! -d $(@D) ] ; then echo "MKDIR     $(@D)" ; mkdir -p $(@D) ; fi
	echo "$(CC_CMD) $@"
	$(CCACHE) $(CC) $(GTK_CFLAGS) -Itmp/include -o $@ -c $<


tmp/objects/%.o: src/bindings/%.c
	@if [ ! -d $(@D) ] ; then echo "MKDIR     $(@D)" ; mkdir -p $(@D) ; fi
	@echo "$(CC_CMD) $@"
	$(CCACHE) $(CC) $(GTK_CFLAGS) -Itmp/include -o $@ -c $<

tmp/objects/%.o: mockup/bindings/%.c
	@if [ ! -d $(@D) ] ; then echo "MKDIR     $(@D)" ; mkdir -p $(@D) ; fi
	@echo "$(CC_CMD) $@"
	$(CCACHE) $(CC) $(GTK_CFLAGS) -Itmp/include -o $@ -c $<

tmp/libgtkjni-$(APIVERSION).so: build/config build/headers $(OBJECTS_C)
	@echo "$(LINK_CMD) $@"
	$(LINK) \
		 $(GTK_LIBS) \
		-o $@ $(OBJECTS_C)
#	@echo "STRIP     $@"
#	strip --only-keep-debug $@

.SECONDARY: tmp/native/gtk.o

tmp/native/gtk.o: tmp/gtk-$(APIVERSION).jar
	@echo "$(GCJ_CMD) $@"
	$(GCJ) \
		-classpath $(JAVAGNOME_JARS):src/bindings:tmp/bindings \
		-o $@ -c $<

tmp/libgtkjava-$(APIVERSION).so: tmp/native/gtk.o
	@echo "$(GCJ_LINK_CMD) $@"
	$(GCJ_LINK) \
		-Wl,-rpath=$(JAVAGNOME_HOME)/lib \
		-L$(JAVAGNOME_HOME)/lib \
		-o $@ $<
#	@echo "STRIP     $@"
#	strip --only-keep-debug $@

# --------------------------------------------------------------------
# Install (run as root, or specify DESTDIR on Make command line)
# --------------------------------------------------------------------

ifdef GCJ
install: build-java install-dirs install-java install-native
else
install: build-native install-dirs install-java
endif
	rm $(DESTDIR)$(PREFIX)/.java-gnome-install-dirs

install-dirs: $(DESTDIR)$(PREFIX)/.java-gnome-install-dirs
$(DESTDIR)$(PREFIX)/.java-gnome-install-dirs:
	@test -d $(DESTDIR)$(PREFIX)/share/java || echo "MKDIR     installation directories"
	-mkdir -p $(DESTDIR)$(PREFIX)
	-touch $@ 2>/dev/null
	test -w $@ || ( echo -e "\nYou don't seem to have write permissions to $(DESDIR)$(PREFIX)\nPerhaps you need to be root?\n" && exit 7 )
	mkdir -p $(DESTDIR)$(PREFIX)/share/java
	mkdir -p $(DESTDIR)$(PREFIX)/lib

install-java: build-java \
	$(DESTDIR)$(PREFIX)/share/bindings/gtk-$(APIVERSION).jar \
	$(DESTDIR)$(PREFIX)/lib/libgtkjni-$(APIVERSION).so

install-native: build-native install-java \
	$(DESTDIR)$(PREFIX)/lib/libgtkjava-$(APIVERSION).so

$(DESTDIR)$(PREFIX)/share/bindings/gtk-$(APIVERSION).jar: tmp/gtk-$(APIVERSION).jar
	@echo "INSTALL   $@"
	cp -f $< $@
	@echo "SYMLINK   $(@D)/gtk.jar -> gtk-$(APIVERSION).jar"
	cd $(@D) && rm -f gtk.jar && ln -s gtk-$(APIVERSION).jar gtk.jar
	
$(DESTDIR)$(PREFIX)/lib/libgtkjni-$(APIVERSION).so: tmp/libgtkjni-$(APIVERSION).so
	@echo "INSTALL   $@"
	cp -f $< $@

$(DESTDIR)$(PREFIX)/lib/libgtkjava-$(APIVERSION).so: tmp/libgtkjava-$(APIVERSION).so
	@echo "INSTALL   $@"
	cp -f $< $@


# --------------------------------------------------------------------
# Tests
# --------------------------------------------------------------------

build/classes-test: $(SOURCES_TEST)
	@echo "$(JAVAC_CMD) tmp/tests/*.class"
	$(JAVAC) -d tmp/tests -classpath tmp/tests:tmp/generator:tmp/gtk-$(APIVERSION).jar:$(JUNIT_JARS) -sourcepath tests/prototype:tests/bindings:tmp/generator $?
	touch $@

# This is a bit of ugliness necessary to ensure that COLUMNS is in the
# envionment Make passes to the test command. If anyone can suggest a better
# way to do this, by all means please do so.
export COLUMNS:=$(shell stty size 2>/dev/null | sed -e 's/[0-9]* \([0-9]*\)/\1/' )

test: build-java build/classes-test
	@echo "$(JAVA_CMD) UnitTests"
	$(JAVA) \
		-classpath tmp/tests:tmp/generator:tmp/gtk-$(APIVERSION).jar:$(JUNIT_JARS) \
		-Djava.library.path=tmp \
		-ea \
		UnitTests

demo: build-java build/classes-test
	@echo "$(JAVA_CMD) Experiment"
	$(JAVA) \
		-classpath tmp/tests:tmp/gtk-$(APIVERSION).jar \
		-Djava.library.path=tmp \
		-ea \
		Experiment

# --------------------------------------------------------------------
# Documentation generation
# --------------------------------------------------------------------

ifdef V
else
JAVADOC:=$(JAVADOC) -quiet
REDIRECT=>/dev/null
endif

doc: build/classes-dist
	@echo "$(JAVADOC_CMD) doc/api/*.html"
	$(JAVADOC) \
		-d doc/api \
		-classpath tmp/bindings \
		-public \
		-nodeprecated \
		-source 1.4 \
		-notree \
		-noindex \
		-nohelp \
		-version \
		-author \
		-windowtitle "java-gnome $(APIVERSION) API Documentation" \
		-doctitle "<h1>java-gnome $(APIVERSION) API Documentation</h1>" \
		-header "java-gnome version $(VERSION)" \
		-footer "<img src=\"/images/java-gnome_JavaDocLogo.png\" style=\"padding-right:25px;\"><br> <span style=\"font-family: Arial; font-style: normal; font-size: large;\">java-gnome</span>" \
		-breakiterator \
		-overview src/bindings/overview.html \
		-sourcepath src/bindings \
		-subpackages org \
		-exclude "org.freedesktop.bindings" \
		$(REDIRECT)


#
# Remember that if you bump the version number you need to commit the change
# and re-./configure before being able to run this! On the other hand, we
# don't have to distclean before calling this.
#
dist: all
	@echo "CHECK     fully committed state"
	bzr diff > /dev/null || ( echo -e "\nYou need to commit all changes before running make dist\n" ; exit 4 )
	@echo "EXPORT    tmp/java-gnome-$(VERSION)"
	-rm -rf tmp/java-gnome-$(VERSION)
	bzr export --format=dir tmp/java-gnome-$(VERSION)
	@echo "RM        non essential files"
	rm -r tmp/java-gnome-$(VERSION)/web
	rm    tmp/java-gnome-$(VERSION)/.aspell.en.pws
	@echo "TAR       java-gnome-$(VERSION).tar.bz2"
	tar cjf java-gnome-$(VERSION).tar.bz2 -C tmp java-gnome-$(VERSION)
	rm -r tmp/java-gnome-$(VERSION)

clean:
	@echo "RM        temporary files"
	rm -rf build/* tmp/bindings/* tmp/include/* tmp/native/* tmp/objects/*
	rm -f hs_err_*
	@echo "RM        built .jar and .so"
	rm -f tmp/gtk-*.jar \
		tmp/libgtkjni-*.so \
		tmp/libgtkjava-*.so

distclean: clean
	@echo "RM        build configuration information"
	-rm -f .config .config.tmp
	@echo "RM        generated documentation"
	-rm -rf doc/api/*
	-rm -f java-gnome-*.tar.bz2
	@echo "RM        temporary directories"
	-rm -rf tmp build
	@echo "RM        glade cruft"
	find . -name '*.glade.bak' -o -name '*.gladep*' -type f | xargs rm -f

# vim: set filetype=make textwidth=78 nowrap:
