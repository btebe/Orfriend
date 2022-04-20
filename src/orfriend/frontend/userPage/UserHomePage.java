package com.orfriend.orfriend.frontend.userPage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.orfriend.orfriend.R;
import com.orfriend.orfriend.SessionManager;
import com.orfriend.orfriend.frontend.index.LoginUser;
import com.orfriend.orfriend.frontend.userPage.adoptPage.AdoptPage;
import com.orfriend.orfriend.frontend.userPage.befriendPage.BeFriendPage;
import com.orfriend.orfriend.frontend.userPage.mychildrenPage.MyChildren;

/**
 * csis 330 project
 * project name: OrFriend
 * UserHomePage.java
 * purpose: the home page of the user
 * @author basmatebe
 * @version 1.0 27/11/2016
 */
public class UserHomePage extends AppCompatActivity {
    /**
     * represents the mychildren button
     */
    public ImageButton but1;
    /**
     * represents the log out button
     */
    public ImageButton but2;
    /**
     * represents the user's profile button
     */
    public ImageButton but3;
    /**
     * represents the befirend button
     */
    public ImageButton but4;
    /**
     * represents the adopt buton
     */
    public ImageButton but5;

    /**
     * it transfers the user from user's home page to the my children page
     */
    public void myChildrenList(){
        but1 = (ImageButton) findViewById(R.id.mychildernbtn);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(UserHomePage.this,MyChildren.class);
                startActivity(x);
            }
        });
    }

    /**
     * main purpose:it logs out the user for the user's account
     * for now: it transfers the user from the user's homepage to the login page
     */

    public void logout(){
        but2 = (ImageButton) findViewById(R.id.logout_btn);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                session.logoutUser();
                //Intent x = new Intent(UserHomePage.this,LoginUser.class);
                //startActivity(x);
            }
        });
    }

    /**
     * main purpose: transfers the user from the home page to the user's profile page
     * for now: transfers the user to the profile page
     */
    public void userProfilePage(){
        but3 = (ImageButton) findViewById(R.id.userProfilebtn);
        but3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(UserHomePage.this,ProfilePage.class);
                startActivity(x);
            }
        });
    }

    /**
     * main purpose: transfer the user to the adopt page
     */
    public void adopt(){
        but4 = (ImageButton) findViewById(R.id.adoptbtn);
        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(UserHomePage.this,AdoptPage.class);
                startActivity(x);
            }
        });
    }

    /**
     * it transfers the user from the home page to the befriend page
     */
    public void befriendPage(){
        but4 = (ImageButton) findViewById(R.id.befriendbtn);
        but4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(UserHomePage.this,BeFriendPage.class);
                startActivity(x);
            }
        });
    }

    /** it is used to start an activity
     *
     * @param savedInstanceState it saves the state of the activity and when it
     *                           needs to be recreated, it can be passed back to to onCreate
     *  in this case, it can activate the mychildren button,the log out button, the user profile
     *                           page button and the befriend page button
     */
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);
        session = new SessionManager(UserHomePage.this);
        session.checkLogin();
        myChildrenList();
        logout();
        userProfilePage();
        befriendPage();
        adopt();
    }
}
