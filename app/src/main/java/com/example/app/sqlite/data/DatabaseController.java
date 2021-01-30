package com.example.app.sqlite.data;

import android.app.Application;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.app.sqlite.data.db.DatabaseAdapter;
import com.example.app.sqlite.data.db.DatabaseOpenHelper;
import com.example.app.sqlite.model.CursorModel;

import java.util.ArrayList;
import java.util.List;


public class DatabaseController {

    protected DatabaseAdapter adapter;
    protected SQLiteDatabase db;

    public DatabaseController(Application context) {
        this.adapter = new DatabaseAdapter(DatabaseOpenHelper.getInstance(context));
        this.db = this.adapter.open();
    }

    public long insert(CursorModel model){
        return this.db.insert(model.getTableName(), null, model.getContentValues(1));
    }

    public List<?> readAll(CursorModel model, String selection, String[] selectionArgs, String orderBy) {
        List<CursorModel> models = new ArrayList<>();

        if(orderBy == null){
            orderBy = DatabaseCreator.COLUMN_ID + " DESC";

        }

        if(this.db == null){
            this.db = this.adapter.open();
        }

        Cursor cursor = this.db.query(model.getTableName(),
                model.getAllColumns(), selection, selectionArgs, null, null, orderBy);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            CursorModel mod = model.getInstance();
            mod.read(cursor);
            models.add(mod);
            cursor.moveToNext();
        }
        cursor.close();
        return models;
    }

    public CursorModel read(CursorModel model, String selection, String[] selectionArgs){
        return read(model, selection, selectionArgs, null);
    }

    public CursorModel read(CursorModel model, String selection, String[] selectionArgs, String orderBy){
        CursorModel mod = model.getInstance();
        if (selection == null){
            selection = DatabaseCreator.COLUMN_ID + "=?";;
        }
        if (selectionArgs == null || selectionArgs.length == 0){
            if(model.getId() > 0){
                selectionArgs = new String[]{String.valueOf(model.getId())};
            }else{
                return null;
            }
        }
        Cursor cursor = this.db.query(model.getTableName(), model.getAllColumns(),selection, selectionArgs, null, null, orderBy, "1");
        if (cursor.moveToFirst()) {
            mod.read(cursor);
            cursor.close();;
        }
        return mod;
    }

    public long update(CursorModel model, ContentValues values) {
        if (values == null){
            values = model.getContentValues(2);
        }
        return this.db.update(model.getTableName(), values, DatabaseCreator.COLUMN_ID + "=?", new String[]{String.valueOf(model.getId())});
    }

    public int delete(CursorModel model){
        return this.db.delete(model.getTableName(), DatabaseCreator.COLUMN_ID + "=?", new String[]{String.valueOf(model.getId())});
    }

    public int delete(CursorModel model, String where, String[] whereArgs){
        if(where == null){
            where = DatabaseCreator.COLUMN_ID + "=?";
            whereArgs = new String[]{String.valueOf(model.getId())};
        }
        if(whereArgs == null){
            whereArgs = new String[]{String.valueOf(model.getId())};
        }

        return this.db.delete(model.getTableName(), where, whereArgs);
    }


    public int empty(CursorModel model, String selection, String[] selectionArgs){
        int result = db.delete(model.getTableName(), selection, selectionArgs);
        db.execSQL("vacuum");
        return result;
    }



}
