package com.orfriend.orfriend.frontend.userPage;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

import com.orfriend.orfriend.R;
import com.orfriend.orfriend.SessionManager;
import com.orfriend.orfriend.backend.m_Mysql.User;
import com.orfriend.orfriend.backend.MySQLClient;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.orfriend.orfriend.SessionManager.KEY_USER_ID;

/**
 * csis 330 project
 * project name: OrFriend
 * ProfilePage.java
 * purpose: the user's profile page. its for the admin to keep check with the user.
 * @author basmatebe
 * @version 1.0 27/11/2016
 */
public class ProfilePage extends AppCompatActivity {

    /**
     * represents the back button
     */
    public ImageButton but2;
    ListView lv;
    Button editbtn;
    AlertDialog.Builder builder;



    /**
     * it lets the user to go back to the previous page
     */
    SessionManager session;
    MySQLClient x;


    public void goBack() {
        but2 = (ImageButton) findViewById(R.id.backbtn6);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(ProfilePage.this, UserHomePage.class);
                startActivity(x);
            }
        });

    }
    public void dialogview(){
        //context = this;
        //lsv = new ListView();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("select child");
        alertDialog.setMessage("soon to be list");
        alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        //cancels
        alertDialog.setNegativeButton("cancel", null);
        AlertDialog ald = alertDialog.create();
        ald.show();

    }
    public void promptInput(String name, final String colname){
        LayoutInflater li = LayoutInflater.from(ProfilePage.this);
        View promptsView = li.inflate(R.layout.promptwindow, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(ProfilePage.this);
        alertDialogBuilder.setView(promptsView);
        final EditText userInput = (EditText) promptsView.findViewById(R.id.editTextDialogUserInput);
        alertDialogBuilder
                .setCancelable(false)
                .setPositiveButton("OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                // get user input and set it to result
                                // edit text
                               // result.setText(userInput.getText());
                                x = new MySQLClient(ProfilePage.this);
                                session = new SessionManager(ProfilePage.this);
                                x.Update(ProfilePage.this,colname,userInput.getText().toString(),
                                        session.getUserDetails().get(KEY_USER_ID));
                            }
                        })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setTitle(name);
        alertDialog.show();


    }

    public void editDialog(){
        editbtn = (Button) findViewById(R.id.editbtn);
        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dialogview();
                listview();
                showDiologV();
            }
        });
    }
    Context context;
    ListView lsv;
    public void listview() {
        lsv = new ListView(this);
        String[] items = {"Age", "Hobby", "Job", "Nationality", "Status", "Email"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.profile, R.id.optiontxt, items);
        lsv.setAdapter(adapter);
        lsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ViewGroup vg = (ViewGroup) view;
                TextView txt = (TextView) vg.findViewById(R.id.optiontxt);
                //Toast.makeText(ProfilePage.this, txt.getText().toString(), Toast.LENGTH_LONG).show();
                if(txt.getText().toString().equals("Age")){
                    //Toast.makeText(ProfilePage.this, "ok1", Toast.LENGTH_LONG).show();
                    promptInput("Update age:","u_age");

                }
                if(txt.getText().toString().equals("Hobby")){
                    //Toast.makeText(ProfilePage.this, "ok2", Toast.LENGTH_LONG).show();
                    promptInput("Update Hobby:","u_hobby");
                    //x = new MySQLClient(ProfilePage.this);
                    //x.Update(this,);
                }
                if(txt.getText().toString().equals("Nationality")){
                    //Toast.makeText(ProfilePage.this, "ok3", Toast.LENGTH_LONG).show();
                    promptInput("Update Nationality:","u_nationality");
                }
                if(txt.getText().toString().equals("Status")){
                    //Toast.makeText(ProfilePage.this, "ok4", Toast.LENGTH_LONG).show();
                    promptInput("Update Status:","u_status");
                }
                if(txt.getText().toString().equals("Job")){
                    //Toast.makeText(ProfilePage.this, "ok4", Toast.LENGTH_LONG).show();
                    promptInput("Update Job:","u_job");
                }
                if(txt.getText().toString().equals("Email")){
                    //Toast.makeText(ProfilePage.this, "ok4", Toast.LENGTH_LONG).show();
                    promptInput("Update Email:","u_email");
                }

            }
        });
        //builder.show().dismiss();


    }
    public void showDiologV(){
        builder = new AlertDialog.Builder(this);
        builder.setTitle("Update:");
        builder.setCancelable(true);
        builder.setPositiveButton("dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                x = new MySQLClient(ProfilePage.this);
                x.selectProfile("clients", session.getUserDetails().get(KEY_USER_ID), ProfilePage.this);
                dialogInterface.dismiss();
            }
        });;
        builder.setView(lsv);
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    /** it is used to start an activity
     *
     * @param savedInstanceState it saves the state of the activity and when it
     *                           needs to be recreated, it can be passed back to to onCreate
     *  in this case, it activates the back button button and goBack button.
     */
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
        goBack();

        session = new SessionManager(ProfilePage.this);
        session.checkLogin();
        x = new MySQLClient(ProfilePage.this);
        x.selectProfile("clients", session.getUserDetails().get(KEY_USER_ID), this);
        editDialog();



    }


}
