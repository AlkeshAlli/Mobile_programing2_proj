package com.example.mp2_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ImageButton conv = findViewById(R.id.converter);
        ImageButton stat = findViewById(R.id.statistics);
        ImageButton lis = findViewById(R.id.lists);
        conv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent incon= new Intent(Menu.this,Converter.class);
                startActivity(incon);
            }
        });
        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent instat= new Intent(Menu.this,MainActivity.class);
                startActivity(instat);
            }
        });
        lis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Menu.this,One_to_many.class);
                startActivity(intent);
            }
        });


    }
}
