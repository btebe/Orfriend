package com.orfriend.orfriend.backend.m_Mysql;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by basmatebe on 1/2/17.
 */

public class Connector {

    public static HttpURLConnection connect(String urlAddress)
    {
        try
        {
            URL url=new URL(urlAddress);
            HttpURLConnection con= (HttpURLConnection) url.openConnection();

            //CON PROPS
            con.setRequestMethod("GET");
            con.setConnectTimeout(20000);
            con.setReadTimeout(20000);
            con.setDoInput(true);

            return con;


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
