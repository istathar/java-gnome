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
<title>java-gnome 2.x - DO NOT USE</title>
<style>
div.box {
	background-color: #DDDDDD;
	padding: 15px;
	border: dashed 3px orange;
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
@deprecated 
</span>
<p>
The java-gnome 2.<span class="x">x</span> series of releases have reached
end-of-life and are no longer being developed or supported by anyone.</p>
</div>

<p>The original java-gnome project was written by <b>five</b> successive teams
of hackers from 1998-2006. That's <b>nine</b> years!</p>

<p>Unfortunately, there has been almost zero new coverage activity over the
past several years and the code that is there is full of cruft, inefficiency,
bugs, and serious memory leaks. Its codebase was spread across
<b>seven</b>-plus libraries, and it was virtually impossible to maintain. Its
website was hideously out of date and the quality of examples poor. Worst of
all, it was exceedingly difficult for new people to learn how to contribute to
java-gnome 2.<span class="x">x</span>, and as a result was abandoned in October
2006.</p>

<p>This page is for historical reference and to honour the hard work of the
fine team of <a class="nav-black" href="AUTHORS">contributors, hackers, and
maintainers</a> who wrote the first generations of the Java bindings for GTK
and GNOME. We owe them a debt of gratitude.</p>

<h2>Last Releases</h2>

java-gnome <code>2.16.2</code> was the last release of the <code>2.</code><span
class="x">x</span> series. It consisted of the following libraries:

<pre style="background-color: white;">
	<a style="text-decoration: none;" href="http://ftp.gnome.org/pub/gnome/sources/glib-java/0.4/glib-java-0.4.2.tar.bz2">glib-java     0.4.2</a>
	<a style="text-decoration: none;" href="http://ftp.gnome.org/pub/gnome/sources/cairo-java/1.0/cairo-java-1.0.7.tar.bz2">cairo-java    1.0.7</a>
	<a style="text-decoration: none;" href="http://ftp.gnome.org/pub/gnome/sources/libgtk-java/2.10/libgtk-java-2.10.2.tar.bz2">libgtk-java   2.10.2</a>
	<a style="text-decoration: none;" href="http://ftp.gnome.org/pub/gnome/sources/libgnome-java/2.12/libgnome-java-2.12.7.tar.bz2">libgnome-java 2.12.7</a>
	<a style="text-decoration: none;" href="http://ftp.gnome.org/pub/gnome/sources/libglade-java/2.12/libglade-java-2.12.8.tar.bz2">libglade-java 2.12.8</a>
	<a style="text-decoration: none;" href="http://ftp.gnome.org/pub/gnome/sources/libgconf-java/2.12/libgconf-java-2.12.6.tar.bz2">libgconf-java 2.12.6</a>
	<a style="text-decoration: none;" href="http://ftp.gnome.org/pub/gnome/sources/libvte-java/0.12/libvte-java-0.12.3.tar.bz2">libvte-java   0.12.3</a>
</pre>

<p>There is no reason whatsoever to start a new project in java-gnome 2.<span
class="x">x</span>. Despite the version numbers above, coverage never made it 
past GTK 2.6</p>

<p>If you have an existing application that uses the old
bindings, the you are advised to upgrade to the latest versions of these
libraries available, as they contain bug fixes of considerable importance. Even
then it'll probably crash.

<p>From this point forward, however, each of the packages making up java-gnome
<code>2.16.2</code> have been <b>declared end-of-life</b> and the APIs used
have been formally deprecated. It is no longer being maintained by anyone from
the previous or present java-gnome teams. As these bindings have been
abandoned, contributed patches will not be accepted.</p>

<p>Please note that several other libraries are available in the old java-gnome
family; none of them achieved release quality, were abandoned as early as
java-gnome 2.8, and probably don't build anyway. Don't even think about trying
to use them:</p>

<pre style="background-color: white;">
	<a style="text-decoration: none;" href="http://ftp.gnome.org/pub/gnome/sources/libeds-java/0.5/libeds-java-0.5.1.tar.bz2">libeds-java   0.5.1</a>
	<a style="text-decoration: none;" href="null://">libbonobo-java</a>
	<a style="text-decoration: none;" href="null://">libgtkhtml-java</a>
	<a style="text-decoration: none;" href="null://">libgtkmozembed-java</a>
	<a style="text-decoration: none;" href="null://">libgnomevfs-java</a>
	<a style="text-decoration: none;" href="null://">libgst-java</a>
</pre>

<h2>Migrating existing applications</h2>

<p>There is a complete API break between java-gnome 2.<span class="x">x</span>
and the new java-gnome 4.0 bindings. While the style of coding will be
familiar, package space, classes, method names, and arguments are at times very
different. This was necessary in order to ensure an algorithmic mapping of the
underlying libraries, something sadly lacking in 2.<span class="x">x</span>.
There are also some wholesale API redesigns, with signal handling in particular
being completely new. Finally, internals that were previously exposed are no
longer publicly visible and not part of the public API.

<p>Also, you might note that unlike the above list of libraries with
inconsistent names, java-gnome 4.0 is shipping at present in <!-- FIXME --> a
single unified source release.</p>

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
