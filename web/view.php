<?php
#
# view.php
# Render a text document marked up with Markdown syntax as an HTML page
#
# Copyright (c) 2004-2006 Operational Dynamics Consulting Pty Ltd 
# 
# The code in this file, and the library it is a part of, are made available
# to you by the authors under the terms of the "GNU General Public Licence,
# version 2". See the LICENCE file for the terms governing usage and
# redistribution.
#

#
# parse the inbound URI. Limit to 4 pieces: 1st is throw away, 2nd is project,
# 3rd is branch, and the 4th is the remainder of the URI string. Since URI
# starts with / (as in "/2003/09") [0] in the array is blank!
#

	$MAX_URI_TOKENS = 4;

	$pieces = explode("/",$_SERVER["REQUEST_URI"], $MAX_URI_TOKENS);

	switch ($pieces[1]) {
	case "java-gnome":
		$basedir = "/home/andrew/src/andrew/java-gnome/$pieces[2]";
		if (!file_exists($basedir)) {
			echo "<P>Error, can't find \"$basedir\"</P>";
			exit;
		}
		$filepath = "$basedir/$pieces[3]";	
		break;

	default:
		echo "<P>Error, don't know how to route \"$pieces[1]\"</P>";
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

?>
<html>
<head>
<title><?=$title?></title>
<link rel="stylesheet" href="http://research/web.css" type="text/css">
<style>
body {
	font-family: "Times New Roman", serif;
}
h1 {
	background-color: #CCCCCC;
	border-bottom: 1px solid black;
	padding-left: 5px;
}
h1.title {
	color: green;
	font-family: "Arial", sans-serif;
	font-size: 40px;
}
h2 {
	padding-left: 5px;
}
p {
	padding-left: 5px;
	padding-right: 5px;
}
.highlight {
	background-color: yellow;
}
</style>
</head>
<body>
<h1 class="title"><?=$title?></h1>

<?
	include_once "markdown.php";
	include_once "smartypants.php";

	$text = preg_replace('/(FIXME|TODO)/', '<span class="highlight">${1}</span>', $text);

	echo SmartyPants(Markdown($text));
?>
</body>
</html>
