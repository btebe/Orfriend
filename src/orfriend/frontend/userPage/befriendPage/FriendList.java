package com.orfriend.orfriend.frontend.userPage.befriendPage;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.orfriend.orfriend.R;
import com.orfriend.orfriend.SessionManager;
import com.orfriend.orfriend.backend.Children;
import com.orfriend.orfriend.backend.MySQLClient;
import com.orfriend.orfriend.frontend.userPage.mychildrenPage.Call;
import com.orfriend.orfriend.frontend.userPage.mychildrenPage.KidMenu;
import com.orfriend.orfriend.frontend.userPage.mychildrenPage.MyChildren;

import java.util.ArrayList;

import static com.orfriend.orfriend.SessionManager.KEY_CHILD_ID;
import static com.orfriend.orfriend.SessionManager.KEY_CHILD_NAME;
import static com.orfriend.orfriend.SessionManager.KEY_CHILD_PIC;
import static com.orfriend.orfriend.SessionManager.KEY_USER_ID;

public class FriendList extends AppCompatActivity {

    private ListView mListView;
    private MySQLClient X;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);
        mListView = (ListView) findViewById(R.id.flist);
        final ArrayList<Children> children = new ArrayList<>();
        session = new SessionManager(FriendList.this);
        session.checkLogin();
        X = new MySQLClient(FriendList.this);
        //String table = "MyChildrenList";
        X.retrieveFriendList(mListView, children,session.getUserDetails().get(KEY_USER_ID));
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {

                Children selectedChild = children.get(position);
                session.createChildIdHolder(String.valueOf(selectedChild.getId()),
                        selectedChild.getName(),selectedChild.getImageUrl());
                Toast.makeText(FriendList.this, session.getChildDetails().get(KEY_CHILD_ID), Toast.LENGTH_SHORT).show();
                Intent n = new Intent(FriendList.this,Call.class);
                n.putExtra("page", "befriendPage");
                startActivity(n);

            }
        });
    }
}
