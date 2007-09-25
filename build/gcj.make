#
# java.make, part of the Equivalence setup used by java-gnome
#
# Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd 
# 
# The code in this file, and the library it is a part of, are made available
# to you by the authors under the terms of the "GNU General Public Licence,
# version 2". See the LICENCE file for the terms governing usage and
# redistribution.
#

#
# This doesn't necessarily have to be separate, but it's cleaner this way
# This runs relative to the project root
#
# Just so you know, the GCJ build is bit rotting a bit. Not to say it's not
# useful, or important, but no one is actively using it at the moment so it
# might have some glitches.
#

-include .config

all: tmp/libgtkjava-$(APIVERSION).so

.SECONDARY: tmp/native/gtk.o

tmp/native/gtk.o: tmp/gtk-$(APIVERSION).jar
	@echo "$(GCJ_CMD) $@"
	$(GCJ) \
		-classpath src/bindings:tmp/bindings \
		-o $@ -c $<

tmp/libgtkjava-$(APIVERSION).so: tmp/native/gtk.o
	@echo "$(GCJ_LINK_CMD) $@"
	$(GCJ_LINK) \
		-Wl,-rpath=$(JAVAGNOME_HOME)/lib \
		-L$(JAVAGNOME_HOME)/lib \
		-o $@ $<
#	@echo "STRIP     $@"
#	strip --only-keep-debug $@

# vim: set filetype=make textwidth=78 nowrap:
