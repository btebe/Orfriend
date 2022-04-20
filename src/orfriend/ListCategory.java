package com.orfriend.orfriend;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.orfriend.orfriend.backend.Children;
import com.orfriend.orfriend.backend.MySQLClient;
import com.orfriend.orfriend.backend.m_Mysql.Downloader;
import com.orfriend.orfriend.frontend.userPage.ProfilePage;

import java.util.ArrayList;

import static com.orfriend.orfriend.SessionManager.KEY_USER_ID;

public class ListCategory extends AppCompatActivity {

    private ListView mListView;
    private MySQLClient X;
    private static final String DATA_RETRIEVE_URL="http://192.168.0.7/dbRandA/index1.php";
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_category);


        mListView = (ListView) findViewById(R.id.recipe_list_view);
        final ArrayList<Children> children = new ArrayList<>();
        X = new MySQLClient(ListCategory.this);
        String tablename = "Children";
        String data= getIntent().getStringExtra("O_ID");
        String location= getIntent().getStringExtra("location");
        TextView locationtxt = (TextView) findViewById(R.id.locationtxt);
        locationtxt.setText(location+": ");
        X.retrieve(mListView, children, data,"Children", "O_ID");
        //X.selectProfile(mListView, children, tablename);
        //new Downloader(ListCategory.this,DATA_RETRIEVE_URL,mListView).execute();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ListCategory.this);
                alertDialog.setMessage("Are you sure you want to add this child?");
                alertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {


                        String listtype = "MyChildrenList";
                        Children selectedChild = children.get(position);
                        session = new SessionManager(ListCategory.this);
                        //Toast.makeText(ListViewPage.this, Integer.toString(selectedChild.getId()), Toast.LENGTH_LONG).show();
                        new MySQLClient(ListCategory.this).add(selectedChild, listtype,session.getUserDetails().get(KEY_USER_ID));
                        dialogInterface.dismiss();

                    }
                });
                //cancels
                alertDialog.setNegativeButton("cancel", null);
                AlertDialog ald = alertDialog.create();
                ald.show();

            }
        });
    }
}
