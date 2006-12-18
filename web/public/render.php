<?php
#
# render.php
# Render a text document marked up with Markdown syntax as an HTML page. Based
# on view.php from web/local/markdown/
#
# Copyright (c) 2004-2006 Operational Dynamics Consulting Pty Ltd 
# 
# The code in this file, and the library it is a part of, are made available to
# you by the authors under the terms of the "GNU General Public Licence,
# version 2". See the LICENCE file for the terms governing usage and
# redistribution.
#


	function debug($msg) {
		if (0) {
			echo "$msg"."<BR>";
		}
	}

#
# parse the inbound URI.
#

	$filepath = $_SERVER["DOCUMENT_ROOT"] . $_SERVER["REQUEST_URI"];

	debug($filepath);

	if (substr($filepath, -5) == ".html") {
		$filepath = substr_replace($filepath, ".txt" ,-5);

		debug($filepath);

		if (!file_exists($filepath)) {
			debug("Not found");

			$filepath = substr($filepath, 0, -4);

			debug($filepath);

			if (!file_exists($filepath)) {
				echo "<H1>404 Tried looking for your file, but no joy<H1>";
				exit;
			}
		}
		debug("Will render");
	} else {
		echo "<H1>404 for real</H1>";
		exit;
	}

#
# now, with the filename in hand, shlurp its contents and parse out the first
# line as title.
#

	$text = file_get_contents($filepath);

	$first_line_end = strpos($text, "\n");
	$title = substr($text, 0, $first_line_end);
	$text = substr($text, $first_line_end);

#
# and with that done, proceed with the site template
#

	require "template.inc";
?>
<html>
<head>
<?
	template_header();
?>
<title><?=$title?></title>
<style>
body {
	font-family: "Times New Roman", serif;
}
</style>
</head>
<body>
<?
	template_begin();
?>
<h1 class="title"><?=$title?></h1>

<?
	include_once "markdown.php";
	include_once "smartypants.php";

	$text = preg_replace('/(FIXME|TODO)/', '<span class="highlight">${1}</span>', $text);

	echo SmartyPants(Markdown($text));

	template_end();
?>
</body>
</html>
