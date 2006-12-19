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
<style>
.italics {
	font-family: "Times New Roman", serif;
	font-style: italic;
}
.sans {
	font-family: "Arial", sans-serif;
	font-style: normal;
}
</style>
</head>
<body>
<?
	template_begin();
?>
<h1 class="title">The java-gnome langugage bindings project</h1>

<div class="sans" style="font-size: 50px; padding: 10px; padding-top: 30px;">
<center>
	<span class="italics">...opening</span>
	GTK
	<span class="italics">and</span>
	GNOME
	<span class="italics">to Java programmers...</span>
</center>
</div>

<p>Since 1998, the java-gnome project has been offering Java bindings for the
outstanding GTK widget toolkit and the for the rich family of libraries making
up the GNOME desktop.</p>

<p>In 2006, Andrew Cowie and the staff of Operational Dynamics did a complete
re-engineering of the bindings and accordingly a re-write has begun. That work
is the focus of the project going forward:

<p style="padding-left: 50px;">
<a class="subject" href="/4.0/"><img align="texttop" src="/images/next1.jpg" style="padding-right: 10px;" border="0"></a>
<a class="subject" href="/4.0/">java-gnome 4.0</a>
</p>

<p>The original bindings are still available:

<p style="padding-left: 50px;">
<a class="subject" href="/2.x/"><img align="texttop" src="/images/next1.jpg" style="padding-right: 10px;" border="0"></a>
<a class="subject" href="/2.x/">java-gnome 2.<span class="x" style="padding-left: 3px;">x</span></a>
</p>

<?
	$bottom_message = "The amazing java-gnome logo copyright &copy; 2004 by
	Joao Victor, used with permission. This website is hosted at
	SourceForge. Java is a trademark of Sun Microsystems, Inc";

	template_end();
?>
</body>
</html>
<?
# vim: set textwidth=78 nowrap:
?>
