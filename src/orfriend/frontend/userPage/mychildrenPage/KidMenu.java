package com.orfriend.orfriend.frontend.userPage.mychildrenPage;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.orfriend.orfriend.R;
import com.orfriend.orfriend.SessionManager;
import com.orfriend.orfriend.frontend.userPage.UserHomePage;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class KidMenu extends AppCompatActivity {

    /**
     * represents the home button
     */
    public ImageButton but1;

    /**
     * represents the back button
     */
    public ImageButton but2;
    /**
     * information
     */
    public ImageButton but3;
    /**
     * story telling
     */

    public ImageButton but4;
    /**
     * Donate
     */
    public ImageButton but5;
    /**
     * play with me
     */
    public ImageButton but6;
    /**
     * letsTalk
     */
    public ImageButton but7;
    /**
     * prepare a visit
     */
    public ImageButton but8;

    /**
     * it directs the user back to the user's home page
     */
    public void home(){
        but1 = (ImageButton) findViewById(R.id.homekidbtn);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(KidMenu.this,UserHomePage.class);
                startActivity(x);
            }
        });
    }

    /**
     * it lets the user to go back to the previous page
     */
    public void goBack(){
        but2 = (ImageButton) findViewById(R.id.backbtn4);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(KidMenu.this,MyChildren.class);
                startActivity(x);
            }
        });
    }
    public void letsTalk(){
        but2 = (ImageButton) findViewById(R.id.imageButton11);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent x = new Intent(KidMenu.this,Call.class);
                x.putExtra("page", "kidmenu");
                startActivity(x);
            }
        });
    }
    public void playWithMe(){
        but6 = (ImageButton) findViewById(R.id.imageButton10);
        but6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(KidMenu.this,PlayWithMe.class);
                startActivity(x);
            }
        });
    }
    public void donate(){
        but5 = (ImageButton) findViewById(R.id.imageButton8);
        but5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.paypal.com/us/webapps/mpp/fundraising"));
                startActivity(browserIntent);
            }
        });
    }
    public void storyTelling(){
        but4 = (ImageButton) findViewById(R.id.imageButton9);
        but4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent x = new Intent(KidMenu.this,StoryTelling.class);
                startActivity(x);
            }
        });
    }
    public void visit(){
        but4 = (ImageButton) findViewById(R.id.imageButton12);
        but4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent x = new Intent(KidMenu.this,PrepareVisit.class);
                startActivity(x);
            }
        });
    }
    public void info(){
        but4 = (ImageButton) findViewById(R.id.imageButton6);
        but4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent x = new Intent(KidMenu.this,ChildInfo.class);
                startActivity(x);
            }
        });
    }
    CircleImageView kidpic;
    TextView kidname;
    public void retrievepicAndName(){
        String pic= getIntent().getStringExtra("pic");
        String name= getIntent().getStringExtra("name");
        kidpic = (CircleImageView) findViewById(R.id.kidpic);
        kidname = (TextView) findViewById(R.id.kidname);
        Picasso.with(this).load(pic).placeholder(R.mipmap.ic_launcher).into(kidpic);
        kidname.setText(name);
    }
    /** it is used to start an activity
     *
     * @param savedInstanceState it saves the state of the activity and when it
     *                           needs to be recreated, it can be passed back to to onCreate
     *  in this case, it can  activate the home button and goBack button.
     */
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kid_menu);
        session = new SessionManager(KidMenu.this);
        session.checkLogin();
        home();
        goBack();
        letsTalk();
        playWithMe();
        donate();
        storyTelling();
        visit();
        info();
        retrievepicAndName();

    }
}
