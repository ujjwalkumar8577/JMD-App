package com.ujjwalkumar.jmd;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.ujjwalkumar.jmd.util.FileUtil;
import com.ujjwalkumar.jmd.util.GoogleMapController;
import com.ujjwalkumar.jmd.util.SketchwareUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class AddshopActivity extends AppCompatActivity {

    public final int REQ_CD_FP = 101;
    public final int REQ_CD_CAM = 102;
    private Timer _timer = new Timer();
    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();
    private FirebaseStorage _firebase_storage = FirebaseStorage.getInstance();
    private String path = "";
    private HashMap<String, Object> mp = new HashMap<>();
    private double loc_update = 0;
    private double lat = 0;
    private double lng = 0;
    private String d_url = "";
    private String enc = "";
    private ArrayList<String> areas = new ArrayList<>();

    private ImageView imageviewback;
    private TextView textviewdate;
    private EditText edittextname;
    private EditText edittextcontact;
    private Spinner spinner1;
    private EditText edittextaddress;
    private MapView mapview1;
    private GoogleMapController _mapview1_controller;
    private ImageView imageview1;
    private ImageView imageview3;
    private ProgressBar progressbar1;
    private ImageView imageview2;
    private ImageView imageview4;
    private Button buttoncalc;
    private Button buttonclear;

    private Intent inad = new Intent();
    private Intent fp = new Intent(Intent.ACTION_GET_CONTENT);
    private DatabaseReference fbs = _firebase.getReference("shops");
    private ChildEventListener _fbs_child_listener;
    private SharedPreferences details;
    private LocationManager loc;
    private Calendar cal = Calendar.getInstance();
    private FirebaseAuth auth;
    private OnCompleteListener<AuthResult> _auth_create_user_listener;
    private OnCompleteListener<AuthResult> _auth_sign_in_listener;
    private OnCompleteListener<Void> _auth_reset_password_listener;
    private TimerTask wait;
    private Intent cam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    private File _file_cam;
    private AlertDialog.Builder exit;
    private StorageReference fbstorage = _firebase_storage.getReference("images");
    private OnCompleteListener<Uri> _fbstorage_upload_success_listener;
    private OnSuccessListener<FileDownloadTask.TaskSnapshot> _fbstorage_download_success_listener;
    private OnSuccessListener _fbstorage_delete_success_listener;
    private OnProgressListener _fbstorage_upload_progress_listener;
    private OnProgressListener _fbstorage_download_progress_listener;
    private OnFailureListener _fbstorage_failure_listener;
    private TimerTask wait2;
    private FusedLocationProviderClient fusedLocationClient;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.addshop);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initialize(_savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1000);
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

        imageviewback = (ImageView) findViewById(R.id.imageviewback);
        textviewdate = (TextView) findViewById(R.id.textviewdate);
        edittextname = (EditText) findViewById(R.id.edittextname);
        edittextcontact = (EditText) findViewById(R.id.edittextcontact);
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        edittextaddress = (EditText) findViewById(R.id.edittextaddress);
        mapview1 = (MapView) findViewById(R.id.mapview1);
        mapview1.onCreate(_savedInstanceState);
        imageview1 = (ImageView) findViewById(R.id.imageview1);
        imageview3 = (ImageView) findViewById(R.id.imageview3);
        progressbar1 = (ProgressBar) findViewById(R.id.progressbar1);
        imageview2 = (ImageView) findViewById(R.id.imageview2);
        imageview4 = (ImageView) findViewById(R.id.imageview4);
        buttoncalc = (Button) findViewById(R.id.buttoncalc);
        buttonclear = (Button) findViewById(R.id.buttonclear);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fp.setType("image/*");
        fp.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        details = getSharedPreferences("user", Activity.MODE_PRIVATE);
        loc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        auth = FirebaseAuth.getInstance();
        _file_cam = FileUtil.createNewPictureFile(getApplicationContext());
        Uri _uri_cam = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            _uri_cam = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", _file_cam);
        } else {
            _uri_cam = Uri.fromFile(_file_cam);
        }
        cam.putExtra(MediaStore.EXTRA_OUTPUT, _uri_cam);
        cam.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        exit = new AlertDialog.Builder(this);

        imageviewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                inad.setAction(Intent.ACTION_VIEW);
                inad.setClass(getApplicationContext(), MenuActivity.class);
                inad.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(inad);
                finish();
            }
        });

        _mapview1_controller = new GoogleMapController(mapview1, new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap _googleMap) {
                _mapview1_controller.setGoogleMap(_googleMap);
                _mapview1_controller.moveCamera(25.426d, 81.816d);
                _mapview1_controller.zoomTo(15);
                wait = new TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                _mapview1_controller.moveCamera(lat, lng);
                                _mapview1_controller.zoomTo(15);
                                _mapview1_controller.addMarker("id", lat, lng);
                                _mapview1_controller.setMarkerIcon("id", R.drawable.ic_location_on_black);
                            }
                        });
                    }
                };
                _timer.scheduleAtFixedRate(wait, (int) (5000), (int) (5000));
            }
        });

        imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                startActivityForResult(fp, REQ_CD_FP);
            }
        });

        imageview4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                startActivityForResult(cam, REQ_CD_CAM);
            }
        });

        buttoncalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                if (!edittextname.getText().toString().equals("")) {
                    if (!edittextcontact.getText().toString().equals("")) {
                        if (edittextcontact.getText().toString().length() == 10) {
                            if (!(spinner1.getSelectedItemPosition() == 0)) {
                                if (loc_update == 1) {
                                    mp = new HashMap<>();
                                    mp.put("id", fbs.push().getKey());
                                    mp.put("uid", details.getString("uid", ""));
                                    mp.put("name", edittextname.getText().toString());
                                    mp.put("contact", edittextcontact.getText().toString());
                                    mp.put("area", areas.get((int) (spinner1.getSelectedItemPosition())));
                                    mp.put("address", edittextaddress.getText().toString());
                                    mp.put("lat", String.valueOf(lat));
                                    mp.put("lng", String.valueOf(lng));
                                    mp.put("img", d_url);
                                    fbs.child(mp.get("id").toString()).updateChildren(mp);
                                    mp.clear();
                                    SketchwareUtil.showMessage(getApplicationContext(), "Added successfully");
                                    _clear();
                                } else {
                                    SketchwareUtil.showMessage(getApplicationContext(), "Location not updated");
                                }
                            } else {
                                SketchwareUtil.showMessage(getApplicationContext(), "Select area");
                            }
                        } else {
                            SketchwareUtil.showMessage(getApplicationContext(), "Contact should be of 10 digits");
                        }
                    } else {
                        SketchwareUtil.showMessage(getApplicationContext(), "Contact required");
                    }
                } else {
                    SketchwareUtil.showMessage(getApplicationContext(), "Name required");
                }
            }
        });

        buttonclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                _clear();
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
                SketchwareUtil.showMessage(getApplicationContext(), _errorMessage);
            }
        };
        fbs.addChildEventListener(_fbs_child_listener);


        _fbstorage_upload_progress_listener = new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();
                progressbar1.setProgress((int) _progressValue);
            }
        };

        _fbstorage_download_progress_listener = new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onProgress(FileDownloadTask.TaskSnapshot _param1) {
                double _progressValue = (100.0 * _param1.getBytesTransferred()) / _param1.getTotalByteCount();

            }
        };

        _fbstorage_upload_success_listener = new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(Task<Uri> _param1) {
                final String _downloadUrl = _param1.getResult().toString();
                d_url = _downloadUrl;
                progressbar1.setProgress((int) 1);
                imageview3.setAlpha((float) (1));
                SketchwareUtil.showMessage(getApplicationContext(), "Image uploaded");
            }
        };

        _fbstorage_download_success_listener = new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot _param1) {
                final long _totalByteCount = _param1.getTotalByteCount();

            }
        };

        _fbstorage_delete_success_listener = new OnSuccessListener() {
            @Override
            public void onSuccess(Object _param1) {

            }
        };

        _fbstorage_failure_listener = new OnFailureListener() {
            @Override
            public void onFailure(Exception _param1) {
                final String _message = _param1.getMessage();
                SketchwareUtil.showMessage(getApplicationContext(), _message);
            }
        };

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
        android.graphics.drawable.GradientDrawable gd1 = new android.graphics.drawable.GradientDrawable();
        gd1.setColor(Color.parseColor("#F44336"));
        gd1.setCornerRadius(15);
        buttoncalc.setBackground(gd1);
        android.graphics.drawable.GradientDrawable gd2 = new android.graphics.drawable.GradientDrawable();
        gd2.setColor(Color.parseColor("#F44336"));
        gd2.setCornerRadius(15);
        buttonclear.setBackground(gd2);
        cal = Calendar.getInstance();
        textviewdate.setText("Date : ".concat(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime())));
        loc_update = 0;

        areas.add((int) (0), "-select-");
        areas.add((int) (1), "Chakiya");
        areas.add((int) (2), "Civil Lines");
        areas.add((int) (3), "Himmatganj");
        areas.add((int) (4), "Kareli");
        areas.add((int) (5), "Rajapur");
        spinner1.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, areas));
        ((ArrayAdapter) spinner1.getAdapter()).notifyDataSetChanged();
        spinner1.setSelection((int) (0));

        if (!loc.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
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
                            lat = location.getLatitude();
                            lng = location.getAccuracy();
                            imageview1.setImageResource(R.drawable.ic_gps_fixed_black);
                            loc_update = 1;
                            SketchwareUtil.showMessage(getApplicationContext(), "Location Updated");

                        } else {
                            SketchwareUtil.showMessage(getApplicationContext(), "Please check your GPS");

                        }
                    }
                });

    }

    @Override
    protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
        super.onActivityResult(_requestCode, _resultCode, _data);

        switch (_requestCode) {
            case REQ_CD_FP:
                if (_resultCode == Activity.RESULT_OK) {
                    ArrayList<String> _filePath = new ArrayList<>();
                    if (_data != null) {
                        if (_data.getClipData() != null) {
                            for (int _index = 0; _index < _data.getClipData().getItemCount(); _index++) {
                                ClipData.Item _item = _data.getClipData().getItemAt(_index);
                                _filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _item.getUri()));
                            }
                        } else {
                            _filePath.add(FileUtil.convertUriToFilePath(getApplicationContext(), _data.getData()));
                        }
                    }
                    cal = Calendar.getInstance();
                    path = _filePath.get((int) (0));
                    imageview3.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(path, 1024, 1024));
                    imageview3.setAlpha((float) (0.5d));
                    fbstorage.child(String.valueOf((long) (cal.getTimeInMillis())).concat(Uri.parse(path).getLastPathSegment())).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_fbstorage_failure_listener).addOnProgressListener(_fbstorage_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                            return fbstorage.child(String.valueOf((long) (cal.getTimeInMillis())).concat(Uri.parse(path).getLastPathSegment())).getDownloadUrl();
                        }
                    }).addOnCompleteListener(_fbstorage_upload_success_listener);
                } else {

                }
                break;

            case REQ_CD_CAM:
                if (_resultCode == Activity.RESULT_OK) {
                    String _filePath = _file_cam.getAbsolutePath();

                    cal = Calendar.getInstance();
                    path = _filePath;
                    imageview3.setImageBitmap(FileUtil.decodeSampleBitmapFromPath(path, 1024, 1024));
                    imageview3.setAlpha((float) (0.5d));
                    fbstorage.child(String.valueOf((long) (cal.getTimeInMillis())).concat(Uri.parse(path).getLastPathSegment())).putFile(Uri.fromFile(new File(path))).addOnFailureListener(_fbstorage_failure_listener).addOnProgressListener(_fbstorage_upload_progress_listener).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(Task<UploadTask.TaskSnapshot> task) throws Exception {
                            return fbstorage.child(String.valueOf((long) (cal.getTimeInMillis())).concat(Uri.parse(path).getLastPathSegment())).getDownloadUrl();
                        }
                    }).addOnCompleteListener(_fbstorage_upload_success_listener);
                } else {

                }
                break;
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
                inad.setAction(Intent.ACTION_VIEW);
                inad.setClass(getApplicationContext(), MenuActivity.class);
                inad.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(inad);
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

    private void _clear() {
        _mapview1_controller.setMarkerVisible("id", false);
        loc_update = 0;
        path = "";
        d_url = "";
        imageview1.setImageResource(R.drawable.ic_location_searching_black);
        imageview3.setImageResource(R.drawable.inst1);
        progressbar1.setProgress((int) 0);
        mp.clear();
        edittextname.setText("");
        edittextaddress.setText("");
        edittextcontact.setText("");
        spinner1.setSelection((int) (0));

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
                            lat = location.getLatitude();
                            lng = location.getAccuracy();
                            imageview1.setImageResource(R.drawable.ic_gps_fixed_black);
                            loc_update = 1;
                            SketchwareUtil.showMessage(getApplicationContext(), "Location Updated");

                        } else {
                            SketchwareUtil.showMessage(getApplicationContext(), "Please check your GPS");

                        }
                    }
                });

    }

}
