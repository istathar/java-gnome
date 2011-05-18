<?php
/*
 * index.php
 *
 * Copyright (c) 2006-2011 Operational Dynamics Consulting, Pty Ltd
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
div.box {
	margin-top: 20px;
	font-size: x-large;
}
</style>
</head>
<body>
<?
	template_begin();
?>
<h1 class="title">The java-gnome User Interface Library</h1>
<div class="bluepanel"></div>

<div class="box" style="width: 70%; padding-right: 50px; padding-bottom:
50px; padding-left: 50px;">
<center>
	<img src="/images/cross-product.png" width="450" height="120">

<p>These are the Java bindings for GTK and GNOME. Featuring a robust
engineering design, completely generated internals, a lovingly crafted layer
presenting the public API, and steadily increasing coverage of the underlying
libraries.</p>

<p>You can use java-gnome to develop sophisticated user interfaces for Linux
applications so that they richly integrate with the GNOME Desktop while
leveraging the power of the Java language and your expertise with it.</p>

<img style="padding: 10px;" src="/doc/api/4.1/org/gnome/gtk/AboutDialog.png">

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
