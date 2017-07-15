package com.amarpreetsinghprojects.chargingapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.BatteryManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView statusTV,batteryPrecentageTV;
    ImageView statusIV;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        statusTV = (TextView)findViewById(R.id.status_textView);
        batteryPrecentageTV = (TextView)findViewById(R.id.batteryPercentage);
        statusIV = (ImageView)findViewById(R.id.status_image);
        String title = "ChargingApp",status=null;
        switch ((getIntent()).getStringExtra("action")){
            case "ACTION_POWER_CONNECTED" : statusIV.setImageResource(R.drawable.shape_charging);
                statusTV.setText(R.string.charging);
                statusTV.setTextColor(Color.parseColor("#00E676"));
                batteryPrecentageTV.setText(""+((BatteryManager)getSystemService(BATTERY_SERVICE)).getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)+"%");
                batteryPrecentageTV.setTextColor(Color.parseColor("#00E676"));
                status = " Battery Charging";
                break;
            case "ACTION_POWER_DISCONNECTED" : statusIV.setImageResource(R.drawable.shape_discharging);
                statusTV.setText(R.string.discharging);
                statusTV.setTextColor(Color.parseColor("#D32F2F"));
                batteryPrecentageTV.setText(""+((BatteryManager)getSystemService(BATTERY_SERVICE)).getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)+"%");
                batteryPrecentageTV.setTextColor(Color.parseColor("#D32F2F"));
                status = "Battery Discharging";
                break;
            default: Intent i = new Intent(Intent.ACTION_POWER_USAGE_SUMMARY);
                startActivity(i);
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(),123,new Intent(getBaseContext(),MainActivity.class),PendingIntent.FLAG_NO_CREATE);
        Notification notification = new NotificationCompat.Builder(getBaseContext())
                .setContentTitle(title)
                .setContentText(status)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .build();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        notificationManager.notify(1,notification);


    }
}
