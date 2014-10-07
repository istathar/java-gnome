<?php
/*
 * index.php
 *
 * Copyright (c) 2006-2009 Operational Dynamics Consulting Pty Ltd, and Others
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
<title>Get java-gnome!</title>
<meta name="author" content="Andrew Cowie">
</head>
<body>
<?
	template_begin();
?>
<h1 class="title">Get java-gnome!</h1>
<div class="bluepanel">
	<a class="nav-white" href="#source">Source will always be better than
	binary</a>
</div>

<table cellpadding="0" cellspacing="0" border="0">
<tr style="vertical-align: top;">
<td style="padding-right: 15px;">

<h1>Download source</h1>

<p style="min-width: 400px;">
Installing binary packages provided by your distribution is certainly
convenient, but java-gnome is easily built from source and you are welcome to
do so.</p>

<h2>Grab the tarball with latest release...</h2>

<p>Like most GNOME projects, official releases of java-gnome in tarball form
are hosted on the <code>ftp.gnome.org</code> servers. You can download the
latest release from
<a class="nav-black"
href="http://ftp.gnome.org/pub/gnome/sources/java-gnome/4.1/">
<pre style="margin-left: 0px;">http://ftp.gnome.org/pub/gnome/sources/java-gnome/4.1/</pre></a>
</p>

<h2>Or check out the sources from the repository...</h2>

<p>For those who like to live on the bleeding edge, you can checkout the
code from our version control repository (branch URL below). You'll need to install Git,
available in most Linux and Unix distributions or from <a href="http://www.git-scm.com">www.git-scm.com</a>.

<a class="nav-black"
href="https://github.com/afcowie/java-gnome.git">
<pre style="margin-left: 0px;">$ Git checkout URL</pre></a>
</p>

<a name="source" title="Source will always be better than binary"></a>

<p>If you're going to hack seriously with, or on, java-gnome, we recommend the
following sequence to checkout the source code described in the 
<a href="/4.0/HACKING.html"><code>HACKING</code></a> file.


<p>We try our best to keep '<code>mainline</code>' in a buildable state, and
certainly the latest bugfixes and improvements will be present there, but we
can't guarantee that it'll be problem free. As ever when building pre-release
code, you would do well to hang with us on IRC or mailing list so you're up
to date with the state of the project in the current cycle.

<h2>... and build!</h2>

<p>Regardless of which sources you get, the next step is to build them. Doing
so is quite straight forward, but since java-gnome is a native library (and
not just architecture independent Java bytecode), building it is a wee touch
more involved than just compiling <code>.java</code> files. See <a
href="../README.html"><code>README</code></a> for more details of how to
configure and compile the bindings.</p>

</td>
<td style="padding-left: 15px;">

<h1>Download binaries</h1>

<p style="min-width: 400px;">
Proper binary packages of the java-gnome <i>will be</i> available in many
fine Linux and Unix distributions, allowing you to quickly and easily install
the library on your system.</p>

<p>Distro specific instructions are available for each of:

<ul>
<li><p><a class="subject" href="gentoo.php">Gentoo Linux</a><br></p>
<li><p><a class="subject" href="arch.php">Arch Linux</a><br></p>
<li><p><a class="subject" href="debian.php">Debian Linux</a><br></p>
<li><p><a class="subject" href="ubuntu.php">Ubuntu Linux</a><br></p>
</ul>

<p>
If yours isn't on that list, or if they don't have the latest release, then
it is certainly time to file a bug report!</p>

<p>
<i>And forthcoming,</i>

<ul>
<li><p><a class="subject" href="solaris.php">Open Solaris</a><br></p>
<li><p><a class="subject" href="fedora.php">Fedora Core Linux</a><br></p>
</ul>
</p>

<p><i>Binaries from by third parties are necessarily used at your own risk. We
encourage the developers in the various downstream distributions to actively
participate in our project; if you're using packages provided by groups that
do so you will find that the level of community interest and support will be
quite high. Those on other platforms will necessarily meet with a less
enthusiastic response, but Open Source is about choice, so try java-gnome
where ever you like.</i></p>

<p><i>You can help by ensuring java-gnome is properly packaged for your 
operating system!</i></p>


</td>
</tr>
</table>

<h1>Browse source</h1>

<p>One of the interesting things about Git (and many of the other modern
Distributed Version Control Systems) is that when a repository is put online,
you can usually have the source code directly available via <code>HTTP</code>.
This means that if you want to quickly point someone at a particular file,
it's as easy as directly linking to it at the URL you checked out from + the
path to the file.</p>

<p>If you would like to browse the source code, here is the top of the
'<code>mainline</code>' branch (perhaps known as <code>HEAD</code> or <code>trunk</code> in 
other version control systems you might have used):

<a class="download" href="https://github.com/afcowie/java-gnome.git">
<code>https://github.com/afcowie/java-gnome.git</code>
</a>
</p>

<?
	template_end();
?>
</body>
</html>

<!-- vim: set textwidth=78: -->
