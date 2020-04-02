package com.example.andrei.wardrobe;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Wardrobe extends AppCompatActivity {

    ImageButton shelf1,shelf2,shelf3,shelf4;
    ActionBar actionBar;

    public void buttonFunction(){

        shelf1 = (ImageButton)findViewById(R.id.shelf1);
        shelf2 = (ImageButton)findViewById(R.id.shelf2);
        shelf3 = (ImageButton)findViewById(R.id.shelf3);
        shelf4 = (ImageButton)findViewById(R.id.shelf4);

        shelf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Wardrobe.this,Shelf1.class));
            }
        });

        shelf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Wardrobe.this,Shelf2.class));
            }
        });

        shelf3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Wardrobe.this,Shelf3.class));
            }
        });

        shelf4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Wardrobe.this,Shelf4.class));
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wardrobe);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#282828")));

        buttonFunction();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_info:
                startActivity(new Intent(this,Credits.class));
                return true;

        }
        return super.onOptionsItemSelected(item);
    }
}
