package com.orfriend.orfriend.backend.Login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Toast;

import com.orfriend.orfriend.SessionManager;
import com.orfriend.orfriend.backend.m_Mysql.User;
import com.orfriend.orfriend.frontend.userPage.UserHomePage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

/**
 * Created by basmatebe on 1/2/17.
 */

public class LoginHelper extends AsyncTask<Void,Void,String> {


    static Context c;
    String urlAddress;
    EditText usernameTxt,passwordTxt;
    SessionManager session;
    ProgressDialog pd;
    User user;

    public LoginHelper(Context c ,String urlAddress, EditText usernameTxt, EditText passwordTxt) {
        this.c = c;

        this.urlAddress = urlAddress;
        this.usernameTxt = usernameTxt;
        this.passwordTxt = passwordTxt;

        user=new User();
        user.setUsernameOrEmail(usernameTxt.getText().toString());
        user.setPassword(passwordTxt.getText().toString());

    }





    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Log In");
        pd.setMessage("Logging In...Please wait");
        pd.show();

    }

    @Override
    protected String doInBackground(Void... params) {
        return this.logIn();
    }

    public static void startpage(){
        Intent n = new Intent(c, UserHomePage.class);
        c.startActivity(n);

    }

    @Override
    protected  void onPostExecute(String response) {
        super.onPostExecute(response);

        pd.dismiss();

        if(response.startsWith("Error"))
        {
            display(response);
        }else
        {
            //IF LOGIN SUCCESS
            if(response.startsWith(ErrorTracker.SUCCESS))
            {
                session = new SessionManager(c);
                session.createLoginSessionUser(usernameTxt.toString());
                usernameTxt.setText("");
                passwordTxt.setText("");
                display(response);
                startpage();


            }else {
                //E.G IF USER EXISTS
                display(response);
            }
        }

    }

    private void display(String text)
    {
        Toast.makeText(c,text,Toast.LENGTH_LONG).show();
    }
    private String logIn()
    {
        Object connection=LoginConnector.connect(urlAddress);
        if(connection.toString().startsWith("Error"))
        {
            return connection.toString();
        }

        try
        {
            HttpURLConnection con= (HttpURLConnection) connection;
            OutputStream os=new BufferedOutputStream(con.getOutputStream());
            BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(os));

            String loginData=new LoginData(user).packLoginData();
            if(loginData.startsWith("Error"))
            {
                //from php
                return loginData;
            }

            bw.write(loginData);
            bw.flush();
            bw.close();
            os.close();

            //GET RESPONSE
            int responseCode=con.getResponseCode();
            if(responseCode==con.HTTP_OK)
            {
                InputStream is=new BufferedInputStream(con.getInputStream());
                BufferedReader br=new BufferedReader(new InputStreamReader(is));

                String line;
                StringBuffer response=new StringBuffer();

                while ((line=br.readLine()) != null)
                {
                    response.append(line+"\n");
                }

                br.close();
                is.close();

                return response.toString();


            }else {
                return ErrorTracker.RESPONSE_ERROR + String.valueOf(responseCode);
            }


        } catch (IOException e) {
            e.printStackTrace();
            return ErrorTracker.IO_ERROR;
        }
    }

}
