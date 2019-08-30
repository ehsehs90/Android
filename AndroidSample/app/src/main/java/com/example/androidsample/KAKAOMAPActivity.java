package com.example.androidsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;

import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapView;

public class KAKAOMAPActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kakaomapactivity);


        MapView map = new MapView(this);
        ViewGroup group  = (ViewGroup)findViewById(R.id.mapll);

        //linearlayout으로 들고와서 viewgroup으로 상위 point로 잡아줌
        MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(37.50186, 127.039632); //mappoint잡아서
        map.setMapCenterPoint(mapPoint, true);
        group.addView(map);

    }
}
