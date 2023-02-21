package cn.edu.hznu.firstlab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MessageActivity","Running in onCreate()");

        //获取按钮
        Button button1 = (Button) findViewById(R.id.button_view_message);

        //为按钮注册事件监听
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //输出用户提示信息
                Toast.makeText(MainActivity.this,"You clicked Button",
                        Toast.LENGTH_SHORT).show();
                //启动Message
                Intent intent =new Intent(MainActivity.this,
                        MessageActivity.class);
                startActivity(intent);

            }
        });
    }
}