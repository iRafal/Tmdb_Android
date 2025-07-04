package com.tmdb.ui.core.compose.resources

import android.R.array
import android.R.color
import android.R.dimen
import android.R.string
import android.R.style
import android.content.Context
import android.text.style.TextAppearanceSpan
import androidx.core.content.ContextCompat
import com.tmdb.ui.core.KoinAndroidTestRule
import com.tmdb.ui.core.R
import com.tmdb.ui.core.di.module.testAppModule
import org.junit.Rule
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ResourceManagerTest: KoinTest {
    private val context: Context by inject()
    private val resourceManager: ResourceManager by inject()

    @get:Rule
    val rule = KoinAndroidTestRule()

    @Test
    fun getString_returnsExpectedValue() {
        val result = resourceManager.getString(string.ok)
        assertEquals(expected = context.resources.getString(string.ok), actual = result)
    }

    @Test
    fun getFormattedString_returnsExpectedValue() {
        val result = resourceManager.getFormattedString(string.ok, *arrayOf("arg1"))
        assertEquals(expected = context.resources.getString(string.ok), actual = result)
    }

    @Test
    fun getQuantityString_returnsExpectedValue() {
        val quantity = 2
        val result = resourceManager.getQuantityString(R.plurals.begins_in_days, quantity, quantity)
        assertEquals(
            expected = context.resources.getQuantityString(R.plurals.begins_in_days, quantity, quantity),
            actual = result
        )
    }

    @Test
    fun getStringArray_returnsExpectedValue() {
        val result = resourceManager.getStringArray(array.emailAddressTypes)
        assertTrue(context.resources.getStringArray(array.emailAddressTypes).contentEquals(result))
    }

    @Test
    fun getColor_returnsExpectedValue() {
        val result = resourceManager.getColor(color.black)
        assertEquals(expected = ContextCompat.getColor(context, color.black), actual = result)
    }

    @Test
    fun getTextAppearance_returnsExpectedType() {
        val result = resourceManager.getTextAppearance(style.TextAppearance)
        assertTrue(areTextAppearanceSpansEqual(TextAppearanceSpan(context, style.TextAppearance), result))
    }

    @Test
    fun getDimension_returnsExpectedValue() {
        val result = resourceManager.getDimension(dimen.app_icon_size)
        assertEquals(expected = context.resources.getDimension(dimen.app_icon_size), actual = result)
    }

    private fun areTextAppearanceSpansEqual(span1: TextAppearanceSpan, span2: TextAppearanceSpan): Boolean {
        return span1.family == span2.family &&
                span1.textSize == span2.textSize &&
                span1.textColor == span2.textColor &&
                span1.textStyle == span2.textStyle &&
                span1.linkTextColor == span2.linkTextColor
    }
}
