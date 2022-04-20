package com.orfriend.orfriend.frontend.userPage.befriendPage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.orfriend.orfriend.R;
import com.orfriend.orfriend.SessionManager;
import com.orfriend.orfriend.backend.MySQLClient;
import com.orfriend.orfriend.frontend.userPage.UserHomePage;

/**
 * csis 330 project
 * project name: OrFriend
 * BeFriendPage.java
 * purpose: to find a friend either by selection or at random
 * @author basmatebe
 * @version 1.0 27/11/2016
 */
public class BeFriendPage extends AppCompatActivity {
    /**
     * represents the back button
     */
    public ImageButton but1;

    /**
     * it directs the user back to the user's home page
     */
    public void home(){
        but1 = (ImageButton) findViewById(R.id.backbtn6);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(BeFriendPage.this,UserHomePage.class);
                startActivity(x);
            }
        });
    }
    public Button friendlistbtn;
    public void friendlist(){
        friendlistbtn = (Button) findViewById(R.id.frienlistbtn);
        friendlistbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(BeFriendPage.this,FriendList.class);
                startActivity(x);
            }
        });

    }




    public Button randombtn;
    ListView lv;
    MySQLClient x;
    public void randFriend(){
        randombtn = (Button) findViewById(R.id.randombtn);
        randombtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog();
                x = new MySQLClient(BeFriendPage.this);
                x.randomFriendList(lv, BeFriendPage.this);


                //lv.getAdapter().getItem(new Random().nextInt(lv.getCount()));

            }
        });
    }

    /** it is used to start an activity
     *
     * @param savedInstanceState it saves the state of the activity and when it
     *                           needs to be recreated, it can be passed back to to onCreate
     *  in this case, it activates the back button button and goBack button.
     */
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_be_friend_page);
        session = new SessionManager(BeFriendPage.this);
        session.checkLogin();
        home();
        randFriend();
        friendlist();
    }
}
