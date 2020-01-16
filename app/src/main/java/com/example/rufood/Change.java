package com.example.rufood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class Change extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        Thread threads= new Thread(){


            @Override
            public void run() {

                try{

                    sleep(3000);
                    startActivity( new Intent (getApplicationContext(), MainActivity.class));
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        threads.start();




    }
}
