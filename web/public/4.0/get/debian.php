<?php
/*
 * debian.php
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
<title>Install java-gnome on a Debian Linux system</title>
<meta name="author" content="Andrew Cowie">
</head>
<body>
<?
	template_begin();
?>
<h1 class="title">Installing java-gnome on Debian</h1>

<p><span class="highlight">
	TODO:
	Write an introductory blurb about installing software on Debian
</span></p>

<p>The following command should install the latest released version of the
bindings library:

<pre>
# apt-get install java-gnome
</pre>

<p>As java-gnome 4.0 is a completely new from-the-ground-up implementation, it
depends on a very modern version of GLib, GTK, and the other libraries in the
GNOME stack. If you have GNOME 2.16.<span class="x">x</span>) or newer you
will certainly have all the necessary prerequisites.</p>

<?
	template_end();
?>
</body>
</html>

<!-- vim: set textwidth=78: -->
