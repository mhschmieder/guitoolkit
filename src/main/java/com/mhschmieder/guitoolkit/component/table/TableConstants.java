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

import java.awt.Color;

/**
 * {@code TableConstants} is a container for constants related to tables,
 * whether mandated as invariant or simply provided as useful or common
 * default values in the table context.
 *
 * @version 1.0
 *
 * @author Mark Schmieder
 */
public final class TableConstants {

    /**
     * The default constructor is disabled, as this is a static constants class.
     */
    private TableConstants() {}

    /**
     * Swing starts column numbering at index 0.
     */
    public static final int   COLUMN_ROW_HEADER               = 0;

    /**
     * The default {@link Color} to use for row header cell background.
     * <p>
     * This is a nice dark royal blue that used to be common for table headers
     * in many applications and reports. The Swing default is otherwise gray.
     */
    public static final Color DEFAULT_HEADER_BACKGROUND_COLOR = new Color( 62, 86, 151 );

    /**
     * The default {@link Color} to use for row header cell foreground.
     */
    public static final Color DEFAULT_HEADER_FOREGROUND_COLOR = Color.WHITE;

    /**
     * The default {@link Color} to use for regular cell background.
     */
    public static final Color DEFAULT_CELL_BACKGROUND_COLOR   = Color.WHITE;

    /**
     * The default {@link Color} to use for regular cell foreground.
     */
    public static final Color DEFAULT_CELL_FOREGROUND_COLOR   = Color.BLACK;

}
