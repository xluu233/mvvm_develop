package com.example.baselibrary.utils.view

import android.view.View

internal const val NO_GETTER: String = "Property does not have a getter"


internal fun noGetter(): Nothing = throw NotImplementedError(NO_GETTER)

internal var View.isAddedMarginTop: Boolean? by viewTags(-101)
internal var View.isAddedPaddingTop: Boolean? by viewTags(-102)
internal var View.isAddedMarginBottom: Boolean? by viewTags(-103)
internal var View.lastClickTime: Long? by viewTags(-104)
