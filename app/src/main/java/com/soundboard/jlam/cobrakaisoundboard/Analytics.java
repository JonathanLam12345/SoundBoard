package com.soundboard.jlam.cobrakaisoundboard;

import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Arrays;
import java.util.List;

public class Analytics
{

    FirebaseAnalytics firebaseAnalytics;
    public static boolean isDeveloper = true;

    // 356339090890692 s9+
    // 351606080815920  ASUS


    public Analytics()
    {
    }

    public void sendAnalytics(Context context, String content_type)   // content_type: button name
    {
        // Analytics will not be recorded for developers.
        if (!Analytics.isDeveloper)
        {
            firebaseAnalytics = FirebaseAnalytics.getInstance(context);
            Bundle bundle_analytics = new Bundle();
            bundle_analytics.putString(FirebaseAnalytics.Param.ITEM_ID, "ITEM_ID: " + content_type);
            bundle_analytics.putString(FirebaseAnalytics.Param.ITEM_NAME, "ITEM_NAME: " + content_type);
            bundle_analytics.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "CONTENT_TYPE: " + content_type);
            firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle_analytics);
        }
    }
}
