package com.example.myapplication.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.adapter.CoffeeAdapter;
import com.example.myapplication.adapter.CoffeeCategoryAdapter;
import com.example.myapplication.adapter.CoffeeFavAdapter;
import com.example.myapplication.component.FrBase;
import com.example.myapplication.component.InfoUtil;
import com.example.myapplication.component.NoScrollGridLayoutManager;
import com.example.myapplication.datahelper.CoffeeDataHelper;
import com.example.myapplication.model.Coffee;
import com.example.myapplication.model.CoffeeCategory;

import java.lang.reflect.Array;
import java.time.chrono.MinguoDate;
import java.util.ArrayList;

public class FavoriteFragment extends FrBase implements CoffeeAdapter.Listener, CoffeeCategoryAdapter.Listener, CoffeeFavAdapter.Listener {
    private final String SAVE_DATA_STATE = "SAVE_DATA_STATE";
    private RecyclerView mRvFavorite;
    private CoffeeFavAdapter mAdpCoffee;
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

        if(InfoUtil.getInstance().getCoffeeFav() !=null){
            ArrayList<Coffee> aryCoff = new ArrayList<>();
            for (Coffee coffee : InfoUtil.getInstance().getCoffeeFav()) {
                if (coffee.isFavorite()) {
                    aryCoff.add(new Coffee(coffee.getName(), coffee.getTime(), "", coffee.getIngredients(), coffee.getInstructions(), coffee.isFavorite(), coffee.getUrl()));
                }
            }
            mDataHelper.setCoffeeFav(aryCoff);
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
        mAdpCoffee = new CoffeeFavAdapter(mContext, mDataHelper.getCoffeeFav(), this);
        mRvFavorite.setLayoutManager(mGlMgr);
        mRvFavorite.setAdapter(mAdpCoffee);


        mAdpCoffee.notifyDataSetChanged();
    }

    @Override
    public void onHeaderClick(int iPosition) {

    }

    @Override
    public void onBodyClick(int iPosition) {

    }

    @Override
    public void onFavClick(int iPosition) {
        Coffee coffee = mDataHelper.getCoffeeFav().get(iPosition);
        if (coffee.isFavorite()) {
            coffee.setFavorite(false);
        } else {
            coffee.setFavorite(true);
        }

        for (Coffee coff : InfoUtil.getInstance().getCoffeeData().getCoffeeData()) {
            if (coffee.getName().matches(coff.getName())) {
                coff.setFavorite(coffee.isFavorite());
            }
        }

        InfoUtil.getInstance().setCoffeeFav(InfoUtil.getInstance().getCoffeeData().getCoffeeData());
        InfoUtil.getInstance().setCoffee(InfoUtil.getInstance().getCoffeeData().getCoffeeData());

        mAdpCoffee.notifyDataSetChanged();
    }

}
