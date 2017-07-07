package ness.edu.locationaware;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

//Service = Main Thread.
//IntentService = Background/Secondary Thread
public class NotificationService extends IntentService {

    /**
     * Creates an IntentService.
     *
     * Service name Used to name the worker thread, important only for debugging.
     */

    //Required Empty Constructor:
    public NotificationService() {
        super("NotificationService");
    }

    //here the service will get it's mission parameters.
    //the entry point to the service
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
         //Do some work:
         //How do we report the result?
         //Push Notification: The Only UI That a service is meant to do.

       // Context context = this;


        //Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        //Bare minimum:
        builder.setContentTitle("This is the Title");
        builder.setContentText("The Text");
        builder.setSmallIcon(R.drawable.ic_note);//Icon that matches the standards.
        //builder.setContentIntent()

        builder.setAutoCancel(true);

        Intent contentIntent = new Intent(this/*context*/, MapsActivity.class);


        PendingIntent pi =
                PendingIntent.getActivity(this, 1, contentIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        builder.setContentIntent(pi);

        Notification notification = builder.build();
        //title
        //message
        //icon
        //Action?

        //Show the notification:
        NotificationManagerCompat nm = NotificationManagerCompat.from(this/*Context*/);
        nm.notify(1, notification);


        //Another Option: Using the service as a Worker Service (Thread)->How do we report? Broadcasts

    }
}
