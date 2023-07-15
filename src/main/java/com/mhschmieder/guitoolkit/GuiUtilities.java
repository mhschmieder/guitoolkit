/**
 * MIT License
 *
 * Copyright (c) 2023 Mark Schmieder
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
 * GuiToolkit Library. If not, see <https://opensource.org/licenses/MIT>.
 *
 * Project: https://github.com/mhschmieder/guitoolkit
 */
package com.mhschmieder.guitoolkit;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import com.mhschmieder.guitoolkit.component.ButtonTabComponent;

/**
 * General utilities for working with Swing, providing boilerplate code.
 */
public class GuiUtilities {

    /**
     * The default constructor is disabled, as this is a static utilities class.
     */
    private GuiUtilities() {}
    
    /**
     * Adds a closable tab to the provided tabbed pane.
     * 
     * @param tabbedPane The Tabbed Pane to add the new Closable Tab to
     * @param tabComponent The Component that fills the Tab's Content Pane
     * @param tabLabel The Label to use for the Tab's Title and Icon
     */
    public static void addClosableTab( final JTabbedPane tabbedPane,
                                       final Component tabComponent,
                                       final JLabel tabLabel ) {
        final String title = tabLabel.getText();
        final Icon tabIcon = tabLabel.getIcon();
        
        // First, add the tab component in the traditional way.
        tabbedPane.add( title, tabComponent );
        
        final int tabIndex = tabbedPane.indexOfComponent( tabComponent );
        
        // Now, make a custom wrapper that supports closable tabs.
        final ButtonTabComponent buttonTabComponent
            = new ButtonTabComponent( tabbedPane, tabIcon );
        
        // Re-set the component at this tab so that it uses the custom wrapper
        // class that allows for closing the tab and that preserves its title,
        // noting that we have to "manually" reset the original tab icon here.
        tabbedPane.setTabComponentAt( tabIndex, buttonTabComponent );
    }
}
