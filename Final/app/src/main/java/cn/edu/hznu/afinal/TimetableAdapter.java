package cn.edu.hznu.afinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

//时间表listView继承类
public class TimetableAdapter extends ArrayAdapter<Timetable> {
    Context context;
    private int resourceId;
    DatabaseHelper helper;
    SQLiteDatabase sqLiteDatabase;

    public TimetableAdapter(Context context, int textViewResource, List<Timetable> objects) {
        super(context, textViewResource, objects);
        this.context=context;
        resourceId = textViewResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        helper = new DatabaseHelper(context,"timerlist.db",null,1);
        sqLiteDatabase = helper.getWritableDatabase();

        Timetable t = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        CheckBox listCheck = (CheckBox) view.findViewById(R.id.list_check);
        TextView endTime = (TextView) view.findViewById(R.id.end_time);
        TextView nextTime = (TextView) view.findViewById(R.id.next_time);
        String notype=t.getNotype();
        listCheck.setText(t.getTableName());
        endTime.setText(t.getTdate());
        nextTime.setText(t.getTtime());
        listCheck.setChecked(t.isSolve());

        if(notype.equals("1")){
            listCheck.setChecked(true);
            listCheck.setClickable(false);
            endTime.setText(Html.fromHtml("<s>"+endTime.getText().toString()+"</s>"));
            nextTime.setText(Html.fromHtml("<s>"+nextTime.getText().toString()+"</s>"));
        }


        listCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(listCheck.isChecked()) {
                    ContentValues values=new ContentValues();
                    values.put("notype","1");
                    sqLiteDatabase.update("timer", values,"tname=?" , new String[]{t.getTableName()});

                    listCheck.setClickable(false);
                    t.setSolve(true);
                    endTime.setText(Html.fromHtml("<s>"+endTime.getText().toString()+"</s>"));
                    nextTime.setText(Html.fromHtml("<s>"+nextTime.getText().toString()+"</s>"));
                }
            }
        });
        return view;
    }
}

