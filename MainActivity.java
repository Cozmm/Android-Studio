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

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button b;

    Handler timer;
    TextView text;

    boolean repos;
    boolean attente;
    boolean attenteClic;
    boolean succes;

    int count;

    AlertDialog erreur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b = findViewById(R.id.button);
        text = findViewById(R.id.text);


        timer = new Handler();
        repos = true;
        attente = false;
        succes = false;

        double waitTime =  (Math.random() * (10 - 3)) + 1;

        b.setOnClickListener(b_listener);

        erreur = new AlertDialog.Builder(MainActivity.this).create();
        erreur.setTitle(R.string.titre);
        erreur.setMessage(getString(R.string.message));

    }

    View.OnClickListener b_listener = (v) -> {
        System.out.println("clic");
        System.out.println("repos " + repos);
        System.out.println( "attente "+ attente);
        if (repos) {
            /* l'application est en mode repos */
            repos = false;
            b.setBackgroundColor(getResources().getColor(R.color.grey));
            attente = true;
            text.setText("Le jeu vas commencer veuillez attendre quelques secondes");
        } else {
            if (attente) {

            } else {

            }
        }
    };


    public void waitS(float waitTime) {
        float stime = System.currentTimeMillis();
        float time = 0 ;
        while (time < waitTime){
            time =  ((System.currentTimeMillis()-stime)/1000F);
        }
    }
}