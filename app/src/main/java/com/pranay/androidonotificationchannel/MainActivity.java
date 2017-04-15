package com.pranay.androidonotificationchannel;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton btnShowNotificationFirst, btnShowNotificationSecond,
            btnShowChannelOneSetting, btnShowChannelTwoSetting;
    private NotificationManager mNotificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        initView();
        initNotificationChannels();
    }


    private void initView() {
        btnShowNotificationFirst = (AppCompatButton) findViewById(R.id.btnShowNotificationFirst);
        btnShowNotificationSecond = (AppCompatButton) findViewById(R.id.btnShowNotificationSec);
        btnShowChannelOneSetting = (AppCompatButton) findViewById(R.id.btnShowChannelOneSetting);
        btnShowChannelTwoSetting = (AppCompatButton) findViewById(R.id.btnShowChannelTwoSetting);
        btnShowNotificationFirst.setOnClickListener(this);
        btnShowNotificationSecond.setOnClickListener(this);
        btnShowChannelOneSetting.setOnClickListener(this);
        btnShowChannelTwoSetting.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnShowNotificationFirst:
                showFirstChannelNotification();
                break;
            case R.id.btnShowNotificationSec:
                showSecondChannelNotification();
                break;
            case R.id.btnShowChannelOneSetting:
                openNotificationSettings(Constant.NOTIFICATION_CHANNEL_ID_ONE);
                break;
            case R.id.btnShowChannelTwoSetting:
                openNotificationSettings(Constant.NOTIFICATION_CHANNEL_ID_TWO);
                break;
        }
    }

    private void initNotificationChannels() {


        NotificationChannel channelFirst = new NotificationChannel(Constant.NOTIFICATION_CHANNEL_ID_ONE
                , Constant.NOTIFICATION_TITLE_ONE
                , NotificationManager.IMPORTANCE_DEFAULT);
        channelFirst.setLightColor(ContextCompat.getColor(this, R.color.colorAccent));
        channelFirst.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        mNotificationManager.createNotificationChannel(channelFirst);


        NotificationChannel channelSec = new NotificationChannel(Constant.NOTIFICATION_CHANNEL_ID_TWO
                , Constant.NOTIFICATION_TITLE_TWO
                , NotificationManager.IMPORTANCE_HIGH);
        channelSec.setLightColor(ContextCompat.getColor(this, R.color.colorAccent));
        channelSec.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

        mNotificationManager.createNotificationChannel(channelSec);
    }

    private void showFirstChannelNotification() {
        Notification.Builder builder = new Notification.Builder(getApplicationContext(), Constant.NOTIFICATION_CHANNEL_ID_ONE)
                .setContentTitle(getString(R.string.str_title_notification_one))
                .setContentText(getString(R.string.str_body_notification_one))
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true);
        mNotificationManager.notify(Constant.NOTIFICATION_ID_ONE, builder.build());

        Notification.Builder builderTwo = new Notification.Builder(getApplicationContext(), Constant.NOTIFICATION_CHANNEL_ID_ONE)
                .setContentTitle(getString(R.string.str_title_notification_two))
                .setContentText(getString(R.string.str_body_notification_two))
                .setSmallIcon(android.R.drawable.ic_dialog_email)
                .setAutoCancel(true);
        mNotificationManager.notify(Constant.NOTIFICATION_ID_TWO, builderTwo.build());

    }

    private void showSecondChannelNotification() {
        Notification.Builder builder = new Notification.Builder(getApplicationContext(), Constant.NOTIFICATION_CHANNEL_ID_TWO)
                .setContentTitle(getString(R.string.str_title_notification_one))
                .setContentText(getString(R.string.str_body_notification_three))
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setAutoCancel(true);
        mNotificationManager.notify(Constant.NOTIFICATION_ID_THREE, builder.build());

        Notification.Builder builderTwo = new Notification.Builder(getApplicationContext(), Constant.NOTIFICATION_CHANNEL_ID_TWO)
                .setContentTitle(getString(R.string.str_title_notification_two))
                .setContentText(getString(R.string.str_body_notification_four))
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setAutoCancel(true);
        mNotificationManager.notify(Constant.NOTIFICATION_ID_FOUR, builderTwo.build());

    }

    /**
     * Open Notification Settings for this app.
     */
    public void openNotificationSettings() {
        Intent i = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
        i.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        startActivity(i);
    }

    /**
     * Open Notification Settings UI for a particular channel.
     *
     * @param channel Name of channel to configure
     */
    public void openNotificationSettings(String channel) {
        Intent i = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
        i.putExtra(Settings.EXTRA_APP_PACKAGE, getPackageName());
        i.putExtra(Settings.EXTRA_CHANNEL_ID, channel);
        startActivity(i);
    }
}
