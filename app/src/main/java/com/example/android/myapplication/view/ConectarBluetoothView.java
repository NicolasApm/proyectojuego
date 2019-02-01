package com.example.android.myapplication.view;


import android.bluetooth.BluetoothSocket;

public interface ConectarBluetoothView {

    void showData(String data);

    void showMessage(String message);

    void RunOnMain(Runnable action);

    void finish();
}
