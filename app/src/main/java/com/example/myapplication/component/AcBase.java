package com.example.myapplication.component;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.myapplication.R;

public class AcBase extends AppCompatActivity implements View.OnClickListener {
    private static long mlLastClickTime;
    private static int miLastClickViewId;
    private final String INFOUTIL_TAG = "infoutil";

    public void replaceFragment(int tag, Fragment fragment) {
        FragmentTransaction frTransaction = getSupportFragmentManager().beginTransaction();
        frTransaction.setCustomAnimations(R.anim.fade_in, android.R.animator.fade_out, android.R.animator.fade_in, R.anim.fade_out);
        frTransaction.addToBackStack(getString(tag));
        frTransaction.replace(R.id.fragment_container, fragment, getString(tag));

        frTransaction.commit();
    }


    public static boolean isFastDoubleClick(View view) {
        long time = System.currentTimeMillis();

        if (view.getId() == miLastClickViewId && Math.abs(time - mlLastClickTime) < 500) {
            return true;
        }
        mlLastClickTime = time;
        miLastClickViewId = view.getId();
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState !=null){
            InfoUtil.setInfoUtil((InfoUtil) savedInstanceState.getSerializable(INFOUTIL_TAG));
        }
    }

    @Override
    public void onClick(View view) {
        if (isFastDoubleClick(view)) {
            return;
        }
    }

    public void clearStack() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }

}
