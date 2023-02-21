package cn.edu.hznu.labbasicui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import androidx.appcompat.app.AlertDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button change =(Button) findViewById(R.id.change);
        Button check =(Button) findViewById(R.id.check);
        EditText numedt =(EditText) findViewById(R.id.number);
        ProgressBar prog = findViewById(R.id.progress);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int  num = Integer.parseInt(numedt.getText().toString());
                if(num>=1 && num<=100){
                    prog.setProgress(num);
                }else{
                    prog.setProgress(0);
                    numedt.setText("");
                    AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Indication");
                    dialog.setMessage("输入数字不合法！");
                    dialog.setNegativeButton("OK",null);
                    dialog.show();
                }
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = prog.getProgress();
                AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Progress Value");
                dialog.setMessage("当前值："+value);
                dialog.setNegativeButton("OK",null);
                dialog.show();
            }
        });
    }
}