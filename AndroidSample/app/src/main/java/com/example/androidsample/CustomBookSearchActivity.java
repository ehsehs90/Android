package com.example.androidsample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


class BookSearchRunnable implements Runnable{
    private Handler handler;
    private String keyword;

    BookSearchRunnable(Handler handler, String keyword){
        this.handler=handler;
        this.keyword=keyword;

    }
    @Override
    public void run() {
        //메인스레드가 화면 제어
        String url = "http://70.12.115.58:8080/bookSearch/search?search_keyword="+ keyword;
        //해당 주소의 네트워크 접속 (url객체 만들고~ 커넥션 해서~)
        try {
            URL urlObj = new URL(url);
            HttpURLConnection con = (HttpURLConnection)urlObj.openConnection();
            con.setRequestMethod("GET"); //호출방식 결정 -GET은 default이기 떄문에 안써줘도 됨 코드 한줄 날려
            BufferedReader br =
                    new BufferedReader(new InputStreamReader(con.getInputStream()));




            //기본입력con.getInputStream() -> 조금더 좋은 입력형태nputStreamReader -> 그것보다 더 좋은 입력형태BufferedReader
            //BufferedReader 는 라인단위로 데이터를 끊어올 수 있다.
            String input = "";
            StringBuffer sb = new StringBuffer();
            while((input = br.readLine()) !=null){
                sb.append(input);
            }
            //서버에 대한 스트림은 종료
            br.close();
            //결과적으로 우리가 얻어낸건... json형태의 문자열
            //json 문자열을 원래 객체 형태로 복원
            ObjectMapper mapper = new ObjectMapper();

            //"[aaa,bbb,ccc]"
            BookVO[] resultArr = mapper.readValue(sb.toString(),BookVO[].class);
                                                  //이걸        //이케 바꿀거에요
//            for(BookVO vo : resultArr){
//                Log.i("customListView",vo.getBtitle());
//            }
            //최종적으로 얻어낸 객체를 UI Thread(Main Thread, Activity Thread)
            //에게 전달해야 해요!
            for(BookVO vo  : resultArr){
                vo.drawableFromURL();
            }  //데이터 셋팅된 BooKVO 만들어집니다.

            Bundle bundle =new Bundle();
            bundle.putSerializable("BOOKS",resultArr);
            Message msg = new Message();
            msg.setData(bundle);
            handler.sendMessage(msg);

            Log.i("customListView",sb.toString()); // 서버쪽 데이터 스트림으로 읽어서 로그에 찍어요.
        }catch(Exception e) {
            Log.i("customListView",e.toString());


        }

    }
}
public class CustomBookSearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_book_search);


        //필요한 reference얻어오기

        //Button, EditText, ListView
        Button customBtn = (Button) findViewById(R.id.customBtn);
        final EditText customEt = (EditText) findViewById(R.id.customEt); //얘도 마찬가지
        final ListView customLv = (ListView) findViewById(R.id.customLv);

        //이벤트 핸들링 할 핸들러랑
        //에디트 택스트 이거 두개 인자로 넘겨
        //final 상수화 - 이유? 해당 핸들러가 지역변수가 되지 않고 이벤트 처리를 할 수 있게 됨.
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                //외부 Thread에서 message를 받으면 호출
                Bundle bundle = msg.getData();
                BookVO[] list = (BookVO[]) bundle.getSerializable("BOOKS");

                //UI Thread에게 데이터를 전달하기 전에
                //화면에 표현할 데이터가 완전히 준비해야 해요


                // Listview에 adapeter를 적용해서 ListView를 그리는 작업
                // ListView : 화면에 리스트형태를 보여주는 widget
               // ListView에 데이터를 가져다가 실제 그려주는 작업 => adapter

//                **예전에는 이거 썻었는데 **  //안드로이드에서 주는 기본 어댑터
//                String[] result = bundle.getStringArray("BOOKARRAY"); //데이터 주워와서
//
//                //adapter라는 객체는 데이터를 가져다가 view를 그리는 역할을 담당
//
//                ArrayAdapter adapter = new ArrayAdapter(BookSearchActivity.this, //안드로이드가 제공해주는 어댑터개체 갖다씀
//                        android.R.layout.simple_list_item_1,result);  //어떤 형식으로 화면에 뿌릴건지를 알려줌.(이 어댑터가 하는 일)
//                listView.setAdapter(adapter);
 //                *****************************
                //이제는 어댑터를 확장시켜서 그리는 역할을 만드는 어댑터를 만들어 줘야한다.
                //뭘 해야 하냐면 아까 엑셀에 만든 모형으로 뿌려줄 수 있는 어댑터를 만들어야 한다.
//                ArrayAdapter adapter = new ArrayAdapter(BookSearchActivity.this, android.R.layout.simple_list_item_1,result);

                CustomListViewAdapter adapter = new CustomListViewAdapter(); //그리는 역할하는 객체 만들어

                customLv.setAdapter(adapter); ///출력해야하는 listview에 셋팅

                for(BookVO vo : list){
                    adapter.addItem(vo);         //배열안에있는 각각의 데이터 뽑아서 어댑터한테 주면 어댑터는
                                                  //각각의 데이터 뽑아가지고 그리는작업을 수행한다.
                }


            }
        };

        //스레드가 유아이 레드한테 데이터를 줄 때 자동적으로 콜백시키기 위해
        //핸들러는 이
        customBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 검색 버튼이 클릭되면
                // 사용자가 입력한 keyword를 이용해서 network 접속을 시도
                // 외부 servlet web program 을 호출해서 결과를 받아와야 해요
                //Thread 를 이용해서 network연결 처리를 해야해요!
                //Thread의 입력 keyword값과 handler 개체가 인자로 넘어가야해요.
                //그래야지만


                BookSearchRunnable runnable =
                        new BookSearchRunnable(handler, customEt.getText().toString());
                Thread t = new Thread(runnable);  //키워드가 해당 스레드한테 데이터 넘어가요.
                t.start();


            }
        });
    }
}
