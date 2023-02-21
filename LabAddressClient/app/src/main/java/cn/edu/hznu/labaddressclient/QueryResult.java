package cn.edu.hznu.labaddressclient;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class QueryResult extends AppCompatActivity {
    Uri uri= Uri.parse("content://cn.edu.hznu.databaseproject.provider/contact");
    private List<Phone> phonelist=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.query_result);
        Intent intent=getIntent();
        initData(intent.getStringExtra("way"),intent.getStringExtra("key"));
        PhoneAdapter adapter=new PhoneAdapter(QueryResult.this,R.layout.query_list,phonelist);
        ListView listView=(ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        Button back=(Button) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QueryResult.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private void initData(String way,String keyw){
        Cursor cursor =getContentResolver().query(uri,null,null,null,null,null);
        if(cursor.moveToFirst()) {
            do {
                @SuppressWarnings("Range")
                String id = cursor.getString(cursor.getColumnIndex("id"));
                @SuppressWarnings("Range")
                String namefor = cursor.getString(cursor.getColumnIndex("name"));
                @SuppressWarnings("Range")
                String numfor = cursor.getString(cursor.getColumnIndex("mobile"));
                if(way.equals("按姓名")){
                    if(namefor.contains(keyw)){
                        Phone lone = new Phone(namefor, numfor,id);
                        phonelist.add(lone);
                    }
                }
                else if(way.equals("按手机号码")){
                    if(numfor.contains("7")){
                        Phone lone = new Phone(namefor, numfor,id);
                        phonelist.add(lone);
                    }
                }
            } while (cursor.moveToNext());
            cursor.close();
        }
    }
}
