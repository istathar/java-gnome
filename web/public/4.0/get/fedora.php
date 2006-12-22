<?php
/*
 * fedora.php
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
<title>Install java-gnome on a Red Hat Linux system</title>
<meta name="author" content="Andrew Cowie">
</head>
<body>
<?
	template_begin();
?>
<h1 class="title">Installing java-gnome on Fedora Core or RHEL</h1>

<p>java-gnome is part of the official GNOME language bindings suite, and as
such should be present in any recent Red Hat Linux system.</p>

<p>The following command will install the latest released version of the
bindings library:

<p><span class="highlight">FIXME</span> (do we need to point people at
a special package repository?</p>

<pre>
# yum install java-gnome
</pre>

<p>As java-gnome 4.0 is a completely new from-the-ground-up implementation, it
depends on a very modern version of GLib, GTK, and the other libraries in the
GNOME stack. While Fedora Core typically ships only a few months off the GNOME
release cycle, Red Hat Enterprise Linux is at times years behind, so you may
find you have to take extra steps to ensure the necessary prerequisites are
present. A Red Hat system with GNOME 2.16.<span class="x">x</span> installed
should be more than sufficient.</p>

<?
	template_end();
?>
</body>
</html>

<!-- vim: set textwidth=78: -->
