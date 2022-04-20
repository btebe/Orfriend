package com.orfriend.orfriend.frontend.userPage.adoptPage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.orfriend.orfriend.R;
import com.orfriend.orfriend.SessionManager;
import com.orfriend.orfriend.backend.Children;
import com.orfriend.orfriend.backend.MySQLClient;
import com.orfriend.orfriend.frontend.userPage.ProfilePage;
import com.orfriend.orfriend.frontend.userPage.UserHomePage;

import java.util.ArrayList;
import java.util.Random;

import static android.R.id.list;
import static com.orfriend.orfriend.SessionManager.KEY_USER_ID;

/**
 * csis 330 project
 * project name: OrFriend
 * AdoptPage.java
 * purpose: to choose options to do with the child
 * @author basmatebe
 * @version 1.0 27/11/2016
 */

public class AdoptPage extends AppCompatActivity {

    /**
     * represents the home button
     */
    public ImageButton but1;
    /**
     * represents the choose child button
     */
    public Button but2;

    /**
     * it directs the user back to the user's home page
     */
    public Button randomchild;
    public void home(){
        but1 = (ImageButton) findViewById(R.id.homebtn2);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(AdoptPage.this,UserHomePage.class);
                startActivity(x);
            }
        });
    }

    /**
     * it lets the user to the choose child page
     */
    public void chooseChild(){
        but2 = (Button) findViewById(R.id.choosebtn2);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(AdoptPage.this,ChooseChild.class);
                startActivity(x);
            }
        });
    }

    ListView lv;
    MySQLClient x;
    public void randomChild(){
        randomchild = (Button) findViewById(R.id.randomchild);
        final ArrayList<Children> children = new ArrayList<>();

        randomchild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialog();
                x = new MySQLClient(AdoptPage.this);
                x.retrieveToExclude(lv, children, AdoptPage.this);

                //lv.getAdapter().getItem(new Random().nextInt(lv.getCount()));

            }
        });
    }
    public void dialog(){

    }
    SessionManager session;
    /** it is used to start an activity
     *
     * @param savedInstanceState it saves the state of the activity and when it
     *                           needs to be recreated, it can be passed back to to onCreate
     *  in this case, it can  activate the home button, goBack button and choosechild button.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt_page);
        session = new SessionManager(AdoptPage.this);
        session.checkLogin();
        home();
        chooseChild();
        randomChild();
    }
}
