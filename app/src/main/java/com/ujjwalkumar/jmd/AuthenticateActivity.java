package com.ujjwalkumar.jmd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.ujjwalkumar.jmd.util.SketchwareUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class AuthenticateActivity extends AppCompatActivity {

    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private HashMap<String, Object> user = new HashMap<>();
    private double t = 0;
    private double flag = 0;
    private ArrayList<HashMap<String, Object>> lmp = new ArrayList<>();

    private LinearLayout linear15;
    private LinearLayout linear20;
    private LinearLayout linear21;
    private EditText edittextemail;
    private EditText edittextpass;
    private TextView textviewforgot;
    private Button buttonlogin;

    private Intent inl = new Intent();
    private FirebaseAuth auth;
    private OnCompleteListener<AuthResult> _auth_create_user_listener;
    private OnCompleteListener<AuthResult> _auth_sign_in_listener;
    private OnCompleteListener<Void> _auth_reset_password_listener;
    private SharedPreferences details;
    private AlertDialog.Builder info_bid;
    private DatabaseReference fbsuser = _firebase.getReference("users");
    private ChildEventListener _fbsuser_child_listener;
    private Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.authenticate);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initialize(_savedInstanceState);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {

        linear15 = (LinearLayout) findViewById(R.id.linear15);
        linear20 = (LinearLayout) findViewById(R.id.linear20);
        linear21 = (LinearLayout) findViewById(R.id.linear21);
        edittextemail = (EditText) findViewById(R.id.edittextemail);
        edittextpass = (EditText) findViewById(R.id.edittextpass);
        textviewforgot = (TextView) findViewById(R.id.textviewforgot);
        buttonlogin = (Button) findViewById(R.id.buttonlogin);
        auth = FirebaseAuth.getInstance();
        details = getSharedPreferences("user", Activity.MODE_PRIVATE);
        info_bid = new AlertDialog.Builder(this);

        textviewforgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (edittextemail.getText().toString().equals("")) {
                    SketchwareUtil.showMessage(getApplicationContext(), "Enter your email");
                } else {
                    auth.sendPasswordResetEmail(edittextemail.getText().toString()).addOnCompleteListener(_auth_reset_password_listener);
                }
            }
        });

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                details.edit().putString("email", edittextemail.getText().toString()).commit();
                details.edit().putString("password", edittextpass.getText().toString()).commit();
                auth.signInWithEmailAndPassword(edittextemail.getText().toString(), edittextpass.getText().toString()).addOnCompleteListener(AuthenticateActivity.this, _auth_sign_in_listener);
            }
        });

        _fbsuser_child_listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);

            }

            @Override
            public void onChildChanged(DataSnapshot _param1, String _param2) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);

            }

            @Override
            public void onChildMoved(DataSnapshot _param1, String _param2) {

            }

            @Override
            public void onChildRemoved(DataSnapshot _param1) {
                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                };
                final String _childKey = _param1.getKey();
                final HashMap<String, Object> _childValue = _param1.getValue(_ind);

            }

            @Override
            public void onCancelled(DatabaseError _param1) {
                final int _errorCode = _param1.getCode();
                final String _errorMessage = _param1.getMessage();

            }
        };
        fbsuser.addChildEventListener(_fbsuser_child_listener);

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
                if (_success) {
                    fbsuser.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot _dataSnapshot) {
                            lmp = new ArrayList<>();
                            try {
                                GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                                };
                                for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                                    HashMap<String, Object> _map = _data.getValue(_ind);
                                    lmp.add(_map);
                                }
                            } catch (Exception _e) {
                                _e.printStackTrace();
                            }
                            t = 0;
                            flag = 0;
                            for (int _repeat98 = 0; _repeat98 < (int) (lmp.size()); _repeat98++) {
                                if (lmp.get((int) t).get("uid").toString().equals(FirebaseAuth.getInstance().getCurrentUser().getUid())) {
                                    flag = t;
                                    break;
                                }
                                t++;
                            }
                            details.edit().putString("uid", FirebaseAuth.getInstance().getCurrentUser().getUid()).commit();
                            details.edit().putString("name", lmp.get((int) flag).get("name").toString()).commit();
                            details.edit().putString("email", lmp.get((int) flag).get("email").toString()).commit();
                            details.edit().putString("password", lmp.get((int) flag).get("password").toString()).commit();
                            details.edit().putString("usertype", lmp.get((int) flag).get("usertype").toString()).commit();
                            user = new HashMap<>();
                            user.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
                            user.put("name", details.getString("name", ""));
                            user.put("email", FirebaseAuth.getInstance().getCurrentUser().getEmail());
                            user.put("password", edittextpass.getText().toString());
                            user.put("usertype", details.getString("usertype", ""));
                            fbsuser.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(user);
                            inl.setAction(Intent.ACTION_VIEW);
                            inl.setClass(getApplicationContext(), SetupActivity.class);
                            inl.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(inl);
                            finish();
                        }

                        @Override
                        public void onCancelled(DatabaseError _databaseError) {
                        }
                    });
                } else {
                    SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
                }
            }
        };

        _auth_reset_password_listener = new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> _param1) {
                final boolean _success = _param1.isSuccessful();
                SketchwareUtil.showMessage(getApplicationContext(), "Email sent");
            }
        };
    }

    private void initializeLogic() {
        android.graphics.drawable.GradientDrawable gd1 = new android.graphics.drawable.GradientDrawable();
        gd1.setColor(Color.parseColor("#FFFFFF"));
        gd1.setCornerRadius(50);
        linear15.setBackground(gd1);
        android.graphics.drawable.GradientDrawable gd2 = new android.graphics.drawable.GradientDrawable();
        gd2.setColor(Color.parseColor("#FFCDD2"));
        gd2.setCornerRadius(80);
        linear20.setBackground(gd2);
        android.graphics.drawable.GradientDrawable gd3 = new android.graphics.drawable.GradientDrawable();
        gd3.setColor(Color.parseColor("#FFCDD2"));
        gd3.setCornerRadius(80);
        linear21.setBackground(gd3);
        android.graphics.drawable.GradientDrawable gd4 = new android.graphics.drawable.GradientDrawable();
        gd4.setColor(Color.parseColor("#FFFFFF"));
        gd4.setCornerRadius(80);
        buttonlogin.setBackground(gd4);
        buttonlogin.setVisibility(View.VISIBLE);
        if ((FirebaseAuth.getInstance().getCurrentUser() != null)) {
            inl.setAction(Intent.ACTION_VIEW);
            inl.setClass(getApplicationContext(), MenuActivity.class);
            inl.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(inl);
            finish();
        } else {
            edittextemail.setText(details.getString("email", ""));
            edittextpass.setText(details.getString("password", ""));
            fbsuser.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot _dataSnapshot) {
                    lmp = new ArrayList<>();
                    try {
                        GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                        };
                        for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                            HashMap<String, Object> _map = _data.getValue(_ind);
                            lmp.add(_map);
                        }
                    } catch (Exception _e) {
                        _e.printStackTrace();
                    }

                }

                @Override
                public void onCancelled(DatabaseError _databaseError) {
                }
            });
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

}
