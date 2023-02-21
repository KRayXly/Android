package cn.edu.hznu.labaddressclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PhoneAdapter extends ArrayAdapter<Phone> {
    private int resoureId;
    public PhoneAdapter(Context context, int textViewResoureId, List<Phone> objects){
        super(context,textViewResoureId,objects);
        resoureId=textViewResoureId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Phone phone=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resoureId,parent,false);
        TextView namer=(TextView) view.findViewById(R.id.nameresult);
        TextView phoner=(TextView) view.findViewById(R.id.phoneresult);
        namer.setText(phone.getName());
        phoner.setText(phone.getPhone());
        return view;
    }
}
