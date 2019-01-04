package com.example.android.myapplication.presenter;

import com.example.android.myapplication.common.EBotones;
import com.example.android.myapplication.model.GameSequence;
import com.example.android.myapplication.view.GameView;

import java.util.Timer;
import java.util.TimerTask;

public class GamePresenterImpl implements GamePresenter {

    private GameView view;
    private GameSequence sequence;
    private boolean started = false;
    // TIMEPO DE SECUENCIA
    private final int SEQ_TIME = 1000;
    private int index = 0;
    private boolean isOn = false;
    private Timer timer = new Timer();

    public GamePresenterImpl(GameView view, GameSequence sequence){
        this.view = view;
        this.sequence = sequence;
    }

    @Override
    public void clickOnButton(EBotones button) {

    }

    @Override
    public void start() {
        if(started) return;
        started = true;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // si no se ha terminado
                if(sequence.getSequence().size() <= index){
                    timer.cancel();
                    return;
                }
                EBotones btn =  sequence.getSequence().get(index);
                if(isOn){
                    view.offButton(btn);
                    isOn = false;
                    index++;
                } else {
                    view.onButton(btn);
                    isOn = true;
                }
            }
        },500,  SEQ_TIME);
    }
}
