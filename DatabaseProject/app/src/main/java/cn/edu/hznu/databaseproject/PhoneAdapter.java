package cn.edu.hznu.databaseproject;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneAdapter.ViewHolder>{
    public List<Phone> mPhoneList;
    static class  ViewHolder extends RecyclerView.ViewHolder {
        View phoneView;
        TextView name;
        TextView phonen;
        CheckBox check;

        public ViewHolder(View view){
            super(view);
            phoneView = view;
            name= (TextView) view.findViewById(R.id.name);
            phonen = (TextView) view.findViewById(R.id.phone);
            check = (CheckBox) view.findViewById(R.id.checked);
        }
    }
    public  PhoneAdapter(List<Phone> objects){
        mPhoneList = objects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listline,parent,false);
        final ViewHolder holder=new ViewHolder(view);
        CheckBox ch =(CheckBox) view.findViewById(R.id.checked);
        holder.check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    int position = holder.getAdapterPosition();
                    Phone phone=mPhoneList.get(position);
                    phone.setChecked(true);
                }
            }
        });
        holder.phoneView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Phone phone=mPhoneList.get(position);
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phone.getPhone()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder,int position){
        Phone phone=mPhoneList.get(position);
        holder.name.setText(phone.getName());
        holder.phonen.setText(phone.getPhone());
    }
    @Override
    public int getItemCount() {
        return mPhoneList.size();
    }
}
