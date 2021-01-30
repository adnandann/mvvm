package com.example.app.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.app.room.model.Todo;

import java.util.List;

@Dao
public interface TodoDao {

    @Query("SELECT * FROM todo ORDER BY id DESC")
    LiveData<List<Todo>> readDataAll();

    @Query("SELECT * FROM todo WHERE id = :id LIMIT 1")
    Todo readDataById(long id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Todo> todos);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Todo... todos);

    @Update
    void update(Todo... todos);

    @Delete
    void delete(Todo todo);

    @Query("SELECT COUNT(*) FROM todo")
    int count();
}
