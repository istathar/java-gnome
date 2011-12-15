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
<title>The Java bindings for the GNOME Desktop</title>
<meta name="author" content="Andrew Cowie">
<style>
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
<h1 class="title">java-gnome 4.1</h1>

<div class="bluepanel">
	<a class="nav-white" href="objectives.php">Project Objectives</a>;
	<a class="nav-white" href="/NEWS.html">Release notes (in
	<code>NEWS</code> file)</a>
</div>

<img style="padding: 10px; float: right;" src="/doc/api/4.1/org/gnome/gtk/AboutDialog.png">
<p>
<i>Everyone loves a screenshot!</i>
</p>

<p>
These are the Java bindings for GTK and GNOME! Featuring a robust
engineering design, completely generated internals, a lovingly crafted layer
presenting the public API, and steadily increasing coverage of the underlying
libraries.
</p>

<p>
You can use java-gnome to develop sophisticated user interfaces for Linux
applications so that they richly integrate with the GNOME Desktop while
leveraging the power of the Java language and your expertise with it.
</p>

<h2>Overview</h2>

<p>If you're new to java-gnome, then you want to jump straight to the <a
href="/doc/">documentation</a> section for everything you need to get
started.</p>

<p>
The latest release notes are always available on the <code><a
href="/NEWS.html">NEWS</a></code> page. If you're already developing
applications that use java-gnome, that's where you want to look to catch up
on the latest changes.
</p>

<p>
Other top-level meta files include

<code><a href="/README.html">README</a></code>,
<code><a href="/HACKING.html">HACKING</a></code>,
<code><a href="/AUTHORS.html">AUTHORS</a></code>, and
<code><a href="/LICENCE.html">LICENCE</a></code>.
</p>

<p>
java-gnome has been around over 12 years! The current 4.x series was originally an initiative of Andrew Cowie at <a href="http://www.operationaldynamics.com/">Operational Dynamics</a>, who started
using java-gnome to write tools for executing massive changes and upgrades. java-gnome has since become a general library used by people writing GNOME programs for both their business and personal environments. We have <a href="http://www.ohloh.net/p/java-gnome/">contributors</a> from around the world; feel free to join in.
</p>

<?
	template_end();
?>
</body>
</html>

<!-- vim: set textwidth=78: -->
