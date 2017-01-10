package com.epam.view;

import java.awt.*;
import javax.swing.*;


public class ExampleGUI {

    final static boolean shouldFill = true;
    final static boolean shouldWeightX = true;
    final static boolean RIGHT_TO_LEFT = false;

    public static void addComponentsToPane(Container pane) {
        if (RIGHT_TO_LEFT) {
            pane.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        }

        //pane.setMinimumSize(new Dimension(800,600));
        //pane.setPreferredSize(new Dimension(800,600));
        //pane.setMaximumSize(new Dimension(800,600));

        JLabel personNameLabel = new JLabel("Person Name:");
        JLabel logDescriptionLabel = new JLabel("Log Description:");
        JLabel startDateTimeLabel = new JLabel("Start DateTime:");
        JLabel endDateTimeLabel = new JLabel("End DateTime:");
        JTextField personNameTextField = new JTextField();
        JTextField logDescriptionTextField = new JTextField();
        JTextField startDateTimeTextField = new JTextField();
        JTextField endDateTimeTextField = new JTextField();
        JButton createTimeLogButton = new JButton("Create");

        JLabel filterLabel = new JLabel("Filter:");
        JTextField filterTextField = new JTextField();
        JButton filterButton = new JButton("Search");

        JLabel totalTimeLabel = new JLabel("Total Time:");
        JTextField totalTimeTextField = new JTextField();

        pane.setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        if (shouldFill) {
            //natural height, maximum width
            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        }

        if (shouldWeightX) {
            gridBagConstraints.weightx = 0.5;
        }

        ////--------------
        /** personNameLabel **/
        GridBagConstraints personNameLabelConstraints = new GridBagConstraints();
        personNameLabelConstraints.fill = GridBagConstraints.NONE;
        personNameLabelConstraints.gridx = 0;
        personNameLabelConstraints.gridy = 0;
        personNameLabelConstraints.anchor = GridBagConstraints.LINE_END;
        personNameLabelConstraints.insets = new Insets(0,5,0,0);
        pane.add(personNameLabel, personNameLabelConstraints);

        /** logDescriptionLabel **/
        GridBagConstraints logDescriptionLabelConstraints = new GridBagConstraints();
        logDescriptionLabelConstraints.fill = GridBagConstraints.NONE;
        logDescriptionLabelConstraints.gridx = 1;
        logDescriptionLabelConstraints.gridy = 0;
        logDescriptionLabelConstraints.anchor = GridBagConstraints.LINE_END;
        logDescriptionLabelConstraints.insets = new Insets(0,5,0,0);
        pane.add(logDescriptionLabel, logDescriptionLabelConstraints);

        /** startDateTimeLabel **/
        GridBagConstraints startDateTimeLabelConstraints = new GridBagConstraints();
        startDateTimeLabelConstraints.fill = GridBagConstraints.NONE;
        startDateTimeLabelConstraints.gridx = 2;
        startDateTimeLabelConstraints.gridy = 0;
        startDateTimeLabelConstraints.anchor = GridBagConstraints.LINE_END;
        startDateTimeLabelConstraints.insets = new Insets(0,5,0,0);
        pane.add(startDateTimeLabel, startDateTimeLabelConstraints);

        /** endDateTimeLabel **/
        GridBagConstraints endDateTimeLabelConstraints = new GridBagConstraints();
        endDateTimeLabelConstraints.fill = GridBagConstraints.NONE;
        endDateTimeLabelConstraints.gridx = 3;
        endDateTimeLabelConstraints.gridy = 0;
        endDateTimeLabelConstraints.anchor = GridBagConstraints.LINE_END;
        endDateTimeLabelConstraints.insets = new Insets(0,5,0,0);
        pane.add(endDateTimeLabel, endDateTimeLabelConstraints);

        /** personNameTextField **/
        GridBagConstraints personNameTextFieldConstraints = new GridBagConstraints();
        personNameTextFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        personNameTextFieldConstraints.gridx = 0;
        personNameTextFieldConstraints.gridy = 1;
        personNameTextFieldConstraints.weightx = 0.5;
        personNameTextFieldConstraints.anchor = GridBagConstraints.CENTER;
        personNameTextFieldConstraints.insets = new Insets(0,5,0,0);
        personNameTextField.setMinimumSize(new Dimension(100, 20));
        personNameTextField.setPreferredSize(new Dimension(100, 20));
        pane.add(personNameTextField, personNameTextFieldConstraints);

        /** logDescriptionTextField **/
        GridBagConstraints logDescriptionTextFieldConstraints = new GridBagConstraints();
        logDescriptionTextFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        logDescriptionTextFieldConstraints.gridx = 1;
        logDescriptionTextFieldConstraints.gridy = 1;
        logDescriptionTextFieldConstraints.weightx = 0.5;
        logDescriptionTextFieldConstraints.anchor = GridBagConstraints.CENTER;
        logDescriptionTextFieldConstraints.insets = new Insets(0,5,0,0);
        logDescriptionTextField.setMinimumSize(new Dimension(100, 20));
        logDescriptionTextField.setPreferredSize(new Dimension(100, 20));
        pane.add(logDescriptionTextField, logDescriptionTextFieldConstraints);

        /** startDateTimeTextField **/
        GridBagConstraints startDateTimeTextFieldConstraints = new GridBagConstraints();
        startDateTimeTextFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        startDateTimeTextFieldConstraints.gridx = 2;
        startDateTimeTextFieldConstraints.gridy = 1;
        startDateTimeTextFieldConstraints.weightx = 0.5;
        startDateTimeTextFieldConstraints.anchor = GridBagConstraints.CENTER;
        startDateTimeTextFieldConstraints.insets = new Insets(0,5,0,0);
        startDateTimeTextField.setMinimumSize(new Dimension(100, 20));
        startDateTimeTextField.setPreferredSize(new Dimension(100, 20));
        pane.add(startDateTimeTextField, startDateTimeTextFieldConstraints);

        /** endDateTimeTextField **/
        GridBagConstraints endDateTimeTextFieldConstraints = new GridBagConstraints();
        endDateTimeTextFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        endDateTimeTextFieldConstraints.gridx = 3;
        endDateTimeTextFieldConstraints.gridy = 1;
        endDateTimeTextFieldConstraints.weightx = 0.5;
        endDateTimeTextFieldConstraints.anchor = GridBagConstraints.CENTER;
        endDateTimeTextFieldConstraints.insets = new Insets(0,5,0,0);
        endDateTimeTextField.setMinimumSize(new Dimension(100, 20));
        endDateTimeTextField.setPreferredSize(new Dimension(100, 20));
        pane.add(endDateTimeTextField, endDateTimeTextFieldConstraints);

        /** createTimeLogButton **/
        GridBagConstraints createTimeLogButtonConstraints = new GridBagConstraints();
        createTimeLogButtonConstraints.fill = GridBagConstraints.NONE;
        createTimeLogButtonConstraints.gridx = 4;
        createTimeLogButtonConstraints.gridy = 1;
        createTimeLogButtonConstraints.anchor = GridBagConstraints.LINE_START;
        createTimeLogButtonConstraints.insets = new Insets(0,5,0,5);
        createTimeLogButton.setMinimumSize(new Dimension(75, 20));
        createTimeLogButton.setPreferredSize(new Dimension(75, 20));
        pane.add(createTimeLogButton, createTimeLogButtonConstraints);

        /** filterLabel **/
        GridBagConstraints filterLabelConstraints = new GridBagConstraints();
        filterLabelConstraints.fill = GridBagConstraints.NONE;
        filterLabelConstraints.gridx = 0;
        filterLabelConstraints.gridy = 2;
        filterLabelConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        filterLabelConstraints.insets = new Insets(0,5,0,0);
        pane.add(filterLabel, filterLabelConstraints);

        /** filterTextField **/
        GridBagConstraints filterTextFieldConstraints = new GridBagConstraints();
        filterTextFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        filterTextFieldConstraints.gridx = 1;
        filterTextFieldConstraints.gridy = 2;
        filterTextFieldConstraints.anchor = GridBagConstraints.CENTER;
        filterTextFieldConstraints.insets = new Insets(0,5,0,0);
        filterTextField.setMinimumSize(new Dimension(100, 20));
        filterTextField.setPreferredSize(new Dimension(100, 20));
        pane.add(filterTextField, filterTextFieldConstraints);

        /** filterButton **/
        GridBagConstraints filterButtonConstraints = new GridBagConstraints();
        filterButtonConstraints.fill = GridBagConstraints.NONE;
        filterButtonConstraints.gridx = 2;
        filterButtonConstraints.gridy = 2;
        filterButtonConstraints.anchor = GridBagConstraints.LINE_START;
        filterButtonConstraints.insets = new Insets(0,5,0,0);
        filterButton.setMinimumSize(new Dimension(75, 20));
        filterButton.setPreferredSize(new Dimension(75, 20));
        pane.add(filterButton, filterButtonConstraints);

        /** TimeLogTable **/
        JTable timeLogTable = new JTable(new TimeLogTableModel());
        //timeLogTable.setPreferredScrollableViewportSize(new Dimension(800, 70));
        timeLogTable.setFillsViewportHeight(true);

        JScrollPane timeLogScrollPane = new JScrollPane(timeLogTable);

        GridBagConstraints timeLogScrollPaneConstraints = new GridBagConstraints();
        timeLogScrollPaneConstraints.fill = GridBagConstraints.BOTH;
        timeLogScrollPaneConstraints.gridx = 0;
        timeLogScrollPaneConstraints.gridy = 3;
        timeLogScrollPaneConstraints.gridwidth = 5;
        timeLogScrollPaneConstraints.insets = new Insets(5,5,5,5);
        pane.add(timeLogScrollPane, timeLogScrollPaneConstraints);


        /** totalTimeLabel **/
        GridBagConstraints totalTimeLabelConstraints = new GridBagConstraints();
        totalTimeLabelConstraints.fill = GridBagConstraints.NONE;
        totalTimeLabelConstraints.gridx = 0;
        totalTimeLabelConstraints.gridy = 4;
        totalTimeLabelConstraints.anchor = GridBagConstraints.LAST_LINE_END;
        totalTimeLabelConstraints.insets = new Insets(0,5,0,0);
        pane.add(totalTimeLabel, totalTimeLabelConstraints);

        /** totalTimeTextField **/
        GridBagConstraints totalTimeTextFieldConstraints = new GridBagConstraints();
        totalTimeTextFieldConstraints.fill = GridBagConstraints.NONE;
        totalTimeTextFieldConstraints.gridx = 1;
        totalTimeTextFieldConstraints.gridy = 4;
        totalTimeTextFieldConstraints.anchor = GridBagConstraints.LINE_START;
        totalTimeTextFieldConstraints.insets = new Insets(0,5,0,0);
        totalTimeTextField.setMinimumSize(new Dimension(100, 20));
        totalTimeTextField.setPreferredSize(new Dimension(100, 20));
        pane.add(totalTimeTextField, totalTimeTextFieldConstraints);



        ////--------------

//        button = new JButton("Button 2");
//        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
//        gridBagConstraints.weightx = 0.5;
//        gridBagConstraints.gridx = 1;
//        gridBagConstraints.gridy = 0;
//        pane.add(button, gridBagConstraints);
//
//        button = new JButton("Button 3");
//        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
//        gridBagConstraints.weightx = 0.5;
//        gridBagConstraints.gridx = 2;
//        gridBagConstraints.gridy = 0;
//        pane.add(button, gridBagConstraints);
//
//        button = new JButton("Long-Named Button 4");
//        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
//        gridBagConstraints.ipady = 40;      //make this component tall
//        gridBagConstraints.weightx = 0.0;
//        gridBagConstraints.gridwidth = 3;
//        gridBagConstraints.gridx = 0;
//        gridBagConstraints.gridy = 1;
//        pane.add(button, gridBagConstraints);
//
//        button = new JButton("5");
//        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
//        gridBagConstraints.ipady = 0;       //reset to default
//        gridBagConstraints.weighty = 1.0;   //request any extra vertical space
//        gridBagConstraints.anchor = GridBagConstraints.PAGE_END; //bottom of space
//        gridBagConstraints.insets = new Insets(10,0,0,0);  //top padding
//        gridBagConstraints.gridx = 1;       //aligned with button 2
//        gridBagConstraints.gridwidth = 2;   //2 columns wide
//        gridBagConstraints.gridy = 2;       //third row
//        pane.add(button, gridBagConstraints);
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("TimeTracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800,600));
        frame.setPreferredSize(new Dimension(800, 600));

        //Set up the content pane.
        addComponentsToPane(frame.getContentPane());

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

}
