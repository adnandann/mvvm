package com.example.app.sqlite.model;

import android.content.ContentValues;
import android.database.Cursor;

import androidx.room.Ignore;

import com.example.app.sqlite.data.DataConstants;
import com.example.app.sqlite.data.DatabaseCreator;

public class Note implements CursorModel{

    private int id;
    private String text;
    private String color;
    private long timestamp;
    private int status;
    private long createdAt;
    private long updatedAt;

    public Note() {
    }

    public Note(String text) {
        this.text = text;
    }

    @Ignore
    public Note(String text, String color) {
        this.text = text;
        this.color = color;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public CursorModel getInstance() {
        return new Note();
    }

    @Override
    public String getTableName() {
        return DataConstants.NOTES;
    }

    @Override
    public String[] getAllColumns() {
        return DatabaseCreator.NOTE.ALL_COLUMNS;
    }

    @Override
    public ContentValues getContentValues(int opt) {
        ContentValues values = new ContentValues();
        if(this.getId() != 0){
            values.put(DatabaseCreator.COLUMN_ID, this.getId());
        }
        values.put(DatabaseCreator.COLUMN_TEXT, this.getText());
        values.put(DatabaseCreator.COLUMN_COLOR, this.getColor());
        values.put(DatabaseCreator.COLUMN_STATUS, this.getStatus());
        values.put(DatabaseCreator.COLUMN_TIMESTAMP, this.getTimestamp());

        switch(opt){
            case 1 : values.put(DatabaseCreator.COLUMN_CREATED_AT, Long.toString(System.currentTimeMillis())); break;
            case 2 : values.put(DatabaseCreator.COLUMN_UPDATED_AT, Long.toString(System.currentTimeMillis())); break;
            case 3 : values.put(DatabaseCreator.COLUMN_DELETED_AT, Long.toString(System.currentTimeMillis())); break;
        }
        return values;
    }

    @Override
    public void read(Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(DatabaseCreator.COLUMN_ID)));
        this.setText(cursor.getString(cursor.getColumnIndex(DatabaseCreator.COLUMN_TEXT)));
        this.setColor(cursor.getString(cursor.getColumnIndex(DatabaseCreator.COLUMN_COLOR)));
        this.setTimestamp(cursor.getLong(cursor.getColumnIndex(DatabaseCreator.COLUMN_TIMESTAMP)));
        this.setStatus(cursor.getInt(cursor.getColumnIndex(DatabaseCreator.COLUMN_STATUS)));
        this.setCreatedAt(cursor.getLong(cursor.getColumnIndex(DatabaseCreator.COLUMN_CREATED_AT)));
        this.setUpdatedAt(cursor.getLong(cursor.getColumnIndex(DatabaseCreator.COLUMN_UPDATED_AT)));
    }
}
