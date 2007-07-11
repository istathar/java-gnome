#!/usr/bin/env python

import os, md5, subprocess

hashes = {}
verbose = False

def runMkdir(dir):
	pass

def prepareDirectories():
	runMkdir("tmp/stamp")
	runMkdir("generated/bindings")
	runMkdir("tmp/bindings")
	runMkdir("tmp/generator")
	runMkdir("tmp/native")
	runMkdir("tmp/objects")
	runMkdir("tmp/include")
	runMkdir("tmp/tests")


def findJavaSourceFiles(baseDir):
	result = []
	for (root, dirs, files) in os.walk(baseDir):
		for file in files:
			if file.endswith(".java"):
				result.append(os.path.join(root, file))
	return result


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
	print status

def compileJavaCode(outputDir, classpath, sourcepath, sources):
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

	prepareDirectories()
	generateBindings()


