package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Toast;

public class TouchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){         // 이벤트 객체
        Toast.makeText(this,R.string.toastMsg,Toast.LENGTH_SHORT).show(); // show를 호출해야 화면에 보임.
        return super.onTouchEvent(event);
    }
}