package com.example.zr.myfirst;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zr on 2017/12/20.
 */

public class MyBaseAdapter extends RecyclerView.Adapter<MyBaseAdapter.ViewHolder> implements View.OnClickListener {
    //数据集合
    private List<Data.DataBean.ReturnDataBean.ComicsBean> list = new ArrayList<>();
    private Context context;
    private OnItemClickListener mOnItemClickListener = null;

    public MyBaseAdapter(List<Data.DataBean.ReturnDataBean.ComicsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    AdapterView.OnItemClickListener listener;


    public void setListener(AdapterView.OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public MyBaseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.myitme, parent, false);


        view.setOnClickListener((View.OnClickListener) this);

        return new ViewHolder(view);
    }



//adb connect 127.0.0.1:62001
    @Override
    public void onBindViewHolder(final MyBaseAdapter.ViewHolder holder,  int position) {
        //                    listener.onItemClick(v, position);

        //将position保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
        holder.tv1.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getCover()).into(holder.iv1);

    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(v,(int)v.getTag());
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv1;
        TextView tv1;

        public ViewHolder(View itemView) {
            super(itemView);
            iv1 = itemView.findViewById(R.id.iv1);
            tv1 = itemView.findViewById(R.id.tx1);
            itemView=this.itemView;

        }
    }

    public static interface OnItemClickListener {
        void onItemClick(View var2, int var3);
    }
}
