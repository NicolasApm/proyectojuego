package com.example.android.myapplication.view;

import android.content.Intent;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myapplication.R;
import com.example.android.myapplication.common.EBotones;
import com.example.android.myapplication.common.ENnum;
import com.example.android.myapplication.devices.BTUtil;
import com.example.android.myapplication.model.GameNumSequence;
import com.example.android.myapplication.model.GameSequence;
import com.example.android.myapplication.model.Niv6_7Secuence;
import com.example.android.myapplication.model.SecuenciaNiv5;
import com.example.android.myapplication.presenter.ConectarBluetoothPresenter;
import com.example.android.myapplication.presenter.ConectarBluetoothPresenterImpl;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConectarBluetoothActivity extends AppCompatActivity implements ConectarBluetoothView {
    //1)

    //-------------------------------------------
    private ConectarBluetoothPresenter presenter;
    private List<EBotones> sequence = new ArrayList<>();
    private SecuenciaNiv5 secuenciaNiv5 = new SecuenciaNiv5(sequence);
    private List<ENnum> sequence2 = new ArrayList<>();
    private Niv6_7Secuence secuenciaNiv67 = new Niv6_7Secuence(sequence2);
    private String findAgeUser,data,CompareDataRvr;



    @BindView(R.id.IdBufferIn)
    TextView IdBufferIn;

    Intent i, j;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user_interfaz);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        i = new Intent(this, GameActivity.class);
        j = new Intent(this, GameSecNumActivity.class);
        //Consigue la direccion MAC desde DeviceListActivity via EXTRA
        //

        String address = intent.getStringExtra(ListaDispositivosFragment.EXTRA_DEVICE_ADDRESS);
        CompareDataRvr = intent.getStringExtra(GameActivity.DataCompareRv);
        findAgeUser = intent.getStringExtra(ListaDispositivosFragment.EXTRA_FIND);
        presenter = new ConectarBluetoothPresenterImpl(this, address);


    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.onResume();

    }

    protected void onPause() {

        super.onPause();
        //presenter.onResume();
        presenter.OnPause();
        //finish();
    }



    @OnClick(R.id.IdEncender)
    void encender() {

        int findAgeUserInt=Integer.parseInt(findAgeUser);

         if (findAgeUserInt>=5) {

             //Toast.makeText(getBaseContext(), findAgeUser, Toast.LENGTH_SHORT).show();
             secuenciaNiv5.CuartaSec();
             CallGame();
             sequence.clear();
         }

        //else if


/*
        if (findAgeUser == 6) {

            Toast.makeText(getBaseContext(), findAgeUser, Toast.LENGTH_SHORT).show();

        }

        if (findAgeUser == 7) {

            Toast.makeText(getBaseContext(), findAgeUser, Toast.LENGTH_SHORT).show();

        }
        if (findAgeUser == 8) {

        }
*/
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
        this.data=data;

        //IdBufferIn.setText("Dato: " + data);//<-<- PARTE A MODIFICAR >->->

        /*if (data.equals("LED ENCENDIDO")){

             IdBufferIn.setText("Dato: " + "inciaLed");
        }
        else if(data.equals("LED APAGADO")){

            IdBufferIn.setText("Dato: " + "OFFLed");
        }*/

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void RunOnMain(Runnable action) {
        runOnUiThread(action);
    }

      public void CallGame() {

        GameSequence seq = new GameSequence();
        seq.setSequence(sequence);

        String tx = new Gson().toJson(seq);

        i.putExtra(GameActivity.SEQUENCE, tx);
        startActivity(i);
    }

    public void CallGame2() {

        GameNumSequence seq = new GameNumSequence();
        seq.setSequence(sequence2);

        String tx = new Gson().toJson(seq);

        j.putExtra(GameSecNumActivity.SEQUENCE, tx);
        startActivity(j);
    }




}