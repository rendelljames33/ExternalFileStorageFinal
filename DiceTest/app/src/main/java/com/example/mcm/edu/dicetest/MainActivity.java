package com.example.mcm.edu.dicetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.Random;

import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    public TextView number;
    public Random rng = new Random(6);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rng = new Random(6);
        Button roll = findViewById(R.id.button);
        number = findViewById(R.id.name);
        roll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rollDice();
                    }
                }
        );


    }
    public void rollDice(){
        int randomNumber = rng.nextInt(6)+1;
        switch (randomNumber){
            case(1):
                number.setText("You got 1");
                break;
            case(2):
                number.setText("You got 2");
                break;
            case(3):
                number.setText("You got 3");
                break;
            case(4):
                number.setText("You got 4");
                break;
            case(5):
                number.setText("You got 5");
                break;
            case(6):
                number.setText("You got 6");
                break;
        }
    }
}
