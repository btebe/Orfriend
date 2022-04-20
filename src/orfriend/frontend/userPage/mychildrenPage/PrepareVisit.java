package com.orfriend.orfriend.frontend.userPage.mychildrenPage;

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
import com.orfriend.orfriend.frontend.userPage.UserHomePage;

import static com.orfriend.orfriend.SessionManager.KEY_CHILD_NAME;
import static com.orfriend.orfriend.SessionManager.KEY_CHILD_PIC;


public class PrepareVisit extends AppCompatActivity {

    private Button b;
    private EditText date;
    private EditText time;
    private ImageButton but1;
    private ImageButton but2;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prepare_visit);
        session = new SessionManager(PrepareVisit.this);
        session.checkLogin();
        b=(Button) findViewById(R.id.button5);
        date=(EditText) findViewById(R.id.date);
        time=(EditText) findViewById(R.id.time);

/**if((date.getText().toString().isEmpty() )&&(time.getText().toString().isEmpty() )){
 b.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
Toast.makeText(PrepareAVisit.this, "Please Enter Date and Time", Toast.LENGTH_LONG).show();

}
});
 }else if(time.getText().toString().isEmpty() ){
 b.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
Toast.makeText(PrepareAVisit.this, "Please Enter Time", Toast.LENGTH_LONG).show();

}
});
 }else if(date.getText().toString().isEmpty() ){
 b.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View view) {
Toast.makeText(PrepareAVisit.this, "Please Enter Date", Toast.LENGTH_LONG).show();

}
});
 }else{**/
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PrepareVisit.this, "Request Sent", Toast.LENGTH_LONG).show();
                Intent x = new Intent(PrepareVisit.this,KidMenu.class);
                startActivity(x);
            }
        });
        //}
        home();
        goBack();

    }
    public void home(){
        but1 = (ImageButton) findViewById(R.id.home4);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(PrepareVisit.this,UserHomePage.class);
                startActivity(x);
            }
        });
    }

    public void goBack(){

        but2 = (ImageButton) findViewById(R.id.back4);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent x = new Intent(PrepareVisit.this,KidMenu.class);
                x.putExtra("pic", session.getChildDetails().get(KEY_CHILD_PIC));
                x.putExtra("name",  session.getChildDetails().get(KEY_CHILD_NAME));
                startActivity(x);
            }
        });
    }
}
