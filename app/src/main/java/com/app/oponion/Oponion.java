package com.app.oponion;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.squareup.picasso.Picasso;

import extra.TypefaceUtil;

/**
 * Created by rutvik on 19-08-2016 at 10:55 PM.
 */

public class Oponion extends MultiDexApplication
{

    public static final String APP_TAG = "oponion";

    @Override
    public void onCreate()
    {
        super.onCreate();

        TypefaceUtil.setDefaultFont(this, "DEFAULT", "montserrat_bold.ttf");
        TypefaceUtil.setDefaultFont(this, "MONOSPACE", "montserrat_bold.ttf");
        TypefaceUtil.setDefaultFont(this, "SERIF", "montserrat_bold.ttf");
        TypefaceUtil.setDefaultFont(this, "SANS_SERIF", "montserrat_bold.ttf");
    }

    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
