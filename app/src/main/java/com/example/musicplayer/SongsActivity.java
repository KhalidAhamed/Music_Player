package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SongsActivity extends AppCompatActivity {
TextView title;
ImageView previous,next,pause;
ArrayList<File> songs;
MediaPlayer mp;
String textContent;
int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        previous = findViewById(R.id.previous);
        pause = findViewById(R.id.pause);
        next = findViewById(R.id.next);
        title = findViewById(R.id.title);


        Intent iSongs = getIntent();
        Bundle bundle = iSongs.getExtras();
        songs = (ArrayList) bundle.getParcelableArrayList("songList");
        textContent = iSongs.getStringExtra("currentSong");
        title.setText(textContent);
        position = iSongs.getIntExtra("position",0);
        Uri uri = Uri.parse(songs.get(position).toString());
        mp = MediaPlayer.create(this,uri);

        mp.start();

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()){
                    pause.setImageResource(R.drawable.play);
                    mp.pause();
                }
                else{
                    pause.setImageResource(R.drawable.pause);
                    mp.start();
                }
            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                mp.release();
                if(position!=0){
                    position = position - 1;
                }
                else{
                    position = songs.size()-1;
                }
                details();

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                mp.release();
                if(position!=songs.size()-1){
                    position = position +1;
                }else{
                   position = 0;
                }
                details();
            }
        });











    }

    public void details(){
        Uri uri = Uri.parse(songs.get(position).toString());
        mp = MediaPlayer.create(getApplicationContext(),uri);
        mp.start();
        textContent = songs.get(position).getName().toString();
        pause.setImageResource(R.drawable.pause);
        title.setText(textContent);
    }


}