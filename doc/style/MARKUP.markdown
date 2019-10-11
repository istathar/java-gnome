MARKUP
======

About the format of these documents
-----------------------------------

As a matter of sound practise, all our documentation is done in text files so
that a developer can easily read them while browsing the code or working on
their own projects. When writing our documents, however, we _**lightly**_ mark
them up with the syntax of John Gruber's "[Markdown][syntax]" so that they
also present nicely as web pages on our [website][].

Syntax overview
---------------

In brief, the Markdown works like this: you just write text! Then,
occasionally, you punctuate things in the way Markdown recognizes, and you get
the markup you need.

The biggest problem with text documents is that in both spoken and written
communications we use emphasis but the medium of the traditional terminal and
Unix command line aren't up to it. So from the early days of Usenet (and long,
long before wikis), people developed the habit of using extra punctuation
characters to express themselves: 

    I have a compulsive need to use _italics_ and **bold** in my writing

results in "I have a compulsive need to use _italics_ and **bold** in my
writing".

To mark program names, file names, variables, etc in a constant width font you
simply use backticks:

    `filename`

which puts the `filename` in a `<code>` span.

If you write

    Heading
    -------

you get an `<H2>` heading. Using `===` (which is visually a bit stronger)
creates an `<H1>` heading, which makes sense and looks awesome in text.

To do code snippets we use:

    <TAB>Mark a section as being a preformatted
    <TAB>code block, ignoring any _markup_ present.
    <TAB>

As you would expect, the result is a preformatted block like this:

    Mark a section as being a preformatted
    code block, ignoring any _markup_ present.

This works with either a `<TAB>` or `4 spaces` as the indent character. We used
to use two tabs to create the appearance of indent from the left margin so it
would look better when doing code snippets, but now we just use CSS to put some
`margin` and `padding` around `PRE` blocks.

Note that Markdown is actually quite a bit richer than this. It has all kinds
of conveniences such as an easy and natural link (href) syntax, bullet lists
and ordered lists, images, everything you need. It does The Right Thing (tm)
when you'd expect it to, and doesn't bother you about strictness -- it just
lets you get on with writing what looks like a nice readable text file.

Check it out!

Download
--------

John Gruber's [original Markdown is a Perl program][perl] intended for blogs
and wikis; Michel Fortin [ported Markdown to PHP][php]. We use the latter
version to render our documentation as HTML, working off the latest documents
as checked into the mainline branch of the source code.

Convenient rendering
--------------------

One way you can quickly look at the result of your markup is to create a simple
script in a virtual host on your local web server and have it call markdown on
the file you're working on. In PHP it is as simple as:

        include_once "markdown.php";

        $text = file_get_contents("README");
        echo Markdown($text);

An example is included in the source code as [`view.php`][view].

[view]: http://research.operationaldynamics.com/bzr/java-gnome/mainline/web/local/view.php


_Last modified 14 Aug 07_

[syntax]: http://daringfireball.net/projects/markdown/basics
[website]: http://java-gnome.sourceforge.net/docs/design/
[perl]: http://daringfireball.net/projects/markdown/
[php]: http://www.michelf.com/projects/php-markdown/

<!--
 
  Copyright © 2006 Operational Dynamics Consulting, Pty Ltd and Others

  As project documentation, this file forms an integral part of the source
  code of the library it accompanies, and thus is made available to you by its
  authors as open source software: you can redistribute it and/or modify it
  under the terms of the GNU General Public License version 2 ("GPL") as
  published by the Free Software Foundation.

  This program is distributed in the hope that it will be useful, but WITHOUT
  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
  FITNESS FOR A PARTICULAR PURPOSE. See the GPL for more details.

  You should have received a copy of the GPL along with this program. If not,
  see http://www.gnu.org/licenses/. The authors of this program may be
  contacted through http://java-gnome.sourceforge.net/.

  vim: set textwidth=78 nowrap:

-->
