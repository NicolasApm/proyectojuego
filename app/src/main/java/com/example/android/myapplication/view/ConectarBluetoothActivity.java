package com.example.android.myapplication.view;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myapplication.R;
import com.example.android.myapplication.presenter.ConectarBluetoothPresenter;
import com.example.android.myapplication.presenter.ConectarBluetoothPresenterImpl;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConectarBluetoothActivity extends AppCompatActivity implements ConectarBluetoothView {
    //1)
    @BindView(R.id.IdBufferIn)
    TextView IdBufferIn;
    String data;
    //-------------------------------------------
    private ConectarBluetoothPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_interfaz);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        //Consigue la direccion MAC desde DeviceListActivity via EXTRA
        //
        String address = intent.getStringExtra(ListaDispositivosFragment.EXTRA_DEVICE_ADDRESS);
        presenter = new ConectarBluetoothPresenterImpl(this, address);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();


    }

    @OnClick(R.id.IdEncender)
    void encender() {
        presenter.encender();
    }

    @OnClick(R.id.IdApagar)
    void apagar() {
        presenter.apagar();
    }

    @OnClick(R.id.IdDesconectar)
    void desconectar() {
        presenter.desconectar();
    }

    @Override
    public void showData(String data) {
        //this.data=data;
        //IdBufferIn.setText("Dato: " + data);//<-<- PARTE A MODIFICAR >->->

        if (data.equals("LED ENCENDIDO")){

             IdBufferIn.setText("Dato: " + "inciaLed");
        }
        else if(data.equals("LED APAGADO")){

            IdBufferIn.setText("Dato: " + "OFFLed");
        }
    }



    @Override
    public void showMessage(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void RunOnMain(Runnable action){
        runOnUiThread(action);
    }
}