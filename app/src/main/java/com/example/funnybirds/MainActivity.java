package com.example.funnybirds;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.funnybirds.gameView.points;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new gameView(this));
        if(points>=100 || points <= -100){
            setContentView(R.layout.activity_main);
        }
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
            Toast.makeText(this, "Pause", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }
}