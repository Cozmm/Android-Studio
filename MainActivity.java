package com.example.dev1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button b;

    Handler timer;
    TextView text;

    boolean repos;
    boolean attente;
    boolean attenteClic;
    boolean start;
    boolean succes;
    Chronometer chronometer;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.button);
        text = findViewById(R.id.text);

        timer = new Handler();
        count = 0;

        repos = true;
        attente = false;
        attenteClic = false;
        succes = false;
        start = false;

        chronometer = findViewById(R.id.chrono);
        b.setOnClickListener(b_listener);
    }

    View.OnClickListener b_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            System.out.println(" attenteClic " + attenteClic);
            System.out.println(" attente " + attente);
            System.out.println(" play " + start);
            System.out.println(" repos " + repos);
            System.out.println(" succes " + succes);
            if (succes) {
                /* l'application est en mode succès */
                chronometer.stop();
                count +=1 ;
            }
            if (start) {
                /* l'application est en mode jeux */
            }
            if (attente) {
                /* l'application est en mode attente */
                attenteClic = true;
            }
            if (repos) {
                /* l'application est en mode repos */
                repos = false;
                attente = true;
                text.setText("Le jeu vas commencer veuillez attendre quelques secondes");
                b.setBackgroundColor(getResources().getColor(R.color.grey));
            }
            timer_listener.run();
        }
    };

    Runnable timer_listener = new Runnable() {
        @Override
        public void run() {
            /* si on clic pendant la phase d'attente */
            if (attenteClic) {
                b.setBackgroundColor(getResources().getColor(R.color.red));
                Toast.makeText(getApplicationContext(),
                        "Erreur...phase...Attente...", Toast.LENGTH_SHORT).show();
                timer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        b.setBackgroundColor(getResources().getColor(R.color.grey));
                        attenteClic = false;
                    }
                }, 1500);
            }
            /* si on est en phase d'attente et que l'on a pas cliqué pendant cette phase*/
            if ((attente) && (attenteClic == false)){
                timer.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        attente = false;
                        start = true;
                        text.setText("Cliquez le plus vite possible");
                        b.setBackgroundColor(getResources().getColor(R.color.yellow));
                    }
                }, (int) (Math.random()*(10000-3000)+1000));
            }
            if (start){
                chronometer.start();
            }

        }
    };
}

