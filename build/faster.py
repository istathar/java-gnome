#!/usr/bin/env python

#
# faster.py, an abreviated build script for java-gnome
# Must be invoked from the project top level directory as ./build/faster.py
#
# Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd 
# 
# The code in this file, and the library it is a part of, are made available
# to you by the authors under the terms of the "GNU General Public Licence,
# version 2". See the LICENCE file for the terms governing usage and
# redistribution.
#

#
# This is a total hack, but a good one :) Make has two critical weakness, which
# this script, being programmatic, tries to address:
#
# 1) variables are only populated once, so the variables containing the lists
# of files to be built resulting from output of the code generator are
# inaccurate (or in a clean build, empty)
#
# 2) it doesn't look at actual file contents, only looking to see if the target
# is older than the source file(s). This is a real problem for people hacking
# on the bindings; everytime you save a source file in the generator, it runs,
# and even though most (even all) of output files are unchanged [ie, were
# refilled with exactly the same content], they are newer, and so make charges
# ahead with a full rebuild, costing over 5 minutes of CPU time.
#
# So this program takes an md5sum of each source file at each step, only
# invoking the extermal program for that target if 
#
# No, this is not some grand replacement for all the worlds problems. It's a
# quick hack. I said so already. And it's entirely custom for building
# java-gnome. But it encapsulates some of the capabilites that buildtool will
# bring to the process when it lands, so it is a step in the right direction.
#

import os, md5, subprocess, pickle, sys
from os.path import *
from shutil import move

hashes = {}
verbose = False

hashFile = "tmp/.hashes"

GTK_MODULES = "gthread-2.0 glib-2.0 gtk+-2.0 gtk+-unix-print-2.0 libglade-2.0"


def loadHashes():
	global hashes
	if isfile(hashFile):
		try:
			db = open(hashFile, "rb")
			hashes = pickle.load(db)
			db.close()
		except (EOFError, KeyError, IndexError):
			print "build checksum cache corrupt; full rebuild forced"
			hashes = {}


def checkpointHashes():
	db = open(hashFile + ".tmp", "wb")
	pickle.dump(hashes, db)
	db.close()

	move(hashFile + ".tmp", hashFile)


def ensureDirectory(dir):
	if isdir(dir):
		return
	executeCommand("MKDIR", dir, "mkdir -p " + dir)
	

def touchFile(file):
	f = open(file, "w")
	f.close()


def prepareBindingsDirectories():
	ensureDirectory("tmp/native/")
	ensureDirectory("tmp/stamp/")
	ensureDirectory("generated/bindings/")
	ensureDirectory("tmp/bindings/")
	ensureDirectory("tmp/generator/")
	ensureDirectory("tmp/objects/")
	ensureDirectory("tmp/include/")
	ensureDirectory("tmp/tests/")

def prepareTestDirectories():
	ensureDirectory("tmp/tests/")

def findFiles(baseDir, ext):
	result = []
	for (root, dirs, files) in os.walk(baseDir):
		for file in files:
			if file.endswith(ext):
				result.append(join(root, file))
	return result


#
# Scan a list of files and decide if they need [re]-building.
#
# Two things to check:
# 1) target older?
# 2) if so, has source changed?
#
# Otherwise, (no target), just

# *) has source changed?
#
# We compare source files' md5sums against the values we have stored in our
# hash dictionary. The dictionary is immediately updated but this only has any
# persistant effect if a checkpoint happens after a command is run
# successfully. FIXME verify!
#
# Takes a list of touples mapping candidate source files to target filenames
#

def sourceChanged(file, hash):
	if hashes.has_key(file):
		if hashes[file] == hash:
			return False
	return True


def updateHash(file, hash):
	hashes[file] = hash


def debug(args):
	if False:
		print args


def filesNeedBuilding(list):
	changed = []
	for (source, target) in list:
		if fileNeedsBuilding(source, target):
			changed.append(source)
	return changed


def fileNeedsBuilding(source, target):
	if not isfile(source):
		sys.exit(source + " missing, abort")

	f = open(source)
	m = md5.new(f.read())
	f.close()
	hash = m.hexdigest()

	debug("CHECK\t"+str(target)+" from "+source)

	debug("TARGET",)
	if not isfile(target):
		debug("MISSING",)
	elif getmtime(target) < getmtime(source):
		debug("OLDER,")
		if not sourceChanged(source, hash):
			debug("SOURCE UNCHANGED")
			return False
	else:
		debug("NEWER, SKIP")
		return False

	debug("BUILD")
	updateHash(source, hash)

	return True

#
# common use case that source files transform predictably 1:1 into target
# files. Return a list of (source, target) touples
#

def dependsMapSourceFilesToTargetFiles(sourceDir, sourceExt, targetDir, targetExt):
	list = findFiles(sourceDir, sourceExt)
	result = []

	for source in list:
		target = source.replace(sourceDir, targetDir)
		target = target.replace(sourceExt, targetExt)

		pair = (source, target)
		result.append(pair)
	
	return result

#
# single target depends on many files. Use this with a stamp if all you really
# want to do is check to see if a series of sources have changed
#

def dependsListToSingleTarget(list, target):
	result = []

	for source in list:
		pair = (source, target)
		result.append(pair)

	return result


def executeCommand(short, what, cmd, inDir=None):
	print short + "\t" + what
	if verbose:
		print cmd
	
	status = subprocess.call(cmd, shell=True, cwd=inDir)
	if status != 0:
		sys.exit(1)

	checkpointHashes()


def compileJavaCode(outputDir, classpath, sourcepath, sources):
	cmd = "/opt/sun-jdk-1.5.0.12/bin/javac -source 1.4 -target 1.4"
	cmd += " -d " + outputDir
	if classpath:
		   cmd += " -classpath " + classpath
	if sourcepath:
		cmd += " -sourcepath " + sourcepath
	cmd += " " + " ".join(sources)

	blurb = ""
	for path in sourcepath.split(":"):
		if blurb:
			blurb += ", "
		blurb += path + "*.java"

	executeCommand("JAVAC", blurb, cmd)


def runJavaClass(classname, classpath, librarypath=""):
	cmd = "/opt/sun-jdk-1.5.0.12/bin/java "
	cmd += "-classpath " + classpath + " "
	cmd += "-ea "
	if librarypath:
		cmd += "-Djava.library.path=" + librarypath + " "
	cmd += classname
	if not librarypath and not verbose:
		cmd += " >/dev/null"

	executeCommand("JAVA", classname, cmd)


def compileGeneratorClasses():
	pairs = dependsMapSourceFilesToTargetFiles("src/generator/", ".java", "tmp/generator/", ".class")
	changed = filesNeedBuilding(pairs)
	if not changed:
		return
	compileJavaCode("tmp/generator/", "", "src/generator/", changed)


def generateTranslationAndJniLayers():
	list = findFiles("tmp/generator", ".class")
	list += findFiles("src/defs", ".defs")
	stamp = "tmp/stamp/generator"

	pairs = dependsListToSingleTarget(list, stamp)

	changed = filesNeedBuilding(pairs)
	if not changed:
		return

	runJavaClass("BindingsGenerator", "tmp/generator/", "")
	touchFile(stamp)


def compileBindingsClasses():
	pairs = dependsMapSourceFilesToTargetFiles("src/bindings/", ".java", "tmp/bindings/", ".class")
	pairs += dependsMapSourceFilesToTargetFiles("generated/bindings/", ".java", "tmp/bindings/", ".class")

	changed = filesNeedBuilding(pairs)
	if not changed:
		return

	compileJavaCode("tmp/bindings/", "tmp/bindings/", "src/bindings/:generated/bindings/", changed)


#
# This seems like a lot of effort to copy a file
#

def copyMappingFile():
	source = "mockup/bindings/typeMapping.properties"
	target = "tmp/bindings/typeMapping.properties"

	if not fileNeedsBuilding(source, target):
		return

	cmd = "cp " + source + " " + target
	executeCommand("CP", target, cmd)


def makeJarFile():
	jar = "tmp/gtk-4.0.jar"

	list = findFiles("tmp/bindings/", ".class")
	list += findFiles("tmp/bindings/", ".properties")
	pairs = dependsListToSingleTarget(list, jar)

	changed = filesNeedBuilding(pairs)
	if not changed:
		return

	files = [] 
	for file in list:
		file = file.replace("tmp/bindings/", "")
		file = file.replace("$", "\$")
		files.append(file)

	cmd = "/opt/sun-jdk-1.5.0.12/bin/jar cf "
	cmd += "../../" + jar + " "
	cmd += " ".join(files)

	executeCommand("JAR", jar, cmd, "tmp/bindings/")


def generateHeaderFiles():
	list = findFiles("tmp/bindings/", ".class")
	map = {}
	pairs = []
	classes = []

	for file in list:
		if file.find("$") != -1:
			continue
		t = file.replace("tmp/bindings/", "")
		t = t.replace(".class", "")
		c = t.replace("/", ".")

		t = t.replace("/", "_")
		t = t.replace("$", "_")
		t = t + ".h"
		t = "tmp/include/" + t

		pairs.append((file, t))
		map[file] = c

	changed = filesNeedBuilding(pairs)
	if not changed:
		return

	for file in changed:
		classes.append(map[file])
	
	cmd = "/opt/sun-jdk-1.5.0.12/bin/javah -jni "
	if verbose:
		cmd += "-verbose "
	cmd += "-d tmp/include/ "
	cmd += "-classpath tmp/bindings/ "
	cmd += " ".join(classes)
	if not verbose:
		cmd += " >/dev/null"

	executeCommand("JAVAH", "tmp/include/*.h", cmd)


def compileCSourceToObject(source, target):

	ensureDirectory(dirname(target))

	cmd = "/usr/bin/ccache /usr/bin/gcc -g -Wall -fPIC "
	cmd += "-I/opt/sun-jdk-1.5.0.12/include -I/opt/sun-jdk-1.5.0.12/include/linux "
	cmd += "-Isrc/jni -Itmp/include "
	cmd += "-Wno-int-to-pointer-cast -Wno-pointer-to-int-cast "
	cmd += "`pkg-config --cflags " + GTK_MODULES + "` "
	cmd += "-o " + target + " -c " + source

	executeCommand("GCC", source, cmd)


def compileBindingsObjects():
	pairs = dependsMapSourceFilesToTargetFiles("src/bindings/", ".c", "tmp/objects/", ".o")
	pairs += dependsMapSourceFilesToTargetFiles("generated/bindings/", ".c", "tmp/objects/", ".o")
	pairs += dependsMapSourceFilesToTargetFiles("src/jni/", ".c", "tmp/objects/", ".o")
	
	for (source, target) in pairs:
		if fileNeedsBuilding(source, target):
			compileCSourceToObject(source, target)

	
def linkSharedLibrary():
	so = "tmp/libgtkjni-4.0.so"

	list = findFiles("tmp/objects/", ".o")
	pairs = dependsListToSingleTarget(list, so)

	changed = filesNeedBuilding(pairs)
	if not changed:
		return

	cmd = "/usr/bin/gcc -g -Wall -fPIC "
	cmd += "-shared "
	cmd += "`pkg-config --libs " + GTK_MODULES + "` "
	cmd += "-o " + so + " "
	cmd += " ".join(list)

	executeCommand("LINK", so, cmd)


def compileTestClasses():
	pairs = dependsMapSourceFilesToTargetFiles("tests/generator/", ".java", "tmp/tests/", ".class")
	pairs += dependsMapSourceFilesToTargetFiles("tests/bindings/", ".java", "tmp/tests/", ".class")
	pairs += dependsMapSourceFilesToTargetFiles("tests/prototype/", ".java", "tmp/tests/", ".class")

	changed = filesNeedBuilding(pairs)
	if not changed:
		return

	compileJavaCode("tmp/tests/", "tmp/generator:tmp/bindings/:/usr/share/junit/lib/junit.jar", "tests/generator/:tests/bindings/:tests/prototype/", changed)



#
# main build sequence, with elaborately named methods Carl Rosenberger style
#

def generateBindings():
	prepareBindingsDirectories()

	compileGeneratorClasses()
	generateTranslationAndJniLayers()

	compileBindingsClasses()
	copyMappingFile()

	makeJarFile()

	generateHeaderFiles()
	compileBindingsObjects()
	linkSharedLibrary()


def generateTests():
	prepareTestDirectories()
	compileTestClasses()


def runTests():
	runJavaClass("UnitTests", "tmp/gtk-4.0.jar:tmp/generator/:tmp/tests/:/usr/share/junit/lib/junit.jar", "tmp/")

def runDemo():
	runJavaClass("Experiment", "tmp/gtk-4.0.jar:tmp/tests/", "tmp/")


#
# prelininary setup
#

from sys import argv

if __name__ == '__main__':
	if os.getenv("V"):
		verbose = True

	loadHashes()

	generateBindings()

	if len(argv) > 1 and sys.argv[1] == "test":
		generateTests()
		runTests()

	if len(argv) > 1 and sys.argv[1] == "demo":
		generateTests()
		runDemo()

	if len(argv) > 1 and sys.argv[1] == "eclipse":
		generateTests()

