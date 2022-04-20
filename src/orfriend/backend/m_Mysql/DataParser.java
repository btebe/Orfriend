package com.orfriend.orfriend.backend.m_Mysql;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;
import android.widget.Toast;

import com.orfriend.orfriend.backend.Children;
import com.orfriend.orfriend.backend.DbAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by basmatebe on 1/2/17.
 */

public class DataParser extends AsyncTask<Void,Void,Integer> {

    private Context c;
    private String jsonData;
    private ListView rv;
    private DbAdapter adapter ;

    ProgressDialog pd;
    private ArrayList<Children> children =new ArrayList<>();

    public DataParser(Context c, String jsonData, ListView rv) {
        this.c = c;
        this.jsonData = jsonData;
        this.rv = rv;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd=new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing...Please wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params) {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        pd.dismiss();

        if(result==0)
        {
            Toast.makeText(c,"Unable To Parse", Toast.LENGTH_SHORT).show();
        }else
        {
            //BIND DATA TO RECYCLERVIEW
            adapter =new DbAdapter(c,children);
            rv.setAdapter(adapter);
        }
    }

    private int parseData()
    {
        try
        {
            JSONArray ja=new JSONArray(jsonData);
            JSONObject jo=null;

            children.clear();
            Children s;

            for (int i=0;i<ja.length();i++)
            {
                jo=ja.getJSONObject(i);

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

            return 1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }


}
