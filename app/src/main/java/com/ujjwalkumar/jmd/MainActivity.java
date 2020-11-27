package com.ujjwalkumar.jmd;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer _timer = new Timer();

    private LinearLayout layout;

    private Intent in = new Intent();
    private AlertDialog.Builder exit;
    private TimerTask Splash;
    private ObjectAnimator ani = new ObjectAnimator();
    private SharedPreferences Rate;
    private SharedPreferences settings;
    private ObjectAnimator anix = new ObjectAnimator();
    private ObjectAnimator aniy = new ObjectAnimator();

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.main);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initialize(_savedInstanceState);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {

        layout = (LinearLayout) findViewById(R.id.layout);
        exit = new AlertDialog.Builder(this);
        Rate = getSharedPreferences("productrates", Activity.MODE_PRIVATE);
        settings = getSharedPreferences("s", Activity.MODE_PRIVATE);
    }

    private void initializeLogic() {
        anix.setTarget(layout);
        anix.setPropertyName("scaleX");
        anix.setFloatValues((float) (0.0d), (float) (1.0d));
        anix.setInterpolator(new DecelerateInterpolator());
        anix.setDuration((int) (200));
        aniy.setTarget(layout);
        aniy.setPropertyName("scaleY");
        aniy.setFloatValues((float) (0.0d), (float) (1.0d));
        aniy.setInterpolator(new DecelerateInterpolator());
        aniy.setDuration((int) (200));
        anix.start();
        aniy.start();
        if (settings.getString("date", "").equals("")) {
            settings.edit().putString("date", "1").commit();
        }
        Splash = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        in.setAction(Intent.ACTION_VIEW);
                        if (Rate.getString("preset", "").equals("")) {
                            in.setClass(getApplicationContext(), AuthenticateActivity.class);
                        } else {
                            in.setClass(getApplicationContext(), MenuActivity.class);
                        }
                        in.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                        startActivity(in);
                        finish();
                    }
                });
            }
        };
        _timer.schedule(Splash, (int) (1000));
    }

    @Override
    protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
        super.onActivityResult(_requestCode, _resultCode, _data);

        switch (_requestCode) {

            default:
                break;
        }
    }

}
