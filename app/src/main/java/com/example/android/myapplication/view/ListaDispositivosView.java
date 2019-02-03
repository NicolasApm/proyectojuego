package com.example.android.myapplication.view;

import java.util.List;

public interface ListaDispositivosView {

    void showToast(String message);

    void askEnableBT();

    void setData(List<String> data);
}
