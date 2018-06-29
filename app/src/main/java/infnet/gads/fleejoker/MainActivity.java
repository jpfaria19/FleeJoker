package infnet.gads.fleejoker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private AlertDialog alert;
    public long aux;
    public Chronometer chronometer;
    private Button tryAgain;
    private TextView txtResultado;
    private ImageView card1, card2, card3;
    private String[] arrayString = {"ace", "jk", "jk"};

    private List<String> cards = new ArrayList<>(Arrays.asList(arrayString));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialogInitial();
        tryAgain = findViewById(R.id.tryAgainButton);
        card1 = findViewById(R.id.card_right);
        card2 = findViewById(R.id.card_middle);
        card3 = findViewById(R.id.card_left);
        txtResultado = findViewById(R.id.textView);

    }

    //Random cards
    private String posicaoDasCartas() {
        Random random = new Random();
        int randomize = random.nextInt(cards.size());
        String cardName = cards.get(randomize);
        cards.remove(randomize);

        return cardName;
    }

    private void changeCard1(){
        card2.setClickable(false);
        card3.setClickable(false);

        //ESTÁ SELECIONANDO MAS SÓ DA PRA VER DEPOIS QUE CONFIRMA.
        card1.setSelected(true);

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                String carta = posicaoDasCartas();
                if (carta.equals("ace")) {
                    card1.setImageResource(R.drawable.aceofspades);
                    txtResultado.setText("Aeehh, você ganhou");

                } else {
                    card1.setImageResource(R.drawable.joker);
                    txtResultado.setText("Ahh droga, você perdeu");
                }


                Animation fadeIn = new AlphaAnimation(0, 1);
                fadeIn.setInterpolator(new DecelerateInterpolator());
                fadeIn.setDuration(1000);
                fadeIn.setFillAfter(true);

                card1.startAnimation(fadeIn);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        card1.startAnimation(fadeOut);
    }

    //Initial dialog to start game
    public void dialogInitial() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Bem vindo ao FleeJoker");
        dialogBuilder.setMessage
                (
                        "Selecione uma carta e clique em confirmar; \n \n " +
                                "Quando você clicar em 'COMEÇAR' um tempo vai começar a contar, " +
                                "então quantos menos tempo você acertar, melhor. \n " +
                                "Bom jogo!"
                );

        dialogBuilder.setPositiveButton("Começar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.v("DEBUG", "verb antes do chronometro...");
                chronometer = findViewById(R.id.result);
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                Toast.makeText(MainActivity.this, "COMEÇOUUUUU", Toast.LENGTH_LONG).show();
            }
        });
        dialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);
            }
        });

        alert = dialogBuilder.create();
        alert.show();
    }

    public void confirmButton(View v) {
        if (!card1.isSelected()){
            card1.setImageResource(R.drawable.backcardselected);
            changeCard1();
        }else {
            Toast.makeText(this, "SELECIONE UMA CARTA", Toast.LENGTH_LONG).show();
        }

        //TIMER
        chronometer.stop();
        aux = chronometer.getBase();
        findViewById(R.id.result).setVisibility(View.VISIBLE);
        chronometer.setBase(aux);
    }

    public void tryAgainButton(View v){
        card1.setClickable(true);
        card2.setClickable(true);
        card3.setClickable(true);
        Toast.makeText(this, "SELECIONE UMA CARTA", Toast.LENGTH_LONG).show();
        cards = new ArrayList<>(Arrays.asList(arrayString));

        //Animation

        chronometer.setBase(SystemClock.elapsedRealtime());
        aux = 0;
        chronometer.start();
    }
}

