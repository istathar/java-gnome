#
# Makefile, part of Equivalence
#
# Copyright (c) 2006-2008 Operational Dynamics Consulting Pty Ltd, and Others 
# 
# The code in this file, and the library it is a part of, are made available
# to you by the authors under the terms of the "GNU General Public Licence,
# version 2". See the LICENCE file for the terms governing usage and
# redistribution.
#

ifdef V
else
MAKEFLAGS=-s
REDIRECT=>/dev/null
endif

-include .config

all: build-java

.PHONY: test demo doc clean distclean install


# --------------------------------------------------------------------
# Source compilation
# --------------------------------------------------------------------

build-java:
	build/faster

# --------------------------------------------------------------------
# Install (run as root, or specify DESTDIR on Make command line)
# --------------------------------------------------------------------

install: build-java install-dirs install-java
	rm $(DESTDIR)$(PREFIX)/.java-gnome-install-dirs

install-dirs: $(DESTDIR)$(PREFIX)/.java-gnome-install-dirs
$(DESTDIR)$(PREFIX)/.java-gnome-install-dirs:
	@test -d $(DESTDIR)$(PREFIX)/share/java || echo -e "MKDIR\tinstallation directories"
	-mkdir -p $(DESTDIR)$(PREFIX)
	-touch $@ 2>/dev/null
	test -w $@ || ( echo -e "\nYou don't seem to have write permissions to $(DESDIR)$(PREFIX)\nPerhaps you need to be root?\n" && exit 7 )
	mkdir -p $(DESTDIR)$(PREFIX)/share/java
	mkdir -p $(DESTDIR)$(LIBDIR)

install-java: build-java \
	$(DESTDIR)$(PREFIX)/share/java/gtk-$(APIVERSION).jar \
	$(DESTDIR)$(LIBDIR)/libgtkjni-$(APIVERSION).so

$(DESTDIR)$(PREFIX)/share/java/gtk-$(APIVERSION).jar: tmp/gtk-$(APIVERSION).jar
	@echo -e "INSTALL\t$@"
	cp -f $< $@
	@echo -e "SYMLINK\t$(@D)/gtk.jar -> gtk-$(APIVERSION).jar"
	cd $(@D) && rm -f gtk.jar && ln -s gtk-$(APIVERSION).jar gtk.jar
	
$(DESTDIR)$(LIBDIR)/libgtkjni-$(APIVERSION).so: tmp/libgtkjni-$(APIVERSION).so
	@echo -e "INSTALL\t$@"
	cp -f $< $@


# --------------------------------------------------------------------
# Tests
# --------------------------------------------------------------------

test:
	build/faster test

demo:
	build/faster demo

# --------------------------------------------------------------------
# Documentation generation
# --------------------------------------------------------------------

ifdef V
else
JAVADOC:=$(JAVADOC) -quiet
endif

doc:
	@echo "$(JAVADOC_CMD) doc/api/*.html"
	$(JAVADOC) \
		-d doc/api \
		-classpath tmp/bindings \
		-public \
		-nodeprecated \
		-source 1.5 \
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
		-stylesheetfile src/bindings/stylesheet.css \
		-overview src/bindings/overview.html \
		-sourcepath src/bindings \
		-subpackages org \
		$(REDIRECT)


#
# Remember that if you bump the version number you need to commit the change
# and re-./configure before being able to run this! On the other hand, we
# don't have to distclean before calling this.
#
dist: all
	@echo -e "CHECK\tfully committed state"
	bzr diff > /dev/null || ( echo -e "\nYou need to commit all changes before running make dist\n" ; exit 4 )
	@echo -e "EXPORT\ttmp/java-gnome-$(VERSION)"
	-rm -rf tmp/java-gnome-$(VERSION)
	bzr export --format=dir tmp/java-gnome-$(VERSION)
	@echo -e "RM\tnon essential files"
	rm -r tmp/java-gnome-$(VERSION)/lib
	rm -r tmp/java-gnome-$(VERSION)/web
	rm    tmp/java-gnome-$(VERSION)/.aspell.en.pws
	@echo -e "TAR\tjava-gnome-$(VERSION).tar.bz2"
	tar cjf java-gnome-$(VERSION).tar.bz2 -C tmp java-gnome-$(VERSION)
	rm -r tmp/java-gnome-$(VERSION)

clean:
	@echo -e "RM\tgenerated code"
	rm -rf generated/bindings/*
	@echo -e "RM\tcompiled output"
	rm -rf tmp/generator/ tmp/bindings/ tmp/tests/
	rm -rf tmp/include/ tmp/objects/
	rm -rf tmp/i18n/ tmp/locale/
	@echo -e "RM\ttemporary files"
	rm -rf tmp/stamp/
	rm -f hs_err_*
	@echo -e "RM\tbuilt .jar and .so"
	rm -f tmp/gtk-*.jar \
		tmp/libgtkjni-*.so \
		tmp/libgtkjava-*.so

distclean: clean
	@echo -e "RM\tbuild configuration information"
	-rm -f .config .config.tmp
	@echo -e "RM\tgenerated documentation"
	-rm -rf doc/api/*
	-rm -f java-gnome-*.tar.bz2
	@echo -e "RM\ttemporary directories"
	-rm -rf tmp generated
	@echo -e "RM\tglade cruft"
	find . -name '*.glade.bak' -o -name '*.gladep*' -type f | xargs rm -f

# vim: set filetype=make textwidth=78 nowrap:
