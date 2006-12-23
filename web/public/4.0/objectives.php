<?php
/*
 * objectives.php
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
<title>The objectives of the new java-gnome project</title>
<meta name="author" content="Andrew Cowie">
<style>
h2.goal {
	text-align: center;
	font-size: 40px;
	color: #597ba2;
}
</style>
</head>
<body>
<?
	template_begin();
?>
<h1 class="title">What We Do</h1>

<p>We have two objectives in the java-gnome project. First and foremost, we
seek to achieve:

<h2 class="goal">APPROACHABILITY</h2>

<p>You might think this goes without saying, but our target audience are
people who are Java programmers who happen to be using GNOME on Linux or
Solaris and who just might like to write an application and want it to look
good and therefore want to write it in GTK.</p>

<p>The existing pool of people with Java expertise is enormous and yet have
been largely ignored by the Open Source movement over the years. More than
anything, we'd like to ensure we give these people the power to write rich
desktop programs and clients, and in the process bring a whole new crowd of
people into the amazing world that is GNOME and Software Libre.</p>


<p>The GTK APIs themselves are powerful but with that richness comes
complexity and much detail that must be mastered.  In its native C, GNOME
programs are very nearly impenetrable, certainly exceedingly verbose, and
brutally difficult to debug, making it very difficult for people to learn how
to write applications. These are all areas where java-gnome is a great
improvement. We take every effort to make GTK easy to learn and as fun to
develop with as possible. The combination of a naturally object-oriented
language like Java along with the code completion features of a rich IDE like
Eclipse make for a really excellent development experience.</p>

<p>If we do our job right, then newcomers who have chosen to write a GTK app
from Java to quickly learn for themselves how to go about doing what they need
to do.</p>

<p>This doesn't mean we don't do things differently than a Java programmer
might expect! After all is said and done, the primary technical goal of the
java-gnome project is:</p>

<h2 class="goal">TO PRESENT GTK WELL</h2>

<p>We do our best to provide an excellent representation of GTK in Java.

<p>Note
that we're not trying to come up with some idealized watered-down
middle-of-the-road please-everyone toolkit that looks like all the other
toolkits just because that's the way they work.

Rather, we're trying to do as effective a language binding to the underlying
GNOME libraries as we can possibly come up with, consistent with the
goal of approachability and the design constraints we decided to adopt.

<p>If you'd like to know more about the background, design, and architecture
of java-gnome 4.0, please see the pages in the <a
href="doc/">documentation</a> section.

<p class="note">This is an edited version of the Objectives and Audience <a
href="doc/design/2a-ObjectiveAndAudience.html">proposal</a> proposal
originally made by Andrew Cowie to the java-gnome-hackers mailing lists. These
ideas have subsequently been presented at numerous events worldwide and have
received enthusiastic support.</p>

<?
	template_end();
?>
</body>
</html>

<!-- vim: set textwidth=78: -->
