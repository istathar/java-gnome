<?php
/*
 * arch.php
 *
 * Copyright (c) 2007 Operational Dynamics Consulting Pty Ltd
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
<title>Install java-gnome on an Arch system</title>
<meta name="author" content="Andrew Cowie">
</head>
<body>
<?
	template_begin();
?>
<h1 class="title">Installing java-gnome on Arch Linux</h1>

<p>Package files now exist to allow you to easily install the java-gnome
library on an Arch Linux system.</p>

<h1>Installing java-gnome from AUR</h1>

<p>As java-gnome is not yet in [extra] or [community] you will need to fetch
the PKGBUILD and build the package yourself. But don't worry, this is easily
done in four steps.</p>

<p>First, create a build directory for java-gnome and change into it</p>
<pre>
$ mkdir java-gnome_builddir && cd java-gnome_builddir
</pre>

<p>Now fetch the PKGBUILD</p>
<pre>
$ wget http://aur.archlinux.org/packages/java-gnome/java-gnome/PKGBUILD
</pre>

<p>Build the package with <i>makepkg</i></p>
<pre>
$ makepkg
</pre>

<p>Finally, login as root (using <i>su</i>) and install the package</p>
<pre>
# pacman -U java-gnome-*.pkg.tar.gz
</pre>

<h1>Installing java-gnome-docs from AUR</h1>

<p>If you would like to read the java-gnome documentation offline, you can also
get the java-gnome-docs package from AUR.</p>

<p>Again, we create a builddir for java-gnome-docs and switch into it</p>
<pre>
$ mkdir java-gnome-docs_builddir && cd java-gnome-docs_builddir
</pre>

<p>Now we will fetch the build-instructions package which includes the
PKGBUILD</p>
<pre>
$ wget http://aur.archlinux.org/packages/java-gnome-docs/java-gnome-docs.tar.gz
</pre>

<p>Extract that file and build the package with <i>makepkg</i></p>
<pre>
$ tar -xfvz java-gnome-docs.tar.gz && makepkg
</pre>

<p>Finally, login as root (using <i>su</i>) and install the package</p>
<pre>
# pacman -U java-gnome-docs*.pkg.tar.gz
</pre>

<?
	template_end();
?>
</body>
</html>

<!-- vim: set textwidth=78: -->
