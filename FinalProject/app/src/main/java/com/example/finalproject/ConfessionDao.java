package com.example.finalproject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ConfessionDao {
    @Query("SELECT * FROM Confession")
    List<Confession> getAll();

    @Insert
    void insert(Confession confession);

    @Delete
    void delete(Confession confession);

    @Update
    void updateOne(Confession confession);
}