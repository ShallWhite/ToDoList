package com.example.shallwhite.todolist;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lenovo on 2017/2/26.
 */

public class Mission {
    private Date mDate;
    private String text;
    private boolean isSolve;

    public String getDate() {
        String template = "MM月dd日";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(template);
        return simpleDateFormat.format(mDate);
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSolve(boolean solve) {
        isSolve = solve;
    }
    public boolean getSolve(){
        return isSolve;
    }
}
