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
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.media.Ringtone;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.TimePicker;

import java.util.Arrays;
import java.util.Calendar;
import java.text.DateFormat;
import java.util.Objects;
import java.util.TimeZone;

import android.content.pm.PackageManager.NameNotFoundException;
import android.widget.Toast;


import com.example.myalarm.databinding.ActivityMainBinding;

import static android.app.AlarmManager.*;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    ActivityMainBinding b;
//    Ringtone ringtone;
    String[] weekDays = {"0","0","0","0","0","0","0"};
    private AlarmManager alarmManager;
    PendingIntent pendingIntent;
    Intent intent;
    private int hourOfDay;
    private int minute;
    boolean isTimeSet = false;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        b = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(b.getRoot());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

//        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        intent = new Intent(this, AlertReceiver.class);
//        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//

        b.turnNotificationOnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    b.setTimeConstLayout.setVisibility(View.VISIBLE);
                }else {
                    cancelAllAlarms();
                }
            }
        });

        b.setAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
//                startAlarm();
//                startAlarm2();

            }
        });

        handleWeeklyPaidButtons();
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        this.hourOfDay = hourOfDay;
        this.minute = minute;


        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);
        updateTimeText(c);

        isTimeSet = true;

//        if (weekDays[0].equals("1")){
//            Calendar c = Calendar.getInstance();
//            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
//            c.set(Calendar.MINUTE, minute);
//            c.set(Calendar.SECOND, 0);
//            c.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
//            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//            Intent intent = new Intent(this, AlertReceiver.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),7*24*60*60*1000,pendingIntent);
//            updateTimeText(c);
//            startAlarm(c);
//
//        }
//        if (weekDays[1].equals("1")){
//            Calendar c = Calendar.getInstance();
//            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
//            c.set(Calendar.MINUTE, minute);
//            c.set(Calendar.SECOND, 0);
//            c.set(Calendar.DAY_OF_WEEK,Calendar.TUESDAY);
//            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//            Intent intent = new Intent(this, AlertReceiver.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),7*24*60*60*1000,pendingIntent);
//            updateTimeText(c);
//            startAlarm(c);
//
//        }
//        if (weekDays[2].equals("1")){
//            Calendar c = Calendar.getInstance();
//            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
//            c.set(Calendar.MINUTE, minute);
//            c.set(Calendar.SECOND, 0);
//            c.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY);
//            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//            Intent intent = new Intent(this, AlertReceiver.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),7*24*60*60*1000,pendingIntent);
//            updateTimeText(c);
//            startAlarm(c);
//
//        }
//        if (weekDays[3].equals("1")){
//            Calendar c = Calendar.getInstance();
//            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
//            c.set(Calendar.MINUTE, minute);
//            c.set(Calendar.SECOND, 0);
//            c.set(Calendar.DAY_OF_WEEK,Calendar.THURSDAY);
//            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//            Intent intent = new Intent(this, AlertReceiver.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),7*24*60*60*1000,pendingIntent);
//            updateTimeText(c);
//            startAlarm(c);
//
//        }
//        if (weekDays[4].equals("1")){
//            Calendar c = Calendar.getInstance();
//            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
//            c.set(Calendar.MINUTE, minute);
//            c.set(Calendar.SECOND, 0);
//            c.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);
//            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//            Intent intent = new Intent(this, AlertReceiver.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),7*24*60*60*1000,pendingIntent);
//            updateTimeText(c);
//            startAlarm(c);
//
//        }
//        if (weekDays[5].equals("1")){
//            Calendar c = Calendar.getInstance();
//            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
//            c.set(Calendar.MINUTE, minute);
//            c.set(Calendar.SECOND, 0);
//            c.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
//            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//            Intent intent = new Intent(this, AlertReceiver.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),7*24*60*60*1000,pendingIntent);
//            updateTimeText(c);
//            startAlarm(c);
//
//        }
//        if (weekDays[6].equals("1")){
//            Calendar c = Calendar.getInstance();
//            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
//            c.set(Calendar.MINUTE, minute);
//            c.set(Calendar.SECOND, 0);
//            c.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
//            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//            Intent intent = new Intent(this, AlertReceiver.class);
//            PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),7*24*60*60*1000,pendingIntent);
//            updateTimeText(c);
//            startAlarm(c);
//
//        }
//        String text = b.timeTV.getText().toString();
//        Calendar updateTime = Calendar.getInstance();
//        updateTime.setTimeZone(TimeZone.getTimeZone("GMT"));
//        updateTime.set(Calendar.HOUR_OF_DAY, 11);
//        updateTime.set(Calendar.MINUTE, 45);
//
//        Intent downloader = new Intent(getApplicationContext(), AlertReceiver.class);
//        PendingIntent recurringDownload = PendingIntent.getBroadcast(getApplicationContext(),
//                0, downloader, PendingIntent.FLAG_CANCEL_CURRENT);
//        AlarmManager alarms = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
//        alarms.setInexactRepeating(AlarmManager.RTC_WAKEUP,
//                updateTime.getTimeInMillis(),
//                AlarmManager.INTERVAL_DAY, recurringDownload);

//        updateTimeText(c);
//        startAlarm(c);
        //new AlertDialog.Builder(this).setMessage(c.toString()).show();

    }

    private void startSundayAlarm( ) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
//        cal.set(Calendar.PM,4,7,5,5,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), Calendar.SUNDAY, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        /*if (cal.before(Calendar.getInstance())) {
            cal.add(Calendar.DATE, 1);
        }*/
        //alarmManager.setInexactRepeating(RTC_WAKEUP, c.getTimeInMillis(), INTERVAL_DAY*7, pendingIntent);
        //alarmManager.setExact(RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),7*24*60*60*1000,pendingIntent);
//        alarmManager.setExact(RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
    }

    private void startMondayAlarm() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
//        cal.set(Calendar.PM,4,7,5,10,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), Calendar.MONDAY, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        /*if (cal.before(Calendar.getInstance())) {
            cal.add(Calendar.DATE, 1);
        }*/

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),7*24*60*60*1000,pendingIntent);

    }

    private void startTuesdayAlarm(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.DAY_OF_WEEK,Calendar.TUESDAY);
//        cal.set(Calendar.PM,4,7,5,10,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), Calendar.TUESDAY, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        /*if (cal.before(Calendar.getInstance())) {
            cal.add(Calendar.DATE, 1);
        }*/

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),7*24*60*60*1000,pendingIntent);
    }

    private void startWednesdayAlarm(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY);
//        cal.set(Calendar.PM,4,7,5,10,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), Calendar.WEDNESDAY, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        /*if (cal.before(Calendar.getInstance())) {
            cal.add(Calendar.DATE, 1);
        }*/

        // TODO: change repeat interval
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),2*60*1000,pendingIntent);
    }

    private void startThursdayAlarm(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.DAY_OF_WEEK,Calendar.THURSDAY);
//        cal.set(Calendar.PM,4,7,5,10,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), Calendar.THURSDAY, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        /*if (cal.before(Calendar.getInstance())) {
            cal.add(Calendar.DATE, 1);
        }*/

        // todo : change repeat interval time
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),2*60*1000,pendingIntent);
    }

    private void startFridayAlarm(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);
//        cal.set(Calendar.PM,4,7,5,10,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), Calendar.FRIDAY, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        /*if (cal.before(Calendar.getInstance())) {
            cal.add(Calendar.DATE, 1);
        }*/

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),7*24*60*60*1000,pendingIntent);
    }

    private void startSaturdayAlarm(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.DAY_OF_WEEK,Calendar.SATURDAY);
//        cal.set(Calendar.PM,4,7,5,10,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), Calendar.SATURDAY, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        /*if (cal.before(Calendar.getInstance())) {
            cal.add(Calendar.DATE, 1);
        }*/

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis(),7*24*60*60*1000,pendingIntent);
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(MainActivity.this).setTitle("Set Alarm?")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (isTimeSet){
                            setRequiredAlarms();
//                            finish();
                        }else {
                            Toast.makeText(MainActivity.this, "Please Set time first!", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNeutralButton("Cancel All Alarms", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cancelAllAlarms();
//                        finish();
                    }
                })
                .show();
//        super.onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setRequiredAlarms(){

//        boolean isContains = Arrays.stream(weekDays).anyMatch("1"::equals);
        boolean isContains = false;
        for (String e : weekDays){
            if (e.equals("1")) {
                isContains = true;
                break;
            }
        }


        if (isContains){
            if (weekDays[0].equals("1")) {
                startSundayAlarm();
            }
            if (weekDays[1].equals("1")) {
                startMondayAlarm();
            }
            if (weekDays[2].equals("1")) {
                startTuesdayAlarm();
            }
            if (weekDays[3].equals("1")) {
                startWednesdayAlarm();
            }
            if (weekDays[4].equals("1")) {
                startThursdayAlarm();
            }
            if (weekDays[5].equals("1")) {
                startFridayAlarm();
            }
            if (weekDays[6].equals("1")) {
                startSaturdayAlarm();
            }
        }else {
            setTodayAlarm();
        }
    }

    private void setTodayAlarm() {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        c.set(Calendar.SECOND,0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), c.get(Calendar.DAY_OF_WEEK), intent, PendingIntent.FLAG_UPDATE_CURRENT);

        /*if (cal.before(Calendar.getInstance())) {
            cal.add(Calendar.DATE, 1);
        }*/

        // todo : change repeat interval time
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),/*7*24*60*/2*60*1000,pendingIntent);
    }

    private void updateTimeText(Calendar c) {
        String timeText = "Alarm set for: ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());
        b.timeTV.setText(timeText);
    }



    private void cancelAllAlarms() {
        cancelSundayAlarm();
        cancelMondayAlarm();
        cancelTuesdayAlarm();
        cancelWednesdayAlarm();
        cancelThursdayAlarm();
        cancelFridayAlarm();
        cancelSaturdayAlarm();
    }

    private void cancelSundayAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), Calendar.SUNDAY, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
//        b.timeTV.setText("Alarm canceled");

//        ringtone.stop();
    }

    private void cancelMondayAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), Calendar.MONDAY, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
//        b.timeTV.setText("Alarm canceled");

    }

    private void cancelTuesdayAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), Calendar.TUESDAY, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
//        b.timeTV.setText("Alarm canceled");

    }

    private void cancelWednesdayAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), Calendar.WEDNESDAY, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
//        b.timeTV.setText("Alarm canceled");

    }

    private void cancelThursdayAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), Calendar.THURSDAY, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
//        b.timeTV.setText("Alarm canceled");

    }

    private void cancelFridayAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), Calendar.FRIDAY, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
//        b.timeTV.setText("Alarm canceled");

    }

    private void cancelSaturdayAlarm() {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), Calendar.SATURDAY, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.cancel(pendingIntent);
//        b.timeTV.setText("Alarm canceled");

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handleWeeklyPaidButtons() {
        b.sundayBtn.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                if (Objects.equals(b.sundayBtn.getBackground().getConstantState(), getResources().getDrawable(R.drawable.border).getConstantState())) {
                    b.sundayBtn.setBackground(getDrawable(R.drawable.weekdaybg));
                    weekDays[0] = "1";

                } else {
                    b.sundayBtn.setBackground(getDrawable(R.drawable.border));
                    weekDays[0] = "0";
                }
            }
        });
        b.mondayBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                if (Objects.equals(b.mondayBtn.getBackground().getConstantState(), getResources().getDrawable(R.drawable.border).getConstantState())) {
                    b.mondayBtn.setBackground(getDrawable(R.drawable.weekdaybg));
                    weekDays[1] = "1";
                } else {
                    b.mondayBtn.setBackground(getDrawable(R.drawable.border));
                    weekDays[1] = "0";
                }
            }
        });
        b.tuesdayBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                if (Objects.equals(b.tuesdayBtn.getBackground().getConstantState(),getResources().getDrawable(R.drawable.border).getConstantState())) {
                    b.tuesdayBtn.setBackground(getDrawable(R.drawable.weekdaybg));
                    weekDays[2] = "1";
                } else {
                    b.tuesdayBtn.setBackground(getDrawable(R.drawable.border));
                    weekDays[2] = "0";
                }
            }
        });
        b.wednesdayBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                if (Objects.equals(b.wednesdayBtn.getBackground().getConstantState(), getResources().getDrawable(R.drawable.border).getConstantState())) {
                    b.wednesdayBtn.setBackground(getDrawable(R.drawable.weekdaybg));
                    weekDays[3] = "1";
                } else {
                    b.wednesdayBtn.setBackground(getDrawable(R.drawable.border));
                    weekDays[3] = "0";
                }
            }
        });
        b.thursdayBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                if (Objects.equals(b.thursdayBtn.getBackground().getConstantState(), getResources().getDrawable(R.drawable.border).getConstantState())) {
                    b.thursdayBtn.setBackground(getDrawable(R.drawable.weekdaybg));
                    weekDays[4] = "1";
                } else {
                    b.thursdayBtn.setBackground(getDrawable(R.drawable.border));
                    weekDays[4] = "0";
                }
            }
        });
        b.fridayBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                if (Objects.equals(b.fridayBtn.getBackground().getConstantState(), getResources().getDrawable(R.drawable.border).getConstantState())) {
                    b.fridayBtn.setBackground(getDrawable(R.drawable.weekdaybg));
                    weekDays[5] = "1";
                } else {
                    b.fridayBtn.setBackground(getDrawable(R.drawable.border));
                    weekDays[5] = "0";
                }
            }
        });
        b.saturdayBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {

                if (Objects.equals(b.saturdayBtn.getBackground().getConstantState(), getResources().getDrawable(R.drawable.border).getConstantState())) {
                    b.saturdayBtn.setBackground(getDrawable(R.drawable.weekdaybg));
                    weekDays[6] = "1";
                } else {
                    b.saturdayBtn.setBackground(getDrawable(R.drawable.border));
                    weekDays[6] = "0";
                }
            }
        });
    }

}