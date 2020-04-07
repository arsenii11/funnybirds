package com.example.funnybirds;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import java.util.Scanner;



public class result {
    public result(Context context) {

    }



    }
    class win extends View {
        public win (Context context) {
            super(context);
        }

        @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Paint y = new Paint();
        y.setAntiAlias(true);
        y.setTextSize(200.0f);
        y.setColor(Color.WHITE);
        y.setStyle(Paint.Style.FILL_AND_STROKE);

            if (gameView.points >= 100) {
                canvas.drawText("You win ", gameView.viewWidth-1000, 1000, y);
            }
            else if(gameView.points <= -100){
                canvas.drawText("You lose ", gameView.viewWidth-1000, 1000, y);
            }
     }
    }
