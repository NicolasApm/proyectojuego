package com.example.android.myapplication.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.myapplication.R;
import com.example.android.myapplication.common.EBotones;
import com.example.android.myapplication.common.ENnum;
import com.example.android.myapplication.model.GameNumSequence;
import com.example.android.myapplication.model.GameSequence;
import com.example.android.myapplication.model.Niv6_7Secuence;
import com.example.android.myapplication.model.SecuenciaNiv5;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class InitGameActivity extends AppCompatActivity {
    Intent i, j;

    private List<EBotones> sequence = new ArrayList<>();
    private SecuenciaNiv5 secuenciaNiv5 = new SecuenciaNiv5(sequence);
    private List<ENnum> sequence2 = new ArrayList<>();
    private Niv6_7Secuence secuenciaNiv67 = new Niv6_7Secuence(sequence2);
    public static String EXTRA_DEVICE_ADDRESS = "device_address";
    private String address;
    private String findAgeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_game);
        ButterKnife.bind(this);
        i = new Intent(this, GameActivity.class);
        j = new Intent(this, GameSecNumActivity.class);
        Intent intent = getIntent();
        findAgeUser = intent.getStringExtra(ListaDispositivosFragment.EXTRA_FIND);
        address = intent.getStringExtra(ListaDispositivosFragment.EXTRA_DEVICE_ADDRESS);


    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        findAgeUser = intent.getStringExtra(ListaDispositivosFragment.EXTRA_FIND);
        address = intent.getStringExtra(ListaDispositivosFragment.EXTRA_DEVICE_ADDRESS);
    }


    @OnClick(R.id.prutng)
    public void InitGameLVL() {

        int findAgeUserInt = Integer.parseInt(findAgeUser);

        if (findAgeUserInt <= 5) {

            //Toast.makeText(getBaseContext(), findAgeUser, Toast.LENGTH_SHORT).show();
            secuenciaNiv5.CuartaSec();
            CallGame();
            sequence.clear();
        }

        else if (findAgeUserInt == 6) {

            Toast.makeText(getBaseContext(), findAgeUserInt, Toast.LENGTH_SHORT).show();

        }

        else if (findAgeUserInt == 7) {

            Toast.makeText(getBaseContext(), findAgeUserInt, Toast.LENGTH_SHORT).show();

        }

        else if (findAgeUserInt == 8) {

//            Toast.makeText(getBaseContext(), findAgeUserInt, Toast.LENGTH_SHORT).show();
            secuenciaNiv67.CuartaSec();
            CallGame2();
            sequence2.clear();
        }

    }

    public void CallGame() {

        GameSequence seq = new GameSequence();
        seq.setSequence(sequence);

        String tx = new Gson().toJson(seq);

        i.putExtra(GameActivity.SEQUENCE, tx);
        i.putExtra(EXTRA_DEVICE_ADDRESS, address);
        startActivity(i);
    }

    public void CallGame2() {

        GameNumSequence seq = new GameNumSequence();
        seq.setSequence(sequence2);

        String tx = new Gson().toJson(seq);

        j.putExtra(GameSecNumActivity.SEQUENCE, tx);
        j.putExtra(EXTRA_DEVICE_ADDRESS, address);
        startActivity(j);
    }

}
