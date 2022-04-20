package com.orfriend.orfriend.frontend.index;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.orfriend.orfriend.R;
import com.orfriend.orfriend.SessionManager;
import com.orfriend.orfriend.backend.m_Mysql.User;
import com.orfriend.orfriend.backend.MySQLClient;
import com.orfriend.orfriend.frontend.userPage.UserHomePage;

import java.util.ArrayList;

/**
 * csis 330 project
 * project name: OrFriend
 * LoginUser.java
 * purpose: Its where the user logs in
 * @author basmatebe
 * @version 1.0 27/11/2016
 */
public class LoginUser extends AppCompatActivity {

    /**
     * represents the actual login button
     */
    public Button but1;
    /**
     * represents the back button
     */
    public ImageButton but2;

    final static String urlAddress="http://192.168.0.7/con_db.php";
    EditText usernameTxt,passwordTxt;
    SessionManager session;
    private MySQLClient x;

    /**
     * main purpose:It transfers the user from the login page to the user's account
     * for now: it transfers the user to the user page
     */
    public void login(){
        but1 = (Button) findViewById(R.id.loginbtn);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ///Intent x = new Intent(LoginUser.this,UserHomePage.class);
                //startActivity(x);
                String usernameOrEmail=usernameTxt.getText().toString();
                String password=passwordTxt.getText().toString();

                //BASIC VALIDATION
                if((usernameOrEmail.length()<=0 || usernameOrEmail==null) || (password.length()<=0 || password==null))
                {
                    Toast.makeText(LoginUser.this, "Please fill all Fields", Toast.LENGTH_SHORT).show();
                }else {
                    //new LoginHelper(LoginUser.this,urlAddress,usernameTxt,passwordTxt).execute();
                    final ArrayList<User> user = new ArrayList<>();
                    x = new MySQLClient(LoginUser.this);
                    x.login(usernameOrEmail,password, user);
                    if (!user.isEmpty()){
                        session = new SessionManager(LoginUser.this);
                        session.createLoginSessionUser(String.valueOf(user.get(0).getId()));
                        Intent n = new Intent(LoginUser.this,UserHomePage.class);
                        startActivity(n);
                    }
                    //session.createLoginSessionUser(usernameOrEmail);
                    //Toast.makeText(LoginUser.this, session.getUserDetails().get(KEY_USER_ID), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(LoginUser.this, "hello1", Toast.LENGTH_SHORT).show();
                    /*Intent n = new Intent(LoginUser.this,UserHomePage.class);
                     startActivity(n);
                     finish();*/

                }
            }
        });
    }
    /**
     * it lets the user to go back to the previous page
     */
    public void goBack(){
        but2 = (ImageButton) findViewById(R.id.backbtn2);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(LoginUser.this,Options.class);
                startActivity(x);
            }
        });
    }
    /** it is used to start an activity
     *
     * @param savedInstanceState it saves the state of the activity and when it
     *                           needs to be recreated, it can be passed back to to onCreate
     *  in this case, it activates the login button button
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);
        login();
        goBack();
        usernameTxt= (EditText) findViewById(R.id.usernametxt);
        passwordTxt= (EditText) findViewById(R.id.passwordtxt);
    }

}
