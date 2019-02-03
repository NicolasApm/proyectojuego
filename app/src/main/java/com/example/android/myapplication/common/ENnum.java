package com.example.android.myapplication.common;

public enum ENnum {
    TXTVIEW11("11"),
    TXTVIEW12("12"),
    TXTVIEW13("13"),
    TXTVIEW14("14"),
    TXTVIEW21("21"),
    TXTVIEW22("22"),
    TXTVIEW23("23"),
    TXTVIEW24("24");

    private String id;
    ENnum(String id) {
        this.id = id;
    }
    public String getId() {
        return this.id;
    }
}
