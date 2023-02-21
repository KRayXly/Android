package cn.edu.hznu.labaddressclient;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

public class QueryActivity extends AppCompatActivity {
    Uri uri = Uri.parse("content://cn.edu.hznu.databaseproject.provider/contact");
    RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.query_layout);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioButton =(RadioButton) QueryActivity.this.findViewById(radioGroup.getCheckedRadioButtonId());
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                radioButton =(RadioButton) QueryActivity.this.findViewById(radioGroup.getCheckedRadioButtonId());
            }
        });
        Button querybt = (Button) findViewById(R.id.quebt);
        EditText keyword = (EditText) findViewById(R.id.keyw);
        querybt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(QueryActivity.this,QueryResult.class);
                intent.putExtra("way", radioButton.getText().toString());
                intent.putExtra("key", keyword.getText().toString());
                startActivity(intent);
            }
        });
    }
}