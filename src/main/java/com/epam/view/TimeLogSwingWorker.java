package com.epam.view;

import com.epam.model.TimeLog;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class TimeLogSwingWorker extends SwingWorker<List, String> {

    @Override
    protected List<TimeLog> doInBackground() throws Exception {
        List<TimeLog> list = new ArrayList<>();

        System.out.println("Start TimeLogSwingWorker");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {}

        System.out.println("End TimeLogSwingWorker");

        return list;
    }


}
