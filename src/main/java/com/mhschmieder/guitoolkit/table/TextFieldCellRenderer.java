/**
 * MIT License
 *
 * Copyright (c) 2020, 2022 Mark Schmieder
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
package com.mhschmieder.guitoolkit.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.SwingConstants;

/**
 * {@code TextFieldCellRenderer} customizes the special {@link XCellRenderer}
 * further in order to focus on rendering aspects that are unique to text.
 *
 * @version 1.0
 *
 * @author Mark Schmieder
 */
public class TextFieldCellRenderer extends XCellRenderer {
    /**
     * Unique Serial Version ID for this class, to avoid class loader conflicts.
     */
    private static final long serialVersionUID     = -2489364149488561756L;

    /**
     * Regular text is generally left-justified so that similar values are
     * easier to detect than with centered or right-justified text.
     */
    private static final int  ROW_HEADER_ALIGNMENT = SwingConstants.LEFT;

    /**
     * The {@link Color} to use for row header cell background.
     */
    private final Color       rowHeaderBackground;

    /**
     * The {@link Color} to use for row header cell foreground.
     */
    private final Color       rowHeaderForeground;

    /**
     * The {@link Color} to use for regular cell background.
     */
    private final Color       cellBackground;

    /**
     * The {@link Color} to use for regular cell foreground.
     */
    private final Color       cellForeground;

    //////////////////////////// Constructors ////////////////////////////////

    /**
     * Constructs a Table Cell Renderer that is specialized for rendering text.
     *
     * @param isRowHeader
     *            {@code true} if this cell should be used as a row header
     * @param cellAlignment
     *            The alignment to use if this cell is not a row header
     * @param fontSize
     *            The preferred size of the fonts to be used by this table cell
     *            renderer
     * @param rowHeaderBackgroundColor
     *            The {@link Color} to use for row header cell background
     * @param rowHeaderForegroundColor
     *            The {@link Color} to use for row header cell foreground
     * @param cellBackgroundColor
     *            The {@link Color} to use for regular cell background
     * @param cellForegroundColor
     *            The {@link Color} to use for regular cell foreground
     *
     * @version 1.0
     */
    public TextFieldCellRenderer( final boolean isRowHeader,
                                  final int cellAlignment,
                                  final float fontSize,
                                  final Color rowHeaderBackgroundColor,
                                  final Color rowHeaderForegroundColor,
                                  final Color cellBackgroundColor,
                                  final Color cellForegroundColor ) {
        // Always call the superclass constructor first!
        super( isRowHeader, ROW_HEADER_ALIGNMENT, cellAlignment, fontSize );

        rowHeaderBackground = rowHeaderBackgroundColor;
        rowHeaderForeground = rowHeaderForegroundColor;
        cellBackground = cellBackgroundColor;
        cellForeground = cellForegroundColor;
    }

    /////////////// DefaultTableCellRenderer method overrides ////////////////

    /**
     * Returns the default table cell renderer for the specified row and column
     * given the object type of the cell's value.
     * <p>
     * During a printing operation, this method will be called with
     * {@code isSelected} and {@code hasFocus} values of {@code false} to
     * prevent selection and focus from appearing in the printed output. To do
     * other customization based on whether or not the table is being printed,
     * check the return value from
     * {@link javax.swing.JComponent#isPaintingForPrint()}.
     *
     * @param table
     *            The {@link JTable} that uses this header cell renderer
     * @param value
     *            The value to assign to the cell at {@code [row, column]}
     * @param isSelected
     *            {@code true} if cell is selected
     * @param hasFocus
     *            {@code true} if cell has focus
     * @param row
     *            The row index of the cell to render
     * @param column
     *            The column index of the cell to render
     * @return The default table cell renderer for the specified cell
     * @see javax.swing.JComponent#isPaintingForPrint()
     *
     * @version 1.0
     */
    @SuppressWarnings("nls")
    @Override
    public Component getTableCellRendererComponent( final JTable table,
                                                    final Object value,
                                                    final boolean isSelected,
                                                    final boolean hasFocus,
                                                    final int row,
                                                    final int column ) {
        final String newValue = ( value instanceof String ) ? ( String ) value : "";

        final Component component = super.getTableCellRendererComponent( table,
                                                                         newValue,
                                                                         isSelected,
                                                                         hasFocus,
                                                                         row,
                                                                         column );

        final boolean applyRowHeaderStyle = cellIsRowHeader
                && ( column == TableConstants.COLUMN_ROW_HEADER );

        // Set the background and foreground colors based on whether the cell is
        // in the header column location and is to be treated as a row header.
        final Color background = applyRowHeaderStyle
            ? rowHeaderBackground
            : isSelected ? table.getSelectionBackground() : cellBackground;
        final Color foreground = applyRowHeaderStyle
            ? rowHeaderForeground
            : isSelected ? table.getSelectionForeground() : cellForeground;
        component.setBackground( background );
        component.setForeground( foreground );

        return component;
    }

}
