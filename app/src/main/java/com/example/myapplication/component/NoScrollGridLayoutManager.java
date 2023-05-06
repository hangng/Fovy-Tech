package com.example.myapplication.component;

import android.content.Context;

import androidx.recyclerview.widget.GridLayoutManager;

public class NoScrollGridLayoutManager extends GridLayoutManager {
    public NoScrollGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    @Override
    public boolean canScrollHorizontally() {
        return false;
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }
}
