package com.orfriend.orfriend.marwaPart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.orfriend.orfriend.R;
import com.orfriend.orfriend.frontend.userPage.UserHomePage;
import com.orfriend.orfriend.frontend.userPage.mychildrenPage.KidMenu;

public class ChildInfo extends AppCompatActivity {
ImageButton but1;
    ImageButton but2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_info);
        home();
        goBack();
    }
    /**
     * it directs the user back to the user's home page
     */
    public void home(){
        but1 = (ImageButton) findViewById(R.id.home4);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(ChildInfo.this,UserHomePage.class);
                startActivity(x);
            }
        });
    }

    /**
     * it lets the user to go back to the previous page
     */
    public void goBack(){
        but2 = (ImageButton) findViewById(R.id.back4);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(ChildInfo.this,KidMenu.class);
                startActivity(x);
            }
        });
    }
}
