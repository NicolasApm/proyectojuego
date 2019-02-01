package com.example.android.myapplication.devices;

public interface BTCallback {
    void onNext(String data);
    void onError(Exception e);
}
