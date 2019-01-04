package com.example.android.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.example.android.myapplication.R;
import com.example.android.myapplication.common.EBotones;
import com.example.android.myapplication.model.GameSequence;
import com.example.android.myapplication.presenter.GamePresenter;
import com.example.android.myapplication.presenter.GamePresenterImpl;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity implements GameView {

    public static final String SEQUENCE = "SEQ";

    @BindView(R.id.btn11)
    Button btn11;
    @BindView(R.id.btn12)
    Button btn12;
    @BindView(R.id.btn13)
    Button btn13;
    @BindView(R.id.btn14)
    Button btn14;

    @BindView(R.id.btn21)
    Button btn21;
    @BindView(R.id.btn22)
    Button btn22;
    @BindView(R.id.btn23)
    Button btn23;
    @BindView(R.id.btn24)
    Button btn24;

    @BindView(R.id.btn31)
    Button btn31;
    @BindView(R.id.btn32)
    Button btn32;
    @BindView(R.id.btn33)
    Button btn33;
    @BindView(R.id.btn34)
    Button btn34;

    @BindView(R.id.btn41)
    Button btn41;
    @BindView(R.id.btn42)
    Button btn42;
    @BindView(R.id.btn43)
    Button btn43;
    @BindView(R.id.btn44)
    Button btn44;

    private GamePresenter presenter;

    private View.OnClickListener clickbutton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EBotones[] btns = EBotones.values();
            for (int i=0 ; i < btns.length; i++){
                if(v == getButtonFromEnum(btns[i])){
                    presenter.clickOnButton(btns[i]);
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        configOnClick();
        //leer los extras
        String data = getIntent().getStringExtra(SEQUENCE);
        GameSequence gs = new Gson().fromJson(data, GameSequence.class);

        presenter = new GamePresenterImpl(this, gs);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    private void configOnClick() {
        btn11.setOnClickListener(clickbutton);
        btn12.setOnClickListener(clickbutton);
        btn13.setOnClickListener(clickbutton);
        btn14.setOnClickListener(clickbutton);

        btn21.setOnClickListener(clickbutton);
        btn22.setOnClickListener(clickbutton);
        btn23.setOnClickListener(clickbutton);
        btn24.setOnClickListener(clickbutton);

        btn31.setOnClickListener(clickbutton);
        btn32.setOnClickListener(clickbutton);
        btn33.setOnClickListener(clickbutton);
        btn34.setOnClickListener(clickbutton);

        btn41.setOnClickListener(clickbutton);
        btn42.setOnClickListener(clickbutton);
        btn43.setOnClickListener(clickbutton);
        btn44.setOnClickListener(clickbutton);
    }

    public void onButton(EBotones btn) {
        getButtonFromEnum(btn).setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
    }

    public void offButton(EBotones btn) {
        getButtonFromEnum(btn).setBackgroundColor(getResources().getColor(R.color.colorAccent));
    }

    Button getButtonFromEnum(EBotones btn) {
        switch (btn) {
            case BUTTON11:
                return btn11;
            case BUTTON12:
                return btn12;
            case BUTTON13:
                return btn13;
            case BUTTON14:
                return btn14;
            case BUTTON21:
                return btn21;
            case BUTTON22:
                return btn22;
            case BUTTON23:
                return btn23;
            case BUTTON24:
                return btn24;
            case BUTTON31:
                return btn31;
            case BUTTON32:
                return btn32;
            case BUTTON33:
                return btn33;
            case BUTTON34:
                return btn34;
            case BUTTON41:
                return btn41;
            case BUTTON42:
                return btn42;
            case BUTTON43:
                return btn43;
            //case BUTTON44:
            default:
                return btn44;
        }
    }

}
