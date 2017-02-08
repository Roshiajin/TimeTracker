package com.epam.presentation.listener;

import com.epam.core.controller.Controller;
import com.epam.presentation.services.ListenerRegistrar;
import com.epam.utilities.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public final class SearchButtonListener implements ActionListener {

    private static final String BUTTON_NAME = Constants.ComponentNames.B_SEARCH_TIMELOG;

    @Autowired
    private ListenerRegistrar listenerRegistrar;

    @Autowired
    @Qualifier("SearchTimeLogController")
    private Controller controller;

    @PostConstruct
    public void init() {
        listenerRegistrar.addButtonActionListener(this, BUTTON_NAME);
    }

    public void actionPerformed(ActionEvent e) {
        this.controller.perform();
    }
}
