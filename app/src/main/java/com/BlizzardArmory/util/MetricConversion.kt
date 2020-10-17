package com.BlizzardArmory.util

import android.content.Context

/**
 * The type Metric conversion.
 */
object MetricConversion {
    /**
     * Gets dp metric.
     *
     * @param size     the size
     * @param context the activity
     * @return the dp metric
     */
    @JvmStatic
    fun getDPMetric(size: Int, context: Context): Int {
        val scale = context.resources.displayMetrics.density
        return (size * scale + 0.5f).toInt()
    }
}