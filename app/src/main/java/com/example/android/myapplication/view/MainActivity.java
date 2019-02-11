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
        Registro_Fragment.OnFragmentInteractionListener,
        MenuUcc_Fragment.OnFragmentInteractionListener,
        ListaDispositivosFragment.OnFragmentInteractionListener
{

    private TextView mTextMessage;
    Registro_Fragment fragmentRegistro;
    MenuUcc_Fragment fragmentMenuUcc;
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
                    fragmentMenuUcc =new MenuUcc_Fragment();
                    fragmentTransaction.replace(R.id.ContenedorFragmentos, fragmentMenuUcc);
                    fragmentTransaction.commit();

                    return true;
                case R.id.navigation_dashboard:
                    fragmentRegistro =new Registro_Fragment();
                    fragmentTransaction.replace(R.id.ContenedorFragmentos, fragmentRegistro);
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
