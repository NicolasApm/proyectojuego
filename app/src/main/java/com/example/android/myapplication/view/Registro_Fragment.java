package com.example.android.myapplication.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.myapplication.R;
import com.example.android.myapplication.common.EBotones;
import com.example.android.myapplication.common.ENnum;
import com.example.android.myapplication.model.GameNumSequence;
import com.example.android.myapplication.model.GameSequence;
import com.example.android.myapplication.model.Niv6_7Secuence;
import com.example.android.myapplication.model.SecuenciaNiv5;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import OpenHelper.Sqlite_OpenHelper;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Registro_Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Registro_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registro_Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Button btnGrabarUsu, btnPrueba1, btnPrueba2, btnPrueba3, btnPrueba4, btnPrueba5;
    Intent i, j;
    public EditText txtNomusu, txtApellidousu, txtEdadusu;
    private List<EBotones> sequence = new ArrayList<>();
    private SecuenciaNiv5 secuenciaNiv5 = new SecuenciaNiv5(sequence);
    private List<ENnum> sequence2 = new ArrayList<>();
    private Niv6_7Secuence secuenciaNiv67 = new Niv6_7Secuence(sequence2);


    // secuenciaNiv5 = new SecuenciaNiv5(sequence);
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Registro_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registro_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Registro_Fragment newInstance(String param1, String param2) {
        Registro_Fragment fragment = new Registro_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_registro_, container, false);

        txtNomusu = (rootView.findViewById(R.id.txtnomusu));
        txtApellidousu = (rootView.findViewById(R.id.txtciudadusu));
        txtEdadusu = (rootView.findViewById(R.id.txtedadusu));
        i = new Intent(getActivity(), GameActivity.class);
        j = new Intent(getActivity(), GameSecNumActivity.class);
        // Inflate the layout for this fragment
        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        btnGrabarUsu = (Button) getActivity().findViewById(R.id.RegistroUsu);
        btnGrabarUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Sqlite_OpenHelper helper = new Sqlite_OpenHelper(getActivity(), "usuario", null, 1);
                helper.abrirdb();
                helper.insertarReg(String.valueOf(txtNomusu.getText()),
                        String.valueOf(txtApellidousu.getText()),
                        String.valueOf(txtEdadusu.getText()));
                helper.cerrardb();
            }
        });

        btnPrueba1 = getActivity().findViewById(R.id.pruebaNivel);
        btnPrueba1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                secuenciaNiv5.PrimeraSec();
                CallGame();
                sequence.clear();
            }
        });
        btnPrueba2 = getActivity().findViewById(R.id.pruebaNive2);
        btnPrueba2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                secuenciaNiv5.SegundaSec();
                CallGame();
                sequence.clear();
            }
        });
        btnPrueba3 = getActivity().findViewById(R.id.pruebaNive3);
        btnPrueba3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                secuenciaNiv5.TerceraSec();
                CallGame();
                sequence.clear();
            }
        });

        btnPrueba4 = getActivity().findViewById(R.id.PruebaNive4);
        btnPrueba4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                secuenciaNiv5.CuartaSec();
                CallGame();
                sequence.clear();
            }
        });

        btnPrueba5 = getActivity().findViewById(R.id.PruebaNive5);
        btnPrueba5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                secuenciaNiv67.PrimeraSec();
                CallGame2();
                sequence2.clear();
            }
        });
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void CallGame() {

        GameSequence seq = new GameSequence();
        seq.setSequence(sequence);

        String tx = new Gson().toJson(seq);

        i.putExtra(GameActivity.SEQUENCE, tx);
        startActivity(i);
    }

    public void CallGame2() {

        GameNumSequence seq = new GameNumSequence();
        seq.setSequence(sequence2);

        String tx = new Gson().toJson(seq);

        j.putExtra(GameSecNumActivity.SEQUENCE, tx);
        startActivity(j);
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
}
