package com.example.myapplication.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CoffeeAdapter;
import com.example.myapplication.adapter.CoffeeCategoryAdapter;
import com.example.myapplication.component.FrBase;
import com.example.myapplication.component.InfoUtil;
import com.example.myapplication.component.NoScrollGridLayoutManager;
import com.example.myapplication.datahelper.CoffeeDataHelper;
import com.example.myapplication.model.CoffeeCategory;

import java.util.ArrayList;

public class FavoriteFragment extends FrBase implements CoffeeAdapter.Listener, CoffeeCategoryAdapter.Listener  {
    private final String SAVE_DATA_STATE = "SAVE_DATA_STATE";
    private RecyclerView mRvFavorite;
    private CoffeeAdapter mAdpCoffee;
    private GridLayoutManager mGlMgr;
    private CoffeeDataHelper mDataHelper;
    private ArrayList<CoffeeCategory> mAryCoffeeCategory = new ArrayList<>();


    public static FavoriteFragment newInstance() {
        Bundle args = new Bundle();
        FavoriteFragment fragment = new FavoriteFragment();
        fragment.setArguments(args);
        return new FavoriteFragment();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle;

        if (savedInstanceState != null) {
            bundle = savedInstanceState;
        } else {
            bundle = getArguments();
        }

        if (bundle != null) {
            mDataHelper = (CoffeeDataHelper) bundle.getSerializable(SAVE_DATA_STATE);
        } else {
            mDataHelper = new CoffeeDataHelper();
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, R.layout.fragment_favorite, R.string.favorite_id);
        initComponents();
        return mRootView;
    }


    private void initComponents() {
        mRvFavorite = mRootView.findViewById(R.id.rv_favorite);
        mGlMgr = new NoScrollGridLayoutManager(mContext, 2);
        mAdpCoffee = new CoffeeAdapter(mContext, mDataHelper.getCoffeeFav(), this);
        mRvFavorite.setLayoutManager(mGlMgr);
        mRvFavorite.setAdapter(mAdpCoffee);

        mAdpCoffee.notifyDataSetChanged();

        Log.i("TAG","checking size = " + InfoUtil.getInstance().getCoffee().size());
    }

    @Override
    public void onHeaderClick(int iPosition) {

    }

    @Override
    public void onBodyClick(int iPosition) {

    }

    @Override
    public void onFavClick(int iPosition) {

    }

}
