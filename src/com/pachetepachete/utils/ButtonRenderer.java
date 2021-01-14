package com.pachetepachete.utils;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

/*
    Renderer pentru butoanele din Jtable.
    De asemenea a trebuit sa fie si TableCellEditor pentru a putea
    avea functionalitate.
 */
public class ButtonRenderer extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {
    private JTable table;
    private Action action;
    private JButton btn;
    private Object value;

    public ButtonRenderer(JTable table, Action action, int column) {
        this.table = table;
        this.action = action;
        btn = new JButton();
        btn.addActionListener(this);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(column).setCellRenderer(this);
        columnModel.getColumn(column).setCellEditor(this);
    }

    @Override
    public Component getTableCellEditorComponent( JTable table, Object value, boolean isSelected, int row, int column) {
        btn.setText(value.toString());
        this.value = value;
        return btn;
    }

    @Override
    public Object getCellEditorValue() {
        return value;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        btn.setText( value.toString() );
        return btn;
    }

    public void actionPerformed(ActionEvent e) {
        int row = table.convertRowIndexToModel(table.getEditingRow());
        fireEditingStopped();
        ActionEvent event = new ActionEvent(table, ActionEvent.ACTION_PERFORMED, "" + row);
        action.actionPerformed(event);
    }

}