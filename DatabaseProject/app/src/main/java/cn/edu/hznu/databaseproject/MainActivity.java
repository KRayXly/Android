package cn.edu.hznu.databaseproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Phone> phoneList = new ArrayList<>();
    private  RecyclerView recyclerView;
    private  MyDatabase dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new MyDatabase(this,"phone.db",null,1);
        dbHelper.getWritableDatabase();
        initData();
        recyclerView=(RecyclerView)findViewById(R.id.listv);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        PhoneAdapter adapter=new PhoneAdapter(phoneList);
        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuadd:
                addv();
                break;
            case R.id.menude:
                deletev();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void initData(){
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor =db.query("contact",null,null,null,null,null,null);
        if(cursor.moveToFirst()) {
            do {
                @SuppressWarnings("Range")
                String id = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressWarnings("Range")
                String namefor = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressWarnings("Range")
                String numfor = cursor.getString(cursor.getColumnIndex("mobile"));
                Phone lone = new Phone(namefor, numfor,false,id);
                phoneList.add(lone);
            } while (cursor.moveToNext());
        }
    }
    public void addv(){
        Intent intent=new Intent(MainActivity.this,AddActivity.class);
        startActivity(intent);
    }
    public void deletev(){
        List<Phone> fiList = new ArrayList<>();
        for (Phone phone :phoneList) {
            if (phone.getCheck()) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                db.delete("contact", "id=?", new String[]{phone.getid()});
            }else{
                fiList.add(phone);
            }
        }
        PhoneAdapter adapter=new PhoneAdapter(fiList);
        recyclerView.setAdapter(adapter);

    }

}