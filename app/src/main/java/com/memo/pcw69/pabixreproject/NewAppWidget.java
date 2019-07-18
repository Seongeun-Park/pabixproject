package com.memo.pcw69.pabixreproject;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.widget.RemoteViews;

import static android.appwidget.AppWidgetManager.ACTION_APPWIDGET_UPDATE;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    private int size = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);

        if (intent.getAction().equals(ACTION_APPWIDGET_UPDATE)) {
            AppWidgetManager manager = AppWidgetManager.getInstance(context);
            this.onUpdate(context, manager, manager.getAppWidgetIds(new ComponentName(context, getClass())));
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them


        for (int appWidgetId : appWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            remoteViews.setOnClickPendingIntent(R.id.button, pendingIntent);

            SharedPreferences sharedPreferences = context.getSharedPreferences("com.memo.pcw69.pabixreproject.sharedPreferences", Context.MODE_PRIVATE);

            remoteViews.setTextViewText(R.id.button, sharedPreferences.getString("textbox", "빠른메모"));
            size = sharedPreferences.getInt("size", 15);
            remoteViews.setFloat(R.id.button, "setTextSize", size);
            int color = sharedPreferences.getInt("color", ContextCompat.getColor(context, R.color.black));
            remoteViews.setTextColor(R.id.button, color);
            int bgcolor = sharedPreferences.getInt("clear", ContextCompat.getColor(context, R.color.transparent));
            remoteViews.setInt(R.id.background, "setBackgroundColor", bgcolor);

            this.refresh(context, remoteViews);
            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    private void refresh(Context context, RemoteViews remoteViews) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.memo.pcw69.pabixreproject.sharedPreferences", Context.MODE_PRIVATE);

        remoteViews.setTextViewText(R.id.button, sharedPreferences.getString("textbox", "빠른메모"));
    }

}


