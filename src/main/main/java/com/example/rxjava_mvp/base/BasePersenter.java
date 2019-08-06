package com.example.rxjava_mvp.base;

import android.os.Bundle;

public abstract class BasePersenter<V extends BaseView> {
    public V view;
    public void attach(V view){
        this.view=view;
    }
    public void detattch(){
        if (view!=null){
            view=null;
        }
    }
    public abstract void onCreate();
    public abstract void onSaveIntanceState(Bundle outState);
}
