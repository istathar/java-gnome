<?php
/*
 * ubuntu.php
 *
 * Copyright (c) 2006-2009 Operational Dynamics Consulting Pty Ltd, and Others
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
<title>Install java-gnome on an Ubuntu Linux system</title>
<meta name="author" content="Andrew Cowie">
</head>
<body>
<?
	template_begin();
?>
<h1 class="title">Installing java-gnome on Ubuntu</h1>

<p>java-gnome is part of the official GNOME language bindings suite, and as
such should be present in any recent Ubuntu system.</p>


<p>The following command will install the latest released version of the
bindings library:

<pre>
# sudo apt-get install libjava-gnome-java
</pre>

<p>You should check that the version you're getting from Canonical matches the
latest release as shown above. You can check the available packages at <a
href="http://packages.ubuntu.com/search?keywords=libjava-gnome-java&searchon=names&exact=1&suite=all&section=all">list</a>
of Ubuntu releases and the java-gnome versions they provide. If out of date,
you may prefer to get java-gnome from a <a
href="https://launchpad.net/~respawneral/+archive/ppa/+build/1131323">PPA</a>
or build it from <a href="/4.0/README.html">source</a>.</p>

<p>As java-gnome 4.0 is a completely new from-the-ground-up implementation, it
depends on a very modern version of GLib, GTK, and the other libraries in the
GNOME stack. Being the remarkable Linux distribution it is, Ubuntu typically
ships very recent releases of GNOME, so you will certainly have all the
necessary prerequisites on any recently installed or upgraded system.</p>

<p>
<i>Debian wraps all Java packages as</i> <code>lib...-java</code> <i>so,
somewhat to our chagrin, they have named our bindings package </i>
<code>libjava-gnome-java</code> <i>and Ubuntu has inherited this.</i>.

<?
	template_end();
?>
</body>
</html>

<!-- vim: set textwidth=78: -->
