package com.example.finalproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Confession.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ConfessionDao confessionDao();
}