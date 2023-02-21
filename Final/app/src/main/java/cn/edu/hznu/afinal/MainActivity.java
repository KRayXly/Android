package cn.edu.hznu.afinal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import pl.droidsonroids.gif.GifDrawable;

public class MainActivity extends AppCompatActivity {
    private AlarmManager alarmManager;
    private Calendar c;
    private  DatabaseHelper myDatabaset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c=Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        myDatabaset=new DatabaseHelper(this,"timerlist.db",null,1);
        myDatabaset.getWritableDatabase();

        //设置顶部菜单栏
        String menuTitle = "召唤小火龙";
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(R.layout.title_layout);//设置标题样式
            TextView textView = (TextView) actionBar.getCustomView().findViewById(R.id.display_title);//获取标题布局的textview
            textView.setText(menuTitle);//设置标题名称，menuTitle为String字符串
            actionBar.setHomeButtonEnabled(true);//设置左上角的图标是否可以点击
            actionBar.setDisplayHomeAsUpEnabled(true);//给左上角图标的左边加上一个返回的图标
            actionBar.setDisplayShowCustomEnabled(true);// 使自定义的普通View能在title栏显示，即actionBar.setCustomView能起作用
        }
        //设置日期
        TextView tdate = findViewById(R.id.tdate);
        tdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                //create a datePickerDialog and then shoe it on your screen
                new DatePickerDialog(MainActivity.this,//binding the listener for your DatePickerDialog
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                tdate.setText(year+"年"+(month+1)+"月"+dayOfMonth+"日");
                                //Toast.makeText(MainActivity.this,"Year:" + year + " Month:" + month + " Day:" + dayOfMonth,Toast.LENGTH_SHORT).show();
                                c.set(year, month, dayOfMonth);
                            }
                        }
                        , calendar.get(Calendar.YEAR)
                        , calendar.get(Calendar.MONTH)
                        , calendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        //设置时间
        TextView ttime = findViewById(R.id.ttime);
        ttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();

                new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                String hour,min;
                                if(hourOfDay<10)
                                    hour="0"+hourOfDay;
                                else
                                    hour=""+hourOfDay;
                                if(minute<10)
                                    min="0"+minute;
                                else
                                    min=""+minute;
                                ttime.setText(hour+":"+min);
                               //Toast.makeText(MainActivity.this,"Hour:" + hourOfDay + " Minute:" + minute ,Toast.LENGTH_SHORT).show();
                                c.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                c.set(Calendar.MINUTE, minute);
                            }
                        }
                        , calendar.get(Calendar.HOUR_OF_DAY)
                        , calendar.get(Calendar.MINUTE)
                        , true).show();
            }
        });

        //设置效率
        TextView tretime= findViewById(R.id.tretime);
        tretime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] times={"10秒","30秒","一分钟"};
                new AlertDialog.Builder(MainActivity.this)
                        .setCancelable(false)
                        //.setTitle("喷火时间")
                        .setPositiveButton("ok", null)
                        .setSingleChoiceItems(times, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                tretime.setText(times[i]);
                            }
                        })
                        .show();
            }
        });
        EditText tname=findViewById(R.id.tname);
        Button save=findViewById(R.id.save);
        pl.droidsonroids.gif.GifImageView view1=findViewById(R.id.gv_error);
        save.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                if (tname.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"请输入事件名称",Toast.LENGTH_SHORT).show();
                }
                else if(tdate.getText().toString().equals(">")){
                    Toast.makeText(MainActivity.this,"请选择日期！",Toast.LENGTH_SHORT).show();
                }
                else if(ttime.getText().toString().equals(">")){
                    Toast.makeText(MainActivity.this,"请选择时间！",Toast.LENGTH_SHORT).show();
                }
                else if(tretime.getText().toString().equals(">")){
                    Toast.makeText(MainActivity.this,"请选择时长！",Toast.LENGTH_SHORT).show();
                }
                else{
                    SQLiteDatabase db=myDatabaset.getWritableDatabase();
                    ContentValues values =new ContentValues();
                    //values.put("tid",tname.getText().toString());
                    Intent i=getIntent();
                    values.put("phone",i.getStringExtra("phone"));
                    values.put("tname",tname.getText().toString());
                    values.put("tdate",tdate.getText().toString());
                    values.put("ttime",ttime.getText().toString());
                    values.put("tretime",tretime.getText().toString());
                    values.put("notype","0");
                    db.insert("timer",null,values);
                    values.clear();

                    //view1.setImageResource(R.drawable.long_gif);
                    Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
                    intent.putExtra("name",tname.getText().toString());
                    intent.putExtra("timekey",tretime.getText().toString());

                    Log.d("MainActivity",tname.getText().toString());
                    //创建pendingIntent
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this,0X102, intent,0);
                    //设置闹钟
                    alarmManager.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
                    Toast.makeText(MainActivity.this,"呼叫成功！",Toast.LENGTH_SHORT).show();

                    Intent itt=new Intent(MainActivity.this, ListActivity.class);
                    intent.putExtra("phone",i.getStringExtra("phone"));
                    Log.d("Acccaaa",i.getStringExtra("phone"));
                    startActivity(itt);
                }

            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }






}