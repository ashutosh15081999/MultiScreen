package com.example.multiscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView numbers = (TextView)findViewById(R.id.numbers);

        numbers.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view){
                Intent numbersIntent = new Intent(MainActivity.this, Number.class);

                startActivity(numbersIntent);
            }

        });


        TextView FamilyMembers = (TextView)findViewById(R.id.Family);

        FamilyMembers.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view){
                Intent familyIntent = new Intent(MainActivity.this, Family.class);

                startActivity(familyIntent);
            }

        });


        TextView colors = (TextView)findViewById(R.id.Colors);

        colors.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view){
                Intent colorsIntent = new Intent(MainActivity.this, Colors.class);

                startActivity(colorsIntent);
            }

        });


        TextView phrases = (TextView)findViewById(R.id.Phrases);

        phrases.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view){
                Intent phrasesIntent = new Intent(MainActivity.this, Phrases.class);

                startActivity(phrasesIntent);
            }

        });

    }
}
