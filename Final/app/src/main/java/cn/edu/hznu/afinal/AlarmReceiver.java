package cn.edu.hznu.afinal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, AlarmActivity.class);
        i.putExtra("name",intent.getStringExtra("name"));
        i.putExtra("timekey",intent.getStringExtra("timekey"));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Log.d("AlarmReceiver",intent.getStringExtra("timekey"));
        Log.d("AlarmReceiver",intent.getStringExtra("name"));
        context.startActivity(i);
    }
}