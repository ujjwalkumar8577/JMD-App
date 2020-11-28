package com.ujjwalkumar.jmd;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ujjwalkumar.jmd.util.SketchwareUtil;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class BillcalculatorActivity extends AppCompatActivity {

    private FirebaseDatabase _firebase = FirebaseDatabase.getInstance();

    private double t = 0;
    private double total = 0;
    private double amount = 0;
    private double gstrs = 0;
    private String b = "";
    private double qt = 0;
    private double r = 0;
    private HashMap<String, Object> mp = new HashMap<>();
    private String keydate = "";
    private HashMap<String, Object> temp = new HashMap<>();
    private double cnt = 0;
    private ArrayList<Double> rate = new ArrayList<>();
    private ArrayList<Double> grate = new ArrayList<>();
    private ArrayList<Double> qty = new ArrayList<>();
    private ArrayList<Double> amt = new ArrayList<>();
    private ArrayList<Double> gst = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> mpl = new ArrayList<>();
    private ArrayList<Double> gamt = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> ml1 = new ArrayList<>();
    private ArrayList<HashMap<String, Object>> ml2 = new ArrayList<>();

    private LinearLayout layout1;
    private LinearLayout layout2;
    private ImageView imageviewback;
    private TextView title;
    private ImageView imageviewdetail;
    private TextView textviewdate;
    private ImageView imageviewsync;
    private EditText edittextnam;
    private TextView textview1;
    private EditText edittext1;
    private TextView textview2;
    private EditText edittext2;
    private TextView textview3;
    private EditText edittext3;
    private TextView textview4;
    private EditText edittext4;
    private TextView textview5;
    private EditText edittext5;
    private TextView textview6;
    private EditText edittext6;
    private TextView textview7;
    private EditText edittext7;
    private TextView textview8;
    private EditText edittext8;
    private TextView textview9;
    private EditText edittext9;
    private TextView textview10;
    private EditText edittext10;
    private TextView textview11;
    private EditText edittext11;
    private TextView textview12;
    private EditText edittext12;
    private TextView textview13;
    private EditText edittext13;
    private TextView textview14;
    private EditText edittext14;
    private TextView textview15;
    private EditText edittext15;
    private TextView textview16;
    private EditText edittext16;
    private Button buttoncalc;
    private Button buttonclear;
    private TextView textviewtotal;
    private TextView textviewgst;
    private ImageView imageviewrepback;
    private TextView textview923;
    private ListView listview1;

    private Intent in = new Intent();
    private ObjectAnimator anix = new ObjectAnimator();
    private ObjectAnimator aniy = new ObjectAnimator();
    private SharedPreferences Rate;
    private Calendar cal = Calendar.getInstance();
    private AlertDialog.Builder Exit;
    private SharedPreferences settings;
    private SharedPreferences data;
    private DatabaseReference fbs = _firebase.getReference("saledata");
    private ChildEventListener _fbs_child_listener;
    private DatabaseReference fbsuser = _firebase.getReference("users");
    private ChildEventListener _fbsuser_child_listener;
    private SharedPreferences details;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.billcalculator);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initialize(_savedInstanceState);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {

        layout1 = (LinearLayout) findViewById(R.id.layout1);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        imageviewback = (ImageView) findViewById(R.id.imageviewback);
        title = (TextView) findViewById(R.id.title);
        imageviewdetail = (ImageView) findViewById(R.id.imageviewdetail);
        textviewdate = (TextView) findViewById(R.id.textviewdate);
        imageviewsync = (ImageView) findViewById(R.id.imageviewsync);
        edittextnam = (EditText) findViewById(R.id.edittextnam);
        textview1 = (TextView) findViewById(R.id.textview1);
        edittext1 = (EditText) findViewById(R.id.edittext1);
        textview2 = (TextView) findViewById(R.id.textview2);
        edittext2 = (EditText) findViewById(R.id.edittext2);
        textview3 = (TextView) findViewById(R.id.textview3);
        edittext3 = (EditText) findViewById(R.id.edittext3);
        textview4 = (TextView) findViewById(R.id.textview4);
        edittext4 = (EditText) findViewById(R.id.edittext4);
        textview5 = (TextView) findViewById(R.id.textview5);
        edittext5 = (EditText) findViewById(R.id.edittext5);
        textview6 = (TextView) findViewById(R.id.textview6);
        edittext6 = (EditText) findViewById(R.id.edittext6);
        textview7 = (TextView) findViewById(R.id.textview7);
        edittext7 = (EditText) findViewById(R.id.edittext7);
        textview8 = (TextView) findViewById(R.id.textview8);
        edittext8 = (EditText) findViewById(R.id.edittext8);
        textview9 = (TextView) findViewById(R.id.textview9);
        edittext9 = (EditText) findViewById(R.id.edittext9);
        textview10 = (TextView) findViewById(R.id.textview10);
        edittext10 = (EditText) findViewById(R.id.edittext10);
        textview11 = (TextView) findViewById(R.id.textview11);
        edittext11 = (EditText) findViewById(R.id.edittext11);
        textview12 = (TextView) findViewById(R.id.textview12);
        edittext12 = (EditText) findViewById(R.id.edittext12);
        textview13 = (TextView) findViewById(R.id.textview13);
        edittext13 = (EditText) findViewById(R.id.edittext13);
        textview14 = (TextView) findViewById(R.id.textview14);
        edittext14 = (EditText) findViewById(R.id.edittext14);
        textview15 = (TextView) findViewById(R.id.textview15);
        edittext15 = (EditText) findViewById(R.id.edittext15);
        textview16 = (TextView) findViewById(R.id.textview16);
        edittext16 = (EditText) findViewById(R.id.edittext16);
        buttoncalc = (Button) findViewById(R.id.buttoncalc);
        buttonclear = (Button) findViewById(R.id.buttonclear);
        textviewtotal = (TextView) findViewById(R.id.textviewtotal);
        textviewgst = (TextView) findViewById(R.id.textviewgst);
        imageviewrepback = (ImageView) findViewById(R.id.imageviewrepback);
        textview923 = (TextView) findViewById(R.id.textview923);
        listview1 = (ListView) findViewById(R.id.listview1);
        Rate = getSharedPreferences("productrates", Activity.MODE_PRIVATE);
        Exit = new AlertDialog.Builder(this);
        settings = getSharedPreferences("s", Activity.MODE_PRIVATE);
        data = getSharedPreferences("rep", Activity.MODE_PRIVATE);
        details = getSharedPreferences("user", Activity.MODE_PRIVATE);

        imageviewback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                in.setAction(Intent.ACTION_VIEW);
                in.setClass(getApplicationContext(), MenuActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(in);
                finish();
            }
        });

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {

            }
        });

        imageviewdetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                r = 1;
                textview923.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(cal.getTime()));
                t = 0;
                ml1.clear();
                for (int _repeat18 = 0; _repeat18 < (int) (18); _repeat18++) {
                    {
                        HashMap<String, Object> _item = new HashMap<>();
                        _item.put("s", String.valueOf((long) (t)));
                        ml1.add((int) t, _item);
                    }

                    t++;
                }
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
                listview1.setAdapter(new Listview1Adapter(ml1));
                ((BaseAdapter) listview1.getAdapter()).notifyDataSetChanged();
            }
        });

        imageviewsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                anix.setTarget(imageviewsync);
                anix.setPropertyName("rotation");
                anix.setFloatValues((float) (0), (float) (360));
                anix.start();
                _sync();
            }
        });

        buttoncalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                cal = Calendar.getInstance();
                imageviewdetail.setVisibility(View.VISIBLE);
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
                qty.add((int) (1), Double.valueOf(Double.parseDouble(edittext1.getText().toString())));
                qty.add((int) (2), Double.valueOf(Double.parseDouble(edittext2.getText().toString())));
                qty.add((int) (3), Double.valueOf(Double.parseDouble(edittext3.getText().toString())));
                qty.add((int) (4), Double.valueOf(Double.parseDouble(edittext4.getText().toString())));
                qty.add((int) (5), Double.valueOf(Double.parseDouble(edittext5.getText().toString())));
                qty.add((int) (6), Double.valueOf(Double.parseDouble(edittext6.getText().toString())));
                qty.add((int) (7), Double.valueOf(Double.parseDouble(edittext7.getText().toString())));
                qty.add((int) (8), Double.valueOf(Double.parseDouble(edittext8.getText().toString())));
                qty.add((int) (9), Double.valueOf(Double.parseDouble(edittext9.getText().toString())));
                qty.add((int) (10), Double.valueOf(Double.parseDouble(edittext10.getText().toString())));
                qty.add((int) (11), Double.valueOf(Double.parseDouble(edittext11.getText().toString())));
                qty.add((int) (12), Double.valueOf(Double.parseDouble(edittext12.getText().toString())));
                qty.add((int) (13), Double.valueOf(Double.parseDouble(edittext13.getText().toString())));
                qty.add((int) (14), Double.valueOf(Double.parseDouble(edittext14.getText().toString())));
                qty.add((int) (15), Double.valueOf(Double.parseDouble(edittext15.getText().toString())));
                qty.add((int) (16), Double.valueOf(Double.parseDouble(edittext16.getText().toString())));
                _calculate();
                _save_node();
                _sync();
            }
        });

        buttonclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                _clearallfields();
                SketchwareUtil.showMessage(getApplicationContext(), "Cleared");
            }
        });

        imageviewrepback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
                r = 0;
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
                data.edit().putString("01", _childValue.get("01").toString()).commit();
                data.edit().putString("02", _childValue.get("02").toString()).commit();
                data.edit().putString("03", _childValue.get("03").toString()).commit();
                data.edit().putString("04", _childValue.get("04").toString()).commit();
                data.edit().putString("05", _childValue.get("05").toString()).commit();
                data.edit().putString("06", _childValue.get("06").toString()).commit();
                data.edit().putString("07", _childValue.get("07").toString()).commit();
                data.edit().putString("08", _childValue.get("08").toString()).commit();
                data.edit().putString("09", _childValue.get("09").toString()).commit();
                data.edit().putString("10", _childValue.get("10").toString()).commit();
                data.edit().putString("11", _childValue.get("11").toString()).commit();
                data.edit().putString("12", _childValue.get("12").toString()).commit();
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
    }

    private void initializeLogic() {
        anix.setTarget(layout1);
        anix.setPropertyName("scaleX");
        anix.setFloatValues((float) (0.0d), (float) (1.0d));
        anix.setInterpolator(new DecelerateInterpolator());
        anix.setDuration((int) (200));
        aniy.setTarget(layout1);
        aniy.setPropertyName("scaleY");
        aniy.setFloatValues((float) (0.0d), (float) (1.0d));
        aniy.setInterpolator(new DecelerateInterpolator());
        aniy.setDuration((int) (200));
        anix.start();
        aniy.start();
        cal = Calendar.getInstance();
        fbs.addChildEventListener(_fbs_child_listener);
        android.graphics.drawable.GradientDrawable gd1 = new android.graphics.drawable.GradientDrawable();
        gd1.setColor(Color.parseColor("#F44336"));
        gd1.setCornerRadius(15);
        buttoncalc.setBackground(gd1);
        android.graphics.drawable.GradientDrawable gd2 = new android.graphics.drawable.GradientDrawable();
        gd2.setColor(Color.parseColor("#F44336"));
        gd2.setCornerRadius(15);
        buttonclear.setBackground(gd2);
        layout2.setVisibility(View.GONE);
        imageviewdetail.setVisibility(View.INVISIBLE);
        r = 0;
        _manageact();
        rate.add((int) (0), Double.valueOf(0));
        grate.add((int) (0), Double.valueOf(0));
        qty.add((int) (0), Double.valueOf(0));
        amt.add((int) (0), Double.valueOf(0));
        gst.add((int) (0), Double.valueOf(0));
        t = 1;
        for (int _repeat60 = 0; _repeat60 < (int) (16); _repeat60++) {
            rate.add((int) (t), Double.valueOf(Double.parseDouble(Rate.getString(String.valueOf((long) (t)), ""))));
            t++;
        }
        t = 1;
        for (int _repeat72 = 0; _repeat72 < (int) (16); _repeat72++) {
            grate.add((int) (t), Double.valueOf(Double.parseDouble(Rate.getString(String.valueOf((long) (t)).concat("g"), ""))));
            t++;
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
        if (r == 0) {
            Exit.setTitle("Exit");
            Exit.setMessage("Do you want to exit from Calculator?");
            Exit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface _dialog, int _which) {
                    in.setAction(Intent.ACTION_VIEW);
                    in.setClass(getApplicationContext(), MenuActivity.class);
                    in.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                    startActivity(in);
                    finish();
                }
            });
            Exit.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface _dialog, int _which) {

                }
            });
            Exit.create().show();
        } else {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.GONE);
            r = 0;
        }
    }

    private void _calculate() {
        total = 0;
        gstrs = 0;
        qt = 0;
        t = 1;
        for (int _repeat215 = 0; _repeat215 < (int) (16); _repeat215++) {
            amt.add((int) (t), Double.valueOf(rate.get((int) (t)).doubleValue() * qty.get((int) (t)).doubleValue()));
            gst.add((int) (t), Double.valueOf(grate.get((int) (t)).doubleValue() * qty.get((int) (t)).doubleValue()));
            qt = qt + qty.get((int) (t)).doubleValue();
            total = total + amt.get((int) (t)).doubleValue();
            gstrs = gstrs + gst.get((int) (t)).doubleValue();
            t++;
        }
        amount = total;
        textviewtotal.setText(new DecimalFormat("0.00").format(total));
        textviewgst.setText(new DecimalFormat("0.0000").format(gstrs));
    }


    private void _clearallfields() {
        edittextnam.setText("");
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
        textviewtotal.setText("0.0");
        textviewgst.setText("0.0");
    }


    private void _manageact() {
        cnt = 0;
        textviewdate.setText("Date : ".concat(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime())));
        if (settings.getString("date", "").equals("1")) {
            textviewdate.setVisibility(View.VISIBLE);
        } else {
            textviewdate.setVisibility(View.INVISIBLE);
        }
        if (Rate.getString("1i", "").equals("")) {
            textview1.setVisibility(View.GONE);
            edittext1.setVisibility(View.GONE);
        } else {
            textview1.setText(Rate.getString("1i", ""));
            cnt++;
        }
        if (Rate.getString("2i", "").equals("")) {
            textview2.setVisibility(View.GONE);
            edittext2.setVisibility(View.GONE);
        } else {
            textview2.setText(Rate.getString("2i", ""));
            cnt++;
        }
        if (Rate.getString("3i", "").equals("")) {
            textview3.setVisibility(View.GONE);
            edittext3.setVisibility(View.GONE);
        } else {
            textview3.setText(Rate.getString("3i", ""));
            cnt++;
        }
        if (Rate.getString("4i", "").equals("")) {
            textview4.setVisibility(View.GONE);
            edittext4.setVisibility(View.GONE);
        } else {
            textview4.setText(Rate.getString("4i", ""));
            cnt++;
        }
        if (Rate.getString("5i", "").equals("")) {
            textview5.setVisibility(View.GONE);
            edittext5.setVisibility(View.GONE);
        } else {
            textview5.setText(Rate.getString("5i", ""));
            cnt++;
        }
        if (Rate.getString("6i", "").equals("")) {
            textview6.setVisibility(View.GONE);
            edittext6.setVisibility(View.GONE);
        } else {
            textview6.setText(Rate.getString("6i", ""));
            cnt++;
        }
        if (Rate.getString("7i", "").equals("")) {
            textview7.setVisibility(View.GONE);
            edittext7.setVisibility(View.GONE);
        } else {
            textview7.setText(Rate.getString("7i", ""));
            cnt++;
        }
        if (Rate.getString("8i", "").equals("")) {
            textview8.setVisibility(View.GONE);
            edittext8.setVisibility(View.GONE);
        } else {
            textview8.setText(Rate.getString("8i", ""));
            cnt++;
        }
        if (Rate.getString("9i", "").equals("")) {
            textview9.setVisibility(View.GONE);
            edittext9.setVisibility(View.GONE);
        } else {
            textview9.setText(Rate.getString("9i", ""));
            cnt++;
        }
        if (Rate.getString("10i", "").equals("")) {
            textview10.setVisibility(View.GONE);
            edittext10.setVisibility(View.GONE);
        } else {
            textview10.setText(Rate.getString("10i", ""));
            cnt++;
        }
        if (Rate.getString("11i", "").equals("")) {
            textview11.setVisibility(View.GONE);
            edittext11.setVisibility(View.GONE);
        } else {
            textview11.setText(Rate.getString("11i", ""));
            cnt++;
        }
        if (Rate.getString("12i", "").equals("")) {
            textview12.setVisibility(View.GONE);
            edittext12.setVisibility(View.GONE);
        } else {
            textview12.setText(Rate.getString("12i", ""));
            cnt++;
        }
        if (Rate.getString("13i", "").equals("")) {
            textview13.setVisibility(View.GONE);
            edittext13.setVisibility(View.GONE);
        } else {
            textview13.setText(Rate.getString("13i", ""));
            cnt++;
        }
        if (Rate.getString("14i", "").equals("")) {
            textview14.setVisibility(View.GONE);
            edittext14.setVisibility(View.GONE);
        } else {
            textview14.setText(Rate.getString("14i", ""));
            cnt++;
        }
        if (Rate.getString("15i", "").equals("")) {
            textview15.setVisibility(View.GONE);
            edittext15.setVisibility(View.GONE);
        } else {
            textview15.setText(Rate.getString("15i", ""));
            cnt++;
        }
        if (Rate.getString("16i", "").equals("")) {
            textview16.setVisibility(View.GONE);
            edittext16.setVisibility(View.GONE);
        } else {
            textview16.setText(Rate.getString("16i", ""));
            cnt++;
        }
    }


    private void _save_node() {
        if (!edittextnam.getText().toString().equals("")) {
            cal = Calendar.getInstance();
            keydate = new SimpleDateFormat("MM").format(cal.getTime());
            if (!data.getString(keydate, "").equals("")) {
                mpl = new Gson().fromJson(data.getString(keydate, ""), new TypeToken<ArrayList<HashMap<String, Object>>>() {
                }.getType());
            }
            mp = new HashMap<>();
            t = 1;
            for (int _repeat14 = 0; _repeat14 < (int) (16); _repeat14++) {
                mp.put(String.valueOf((long) (t)).concat("i"), Rate.getString(String.valueOf((long) (t)).concat("i"), ""));
                mp.put(String.valueOf((long) (t)).concat("r"), new DecimalFormat("0.00").format(rate.get((int) (t)).doubleValue()));
                mp.put(String.valueOf((long) (t)).concat("gr"), new DecimalFormat("0.000").format(grate.get((int) (t)).doubleValue()));
                mp.put(String.valueOf((long) (t)).concat("q"), new DecimalFormat("0.00").format(qty.get((int) (t)).doubleValue()));
                mp.put(String.valueOf((long) (t)).concat("a"), new DecimalFormat("0.00").format(amt.get((int) (t)).doubleValue()));
                mp.put(String.valueOf((long) (t)).concat("g"), new DecimalFormat("0.0000").format(gst.get((int) (t)).doubleValue()));
                t++;
            }
            mp.put("name", edittextnam.getText().toString());
            mp.put("dat", new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(cal.getTime()));
            mp.put("qt", new DecimalFormat("0.00").format(qt));
            mp.put("total", new DecimalFormat("0.00").format(total));
            mp.put("gstrs", new DecimalFormat("0.000").format(gstrs));
            mp.put("amount", new DecimalFormat("0.00").format(amount));
            mpl.add((int) 0, mp);
            data.edit().putString(keydate, new Gson().toJson(mpl)).commit();
        }
    }


    private void _sync() {
        temp = new HashMap<>();
        temp.put("01", data.getString("01", ""));
        temp.put("02", data.getString("02", ""));
        temp.put("03", data.getString("03", ""));
        temp.put("04", data.getString("04", ""));
        temp.put("05", data.getString("05", ""));
        temp.put("06", data.getString("06", ""));
        temp.put("07", data.getString("07", ""));
        temp.put("08", data.getString("08", ""));
        temp.put("09", data.getString("09", ""));
        temp.put("10", data.getString("10", ""));
        temp.put("11", data.getString("11", ""));
        temp.put("12", data.getString("12", ""));
        cal = Calendar.getInstance();
        fbs.child(new SimpleDateFormat("yyyy").format(cal.getTime())).updateChildren(temp);
    }


    public class Listview1Adapter extends BaseAdapter {
        ArrayList<HashMap<String, Object>> _data;

        public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
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
                _v = _inflater.inflate(R.layout.cust_detail, null);
            }

            final LinearLayout linear1 = (LinearLayout) _v.findViewById(R.id.linear1);
            final LinearLayout linear2 = (LinearLayout) _v.findViewById(R.id.linear2);
            final TextView textview0 = (TextView) _v.findViewById(R.id.textview0);
            final TextView textview1 = (TextView) _v.findViewById(R.id.textview1);
            final TextView textview2 = (TextView) _v.findViewById(R.id.textview2);
            final TextView textview3 = (TextView) _v.findViewById(R.id.textview3);
            final TextView textview4 = (TextView) _v.findViewById(R.id.textview4);
            final TextView textview5 = (TextView) _v.findViewById(R.id.textview5);
            final TextView textview6 = (TextView) _v.findViewById(R.id.textview6);

            if (_position == 0) {
                linear2.setBackgroundColor(0xFFF44336);
                textview0.setText("S.");
                textview1.setText("Items");
                textview2.setText("Qty");
                textview3.setText("Rate/Unit");
                textview4.setText("Amount");
                textview5.setText("Wt/Unit");
                textview6.setText("Wt.");
            } else {
                if (_position == 17) {
                    linear2.setBackgroundColor(0xFFF44336);
                    textview0.setText("");
                    textview1.setText("Total");
                    textview2.setText(new DecimalFormat("0.00").format(qt));
                    textview3.setText("");
                    textview4.setText(new DecimalFormat("0.00").format(total));
                    textview5.setText("");
                    textview6.setText(new DecimalFormat("0.000").format(gstrs));
                } else {
                    if (qty.get((int) (_position)).doubleValue() == 0) {
                        textview0.setText("");
                        textview1.setText("");
                        textview2.setText("");
                        textview3.setText("");
                        textview4.setText("");
                        textview5.setText("");
                        textview6.setText("");
                        linear2.setVisibility(View.GONE);
                    } else {
                        if ((_position % 2) == 0) {
                            linear2.setBackgroundColor(0xFFFFCDD2);
                        }
                        textview1.setText(Rate.getString(String.valueOf((long) (_position)).concat("i"), ""));
                        textview2.setText(new DecimalFormat("0.00").format(qty.get((int) (_position)).doubleValue()));
                        textview3.setText(new DecimalFormat("0.00").format(rate.get((int) (_position)).doubleValue()));
                        textview4.setText(new DecimalFormat("0.00").format(amt.get((int) (_position)).doubleValue()));
                        textview5.setText(new DecimalFormat("0.000").format(grate.get((int) (_position)).doubleValue()));
                        textview6.setText(new DecimalFormat("0.000").format(gst.get((int) (_position)).doubleValue()));
                        textview0.setText(String.valueOf((long) (_position)));
                    }
                }
            }

            return _v;
        }
    }

}
