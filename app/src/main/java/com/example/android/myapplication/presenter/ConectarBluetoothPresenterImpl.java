package com.example.android.myapplication.presenter;

import android.bluetooth.BluetoothDevice;

import com.example.android.myapplication.devices.BTCallback;
import com.example.android.myapplication.devices.BTUtil;
import com.example.android.myapplication.view.ConectarBluetoothActivity;
import com.example.android.myapplication.view.ConectarBluetoothView;

import java.io.IOException;

public class ConectarBluetoothPresenterImpl implements ConectarBluetoothPresenter, BTCallback {
    private ConectarBluetoothView view;
    private String address;
    private BTUtil btUtil;

    public ConectarBluetoothPresenterImpl(ConectarBluetoothView view, String address) {
        this.view = view;
        this.address = address;
        this.btUtil = new BTUtil();
    }

    @Override
    public void onResume() {
        try {
            boolean btStatus = btUtil.VerificarEstadoBT();
            if(!btStatus)
                throw new Exception("BT desactivado");
            btUtil.connect(address, this);
        } catch (Exception e) {
            view.showMessage(e.getMessage());
        }
    }

    @Override
    public void encender() {
        btUtil.write("1");
    }

    @Override
    public void apagar() {
        btUtil.write("0");
    }

    @Override
    public void desconectar() {
        btUtil.close();
        view.finish();
    }

    @Override
    public void onNext(String data) {
        view.showData(data);
    }

    @Override
    public void onError(Exception e) {
        view.RunOnMain(() -> {
            view.showMessage(e.getMessage());
            view.finish();
        });

    }
}
