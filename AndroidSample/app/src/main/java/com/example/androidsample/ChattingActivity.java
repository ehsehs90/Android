package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChattingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        final TextView tv = (TextView)findViewById(R.id.tv);
        final TextView uId = (TextView)findViewById(R.id.userId);
        final EditText et = (EditText)findViewById(R.id.et);
        Button sendBtn = (Button)findViewById(R.id.sendBtn);    // 해당클래스의 멤버로 올려서 클래스가 끝나도 해당 필드가 남아있을 것임.
        // 하지만 힙영역에서 메모리를 많이 차지하게됨. 그래서
        // 파이날 키워드를 이용해서 상수로 만들어버림.
        tv.setMovementMethod(ScrollingMovementMethod.getInstance());

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.append(uId.getText() + ">>" + et.getText() + "\n");
                // 스크롤을 해야하는지 판단해서
                int lineTop =  tv.getLayout().getLineTop(tv.getLineCount());
                int scrollY = lineTop - tv.getHeight();
                if (scrollY > 0) {
                    tv.scrollTo(0, scrollY);
                } else {
                    tv.scrollTo(0,100);   // 현재 텍스트의 높이, 사용한 크기가 얼마냐 등등을 판단

                }
            }
        });
    }
}