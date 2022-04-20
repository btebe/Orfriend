package com.orfriend.orfriend.frontend.index;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.orfriend.orfriend.R;

/**
 * csis 330 project
 * project name: OrFriend
 * Options.java
 * purpose: to let the user choose options such as login, register and about OrFriend
 * @author basmatebe
 * @version 1.0 27/11/2016
 */
public class Options extends AppCompatActivity {
    /**
     * represents the login page button
     */
    public Button but1;
    /**
     * represents the registration page button
     */
    public Button but2;

    /**
     * represents the About OrFriend page button
     */
    public Button but3;


    /**
     * it transfers the user from the option's page to the loginUser page
     */
    public void loginPage(){
        but1 = (Button) findViewById(R.id.loginPagebtn);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(Options.this,LoginUser.class);
                startActivity(x);
            }
        });
    }

    /**
     * it transfers the user form the option's page to the RegisterUser page
     */
    public void registerPage(){
        but2 = (Button) findViewById(R.id.registerbtn);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(Options.this,RegisterUser.class);
                startActivity(x);
            }
        });
    }

    /**
     * it will direct the user to the About OrFriend page on the OrFriend's website
     */
    public void aboutOfriendPage(){

        but3 = (Button) findViewById(R.id.abtorfbtn);
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browse = new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://172.20.5.126/wordpress2"));
                startActivity(browse);
            }
        });
    }

    /** it is used to start an activity
     *
     * @param savedInstanceState it saves the state of the activity and when it
     *                           needs to be recreated, it can be passed back to to onCreate
     *  in this case, it can activate the login page button, the register page button and the
     *                           about OrFriend page button
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        loginPage();
        registerPage();
        aboutOfriendPage();


    }
}
