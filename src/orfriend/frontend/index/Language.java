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
 * Language.java
 * purpose: to let the user choose between two languages English and Arabic
 * @author basmatebe
 * @version 1.0 27/11/2016
 */
public class Language extends AppCompatActivity {

    /**
     *  represent the English option button
     */
    public Button btn1;

    /**
     * it transfers the user from the Language page to the Options page
     * everything in that page will be in english
     */
    public void engOption(){
        btn1 = (Button) findViewById(R.id.engbtn);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(Language.this,Options.class);
                startActivity(x);
            }
        });
    }

    /** it is used to start an activity
     *
     * @param savedInstanceState it saves the state of the activity and when it
     *                           needs to be recreated, it can be passed back to to onCreate
     *  in this case, it can activate the English option button button
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        engOption();
    }
}
