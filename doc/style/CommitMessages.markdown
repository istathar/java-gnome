Style guide: Commit Messages
============================

It might seem a bit anal to ask people to follow certain conventions when
writing their commit messages, but reading code history is a big part of
debugging, and its a lot easier when things are consistent. So, please keep
the following in mind:

Complete ideas preferred over one-liners
----------------------------------------

I'd prefer patches that are complete and comprehensive; one liner bug fixes
are fine when necessary (ie, if that's all it is) but if, say, you're adding
support for a new distro to the top level configure script, commit it as one
complete patch rather than 5 small ones.

Of course at slightly larger levels of aggregation, series of patches chain
together, and that's what branches are for.
Branches are ultimately just a mechanism for shipping code around between
developers and we end up doing merges several times a day. I'm not really that
concerned about what the eventual branch tree ends up looking like, so just
concentrate on doing your work and we'll let Git take care of things
from there.

Summary line and revision text
------------------------------

Git, like all other modern VCS tools, uses the first line of the commit
message as the summary which appears in email Subject: lines, the `--pretty=oneline`
output to `git log`, etc. So a descriptive yet concise first line is
important.

For really trivial fixes, that single line as patch name is sufficient; but
for more comprehensive patches please add a long comment (ie the subject line
_plus_ some prose describing what you're up to. It doesn't have to be perfect.

Put a single blank line between your summary line and the paragraphs you write
as the main descriptive text of the revision comment.

Remember that years from now someone will do `git log filename` and all the
revisions which affected that one file will show. So if you can, try to keep
commits on-topic; you don't have to mention every last little change but try
your best to summarize the impact on major files if they're somewhat
tangential to the main thrust of the rest of the commit.

Max message width 70 characters!
--------------------------------

One problem with standard text editor is that they word wrap at a default of
the normal terminal width, 80 characters. This is a problem when viewing
commit messages because VCS tools typically indent the message by a few
whitespace characters, resulting in insanely ugly wrapping when viewing commit
logs in a terminal window.

Therefore, **lines in commit messages must be no longer than 70 characters**.

I forgot once and it looks stupid: see `bzr log --short -r 14`. Too late now.
But to prevent this from happening again, try and ensure your editor knows
about the width limit. Vim users can put the following fragment in their
`.vimrc`:

    if expand("%:t") =~ "^bzr_log*"
        set textwidth=70
        set filetype=none
        syn match commitComment "^#.*"
        hi link commitComment Comment
    endif

While you're at it, you might want

    if expand("%:e") == "java"
         set nowrap
         set ts=4
    endif

to make sure a casual edit of a Java source file doesn't mess it up.

<a name="id"></a>

Set your user id!
=================

Keeping the integrity of the author history is important so that you get
credit for your work. When you make a commit and subsequently send it to us in
a bundle, we (and the rest of the world) sees who the author of each commit
was. That's terrific, but we do want to keep it professional for both
aesthetic and legal reasons. So, one thing that we require is that the name
and email address of the author be properly set. One of the following will
take care of it:

### `bzr`'s global configuration file

If you put

    [DEFAULT]
    email = George Jones <grjones@example.com>

in the `~/.bazaar/bazaar.conf` file, it will be set for all branches and you
won't have to think about it ever again.

### `bzr whoami`

Or you can run the `bzr whoami` command as follows:

    $ bzr whoami 'George Jones <grjones@example.com>'

Don't forget the single quote to escape those angle brackets!

### Environment variable

Or, you can use the `BZR_EMAIL` environment variable:

    $ export BZR_EMAIL='George Jones <grjones@example.com>'

Whichever you choose, please take the time to do this properly. It will ensure
that the patch you send is properly attributed and that you will retain the
full credit you deserve for your contribution. Patches that are not properly
identified will not be accepted.

And if you try to tell me that your name is George Jones and that you work at
example.com, I **will** hit you with a large mallet. `:)`

AfC

_Originally written by Andrew Cowie 2 Dec 06. Last modified 8 Oct 14._

<!--
 
  Copyright © 2006-2010 Operational Dynamics Consulting, Pty Ltd and Others

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
