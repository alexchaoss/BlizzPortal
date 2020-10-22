package com.BlizzardArmory.util

import com.BlizzardArmory.BlizzardArmory
import junit.framework.TestCase
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class MetricConversionTest : TestCase() {

    @Test
    fun getDPMetric() {
        Assertions.assertEquals(100) { MetricConversion.getDPMetric(10, BlizzardArmory.instance?.applicationContext!!) }
    }
}