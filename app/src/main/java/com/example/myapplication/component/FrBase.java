package com.example.myapplication.component;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;

public class FrBase extends Fragment {
    public View mRootView;
    public Context mContext;
    public Activity mActivity;
    public Resources mResources;
    public Handler mHandler;
    private static long mlLastClickTime;

    protected boolean mbShowBackButton = true;


    public FrBase() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler = new Handler();
        setHasOptionsMenu(true);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, int iLayoutId, int titleResId) {
        mContext = container != null ? container.getContext() : getActivity();

        mRootView = inflater.inflate(iLayoutId, container, false);
        return mRootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mContext = mActivity = activity;
        mResources = activity.getResources();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {

        if(mbShowBackButton) {
            MenuItem item = menu.add("");
            item.setIcon(androidx.appcompat.R.drawable.abc_ic_ab_back_material);
            item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
            item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    mActivity.onBackPressed();
                    return true;
                }
            });
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (Math.abs(time - mlLastClickTime) < 1000) {
            return true;
        }
        mlLastClickTime = time;
        return false;
    }
}