<?php
/*
 * debian.php
 *
 * Copyright (c) 2006-2008 Operational Dynamics Consulting Pty Ltd
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
<title>Install java-gnome on a Debian Linux system</title>
<meta name="author" content="Andrew Cowie">
</head>
<body>
<?
	template_begin();
?>
<h1 class="title">Installing java-gnome on Debian</h1>

<p>The following command should install the latest released version of the
bindings library:

<pre>
# apt-get install libjava-gnome-java
</pre>

<p>This will install the Debian package corresponding to the current 
release of java-gnome 4.0.<span class="x">x</span>.

<p>
Please note: java-gnome is an active and vibrant project; the bug fixes and
new code are in the latest release. Older versions are <b>not</b> superior by
virtue of having been "out there" longer.</p>

<p>The java-gnome user interface toolkit depends on very modern versions of
GLib, GTK, and the other libraries in the GNOME stack. If you have GNOME
2.20.<span class="x">x</span> or newer installed you should have all the
necessary prerequisites.</p>

<p>
<i>Debian wraps all Java packages as</i> <code>lib...-java</code> <i>so,
somewhat to our chagrin,</i> <code>libjava-gnome-java</code> <i>it is.</i>


<?
	template_end();
?>
</body>
</html>

<!-- vim: set textwidth=78: -->
