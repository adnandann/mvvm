package com.example.app.sqlite.data;

public class DatabaseCreator {

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TEXT = "text";
    public static final String COLUMN_COLOR = "color";
    public static final String COLUMN_TIMESTAMP = "timestamp";
    public static final String COLUMN_STATUS = "status";
    public static final String COLUMN_CREATED_AT = "createdAt";
    public static final String COLUMN_UPDATED_AT = "updatedAt";
    public static final String COLUMN_DELETED_AT = "deletedAt";

    public static class NOTE {

        public static final String[] ALL_COLUMNS = {
                COLUMN_ID, COLUMN_TEXT, COLUMN_COLOR, COLUMN_TIMESTAMP, COLUMN_STATUS,
                COLUMN_CREATED_AT, COLUMN_UPDATED_AT, COLUMN_DELETED_AT
        };

        private static final String CREATE_COLUMNS = " ("
                + COLUMN_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_TEXT + " TEXT, "
                + COLUMN_COLOR + " TEXT, "
                + COLUMN_TIMESTAMP + " TEXT, "
                + COLUMN_STATUS+ " INTEGER DEFAULT '0',"
                + COLUMN_CREATED_AT + " TEXT, "
                + COLUMN_UPDATED_AT + " TEXT, "
                + COLUMN_DELETED_AT + " TEXT" + ");";

        public static String MY_CREATE = "create table if not exists " + DataConstants.NOTES + CREATE_COLUMNS;

    }
}
