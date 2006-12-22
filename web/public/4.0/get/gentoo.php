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
# emerge >=dev-java/java-gnome-4.0
</pre>
<?
	template_end();
?>
</body>
</html>

<!-- vim: set textwidth=78: -->
