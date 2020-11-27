package com.ujjwalkumar.jmd;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import java.util.HashMap;

public class AccountActivity extends AppCompatActivity {

    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private HashMap<String, Object> user = new HashMap<>();
    private double t = 0;
    private HashMap<String, Object> tmp = new HashMap<>();
    private ArrayList<HashMap<String, Object>> lmp = new ArrayList<>();
    private ArrayList<String> users = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lmp_filter = new ArrayList<>();

    private ImageView imageback;
    private LinearLayout linear21;
    private Button button1;
    private TextView textview2;
    private TextView textview6;
    private TextView textview8;
    private TextView textview10;
    private LinearLayout linear22;
    private LinearLayout linearstac;
    private ListView listview1;
    private TextView textview21;
    private ImageView imageview1;
    private ImageView imageviewshare;
    private ImageView imageview2;
    private EditText edittext1;
    private EditText edittext2;
    private EditText edittext3;
    private Button button2;

    private SharedPreferences details;
    private FirebaseAuth auth;
    private OnCompleteListener<AuthResult> _auth_create_user_listener;
    private OnCompleteListener<AuthResult> _auth_sign_in_listener;
    private OnCompleteListener<Void> _auth_reset_password_listener;
    private DatabaseReference fbsuser = _firebase.getReference("users");
    private ChildEventListener _fbsuser_child_listener;
    private Intent inac = new Intent();
    private SharedPreferences Rate;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.account);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initialize(_savedInstanceState);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {

        imageback = (ImageView) findViewById(R.id.imageback);
        linear21 = (LinearLayout) findViewById(R.id.linear21);
        button1 = (Button) findViewById(R.id.button1);
        textview2 = (TextView) findViewById(R.id.textview2);
        textview6 = (TextView) findViewById(R.id.textview6);
        textview8 = (TextView) findViewById(R.id.textview8);
        textview10 = (TextView) findViewById(R.id.textview10);
        linear22 = (LinearLayout) findViewById(R.id.linear22);
        linearstac = (LinearLayout) findViewById(R.id.linearstac);
        listview1 = (ListView) findViewById(R.id.listview1);
        textview21 = (TextView) findViewById(R.id.textview21);
        imageview1 = (ImageView) findViewById(R.id.imageview1);
        imageviewshare = (ImageView) findViewById(R.id.imageviewshare);
        imageview2 = (ImageView) findViewById(R.id.imageview2);
        edittext1 = (EditText) findViewById(R.id.edittext1);
        edittext2 = (EditText) findViewById(R.id.edittext2);
        edittext3 = (EditText) findViewById(R.id.edittext3);
        button2 = (Button) findViewById(R.id.button2);
        details = getSharedPreferences("user", Activity.MODE_PRIVATE);
        auth = FirebaseAuth.getInstance();
        Rate = getSharedPreferences("productrates", Activity.MODE_PRIVATE);

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                inac.setAction(Intent.ACTION_VIEW);
                inac.setClass(getApplicationContext(), MenuActivity.class);
                inac.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(inac);
                finish();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                FirebaseAuth.getInstance().signOut();
                Rate.edit().putString("preset", "").commit();
                details.edit().putString("uid", "").commit();
                details.edit().putString("name", "").commit();
                details.edit().putString("email", "").commit();
                details.edit().putString("password", "").commit();
                details.edit().putString("usertype", "").commit();
                finish();
            }
        });

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
                final int _position = _param3;
                inac.setAction(Intent.ACTION_VIEW);
                inac.setClass(getApplicationContext(), StaffActivity.class);
                inac.putExtra("uid", lmp_filter.get((int) _position).get("uid").toString());
                inac.putExtra("name", lmp_filter.get((int) _position).get("name").toString());
                inac.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(inac);
                finish();
            }
        });

        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                linear22.setVisibility(View.GONE);
                linearstac.setVisibility(View.VISIBLE);
            }
        });

        imageviewshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

            }
        });

        imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                linear22.setVisibility(View.VISIBLE);
                linearstac.setVisibility(View.GONE);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                auth.createUserWithEmailAndPassword(edittext2.getText().toString(), edittext3.getText().toString()).addOnCompleteListener(AccountActivity.this, _auth_create_user_listener);
                imageviewshare.setVisibility(View.VISIBLE);
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
                if (_success) {
                    SketchwareUtil.showMessage(getApplicationContext(), "New user created successfully");
                    user = new HashMap<>();
                    user.put("uid", FirebaseAuth.getInstance().getCurrentUser().getUid());
                    user.put("name", edittext1.getText().toString());
                    user.put("email", edittext2.getText().toString());
                    user.put("password", edittext3.getText().toString());
                    user.put("usertype", "staff");
                    fbsuser.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).updateChildren(user);
                    FirebaseAuth.getInstance().signOut();
                    auth.signInWithEmailAndPassword(details.getString("email", ""), details.getString("password", "")).addOnCompleteListener(AccountActivity.this, _auth_sign_in_listener);
                    _loadList();
                } else {
                    SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
                }
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
        imageviewshare.setVisibility(View.GONE);
        textview2.setText(details.getString("uid", ""));
        textview6.setText(details.getString("name", ""));
        textview8.setText(details.getString("email", ""));
        textview10.setText(details.getString("usertype", ""));
        if (details.getString("usertype", "").equals("admin")) {
            linear21.setVisibility(View.VISIBLE);
        } else {
            linear21.setVisibility(View.GONE);
        }
        linearstac.setVisibility(View.GONE);
        _loadList();
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
        inac.setAction(Intent.ACTION_VIEW);
        inac.setClass(getApplicationContext(), MenuActivity.class);
        inac.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(inac);
        finish();
    }

    private void _loadList() {
        lmp.clear();
        users.clear();
        textview21.setVisibility(View.INVISIBLE);
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
                if (lmp.size() > 0) {
                    for (int _repeat16 = 0; _repeat16 < (int) (lmp.size()); _repeat16++) {
                        tmp = lmp.get((int) t);
                        lmp_filter.add(tmp);
                        users.add(lmp.get((int) t).get("name").toString());
                        t++;
                    }
                }
                listview1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_list_item_1, users));
                ((BaseAdapter) listview1.getAdapter()).notifyDataSetChanged();
                if (users.size() == 0) {
                    textview21.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(DatabaseError _databaseError) {
            }
        });
    }

}
