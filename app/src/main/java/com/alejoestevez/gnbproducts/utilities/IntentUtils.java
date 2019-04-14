package com.alejoestevez.gnbproducts.utilities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.alejoestevez.gnbproducts.view.ui.activities.MainActivity;
import com.alejoestevez.gnbproducts.view.ui.activities.ProductActivity;

import androidx.appcompat.app.AppCompatActivity;

public class IntentUtils extends BaseIntentUtils {

    public static void goToMainActivity(Activity activity) {
        launchIntentAndFinish(activity, MainActivity.class);
    }

    public static void goToProductActivity(Context context, Bundle params) {
        launchIntent((AppCompatActivity)context, ProductActivity.class, false, params);
    }

}
