package com.epam.utilities;

import java.text.SimpleDateFormat;

public class Constants {
    public static class ComponentNames {
        public static final String B_CREATE_TIMELOG = "createTimeLogButton";
        public static final String B_SEARCH_TIMELOG = "filterButton";
        public static final String B_CLEAR_FILTER = "filterClearButton";
    }

    public static class TableModelNames {
        public static String[] columnNames = {"Person name",
                "Log Description",
                "Start DateTime",
                "End DateTime",
                "Interval"};
    }

    public static class TableColumnNames {
        public static final String ID = "ID";
        public static final String NAME = "Name";
        public static final String CITY = "City";
        public static final String COUNTRY = "Country";
        public static final String FROM_ID = "From ID";
        public static final String TO_ID = "To ID";
        public static final String PRICE = "Price";
        public static final String DATE = "Date";
        public static final String TIME = "Time";
    }

    public static class ComponentTitles {
        public static final String FORM_NAME = "TimeTracker";
        public static final String MESSAGE_SUCCESS = "Operation success!";
        public static final String MESSAGE_FAILURE = "Operation failed!";
        public static final String MESSAGE_ERROR = "Error!";
    }

    public static class DefaultValues {
        public static final int FORM_WIDTH = 800;
        public static final int FORM_HEIGHT = 600;
    }

    public static class Errors {
        public static final String FORM_VALIDATION = "form.validation.exception";
        public static final String NUMBER_REQUIRED = "form.number.required";
        public static final String PERSON_REQUIRED = "form.field.person.required";
        public static final String PERSON_NOT_FOUND = "form.request.person.not_found";

    }

    public static class Messages {
        public static final String OPERATION_SUCCESS = "form.operation.success";
        public static final String OPERATION_FAILED = "form.operation.failure";
    }

    public static class Formats {
        public static final SimpleDateFormat dateFormatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
    }
}
