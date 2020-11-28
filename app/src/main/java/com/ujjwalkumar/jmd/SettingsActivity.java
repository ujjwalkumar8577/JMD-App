package com.ujjwalkumar.jmd;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SettingsActivity extends AppCompatActivity {

    private LinearLayout layout;
    private ImageView imageback;
    private Switch switch1;
    private TextView textviewfeed;
    private TextView textviewabout;

    private Intent ins = new Intent();
    private ObjectAnimator anix = new ObjectAnimator();
    private ObjectAnimator aniy = new ObjectAnimator();
    private SharedPreferences settings;
    private FirebaseAuth auth;
    private OnCompleteListener<AuthResult> _auth_create_user_listener;
    private OnCompleteListener<AuthResult> _auth_sign_in_listener;
    private OnCompleteListener<Void> _auth_reset_password_listener;
    private SharedPreferences details;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.settings);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initialize(_savedInstanceState);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {

        layout = (LinearLayout) findViewById(R.id.layout);
        imageback = (ImageView) findViewById(R.id.imageback);
        switch1 = (Switch) findViewById(R.id.switch1);
        textviewfeed = (TextView) findViewById(R.id.textviewfeed);
        textviewabout = (TextView) findViewById(R.id.textviewabout);
        settings = getSharedPreferences("s", Activity.MODE_PRIVATE);
        auth = FirebaseAuth.getInstance();
        details = getSharedPreferences("user", Activity.MODE_PRIVATE);

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                ins.setAction(Intent.ACTION_VIEW);
                ins.setClass(getApplicationContext(), MenuActivity.class);
                ins.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(ins);
                finish();
            }
        });

        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton _param1, boolean _param2) {
                final boolean _isChecked = _param2;
                if (_isChecked) {
                    settings.edit().putString("date", "1").commit();
                } else {
                    settings.edit().putString("date", "0").commit();
                }
            }
        });

        textviewfeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                ins.setAction(Intent.ACTION_VIEW);
                ins.setClass(getApplicationContext(), FeedbackActivity.class);
                ins.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(ins);
                finish();
            }
        });

        textviewabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                ins.setAction(Intent.ACTION_VIEW);
                ins.setClass(getApplicationContext(), AboutActivity.class);
                ins.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(ins);
                finish();
            }
        });

        _auth_create_user_listener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> _param1) {
                final boolean _success = _param1.isSuccessful();
                final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";

            }
        };

        _auth_sign_in_listener = new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(Task<AuthResult> _param1) {
                final boolean _success = _param1.isSuccessful();
                final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";

            }
        };

        _auth_reset_password_listener = new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> _param1) {
                final boolean _success = _param1.isSuccessful();

            }
        };
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
        if (settings.getString("date", "").equals("1")) {
            switch1.setChecked(true);
        } else {
            switch1.setChecked(false);
        }
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
        ins.setAction(Intent.ACTION_VIEW);
        ins.setClass(getApplicationContext(), MenuActivity.class);
        ins.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(ins);
        finish();
    }

}
