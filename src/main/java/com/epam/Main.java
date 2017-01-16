package com.epam;

import com.epam.service.TimeTrackerService;
import com.epam.view.TimeTrackerGUI;

/**
 * Created by Alexander_Gaptullin on 12/19/2016.
 */
public class Main {

    public static void main( String[] args )
    {

        System.out.println( "Welcome to TimeTracker!" );

        TimeTrackerService service = new TimeTrackerService();

        new TimeTrackerGUI(service);
    }
}
