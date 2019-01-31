package com.example.android.myapplication.view;

import com.example.android.myapplication.common.EBotones;

public interface GameView {

    void onButton(EBotones btn);
    void offButton(EBotones btn);
    void Borrar(String borrar);
}
