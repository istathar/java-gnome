<?php
/*
 * ubuntu.php
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
<title>Install java-gnome on an Ubuntu Linux system</title>
<meta name="author" content="Andrew Cowie">
</head>
<body>
<?
	template_begin();
?>
<h1 class="title">Installing java-gnome on Ubuntu</h1>

<p><span class="highlight">
TODO: This will work once someone actually packages java-gnome 4.0 for Ubuntu
</span></p>

<p>java-gnome is part of the official GNOME language bindings suite, and as
such should be present in any recent Ubuntu system.</p>

<p>The following command will install the latest released version of the
bindings library:

<pre>
# apt-get install java-gnome
</pre>

<p>As java-gnome 4.0 is a completely new from-the-ground-up implementation, it
depends on a very modern version of GLib, GTK, and the other libraries in the
GNOME stack. Being the remarkable Linux distribution it is, Ubuntu typically
ships very recent releases of GNOME, so you will certainly have all the
necessary prerequisites on any recently installed or upgraded system.</p>

<?
	template_end();
?>
</body>
</html>

<!-- vim: set textwidth=78: -->
