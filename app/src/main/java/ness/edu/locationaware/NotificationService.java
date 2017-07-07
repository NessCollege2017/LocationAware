package ness.edu.locationaware;

import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.LocalBroadcastManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

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
       //showNotification();

        try {
            //Uses internet permission:
            URL url = new URL("https://itunes.apple.com/us/rss/topmovies/limit=100/genre=4401/json");
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            StringBuilder builder = new StringBuilder();


            while ((line = reader.readLine())!=null){
                builder.append(line).append("\n");
            }

            //done
            String result = builder.toString();


            //Toast.makeText(this, "fdssdf", Toast.LENGTH_SHORT).setGravity().show();

            //Report the result?
            Intent message = new Intent("ITunesChannel");
            message.putExtra("json", result);

            //Messenger in C#:
            //...Event Bus...
            LocalBroadcastManager.getInstance(this).sendBroadcast(message);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showNotification() {
        //Do some work:
        //How do we report the result?
        //Push Notification: The Only UI That a service is meant to do.

        // Context context = this;


        //Build the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        //Bare minimum:
        builder.setContentTitle("This is the Title " + new Date());
        builder.setContentText("The Text");
        builder.setSmallIcon(R.drawable.ic_note);//Icon that matches the standards.
        //builder.setContentIntent()

        builder.setAutoCancel(true);

        Intent contentIntent = new Intent(this/*context*/, MapsActivity.class);
        contentIntent.putExtra("Map", "A");
        contentIntent.putExtra("Map", "B");

        //We always get the same Pending intent
        //Update Current means that we want to update the extras:
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
        nm.notify(5, notification);


        //Another Option: Using the service as a Worker Service (Thread)->How do we report? Broadcasts

    }
}
