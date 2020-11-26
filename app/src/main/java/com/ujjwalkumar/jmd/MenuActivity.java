package com.ujjwalkumar.jmd;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.ujjwalkumar.jmd.util.RequestNetwork;
import com.ujjwalkumar.jmd.util.RequestNetworkController;
import com.ujjwalkumar.jmd.util.SketchwareUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Random;

public class MenuActivity extends AppCompatActivity {

    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private Toolbar _toolbar;
    private DrawerLayout _drawer;
    private double latitude = 0;
    private double longitude = 0;
    private HashMap<String, Object> user_tmp = new HashMap<>();

    private LinearLayout linear1;
    private TextView textviewnic;
    private LinearLayout linear100;
    private LinearLayout linear200;
    private LinearLayout linear300;
    private LinearLayout linear400;
    private LinearLayout linear500;
    private LinearLayout linear600;
    private LinearLayout _drawer_linear1;
    private LinearLayout _drawer_linear2;
    private LinearLayout _drawer_linear3;
    private LinearLayout _drawer_linear4;
    private TextView _drawer_textview1;

    private Intent inme = new Intent();
    private ObjectAnimator anix = new ObjectAnimator();
    private AlertDialog.Builder exit;
    private ObjectAnimator anir = new ObjectAnimator();
    private DatabaseReference user_locations = _firebase.getReference("locations");
    private ChildEventListener _user_locations_child_listener;
    private LocationManager locate;
    private LocationListener _locate_location_listener;
    private Calendar cal = Calendar.getInstance();
    private SharedPreferences details;
    private RequestNetwork checkConnection;
    private RequestNetwork.RequestListener _checkConnection_request_listener;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.menu);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initialize(_savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
        } else {
            initializeLogic();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            initializeLogic();
        }
    }

    private void initialize(Bundle _savedInstanceState) {

        _toolbar = (Toolbar) findViewById(R.id._toolbar);
        setSupportActionBar(_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        _toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _v) {
                onBackPressed();
            }
        });
        _drawer = (DrawerLayout) findViewById(R.id._drawer);
        ActionBarDrawerToggle _toggle = new ActionBarDrawerToggle(MenuActivity.this, _drawer, _toolbar, R.string.app_name, R.string.app_name);
        _drawer.addDrawerListener(_toggle);
        _toggle.syncState();

        LinearLayout _nav_view = (LinearLayout) findViewById(R.id._nav_view);

        linear1 = (LinearLayout) findViewById(R.id.linear1);
        textviewnic = (TextView) findViewById(R.id.textviewnic);
        linear100 = (LinearLayout) findViewById(R.id.linear100);
        linear200 = (LinearLayout) findViewById(R.id.linear200);
        linear300 = (LinearLayout) findViewById(R.id.linear300);
        linear400 = (LinearLayout) findViewById(R.id.linear400);
        linear500 = (LinearLayout) findViewById(R.id.linear500);
        _drawer_linear1 = (LinearLayout) _nav_view.findViewById(R.id.linear1);
        _drawer_linear2 = (LinearLayout) _nav_view.findViewById(R.id.linear2);
        _drawer_linear3 = (LinearLayout) _nav_view.findViewById(R.id.linear3);
        _drawer_linear4 = (LinearLayout) _nav_view.findViewById(R.id.linear4);
        _drawer_textview1 = (TextView) _nav_view.findViewById(R.id.textview1);
        exit = new AlertDialog.Builder(this);
        locate = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        details = getSharedPreferences("user", Activity.MODE_PRIVATE);
        checkConnection = new RequestNetwork(this);

        linear100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                inme.setAction(Intent.ACTION_VIEW);
                inme.setClass(getApplicationContext(), BillcalculatorActivity.class);
                inme.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(inme);
                finish();
            }
        });

        linear200.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                inme.setAction(Intent.ACTION_VIEW);
                inme.setClass(getApplicationContext(), ReportActivity.class);
                inme.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(inme);
                finish();
            }
        });

        linear300.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                inme.setAction(Intent.ACTION_VIEW);
                inme.setClass(getApplicationContext(), AddshopActivity.class);
                inme.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(inme);
                finish();
            }
        });

        linear400.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                inme.setAction(Intent.ACTION_VIEW);
                inme.setClass(getApplicationContext(), ViewshopActivity.class);
                inme.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(inme);
                finish();
            }
        });

        linear500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                inme.setAction(Intent.ACTION_VIEW);
                inme.setClass(getApplicationContext(), DenominationActivity.class);
                inme.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(inme);
                finish();
            }
        });

        linear600.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                inme.setAction(Intent.ACTION_VIEW);
                inme.setClass(getApplicationContext(), SetupActivity.class);
                inme.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(inme);
                finish();
            }
        });

        _user_locations_child_listener = new ChildEventListener() {
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
        user_locations.addChildEventListener(_user_locations_child_listener);

        _locate_location_listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location _param1) {
                final double _lat = _param1.getLatitude();
                final double _lng = _param1.getLongitude();
                final double _acc = _param1.getAccuracy();
                if ((_lat == latitude) && (_lng == longitude)) {

                } else {
                    cal = Calendar.getInstance();
                    user_tmp = new HashMap<>();
                    user_tmp.put("uid", details.getString("uid", ""));
                    user_tmp.put("time", String.valueOf((long) (cal.getTimeInMillis())));
                    user_tmp.put("loc_lat", String.valueOf(_lat));
                    user_tmp.put("loc_lng", String.valueOf(_lng));
                    user_locations.push().updateChildren(user_tmp);
                }
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        _checkConnection_request_listener = new RequestNetwork.RequestListener() {
            @Override
            public void onResponse(String _param1, String _param2) {
                final String _tag = _param1;
                final String _response = _param2;
                textviewnic.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onErrorResponse(String _param1, String _param2) {
                final String _tag = _param1;
                final String _message = _param2;
                textviewnic.setVisibility(View.VISIBLE);
            }
        };

        _drawer_linear1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                inme.setAction(Intent.ACTION_VIEW);
                inme.setClass(getApplicationContext(), AccountActivity.class);
                inme.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(inme);
                finish();
            }
        });

        _drawer_linear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                SketchwareUtil.showMessage(getApplicationContext(), "Internal error");
            }
        });

        _drawer_linear3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                inme.setAction(Intent.ACTION_VIEW);
                inme.setClass(getApplicationContext(), FeedbackActivity.class);
                inme.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(inme);
                finish();
            }
        });

        _drawer_linear4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                inme.setAction(Intent.ACTION_VIEW);
                inme.setClass(getApplicationContext(), AboutActivity.class);
                inme.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(inme);
                finish();
            }
        });
    }

    private void initializeLogic() {
        setTitle("Jai Maa Durga Traders");
        anix.setTarget(linear1);
        anix.setPropertyName("alpha");
        anix.setFloatValues((float) (0.5d), (float) (1.0d));
        anix.setInterpolator(new DecelerateInterpolator());
        anix.setDuration((int) (500));
        anix.start();
        android.graphics.drawable.GradientDrawable gd100 = new android.graphics.drawable.GradientDrawable();
        gd100.setColor(Color.parseColor("#F44336"));
        gd100.setCornerRadius(50);
        linear100.setBackground(gd100);
        android.graphics.drawable.GradientDrawable gd200 = new android.graphics.drawable.GradientDrawable();
        gd200.setColor(Color.parseColor("#F44336"));
        gd200.setCornerRadius(50);
        linear200.setBackground(gd200);
        android.graphics.drawable.GradientDrawable gd300 = new android.graphics.drawable.GradientDrawable();
        gd300.setColor(Color.parseColor("#F44336"));
        gd300.setCornerRadius(50);
        linear300.setBackground(gd300);
        android.graphics.drawable.GradientDrawable gd400 = new android.graphics.drawable.GradientDrawable();
        gd400.setColor(Color.parseColor("#F44336"));
        gd400.setCornerRadius(50);
        linear400.setBackground(gd400);
        android.graphics.drawable.GradientDrawable gd500 = new android.graphics.drawable.GradientDrawable();
        gd500.setColor(Color.parseColor("#F44336"));
        gd500.setCornerRadius(50);
        linear500.setBackground(gd500);
        android.graphics.drawable.GradientDrawable gd600 = new android.graphics.drawable.GradientDrawable();
        gd600.setColor(Color.parseColor("#F44336"));
        gd600.setCornerRadius(50);
        linear600.setBackground(gd600);
        textviewnic.setVisibility(View.INVISIBLE);
        latitude = 0;
        longitude = 0;
        checkConnection.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com/", "A", _checkConnection_request_listener);
        if (ContextCompat.checkSelfPermission(MenuActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locate.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60000, 50, _locate_location_listener);
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
        exit.setTitle("Exit");
        exit.setMessage("Do you want to exit?");
        exit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface _dialog, int _which) {
                inme.setAction(Intent.ACTION_VIEW);
                inme.setClass(getApplicationContext(), MenuActivity.class);
                inme.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(inme);
                finish();
            }
        });
        exit.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface _dialog, int _which) {

            }
        });
        exit.create().show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        locate.removeUpdates(_locate_location_listener);
    }

    @Deprecated
    public void showMessage(String _s) {
        Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
    }

    @Deprecated
    public int getLocationX(View _v) {
        int[] _location = new int[2];
        _v.getLocationInWindow(_location);
        return _location[0];
    }

    @Deprecated
    public int getLocationY(View _v) {
        int[] _location = new int[2];
        _v.getLocationInWindow(_location);
        return _location[1];
    }

    @Deprecated
    public int getRandom(int _min, int _max) {
        Random random = new Random();
        return random.nextInt(_max - _min + 1) + _min;
    }

    @Deprecated
    public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
        ArrayList<Double> _result = new ArrayList<Double>();
        SparseBooleanArray _arr = _list.getCheckedItemPositions();
        for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
            if (_arr.valueAt(_iIdx))
                _result.add((double) _arr.keyAt(_iIdx));
        }
        return _result;
    }

    @Deprecated
    public float getDip(int _input) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
    }

    @Deprecated
    public int getDisplayWidthPixels() {
        return getResources().getDisplayMetrics().widthPixels;
    }

    @Deprecated
    public int getDisplayHeightPixels() {
        return getResources().getDisplayMetrics().heightPixels;
    }

}
