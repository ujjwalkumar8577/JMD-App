package com.ujjwalkumar.jmd;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ujjwalkumar.jmd.util.SketchwareUtil;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DenominationActivity extends AppCompatActivity {

    private double d2000 = 0;
    private double d500 = 0;
    private double d200 = 0;
    private double d100 = 0;
    private double d50 = 0;
    private double d20 = 0;
    private double d10 = 0;
    private double doth = 0;
    private double R1 = 0;
    private double R2 = 0;
    private double R3 = 0;
    private double R4 = 0;
    private double R5 = 0;
    private double R6 = 0;
    private double R7 = 0;
    private double R8 = 0;
    private double amount = 0;
    private String b = "";

    private LinearLayout layout;
    private ImageView imageback;
    private TextView textviewdate;
    private ImageView imageview1;
    private EditText edittext1;
    private TextView textview9;
    private EditText edittext2;
    private TextView textview10;
    private EditText edittext3;
    private TextView textview11;
    private EditText edittext4;
    private TextView textview12;
    private EditText edittext5;
    private TextView textview13;
    private EditText edittext6;
    private TextView textview14;
    private EditText edittext7;
    private TextView textview15;
    private EditText edittext8;
    private TextView textview16;
    private Button buttoncalc;
    private Button buttonclear;
    private TextView textview18;

    private Intent ind = new Intent();
    private ObjectAnimator anix = new ObjectAnimator();
    private ObjectAnimator aniy = new ObjectAnimator();
    private Calendar cal = Calendar.getInstance();
    private AlertDialog.Builder Exit;
    private SharedPreferences settings;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.denomination);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initialize(_savedInstanceState);
        initializeLogic();
    }

    private void initialize(Bundle _savedInstanceState) {

        layout = (LinearLayout) findViewById(R.id.layout);
        imageback = (ImageView) findViewById(R.id.imageback);
        textviewdate = (TextView) findViewById(R.id.textviewdate);
        imageview1 = (ImageView) findViewById(R.id.imageview1);
        edittext1 = (EditText) findViewById(R.id.edittext1);
        textview9 = (TextView) findViewById(R.id.textview9);
        edittext2 = (EditText) findViewById(R.id.edittext2);
        textview10 = (TextView) findViewById(R.id.textview10);
        edittext3 = (EditText) findViewById(R.id.edittext3);
        textview11 = (TextView) findViewById(R.id.textview11);
        edittext4 = (EditText) findViewById(R.id.edittext4);
        textview12 = (TextView) findViewById(R.id.textview12);
        edittext5 = (EditText) findViewById(R.id.edittext5);
        textview13 = (TextView) findViewById(R.id.textview13);
        edittext6 = (EditText) findViewById(R.id.edittext6);
        textview14 = (TextView) findViewById(R.id.textview14);
        edittext7 = (EditText) findViewById(R.id.edittext7);
        textview15 = (TextView) findViewById(R.id.textview15);
        edittext8 = (EditText) findViewById(R.id.edittext8);
        textview16 = (TextView) findViewById(R.id.textview16);
        buttoncalc = (Button) findViewById(R.id.buttoncalc);
        buttonclear = (Button) findViewById(R.id.buttonclear);
        textview18 = (TextView) findViewById(R.id.textview18);
        Exit = new AlertDialog.Builder(this);
        settings = getSharedPreferences("s", Activity.MODE_PRIVATE);

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                ind.setAction(Intent.ACTION_VIEW);
                ind.setClass(getApplicationContext(), MenuActivity.class);
                ind.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(ind);
                finish();
            }
        });

        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                b = "2000 * " + d2000 + " = " + R1 + "\n" + "500   * " + d500 + " = " + R2 + "\n" + "200   * " + d200 + " = " + R3 + "\n" + "100   * " + d100 + " = " + R4 + "\n" + "50     * " + d50 + " = " + R5 + "\n" + "20     * " + d20 + " = " + R6 + "\n" + "10     * " + d10 + " = " + R7 + "\n" + "1       * " + doth + " = " + R8 + "\n" + "Total Amt.  =  " + amount + "\n";
                Intent ind = new Intent(android.content.Intent.ACTION_SEND);
                ind.setType("text/plain");
                ind.putExtra(android.content.Intent.EXTRA_TEXT, b);
                startActivity(Intent.createChooser(ind, "Share using"));
            }
        });

        buttoncalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                _calculate();
            }
        });

        buttonclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                _clearallfields();
                SketchwareUtil.showMessage(getApplicationContext(), "Cleared");
            }
        });
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
        cal = Calendar.getInstance();
        android.graphics.drawable.GradientDrawable gd1 = new android.graphics.drawable.GradientDrawable();
        gd1.setColor(Color.parseColor("#F44336"));
        gd1.setCornerRadius(15);
        buttoncalc.setBackground(gd1);
        android.graphics.drawable.GradientDrawable gd2 = new android.graphics.drawable.GradientDrawable();
        gd2.setColor(Color.parseColor("#F44336"));
        gd2.setCornerRadius(15);
        buttonclear.setBackground(gd2);
        textviewdate.setText("Date : ".concat(new SimpleDateFormat("dd-MM-yyyy").format(cal.getTime())));
        if (settings.getString("date", "").equals("1")) {
            textviewdate.setVisibility(View.VISIBLE);
        } else {
            textviewdate.setVisibility(View.INVISIBLE);
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
        Exit.setTitle("Exit");
        Exit.setMessage("Do you want to exit from Calculator?");
        Exit.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface _dialog, int _which) {
                ind.setAction(Intent.ACTION_VIEW);
                ind.setClass(getApplicationContext(), MenuActivity.class);
                ind.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(ind);
                finish();
            }
        });
        Exit.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface _dialog, int _which) {

            }
        });
        Exit.create().show();
    }

    private void _clearallfields() {
        edittext1.setText("");
        edittext2.setText("");
        edittext3.setText("");
        edittext4.setText("");
        edittext5.setText("");
        edittext6.setText("");
        edittext7.setText("");
        edittext8.setText("");
        textview9.setText("");
        textview10.setText("");
        textview11.setText("");
        textview12.setText("");
        textview13.setText("");
        textview14.setText("");
        textview15.setText("");
        textview16.setText("");
        textview18.setText("0");
    }


    private void _calculate() {
        if (edittext1.getText().toString().equals("")) {
            d2000 = 0;
            edittext1.setText("0");
        } else {
            d2000 = Double.parseDouble(edittext1.getText().toString());
        }
        if (edittext2.getText().toString().equals("")) {
            d500 = 0;
            edittext2.setText("0");
        } else {
            d500 = Double.parseDouble(edittext2.getText().toString());
        }
        if (edittext3.getText().toString().equals("")) {
            d200 = 0;
            edittext3.setText("0");
        } else {
            d200 = Double.parseDouble(edittext3.getText().toString());
        }
        if (edittext4.getText().toString().equals("")) {
            d100 = 0;
            edittext4.setText("0");
        } else {
            d100 = Double.parseDouble(edittext4.getText().toString());
        }
        if (edittext5.getText().toString().equals("")) {
            d50 = 0;
            edittext5.setText("0");
        } else {
            d50 = Double.parseDouble(edittext5.getText().toString());
        }
        if (edittext6.getText().toString().equals("")) {
            d20 = 0;
            edittext6.setText("0");
        } else {
            d20 = Double.parseDouble(edittext6.getText().toString());
        }
        if (edittext7.getText().toString().equals("")) {
            d10 = 0;
            edittext7.setText("0");
        } else {
            d10 = Double.parseDouble(edittext7.getText().toString());
        }
        if (edittext8.getText().toString().equals("")) {
            doth = 0;
            edittext8.setText("0");
        } else {
            doth = Double.parseDouble(edittext8.getText().toString());
        }
        R1 = d2000 * 2000;
        R2 = d500 * 500;
        R3 = d200 * 200;
        R4 = d100 * 100;
        R5 = d50 * 50;
        R6 = d20 * 20;
        R7 = d10 * 10;
        R8 = doth * 1;
        textview9.setText(String.valueOf((long) (R1)));
        textview10.setText(String.valueOf((long) (R2)));
        textview11.setText(String.valueOf((long) (R3)));
        textview12.setText(String.valueOf((long) (R4)));
        textview13.setText(String.valueOf((long) (R5)));
        textview14.setText(String.valueOf((long) (R6)));
        textview15.setText(String.valueOf((long) (R7)));
        textview16.setText(String.valueOf((long) (R8)));
        amount = R1 + (R2 + (R3 + (R4 + (R5 + (R6 + (R7 + R8))))));
        textview18.setText(String.valueOf((long) (amount)));
    }

}
