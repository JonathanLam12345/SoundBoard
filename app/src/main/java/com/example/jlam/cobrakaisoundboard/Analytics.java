package com.example.jlam.cobrakaisoundboard;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.List;

public class Analytics {

    FirebaseAnalytics firebaseAnalytics;


    public Analytics()
    {
    }

    public void sendAnalytics(Context context, String content_type)   // content_type: button name
    {
        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
        Bundle bundle_analytics = new Bundle();
        bundle_analytics.putString(FirebaseAnalytics.Param.ITEM_ID, "ITEM_ID:" + content_type);
        bundle_analytics.putString(FirebaseAnalytics.Param.ITEM_NAME, "ITEM_NAME:" + content_type);
        bundle_analytics.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "CONTENT_TYPE:" + content_type);
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle_analytics);
    }

}
