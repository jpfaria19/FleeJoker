package infnet.gads.fleejoker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private final static Random random = new Random();
    private AlertDialog alert;
    public long aux;
    public Chronometer chronometer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialogInitial();

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
        chronometer.stop();
        aux = chronometer.getBase();
        findViewById(R.id.result).setVisibility(View.VISIBLE);
        chronometer.setBase(aux);
    }

    public void tryAgainButton(View v){
        chronometer.setBase(SystemClock.elapsedRealtime());
        aux = 0;
        chronometer.start();
    }
}

