package com.example.androidsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



    class MyCounter1 implements Runnable {
//        private TextView tv;
//
//        public MyCounter1(TextView tv)   {
//            this.tv = tv;
//        }
        private  Handler handler;

        MyCounter1( Handler handler) {
            this.handler = handler;

        }
        @Override
        public void run() {
            for(int i=0; i<10; i++) {
                try {
                    Thread.sleep(1000);
                    //UI Thread 에 handler를 이용해서 메세지를 전달
                    Bundle bundle = new Bundle();
                    bundle.putString("COUNT", i +"");
                    Message msg = new Message();
                    msg.setData(bundle);
                    handler.sendMessage(msg);
                    //handler를 이용해서 메세지를 전달
                    //  야 하는데 왜 돼죠?

                } catch(Exception e) {
                    Log.i("RunTryException", e.getMessage());
                }

            }
        }
    }
    public class CounterActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_counter);

            final TextView tv1 = findViewById(R.id.counterTv1);
            Button startBtn1 = findViewById(R.id.startBtn1);

            final Handler handler = new Handler() {
                // message를 받는 순간 아래 method가 호출되요!

                @Override
                public void handleMessage(@NonNull Message msg) {
                    super.handleMessage(msg);
                    Bundle b = msg.getData();
                    tv1.setText(b.getString("COUNT"));
                }
            };
            startBtn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MyCounter1 myCounter1 = new MyCounter1(handler);
                    //Thread를 하나 생성하여 1초마다 TextView에 카운트를 출력
                    Thread t = new Thread(myCounter1);
                    t.start();
                }
            });
        }

    }
