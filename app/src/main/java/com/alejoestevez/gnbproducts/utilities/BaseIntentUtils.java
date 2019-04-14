package com.alejoestevez.gnbproducts.utilities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.alejoestevez.gnbproducts.R;

public class BaseIntentUtils {

    private static AppApplication appInstance = AppApplication.getInstance();

    protected static <T> Intent launchIntentAndFinish(Activity activity, Class<T> className) {
        return launchIntent(activity, className, true, null);
    }

    protected static <T> Intent launchIntent(Activity activity, Class<T> className, boolean finish, Bundle params) {
        Intent intent = new Intent(appInstance, className);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (params != null) {
            intent.putExtras(params);
        }
        if (activity != null) {
            activity.startActivity(intent);

            if (finish) {
                activity.finish();
            }
            activity.overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
        }
        return intent;
    }

    protected static <T> Intent launchIntent(Context context, Class<T> className, boolean finish, Bundle params) {
        Intent intent = new Intent(appInstance, className);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        if (params != null) {
            intent.putExtras(params);
        }
        if (context != null) {
            context.startActivity(intent);
        }
        return intent;
    }

}