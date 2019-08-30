package com.example.androidsample;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

//ListView 안의 내용을 그리는 역할을 하는 adapter
//어댑터는 이케 만들어야해~ 상속 받고 시작하렴~
public class CustomListViewAdapter extends BaseAdapter {

    private List<BookVO> list = new ArrayList<BookVO>() ;
    //반드시 overriding을 해야하는 method가 존재
    public void addItem(BookVO vo){
        list.add(vo);
    }


    @Override
    public int getCount() {
        // 총 몇개의 component를 그려야 하는지를 return - listview가 총 몇개가 그려지는냐~
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        //그려올 Data뽑아올 때 = 몇번째 데이터를 화면에 출력 할 지를 결정.
        //만약 5개잇으면 5번그려야대. 처음엔 int i =0이 호출 돼.  0은 list안의 1번째꺼를 뽑아와야해. --이걸 반복 호출/ 카운트 갯수만큼

        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
                        //i = 몇번째 있는걸 그릴거니
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();

        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            ///생성된 View 객체에 XM layout을 설정
            view = inflater.inflate(R.layout.listview_item, viewGroup, false);

        }
        //출력할 View Component의 reference 를 획득해 세팅
        ImageView iv = (ImageView)view.findViewById(R.id.customIv);
        TextView tv1 = (TextView) view.findViewById(R.id.customTv1);
        TextView tv2 = (TextView) view.findViewById(R.id.customTv2);

        BookVO vo = list.get(i);  //화면에 출력할 데이터를 가져와요
        //각각의 컴포넌트에 데이터 세팅하쟈

        iv.setImageDrawable(vo.getDrawable());

        tv1.setText(vo.getBtitle());
        tv2.setText(vo.getBauthor());


        //화면에 찍힐 view리턴해주면 팡팡팡팡팡
        return view; //하면 view에 뽜앙~하고 나타남
    }
}
