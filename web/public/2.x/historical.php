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
	border: dashed 3px red;
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
SIGSEGV
</span>
<p>
The java-gnome 2.<span class="x">x</span> codebase is abandonded
and not being developed or supported by anyone. Do not use!
</p>
</div>

<p>This page is for historical reference only.</p>

<h2>Last Releases</h2>

java-gnome <code>2.16.2</code> was the last release of the <code>2.</code><span
class="x">x</span> series. It consisted of the following libraries:

<pre style="background-color: white; color: black;">
	glib-java     0.4.2
	cairo-java    1.0.7
	libgtk-java   2.10.2
	libgnome-java 2.12.7
	libglade-java 2.12.8
	libgconf-java 2.12.6
	libvte-java   0.12.3
</pre>

<p>Do not use this code. Despite the version numbers above, coverage never made
it past GTK 2.6. Each of the packages making up java-gnome <code>2.16.2</code>
have been <b>declared end-of-life</b> and the APIs used have been formally
deprecated. It is no longer being maintained by anyone from the previous or
present java-gnome teams. As these bindings have been abandoned, contributed
patches will not be accepted.</p>

<p>Several other libraries are in the old java-gnome family; none of them
achieved release quality, were abandoned as early as java-gnome 2.8, and
probably don't build anyway. Don't even think about trying to use them:</p>

<pre style="background-color: white; color: black;">
	libeds-java   0.5.1
	libbonobo-java
	libgtkhtml-java
	libgtkmozembed-java
	libgnomevfs-java
	libgst-java
</pre>

<h2>Migrating old applications</h2>

<p>There is a complete API break between java-gnome 2.<span class="x">x</span>
and java-gnome 4.0. While the style of coding will be
familiar, package space, classes, method names, and arguments are at times very
different. This was necessary in order to ensure an algorithmic mapping of the
underlying libraries, something sadly lacking in 2.<span class="x">x</span>.
There are also some wholesale API redesigns, with signal handling in particular
being completely changed. Finally, unlike the old library, internals that were
previously exposed are not publicly visible and not part of the public API.

<p>Also, you might note that unlike the above list of libraries with
inconsistent names, java-gnome 4.0 ships as a single unified source
release.</p>

<p>There is a considerable installed base of software written in java-gnome,
but much of it is in-house applications that do not have public open source
code bases. As such there will be little community support forthcoming to help
such people upgrade those applications to the new java-gnome APIs.
Operational Dynamics has considerable expertise in doing such migrations and
would be pleased to talk to you on a commercial basis about porting your
existing code to java-gnome 4.0.</p>

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
