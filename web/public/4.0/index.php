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
<title>Re-engineering the java-gnome bindings</title>
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

<div class="box">
<p>The engineering design of java-gnome 4.0 is complete, and a working
prototype which will be the foundation of the new bindings has been released.
We are now looking to secure the revenue necessary to fund the work to make
the new Java bindings for GTK and GNOME a reality.</p>

</div>

<p>Making GTK and GNOME approachable from Java. That's what we do. See the <a
href="objectives.php">objectives</a> page.

<p>java-gnome 4.0 is an initiative of <a
href="http://www.operationaldynamics.com/">Operational Dynamics</a>, a Change
Management consultancy active in the mission critical IT operations space. We
believe that while the web is ideal for offering services, only carefully
tailored desktop applications can provide a truly rich user experience that is
both responsive and usable. Our choice of the GNOME Desktop is due to our
belief that it provides a great choice for long term growth. With a vibrant
and cosmopolitan user and developer community, GNOME is a broad platform that
has already proved itself suitable to widely diverse requirements ranging from
corporate desktops to embedded devices across its international audience.</p>

<?
	template_end();
?>
</body>
</html>

<!-- vim: set textwidth=78: -->
