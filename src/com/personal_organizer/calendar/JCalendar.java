//    This file is part of jcalendar.
//
//    jcalendar is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    jcalendar is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with jcalendar.  If not, see <http://www.gnu.org/licenses/>.
//	  Copyright Simone Pellegrini 2004-2008
//    Created on Apr 24, 2004
package com.personal_organizer.calendar;

import java.awt.Dimension;
import java.util.Calendar;
import java.util.Observer;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class JCalendar extends JPanel {

    private JPanel jPanel = null;

    private JTable _dayTable = null;

    private JComboBox _months = null;

    private JSpinner _years = null;

    private JScrollPane jScrollPane = null;

    private CalendarModel _model = null;

    /**
     * This is the default constructor
     */
    public JCalendar() {
        this(new CalendarModel());
    }

    public JCalendar(int day, int month, int year) {
        this(new CalendarModel(day, month, year));
        selectCurrentDay();
    }

    private JCalendar(CalendarModel model) {
        super();
        _model = model;
        initialize();
    }

    public void selectCurrentDay() {
        int row = ((DayTableModel) _model.getTableModel()).getCurrentDayRow();
        int col = ((DayTableModel) _model.getTableModel()).getCurrentDayCol();
        _dayTable.setRowSelectionInterval(row, row);
        _dayTable.setColumnSelectionInterval(col, col);
    }

    /**
     * This method initializes this
     *
     * @return void
     */
    private void initialize() {
        this.setLayout(new java.awt.BorderLayout());
        this.add(getJPanel(), java.awt.BorderLayout.NORTH);
        this.add(getJScrollPane(), java.awt.BorderLayout.CENTER);
        this.setSize(200, 165);
        this.setMaximumSize(new Dimension(173, 165));
        this.setPreferredSize(new java.awt.Dimension(200, 165));
        this.setMinimumSize(new Dimension(173, 165));
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, null, null));
    }

    /**
     * This method initializes jPanel
     *
     * @return javax.swing.JPanel
     */
    private JPanel getJPanel() {
        if (jPanel == null) {
            jPanel = new javax.swing.JPanel();
            jPanel.add(get_months(), null);
            jPanel.add(get_years(), null);
        }
        return jPanel;
    }

    /**
     * This method initializes _dayTable
     *
     * @return javax.swing.JTable
     */
    private JTable get_dayTable() {
        if (_dayTable == null) {
            _dayTable = new javax.swing.JTable();
            _dayTable.setModel(_model.getTableModel());
            _dayTable.setShowGrid(false);
            _dayTable.setCellSelectionEnabled(false);
            _dayTable.setColumnSelectionAllowed(true);
            _dayTable.setPreferredSize(new java.awt.Dimension(375, 98));
            _dayTable.setRowSelectionAllowed(true);
            _dayTable
                    .setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            _dayTable.setSelectionForeground(java.awt.Color.white);

            // CENTRO I VALORI DELLA TABELLA
            for (int i = 0; i < _dayTable.getColumnCount(); i++) {
                TableColumnModel tableColumn = _dayTable.getColumnModel();
                TableColumn col = tableColumn.getColumn(i);
                DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
                renderer.setHorizontalAlignment(SwingConstants.CENTER);
                col.setCellRenderer(renderer);
            }

            _dayTable.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent e) {
                    Object day = _dayTable.getValueAt(_dayTable
                            .getSelectedRow(), _dayTable.getSelectedColumn());
                    if (day != null) {
                        _model.setDay(((Integer) day).intValue());
                    }
                }
            });
        }
        return _dayTable;
    }

    /**
     * This method initializes _months
     *
     * @return javax.swing.JComboBox
     */
    private JComboBox get_months() {
        if (_months == null) {
            _months = new javax.swing.JComboBox();
            _months.setModel(_model.getComboBoxModel());
            _months.setPreferredSize(new java.awt.Dimension(105, 21));
            _months.setFont(new java.awt.Font("MS Sans Serif",
                    java.awt.Font.PLAIN, 12));
            _months.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent e) {
                    _model.setMonth(_months.getSelectedIndex());
                    _dayTable.clearSelection();
                }
            });
        }
        return _months;
    }

    /**
     * This method initializes _years
     *
     * @return javax.swing.JTextField
     */
    private JSpinner get_years() {
        if (_years == null) {
            _years = new javax.swing.JSpinner();
            int currentYear = _model.getYear();
            _years.setModel(new SpinnerNumberModel(currentYear, //initial value
                    currentYear - 100, //min
                    currentYear + 100, //max
                    1));
            _years
                    .setEditor(new javax.swing.JSpinner.NumberEditor(_years,
                                    "#"));
            _years.setPreferredSize(new java.awt.Dimension(70, 21));
            _years.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN,
                    12));
            _years.addChangeListener(new javax.swing.event.ChangeListener() {
                public void stateChanged(javax.swing.event.ChangeEvent e) {
                    _model.setYear(((Integer) _years.getValue()).intValue());
                    _dayTable.clearSelection();
                }
            });
        }
        return _years;
    }

    /**
     * This method initializes jScrollPane
     *
     * @return javax.swing.JScrollPane
     */
    private JScrollPane getJScrollPane() {
        if (jScrollPane == null) {
            jScrollPane = new javax.swing.JScrollPane();
            jScrollPane.setViewportView(get_dayTable());
        }
        return jScrollPane;
    }

    public Calendar getCalendar() {
        return _model.getCalendar();
    }

    public void addDateListener(DateListener o) {
        _model.addDateListener(o);
    }

    public void removeDataListener(DateListener o) {
        _model.removeDateListener(o);
    }

    public void selectDay(int year, int month, int day) {
        _model.setYear(year);
        _model.setMonth(month);
        _model.setDay(day);
        selectCurrentDay();
    }

} //  @jve:visual-info decl-index=0 visual-constraint="10,10"
