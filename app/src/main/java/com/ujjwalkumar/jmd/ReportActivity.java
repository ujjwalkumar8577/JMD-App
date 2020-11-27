package com.ujjwalkumar.jmd;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ujjwalkumar.jmd.util.FileUtil;
import com.ujjwalkumar.jmd.util.SketchwareUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class ReportActivity extends AppCompatActivity {

    private double r = 0;
    private String filename = "";
    private String pathfile = "";
    private double pos = 0;
    private String path = "";
    private String b = "";

    private ArrayList<HashMap<String, Object>> mpr = new ArrayList<>();
    private ArrayList<String> mthyr = new ArrayList<>();

    private LinearLayout layout;
    private LinearLayout linear;
    private LinearLayout layout2;
    private LinearLayout layout1;
    private ImageView imageback;
    private ImageView imageview2;
    private TextView textview20;
    private TextView textview21;
    private LinearLayout linear01;
    private LinearLayout linear02;
    private LinearLayout linear03;
    private LinearLayout linear04;
    private LinearLayout linear05;
    private LinearLayout linear06;
    private LinearLayout linear07;
    private LinearLayout linear08;
    private LinearLayout linear09;
    private LinearLayout linear010;
    private LinearLayout linear011;
    private LinearLayout linear012;
    private LinearLayout linear013;
    private LinearLayout linear014;
    private LinearLayout linear015;
    private LinearLayout linear016;
    private TextView textview501;
    private TextView textview601;
    private TextView textview701;
    private TextView textview401;
    private TextView textview801;
    private TextView textview301;
    private TextView textview502;
    private TextView textview602;
    private TextView textview702;
    private TextView textview402;
    private TextView textview802;
    private TextView textview302;
    private TextView textview503;
    private TextView textview603;
    private TextView textview703;
    private TextView textview403;
    private TextView textview803;
    private TextView textview303;
    private TextView textview504;
    private TextView textview604;
    private TextView textview704;
    private TextView textview404;
    private TextView textview804;
    private TextView textview304;
    private TextView textview505;
    private TextView textview605;
    private TextView textview705;
    private TextView textview405;
    private TextView textview805;
    private TextView textview305;
    private TextView textview506;
    private TextView textview606;
    private TextView textview706;
    private TextView textview406;
    private TextView textview806;
    private TextView textview306;
    private TextView textview507;
    private TextView textview607;
    private TextView textview707;
    private TextView textview407;
    private TextView textview807;
    private TextView textview307;
    private TextView textview508;
    private TextView textview608;
    private TextView textview708;
    private TextView textview408;
    private TextView textview808;
    private TextView textview308;
    private TextView textview509;
    private TextView textview609;
    private TextView textview709;
    private TextView textview409;
    private TextView textview809;
    private TextView textview309;
    private TextView textview510;
    private TextView textview610;
    private TextView textview710;
    private TextView textview410;
    private TextView textview810;
    private TextView textview310;
    private TextView textview511;
    private TextView textview611;
    private TextView textview711;
    private TextView textview411;
    private TextView textview811;
    private TextView textview311;
    private TextView textview512;
    private TextView textview612;
    private TextView textview712;
    private TextView textview412;
    private TextView textview812;
    private TextView textview312;
    private TextView textview513;
    private TextView textview613;
    private TextView textview713;
    private TextView textview413;
    private TextView textview813;
    private TextView textview313;
    private TextView textview514;
    private TextView textview614;
    private TextView textview714;
    private TextView textview414;
    private TextView textview814;
    private TextView textview314;
    private TextView textview515;
    private TextView textview615;
    private TextView textview715;
    private TextView textview415;
    private TextView textview815;
    private TextView textview315;
    private TextView textview516;
    private TextView textview616;
    private TextView textview716;
    private TextView textview416;
    private TextView textview816;
    private TextView textview316;
    private TextView textviewqt;
    private TextView textviewatx;
    private TextView textviewgtr;
    private ImageView imageview3;
    private TextView textviewtotamt;
    private ListView listview1;
    private Spinner spinner2;

    private SharedPreferences data;
    private Intent in = new Intent();
    private Calendar cl = Calendar.getInstance();
    private AlertDialog.Builder option;

    @Override
    protected void onCreate(Bundle _savedInstanceState) {
        super.onCreate(_savedInstanceState);
        setContentView(R.layout.report);
        com.google.firebase.FirebaseApp.initializeApp(this);
        initialize(_savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1000);
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

        layout = (LinearLayout) findViewById(R.id.layout);
        linear = (LinearLayout) findViewById(R.id.linear);
        layout2 = (LinearLayout) findViewById(R.id.layout2);
        layout1 = (LinearLayout) findViewById(R.id.layout1);
        imageback = (ImageView) findViewById(R.id.imageback);
        imageview2 = (ImageView) findViewById(R.id.imageview2);
        textview20 = (TextView) findViewById(R.id.textview20);
        textview21 = (TextView) findViewById(R.id.textview21);
        linear01 = (LinearLayout) findViewById(R.id.linear01);
        linear02 = (LinearLayout) findViewById(R.id.linear02);
        linear03 = (LinearLayout) findViewById(R.id.linear03);
        linear04 = (LinearLayout) findViewById(R.id.linear04);
        linear05 = (LinearLayout) findViewById(R.id.linear05);
        linear06 = (LinearLayout) findViewById(R.id.linear06);
        linear07 = (LinearLayout) findViewById(R.id.linear07);
        linear08 = (LinearLayout) findViewById(R.id.linear08);
        linear09 = (LinearLayout) findViewById(R.id.linear09);
        linear010 = (LinearLayout) findViewById(R.id.linear010);
        linear011 = (LinearLayout) findViewById(R.id.linear011);
        linear012 = (LinearLayout) findViewById(R.id.linear012);
        linear013 = (LinearLayout) findViewById(R.id.linear013);
        linear014 = (LinearLayout) findViewById(R.id.linear014);
        linear015 = (LinearLayout) findViewById(R.id.linear015);
        linear016 = (LinearLayout) findViewById(R.id.linear016);
        textview501 = (TextView) findViewById(R.id.textview501);
        textview601 = (TextView) findViewById(R.id.textview601);
        textview701 = (TextView) findViewById(R.id.textview701);
        textview401 = (TextView) findViewById(R.id.textview401);
        textview801 = (TextView) findViewById(R.id.textview801);
        textview301 = (TextView) findViewById(R.id.textview301);
        textview502 = (TextView) findViewById(R.id.textview502);
        textview602 = (TextView) findViewById(R.id.textview602);
        textview702 = (TextView) findViewById(R.id.textview702);
        textview402 = (TextView) findViewById(R.id.textview402);
        textview802 = (TextView) findViewById(R.id.textview802);
        textview302 = (TextView) findViewById(R.id.textview302);
        textview503 = (TextView) findViewById(R.id.textview503);
        textview603 = (TextView) findViewById(R.id.textview603);
        textview703 = (TextView) findViewById(R.id.textview703);
        textview403 = (TextView) findViewById(R.id.textview403);
        textview803 = (TextView) findViewById(R.id.textview803);
        textview303 = (TextView) findViewById(R.id.textview303);
        textview504 = (TextView) findViewById(R.id.textview504);
        textview604 = (TextView) findViewById(R.id.textview604);
        textview704 = (TextView) findViewById(R.id.textview704);
        textview404 = (TextView) findViewById(R.id.textview404);
        textview804 = (TextView) findViewById(R.id.textview804);
        textview304 = (TextView) findViewById(R.id.textview304);
        textview505 = (TextView) findViewById(R.id.textview505);
        textview605 = (TextView) findViewById(R.id.textview605);
        textview705 = (TextView) findViewById(R.id.textview705);
        textview405 = (TextView) findViewById(R.id.textview405);
        textview805 = (TextView) findViewById(R.id.textview805);
        textview305 = (TextView) findViewById(R.id.textview305);
        textview506 = (TextView) findViewById(R.id.textview506);
        textview606 = (TextView) findViewById(R.id.textview606);
        textview706 = (TextView) findViewById(R.id.textview706);
        textview406 = (TextView) findViewById(R.id.textview406);
        textview806 = (TextView) findViewById(R.id.textview806);
        textview306 = (TextView) findViewById(R.id.textview306);
        textview507 = (TextView) findViewById(R.id.textview507);
        textview607 = (TextView) findViewById(R.id.textview607);
        textview707 = (TextView) findViewById(R.id.textview707);
        textview407 = (TextView) findViewById(R.id.textview407);
        textview807 = (TextView) findViewById(R.id.textview807);
        textview307 = (TextView) findViewById(R.id.textview307);
        textview508 = (TextView) findViewById(R.id.textview508);
        textview608 = (TextView) findViewById(R.id.textview608);
        textview708 = (TextView) findViewById(R.id.textview708);
        textview408 = (TextView) findViewById(R.id.textview408);
        textview808 = (TextView) findViewById(R.id.textview808);
        textview308 = (TextView) findViewById(R.id.textview308);
        textview509 = (TextView) findViewById(R.id.textview509);
        textview609 = (TextView) findViewById(R.id.textview609);
        textview709 = (TextView) findViewById(R.id.textview709);
        textview409 = (TextView) findViewById(R.id.textview409);
        textview809 = (TextView) findViewById(R.id.textview809);
        textview309 = (TextView) findViewById(R.id.textview309);
        textview510 = (TextView) findViewById(R.id.textview510);
        textview610 = (TextView) findViewById(R.id.textview610);
        textview710 = (TextView) findViewById(R.id.textview710);
        textview410 = (TextView) findViewById(R.id.textview410);
        textview810 = (TextView) findViewById(R.id.textview810);
        textview310 = (TextView) findViewById(R.id.textview310);
        textview511 = (TextView) findViewById(R.id.textview511);
        textview611 = (TextView) findViewById(R.id.textview611);
        textview711 = (TextView) findViewById(R.id.textview711);
        textview411 = (TextView) findViewById(R.id.textview411);
        textview811 = (TextView) findViewById(R.id.textview811);
        textview311 = (TextView) findViewById(R.id.textview311);
        textview512 = (TextView) findViewById(R.id.textview512);
        textview612 = (TextView) findViewById(R.id.textview612);
        textview712 = (TextView) findViewById(R.id.textview712);
        textview412 = (TextView) findViewById(R.id.textview412);
        textview812 = (TextView) findViewById(R.id.textview812);
        textview312 = (TextView) findViewById(R.id.textview312);
        textview513 = (TextView) findViewById(R.id.textview513);
        textview613 = (TextView) findViewById(R.id.textview613);
        textview713 = (TextView) findViewById(R.id.textview713);
        textview413 = (TextView) findViewById(R.id.textview413);
        textview813 = (TextView) findViewById(R.id.textview813);
        textview313 = (TextView) findViewById(R.id.textview313);
        textview514 = (TextView) findViewById(R.id.textview514);
        textview614 = (TextView) findViewById(R.id.textview614);
        textview714 = (TextView) findViewById(R.id.textview714);
        textview414 = (TextView) findViewById(R.id.textview414);
        textview814 = (TextView) findViewById(R.id.textview814);
        textview314 = (TextView) findViewById(R.id.textview314);
        textview515 = (TextView) findViewById(R.id.textview515);
        textview615 = (TextView) findViewById(R.id.textview615);
        textview715 = (TextView) findViewById(R.id.textview715);
        textview415 = (TextView) findViewById(R.id.textview415);
        textview815 = (TextView) findViewById(R.id.textview815);
        textview315 = (TextView) findViewById(R.id.textview315);
        textview516 = (TextView) findViewById(R.id.textview516);
        textview616 = (TextView) findViewById(R.id.textview616);
        textview716 = (TextView) findViewById(R.id.textview716);
        textview416 = (TextView) findViewById(R.id.textview416);
        textview816 = (TextView) findViewById(R.id.textview816);
        textview316 = (TextView) findViewById(R.id.textview316);
        textviewqt = (TextView) findViewById(R.id.textviewqt);
        textviewatx = (TextView) findViewById(R.id.textviewatx);
        textviewgtr = (TextView) findViewById(R.id.textviewgtr);
        imageview3 = (ImageView) findViewById(R.id.imageview3);
        textviewtotamt = (TextView) findViewById(R.id.textviewtotamt);
        listview1 = (ListView) findViewById(R.id.listview1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        data = getSharedPreferences("rep", Activity.MODE_PRIVATE);
        option = new AlertDialog.Builder(this);

        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                in.setAction(Intent.ACTION_VIEW);
                in.setClass(getApplicationContext(), MenuActivity.class);
                in.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(in);
                finish();
            }
        });

        imageview2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                layout1.setVisibility(View.VISIBLE);
                layout2.setVisibility(View.GONE);
                r = 0;
            }
        });

        imageview3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                try {
                    android.graphics.pdf.PdfDocument document = new android.graphics.pdf.PdfDocument();
                    android.graphics.pdf.PdfDocument.PageInfo pageInfo = new android.graphics.pdf.PdfDocument.PageInfo.Builder(layout2.getWidth(), layout2.getHeight(), 1).create();
                    android.graphics.pdf.PdfDocument.Page page = document.startPage(pageInfo);
                    Canvas canvas = page.getCanvas();
                    Paint paint = new Paint();
                    canvas.drawPaint(paint);
                    layout2.draw(canvas);
                    document.finishPage(page);
                    filename = mpr.get((int) pos).get("dat").toString().concat("1.pdf");
                    path = FileUtil.getExternalStorageDir().concat("/Easy Bill/").concat(filename);
                    FileUtil.writeFile(path, "");
                    java.io.File myFile = new
                            java.io.File(path);
                    java.io.FileOutputStream fOut = new java.io.FileOutputStream(myFile);
                    java.io.OutputStreamWriter myOutWriter = new java.io.OutputStreamWriter(fOut);
                    document.writeTo(fOut);
                    document.close();
                    myOutWriter.close();
                    fOut.close();
                    Toast.makeText(getBaseContext(), "File Saved", Toast.LENGTH_LONG).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                _saveView(layout2);
            }
        });

        listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
                final int _position = _param3;
                r = 1;
                if (Double.parseDouble(mpr.get((int) _position).get("1q").toString()) == 0) {
                    linear01.setVisibility(View.GONE);
                } else {
                    linear01.setVisibility(View.VISIBLE);
                }
                if (Double.parseDouble(mpr.get((int) _position).get("2q").toString()) == 0) {
                    linear02.setVisibility(View.GONE);
                } else {
                    linear02.setVisibility(View.VISIBLE);
                }
                if (Double.parseDouble(mpr.get((int) _position).get("3q").toString()) == 0) {
                    linear03.setVisibility(View.GONE);
                } else {
                    linear03.setVisibility(View.VISIBLE);
                }
                if (Double.parseDouble(mpr.get((int) _position).get("4q").toString()) == 0) {
                    linear04.setVisibility(View.GONE);
                } else {
                    linear04.setVisibility(View.VISIBLE);
                }
                if (Double.parseDouble(mpr.get((int) _position).get("5q").toString()) == 0) {
                    linear05.setVisibility(View.GONE);
                } else {
                    linear05.setVisibility(View.VISIBLE);
                }
                if (Double.parseDouble(mpr.get((int) _position).get("6q").toString()) == 0) {
                    linear06.setVisibility(View.GONE);
                } else {
                    linear06.setVisibility(View.VISIBLE);
                }
                if (Double.parseDouble(mpr.get((int) _position).get("7q").toString()) == 0) {
                    linear07.setVisibility(View.GONE);
                } else {
                    linear07.setVisibility(View.VISIBLE);
                }
                if (Double.parseDouble(mpr.get((int) _position).get("8q").toString()) == 0) {
                    linear08.setVisibility(View.GONE);
                } else {
                    linear08.setVisibility(View.VISIBLE);
                }
                if (Double.parseDouble(mpr.get((int) _position).get("9q").toString()) == 0) {
                    linear09.setVisibility(View.GONE);
                } else {
                    linear09.setVisibility(View.VISIBLE);
                }
                if (Double.parseDouble(mpr.get((int) _position).get("10q").toString()) == 0) {
                    linear010.setVisibility(View.GONE);
                } else {
                    linear010.setVisibility(View.VISIBLE);
                }
                if (Double.parseDouble(mpr.get((int) _position).get("11q").toString()) == 0) {
                    linear011.setVisibility(View.GONE);
                } else {
                    linear01.setVisibility(View.VISIBLE);
                }
                if (Double.parseDouble(mpr.get((int) _position).get("12q").toString()) == 0) {
                    linear012.setVisibility(View.GONE);
                } else {
                    linear012.setVisibility(View.VISIBLE);
                }
                if (Double.parseDouble(mpr.get((int) _position).get("13q").toString()) == 0) {
                    linear013.setVisibility(View.GONE);
                } else {
                    linear013.setVisibility(View.VISIBLE);
                }
                if (Double.parseDouble(mpr.get((int) _position).get("14q").toString()) == 0) {
                    linear014.setVisibility(View.GONE);
                } else {
                    linear014.setVisibility(View.VISIBLE);
                }
                if (Double.parseDouble(mpr.get((int) _position).get("15q").toString()) == 0) {
                    linear015.setVisibility(View.GONE);
                } else {
                    linear015.setVisibility(View.VISIBLE);
                }
                if (Double.parseDouble(mpr.get((int) _position).get("16q").toString()) == 0) {
                    linear016.setVisibility(View.GONE);
                } else {
                    linear016.setVisibility(View.VISIBLE);
                }
                textview20.setText(mpr.get((int) _position).get("name").toString());
                textview21.setText(mpr.get((int) _position).get("dat").toString());
                textview501.setText(mpr.get((int) _position).get("1i").toString());
                textview502.setText(mpr.get((int) _position).get("2i").toString());
                textview503.setText(mpr.get((int) _position).get("3i").toString());
                textview504.setText(mpr.get((int) _position).get("4i").toString());
                textview505.setText(mpr.get((int) _position).get("5i").toString());
                textview506.setText(mpr.get((int) _position).get("6i").toString());
                textview507.setText(mpr.get((int) _position).get("7i").toString());
                textview508.setText(mpr.get((int) _position).get("8i").toString());
                textview509.setText(mpr.get((int) _position).get("9i").toString());
                textview510.setText(mpr.get((int) _position).get("10i").toString());
                textview511.setText(mpr.get((int) _position).get("11i").toString());
                textview512.setText(mpr.get((int) _position).get("12i").toString());
                textview513.setText(mpr.get((int) _position).get("13i").toString());
                textview514.setText(mpr.get((int) _position).get("14i").toString());
                textview515.setText(mpr.get((int) _position).get("15i").toString());
                textview516.setText(mpr.get((int) _position).get("16i").toString());
                textview601.setText(mpr.get((int) _position).get("1q").toString());
                textview602.setText(mpr.get((int) _position).get("2q").toString());
                textview603.setText(mpr.get((int) _position).get("3q").toString());
                textview604.setText(mpr.get((int) _position).get("4q").toString());
                textview605.setText(mpr.get((int) _position).get("5q").toString());
                textview606.setText(mpr.get((int) _position).get("6q").toString());
                textview607.setText(mpr.get((int) _position).get("7q").toString());
                textview608.setText(mpr.get((int) _position).get("8q").toString());
                textview609.setText(mpr.get((int) _position).get("9q").toString());
                textview610.setText(mpr.get((int) _position).get("10q").toString());
                textview611.setText(mpr.get((int) _position).get("11q").toString());
                textview612.setText(mpr.get((int) _position).get("12q").toString());
                textview613.setText(mpr.get((int) _position).get("13q").toString());
                textview614.setText(mpr.get((int) _position).get("14q").toString());
                textview615.setText(mpr.get((int) _position).get("15q").toString());
                textview616.setText(mpr.get((int) _position).get("16q").toString());
                textview701.setText(mpr.get((int) _position).get("1r").toString());
                textview702.setText(mpr.get((int) _position).get("2r").toString());
                textview703.setText(mpr.get((int) _position).get("3r").toString());
                textview704.setText(mpr.get((int) _position).get("4r").toString());
                textview705.setText(mpr.get((int) _position).get("5r").toString());
                textview706.setText(mpr.get((int) _position).get("6r").toString());
                textview707.setText(mpr.get((int) _position).get("7r").toString());
                textview708.setText(mpr.get((int) _position).get("8r").toString());
                textview709.setText(mpr.get((int) _position).get("9r").toString());
                textview710.setText(mpr.get((int) _position).get("10r").toString());
                textview711.setText(mpr.get((int) _position).get("11r").toString());
                textview712.setText(mpr.get((int) _position).get("12r").toString());
                textview713.setText(mpr.get((int) _position).get("13r").toString());
                textview714.setText(mpr.get((int) _position).get("14r").toString());
                textview715.setText(mpr.get((int) _position).get("15r").toString());
                textview716.setText(mpr.get((int) _position).get("16r").toString());
                textview801.setText(mpr.get((int) _position).get("1gr").toString());
                textview802.setText(mpr.get((int) _position).get("2gr").toString());
                textview803.setText(mpr.get((int) _position).get("3gr").toString());
                textview804.setText(mpr.get((int) _position).get("4gr").toString());
                textview805.setText(mpr.get((int) _position).get("5gr").toString());
                textview806.setText(mpr.get((int) _position).get("6gr").toString());
                textview807.setText(mpr.get((int) _position).get("7gr").toString());
                textview808.setText(mpr.get((int) _position).get("8gr").toString());
                textview809.setText(mpr.get((int) _position).get("9gr").toString());
                textview810.setText(mpr.get((int) _position).get("10gr").toString());
                textview811.setText(mpr.get((int) _position).get("11gr").toString());
                textview812.setText(mpr.get((int) _position).get("12gr").toString());
                textview813.setText(mpr.get((int) _position).get("13gr").toString());
                textview814.setText(mpr.get((int) _position).get("14gr").toString());
                textview815.setText(mpr.get((int) _position).get("15gr").toString());
                textview816.setText(mpr.get((int) _position).get("16gr").toString());
                textview301.setText(mpr.get((int) _position).get("1g").toString());
                textview302.setText(mpr.get((int) _position).get("2g").toString());
                textview303.setText(mpr.get((int) _position).get("3g").toString());
                textview304.setText(mpr.get((int) _position).get("4g").toString());
                textview305.setText(mpr.get((int) _position).get("5g").toString());
                textview306.setText(mpr.get((int) _position).get("6g").toString());
                textview307.setText(mpr.get((int) _position).get("7g").toString());
                textview308.setText(mpr.get((int) _position).get("8g").toString());
                textview309.setText(mpr.get((int) _position).get("9g").toString());
                textview310.setText(mpr.get((int) _position).get("10g").toString());
                textview311.setText(mpr.get((int) _position).get("11g").toString());
                textview312.setText(mpr.get((int) _position).get("12g").toString());
                textview313.setText(mpr.get((int) _position).get("13g").toString());
                textview314.setText(mpr.get((int) _position).get("14g").toString());
                textview315.setText(mpr.get((int) _position).get("15g").toString());
                textview316.setText(mpr.get((int) _position).get("16g").toString());
                textview401.setText(mpr.get((int) _position).get("1a").toString());
                textview402.setText(mpr.get((int) _position).get("2a").toString());
                textview403.setText(mpr.get((int) _position).get("3a").toString());
                textview404.setText(mpr.get((int) _position).get("4a").toString());
                textview405.setText(mpr.get((int) _position).get("5a").toString());
                textview406.setText(mpr.get((int) _position).get("6a").toString());
                textview407.setText(mpr.get((int) _position).get("7a").toString());
                textview408.setText(mpr.get((int) _position).get("8a").toString());
                textview409.setText(mpr.get((int) _position).get("9a").toString());
                textview410.setText(mpr.get((int) _position).get("10a").toString());
                textview411.setText(mpr.get((int) _position).get("11a").toString());
                textview412.setText(mpr.get((int) _position).get("12a").toString());
                textview413.setText(mpr.get((int) _position).get("13a").toString());
                textview414.setText(mpr.get((int) _position).get("14a").toString());
                textview415.setText(mpr.get((int) _position).get("15a").toString());
                textview416.setText(mpr.get((int) _position).get("16a").toString());
                textviewqt.setText(mpr.get((int) _position).get("qt").toString());
                textviewgtr.setText(mpr.get((int) _position).get("gstrs").toString());
                textviewtotamt.setText(mpr.get((int) _position).get("amount").toString());
                textviewatx.setText(mpr.get((int) _position).get("total").toString());
                layout1.setVisibility(View.GONE);
                layout2.setVisibility(View.VISIBLE);
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
                final int _position = _param3;
                _loadList(mthyr.get((int) (_position)));
            }

            @Override
            public void onNothingSelected(AdapterView<?> _param1) {

            }
        });
    }

    private void initializeLogic() {
        r = 0;
        layout2.setVisibility(View.GONE);
        cl = Calendar.getInstance();
        mthyr.add((int) (0), "01");
        mthyr.add((int) (1), "02");
        mthyr.add((int) (2), "03");
        mthyr.add((int) (3), "04");
        mthyr.add((int) (4), "05");
        mthyr.add((int) (5), "06");
        mthyr.add((int) (6), "07");
        mthyr.add((int) (7), "08");
        mthyr.add((int) (8), "09");
        mthyr.add((int) (9), "10");
        mthyr.add((int) (10), "11");
        mthyr.add((int) (11), "12");
        spinner2.setAdapter(new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_dropdown_item, mthyr));
        ((ArrayAdapter) spinner2.getAdapter()).notifyDataSetChanged();
        spinner2.setSelection((int) (Double.parseDouble(new SimpleDateFormat("MM").format(cl.getTime())) - 1));
        _loadList(new SimpleDateFormat("MM").format(cl.getTime()));
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
            in.setAction(Intent.ACTION_VIEW);
            in.setClass(getApplicationContext(), MenuActivity.class);
            in.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(in);
            finish();
        } else {
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.GONE);
            r = 0;
        }
    }

    private void _loadList(final String _keymth) {
        if (!data.getString(_keymth, "").equals("")) {
            mpr = new Gson().fromJson(data.getString(_keymth, ""), new TypeToken<ArrayList<HashMap<String, Object>>>() {
            }.getType());
        } else {

        }
        if (!(mpr.size() == 0)) {
            listview1.setAdapter(new Listview1Adapter(mpr));
            ((BaseAdapter) listview1.getAdapter()).notifyDataSetChanged();
        } else {
            SketchwareUtil.showMessage(getApplicationContext(), "No record found");
        }
    }


    private void _saveView(final View _view) {
        filename = mpr.get((int) pos).get("dat").toString().concat(".jpg");
        pathfile = FileUtil.getExternalStorageDir().concat("/Easy Bill/").concat(filename);
        Bitmap returnedBitmap = Bitmap.createBitmap(_view.getWidth(), _view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        android.graphics.drawable.Drawable bgDrawable = _view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        _view.draw(canvas);
        java.io.File pictureFile = new java.io.File(pathfile);
        if (pictureFile == null) {
            SketchwareUtil.showMessage(getApplicationContext(),"Error creating media file, check storage permissions: ");
            return;
        }
        try {
            java.io.FileOutputStream fos = new java.io.FileOutputStream(pictureFile);
            returnedBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
            SketchwareUtil.showMessage(getApplicationContext(),"Image Saved");
        } catch (java.io.FileNotFoundException e) {
            SketchwareUtil.showMessage(getApplicationContext(),"File not found: " + e.getMessage());
        } catch (java.io.IOException e) {
            SketchwareUtil.showMessage(getApplicationContext(),"Error accessing file: " + e.getMessage());
        }
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
                _v = _inflater.inflate(R.layout.cust_report, null);
            }

            final LinearLayout linear1 = (LinearLayout) _v.findViewById(R.id.linear1);
            final LinearLayout linear2 = (LinearLayout) _v.findViewById(R.id.linear2);
            final LinearLayout linear3 = (LinearLayout) _v.findViewById(R.id.linear3);
            final TextView textview5 = (TextView) _v.findViewById(R.id.textview5);
            final TextView textview1 = (TextView) _v.findViewById(R.id.textview1);
            final TextView textview2 = (TextView) _v.findViewById(R.id.textview2);
            final TextView textview3 = (TextView) _v.findViewById(R.id.textview3);
            final TextView textview4 = (TextView) _v.findViewById(R.id.textview4);
            final ImageView imageview1 = (ImageView) _v.findViewById(R.id.imageview1);

            textview1.setText(mpr.get((int) _position).get("name").toString());
            textview2.setText(mpr.get((int) _position).get("dat").toString());
            textview4.setText(mpr.get((int) _position).get("amount").toString());
            textview5.setText(String.valueOf((long) (_position + 1)));
            imageview1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View _view) {
                    b = mpr.get((int) _position).get("name").toString().concat("\n".concat(mpr.get((int) _position).get("dat").toString().concat("\n".concat(mpr.get((int) _position).get("amount").toString()))));
                    Intent inb = new Intent(android.content.Intent.ACTION_SEND);
                    inb.setType("text/plain");
                    inb.putExtra(android.content.Intent.EXTRA_TEXT, b);
                    startActivity(Intent.createChooser(inb, "Share using"));
                }
            });

            return _v;
        }
    }

}
