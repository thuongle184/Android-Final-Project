package com.example.finalproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Confession {
    @PrimaryKey(autoGenerate = true)
    private int id;


    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "text")
    private String content;

    @ColumnInfo(name = "date")
    private String date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Confession() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



    public Confession(String date, String title, String content) {
        this.title = title;
        this.content = content;
        this.date = date;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
