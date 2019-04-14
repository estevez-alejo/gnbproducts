package com.alejoestevez.gnbproducts.view.ui.flows;

import com.alejoestevez.gnbproducts.R;
import com.alejoestevez.gnbproducts.utilities.Transition;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FlowBase {

    public static void swapFragment(AppCompatActivity activity, Fragment fragment, int container, Integer orientation) {
        if (fragment != null) {
            try {
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                if (orientation == Transition.FROM_RIGHT_TO_LEFT.getId()) {
                    ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
                } else if (orientation == Transition.FROM_LEFT_TO_RIGHT.getId()) {
                    ft.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right, R.anim.enter_from_right, R.anim.exit_to_left);
                }
                ft.addToBackStack(null);
                ft.replace(container, fragment);
                ft.commitAllowingStateLoss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
