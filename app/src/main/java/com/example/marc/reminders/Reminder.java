package com.example.marc.reminders;
/**
 * Created by marc on 03/10/2015.
 */
public class Reminder {
    private int mId;
    private String mContent;
    private int mImportant;

    public Reminder(int id, String content, int important) {
        mId = id;
        mContent = content;
        this.mImportant = important;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        mContent = content;
    }

    public int getImportant() {
        return mImportant;
    }

    public void setImportant(int important) {
        this.mImportant = important;
    }
}
