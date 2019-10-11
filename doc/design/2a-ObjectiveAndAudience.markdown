Re-engineering (2): Objective and Audience
==========================================

Now that you've done some homework, we can talk about where we're going and
why.

As I've said elsewhere on many occasions, I have the utmost respect for the
authors who have built the Java bindings up over the past 9 years.  As someone
with well over 38,400 lines of application code built on libgtk-java and
friends, I continue to be astounded by the richness and power of GTK and the
ease of use [relative to C, anyway] of the bindings.

Having been a developer with java-gnome, QA person for a distro's packages,
then author of that distro's packages of the java-gnome libraries, meanwhile
bug reporter to the bindings themselves, then code contributor, then
committer, for now for the last year or so I have found myself the titular
maintainer of the java-gnome project. Along the way as I've been confronted
with bug reports, patches, and my own attempts to scratch out improvements,
I've gained a fair familiarity with what's inside the guts of the project and
appreciate well the complexity and pain involved in deploying a set of
libraries such as ours.

6 weeks ago I was sitting at GUADEC listening to the lecture about all the new
features in GTK 2.10, and I started going into cardiac arrest wondering how
the heck we were going to manually re-invent and wrap all the new APIs.
Unfortunately, the existing foundation we have just isn't scalable and
requires far too much work to maintain, let alone grow.

So we're off on a redesign, something we've been talking about for years. The
primary motivation for this process is so that we can switch to a mostly
generated bindings to allow us keep up with the underlying libraries with
minimal further marginal effort, but along the way hopefully we can improve
some of the things that have been less than ideal all these years.

That's all nice and fine, but who are we as a project, and what are we trying
to achieve?

I'd like to put the following suggestion of what our goals should be and who I
think our audience is to you in this email; the following one will describe
what I think the overall design constraints are, and then we can move on into
the technical decisions we need to agree upon which we will use to govern the
rest of our work together.


Objective
=========

As I see it, our mission, as a bindings, is to achieve one quality above all
else:

<center>
                            **APPROACHABILITY**
</center>

I define this to mean that we take every effort to make java-gnome as easy to
learn as possible and as pleasant to develop with as possible.

Bog knows GTK itself is hard! The GTK APIs themselves are rich and powerful
but with that richness comes complexity and detail that needs to be mastered.

In its native C, GTK programs are very nearly impenetrable, certainly
exceedingly verbose, and brutally difficult to debug. All these factors are
areas where Java in general and java-gnome in particular are a great
improvement. In particular, we've already seen that the combination of a
naturally object-oriented language like Java along with the code completion
features of a rich IDE like Eclipse make for a really excellent development
experience.

If we do our job with the bindings right, then newcomers who have chosen to
write a GTK app from Java to quickly learn for themselves how to go about
doing what they need to do.

This doesn't mean we don't do things differently! After all is said and done,
technically, the primary goal is:

<center>
                          **TO PRESENT GTK WELL**
</center>


so we will do our best to come up with an excellent representation of GTK in
Java, not to come up with some idealized watered-down middle-of-the-road
please-everyone toolkit that looks like all the other toolkits just because
that's the way they look.

Rather, we're trying to do as effective a language binding as we can possibly
come up with, consistent with the discussion in the rest of this email and the
ones that follow in topic (2).


Audiences
=========

We have a number of audiences to look towards. It's just as important,
however, to point out who I *don't* think we should be worrying about, as much
as who we are trying to support.


Java developers who want to write a "native" Linux app: YES!
------------------------------------------------------------

There is an important segment which I think we can address effectively: the
huge number of people with Java programming experience.

You might think this goes without saying, but specifically, our target
audience are people who are Java programmers who happen to be using GNOME on
Linux or Solaris and who just might like to write an application and want it
to look good and therefore want to write it in GTK.

The existing pool of people with Java expertise is enormous and is a large
audience whom free software have largely ignored over the years.  More than
anything, I'd like to ensure we give these people the power to write rich
desktop programs and clients, and in the process bring a whole new crowd of
people into the amazing world that is GNOME and Software Libre.


People who are already GTK hackers: NOT REALLY
----------------------------------------------

On the other hand, existing GTK hackers are not a group that I think we should
be trying to target as our primary audience.

They are, after all, **already** proficient with GTK! (presumably from C but
perhaps from Python or whatever) and are quite the wizards to have been able
to do so.

And so I feel no moral imperative to try and convert them to using Java or our
bindings. I point this out explicitly to contrast to the Mono project, who
treat their work like its some kind of religion that everyone must convert to.

Now: if an experienced GNOME developer wants to move on to using a mature and
fast managed runtime, and if they want to avoid writing in Microsoft's patent
encumbered languages, and if they want to take advantage of the monumental
power of Eclipse, then yes, absolutely, we'd love to have them working in GTK
via java-gnome.

And that's one of the reasons that as-complete-as-we-can-manage coverage and
an algorithmic mapping of the APIs is important. But in the mean time, lets
concentrate on approachability, ok?


Everyone else: NO
-----------------

There are lots of GUI choices out there already.

We will differentiate ourselves by being a good binding of GTK.

If someone wants to go on and on about how SWING does that and SWT does that,
well then my very simple answer is "sure, no problem. Go use it, then". We're
not forcing anyone to do anything; Open Source is about choice, after all.

So we don't have to try and be all things to all people, which frees us up
nicely to concentrate on doing a good job of being a Java binding to GTK,
Cairo, and the GNOME libraries.

Which leads us to the question of how we can best do that. [Design
constraints](2b-DesignConstraints.html) are the subject of the next document.

AfC   

_Originally written as an email to java-gnome-hackers by Andrew Cowie on 19
Aug 06; last modified 4 Dec 06._

<!--
 
  Copyright © 2006 Operational Dynamics Consulting, Pty Ltd

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
