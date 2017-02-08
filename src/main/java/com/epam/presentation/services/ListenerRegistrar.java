package com.epam.presentation.services;

import java.awt.event.ActionListener;

public interface ListenerRegistrar {
    void addButtonActionListener(ActionListener listener, String buttonName);
}
