package cn.edu.hznu.afinal;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by  on 2017/9/16.
 * 自定义Dialog
 */

public class MyDialog extends Dialog {

    private View view;
    //确定文本和取消文本的显示内容
    public String testname;
    public int key=0;
    public Context context;

    public MyDialog(Context context,String tn,String timekey) {
        super(context, R.style.Mydialog);
        this.context=context;
        this.testname=tn;
        if(timekey.equals("10秒")){
            this.key=10000;
        }else if(timekey.equals("30秒")){
            this.key=30000;
        }else{
            this.key=60000;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gif_layout);
        setCanceledOnTouchOutside(true);

        TextView titlen = (TextView) findViewById(R.id.tnamere);
        pl.droidsonroids.gif.GifImageView view1=findViewById(R.id.gv_error);
        view1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                dismiss();

            }
        });
        titlen.setText(this.testname);
        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                    dismiss();
            }
        }, this.key);

    }


}

