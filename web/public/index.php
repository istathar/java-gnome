<?php
/*
 * index.php
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

<div style="float:right; width: 40%; padding-right: 50px;">

<p>Since 1998, the java-gnome project has been offering Java bindings for the
GTK widget toolkit and the for the rich family of libraries making up the
GNOME desktop.</p>

<p>In 2006, a complete re-engineering of the bindings was initiated and
accordingly a re-write of java-gnome is in progress. That work is the focus of
the project going forward:

<p style="padding-left: 50px;">
<a class="subject" href="/4.0/"><img align="texttop" src="/images/openoffice_ImpressNextSlide.jpg" style="padding-right: 10px;" border="0"></a>
<a class="subject" href="/4.0/">java-gnome 4.0</a>
</p>

<p>The original bindings are still available, though no longer maintained:

<p style="padding-left: 50px;">
<a class="subject" href="/2.x/"><img align="texttop" src="/images/openoffice_ImpressNextSlide.jpg" style="padding-right: 10px;" border="0"></a>
<a class="subject" href="/2.x/">java-gnome 2.<span class="x" style="padding-left: 3px;">x</span></a>
</p>


</div>

<div class="italics" style="font-size: 30px; padding-left: 10px;
padding-top: 30px; padding-right: 50px;">
<center>
	Write outstanding<br>
	<img src="/images/gtk_RGBLogo.png"><br>
	and<br>
	<!--<img src="/images/gnome_FootLogo.png"><br>-->
	<img style="padding:5px;" src="/images/gnome_ButtonLogo.png"><br>
	programs ... from <br>
	<img src="/images/java_CupLogo.png">
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
