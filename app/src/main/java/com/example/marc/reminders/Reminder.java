package com.example.marc.reminders;
/**
 * Created by marc on 03/10/2015.
 */
public class Reminder {
    private int mId;
    private String mContent;
    private int important;

    public Reminder(int id, String content, int important) {
        mId = id;
        mContent = content;
        this.important = important;
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
        return important;
    }

    public void setImportant(int important) {
        this.important = important;
    }
}
