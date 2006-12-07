<?php
	$text = file_get_contents("content.txt");

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
</style>
</head>
<body>
<h1 class="title"><?=$title?></h1>

<?
	include_once "markdown.php";
	include_once "smartypants.php";

	echo SmartyPants(Markdown($text));
?>
</body>
</html>
