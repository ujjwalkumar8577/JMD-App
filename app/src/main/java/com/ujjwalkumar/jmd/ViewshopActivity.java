package com.ujjwalkumar.jmd;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
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
import com.ujjwalkumar.jmd.util.SketchwareUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewshopActivity extends AppCompatActivity {

    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private double lat = 0;
    private double lng = 0;
    private double t = 0;
    private double count = 0;
    private HashMap<String, Object> mp = new HashMap<>();
    private ArrayList<HashMap<String, Object>> lmp = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> filtered = new ArrayList<>();

    private LinearLayout layout1;
    private LinearLayout layout2;
    private ImageView imageback;
    private TextView textviewstatus;
    private ListView listview0;
    private EditText edittext1;
    private MapView mapview1;
    private GoogleMapController _mapview1_controller;
    private ImageView imageview2;
    private TextView textview20;
    private ImageView imageview5;
    private TextView textview922;
    private TextView textview930;
    private ImageView imageview7;
    private TextView textview932;
    private TextView textview934;
    private ImageView imageview8;

    private Intent inv = new Intent();
    private DatabaseReference fbs = _firebase.getReference("shops");
    private ChildEventListener _fbs_child_listener;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.viewshop);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initialize(_savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1000);
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

        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        imageback = (ImageView) findViewById(R.id.imageback);
        textviewstatus = (TextView) findViewById(R.id.textviewstatus);
        listview0 = (ListView) findViewById(R.id.listview0);
        edittext1 = (EditText) findViewById(R.id.edittext1);
        mapview1 = (MapView) findViewById(R.id.mapview1);
        mapview1.onCreate(_savedInstanceState);
        imageview2 = (ImageView) findViewById(R.id.imageview2);
        textview20 = (TextView) findViewById(R.id.textview20);
        imageview5 = (ImageView) findViewById(R.id.imageview5);
        textview922 = (TextView) findViewById(R.id.textview922);
        textview930 = (TextView) findViewById(R.id.textview930);
        imageview7 = (ImageView) findViewById(R.id.imageview7);
        textview932 = (TextView) findViewById(R.id.textview932);
        textview934 = (TextView) findViewById(R.id.textview934);
        imageview8 = (ImageView) findViewById(R.id.imageview8);

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                inv.setAction(Intent.ACTION_VIEW);
                inv.setClass(getApplicationContext(), MenuActivity.class);
                inv.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(inv);
                finish();
            }
        });

        listview0.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
                final int _position = _param3;
                textview20.setText(filtered.get((int) _position).get("name").toString());
                textview922.setText(filtered.get((int) _position).get("name").toString());
                textview930.setText(filtered.get((int) _position).get("contact").toString());
                textview932.setText(filtered.get((int) _position).get("area").toString());
                textview934.setText(filtered.get((int) _position).get("address").toString());
                if (!filtered.get((int) _position).get("img").toString().equals("")) {
                    Glide.with(getApplicationContext()).load(Uri.parse(filtered.get((int) _position).get("img").toString())).into(imageview5);
                } else {
                    imageview5.setImageResource(R.drawable.inst1);
                }
                lat = Double.parseDouble(filtered.get((int) _position).get("lat").toString());
                lng = Double.parseDouble(filtered.get((int) _position).get("lng").toString());
                _mapview1_controller.moveCamera(lat, lng);
                _mapview1_controller.zoomTo(15);
                _mapview1_controller.addMarker("id", lat, lng);
                _mapview1_controller.setMarkerIcon("id", R.drawable.ic_location_on_black);
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
            }
        });

        edittext1.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
                final String _charSeq = _param1.toString();
                _searchList(_charSeq);
            }

            @Override
            public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {

            }

            @Override
            public void afterTextChanged(Editable _param1) {

            }
        });

        _mapview1_controller = new GoogleMapController(mapview1, new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap _googleMap) {
                _mapview1_controller.setGoogleMap(_googleMap);

            }
        });

        imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                t = 0;
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
            }
        });

        imageview7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                inv.setAction(Intent.ACTION_CALL);
                inv.setData(Uri.parse("tel:".concat(textview930.getText().toString())));
                startActivity(inv);
            }
        });

        imageview8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                inv.setAction(Intent.ACTION_VIEW);
                inv.setData(Uri.parse("google.navigation:q=".concat(String.valueOf(lat).concat(",".concat(String.valueOf(lng))))));
                if(inv.resolveActivity(getPackageManager())!=null) {
                    startActivity(inv);
                }
                else
                {
                    SketchwareUtil.showMessage(getApplicationContext(),"No app found for navigation");
                }
            }
        });

        _fbs_child_listener = new ChildEventListener() {
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
        fbs.addChildEventListener(_fbs_child_listener);
    }

    private void initializeLogic() {
        fbs.addListenerForSingleValueEvent(new ValueEventListener() {
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
                _loadList();
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
        if (t == 0) {
            inv.setAction(Intent.ACTION_VIEW);
            inv.setClass(getApplicationContext(), MenuActivity.class);
            inv.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(inv);
            finish();
        } else {
            t = 0;
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.GONE);
        }
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

    private void _loadList() {
        filtered.clear();
        t = 0;
        for (int _repeat60 = 0; _repeat60 < (int) (lmp.size()); _repeat60++) {
            mp = lmp.get((int) t);
            filtered.add(mp);
            t++;
        }
        if (filtered.size() == 0) {
            textviewstatus.setVisibility(View.VISIBLE);
            textviewstatus.setText("No shops found");
        } else {
            textviewstatus.setVisibility(View.GONE);
            textviewstatus.setText("Loading ...");
        }
        listview0.setAdapter(new Listview0Adapter(filtered));
        ((BaseAdapter) listview0.getAdapter()).notifyDataSetChanged();
    }


    private void _searchList(final String _str) {
        filtered.clear();
        t = 0;
        for (int _repeat16 = 0; _repeat16 < (int) (lmp.size()); _repeat16++) {
            if (lmp.get((int) t).get("name").toString().toLowerCase().contains(_str.toLowerCase()) || lmp.get((int) t).get("area").toString().toLowerCase().contains(_str.toLowerCase())) {
                mp = lmp.get((int) t);
                filtered.add(mp);
            }
            t++;
        }
        if (filtered.size() == 0) {
            textviewstatus.setVisibility(View.VISIBLE);
            textviewstatus.setText("No shops found");
        } else {
            textviewstatus.setVisibility(View.GONE);
            textviewstatus.setText("Loading ...");
        }
        ((BaseAdapter) listview0.getAdapter()).notifyDataSetChanged();
    }


    public class Listview0Adapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        public Listview0Adapter(ArrayList<HashMap<String, Object>> _arr) {
            _data = _arr;
        }

        @Override
        public int getCount() {
            return _data.size();
        }

        @Override
        public HashMap<String, Object> getItem(int _index) {
            return _data.get(_index);
        }

        @Override
        public long getItemId(int _index) {
            return _index;
        }

        @Override
        public View getView(final int _position, View _view, ViewGroup _viewGroup) {
            LayoutInflater _inflater = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View _v = _view;
            if (_v == null) {
                _v = _inflater.inflate(R.layout.shops, null);
            }

            final LinearLayout linear1 = (LinearLayout) _v.findViewById(R.id.linear1);
            final LinearLayout linear2 = (LinearLayout) _v.findViewById(R.id.linear2);
            final LinearLayout linear5 = (LinearLayout) _v.findViewById(R.id.linear5);
            final LinearLayout linear3 = (LinearLayout) _v.findViewById(R.id.linear3);
            final TextView textview1 = (TextView) _v.findViewById(R.id.textview1);
            final TextView textview2 = (TextView) _v.findViewById(R.id.textview2);
            final TextView textview3 = (TextView) _v.findViewById(R.id.textview3);
            final ImageView imageview2 = (ImageView) _v.findViewById(R.id.imageview2);
            final ImageView imageview1 = (ImageView) _v.findViewById(R.id.imageview1);

            textview1.setText(filtered.get((int) _position).get("name").toString());
            textview2.setText(filtered.get((int) _position).get("contact").toString());
            textview3.setText(filtered.get((int) _position).get("address").toString());
            lat = Double.parseDouble(filtered.get((int) _position).get("lat").toString());
            lng = Double.parseDouble(filtered.get((int) _position).get("lng").toString());
            imageview1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                    inv.setAction(Intent.ACTION_CALL);
                    inv.setData(Uri.parse("tel:".concat(filtered.get((int) _position).get("contact").toString())));
                    startActivity(inv);
                }
            });
            imageview2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                    inv.setAction(Intent.ACTION_VIEW);
                    inv.setData(Uri.parse("google.navigation:q=".concat(String.valueOf(lat).concat(",".concat(String.valueOf(lng))))));
                    startActivity(inv);
                }
            });

            return _v;
        }
    }

}
