package com.orfriend.orfriend;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.orfriend.orfriend.frontend.index.LoginUser;

import java.util.HashMap;

/**
 * Created by basmatebe on 1/3/17.
 */

public class SessionManager {

    // Shared Preferences
    SharedPreferences pref;

    // Editor for Shared preferences
    Editor editor;

    // Context
    Context _context;

    // Shared pref mode
    int PRIVATE_MODE = 0;

    // Sharedpref file name
    private static final String PREF_NAME = "orfriend";

    // All Shared Preferences Keys
    private static final String IS_LOGIN = "IsLoggedIn";

    // User name (make variable public to access from outside)
    public static final String KEY_USER_ID = "name";

    // Email address (make variable public to access from outside)
    public static final String KEY_CHILD_ID = "id";
    public static final String KEY_CHILD_NAME = "cname";
    public static final String KEY_CHILD_PIC = "pic";


    // Constructor
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Create login session
     * */
    public void createLoginSessionUser(String name){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_USER_ID, name);

        // Storing email in pref


        // commit changes
        editor.commit();
    }

    /**
     * Get stored user session data
     * */
    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        // user name
        user.put(KEY_USER_ID, pref.getString(KEY_USER_ID, null));
        // return user
        return user;
    }

    /**
     * Create login session
     * */
    public void createChildIdHolder(String id,String name, String pic ){
        // Storing login value as TRUE
        editor.putBoolean(IS_LOGIN, true);

        // Storing name in pref
        editor.putString(KEY_CHILD_ID, id);
        editor.putString(KEY_CHILD_PIC, pic);
        editor.putString(KEY_CHILD_NAME, name);

        // Storing email in pref


        // commit changes
        editor.commit();
    }



    /**
     * Get stored child session data
     * */
    public HashMap<String, String> getChildDetails(){
        HashMap<String, String> child = new HashMap<String, String>();
        // user name
        child.put(KEY_CHILD_ID, pref.getString(KEY_CHILD_ID, null));
        child.put(KEY_CHILD_NAME, pref.getString(KEY_CHILD_NAME, null));
        child.put(KEY_CHILD_PIC, pref.getString(KEY_CHILD_PIC, null));
        // return user
        return child;
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     * */
    public void checkLogin(){
        // Check login status
        if(!this.isLoggedIn()){
            // user is not logged in redirect him to Login Activity
            Intent i = new Intent(_context, LoginUser.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring Login Activity
            _context.startActivity(i);
        }

    }
    /**
     * Quick check for login
     * **/
    // Get Login State
    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    /**
     * Clear session details
     * */
    public void logoutUser(){
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

        // After logout redirect user to Loing Activity
        Intent i = new Intent(_context, LoginUser.class);
        // Closing all the Activities
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // Add new Flag to start new Activity
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        // Staring Login Activity
        _context.startActivity(i);
    }

}
