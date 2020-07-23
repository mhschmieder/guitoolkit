/**
 * MIT License
 *
 * Copyright (c) 2020 Mark Schmieder
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * This file is part of the GuiToolkit Library
 *
 * You should have received a copy of the MIT License along with the
 * FxConverterToolkit Library. If not, see
 * <https://opensource.org/licenses/MIT>.
 *
 * Project: https://github.com/mhschmieder/guitoolkit
 */
package com.mhschmieder.guitoolkit;

import java.net.URL;

import javax.swing.ImageIcon;

/**
 * {@code GuiUtilities} is a utility class for Swing based GUI methods.
 *
 * @version 1.0
 *
 * @author Mark Schmieder
 */
public final class GuiUtilities {

    /**
     * This method serves merely as a sanity check that the Maven integration
     * and builds work properly and also behave correctly inside Eclipse IDE. It
     * will likely get removed once I gain more confidence that I have solved
     * the well-known issues with Maven inside Eclipse as I move on to more
     * complex projects with dependencies (this project is quite simple and has
     * no dependencies at this time, until more functionality is added).
     *
     * @param args
     *            The command-line arguments for executing this class as the
     *            main entry point for an application
     *
     * @since 1.0
     */
    public static void main( final String[] args ) {
        System.out.println( "Hello Maven from GuiToolkit!" ); //$NON-NLS-1$
    }

    /**
     * The default constructor is disabled, as this is a static utilities class.
     */
    private GuiUtilities() {}

    /**
     * Returns an {@link ImageIcon} that contains the image specified by the
     * resource name.
     * <p>
     * This method makes a URL out of a provided image resource name, and uses
     * it to load the image into an {@link ImageIcon} container that can be
     * added to a Swing {@code JLabel} for display in the GUI. In most cases,
     * the resource will be a JAR-relative path within the client JAR, as
     * long-term availability of a web-hosted image might shorten the usefulness
     * of application code should the image resource move or get deleted.
     *
     * @param imageResourceName
     *            The name of the resource that contains the image
     * @return An {@link ImageIcon} that contains the image specified by the
     *         resource name
     *
     * @since 1.0
     */
    public static ImageIcon makeImageIcon( final String imageResourceName ) {
        ImageIcon imageIcon = null;

        // Demand-load the image resource to be immediately available.
        final ClassLoader classLoader = GuiUtilities.class.getClassLoader();
        final URL imageUrl = classLoader.getResource( imageResourceName );
        if ( imageUrl != null ) {
            imageIcon = new ImageIcon( imageUrl );
        }

        return imageIcon;
    }

}
