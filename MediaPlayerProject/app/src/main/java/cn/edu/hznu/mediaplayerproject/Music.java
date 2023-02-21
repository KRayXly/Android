package cn.edu.hznu.mediaplayerproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Music {
    private int id;
    private String name;
    private String signer;
    private String timer;
    private String path;

    public Music(int id,String name,String signer,int timer,String path){
        this.id=id;
        this.name=name;
        this.signer=signer;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(timer);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(timer) - TimeUnit.MINUTES.toSeconds(minutes);
        this.timer=String.format("%02d:%02d", minutes, seconds);
        this.path=path;
    }
    public int getID() {return id;}
    public String getName() {return name;}
    public String getSigner() {return signer;}
    public String getTimer() {return timer;}
    public String getPath() {return path;}
}
