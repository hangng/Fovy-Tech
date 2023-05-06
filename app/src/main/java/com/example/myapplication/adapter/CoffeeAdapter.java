package com.example.myapplication.adapter;


import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.model.Coffee;

import java.util.ArrayList;


public class CoffeeAdapter extends RecyclerView.Adapter<CoffeeAdapter.ViewHolder> {
    private ArrayList<Coffee> mAryLst = new ArrayList<>();
    private Context mContext;
    private Listener mCallBack;


    public CoffeeAdapter(Context context, ArrayList<Coffee> aryLst, Listener callBack) {
        mAryLst = aryLst;
        mContext = context;
        mCallBack = callBack;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_body, parent, false);
        return new VHBody(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Coffee coffee = mAryLst.get(position);
        int itemType = holder.getItemViewType();

        VHBody vhBody = (VHBody) holder;
        vhBody.mTvContentTile.setText(coffee.getTitle());
        vhBody.mTvContentDescp.setText(coffee.getDescription());

        if (coffee.isFavorite()) {
            vhBody.mImgContentFavorite.setColorFilter(mContext.getColor(R.color.pink), PorterDuff.Mode.SRC_IN);
        } else {
            vhBody.mImgContentFavorite.setColorFilter(mContext.getColor(R.color.aqua), PorterDuff.Mode.SRC_IN);
        }

        vhBody.mRlBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onBodyClick(holder.getAdapterPosition());
            }
        });
        vhBody.mImgContentFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onFavClick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return mAryLst.get(position).getSectionId();
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


    class VHBody extends ViewHolder {


        private TextView mTvContentTile, mTvContentDescp;
        private ImageView mImgFavorite, mImgContentFavorite;
        private RelativeLayout mRlBody;


        public VHBody(View itemView) {
            super(itemView);
            mTvContentTile = itemView.findViewById(R.id.tv_content_title);
            mTvContentDescp = itemView.findViewById(R.id.tv_content_descp);
            mImgFavorite = itemView.findViewById(R.id.iv_favorite);
            mImgContentFavorite = itemView.findViewById(R.id.iv_content_favorite);
            mRlBody = itemView.findViewById(R.id.rl_body);

        }
    }


    public interface Listener {
        void onBodyClick(int iPosition);
        void onFavClick(int iPosition);
    }

}
