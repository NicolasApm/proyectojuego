package com.example.android.myapplication.presenter;

import android.content.Intent;

import com.example.android.myapplication.common.EBotones;
import com.example.android.myapplication.common.ENnum;
import com.example.android.myapplication.devices.BTCallback;
import com.example.android.myapplication.devices.BTUtil;
import com.example.android.myapplication.model.GameNumSequence;
import com.example.android.myapplication.model.GameSequence;
import com.example.android.myapplication.model.Niv6_7Secuence;
import com.example.android.myapplication.model.SecuenciaNiv5;
import com.example.android.myapplication.view.ConectarBluetoothView;
import com.example.android.myapplication.view.GameActivity;
import com.example.android.myapplication.view.GameSecNumActivity;
import com.example.android.myapplication.view.ListaDispositivosFragment;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConectarBluetoothPresenterImpl implements ConectarBluetoothPresenter, BTCallback {
    private ConectarBluetoothView view;
    private String address,data;
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
            view.showMessage("No hay dispotivo conectado");
        }
    }

    /**
     * encender
     */
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
    public void OnPause() {

        try { // Cuando se sale de la aplicación esta parte permite
            // que no se deje abierto el socket
            btUtil.close();
        } catch (Exception e2) {
            view.showMessage("error");
        }
    }


    @Override
    public void onNext(String data) {
        this.data=data;
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
