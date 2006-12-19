<?php
/*
 * template.inc
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
<title>Opening GTK and GNOME to Java Programmers</title>
<meta name="author" content="Andrew Cowie">
</head>
<body>
<?
	template_begin();
?>
<h1 class="title">The java-gnome langugage bindings project</h1>

<p>Since 1998, the java-gnome project has been offering Java bindings for the outstanding
GTK widget toolkit and the for the rich family of libraries making up the 
GNOME desktop.</p>

<a href="/4.0/">java-gnome 4.0</a><br/>
<a href="/2.x/">java-gnome 2.x</a>

<?
	$bottom_message = "The amazing java-gnome logo copyright &copy; 2004 by
	Joao Victor, used with permission. This website is hosted at
	SourceForge!";

	template_end();
?>
</body>
</html>
