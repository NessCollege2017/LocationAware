package ness.edu.locationaware;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Firebase Messaging Service for Getting the cloud notification:
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    //
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("Ness", remoteMessage.toString());
    }
}
