package com.alejoestevez.gnbproducts.utilities;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.alejoestevez.gnbproducts.R;


public enum Transition {

    FROM_LEFT_TO_RIGHT(0, "Transition from left to right"),
    FROM_RIGHT_TO_LEFT(1, "Transition from right to left");

    private Integer id;
    private String description;

    Transition(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public static void setTextViewAnim(Context context, TextView textView) {
        Animation a = AnimationUtils.loadAnimation(context, R.anim.scale);
        a.reset();
        textView.clearAnimation();
        textView.startAnimation(a);
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.valueOf(this.id);
    }

}
