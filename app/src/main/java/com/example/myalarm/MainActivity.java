package com.example.myalarm;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TimePicker;
import java.util.Calendar;
import java.text.DateFormat;
import java.util.Objects;

import android.content.pm.PackageManager.NameNotFoundException;


import com.example.myalarm.databinding.ActivityMainBinding;

import static android.app.AlarmManager.*;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    ActivityMainBinding b;
//    Ringtone ringtone;
    String[] weekDays = {"0","0","0","0","0","0","0"};

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        b.setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");

            }
        });
        
        b.cancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAlarm();
            }
        });

        handleWeeklyPaidButtons();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        if (weekDays[0].equals("1")){
            c.set(Calendar.DAY_OF_WEEK,1);
        }
        if (weekDays[1].equals("1")){
            c.set(Calendar.DAY_OF_WEEK,2);
        }
        if (weekDays[2].equals("1")){
            c.set(Calendar.DAY_OF_WEEK,3);
        }
        if (weekDays[3].equals("1")){
            c.set(Calendar.DAY_OF_WEEK,4);
        }
        if (weekDays[4].equals("1")){
            c.set(Calendar.DAY_OF_WEEK,5);
        }
        if (weekDays[5].equals("1")){
            c.set(Calendar.DAY_OF_WEEK,6);
        }
        if (weekDays[6].equals("1")){
            c.set(Calendar.DAY_OF_WEEK,7);
        }

        updateTimeText(c);
        startAlarm(c);

        new AlertDialog.Builder(this).setMessage(c.toString()).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }
        alarmManager.setExact(RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        alarmManager.setRepeating(RTC_WAKEUP,c.getTimeInMillis(),24*60*60*1000,pendingIntent);
    }

    private void updateTimeText(Calendar c) {
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        b.timeTV.setText(timeText);
    }

    private void cancelAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
        b.timeTV.setText("Alarm canceled");

//        ringtone.stop();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handleWeeklyPaidButtons() {
        b.sundayBtn.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                if (Objects.equals(b.sundayBtn.getBackground().getConstantState(), getResources().getDrawable(R.drawable.border).getConstantState())) {
                    b.sundayBtn.setBackground(getDrawable(R.drawable.weekdaybg));
                    weekDays[6] = "1";

                } else {
                    b.sundayBtn.setBackground(getDrawable(R.drawable.border));
                    weekDays[6] = "0";
                }
            }
        });
        b.mondayBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                if (Objects.equals(b.mondayBtn.getBackground().getConstantState(), getResources().getDrawable(R.drawable.border).getConstantState())) {
                    b.mondayBtn.setBackground(getDrawable(R.drawable.weekdaybg));
                    weekDays[0] = "1";
                } else {
                    b.mondayBtn.setBackground(getDrawable(R.drawable.border));
                    weekDays[0] = "0";
                }
            }
        });
        b.tuesdayBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                if (Objects.equals(b.tuesdayBtn.getBackground().getConstantState(),getResources().getDrawable(R.drawable.border).getConstantState())) {
                    b.tuesdayBtn.setBackground(getDrawable(R.drawable.weekdaybg));
                    weekDays[1] = "1";
                } else {
                    b.tuesdayBtn.setBackground(getDrawable(R.drawable.border));
                    weekDays[1] = "0";
                }
            }
        });
        b.wednesdayBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                if (Objects.equals(b.wednesdayBtn.getBackground().getConstantState(), getResources().getDrawable(R.drawable.border).getConstantState())) {
                    b.wednesdayBtn.setBackground(getDrawable(R.drawable.weekdaybg));
                    weekDays[2] = "1";
                } else {
                    b.wednesdayBtn.setBackground(getDrawable(R.drawable.border));
                    weekDays[2] = "0";
                }
            }
        });
        b.thursdayBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                if (Objects.equals(b.thursdayBtn.getBackground().getConstantState(), getResources().getDrawable(R.drawable.border).getConstantState())) {
                    b.thursdayBtn.setBackground(getDrawable(R.drawable.weekdaybg));
                    weekDays[3] = "1";
                } else {
                    b.thursdayBtn.setBackground(getDrawable(R.drawable.border));
                    weekDays[3] = "0";
                }
            }
        });
        b.fridayBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                if (Objects.equals(b.fridayBtn.getBackground().getConstantState(), getResources().getDrawable(R.drawable.border).getConstantState())) {
                    b.fridayBtn.setBackground(getDrawable(R.drawable.weekdaybg));
                    weekDays[4] = "1";
                } else {
                    b.fridayBtn.setBackground(getDrawable(R.drawable.border));
                    weekDays[4] = "0";
                }
            }
        });
        b.saturdayBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                if (Objects.equals(b.saturdayBtn.getBackground().getConstantState(), getResources().getDrawable(R.drawable.border).getConstantState())) {
                    b.saturdayBtn.setBackground(getDrawable(R.drawable.weekdaybg));
                    weekDays[5] = "1";
                } else {
                    b.saturdayBtn.setBackground(getDrawable(R.drawable.border));
                    weekDays[5] = "0";
                }
            }
        });
    }

}