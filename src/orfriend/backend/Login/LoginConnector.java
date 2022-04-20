package com.orfriend.orfriend.backend.Login;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by basmatebe on 1/2/17.
 */

public class LoginConnector {
    public static Object connect(String urlAddress)
    {
        try
        {
            URL url=new URL(urlAddress);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();

            //CONNECTION PROPS
            con.setRequestMethod("POST");
            con.setConnectTimeout(15000);
            con.setReadTimeout(15000);
            con.setDoInput(true);
            con.setDoOutput(true);

            return  con;

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return ErrorTracker.WRONG_URL;

        } catch (IOException e) {
            e.printStackTrace();
            return ErrorTracker.CONNECTION_ERROR;
        }
    }
}
