package com.example.android.myapplication.model;

import com.example.android.myapplication.common.EBotones;

import java.util.List;

public class SecuenciaNiv5 {

    static List<EBotones> sequence;

    public SecuenciaNiv5(List<EBotones> sequence) {

        this.sequence = sequence;
    }

    public static void PrimeraSec(){

        sequence.add(EBotones.BUTTON11);
        sequence.add(EBotones.BUTTON12);
        sequence.add(EBotones.BUTTON13);
        sequence.add(EBotones.BUTTON23);
        sequence.add(EBotones.BUTTON33);
        sequence.add(EBotones.BUTTON32);
        sequence.add(EBotones.BUTTON31);
        sequence.add(EBotones.BUTTON21);

    }
    public static void SegundaSec(){

        sequence.add(EBotones.BUTTON11);
        sequence.add(EBotones.BUTTON22);
        sequence.add(EBotones.BUTTON33);
        sequence.add(EBotones.BUTTON44);
        sequence.add(EBotones.BUTTON14);
        sequence.add(EBotones.BUTTON23);
        sequence.add(EBotones.BUTTON32);
        sequence.add(EBotones.BUTTON41);
    }
    public static void TerceraSec(){

        sequence.add(EBotones.BUTTON11);
        sequence.add(EBotones.BUTTON22);
        sequence.add(EBotones.BUTTON33);

    }
    public static void CuartaSec(){


        sequence.add(EBotones.BUTTON23);
        sequence.add(EBotones.BUTTON32);
        sequence.add(EBotones.BUTTON41);
    }



}

