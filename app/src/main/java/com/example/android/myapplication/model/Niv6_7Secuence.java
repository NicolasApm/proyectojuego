package com.example.android.myapplication.model;

import com.example.android.myapplication.common.ENnum;

import java.util.List;

public class Niv6_7Secuence {

    static List<ENnum> sequence;

    public Niv6_7Secuence(List<ENnum> sequence) {

        this.sequence = sequence;
    }

    public static void PrimeraSec(){

        sequence.add(ENnum.TXTVIEW11);
        sequence.add(ENnum.TXTVIEW12);
        sequence.add(ENnum.TXTVIEW13);
        sequence.add(ENnum.TXTVIEW14);
        sequence.add(ENnum.TXTVIEW24);

    }
    public static void SegundaSec(){

        sequence.add(ENnum.TXTVIEW11);
        sequence.add(ENnum.TXTVIEW12);

        sequence.add(ENnum.TXTVIEW13);
        sequence.add(ENnum.TXTVIEW14);

    }
    public static void TerceraSec(){

        sequence.add(ENnum.TXTVIEW21);
        sequence.add(ENnum.TXTVIEW22);


    }
    public static void CuartaSec(){


        sequence.add(ENnum.TXTVIEW21);
        sequence.add(ENnum.TXTVIEW22);
        sequence.add(ENnum.TXTVIEW23);
        sequence.add(ENnum.TXTVIEW24);

    }



}

