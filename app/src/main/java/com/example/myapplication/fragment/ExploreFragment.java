package com.example.myapplication.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Coffee_Details;
import com.example.myapplication.MainActivity;
import com.example.myapplication.MenuActivity;
import com.example.myapplication.R;
import com.example.myapplication.adapter.CoffeeAdapter;
import com.example.myapplication.adapter.CoffeeCategoryAdapter;
import com.example.myapplication.component.FrBase;
import com.example.myapplication.component.NoScrollGridLayoutManager;
import com.example.myapplication.component.InfoUtil;
import com.example.myapplication.datahelper.CoffeeDataHelper;
import com.example.myapplication.model.Coffee;
import com.example.myapplication.model.CoffeeCategory;
import com.example.myapplication.model.CoffeeData;
import com.example.myapplication.model.CoffeeFavorite;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ExploreFragment extends FrBase implements CoffeeAdapter.Listener, CoffeeCategoryAdapter.Listener, View.OnClickListener {

    private RecyclerView mRvHeader, mRvBody;
    private CoffeeAdapter mAdpCoffee;
    private CoffeeCategoryAdapter mAdpCatCoffee;
    private LinearLayoutManager mLlMgr;
    private GridLayoutManager mGlMgr;
    private ArrayList<Coffee> mAryCoffee = new ArrayList<>();
    private ArrayList<CoffeeCategory> mAryCoffeeCat = new ArrayList<>();
    private CoffeeData mCoffeeData;
    private EditText mEtExplore;
    private ImageView mIvSearch;


    public static ExploreFragment newInstance(CoffeeData coffeeData) {
        Bundle bundle = new Bundle();
        ExploreFragment fragment = new ExploreFragment();
        bundle.putSerializable(MenuActivity.SHARE_COFFEE_DATA, coffeeData);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
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
            mCoffeeData = (CoffeeData) bundle.getSerializable(MenuActivity.SHARE_COFFEE_DATA);
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, R.layout.fragment_explore, R.string.explore_id);


        if (InfoUtil.getInstance().getCoffeeFav() == null) {
            mAryCoffee.addAll(InfoUtil.getInstance().getCoffeeData().getCoffeeData());
        } else {
            mAryCoffee.addAll(InfoUtil.getInstance().getCoffeeFav());
        }


        mIvSearch = mRootView.findViewById(R.id.iv_search);
        mEtExplore = mRootView.findViewById(R.id.et_explore);

        mAryCoffeeCat.clear();
        mAryCoffeeCat.add(new CoffeeCategory("", "All"));
        mAryCoffeeCat.addAll(mCoffeeData.getCoffeeCategory());

        mRvBody = mRootView.findViewById(R.id.rv_body);

        mGlMgr = new GridLayoutManager(mContext, 2);
        mAdpCoffee = new CoffeeAdapter(mContext, mAryCoffee, this);
        mRvBody.setLayoutManager(mGlMgr);
        mRvBody.setAdapter(mAdpCoffee);

        mRvHeader = mRootView.findViewById(R.id.rv_header);
        mLlMgr = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);

        mAdpCatCoffee = new CoffeeCategoryAdapter(mContext, mAryCoffeeCat, this);
        mRvHeader.setLayoutManager(mLlMgr);
        mRvHeader.setAdapter(mAdpCatCoffee);

        mIvSearch.setOnClickListener(this);

        if (InfoUtil.getInstance().getSelectItemPosition() > -1) {
            mAryCoffee.clear();
            int firstVisiblePosition = mLlMgr.findFirstVisibleItemPosition();
            int lastVisiblePosition = mLlMgr.findLastVisibleItemPosition();

            ArrayList<Coffee> coffees = new ArrayList<>();
            if (InfoUtil.getInstance().getSelectItemPosition() > 0) {
                mAdpCatCoffee.setSelectPosition(InfoUtil.getInstance().getSelectItemPosition());


                if (InfoUtil.getInstance().getCoffeeFav().size() > 0) {

                    for (int i = 0; i < InfoUtil.getInstance().getCoffeeFav().size(); i++) {
                        Coffee coffee = InfoUtil.getInstance().getCoffeeFav().get(i);
                        if (InfoUtil.getInstance().getSelectItemPosition() == Integer.parseInt(InfoUtil.getInstance().getCoffeeFav().get(i).getCatId())) {
                            coffees.add(new Coffee(coffee.getName(), coffee.getTime(), coffee.getDescription(), coffee.getIngredients(), coffee.getInstructions(), coffee.isFavorite(), coffee.getUrl(), coffee.getServe(), coffee.getCatId()));
                        }
                    }
                } else {
                    for (int i = 0; i < InfoUtil.getInstance().getCoffeeData().getCoffeeData().size(); i++) {
                        Coffee coffee = InfoUtil.getInstance().getCoffeeData().getCoffeeData().get(i);
                        if (InfoUtil.getInstance().getSelectItemPosition() == Integer.parseInt(InfoUtil.getInstance().getCoffeeData().getCoffeeData().get(i).getCatId())) {
                            coffees.add(new Coffee(coffee.getName(), coffee.getTime(), coffee.getDescription(), coffee.getIngredients(), coffee.getInstructions(), coffee.isFavorite(), coffee.getUrl(), coffee.getServe(), coffee.getCatId()));
                        }
                    }
                }


                mRvHeader.smoothScrollToPosition(InfoUtil.getInstance().getSelectItemPosition());
                mAdpCatCoffee.setSelectPosition(InfoUtil.getInstance().getSelectItemPosition());
                mAryCoffee.addAll(coffees);
            } else {
                if (InfoUtil.getInstance().getCoffeeFav().size() > 0) {

                    for (int i = 0; i < InfoUtil.getInstance().getCoffeeFav().size(); i++) {
                        Coffee coffee = InfoUtil.getInstance().getCoffeeFav().get(i);
//                        if (InfoUtil.getInstance().getSelectItemPosition() == Integer. parseInt(InfoUtil.getInstance().getCoffeeFav().get(i).getCatId())) {
                        coffees.add(new Coffee(coffee.getName(), coffee.getTime(), coffee.getDescription(), coffee.getIngredients(), coffee.getInstructions(), coffee.isFavorite(), coffee.getUrl(), coffee.getServe(), coffee.getCatId()));
//                        }
                    }
                } else {
                    for (int i = 0; i < InfoUtil.getInstance().getCoffeeData().getCoffeeData().size(); i++) {
                        Coffee coffee = InfoUtil.getInstance().getCoffeeData().getCoffeeData().get(i);
                        if (InfoUtil.getInstance().getSelectItemPosition() == Integer.parseInt(InfoUtil.getInstance().getCoffeeData().getCoffeeData().get(i).getCatId())) {
                            coffees.add(new Coffee(coffee.getName(), coffee.getTime(), coffee.getDescription(), coffee.getIngredients(), coffee.getInstructions(), coffee.isFavorite(), coffee.getUrl(), coffee.getServe(), coffee.getCatId()));
                        }
                    }
                }

                if (0 <= firstVisiblePosition || 0 >= lastVisiblePosition) {
                    mLlMgr.smoothScrollToPosition(mRvHeader, new RecyclerView.State(), InfoUtil.getInstance().getSelectItemPosition());
                }
                mAdpCatCoffee.setSelectPosition(0);
                mAryCoffee.addAll(InfoUtil.getInstance().getCoffeeFav());
            }

            mAdpCatCoffee.notifyDataSetChanged();
            mAdpCoffee.notifyDataSetChanged();

        }


        return mRootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onBodyClick(int iPosition) {
        Coffee coffee = mAryCoffee.get(iPosition);
        Intent intent = new Intent(mContext, Coffee_Details.class);
        InfoUtil.getInstance().setSelectCoffee(coffee);
        mContext.startActivity(intent);
    }

    @Override
    public void onFavClick(int iPosition) {

        Coffee coffee = mAryCoffee.get(iPosition);

        if (coffee.isFavorite()) {
            coffee.setFavorite(false);
        } else {
            coffee.setFavorite(true);
        }

        for (Coffee coff : mAryCoffee) {
            if (coffee.getName().matches(coff.getName())) {
                coff.setFavorite(coffee.isFavorite());
            }
        }

        InfoUtil.getInstance().setCoffeeFav(mAryCoffee);
        InfoUtil.getInstance().setCoffee(mAryCoffee);

        mAdpCoffee.notifyDataSetChanged();
    }

    @Override
    public void onHeaderClick(int iPosition) {
        mAryCoffee.clear();
        ArrayList<Coffee> coffees = new ArrayList<>();
        if (iPosition == 0) {
            mAryCoffee.addAll(mCoffeeData.getCoffeeData());
            mAdpCatCoffee.setSelectPosition(0);
            InfoUtil.getInstance().setSelectItemPosition(0);
        } else {
            for (int i = 0; i < InfoUtil.getInstance().getCoffeeData().getCoffeeData().size(); i++) {
                Coffee coffee = InfoUtil.getInstance().getCoffeeData().getCoffeeData().get(i);
                if (iPosition == Integer.parseInt(InfoUtil.getInstance().getCoffeeData().getCoffeeData().get(i).getCatId())) {
                    coffees.add(new Coffee(coffee.getName(), coffee.getTime(), coffee.getDescription(), coffee.getIngredients(), coffee.getInstructions(), coffee.isFavorite(), coffee.getUrl(), coffee.getServe(), coffee.getCatId()));
                }
            }

            InfoUtil.getInstance().setSelectItemPosition(iPosition);
            mAdpCatCoffee.setSelectPosition(iPosition);
            mAryCoffee.addAll(coffees);
        }

        mAdpCatCoffee.notifyDataSetChanged();
        mAdpCoffee.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        if (v == mIvSearch) {
            int position = 0;
            String name = mEtExplore.getText().toString();
            ArrayList<Coffee> aryCoffeeLst = new ArrayList<>();
            int firstVisiblePosition = mLlMgr.findFirstVisibleItemPosition();
            int lastVisiblePosition = mLlMgr.findLastVisibleItemPosition();


            if (name.toLowerCase().equals("All".toLowerCase())) {
                mAryCoffee.clear();
                mAryCoffee.addAll(InfoUtil.getInstance().getCoffeeData().getCoffeeData());
                mRvHeader.smoothScrollToPosition(0);
                mAdpCatCoffee.setSelectPosition(0);
                InfoUtil.getInstance().setSelectItemPosition(0);
            } else {
                for (int i = 1; i < mAryCoffeeCat.size(); i++) {
                    if (mAryCoffeeCat.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                        position = i;
                    }
                }
                for (int i = 0; i < InfoUtil.getInstance().getCoffeeData().getCoffeeData().size(); i++) {
                    Coffee coffee = InfoUtil.getInstance().getCoffeeData().getCoffeeData().get(i);
                    if (position == Integer.parseInt(InfoUtil.getInstance().getCoffeeData().getCoffeeData().get(i).getCatId())) {
                        aryCoffeeLst.add(new Coffee(coffee.getName(), coffee.getTime(), coffee.getDescription(), coffee.getIngredients(), coffee.getInstructions(), coffee.isFavorite(), coffee.getUrl(), coffee.getServe(), coffee.getCatId()));
                    }
                }


                if (position <= firstVisiblePosition || position >= lastVisiblePosition) {
                    mLlMgr.smoothScrollToPosition(mRvHeader, new RecyclerView.State(), position);
                }

                InfoUtil.getInstance().setSelectItemPosition(position);
                mRvHeader.smoothScrollToPosition(position);
                mAdpCatCoffee.setSelectPosition(position);
                mAryCoffee.clear();
                mAryCoffee.addAll(aryCoffeeLst);
            }


            mAdpCatCoffee.notifyDataSetChanged();
            mAdpCoffee.notifyDataSetChanged();
        }
    }
}
