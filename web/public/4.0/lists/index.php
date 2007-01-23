<?php
/*
 * index.php
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
<title>Mailing lists and other contact information</title>
<meta name="author" content="Andrew Cowie">
<style>
dt.list {
	font-family: "Bitstream Vera Sans Mono", "Monospaced", "Courier New", mono;
}
</style>
</head>
<body>
<?
	template_begin();
?>
<h1 class="title">Interact!</h1>
<div class="bluepanel"></div>

<h2>Mailing lists</h2>

<p>The java-gnome project currently has two active mailing lists. At the
moment, both are hosted at SourceForge.</p>

<dl>

<dt class="list">java-gnome-developer&#0064;lists&#0046;sourceforge&#0046;net</dt>
	<dd><p>This is the place for <i>developers</i>, that is, people using
	the bindings to create applications, to ask questions and to discuss
	issues like usability and best practise.</p></dd>

<dt class="list">java-gnome-hackers&#0064;lists&#0046;sourceforge&#0046;net</dt>
	<dd><p>This is the mailing list where the people <i>hacking</i> on the
	bindings themselves discuss design issues, what they're working on,
	and manage the release process. You're welcome to join the list, but
	please keep discussions on topic.</p></dd>

</dl>


<h2>IRC</h2>

<dl>

<dt class="list">#java-gnome</dt>
	<dd><p>We hang out in the <code>#java-gnome</code> IRC channel on the
	GNOME IRC network at <code>irc.gimp.net</code>. The people who hack on
	the bindings tend to discuss the work they're doing there, but
	developers using java-gnome to create applications are welcome to stop
	by and ask questions. And if you're just casually interested, then by
	all means feel free to lurk in the channel.</p></dd>

</dl>

<p><i>The team of people active in the java-gnome community are spread all
over the world and so tend to be on the net at different times of the day.  So
if you have a question or problem, do be patient.</i></p>

<?
	template_end();
?>
</body>
</html>

<!-- vim: set textwidth=78: -->
