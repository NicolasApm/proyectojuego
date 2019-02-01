package com.example.android.myapplication.presenter;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.util.Log;

import com.example.android.myapplication.devices.BTUtil;
import com.example.android.myapplication.view.ListaDispositivosFragment;
import com.example.android.myapplication.view.ListaDispositivosView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ListaDispositivosPresenterImpl implements ListaDispositivosPresenter {
    private final String TAG = this.getClass().getName();
    private ListaDispositivosView view;
    private BTUtil btUtil;

    public ListaDispositivosPresenterImpl(ListaDispositivosView view) {
        this.view = view;
        this.btUtil = new BTUtil();
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onResume() {
        try {
            boolean btActive = btUtil.VerificarEstadoBT();
            if (btActive) {
                Log.d(TAG, "...Bluetooth Activado...");
            } else {
                view.askEnableBT();
            }

            Set<BluetoothDevice> pairedDevices =  btUtil.getDevices();

            List<String> data = new ArrayList<>();
            // Adiciona un dispositivos previo emparejado al array
            if (pairedDevices.size() > 0)
            {
                // data = pairedDevices.stream().map(device -> device.getName() + "\n" + device.getAddress()).collect(Collectors.toList());
                for (BluetoothDevice device : pairedDevices) { //EN CASO DE ERROR LEER LA ANTERIOR EXPLICACION
                    data.add(device.getName() + "\n" + device.getAddress());
                }
            }
            view.setData(data);
        } catch (Exception e) {
            view.showToast(e.getMessage());
        }
    }
}
