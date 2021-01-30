package com.example.app.room.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.app.room.dao.TodoDao;
import com.example.app.room.model.Todo;

@Database(entities = {
        Todo.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase sInstance;
    private final MutableLiveData<Boolean> isDatabaseCreated = new MutableLiveData<>();

    public abstract TodoDao getTodoDao();

    public static AppDatabase getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext());
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, DataConstants.DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                    }
                })
                //.addMigrations(MIGRATION_1_2)
                .build();
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DataConstants.DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private void setDatabaseCreated(){
        isDatabaseCreated.postValue(true);
    }

    public LiveData<Boolean> getDatabaseCreated() {
        return isDatabaseCreated;
    }

    private static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
           //database.execSQL("ALTER TABLE tournament ADD COLUMN logo TEXT");
        }
    };

}
