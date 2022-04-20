package com.orfriend.orfriend.frontend.index;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.orfriend.orfriend.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * csis 330 project
 * project name: OrFriend
 * RegisterUser.java
 * purpose: to let the user know the process of registration
 * @author basmatebe
 * @version 1.0 27/11/2016
 */
public class RegisterUser extends AppCompatActivity {
    /**
     * represents the back button
     */
    ImageButton btn1;
    /**
     * represents the register button
     */
    Button btn2;
    /**
     * the text box where the text will be displayed
     */
    TextView tv;
    /**
     * constructs a string builder with no characters
     */
    private StringBuilder text = new StringBuilder();


    /**
     * it lets the user to go back to the previous page
     */
    public void goBack(){
        btn1 = (ImageButton) findViewById(R.id.backbtn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(RegisterUser.this,Options.class);
                startActivity(x);
            }
        });
    }

    /**
     * it directs the user to the application page on the OrFirend's website
     */
    public void webApplication(){
        btn2 = (Button) findViewById(R.id.register_btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse("http://172.20.5.126/wordpress2/index.php/orfriend/application-form/"));
                startActivity(browse);
            }
        });

    }



    /** it is used to start an activity
     *
     * @param savedInstanceState it saves the state of the activity and when it
     *                           needs to be recreated, it can be passed back to to onCreate
     *  in this case, it can activate the go back button, the application button and it activates the
     *                           loading of the text to be displayed in the text box.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        goBack();
        webApplication();


        tv = (TextView) findViewById(R.id.tv_process);

        try {
            loadtext();
        } catch (IOException e) {
            e.printStackTrace();
        }




    }

    /**
     * allows the text to be displayed in the text box
     * @throws IOException if a file cannot be read or the about the file name being incorrect
     */

    public void loadtext() throws IOException {


        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("registration.txt")));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                text.append(mLine);
                text.append('\n');
            }
        } catch (IOException e) {
            Toast.makeText(getApplicationContext(), "Error reading file!", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println (e.toString());
                }
            }
            tv.setMovementMethod(new ScrollingMovementMethod());
            tv.setText(text);

        }
    }
}






