package com.reinvo.together;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class QuickAddActivity extends AppCompatActivity {

    private Button mDatePicker;
    private Button mTimePicker;
    private TextView mAdd;
    private TextView mCancel;

    private int year_, month_, day_;
    private int hour_, minute_;
    private String month_name;

    private static final int DATE_DIALOG_ID = 0;
    private static final int TIME_DIALOG_ID = 1;
    private static final Calendar current_calendar = Calendar.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_quick_add);

        // Implementing button from id
        mDatePicker = (Button) findViewById(R.id.date_picker);
        mTimePicker = (Button) findViewById(R.id.time_picker);
        mAdd = (TextView) findViewById(R.id.quick_add_ok_btn);
        mCancel = (TextView) findViewById(R.id.quick_add_cancel_btn);

        // Implementing initial time of the date picker
        year_ = current_calendar.get(Calendar.YEAR);
        month_ = current_calendar.get(Calendar.MONTH);
        day_ = current_calendar.get(Calendar.DAY_OF_MONTH);

        // Check if the date dialog is clicked
        showDateDialogOnClick();
        showTimeDialogOnClick();
    }

    public void onCancelButton() {

    }

    public void showDateDialogOnClick() {
        mDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }

    public void showTimeDialogOnClick() {
        mTimePicker.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDialog(TIME_DIALOG_ID);
            }
        });
    }

    public Dialog onCreateDialog(int dialog_id) {
        if (dialog_id == DATE_DIALOG_ID) {
            return new DatePickerDialog(this, datePickerListener, year_, month_, day_);
        }
        if (dialog_id == TIME_DIALOG_ID) {
            return new TimePickerDialog(this, timePickerListener, hour_, minute_, false);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    year_ = year;
                    month_ = monthOfYear;
                    day_ = dayOfMonth;
                    month_name = getMonthName(month_);
                    mDatePicker.setText(month_name + " " + day_);
                    Toast.makeText(QuickAddActivity.this, "Picked: " +
                                    month_name + " " + day_ + ", " + year_
                                    , Toast.LENGTH_LONG).show();
                }
            };

    private TimePickerDialog.OnTimeSetListener timePickerListener =
            new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hour_ = hourOfDay;
                    minute_ = minute;
                    mTimePicker.setText(hour_ + " : " + minute_);
                    Toast.makeText(QuickAddActivity.this, "Picked: " +
                            hour_ + " : " + minute_
                            , Toast.LENGTH_LONG).show();
                }
            };

    private String getMonthName(int month) {
        String month_s = "";

        switch (month) {
            case 1:
                month_s = "January";
                break;
            case 2:
                month_s = "February";
                break;
            case 3:
                month_s = "March";
                break;
            case 4:
                month_s = "April";
                break;
            case 5:
                month_s = "May";
                break;
            case 6:
                month_s = "June";
                break;
            case 7:
                month_s = "July";
                break;
            case 8:
                month_s = "August";
                break;
            case 9:
                month_s = "September";
                break;
            case 10:
                month_s = "October";
                break;
            case 11:
                month_s = "November";
                break;
            case 12:
                month_s = "December";
                break;
            default:
                break;
        }

        return month_s;
    }

}
