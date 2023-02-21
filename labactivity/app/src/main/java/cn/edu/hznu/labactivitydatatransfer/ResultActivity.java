package cn.edu.hznu.labactivitydatatransfer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView name =(TextView) findViewById(R.id.name);
        TextView passwd =(TextView) findViewById(R.id.passwd);
        TextView gender =(TextView) findViewById(R.id.gender);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        name.setText(bundle.getString("name"));
        passwd.setText(bundle.getString("passwd"));
        gender.setText(bundle.getString("gender"));

        Button btn_back=(Button) findViewById(R.id.back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
