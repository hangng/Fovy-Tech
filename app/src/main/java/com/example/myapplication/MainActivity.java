package com.example.myapplication;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.component.AcBase;
import com.example.myapplication.component.InfoUtil;
import com.example.myapplication.fragment.ExploreFragment;
import com.example.myapplication.fragment.FavoriteFragment;
import com.example.myapplication.model.Coffee;
import com.example.myapplication.model.CoffeeCategory;
import com.example.myapplication.model.CoffeeData;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AcBase implements View.OnClickListener {

    private TextView mTvExplore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvExplore = findViewById(R.id.tv_explore);
        mTvExplore.setOnClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }


    @Override
    public void onClick(View v) {
        if (isFastDoubleClick(v)) {
            return;
        }
        if (v == mTvExplore) {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }

    }


}