<?php
/*
 * template.inc
 *
 * Copyright (c) 2006-2010 Operational Dynamics Consulting Pty Ltd
 * 
 * This file comprises part of the infrastructure and content of the
 * java-gnome project website. As such, it is conveyed alongside the source
 * code and is made available to you by its authors under the terms of the
 * "GNU General Public Licence, version 2". See the LICENCE file for the terms
 * governing usage, copying and redistribution.
 */

/*
 * Create tables and structure of standard page template. It is used by
 * calling
 *
 *	What			Where
 *
 *	template_header()	in HEAD block
 *	template_begin()	top of BODY block, before actual page content
 *	template_end()		after page content.
 *
 * It is assumed that the page itself will contain HTML, HEAD, and BODY
 * elements. This is largely so that pages can be authored independently
 * without requiring the full website environment to iew their content.
 */

	if ($nocache) {
		header("Cache-Control: max-age = 1");
	} else {
		header("Last-Modified: ".gmdate('D, d M Y H:i:s T',getlastmod()));
	}

?>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
  "http://www.w3.org/TR/html4/loose.dtd">
<?

/*
 * template_header()
 *
 * This prints out the stylesheet appropriate to the template
 * and takes care of ensuring that an appropriate title is set
 */

function template_header() {
	global $nocache;	// provided by calling page author

	if ($nocache) {
?>
<meta http-equiv="expires" content="Tue, 04 Dec 1993 21:29:02 GMT">
<meta http-equiv="pragma" content="no-cache">
<?
	}
?>
<link rel="stylesheet" href="/web.css" type="text/css">
<link rel="icon" href="/favicon.png" type="image/png">
<?

}

/*
 * template_begin()
 *
 * This prints out the table structure and contents of the 
 * page template.
 * 
 * This function ends with the HTML having been initialized into a blank DIV
 * which can be treated by the calling document as the full document itself.
 */

function template_begin() {
?>

<div class="topbanner">
	<a href="/"><img id="logo" src="/images/java-gnome_HeaderLogo.png" width="85" height="92" border="0" alt=""></a>
	<div align="right" style="padding:10px; padding-top:12px">
	<a class="nav-black"
	href="http://ftp.gnome.org/pub/gnome/sources/java-gnome/4.0/java-gnome-4.0.16.tar.bz2">java-gnome 4.0.16</a>,
	<a class="nav-black" href="/4.0/NEWS.html#4.0.16">released 17 Jun 2010</a>
	</div>
</div>

<!-- Global (top) nav -->
<table class="topnavpanel" cellpadding="0" cellspacing="0">
<tr>
	<td class="block">
	<a class="nav-black" href="/4.0/">About<br>
	<span class="tiny">Overview, News</span></a>
	</td>

	<td class="block">
	<a class="nav-black" href="/4.0/doc/">Documentation<br>
	<span class="tiny">Tutorials, API, and Hacking</span></a>
	</td>

	<td class="block">
	<a class="nav-black" href="/4.0/get/">Download<br>
	<span class="tiny">Binaries and Source</span></a>
	</td>

	<td class="block" style="border:none">
	<a class="nav-black" href="/4.0/lists/">Interact<br>
	<span class="tiny">Mailing Lists, IRC, and Reporting Bugs</span></a>
	</td>
</tr>
</table>


<!-- Local (right) nav -->

<?
	if (function_exists('sidebar')) {
?>
<div class="rightnavpanel">
<?
		sidebar();
?>
</div>
<?
	}
?>
<!-- Main content area -->
<div class="maincontent">

<!-- main body content begins -->
<?
}


/*
 * template_end()
 *
 * This closes the main content div, and then prints the page footer.
 */


function template_end() {
	global $valid;
	global $bottom_message;

?>
<!-- main body content ends -->
</div>

<!-- bottom spacer -->
<div class="bluepanel" style="background: url(http://www.operationaldynamics.com/images/logo/twirl-bottom.jpg); height: 12px; margin-top: 5px;">
</div>

<!-- copyright text -->

<div class="bottompanel">

Contents copyright &copy; 2006-2010
<a class="black" href="http://www.operationaldynamics.com/">Operational Dynamics</a>
Consulting Pty Ltd, and Others. See <a class="black" href="/4.0/AUTHORS.html">AUTHORS</a>
file and source code history for the various files comprising this site for
full details<?
	if ($valid) {
?>.
<a class="nav-black-current" href="http://validator.w3.org/check?uri=referer">Optimized</a> for standards<?
	// tag above so that no trailing whitespace is there
	}

	if ($bottom_message) {
		print ". ";
		print "$bottom_message";
	}

?>
<br>
<br>
<div style="padding-bottom:10px;">
<a href="http://www.operationaldynamics.com/" style="padding-right:12px;
vertical-align: middle;"><img src="http://www.operationaldynamics.com/images/logo/logo-22x28.jpg" width="22" height="28" border="0"></a>
<a href="http://www.gnome.org/" style="padding-right:5px; vertical-align: bottom;"><img src="/images/gnome_FootLogo.png" width="27" height="33" border="0"></a>
<a href="http://sourceforge.net/"><img src="/images/sourceforge_Logo.png" width="86" heigh="29" border="0"></a>
</div>
</div>
<?
}
/*
 * vim: set textwidth=78 nowrap:
 */
?>
