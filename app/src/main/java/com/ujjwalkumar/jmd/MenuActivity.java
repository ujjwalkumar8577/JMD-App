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
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.ujjwalkumar.jmd.util.RequestNetwork;
import com.ujjwalkumar.jmd.util.RequestNetworkController;
import com.ujjwalkumar.jmd.util.SketchwareUtil;

import java.util.Calendar;
import java.util.HashMap;

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
    private Calendar cal = Calendar.getInstance();
    private SharedPreferences details;
    private RequestNetwork checkConnection;
    private RequestNetwork.RequestListener _checkConnection_request_listener;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.menu);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initialize(_savedInstanceState);
        initializeLogic();
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
        linear600 = (LinearLayout) findViewById(R.id.linear600);
        _drawer_linear1 = (LinearLayout) _nav_view.findViewById(R.id.linear1);
        _drawer_linear2 = (LinearLayout) _nav_view.findViewById(R.id.linear2);
        _drawer_linear3 = (LinearLayout) _nav_view.findViewById(R.id.linear3);
        _drawer_linear4 = (LinearLayout) _nav_view.findViewById(R.id.linear4);
        _drawer_textview1 = (TextView) _nav_view.findViewById(R.id.textview1);
        exit = new AlertDialog.Builder(this);
        locate = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        details = getSharedPreferences("user", Activity.MODE_PRIVATE);
        checkConnection = new RequestNetwork(this);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

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
        _drawer_textview1.setText(details.getString("name", ""));
        anix.setTarget(linear1);
        anix.setPropertyName("alpha");
        anix.setFloatValues((float) (0.5d), (float) (1.0d));
        anix.setInterpolator(new DecelerateInterpolator());
        anix.setDuration((int) (500));
        anix.start();

        android.graphics.drawable.GradientDrawable gd = new android.graphics.drawable.GradientDrawable();
        gd.setColor(Color.parseColor("#F44336"));
        gd.setCornerRadius(50);
        linear100.setBackground(gd);
        linear200.setBackground(gd);
        linear300.setBackground(gd);
        linear400.setBackground(gd);
        linear500.setBackground(gd);
        linear600.setBackground(gd);
        textviewnic.setVisibility(View.INVISIBLE);
        checkConnection.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com/", "A", _checkConnection_request_listener);

        if (!locate.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            SketchwareUtil.showMessage(getApplicationContext(), "GPS not enabled");
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
            return;
        }

            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {

                            // Got last known location. In some rare situations this can be null.
                            if (location != null) {
                                SketchwareUtil.showMessage(getApplicationContext(), "Got location");
                                final double _lat = location.getLatitude();
                                final double _lng = location.getLongitude();
                                final double _acc = location.getAccuracy();

                                if ((_lat != latitude) || (_lng != longitude)) {
                                    latitude = _lat;
                                    longitude = _lng;
                                    cal = Calendar.getInstance();
                                    user_tmp = new HashMap<>();
                                    user_tmp.put("uid", details.getString("uid", ""));
                                    user_tmp.put("time", String.valueOf((long) (cal.getTimeInMillis())));
                                    user_tmp.put("loc_lat", String.valueOf(_lat));
                                    user_tmp.put("loc_lng", String.valueOf(_lng));
                                    user_locations.push().updateChildren(user_tmp);
                                }
                            } else {
                                SketchwareUtil.showMessage(getApplicationContext(), "Couldn't get location");
                            }
                        }
                    });
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

}
