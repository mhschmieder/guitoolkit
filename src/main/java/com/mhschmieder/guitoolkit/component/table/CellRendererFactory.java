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
package com.mhschmieder.guitoolkit.component.table;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * {@code CellRendererFactory} is a factory class for making customized Table
 * Cell Renderers, without having to unnecessarily derive subclasses from
 * existing classes in the library when there is no new data or extra
 * functionality to add.
 *
 * @version 1.0
 *
 * @author Mark Schmieder
 */
public final class CellRendererFactory {

    /**
     * The default constructor is disabled, as this is a static factory class.
     */
    private CellRendererFactory() {}

    /**
     * Returns a {@link TextFieldCellRenderer} with appropriate settings for
     * read-only labels.
     *
     * @param fontSize
     *            The preferred size of the fonts to be used by the table cells
     * @return A {@link TextFieldCellRenderer} with appropriate settings for
     *         read-only labels
     *
     * @version 1.0
     */
    public static TextFieldCellRenderer makeStaticLabelCellRenderer( final float fontSize ) {
        return new TextFieldCellRenderer( false,
                                          SwingConstants.CENTER,
                                          fontSize,
                                          TableConstants.DEFAULT_HEADER_BACKGROUND_COLOR,
                                          TableConstants.DEFAULT_HEADER_FOREGROUND_COLOR,
                                          TableConstants.DEFAULT_CELL_BACKGROUND_COLOR,
                                          TableConstants.DEFAULT_CELL_FOREGROUND_COLOR );
    }

    /**
     * Returns a {@link TextFieldCellRenderer} with appropriate settings for
     * editable labels.
     *
     * @param fontSize
     *            The preferred size of the fonts to be used by the table cells
     * @return A {@link TextFieldCellRenderer} with appropriate settings for
     *         editable labels
     *
     * @version 1.0
     */
    public static DefaultTableCellRenderer makeDynamicLabelCellRenderer( final float fontSize ) {
        return new TextFieldCellRenderer( false,
                                          SwingConstants.LEFT,
                                          fontSize,
                                          TableConstants.DEFAULT_HEADER_BACKGROUND_COLOR,
                                          TableConstants.DEFAULT_HEADER_FOREGROUND_COLOR,
                                          TableConstants.DEFAULT_CELL_BACKGROUND_COLOR,
                                          TableConstants.DEFAULT_CELL_FOREGROUND_COLOR );
    }

}
