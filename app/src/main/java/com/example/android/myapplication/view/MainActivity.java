package com.example.android.myapplication.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.android.myapplication.R;

public class MainActivity extends AppCompatActivity implements
        Registro.OnFragmentInteractionListener,
        MenuUcc.OnFragmentInteractionListener,
        ListaDispositivosFragment.OnFragmentInteractionListener
{

    private TextView mTextMessage;
    Registro registro;
    MenuUcc menuUcc;
    ListaDispositivosFragment listaDispositivosFragment;

       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    menuUcc=new MenuUcc();
                    fragmentTransaction.replace(R.id.ContenedorFragmentos,menuUcc);
                    fragmentTransaction.commit();

                    return true;
                case R.id.navigation_dashboard:
                    registro = new Registro();
                    if(listaDispositivosFragment!=null){
                        registro.setBtAdress(listaDispositivosFragment.getAddress());
                    }
                    fragmentTransaction.replace(R.id.ContenedorFragmentos, registro);
                    fragmentTransaction.commit();
                    return true;

                case R.id.navigation_notifications:
                    listaDispositivosFragment = ListaDispositivosFragment.newInstance();
                    fragmentTransaction.replace(R.id.ContenedorFragmentos, listaDispositivosFragment);
                    fragmentTransaction.commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void onResume () {
        super.onResume();

    }
}
