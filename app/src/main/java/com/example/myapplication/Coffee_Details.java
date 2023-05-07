package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.component.InfoUtil;
import com.example.myapplication.model.Coffee;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class Coffee_Details extends AppCompatActivity implements View.OnClickListener {

    private ImageView mIvCoffee;
    private TextView mTvTime, mTvServe, mTvDecp, mTvName, mTvInstructions, mTvIngredients;
    private Coffee mCoffee;
    private View mVIngredients, mVInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee_details);

        mIvCoffee = findViewById(R.id.iv_item);
        mTvDecp = findViewById(R.id.tv_descp);
        mTvTime = findViewById(R.id.tv_time);
        mTvServe = findViewById(R.id.tv_serve);
        mTvName = findViewById(R.id.name);
        mTvInstructions = findViewById(R.id.tv_instructions);
        mTvIngredients = findViewById(R.id.tv_ingredients);
        mVIngredients = findViewById(R.id.div_ingredients);
        mVInstructions = findViewById(R.id.div_instructions);
        mTvIngredients.setOnClickListener(this);
        mTvInstructions.setOnClickListener(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onClick(View v) {
        if (mTvIngredients == v) {
            mVInstructions.setBackgroundColor(getColor(R.color.white_70));
            mVIngredients.setBackgroundColor(getColor(R.color.brown));
            mTvDecp.setText(mCoffee.getIngredients());
        } else if (mTvInstructions == v) {
            mVInstructions.setBackgroundColor(getColor(R.color.brown));
            mVIngredients.setBackgroundColor(getColor(R.color.white_70));
            mTvDecp.setText(mCoffee.getInstructions());
        }
    }

    private void initData() {
        mCoffee = InfoUtil.getInstance().getSelectCoffee();
        mTvDecp.setText(mCoffee.getIngredients());
        mTvName.setText(mCoffee.getName());
        mTvTime.setText(mCoffee.getTime());
        mTvServe.setText(getString(R.string.serves) + " " + mCoffee.getServe());

        String imageUrl = mCoffee.getUrl();
        Picasso.get().load(imageUrl).into(mIvCoffee);
        mIvCoffee.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }
}