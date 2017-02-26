package com.example.shallwhite.todolist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class AddMissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mission);
        EditText addMissionText = (EditText)findViewById(R.id.add_mission_text);//笨方法：通过两个EditText获取mission的日期
        EditText dateMonth = (EditText)findViewById(R.id.date_month);
        EditText dateDay = (EditText)findViewById(R.id.date_day);

    }
}
