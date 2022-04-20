package com.orfriend.orfriend.frontend.userPage.mychildrenPage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.orfriend.orfriend.R;
import com.orfriend.orfriend.SessionManager;
import com.orfriend.orfriend.backend.Children;
import com.orfriend.orfriend.backend.DbAdapter;
import com.orfriend.orfriend.frontend.userPage.UserHomePage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.orfriend.orfriend.SessionManager.KEY_CHILD_ID;
import static com.orfriend.orfriend.SessionManager.KEY_CHILD_NAME;
import static com.orfriend.orfriend.SessionManager.KEY_CHILD_PIC;

public class ChildInfo extends AppCompatActivity {

    ImageButton but1;
    ImageButton but2;
    private static final String DATA_INSERT2_URL="http://172.20.5.126/dbRandA/index2.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //can be more than one layout in one layout
        setContentView(R.layout.activity_child_info);
        home();
        goBack();
        retrieveInfo(session.getChildDetails().get(KEY_CHILD_ID));
    }
    /**
     * it directs the user back to the user's home page
     */
    public void home(){
        but1 = (ImageButton) findViewById(R.id.home4);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(ChildInfo.this,UserHomePage.class);
                startActivity(x);
            }
        });
    }
    TextView name;
    TextView age;
    TextView location;
    TextView hobby;
    public void retrieveInfo(String id){
        final ArrayList<Children> children = new ArrayList<>();

        AndroidNetworking.post(DATA_INSERT2_URL)
                .addBodyParameter("action", "retrieveChild")
                .addBodyParameter("id", id)
                .setTag("TAG_ADD")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jo;
                        Children s;
                        try
                        {
                            for(int i=0;i<response.length();i++)
                            {
                                jo=response.getJSONObject(i);

                                int id=jo.getInt("CH_ID");
                                String name=jo.getString("CH_NAME");
                                int age=jo.getInt("CH_AGE");
                                String hobby=jo.getString("CH_HOBBY");
                                String imgURL=jo.getString("IMAGE_URL");
                                String location = jo.getString("O_NAME");

                                s=new Children();
                                s.setId(id);
                                s.setName(name);
                                s.setAge(age);
                                s.setHobby(hobby);
                                s.setOrphanageName(location);
                                s.setImageUrl(imgURL);

                                children.add(s);
                            }

                            name = (TextView) findViewById(R.id.infoname);
                            age = (TextView) findViewById(R.id.infoage);
                            location = (TextView) findViewById(R.id.infolocation);
                            hobby = (TextView) findViewById(R.id.infohobby);
                            Children x = children.get(0);
                            name.setText("Name:  "+x.getName());
                            age.setText("Age:  "+x.getAge());
                            location.setText("location:  "+x.getOrphanageName());
                            hobby.setText("hobby:  "+x.getHobby());




                        }catch (JSONException e)
                        {
                            Toast.makeText(ChildInfo.this, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIEVED. "+e.getMessage(), Toast.LENGTH_LONG).show();

                        }

                    }

                    //ERROR
                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Toast.makeText(ChildInfo.this, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_LONG).show();

                    }


                });
    }
    SessionManager session;

    /**
     * it lets the user to go back to the previous page
     */
    public void goBack(){
        session = new SessionManager(ChildInfo.this);
        session.checkLogin();
        but2 = (ImageButton) findViewById(R.id.back4);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x = new Intent(ChildInfo.this,KidMenu.class);
                x.putExtra("pic", session.getChildDetails().get(KEY_CHILD_PIC));
                x.putExtra("name",  session.getChildDetails().get(KEY_CHILD_NAME));
                startActivity(x);
            }
        });
    }
}
