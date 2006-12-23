<?php
/*
 * listing.php
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

	$dirpath .= $_SERVER["REQUEST_URI"];
?>
<title>Index for <?=$dirpath?></title>
<meta name="author" content="Andrew Cowie">
</head>
<body>
<?
	template_begin();
?>
<h1 class="title">Documentation files</h1>
<h2 class="mono"><?=substr($dirpath, 5)?></h2>

<p>The text files that you can view here are:</p>

<ul>
<?
	$dirpath = substr($_SERVER["SCRIPT_FILENAME"], 0, -12) . $dirpath;

	$dh = opendir($dirpath);

	while (($file = readdir($dh)) !== false) {
		if (is_dir($file)) {
			continue;
		}
	
		if (substr($file, -4) == ".txt") {
			$basename = substr($file, 0, -4);
		} else if (($file == "README") ||
			($file == "MARKUP")) {
			$basename = $file;
		} else {
			continue;
		}

		if ($basename) {
?>
<li><a href="<?=$basename?>.html"><?=$file?></a></li>
<?
		}
		
	}
	closedir($dh);

?>
</ul>

<?

	template_end();
?>
</body>
</html>
<?
# vim: set textwidth=78 nowrap:
?>
