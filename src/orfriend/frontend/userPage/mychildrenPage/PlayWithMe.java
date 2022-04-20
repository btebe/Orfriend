package com.orfriend.orfriend.frontend.userPage.mychildrenPage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.orfriend.orfriend.R;
import com.orfriend.orfriend.SessionManager;
import com.orfriend.orfriend.marwaPart.Game;

public class PlayWithMe extends AppCompatActivity {

    private Game game1;
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(PlayWithMe.this);
        session.checkLogin();
        game1 = new Game(this);
        setContentView(game1);
    }
}
