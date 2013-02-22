#
# java-gnome, a UI library for writing GTK and GNOME programs from Java!
#
# Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd and Others 
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
	@test -d $(DESTDIR)$(JARDIR) || /bin/echo -e "MKDIR\tinstallation directories"
	-mkdir -p $(DESTDIR)$(PREFIX)
	-touch $@ 2>/dev/null
	test -w $@ || ( /bin/echo -e "\nYou don't seem to have write permissions to $(DESTDIR)$(PREFIX)\nPerhaps you need to be root?\n" && exit 7 )
	mkdir -p $(DESTDIR)$(JARDIR)
	mkdir -p $(DESTDIR)$(LIBDIR)

install-java: build-java \
	$(DESTDIR)$(JARDIR)/gtk-$(APIVERSION).jar \
	$(DESTDIR)$(LIBDIR)/libgtkjni-$(VERSION).so

$(DESTDIR)$(JARDIR)/gtk-$(APIVERSION).jar: tmp/gtk-$(APIVERSION).jar
	@/bin/echo -e "INSTALL\t$@"
	cp -f $< $@
	@/bin/echo -e "JAR\t$@"
	jar uf $@ .libdir
	@/bin/echo -e "SYMLINK\t$(@D)/gtk.jar -> gtk-$(APIVERSION).jar"
	cd $(@D) && rm -f gtk.jar && ln -s gtk-$(APIVERSION).jar gtk.jar
	
$(DESTDIR)$(LIBDIR)/libgtkjni-$(VERSION).so: tmp/libgtkjni-$(VERSION).so
	@/bin/echo -e "INSTALL\t$@"
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

doc:
	build/faster doc


#
# Remember that if you bump the version number you need to commit the change
# and re-./configure before being able to run this! On the other hand, we
# don't have to distclean before calling this.
#
dist: all
	@/bin/echo -e "CHECK\tfully committed state"
	[ -z "`git status --short`" ] || ( /bin/echo -e "\nYou need to commit all changes before running make dist\n" ; exit 4 )
	@/bin/echo -e "EXPORT\tjava-gnome-$(VERSION).tar.xz"
	git archive --format tar master --prefix java-gnome-$(VERSION)/ | xz -z - > java-gnome-$(VERSION).tar.xz

clean:
	@/bin/echo -e "RM\tgenerated code"
	rm -rf generated/bindings/*
	@/bin/echo -e "RM\tcompiled output"
	rm -rf tmp/generator/ tmp/bindings/ tmp/tests/
	rm -rf tmp/include/ tmp/objects/
	rm -rf tmp/i18n/ tmp/locale/
	@/bin/echo -e "RM\ttemporary files"
	rm -rf tmp/stamp/
	rm -f hs_err_*
	@/bin/echo -e "RM\tbuilt .jar and .so"
	rm -f tmp/gtk-*.jar \
		tmp/libgtkjni-*.so

distclean: clean
	@/bin/echo -e "RM\tbuild configuration information"
	-rm -f .config .config.tmp .libdir
	@/bin/echo -e "RM\tgenerated documentation"
	-rm -rf doc/api/*
	-rm -f java-gnome-*.tar.bz2
	@/bin/echo -e "RM\ttemporary directories"
	-rm -rf tmp generated
	@/bin/echo -e "RM\tglade cruft"
	find . -name '*.glade.bak' -o -name '*.gladep*' -type f | xargs rm -f

#
# A convenience target to run the code formatter built into Eclipse. This is
# for people who don't use the Eclipse Java IDE as their editor so they
# can normalize their code and ensure clean patches are submitted.
#

ifdef ECLIPSE
else
ECLIPSE=/usr/bin/eclipse
endif

ifdef V
ECLIPSE:=$(ECLIPSE) -verbose
else
endif

format: all
	@/bin/echo -e "FORMAT\tsrc/ tests/ doc/examples/"
	touch -r src/bindings/org/freedesktop/bindings/Version.java tmp/stamp/version
	$(ECLIPSE) -nosplash \
		-application org.eclipse.jdt.core.JavaCodeFormatter \
		-config .settings/org.eclipse.jdt.core.prefs \
		src/ tests/ doc/examples/ \
		$(REDIRECT)
	touch -r tmp/stamp/version src/bindings/org/freedesktop/bindings/Version.java
	

# vim: set filetype=make textwidth=78 nowrap:
