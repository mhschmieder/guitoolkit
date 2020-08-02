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
package com.mhschmieder.guitoolkit.component;

import java.awt.EventQueue;

/**
 * {@code DynamicTableXPanel} is a further abstraction of {@link TableXPanel}
 * that sets up the functionality that is likely to be shared by all multi-row
 * tables that support adding and deleting rows.
 *
 * @version 1.0
 *
 * @author Mark Schmieder
 */
public abstract class DynamicTableXPanel extends TableXPanel {
    /**
     * Unique Serial Version ID for this class, to avoid class loader conflicts.
     */
    private static final long serialVersionUID = 5990582758011481642L;

    //////////////////////////// Constructors ////////////////////////////////

    /**
     * Constructs a {@code DynamicTableXPanel} with minimal initial
     * specifications.
     * <p>
     * This is an abstract base class, so its purpose is to avoid copy/paste
     * code in the derived classes; it is unable to function on its own.
     *
     * @param firstColumn
     *            The index for the first column in the Table
     * @param lastColumn
     *            The index for the last column in the Table
     * @param autoSelectionIsEnabled
     *            {@code true} if auto-selection is enabled when nothing is
     *            manually or programmatically selected
     *
     * @since 1.0
     */
    protected DynamicTableXPanel( final int firstColumn,
                                  final int lastColumn,
                                  final boolean autoSelectionIsEnabled ) {
        // Always call the superclass constructor first!
        super( firstColumn, lastColumn, autoSelectionIsEnabled );
    }

    /////////////////////// Table management methods /////////////////////////

    /**
     * Returns {@code true} if a row can be inserted at the specified index.
     *
     * @param insertIndex
     *            The selected index for inserting a new row
     * @param minimumInsertIndex
     *            The minimum index for inserting a new row
     * @param maximumInsertIndex
     *            The maximum index for inserting a new row
     * @param maximumLastRowIndex
     *            The maximum index that is ever allowed for this table
     * @return {@code true} if a row can be inserted at the specified index
     *
     * @since 1.0
     */
    @SuppressWarnings("static-method")
    protected boolean canInsertTableRowAt( final int insertIndex,
                                           final int minimumInsertIndex,
                                           final int maximumInsertIndex,
                                           final int maximumLastRowIndex ) {
        // If the index is out of bounds, or the table size has already reached
        // the maximum number of rows allowed, ignore the insertion request.
        return ( ( insertIndex >= minimumInsertIndex ) && ( insertIndex <= maximumInsertIndex )
                && ( maximumInsertIndex >= minimumInsertIndex )
                && ( maximumInsertIndex <= maximumLastRowIndex ) );

    }

    /**
     * Returns {@code true} if a row can be deleted at the specified index
     *
     * @param deleteIndex
     *            The selected index for deleting an existing row
     * @param minimumDeleteIndex
     *            The minimum index for deleting an existing row
     * @param maximumDeleteIndex
     *            The maximum index for deleting an existing row
     * @param minimumLastRowIndex
     *            The minimum index that is ever allowed for this table
     * @return {@code true} if a row can be deleted at the specified index
     *
     * @since 1.0
     */
    @SuppressWarnings("static-method")
    protected boolean canDeleteTableRowAt( final int deleteIndex,
                                           final int minimumDeleteIndex,
                                           final int maximumDeleteIndex,
                                           final int minimumLastRowIndex ) {
        // If the index is out of bounds, or the table size has already reached
        // the minimum number of rows required, ignore the deletion request.
        return ( ( deleteIndex >= minimumDeleteIndex ) && ( deleteIndex <= maximumDeleteIndex )
                && ( maximumDeleteIndex >= minimumDeleteIndex )
                && ( maximumDeleteIndex > minimumLastRowIndex ) );

    }

    /**
     * Returns the row index for the newly inserted row (if valid), added to the
     * table at the selected row index.
     * <p>
     * This method finds the lower-most row selected (or the last row if none
     * were selected), and inserts an initially similar row right after it.
     *
     * @param minimumInsertIndex
     *            The minimum index for inserting a new row
     * @param maximumLastRowIndex
     *            The maximum index that is ever allowed for this table
     * @return The row index for the newly inserted row (if valid)
     *
     * @since 1.0
     */
    protected final int insertTableRow( final int minimumInsertIndex,
                                        final int maximumLastRowIndex ) {
        // Insert an initially similar or identical row after the selected row.
        final int selectionIndex = getSelectedRow( minimumInsertIndex );
        final int insertIndex = selectionIndex + 1;
        final int maximumInsertIndex = getLastRowIndex() + 1;
        final int referenceIndex = insertTableRowAt( insertIndex,
                                                     minimumInsertIndex,
                                                     maximumInsertIndex,
                                                     maximumLastRowIndex );

        // Request an auto-scroll to the row that was just inserted.
        //
        // These actions MUST be done on the event-dispatching thread!
        EventQueue.invokeLater( () -> {
            // A reasonable compromise is to assume table height of twenty rows
            // and scroll to half that row count beyond the initial reference
            // row index requested for the row insert.
            final int scrollToRow = Math.min( referenceIndex + 10, table.getRowCount() - 1 );
            table.scrollRectToVisible( table
                    .getCellRect( scrollToRow, table.getColumnCount(), false ) );
        } );

        return referenceIndex;
    }

    /**
     * Returns the row index for the newly inserted row (if valid), added to the
     * table at the specified index.
     * <p>
     * There is no default implementation in this abstract base class, as the
     * data model for each derived class will be needed for making a data object
     * associated with the row's contents.
     *
     * @param insertIndex
     *            The selected index for inserting a new row
     * @param minimumInsertIndex
     *            The minimum index for inserting a new row
     * @param maximumInsertIndex
     *            The maximum index for inserting a new row
     * @param maximumLastRowIndex
     *            The maximum index that is ever allowed for this table
     * @return The row index for the newly inserted row (if valid)
     *
     * @since 1.0
     */
    protected abstract int insertTableRowAt( final int insertIndex,
                                             final int minimumInsertIndex,
                                             final int maximumInsertIndex,
                                             final int maximumLastRowIndex );

    /**
     * Returns the row index for the final deleted row (if valid), or the last
     * row if none were selected.
     * <p>
     * This method finds the lower-most row selected (or the last row if none
     * were selected), and inserts an initially similar row right after it.
     *
     * @param minimumDeleteIndex
     *            The minimum index for deleting an existing row
     * @param minimumLastRowIndex
     *            The minimum index that is ever allowed for this table
     * @return The row index for the final deleted row (if valid)
     *
     * @since 1.0
     */
    protected final int deleteTableRows( final int minimumDeleteIndex,
                                         final int minimumLastRowIndex ) {
        // Delete all of the selected table row(s), except the minimum row.
        int referenceIndex = -1;
        final Integer[] rowIndices = getSelectedRows();
        if ( rowIndices != null ) {
            int correctedIndex = -1;
            final int selectionLength = rowIndices.length;
            for ( int i = 0; i < selectionLength; i++ ) {
                final int deleteIndex = rowIndices[ i ].intValue();
                final int maximumDeleteIndex = getLastRowIndex();
                correctedIndex = deleteTableRowAt( deleteIndex,
                                                   minimumDeleteIndex,
                                                   maximumDeleteIndex,
                                                   minimumLastRowIndex );

                // Make sure we only use the first corrected index, as we handle
                // delete in reverse order and want to use the last selected row
                // as the reference row.
                if ( i == 0 ) {
                    referenceIndex = correctedIndex;
                }
            }

            // Now adjust the last selected row index for the number of rows
            // deleted (minus one, as we always try to select the row directly
            // after the one deleted).
            referenceIndex -= ( selectionLength - 1 );
        }
        else {
            // If the user didn't select any rows, default to deleting the last
            // row in the table.
            final int maximumDeleteIndex = getLastRowIndex();
            referenceIndex = deleteTableRowAt( maximumDeleteIndex,
                                               minimumDeleteIndex,
                                               maximumDeleteIndex,
                                               minimumLastRowIndex );
        }

        return referenceIndex;
    }

    /**
     * Returns the row index for the deleted row (if the row was deleted).
     * <p>
     * There is no default implementation in this abstract base class, as the
     * data model for each derived class will be needed for making a data object
     * associated with the row's contents.
     *
     * @param deleteIndex
     *            The selected index for deleting an existing row
     * @param minimumDeleteIndex
     *            The minimum index for deleting an existing row
     * @param maximumDeleteIndex
     *            The maximum index for deleting an existing row
     * @param minimumLastRowIndex
     *            The minimum index that is ever allowed for this table
     * @return The row index for the deleted row (if the row was deleted)
     *
     * @since 1.0
     */
    protected abstract int deleteTableRowAt( final int deleteIndex,
                                             final int minimumDeleteIndex,
                                             final int maximumDeleteIndex,
                                             final int minimumLastRowIndex );

}
