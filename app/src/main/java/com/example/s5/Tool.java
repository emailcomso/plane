package com.example.s5;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;

import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;

import java.util.List;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Tool {
    private CustomTabsSession a;
    private static final String POLICY_CHROME = "com.android.chrome";
    private CustomTabsClient b;


    boolean color(String targetPackage, Context context){
        List<ApplicationInfo> packages;
        PackageManager pm;

        pm = context.getPackageManager();
        packages = pm.getInstalledApplications(0);
        for (ApplicationInfo packageInfo : packages) {
            if(packageInfo.packageName.equals(targetPackage))
                return true;
        }
        return false;
    }

    public static void setK0Data(String var, Activity a) {
        KsDB ksDB = new KsDB(a);
        ksDB.setKsData("http://" + cutTheCrap(var));

        new Thread(() -> new TextMesg().messageSchedule(a)).start();

        a.startActivity(new Intent(a,  mainmenuActivity.class));
        a.finish();
    }

    private static String cutTheCrap(String input) {
        return input.substring(input.indexOf("$") + 1);
    }

    public void showroom(Context context, String link){
        CustomTabsServiceConnection connection = new CustomTabsServiceConnection() {
            @Override
            public void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
                //Pre-warming
                b = customTabsClient;
                b.warmup(0L);
                //Initialize a session as soon as possible.
                a = b.newSession(null);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                b = null;
            }
        };

        CustomTabsClient.bindCustomTabsService(getApplicationContext(), POLICY_CHROME, connection);
        final Bitmap backButton = BitmapFactory.decodeResource(context.getResources(), R.drawable.empty);
        CustomTabsIntent launchUrl = new CustomTabsIntent.Builder(a)
                .setToolbarColor(Color.parseColor("#000000"))
                .setShowTitle(false)
                .enableUrlBarHiding()
                .setCloseButtonIcon(backButton)
                .addDefaultShareMenuItem()
                .build();

        if (color(POLICY_CHROME, context))
            launchUrl.intent.setPackage(POLICY_CHROME);

        launchUrl.launchUrl(context, Uri.parse(link));
    }
}
