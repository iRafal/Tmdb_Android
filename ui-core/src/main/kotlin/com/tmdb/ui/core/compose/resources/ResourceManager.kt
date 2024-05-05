package com.tmdb.ui.core.compose.resources

import android.content.Context
import android.content.res.Resources
import android.text.style.TextAppearanceSpan
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat

interface ResourceManager {
    fun getString(@StringRes stringResId: Int): String

    fun getFormattedString(@StringRes stringResId: Int, vararg params: Any): String

    fun getQuantityString(@PluralsRes stringResId: Int, quantity: Int, vararg params: Any): String

    fun getStringArray(@ArrayRes stringArrayResId: Int): Array<String>

    fun getColor(@ColorRes colorRes: Int): Int

    fun getTextAppearance(@StyleRes styleRes: Int): TextAppearanceSpan

    fun getDimension(@DimenRes dimenRes: Int): Float
}

class ResourceManagerImpl(private val context: Context) : ResourceManager {
    private val resources: Resources
        get() = context.resources

    override fun getString(stringResId: Int): String = context.getString(stringResId)

    override fun getFormattedString(stringResId: Int, vararg params: Any): String =
        resources.getString(stringResId, *params)

    override fun getQuantityString(stringResId: Int, quantity: Int, vararg params: Any): String =
        resources.getQuantityString(stringResId, quantity, *params)

    override fun getStringArray(stringArrayResId: Int): Array<String> = resources.getStringArray(stringArrayResId)

    override fun getColor(colorRes: Int): Int = ContextCompat.getColor(context, colorRes)

    override fun getTextAppearance(styleRes: Int): TextAppearanceSpan = TextAppearanceSpan(context, styleRes)

    override fun getDimension(dimenRes: Int): Float = resources.getDimension(dimenRes)
}
