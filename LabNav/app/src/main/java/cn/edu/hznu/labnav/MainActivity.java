package cn.edu.hznu.labnav;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout dr;
    private List<Timetable> contactList = new ArrayList<>();
    TimetableAdapter adapter;

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
            default:
                break;
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTimetable();
        adapter = new TimetableAdapter(MainActivity.this,R.layout.timetable_list,
                contactList);
        ListView listView = (ListView) findViewById(R.id.list_table);
        listView.setAdapter(adapter);

        //侧面弹窗菜单项目的点击事件
        NavigationView nv = (NavigationView) findViewById(R.id.navigation_view);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.item1:
                        Toast.makeText(MainActivity.this,"手机号为：",Toast.LENGTH_LONG).show();
                        break;
                    case R.id.item2:
                        //修改用户名弹窗
                        LayoutInflater layoutInflater1 = LayoutInflater.from(MainActivity.this);
                        View v1 = layoutInflater1.inflate(R.layout.edit_name, null);
                        Button submitName = (Button) findViewById(R.id.submit_name);
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
                        builder1.setView(v1).show();
//                        submitName.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//
//                            }
//                        });

                        break;
                    case R.id.item3:
                        //修改密码弹窗
                        LayoutInflater layoutInflater2 = LayoutInflater.from(MainActivity.this);
                        View v2 = layoutInflater2.inflate(R.layout.edit_password, null);
                        Button submitPassword = (Button) findViewById(R.id.submit_password);
                        AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                        builder2.setView(v2).show();
//                        submitPassword.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//
//                            }
//                        });

                        break;

                }
                return false;
            }
        });
    }

    //初始化时间表
    private void initTimetable(){
        Timetable t = new Timetable("下一次时间：2022-10-22 20：00","提醒日期： 周三 周五 周六",
                "重复","劳动教育",false);
        contactList.add(t);
        t = new Timetable("下一次时间：2022-10-22 20：00","截止日期：2022-12-20",
                "一次","期末考试",false);
        contactList.add(t);
        contactList.add(t);
        contactList.add(t);
        contactList.add(t);
    }

}