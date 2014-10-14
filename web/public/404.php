<?php
/*
 * 404.php
 *
 * Copyright (c) 2006-2007 Operational Dynamics Consulting Pty Ltd
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
<title>Page not found</title>
<meta name="author" content="Andrew Cowie">
</head>
<body>
<?
	template_begin();
?>
<h1 class="title">Doh!</h1>
<div class="bluepanel"></div>
<h2 style="font-family: Arial, sans;">404 Not Found</h1>

<p>Sorry, but the page you're looking for isn't here.</p>

<p>Our website was recently updated to reflect the migration to the new 4.0
generation of the java-gnome bindings.</p>

<?
	if (strstr($_SERVER["REQUEST_URI"], "cgi-bin/bin/view")) {
?>
<p>You may have been looking for a page on the wiki that was used to power the
old java-gnome 2.<span class="x">x</span> website. Unfortunately, the people
who set it up left the project without giving anyone instructions for its
maintenance or administration. As a result, the old website suffered from
heavy link spam and graffiti, considerably diluting its value.  Furthermore,
legitimate content was mostly out of date and often inaccurate or at least
unhelpful. That wiki has therefore been deactivated and removed.</p>

<p>We are now using the GNOME wiki, <a class="download"
href="http://live.gnome.org/Java/">live.gnome.org</a>, for community
contributed content. You are certainly welcome to make any changes you like
there!</p>

<?
	} else if ((strstr($_SERVER["REQUEST_URI"], "doc/javadoc")) ||
	           (strstr($_SERVER["REQUEST_URI"], "docs/javadoc"))) {
?>

<p>Public JavaDoc has been permanently moved. If you are pointing your own
JavaDoc at the canonical public reference for the java-gnome classes, the
correct URL to link to is as follows:

<ul>
<li><p>
<a href="http://java-gnome.sourceforge.net/4.0/doc/api/overview-summary.html"><code>http://java-gnome.sourceforge.net/4.0/doc/api/</code></a>
</p></li>
</ul>

<p>where <code>4.0</code> is the current API version. The standard
<code>pacakges-list</code> file is there.</p>

<?
	} else {
?>

<p>Chances are you have a URL that is either mislinked or which refers to
something that might have been in the old site. In particular, URLs for common
targets are as follows

<ul>
<li><p>API documentation root<br>
<a
href="http://java-gnome.sourceforge.net/doc/api/4.1/overview-summary.html"><code>http://java-gnome.sourceforge.net/doc/api/4.1/</code></a>
</p></li>

<li><p>Source code<br>
<a href="https://github.com/afcowie/java-gnome"><code>https://github.com/afcowie/java-gnome</code></a>
</p></li>

<li><p>Download tarballs<br>
<a href="http://ftp.gnome.org/pub/gnome/sources/java-gnome/4.1/"><code>http://ftp.gnome.org/pub/gnome/sources/java-gnome/4.1/</code></a>
</p></li>

</ul>
</p>

<?
	}
?>

<p>Fixes or contributions to the java-gnome website proper can be accomplished
by checking out the source code and sending a patch. See <a class="download"
href="/HACKING.html">HACKING</a>.</p>

<?

	template_end();
?>
</body>
</html>
<?
# vim: set textwidth=78 nowrap:
?>
