#! env python
#
# codegen.py
# 
# Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd
# Copyright (c) 2007      Srichand Pendyala
# Python rewrite of the Java-GNOME-4.0 code generator

import re

RED="\033[31;01m"
BROWN="\033[31;02m"
LIGHTGREEN="\033[32;01m"
GREEN="\033[32;02m"
LIGHTYELLOW="\033[33;01m"
YELLOW="\033[33;02m"
BLUE="\033[34;01m"
NAVY="\033[34;02m"
MAGENTA="\033[35;01m" #BOLD?
PURPLE="\033[35;02m"
CYAN="\033[36;01m"
TURQUOISE="\033[36;02m"
WHITE="\033[37;01m"
GRAY="\033[37;02m"
BLACKUNDERLINE="\033[38;01m"
GRAYUNDERLINE="\033[38;02m"
NORMAL="\033[0m"

types = dict()


types['gfloat'] = dict([
	('phylum', "fundamental"),
	('java', "float"),
	('translation', ""),
	('native', "float"),
	('jni', "jfloat")
		])
types['gfloat*'] = dict([
	('phylum', "fundamental-outparameter"),
	('java', "float[]"),
	('translation', ""),
	('native', "float[]"),
	('jni', "jfloatArray")
		])
types['gboolean'] = dict([
	('phylum', "fundamental"),
	('java', "boolean"),
	('translation', ""),
	('native', "boolean"),
	('jni', "jboolean")
		])
types['guint'] = dict([
	('phylum', "fundamental"),
	('java', "int"),
	('translation', ""),
	('native', "int"),
	('jni', "jint")
		])
types['gint'] = dict([
	('phylum', "fundamental"),
	('java', "int"),
	('translation', ""),
	('native', "int"),
	('jni', "jint")
		])
types['gulong'] = dict([
	('phylum', "fundamental"),
	('java', "long"),
	('translation', ""),
	('native', "long"),
	('jni', "jlong")
		])
types['glong'] = dict([
	('phylum', "fundamental"),
	('java', "long"),
	('translation', ""),
	('native', "long"),
	('jni', "jlong")
		])
types['gchar*'] = dict([
	('phylum', "fundamental"),
	('java', "String"),
	('translation', ""),
	('native', "String"),
	('jni', "jstring")
		])


def warning(*args):
	print YELLOW
	for arg in args:
		print arg
	print NORMAL
	
def spit_types():
	global types
	for typething in  types.values():
		print typething
	
def javaEventName(var): 
	signal = var.upper()
	signal = signal.replace('-', '_')
	return signal

class block:
	def __init__(self, block_content):
		self.lines = block_content
		self.characteristics = {}
		self.parameters = {}
		self.inparam = False
		self.lines = block_content.split("\n")
		self.phylum = ""
		self.thing = ""
		self.g_class = ""
		self.g_module = ""
		for line in self.lines:
			# Now that we have the block, lets get the first line, viz. the Phylum and Thing
			match = re.search("^\(define-(\w+)\s+(\w+)", line)
			if match:
				#print "Phylum " + match.group(1)
				#print "Thing  " + match.group(2)
				self.phylum = match.group(1)
				self.thing = match.group(2)
			# If its not the first line, a characteristic perhaps?
			match = re.search("^\s+\((\S+)\s+\"?([\w\-\*]+)\"?\)", line)
			if match:
				if not self.phylum and not self.thing:
					warning ("Whoops! Characteristic found outside of define!")
					exit
				charac = match.group(1)
				data = match.group(2) # Officially voted the worst variable name ever!
				self.characteristics[charac] = data
			# If its not the characteristic line either, must be a parameters sub block?
			
			match = re.search("^\s+\(parameters", line)
			if match:
				if not self.phylum and not self.thing:
					warning("Whoops! Found parameter declaration outside a parameters block!")
				if self.inparam:
					warning("Whoops! Found parameter declaration inside a parameters block!")
				self.inparam = True

			match = re.search("^\s+'?\(\"?([\w\-\*]+)\"?\s+\"?([\w]+)\"?\)", line)
			if match:
				param = match.group(1)
				value = match.group(2)
				if not self.phylum:
					warning("Whoops! Found a parameter declaration outside a parameters block!")
				elif self.inparam:
					self.parameters[param] = value
			match = re.search("^\s+\)", line)
			if match:
				if self.inparam:
					pass

	def getCharac(self, charac):
		if self.characteristics.has_key(charac):
			return self.characteristics[charac]
	
	def getPhylum(self):
		return self.phylum
	
	def getThing(self):
		return self.thing
	
	def getParam(self, param):
		if self.parameters.has_key(param):
			return self.parameters[param]
		
	def setParam(self, param, value):
		self.parameters[param] = value
		
	def parseMe(self):
		#print "Looking at " + self.getPhylum()
		if self.getPhylum() == "object": 
			self.g_class = self.getCharac("c-name") + "*"
			self.g_module = "org.gnome." + self.getCharac("in-module").lower()
			
			print self.g_module
			types[self.g_class] = dict([
				('phylum', self.getPhylum()),
				('java', self.getThing()),
				('translation', "pointerOf"),
				('native', "long"),
				('jni', "jlong"),
				('package', self.g_module)
			])
			
		elif self.getPhylum() == "enum":
			self.g_class = self.getCharac("c-name")
			print "Found Enum\t" , self.g_class
			types[self.g_class] = dict([
				('phylum', self.getPhylum()),
				('java', self.getThing()),
				('translation', "numOf"),
				('native', "int"),
				('jni', "jint"),
				('package', "org.gnome." + self.g_module)
			])
			
			print "For the enum its ", types[self.g_class]
			
		elif self.getPhylum() == "function":
			
			self.g_class = self.getCharac("is-constructor-of")
			print "Found function\t" , self.g_class
			if self.g_class:
				print "Will write constructor!"
				
		elif self.getPhylum() == "method":
			print "Found method:\t", self.g_class
			if self.getCharac("of-object"):
				self.g_class = self.getCharac("of-object")
				self.g_class = self.g_class + "*"
				self.setParam("self", self.g_class)
				
		elif self.getPhylum() == "virtual":
			self.g_class = self.getCharac("of-object")
			print "Will write signal handler " + javaEventName(self.thing)
				
	

# This is a Java/JNI code generator.
# Warning: HIGHLY incomplete! Use at your own risk! You have been warned!

class codegenerator:
	def __init__(self, ablock):
		# For a given block, we need to generate a few things:
		#0. Appropriate files for these:
		#1. The Translation layer code
		#2. The JNI layer code
	
		self.block = ablock
		self.j_class = self.block.g_class.rstrip("*")
		print "jclass "+ self.j_class + " from gclass " + self.block.g_class
		self.j_package = types[self.block.g_class]["package"]
		#print self.j_class
		if(self.block.getPhylum() == "method"):
			print "Generating method: " + self.block.getCharac("c-name")
			
		self.makefiles()
		self.spit_translation_layer()
		self.spit_jni_layer()
	
	def makefiles(self):

		gendir = "tmp/"
		java_filename = "Gtk" + self.block.getThing() + ".java"
		java_filename = gendir + self.j_package.replace(".", "/") + "/" + java_filename
		jni_filename = gendir + self.j_package.replace(".", "/") + "/" + "Gtk" + self.block.getThing() + ".c"
		
		print "Java file:\t", java_filename
		print "JNI file:\t", jni_filename
		
	def spit_translation_layer(self):
		print "This is the stub to build the translation layer"
		
		pass
	def spit_jni_layer(self):
		print "This is the stub to build the JNI layer"
		pass
	
class defsfile:
	def __init__(self, filename):
		self.blocks = []
		self.parsedblocks = []
		self.f = open(filename, "r")
		self.blockize()
		self.printblocks()
		self.generate()
		
	def blockize(self):
		myblock = ""
		#self.blocks
		for line in self.f:
			#strip comments and whitespace
			if re.search(r"^;;", line) or re.search(r"^\s+$", line):
				pass
			elif not re.search(r"^\s*\)\s*", line):
				myblock += line
			else:
				myblock += ")"
				self.blocks.append(myblock)
				myblock = ""
	
	def printblocks(self):
		#for myblock in self.blocks:
		b = block(self.blocks[1])
		b.parseMe()
		self.parsedblocks.append(b)	
			
	def pickleblocks(self):
		#Does there exist a need to pickle blocks?
		# WARNING: Debate on this!
		#
		pass
	
	def generate(self):
		for myblock in self.parsedblocks:
			c = codegenerator(myblock)
		c = codegenerator(self.parsedblocks[0])
		#pass
	
if __name__ == "__main__":
	defs = defsfile("../defs/GtkButton.defs")
	defs.printblocks()
#spit_types()
