<?php
?>
<html>
<head>
<title>Notes</title>
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
<?
	include_once "markdown.php";
	include_once "smartypants.php";

	$text = file_get_contents("content.txt");
	echo SmartyPants(Markdown($text));
?>
</body>
</html>
