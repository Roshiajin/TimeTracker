package com.epam.core.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public final class MessageResolver {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String key, Object[] objects) {
        String message = messageSource.getMessage(key, objects, null);
        return message;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
