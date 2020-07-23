# GuiToolkit
The GuiToolkit library is an open source project for Java 8 that serves as a small collection of basic utilities and custom classes for stuff that is needed by a lot of AWT and Swing oriented code bases.

This is a smaller library than GraphicsToolkit, as I haven't done Swing GUI development in years (I switched to JavaFX when it debuted; although the first couple of years were painful hybrid development), so most of my older approaches are long-deleted. Mostly, I have to use Swing and/or AWT when preparing reports and graphics exports that rely on AWT as most of that stuff is quite difficult to convert to a JavaFX Scene Graph based rendering base due to no repaint loop.

I plan to pull in some legacy functions that had fallen out of use once I switched to mostly JavaFX based development, and they have been improved to be more up-to-date with Java 8 standards, but I do not have the ability at the moment to rigorously retest them.

The initial release of Version 1 will require Java 8 as the JDK/JRE target, due to its use of newer language features. I will check to see if it might be made less restrictive as it may only need Java 7, but anything earlier than that introduces unpleasantires on macOS in terms of independent standalone builds.

There will be a modularized version soon, that supports Java 14+. If I find a way to make it compatible with other still-supported versions of Java (I think Java 11 is still supported by Oracle, but not Java 12 or Java 10, and maybe not Java 9 either), I will do what I can, but may have trouble finding an appropriate JDK still available to download and test against.

Eclipse and NetBeans related support files are included as they are generic and are agnostic to the OS or to the user's system details and file system structure, so it seems helpful to post them in order to accelerate the integration of this library into a user's normal IDE project workflow and build cycle.

The Javadocs are 100% compliant and complete, but I am still learning how to publish those at the hosting site that I think is part of Maven Central, as it is a bad idea to bloat a GitHub project with such files and to complicate repository changes (just as with binary files and archices). Hopefully later tonight!

As a confidence boost at both ends, EpsGraphicsUtilities has a main() function that prints "Hello Maven from EpsToolkit" to the console (e.g. the one in Eclipse IDE). By running Maven's clean task, then the install task, you can quickly gain confidence that everything is integrated properly, by then running the main class and seeing the console and confirming that this library was the source of the validation message.

This projects depends on my GraphicsToolkit library, and is marked as such in the Maven POM file.
