package cn.edu.hznu.mediaplayerproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int po;
    private MediaPlayer mediaPlayer=new MediaPlayer();
    private List<Music> musiclist=new ArrayList<>();
    TextView choose_name;
    TextView choose_time;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent=getIntent();
        initData();
        MusicAdapter adapter=new MusicAdapter(MainActivity.this,R.layout.media_list,musiclist);
        listView=(ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);
        if(musiclist.size()<=0){
            Toast.makeText(MainActivity.this, "没有音乐文件！", Toast.LENGTH_LONG).show();
        }

        Button pause =(Button) findViewById(R.id.pause_btn);
        Button play =(Button) findViewById(R.id.start_btn);
        Button stop =(Button)findViewById(R.id.stop_btn);
        Button last=(Button)findViewById(R.id.last_btn);
        Button next=(Button)findViewById(R.id.next_btn);
        pause.setOnClickListener(this);
        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        last.setOnClickListener(this);
        next.setOnClickListener(this);
        choose_name=(TextView)findViewById(R.id.choose_name);
        choose_name.setHorizontallyScrolling(true);
        choose_time=(TextView)findViewById(R.id.choose_time);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int p, long l) {
                playMusic(p);
            }
        });

    }
    private void initData(){
        int i= 1;
        Cursor cursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if(cursor.moveToFirst()) {
            do {
                int id = i;
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                Music lone = new Music(id,title,artist,duration,path);
                musiclist.add(lone);
                i++;
            } while (cursor.moveToNext());
            cursor.close();
        }
    }
    private void playMusic(int position){
        mediaPlayer.reset();
        Music music=musiclist.get(position);
        choose_name.setText(music.getName());
        choose_time.setText(music.getTimer());
        try {
            listView.getChildAt(this.po).setBackgroundResource(R.color.white);
            this.po=position;
            listView.getChildAt(this.po).setBackgroundResource(R.color.purple);
            mediaPlayer.setDataSource(music.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    playNextMusic();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void playNextMusic(){
        listView.getChildAt(this.po).setBackgroundResource(R.color.white);
        if(++this.po>=musiclist.size())
            this.po=0;
        playMusic(this.po);
        listView.getChildAt(this.po).setBackgroundResource(R.color.purple);
    }
    private void playLastMusic(){
        listView.getChildAt(this.po).setBackgroundResource(R.color.white);
        if(--this.po<0)
            this.po=musiclist.size()-1;
        playMusic(this.po);
        listView.getChildAt(this.po).setBackgroundResource(R.color.purple);
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.pause_btn:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
                break;
            case R.id.start_btn:
                if (!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
                break;
            case  R.id.stop_btn:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                break;
            case R.id.last_btn:
                playLastMusic();
                break;
            case R.id.next_btn:
                playNextMusic();
                break;
            default:
                break;
        }
    }

//    protected void onDestory(){
//        super.onDestroy();
//        if (mediaPlayer!=null){
//            mediaPlayer.stop();
//            mediaPlayer.release();
//        }
//    }
}