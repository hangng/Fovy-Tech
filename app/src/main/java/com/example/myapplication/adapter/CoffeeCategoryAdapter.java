package com.example.myapplication.adapter;


import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.CoffeeCategory;

import java.util.ArrayList;

public class CoffeeCategoryAdapter extends RecyclerView.Adapter<CoffeeCategoryAdapter.ViewHolder> {
    private ArrayList<CoffeeCategory> mAryLst;
    private Context mContext;
    private Listener mCallBack;
    private int mSelectPosition = -1;

    public CoffeeCategoryAdapter(Context context, ArrayList<CoffeeCategory> aryLst, Listener callBack) {
        mAryLst = aryLst;
        mContext = context;
        mCallBack = callBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header, parent, false);
        return new VHTitle(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final CoffeeCategory coffeeCategory = mAryLst.get(position);
        int itemType = holder.getItemViewType();

        VHTitle vhTitle = (VHTitle) holder;
        vhTitle.mTvTitle.setText(coffeeCategory.getName());
        if (mSelectPosition == -1) {
            mSelectPosition = 0;
            vhTitle.mTvTitle.setTypeface(null, Typeface.BOLD);
            vhTitle.mTvTitle.setTextColor(mContext.getColor(R.color.brown));
        } else if (mSelectPosition == holder.getAdapterPosition()) {
            vhTitle.mTvTitle.setTypeface(null, Typeface.BOLD);
            vhTitle.mTvTitle.setTextColor(mContext.getColor(R.color.brown));
        } else {
            vhTitle.mTvTitle.setTextColor(mContext.getColor(R.color.black));
            vhTitle.mTvTitle.setTypeface(null, Typeface.NORMAL);
        }
        vhTitle.mRlItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onHeaderClick(holder.getAdapterPosition());
            }
        });


    }


    @Override
    public int getItemCount() {
        return mAryLst.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    class VHTitle extends ViewHolder {
        private TextView mTvTitle;
        private ImageView mIvItem;
        private RelativeLayout mRlItem;

        public VHTitle(View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_title);
            mIvItem = itemView.findViewById(R.id.iv_item);
            mRlItem = itemView.findViewById(R.id.rl_item);
        }
    }


    public interface Listener {
        void onHeaderClick(int iPosition);
    }


    public void setSelectPosition(int selectPosition) {
        this.mSelectPosition = selectPosition;
    }
}
