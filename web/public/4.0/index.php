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
<title>The new java-gnome bindings</title>
<meta name="author" content="Andrew Cowie">
<style>
div.box {
	background-color: #DDDDDD;
	padding: 15px;
	border: dashed 3px blue;
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
<h1 class="title">java-gnome 4.0</h1>

<div class="bluepanel">
	<a class="nav-white" href="objectives.php">Project Objectives</a>;
	<a class="nav-white" href="NEWS.html">Release notes (in
	<code>NEWS</code> file)</a>
</div>

<div class="box">
<span class="mono" style="font-size: xx-large; font-weight: bold;">
@since 4.0.6
</span>
<p>These are the Java bindings for GTK and GNOME! With a robust engineering
design and the machinery to generate the complete internals of the bindings in
place, the steadily increasing coverage of the underlying libraries means you
can use java-gnome to develop sophisticated user interfaces for your
applications.

</p>
</div>

<h2>Overview</h2>

<p>Making GTK and GNOME approachable from Java. That's what we do! Still
confused? <code>:)</code> No problem; the <a
href="objectives.php">objectives</a> page explains in greater detail.

<p>If you're new to java-gnome, then you want to jump straight to the <a
href="/4.0/doc/">documentation</a> section for everything you need to get
started.</p>

<p>The latest release notes are always available in the <code><a
href="/4.0/NEWS.html">NEWS</a></code> file; if you're already developing
applications that use java-gnome 4.0, that's where you want to look to catch up
on the latest changes. Other top level meta files include

<code><a href="/4.0/README.html">README</a></code>,
<code><a href="/4.0/HACKING.html">HACKING</a></code>,
<code><a href="/4.0/AUTHORS">AUTHORS</a></code>, and
<code><a href="http://research.operationaldynamics.com/bzr/java-gnome/mainline/LICENCE">LICENCE</a></code>


<h2>Rationale</h2>

<p>We believe that while the web is ideal for offering services, only carefully
tailored desktop applications can provide a truly rich user experience that is
both responsive and usable. Our choice of the GNOME Desktop is due to our
belief that it provides a great choice for long term growth. With a vibrant and
cosmopolitan user and developer community, GNOME is a broad platform that has
already proved itself suitable to widely diverse requirements ranging from
corporate desktops to embedded devices across its international audience.</p>

<p>java-gnome 4.0 was originally an initiative of <a
href="http://www.operationaldynamics.com/">Operational Dynamics</a>, a Change
Management consultancy active in the IT operations space. Although we started
using java-gnome in order to write tools to enable our clients to define and
work through procedures to successfully execute massive changes and upgrades,
we now find ourselves using java-gnome to write quality GNOME programs for many
aspects of our business and personal lives.

<p>We're pleased to have been joined by dozens of developers from around the
world who are likewise excited about the opportunity to write rich GNOME
applications using modern tools in a powerful language unencumbered by patents
held by companies who want to destroy the Free and Open Source Software
movement.

<p>java-gnome 4.0 is now a truly global effort with contributors from around
the world. There is still much work to be done, of course, so please feel
welcome to join in and contribute!

<h2>Warning</h2>

<p><i>Please note that we will not be declaring API stable (which, under the GNOME release definitions inevitably means "frozen forever") until java-gnome 4.2.0; method signatures are subject to change. This gives us an opportunity to learn from mistakes and to ensure the bindings reach the extreme quality and usability standards we aspire to.</i>

<?
	template_end();
?>
</body>
</html>

<!-- vim: set textwidth=78: -->
