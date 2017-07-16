package com.example.bhupendrajain.touch;

import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final HashMap<Integer,View> map= new HashMap<>();
       final RelativeLayout rl= (RelativeLayout)findViewById(R.id.rel);
        rl.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if(event.getActionMasked()==MotionEvent.ACTION_DOWN){
                    int newPointerIndex = event.getActionIndex();
                    int newPointerId = event.getPointerId(newPointerIndex);
                    View newView = new View(MainActivity.this);
                    newView.setBackgroundColor(Color.BLACK);
                    float x = event.getX();
                    float y = event.getY();
                    newView.setLayoutParams(new LinearLayout.LayoutParams(200,200));
                    newView.setX(x-100);
                    newView.setY(y-100);
                    map.put(newPointerId,newView);
                    rl.addView(newView);
                }
                else if(event.getActionMasked()==MotionEvent.ACTION_POINTER_DOWN){
                    int newPointerIndex = event.getActionIndex();
                    int newPointerId = event.getPointerId(newPointerIndex);
                    View newView = new View(MainActivity.this);
                    newView.setBackgroundColor(Color.BLACK);
                    float x = event.getX(newPointerIndex);
                    float y = event.getY(newPointerIndex);
                    newView.setLayoutParams(new LinearLayout.LayoutParams(200,200));
                    newView.setX(x-100);
                    newView.setY(y-100);
                    map.put(newPointerId,newView);
                    rl.addView(newView);
                }else if(event.getActionMasked()==MotionEvent.ACTION_UP){
                    int newPointerIndex = event.getActionIndex();
                    int newPointerId = event.getPointerId(newPointerIndex);
                    View viewToBeRemoved = map.get(newPointerId);
                    rl.removeView(viewToBeRemoved);
                }
                else if(event.getActionMasked()==MotionEvent.ACTION_POINTER_UP){
                    int newPointerIndex = event.getActionIndex();
                    int newPointerId = event.getPointerId(newPointerIndex);
                    View viewToBeRemoved = map.get(newPointerId);
                    rl.removeView(viewToBeRemoved);
                }else if(event.getActionMasked()==MotionEvent.ACTION_MOVE){
                    int count = event.getPointerCount();

                    for (int i =0;i<count;i++){
                        int id=event.getPointerId(i);
                        float x = event.getX(i);
                        float y = event.getY(i);
                        View nv=map.get(id);
                        nv.setX(x-100);
                        nv.setY(y-100);

                    }


                }

                return true;
            }
        });
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
}
