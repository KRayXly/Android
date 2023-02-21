package cn.edu.hznu.labfileproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name = (EditText) findViewById(R.id.name);
        EditText content = (EditText) findViewById(R.id.content);
        Button save = (Button) findViewById(R.id.save);
        Button load = (Button) findViewById(R.id.load);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputName = name.getText().toString();
                String inputText = content.getText().toString();
                if(inputName.equals("")){
                    AlertDialog.Builder dialog = alertDialog();
                    dialog.show();
                }else{
                    savet(inputName,inputText);
                    Toast.makeText(MainActivity.this,"Data have been saved.",Toast.LENGTH_SHORT).show();
                    content.setText("");
                }

            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputName = name.getText().toString();
                String inputText = content.getText().toString();
                if(inputName.equals("")){
                    AlertDialog.Builder dialog = alertDialog();
                    dialog.show();
                }else {
                    content.setText(loadt(inputName));
                    Toast.makeText(MainActivity.this, "Data have been loaded.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void savet(String inputName,String inputText){
        FileOutputStream out =null;
        BufferedWriter writer =null;
        try {
            out = openFileOutput(inputName, Context.MODE_PRIVATE);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(inputText);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                if(writer != null){
                    writer.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    public String loadt(String inputName){
        FileInputStream in =null;
        BufferedReader reader =null;
        StringBuilder content =new StringBuilder();
        try {
            in = openFileInput(inputName);
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine())!= null){
                content.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (reader != null){
                try{
                    reader.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }
    public AlertDialog.Builder alertDialog(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("Waring");
        dialog.setMessage("文件名不能为空");
        dialog.setNegativeButton("OK",null);
        return dialog;
    }
}