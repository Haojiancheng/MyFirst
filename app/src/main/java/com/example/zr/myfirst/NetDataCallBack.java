package com.example.zr.myfirst;

/**
 * Created by zr on 2017/12/20.
 */

public interface NetDataCallBack<T> {
    void success(T t);
    void field(int position,String str);

}