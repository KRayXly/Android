package cn.edu.hznu.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private DrawerLayout dr;
    private List<Timetable> contactList = new ArrayList<>();
    TimetableAdapter adapter;

    //数据库
    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper helper;
    String password;
    String name;
    String phone;
    AlertDialog dialog;

    //菜单项（个人界面）建立
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bar_menu, menu);
        return true;
    }
    //菜单项（个人界面）事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user_nav:
                dr = (DrawerLayout) findViewById(R.id.main_layout );
                dr.openDrawer(Gravity.LEFT);
                break;
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);

    }


    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //设置顶部菜单栏
        String menuTitle = "喷火提醒";
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setCustomView(R.layout.title_layout);//设置标题样式
            TextView textView = (TextView) actionBar.getCustomView().findViewById(R.id.display_title);//获取标题布局的textview
            textView.setText(menuTitle);//设置标题名称，menuTitle为String字符串
            actionBar.setHomeButtonEnabled(true);//设置左上角的图标是否可以点击
            actionBar.setDisplayHomeAsUpEnabled(true);//给左上角图标的左边加上一个返回的图标
            actionBar.setDisplayShowCustomEnabled(true);// 使自定义的普通View能在title栏显示，即actionBar.setCustomView能起作用
        }

        //数据库
        helper = new DatabaseHelper(this,"timerlist.db",null,1);
        sqLiteDatabase = helper.getWritableDatabase();

        NavigationView nv = (NavigationView) findViewById(R.id.navigation_view);
        View headerView = nv.inflateHeaderView(R.layout.nav_header);
        TextView username1 = headerView.findViewById(R.id.user_name);
        //从登录注册界面获取手机号
        Intent intent = getIntent();
        phone = intent.getStringExtra("phone");
        //Toast.makeText(ListActivity.this,phone,Toast.LENGTH_SHORT).show();
        //通过手机号查找用户信息
        Log.d("Acccbbb",intent.getStringExtra("phone"));
        Cursor cursor = sqLiteDatabase.query("user", null,"phone=?" , new String[]{phone}, null, null, null);
        //游标的遍历，寻找name对应的password的值
        if(cursor.moveToNext()) {
            password = cursor.getString(cursor.getColumnIndex("password"));
            name = cursor.getString(cursor.getColumnIndex("name"));
        }
        username1.setText(name);
        //图片点击事件
        ImageView imageView = findViewById(R.id.imageButton);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this,MainActivity.class);
                intent.putExtra("phone",phone);
                startActivity(intent);
            }
        });

        initTimetable();
        adapter = new TimetableAdapter(ListActivity.this,R.layout.timetable,
                contactList);
        ListView listView = (ListView) findViewById(R.id.list_table);
        listView.setAdapter(adapter);
        //listView点击跳转到修改事件界面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            }
        });

        //侧面弹窗菜单项目的点击事件

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item1:
                        Toast.makeText(ListActivity.this,"手机号为："+phone,Toast.LENGTH_LONG).show();
                        break;
                    case R.id.item2:
                        //修改用户名弹窗
                        LayoutInflater layoutInflater1 = LayoutInflater.from(ListActivity.this);
                        View v1 = layoutInflater1.inflate(R.layout.edit_name, null);
                        dialog = new AlertDialog.Builder(ListActivity.this)
                                .setTitle("修改用户名")
                                .setView(v1)
                                .create();
                        dialog.show();
                        Button submitName = dialog.getWindow().findViewById(R.id.submit_name);
                        @SuppressLint("ResourceType")
                        TextView newname = dialog.getWindow().findViewById(R.id.new_name);
                        submitName.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                name = newname.getText().toString();
                                sqLiteDatabase = helper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put("name",name);
                                values.put("password",password);
                                values.put("phone",phone);
                                sqLiteDatabase.update("user",values,"phone=?",new String[]{phone});
                                username1.setText(name);
                                dialog.hide();
                            }
                        });

                        break;
                    case R.id.item3:
                        //修改密码弹窗
                        LayoutInflater layoutInflater2 = LayoutInflater.from(ListActivity.this);
                        View v2 = layoutInflater2.inflate(R.layout.edit_password, null);
                        dialog = new AlertDialog.Builder(ListActivity.this)
                                .setTitle("修改密码")
                                .setView(v2)
                                .create();
                        dialog.show();
                        Button submitPassword = dialog.getWindow().findViewById(R.id.submit_password);
                        @SuppressLint("ResourceType")
                        TextView newpassword = dialog.getWindow().findViewById(R.id.new_password);
                        submitPassword.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                sqLiteDatabase = helper.getWritableDatabase();
                                password = newpassword.getText().toString();
                                sqLiteDatabase = helper.getWritableDatabase();
                                ContentValues values = new ContentValues();
                                values.put("name",name);
                                values.put("password",password);
                                values.put("phone",phone);
                                sqLiteDatabase.update("user",values,"phone=?",new String[]{phone});
                                dialog.hide();
                            }
                        });

                        break;
                    case R.id.item4:
                        //返回登录界面
                        Intent i = new Intent(ListActivity.this,LoginActivity.class);
                        startActivity(i);

                        break;
                }
                return false;
            }
        });
    }

    //初始化时间表
    @SuppressLint("Range")
    private void initTimetable(){
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.query("timer", null,"phone=?" , new String[]{phone}, null, null, null);
        if(cursor.moveToNext()){
            do{
                @SuppressLint("Range")
                String tdata = "截止日期："+cursor.getString(cursor.getColumnIndex("tdate"));
                @SuppressLint("Range")
                String ttime = "截止时间："+cursor.getString(cursor.getColumnIndex("ttime"));
                @SuppressLint("Range")
                String notype= cursor.getString(cursor.getColumnIndex("notype"));
                @SuppressLint("Range")
                String tableName = cursor.getString(cursor.getColumnIndex("tname"));

                contactList.add(new Timetable(tdata,ttime,tableName,notype,false));
            }while(cursor.moveToNext());
        }
        cursor.close();
    }

}
