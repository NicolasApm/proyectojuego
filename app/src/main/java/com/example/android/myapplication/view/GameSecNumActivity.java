package com.example.android.myapplication.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.android.myapplication.R;
import com.example.android.myapplication.common.ENnum;
import com.example.android.myapplication.model.GameNumSequence;
import com.example.android.myapplication.presenter.GameSecNumPresenter;
import com.example.android.myapplication.presenter.GameSecNumPresenterSecNumImpl;
import com.google.gson.Gson;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameSecNumActivity extends AppCompatActivity implements GameSecNumView,MenuUcc_Fragment.OnFragmentInteractionListener {

    public static final String SEQUENCE = "SEQ";
    static Random RandonGenerator = new Random();
    static int randomInteger = 0, cont = 0, Rand1 = 0, Rand2 = 0, Rand3 = 0,Rand4 = 0,Rand5 = 0;
    static String Randstg;
    MenuUcc_Fragment fragmentMenuUcc;

    @BindView(R.id.Txtv11)
    TextView txtv11;
    @BindView(R.id.Txtv12)
    TextView txtv12;
    @BindView(R.id.Txtv13)
    TextView txtv13;
    @BindView(R.id.Txtv14)
    TextView txtv14;

    @BindView(R.id.Txtv21)
    TextView txtv21;
    @BindView(R.id.Txtv22)
    TextView txtv22;
    @BindView(R.id.Txtv23)
    TextView txtv23;
    @BindView(R.id.Txtv24)
    TextView txtv24;

    private GameSecNumPresenter presenter;

    //Funcion para oprimir botones y saber posicion
   /* private View.OnClickListener clickbutton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ENnum[] btns = ENnum.values();
            for (int i=0 ; i < btns.length; i++){
                if(v == getTextViewFromEnum(btns[i])){
                    presenter.clickOnButton(btns[i]);
                }
            }
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_niveles6_7);
        //Para implementar botones en el layout
        ButterKnife.bind(this);

        // configOnClick();
        //leer los extras
        String data = getIntent().getStringExtra(SEQUENCE);
        GameNumSequence gs = new Gson().fromJson(data, GameNumSequence.class);

        presenter = new GameSecNumPresenterSecNumImpl(this, gs);


    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    //Apagar boton por boton
    public void offButton(ENnum btn) {
        // getTextViewFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
    }

    @Override
    //Apagar todos los botones

    public void Borrar(String borrar) {

        if (borrar == "BorrarBotones") {

            Timer timer = new Timer();
            TimerTask t = new TimerTask() {
                @Override
                public void run() {

                    getTextViewFromEnum(ENnum.TXTVIEW11).setText("");
                    getTextViewFromEnum(ENnum.TXTVIEW12).setText("");
                    getTextViewFromEnum(ENnum.TXTVIEW13).setText("");
                    getTextViewFromEnum(ENnum.TXTVIEW14).setText("");
                    getTextViewFromEnum(ENnum.TXTVIEW21).setText("");
                    getTextViewFromEnum(ENnum.TXTVIEW22).setText("");
                    getTextViewFromEnum(ENnum.TXTVIEW23).setText("");
                    getTextViewFromEnum(ENnum.TXTVIEW24).setText("");

                }

            };
            timer.scheduleAtFixedRate(t, 0, 10000);


        }

        //if

        FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        fragmentMenuUcc =new MenuUcc_Fragment();
        fragmentTransaction.replace(R.id.ContenedorNiv, fragmentMenuUcc);
        fragmentTransaction.commit();

    }

    //Encender los botones
    @Override
    public void onButton(ENnum txt) {

        generarSecuencia();

        if (txt == ENnum.TXTVIEW11) {
            getTextViewFromEnum(txt).setText(Randstg);
        }
        else if (txt == ENnum.TXTVIEW12) {
            getTextViewFromEnum(txt).setText(Randstg);
        }
        else if (txt == ENnum.TXTVIEW13) {
            getTextViewFromEnum(txt).setText(Randstg);
        }
        else if (txt == ENnum.TXTVIEW14) {
            getTextViewFromEnum(txt).setText(Randstg);
        }
        else if (txt == ENnum.TXTVIEW21) {
            getTextViewFromEnum(txt).setText(Randstg);
        }
        else if (txt == ENnum.TXTVIEW22) {
            getTextViewFromEnum(txt).setText(Randstg);
        }
        else if (txt == ENnum.TXTVIEW23) {
            getTextViewFromEnum(txt).setText(Randstg);
        }
        else if (txt == ENnum.TXTVIEW24) {
            getTextViewFromEnum(txt).setText(Randstg);
        }
        else{}

    }

    TextView getTextViewFromEnum(ENnum txt) {

        switch (txt) {
            case TXTVIEW11:
                return txtv11;
            case TXTVIEW12:
                return txtv12;
            case TXTVIEW13:
                return txtv13;
            case TXTVIEW14:
                return txtv14;
            case TXTVIEW21:
                return txtv21;
            case TXTVIEW22:
                return txtv22;
            case TXTVIEW23:
                return txtv23;

            default:
                return txtv24;
        }
    }

    public static void generarSecuencia() {

        for (cont = 0; cont < 4; cont++) {
            randomInteger = RandonGenerator.nextInt(9) + 1;

            switch (cont) {
                case 0:
                    Rand1 = randomInteger;
                    Randstg = Integer.toString(Rand1);
                    break;
                case 1:
                    Rand2 = randomInteger;
                    Randstg = Integer.toString(Rand2);
                    break;
                case 2:
                    Rand3 = randomInteger;
                    Randstg = Integer.toString(Rand3);
                    break;
                case 3:
                    Rand4 = randomInteger;
                    Randstg = Integer.toString(Rand4);
                    break;
                case 4:
                    Rand5 = randomInteger;
                    Randstg = Integer.toString(Rand5);
                    break;
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
