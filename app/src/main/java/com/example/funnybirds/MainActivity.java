package com.example.funnybirds;


import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.KeyStore;

import static com.example.funnybirds.gameView.points;
import static com.example.funnybirds.managment.restart;


public class MainActivity extends AppCompatActivity {


    public static boolean PauseFlag = true;
    public static int dif = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new gameView(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        if(item.getItemId() == R.id.action_pause){
            if(dif%2 == 0){
                PauseFlag = false;
                Toast toast = Toast.makeText(getApplicationContext(),"Pause",Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
            else {PauseFlag = true;}
            dif += 1;
        }


        if(item.getItemId() == R.id.action_restart){
            restart();
        }

        if(item.getItemId() == R.id.action_exit){
            System.exit(0);
        }

        return super.onOptionsItemSelected(item);
    }
}