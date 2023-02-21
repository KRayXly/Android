package cn.edu.hznu.labaddressclient;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity  {
    Uri uri = Uri.parse("content://cn.edu.hznu.databaseproject.provider/contact");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_layout);
        Button adddata=(Button) findViewById(R.id.addbt);
        adddata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values =new ContentValues();
                EditText nameg =(EditText) findViewById(R.id.nameget);
                EditText numg =(EditText) findViewById(R.id.phoneget);
                if (nameg.getText().toString().equals("")){
                    Toast.makeText(AddActivity.this,"请输入姓名",Toast.LENGTH_SHORT).show();
                }
                else if(numg.getText().toString().equals("")){
                    Toast.makeText(AddActivity.this,"请输入电话号码",Toast.LENGTH_SHORT).show();
                }
                else{
                    values.put("name",nameg.getText().toString());
                    values.put("mobile",numg.getText().toString());
                    getContentResolver().insert(uri,values);
                    values.clear();
                    Intent intent=new Intent(AddActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}
