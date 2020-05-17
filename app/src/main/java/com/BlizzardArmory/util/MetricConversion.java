package com.BlizzardArmory.util;

import android.app.Activity;

/**
 * The type Metric conversion.
 */
public class MetricConversion {

    /**
     * Gets dp metric.
     *
     * @param size     the size
     * @param activity the activity
     * @return the dp metric
     */
    public static int getDPMetric(int size, Activity activity) {
        final float scale = activity.getResources().getDisplayMetrics().density;
        return (int) (size * scale + 0.5f);
    }
}
