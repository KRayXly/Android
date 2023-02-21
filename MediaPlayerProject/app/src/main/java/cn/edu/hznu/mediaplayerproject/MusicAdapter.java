package cn.edu.hznu.mediaplayerproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MusicAdapter extends ArrayAdapter<Music>  {
    private int resoureId;
    private int selectItem = -1 ;
    public MusicAdapter(Context context, int textViewResoureId, List<Music> objects){
        super(context,textViewResoureId,objects);
        resoureId=textViewResoureId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Music music=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resoureId,parent,false);
        TextView id=(TextView) view.findViewById(R.id.id);
        TextView name=(TextView) view.findViewById(R.id.name);
        TextView sing=(TextView) view.findViewById(R.id.signer);
        TextView timer=(TextView) view.findViewById(R.id.time);
        id.setText(music.getID()+"");
        name.setText(music.getName());
        sing.setText(music.getSigner());
        timer.setText(music.getTimer());
        return view;
    }
    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem ;
    }
}
