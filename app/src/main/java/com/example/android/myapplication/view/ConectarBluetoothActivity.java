package com.example.android.myapplication.presenter;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Context;
import android.content.Intent;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myapplication.view.ConectarBluetooth;
import com.example.android.myapplication.view.FallaConnected;
import com.example.android.myapplication.view.UserInterfaz;
import com.example.android.myapplication.view.UserView;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.UUID;

import OpenHelper.Usuarios;

public class ConectarBluetoothImp extends AppCompatActivity implements ConectarBluetooth, FallaConnected {
    //1)
    private ArrayList<Usuarios> usuarios = new ArrayList<>();

    TextView IdBufferIn;
    //-------------------------------------------
    static Handler bluetoothIn;
    static final int handlerState = 0;
    private BluetoothSocket btSocket;
    private BluetoothAdapter btAdapter = null;
    private StringBuilder DataStringIN = new StringBuilder();
    public ConnectedThread MyConexionBT;
    public UserView view;
    // Identificador unico de servicio - SPP UUID
    private static final UUID BTMODULEUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    // String para la direccion MAC
    private static String address = null;
    public String Datawrite;
    private Context context;


    public ConectarBluetoothImp(BluetoothSocket btSocket) {
        this.btSocket=btSocket;
    }

    public ConectarBluetoothImp(UserView view, UserInterfaz context) {
        this.view=view;
        this.context=context;
    }


    public void DataWrite(String DataWrite) {
      //  try {
            this.Datawrite = DataWrite;
            switch (DataWrite){
                case "1":
                    MyConexionBT.write("1");
                    break;
                case "0":
                    MyConexionBT.write("0");
                    break;
            }
    }


    @Override
    public void RecibirDatos(){

        bluetoothIn = new Handler() {
            public void handleMessage(android.os.Message msg) {
                if (msg.what == handlerState) {
                    String readMessage = (String) msg.obj;
                    DataStringIN.append(readMessage);

                    int endOfLineIndex = DataStringIN.indexOf("#");

                    if (endOfLineIndex > 0) {
                        String dataInPrint = DataStringIN.substring(0, endOfLineIndex);
                        IdBufferIn.setText("Dato: " + dataInPrint);//<-<- PARTE A MODIFICAR >->->
                        DataStringIN.delete(0, DataStringIN.length());
                    }
                }
            }
        };

        btAdapter = BluetoothAdapter.getDefaultAdapter(); // get Bluetooth adapter
        VerificarEstadoBT();

        // Configuracion onClick listeners para los botones
        // para indicar que se realizara cuando se detecte
        // el evento de Click
    }


    private BluetoothSocket createBluetoothSocket(BluetoothDevice device) throws IOException
    {
        //crea un conexion de salida segura para el dispositivo
        //usando el servicio UUID
        return device.createRfcommSocketToServiceRecord(BTMODULEUUID);
    }

    @Override
    public void ConectarSocket( ){

        ////Consigue la direccion MAC desde DeviceListActivity via intent
        Intent intent = getIntent();
        //Consigue la direccion MAC desde DeviceListActivity via EXTRA
        //address = intent.getStringExtra(ListaDispositivosFragment.EXTRA_DEVICE_ADDRESS);//<-<- PARTE A MODIFICAR >->->
        address="B8:69:C2:CD:47:CE";
        //Setea la direccion MAC
        //Log.d("direccio", address);
        BluetoothDevice device = btAdapter.getRemoteDevice(address);
        try
        {
            this.btSocket = createBluetoothSocket(device);
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), "La creacción del Socket fallo", Toast.LENGTH_LONG).show();
        }
        // Establece la conexión con el socket Bluetooth.
        try
        {
            this.btSocket.connect();
        } catch (IOException e) {
            try {
                this.btSocket.close();
            } catch (IOException e2) {}
        }

        MyConexionBT = new ConnectedThread(btSocket);
        MyConexionBT.start();
    }


    public void cerrarSocket( ){

        try
        { // Cuando se sale de la aplicación esta parte permite
            // que no se deje abierto el socket
            this.btSocket.close();
        } catch (IOException e2) {}
    }


    //Comprueba que el dispositivo Bluetooth Bluetooth está disponible y solicita que se active si está desactivado

    public void VerificarEstadoBT() {

        if(btAdapter==null) {
            Toast.makeText(getBaseContext(), "El dispositivo no soporta bluetooth", Toast.LENGTH_LONG).show();
        } else {
            if (btAdapter.isEnabled()) {
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
    }

    public class ConnectedThread extends Thread
    {
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        Handler bluetoothIn;
        final int handlerState = 0;

        public ConnectedThread(BluetoothSocket socket)
        {
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            try
            {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) { }
            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run()
        {
            byte[] buffer = new byte[256];
            int bytes;

            // Se mantiene en modo escucha para determinar el ingreso de datos
            while (true) {
                try {
                    bytes = mmInStream.read(buffer);
                    String readMessage = new String(buffer, 0, bytes);
                    // Envia los datos obtenidos hacia el evento via handler
                    bluetoothIn.obtainMessage(handlerState, bytes, -1, readMessage).sendToTarget();
                } catch (IOException e) {
                    break;
                }
            }
        }

        //Envio de trama
        public void write(String input)
        {
            try {
                mmOutStream.write(input.getBytes());
            }
            catch (IOException e)
            {

                Log.d("HolaMundo","La Conexión fallo");
                //si no es posible enviar datos se cierra la conexión

             finish();
            }
        }
    }
}