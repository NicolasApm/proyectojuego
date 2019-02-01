package com.example.android.myapplication.presenter;

import com.example.android.myapplication.common.EBotones;
import com.example.android.myapplication.model.GameSequence;
import com.example.android.myapplication.view.FallaConnected;
import com.example.android.myapplication.view.GameView;

import java.util.Timer;
import java.util.TimerTask;

public class GamePresenterImpl implements GamePresenter{

    private GameView view;
    private GameSequence sequence;
    private boolean started = false;
    // TIMEPO DE SECUENCIA
    private final int SEQ_TIME = 1000;
    private int index = 0;
    private boolean isOn = false;
    private Timer timer = new Timer();
    private EBotones btn=null;


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
        //timmer
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // si no se ha terminado
                //cancelar si el indice es igual a numero de la secuencia
                if(sequence.getSequence().size() <= index){
                    timer.cancel();
                    view.Borrar("BorrarBotones");
                    return;
                }
                btn =  sequence.getSequence().get(index);
                if(isOn){
                    //condicion para validar si estan encendidos los botones
                    //view.offButton(btn);
                    isOn = false;
                    index++;
                }
                else {
                    //Encender si estan apagados
                    view.onButton(btn);
                    isOn = true;
                }
            }
        },1000,  SEQ_TIME);
    }
}
