package com.BlizzardArmory.ui;

import android.app.Activity;

public class MetricConversion {

    public static int getDPMetric(int size, Activity activity) {
        final float scale = activity.getResources().getDisplayMetrics().density;
        return (int) (size * scale + 0.5f);
    }
}
