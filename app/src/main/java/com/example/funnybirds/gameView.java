package com.example.funnybirds;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;




public class gameView extends View{




    private Sprite playerBird;
    private Sprite enemyBird;
    private Sprite coronavirus;


    public static boolean Level2 = false;
    public static boolean finish = false;
    public static int viewWidth;
    public static int viewHeight;

    public static int points = 10;
    public static int points2 = 0;
    public int points3 = 0;


    private final int timerInterval = 30;


    public boolean Finish(){
        if(points>100){
            Level2 = true;
        }
        if (Level2 && points<100){
            finish = true;
        }
        else if (points<0){
            finish = true;
        }
        return finish;
    }


    public gameView(Context context) {
        super(context);

        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.player);
        int w = b.getWidth()/5;
        int h = b.getHeight()/3;
        Rect firstFrame = new Rect(0, 0, w, h);
        playerBird = new Sprite(10, 0, 0, 100, firstFrame, b);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (i ==0 && j == 0) {
                    continue;
                }
                if (i ==2 && j == 3) {
                    continue;
                }
                playerBird.addFrame(new Rect(j*w, i*h, j*w+w, i*w+w));
            }
        }

        b = BitmapFactory.decodeResource(getResources(), R.drawable.coronavirus);
        w = b.getWidth()/3;
        h = b.getHeight();
        firstFrame = new Rect(2*w, 0, 3*w, 3*h);

        coronavirus = new Sprite(2000, 250, -300, 10, firstFrame, b);

        /*for (int i = 0; i < 3; i++) {
            for (int j = 4; j >= 0; j--) {

                if (i ==0 && j == 4) {
                    continue;
                }

                if (i ==2 && j == 0) {
                    continue;
                }

                coronavirus.addFrame(new Rect(j*w, i*h, j*w+w, i*w+w));
            }
        }
*/


        b = BitmapFactory.decodeResource(getResources(), R.drawable.enemy);
        w = b.getWidth()/5;
        h = b.getHeight()/3;
        firstFrame = new Rect(4*w, 0, 5*w, h);



        enemyBird = new Sprite(2000, 150, -300, 0, firstFrame, b);

        for (int i = 0; i < 3; i++) {
            for (int j = 4; j >= 0; j--) {

              if (i ==0 && j == 4) {
                    continue;
                }

                if (i ==2 && j == 0) {
                    continue;
                }

                enemyBird.addFrame(new Rect(j*w, i*h, j*w+w, i*w+w));
            }
        }




        Timer t = new Timer();
        t.start();

    }




    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        viewWidth = w;
        viewHeight = h;


    }











    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawARGB(250, 127, 199, 255);
        playerBird.draw(canvas);
        enemyBird.draw(canvas);
        coronavirus.draw(canvas);

        Paint y = new Paint();
        y.setAntiAlias(true);
        y.setTextSize(200.0f);
        y.setColor(Color.WHITE);
        y.setStyle(Paint.Style.FILL_AND_STROKE);


        if (points >= 200) {
            canvas.drawText("You win ", viewWidth/6, viewHeight/2, y);
        }
        if(points<0||Finish()){
            canvas.drawText("You lose ",viewWidth/6 , viewHeight/2, y);
        }

        int ScoreColor = getResources().getColor(R.color.ScoreСolor);

        Paint g = new Paint();
        g.setAntiAlias(true);
        g.setColor(ScoreColor);
        canvas.drawCircle(viewWidth - 140, 117, 75, g);

        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setTextSize(70.0f);
        p.setSubpixelText(true);
        p.setColor(Color.WHITE);
        p.setStyle(Paint.Style.FILL);



        if(points <= 100){
            points3 = points;}
        else {
            points3 = points-100;
        }

        if(points<=100){
            canvas.drawText("Level 1", viewWidth-1000, 130,p);
        }
        else {
            canvas.drawText("Level 2", viewWidth-1000, 130,p);
        }


        if(Finish()){
            canvas.drawText("0", viewWidth - 165, 140, p);
        }
        else if(points3 > 9 && points3 < 100){
            canvas.drawText(points3 + "", viewWidth - 180, 140, p);}
        else if(points3 < 10 && points3 >= 0 ){
            canvas.drawText(points3 + "", viewWidth - 165, 140, p);}
        else if(points3 > 100 ){
            canvas.drawText(points3 + "", viewWidth - 200, 140, p);
        }
        if(Finish()){
            canvas.drawText("0", viewWidth - 165, 140, p);
        }



    }




    public void update () {
        if(MainActivity.PauseFlag &&(points >= 0 && points < 200)&& !Finish()){

            playerBird.update(timerInterval);
            enemyBird.update(timerInterval);
            coronavirus.update(timerInterval);

            if (playerBird.getY() + playerBird.getFrameHeight() > viewHeight) {
                playerBird.setY(viewHeight - playerBird.getFrameHeight());
                playerBird.setVy(-playerBird.getVy());
                points--;
            } else if (playerBird.getY() < 0) {
                playerBird.setY(0);
                playerBird.setVy(-playerBird.getVy());
                points--;
            }

            if (enemyBird.getX() < -enemyBird.getFrameWidth()) {
                teleportEnemy();
                points += 10;
            }

            if (enemyBird.intersect(playerBird)) {
                teleportEnemy();
                points -= 20;
            }

           if (coronavirus.getX() < -coronavirus.getFrameWidth()) {
                teleportCoronavirus();
                points -= 10;
            }

            if (coronavirus.intersect(playerBird)) {
                teleportCoronavirus();
                points += 40;
            }


            invalidate();
        }
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int eventAction = event.getAction();
        if (eventAction == MotionEvent.ACTION_DOWN)  {

            if (event.getY() < playerBird.getBoundingBoxRect().top) {
                playerBird.setVy(-100);
                points--;
            }
            else if (event.getY() > (playerBird.getBoundingBoxRect().bottom)) {
                playerBird.setVy(100);
                points--;
            }
        }

        return true;
    }



    private void teleportEnemy () {
        enemyBird.setX(viewWidth + Math.random() * 500);
        enemyBird.setY(Math.random() * (viewHeight - enemyBird.getFrameHeight()));
    }



    class Timer extends CountDownTimer {

        public Timer() {
            super(Integer.MAX_VALUE, timerInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

            update ();
        }

        @Override
        public void onFinish() {

        }
    }

    private void teleportCoronavirus () {
        coronavirus.setX(viewWidth + Math.random() * 500);
        coronavirus.setY(Math.random() * (viewHeight - coronavirus.getFrameHeight()));
    }


    }




