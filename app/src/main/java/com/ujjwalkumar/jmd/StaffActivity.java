package com.ujjwalkumar.jmd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.ujjwalkumar.jmd.util.GoogleMapController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class StaffActivity extends AppCompatActivity {

    private Timer _timer = new Timer();
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
    private double t = 0;
    private String uid = "";
    private HashMap<String, Object> tmp = new HashMap<>();
    private double u = 0;
    private ArrayList<HashMap<String, Object>> lmp = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> lmp_filter = new ArrayList<>();

    private ImageView imageback;
    private TextView title;
    private MapView mapview1;
    private GoogleMapController _mapview1_controller;
    private TextView textview2;

    private DatabaseReference user_locations = _firebase.getReference("locations");
    private ChildEventListener _user_locations_child_listener;
    private Intent inst = new Intent();
    private Calendar cal = Calendar.getInstance();
    private TimerTask wait;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.staff);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initialize(_savedInstanceState);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {

        imageback = (ImageView) findViewById(R.id.imageback);
        title = (TextView) findViewById(R.id.title);
        mapview1 = (MapView) findViewById(R.id.mapview1);
        mapview1.onCreate(_savedInstanceState);
        textview2 = (TextView) findViewById(R.id.textview2);

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                inst.setAction(Intent.ACTION_VIEW);
                inst.setClass(getApplicationContext(), AccountActivity.class);
                inst.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(inst);
                finish();
            }
        });

        _mapview1_controller = new GoogleMapController(mapview1, new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap _googleMap) {
                _mapview1_controller.setGoogleMap(_googleMap);
                wait = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                u = 0;
                                if (lmp_filter.size() > 0) {
                                    for (int _repeat14 = 0; _repeat14 < (int) (lmp_filter.size()); _repeat14++) {
                                        _setMarkers(Double.parseDouble(lmp_filter.get((int) u).get("loc_lat").toString()), Double.parseDouble(lmp_filter.get((int) u).get("loc_lng").toString()), Double.parseDouble(lmp_filter.get((int) u).get("time").toString()));
                                        u++;
                                    }
                                }
                            }
                        });
                    }
                };
                _timer.schedule(wait, (int) (5000));
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
    }

    private void initializeLogic() {
        title.setText(getIntent().getStringExtra("name"));
        uid = getIntent().getStringExtra("uid");
        user_locations.addListenerForSingleValueEvent(new ValueEventListener() {
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
                    for (int _repeat24 = 0; _repeat24 < (int) (lmp.size()); _repeat24++) {
                        if (lmp.get((int) t).get("uid").toString().equals(uid)) {
                            tmp = lmp.get((int) t);
                            lmp_filter.add(tmp);
                        }
                        t++;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError _databaseError) {
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
        inst.setAction(Intent.ACTION_VIEW);
        inst.setClass(getApplicationContext(), AccountActivity.class);
        inst.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(inst);
        finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapview1.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapview1.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapview1.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapview1.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapview1.onStop();
    }

    private void _setMarkers(final double _lat, final double _lng, final double _id) {
        cal.setTimeInMillis((long) (_id));
        _mapview1_controller.zoomTo(15);
        _mapview1_controller.moveCamera(_lat, _lng);
        _mapview1_controller.addMarker(String.valueOf((long) (_id)), _lat, _lng);
        _mapview1_controller.setMarkerInfo(String.valueOf((long) (_id)), new SimpleDateFormat("d MMMM yyyy").format(cal.getTime()), new SimpleDateFormat("HH:mm:ss a").format(cal.getTime()));
        _mapview1_controller.setMarkerIcon(String.valueOf((long) (_id)), R.drawable.ic_location_on_black);
        textview2.setText(new SimpleDateFormat("d MMMM yyyy").format(cal.getTime()).concat("  ".concat(new SimpleDateFormat("HH:mm:ss a").format(cal.getTime()))));
    }

}
