package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


class MySum implements Runnable {
    // tv 넘겨받기 위해 생성자 하나 만들어@
    private TextView tv;
    // 한 객체에 다른 객체 넣어줘서 해당 객체를 이용할 수 있도록 하는것 : injection(인젝선) . 생성자에서 injection했기 때문에
    //Constructor injection -- 라고 부름@ 프로그램 유연하게 끌고갈 수 있음.

    MySum(TextView tv){
        this.tv = tv;
    }

    @Override
    public void run() {
        // Thread가 실행이 되면 수행되는 코드를 여기에 작성
        long sum =0;
        for (long i =0;i<100000000L ;i++){
            sum+= i;
        }
        tv.setText("총 합은 : " + sum);

    }
}

public class ANRActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anr);

        final TextView tv = (TextView)findViewById(R.id.countTv);
        Button countBtn = (Button)findViewById(R.id.countBtn);
        Button toastBtn = (Button)findViewById(R.id.toastBtn);


        countBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //Thread를 파생시켜야 해요!

                MySum mySum = new MySum(tv);  //Runnable interface를 구현한 객체

                Thread t = new Thread(mySum); //Thread 생성하는 코드

                t.start(); //Start 하고 시간 지나면 run() 이란 메서드 실행됨
                           //새로운 실행흐름을 만들어 낼 수 있어요. // non-bloacking method이기 때문에

            }
        });
        toastBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ANRActivity.this, "Toast가 실행되요", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
