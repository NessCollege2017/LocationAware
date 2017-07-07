package ness.edu.locationaware;

import android.content.SharedPreferences;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Android2017 on 7/7/2017.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    //get the new token here:
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();


        //99% no user yet. the app was just installed.
        //the user didn't sign in yet.

        SharedPreferences prefs = getSharedPreferences("userId", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("userId", token);
        editor.apply();

    }
}
