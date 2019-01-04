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
import com.example.android.myapplication.model.GameSequence;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import OpenHelper.Sqlite_OpenHelper;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Registro.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Registro#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Registro extends Fragment  {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button btnGrabarUsu;
    EditText txtNomusu,txtApellidousu,txtEdadusu;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Registro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registro.
     */
    // TODO: Rename and change types and number of parameters
    public static Registro newInstance(String param1, String param2) {
        Registro fragment = new Registro();
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

        View rootView= inflater.inflate(R.layout.fragment_registro_, container, false);

        txtNomusu=(rootView.findViewById(R.id.txtnomusu));
        txtApellidousu=(rootView.findViewById(R.id.txtciudadusu));
        txtEdadusu=(rootView.findViewById(R.id.txtedadusu));

        // Inflate the layout for this fragment
        return rootView;
    }

    public void onActivityCreated (Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        btnGrabarUsu=(Button)getActivity().findViewById(R.id.RegistroUsu);
        btnGrabarUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), GameActivity.class);

                List<EBotones> sequence = new ArrayList<>();
                sequence.add(EBotones.BUTTON22);
                sequence.add(EBotones.BUTTON23);
                sequence.add(EBotones.BUTTON32);
                sequence.add(EBotones.BUTTON33);

                List<EBotones> sequence2 = new ArrayList<>();
                sequence2.add(EBotones.BUTTON11);
                sequence2.add(EBotones.BUTTON22);
                sequence2.add(EBotones.BUTTON33);
                sequence2.add(EBotones.BUTTON44);
                sequence2.add(EBotones.BUTTON14);
                sequence2.add(EBotones.BUTTON23);
                sequence2.add(EBotones.BUTTON32);
                sequence2.add(EBotones.BUTTON41);

                GameSequence seq = new GameSequence();
                seq.setSequence(sequence2);

                String tx = new Gson().toJson(seq);

                i.putExtra(GameActivity.SEQUENCE, tx);
                startActivity(i);

/*
                Sqlite_OpenHelper helper=new Sqlite_OpenHelper(getActivity(),"usuario",null,1);
                helper.abrirdb();
                helper.insertarReg(String.valueOf(txtNomusu.getText()),
                String.valueOf(txtApellidousu.getText()),
                String.valueOf(txtEdadusu.getText()));
                helper.cerrardb();
                */
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
