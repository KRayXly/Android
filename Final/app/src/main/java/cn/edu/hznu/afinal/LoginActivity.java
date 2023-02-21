package cn.edu.hznu.afinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText lphone = (EditText) findViewById(R.id.lphone);
        EditText lpassword = (EditText)findViewById(R.id.lpassword);
        Button login = (Button) findViewById(R.id.login);
        Button register = (Button) findViewById(R.id.register);

        helper = new DatabaseHelper(this,"timerlist.db",null,1);
        sqLiteDatabase = helper.getWritableDatabase();

        //登录验证
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = lphone.getText().toString();
                String pw = lpassword.getText().toString();
                if (phone.equals("")) {
                    Toast.makeText(LoginActivity.this, "手机号或密码不能为空", Toast.LENGTH_SHORT).show();
                }else {
                    Cursor cursor = sqLiteDatabase.query("user", new String[]{"password"},"phone=?" , new String[]{phone}, null, null, null);
                    //游标的遍历，寻找name对应的password的值
                    if(cursor.moveToNext()){
                        @SuppressLint("Range")
                        String pswQ=cursor.getString(cursor.getColumnIndex("password"));
                        //用户名对应的密码与输入的密码相同时
                        if(pw.equals(pswQ)){
                            Toast.makeText(LoginActivity.this, "登录成功!", Toast.LENGTH_SHORT).show();
                            //跳转到主页面
                            Intent intent=new Intent (LoginActivity.this,ListActivity.class);
                            //intent传递用户数据
                            intent.putExtra("phone",phone);
                            //开始跳转事件
                            startActivity(intent);
                        }
                        //密码输入错误时
                        else{
                            Toast.makeText(LoginActivity.this, "密码输入错误", Toast.LENGTH_SHORT).show();
                        }
                    }
                    //遍历完后发现在表中找不到游标携带的name的值时
                    else{
                        Toast.makeText(LoginActivity.this, "账号不存在，请先注册！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //跳转注册界面
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}