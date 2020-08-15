package com.BlizzardArmory.util

import android.app.Activity

/**
 * The type Metric conversion.
 */
object MetricConversion {
    /**
     * Gets dp metric.
     *
     * @param size     the size
     * @param activity the activity
     * @return the dp metric
     */
    @JvmStatic
    fun getDPMetric(size: Int, activity: Activity): Int {
        val scale = activity.resources.displayMetrics.density
        return (size * scale + 0.5f).toInt()
    }
}