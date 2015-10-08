package com.example.doomsquad.ghostdetectomatic;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private ImageView radar;
    private Random detect;
    private Boolean detecting;
    private Canvas radarScreen;
    private int radW;
    private int radH;
    private static Bitmap bt;
    private static SensorManager smg;
    private static Sensor sense;
    //private static final Bitmap.Config.ARGB_8888 argb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initializing
        detecting = true;
        radH = 270;
        radW = 300;
        bt.createBitmap(radW, radH, Bitmap.Config.ARGB_8888);
        radarScreen = new Canvas(bt);
        smg = (SensorManager)this.getSystemService(SENSOR_SERVICE);
        sense = smg.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        //smg.registerListener(this, sense, SensorManager.SENSOR_DELAY_NORMAL);

        //animate radar
        radar = (ImageView)findViewById(R.id.radar);
        RotateAnimation rotate = new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setDuration(2000);
        radar.startAnimation(rotate);


    }

    public void takePhoto()
    {
        detecting = true;
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivity(intent);
    }
    public void Radar(Canvas c)
    {
        detect = new Random();
        int search = detect.nextInt(100);
        Paint color = new Paint();
        color.setARGB(70, 0, 255, 0);
        if(search >= 70){
            int circX = detect.nextInt(radW);
            int circY = detect.nextInt(radH);
            c.drawCircle(circX, circY, 10, color);
            detecting = false;
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //@Override
}
