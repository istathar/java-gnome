<?php
/*
 * arch.php
 *
 * Copyright (c) 2007-2008 Operational Dynamics Consulting Pty Ltd, and Others
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
<title>Install java-gnome on an Arch system</title>
<meta name="author" content="Andrew Cowie">
</head>
<body>
<?
	template_begin();
?>
<h1 class="title">Installing java-gnome on Arch Linux</h1>

<p>Packages now exist to allow you to easily install the java-gnome library
on an Arch Linux system. The packages are in [community], so you need
to have the [community]-repository enabled.</p>

<p>The following command should install the latest released version of the
bindings:</p>

<pre>
# pacman -S java-gnome
</pre>

<p>To also install the docs, for use in <code>devhelp</code> for example, just
run:</p>

<pre>
# pacman -S java-gnome-docs
</pre>

<?
	template_end();
?>
</body>
</html>

<!-- vim: set textwidth=78: -->
