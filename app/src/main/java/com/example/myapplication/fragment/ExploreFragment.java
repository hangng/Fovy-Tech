package com.example.myapplication.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CoffeeAdapter;
import com.example.myapplication.adapter.CoffeeCategoryAdapter;
import com.example.myapplication.component.FrBase;
import com.example.myapplication.component.NoScrollGridLayoutManager;
import com.example.myapplication.component.InfoUtil;
import com.example.myapplication.datahelper.CoffeeDataHelper;
import com.example.myapplication.model.Coffee;

public class ExploreFragment extends FrBase implements CoffeeAdapter.Listener, CoffeeCategoryAdapter.Listener {


    private final String SAVE_DATA_STATE = "SAVE_DATA_STATE";
    private RecyclerView mRvHeader, mRvBody;
    private CoffeeAdapter mAdpCoffee;
    private CoffeeCategoryAdapter mAdpCatCoffee;
    private LinearLayoutManager mLlMgr;
    private GridLayoutManager mGlMgr;
    private CoffeeDataHelper mDataHelper;

    public static ExploreFragment newInstance() {
        Bundle args = new Bundle();
        ExploreFragment fragment = new ExploreFragment();
        fragment.setArguments(args);
        return new ExploreFragment();
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
        super.onCreateView(inflater, container, R.layout.fragment_explore, R.string.explore_id);
        initComponents();
        return mRootView;
    }


    private void initComponents() {

        mRvBody = mRootView.findViewById(R.id.rv_body);
        mGlMgr = new NoScrollGridLayoutManager(mContext, 2);
        mAdpCoffee = new CoffeeAdapter(mContext, mDataHelper.getCoffeeLst(), this);
        mRvBody.setLayoutManager(mGlMgr);
        mRvBody.setAdapter(mAdpCoffee);

        mAdpCoffee.notifyDataSetChanged();

        mRvHeader = mRootView.findViewById(R.id.rv_header);
        mLlMgr = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false);

        mAdpCatCoffee = new CoffeeCategoryAdapter(mContext, mDataHelper.getCoffeeCategory(), this);
        mRvHeader.setLayoutManager(mLlMgr);
        mRvHeader.setAdapter(mAdpCatCoffee);

        mAdpCatCoffee.notifyDataSetChanged();

    }


    @Override
    public void onHeaderClick(int iPosition) {

    }

    @Override
    public void onBodyClick(int iPosition) {

    }

    @Override
    public void onFavClick(int iPosition) {
        Coffee coffee = mDataHelper.getCoffeeLst().get(iPosition);

        if (coffee.isFavorite()) {
            coffee.setFavorite(false);
        } else {
            coffee.setFavorite(true);
        }

        InfoUtil.getInstance().setCoffee(mDataHelper.getCoffeeLst());

        mAdpCoffee.notifyDataSetChanged();
    }

}
