package com.epam.presentation.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.epam.presentation.services.FormService;
import com.epam.presentation.services.ListenerRegistrar;

import java.awt.event.ActionListener;

@Component
public class ListenerRegistrarImpl implements ListenerRegistrar {

    @Autowired
    private FormService formService;

    public void addButtonActionListener (ActionListener listener, String buttonName) {
        this.formService.addButtonActionListener(listener, buttonName);
    }
}
