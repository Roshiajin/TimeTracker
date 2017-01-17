package com.epam.view;

import com.epam.util.TimeTrackerUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import javax.swing.text.MaskFormatter;


abstract class ExampleGUI {

    private static final Logger logger = LogManager.getLogger(ExampleGUI.class);

    private JLabel personNameLabel = new JLabel("Person Name:");
    private JLabel logDescriptionLabel = new JLabel("Log Description:");
    private JLabel startDateTimeLabel = new JLabel("Start DateTime:");
    private JLabel endDateTimeLabel = new JLabel("End DateTime:");
    private JTextField personNameTextField = new JTextField();
    private JTextField logDescriptionTextField = new JTextField();
    private JFormattedTextField startDateTimeTextField = new JFormattedTextField(createFormatter("##:##:## ##-##-####"));;
    private JFormattedTextField endDateTimeTextField = new JFormattedTextField(createFormatter("##:##:## ##-##-####"));;
    private JButton createTimeLogButton = new JButton("Create");

    private JLabel filterLabel = new JLabel("Filter:");
    private JTextField filterTextField = new JTextField();
    private JButton filterButton = new JButton("Search");
    private JButton filterClearButton = new JButton("Clear");

    private JLabel totalTimeLabel = new JLabel("Total Time:");
    private JLabel totalTimeFormatLabel = new JLabel("(Hours)");
    private JTextField totalTimeTextField = new JTextField();

    private Container pane;

    private TimeLogTableModel tableModel;

    SimpleDateFormat formatter = TimeTrackerUtil.formatter;

    protected ExampleGUI(String frameTitle, TimeLogTableModel tableModel) {

        this.tableModel = tableModel;

        JFrame frame = new JFrame(frameTitle);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800,600));
        frame.setPreferredSize(new Dimension(800, 600));

        //Set up the content pane.
        pane = frame.getContentPane();

        pane.setLayout(new GridBagLayout());

        addLabel(pane, personNameLabel, 0, 0);
        addLabel(pane, logDescriptionLabel, 1, 0);
        addLabel(pane, startDateTimeLabel, 2, 0);
        addLabel(pane, endDateTimeLabel, 3, 0);
        addTextField(pane, personNameTextField, 0, 1);
        addTextField(pane, logDescriptionTextField, 1, 1);
        addDateFormattedTextField(pane, startDateTimeTextField, 2, 1);
        addDateFormattedTextField(pane, endDateTimeTextField, 3, 1);
        addButton(pane, createTimeLogButton, 4, 1, GridBagConstraints.LINE_START);

        addLabel(pane, filterLabel, 0, 2);
        addTextField(pane, filterTextField, 1, 2);
        addButton(pane, filterButton, 2, 2, GridBagConstraints.LINE_START);
        addButton(pane, filterClearButton, 2, 2, GridBagConstraints.LINE_END);

        JTable timeLogTable = new JTable(this.tableModel);

        timeLogTable.setFillsViewportHeight(true);

        JScrollPane timeLogScrollPane = new JScrollPane(timeLogTable);

        GridBagConstraints timeLogScrollPaneConstraints = new GridBagConstraints();
        timeLogScrollPaneConstraints.fill = GridBagConstraints.BOTH;
        timeLogScrollPaneConstraints.gridx = 0;
        timeLogScrollPaneConstraints.gridy = 3;
        timeLogScrollPaneConstraints.gridwidth = 5;
        timeLogScrollPaneConstraints.insets = new Insets(5,5,5,5);
        pane.add(timeLogScrollPane, timeLogScrollPaneConstraints);

        addLabel(pane, totalTimeLabel, 0, 4);
        addTextField(pane, totalTimeTextField, 1, 4);
        totalTimeTextField.setEditable(false);

        addLabel(pane, totalTimeFormatLabel, 2, 4, GridBagConstraints.LINE_START);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    private void addLabel(Container pane, JLabel label, int gridx, int gridy) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.anchor = GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new Insets(0,5,0,0);
        pane.add(label, gridBagConstraints);
    }

    private void addLabel(Container pane, JLabel label, int gridx, int gridy, int anchor) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.anchor = anchor;
        gridBagConstraints.insets = new Insets(0,5,0,0);
        pane.add(label, gridBagConstraints);
    }

    private void addTextField(Container pane, JTextField textField, int gridx, int gridy) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0,5,0,0);
        textField.setMinimumSize(new Dimension(100, 20));
        textField.setPreferredSize(new Dimension(100, 20));
        pane.add(textField, gridBagConstraints);
    }

    private void addDateFormattedTextField(Container pane, JFormattedTextField textField, int gridx, int gridy) {

        Date date = new Date();
        String dateString = formatter.format(date);
        textField.setColumns(17);
        textField.setText(dateString);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.anchor = GridBagConstraints.CENTER;
        gridBagConstraints.insets = new Insets(0,5,0,0);
        textField.setMinimumSize(new Dimension(100, 20));
        textField.setPreferredSize(new Dimension(100, 20));
        pane.add(textField, gridBagConstraints);
    }

    private void addButton(Container pane, JButton jButton, int gridx, int gridy, int anchor) {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.NONE;
        gridBagConstraints.gridx = gridx;
        gridBagConstraints.gridy = gridy;
        gridBagConstraints.anchor = anchor;
        gridBagConstraints.insets = new Insets(0,5,0,5);
        jButton.setMinimumSize(new Dimension(75, 20));
        jButton.setPreferredSize(new Dimension(75, 20));
        pane.add(jButton, gridBagConstraints);
    }

    public void addCreateTimeLogListener(ActionListener a) {
        createTimeLogButton.addActionListener(a);
    }

    public void addFilterListener(ActionListener a) {
        filterButton.addActionListener(a);
    }

    public void addFilterClearListener(ActionListener a) {
        filterClearButton.addActionListener(a);
    }

    public void setPersonName(String personName) {
        personNameTextField.setText(personName);
    }

    public String getPersonName() {return personNameTextField.getText().trim();}

    public void setLogDescription(String logDescription) {
        logDescriptionTextField.setText(logDescription);
    }

    public String getLogDescription() {return logDescriptionTextField.getText().trim();}

    public Date getStartDateTime() {
        Date startDateTime = new Date();
        try {
            logger.trace("ExampleGUI.getStartDateTime " + startDateTimeTextField.getText());
            startDateTime = formatter.parse(startDateTimeTextField.getText().trim());
            logger.trace("ExampleGUI.getStartDateTime " + startDateTime.toString());

        } catch (ParseException e) {
            logger.catching(e);
        }
        return startDateTime;
    }

    public Date getEndDateTime() {
        Date endDateTime = new Date();
        try {
            endDateTime = formatter.parse(endDateTimeTextField.getText().trim());
        } catch (ParseException e) {
            logger.catching(e);
        }
        return endDateTime;
    }

    public void setFilter(String s) {
        filterTextField.setText(s);
    }

    public String getFilter() {return filterTextField.getText().trim();}

    public void setTotalTime(String s) { totalTimeTextField.setText(s);}

    public String getTotalTime() {return totalTimeTextField.getText().trim();}

    private MaskFormatter createFormatter(String s) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(s);
        } catch (java.text.ParseException e) {
            logger.catching(e);
        }
        return formatter;
    }

    public TimeLogTableModel getTableModel() {return tableModel;}

    public void showMessage(String message, String title) {
        JOptionPane.showMessageDialog(null,
                message, title, JOptionPane.PLAIN_MESSAGE);
    }

}
