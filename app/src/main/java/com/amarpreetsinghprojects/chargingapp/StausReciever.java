package com.amarpreetsinghprojects.chargingapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Switch;

public class StausReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Intent intent1 = new Intent(context,MainActivity.class);
        String action;
        switch (intent.getAction()){
            case Intent.ACTION_POWER_CONNECTED: action = "ACTION_POWER_CONNECTED";
                break;
            case Intent.ACTION_POWER_DISCONNECTED: action = "ACTION_POWER_DISCONNECTED";
                break;
            default: action=null;
        }

        intent1.putExtra("action",action);
        context.startActivity(intent1);

    }
}
