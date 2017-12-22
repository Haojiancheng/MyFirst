package com.example.zr.myfirst;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements NetDataCallBack<Data>{
    private List<Data.DataBean.ReturnDataBean.ComicsBean> list=new ArrayList<>();
    private RecyclerView mrview;
    private MyBaseAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mrview=findViewById(R.id.mrview);
        LinearLayoutManager manager1=new LinearLayoutManager(this);
        mrview.setLayoutManager(manager1);
        mrview.addItemDecoration(new MyLine(this,R.drawable.itemline));
        setmaindata();
    }



    @Override
    protected void onRestart() {
        Toast.makeText(this, "My name is haojiancheng", Toast.LENGTH_SHORT).show();
        super.onRestart();

    }
    public void setmaindata(){
        String url="http://app.u17.com/v3/appV3_3/android/phone/list/commonComicList?argValue=23&argName=sort&argCon=0&page=1&android_id=4058040115108878&v=3330110&model=GT-P5210&come_from=Tg002";
        OKutils oKutils=new OKutils();
        oKutils.getdata(url,this,Data.class);
    }

    @Override
    public void success(final Data data) {
        list.addAll(data.getData().getReturnData().getComics());
        adapter=new MyBaseAdapter(list,this);
        adapter.setOnItemClickListener(new MyBaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View var2, int var3) {
                int itemnumer=var3+1;
                Toast.makeText(MainActivity.this, ""+itemnumer, Toast.LENGTH_SHORT).show();
            }
        });
        mrview.setAdapter(adapter);

    }

    @Override
    public void field(int position, String str) {


    }

}
