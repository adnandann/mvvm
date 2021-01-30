package com.example.app.sqlite.model;

import android.content.ContentValues;
import android.database.Cursor;

public interface CursorModel {

    int getId();

    CursorModel getInstance();

    String getTableName();
    String[] getAllColumns();

    ContentValues getContentValues(int opt);
    void read(Cursor cursor);
}
