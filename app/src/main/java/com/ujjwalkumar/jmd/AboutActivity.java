package com.ujjwalkumar.jmd;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    private LinearLayout layout;
    private ImageView imageback;
    private Intent ina = new Intent();
    private ObjectAnimator anix = new ObjectAnimator();
    private ObjectAnimator aniy = new ObjectAnimator();

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.about);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initialize(_savedInstanceState);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {

        layout = (LinearLayout) findViewById(R.id.layout);
        imageback = (ImageView) findViewById(R.id.imageback);

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                ina.setAction(Intent.ACTION_VIEW);
                ina.setClass(getApplicationContext(), MenuActivity.class);
                ina.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(ina);
                finish();
            }
        });
    }

    private void initializeLogic() {
        anix.setTarget(layout);
        anix.setPropertyName("scaleX");
        anix.setFloatValues((float) (0.0d), (float) (1.0d));
        anix.setInterpolator(new DecelerateInterpolator());
        anix.setDuration((int) (500));
        aniy.setTarget(layout);
        aniy.setPropertyName("scaleY");
        aniy.setFloatValues((float) (0.0d), (float) (1.0d));
        aniy.setInterpolator(new DecelerateInterpolator());
        aniy.setDuration((int) (500));
        anix.start();
        aniy.start();
    }

    @Override
    protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
        super.onActivityResult(_requestCode, _resultCode, _data);

        switch (_requestCode) {

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        ina.setAction(Intent.ACTION_VIEW);
        ina.setClass(getApplicationContext(), MenuActivity.class);
        ina.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(ina);
        finish();
    }

}
