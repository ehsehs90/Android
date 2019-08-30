package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;

public class DataFromActivity extends AppCompatActivity {

    private String selectedItem="";  //멤버로 올리자

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_from_activity);

        final ArrayList<String> list = new ArrayList<String>();
        list.add("수박");
        list.add("바나나");
        list.add("딸기");
        list.add("멜론");

        Spinner spinner = (Spinner)findViewById(R.id.spinnerer);

        //adapter가 필요 : 붙이기 가능@ :: 사용하는 자료구조가 arraylist니까 arrayadapter 사용하자.. 인자 3개 : view/spinner type?/ 데이터는 list
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item, list);


        spinner.setAdapter(adapter);
        //spinner에서 item을 선택하는
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override //인자 3개 int i = 인덱스 값. (몇번째 인자인가)
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = (String)list.get(i);
                Log.i("selectedText", "선택된 파일 : "+selectedItem);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Button sendDataBtn = (Button)findViewById(R.id.sendMgBtn);
        sendDataBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //클릭 되면 현재 선택한 과일 이름을 이전 Activity로 전달.
                //현재 Activity는 종료
                Intent resultIntent  = new Intent();
                resultIntent.putExtra("DATA",selectedItem);
                setResult(5000, resultIntent); //결과코드값은 5000, 내가 결과값으로 돌려줄 건 resultIntent야.
                //(지금 사용하고 있는 액티비티가 종료되면 자동으로 전해줌)
                DataFromActivity.this.finish();
                // 현재 액티비티 종료하라는 finish. 앞에는 액티비티 객체가 나와줘야함. 현재 사용하는 액티비티 객체를 class명을 통해 쓴다



            }
        });
    }
}
