package cn.edu.hznu.afinal;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.Html;

public class AlarmActivity extends Activity {
    DatabaseHelper helper;
    SQLiteDatabase sqLiteDatabase;

    @SuppressLint("Range")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        //显示闹钟提醒框
//        new AlertDialog.Builder(AlarmActivity.this)
//                .setTitle("闹钟")
//                .setMessage("时间到了")
//                .setPositiveButton("确定", new DialogInterface.OnClickListener(){
//                    public void onClick(DialogInterface dialogInterface, int which) {
//                        AlarmActivity.this.finish();
//                    }
//                }).create().show();

        MyDialog myDialog=new MyDialog(AlarmActivity.this,intent.getStringExtra("name"),intent.getStringExtra("timekey"));
        myDialog.show();
        myDialog.setOnDismissListener(dialogInterface -> this.finish());

        helper = new DatabaseHelper(this,"timerlist.db",null,1);
        sqLiteDatabase = helper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("notype","1");
        sqLiteDatabase.update("timer", values,"tname=?" , new String[]{intent.getStringExtra("name")});
    }
}
