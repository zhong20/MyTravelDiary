package com.example.a15862.mytraveldiary.DAO;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.a15862.mytraveldiary.Entity.User;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class UserDAO {
    private FirebaseFirestore db;


    public UserDAO(){
        db = FirebaseFirestore.getInstance();
    }

    public void addBasicUser(User user){
        Log.i("Jing","oncall");
        Map<String,Object> m = new HashMap<>();
        m.put("username",user.getUsername());
        m.put("password",user.getPassword());
        m.put("displayName",user.getDisplayName());
        Log.i("Jing","Dao");
        db.collection("User").document(user.getUsername()).set(m);
    }

    public void saveUserInApp(User user, Context activity){

        SharedPreferences saveUser = activity.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=saveUser.edit();
        editor.putString("username",user.getUsername());
        editor.putString("displayName",user.getDisplayName());

    }

    public User loadUserInApp( Context activity){
        User loadUser=new User();
        SharedPreferences  load = PreferenceManager.getDefaultSharedPreferences(activity);
        loadUser.setDisplayName(load.getString("displayName", "DEFAULT"));
        loadUser.setUsername(load.getString("username","DEFAULT"));
        return loadUser;
    }

}
