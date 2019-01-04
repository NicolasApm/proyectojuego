package com.example.android.myapplication.common;

public enum EBotones {
    BUTTON11("11"),
    BUTTON12("12"),
    BUTTON13("13"),
    BUTTON14("14"),
    BUTTON21("21"),
    BUTTON22("22"),
    BUTTON23("23"),
    BUTTON24("24"),
    BUTTON31("31"),
    BUTTON32("32"),
    BUTTON33("33"),
    BUTTON34("34"),
    BUTTON41("41"),
    BUTTON42("42"),
    BUTTON43("43"),
    BUTTON44("44");

    private String id;

    EBotones(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
