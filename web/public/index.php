<?php
/*
 * index.php
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
<title>Opening GTK and GNOME to Java Programmers</title>
<meta name="author" content="Andrew Cowie">
<meta name="author" content="Katrina Ross">
<meta name="verify-v1" content="Tw3DRid/hBqoKxSEHYqegnqwJ9wTs+quVgulejkk5E0=" />
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
<h1 class="title">The java-gnome User Interface Library</h1>
<div class="bluepanel"></div>

<div style="float:right; width: 40%; padding-right: 50px; padding-bottom:
50px; padding-left: 50px;">

<p>Since 1998, the java-gnome project has been offering Java bindings for the
GTK widget toolkit and for the rich family of libraries making up the GNOME
desktop.</p>

<p>In 2006, a complete re-engineering of the bindings was initiated, and
that work has resulted in:

<p style="padding-left: 50px;">
<a class="subject" href="/4.0/"><img align="texttop" src="/images/openoffice_ImpressNextSlide.jpg" style="padding-right: 10px;" border="0"></a>
<a class="subject" href="/4.0/">java-gnome 4.0</a>
</p>

<p>
An outstanding experience for developers using the library along with high
standards for internal quality, steadily increasing breadth of coverage, and
excellent performance are the hallmarks of the project. We are pleased to
maintain this effort as a part of the GNOME development platform, and hope
you'll find our work of interest!
</p>
<hr>
<p>The old libraries are long since abandoned. For historical interest only,
we have few notes on the previous project:

<p style="padding-left: 50px;">
<a class="subject" href="/2.x/"><img align="texttop" src="/images/openoffice_ImpressNextSlide.jpg" style="padding-right: 10px;" border="0"></a>
<a class="subject" href="/2.x/">java-gnome 2.<span class="x" style="padding-left: 3px;">x</span></a>
</p>


</div>

<div style="font-size: 30px; padding-left: 10px;
padding-top: 80px; padding-right: 80px;">
<center>
	<img src="/images/cross-product.png" width="420" height="120"><br>
	<span class="italics">Write outstanding</span>
	<span style="font-size: 24px; font-weight: bold">GTK</span>
	<span class="italics">based</span>
	<span style="font-size: 24px; font-weight: bold">GNOME</span>
	<span class="italics">programs ... from Java!</span>
</center>
</div>

<div style="padding-top:50px;"></div>


<?
	$bottom_message = "The amazing java-gnome logo is copyright &copy;
	2004 by Joao Victor, used with permission. GNOME and the foot logo are
	trademarks of the <a class=\"black\"
	href=\"http://foundation.gnome.org/\">GNOME Foundation</a>. Java is a
	trademark of <a class=\"black\" href=\"http://java.sun.com/\">Sun
	Microsystems</a>, Inc. The java-gnome project website is hosted at
	SourceForge";

	template_end();
?>
</body>
</html>
<?
# vim: set textwidth=78 nowrap:
?>
