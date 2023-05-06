package com.example.myapplication;

import static com.example.myapplication.component.FrBase.isFastDoubleClick;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.component.AcBase;
import com.example.myapplication.fragment.ExploreFragment;
import com.example.myapplication.fragment.FavoriteFragment;
import com.example.myapplication.fragment.OnDataChangeListener;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.FileWriter;

public class MainActivity extends AcBase implements View.OnClickListener {

    private TextView mTvExplore, mTvFavorite;
    private ImageView mIvExplore, mIvFavorite;
    private LinearLayout mLlExplore, mLlFavorite;
    private int lastfragmentId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initComponents();
    }


    private void initComponents() {
        mTvExplore = findViewById(R.id.tv_explore);
        mTvFavorite = findViewById(R.id.tv_fav);
        mIvFavorite = findViewById(R.id.iv_fav);
        mIvExplore = findViewById(R.id.iv_explore);
        mLlExplore = findViewById(R.id.ll_explore);
        mLlFavorite = findViewById(R.id.ll_favorite);
        mLlExplore.setOnClickListener(this);
        mLlFavorite.setOnClickListener(this);

        replaceFragment(R.string.explore, ExploreFragment.newInstance());

    }

    @Override
    protected void onResume() {

        if (lastfragmentId == -1 || lastfragmentId == 0) {
            replaceFragment(R.string.explore, ExploreFragment.newInstance());
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
            replaceFragment(R.string.explore, ExploreFragment.newInstance());
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



}