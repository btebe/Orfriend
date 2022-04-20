package com.orfriend.orfriend.frontend.userPage.mychildrenPage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;


import com.orfriend.orfriend.R;
import com.orfriend.orfriend.SessionManager;
import com.orfriend.orfriend.backend.Children;
import com.orfriend.orfriend.backend.MySQLClient;
import com.orfriend.orfriend.frontend.userPage.UserHomePage;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.orfriend.orfriend.SessionManager.KEY_CHILD_ID;
import static com.orfriend.orfriend.SessionManager.KEY_CHILD_NAME;
import static com.orfriend.orfriend.SessionManager.KEY_CHILD_PIC;
import static com.orfriend.orfriend.SessionManager.KEY_USER_ID;

/**
 * csis 330 project
 * project name: OrFriend
 * MyChildren.java
 * purpose: the list of children the user is going to adopt
 * @author basmatebe
 * @version 1.0 27/11/2016
 */
public class MyChildren extends AppCompatActivity {

    /**
     * represents the kid number one button
     */
    public CircleImageView btn;
    /**
     * represents the back button
     */
    public ImageButton but2;
    private ListView mListView;
    private MySQLClient X;
    SessionManager session;



    /**
     * it lets the user to go back to the previous page
     */
    public void goBack(){
        but2 = (ImageButton) findViewById(R.id.backbtn3);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(MyChildren.this,UserHomePage.class);
                startActivity(x);
            }
        });
    }
    /** it is used to start an activity
     *
     * @param savedInstanceState it saves the state of the activity and when it
     *                           needs to be recreated, it can be passed back to to onCreate
     *  in this case, it can activate the kidmenu1 button and goBack button.
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_children);

        goBack();
        mListView = (ListView) findViewById(R.id.recipe_list_view);
        final ArrayList<Children> children = new ArrayList<>();
        session = new SessionManager(MyChildren.this);
        session.checkLogin();
        X = new MySQLClient(MyChildren.this);
        String table = "MyChildrenList";
        X.retrieveChildrenList(mListView, children,session.getUserDetails().get(KEY_USER_ID));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {


                //doesntwork
                Children selectedChild = children.get(position);
                session.createChildIdHolder(String.valueOf(selectedChild.getId()),
                        selectedChild.getName(),selectedChild.getImageUrl());
                Toast.makeText(MyChildren.this, session.getChildDetails().get(KEY_CHILD_ID), Toast.LENGTH_SHORT).show();
                Intent n = new Intent(MyChildren.this,KidMenu.class);
                n.putExtra("pic", session.getChildDetails().get(KEY_CHILD_PIC));
                n.putExtra("name",  session.getChildDetails().get(KEY_CHILD_NAME));
                //session.getChildDetails().get(KEY_CHILD_ID)
                startActivity(n);

            }
        });
    }
}
