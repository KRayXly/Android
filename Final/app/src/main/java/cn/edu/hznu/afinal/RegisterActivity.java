package cn.edu.hznu.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText rphone = (EditText)findViewById(R.id.rphone);
        EditText rusername = (EditText)findViewById(R.id.rusername);
        EditText rpassword = (EditText)findViewById(R.id.rpassword);
        Button register = (Button) findViewById(R.id.rregister);
        Button login = findViewById(R.id.rlogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        helper = new DatabaseHelper(this,"timerlist.db",null,1);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = rusername.getText().toString();
                String pw = rpassword.getText().toString();
                String phone = rphone.getText().toString();
                if(name.equals("")||pw.equals("")){
                    Toast.makeText(RegisterActivity.this,"账号或密码不能为空"+name,Toast.LENGTH_LONG).show();
                }
                else{
                    sqLiteDatabase = helper.getWritableDatabase();
                    Cursor cursor = sqLiteDatabase.query("user", new String[]{"password"},"phone=?" , new String[]{phone}, null, null, null);
                    //游标的遍历，寻找该手机是否注册账号
                    if(cursor.moveToNext()){
                        Toast.makeText(RegisterActivity.this,"该手机号已注册",Toast.LENGTH_LONG);
                    }
                    else {
                        ContentValues values = new ContentValues();
                        values.put("phone", phone);
                        values.put("name", name);
                        values.put("password", pw);
                        sqLiteDatabase.insert("user", null, values);
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        intent.putExtra("phone", phone);
                        startActivity(intent);
                    }
                }
            }
        });
    }
}