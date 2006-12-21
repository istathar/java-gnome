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
<title>Design, Architecture, and Documentation for java-gnome 4.0</title>
<meta name="author" content="Andrew Cowie">
</head>
<body>
<?
	template_begin();
?>
<h1 class="title">Documentation</h1>

<h1>Getting Started</h1>
<h2>Tutorials</h2>

<h1>API Documentation</h1>

<a class="download" href="api/">doc/api/</a>

<p>We have put considerable effort into ensuring the java-gnome bindings are
easily useable for people using IDEs like Eclipse, and a big part of that is
having useful JavaDoc on both classes and methods so that the help popups
containing these JavaDoc fragments are helpful to the programmer in that
context.</p>

<p>The API documentation in the underlying GNOME libraries tends to be sparse
and is very much in the reference style, which is great if you already know
what you're doing and just need to look something up, but isn't much help if
you are trying to figure out how to use a library in the first place.</p>

<p>Because of our focus on approachability, the API documentation in
java-gnome is in a more conversational style and is squarely
aimed at helping people learn GTK.</p> 

<p>The JavaDoc is of course generated when you build the source code and is
located at <code>doc/api/</code>. The canonical public <a
href="api/">JavaDoc</a> is hosted here; you can link to it from your own
JavaDoc if you like.

<h1>Contributing</h1>

<p>java-gnome is an Open Source project, and as such is the sum of the
contributions of the hackers who actually write the bindings code, the
maintainers who take care of library's build system and do releases, the
packagers downstream who make the software available in their distributions,
and of course the bug reports and feedback from you, the developers using the
library to write your applications.</p>

<p>Providing complete coverage of the underlying GTK and GNOME libraries is a
mamoth task and you are welcome to get involved!</p>

<p>
In order to meet our
quality and approachability goals we have put in place certain guidelines to
help ensure code and documentation sent in will be accepted.
Anyone wishing to contribute to the java-gnome project is therefore advised
to read the top level <a href="HACKING.html"><code>HACKING</code></a> file
before doing anything else. It provides instructions on how to checkout the
source code, tips on how to go about learning the internals of the bindings,
and pointers to the style guidelines.
</p>

<h2>Design and Architecture</h2>

<p>The <code>doc/design/</code> tree in the source code contains considerable
reference material about the background of the project, the design constraints
which governed the re-engineering effort and discussion of the architecture we
arrived at. Much of this documentation is made available here on this website,
cunningly rendered into HTML form.</p>

<p>A good starting point is <a
href="/4.0/doc/design/README.html"><code>doc/design/README</a>.

<?
	template_end();
?>
</body>
</html>

<!-- vim: set textwidth=78: -->
