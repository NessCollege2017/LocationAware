package ness.edu.locationaware;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A Geo Fence Demo Fragment
 */
public class FencingFragment extends Fragment {


    @BindView(R.id.fabNotify)
    FloatingActionButton fabNotify;
    Unbinder unbinder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fencing, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        /*
        //The Sending side:
        Intent intent = new Intent("ITunesChannel");
        intent.putExtra("json", "Some Json");

        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
        */

        IntentFilter filter = new IntentFilter("ITunesChannel");

        //What do we do with the message (onReceive)
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String json = intent.getStringExtra("json");
                Toast.makeText(context, json, Toast.LENGTH_SHORT).show();
            }
        };



        LocalBroadcastManager.getInstance(getContext()).registerReceiver(
                receiver, filter
        );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.fabNotify)
    public void onNotifyClicked() {
        /*
        Intent intent = new Intent(getContext(), NotificationService.class);
        getActivity().startService(intent);
        */

        //alarm manager may help us schedule repeating tasks
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);


        Intent intent = new Intent(getContext(), NotificationService.class);
        PendingIntent pi = PendingIntent.getService(getContext(), 10, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        //alarmManager.set();
        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 10*1000, 60*1000, pi);
        //alarmManager.setInexactRepeating();

        //alarmManager.setExact();
        //alarmManager.setExactAndAllowWhileIdle();


    }
}
