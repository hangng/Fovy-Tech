package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.example.myapplication.component.AcBase;
import com.example.myapplication.component.InfoUtil;
import com.example.myapplication.fragment.ExploreFragment;
import com.example.myapplication.fragment.FavoriteFragment;
import com.example.myapplication.model.CoffeeData;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MenuActivity extends AcBase implements View.OnClickListener {
    public static String SHARE_COFFEE_DATA = "SHARE_COFFEE_DATA";

    private LinearLayout mLlExplore, mLlFavorite;
    private int lastfragmentId = -1;

    private CoffeeData mCoffeeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initComponents();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }

    private void initComponents() {
        mLlExplore = findViewById(R.id.ll_explore);
        mLlFavorite = findViewById(R.id.ll_favorite);
        mLlExplore.setOnClickListener(this);
        mLlFavorite.setOnClickListener(this);
        readFromJson();

    }

    @Override
    protected void onResume() {
        if (lastfragmentId == -1 || lastfragmentId == 0) {
            replaceFragment(R.string.explore, ExploreFragment.newInstance(mCoffeeData));
        } else if (lastfragmentId == 1) {
            replaceFragment(R.string.favorite, FavoriteFragment.newInstance());
        }
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if (isFastDoubleClick(v)) {
            return;
        }

        if (v == mLlExplore) {
            lastfragmentId = 0;
            clearStack();
            replaceFragment(R.string.explore, ExploreFragment.newInstance(mCoffeeData));
        } else if (v == mLlFavorite) {
            lastfragmentId = 1;
            clearStack();
            replaceFragment(R.string.favorite, FavoriteFragment.newInstance());
        }

    }

    @Override
    public void onBackPressed() {
        clearStack();
        super.onBackPressed();
    }


    private void readFromJson() {
        AssetManager assetManager = getAssets();
        Gson gson = new Gson();

        try {
            InputStream inputStream = assetManager.open("dummy_data.json");
            JsonReader reader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));
            mCoffeeData = gson.fromJson(reader, CoffeeData.class);
            InfoUtil.getInstance().setCoffeeData(mCoffeeData);
            replaceFragment(R.string.explore, ExploreFragment.newInstance(mCoffeeData));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

}