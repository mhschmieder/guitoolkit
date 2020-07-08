# GuiToolkit
The GuiToolkit library is an open source project for Java 8 that serves as a small collection of basic utilities and custom classes for stuff that is needed by a lot of AWT and Swing oriented code bases.

This is a smaller library than GraphicsToolkit, as I haven't done Swing GUI development in years (I switched to JavaFX when it debuted), so most of my older approaches are long-deleted. Mostly, I have to use Swing and/or AWT when preparing reports and graphics exports that rely on AWT as most of that stuff is quite difficult to convert to a JavaFX Scene Graph based rendering base due to no repaint loop.

I plan to pull in some legacy functions that had fallen out of use once I switched to mostly JavaFX based development, and they have been improved to be more up-to-date with Java 8 standards, but I do not have the ability at the moment to rigorously retest them.

The initial release of Version 1 will require Java 8 as the JDK/JRE target, due to its use of newer language features. I will check to see if it might be made less restrictive as it may only require Java 7, but anything earlier than that introduces unpleasantires on macOS in terms of independent standalone builds.

There will be a modularized version soon, that supports Java 14+. If I find a way to make it compatible with other still-supported versions of Java (I think Java 11 is still supported by Oracle, but not Java 12 or Java 10, and maybe not Java 9 either), I will do what I can, but may have trouble finding an appropriate JDK still available to download and test against.

The licensing is under review. I'm not a fan of licensing restrictions for open source code and am also not a lawyer, so I am trying to avoid choosing something that precludes using this library with code that follows a different license model. As GPL2+ and GPL3+ are the most common in AWT oriented libraries for graphics export and the like, but are highly restrictive, I am leaning towards the MIT License or the Apache License, as both can be used alongside GPL-licensed libraries.
