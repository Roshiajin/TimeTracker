package com.epam.view;

import java.util.Observer;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


abstract class TimeTrackerGUI implements Observer {

    private String label;
    private JTextField personNameField = new JTextField("Person Name");
    private JTextField logDescField = new JTextField("Log Description");
    private JTextField startDateTimeField = new JTextField("Start Datetime");
    private JTextField endDateTimeField = new JTextField("End Datetime");
    private JButton createLogButton = new JButton("Add log");




}
