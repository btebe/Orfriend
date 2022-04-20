package com.orfriend.orfriend.frontend.userPage.mychildrenPage;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.orfriend.orfriend.R;
import com.orfriend.orfriend.SessionManager;
import com.orfriend.orfriend.frontend.userPage.UserHomePage;
import com.orfriend.orfriend.frontend.userPage.befriendPage.BeFriendPage;

import static com.orfriend.orfriend.SessionManager.KEY_CHILD_NAME;
import static com.orfriend.orfriend.SessionManager.KEY_CHILD_PIC;

public class Call extends AppCompatActivity {

    ImageButton but2;
    ImageButton but1;
    SessionManager session;

    /** @Override
    protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_call);
    }**/
    // Make sure you are sign in skype application if you not then you need to sign in
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        session = new SessionManager(Call.this);
        session.checkLogin();
        // Open skype button click event code here
        ((Button) findViewById(R.id.openskype)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String mySkypeUri = "skype:";
                SkypeUri(Call.this, mySkypeUri);
            }
        });

        // skype message button click event code here
        ((Button) findViewById(R.id.skypemsg)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String skypeName = ((EditText) findViewById(R.id.edt_skypeusername)).getText().toString().trim();
                if(skypeName.length()<=0)
                {
                    Toast.makeText(getApplicationContext(), "Please enter skype username to message", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String mySkypeUri = "skype:"+skypeName+"?chat";
                    SkypeUri(Call.this, mySkypeUri);
                }
            }
        });

        // Skype Audio call button click event code here
        ((Button) findViewById(R.id.skypecall)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String skypeName = ((EditText) findViewById(R.id.edt_skypeusername)).getText().toString().trim();
                if(skypeName.length()<=0)
                {
                    Toast.makeText(getApplicationContext(), "Please enter skype username to call", Toast.LENGTH_SHORT).show();
                }
                else {
                    String mySkypeUri = "skype:"+skypeName+"?call";
                    SkypeUri(Call.this, mySkypeUri);
                }
            }
        });

        // Skype Video call button click event code here
        ((Button) findViewById(R.id.skypevideocall)).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String skypeName = ((EditText) findViewById(R.id.edt_skypeusername)).getText().toString().trim();
                if(skypeName.length()<=0)
                {
                    Toast.makeText(getApplicationContext(), "Please enter skype username to video call", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String mySkypeUri = "skype:"+skypeName+"?call&video=true";
                    SkypeUri(Call.this, mySkypeUri);
                }
            }
        });
        home();
        String page= getIntent().getStringExtra("page");
        if(page.equals("befriendPage")){
            goBackfriendPage();
        }else{
            goBackkidmenu();
        }



    }

    public void SkypeUri(Context myContext, String mySkypeUri) {

        // Make sure the Skype for Android client is installed.
        if (!isSkypeClientInstalled(myContext)) {
            goToMarket(myContext);
            return;
        }
        Uri skypeUri = Uri.parse(mySkypeUri);
        Intent myIntent = new Intent(Intent.ACTION_VIEW, skypeUri);
        myIntent.setComponent(new ComponentName("com.skype.raider", "com.skype.raider.Main"));
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        myContext.startActivity(myIntent);

        return;
    }

    /**
     * Determine whether the Skype for Android client is installed on this device.
     */
    public boolean isSkypeClientInstalled(Context myContext) {
        PackageManager myPackageMgr = myContext.getPackageManager();
        try {
            myPackageMgr.getPackageInfo("com.skype.raider", PackageManager.GET_ACTIVITIES);
        }
        catch (PackageManager.NameNotFoundException e) {
            return (false);
        }
        return (true);
    }

    /**
     * Install the Skype client through the market: URI scheme.
     */

    public void goToMarket(Context myContext) {
        Uri marketUri = Uri.parse("market://details?id=com.skype.raider");
        Intent myIntent = new Intent(Intent.ACTION_VIEW, marketUri);
        myIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        myContext.startActivity(myIntent);
        return;
    }

    public void goBackkidmenu(){
        but2 = (ImageButton) findViewById(R.id.back4);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent x = new Intent(Call.this,KidMenu.class);
                x.putExtra("pic", session.getChildDetails().get(KEY_CHILD_PIC));
                x.putExtra("name",  session.getChildDetails().get(KEY_CHILD_NAME));
                startActivity(x);
            }
        });
    }

    public void goBackfriendPage(){
        but2 = (ImageButton) findViewById(R.id.back4);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //session = new SessionManager(Call.this);
                Intent x = new Intent(Call.this,BeFriendPage.class);
                startActivity(x);
            }
        });
    }
    public void home(){
        but1 = (ImageButton) findViewById(R.id.home4);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(Call.this,UserHomePage.class);
                startActivity(x);
            }
        });
    }
}
