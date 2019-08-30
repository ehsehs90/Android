package com.example.androidsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class IntentTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_test);


        Button implicitBtn = (Button) findViewById(R.id.implicitBtn);

        implicitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 새로운 Activity 를 호출!
                // Implicit Intent 를 이용해서 Activity를 호출
                Intent i = new Intent();
                //Action 과 Category를 지정해준다. Intent에게...
                i.setAction("MY_ACTION");         //Intent에 Action은 하나밖에 못ㅎㅐ 그래서 set

                i.addCategory("MY_CATEGORY");      //Intent에 Category는 여러개 가능해. 그래서 add 야
                i.putExtra("DATA", "소리없는 아우성!!");
                startActivity(i);
            }
        });

        Button dialBtn = (Button) findViewById(R.id.dialBtn);

        dialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 전화걸기 Activity를 호출@!@!@!@!
                //클래스 이름을 모르기떄문에 해당 액티비티를 호출하기 위해
                //어쩔수없이 묵시적 intent 를써야한다

                Intent i = new Intent();
                i.setAction(Intent.ACTION_DIAL);
                i.setData(Uri.parse("tel:01011112222"));
                startActivity(i);

            }
        });

        Button browserBtn = (Button) findViewById(R.id.browserBtn);

        browserBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Browser를 이용해서 특정 URL로 접속
                Uri uri = Uri.parse("http://www.naver.com");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }// 네트웍 연결이라 보안 해제 해야함-> 우린 해제 되어있죵~

        });

        Button mapBtn = (Button) findViewById(R.id.mapBtn);

        mapBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //지도를 표현할 activity를 실행
                Intent i = new Intent();
                ComponentName cname = new ComponentName("com.example.androidsample",
                        "com.example.androidsample.KAKAOMAPActivity");
                i.setComponent(cname);
                startActivity(i);
            }// 네트웍 연결이라 보안 해제 해야함-> 우린 해제 되어있죵~

        });


        Button callBtn = (Button) findViewById(R.id.callBtn);

        callBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //1. 사용자의 안드로이드 버전이 6버전 보다 작은가?
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) { //내가 사용하는 버전정보 숫자로 떨어짐.
                    // 내가 사용하는 버전이~보다 크니?
                    //추가적인 보안해제가 필요
                    int result = checkSelfPermission(Manifest.permission.CALL_PHONE); //퍼미션 열려있니?
                    if (result == PackageManager.PERMISSION_DENIED) { //만약..퍼미션디나이드면?(설정안되있다)?
                        // 전화걸기 기능에 대한 보안이 설정 안되어 있어요!


                        // 전화걸기 기능에 대한 보안이 설정 되어 있어요!
                        // 한번이라도 전화걸기에 대한 권한 설정을 거부한 적이 있는가?
                        if (shouldShowRequestPermissionRationale(Manifest.permission.CALL_PHONE)) {
                            //(Manifest.permission.CALL_PHONE라는 전화걸기 퍼미션에 대해 거부한 적이 있니?
                            // 거부한 적이 있어요! 다시 dialog을 띄워서 물어봐야 해요.
                            AlertDialog.Builder builder = new AlertDialog.Builder(IntentTestActivity.this);
                            builder.setTitle("권한 필요");
                            builder.setMessage("전화걸기 기능이 필요해요. 수락할까요? ");

                            builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
                                }
                            });
                            builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                            builder.show();


                        } else {
                            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1000);
                        }


                    } else {
                        //이전 안드로이드 버전이기 때문에 간단한 설정으로 바로 실행
                        //직접 calling하는 activity 호출
                        Intent i = new Intent();
                        i.setAction(Intent.ACTION_CALL);
                        i.setData(Uri.parse("tel:01098567764"));
                        startActivity(i);


                    }

                }// 네트웍 연결이라 보안 해제 해야함-> 우린 해제 되어있죵~
            }
        });
    }

        @Override
        public void onRequestPermissionsResult(int requestCode,@NonNull String[] permissions,
        @NonNull int[] grantResults){
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
            if (requestCode == 1000) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent i = new Intent();
                    i.setAction(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:01056113427"));
                    startActivity(i);
                }
            }
        }
    }
