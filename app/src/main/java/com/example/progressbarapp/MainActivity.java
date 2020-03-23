package com.example.progressbarapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button b;
    ProgressBar pb;
    Thread pro;
    Handler handler;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b = findViewById(R.id.b1);
        pb = findViewById(R.id.progressBar);
        pb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "progress = "+pb.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });
        b.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        handler = new Handler(this.getMainLooper());
        pro = new Progress(handler, pb);
        pro.start();
    }
}
class Progress extends Thread{
    Handler handler; ProgressBar pb;
    static int i = 1;
    public Progress(Handler handler, ProgressBar pb){
        this.handler = handler;
        this.pb = pb;
    }
    @Override
    public void run() {
        while(i <= 100){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    pb.setProgress(i);
                }
            });
            i++;
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

