package com.epam.utilities;

import java.text.SimpleDateFormat;

public class Constants {
    public static class ComponentNames {
        public static final String B_CREATE_TIMELOG = "createTimeLogButton";
        public static final String B_SEARCH_TIMELOG = "filterButton";
        public static final String B_CLEAR_FILTER = "filterClearButton";
    }

    public static class TableColumnNames {
        public static String[] columnNames = {"Person name",
                "Phone Number",
                "Log Description",
                "Start DateTime",
                "End DateTime",
                "Interval"};
    }

    public static class ComponentTitles {
        public static final String FORM_NAME = "TimeTracker";
        public static final String MESSAGE_SUCCESS = "Operation success!";
        public static final String MESSAGE_FAILURE = "Operation failed!";
        public static final String MESSAGE_ERROR = "Error!";
    }

    public static class DefaultValues {
        public static final int FORM_WIDTH = 850;
        public static final int FORM_HEIGHT = 600;
    }

    public static class Errors {
        public static final String ERR_FORM_VALIDATION = "form.validation.exception";
        public static final String ERR_NUMBER_REQUIRED = "form.number.required";
        public static final String ERR_PERSON_REQUIRED = "form.field.person.required";
        public static final String ERR_PERSON_NOT_FOUND = "form.request.person.not_found";
        public static final String ERR_OPERATION_SUCCESS = "form.operation.success";
        public static final String ERR_OPERATION_FAILED = "form.operation.failure";
    }

    public static class Messages {
        public static final String MSG_OPERATION_SUCCESS = "Time log added!";
        public static final String MSG_OPERATION_FAILED = "Check logs for error!";
        public static final String MSG_PERSON_REQUIRED = "Person name is empty!";
        public static final String MSG_PERSON_NOT_FOUND = "No such person name found!";
    }

    public static class Formats {
        public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
    }
}
