package com.example.androidsample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // btn1 : Event Source
        Button btn1 = (Button) findViewById(R.id.btn1);   // id를 가지고 view 객체를 (우리 눈에 보이는 컴포넌트들)을 찾아올거야.
        // id 라는 필드안에 뭉쳐있고, 스태틱 필드에서 btn1을 가져옴
        // 뭔 타입인지 모르고 가져오기 때문에 앞에 캐스팅 해줘야함!
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            // 클래스를 따로 안만들고 내부적으로 처리 ( 익명의(어나니모스) 클래스 )
            public void onClick(View view) {            // 외부에 명시적이냐 익명 내부클래스냐의 차이. 익명 내부가 일반적
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.LinearLayoutExampleActivity");
                i.setComponent(cname);  // 인텐트에 정보를 실어줌
                startActivity(i);       // 그 정보를 이용해 액티비티 스타트
            }
        });
        Button btn2 = (Button) findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.ChattingActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });


        Button btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.ImageActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button btn4 = (Button) findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.TouchActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });


        Button btn5 = (Button) findViewById(R.id.btn5);  // findViewById =  이미 존재하는 레퍼런스를 획득하는 방식
        btn5.setOnClickListener(new View.OnClickListener() { // 클릭에 대한 이벤트를 처리할 수 있는 리스너 객체가 나와야 해요
            // 얘는 인터페이스 이기 떄문에 객체를 못만들어요 @@ 추상메서드를 오버라이딩해 인터페이스 객체를 뽑아내요( 익명 내부 클래스를 이용한 이벤트 처리방식)

            @Override
            public void onClick(View view) {
                 final EditText et = new EditText(MainActivity.this);  // 새로 만드는 형식 :: Activity 객체를 넣어준다 (is a 관계에 의해 Context를 가질 수 있음)
                 // AlertDialog 를 생성해 보아요!
                 AlertDialog.Builder dialog = new AlertDialog.Builder( MainActivity.this);
                 dialog.setTitle("Activitiy에 데이터 전달");
                 dialog.setMessage("전달할 내용을 입력하세요");
                 dialog.setView(et);
                 dialog.setPositiveButton("Activity 호출", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         // 다른 Activity 를 호출하는 코드
                         Intent intent = new Intent();
                         ComponentName cname = new ComponentName("com.example.androidsample",
                                 "com.example.androidsample.SecondActivity");
                         intent.setComponent(cname);
                         intent.putExtra("sendMsg", et.getText().toString());  // 추가적인 데이타를 세팅해요.(Key-Value형태로 세팅가능)
                         intent.putExtra("anotherMsg","다른데이터");
                         // Edit Tet안에 있는 내용 과 sendMsg라는 키캆을 intent에 저장할거에요
                         startActivity(intent);
                     }
                 });
                 dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                     @Override
                     public void onClick(DialogInterface dialogInterface, int i) {
                         //취소 버튼을 눌렀을 때 하는일은 딱히 없어요@
                         //취소 버튼을 눌렀을 때 수행할 코드 작성
                     }
                 });

                 dialog.show();

            }
        });
        Button btn6 = (Button) findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.DataFromActivity");
                i.setComponent(cname);
                //startActivity(i);   // Activity 실행 1 ( 근데 내가 실행하는 액티비티는 데이타를 땡겨와야 하는 Activity.
                // startActivityForResult(i); 라는 조금 다른 startActivity 사용하자. requestcode 와 같이 써. 누가 나에게 결과값 request 하냐는 판단 Flag값으로 정수 값 사용.
                startActivityForResult(i,3000);

            }
        });
        Button btn7 = (Button) findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.ANRActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button btn8 = (Button) findViewById(R.id.btn8);
        btn8.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.NoCounterActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button btn9 = (Button) findViewById(R.id.btn9);
        btn9.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.CounterActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button btn10 = (Button) findViewById(R.id.btn10);
        btn10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.BookSearchActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button btn11 = (Button) findViewById(R.id.btn11);
        btn11.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.MovieSearchActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button btn12 = (Button) findViewById(R.id.btn12);
        btn12.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.CustomBookSearchActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });

        Button btn13 = (Button) findViewById(R.id.btn13);
        btn13.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //명시적 인텐트 - Explicit Intent
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.IntentTestActivity");
                i.setComponent(cname);
                startActivity(i);
            }
        });
    }







    @Override
    protected void onActivityResult(int requestCode,
                                      int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==3000 && resultCode == 5000 ){
            String result =data.getExtras().getString("DATA");
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();


        }

    }
}

