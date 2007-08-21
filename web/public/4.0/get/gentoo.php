<?php
/*
 * gentoo.php
 *
 * Copyright (c) 2006 Operational Dynamics Consulting Pty Ltd
 * 
 * This file comprises part of the infrastructure and content of the
 * java-gnome project website. As such, it is conveyed alongside the source
 * code and is made available to you by its authors under the terms of the
 * "GNU General Public Licence, version 2". See the LICENCE file for the terms
 * governing usage, copying and redistribution.
 */

	require "template.inc";
?>
<html>
<head>
<?
	template_header();
?>
<title>Install java-gnome on a Gentoo system</title>
<meta name="author" content="Andrew Cowie">
</head>
<body>
<?
	template_begin();
?>
<h1 class="title">Installing java-gnome on Gentoo Linux</h1>

<p>Downloading, building, and installing java-gnome on Gentoo is simple and
fast. Of course it is. It's Gentoo!</p>

<p>The following command should install the latest released version of the
bindings library:

<pre>
# emerge &gt;=dev-java/java-gnome-4.0
</pre>

<h1>Use latest release!</h1>

<p>java-gnome is a rapidly moving project, and each release immediately
obsoletes the proceeding one. There is no reason to use anything other than the
latest release; we encourage you to use the package in <code>~arch</code> as
soon as a newer one is available.

<p>Thus it's probably a good idea to either add:

<pre>
&gt;=dev-java/java-gnome-4.0	~x86
</pre>

to <code>/etc/portage/package.keywords</code>, or to use
<code>ACCEPT_KEYWORDS</code>  on the emerge command line, for 
example:

<pre>
# ACCEPT_KEYWORDS=~x86 emerge java-gnome
</pre>


<h1>Getting variables from <code>java-config</code></h1>

<p>Somewhat unusually for Gentoo, there is a strict policy in place defining
the installed locations of Java libraries. Therefore you'll want to use the
<code>java-config</code> tool as a helper to find the java-gnome
<code>.jar</code> and <code>.so</code> files, for example:

<pre>
$ java-config -l java-gnome-4.0
/usr/share/java-gnome-4.0/lib/gtk-4.0.jar

$ java-config -i java-gnome-4.0
/usr/lib/java-gnome-4.0
</pre>

This implies that the prototypical command line to run java-gnome programs on
Gentoo will look something like:

<pre>
$ java \
	-classpath `java-config -l java-gnome-4.0` \
	-Djava.library.path=`java-config -i java-gnome-4.0` \
	package.ClassName
</pre>

which should resolve to something like:

<pre>
$ java \
	-classpath /usr/share/java-gnome-4.0/lib/gtk-4.0.jar \
	-Djava.library.path=/usr/lib/java-gnome-4.0 \
	package.ClassName
</pre>

Either will work.
<?
	template_end();
?>
</body>
</html>

<!-- vim: set textwidth=78: -->
