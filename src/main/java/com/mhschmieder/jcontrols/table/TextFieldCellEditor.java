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
package com.mhschmieder.jcontrols.table;

import com.mhschmieder.jgraphics.color.ColorConstants;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import java.awt.Component;

/**
 * {@code TextFieldCellEditor} is a specialization of {@link XCellEditor} to
 * customize how Text Fields are handled in the context of table cells.
 *
 * @version 1.0
 *
 * @author Mark Schmieder
 */
public class TextFieldCellEditor extends XCellEditor {
    /**
     * Unique Serial Version ID for this class, to avoid class loader conflicts.
     */
    private static final long serialVersionUID = -1061137972901159516L;

    //////////////////////////// Constructors ////////////////////////////////

    /**
     * Constructs a Cell Editor that customizes how Text Fields are handled.
     * <p>
     * This is the preferred constructor in most cases, for regular text.
     *
     * @param isRowHeader
     *            {@code true} if this cell should be used as a row header
     * @param isEnabled
     *            {@code true} if this cell should be enabled by default
     * @param isVisible
     *            {@code true} if this cell should be visible by default
     *
     * @version 1.0
     */
    public TextFieldCellEditor( final boolean isRowHeader,
                                final boolean isEnabled,
                                final boolean isVisible ) {
        this( new JTextField(), isRowHeader, isEnabled, isVisible );
    }

    /**
     * Constructs a Cell Editor that customizes how Text Fields are handled.
     * <p>
     * This special constructor is provided for cases where a subclass of
     * {@link JTextField} may be needed (such as for domain-specific numeric
     * data entry); mostly because those classes have unique constructors.
     *
     * @param textField
     *            The Text Field (or derived class) to use as the cell editor
     * @param isRowHeader
     *            {@code true} if this cell should be used as a row header
     * @param isEnabled
     *            {@code true} if this cell should be enabled by default
     * @param isVisible
     *            {@code true} if this cell should be visible by default
     *
     * @version 1.0
     */
    public TextFieldCellEditor( final JTextField textField,
                                final boolean isRowHeader,
                                final boolean isEnabled,
                                final boolean isVisible ) {
        // Always call the superclass constructor first!
        super( textField, isRowHeader, isEnabled, isVisible );

        // Prevent certain LAF's (such as Nimbus) from adding so much cell
        // decoration that the text won't fit and is illegible.
        //
        // See if we can find out whether the text field currently has rounded
        // corners, and set the border accordingly. Using a thickness of 2
        // pixels is equivalent to the standard insets of 2 pixels that all
        // LAF's but Nimbus use (Nimbus uses 6 pixels, which is why text becomes
        // obscured when a cell editor becomes active for editing).
        //
        // Try to recreate the default border, but simply without the extra
        // inside track that obscures text (only a problem in macOS Look and
        // Feel and Nimbus). This current border steps on LAF design guidelines
        // so is a placeholder hack.
        // final Border border = editorComponent.getBorder();
        // System.out.println( border.toString() );
        // editorComponent.setBorder( BorderFactory.createEmptyBorder( 2, 2, 2,
        // 2 ) );
        editorComponent.setBorder( new LineBorder( ColorConstants.TEAL, 2, false ) );

        // Allow Text Fields to participate in focus (i.e. tab) traversal.
        editorComponent.setFocusable( true );
    }

    ////////////////// DefaultCellEditor method overrides ////////////////////

    /**
     * Sets an initial {@code value} for the editor. This will cause the editor
     * to {@code stopEditing} and lose any partially edited value if the editor
     * is editing when this method is called.
     * <p>
     * Return {@code Component} hierarchy. Once installed in the client's
     * hierarchy this component will then be able to draw and receive user
     * input.
     * <p>
     * This method is called when a cell value is edited by the user.
     *
     * @param table
     *            The {@code JTable} that is asking the editor to edit; can be
     *            {@code null}
     * @param value
     *            The value of the cell to be edited; it is up to the specific
     *            editor to interpret and draw the value. For example, if value
     *            is the string "true", it could be rendered as a string or it
     *            could be rendered as a check box that is checked. {@code null}
     *            is a valid value
     * @param isSelected
     *            {@code true }if the cell is to be rendered with highlighting
     * @param row
     *            The row of the cell being edited
     * @param column
     *            The column of the cell being edited
     * @return The component for editing
     *
     * @version 1.0
     */
    @Override
    public Component getTableCellEditorComponent( final JTable table,
                                                  final Object value,
                                                  final boolean isSelected,
                                                  final int row,
                                                  final int column ) {
        final boolean applyRowHeaderStyle = cellIsRowHeader
                && ( column == TableConstants.COLUMN_ROW_HEADER );

        if ( applyRowHeaderStyle ) {
            // Disable the Text Field but make it visible.
            editorComponent.setEnabled( false );
            editorComponent.setVisible( true );
        }
        else {
            // Set the model component to match the edited state.
            if ( value instanceof String ) {
                delegate.setValue( value );

                // Conditionally enable the Text Field and make it visible.
                //
                // In cases where the Text Field is enabled without making it
                // visible, the custom renderer takes over but the cell can
                // still receive edits.
                editorComponent.setEnabled( defaultEnabled );
                editorComponent.setVisible( defaultVisible );
            }
            else {
                // Disable the Text Field but conditionally make it visible.
                editorComponent.setEnabled( false );
                editorComponent.setVisible( defaultVisible );
            }
        }

        final Component component = super.getTableCellEditorComponent( table,
                                                                       value,
                                                                       isSelected,
                                                                       row,
                                                                       column );

        return component;
    }

}
