#!/usr/bin/env python

import os, md5, subprocess, pickle, sys

hashes = {}
verbose = False

hashFile = ".hashes"

def loadHashes():
	global hashes
	if os.path.isfile(hashFile):
		db = open(hashFile, "rb")
		hashes = pickle.load(db)
		db.close()



def checkpointHashes():
	db = open(hashFile, "wb")
	pickle.dump(hashes, db)
	db.close()


def ensureDirectory(dir):
	if os.path.isdir(dir):
		return
	executeCommand("MKDIR", dir, "mkdir -p " + dir)
	

def prepareDirectories():
	ensureDirectory("tmp/stamp")
	ensureDirectory("generated/bindings")
	ensureDirectory("tmp/bindings")
	ensureDirectory("tmp/generator")
	ensureDirectory("tmp/native")
	ensureDirectory("tmp/objects")
	ensureDirectory("tmp/include")
	ensureDirectory("tmp/tests")


def findJavaSourceFiles(baseDir):
	result = []
	for (root, dirs, files) in os.walk(baseDir):
		for file in files:
			if file.endswith(".java"):
				result.append(os.path.join(root, file))
	return result


#
# Scan a list of files and compare those files' md5sums against the values we
# have stored in our hash dictionary. The dictionary is immediately updated but
# this only has any persistant effect if a checkpoint happens after a command
# is run successfully.
#
def updatedFiles(list):
	changed = []

	for file in list:
		f = open(file)
		m = md5.new(f.read())
		f.close()
		hash = m.hexdigest()

		if hashes.has_key(file):
			if hashes[file] == hash:
				continue
		changed.append(file)
		hashes[file] = hash
	return changed


def executeCommand(short, what, cmd):
	if verbose:
		print cmd
	else:
		print short + "\t" + what
	
	status = subprocess.call(cmd, shell=True)
	if status != 0:
		sys.exit()

	checkpointHashes()


def compileJavaCode(outputDir, classpath, sourcepath, sources):
	if sources == []:
		return

	cmd = "/opt/sun-jdk-1.5.0.08/bin/javac"
	cmd += " -d " + outputDir
	cmd += " -classpath " + classpath
	cmd += " -sourcepath " + sourcepath
	cmd += " " + " ".join(sources)

	executeCommand("JAVAC", sourcepath + "*.java", cmd)


def compileGenerator():
	list = findJavaSourceFiles("src/generator/")
	changed = updatedFiles(list)
	compileJavaCode("tmp/generator/", "src/generator/", "src/generator/", changed)


def executeGenerator():
	pass


def generateBindings():
	compileGenerator()
	executeGenerator()



if __name__ == '__main__':
	if os.getenv("V"):
		verbose = True

	loadHashes()

	prepareDirectories()
	generateBindings()


