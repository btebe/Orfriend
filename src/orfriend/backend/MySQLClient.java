package com.orfriend.orfriend.backend;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.widget.ListView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.orfriend.orfriend.SessionManager;
import com.orfriend.orfriend.backend.m_Mysql.Bridge;
import com.orfriend.orfriend.backend.m_Mysql.RandomBridge;
import com.orfriend.orfriend.backend.m_Mysql.User;
import com.orfriend.orfriend.frontend.userPage.UserHomePage;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * Created by basmatebe on 1/2/17.
 */

public class MySQLClient {

    SessionManager session;


    //SAVE/RETRIEVE URLS
    //private static final String DATA_INSERT_URL="http://192.168.0.7/dbRandA/CRUD2.php";
    private static final String DATA_RETRIEVE_URL="http://172.20.5.126/dbRandA/index1.php";
    private static final String DATA_INSERT2_URL="http://172.20.5.126/dbRandA/index2.php";
    private static final String DATA_INSERT_URL="http://172.20.5.126/dbRandA/CRUD3.php";

    //INSTANCE FIELDS(important)
    private final Context c;
    private DbAdapter adapter ;
    private Bridge adapter2;


    public MySQLClient(Context c) {
        this.c = c;

    }


    /*
    RETRIEVE/SELECT/REFRESH
     */
    public void retrieve(final ListView lv, ArrayList<Children> items, String id, String type, String col)
    {   //perhaps initiate arraylist outside this method
        final ArrayList<Children> children = items;

        AndroidNetworking.post(DATA_INSERT2_URL)
                .addBodyParameter("action", "retrieve")
                .addBodyParameter("id", id)
                .addBodyParameter("type", type)
                .addBodyParameter("col", col)
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

                                s=new Children();
                                s.setId(id);
                                s.setName(name);
                                s.setAge(age);
                                s.setHobby(hobby);
                                s.setImageUrl(imgURL);

                                children.add(s);
                            }

                            //pass children array to the adapter class
                            adapter =new DbAdapter(c,children);
                            lv.setAdapter(adapter);


                        }catch (JSONException e)
                        {
                            Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIEVED. "+e.getMessage(), Toast.LENGTH_LONG).show();

                        }

                    }

                    //ERROR
                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_LONG).show();

                    }


                });
    }
    public void retrieveChildrenList(final ListView lv, ArrayList<Children> items, String id)
    {   //perhaps initiate arraylist outside this method
        final ArrayList<Children> children = items;

        AndroidNetworking.post(DATA_INSERT2_URL)
                .addBodyParameter("action", "childrenList")
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

                                s=new Children();
                                s.setId(id);
                                s.setName(name);
                                s.setAge(age);
                                s.setHobby(hobby);
                                s.setImageUrl(imgURL);

                                children.add(s);
                            }

                            //pass children array to the adapter class
                            adapter =new DbAdapter(c,children);
                            lv.setAdapter(adapter);


                        }catch (JSONException e)
                        {
                            Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIEVED. "+e.getMessage(), Toast.LENGTH_LONG).show();

                        }

                    }

                    //ERROR
                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_LONG).show();

                    }


                });
    }
    public void retrieveFriendList(final ListView lv, ArrayList<Children> items, String id)
    {   //perhaps initiate arraylist outside this method
        final ArrayList<Children> children = items;

        AndroidNetworking.post(DATA_INSERT_URL)
                .addBodyParameter("action", "list")
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

                                s=new Children();
                                s.setId(id);
                                s.setName(name);
                                s.setAge(age);
                                s.setHobby(hobby);
                                s.setImageUrl(imgURL);

                                children.add(s);
                            }

                            //pass children array to the adapter class
                            adapter =new DbAdapter(c,children);
                            lv.setAdapter(adapter);


                        }catch (JSONException e)
                        {
                            Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIEVED. "+e.getMessage(), Toast.LENGTH_LONG).show();

                        }

                    }

                    //ERROR
                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_LONG).show();

                    }


                });
    }






    RandomBridge r;
    public void retrieveToExclude (final ListView lv, ArrayList<Children> items, final Activity x){
        final ArrayList<Children> children = items;
        AndroidNetworking.get(DATA_RETRIEVE_URL)
                .setPriority(Priority.HIGH)
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

                                s=new Children();
                                s.setId(id);
                                s.setName(name);
                                s.setAge(age);
                                s.setHobby(hobby);
                                s.setImageUrl(imgURL);

                                children.add(s);
                            }

                            Toast.makeText(c, "GOOD", Toast.LENGTH_LONG).show();
                            r =new RandomBridge(c,children,x);
                            r.dialog("MyChildrenList");
                            //lv.setAdapter(adapter);


                        }catch (JSONException e)
                        {
                            Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIEVED. "+e.getMessage(), Toast.LENGTH_LONG).show();

                        }

                    }

                    //ERROR
                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_LONG).show();

                    }


                });

    }


    private static final String DATA_RETRIEVE2_URL="http://172.20.5.126/dbRandA/randfriend.php";
    RandomBridge rf;
    public void randomFriendList (final ListView lv, final Activity x){
        final ArrayList<Children> children = new ArrayList<>();
        AndroidNetworking.get(DATA_RETRIEVE2_URL)
                .setPriority(Priority.HIGH)
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
                                s.setImageUrl(imgURL);
                                s.setOrphanageName(location);

                                children.add(s);
                            }
                            //Toast.makeText(c, children.get(0).getOrphanageName(), Toast.LENGTH_LONG).show();
                            rf =new RandomBridge(c,children,x);
                            rf.dialog("friendList");



                        }catch (JSONException e)
                        {
                            Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIEVED. "+e.getMessage(), Toast.LENGTH_LONG).show();

                        }

                    }

                    //ERROR
                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_LONG).show();

                    }


                });

    }






    public void add(Children s, String typeList, String uid)
    {
        if(s==null)
        {
            Toast.makeText(c, "No Data To Save", Toast.LENGTH_SHORT).show();
        }
        else
        {
            AndroidNetworking.post(DATA_INSERT2_URL)

                    .addBodyParameter("action","save")
                    .addBodyParameter("id",String.valueOf(s.getId()))
                    .addBodyParameter("listType",typeList)
                    .addBodyParameter("uid",uid)
                    .setTag("TAG_ADD")
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {

                            if(response != null)
                                try {
                                    //SHOW RESPONSE FROM SERVER
                                    String responseString = response.get(0).toString();
                                    Toast.makeText(c, "PHP SERVER RESPONSE : " + responseString, Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIVED : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                        }

                        //ERROR
                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    });

        }
    }

    public void login(final String username, String password, ArrayList<User> users)
    {
        final ArrayList<User> user = users;
        if(username==null)
        {
            Toast.makeText(c, "No Data To Save", Toast.LENGTH_SHORT).show();
        }
        else
        {
            AndroidNetworking.post(DATA_INSERT2_URL)

                    .addBodyParameter("action","login")
                    .addBodyParameter("username",username)
                    .addBodyParameter("password",password)
                    .setTag("TAG_ADD")
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            JSONObject jo;
                            User s;
                            if(response != null)
                                try {
                                    //SHOW RESPONSE FROM SERVER
                                    String responseString = response.get(0).toString();
                                    Toast.makeText(c, "PHP SERVER RESPONSE : " + responseString, Toast.LENGTH_SHORT).show();

                                    for(int i=0;i<response.length();i++)
                                    {
                                        jo=response.getJSONObject(i);

                                        int id=jo.getInt("C_ID");
                                        s=new User();
                                        s.setId(id);
                                        user.add(s);
                                    }

                                    session = new SessionManager(c);
                                    session.createLoginSessionUser(String.valueOf(user.get(0).getId()));
                                    //Toast.makeText(c, String.valueOf(user.get(0).getId()), Toast.LENGTH_SHORT).show();
                                    Intent n = new Intent(c, UserHomePage.class);
                                    c.startActivity(n);



                                } catch (JSONException e) {
                                    e.printStackTrace();

                                    Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIVED : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        }



                        //ERROR
                        @Override
                        public void onError(ANError anError) {
                            anError.printStackTrace();

                            Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_LONG).show();

                        }


                    });

        }

    }

    public void selectProfile(String tablename, String id, Activity x)
    {
        final ArrayList<User> users = new ArrayList<>();
        final Activity ac = x;
        if(tablename==null)
        {
            Toast.makeText(c, "No Data To Save", Toast.LENGTH_SHORT).show();
        }
        else
        {
            AndroidNetworking.post(DATA_INSERT2_URL)

                    .addBodyParameter("action","profile")
                    .addBodyParameter("id",String.valueOf(id))
                    .addBodyParameter("listType",tablename)
                    .addBodyParameter("col","C_ID")
                    .setTag("TAG_ADD")
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                        @Override
                        public void onResponse(JSONArray response) {
                            JSONObject jo;;
                            User s;

                            if(response != null)
                                try {
                                    //SHOW RESPONSE FROM SERVER
                                    //String responseString = response.get(0).toString();
                                    //Toast.makeText(c, "PHP SERVER RESPONSE : " + responseString, Toast.LENGTH_SHORT).show();


                                    for(int i=0;i<response.length();i++) {
                                        jo = response.getJSONObject(i);

                                        int id = jo.getInt("C_ID");
                                        int age = jo.getInt("u_age");
                                        String name = jo.getString("C_NAME");
                                        String lname = jo.getString("C_LNAME");
                                        String email = jo.getString("u_email");
                                        String job= jo.getString("u_job");
                                        String nationality = jo.getString("u_nationality");
                                        String status = jo.getString("u_status");
                                        String hobby = jo.getString("u_hobby");
                                        String pic = jo.getString("u_pic");

                                        s = new User();
                                        s.setId(id);
                                        s.setLname(lname);
                                        s.setAge(age);
                                        s.setEmail(email);
                                        s.setHobby(hobby);
                                        s.setNationality(nationality);
                                        s.setPic(pic);
                                        s.setJob(job);
                                        s.setStatus(status);

                                        s.setUsernameOrEmail(name);


                                        users.add(s);
                                    }

                                    Bridge b = new Bridge(c, users, ac);
                                    b.showPic();






                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIVED : "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                }

                        }

                        //ERROR
                        @Override
                        public void onError(ANError anError) {
                            Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : "+anError.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    });

        }


    }

    public void Update(Activity x, String colname, String value, String id){

        if(value.isEmpty())
        {
            Toast.makeText(c, "Enter data please", Toast.LENGTH_SHORT).show();
        }else {


            AndroidNetworking.post(DATA_INSERT2_URL)

                    .addBodyParameter("action", "update")
                    .addBodyParameter("colname", colname)
                    .addBodyParameter("value", value)
                    .addBodyParameter("id", id)
                    .setTag("TAG_ADD")
                    .build()
                    .getAsJSONArray(new JSONArrayRequestListener() {
                        @Override
                        public void onResponse(JSONArray response) {
                            JSONObject jo;
                            User s;
                            if (response != null)
                                try {
                                    //SHOW RESPONSE FROM SERVER
                                    String responseString = response.get(0).toString();
                                    Toast.makeText(c, "PHP SERVER RESPONSE : " + responseString, Toast.LENGTH_SHORT).show();


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(c, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIVED : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        }


                        //ERROR
                        @Override
                        public void onError(ANError anError) {
                            anError.printStackTrace();
                            Toast.makeText(c, "UNSUCCESSFUL :  ERROR IS : " + anError.getMessage(), Toast.LENGTH_LONG).show();

                        }


                    });


        }



    }




}
