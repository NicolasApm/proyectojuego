package com.example.android.myapplication.view;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myapplication.R;
import com.example.android.myapplication.devices.BTUtil;
import com.example.android.myapplication.presenter.ConectarBluetoothPresenter;
import com.example.android.myapplication.presenter.ListaDispositivosPresenter;
import com.example.android.myapplication.presenter.ListaDispositivosPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import OpenHelper.Sqlite_OpenHelper;
import OpenHelper.Usuarios;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaDispositivosFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaDispositivosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaDispositivosFragment extends Fragment implements ListaDispositivosView{

    //1)
    // Depuración de LOGCAT
    private static final String TAG = "DispositivosBT"; //<-<- PARTE A MODIFICAR >->->
    // Declaracion de ListView
    ListView IdLista;
    // String que se enviara a la actividad principal, mainactivity
    public static String EXTRA_DEVICE_ADDRESS = "device_address";
    public static String EXTRA_FIND = "EXTRA_FIND";

    // Declaracion de campos
    //private BluetoothAdapter mBtAdapter;
    private ArrayAdapter mPairedDevicesArrayAdapter;
    private OnFragmentInteractionListener mListener;
    private ListaDispositivosPresenter presenter;
    private ArrayList<Usuarios> usuarios = new ArrayList<>();
    private String findAgeUser;
    private BTUtil btUtil;
    Intent j,k;
    ConectarBluetoothActivity  conectarBluetoothActivity;



    public ListaDispositivosFragment() {
        // Required empty public constructor
    }

       // TODO: Rename and change types and number of parameters
    public static ListaDispositivosFragment newInstance() {
        ListaDispositivosFragment fragment = new ListaDispositivosFragment();
        /*Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ListaDispositivosPresenterImpl(this);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
*/

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        presenter = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        try {
            btUtil.VerificarEstadoBT();
        } catch (Exception e) {
            e.printStackTrace();
        }

        View v = inflater.inflate(R.layout.fragment_listadispositivos, container, false);
        IdLista = (ListView)v.findViewById(R.id.IdLista);
        initRecyclerView();
        return  v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void onResume () {
        super.onResume();
        presenter.onResume();

    }

    private void initRecyclerView(){
        // Inicializa la array que contendra la lista de los dispositivos bluetooth vinculados
        mPairedDevicesArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.nombre_dispositivos);

        // Presenta los disposisitivos vinculados en el ListView
        IdLista.setAdapter(mPairedDevicesArrayAdapter);
        IdLista.setOnItemClickListener(mDeviceClickListener);
    }

    private AdapterView.OnItemClickListener mDeviceClickListener = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView av, View v, int arg2, long arg3) {
            // Obtener la dirección MAC del dispositivo, que son los últimos 17 caracteres en la vista
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);

            // Realiza un intent para iniciar la siguiente actividad
            // mientras toma un EXTRA_DEVICE_ADDRESS que es la dirección MAC.
            j = new Intent(getActivity(), InitGameActivity.class);
            //k = new Intent(getActivity(), InitGameActivity.class);
            j.putExtra(EXTRA_DEVICE_ADDRESS, address);
            find();

            //Intent i = new Intent(getActivity(), UserInterfaz.class);//<-<- PARTE A MODIFICAR >->->
            startActivity(j);
        }
    };
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void askEnableBT() {
        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(enableBtIntent, 1);
    }

    @Override
    public void setData(List<String> data) {
        mPairedDevicesArrayAdapter.clear();
        for (String d: data) {
            mPairedDevicesArrayAdapter.add(d);
        }
        mPairedDevicesArrayAdapter.notifyDataSetChanged();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void find(){

        ConectarBluetoothView conectarBluetoothView = null;
        Sqlite_OpenHelper bh = new Sqlite_OpenHelper(getActivity(),"usuario",null,1);
        if(bh!=null){
            SQLiteDatabase db = bh.getReadableDatabase();
            Cursor c = db.rawQuery("SELECT * FROM usuario WHERE _ID = (SELECT MAX(_ID) FROM usuario)",null);

            if(c.moveToFirst()){
                do{
                    usuarios.add(new Usuarios(c.getInt(0),c.getString(1),c.getString(2),c.getString(3)));

                }while(c.moveToNext());

                findAgeUser =usuarios.get(0).getEdad();

                j.putExtra(EXTRA_FIND,findAgeUser);
                //Log.d("pruebafind", findAgeUser);
            }

        }
    }
}
