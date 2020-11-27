package com.ujjwalkumar.jmd;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

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

public class SetupActivity extends AppCompatActivity {

    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private double t = 0;
    private HashMap<String, Object> tmp = new HashMap<>();
    private ArrayList<HashMap<String, Object>> lmap = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> sdata = new ArrayList<>();

    private LinearLayout linear11;
    private ImageView imageback;
    private ImageView imageviewupdate;
    private ImageView imageview1;
    private EditText edittext01;
    private EditText edittext1;
    private EditText edittext201;
    private EditText edittext02;
    private EditText edittext2;
    private EditText edittext202;
    private EditText edittext03;
    private EditText edittext3;
    private EditText edittext203;
    private EditText edittext04;
    private EditText edittext4;
    private EditText edittext204;
    private EditText edittext05;
    private EditText edittext5;
    private EditText edittext205;
    private EditText edittext06;
    private EditText edittext6;
    private EditText edittext206;
    private EditText edittext07;
    private EditText edittext7;
    private EditText edittext207;
    private EditText edittext08;
    private EditText edittext8;
    private EditText edittext208;
    private EditText edittext09;
    private EditText edittext9;
    private EditText edittext209;
    private EditText edittext010;
    private EditText edittext10;
    private EditText edittext210;
    private EditText edittext011;
    private EditText edittext11;
    private EditText edittext211;
    private EditText edittext012;
    private EditText edittext12;
    private EditText edittext212;
    private EditText edittext013;
    private EditText edittext13;
    private EditText edittext213;
    private EditText edittext014;
    private EditText edittext14;
    private EditText edittext214;
    private EditText edittext015;
    private EditText edittext15;
    private EditText edittext215;
    private EditText edittext016;
    private EditText edittext16;
    private EditText edittext216;
    private Button buttonsave;
    private Button buttonclear;

    private Intent insup = new Intent();
    private Vibrator vib;
    private AlertDialog.Builder exit;
    private SharedPreferences Rate;
    private SharedPreferences settings;
    private AlertDialog.Builder info;
    private DatabaseReference fbs_rates = _firebase.getReference("rates");
    private ChildEventListener _fbs_rates_child_listener;
    private SharedPreferences details;
    private DatabaseReference fbs = _firebase.getReference("saledata");
    private ChildEventListener _fbs_child_listener;
    private SharedPreferences data;
    private AlertDialog.Builder confirm;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.setup);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initialize(_savedInstanceState);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {

        linear11 = (LinearLayout) findViewById(R.id.linear11);
        imageback = (ImageView) findViewById(R.id.imageback);
        imageviewupdate = (ImageView) findViewById(R.id.imageviewupdate);
        imageview1 = (ImageView) findViewById(R.id.imageview1);
        edittext01 = (EditText) findViewById(R.id.edittext01);
        edittext1 = (EditText) findViewById(R.id.edittext1);
        edittext201 = (EditText) findViewById(R.id.edittext201);
        edittext02 = (EditText) findViewById(R.id.edittext02);
        edittext2 = (EditText) findViewById(R.id.edittext2);
        edittext202 = (EditText) findViewById(R.id.edittext202);
        edittext03 = (EditText) findViewById(R.id.edittext03);
        edittext3 = (EditText) findViewById(R.id.edittext3);
        edittext203 = (EditText) findViewById(R.id.edittext203);
        edittext04 = (EditText) findViewById(R.id.edittext04);
        edittext4 = (EditText) findViewById(R.id.edittext4);
        edittext204 = (EditText) findViewById(R.id.edittext204);
        edittext05 = (EditText) findViewById(R.id.edittext05);
        edittext5 = (EditText) findViewById(R.id.edittext5);
        edittext205 = (EditText) findViewById(R.id.edittext205);
        edittext06 = (EditText) findViewById(R.id.edittext06);
        edittext6 = (EditText) findViewById(R.id.edittext6);
        edittext206 = (EditText) findViewById(R.id.edittext206);
        edittext07 = (EditText) findViewById(R.id.edittext07);
        edittext7 = (EditText) findViewById(R.id.edittext7);
        edittext207 = (EditText) findViewById(R.id.edittext207);
        edittext08 = (EditText) findViewById(R.id.edittext08);
        edittext8 = (EditText) findViewById(R.id.edittext8);
        edittext208 = (EditText) findViewById(R.id.edittext208);
        edittext09 = (EditText) findViewById(R.id.edittext09);
        edittext9 = (EditText) findViewById(R.id.edittext9);
        edittext209 = (EditText) findViewById(R.id.edittext209);
        edittext010 = (EditText) findViewById(R.id.edittext010);
        edittext10 = (EditText) findViewById(R.id.edittext10);
        edittext210 = (EditText) findViewById(R.id.edittext210);
        edittext011 = (EditText) findViewById(R.id.edittext011);
        edittext11 = (EditText) findViewById(R.id.edittext11);
        edittext211 = (EditText) findViewById(R.id.edittext211);
        edittext012 = (EditText) findViewById(R.id.edittext012);
        edittext12 = (EditText) findViewById(R.id.edittext12);
        edittext212 = (EditText) findViewById(R.id.edittext212);
        edittext013 = (EditText) findViewById(R.id.edittext013);
        edittext13 = (EditText) findViewById(R.id.edittext13);
        edittext213 = (EditText) findViewById(R.id.edittext213);
        edittext014 = (EditText) findViewById(R.id.edittext014);
        edittext14 = (EditText) findViewById(R.id.edittext14);
        edittext214 = (EditText) findViewById(R.id.edittext214);
        edittext015 = (EditText) findViewById(R.id.edittext015);
        edittext15 = (EditText) findViewById(R.id.edittext15);
        edittext215 = (EditText) findViewById(R.id.edittext215);
        edittext016 = (EditText) findViewById(R.id.edittext016);
        edittext16 = (EditText) findViewById(R.id.edittext16);
        edittext216 = (EditText) findViewById(R.id.edittext216);
        buttonsave = (Button) findViewById(R.id.buttonsave);
        buttonclear = (Button) findViewById(R.id.buttonclear);
        vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        exit = new AlertDialog.Builder(this);
        Rate = getSharedPreferences("productrates", Activity.MODE_PRIVATE);
        settings = getSharedPreferences("s", Activity.MODE_PRIVATE);
        info = new AlertDialog.Builder(this);
        details = getSharedPreferences("user", Activity.MODE_PRIVATE);
        data = getSharedPreferences("rep", Activity.MODE_PRIVATE);
        confirm = new AlertDialog.Builder(this);

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                exit.setTitle("Exit");
                exit.setMessage("Do you want to exit?\nPlz save your data");
                exit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        if (Rate.getString("preset", "").equals("")) {
                            finish();
                        } else {
                            insup.setAction(Intent.ACTION_VIEW);
                            insup.setClass(getApplicationContext(), MenuActivity.class);
                            insup.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                            startActivity(insup);
                            finish();
                        }
                    }
                });
                exit.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {

                    }
                });
                exit.create().show();
            }
        });

        imageviewupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                fbs_rates.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot _dataSnapshot) {
                        lmap = new ArrayList<>();
                        try {
                            GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                            };
                            for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                                HashMap<String, Object> _map = _data.getValue(_ind);
                                lmap.add(_map);
                            }
                        } catch (Exception _e) {
                            _e.printStackTrace();
                        }
                        if (lmap.size() > 0) {
                            t = 1;
                            for (int _repeat41 = 0; _repeat41 < (int) (16); _repeat41++) {
                                Rate.edit().putString(String.valueOf((long) (t)).concat("i"), lmap.get((int) t).get(String.valueOf((long) (t)).concat("i")).toString()).commit();
                                Rate.edit().putString(String.valueOf((long) (t)), lmap.get((int) t).get(String.valueOf((long) (t))).toString()).commit();
                                Rate.edit().putString(String.valueOf((long) (t)).concat("g"), lmap.get((int) t).get(String.valueOf((long) (t)).concat("g")).toString()).commit();
                                t++;
                            }
                            _setValues();
                            Rate.edit().putString("preset", "1").commit();
                            SketchwareUtil.showMessage(getApplicationContext(), "Updated.");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError _databaseError) {
                    }
                });
            }
        });

        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                info.setTitle("Info");
                info.setMessage("Leave product name empty if you have fewer items.\nMaximum items allowed is 16.\nYou can disable gst from the settings option.");
                info.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {

                    }
                });
                info.create().show();
            }
        });

        buttonsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                confirm.setTitle("Save");
                confirm.setMessage("Do you want to save changes?");
                confirm.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {
                        Rate.edit().putString("preset", "1").commit();
                        if (edittext1.getText().toString().equals("")) {
                            edittext1.setText("0");
                        }
                        if (edittext2.getText().toString().equals("")) {
                            edittext2.setText("0");
                        }
                        if (edittext3.getText().toString().equals("")) {
                            edittext3.setText("0");
                        }
                        if (edittext4.getText().toString().equals("")) {
                            edittext4.setText("0");
                        }
                        if (edittext5.getText().toString().equals("")) {
                            edittext5.setText("0");
                        }
                        if (edittext6.getText().toString().equals("")) {
                            edittext6.setText("0");
                        }
                        if (edittext7.getText().toString().equals("")) {
                            edittext7.setText("0");
                        }
                        if (edittext8.getText().toString().equals("")) {
                            edittext8.setText("0");
                        }
                        if (edittext9.getText().toString().equals("")) {
                            edittext9.setText("0");
                        }
                        if (edittext10.getText().toString().equals("")) {
                            edittext10.setText("0");
                        }
                        if (edittext11.getText().toString().equals("")) {
                            edittext11.setText("0");
                        }
                        if (edittext12.getText().toString().equals("")) {
                            edittext12.setText("0");
                        }
                        if (edittext13.getText().toString().equals("")) {
                            edittext13.setText("0");
                        }
                        if (edittext14.getText().toString().equals("")) {
                            edittext14.setText("0");
                        }
                        if (edittext15.getText().toString().equals("")) {
                            edittext15.setText("0");
                        }
                        if (edittext16.getText().toString().equals("")) {
                            edittext16.setText("0");
                        }
                        if (edittext201.getText().toString().equals("")) {
                            edittext201.setText("0");
                        }
                        if (edittext202.getText().toString().equals("")) {
                            edittext202.setText("0");
                        }
                        if (edittext203.getText().toString().equals("")) {
                            edittext203.setText("0");
                        }
                        if (edittext204.getText().toString().equals("")) {
                            edittext204.setText("0");
                        }
                        if (edittext205.getText().toString().equals("")) {
                            edittext205.setText("0");
                        }
                        if (edittext206.getText().toString().equals("")) {
                            edittext206.setText("0");
                        }
                        if (edittext207.getText().toString().equals("")) {
                            edittext207.setText("0");
                        }
                        if (edittext208.getText().toString().equals("")) {
                            edittext208.setText("0");
                        }
                        if (edittext209.getText().toString().equals("")) {
                            edittext209.setText("0");
                        }
                        if (edittext210.getText().toString().equals("")) {
                            edittext210.setText("0");
                        }
                        if (edittext211.getText().toString().equals("")) {
                            edittext211.setText("0");
                        }
                        if (edittext212.getText().toString().equals("")) {
                            edittext212.setText("0");
                        }
                        if (edittext213.getText().toString().equals("")) {
                            edittext213.setText("0");
                        }
                        if (edittext214.getText().toString().equals("")) {
                            edittext214.setText("0");
                        }
                        if (edittext215.getText().toString().equals("")) {
                            edittext215.setText("0");
                        }
                        if (edittext216.getText().toString().equals("")) {
                            edittext216.setText("0");
                        }
                        Rate.edit().putString("1i", edittext01.getText().toString()).commit();
                        Rate.edit().putString("2i", edittext02.getText().toString()).commit();
                        Rate.edit().putString("3i", edittext03.getText().toString()).commit();
                        Rate.edit().putString("4i", edittext04.getText().toString()).commit();
                        Rate.edit().putString("5i", edittext05.getText().toString()).commit();
                        Rate.edit().putString("6i", edittext06.getText().toString()).commit();
                        Rate.edit().putString("7i", edittext07.getText().toString()).commit();
                        Rate.edit().putString("8i", edittext08.getText().toString()).commit();
                        Rate.edit().putString("9i", edittext09.getText().toString()).commit();
                        Rate.edit().putString("10i", edittext010.getText().toString()).commit();
                        Rate.edit().putString("11i", edittext011.getText().toString()).commit();
                        Rate.edit().putString("12i", edittext012.getText().toString()).commit();
                        Rate.edit().putString("13i", edittext013.getText().toString()).commit();
                        Rate.edit().putString("14i", edittext014.getText().toString()).commit();
                        Rate.edit().putString("15i", edittext015.getText().toString()).commit();
                        Rate.edit().putString("16i", edittext016.getText().toString()).commit();
                        Rate.edit().putString("1", edittext1.getText().toString()).commit();
                        Rate.edit().putString("2", edittext2.getText().toString()).commit();
                        Rate.edit().putString("3", edittext3.getText().toString()).commit();
                        Rate.edit().putString("4", edittext4.getText().toString()).commit();
                        Rate.edit().putString("5", edittext5.getText().toString()).commit();
                        Rate.edit().putString("6", edittext6.getText().toString()).commit();
                        Rate.edit().putString("7", edittext7.getText().toString()).commit();
                        Rate.edit().putString("8", edittext8.getText().toString()).commit();
                        Rate.edit().putString("9", edittext9.getText().toString()).commit();
                        Rate.edit().putString("10", edittext10.getText().toString()).commit();
                        Rate.edit().putString("11", edittext11.getText().toString()).commit();
                        Rate.edit().putString("12", edittext12.getText().toString()).commit();
                        Rate.edit().putString("13", edittext13.getText().toString()).commit();
                        Rate.edit().putString("14", edittext14.getText().toString()).commit();
                        Rate.edit().putString("15", edittext15.getText().toString()).commit();
                        Rate.edit().putString("16", edittext16.getText().toString()).commit();
                        Rate.edit().putString("1g", edittext201.getText().toString()).commit();
                        Rate.edit().putString("2g", edittext202.getText().toString()).commit();
                        Rate.edit().putString("3g", edittext203.getText().toString()).commit();
                        Rate.edit().putString("4g", edittext204.getText().toString()).commit();
                        Rate.edit().putString("5g", edittext205.getText().toString()).commit();
                        Rate.edit().putString("6g", edittext206.getText().toString()).commit();
                        Rate.edit().putString("7g", edittext207.getText().toString()).commit();
                        Rate.edit().putString("8g", edittext208.getText().toString()).commit();
                        Rate.edit().putString("9g", edittext209.getText().toString()).commit();
                        Rate.edit().putString("10g", edittext210.getText().toString()).commit();
                        Rate.edit().putString("11g", edittext211.getText().toString()).commit();
                        Rate.edit().putString("12g", edittext212.getText().toString()).commit();
                        Rate.edit().putString("13g", edittext213.getText().toString()).commit();
                        Rate.edit().putString("14g", edittext214.getText().toString()).commit();
                        Rate.edit().putString("15g", edittext215.getText().toString()).commit();
                        Rate.edit().putString("16g", edittext216.getText().toString()).commit();
                        vib.vibrate((long) (100));
                        _sync();
                    }
                });
                confirm.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface _dialog, int _which) {

                    }
                });
                confirm.create().show();
            }
        });

        buttonclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                edittext1.setText("");
                edittext2.setText("");
                edittext3.setText("");
                edittext4.setText("");
                edittext5.setText("");
                edittext6.setText("");
                edittext7.setText("");
                edittext8.setText("");
                edittext9.setText("");
                edittext10.setText("");
                edittext11.setText("");
                edittext12.setText("");
                edittext13.setText("");
                edittext14.setText("");
                edittext15.setText("");
                edittext16.setText("");
                edittext01.setText("");
                edittext02.setText("");
                edittext03.setText("");
                edittext04.setText("");
                edittext05.setText("");
                edittext06.setText("");
                edittext07.setText("");
                edittext08.setText("");
                edittext09.setText("");
                edittext010.setText("");
                edittext011.setText("");
                edittext012.setText("");
                edittext013.setText("");
                edittext014.setText("");
                edittext015.setText("");
                edittext016.setText("");
                edittext201.setText("");
                edittext202.setText("");
                edittext203.setText("");
                edittext204.setText("");
                edittext205.setText("");
                edittext206.setText("");
                edittext207.setText("");
                edittext208.setText("");
                edittext209.setText("");
                edittext210.setText("");
                edittext211.setText("");
                edittext212.setText("");
                edittext213.setText("");
                edittext214.setText("");
                edittext215.setText("");
                edittext216.setText("");
            }
        });

        _fbs_rates_child_listener = new ChildEventListener() {
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
        fbs_rates.addChildEventListener(_fbs_rates_child_listener);

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
        android.graphics.drawable.GradientDrawable gd1 = new android.graphics.drawable.GradientDrawable();
        gd1.setColor(Color.parseColor("#F44336"));
        gd1.setCornerRadius(15);
        buttonsave.setBackground(gd1);
        android.graphics.drawable.GradientDrawable gd2 = new android.graphics.drawable.GradientDrawable();
        gd2.setColor(Color.parseColor("#F44336"));
        gd2.setCornerRadius(15);
        buttonclear.setBackground(gd2);
        if (details.getString("usertype", "").equals("staff")) {
            linear11.setVisibility(View.GONE);
        }
        if (Rate.getString("preset", "").equals("")) {
            fbs.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot _dataSnapshot) {
                    sdata = new ArrayList<>();
                    try {
                        GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                        };
                        for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                            HashMap<String, Object> _map = _data.getValue(_ind);
                            sdata.add(_map);
                        }
                    } catch (Exception _e) {
                        _e.printStackTrace();
                    }
                    if (sdata.size() > 0) {
                        data.edit().putString("01", sdata.get((int) sdata.size() - 1).get("01").toString()).commit();
                        data.edit().putString("02", sdata.get((int) sdata.size() - 1).get("02").toString()).commit();
                        data.edit().putString("03", sdata.get((int) sdata.size() - 1).get("03").toString()).commit();
                        data.edit().putString("04", sdata.get((int) sdata.size() - 1).get("04").toString()).commit();
                        data.edit().putString("05", sdata.get((int) sdata.size() - 1).get("05").toString()).commit();
                        data.edit().putString("06", sdata.get((int) sdata.size() - 1).get("06").toString()).commit();
                        data.edit().putString("07", sdata.get((int) sdata.size() - 1).get("07").toString()).commit();
                        data.edit().putString("08", sdata.get((int) sdata.size() - 1).get("08").toString()).commit();
                        data.edit().putString("09", sdata.get((int) sdata.size() - 1).get("09").toString()).commit();
                        data.edit().putString("10", sdata.get((int) sdata.size() - 1).get("10").toString()).commit();
                        data.edit().putString("11", sdata.get((int) sdata.size() - 1).get("11").toString()).commit();
                        data.edit().putString("12", sdata.get((int) sdata.size() - 1).get("12").toString()).commit();
                    }
                }

                @Override
                public void onCancelled(DatabaseError _databaseError) {
                }
            });
            fbs_rates.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot _dataSnapshot) {
                    lmap = new ArrayList<>();
                    try {
                        GenericTypeIndicator<HashMap<String, Object>> _ind = new GenericTypeIndicator<HashMap<String, Object>>() {
                        };
                        for (DataSnapshot _data : _dataSnapshot.getChildren()) {
                            HashMap<String, Object> _map = _data.getValue(_ind);
                            lmap.add(_map);
                        }
                    } catch (Exception _e) {
                        _e.printStackTrace();
                    }
                    if (lmap.size() > 0) {
                        t = 1;
                        for (int _repeat200 = 0; _repeat200 < (int) (16); _repeat200++) {
                            Rate.edit().putString(String.valueOf((long) (t)).concat("i"), lmap.get((int) t).get(String.valueOf((long) (t)).concat("i")).toString()).commit();
                            Rate.edit().putString(String.valueOf((long) (t)), lmap.get((int) t).get(String.valueOf((long) (t))).toString()).commit();
                            Rate.edit().putString(String.valueOf((long) (t)).concat("g"), lmap.get((int) t).get(String.valueOf((long) (t)).concat("g")).toString()).commit();
                            t++;
                        }
                        _setValues();
                        Rate.edit().putString("preset", "1").commit();
                    }
                }

                @Override
                public void onCancelled(DatabaseError _databaseError) {
                }
            });
            SketchwareUtil.showMessage(getApplicationContext(), "Data imported automatically");
        }
        _setValues();
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
        exit.setMessage("Do you want to exit?\nPlz save your data");
        exit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface _dialog, int _which) {
                if (Rate.getString("preset", "").equals("")) {
                    finish();
                } else {
                    insup.setAction(Intent.ACTION_VIEW);
                    insup.setClass(getApplicationContext(), MenuActivity.class);
                    insup.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(insup);
                    finish();
                }
            }
        });
        exit.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface _dialog, int _which) {

            }
        });
        exit.create().show();
    }

    private void _sync() {
        t = 0;
        for (int _repeat10 = 0; _repeat10 < (int) (17); _repeat10++) {
            tmp = new HashMap<>();
            tmp.put(String.valueOf((long) (t)).concat("i"), Rate.getString(String.valueOf((long) (t)).concat("i"), ""));
            tmp.put(String.valueOf((long) (t)), Rate.getString(String.valueOf((long) (t)), ""));
            tmp.put(String.valueOf((long) (t)).concat("g"), Rate.getString(String.valueOf((long) (t)).concat("g"), ""));
            fbs_rates.child(String.valueOf((long) (t))).updateChildren(tmp);
            t++;
            tmp.clear();
        }
    }


    private void _setValues() {
        edittext1.setText(Rate.getString("1", ""));
        edittext2.setText(Rate.getString("2", ""));
        edittext3.setText(Rate.getString("3", ""));
        edittext4.setText(Rate.getString("4", ""));
        edittext5.setText(Rate.getString("5", ""));
        edittext6.setText(Rate.getString("6", ""));
        edittext7.setText(Rate.getString("7", ""));
        edittext8.setText(Rate.getString("8", ""));
        edittext9.setText(Rate.getString("9", ""));
        edittext10.setText(Rate.getString("10", ""));
        edittext11.setText(Rate.getString("11", ""));
        edittext12.setText(Rate.getString("12", ""));
        edittext13.setText(Rate.getString("13", ""));
        edittext14.setText(Rate.getString("14", ""));
        edittext15.setText(Rate.getString("15", ""));
        edittext16.setText(Rate.getString("16", ""));
        edittext01.setText(Rate.getString("1i", ""));
        edittext02.setText(Rate.getString("2i", ""));
        edittext03.setText(Rate.getString("3i", ""));
        edittext04.setText(Rate.getString("4i", ""));
        edittext05.setText(Rate.getString("5i", ""));
        edittext06.setText(Rate.getString("6i", ""));
        edittext07.setText(Rate.getString("7i", ""));
        edittext08.setText(Rate.getString("8i", ""));
        edittext09.setText(Rate.getString("9i", ""));
        edittext010.setText(Rate.getString("10i", ""));
        edittext011.setText(Rate.getString("11i", ""));
        edittext012.setText(Rate.getString("12i", ""));
        edittext013.setText(Rate.getString("13i", ""));
        edittext014.setText(Rate.getString("14i", ""));
        edittext015.setText(Rate.getString("15i", ""));
        edittext016.setText(Rate.getString("16i", ""));
        edittext201.setText(Rate.getString("1g", ""));
        edittext202.setText(Rate.getString("2g", ""));
        edittext203.setText(Rate.getString("3g", ""));
        edittext204.setText(Rate.getString("4g", ""));
        edittext205.setText(Rate.getString("5g", ""));
        edittext206.setText(Rate.getString("6g", ""));
        edittext207.setText(Rate.getString("7g", ""));
        edittext208.setText(Rate.getString("8g", ""));
        edittext209.setText(Rate.getString("9g", ""));
        edittext210.setText(Rate.getString("10g", ""));
        edittext211.setText(Rate.getString("11g", ""));
        edittext212.setText(Rate.getString("12g", ""));
        edittext213.setText(Rate.getString("13g", ""));
        edittext214.setText(Rate.getString("14g", ""));
        edittext215.setText(Rate.getString("15g", ""));
        edittext216.setText(Rate.getString("16g", ""));
    }

}
