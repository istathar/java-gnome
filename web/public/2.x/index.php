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
<title>java-gnome 2.x - DO NOT USE</title>
<style>
div.box {
	background-color: #DDDDDD;
	padding: 15px;
	border: dashed 3px orange;
	margin-left: 100px;
	margin-right: 100px;
	margin-top: 20px;
	font-size: x-large;
}
</style>
</head>
<body>
<?
	template_begin();
?>
<h1 class="title">java-gnome 2.<span class="x" style="padding-left:4px;">x</span></h1>


<div class="box">
<span class="mono" style="font-size: xx-large; font-weight: bold;">
@deprecated 
</span>
<p>
The java-gnome 2.<span class="x">x</span> series of releases have reached
end-of-life and are no longer being developed or supported by anyone. Do not
use!</p>
</div>

<p>The original java-gnome project was written by <b>five</b> successive teams
of hackers from 1998-2006. That's <b>nine</b> years!</p>

<p>Over the later years of this period, however, there was almost zero new
coverage activity over the last years of this period, and the code that was
there was full of cruft, inefficiency, bugs, and serious memory leaks. Its
codebase was spread across <b>seven</b>-plus libraries, and was quite
impossible to maintain. The project website was hideously out of date and the
quality of its examples poor. Worst of all, the API was exceedingly difficult
for new people to learn how to contribute to java-gnome 2.<span
class="x">x</span>. As a result, this code was abandoned by the java-gnome 
community in October 2006.</p>

<p>This page is for <a class="nav-black" href="historical.php">historical</a>
reference and to honour the hard work of the
fine team of <a class="nav-black" href="AUTHORS">contributors, hackers, and
maintainers</a> who wrote the first generations of the Java bindings for GTK
and GNOME. We owe them a debt of gratitude.</p>

<?
	$bottom_message = "The original 2.<span class=\"x\">x</span> bindings
	code, leading up the last 2.16 release, is copyright &copy; 1998-2006
	&#8220;The java-gnome Team&#8221 in defacto trust for the group of
	individual contributors";

	template_end();
?>
</body>
</html>

<!-- vim: set textwidth=78 -->
