package infnet.gads.fleejoker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView card_left, card_middle, card_right;

    private final static Random random = new Random();

    private final static int[] cardDeck = new int[]{R.drawable.AceOfSpades, R.drawable.Joker};

//    List<Integer> cards;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        card_left = findViewById(R.id.card_left);
        card_middle = findViewById(R.id.card_middle);
        card_right = findViewById(R.id.card_right);

//        cards = new ArrayList<>();
//        cards.add(107); //Spades
//        cards.add(407); //Joker

        card_left.setImageResource(cardDeck[random.nextInt(cardDeck.length)]);


        card_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        card_middle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        card_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
