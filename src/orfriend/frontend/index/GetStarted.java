package com.orfriend.orfriend.frontend.index;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.orfriend.orfriend.R;

/**
 * csis 330 project
 * project name: OrFriend
 * GetStarted.java
 * purpose: to display the company's logo and go to the next page
 * @author basmatebe
 * @version 1.0 27/11/2016
 */

public class GetStarted extends AppCompatActivity {
    /**
     * represents the GetStarted button
     */
    public Button btn1;

    /**
     * transfers the user from the GetStarted Page to the Language page
     */
    public void getStarted(){
        btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(GetStarted.this,Language.class);
                startActivity(x);
            }
        });
    }

    /** it is used to start an activity
     *
     * @param savedInstanceState it saves the state of the activity and when it
     *                           needs to be recreated, it can be passed back to to onCreate
     *  in this case, it activates the getStrated button
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        getStarted();
    }
}
