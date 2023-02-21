package cn.edu.hznu.labnav;

import android.content.Context;
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
    private int resourceId;

    public TimetableAdapter(Context context, int textViewResource, List<Timetable> objects) {
        super(context, textViewResource, objects);
        resourceId = textViewResource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Timetable t = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        CheckBox listCheck = (CheckBox) view.findViewById(R.id.list_check);
        TextView endTime = (TextView) view.findViewById(R.id.end_time);
        TextView nextTime = (TextView) view.findViewById(R.id.next_time);
        endTime.setText(t.getEndTime());
        nextTime.setText(t.getNextTime());
        listCheck.setChecked(t.isSolve());
        listCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(listCheck.isChecked())
                    t.setSolve(true);
            }
        });
        return view;
    }
}
